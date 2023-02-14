package org.warnotte.elecribulator.GUI;

import java.awt.BorderLayout;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.ShortMessage;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import org.warnotte.elecribulator.PresetManager.PresetManager;
import org.warnotte.elecribulator.PresetManager.PresetManager_Drum;
import org.warnotte.elecribulator.PresetManager.PresetManager_Synth;

import io.github.warnotte.waxlib3.OBJ2GUI.Events.MyChangedEvent;
import io.github.warnotte.waxlib3.OBJ2GUI.Events.MyEventListener;

public class GUI_PRESETMANAGER extends JPanel implements MyEventListener {
	
	private static final long serialVersionUID = 1L;
	PresetManager pm =null;
//	PresetManager_Drum pm_drum =null;
	
	//PresetManager_Synth pm_drum =null;
	//PresetControlValueTableModel pmvm = null;
	private JPanel jPanel = null;
	private JScrollPane jScrollPane = null;
	//private JTable jTable = null;
	private JPanel jPanel2 = null;
	private JPanel jPanel3 = null;
	JButton jButton_SEND_PRESET =null;
	private JCheckBox jCheckBox_BypassSend = null;
	JTabbedPane tpane = null;
	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.X_AXIS));
			jPanel.add(getJButton_SEND_PRESET(), null);
			jPanel.add(getJCheckBox_BypassSend(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jButton_SEND_PRESET	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton_SEND_PRESET() {
		if (jButton_SEND_PRESET == null) {
			jButton_SEND_PRESET = new JButton();
			jButton_SEND_PRESET.setText("Send preset");
			jButton_SEND_PRESET.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					pm.applyPresets();
					//pm_drum.applyPresets();
				}
			});
		}
		return jButton_SEND_PRESET;
	}
	

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	/*private JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable();
			setPresetManager(pm_synth);
		}
		return jTable;
	}*/

	public void setPresetManager(PresetManager pm)
	{
	//	pmvm = new PresetControlValueTableModel(pm_synth);
	//	jTable.setModel(pmvm);
		//pmvm.addTableModelListener(this);
	//	jTable.validate();
		
		refresh_EMXPanel(pm);
		refresh();
	}

	private void refresh_EMXPanel(PresetManager pm) {
		if (jPanel2!=null)
		{
			if (pm instanceof PresetManager_Synth)
			{
				EMXPanel p = (EMXPanel)jPanel2.getComponent(0);
				p.refreshComponents();
			}
			if (pm instanceof PresetManager_Drum)
			{
			//	JTabbedPane tpane = new JTabbedPane();
				for (int i = 0; i < tpane.getTabCount(); i++) {
					
					EMXPanel_DRUM p = (EMXPanel_DRUM) tpane.getComponent(i);
					p.refreshComponents();
				}
				
			}
			/*jPanel2.removeAll();
			if (pm instanceof PresetManager_Synth)
				jPanel2.add(new EMXPanel((PresetManager_Synth)pm));
			if (pm instanceof PresetManager_Drum)
			{
				JTabbedPane tpane = new JTabbedPane();
				for (int i = 1; i < 10; i++) {
					tpane.add("Drum_"+i, new EMXPanel_DRUM((PresetManager_Drum)pm, "DRUM"+i));
				}
				jPanel2.add(tpane);
				
			}*/
		
		
		}
	/*	if (jPanel3!=null)
		{
		jPanel3.removeAll();
		jPanel3.add(new EMXPanel_DRUM(pm_drum));
		
		}*/
		
		validate();
		doLayout();
		repaint();
	}
	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			if (pm instanceof PresetManager_Synth)
				jPanel2.add(new EMXPanel((PresetManager_Synth)pm));
			if (pm instanceof PresetManager_Drum)
			{
				tpane = new JTabbedPane();
				for (int i = 1; i < 10; i++) {
				//	System.err.println("Creation "+i);
					tpane.add("Drum_"+i, new EMXPanel_DRUM((PresetManager_Drum)pm, "DRUM"+i));
				}
				jPanel2.add(tpane);
				
			}
			
		}
		return jPanel2;
	}
	/*private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.add(new EMXPanel_DRUM(pm_drum));
			
		}
		return jPanel3;
	}*/

	/**
	 * This method initializes jCheckBox_BypassSend	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJCheckBox_BypassSend() {
		if (jCheckBox_BypassSend == null) {
			jCheckBox_BypassSend = new JCheckBox();
			jCheckBox_BypassSend.setText("Bypass Send");
			jCheckBox_BypassSend.setToolTipText("Bypass CC Send when changing a control of electribulator");
			jCheckBox_BypassSend.setSelected(pm.isBypassSend());
			jCheckBox_BypassSend.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					pm.setBypassSend(jCheckBox_BypassSend.isSelected());
				}
			});
			
		}
		return jCheckBox_BypassSend;
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	
	public static void main(String[] args) throws Exception {
		
/*		KnowMidiList kml = new KnowMidiList();
		kml.init_SYNTH();
		
		PresetManager_Synth pm = new PresetManager_Synth(new evt(), kml);
		GUI_PRESETMANAGER panel = new GUI_PRESETMANAGER(pm);
		JFrame frame = new JFrame();
		frame.add(panel);
		frame.setVisible(true);
		frame.setSize(480,480);*/
		
		

	}

	/**
	 * This is the default constructor
	 */
	public GUI_PRESETMANAGER(PresetManager pm) {
		super();
		this.pm=pm;
		//this.pm_drum=pm_drum;
		initialize();
		pm.getEvt().getElec_Reic().addIOEnteredExitedListener(this);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setLayout(new BorderLayout());
		this.add(getJPanel(), BorderLayout.SOUTH);
		this.add(new JScrollPane(getJPanel2()), BorderLayout.CENTER);
	//	this.add(new JScrollPane(getJPanel3()), BorderLayout.NORTH);
		
	}

	public void refresh() {
		refresh_EMXPanel(pm);
		
	}
	
   /* public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel)e.getSource();
        
        pm_synth.applyPresets();
    }*/

	
	public void myEventOccurred(MyChangedEvent mychangedevent) {
		if (mychangedevent==null)
			{System.err.println("Oula");System.exit(-1);}
		MidiMessage mm = (MidiMessage)mychangedevent.getSource();
		
		ShortMessage message = ((ShortMessage) mm);
		if (message.getChannel()!=pm.getEvt().getDrumChannel())
			return;
		int cc = message.getData1();
		int value = message.getData2();
		System.err.println("PM Panel recu "+cc+"# == "+value);
		// 36 38 40 41 43 42 46 49 51 
		if (cc == 36) tpane.setSelectedIndex(0);
		if (cc == 38) tpane.setSelectedIndex(1);
		if (cc == 40) tpane.setSelectedIndex(2);
		if (cc == 41) tpane.setSelectedIndex(3);
		if (cc == 43) tpane.setSelectedIndex(4);
		if (cc == 42) tpane.setSelectedIndex(5);
		if (cc == 46) tpane.setSelectedIndex(6);
		if (cc == 49) tpane.setSelectedIndex(7);
		if (cc == 51) tpane.setSelectedIndex(8);
	}
}
