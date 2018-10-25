
package org.warnotte.elecribulator.GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.ShortMessage;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;

import org.warnotte.OBJ2GUI.Events.MyChangedEvent;
import org.warnotte.OBJ2GUI.Events.MyEventListener;
import org.warnotte.Swing.Component.WaxSlider.WRoundSlider;
import org.warnotte.elecribulator.Definitions.FXTypeList;
import org.warnotte.elecribulator.Definitions.OscillatorTypeList;
import org.warnotte.elecribulator.PresetManager.ControlValue;
import org.warnotte.elecribulator.PresetManager.PresetManager_Synth;

public class EMXPanel extends EMXPanelBase implements MyEventListener {

	
	private static final long serialVersionUID = 1L;
	//ImageIcon ic = null;
	private WRoundSlider jSlider_CUT_OFF = null;
	private WRoundSlider jSlider_PAN = null;
	private WRoundSlider jSlider_LEVEL = null;
	private WRoundSlider jSlider_MOD_SPEED = null;
	private WRoundSlider jSlider_MOD_DEPTH = null;
	private WRoundSlider jSlider_GLIDE = null;
	
	
	
	PresetManager_Synth pm = null;//new PresetManager_Synth(null);  //  @jve:decl-index=0:
	private JToggleButton jToggleButton_FX_SEND;
	private JRadioButton jCheckBox_FX1 = null;
	private JRadioButton jCheckBox_FX2 = null;
	private JRadioButton jCheckBox_FX3 = null;
	
	int MaxValue = 0x7F;
	private JRadioButton jCheckBox_MOD_TYPE_1;
	private JRadioButton jCheckBox_MOD_TYPE_2;
	private JRadioButton jCheckBox_MOD_TYPE_3;
	private JRadioButton jCheckBox_MOD_TYPE_4;
	private JRadioButton jCheckBox_MOD_TYPE_5;
	
	private JRadioButton jCheckBox_FILTER_TYPE_1;
	private JRadioButton jCheckBox_FILTER_TYPE_2;
	private JRadioButton jCheckBox_FILTER_TYPE_3;
	private JRadioButton jCheckBox_FILTER_TYPE_4;
	
	private JRadioButton jCheckBox_MOD_DEST_1;
	private JRadioButton jCheckBox_MOD_DEST_2;
	private JRadioButton jCheckBox_MOD_DEST_3;
	private JRadioButton jCheckBox_MOD_DEST_4;
	private JRadioButton jCheckBox_MOD_DEST_5;
	private JRadioButton jCheckBox_MOD_DEST_6;
	
	//private JRadioButton jCheckBox_FX_CHAIN_1 = null;
	private JRadioButton jCheckBox_FX_CHAIN_2 = null;
	private JRadioButton jCheckBox_FX_CHAIN_3 = null;
	
	private WRoundSlider jSlider_FILTER_RESONANCE;
	private WRoundSlider jSlider_EG_TIME;
	private WRoundSlider jSlider_FILTER_DRIVE;
	private WRoundSlider jSlider_OSC_EDIT_2;
	private WRoundSlider jSlider_OSC_EDIT_1;
	private WRoundSlider jSlider_OSC_TYPE;
	
	private WRoundSlider jSlider_FX_1_EDIT_1;
	private WRoundSlider jSlider_FX_1_EDIT_2;
	private WRoundSlider jSlider_FX_1_SELECT;
	private WRoundSlider jSlider_FX_2_EDIT_1;
	private WRoundSlider jSlider_FX_2_EDIT_2;
	private WRoundSlider jSlider_FX_2_SELECT;
	private WRoundSlider jSlider_FX_3_EDIT_1;
	private WRoundSlider jSlider_FX_3_EDIT_2;
	private WRoundSlider jSlider_FX_3_SELECT;
	private WRoundSlider jSlider_WAVE;
	private JLabel jLabel_LCD_LINE_1 = null;
	private JLabel jLabel_LCD_LINE_2 = null;
	
	private int FX_SELECTED=-1;
	
	Toolkit kit = Toolkit.getDefaultToolkit();
	private JToggleButton jToggleButton_BPM_SYNC;
	private JLabel jTextField_FX_SELECTED = null;
	
	/**
	 * This method initializes jSlider_CUT_OFF	
	 * 	
	 * @return javax.swing.JSlider	
	 */
	private WRoundSlider getJSlider_CUT_OFF() {
		if (jSlider_CUT_OFF == null) {
			
			final ControlValue cv = pm.getPresetByName("FILTER CUTOFF");
			jSlider_CUT_OFF = createSlider("FILTER CUTOFF", cv.getValue()); 
			jSlider_CUT_OFF.setBounds(new Rectangle(438+202, 365, 56, 56));
			jSlider_CUT_OFF.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					float value = jSlider_CUT_OFF.getValue();
					cv.setValue(value);
					pm.applyPresets(cv); 
				}
			});
		}
		return jSlider_CUT_OFF;
	}
	
	private WRoundSlider getJSlider_LEVEL() {
		if (jSlider_LEVEL == null) {
			final ControlValue cv = pm.getPresetByName("LEVEL");
			jSlider_LEVEL = createSlider("LEVEL", cv.getValue()); 
			jSlider_LEVEL.setBounds(new Rectangle(333+201, 153, 56, 56));
			jSlider_LEVEL.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					float value = jSlider_LEVEL.getValue();
					cv.setValue(value);
					pm.applyPresets(cv); 
				}
			});
		}
		return jSlider_LEVEL;
	}
	

	private WRoundSlider getJSlider_PAN() {
		if (jSlider_PAN == null) {
			final ControlValue cv = pm.getPresetByName("PAN");
			jSlider_PAN = createSlider("PAN", cv.getValue()); 
			jSlider_PAN.setBounds(new Rectangle(333+201, 65, 56, 56));
			jSlider_PAN.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					float value = jSlider_PAN.getValue();
					cv.setValue(value);
					pm.applyPresets(cv); 
				}
			});
		}
		return jSlider_PAN;
	}


	private WRoundSlider getJSlider_MOD_SPEED() {
		if (jSlider_MOD_SPEED == null) {
			final ControlValue cv = pm.getPresetByName("MOD SPEED");
			jSlider_MOD_SPEED = createSlider("MOD SPEED", cv.getValue()); 
			jSlider_MOD_SPEED.setBounds(new Rectangle(439+202, 65, 56, 56));
			jSlider_MOD_SPEED.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					float value = jSlider_MOD_SPEED.getValue();
					cv.setValue(value);
					pm.applyPresets(cv); 
				}
			});
		}
		return jSlider_MOD_SPEED;
	}

	private WRoundSlider getJSlider_MOD_DEPTH() {
		if (jSlider_MOD_DEPTH == null) {
			final ControlValue cv = pm.getPresetByName("MOD DEPTH");
			jSlider_MOD_DEPTH = createSlider("MOD DEPTH", cv.getValue()); 
			jSlider_MOD_DEPTH.setBounds(new Rectangle(546+202, 65, 56, 56));
			jSlider_MOD_DEPTH.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					float value = jSlider_MOD_DEPTH.getValue();
					cv.setValue(value);
					pm.applyPresets(cv); 
				}
			});
		}
		return jSlider_MOD_DEPTH;
	}
	private WRoundSlider getJSlider_GLIDE() {
		if (jSlider_GLIDE == null) {
			final ControlValue cv = pm.getPresetByName("GLIDE");
			jSlider_GLIDE = createSlider("GLIDE", cv.getValue()); 
			jSlider_GLIDE.setToolTipText("Evite de toucher � ca a mon avis");
			jSlider_GLIDE.setBounds(new Rectangle(226+201, 65, 56, 56));
			jSlider_GLIDE.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					float value = jSlider_GLIDE.getValue();
					cv.setValue(value);
					pm.applyPresets(cv); 
				}
			});
		}
		return jSlider_GLIDE;
	}
	
	private WRoundSlider getJSlider_FILTER_RESONNANCE() {
		if (jSlider_FILTER_RESONANCE == null) {
			final ControlValue cv = pm.getPresetByName("FILTER RESONNANCE");
			jSlider_FILTER_RESONANCE = createSlider("FILTER RESONNANCE", cv.getValue()); 
			jSlider_FILTER_RESONANCE.setBounds(new Rectangle(546+201, 365, 56, 56));
			
			jSlider_FILTER_RESONANCE.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					float value = jSlider_FILTER_RESONANCE.getValue();
					cv.setValue(value);
					pm.applyPresets(cv); 
				}
			});
		}
		return jSlider_FILTER_RESONANCE;
	}
	private WRoundSlider getJSlider_FILTER_DRIVE() {
		if (jSlider_FILTER_DRIVE == null) {
			final ControlValue cv = pm.getPresetByName("FILTER DRIVE");
			jSlider_FILTER_DRIVE = createSlider("FILTER DRIVE", cv.getValue()); 
			jSlider_FILTER_DRIVE.setBounds(new Rectangle(546+201, 454, 56, 56));
			
			
			jSlider_FILTER_DRIVE.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					float value = jSlider_FILTER_DRIVE.getValue();
					cv.setValue(value);
					pm.applyPresets(cv); 
				}
			});
		}
		return jSlider_FILTER_DRIVE;
	}
	
	
	private WRoundSlider getJSlider_EG_TIME() {
		if (jSlider_EG_TIME == null) {
			final ControlValue cv = pm.getPresetByName("FILTER EG INT");
			jSlider_EG_TIME = createSlider("FILTER EG INT", cv.getValue()); 
			jSlider_EG_TIME.setBounds(new Rectangle(438+202, 454, 56, 56));
			jSlider_EG_TIME.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					float value = jSlider_EG_TIME.getValue();
					cv.setValue(value);
					pm.applyPresets(cv); 
				}
			});
		}
		return jSlider_EG_TIME;
	}
	
	private WRoundSlider getJSlider_OSC_TYPE() {
		if (jSlider_OSC_TYPE == null) {
			//final ControlValue cv = pm.getPresetByName("OSC TYPE");
			ControlValue cv = pm.getPresetByName("OSC TYPE");
			jSlider_OSC_TYPE = createSlider("OSC TYPE", cv.getValue(), 0,127,360,100); 
			jSlider_OSC_TYPE.setBounds(new Rectangle(261+202, 361, 90, 90));
			jSlider_OSC_TYPE.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					ControlValue cv = pm.getPresetByName("OSC TYPE");
					float value = jSlider_OSC_TYPE.getValue();
					System.err.println("Slider value == "+value);
					cv.setValue(value);
					pm.applyPresets(cv); 
					refreshLCDTypeWave();
				}
			});
		}
		return jSlider_OSC_TYPE;
	}
	
	
	private WRoundSlider getJSlider_OSC_EDIT_1() {
		if (jSlider_OSC_EDIT_1 == null) {
			final ControlValue cv = pm.getPresetByName("OSC EDIT 1");
			jSlider_OSC_EDIT_1 = createSlider("OSC EDIT 1", cv.getValue()); 
			jSlider_OSC_EDIT_1.setBounds(new Rectangle(225+202, 497, 56, 56));
			jSlider_OSC_EDIT_1.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					float value = jSlider_OSC_EDIT_1.getValue();
					cv.setValue(value);
					pm.applyPresets(cv); 
				}
			});
		}
		return jSlider_OSC_EDIT_1;
	}
	
	private WRoundSlider getJSlider_OSC_EDIT_2() {
		if (jSlider_OSC_EDIT_2 == null) {
			final ControlValue cv = pm.getPresetByName("OSC EDIT 2");
			jSlider_OSC_EDIT_2 = createSlider("OSC EDIT 2", cv.getValue()); 
			jSlider_OSC_EDIT_2.setBounds(new Rectangle(332+202, 497, 56, 56));
			jSlider_OSC_EDIT_2.addChangeListener(new javax.swing.event.ChangeListener() {
				
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					float value = jSlider_OSC_EDIT_2.getValue();
					cv.setValue(value);
					pm.applyPresets(cv); 
				}
			});
		}
		return jSlider_OSC_EDIT_2;
	}
	
	
	private WRoundSlider getJSlider_FX_1_SELECT() {
		if (jSlider_FX_1_SELECT == null) {
			final ControlValue cv = pm.getPresetByName("FX 1 TYPE");
			jSlider_FX_1_SELECT = createSlider("FX 1 TYPE", cv.getValue(), 0, 127, 360, 82);
			jSlider_FX_1_SELECT.setBounds(new Rectangle(55, 57, 90, 90));
			jSlider_FX_1_SELECT.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent e) {
					FX_SELECTED=1;
					refresh_FX_TEXT(cv);
				}
				public void mouseExited(java.awt.event.MouseEvent e) {
					FX_SELECTED=-1;
					refresh_FX_TEXT(cv);
					repaint();
				}
			});
			jSlider_FX_1_SELECT.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					float value = jSlider_FX_1_SELECT.getValue();
					cv.setValue(value);
					pm.applyPresets(cv); 
					refresh_FX_TEXT(cv);
				}
			});
		}
		return jSlider_FX_1_SELECT;
	}
	private WRoundSlider getJSlider_FX_1_EDIT_1() {
		if (jSlider_FX_1_EDIT_1 == null) {
			final ControlValue cv = pm.getPresetByName("FX 1 EDIT1");
			jSlider_FX_1_EDIT_1 = createSlider("FX 1 EDIT1", cv.getValue()); 
			jSlider_FX_1_EDIT_1.setBounds(new Rectangle(19, 190, 56, 56));
			jSlider_FX_1_EDIT_1.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					float value = jSlider_FX_1_EDIT_1.getValue();
					cv.setValue(value);
					pm.applyPresets(cv); 
				}
			});
		}
		return jSlider_FX_1_EDIT_1;
	}
	
	private WRoundSlider getJSlider_FX_1_EDIT_2() {
		if (jSlider_FX_1_EDIT_2 == null) {
			final ControlValue cv = pm.getPresetByName("FX 1 EDIT2");
			jSlider_FX_1_EDIT_2 = createSlider("FX 1 EDIT2", cv.getValue()); 
			jSlider_FX_1_EDIT_2.setBounds(new Rectangle(126, 190, 56, 56));
			jSlider_FX_1_EDIT_2.addChangeListener(new javax.swing.event.ChangeListener() {
				
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					float value = jSlider_FX_1_EDIT_2.getValue();
					cv.setValue(value);
					pm.applyPresets(cv); 
				}
			});
		}
		return jSlider_FX_1_EDIT_2;
	}
	
	
	private WRoundSlider getJSlider_FX_2_SELECT() {
		if (jSlider_FX_2_SELECT == null) {
			final ControlValue cv = pm.getPresetByName("FX 2 TYPE");
			jSlider_FX_2_SELECT = createSlider("FX 2 TYPE", cv.getValue(),0, 127, 360, 82); 
			jSlider_FX_2_SELECT.setBounds(new Rectangle(55, 335, 90, 90));
			jSlider_FX_2_SELECT.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent e) {
					FX_SELECTED=2;
					refresh_FX_TEXT(cv);
				}
				public void mouseExited(java.awt.event.MouseEvent e) {
					FX_SELECTED=-1;
					refresh_FX_TEXT(cv);
				}
			});
			jSlider_FX_2_SELECT.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					float value = jSlider_FX_2_SELECT.getValue();
					cv.setValue(value);
					pm.applyPresets(cv); 
					refresh_FX_TEXT(cv);
				}
				
			});
		}
		return jSlider_FX_2_SELECT;
	}
	private WRoundSlider getJSlider_FX_2_EDIT_1() {
		if (jSlider_FX_2_EDIT_1 == null) {
			final ControlValue cv = pm.getPresetByName("FX 2 EDIT1");
			jSlider_FX_2_EDIT_1 = createSlider("FX 2 EDIT1", cv.getValue()); 
			jSlider_FX_2_EDIT_1.setBounds(new Rectangle(19, 469, 56, 56));
			jSlider_FX_2_EDIT_1.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					float value = jSlider_FX_2_EDIT_1.getValue();
					cv.setValue(value);
					pm.applyPresets(cv); 
				}
			});
		}
		return jSlider_FX_2_EDIT_1;
	}
	
	private WRoundSlider getJSlider_FX_2_EDIT_2() {
		if (jSlider_FX_2_EDIT_2 == null) {
			final ControlValue cv = pm.getPresetByName("FX 2 EDIT2");
			jSlider_FX_2_EDIT_2 = createSlider("FX 2 EDIT2", cv.getValue()); 
			jSlider_FX_2_EDIT_2.setBounds(new Rectangle(126, 469, 56, 56));
			jSlider_FX_2_EDIT_2.addChangeListener(new javax.swing.event.ChangeListener() {
				
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					float value = jSlider_FX_2_EDIT_2.getValue();
					cv.setValue(value);
					pm.applyPresets(cv); 
				}
			});
		}
		return jSlider_FX_2_EDIT_2;
	}

	
	
	private WRoundSlider getJSlider_FX_3_SELECT() {
		if (jSlider_FX_3_SELECT == null) {
			final ControlValue cv = pm.getPresetByName("FX 3 TYPE");
			jSlider_FX_3_SELECT = createSlider("FX 3 TYPE", cv.getValue(),0, 127, 360, 82); 
			jSlider_FX_3_SELECT.setBounds(new Rectangle(257, 246, 90, 90));
			jSlider_FX_3_SELECT.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent e) {
					FX_SELECTED=3;
					refresh_FX_TEXT(cv);
				}
				public void mouseExited(java.awt.event.MouseEvent e) {
					FX_SELECTED=-1;
					refresh_FX_TEXT(cv);
				}
			});
			jSlider_FX_3_SELECT.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					float value = jSlider_FX_3_SELECT.getValue();
					cv.setValue(value);
					pm.applyPresets(cv); 
					refresh_FX_TEXT(cv);
				}
			});
		}
		return jSlider_FX_3_SELECT;
	}
	protected void refresh_FX_TEXT(ControlValue cv) {
		if (FX_SELECTED!=-1)
		{
			String name = "";
			int v = (int)(cv.getValue());	
	//		if ((v>=0x00) && (v<=0x07)) 
			name=FXTypeList.type[v/0x08];
		
			
			jTextField_FX_SELECTED.setText("FX "+FX_SELECTED+ " "+name+"="+cv.getValue());
		}
		else
			jTextField_FX_SELECTED.setText("No Fx Selected");
	}

	private WRoundSlider getJSlider_FX_3_EDIT_1() {
		if (jSlider_FX_3_EDIT_1 == null) {
			final ControlValue cv = pm.getPresetByName("FX 3 EDIT1");
			jSlider_FX_3_EDIT_1 = createSlider("FX 3 EDIT1", cv.getValue()); 
			jSlider_FX_3_EDIT_1.setBounds(new Rectangle(220, 380, 56, 56));
			jSlider_FX_3_EDIT_1.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					float value = jSlider_FX_3_EDIT_1.getValue();
					cv.setValue(value);
					pm.applyPresets(cv); 
				}
			});
		}
		return jSlider_FX_3_EDIT_1;
	}
	
	private WRoundSlider getJSlider_FX_3_EDIT_2() {
		if (jSlider_FX_3_EDIT_2 == null) {
			final ControlValue cv = pm.getPresetByName("FX 3 EDIT2");
			jSlider_FX_3_EDIT_2 = createSlider("FX 3 EDIT2", cv.getValue()); 
			jSlider_FX_3_EDIT_2.setBounds(new Rectangle(327, 380, 56, 56));
			jSlider_FX_3_EDIT_2.addChangeListener(new javax.swing.event.ChangeListener() {
				
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					float value = jSlider_FX_3_EDIT_2.getValue();
					cv.setValue(value);
					pm.applyPresets(cv); 
				}
			});
		}
		return jSlider_FX_3_EDIT_2;
	}
	
	private WRoundSlider getJSlider_WAVE() {
		if (jSlider_WAVE == null) {
			final ControlValue cv = pm.getPresetByName("WAVE");
			jSlider_WAVE = createSlider("WAVE", cv.getValue(), 0, 76); 
			jSlider_WAVE.setBounds(new Rectangle(300, 635, 90, 90));
			jSlider_WAVE.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					float value = jSlider_WAVE.getValue();
					cv.setValue(value);
					pm.applyPresets(cv); 
					refreshLCDTypeWave();
				}
			});
		}
		return jSlider_WAVE;
	}

	protected void refreshLCDTypeWave() {
		
		final ControlValue cv = pm.getPresetByName("WAVE");
		int wave_value = (int) (cv.getValue());
		
		final ControlValue cv2 = pm.getPresetByName("OSC TYPE");
		int osc_type_value = (int) ((float)cv2.getValue()/1.0f);
		//System.err.println("OSC VALUE == "+osc_type_value);
		if ((osc_type_value>=0x00) && (osc_type_value<=0x07)) osc_type_value=0;
		if ((osc_type_value>=0x08) && (osc_type_value<=0x0F)) osc_type_value=1;
		if ((osc_type_value>=0x10) && (osc_type_value<=0x17)) osc_type_value=2;
		if ((osc_type_value>=0x18) && (osc_type_value<=0x1F)) osc_type_value=3;
		if ((osc_type_value>=0x20) && (osc_type_value<=0x27)) osc_type_value=4;
		if ((osc_type_value>=0x28) && (osc_type_value<=0x2F)) osc_type_value=5;
		if ((osc_type_value>=0x30) && (osc_type_value<=0x37)) osc_type_value=6;
		if ((osc_type_value>=0x38) && (osc_type_value<=0x3F)) osc_type_value=7;
		if ((osc_type_value>=0x40) && (osc_type_value<=0x47)) osc_type_value=8;
		if ((osc_type_value>=0x48) && (osc_type_value<=0x4F)) osc_type_value=9;
		if ((osc_type_value>=0x50) && (osc_type_value<=0x57)) osc_type_value=10;
		if ((osc_type_value>=0x58) && (osc_type_value<=0x5F)) osc_type_value=11;
		if ((osc_type_value>=0x60) && (osc_type_value<=0x67)) osc_type_value=12;
		if ((osc_type_value>=0x68) && (osc_type_value<=0x6F)) osc_type_value=13;
		if ((osc_type_value>=0x70) && (osc_type_value<=0x67)) osc_type_value=14;
		if ((osc_type_value>=0x78) && (osc_type_value<=0x7F)) osc_type_value=15;
		
		
		
		// TODO : Creer des tableau de tout les type de wave pour tout les type de synthese.
		
		//System.err.println("wave_value="+wave_value);
		//System.err.println("osc_type_value=="+osc_type_value);
		
		String nom =OscillatorTypeList.get(osc_type_value, wave_value);
		String oscType = OscillatorTypeList.get(osc_type_value);
		
		
		
	//	jSlider_WAVE.setMaximum(OscillatorTypeList.getWavesize(osc_type_value));
		
		setLCDText(""+oscType,""+nom);
	}

	protected void setLCDText(String string, String string2) {
		jLabel_LCD_LINE_1.setText(string);
		jLabel_LCD_LINE_1.repaint();
		jLabel_LCD_LINE_2.setText(string2);
		jLabel_LCD_LINE_2.repaint();
		
		
	}


	public void refreshComponents()
	{
		super.refreshComponents();
		refreshLCDTypeWave();
		refreshLedFILTERTYPE();
		refreshLedFXCHAIN();
		refreshLedFXSelected();
		refreshLedMODDEST();
		refreshLedMODTYPE();
		
		if (jToggleButton_FX_SEND.isSelected()==true)
			jToggleButton_FX_SEND.setIcon(new ImageIcon("images/boutons/BT_FX_SEND_RED.png"));
		else
			jToggleButton_FX_SEND.setIcon(new ImageIcon("images/boutons/BT_FX_SEND.png"));
		if (jToggleButton_BPM_SYNC.isSelected()==true)
			jToggleButton_BPM_SYNC.setIcon(new ImageIcon("images/boutons/BT_MOD_BPM_SYNC_RED.png"));
		else
			jToggleButton_BPM_SYNC.setIcon(new ImageIcon("images/boutons/BT_MOD_BPM_SYNC.png"));
	
		
	}
	
	private JToggleButton getJToggleButton_FX_SEND() {
		if (jToggleButton_FX_SEND == null) {
			jToggleButton_FX_SEND = new JToggleButton();
			jToggleButton_FX_SEND.setOpaque(false);
			//jToggleButton_FX_SEND.setBounds(new Rectangle(295, 248, 68, 27));
			jToggleButton_FX_SEND.setBounds(new Rectangle(295+202, 286, 68, 27));
			jToggleButton_FX_SEND.setMnemonic(KeyEvent.VK_UNDEFINED);
			
			jToggleButton_FX_SEND.setName("FX SEND");
			
			ControlValue cv = pm.getPresetByName("FX SEND");
			float value = cv.getValue();
			if (value>63) 
				jToggleButton_FX_SEND.setSelected(true);
			
			jToggleButton_FX_SEND.setPressedIcon(new ImageIcon("images/boutons/BT_FX_SEND_RED.png"));
			jToggleButton_FX_SEND.setIcon(new ImageIcon("images/boutons/BT_FX_SEND.png"));
			jToggleButton_FX_SEND.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					ControlValue cv = pm.getPresetByName("FX SEND");
					float value = 0;
					if (jToggleButton_FX_SEND.isSelected()==true)
						value = 126;
					else
						value = 0;
						
					cv.setValue(value);
					
				//	pm_synth.applyPresets(); // TODO : un peu lourd a mon avis.
					pm.applyPresets(cv); 
					
					if (jToggleButton_FX_SEND.isSelected()==true)
						jToggleButton_FX_SEND.setIcon(new ImageIcon("images/boutons/BT_FX_SEND_RED.png"));
					else
						jToggleButton_FX_SEND.setIcon(new ImageIcon("images/boutons/BT_FX_SEND.png"));
					
				}
			});
			if (jToggleButton_FX_SEND.isSelected()==true)
				jToggleButton_FX_SEND.setIcon(new ImageIcon("images/boutons/BT_FX_SEND_RED.png"));
			else
				jToggleButton_FX_SEND.setIcon(new ImageIcon("images/boutons/BT_FX_SEND.png"));
			
		}
		return jToggleButton_FX_SEND;
	}
	
	private JToggleButton getJToggleButton_BPM_SYNC() {
		if (jToggleButton_BPM_SYNC == null) {
			jToggleButton_BPM_SYNC = new JToggleButton();
			jToggleButton_BPM_SYNC.setOpaque(false);
			//jToggleButton_FX_SEND.setBounds(new Rectangle(295, 248, 68, 27));
			jToggleButton_BPM_SYNC.setBounds(new Rectangle(295+412, 286, 68, 27));
			jToggleButton_BPM_SYNC.setMnemonic(KeyEvent.VK_UNDEFINED);
			
			jToggleButton_BPM_SYNC.setName("MOD BPM SYNC");
			ControlValue cv = pm.getPresetByName("MOD BPM SYNC");	
			float value = cv.getValue();
			if (value>63) 
				jToggleButton_BPM_SYNC.setSelected(true);
			
			jToggleButton_BPM_SYNC.setPressedIcon(new ImageIcon("images/boutons/BT_MOD_BPM_SYNC_RED.png"));
			jToggleButton_BPM_SYNC.setIcon(new ImageIcon("images/boutons/BT_MOD_BPM_SYNC.png"));
			jToggleButton_BPM_SYNC.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					ControlValue cv = pm.getPresetByName("MOD BPM SYNC");
					float value = 0;
					if (jToggleButton_BPM_SYNC.isSelected()==true)
						value = 126;
					else
						value = 0;
						
					cv.setValue(value);
					
				//	pm_synth.applyPresets(); // TODO : un peu lourd a mon avis.
					pm.applyPresets(cv); 
					
					if (jToggleButton_BPM_SYNC.isSelected()==true)
						jToggleButton_BPM_SYNC.setIcon(new ImageIcon("images/boutons/BT_MOD_BPM_SYNC_RED.png"));
					else
						jToggleButton_BPM_SYNC.setIcon(new ImageIcon("images/boutons/BT_MOD_BPM_SYNC.png"));
					
				}
			});
			if (jToggleButton_BPM_SYNC.isSelected()==true)
				jToggleButton_BPM_SYNC.setIcon(new ImageIcon("images/boutons/BT_MOD_BPM_SYNC_RED.png"));
			else
				jToggleButton_BPM_SYNC.setIcon(new ImageIcon("images/boutons/BT_MOD_BPM_SYNC.png"));
			
		}
		return jToggleButton_BPM_SYNC;
	}

	/**
	 * This method initializes jCheckBox_FX1	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JRadioButton getJCheckBox_FX1() {
		if (jCheckBox_FX1 == null) {
			jCheckBox_FX1 = new JRadioButton();
			jCheckBox_FX1.setBounds(new Rectangle(290+202, 174, 24, 24));
			jCheckBox_FX1.setOpaque(false);
			jCheckBox_FX1.setSelectedIcon(new ImageIcon("images/led/on.png"));
			jCheckBox_FX1.setIcon(new ImageIcon("images/led/off.png"));
			jCheckBox_FX1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final ControlValue cv = pm.getPresetByName("FX SELECT");
					cv.setValue(0);
					pm.applyPresets(cv); 
				}
			});
		}
		return jCheckBox_FX1;
	}
	private JRadioButton getJCheckBox_FX2() {
		if (jCheckBox_FX2 == null) {
			jCheckBox_FX2 = new JRadioButton();
			jCheckBox_FX2.setBounds(new Rectangle(290+202, 196, 24, 24));
			jCheckBox_FX2.setOpaque(false);
			jCheckBox_FX2.setSelectedIcon(new ImageIcon("images/led/on.png"));
			jCheckBox_FX2.setIcon(new ImageIcon("images/led/off.png"));
			jCheckBox_FX2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final ControlValue cv = pm.getPresetByName("FX SELECT");
					cv.setValue(64);
					pm.applyPresets(cv); 
				}
			});
		}
		return jCheckBox_FX2;
	}
	private JRadioButton getJCheckBox_FX3() {
		if (jCheckBox_FX3 == null) {
			jCheckBox_FX3 = new JRadioButton();
			jCheckBox_FX3.setBounds(new Rectangle(290+202, 219, 24, 24));
			jCheckBox_FX3.setOpaque(false);
			jCheckBox_FX3.setSelectedIcon(new ImageIcon("images/led/on.png"));
			jCheckBox_FX3.setIcon(new ImageIcon("images/led/off.png"));
			jCheckBox_FX3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final ControlValue cv = pm.getPresetByName("FX SELECT");
					cv.setValue(127);
					pm.applyPresets(cv); 
				}
			});
		}
		return jCheckBox_FX3;
	}
	
	/*private JRadioButton getJCheckBox_FX_CIRCUIT_1() {
		if (jCheckBox_FX_CHAIN_1 == null) {
			jCheckBox_FX_CHAIN_1 = new JRadioButton();
			jCheckBox_FX_CHAIN_1.setBounds(new Rectangle(190+202, 220, 24, 24));
			jCheckBox_FX_CHAIN_1.setOpaque(false);
			jCheckBox_FX_CHAIN_1.setSelectedIcon(new ImageIcon("images/led/on.png"));
			jCheckBox_FX_CHAIN_1.setIcon(new ImageIcon("images/led/off.png"));
			jCheckBox_FX_CHAIN_1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final ControlValue cv = pm_synth.getPresetByName("FX CHAIN");
				/*	*T2-5 : 00~1F : none
			         20~3F : FX1-FX2
			         40~5F : FX2-FX3
			         60~7F : FX1-FX2-FX3
					int value = 0
					if (jCheckBox_FX_CHAIN_1.isSelected() && jCheckBox_FX_CHAIN_2.isSelected()) value = 0x25; 
					if (jCheckBox_FX_CHAIN_2.isSelected() && jCheckBox_FX_CHAIN_3.isSelected()) value = 0x45;
					if (jCheckBox_FX_CHAIN_1.isSelected() &&jCheckBox_FX_CHAIN_2.isSelected() && jCheckBox_FX_CHAIN_3.isSelected()) value = 0x65;
					cv.setValue(value);
					pm_synth.applyPresets(cv); 
				}
			});
		}
		return jCheckBox_FX_CHAIN_1;
	}*/
	private JRadioButton getJCheckBox_FX_CIRCUIT_2() {
		if (jCheckBox_FX_CHAIN_2 == null) {
			jCheckBox_FX_CHAIN_2 = new JRadioButton();
			jCheckBox_FX_CHAIN_2.setBounds(new Rectangle(269, 473, 24, 24));
			jCheckBox_FX_CHAIN_2.setOpaque(false);
			jCheckBox_FX_CHAIN_2.setSelectedIcon(new ImageIcon("images/led/on.png"));
			jCheckBox_FX_CHAIN_2.setIcon(new ImageIcon("images/led/off.png"));
			jCheckBox_FX_CHAIN_2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final ControlValue cv = pm.getPresetByName("FX CHAIN");
				/*	*T2-5 : 00~1F : none
			         20~3F : FX1-FX2
			         40~5F : FX2-FX3
			         60~7F : FX1-FX2-FX3*/
					int value = 0;
					if (jCheckBox_FX_CHAIN_2.isSelected()) value = 0x25;
					if (jCheckBox_FX_CHAIN_3.isSelected()) value = 0x45;
					if (jCheckBox_FX_CHAIN_2.isSelected() && jCheckBox_FX_CHAIN_3.isSelected()) value = 0x65;
					cv.setValue(value);
					pm.applyPresets(cv); 
				}
			});
		}
		return jCheckBox_FX_CHAIN_2;
	}
	private JRadioButton getJCheckBox_FX_CIRCUIT_3() {
		if (jCheckBox_FX_CHAIN_3 == null) {
			jCheckBox_FX_CHAIN_3 = new JRadioButton();
			jCheckBox_FX_CHAIN_3.setBounds(new Rectangle(269, 504, 24, 24));
			jCheckBox_FX_CHAIN_3.setOpaque(false);
			jCheckBox_FX_CHAIN_3.setSelectedIcon(new ImageIcon("images/led/on.png"));
			jCheckBox_FX_CHAIN_3.setIcon(new ImageIcon("images/led/off.png"));
			jCheckBox_FX_CHAIN_3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final ControlValue cv = pm.getPresetByName("FX CHAIN");
				/*	*T2-5 : 00~1F : none
			         20~3F : FX1-FX2
			         40~5F : FX2-FX3
			         60~7F : FX1-FX2-FX3*/
					int value = 0;
					if (jCheckBox_FX_CHAIN_2.isSelected()) value = 0x25; 
					if (jCheckBox_FX_CHAIN_3.isSelected()) value = 0x45; 
					if (jCheckBox_FX_CHAIN_2.isSelected() && jCheckBox_FX_CHAIN_3.isSelected()) value = 0x65;
					cv.setValue(value);
					pm.applyPresets(cv); 
				}
			});
		}
		return jCheckBox_FX_CHAIN_3;
	}
	
	
	
	/**T2-2 : 00~0F : PITCH
    10~1F : OSC EDIT1
    20~2F : OSC EDIT2
    30~3F : CUTOFF
    40~4F : AMP
    50~7F : PAN*/
	
	private JRadioButton getJCheckBox_MOD_TYPE_1() {
		if (jCheckBox_MOD_TYPE_1 == null) {
			jCheckBox_MOD_TYPE_1 = new JRadioButton();
			jCheckBox_MOD_TYPE_1.setOpaque(false);
			jCheckBox_MOD_TYPE_1.setSelectedIcon(new ImageIcon("images/led/on.png"));
			jCheckBox_MOD_TYPE_1.setBounds(new Rectangle(460+202,129, 24, 24));
			jCheckBox_MOD_TYPE_1.setIcon(new ImageIcon("images/led/off.png"));
			jCheckBox_MOD_TYPE_1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final ControlValue cv = pm.getPresetByName("MOD TYPE");
					cv.setValue(0);
					pm.applyPresets(cv); 
				}
			});
		}
		return jCheckBox_MOD_TYPE_1;
	}
	private JRadioButton getJCheckBox_MOD_TYPE_2() {
		if (jCheckBox_MOD_TYPE_2 == null) {
			jCheckBox_MOD_TYPE_2 = new JRadioButton();
			jCheckBox_MOD_TYPE_2.setOpaque(false);
			jCheckBox_MOD_TYPE_2.setSelectedIcon(new ImageIcon("images/led/on.png"));
			jCheckBox_MOD_TYPE_2.setBounds(new Rectangle(460+202, 151, 24, 24));
			jCheckBox_MOD_TYPE_2.setIcon(new ImageIcon("images/led/off.png"));
			jCheckBox_MOD_TYPE_2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final ControlValue cv = pm.getPresetByName("MOD TYPE");
					cv.setValue(26);
					pm.applyPresets(cv); 
				}
			});
		}
		return jCheckBox_MOD_TYPE_2;
	}
	private JRadioButton getJCheckBox_MOD_TYPE_3() {
		if (jCheckBox_MOD_TYPE_3 == null) {
			jCheckBox_MOD_TYPE_3 = new JRadioButton();
			jCheckBox_MOD_TYPE_3.setOpaque(false);
			jCheckBox_MOD_TYPE_3.setSelectedIcon(new ImageIcon("images/led/on.png"));
			jCheckBox_MOD_TYPE_3.setBounds(new Rectangle(460+202, 174, 24, 24));
			jCheckBox_MOD_TYPE_3.setIcon(new ImageIcon("images/led/off.png"));
			jCheckBox_MOD_TYPE_3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final ControlValue cv = pm.getPresetByName("MOD TYPE");
					cv.setValue(40);
					pm.applyPresets(cv); 
				}
			});
		}
		return jCheckBox_MOD_TYPE_3;
	}
	private JRadioButton getJCheckBox_MOD_TYPE_4() {
		if (jCheckBox_MOD_TYPE_4 == null) {
			jCheckBox_MOD_TYPE_4 = new JRadioButton();
			jCheckBox_MOD_TYPE_4.setOpaque(false);
			jCheckBox_MOD_TYPE_4.setSelectedIcon(new ImageIcon("images/led/on.png"));
			jCheckBox_MOD_TYPE_4.setBounds(new Rectangle(460+202, 196, 24, 24));
			jCheckBox_MOD_TYPE_4.setIcon(new ImageIcon("images/led/off.png"));
			jCheckBox_MOD_TYPE_4.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final ControlValue cv = pm.getPresetByName("MOD TYPE");
					cv.setValue(50);
					pm.applyPresets(cv); 
				}
			});
		}
		return jCheckBox_MOD_TYPE_4;
	}
	private JRadioButton getJCheckBox_MOD_TYPE_5() {
		if (jCheckBox_MOD_TYPE_5 == null) {
			jCheckBox_MOD_TYPE_5 = new JRadioButton();
			jCheckBox_MOD_TYPE_5.setOpaque(false);
			jCheckBox_MOD_TYPE_5.setSelectedIcon(new ImageIcon("images/led/on.png"));
			jCheckBox_MOD_TYPE_5.setBounds(new Rectangle(460+202, 219, 24, 24));
			jCheckBox_MOD_TYPE_5.setIcon(new ImageIcon("images/led/off.png"));
			jCheckBox_MOD_TYPE_5.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final ControlValue cv = pm.getPresetByName("MOD TYPE");
					cv.setValue(64);
					pm.applyPresets(cv); 
				}
			});
		}
		return jCheckBox_MOD_TYPE_5;
	}
	
	private JRadioButton getJCheckBox_MOD_DEST_1() {
		if (jCheckBox_MOD_DEST_1 == null) {
			jCheckBox_MOD_DEST_1 = new JRadioButton();
			jCheckBox_MOD_DEST_1.setOpaque(false);
			jCheckBox_MOD_DEST_1.setSelectedIcon(new ImageIcon("images/led/on.png"));
			jCheckBox_MOD_DEST_1.setBounds(new Rectangle(543+202, 129, 24, 24));
			jCheckBox_MOD_DEST_1.setIcon(new ImageIcon("images/led/off.png"));
			jCheckBox_MOD_DEST_1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final ControlValue cv = pm.getPresetByName("MOD DEST");
					cv.setValue(0x05);
					pm.applyPresets(cv); 
				}
			});
		}
		return jCheckBox_MOD_DEST_1;
	}
	private JRadioButton getJCheckBox_MOD_DEST_2() {
		if (jCheckBox_MOD_DEST_2 == null) {
			jCheckBox_MOD_DEST_2 = new JRadioButton();
			jCheckBox_MOD_DEST_2.setOpaque(false);
			jCheckBox_MOD_DEST_2.setSelectedIcon(new ImageIcon("images/led/on.png"));
			jCheckBox_MOD_DEST_2.setBounds(new Rectangle(543+202, 151, 24, 24));
			jCheckBox_MOD_DEST_2.setIcon(new ImageIcon("images/led/off.png"));
			jCheckBox_MOD_DEST_2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final ControlValue cv = pm.getPresetByName("MOD DEST");
					cv.setValue(0x15);
					pm.applyPresets(cv); 
				}
			});
		}
		return jCheckBox_MOD_DEST_2;
	}
	private JRadioButton getJCheckBox_MOD_DEST_3() {
		if (jCheckBox_MOD_DEST_3 == null) {
			jCheckBox_MOD_DEST_3 = new JRadioButton();
			jCheckBox_MOD_DEST_3.setOpaque(false);
			jCheckBox_MOD_DEST_3.setSelectedIcon(new ImageIcon("images/led/on.png"));
			jCheckBox_MOD_DEST_3.setBounds(new Rectangle(543+202, 174, 24, 24));
			jCheckBox_MOD_DEST_3.setIcon(new ImageIcon("images/led/off.png"));
			jCheckBox_MOD_DEST_3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final ControlValue cv = pm.getPresetByName("MOD DEST");
					cv.setValue(0x25);
					pm.applyPresets(cv); 
				}
			});
		}
		return jCheckBox_MOD_DEST_3;
	}
	private JRadioButton getJCheckBox_MOD_DEST_4() {
		if (jCheckBox_MOD_DEST_4 == null) {
			jCheckBox_MOD_DEST_4 = new JRadioButton();
			jCheckBox_MOD_DEST_4.setOpaque(false);
			jCheckBox_MOD_DEST_4.setSelectedIcon(new ImageIcon("images/led/on.png"));
			jCheckBox_MOD_DEST_4.setBounds(new Rectangle(544+202, 197, 24, 24));
			jCheckBox_MOD_DEST_4.setIcon(new ImageIcon("images/led/off.png"));
			jCheckBox_MOD_DEST_4.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final ControlValue cv = pm.getPresetByName("MOD DEST");
					cv.setValue(0x35);
					pm.applyPresets(cv); 
				}
			});
		}
		return jCheckBox_MOD_DEST_4;
	}
	
	private JRadioButton getJCheckBox_MOD_DEST_5() {
		if (jCheckBox_MOD_DEST_5 == null) {
			jCheckBox_MOD_DEST_5 = new JRadioButton();
			jCheckBox_MOD_DEST_5.setOpaque(false);
			jCheckBox_MOD_DEST_5.setSelectedIcon(new ImageIcon("images/led/on.png"));
			jCheckBox_MOD_DEST_5.setBounds(new Rectangle(544+202, 220, 24, 24));
			jCheckBox_MOD_DEST_5.setIcon(new ImageIcon("images/led/off.png"));
			jCheckBox_MOD_DEST_5.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final ControlValue cv = pm.getPresetByName("MOD DEST");
					cv.setValue(0x45);
					pm.applyPresets(cv); 
				}
			});
		}
		return jCheckBox_MOD_DEST_5;
	}
	
	private JRadioButton getJCheckBox_MOD_DEST_6() {
		if (jCheckBox_MOD_DEST_6 == null) {
			jCheckBox_MOD_DEST_6 = new JRadioButton();
			jCheckBox_MOD_DEST_6.setOpaque(false);
			jCheckBox_MOD_DEST_6.setSelectedIcon(new ImageIcon("images/led/on.png"));
			jCheckBox_MOD_DEST_6.setBounds(new Rectangle(543+202, 219+24, 24, 24));
			jCheckBox_MOD_DEST_6.setIcon(new ImageIcon("images/led/off.png"));
			jCheckBox_MOD_DEST_6.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final ControlValue cv = pm.getPresetByName("MOD DEST");
					cv.setValue(0x60);
					pm.applyPresets(cv); 
				}
			});
		}
		return jCheckBox_MOD_DEST_6;
	}

	
	private JRadioButton getJCheckBox_FILTER_TYPE_1() {
		if (jCheckBox_FILTER_TYPE_1 == null) {
			jCheckBox_FILTER_TYPE_1 = new JRadioButton();
			jCheckBox_FILTER_TYPE_1.setOpaque(false);
			jCheckBox_FILTER_TYPE_1.setSelectedIcon(new ImageIcon("images/led/on.png"));
			jCheckBox_FILTER_TYPE_1.setBounds(new Rectangle(715,519, 24, 24));
			jCheckBox_FILTER_TYPE_1.setIcon(new ImageIcon("images/led/off.png"));
			jCheckBox_FILTER_TYPE_1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final ControlValue cv = pm.getPresetByName("FILTER TYPE");
					cv.setValue(0x0);
					
					pm.applyPresets(cv); 
				}
			});
		}
		return jCheckBox_FILTER_TYPE_1;
	}
	private JRadioButton getJCheckBox_FILTER_TYPE_2() {
		if (jCheckBox_FILTER_TYPE_2 == null) {
			jCheckBox_FILTER_TYPE_2 = new JRadioButton();
			jCheckBox_FILTER_TYPE_2.setOpaque(false);
			jCheckBox_FILTER_TYPE_2.setSelectedIcon(new ImageIcon("images/led/on.png"));
			jCheckBox_FILTER_TYPE_2.setBounds(new Rectangle(753,519, 24, 24));
			jCheckBox_FILTER_TYPE_2.setIcon(new ImageIcon("images/led/off.png"));
			jCheckBox_FILTER_TYPE_2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final ControlValue cv = pm.getPresetByName("FILTER TYPE");
					cv.setValue(0x25);
					
					pm.applyPresets(cv); 
				}
			});
		}
		return jCheckBox_FILTER_TYPE_2;
	}
	private JRadioButton getJCheckBox_FILTER_TYPE_3() {
		if (jCheckBox_FILTER_TYPE_3 == null) {
			jCheckBox_FILTER_TYPE_3 = new JRadioButton();
			jCheckBox_FILTER_TYPE_3.setOpaque(false);
			jCheckBox_FILTER_TYPE_3.setSelectedIcon(new ImageIcon("images/led/on.png"));
			jCheckBox_FILTER_TYPE_3.setBounds(new Rectangle(715,542, 24, 24));
			jCheckBox_FILTER_TYPE_3.setIcon(new ImageIcon("images/led/off.png"));
			jCheckBox_FILTER_TYPE_3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final ControlValue cv = pm.getPresetByName("FILTER TYPE");
					cv.setValue(0x45);
					pm.applyPresets(cv); 
				}
			});
		}
		return jCheckBox_FILTER_TYPE_3;
	}
	private JRadioButton getJCheckBox_FILTER_TYPE_4() {
		if (jCheckBox_FILTER_TYPE_4 == null) {
			jCheckBox_FILTER_TYPE_4 = new JRadioButton();
			jCheckBox_FILTER_TYPE_4.setOpaque(false);
			jCheckBox_FILTER_TYPE_4.setSelectedIcon(new ImageIcon("images/led/on.png"));
			jCheckBox_FILTER_TYPE_4.setBounds(new Rectangle(753,542, 24, 24));
			jCheckBox_FILTER_TYPE_4.setIcon(new ImageIcon("images/led/off.png"));
			jCheckBox_FILTER_TYPE_4.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final ControlValue cv = pm.getPresetByName("FILTER TYPE");
					cv.setValue(0x65);
					pm.applyPresets(cv); 
				}
			});
		}
		return jCheckBox_FILTER_TYPE_4;
	}
	
	
	private void setLedFXSelect(int index)
	{
		jCheckBox_FX1.setSelected(false);
		jCheckBox_FX2.setSelected(false);
		jCheckBox_FX3.setSelected(false);
		if (index==0) jCheckBox_FX1.setSelected(true);
		if (index==1) jCheckBox_FX2.setSelected(true);
		if (index==2) jCheckBox_FX3.setSelected(true);
			
	}
	private void setLedFXModType(int index)
	{
		jCheckBox_MOD_TYPE_1.setSelected(false);
		jCheckBox_MOD_TYPE_2.setSelected(false);
		jCheckBox_MOD_TYPE_3.setSelected(false);
		jCheckBox_MOD_TYPE_4.setSelected(false);
		jCheckBox_MOD_TYPE_5.setSelected(false);
		
		if (index==0) jCheckBox_MOD_TYPE_1.setSelected(true);
		if (index==1) jCheckBox_MOD_TYPE_2.setSelected(true);
		if (index==2) jCheckBox_MOD_TYPE_3.setSelected(true);
		if (index==3) jCheckBox_MOD_TYPE_4.setSelected(true);
		if (index==4) jCheckBox_MOD_TYPE_5.setSelected(true);
			
	}
	private void setLedFXFilteType(int index)
	{
		jCheckBox_FILTER_TYPE_1.setSelected(false);
		jCheckBox_FILTER_TYPE_2.setSelected(false);
		jCheckBox_FILTER_TYPE_3.setSelected(false);
		jCheckBox_FILTER_TYPE_4.setSelected(false);
		
		if (index==0) jCheckBox_FILTER_TYPE_1.setSelected(true);
		if (index==1) jCheckBox_FILTER_TYPE_2.setSelected(true);
		if (index==2) jCheckBox_FILTER_TYPE_3.setSelected(true);
		if (index==3) jCheckBox_FILTER_TYPE_4.setSelected(true);
		
			
	}
	private void setLedFXModDest(int index)
	{
		jCheckBox_MOD_DEST_1.setSelected(false);
		jCheckBox_MOD_DEST_2.setSelected(false);
		jCheckBox_MOD_DEST_3.setSelected(false);
		jCheckBox_MOD_DEST_4.setSelected(false);
		jCheckBox_MOD_DEST_5.setSelected(false);
		jCheckBox_MOD_DEST_6.setSelected(false);
		
		if (index==0) jCheckBox_MOD_DEST_1.setSelected(true);
		if (index==1) jCheckBox_MOD_DEST_2.setSelected(true);
		if (index==2) jCheckBox_MOD_DEST_3.setSelected(true);
		if (index==3) jCheckBox_MOD_DEST_4.setSelected(true);
		if (index==4) jCheckBox_MOD_DEST_5.setSelected(true);
		if (index==5) jCheckBox_MOD_DEST_6.setSelected(true);
			
	}
	
	private void setLedFXChain(int index)
	{
	//	jCheckBox_FX_CHAIN_1.setSelected(false);
		jCheckBox_FX_CHAIN_2.setSelected(false);
		jCheckBox_FX_CHAIN_3.setSelected(false);
		
		/*T2-5 :
		 00~1F : none
        20~3F : FX1-FX2
        40~5F : FX2-FX3
        60~7F : FX1-FX2-FX3*/

		if (index==1){/*jCheckBox_FX_CHAIN_1.setSelected(true);*/jCheckBox_FX_CHAIN_2.setSelected(true);}
		if (index==2){jCheckBox_FX_CHAIN_2.setSelected(true);jCheckBox_FX_CHAIN_3.setSelected(true);}
		if (index==3){/*jCheckBox_FX_CHAIN_1.setSelected(true);*/jCheckBox_FX_CHAIN_2.setSelected(true);jCheckBox_FX_CHAIN_3.setSelected(true);}
	}
	
	
	/**
	 * This method initializes jTextField_FX_SELECTED	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JLabel getJTextField_FX_SELECTED() {
		if (jTextField_FX_SELECTED == null) {
			jTextField_FX_SELECTED = new JLabel();
			jTextField_FX_SELECTED.setBounds(new Rectangle(75, 682, 210, 22));
			jTextField_FX_SELECTED.setOpaque(false);
		//	jTextField_FX_SELECTED.setEditable(false);
		//	jTextField_FX_SELECTED.setEnabled(false);
			jTextField_FX_SELECTED.setText("No FX selected");
		}
		return jTextField_FX_SELECTED;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * This is the default constructor
	 */
	public EMXPanel(PresetManager_Synth pm) {
		super(pm,false);
		this.pm=pm;
		//ic = new ImageIcon("images\\EMX.png");
		try {
			initialize();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		refreshLedFXSelected();
		refreshLedMODTYPE();
		refreshLedMODDEST();
		refreshLedFILTERTYPE();
		refreshLedFXCHAIN();
		refreshLCDTypeWave();
		/*doLayout();
		revalidate();
		repaint();*/
		
		pm.getEvt().getElec_Reic().addIOEnteredExitedListener(this);
		
		revalidate();
		doLayout();
		repaint();
		
	}


	

	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 * @throws FileNotFoundException 
	 */
	private void initialize() throws FileNotFoundException {
		loadBackGround(getClass().getResource("/images/fond/EMX_Wax.png"));
		
		//this.setSize(ic.getIconWidth(),ic.getIconHeight());
		jLabel_LCD_LINE_1 = new JLabel();
		jLabel_LCD_LINE_1.setText("WAX-EMX");
		jLabel_LCD_LINE_2 = new JLabel();
		jLabel_LCD_LINE_2.setText("WAX-EMX");
		
		 /* Font myfont = null ;
			try {
				Font font = Font.createFont(Font.TRUETYPE_FONT, new File("BigDots.ttf"));
				//msg1 = font1.getFontName();
				 myfont = font.deriveFont(24);
					jLabel_LCD_LINE_1.setFont(myfont);
			} catch (FontFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
	          
		
	
		//jLabel_LCD_LINE_1.setFont(new Font("Arcade", Font.PLAIN, 24));
		jLabel_LCD_LINE_1.setBounds(new Rectangle(75, 632, 210, 24));
		//jLabel_LCD_LINE_2.setFont(new Font("Arcade", Font.PLAIN, 32));
		jLabel_LCD_LINE_2.setBounds(new Rectangle(76, 658, 210, 22));
		this.setLayout(null);
		this.setPreferredSize(new Dimension(842, 800));
		this.add(getJSlider_CUT_OFF(), null);
		this.add(getJToggleButton_BPM_SYNC(), null);
		
		this.add(getJToggleButton_FX_SEND(), null);
		this.add(getJCheckBox_FX1(), null);
		this.add(getJCheckBox_FX2(), null);
		this.add(getJCheckBox_FX3(), null);
		ButtonGroup rbg = new ButtonGroup();
		rbg.add(getJCheckBox_FX1());
		rbg.add(getJCheckBox_FX2());
		rbg.add(getJCheckBox_FX3());
		this.add(getJCheckBox_MOD_TYPE_1(), null);
		this.add(getJCheckBox_MOD_TYPE_2(), null);
		this.add(getJCheckBox_MOD_TYPE_3(), null);
		this.add(getJCheckBox_MOD_TYPE_4(), null);
		this.add(getJCheckBox_MOD_TYPE_5(), null);
		ButtonGroup rbg2 = new ButtonGroup();
		rbg2.add(getJCheckBox_MOD_TYPE_1());
		rbg2.add(getJCheckBox_MOD_TYPE_2());
		rbg2.add(getJCheckBox_MOD_TYPE_3());
		rbg2.add(getJCheckBox_MOD_TYPE_4());
		rbg2.add(getJCheckBox_MOD_TYPE_5());
		
		
		
		this.add(getJCheckBox_FILTER_TYPE_1(), null);
		this.add(getJCheckBox_FILTER_TYPE_2(), null);
		this.add(getJCheckBox_FILTER_TYPE_3(), null);
		this.add(getJCheckBox_FILTER_TYPE_4(), null);
		ButtonGroup rbg4 = new ButtonGroup();
		rbg4.add(getJCheckBox_FILTER_TYPE_1());
		rbg4.add(getJCheckBox_FILTER_TYPE_2());
		rbg4.add(getJCheckBox_FILTER_TYPE_3());
		rbg4.add(getJCheckBox_FILTER_TYPE_4());
		
		
		
		this.add(getJCheckBox_MOD_DEST_1(), null);
		this.add(getJCheckBox_MOD_DEST_2(), null);
		this.add(getJCheckBox_MOD_DEST_3(), null);
		this.add(getJCheckBox_MOD_DEST_4(), null);
		this.add(getJCheckBox_MOD_DEST_5(), null);
		this.add(getJCheckBox_MOD_DEST_6(), null);
		this.add(getJSlider_PAN(), null);
		this.add(getJSlider_GLIDE(), null);
		this.add(getJSlider_LEVEL(), null);
		this.add(getJSlider_MOD_SPEED(), null);
		this.add(getJSlider_MOD_DEPTH(), null);
		this.add(getJSlider_OSC_EDIT_1(), null);
		this.add(getJSlider_OSC_EDIT_2(), null);
		this.add(getJSlider_OSC_TYPE(), null);
		this.add(getJSlider_FILTER_RESONNANCE(), null);
		this.add(getJSlider_FILTER_DRIVE(), null);
		this.add(getJSlider_EG_TIME(), null);
		
		ButtonGroup rbg3 = new ButtonGroup();
		rbg3.add(getJCheckBox_MOD_DEST_1());
		rbg3.add(getJCheckBox_MOD_DEST_2());
		rbg3.add(getJCheckBox_MOD_DEST_3());
		rbg3.add(getJCheckBox_MOD_DEST_4());
		rbg3.add(getJCheckBox_MOD_DEST_5());
		rbg3.add(getJCheckBox_MOD_DEST_6());
		
		this.add(getJSlider_FX_1_SELECT(), null);
		this.add(getJSlider_FX_1_EDIT_1(), null);
		this.add(getJSlider_FX_1_EDIT_2(), null);
		this.add(getJSlider_FX_2_SELECT(), null);
		this.add(getJSlider_FX_2_EDIT_1(), null);
		this.add(getJSlider_FX_2_EDIT_2(), null);
		this.add(getJSlider_FX_3_SELECT(), null);
		this.add(getJSlider_FX_3_EDIT_1(), null);
		this.add(getJSlider_FX_3_EDIT_2(), null);
		
	//	this.add(getJCheckBox_FX_CIRCUIT_1(), null);
		this.add(getJCheckBox_FX_CIRCUIT_2(), null);
		this.add(getJCheckBox_FX_CIRCUIT_3(), null);
		
		this.add(jLabel_LCD_LINE_1, null);
		this.add(jLabel_LCD_LINE_2, null);
		this.add(getJSlider_WAVE(), null);
		this.add(getJTextField_FX_SELECTED(), null);
		
		
		
	/*	ButtonGroup rbg5 = new ButtonGroup();
		rbg5.add(getJCheckBox_FX_CIRCUIT_2());
		rbg5.add(getJCheckBox_FX_CIRCUIT_3());
		*/
		
	}
	
	public void paintComponent(Graphics g) {
	/*     Toolkit kit = Toolkit.getDefaultToolkit();
	     Image icone = kit.getImage("images//EMX.png" );
	     g.drawImage(icone ,0, 0, null); */
	}
	
	private void refreshLedFXSelected()
	{
		final ControlValue cv = pm.getPresetByName("FX SELECT");
		float value = cv.getValue();
		int index = (int) (1*value/((float)MaxValue/3.0));
	//	System.err.println("index + "+index);
		setLedFXSelect(index);
				
	}
	private void refreshLedMODTYPE()
	{
		final ControlValue cv = pm.getPresetByName("MOD TYPE");
				float value = cv.getValue();
				
				int index = 0;
				/**T1-4 : 00~0F : Saw
		         10~1F : Squ
		         20~2F : Tri
		         30~3F : S&H
		         40~7F : EG*/

				if ((value>=0x00) && (value<0x0F)) index=0;
				if ((value>=0x10) && (value<0x1F)) index=1;
				if ((value>=0x20) && (value<0x2F)) index=2;
				if ((value>=0x30) && (value<0x3F)) index=3;
				if ((value>=0x40) && (value<0x7F)) index=4;
				
				setLedFXModType(index);
				
	}
	
	private void refreshLedFILTERTYPE()
	{
		final ControlValue cv = pm.getPresetByName("FILTER TYPE");
			float value = cv.getValue();
				
				int index = 0;

			//	System.err.println(" filter type "+value);
				index=(int) (value/32);
				/*T2-2 : 
				 00~1F : LPF.
		         20~3F : HPF.
		         40~5F : BPF.
		         60~7F : BPF++.
		        */
/*
				if ((value>=0x00) && (value<0x0F)) index=value/32;
				if ((value>=0x10) && (value<0x1F)) index=1;
				if ((value>=0x20) && (value<0x2F)) index=2;
				if ((value>=0x30) && (value<0x3F)) index=3;
			*/	
			//	System.err.println(" index type "+index);
				setLedFXFilteType(index);
				
	}
	
	private void refreshLedMODDEST()
	{
		final ControlValue cv = pm.getPresetByName("MOD DEST");
		float value = cv.getValue();
		
		int index = 0;
		/*T2-2 : 00~0F : PITCH
         10~1F : OSC EDIT1
         20~2F : OSC EDIT2
         30~3F : CUTOFF
         40~4F : AMP
         50~7F : PAN*/

		if ((value>=0x00) && (value<0x0F)) index=0;
		if ((value>=0x10) && (value<0x1F)) index=1;
		if ((value>=0x20) && (value<0x2F)) index=2;
		if ((value>=0x30) && (value<0x3F)) index=3;
		if ((value>=0x40) && (value<0x4F)) index=4;
		if ((value>=0x50) && (value<0x7F)) index=5;
		
		setLedFXModDest(index);
				
	}
	
	private void refreshLedFXCHAIN()
	{
		final ControlValue cv = pm.getPresetByName("FX CHAIN");
			float value = cv.getValue();
				
				int index = 0;

				/*T2-5 :
				 00~1F : none
		         20~3F : FX1-FX2
		         40~5F : FX2-FX3
		         60~7F : FX1-FX2-FX3*/

				if ((value>=0x00) && (value<0x1F)) index=0;
				if ((value>=0x20) && (value<0x3F)) index=1;
				if ((value>=0x40) && (value<0x5F)) index=2;
				if ((value>=0x60) && (value<0x7F)) index=3;
				
				setLedFXChain(index);
				
	}
	
	
	
	public void myEventOccurred(MyChangedEvent mychangedevent) {
		MidiMessage mm = (MidiMessage)mychangedevent.getSource();
		ShortMessage message = ((ShortMessage) mm);
		
		boolean found = false;
		for (int i = 0; i < pm.getEvt().getOutputChannels().length; i++) {
			int c = pm.getEvt().getOutputChannels()[i];
			if (message.getChannel()==c)
				{found=true;break;}
		}
		if (found==false)
			return;
		
		int cc = message.getData1();
		int value = message.getData2();
		System.err.println("Panel recu "+cc+"# == "+value);
		
		System.err.println("K");
		WRoundSlider ws = getSlider(cc);
		if (ws!=null)
		{
			System.err.println("1");
		ws.setValue(value);
		if (cc==70)
			refreshLCDTypeWave();
		}
		else
		{
			System.err.println("2");
			JToggleButton button = getBouton(cc);
			if (button==null) 
				{
				System.err.println("Cannot found control for remote CC ="+cc);
				refreshLedFILTERTYPE();
				refreshLedFXCHAIN();
				refreshLedFXSelected();
				refreshLedMODDEST();
				refreshLedMODTYPE();
				}
			if (value>=64)
				button.setSelected(true);
			else
				button.setSelected(false);
			System.err.println("Sux");
			
			
			// Puant ca ;)
			if (jToggleButton_FX_SEND.isSelected()==true)
				jToggleButton_FX_SEND.setIcon(new ImageIcon("images/boutons/BT_FX_SEND_RED.png"));
			else
				jToggleButton_FX_SEND.setIcon(new ImageIcon("images/boutons/BT_FX_SEND.png"));
			
			if (jToggleButton_BPM_SYNC.isSelected()==true)
				jToggleButton_BPM_SYNC.setIcon(new ImageIcon("images/boutons/BT_MOD_BPM_SYNC_RED.png"));
			else
				jToggleButton_BPM_SYNC.setIcon(new ImageIcon("images/boutons/BT_MOD_BPM_SYNC.png"));
		}
	
	}

	
}  //  @jve:decl-index=0:visual-constraint="8,9"
