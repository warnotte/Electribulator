package org.warnotte.elecribulator.CControlers;
import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

import org.warnotte.OBJ2GUI.Annotations.GUI_CLASS;
import org.warnotte.elecribulator.Multi.evt;
import org.warnotte.elecribulator.PresetManager.ControlValue;
import org.warnotte.elecribulator.PresetManager.PresetManager_Synth;
import org.warnotte.waxaudiomiditools.CControlers.Gateable;
import org.warnotte.waxaudiomiditools.CControlers.KnowMidiList;
import org.warnotte.waxaudiomiditools.CControlers.SignGenBase;
import org.warnotte.waxaudiomiditools.CControlers.SignGen_VCA;
import org.warnotte.waxaudiomiditools.CControlers.SignGen_VCA_2ND;
import org.warnotte.waxaudiomiditools.CControlers.SignGen_VCO;
import org.warnotte.waxaudiomiditools.CControlers.Syncable;
import org.warnotte.waxlibswingcomponents.Utils.Curve.Copiable;

@GUI_CLASS(type=GUI_CLASS.Type.BoxLayout, BoxLayout_property=GUI_CLASS.Type_BoxLayout.Y)
public class CCManager implements Syncable
{
	static boolean BypassModulators =  false;
	ArrayList<SignGenBase> gens = new ArrayList<SignGenBase>();
	//@GUI_FIELD_TYPE(type=GUI_FIELD_TYPE.Type.TEXTFIELD)
	int Channel = 1;
	transient private PresetManager_Synth pm = null;

	private Receiver receiver ;
	
	public synchronized int getChannel() {
		return Channel;
	}
	
	public void copysettings(CCManager to)
	{
		for (int i = 0; i < gens.size(); i++) {
			Copiable sourcegen = gens.get(i);
			Copiable destgen = to.getGens().get(i);
			
			sourcegen.copysettings(destgen);
		}
	}

	public CCManager(PresetManager_Synth pm)
	{
		this.setPm(pm);
		SignGen_VCA_2ND sgC3 = new SignGen_VCA_2ND();
		SignGen_VCA_2ND sgC2 = new SignGen_VCA_2ND();
		SignGen_VCA_2ND sgC1 = new SignGen_VCA_2ND();
		
		SignGen_VCA sgA = new SignGen_VCA();
		SignGen_VCA sgB = new SignGen_VCA();
		SignGen_VCO sg1 = new SignGen_VCO();
		SignGen_VCO sg2 = new SignGen_VCO();
		SignGen_VCO sg3 = new SignGen_VCO();
		sg1.setFreq(1f);
		sg2.setFreq(2f);
		sg3.setFreq(4f);
		
		sg1.setCC(10);
		sg2.setCC(10);
		sg3.setCC(10);
		
		sgB.setCC(74);
		sgB.setBypassSend(true);
		sgB.setSa_time(500);
		sgB.setSd_time(500);
		sgB.setSr_level(1.0f);
		sgB.setSr_time(250);
		
		sgC1.setCC(7);
		sgC1.setBypassSend(true);
		sgC1.setSa_time(500);
		sgC1.setSs_time(1000);
		sgC1.setSr_time(1000);
		
		sgC2.setCC(74);
		sgC2.setBypassSend(true);
		sgC2.setSa_time(500);
		sgC2.setSs_time(1000);
		sgC2.setSr_time(1000);
		
		sgC3.setCC(10);
		sgC3.setBypassSend(true);
		sgC3.setSa_time(500);
		sgC3.setSs_time(1000);
		sgC3.setSr_time(1000);
		
		gens.add(sgC1);
		gens.add(sgC2);
		gens.add(sgC3);
		gens.add(sgA);
		gens.add(sgB);
		
		gens.add(sg1);
		gens.add(sg2);
		gens.add(sg3);
		
		
	}
	
	long prev;
	public synchronized void evolue(boolean printInfos)
	{
		// Si on bypass l'usage des modulateur alors on ne fait rien ici :)
		if (CCManager.BypassModulators==true)
			return;
		
		for (int i = 0; i < gens.size(); i++)
		{
			long time = System.currentTimeMillis();
		
			SignGenBase sg = gens.get(i);
			
			if (sg.isBypassSend()==false)
			{
			if (getPm()!=null)
			{
			
			ControlValue cv = getPm().getPresetByCC(sg.getCC());
			if (cv!=null)
			{
				float valeur_preset = cv.getValue()/127.0f;
				
				sg.evolue(Channel, time,valeur_preset);
				
				sendSignalToReceiver(Channel,sg.getCC(), (int) (sg.getValue()*127f));
				
				if (printInfos==true)
				{
					float val = sg.getValue()*1;
					float inttime = sg.getInternalTime();
					System.err.printf("%1d %1.2f %6.2f \r\n",i,val,inttime);
				}
			}
			}
			}
		}
		
	}
	
	
	public void sendSignalToReceiver(int Channel, int CC,  int valeur) // 0 a 127  
	{
		if (BypassModulators == false)
		{
			if (valeur != old_value)
			{
				send(Channel, CC, valeur);
				old_value = valeur;
			}
		}
	}

	int old_value;
	
	  
	public void send(int Channel, int CC, int value)
	{
		if (receiver!=null)
		{
			ShortMessage message = new ShortMessage();
			try
			{
				
				message.setMessage(ShortMessage.CONTROL_CHANGE, evt.getOutputChannels()[Channel-1], CC, value);
				receiver.send(message, -1);
			} 
			catch (java.lang.IllegalStateException e)
			{
				System.err.println("C'est bizarre ceci");
				e.printStackTrace();
			}
			catch (InvalidMidiDataException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	
	public static void main(String args[]) throws Exception
	{
		KnowMidiList kml = new KnowMidiList();
		kml.init_SYNTH();
		CCManager m = new CCManager(new PresetManager_Synth(new evt(), kml));
		
		Thread_Modulateurs t = new Thread_Modulateurs(m, 10);
		t.start();
		
		for (int i = 0; i < 10; i++)
		{
			try
			{
					Thread.sleep(4000);
					
					m.gens.get(0).sync();
					System.err.println("Syncing");
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		t.setFinish(true);
		
	}

	public synchronized ArrayList<SignGenBase> getGens()
	{
		return gens;
	}

	public synchronized void setGens(ArrayList<SignGenBase> gens)
	{
		this.gens = gens;
	}

	
	
	public synchronized void sync()
	{
		for (int i = 0; i < gens.size(); i++)
		{
			SignGenBase sg = gens.get(i);
			sg.sync();
		}
	}

	public void gate(boolean valeur) {
		for (int i = 0; i < gens.size(); i++)
		{
			if (gens.get(i) instanceof Gateable)
			{
				Gateable sg = (Gateable)gens.get(i);
				sg.gate(valeur);
			}
		}
	}

	public void setReceiver(Receiver receiver) {
		this.receiver=receiver;
		
	}

	public void setChannel(int CC) {
		Channel=CC;
	}
	public void setCC(int CC) {
		for (int i = 0; i < gens.size(); i++)
		{
			SignGenBase sg = gens.get(i);
			sg.setCC(CC);
		}
	}

	public void setPm(PresetManager_Synth pm) {
		this.pm = pm;
	}

	public PresetManager_Synth getPm() {
		return pm;
	}

	public static synchronized boolean isBypassModulators() {
		return BypassModulators;
	}

	public static synchronized void setBypassModulators(boolean bypassModulators) {
		BypassModulators = bypassModulators;
	}
	
}
