package org.warnotte.elecribulator.Definitions;

public class OscillatorTypeList {
	
	
	public static String [] osctype = new String []
	                                              {
	                                          		"WAVEFORM",
	                                          		"DUAL OSC",
	                                          		"CHORD OSC",
	                                          		"UNISON OSC",
	                                          		"RING MOD",
	                                          		"OSC SYNC",
	                                          		"CROSS MOD",
	                                          		"VPM OSC",
	                                          		"WAVESHAPPING",
	                                          		"ADDITIVE OSC",
	                                          		"COMB OSC",
	                                          		"FORMANT OSC",
	                                          		"NOISE OSC",
	                                          		"PCM OSC+COMB",
	                                          		"PCM OSC+WS",
	                                          		"AUDIO IN+COMB",
	                                          	};
	                                          	                                                
	                                          	public static String [][] type = new String [][]
	                                          	                               {
	                                          			// WAVEFORM (4)
	                                          			{"Saw", "Pulse", "Tri", "Sin"},
	                                          			// DUAL OSC (20)
	                                          			{
	                                          				"SawSaw","SawSqu","SawTri","SawSin","SawNoise",
	                                          				"SquSaw","SquSqu","SquTri","SquSin","SquNoise",
	                                          				"TriSaw","TriSqu","TriTri","TriSin","TriNoise",
	                                          				"SinSaw","SinSqu","SinTri","SinSin","SinNoise",
	                                          				
	                                          			},
	                                          			// CHORD OSC (4)
	                                          			{"Saw", "Pulse", "Tri", "Sin"},
	                                          			// UNISON OSC (16)
	                                          			{"3Saw","4Saw","5Saw","6Saw", "3Pulse","4Pulse","5Pulse","6Pulse", "3Tri", "4Tri", "5Tri", "6Tri", "3Sin", "4Sin", "5Sin", "6Sin"},
	                                          			// RING MOD (20)
	                                          			{
	                                          				"SawSaw","SawSqu","SawTri","SawSin","SawNoise",
	                                          				"SquSaw","SquSqu","SquTri","SquSin","SquNoise",
	                                          				"TriSaw","TriSqu","TriTri","TriSin","TriNoise",
	                                          				"SinSaw","SinSqu","SinTri","SinSin","SinNoise",
	                                          				
	                                          			},
	                                          			// OSC SYNC(4)
	                                          			{"Saw", "Pulse", "Tri", "Sin"},
	                                          			// CROSS MOD (20)
	                                          			{
	                                          				"SawSaw","SawSqu","SawTri","SawSin","SawNoise",
	                                          				"SquSaw","SquSqu","SquTri","SquSin","SquNoise",
	                                          				"TriSaw","TriSqu","TriTri","TriSin","TriNoise",
	                                          				"SinSaw","SinSqu","SinTri","SinSin","SinNoise",
	                                          				
	                                          			},
	                                          			// VPM OSC (4)
	                                          			{"Saw", "Pulse", "Tri", "Sin"},
	                                          			// WAVESHAPPING (2)
	                                          			{"Type1", "Type2"},
	                                          			// ADDITIVE OSC (4)
	                                          			{"Saw", "Pulse", "Tri", "Sin"},
	                                          			// COMB OSC (5)
	                                          			{"Saw", "Pulse", "Tri", "Sin", "Noise"},
	                                          			// FORMANT OSC (1)
	                                          			{"------"},
	                                          			// NOISE OSC (5)
	                                          			{"------"},
	                                          			// PCM OSC+COMB (76)
	                                          			{
	                                          				"Piano","E Piano","Clav","M1-Organ", "Organ", "Marimba",
	                                          				"Vibe","Cymbell","Flute","AltoSax", "M1-TSax","Trumpet","MuteTp","BrassEns","VoiceAh", "M1-Choir","VoiceWav", "Violin",
	                                          				"Strings","Pizzicato","F.Guitar","KOPKOK.Guitar", "MuteGtr","FunkGtr","Sitar","KOPKOK.Bass","E.Bass", "M1-Bass",
	                                          				"PickBass","SlapBass","FMBass","88Bass", "BoostSaw","SawSqMix","HPFW Saw","OctBass1","OctBass2", "Saw5th",
	                                          				"Squ5th","SynSin1","SynSin2","SynSin3", "SynSin4","SynSin5","Synwire1","Synwire2","Digi1", "Digi2",
	                                          				"Digi3","Digi4","SynVox1","SynVox2", "EndLess","Syn-FX1","Syn-FX2","OrchHit","BandHit1", "BandHit2",
	                                          				"DiscoHit","RaveHit1", "RaveHit2","RaveHit3","RaveHit4", "RaveHit5","RaveHit6","CH-Piano","CH-M1Pia","CH-EPiano", "CH-Organ",
	                                          				"CH-Strgs","CH-Gtr1","CH-Gtr2","DR-BDs", "DR-SDs","DR-CymTm", "Dr-Percs"
	                                          				
	                                          			},
	                                          			// PCM OSC+WS (76)
	                                          			{
	                                          				"Piano","E Piano","Clav","M1-Organ",  "Organ", "Marimba",
	                                          				"Vibe","Cymbell","Flute","AltoSax", "M1-TSax","Trumpet","MuteTp","BrassEns","VoiceAh", "M1-Choir","VoiceWav", "Violin",
	                                          				"Strings","Pizzicato","F.Guitar","KOPKOK.Guitar", "MuteGtr","FunkGtr","Sitar","KOPKOK.Bass","E.Bass", "M1-Bass",
	                                          				"PickBass","SlapBass","FMBass","88Bass", "BoostSaw","SawSqMix","HPFW Saw","OctBass1","OctBass2", "Saw5th",
	                                          				"Squ5th","SynSin1","SynSin2","SynSin3", "SynSin4","SynSin5","Synwire1","Synwire2","Digi1", "Digi2",
	                                          				"Digi3","Digi4","SynVox1","SynVox2", "EndLess","Syn-FX1","Syn-FX2","OrchHit","BandHit1", "BandHit2",
	                                          				"DiscoHit","RaveHit1", "RaveHit2","RaveHit3","RaveHit4", "RaveHit5","RaveHit6","CH-Piano","CH-M1Pia","CH-EPiano", "CH-Organ",
	                                          				"CH-Strgs","CH-Gtr1","CH-Gtr2","DR-BDs", "DR-SDs","DR-CymTm", "Dr-Percs"
	                                          			},
	                                          			// AUDIO IN+COMB (1)
	                                          			{"-----"}
	                                          		
	                                          	                               };

	                                          	public static String get(int osc_type_value, int wave_value) {
	                                          		
	                                          		if (osc_type_value>=OscillatorTypeList.type.length)
	                                          			osc_type_value=OscillatorTypeList.type.length-1;
	                                          		
	                                          		String []b = OscillatorTypeList.type[osc_type_value];
	                                          		
	                                          		if (wave_value>=b.length)
	                                          			wave_value=b.length-1;
	                                          		
	                                          		return OscillatorTypeList.type[osc_type_value][wave_value];
	                                          		 
	                                          		 
	                                          	}

	                                          	public static String get(int osc_type_value) {
	                                          		
	                                          		if (osc_type_value>=osctype.length)
	                                          			osc_type_value=osctype.length-1;
	                                          		
	                                          		
	                                          		return osctype[osc_type_value];
	                                          	}

	                                          	public static int getWavesize(int osc_type_value) {
	                                          		if (osc_type_value>=osctype.length)
	                                          			osc_type_value=osctype.length-1;
	                                          		
	                                          		
	                                          		return osctype[osc_type_value].length();
	                                          		
	                                          	}
}
