package orw.warnotte.elecribulator.Multi;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Transmitter;
import javax.sound.midi.MidiDevice.Info;
import javax.swing.JFrame;
import javax.swing.event.EventListenerList;

import org.warnotte.OBJ2GUI.Events.MyChangedEvent;
import org.warnotte.OBJ2GUI.Events.MyEventListener;
import org.warnotte.waxaudiomiditools.MidiConfig.ConfigMIDIINPUT;
import org.warnotte.waxaudiomiditools.MidiConfig.MidiCommon;
import org.warnotte.waxaudiomiditools.MidiConfig.retourMidiConfig;

import orw.warnotte.elecribulator.PresetManager.ControlValue;

public class electribe_receiver implements Receiver
{

	Transmitter _Transmitter = null;
	evt evt = null;
	
	electribe_receiver(evt evt1) throws MidiUnavailableException
	{
		this.evt=evt1;
		_Transmitter=ConfigMIDIINPUT.createWindow(_Transmitter);
		_Transmitter.setReceiver(this);
	}
	
	
	public void configTransmitter() throws MidiUnavailableException
	{
		ConfigMIDIINPUT md1 = new ConfigMIDIINPUT(new JFrame());
		md1.pack();
		md1.setVisible(true);
		retourMidiConfig retour = md1.getDonnees();
		
	//	System.err.println("IN  : "+retour);
		
		if (_Transmitter!=null)
		{
			_Transmitter.close();
		}
		int index = retour.getIndex();
		Info info = MidiCommon.getMidiDeviceInfo(retour.getInfos().getName(), false);
		
		MidiDevice device = MidiSystem.getMidiDevice(info);
		device.open();
		
		_Transmitter = device.getTransmitter();
		_Transmitter.setReceiver(this);
	}
	
	
	public void close() {
		// TODO Auto-generated method stub
		
	}

	
	public void send(MidiMessage message, long timeStamp) {
		//System.err.println("* "+((ShortMessage) message).getCommand());
		/*
		 * if (message instanceof ShortMessage) { return; }
		 */
		
		if ((((ShortMessage) message).getCommand()==0x63))
				
				{
			System.err.println("Clock ");
				}
		
		/*
		sendNRPN( Channel, 0x08, 0x01, 0x06, valeur);
		message.setMessage(ShortMessage.CONTROL_CHANGE, Channel-1, 0x63, MSB); // MSB
		_Receiver.send(message, -1);
		message.setMessage(ShortMessage.CONTROL_CHANGE, Channel-1, 0x62, LSB); // LSB
		_Receiver.send(message, -1);
		message.setMessage(ShortMessage.CONTROL_CHANGE, Channel-1, value1, value2);
		_Receiver.send(message, -1);*/
		
		int canal = (((ShortMessage) message).getChannel());
		if ((((ShortMessage) message).getCommand() == ShortMessage.NOTE_OFF) || (((ShortMessage) message).getCommand() == ShortMessage.NOTE_ON))
		{
			// System.err.println("I got stg "+message+
			// " "+((ShortMessage)message).getCommand());
		
			
			if (((ShortMessage) message).getCommand() == ShortMessage.NOTE_ON)
			{
				MyChangedEvent xxxEvent = new MyChangedEvent(message, null);
				fireIOEnteredExited( xxxEvent);
				
			//	System.err.println("K Note On  "+canal);
				evt.modulateurs.get(canal).sync();
				evt.modulateurs.get(canal).gate(true);
				
				
				
		}
		//	else
			//	System.err.println("K Note Off "+canal);

			
			
			
		}
		else
			if ((((ShortMessage) message).getCommand() == ShortMessage.CONTROL_CHANGE))
			{
				int cc = ((ShortMessage) message).getData1();
				int value = ((ShortMessage) message).getData2();
				
			//	System.err.println("CC "+cc+" on ch="+canal+" value = "+value);
				ControlValue cv = evt.getPm().getPresetByCC(cc);
				if (cv!=null)
				{
					cv.setValue(value);
					evt.getPm().applyPresets(cv); 
					// Avertir le GUI.
					
					
					MyChangedEvent xxxEvent = new MyChangedEvent(message, null);
					fireIOEnteredExited( xxxEvent);
					
				}
			}
		
	}
	

	EventListenerList IOEnteredExitedListeners = new EventListenerList();  //  @jve:decl-index=0:

    public void addIOEnteredExitedListener(MyEventListener listener) {
    	IOEnteredExitedListeners.add(MyEventListener.class, listener);
    }

    public void removeIOEnteredExitedListener(MyEventListener listener) {
    	IOEnteredExitedListeners.remove(MyEventListener.class, listener);
    }
    
    public void fireIOEnteredExited(MyChangedEvent xxxEvent) {
	Object[] listeners = IOEnteredExitedListeners.getListenerList();
	// loop through each listener and pass on the event if needed
	int numListeners = listeners.length;
	for (int i = 0; i < numListeners; i += 2) {
	    // pass the event to the listeners event dispatch method
		try
		{
		((MyEventListener) listeners[i + 1]).myEventOccurred(xxxEvent);
		}
		catch(NullPointerException e){
			e.printStackTrace();
		}
	}
    }

    
   /* public void MyEventListener(MyEventListener xxxEvent) {
    	fireIOEnteredExited(xxxEvent);
    }*/

}
