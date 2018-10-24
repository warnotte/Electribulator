package orw.warnotte.elecribulator.PresetManager;

import javax.sound.midi.Receiver;

import org.warnotte.waxaudiomiditools.CControlers.KnowMidiList;

import orw.warnotte.elecribulator.Multi.evt;

public class PresetManager_Drum extends PresetManager {
	
	

	public PresetManager_Drum(evt evt, KnowMidiList kml)
	{
		super(kml);
		this.evt=evt;
		reset();
	}
	
	public PresetManager_Drum(evt evt, Receiver r, KnowMidiList kml)
	{
		this(evt,kml);
		set_Receiver(r);
	}
	
	public void applyPresets(ControlValue cv) {
		applyPresets(evt.getDrumChannel(), cv);
	}
	
	public void applyPresets()
	{
		if (isBypassSend()==true) return;
		// Arrete les modulateurs pour qlq secondes.
		evt.pauseModulateurs(true);
		try {
		/*	for (int i = 0; i < controleurs_drum.size(); i++) {
				ControlValue cv = controleurs_drum.get(i);
				applyPresets(9, cv);
				Thread.sleep(1);
				
			}*/
			
			
			for (int i = 0; i < controleurs_synth.size(); i++) {
				ControlValue cv = controleurs_synth.get(i);
				applyPresets(evt.getDrumChannel(), cv);
				Thread.sleep(1);
				
			}
			
				
		
	}
		 catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		// Redemare les modulateurs pour qlq secondes.
		evt.pauseModulateurs(false);
	}
	
	
	
}

