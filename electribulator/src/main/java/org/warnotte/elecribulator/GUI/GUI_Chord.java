package org.warnotte.elecribulator.GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.warnotte.elecribulator.Multi.evt;

public class GUI_Chord extends JPanel {

	private static final long serialVersionUID = 1L;

	evt evt1 = null;

	private JTextField jTextField_NOTEVALUE = null;

	private JCheckBox jCheckBox_ENABLE = null;
	
	int canal=-1;

	/**
	 * This is the default constructor
	 */
	public GUI_Chord(evt evt1, int canal) {
		super();
		this.canal=canal;
		this.evt1=evt1;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.gridx = 3;
		gridBagConstraints1.ipadx = 0;
		gridBagConstraints1.ipady = 0;
		gridBagConstraints1.fill = GridBagConstraints.BOTH;
		gridBagConstraints1.gridy = 0;
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.ipadx = 0;
		gridBagConstraints.ipady = 0;
		gridBagConstraints.weightx = 2.0;
		gridBagConstraints.gridx = 4;
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(200, 23));
		this.setSize(new Dimension(200, 23));
		this.setMaximumSize(new Dimension(1000, 23));
		this.add(getJTextField_NOTEVALUE(), gridBagConstraints);
		this.add(getJCheckBox_ENABLE(), gridBagConstraints1);
		
	}

	/**
	 * This method initializes jTextField_NOTEVALUE	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField_NOTEVALUE() {
		if (jTextField_NOTEVALUE == null) {
			jTextField_NOTEVALUE = new JTextField();
			jTextField_NOTEVALUE.setText(""+evt1.getPolyGameNoteValue()[canal]);
			jTextField_NOTEVALUE.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int noteval = new Integer(""+jTextField_NOTEVALUE.getText());
					evt1.setPolyGameNoteValue(canal,noteval);
				}
			});
		}
		return jTextField_NOTEVALUE;
	}

	/**
	 * This method initializes jCheckBox_ENABLE	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJCheckBox_ENABLE() {
		if (jCheckBox_ENABLE == null) {
			jCheckBox_ENABLE = new JCheckBox();
			jCheckBox_ENABLE.setText("Voice "+canal);
			jCheckBox_ENABLE.setSelected(evt1.getPolyGameEnable()[canal]);
			jCheckBox_ENABLE.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					evt1.setPolyGameEnable(canal, jCheckBox_ENABLE.isSelected());
				}
			});
			
		}
		return jCheckBox_ENABLE;
	}

}
