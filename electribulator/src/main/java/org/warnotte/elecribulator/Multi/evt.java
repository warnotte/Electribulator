package org.warnotte.elecribulator.Multi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Transmitter;
import javax.swing.event.EventListenerList;

import org.warnotte.elecribulator.CControlers.CCManager;
import org.warnotte.elecribulator.CControlers.Thread_Modulateurs;
import org.warnotte.elecribulator.DTOs.Projet;
import org.warnotte.elecribulator.Multi.Evt_Event.Refresh_MSG;
import org.warnotte.elecribulator.PresetManager.PresetManager_Drum;
import org.warnotte.elecribulator.PresetManager.PresetManager_Synth;
import org.warnotte.elecribulator.Utils.Utils;
import org.warnotte.waxaudiomiditools.CControlers.KnowMidiList;
import org.warnotte.waxaudiomiditools.MidiConfig.ConfigMIDIINPUT;
import org.warnotte.waxaudiomiditools.MidiConfig.ConfigMIDIOUTPUT;

import com.thoughtworks.xstream.XStream;

import io.github.warnotte.waxlib3.core.Updater.Updater;
import io.github.warnotte.waxlib3.core.Updater.Version;
import io.github.warnotte.waxlib3.waxlibswingcomponents.Dialog.DialogDivers;

public class evt extends Thread implements	Receiver 
{
	public static Receiver _Receiver = null; 
	Transmitter _Transmitter = null;
	electribe_receiver elec_Reic = null; 
	
	List<MidiMessage> stack = null;
	
	HashMap<Integer, Integer> usedNotes = new HashMap<Integer, Integer>();
	
	boolean ForceSendNoteOFF = false;
	boolean PolyGameEnable []= new boolean[] {true, false, false, false, false};
	int     PolyGameNoteValue []= new int[] {0, -24, -12, 12, 24};

	private int	usedGen;

	ArrayList<CCManager> modulateurs = new ArrayList<CCManager>();
	ArrayList<Thread_Arpegiateur> thr_arpegiateurs = new ArrayList<Thread_Arpegiateur>();

	int Arpege_Note[] = new int[]{-1,-1,-1,-1,-1};

	ArrayList<Thread_Modulateurs> threads = new ArrayList<Thread_Modulateurs>();
	
	PresetManager_Synth pm_synth;
	KnowMidiList kml_synth = new KnowMidiList();
	PresetManager_Drum pm_drum;
	KnowMidiList kml_drum = new KnowMidiList();
	//PresetManager_Drum pm_synth;
	
	boolean NoteDispatchingOrDirectResentWithVCA = true; // Pour monophonisé l'affaire.

	

	private int USEDGEN;
	private final boolean method2=true; // Si false alors y'aun leger bug en fait.
	
	Properties prop = new Properties();
	private boolean Finished;
	private boolean ArpegiatorEnabled=false;
	private int RecordingInputToArpegiator=-1;
	private int RecordingInputToArpegiatorIndex;
	private final EventListenerList xxxXListeners= new EventListenerList();
	private File loaded_modulateur;
	private File loaded_presets;
	private File loaded_projet;
	private File loaded_drum_presets;
	private boolean ApplyPresetOnLoad=true;
	private int DrumChannel=0x09;
	
	private boolean SetTransmitterLevelToMaximum=false; // Permet de mettre le volume a 127 des notes venant du clavier.
	private long lastRecordedNoteTime;
	public enum RecordMode {SIMPLE, CORRECT};
	private RecordMode recordMode=RecordMode.CORRECT;
	
	private static int OutputChannels[] = new int[] {3,4,5,6,7};
	
	public synchronized static int[] getOutputChannels() {
		return OutputChannels;
	}

	public synchronized void setOutputChannels(int[] outputChannels) {
		OutputChannels = outputChannels;
	}

	public evt() throws Exception
	{
		
	
		//checkandGetForNewVersion();
		
		stack = new ArrayList<MidiMessage>(); // Touches entrées par le MIDI IN. (ou le clavier emulé).
		stack = Collections.synchronizedList(stack);
		_Transmitter=ConfigMIDIINPUT.createWindow(_Transmitter);
		_Transmitter.setReceiver(this);
		_Receiver=ConfigMIDIOUTPUT.createWindow(_Receiver);
		elec_Reic = new electribe_receiver(this);
		
		prop = new Properties();
		prop.load(new FileInputStream(new File("config"+File.separator+"config.properties")));
		
		DrumChannel = new Integer(""+prop.getProperty("DrumChannel"))-1; //? -1 ?
		
		readMidiDispatch();
		
		create_new();
		
		this.start();
		this.setName("Thread polyphonisateur");
		
		try {
			loadPresets("saves"+File.separator+"presets"+File.separator+"0_0_8_Echoes.xml");
			load       ("saves"+File.separator+"modulateurs"+File.separator+"0_0_8_Echoes.xml");
		} catch (Exception e) {
			e.printStackTrace();
			
			DialogDivers.Show_dialog(e, "Warning, I cannot found "+"saves"+File.separator+"modulateurs"+File.separator+"0_0_8_Echoes.xml so no default preset is loaded");
		}
	}
	
	private void readMidiDispatch() throws Exception {
		String value = prop.getProperty("SynthOutputChannel");
		if (value==null)
			throw new Exception("Couldn't find value [SynthOutputChannel] inside properties file");
		String values[]= value.split(",");
		if (values.length!=5) 
			throw new Exception("Incorrect values in SynthOutputChannel inside properties file");
		for (int i = 0; i < values.length; i++) {
			int chan = new Integer(""+values[i])-1;
			System.err.println("Synth "+i+" on chan "+(chan+1));
			OutputChannels[i]=chan;
		}
		
		
	}

	public void copySettingsToAllModulateurs()
	{
		CCManager source = modulateurs.get(0);
		for (int i = 1; i < modulateurs.size(); i++) {
			CCManager dest = modulateurs.get(i);
			source.copysettings(dest);
		}
	}
	
	public static void checkandGetForNewVersion()
	{
		Updater update = new Updater();
		String url = "http://renaud.warnotte.be/Projects/Electribulator/maj_electribulator.xml";
		if (update.isNewVersionAivalable(url,Version.getShortVersionString())==true)
			update.update(url.toString(),Version.getShortVersionString());
		else
			System.err.println("Nothing new");
	}
	
	public static boolean checkForNewVersion()
	{
		Updater update = new Updater();
		String url = "http://renaud.warnotte.be/Projects/Electribulator/maj_electribulator.xml";
		return update.isNewVersionAivalable(url,Version.getShortVersionString());
			
	}
	
	

	public void killThread_Modulateurs() throws InterruptedException
	{
		for (int i = 0; i < threads.size(); i++) {
			Thread_Modulateurs tm = threads.get(i);
			tm.setFinish(true);
			tm.join();
		}
	}
	
	
	
	public void creeThread_Modulateurs() {
		for (int i = 0; i < modulateurs.size(); i++) {
			CCManager cc= modulateurs.get(i);
			cc.setReceiver(_Receiver);
			cc.setPm(pm_synth);
			long sleeplen = new Long(""+prop.get("ModulateurPauseTime"));
		//	System.err.println("ModulateurPauseTime == "+sleeplen);
			Thread_Modulateurs t = new Thread_Modulateurs(cc, (int)sleeplen);
			t.start();
			threads.add(t);
		}
	}
	
	public void creeThread_Arpegiateurs() {
		for (int i = 0; i < modulateurs.size(); i++) {
			Thread_Arpegiateur t = new Thread_Arpegiateur(this);
			t.setName("Arpegiator Thread "+i);
			t.start();
			thr_arpegiateurs.add(t);
		}
	}

	
	
	@Override
	public void run()
	{
		Finished=false;
		while(isFinished()==false)
		{
			evolue();
		}
	}
	
	
	public void evolue() {
		synchronized(this)
		{
			try {
			//	System.err.println("EVT waiting");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	//	System.err.println("EVT stack size "+stack.size());
		while(stack.size()>0)
		{
			MidiMessage message = stack.get(0);
			//System.err.println("Message "+message.toString());
			ShortMessage sm = (ShortMessage)message;
			
			//System.err.println("Message ch="+sm.getChannel()+ " cmd="+sm.getCommand()+ " note="+sm.getData1());
			
			if ((sm.getCommand()==ShortMessage.NOTE_OFF) || ((sm.getData2()==0)&&(sm.getCommand()==ShortMessage.NOTE_ON)))
			{
				int note = sm.getData1();
				
				if (usedNotes==null)
				{
			//		System.err.println("putain");
					continue;
				}
				Integer g = usedNotes.get(note);
				if (g==null)
				{
					stack.remove(0);
					continue;
				}
				int idx = usedNotes.get(note);
				
				//idx = sm.getChannel();
				
				if (idx==-1){
				//	System.err.println("Merde");
					System.exit(-1);
				}
				

				if (ForceSendNoteOFF==true)
				{
					// Envoie d'abord un note OFF (utiliser en cas de VCA avec release).
					try
					{
						((ShortMessage)message).setMessage(ShortMessage.NOTE_ON, getOutputChannels()[idx], note, 0);
						_Receiver.send(message, -1);
						((ShortMessage)message).setMessage(ShortMessage.NOTE_OFF, getOutputChannels()[idx], note, 0);
						_Receiver.send(message, -1);
						
					}
					catch (InvalidMidiDataException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				
				usedNotes.remove(note);
				modulateurs.get(idx).gate(false);
				//debugNotes();
				
			//	usedGen --;
			}
			else
				if (sm.getCommand()==ShortMessage.NOTE_ON)
				{
					int note = sm.getData1();
				int idx = usedNotes.size();
				// IDX Nombre totale des generateur utilisés 
				if (idx<=4)
				{
					if (NoteDispatchingOrDirectResentWithVCA==true)
					{
					if (method2==true)
					{
						idx = USEDGEN;
						int maxTry = 50;
						while ((usedNotes.containsValue(idx)==true) && (maxTry!=0))
						{
							
							idx++;
							if (idx>=5)
								idx=0;
							maxTry--;
						}
				//		System.err.println("Maxtry=="+maxTry);
						if ((maxTry==0) || (idx>=5))
						{
							stack.remove(0);
					//		System.err.println("Remove");
							continue;
						}
						
					}
					}
					else
						idx = getIndexOfChannel(sm.getChannel());
						//idx = sm.getChannel();
					
					if (idx==-1)
					{
						System.err.println("This is not a good channel dude!!!");
					}
					else
					{
					usedNotes.put(note,idx);
					modulateurs.get(idx).sync();
					modulateurs.get(idx).gate(true);
					
					
					// Envoie d'abord un note OFF (utiliser en cas de VCA avec release).
					try
					{
						((ShortMessage)message).setMessage(sm.getCommand(), getOutputChannels()[idx], sm.getData1(), sm.getData2());
					}
					catch (InvalidMidiDataException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					_Receiver.send(message, -1);
					
					try
					{
						((ShortMessage)message).setMessage(sm.getCommand(), getOutputChannels()[idx]/*idx*/, sm.getData1(), sm.getData2());
						//System.err.println("ON "+idx);
					//	debugNotes();
					} catch (InvalidMidiDataException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					_Receiver.send(message, -1);
					
					USEDGEN=idx;
					if (method2==true)
					{
						USEDGEN++;
						if (USEDGEN>=5) USEDGEN=0;
					}
					}
				}
			}
			else
			{
				// CC etc ...
				stack.remove(0);
				continue;
			}
			stack.remove(0);
		}
		
	}

	public synchronized boolean isNoteDispatchingOrDirectResentWithVCA() {
		return NoteDispatchingOrDirectResentWithVCA;
	}

	public synchronized void setNoteDispatchingOrDirectResentWithVCA(boolean noteDispatchingOrDirectResentWithVCA) {
		NoteDispatchingOrDirectResentWithVCA = noteDispatchingOrDirectResentWithVCA;
	}

	private int getIndexOfChannel(int channel) {
		for (int i = 0; i < OutputChannels.length; i++) {
			if (OutputChannels[i]==channel) return i;
		}
		return -1;
	}

	public void evolue2()
	{
		synchronized(this)
		{
		try {
		//	System.err.println("EVT waiting");
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	//	System.err.println("EVT stack size "+stack.size());
		while(stack.size()>0)
		{
			MidiMessage message = stack.get(0);
			System.err.println("Message "+message.toString());
			ShortMessage sm = (ShortMessage)message;
			
			System.err.println("Message ch="+sm.getChannel()+ " cmd="+sm.getCommand()+ " note="+sm.getData1());
			
			if ((sm.getCommand()==ShortMessage.NOTE_OFF) || ((sm.getData2()==0)&&(sm.getCommand()==ShortMessage.NOTE_ON)))
			{
				int note = sm.getData1();
				
				if (usedNotes==null)
				{
			//		System.err.println("putain");
					continue;
				}
				Integer g = usedNotes.get(note);
				if (g==null)
				{
					stack.remove(0);
					continue;
				}
				int idx = usedNotes.get(note);
				if (idx==-1){
				//	System.err.println("Merde");
					System.exit(-1);
				}
				
				usedNotes.remove(note);
				modulateurs.get(idx).gate(false);
				debugNotes();
				
			//	usedGen --;
			}
			else
			if (sm.getCommand()==ShortMessage.NOTE_ON)
			{
				int note = sm.getData1();
				int idx = usedNotes.size();
				// IDX Nombre totale des generateur utilisés 
				if (idx<=4)
				{
					if (method2==true)
					{
						idx = USEDGEN;
						int maxTry = 50;
						while ((usedNotes.containsValue(idx)==true) && (maxTry!=0))
						{
							
							idx++;
							if (idx>=5)
								idx=0;
							maxTry--;
						}
				//		System.err.println("Maxtry=="+maxTry);
						if ((maxTry==0) || (idx>=5))
						{
							stack.remove(0);
					//		System.err.println("Remove");
							continue;
						}
						
					}
					
					
					usedNotes.put(note,idx);
					modulateurs.get(idx).sync();
					modulateurs.get(idx).gate(true);
					
					
					// Envoie d'abord un note OFF (utiliser en cas de VCA avec release).
					try
					{
						((ShortMessage)message).setMessage(sm.getCommand(), getOutputChannels()[idx], sm.getData1(), sm.getData2());
					//	System.err.println("OFF "+idx);
						
					} catch (InvalidMidiDataException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					_Receiver.send(message, -1);
					
					try
					{
						((ShortMessage)message).setMessage(sm.getCommand(), getOutputChannels()[idx]/*idx*/, sm.getData1(), sm.getData2());
						//System.err.println("ON "+idx);
						debugNotes();
					} catch (InvalidMidiDataException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					_Receiver.send(message, -1);
					
					USEDGEN=idx;
					if (method2==true)
					{
						USEDGEN++;
						if (USEDGEN>=5) USEDGEN=0;
					}
				}
			}
			else
			{
				// CC etc ...
				stack.remove(0);
				continue;
			}
			stack.remove(0);
		}
		
	}
	
	
	private void debugNotes() {
		Set<Integer> keys = usedNotes.keySet();
		
		Collection<Integer> values = usedNotes.values();
		
		for (int i = 0; i < 5; i++) {
			if (values.contains(i))
			System.err.print(" *");
			else
			System.err.print(" .");
		}
		System.err.println("");

		
	}


	public synchronized electribe_receiver getElec_Reic() {
		return elec_Reic;
	}

	public synchronized void setElec_Reic(electribe_receiver elec_Reic) {
		this.elec_Reic = elec_Reic;
	}

	
	public void close()
	{
		sendNoteOff();
		sendLvl0();
		_Receiver.close(); 
		_Transmitter.close();
		elec_Reic.close(); 
	}


	
	/**
	 * Recois un message par l'entrée (le clavier en thoerie).
	 */
	public void send(MidiMessage message, long timeStamp) {
		
		
		
	
	//	 System.err.println("* "+((ShortMessage) message).getCommand());
		if ((((ShortMessage) message).getCommand() == ShortMessage.NOTE_OFF) || (((ShortMessage) message).getCommand() == ShortMessage.NOTE_ON)) {
			
			// Si c'est pas pour les synth alors envoye tel quel.
			if (((ShortMessage) message).getChannel() == DrumChannel) {
				_Receiver.send(message, -1);
			} else {

				ShortMessage sm = (ShortMessage) message;				// Modifie le LEVEL pour le mettre a fond.
				
				if (isSetTransmitterLevelToMaximum()==true)
					if ((sm.getCommand()==ShortMessage.NOTE_ON) && (sm.getData2()!=0))
						
				{
					try {
						((ShortMessage) message).setMessage(sm.getCommand(), sm.getChannel(), sm.getData1(), 127);
					} catch (InvalidMidiDataException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
				
				// Mode normal sans arpegiateur magique.
				synchronized (this) {
					if (RecordingInputToArpegiator == -1) {
						if (ArpegiatorEnabled == false) {
							
						//	ShortMessage newmessage = new ShortMessage();

							sendMessageToStack(sm);

							// Avertit le thread de dispatching des notes vers
							// l'electribe que le stack a des new messages.
							// synchronized (this) {
							notify();
							// }

						} else {
							sendToArpegiators(message);
						}
					} else {
						
						if (recordMode==RecordMode.SIMPLE)
							recordToArpegiatorsSimple(sm);
						if (recordMode==RecordMode.CORRECT)
							recordToArpegiatorsCorrect(sm);
							
					}
				}
			}
		}
	}

	long startNoteRec = 0;
	long stopNoteRec = 0;
	long previousstartNoteRec = 0;
	private void recordToArpegiatorsCorrect(ShortMessage sm) {
		
		// TODO : Gerer le note OFF aussi pour avoir les durée et la durée du gate.
		Thread_Arpegiateur arp = thr_arpegiateurs.get(RecordingInputToArpegiator);
		
		if ((sm.getCommand()==ShortMessage.NOTE_ON) && (sm.getData2()!=0) && (getRecordingInputToArpegiatorIndex()<arp.getNoteSeqLength()))
		{
			
			
			
			startNoteRec = System.currentTimeMillis();
			
			arp.NoteSeq[getRecordingInputToArpegiatorIndex()]=sm.getData1();
			
			if (getRecordingInputToArpegiatorIndex()>0)
			{
				long sleeplen = startNoteRec-previousstartNoteRec;
				float ratio = -(float)(previousstartNoteRec-stopNoteRec)/sleeplen;
				System.err.println("SleepLen = "+sleeplen);
				System.err.println("Ratio    = "+ratio);
				arp.SleepLen[getRecordingInputToArpegiatorIndex()-1]=sleeplen;
				arp.GateLen[getRecordingInputToArpegiatorIndex()-1]=1.0f-ratio;
			}
			previousstartNoteRec = startNoteRec;
		}
		else
		if ((sm.getCommand()==ShortMessage.NOTE_OFF) || (sm.getData2()==0))
		{
			long noteLenght = System.currentTimeMillis()-startNoteRec;
			stopNoteRec = System.currentTimeMillis();
			
			// Si on a pas rempli tout les case on arrete pas.
			if (getRecordingInputToArpegiatorIndex()<arp.getNoteSeqLength())
			{
			//	arp.NoteSeq[getRecordingInputToArpegiatorIndex()]=sm.getData1();
				
				// si c'est pas le premier
			//	if (getRecordingInputToArpegiatorIndex()>0)
			//	{
			//		long elapsed = System.currentTimeMillis()-lastRecordedNoteTime;
			//		System.err.println("Elapsed = "+elapsed);
			//		arp.SleepLen[getRecordingInputToArpegiatorIndex()]=elapsed;
			//	}
				lastRecordedNoteTime = System.currentTimeMillis();
			}
			else
			{
				RecordingInputToArpegiator=-1;
				// Remets les notes en absolu.
				arp.absolutizeNotes();
				fireStgNeedRefresh(new Evt_Event(Refresh_MSG.RECORDING_ARPEGIATOR_FINISHED, this));
			}
			setRecordingInputToArpegiatorIndex(getRecordingInputToArpegiatorIndex() + 1);
		}
		
		sendMessageToStack(sm);

		// Avertit le thread de dispatching des notes vers
		// l'electribe que le stack a des new messages.
		// synchronized (this) {
		notify();
	}
	
	private void recordToArpegiatorsSimple(ShortMessage sm) {
		if ((sm.getCommand()==ShortMessage.NOTE_ON) && (sm.getData2()!=0))
		{
			// TODO : Gerer le note OFF aussi pour avoir les durée et la durée du gate.
			Thread_Arpegiateur arp = thr_arpegiateurs.get(RecordingInputToArpegiator);
			
			// Si on a pas rempli tout les case on arrete pas.
			if (getRecordingInputToArpegiatorIndex()<arp.getNoteSeqLength())
			{
				arp.NoteSeq[getRecordingInputToArpegiatorIndex()]=sm.getData1();
			
				// si c'est pas le premier
				if (getRecordingInputToArpegiatorIndex()>0)
				{
					long elapsed = System.currentTimeMillis()-lastRecordedNoteTime;
					System.err.println("Elapsed = "+elapsed);
					arp.SleepLen[getRecordingInputToArpegiatorIndex()]=elapsed;
				}
				lastRecordedNoteTime = System.currentTimeMillis();
			}
			else
			{
				RecordingInputToArpegiator=-1;
				// Remets les notes en absolu.
				arp.absolutizeNotes();
				fireStgNeedRefresh(new Evt_Event(Refresh_MSG.RECORDING_ARPEGIATOR_FINISHED, this));
			}
			setRecordingInputToArpegiatorIndex(getRecordingInputToArpegiatorIndex() + 1);
		}
		sendMessageToStack(sm);

		// Avertit le thread de dispatching des notes vers
		// l'electribe que le stack a des new messages.
		// synchronized (this) {
		notify();
	}
	
	void sendMessageToStack(ShortMessage sm) {
		for (int i = 0; i < PolyGameEnable.length; i++) {
			if (PolyGameEnable[i] == true) {
				ShortMessage newmessage = new ShortMessage();
				try {
					newmessage.setMessage(sm.getCommand(), sm.getChannel(), sm.getData1()+ PolyGameNoteValue[i], sm.getData2());
					addMessageToStack(newmessage);
					//stack.add(newmessage);
				} catch (InvalidMidiDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}

	public void sendToArpegiators(MidiMessage message) {
		ShortMessage sm = ((ShortMessage) message);
		int note = sm.getData1();
		try {
		if (((sm.getCommand()==ShortMessage.NOTE_OFF) || ((sm.getData2()==0)&&(sm.getCommand()==ShortMessage.NOTE_ON))))
		{
			Thread_Arpegiateur arp;
			
			arp = getArpegiatorByNote(note);
			int idx = getIndexArpegiatorByNote(note);
				if ((arp==null) || (idx==-1))
				{
					System.err.println("Curieux ca devrait pas arriver d'avoir ca");
				}
				else
				{
					Arpege_Note[idx]=-1;
					if (arp.isBypass()==false)
					arp.Off(sm);
					else
					{
						ShortMessage newmessage = new ShortMessage();
						newmessage.setMessage(sm.getCommand(), sm.getChannel(), sm.getData1(), sm.getData2());
						sendMessageToStack(newmessage);
					}
						
				}
		}
		else
		if (sm.getCommand()==ShortMessage.NOTE_ON)
		{
			if (getArpegiatorByNote(note)==null)
			{
			Thread_Arpegiateur arp = getFreeArpegiator(note);
			if (arp!=null)
			{
				if (arp.isBypass()==false)
				arp.On(sm);
				else
					sendMessageToStack(sm);
			}
			else
				System.err.println("No free arpegiator");
			}
			else
				System.err.println("2 eme fois un note ON ??????");
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	debug_arpegiator();
	}

	private int getIndexArpegiatorByNote(int note) {
		for (int i = 0; i < thr_arpegiateurs.size(); i++) {
			if (Arpege_Note[i]==note)
			return i;
		}
		return -1;
	}

	private Thread_Arpegiateur getArpegiatorByNote(int note) {
		for (int i = 0; i < thr_arpegiateurs.size(); i++) {
			if (Arpege_Note[i]==note)
			return thr_arpegiateurs.get(i);
		}
		return null;
	}

	private Thread_Arpegiateur getFreeArpegiator(int note) {
		for (int i = 0; i < Arpege_Note.length; i++) {
			if (Arpege_Note[i]==-1)
			{
				Arpege_Note[i]=note;
				return thr_arpegiateurs.get(i);
			}
		}
		return null;
	}

	private void debug_arpegiator()	{
		for (int i = 0; i < Arpege_Note.length; i++) {
			System.err.printf("[%2d]", Arpege_Note[i]);
		}
		System.err.println("");
		
	}
	public static void main(String args[])
	{
	
		try {
			evt e = new evt();
		
		} catch (Exception e) {
			e.printStackTrace();
			DialogDivers.Show_dialog(e, "Probleme init :");
			
		}
	
	}

	public synchronized ArrayList<CCManager> getModulateurs() {
		return modulateurs;
	}

	public synchronized void setModulateurs(ArrayList<CCManager> modulateurs) {
		this.modulateurs = modulateurs;
	}
	

	public synchronized boolean isFinished() {
		return Finished;
	}

	public synchronized void setFinished(boolean finished) {
		Finished = finished;
	}

	public void setPolyGameEnable(int canal, boolean selected) {
		PolyGameEnable[canal]=selected;
		
	}

	public void setPolyGameNoteValue(int canal, int noteval) {
		PolyGameNoteValue[canal]=noteval;
		
	}
	

	
	public synchronized int getRecordingInputToArpegiator() {
		return RecordingInputToArpegiator;
	}

	public synchronized void setRecordingInputToArpegiator(int recordingInputToArpegiator) {
		RecordingInputToArpegiator = recordingInputToArpegiator;
		setRecordingInputToArpegiatorIndex(0);
	}

	public synchronized boolean isArpegiatorEnabled() {
		return ArpegiatorEnabled;
	}

	public synchronized void setArpegiatorEnabled(boolean arpegiatorEnabled) {
		ArpegiatorEnabled = arpegiatorEnabled;
	}
	public synchronized ArrayList<Thread_Arpegiateur> getThr_arpegiateurs() {
		return thr_arpegiateurs;
	}

	public synchronized void setThr_arpegiateurs(ArrayList<Thread_Arpegiateur> thr_arpegiateurs) {
		this.thr_arpegiateurs = thr_arpegiateurs;
	}
	public synchronized boolean[] getPolyGameEnable() {
		return PolyGameEnable;
	}

	public synchronized void setPolyGameEnable(boolean[] polyGameEnable) {
		PolyGameEnable = polyGameEnable;
	}

	public synchronized int[] getPolyGameNoteValue() {
		return PolyGameNoteValue;
	}

	public synchronized void setPolyGameNoteValue(int[] polyGameNoteValue) {
		PolyGameNoteValue = polyGameNoteValue;
	}

	public synchronized PresetManager_Synth getPm() {
		return pm_synth;
	}

	/*public synchronized void setPm(PresetManager_Synth pm_synth) {
		this.pm = pm_synth;
	}*/

	public synchronized File getLoaded_modulateur() {
		return loaded_modulateur;
	}

	public synchronized void setLoaded_modulateur(File loaded_modulateur) {
		this.loaded_modulateur = loaded_modulateur;
	}

	public synchronized File getLoaded_presets() {
		return loaded_presets;
	}

	public synchronized void setLoaded_presets(File loaded_presets) {
		this.loaded_presets = loaded_presets;
	}

	public void pauseModulateurs(boolean b) {
		for (int i = 0; i < threads.size(); i++) {
			Thread_Modulateurs tm = threads.get(i);
			tm.setPaused(b);
		}
		
	}

	public void sync()
	{
		System.err.println("Nothing here ...");
	}
	
	public synchronized void save(String filename) throws IOException
	{
		File fil = new File(filename);
	    PrintStream ps = new PrintStream(fil);
      	// Instanciation de la classe XStream		
	    XStream xstream = Utils.getXStreamInstance();
		// Convertion du contenu de l'objet article en XML
	    
	    ObjectOutputStream out = xstream.createObjectOutputStream(ps);
   
	    synchronized(modulateurs)
	    {
	    	out.writeObject(modulateurs);
	    	out.flush();
	    	out.close();
	    	ps.flush();
			ps.close();
	    }
	    loaded_modulateur=fil;
	}
	
	public void save_presets(String f) throws IOException {
		pm_synth.save(f);
		loaded_presets=new File(f);
	}
	
	public void save_drum_presets(String f) throws IOException {
		pm_drum.save(f);
		loaded_drum_presets=new File(f);
	}
	
	public synchronized void load(String file) throws IOException, ClassNotFoundException, InterruptedException
	{  
		File fil = new File(file);
		FileInputStream fis = new FileInputStream(fil);
	    XStream xstream = Utils.getXStreamInstance();
	    ObjectInputStream out = xstream.createObjectInputStream(fis);
	    
	    killThread_Modulateurs();
	    
	    synchronized(modulateurs)
	    {
	    	modulateurs = (ArrayList<CCManager>) out.readObject();
	    }
	    
	    loaded_modulateur = fil;
	    
	    creeThread_Modulateurs();
	    sendLvl0();
	}
	
public PresetManager_Synth loadPresets(String file) throws IOException, ClassNotFoundException, InterruptedException {
		
		if (ApplyPresetOnLoad==true)
		{
			//sendLvl0();
			sendLvl0();
			pauseModulateurs(true);
		
			pm_synth.load(file);
		//	sendLvl0();
			loaded_presets = new File(file);
			pm_synth.applyPresets();
			
			pauseModulateurs(false);
			sendLvl0();
		}
		return pm_synth;
	}

	public PresetManager_Drum loadDrumPresets(String file) throws IOException, ClassNotFoundException, InterruptedException {
		if (ApplyPresetOnLoad==true)
		{
			pm_drum.load(file);
			loaded_drum_presets = new File(file);
			pm_drum.applyPresets();
		
		}
		return pm_drum;
	}
	
	public void saveProjet(String file) throws IOException
	{
		String pathsepa = System.getProperty("file.separator");
		String file_preset="saves"+pathsepa+"presets"+pathsepa+loaded_presets.getName();
		String file_modulateur="saves"+pathsepa+"modulateurs"+pathsepa+loaded_modulateur.getName();
		System.err.println("PRS : "+file_preset);
		System.err.println("MOD : "+file_modulateur);
		
		
		Projet proj = new Projet(file_preset, file_modulateur);
		
		
		File fil = new File(file);
		FileOutputStream fis = new FileOutputStream(fil);
	    XStream xstream =Utils.getXStreamInstance();
	    ObjectOutputStream out = xstream.createObjectOutputStream(fis);
	    
	    out.writeObject(proj);
	    
	    out.flush();
	    out.close();
		
	    loaded_projet=fil;
		
	}
	public void loadProjet(String file) throws IOException, ClassNotFoundException, InterruptedException
	{
		File fil = new File(file);
		FileInputStream fis = new FileInputStream(fil);
	    XStream xstream = Utils.getXStreamInstance();
	    ObjectInputStream in = xstream.createObjectInputStream(fis);
	    
	    Projet proj = (Projet)in.readObject();
	    
	    loadPresets(proj.getFile_preset());
	    load(proj.getFile_modulateur());
		
	    loaded_projet=fil;
		
	}
	
	public void sendLvl0()
	{
		for (int i = 0; i < 5; i++) {
			
			try {
				ShortMessage message =  new ShortMessage();
				message.setMessage(ShortMessage.CONTROL_CHANGE, evt.getOutputChannels()[i], 0x7, 0);
				_Receiver.send(message, -1);
				/*message =  new ShortMessage();
				message.setMessage(ShortMessage.NOTE_ON, i, 0x7, 0);
				sendMessageToStack(message);
				message =  new ShortMessage();
				message.setMessage(ShortMessage.NOTE_OFF, i, 0x7, 0);
				sendMessageToStack(message);*/
				/*synchronized(this)
				{
				this.notify();
				}*/
			} catch (InvalidMidiDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//		_Receiver.send(message, -1);
			
		}
	}
	
	public void sendNoteDemo() throws InvalidMidiDataException, InterruptedException
	{
		
		
		// Peux pas marcher comme si a mon avis ... faut le refiler a evt dans la pile des event...
		ShortMessage message =  new ShortMessage();
				
		message = new ShortMessage();message.setMessage(ShortMessage.NOTE_ON, 1, 55, 127);{stack.add(message);synchronized(this){notify();}};Thread.sleep(200);
		message = new ShortMessage();message.setMessage(ShortMessage.NOTE_OFF, 1, 55, 127);{stack.add(message);}synchronized(this){notify();};
		message = new ShortMessage();message.setMessage(ShortMessage.NOTE_ON, 2, 57, 127);{stack.add(message);}synchronized(this){notify();};Thread.sleep(200);
		message = new ShortMessage();message.setMessage(ShortMessage.NOTE_OFF, 2, 57, 127);{stack.add(message);}synchronized(this){notify();};
		message = new ShortMessage();message.setMessage(ShortMessage.NOTE_ON, 3, 59, 127);{stack.add(message);}synchronized(this){notify();};Thread.sleep(200);
		message = new ShortMessage();message.setMessage(ShortMessage.NOTE_OFF, 3, 59, 127);{stack.add(message);}synchronized(this){notify();};
		message = new ShortMessage();message.setMessage(ShortMessage.NOTE_ON, 4, 61, 127);{stack.add(message);}synchronized(this){notify();};Thread.sleep(200);
		message = new ShortMessage();message.setMessage(ShortMessage.NOTE_OFF, 4, 61, 127);{stack.add(message);}synchronized(this){notify();};
		message = new ShortMessage();message.setMessage(ShortMessage.NOTE_ON, 5, 63, 127);{stack.add(message);}synchronized(this){notify();};Thread.sleep(200);
		message = new ShortMessage();message.setMessage(ShortMessage.NOTE_OFF, 5, 63, 127);{stack.add(message);}synchronized(this){notify();};
		
		message = new ShortMessage();message.setMessage(ShortMessage.NOTE_ON, 1, 55, 127);{stack.add(message);}synchronized(this){notify();}Thread.sleep(500);
		message = new ShortMessage();message.setMessage(ShortMessage.NOTE_ON, 2, 57, 127);{stack.add(message);}synchronized(this){notify();}Thread.sleep(500);
		message = new ShortMessage();message.setMessage(ShortMessage.NOTE_ON, 3, 59, 127);{stack.add(message);}synchronized(this){notify();}Thread.sleep(500);
		message = new ShortMessage();message.setMessage(ShortMessage.NOTE_ON, 4, 61, 127);{stack.add(message);}synchronized(this){notify();}Thread.sleep(500);
		message = new ShortMessage();message.setMessage(ShortMessage.NOTE_ON, 5, 63, 127);{stack.add(message);}synchronized(this){notify();}Thread.sleep(1000);
		
		message = new ShortMessage();message.setMessage(ShortMessage.NOTE_OFF, 1, 55, 127);{stack.add(message);}synchronized(this){notify();}Thread.sleep(500);
		message = new ShortMessage();message.setMessage(ShortMessage.NOTE_OFF, 2, 57, 127);{stack.add(message);}synchronized(this){notify();}Thread.sleep(500);
		message = new ShortMessage();message.setMessage(ShortMessage.NOTE_OFF, 3, 59, 127);{stack.add(message);}synchronized(this){notify();}Thread.sleep(500);
		message = new ShortMessage();message.setMessage(ShortMessage.NOTE_OFF, 4, 61, 127);{stack.add(message);}synchronized(this){notify();}Thread.sleep(500);
		message = new ShortMessage();message.setMessage(ShortMessage.NOTE_OFF, 5, 63, 127);{stack.add(message);}synchronized(this){notify();}Thread.sleep(500);
		
	
	}

	public void create_new() {
		
		
		try {
			killThread_Modulateurs();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (pm_synth==null)
		{
			kml_synth.init_SYNTH();
			pm_synth = new PresetManager_Synth(this, kml_synth);
			pm_synth.set_Receiver(_Receiver);
		}
		
		if (pm_drum==null)
		{
			kml_drum.init_DRUM(DrumChannel);
			pm_drum = new PresetManager_Drum(this, kml_drum);
			pm_drum.set_Receiver(_Receiver);
		}
		
		pm_drum.reset();
		pm_synth.reset();
		modulateurs.clear();
		CCManager man1 = new CCManager(pm_synth);
		//man1.setReceiver(_Receiver);
		man1.setChannel(1);
		man1.setCC(7);
		modulateurs.add(man1);
		
		CCManager man2 = new CCManager(pm_synth);
		//man2.setReceiver(_Receiver);
		man2.setChannel(2);
		man2.setCC(7);
		modulateurs.add(man2);
		
		CCManager man3 = new CCManager(pm_synth);
		//man3.setReceiver(_Receiver);
		man3.setChannel(3);
		man3.setCC(7);
		modulateurs.add(man3);
		
		CCManager man4 = new CCManager(pm_synth);
		//man4.setReceiver(_Receiver);
		man4.setChannel(4);
		man4.setCC(7);
		modulateurs.add(man4);
		
		CCManager man5 = new CCManager(pm_synth);
		//man5.setReceiver(_Receiver);
		man5.setChannel(5);
		man5.setCC(7);
		modulateurs.add(man5);
		
		creeThread_Modulateurs();
		creeThread_Arpegiateurs();
		
		loaded_modulateur=null;
		loaded_presets=null;
		loaded_projet=null;
	}


	/**
	 * Envoie une multi note off sur tout les cannaux
	 * Vide le usedNotes
	 */
	public void sendNoteOff() {
		usedNotes.clear();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j< 127; j++) {
					ShortMessage newmessage = new ShortMessage();
					try {
						newmessage.setMessage(ShortMessage.NOTE_OFF,evt.getOutputChannels()[i], j, 0);
					} catch (InvalidMidiDataException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					_Receiver.send(newmessage,-1);	
			}
		}
	}

	
	boolean started=false;
	public void sendSTART() throws InvalidMidiDataException
	{
		ShortMessage message = new ShortMessage();
		message.setMessage(ShortMessage.START);
		started=true;
		_Receiver.send(message,-1); 
	}
	public void sendSTOP() throws InvalidMidiDataException
	{
		  ShortMessage message = new ShortMessage(); 
		  
	      message.setMessage(ShortMessage.STOP);
	      started=false;
	      _Receiver.send(message,-1); 
	}
	
	public void addMessageToStack(ShortMessage newmessage) {
	//	System.err.println("Add Message to queue "+newmessage.getCommand());
		stack.add(newmessage);
		synchronized(this)
		{
			//	System.err.println("EVT notifying");
			notify();
		}
		
	}
	
	
	/*****
	 * 
	 * EVENT SYSTEM
	 * 
	 * 
	 */

	
	/****
	 * 
	 */
	public void addXXXListener(Evt_Listener listener)
	{
		xxxXListeners.add(Evt_Listener.class, listener);
	}

	public void removeXXXListener(Evt_Listener listener)
	{
		xxxXListeners.remove(Evt_Listener.class, listener);
	}

	public void fireStgNeedRefresh(Evt_Event xxxEvent)
	{
		Object[] listeners = xxxXListeners.getListenerList();
		// loop through each listener and pass on the event if needed
		int numListeners = listeners.length;
		for (int i = 0; i < numListeners; i += 2)
		{
			if (listeners[i] == Evt_Listener.class)
			{
				// pass the event to the listeners event dispatch method
				((Evt_Listener) listeners[i + 1]).somethingNeedRefresh(xxxEvent);
			}
		}
	}

	public void applyPresets() {
		pm_synth.applyPresets();
		pm_drum.applyPresets();
	}

	public File getLoaded_Projet() {
		return loaded_projet;
	}

	public synchronized boolean isApplyPresetOnLoad() {
		return ApplyPresetOnLoad;
	}

	public synchronized void setApplyPresetOnLoad(boolean applyPresetOnLoad) {
		ApplyPresetOnLoad = applyPresetOnLoad;
	}

	public void setRecordingInputToArpegiatorIndex(int recordingInputToArpegiatorIndex) {
		RecordingInputToArpegiatorIndex = recordingInputToArpegiatorIndex;
	}

	public int getRecordingInputToArpegiatorIndex() {
		return RecordingInputToArpegiatorIndex;
	}

	public synchronized PresetManager_Drum getPm_drum() {
		return pm_drum;
	}

	public synchronized void setPm_drum(PresetManager_Drum pm_drum) {
		this.pm_drum = pm_drum;
	}

	public synchronized boolean isSetTransmitterLevelToMaximum() {
		return SetTransmitterLevelToMaximum;
	}

	public synchronized void setSetTransmitterLevelToMaximum(boolean setTransmitterLevelToMaximum) {
		SetTransmitterLevelToMaximum = setTransmitterLevelToMaximum;
	}

	public int getDrumChannel() {
		return DrumChannel;
	}

	public synchronized boolean isForceSendNoteOFF() {
		return ForceSendNoteOFF;
	}

	public synchronized void setForceSendNoteOFF(boolean forceSendNoteOFF) {
		ForceSendNoteOFF = forceSendNoteOFF;
	}

	public synchronized RecordMode getRecordMode() {
		return recordMode;
	}

	public synchronized void setRecordMode(RecordMode recordMode) {
		this.recordMode = recordMode;
		System.err.println("ARP Record mode set to : "+recordMode);
	}

/*	public static void setLs(LoadSplash ls) {
		evt.ls = ls;
	}

	public static LoadSplash getLs() {
		return ls;
	}
*/
	

	
	
}
