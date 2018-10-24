package org.warnotte.elecribulator.GUI;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.warnotte.elecribulator.Multi.Evt_Event;
import org.warnotte.elecribulator.Multi.Evt_Listener;
import org.warnotte.elecribulator.Multi.Thread_Arpegiateur;
import org.warnotte.elecribulator.Multi.evt;
import org.warnotte.elecribulator.Multi.evt.RecordMode;

import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;

public class GUI_Arps extends JPanel implements Evt_Listener {

	private static final long serialVersionUID = 1L;
	evt evt1 = null;
	private GUI_Arp jPanel[] = null;
	private JPanel jPanel1 = null;  //  @jve:decl-index=0:visual-constraint="169,6"
	private JComboBox jComboBox_RECORDMODE = null;
	private JLabel jLabel_RECORDMODE = null;
	
	public GUI_Arps() {
		super();
		initialize();
	}
	/**
	 * This is the default constructor
	 * @param evt1 
	 */
	public GUI_Arps(evt evt1) {
		super();
		this.evt1=evt1;
		evt1.addXXXListener(this);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(getJPanel1());
		
		int cpt = evt1.getThr_arpegiateurs().size();
		jPanel = new GUI_Arp[cpt];
		for (int i = 0; i <cpt; i++) {
			Thread_Arpegiateur ta = evt1.getThr_arpegiateurs().get(i);
			this.add(getJPanel(ta,i));
		}
		
		
		
		
		
	}
	/**
	 * This method initializes jPanel	
	 * @param ta 
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private GUI_Arp getJPanel(Thread_Arpegiateur ta,int i) {
		if (jPanel[i] == null) {
			jPanel[i]= new GUI_Arp(ta, evt1,i);
			
		}
		return jPanel[i];
	}

	//@Override
	public void somethingNeedRefresh(final Evt_Event e) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (e.message==e.message.RECORDING_ARPEGIATOR_FINISHED)
				{
					for (int i = 0; i < jPanel.length; i++) {
						jPanel[i].stoplearn();
					}
					
				}
			}
		});
		
	}
	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.gridy = 0;
			jLabel_RECORDMODE = new JLabel();
			jLabel_RECORDMODE.setText("Record Mode");
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridx = 1;
			gridBagConstraints.weightx = 1.0;
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GridBagLayout());
			jPanel1.add(getJComboBox_RECORDMODE(), gridBagConstraints);
			jPanel1.add(jLabel_RECORDMODE, gridBagConstraints1);
		}
		return jPanel1;
	}
	/**
	 * This method initializes jComboBox_RECORDMODE	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox_RECORDMODE() {
		if (jComboBox_RECORDMODE == null) {
			jComboBox_RECORDMODE = new JComboBox();
			jComboBox_RECORDMODE.setToolTipText("Change the mode of recording ...");
			
			for (int i = 0; i < evt.RecordMode.values().length;i++)
			{
				jComboBox_RECORDMODE.insertItemAt(evt.RecordMode.values()[i], 0);
			}
			jComboBox_RECORDMODE.setSelectedItem(evt1.getRecordMode());
			jComboBox_RECORDMODE.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					evt1.setRecordMode((RecordMode) jComboBox_RECORDMODE.getSelectedObjects()[0]);
				}
			});
			
			
		}
		return jComboBox_RECORDMODE;
	}

	
}
