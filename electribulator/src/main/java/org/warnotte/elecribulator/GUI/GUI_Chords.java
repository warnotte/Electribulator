package org.warnotte.elecribulator.GUI;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.warnotte.elecribulator.Multi.evt;

import java.awt.GridLayout;

public class GUI_Chords extends JPanel {

	private static final long serialVersionUID = 1L;
	evt evt1 = null;
	private JPanel jPanel[] = null;

	/**
	 * This is the default constructor
	 */
	public GUI_Chords(	evt evt1) {
		super();
		this.evt1=evt1;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setSize(300, 200);
		jPanel=  new JPanel[evt1.getPolyGameNoteValue().length];
		for (int i = 0; i < evt1.getPolyGameNoteValue().length; i++) {
			this.add(getJPanel(i));
		}
		
		
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel(int i) {
		if (jPanel[i] == null) {
			jPanel[i] = new GUI_Chord(evt1, i);
		}
		return jPanel[i];
	}

}
