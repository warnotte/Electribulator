package org.warnotte.elecribulator.PresetManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

import org.warnotte.elecribulator.CControlers.CCManager;
import org.warnotte.elecribulator.Multi.evt;
import org.warnotte.elecribulator.Utils.Utils;
import org.warnotte.waxaudiomiditools.CControlers.ControlElement;
import org.warnotte.waxaudiomiditools.CControlers.KnowMidiList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class PresetManager {
	
	Receiver _Receiver = null;
	evt evt = null;
	
	// Il faut une HASHMAP (2 en fait, avec le CC et le NAME).
	ArrayList<ControlValue> controleurs_synth = new ArrayList<ControlValue>();
	//ArrayList<ControlValue> controleurs_drum = new ArrayList<ControlValue>();
	KnowMidiList kwml_synth = null;
	//KnowMidiList kwml_drum = null;
	
	boolean BypassSend = false;
	
	
	
	public PresetManager(KnowMidiList kwml_synth)
	{
		this.kwml_synth=kwml_synth;	
	}
	
	
	public synchronized void save(String filename) throws IOException
	{
	    PrintStream ps = new PrintStream(new File(filename));
      	// Instanciation de la classe XStream		
	    XStream xstream = Utils.getXStreamInstance();
		// Convertion du contenu de l'objet article en XML
	    
	    ObjectOutputStream out = xstream.createObjectOutputStream(ps);
   
	    synchronized(controleurs_synth)
	    {
	    	out.writeObject(controleurs_synth);
	    	out.flush();
	    	out.close();
	    	ps.flush();
			ps.close();
	    }
	}
	public synchronized void load(String file) throws IOException, ClassNotFoundException, InterruptedException
	{  
		FileInputStream fis = new FileInputStream(new File(file));
		XStream xstream = Utils.getXStreamInstance();
	    ObjectInputStream out = xstream.createObjectInputStream(fis);
	    synchronized(controleurs_synth)
	    {
	    	controleurs_synth = (ArrayList<ControlValue>) out.readObject();
	    }
	    applyPresets();
	}


	public synchronized ControlValue get(int arg0) {
		return controleurs_synth.get(arg0);
	}

	public synchronized int size() {
		return controleurs_synth.size();
	}

	
	public void applyPresets() {
	}
	
	protected void applyPresets(int canal, ControlValue cv) {
		if (isBypassSend()==true) return;
		if (cv.ce.isNRPN()==false)
			send(canal, cv.ce.getCC(), (int) cv.value);
		else
		{
			if (cv.ce.isDoubleNRPN()==false)
				sendNRPN(canal,cv.ce.getMSB(),cv.ce.getLSB(), cv.ce.getOtherValue(), (int)cv.value);
			else
				sendNRPN2(canal,cv.ce.getMSB(),cv.ce.getLSB(), (int)cv.value);
		}
	}
	
	
	
	
	public void send(int Channel, int CC, int value) {
		if (isBypassSend()==true) return;
		if (_Receiver!=null)
		{
			ShortMessage message = new ShortMessage();
			try
			{
				if (value>127) value=127;
				if (value<0) value=0;
				message.setMessage(ShortMessage.CONTROL_CHANGE, Channel, CC, value);
				_Receiver.send(message, -1);
			} 
			catch (InvalidMidiDataException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void sendNRPN(int Channel, int MSB, int LSB, int value1, int value2)
	{
		if (isBypassSend()==true) return;
		if (value1<0) {
			System.err.println("Pas normal");
			return;
			};
		if (_Receiver!=null)
		{
			ShortMessage message = new ShortMessage();
			try
			{
				//if (value>127) value=127;
				//if (value<0) value=0;
				message.setMessage(ShortMessage.CONTROL_CHANGE, Channel, 0x63, MSB); // MSB
				_Receiver.send(message, -1);
				message.setMessage(ShortMessage.CONTROL_CHANGE, Channel, 0x62, LSB); // LSB
				_Receiver.send(message, -1);
				message.setMessage(ShortMessage.CONTROL_CHANGE, Channel, value1, value2);
				_Receiver.send(message, -1);
				
				
			} 
			catch (InvalidMidiDataException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void sendNRPN2(int Channel, int MSB, int LSB,  int valeur)
	//public void sendNRPN2(int Channel, int MSB, int LSB, int value1, int value2, int value3, int value4)
	{
		if (isBypassSend()==true) return;
		// 16 bit -> 14 bit.
		int v1 = (valeur >> 7);
		int v2 = valeur-(v1*128);
		//sendNRPN2( Channel, 0x09, 0x20, 0x06, v1, 0x26, v2);
		
		if (_Receiver!=null)
		{
			ShortMessage message = new ShortMessage();
			try
			{
				//if (value>127) value=127;
				//if (value<0) value=0;
				message.setMessage(ShortMessage.CONTROL_CHANGE, Channel, 0x63, MSB); // MSB
				_Receiver.send(message, -1);
				message.setMessage(ShortMessage.CONTROL_CHANGE, Channel, 0x62, LSB); // LSB
				_Receiver.send(message, -1);
				message.setMessage(ShortMessage.CONTROL_CHANGE, Channel, 0x06, v1);
				_Receiver.send(message, -1);
				message.setMessage(ShortMessage.CONTROL_CHANGE, Channel, 0x26, v2);
				_Receiver.send(message, -1);
			} 
			catch (InvalidMidiDataException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*
	public void setWave(int Channel, int valeur)
	{
		sendNRPN( Channel, 0x08, 0x01, 0x06, valeur);
	}
	*/
	
	
		
	public synchronized Receiver get_Receiver() {
		return _Receiver;
	}


	public synchronized void set_Receiver(Receiver receiver) {
		_Receiver = receiver;
	}
	public synchronized evt getEvt() {
		return evt;
	}
	public synchronized void setEvt(evt evt) {
		this.evt = evt;
	}
	public ControlValue getPresetByName(String presetName) {
		for (int i = 0; i < controleurs_synth.size(); i++) {
			ControlValue cv = controleurs_synth.get(i);
			if (cv.ce.getLabel().equalsIgnoreCase(presetName))
				return cv;
		}
		System.err.println("Cannot find value "+presetName+" in this preset");
		return null;
	}
	
	
	// TODO : Tres lent !!!!
	public ControlValue getPresetByCC(int cc) {
		int cpt = controleurs_synth.size();
		for (int i = 0; i < cpt; i++) {
			ControlValue cv = controleurs_synth.get(i);
			if (cv==null)
			{
				System.err.println("F");
				System.exit(-1);
			}
			if (cv.getCC()==cc)
				return cv;
		}
		System.err.println("Unkown control CC #"+cc);
		return null;
	}
	public void reset() {
		synchronized(this)
		{
			controleurs_synth.clear();
			for (int i = 0; i < kwml_synth.size(); i++) {
				ControlElement ce = kwml_synth.get(i);
				ControlValue cv = new ControlValue(ce);
				controleurs_synth.add(cv);
			}
	/*		for (int i = 0; i < kwml_drum.size(); i++) {
				ControlElement ce = kwml_drum.get(i);
				ControlValue cv = new ControlValue(ce);
				controleurs_synth.add(cv);
			}*/
			applyPresets();
		}
	}

	public synchronized boolean isBypassSend() {
		return BypassSend;
	}

	public synchronized void setBypassSend(boolean bypassSend) {
		BypassSend = bypassSend;
	}
	
}
