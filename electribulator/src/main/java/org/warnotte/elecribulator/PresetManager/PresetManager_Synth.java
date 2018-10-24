package org.warnotte.elecribulator.PresetManager;

import javax.sound.midi.Receiver;

import org.warnotte.elecribulator.Multi.evt;
import org.warnotte.waxaudiomiditools.CControlers.KnowMidiList;

public class PresetManager_Synth extends PresetManager  {
	
		
	
	public PresetManager_Synth(evt evt, KnowMidiList kml)
		{
		super(kml);
		//kwml_drum = new KnowMidiList();
		//kwml_drum.init_DRUM();
		
		this.evt=evt;
		reset();
	}
	
	public PresetManager_Synth(evt evt, Receiver r, KnowMidiList kml)
	{
		this(evt, kml);
		set_Receiver(r);
	}
	
	public void applyPresets(ControlValue cv) {
		evt.pauseModulateurs(true);
		for (int j = 1; j < 6; j++) {
			applyPresets(evt.getOutputChannels()[j-1], cv);
		}
		
		evt.pauseModulateurs(false);
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
			
		for (int j = 1; j < 6; j++) {
			
			for (int i = 0; i < controleurs_synth.size(); i++) {
				ControlValue cv = controleurs_synth.get(i);
				applyPresets(evt.getOutputChannels()[j-1], cv);
				Thread.sleep(1);
				
			}
			
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

