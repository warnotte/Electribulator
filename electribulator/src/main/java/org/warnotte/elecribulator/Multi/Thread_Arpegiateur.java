package org.warnotte.elecribulator.Multi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.ShortMessage;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Thread_Arpegiateur extends Thread {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Thread_Arpegiateur ta = new Thread_Arpegiateur(null);
		
		ta.save("saves"+File.separator+"arp"+File.separator+"arp_1.xml");
		ta.load("saves"+File.separator+"arp"+File.separator+"arp_1.xml");
		System.err.println("toto");
	}

	public void save(String string) throws IOException
	{
		File f = new File(string);
		FileOutputStream fos = new FileOutputStream(f);
		XStream xstream = new XStream(new DomDriver());
		ObjectOutputStream out = xstream.createObjectOutputStream(fos, "ArpegiatorConfig");

		out.writeObject(NoteSeq);
		out.writeObject(GateLen);
		out.writeObject(SleepLen);
		out.flush();
		out.close();
	}
	
	public void load(String string) throws IOException, ClassNotFoundException
	{
		this.interrupt(); //?
		File f = new File(string);
		FileInputStream fos = new FileInputStream(f);
		XStream xstream = new XStream(new DomDriver());
		ObjectInputStream in = xstream.createObjectInputStream(fos);

		NoteSeq = (int[])in.readObject();
		GateLen =  (float[])in.readObject();
		SleepLen =  (long[])in.readObject();
		
		fos.close();
	}

	//long SleepLen = 250;
	int NoteSeq[] = new int[] { 	0,	3,	7,	0,	3,	9,	0,	3,10,0,3,12 };
	float GateLen[] = new float[] { 0.35f, 0.35f, 0.40f, 0.35f, 0.35f, 0.40f, 0.35f, 0.35f,0.40f ,0.35f, 0.35f,0.40f };
	long SleepLen[] = new long[] { 500, 500, 500, 500, 500, 500,500,500,500,500,500,500};
	
	//long SleepLen = 250;
	int ARPSeq[] = new int[] { 	0,	1,	2 };
	//long SleepLen = 250;
	float ARPGateDiv[] = new float[] {1,1,1};
	//long SleepLen = 250;
	float ARPSleepDiv[] = new float[] {1,1,1};
	

	
	//float GateLen = 0.5f; // If len == 1 then gate == SleepLen
	boolean AlwaysLoop = true;
	
	boolean LoopMultiArp = true;
	
	public enum MultiArpMode {
		CONSTANT, INCREASE, DECREASE, RANDOM
	};
	
	MultiArpMode multiarpmode = MultiArpMode.CONSTANT;
	
	
	public enum ArpStatus {
		ON, OFF
	};
	
	public enum Mode {
		FORWARD, UPDOWN
	}
	
	Mode Cmode = Mode.FORWARD;

	evt evt1 = null;
	private boolean Finished;
	ArpStatus stat = ArpStatus.OFF;
	ShortMessage messageOrigine = null;
	int old_note = -1;
	int old_vel = -1;
	private int ORIG_NOTE = -1;

	private int light_selected_note;
	private int light_selected_arpnote;
	private int old_chan;
	private boolean bypass;
	private int multiarploop;


	public synchronized boolean isAlwaysLoop() {
		return AlwaysLoop;
	}

	public synchronized void setAlwaysLoop(boolean alwaysLoop) {
		AlwaysLoop = alwaysLoop;
	}

	public synchronized boolean isLoopMultiArp() {
		return LoopMultiArp;
	}

	public synchronized void setLoopMultiArp(boolean loopMultiArp) {
		LoopMultiArp = loopMultiArp;
	}

	public synchronized int getLight_selected_note() {
		return light_selected_note;
	}

	public synchronized void setLight_selected_note(int light_selected_note) {
		this.light_selected_note = light_selected_note;
	}
	public synchronized int getLight_selected_arpnote() {
		return light_selected_arpnote;
	}

	public synchronized void setLight_selected_arpnote(int light_selected_note) {
		this.light_selected_arpnote = light_selected_note;
	}

	public Thread_Arpegiateur(evt evt1) {
		this.evt1 = evt1;
	}

	public void run() {
		ShortMessage newmessage = new ShortMessage();

		
		
		int i = 0;
		while (Finished == false) {
			synchronized (this) {
				{
					try {
						i = 0;
						// System.err.println(getId()+" Attends une note.");
						wait();

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
					//	e.printStackTrace();
					}
				}
			}

			Mode mod = Cmode;
			
			/*if (bypass==true)
			{
				try {
					((ShortMessage) newmessage).setMessage(ShortMessage.NOTE_ON, 1, ORIG_NOTE, old_vel);
				} catch (InvalidMidiDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				evt1.sendMessageToStack(newmessage);
				
			}*/
			
			
			try {
				// terminé=false;
				boolean firstloop = true;
				
				
				// old_note = messageOrigine.getData1();
				while (getStat() == ArpStatus.ON)
				{
					System.err.println("LoopARP == "+multiarploop);
					int ORIG_NOTE = this.ORIG_NOTE+ARPSeq[multiarploop];
					light_selected_arpnote=multiarploop;
					/*if (bypass==true)
					{
						Thread.sleep(10);
						continue;
					}*/
					
					int str=0;
					if (mod==mod.UPDOWN)
						if (firstloop == true)
							str=1;
						
					for (i = str; i < NoteSeq.length; i++) {
						int NOTE = NoteSeq[i] ;
						float GATE = GateLen[i]* ARPGateDiv[multiarploop];
						long SLEEP = (long) ((float)SleepLen[i]* ARPSleepDiv[multiarploop]);;
						
						
						synchronized (this) {
							light_selected_note=i;
							
							// System.err.println(this.getId() + " looping " +
							// i+ ""+getStat());
							// Envoye les notes suivantes et boucle.
							newmessage = new ShortMessage();
							try {
								newmessage = new ShortMessage();
								((ShortMessage) newmessage).setMessage(ShortMessage.NOTE_OFF, 1, old_note, 0);
								// evt1.addMessageToStack(newmessage);
								evt1.sendMessageToStack(newmessage);

								if (firstloop == false) {
									Thread.sleep((int) (SLEEP * (1.0f - GATE)));
									

								} else
									firstloop = false;

								newmessage = new ShortMessage();
								((ShortMessage) newmessage).setMessage(ShortMessage.NOTE_ON, 1, NOTE + ORIG_NOTE, old_vel);

								// sendMessageToStack
								evt1.sendMessageToStack(newmessage);
								// evt1.addMessageToStack(newmessage);
								old_note = NOTE + ORIG_NOTE;

							} catch (InvalidMidiDataException e) {
								// TODO Auto-generated catch block
							//	e.printStackTrace();
							}
						}
						try {
							Thread.sleep((int) (SLEEP * GATE));
						} catch (InterruptedException e) {
							setStat(ArpStatus.OFF);
							i = 3;
							try {
								throw new Exception("Pet de singe");
							} catch (InterruptedException e1) {
							}
							break;

						}
					}
					
					if (mod==mod.UPDOWN)
					for (i = NoteSeq.length-2; i >0 ; i--) {
						int NOTE = NoteSeq[i] ;
						float GATE = GateLen[i]* ARPGateDiv[multiarploop];
						long SLEEP = (long) ((float)SleepLen[i]* ARPSleepDiv[multiarploop]);;
						
						synchronized (this) {
							// System.err.println(this.getId() + " looping " +
							// i+ ""+getStat());
							// Envoye les notes suivantes et boucle.
							newmessage = new ShortMessage();
							try {
								newmessage = new ShortMessage();
								((ShortMessage) newmessage).setMessage(ShortMessage.NOTE_OFF, 1, old_note, 0);
								// evt1.addMessageToStack(newmessage);
								evt1.sendMessageToStack(newmessage);

								if (firstloop == false) {
									Thread.sleep((int) (SLEEP * (1.0f - GATE)));

								} else
									firstloop = false;

								newmessage = new ShortMessage();
								((ShortMessage) newmessage).setMessage(ShortMessage.NOTE_ON, 1, NOTE + ORIG_NOTE, old_vel);

								// sendMessageToStack
								evt1.sendMessageToStack(newmessage);
								// evt1.addMessageToStack(newmessage);
								old_note = NoteSeq[i] + ORIG_NOTE;

							} catch (InvalidMidiDataException e) {
								// TODO Auto-generated catch block
							//	e.printStackTrace();
							}
						}
						try {
							Thread.sleep((int) (SLEEP * GATE));
						} catch (InterruptedException e) {

							setStat(ArpStatus.OFF);
							//e.printStackTrace();

							i = 3;
							try {
								throw new Exception("Pet de singe");
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
							//	e1.printStackTrace();
							}
							break;

						}
					}
					
					
					if (AlwaysLoop==false)
						break;
					
					// Mode multi arp. On recommence en cheangant la note de reference.
					
					
					multiarploop++;
					
					
					// Loop multiarp ?
					if (multiarploop>=getARPSeqLength())
					{
						if (LoopMultiArp==false)
							break;
						multiarploop=0;
					}
					
					
					
				}

			} catch (Exception e) {
			//	e.printStackTrace();
			}

			
			// Fin de la boucle d'ARPegiation
			// Doit-on recommencer si le property_mode multi ARP est activé ?
			
			
			
			{
				// En theorie
				newmessage = new ShortMessage();
				// System.err.println(this.getId()+"-C- Note OFF "+old_note);
				try {
					if (old_note != -1) {
						((ShortMessage) newmessage).setMessage(ShortMessage.NOTE_OFF, 1, old_note, 0);
						// evt1.addMessageToStack(newmessage);
						evt1.sendMessageToStack(newmessage);
						
					}
				} catch (InvalidMidiDataException e) {
					e.printStackTrace();
				}
			}
			this.messageOrigine = null;
		}

	}

	public synchronized ArpStatus getStat() {
		return stat;
	}

	public synchronized void setStat(ArpStatus stat) {
		this.stat = stat;
	}
	

	public synchronized void On(ShortMessage messageOrigine) {
		this.messageOrigine = new ShortMessage();
		try {
			this.messageOrigine.setMessage(messageOrigine.getCommand(), messageOrigine.getChannel(), messageOrigine.getData1(), messageOrigine.getData2());
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		old_chan = messageOrigine.getChannel();
		old_note = messageOrigine.getData1();
		ORIG_NOTE = old_note;
		old_vel = messageOrigine.getData2();
		multiarploop=0;
		setStat(ArpStatus.ON);
		// synchronized(this){notify();}
		notify();
	}

	public void Off() {
		// synchronized(this){stat=ArpStatus.OFF;}
		ShortMessage newmessage = new ShortMessage();
		try {
			newmessage.setMessage(ShortMessage.NOTE_OFF, old_chan, old_note, 0);
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Off(newmessage);
		
	}
	
	public void Off(ShortMessage messageOrigine) {
		// synchronized(this){stat=ArpStatus.OFF;}

		
		this.interrupt();

		// En theorie
		ShortMessage newmessage = new ShortMessage();
		// System.err.println(this.getId()+"-D- Note OFF "+old_note);
		try {
			((ShortMessage) newmessage).setMessage(messageOrigine.NOTE_OFF, messageOrigine.getChannel(), old_note, messageOrigine.getData2());
			// evt1.addMessageToStack(newmessage);
			evt1.sendMessageToStack(newmessage);
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}
		this.messageOrigine = null;
		stat = ArpStatus.OFF;
		messageOrigine = null;
		old_note = -1;
		old_vel = -1;
		ORIG_NOTE = -1;

		this.messageOrigine = null;

	}

	public synchronized boolean isFinished() {
		return Finished;
	}

	public synchronized void setFinished(boolean finished) {
		Finished = finished;
	}
	
	public synchronized int getNoteSeq(int index) {
		if (index>=NoteSeq.length)
		{
			System.err.println("Zarb");
			return -1;
		}
		return NoteSeq[index];
	}
	public synchronized float getGateLen(int index) {
		if (index>=GateLen.length)
		{
			System.err.println("Zarb");
			return -1;
		}
		return GateLen[index];
	}
	public synchronized long getSleepLen(int index) {
		if (index>=SleepLen.length)
		{
			System.err.println("Zarb");
			return -1;
		}
		return SleepLen[index];
	}
	
	public synchronized int getARPSeq(int index) {
		if (index>=ARPSeq.length)
		{
			System.err.println("Zarb");
			return -1;
		}
		return ARPSeq[index];
	}
	public synchronized float getARPGateDiv(int index) {
		if (index>=ARPGateDiv.length)
		{
			System.err.println("Zarb");
			return -1;
		}
		return ARPGateDiv[index];
	}
	public synchronized float getARPSleepDiv(int index) {
		if (index>=ARPSleepDiv.length)
		{
			System.err.println("Zarb");
			return -1;
		}
		return ARPSleepDiv[index];
	}

	public synchronized void setNoteSeq(int[] noteSeq) {
		NoteSeq = noteSeq;
	}
	public synchronized void setARPSeq(int[] noteSeq) {
		ARPSeq = noteSeq;
	}

	/*public synchronized float getGateLen() {
		return GateLen;
	}

	public synchronized void setGateLen(float gateLen) {
		GateLen = gateLen;
	}*/

	public boolean isBypass() {
		return bypass;
	}

	public void setBypass(boolean bypass) {
		this.bypass = bypass;
	}

	public synchronized void addLastNote() {
		int newnote [] = new int[NoteSeq.length+1];
		float newgate [] = new float[NoteSeq.length+1];
		long sleeplen [] = new long[NoteSeq.length+1];
		
		System.arraycopy(NoteSeq, 0, newnote, 0, NoteSeq.length);
		System.arraycopy(GateLen, 0, newgate, 0, NoteSeq.length);
		System.arraycopy(SleepLen, 0, sleeplen, 0, NoteSeq.length);
		if (NoteSeq.length-1>0)
		{
			newnote [NoteSeq.length]=newnote [NoteSeq.length-1];
			newgate [NoteSeq.length]=newgate [NoteSeq.length-1];
			sleeplen [NoteSeq.length]=sleeplen [NoteSeq.length-1];
		}
		else
		{
			newnote [NoteSeq.length]=48;
			newgate [NoteSeq.length]=0.5f;
			sleeplen [NoteSeq.length]=250;
		}
		
		NoteSeq = newnote;
		GateLen = newgate;
		SleepLen = sleeplen;
	}
	public synchronized void addLastARPNote() {
		int newnote [] = new int[ARPSeq.length+1];
		float newgate [] = new float[ARPSeq.length+1];
		float sleeplen [] = new float[ARPSeq.length+1];
		
		System.arraycopy(ARPSeq, 0, newnote, 0, ARPSeq.length);
		System.arraycopy(ARPGateDiv, 0, newgate, 0, ARPSeq.length);
		System.arraycopy(ARPSleepDiv, 0, sleeplen, 0, ARPSeq.length);
		if (ARPSeq.length-1>0)
		{
			newnote [ARPSeq.length]=newnote [ARPSeq.length-1];
			newgate [ARPSeq.length]=newgate [ARPSeq.length-1];
			sleeplen [ARPSeq.length]=sleeplen [ARPSeq.length-1];
		}
		else
		{
			newnote [ARPSeq.length]=0;
			newgate [ARPSeq.length]=1;
			sleeplen [ARPSeq.length]=1;
		}
		
		ARPSeq = newnote;
		ARPGateDiv = newgate;
		ARPSleepDiv = sleeplen;
	}

	public synchronized void removeLastNote() {
		if (NoteSeq.length >= 2) {
			int newnote[] = new int[NoteSeq.length - 1];
			float newgate [] = new float[NoteSeq.length-1];
			long sleeplen [] = new long[NoteSeq.length-1];
			
			System.arraycopy(NoteSeq, 0, newnote, 0, NoteSeq.length - 1);
			System.arraycopy(GateLen, 0, newgate, 0, NoteSeq.length-1);
			System.arraycopy(SleepLen, 0, sleeplen, 0, NoteSeq.length-1);
			
			NoteSeq = newnote;
			GateLen = newgate;
			SleepLen = sleeplen;
		}
	}
	public synchronized void removeLastARPNote() {
		if (ARPSeq.length >= 2) {
			int newnote[] = new int[ARPSeq.length - 1];
			float newgate [] = new float[ARPSeq.length-1];
			float sleeplen [] = new float[ARPSeq.length-1];
			
			System.arraycopy(ARPSeq, 0, newnote, 0, ARPSeq.length - 1);
			System.arraycopy(ARPGateDiv, 0, newgate, 0, ARPSeq.length-1);
			System.arraycopy(ARPSleepDiv, 0, sleeplen, 0, ARPSeq.length-1);
			
			ARPSeq = newnote;
			ARPGateDiv = newgate;
			ARPSleepDiv = sleeplen;
		}
	}

	public synchronized int getNoteSeqLength() {
		return NoteSeq.length;
	}
	/*public synchronized int getGateLenLength() {
		return GateLen.length;
	}
	public synchronized int getSleepLenLength() {
		return SleepLen.length;
	}*/
	public synchronized int getARPSeqLength() {
		return ARPSeq.length;
	}
	/*public synchronized int getGateLenLength() {
		return GateLen.length;
	}
	public synchronized int getSleepLenLength() {
		return SleepLen.length;
	}*/

	public synchronized void setNoteSeq(int col, int value) {
		NoteSeq[col] = value;
	}
	public synchronized void setGateLen(int col, float value) {
		GateLen[col] = value;
	}
	public synchronized void setSleepLen(int col, long value) {
		SleepLen[col] = value;
	}
	public synchronized void setARPSeq(int col, int value) {
		ARPSeq[col] = value;
	}
	public synchronized void setARPGateDiv(int col, float value) {
		ARPGateDiv[col] = value;
	}
	public synchronized void setARPSleepDiv(int col, float value) {
		ARPSleepDiv[col] = value;
	}

	public synchronized Mode getCmode() {
		return Cmode;
	}

	public synchronized void setCmode(Mode cmode) {
		Cmode = cmode;
	}

	public synchronized void Copy_SleepLength(long len) {
		for (int i = 0; i < NoteSeq.length; i++) {
			SleepLen[i] = len;
		}
	}
	public synchronized void Copy_GateLength(float len) {
		for (int i = 0; i < NoteSeq.length; i++) {
			GateLen[i] = len;
		}
		
	}
	
	/**
	 * 
	 * Remets les notes en absolu.
	 * 
	 */
	public synchronized void absolutizeNotes() {
		
		
		int ref = NoteSeq[0];
		for (int i = 0; i < NoteSeq.length; i++) {
			NoteSeq[i]=NoteSeq[i]-ref;
		}
	}

}
