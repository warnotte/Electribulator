
package org.warnotte.elecribulator.GUI;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
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
import org.warnotte.elecribulator.Definitions.DrumTypeList;
import org.warnotte.elecribulator.PresetManager.ControlValue;
import org.warnotte.elecribulator.PresetManager.PresetManager_Drum;
import org.warnotte.waxlibswingcomponents.Swing.Component.WaxSlider.WRoundSlider;

public class EMXPanel_DRUM extends EMXPanelBase implements MyEventListener {

	
	private static final long serialVersionUID = 1L;
	//ImageIcon ic = null;
	private WRoundSlider jSlider_PAN = null;
	private WRoundSlider jSlider_LEVEL = null;
	private WRoundSlider jSlider_MOD_SPEED = null;
	private WRoundSlider jSlider_MOD_DEPTH = null;
	private WRoundSlider jSlider_GLIDE = null;
	
	
	PresetManager_Drum pm = null;//new PresetManager_Synth(null);  //  @jve:decl-index=0:
	
	int MaxValue = 0x7F;
	private JRadioButton jCheckBox_MOD_TYPE_1;
	private JRadioButton jCheckBox_MOD_TYPE_2;
	private JRadioButton jCheckBox_MOD_TYPE_3;
	private JRadioButton jCheckBox_MOD_TYPE_4;
	private JRadioButton jCheckBox_MOD_TYPE_5;
	
	private JRadioButton jCheckBox_MOD_DEST_1;
	private JRadioButton jCheckBox_MOD_DEST_5;
	private JRadioButton jCheckBox_MOD_DEST_6;
	
	private WRoundSlider jSlider_EG_TIME;
	
	private WRoundSlider jSlider_WAVE;
	private JLabel jLabel_LCD_LINE_1 = null;
	private JLabel jLabel_LCD_LINE_2 = null;
	
	private JToggleButton jToggleButton_FX_SEND;
	private JRadioButton jCheckBox_FX1 = null;
	private JRadioButton jCheckBox_FX2 = null;
	private JRadioButton jCheckBox_FX3 = null;
	
	Toolkit kit = Toolkit.getDefaultToolkit();
	
	private JToggleButton jToggleButton_BPM_SYNC;
	private JToggleButton jToggleButton_AMP_EG;
	private JToggleButton jToggleButton_ROLL;
	
	
	
	String TargetDrum = "DRUM1";  //  @jve:decl-index=0:
	
	int OFFSETX = -202;
	
	/**
	 * This method initializes jCheckBox_FX1	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JRadioButton getJCheckBox_FX1() {
		if (jCheckBox_FX1 == null) {
			jCheckBox_FX1 = new JRadioButton();
			jCheckBox_FX1.setBounds(new Rectangle(290+OFFSETX, 174, 24, 24));
			jCheckBox_FX1.setOpaque(false);
			
			jCheckBox_FX1.setSelectedIcon(new ImageIcon(getClass().getResource("/images/led/on.png")));
			jCheckBox_FX1.setIcon(new ImageIcon(getClass().getResource("/images/led/off.png")));
			jCheckBox_FX1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final ControlValue cv = pm.getPresetByName(TargetDrum+"_FX_SELECT");
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
			jCheckBox_FX2.setBounds(new Rectangle(290+OFFSETX, 196, 24, 24));
			jCheckBox_FX2.setOpaque(false);
			jCheckBox_FX2.setSelectedIcon(new ImageIcon(getClass().getResource("/images/led/on.png")));
			jCheckBox_FX2.setIcon(new ImageIcon(getClass().getResource("/images/led/off.png")));
			jCheckBox_FX2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final ControlValue cv = pm.getPresetByName(TargetDrum+"_FX_SELECT");
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
			jCheckBox_FX3.setBounds(new Rectangle(290+OFFSETX, 219, 24, 24));
			jCheckBox_FX3.setOpaque(false);
			jCheckBox_FX3.setSelectedIcon(new ImageIcon(getClass().getResource("/images/led/on.png")));
			jCheckBox_FX3.setIcon(new ImageIcon(getClass().getResource("/images/led/off.png")));
			jCheckBox_FX3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final ControlValue cv = pm.getPresetByName(TargetDrum+"_FX_SELECT");
					cv.setValue(127);
					pm.applyPresets(cv); 
				}
			});
		}
		return jCheckBox_FX3;
	}
	
	private JToggleButton getJToggleButton_FX_SEND() {
		if (jToggleButton_FX_SEND == null) {
			jToggleButton_FX_SEND = new JToggleButton();
			jToggleButton_FX_SEND.setOpaque(false);
			//jToggleButton_FX_SEND.setBounds(new Rectangle(295, 248, 68, 27));
			jToggleButton_FX_SEND.setBounds(new Rectangle(295+OFFSETX, 286, 68, 27));
			jToggleButton_FX_SEND.setMnemonic(KeyEvent.VK_UNDEFINED);
			
			jToggleButton_FX_SEND.setName(TargetDrum+"_FX_SEND");
			
			ControlValue cv = pm.getPresetByName(TargetDrum+"_FX_SEND");
			float value = cv.getValue();
			if (value>63) 
				jToggleButton_FX_SEND.setSelected(true);
			
			jToggleButton_FX_SEND.setPressedIcon(new ImageIcon(getClass().getResource("/images/boutons/BT_FX_SEND_RED.png")));
			jToggleButton_FX_SEND.setIcon(new ImageIcon(getClass().getResource("/images/boutons/BT_FX_SEND.png")));
			jToggleButton_FX_SEND.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					ControlValue cv = pm.getPresetByName(TargetDrum+"_FX_SEND");
					float value = 0;
					if (jToggleButton_FX_SEND.isSelected()==true)
						value = 126;
					else
						value = 0;
						
					cv.setValue(value);
					
				//	pm_synth.applyPresets(); // TODO : un peu lourd a mon avis.
					pm.applyPresets(cv); 
					
					if (jToggleButton_FX_SEND.isSelected()==true)
						jToggleButton_FX_SEND.setIcon(new ImageIcon(getClass().getResource("/images/boutons/BT_FX_SEND_RED.png")));
					else
						jToggleButton_FX_SEND.setIcon(new ImageIcon(getClass().getResource("/images/boutons/BT_FX_SEND.png")));
					
				}
			});
			if (jToggleButton_FX_SEND.isSelected()==true)
				jToggleButton_FX_SEND.setIcon(new ImageIcon(getClass().getResource("/images/boutons/BT_FX_SEND_RED.png")));
			else
				jToggleButton_FX_SEND.setIcon(new ImageIcon(getClass().getResource("/images/boutons/BT_FX_SEND.png")));
			
		}
		return jToggleButton_FX_SEND;
	}
	
	
	private WRoundSlider getJSlider_LEVEL() {
		if (jSlider_LEVEL == null) {
			final ControlValue cv = pm.getPresetByName(TargetDrum+"_LEVEL");
			jSlider_LEVEL = createSlider(TargetDrum+"_LEVEL", cv.getValue()); 
			jSlider_LEVEL.setBounds(new Rectangle(333+OFFSETX, 153, 56, 56));
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
			final ControlValue cv = pm.getPresetByName(TargetDrum+"_PAN");
			jSlider_PAN = createSlider(TargetDrum+"_PAN", cv.getValue()); 
			jSlider_PAN.setBounds(new Rectangle(333+OFFSETX, 65, 56, 56));
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
			final ControlValue cv = pm.getPresetByName(TargetDrum+"_MOD_SPEED");
			jSlider_MOD_SPEED = createSlider(TargetDrum+"_MOD_SPEED", cv.getValue()); 
			jSlider_MOD_SPEED.setBounds(new Rectangle(439+OFFSETX, 65, 56, 56));
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
			final ControlValue cv = pm.getPresetByName(TargetDrum+"_MOD_DEPTH");
			jSlider_MOD_DEPTH = createSlider(TargetDrum+"_MOD_DEPTH", cv.getValue()); 
			jSlider_MOD_DEPTH.setBounds(new Rectangle(546+OFFSETX, 65, 56, 56));
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
	
	
	private WRoundSlider getJSlider_EG_TIME() {
		if (jSlider_EG_TIME == null) {
			final ControlValue cv = pm.getPresetByName(TargetDrum+"_EG_TIME");
			jSlider_EG_TIME = createSlider("EG TIME", cv.getValue()); 
			jSlider_EG_TIME.setBounds(new Rectangle(226+OFFSETX, 153, 56, 56));
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
	
	private WRoundSlider getJSlider_GLIDE() {
		if (jSlider_GLIDE == null) {
			final ControlValue cv = pm.getPresetByName(TargetDrum+"_PITCH");
			jSlider_GLIDE = createSlider("GLIDE", cv.getValue()); 
			jSlider_GLIDE.setToolTipText("Evite de toucher � ca a mon avis");
			jSlider_GLIDE.setBounds(new Rectangle(226+OFFSETX, 65, 56, 56));
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
	
	
	

	
	private WRoundSlider getJSlider_WAVE() {
		if (jSlider_WAVE == null) {
			final ControlValue cv = pm.getPresetByName(TargetDrum+"_WAVE");
			jSlider_WAVE = createSlider(TargetDrum+"_WAVE", cv.getValue(), 0, 207); 
			jSlider_WAVE.setBounds(new Rectangle(300, 405, 90, 90));
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
		
		final ControlValue cv = pm.getPresetByName(TargetDrum+"_WAVE");
		int wave_value = (int) (cv.getValue());
	
		
		// TODO : Creer des tableau de tout les type de wave pour tout les type de synthese.
		
	//	System.err.println("wave_value="+wave_value);
		
		String nom = DrumTypeList.get(wave_value);
		
		
		
		setLCDText(""+wave_value,""+nom);
	}

	protected void setLCDText(String string, String string2) {
		jLabel_LCD_LINE_1.setText(string);
		jLabel_LCD_LINE_1.repaint();
		jLabel_LCD_LINE_2.setText(string2);
		jLabel_LCD_LINE_2.repaint();
	
		
		
		
	}

	private JToggleButton getJToggleButton_BPM_SYNC() {
		if (jToggleButton_BPM_SYNC == null) {
			jToggleButton_BPM_SYNC = new JToggleButton();
			jToggleButton_BPM_SYNC.setOpaque(false);
			//jToggleButton_FX_SEND.setBounds(new Rectangle(295, 248, 68, 27));
			jToggleButton_BPM_SYNC.setBounds(new Rectangle(295+211+OFFSETX, 286, 68, 27));
			jToggleButton_BPM_SYNC.setMnemonic(KeyEvent.VK_UNDEFINED);
			
			jToggleButton_BPM_SYNC.setName(TargetDrum+"_MOD_BPM_SYNC");
			ControlValue cv = pm.getPresetByName(TargetDrum+"_MOD_BPM_SYNC");	
			float value = cv.getValue();
			if (value>63) 
				jToggleButton_BPM_SYNC.setSelected(true);
			
			jToggleButton_BPM_SYNC.setPressedIcon(new ImageIcon(getClass().getResource("/images/boutons/BT_MOD_BPM_SYNC_RED.png")));
			jToggleButton_BPM_SYNC.setIcon(new ImageIcon(getClass().getResource("/images/boutons/BT_MOD_BPM_SYNC.png")));
			jToggleButton_BPM_SYNC.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					ControlValue cv = pm.getPresetByName(TargetDrum+"_MOD_BPM_SYNC");
					float value = 0;
					if (jToggleButton_BPM_SYNC.isSelected()==true)
						value = 126;
					else
						value = 0;
						
					cv.setValue(value);
					
				//	pm_synth.applyPresets(); // TODO : un peu lourd a mon avis.
					pm.applyPresets(cv); 
					
					if (jToggleButton_BPM_SYNC.isSelected()==true)
						jToggleButton_BPM_SYNC.setIcon(new ImageIcon(getClass().getResource("/images/boutons/BT_MOD_BPM_SYNC_RED.png")));
					else
						jToggleButton_BPM_SYNC.setIcon(new ImageIcon(getClass().getResource("/images/boutons/BT_MOD_BPM_SYNC.png")));
					
				}
			});
			if (jToggleButton_BPM_SYNC.isSelected()==true)
				jToggleButton_BPM_SYNC.setIcon(new ImageIcon(getClass().getResource("/images/boutons/BT_MOD_BPM_SYNC_RED.png")));
			else
				jToggleButton_BPM_SYNC.setIcon(new ImageIcon(getClass().getResource("/images/boutons/BT_MOD_BPM_SYNC.png")));
			
		}
		return jToggleButton_BPM_SYNC;
	}
	
	
	private JToggleButton getJToggleButton_AMP_EG() {
		if (jToggleButton_AMP_EG == null) {
			jToggleButton_AMP_EG = new JToggleButton();
		//	jToggleButton_AMP_EG.setOpaque(false);
			//jToggleButton_AMP_EG.setBounds(new Rectangle(295, 248, 68, 27));
			jToggleButton_AMP_EG.setBounds(new Rectangle(215+OFFSETX, 286-36, 68, 27));
			jToggleButton_AMP_EG.setMnemonic(KeyEvent.VK_UNDEFINED);
			jToggleButton_AMP_EG.setText("AMP EG");
			jToggleButton_AMP_EG.setName(TargetDrum+"_AMP_EG");
			ControlValue cv = pm.getPresetByName(TargetDrum+"_AMP_EG");	
			float value = cv.getValue();
			if (value>63) 
				jToggleButton_AMP_EG.setSelected(true);
			
		//	jToggleButton_AMP_EG.setPressedIcon(new ImageIcon(getClass().getResource("/images/boutons/BT_MOD_BPM_SYNC_RED.png")));
		//	jToggleButton_AMP_EG.setIcon(new ImageIcon(getClass().getResource("/images/boutons/BT_MOD_BPM_SYNC.png")));
			jToggleButton_AMP_EG.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					ControlValue cv = pm.getPresetByName(TargetDrum+"_AMP_EG");
					float value = 0;
					if (jToggleButton_AMP_EG.isSelected()==true)
						value = 126;
					else
						value = 0;
						
					cv.setValue(value);
					
				//	pm_synth.applyPresets(); // TODO : un peu lourd a mon avis.
					pm.applyPresets(cv); 
					
			//		if (jToggleButton_AMP_EG.isSelected()==true)
			//			jToggleButton_AMP_EG.setIcon(new ImageIcon(getClass().getResource("/images/boutons/BT_MOD_BPM_SYNC_RED.png")));
			//		else
			//			jToggleButton_AMP_EG.setIcon(new ImageIcon(getClass().getResource("/images/boutons/BT_MOD_BPM_SYNC.png")));
					
				}
			});
		//	if (jToggleButton_AMP_EG.isSelected()==true)
		//		jToggleButton_AMP_EG.setIcon(new ImageIcon(getClass().getResource("/images/boutons/BT_MOD_BPM_SYNC_RED.png")));
		//	else
		//		jToggleButton_AMP_EG.setIcon(new ImageIcon(getClass().getResource("/images/boutons/BT_MOD_BPM_SYNC.png")));
			
		}
		return jToggleButton_AMP_EG;
	}
	
	private JToggleButton getJToggleButton_ROLL() {
		if (jToggleButton_ROLL == null) {
			jToggleButton_ROLL = new JToggleButton();
			jToggleButton_ROLL.setText("ROLL");
		//	jToggleButton_ROLL.setOpaque(false);
			//jToggleButton_FX_SEND.setBounds(new Rectangle(295, 248, 68, 27));
			jToggleButton_ROLL.setBounds(new Rectangle(211+OFFSETX, 286, 68, 27));
			jToggleButton_ROLL.setMnemonic(KeyEvent.VK_UNDEFINED);
			
			jToggleButton_ROLL.setName(TargetDrum+"_ROLL");
			ControlValue cv = pm.getPresetByName(TargetDrum+"_ROLL");	
			float value = cv.getValue();
			if (value>63) 
				jToggleButton_ROLL.setSelected(true);
			
		//	jToggleButton_ROLL.setPressedIcon(new ImageIcon(getClass().getResource("/images/boutons/BT_MOD_BPM_SYNC_RED.png")));
		//	jToggleButton_ROLL.setIcon(new ImageIcon(getClass().getResource("/images/boutons/BT_MOD_BPM_SYNC.png")));
			jToggleButton_ROLL.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					ControlValue cv = pm.getPresetByName(TargetDrum+"_ROLL");
					float value = 0;
					if (jToggleButton_ROLL.isSelected()==true)
						value = 126;
					else
						value = 0;
						
					cv.setValue(value);
					
				//	pm_synth.applyPresets(); // TODO : un peu lourd a mon avis.
					pm.applyPresets(cv); 
					
			//		if (jToggleButton_ROLL.isSelected()==true)
			//			jToggleButton_ROLL.setIcon(new ImageIcon(getClass().getResource("/images/boutons/BT_MOD_BPM_SYNC_RED.png")));
			//		else
			//			jToggleButton_ROLL.setIcon(new ImageIcon(getClass().getResource("/images/boutons/BT_MOD_BPM_SYNC.png")));
					
				}
			});
		//	if (jToggleButton_ROLL.isSelected()==true)
		//		jToggleButton_ROLL.setIcon(new ImageIcon(getClass().getResource("/images/boutons/BT_MOD_BPM_SYNC_RED.png")));
		//	else
		//		jToggleButton_ROLL.setIcon(new ImageIcon(getClass().getResource("/images/boutons/BT_MOD_BPM_SYNC.png")));
			
		}
		return jToggleButton_ROLL;
	}

	
	
	
	/*private JRadioButton getJCheckBox_FX_CIRCUIT_1() {
		if (jCheckBox_FX_CHAIN_1 == null) {
			jCheckBox_FX_CHAIN_1 = new JRadioButton();
			jCheckBox_FX_CHAIN_1.setBounds(new Rectangle(190+OFFSETX, 220, 24, 24));
			jCheckBox_FX_CHAIN_1.setOpaque(false);
			jCheckBox_FX_CHAIN_1.setSelectedIcon(new ImageIcon(getClass().getResource("/images/led/on.png")));
			jCheckBox_FX_CHAIN_1.setIcon(new ImageIcon(getClass().getResource("/images/led/off.png")));
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
			jCheckBox_MOD_TYPE_1.setSelectedIcon(new ImageIcon(getClass().getResource("/images/led/on.png")));
			jCheckBox_MOD_TYPE_1.setBounds(new Rectangle(460+OFFSETX,129, 24, 24));
			jCheckBox_MOD_TYPE_1.setIcon(new ImageIcon(getClass().getResource("/images/led/off.png")));
			jCheckBox_MOD_TYPE_1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
			
					
					final ControlValue cv = pm.getPresetByName(TargetDrum+"_MOD_TYPE");
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
			jCheckBox_MOD_TYPE_2.setSelectedIcon(new ImageIcon(getClass().getResource("/images/led/on.png")));
			jCheckBox_MOD_TYPE_2.setBounds(new Rectangle(460+OFFSETX, 151, 24, 24));
			jCheckBox_MOD_TYPE_2.setIcon(new ImageIcon(getClass().getResource("/images/led/off.png")));
			jCheckBox_MOD_TYPE_2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					final ControlValue cv = pm.getPresetByName(TargetDrum+"_MOD_TYPE");
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
			jCheckBox_MOD_TYPE_3.setSelectedIcon(new ImageIcon(getClass().getResource("/images/led/on.png")));
			jCheckBox_MOD_TYPE_3.setBounds(new Rectangle(460+OFFSETX, 174, 24, 24));
			jCheckBox_MOD_TYPE_3.setIcon(new ImageIcon(getClass().getResource("/images/led/off.png")));
			jCheckBox_MOD_TYPE_3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final ControlValue cv = pm.getPresetByName(TargetDrum+"_MOD_TYPE");
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
			jCheckBox_MOD_TYPE_4.setSelectedIcon(new ImageIcon(getClass().getResource("/images/led/on.png")));
			jCheckBox_MOD_TYPE_4.setBounds(new Rectangle(460+OFFSETX, 196, 24, 24));
			jCheckBox_MOD_TYPE_4.setIcon(new ImageIcon(getClass().getResource("/images/led/off.png")));
			jCheckBox_MOD_TYPE_4.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final ControlValue cv = pm.getPresetByName(TargetDrum+"_MOD_TYPE");
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
			jCheckBox_MOD_TYPE_5.setSelectedIcon(new ImageIcon(getClass().getResource("/images/led/on.png")));
			jCheckBox_MOD_TYPE_5.setBounds(new Rectangle(460+OFFSETX, 219, 24, 24));
			jCheckBox_MOD_TYPE_5.setIcon(new ImageIcon(getClass().getResource("/images/led/off.png")));
			jCheckBox_MOD_TYPE_5.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final ControlValue cv = pm.getPresetByName(TargetDrum+"_MOD_TYPE");
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
			jCheckBox_MOD_DEST_1.setSelectedIcon(new ImageIcon(getClass().getResource("/images/led/on.png")));
			jCheckBox_MOD_DEST_1.setBounds(new Rectangle(543+OFFSETX, 129, 24, 24));
			jCheckBox_MOD_DEST_1.setIcon(new ImageIcon(getClass().getResource("/images/led/off.png")));
			jCheckBox_MOD_DEST_1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final ControlValue cv = pm.getPresetByName(TargetDrum+"_MOD_DESTINATION");
					cv.setValue(0x05);
					pm.applyPresets(cv); 
				}
			});
		}
		return jCheckBox_MOD_DEST_1;
	}
	private JRadioButton getJCheckBox_MOD_DEST_5() {
		if (jCheckBox_MOD_DEST_5 == null) {
			jCheckBox_MOD_DEST_5 = new JRadioButton();
			jCheckBox_MOD_DEST_5.setOpaque(false);
			jCheckBox_MOD_DEST_5.setSelectedIcon(new ImageIcon(getClass().getResource("/images/led/on.png")));
			jCheckBox_MOD_DEST_5.setBounds(new Rectangle(544+OFFSETX, 220, 24, 24));
			jCheckBox_MOD_DEST_5.setIcon(new ImageIcon(getClass().getResource("/images/led/off.png")));
			jCheckBox_MOD_DEST_5.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final ControlValue cv = pm.getPresetByName(TargetDrum+"_MOD_DESTINATION");
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
			jCheckBox_MOD_DEST_6.setSelectedIcon(new ImageIcon(getClass().getResource("/images/led/on.png")));
			jCheckBox_MOD_DEST_6.setBounds(new Rectangle(543+OFFSETX, 219+24, 24, 24));
			jCheckBox_MOD_DEST_6.setIcon(new ImageIcon(getClass().getResource("/images/led/off.png")));
			jCheckBox_MOD_DEST_6.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final ControlValue cv = pm.getPresetByName(TargetDrum+"_MOD_DESTINATION");
					cv.setValue(0x60);
					pm.applyPresets(cv); 
				}
			});
		}
		return jCheckBox_MOD_DEST_6;
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
	
	private void setLedFXModDest(int index)
	{
		jCheckBox_MOD_DEST_1.setSelected(false);
		jCheckBox_MOD_DEST_5.setSelected(false);
		jCheckBox_MOD_DEST_6.setSelected(false);
		
		if (index==0) jCheckBox_MOD_DEST_1.setSelected(true);
		if (index==4) jCheckBox_MOD_DEST_5.setSelected(true);
		if (index==5) jCheckBox_MOD_DEST_6.setSelected(true);
			
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
	public EMXPanel_DRUM(PresetManager_Drum pm, String TargetDrum) {
		super(pm,true);
		this.TargetDrum=TargetDrum;
		this.pm=pm;
		//ic = new ImageIcon("images\\EMX.png");
		try {
			initialize();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		refreshLedMODTYPE();
		refreshLedMODDEST();
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
		loadBackGround(getClass().getResource("/images/fond/EMXDrum_Wax.png"));
		
	//	icone = kit.getImage("images//EMX.png" );
		
		
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
		jLabel_LCD_LINE_1.setBounds(new Rectangle(75, 412, 210, 24));
		//jLabel_LCD_LINE_2.setFont(new Font("Arcade", Font.PLAIN, 32));
		jLabel_LCD_LINE_2.setBounds(new Rectangle(76, 438, 210, 22));
		this.setLayout(null);
		this.setPreferredSize(new Dimension(842, 800));
		this.add(getJToggleButton_BPM_SYNC(), null);
		
		this.add(getJToggleButton_ROLL(), null);
		this.add(getJToggleButton_AMP_EG(), null);
		
		
		
		
		this.add(getJSlider_GLIDE());
		
		ButtonGroup rbg = new ButtonGroup();
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
		
		
		
	
		
		this.add(getJToggleButton_FX_SEND(), null);
		this.add(getJCheckBox_FX1(), null);
		this.add(getJCheckBox_FX2(), null);
		this.add(getJCheckBox_FX3(), null);
		ButtonGroup rbg3 = new ButtonGroup();
		rbg3.add(getJCheckBox_FX1());
		rbg3.add(getJCheckBox_FX2());
		rbg3.add(getJCheckBox_FX3());
		
		
		this.add(getJCheckBox_MOD_DEST_1(), null);
		this.add(getJCheckBox_MOD_DEST_5(), null);
		this.add(getJCheckBox_MOD_DEST_6(), null);
		this.add(getJSlider_PAN(), null);
		this.add(getJSlider_LEVEL(), null);
		this.add(getJSlider_MOD_SPEED(), null);
		this.add(getJSlider_MOD_DEPTH(), null);
		this.add(getJSlider_EG_TIME(), null);
		
		ButtonGroup rbg4 = new ButtonGroup();
		rbg4.add(getJCheckBox_MOD_DEST_1());
		rbg4.add(getJCheckBox_MOD_DEST_5());
		rbg4.add(getJCheckBox_MOD_DEST_6());
		
		
		this.add(jLabel_LCD_LINE_1, null);
		this.add(jLabel_LCD_LINE_2, null);
		this.add(getJSlider_WAVE(), null);
		
		
		
	/*	ButtonGroup rbg5 = new ButtonGroup();
		rbg5.add(getJCheckBox_FX_CIRCUIT_2());
		rbg5.add(getJCheckBox_FX_CIRCUIT_3());
		*/
		
	}
	
	
	
	
	private void refreshLedMODTYPE()
	{
		final ControlValue cv = pm.getPresetByName(TargetDrum+"_MOD_TYPE");
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
	
	
	
	private void refreshLedMODDEST()
	{
		final ControlValue cv = pm.getPresetByName(TargetDrum+"_MOD_DESTINATION");
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
	
	
	

	
	public void myEventOccurred(MyChangedEvent mychangedevent) {
		MidiMessage mm = (MidiMessage)mychangedevent.getSource();
		
		ShortMessage message = ((ShortMessage) mm);
		if (message.getChannel()!=pm.getEvt().getDrumChannel())
			return;
		int cc = message.getData1();
		int value = message.getData2();
		System.err.println("Panel recu "+cc+"# == "+value);
		System.err.println("1");
		
		
		
		WRoundSlider ws = getSlider(cc);
		System.err.println("2");
		
		// TODO : Check ???!
		if (ws!=null)
		{
		ws.setValue(value);
		if (cc==70)
			refreshLCDTypeWave();
		}
		else
		{
			JToggleButton button = getBouton(cc);
			if (value>=64)
				button.setSelected(true);
			else
				button.setSelected(false);
			System.err.println("Sux");
			
			if (jToggleButton_BPM_SYNC.isSelected()==true)
				jToggleButton_BPM_SYNC.setIcon(new ImageIcon(getClass().getResource("/images/boutons/BT_MOD_BPM_SYNC_RED.png")));
			else
				jToggleButton_BPM_SYNC.setIcon(new ImageIcon(getClass().getResource("/images/boutons/BT_MOD_BPM_SYNC.png")));
		}
	
	}
	
	@Override
	public void refreshComponents()
	{
		super.refreshComponents();
		refreshLedMODDEST();
		refreshLedMODTYPE();
		refreshLCDTypeWave();
		
		if (jToggleButton_BPM_SYNC.isSelected()==true)
			jToggleButton_BPM_SYNC.setIcon(new ImageIcon(getClass().getResource("/images/boutons/BT_MOD_BPM_SYNC_RED.png")));
		else
			jToggleButton_BPM_SYNC.setIcon(new ImageIcon(getClass().getResource("/images/boutons/BT_MOD_BPM_SYNC.png")));
		if (jToggleButton_FX_SEND.isSelected()==true)
			jToggleButton_FX_SEND.setIcon(new ImageIcon(getClass().getResource("/images/boutons/BT_FX_SEND_RED.png")));
		else
			jToggleButton_FX_SEND.setIcon(new ImageIcon(getClass().getResource("/images/boutons/BT_FX_SEND.png")));
	}

	
}  //  @jve:decl-index=0:visual-constraint="8,9"
