package org.warnotte.elecribulator.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;

import org.warnotte.elecribulator.GUI.TableModel.ArpMultiTableModel;
import org.warnotte.elecribulator.GUI.TableModel.ArpNotesTableModel;
import org.warnotte.elecribulator.Multi.Thread_Arpegiateur;
import org.warnotte.elecribulator.Multi.Thread_Arpegiateur.Mode;
import org.warnotte.elecribulator.Multi.evt;
import org.warnotte.waxlibswingcomponents.Dialog.DialogDivers;

public class GUI_Arp extends JPanel implements ActionListener {
	
	Thread_Arpegiateur ta = new Thread_Arpegiateur(null);  //  @jve:decl-index=0:
	evt evt1 = null;
	Timer timer=null;
	private static final long serialVersionUID = 1L;

	private JSlider jSlider_GateTime = null;

	private JLabel jLabel_GateLen = null;

	private JTextField jTextField_SleepLen = null;

	private JLabel jLabel_SleepLen = null;

	private JScrollPane jScrollPane = null;

	private JTable jTable = null;
	private JButton jButton_ADD = null;
	private JPanel jPanel = null;
	private JButton jButton_REMOVE = null;
	private JComboBox jComboBox = null;
	private JCheckBox jCheckBox_AlwaysLoop = null;
	private JButton jButton_COPY_SLEEP_LENGTH = null;
	private JButton jButton_COPY_GATE_LENGTH = null;
	private JButton jButton_LEARN = null;
	private boolean learning;
	private final int idarpegiateur;
	private JPanel jPanel_TOP = null;
	private JButton jButton_LOAD = null;
	private JButton jButton_SAVE = null;
	
	int last_selected_note = -1;
	int last_selected_arp_note = -1;
	
	private JCheckBox jCheckBox_Bypass = null;
	private JScrollPane jScrollPane_ARPMULTI = null;
	private JTable jTable_MULTIARP = null;
	private ArpMultiTableModel model_multi;
	private ArpNotesTableModel model_notes = null;
	private JPanel jPanel_ARPMULTI = null;
	private JButton jButton_ADD_ARPMULTI = null;
	private JButton jButton_REMOVE_ARPMULTI = null;
	private JCheckBox jCheckBox_LoopMultiARP = null;
	/**
	 * This is the default constructor
	 */
	public GUI_Arp(Thread_Arpegiateur ta,evt evt1, int idarpegiateur) {
		super();
		this.idarpegiateur=idarpegiateur;
		this.evt1=evt1;
		this.ta=ta;
		initialize();
		
		timer = new Timer(125, this);
		timer.setInitialDelay(2500);
		timer.start(); 
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints32 = new GridBagConstraints();
		gridBagConstraints32.gridx = 1;
		gridBagConstraints32.gridy = 5;
		GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
		gridBagConstraints13.fill = GridBagConstraints.BOTH;
		gridBagConstraints13.gridy = 5;
		gridBagConstraints13.weightx = 1.0;
		gridBagConstraints13.weighty = 1.0;
		gridBagConstraints13.gridwidth = 3;
		gridBagConstraints13.gridx = 2;
		GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
		gridBagConstraints12.gridx = 1;
		gridBagConstraints12.anchor = GridBagConstraints.WEST;
		gridBagConstraints12.gridwidth = 4;
		gridBagConstraints12.gridy = 0;
		GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
		gridBagConstraints22.gridx = 3;
		gridBagConstraints22.gridy = 3;
		GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
		gridBagConstraints11.gridx = 3;
		gridBagConstraints11.gridy = 2;
		GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
		gridBagConstraints41.gridx = 4;
		gridBagConstraints41.fill = GridBagConstraints.BOTH;
		gridBagConstraints41.gridy = 2;
		GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
		gridBagConstraints31.fill = GridBagConstraints.BOTH;
		gridBagConstraints31.gridy = 3;
		gridBagConstraints31.weightx = 1.0;
		gridBagConstraints31.gridx = 4;
		GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
		gridBagConstraints21.gridx = 1;
		gridBagConstraints21.anchor = GridBagConstraints.WEST;
		gridBagConstraints21.fill = GridBagConstraints.BOTH;
		gridBagConstraints21.gridy = 4;
		GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
		gridBagConstraints4.fill = GridBagConstraints.BOTH;
		gridBagConstraints4.gridy = 4;
		gridBagConstraints4.gridwidth = 3;
		gridBagConstraints4.weightx = 1.0;
		gridBagConstraints4.weighty = 1.0;
		gridBagConstraints4.gridx = 2;
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.gridx = 1;
		gridBagConstraints3.fill = GridBagConstraints.BOTH;
		gridBagConstraints3.gridy = 2;
		jLabel_SleepLen = new JLabel();
		jLabel_SleepLen.setText("Sleep Length");
		jLabel_SleepLen.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.fill = GridBagConstraints.BOTH;
		gridBagConstraints2.gridy = 2;
		gridBagConstraints2.weightx = 1.0;
		gridBagConstraints2.gridwidth = 1;
		gridBagConstraints2.gridx = 2;
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.gridx = 1;
		gridBagConstraints1.fill = GridBagConstraints.BOTH;
		gridBagConstraints1.gridy = 3;
		jLabel_GateLen = new JLabel();
		jLabel_GateLen.setText("Gate Length");
		jLabel_GateLen.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.gridx = 2;
		this.setSize(400, 300);
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(500, 300));
		this.setBorder(BorderFactory.createTitledBorder(null, "Arpegiator", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
		this.setMinimumSize(new Dimension(500, 115));
		this.add(getJSlider_GateTime(), gridBagConstraints);
		this.add(jLabel_GateLen, gridBagConstraints1);
		this.add(getJTextField_SleepLen(), gridBagConstraints2);
		this.add(jLabel_SleepLen, gridBagConstraints3);
		this.add(getJScrollPane(), gridBagConstraints4);
		this.add(getJPanel(), gridBagConstraints21);
		this.add(getJComboBox(), gridBagConstraints31);
		this.add(getJCheckBox_AlwaysLoop(), gridBagConstraints41);
		this.add(getJButton_COPY_SLEEP_LENGTH(), gridBagConstraints11);
		this.add(getJButton_COPY_GATE_LENGTH(), gridBagConstraints22);
		this.add(getJPanel_TOP(), gridBagConstraints12);
		this.add(getJScrollPane_ARPMULTI(), gridBagConstraints13);
		this.add(getJPanel_ARPMULTI(), gridBagConstraints32);
	}

	/**
	 * This method initializes jSlider_GateTime	
	 * 	
	 * @return javax.swing.JSlider	
	 */
	private JSlider getJSlider_GateTime() {
		if (jSlider_GateTime == null) {
			jSlider_GateTime = new JSlider();
		/*	jSlider_GateTime.setValue((int) (ta.getGateLen()*100.0f));
			jSlider_GateTime.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					float gateLen = (float)jSlider_GateTime.getValue()/100.0f;
					ta.setGateLen(gateLen);
				}
			});*/
		}
		return jSlider_GateTime;
	}

	/**
	 * This method initializes jTextField_SleepLen	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField_SleepLen() {
		if (jTextField_SleepLen == null) {
			jTextField_SleepLen = new JTextField();
			/*jTextField_SleepLen.setText(""+ta.getSleepLen());
			jTextField_SleepLen.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					long sleepLen = new Long(""+jTextField_SleepLen.getText());
					ta.setSleepLen(sleepLen);
				}
			});*/
		}
		return jTextField_SleepLen;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable() {
		if (jTable == null) {
			model_notes = new ArpNotesTableModel(ta);
			jTable = new JTable(model_notes);
			TableColumn tm = jTable.getColumnModel().getColumn(0);
		    tm.setCellRenderer(new ColorColumnRenderer(Color.black,Color.WHITE));
		    
		    light_selected_note(0);
		    
		}
		
		return jTable;
	}
	
	

	public void refreshBack()
	{
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

		
		boolean activated = false;
		
		if (ta.getStat()==ta.getStat().ON)
			activated=true;
				
		if (activated==true)
			setBackground(Color.GREEN);
		else
			setBackground(Color.LIGHT_GRAY);
		if (learning==true)
			setBackground(Color.RED);
			
		if ((ta.getStat()==ta.getStat().OFF) && (learning==false))
		{
			light_selected_note(0);
			light_selected_arpnote(0);
		}
		else
		{
			int current_note = ta.getLight_selected_note();
			if (learning==true)
				current_note = evt1.getRecordingInputToArpegiatorIndex();
			light_selected_note(current_note);
			
			int current_arpnote = ta.getLight_selected_arpnote();
			//if (learning2==true)//?
			//	current_arpnote = evt1.getRecordingInputToArpegiatorIndex();//?
			light_selected_arpnote(current_arpnote);
		}
		repaint();
			}
		});
	}

	public void actionPerformed(ActionEvent arg0) {
		//System.err.println("Action Performed "+arg0);
		refreshBack();
		
	}

	/**
	 * This method initializes jButton_ADD	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton_ADD() {
		if (jButton_ADD == null) {
			jButton_ADD = new JButton();
			jButton_ADD.setText("ADD");
			jButton_ADD.setIcon(new ImageIcon(getClass().getResource("/images/icons/edit_add.png")));
			jButton_ADD.setHorizontalAlignment(SwingConstants.LEFT);
			jButton_ADD.setToolTipText("Add a step");
			jButton_ADD.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					addNoteAtEnd();
				}
			});
		}
		return jButton_ADD;
	}


	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = 0;
			gridBagConstraints7.fill = GridBagConstraints.BOTH;
			gridBagConstraints7.gridy = 2;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.fill = GridBagConstraints.BOTH;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 0;
			gridBagConstraints5.fill = GridBagConstraints.BOTH;
			gridBagConstraints5.gridy = 1;
			jPanel = new JPanel();
			jPanel.setOpaque(false);
			jPanel.setLayout(new GridBagLayout());
			jPanel.add(getJButton_ADD(), gridBagConstraints6);
			jPanel.add(getJButton_REMOVE(), gridBagConstraints5);
			jPanel.add(getJButton_LEARN(), gridBagConstraints7);
		}
		return jPanel;
	}

	/**
	 * This method initializes jButton_REMOVE	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton_REMOVE() {
		if (jButton_REMOVE == null) {
			jButton_REMOVE = new JButton();
			jButton_REMOVE.setText("REMOVE");
			jButton_REMOVE.setIcon(new ImageIcon(getClass().getResource("/images/icons/edit_remove.png")));
			jButton_REMOVE.setHorizontalAlignment(SwingConstants.LEFT);
			jButton_REMOVE.setToolTipText("Remove a step");
			jButton_REMOVE.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					removeNoteAtEnd();
					light_selected_note(0);
				}

				
			});
		}
		return jButton_REMOVE;
	}
	


	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			jComboBox = new JComboBox();
			for (int i = 0; i < Mode.values().length; i++) {
				jComboBox.insertItemAt(Mode.values()[i], i);
			}
			jComboBox.setSelectedItem(ta.getCmode());
			jComboBox.setToolTipText("Sequence move property_mode");
			jComboBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Mode m = (Mode) jComboBox.getSelectedItem();
					ta.setCmode(m);
				}
			});
		}
		return jComboBox;
	}

	/**
	 * This method initializes jCheckBox_AlwaysLoop	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJCheckBox_AlwaysLoop() {
		if (jCheckBox_AlwaysLoop == null) {
			jCheckBox_AlwaysLoop = new JCheckBox();
			jCheckBox_AlwaysLoop.setText("Loop");
			jCheckBox_AlwaysLoop.setToolTipText("Make the sequence loop until release or just one shot");
			jCheckBox_AlwaysLoop.setSelected(ta.isAlwaysLoop());
			jCheckBox_AlwaysLoop.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ta.setAlwaysLoop(jCheckBox_AlwaysLoop.isSelected());
				}
			});
		}
		return jCheckBox_AlwaysLoop;
	}

	/**
	 * This method initializes jButton_COPY_SLEEP_LENGTH	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton_COPY_SLEEP_LENGTH() {
		if (jButton_COPY_SLEEP_LENGTH == null) {
			jButton_COPY_SLEEP_LENGTH = new JButton();
			jButton_COPY_SLEEP_LENGTH.setText("COPY");
			jButton_COPY_SLEEP_LENGTH.setToolTipText("Copy sleep len to all column");
			jButton_COPY_SLEEP_LENGTH.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String txt = jTextField_SleepLen.getText();
					if (txt!=null)
					{
						long len = new Long(""+txt);
						Copy_SleepLength(len);
					}
				}
			});
		}
		return jButton_COPY_SLEEP_LENGTH;
	}


	/**
	 * This method initializes jButton_COPY_GATE_LENGTH	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton_COPY_GATE_LENGTH() {
		if (jButton_COPY_GATE_LENGTH == null) {
			jButton_COPY_GATE_LENGTH = new JButton();
			jButton_COPY_GATE_LENGTH.setText("COPY");
			jButton_COPY_GATE_LENGTH.setToolTipText("Copy gate len to all column");
			jButton_COPY_GATE_LENGTH.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					float gateLen = jSlider_GateTime.getValue()/100.0f;
					Copy_GateLength(gateLen);
				}
			});
		}
		return jButton_COPY_GATE_LENGTH;
	}

	/**
	 * This method initializes jButton_LEARN	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton_LEARN() {
		if (jButton_LEARN == null) {
			jButton_LEARN = new JButton();
			jButton_LEARN.setText("LEARN");
			jButton_LEARN.setHorizontalAlignment(SwingConstants.LEFT);
			jButton_LEARN.setMnemonic(KeyEvent.VK_UNDEFINED);
			jButton_LEARN.setToolTipText("Begin midi learning to record the notes you type on your keyboard or midi source ...");
			jButton_LEARN.setIcon(new ImageIcon(getClass().getResource("/images/icons/education.png")));
			jButton_LEARN.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					startlearn();
					light_selected_note(0);
				}
			});
		}
		return jButton_LEARN;
	}


	/**
	 * This method initializes jPanel_TOP	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel_TOP()
	{
		if (jPanel_TOP == null)
		{
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.gridx = 3;
			gridBagConstraints14.gridy = 0;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = -1;
			gridBagConstraints8.gridy = -1;
			jPanel_TOP = new JPanel();
			jPanel_TOP.setLayout(new GridBagLayout());
			jPanel_TOP.add(getJButton_LOAD(), new GridBagConstraints());
			jPanel_TOP.add(getJButton_SAVE(), new GridBagConstraints());
			jPanel_TOP.add(getJCheckBox_Bypass(), gridBagConstraints8);
			jPanel_TOP.add(getJCheckBox_LoopMultiARP(), gridBagConstraints14);
		}
		return jPanel_TOP;
	}

	/**
	 * This method initializes jButton_LOAD	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton_LOAD()
	{
		if (jButton_LOAD == null)
		{
			jButton_LOAD = new JButton();
			jButton_LOAD.setText("LOAD");
			jButton_LOAD.setIcon(new ImageIcon(getClass().getResource("/images/icons/folder_open.png")));
			jButton_LOAD.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					load();
				}
			});
		}
		return jButton_LOAD;
	}

	/**
	 * This method initializes jButton_SAVE	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton_SAVE()
	{
		if (jButton_SAVE == null)
		{
			jButton_SAVE = new JButton();
			jButton_SAVE.setText("SAVE");
			jButton_SAVE.setIcon(new ImageIcon(getClass().getResource("/images/icons/filesave.png")));
			jButton_SAVE.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					save();
				}
			});
		}
		return jButton_SAVE;
	}
	
	public void startlearn()
	{
		learning=true;
		ta.Off();
		
		evt1.setRecordingInputToArpegiator(idarpegiateur);
		refreshBack();
	}
	public void stoplearn()
	{
		learning=false;
		refreshBack();
	}


	protected void Copy_SleepLength(long len) {
		ta.Copy_SleepLength(len);
		
	}
	protected void Copy_GateLength(float len) {
		ta.Copy_GateLength(len);
	}
	
	protected void save()
	{
		String f = DialogDivers.SaveDialog(new JFrame(), "xml", "saves"+File.separator+"arp"+File.separator, "Save arpegiator status");
		if (f!=null)
		{
			try
			{
				ta.save(f);
			} 
			catch (IOException e)
			{
				e.printStackTrace();
				DialogDivers.Show_dialog(e, "Error saving arp");
				
			}
		}
	}
	protected void load() 
	{
		try
		{
		File f = DialogDivers.LoadDialog(new JFrame(), "xml", "saves"+File.separator+"arp"+File.separator);
		if (f!=null)
		{
			
				ta.load(f.getAbsolutePath());
				model_notes.fireTableStructureChanged();
			
		}
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			DialogDivers.Show_dialog(e, "Error loading arp");
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			DialogDivers.Show_dialog(e, "Error loading arp");
			
		} catch (Exception e)
		{
			e.printStackTrace();
			DialogDivers.Show_dialog(e, "Error loading arp");
			
		}
	}

	/**
	 * This method initializes jCheckBox_Bypass	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJCheckBox_Bypass() {
		if (jCheckBox_Bypass == null) {
			jCheckBox_Bypass = new JCheckBox();
			jCheckBox_Bypass.setText("Bypass");
			jCheckBox_Bypass.setSelected(ta.isBypass());
			jCheckBox_Bypass.setToolTipText("Send the note received but don't play the sequence, enable this if you want to play a sequence with one finger and a custom played by hand sequence with other finger");
			jCheckBox_Bypass.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ta.setBypass(jCheckBox_Bypass.isSelected());
				}
			});
			
		}
		
		return jCheckBox_Bypass;
	}

	/**
	 * This method initializes jScrollPane_ARPMULTI	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane_ARPMULTI() {
		if (jScrollPane_ARPMULTI == null) {
			jScrollPane_ARPMULTI = new JScrollPane();
			jScrollPane_ARPMULTI.setViewportView(getJTable_MULTIARP());
		}
		return jScrollPane_ARPMULTI;
	}

	/**
	 * This method initializes jTable_MULTIARP	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable_MULTIARP() {
		if (jTable_MULTIARP == null) {
			model_multi = new ArpMultiTableModel(ta);
			jTable_MULTIARP = new JTable(model_multi);
			TableColumn tm = jTable.getColumnModel().getColumn(0);
		    tm.setCellRenderer(new ColorColumnRenderer(Color.black,Color.WHITE));
		    light_selected_arpnote(0);
		}
		return jTable_MULTIARP;
	}

	/**
	 * This method initializes jPanel_ARPMULTI	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel_ARPMULTI() {
		if (jPanel_ARPMULTI == null) {
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.fill = GridBagConstraints.BOTH;
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.gridx = 0;
			gridBagConstraints9.fill = GridBagConstraints.BOTH;
			gridBagConstraints9.gridy = 1;
			jPanel_ARPMULTI = new JPanel();
			jPanel_ARPMULTI.setLayout(new GridBagLayout());
			jPanel_ARPMULTI.add(getJButton_ADD_ARPMULTI(), gridBagConstraints10);
			jPanel_ARPMULTI.add(getJButton_REMOVE_ARPMULTI(), gridBagConstraints9);
		}
		return jPanel_ARPMULTI;
	}

	/**
	 * This method initializes jButton_ADD_ARPMULTI	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton_ADD_ARPMULTI() {
		if (jButton_ADD_ARPMULTI == null) {
			jButton_ADD_ARPMULTI = new JButton();
			jButton_ADD_ARPMULTI.setText("ADD");
			jButton_ADD_ARPMULTI.setIcon(new ImageIcon(getClass().getResource("/images/icons/edit_add.png")));
			jButton_ADD_ARPMULTI.setHorizontalAlignment(SwingConstants.LEFT);
			jButton_ADD_ARPMULTI.setToolTipText("Add a step");
			jButton_ADD_ARPMULTI.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					addARPNoteAtEnd();
				}
			});
			
		}
		return jButton_ADD_ARPMULTI;
	}

	/**
	 * This method initializes jButton_REMOVE_ARPMULTI	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton_REMOVE_ARPMULTI() {
		if (jButton_REMOVE_ARPMULTI == null) {
			jButton_REMOVE_ARPMULTI = new JButton();
			jButton_REMOVE_ARPMULTI.setText("REMOVE");
			jButton_REMOVE_ARPMULTI.setIcon(new ImageIcon(getClass().getResource("/images/icons/edit_remove.png")));
			jButton_REMOVE_ARPMULTI.setHorizontalAlignment(SwingConstants.LEFT);
			jButton_REMOVE_ARPMULTI.setToolTipText("Remove a step");
			jButton_REMOVE_ARPMULTI.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					removeARPNoteAtEnd();
					//light_selected_arp(0);
				}
			});
			
		}
		return jButton_REMOVE_ARPMULTI;
	}
	
	
	private void removeNoteAtEnd() {
		ta.removeLastNote();
		model_notes.fireTableStructureChanged();
	}
	
	protected void addNoteAtEnd() {
		ta.addLastNote();
		model_notes.fireTableStructureChanged();
		light_selected_note(0);
		
	}
	
	private void removeARPNoteAtEnd() {
		ta.removeLastARPNote();
		
		model_multi.fireTableStructureChanged();
	}
	
	protected void addARPNoteAtEnd() {
		ta.addLastARPNote();
		model_multi.fireTableStructureChanged();
		light_selected_arpnote(0);
		
	}
	
	private void light_selected_arpnote(int arp_note_step) {
		arp_note_step+=1; // KOPKOK cause de la premier colonne;
		
		if (arp_note_step==last_selected_arp_note)
			return;
	//	System.err.println("Note == "+arp_note_step);
		
		
		if (last_selected_arp_note!=-1)
		{
			TableColumn tm = jTable_MULTIARP.getColumnModel().getColumn(last_selected_arp_note);
		    tm.setCellRenderer(new ColorColumnRenderer(Color.white,Color.black));
		}
		if (arp_note_step<jTable_MULTIARP.getColumnModel().getColumnCount())
		{
		TableColumn tm = jTable_MULTIARP.getColumnModel().getColumn(arp_note_step);
	    tm.setCellRenderer(new ColorColumnRenderer(Color.gray,Color.WHITE));
		last_selected_arp_note=arp_note_step;
		}
	}

	private void light_selected_note(int note_step)
	{
		
	note_step+=1; // KOPKOK cause de la premier colonne;
		
		if (note_step==last_selected_note)
			return;
	//	System.err.println("Note == "+note_step);
		
		if (last_selected_note!=-1)
		{
			if (last_selected_note<jTable.getColumnModel().getColumnCount())
			{
				TableColumn tm = jTable.getColumnModel().getColumn(last_selected_note);
			    tm.setCellRenderer(new ColorColumnRenderer(Color.white,Color.black));
			}
		}
		if (note_step<jTable.getColumnModel().getColumnCount())
		{
		TableColumn tm = jTable.getColumnModel().getColumn(note_step);
	    tm.setCellRenderer(new ColorColumnRenderer(Color.gray,Color.WHITE));
		last_selected_note=note_step;
		}
	}

	/**
	 * This method initializes jCheckBox_LoopMultiARP	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJCheckBox_LoopMultiARP() {
		if (jCheckBox_LoopMultiARP == null) {
			jCheckBox_LoopMultiARP = new JCheckBox();
			jCheckBox_LoopMultiARP.setSelected(ta.isLoopMultiArp());
			jCheckBox_LoopMultiARP.setText("LoopMultiARP");
			jCheckBox_LoopMultiARP.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ta.setLoopMultiArp(jCheckBox_LoopMultiARP.isSelected());
				}
			});
		}
		return jCheckBox_LoopMultiARP;
	}

	
}
