package orw.warnotte.elecribulator.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.midi.InvalidMidiDataException;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;
import org.warnotte.OBJ2GUI.JWPanel;
import org.warnotte.OBJ2GUI.ParseurAnnotations;
import org.warnotte.OBJ2GUI.Events.MyChangedEvent;
import org.warnotte.OBJ2GUI.Events.MyEventListener;
import org.warnotte.waxaudiomiditools.CControlers.SignGenBase;
import org.warnotte.waxaudiomiditools.CControlers.SignGen_VCA_2ND;
import org.warnotte.waxaudiomiditools.CControlers.GUI.Panel_VCA2ND;
import org.warnotte.waxlib2.Updater.Version;

import orw.warnotte.elecribulator.CControlers.CCManager;
import orw.warnotte.elecribulator.CControlers.Thread_Modulateurs;
import orw.warnotte.elecribulator.Multi.evt;

public class MainFrame extends JFrame implements WindowListener
{

	private static final long	serialVersionUID	= 1L;
	private JPanel				jContentPane		= null;
	private evt evt1 = null;
	ArrayList<CCManager> manager =null;  //  @jve:decl-index=0:
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JButton jButton_MasterSync = null;
	private JButton jButton_CopyToAllModulator = null;
	private JButton jButton_SendStart = null;
	private JButton jButton_SendStop = null;
	
	private final Random rand = new Random();
	private JMenuBar jJMenuBar = null;
	private JMenu jMenu_File = null;
	private JMenu jMenu_Help = null;
	
	private JMenuItem jMenuItem_File_New = null;
	private JMenuItem jMenuItem_Load_Modulators = null;
	private JMenuItem jMenuItem_Save_Modulators = null;
	private JMenuItem jMenuItem_Help_Update = null;
	private JMenu jMenu_BackGround = null;
	GUI_PRESETMANAGER panel_gui_preset_man;
	GUI_PRESETMANAGER panel_gui_preset_man_drum;
	private JTabbedPane jTabbedPane = null;
	private JMenuItem jMenuItem_Load_General_Presets = null;
	private JMenuItem jMenuItem_Save_General_Presets = null;
	private JButton jButton_SendNoteDemo;
	private JButton jButton_SEND_PRESET;
	private GUI_Chords jPanel_GUI_Chords;
	private JButton jButton_NoteOff;
	private JCheckBox jButton_ENABLE_ARP;
	private JCheckBox jButton_ENABLE_LEVEL_ADJUSTER;
	private GUI_Arps jPanel_GUI_Arps;
	private JMenuItem jMenuItem_Load_Projet = null;
	private JMenuItem jMenuItem_Save_Project = null;
	private JPanel jPanel_ListProjet = null;
	private JCheckBox jCheckBox_DISPLAY_PROJET = null;
	private JList jList_Projets = null;
	
	DefaultListModel list_projets;
	private JCheckBox jButton_DISABLE_NOTE_DISPATCHER;
	
	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel()
	{
		if (jPanel == null)
		{
			jPanel = new JPanel();
			jPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.X_AXIS));
		}
		return jPanel;
	}
	
	private JPanel getGUI_Chords()
	{
		if (jPanel_GUI_Chords == null)
		{
			jPanel_GUI_Chords = new GUI_Chords(evt1);
			
		}
		return jPanel_GUI_Chords;
	}
	
	private GUI_Arps getGUI_Arps()
	{
		if (jPanel_GUI_Arps == null)
		{
			jPanel_GUI_Arps = new GUI_Arps(evt1);
			
		}
		return jPanel_GUI_Arps;
	}
	
	
	
	
	
	private GUI_PRESETMANAGER getGUI_PRESETMANAGER()
	{
		if (panel_gui_preset_man == null)
		{
			panel_gui_preset_man = new GUI_PRESETMANAGER(evt1.getPm());
		}
		return panel_gui_preset_man;
	}
	private GUI_PRESETMANAGER getGUI_PRESETMANAGER_DRUM()
	{
		if (panel_gui_preset_man_drum == null)
		{
			panel_gui_preset_man_drum = new GUI_PRESETMANAGER(evt1.getPm_drum());
		}
		return panel_gui_preset_man_drum;
	}
	
	
	

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1()
	{
		if (jPanel1 == null)
		{
			GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
			gridBagConstraints41.insets = new Insets(0, 0, 5, 5);
			gridBagConstraints41.gridx = 5;
			gridBagConstraints41.gridy = 0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.insets = new Insets(0, 0, 5, 5);
			gridBagConstraints.gridx = 4;
			gridBagConstraints.gridy = 0;
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints21.insets = new Insets(0, 0, 5, 5);
			gridBagConstraints21.gridx = 7;
			gridBagConstraints21.gridy = 0;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints1.insets = new Insets(0, 0, 5, 5);
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.gridx = 6;
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GridBagLayout());
			GridBagConstraints gbc_jCheckBox_ApplyPresetOnLoad = new GridBagConstraints();
			gbc_jCheckBox_ApplyPresetOnLoad.insets = new Insets(0, 0, 5, 5);
			gbc_jCheckBox_ApplyPresetOnLoad.gridx = 0;
			gbc_jCheckBox_ApplyPresetOnLoad.gridy = 0;
			jPanel1.add(getJCheckBox_ApplyPresetOnLoad(), gbc_jCheckBox_ApplyPresetOnLoad);
			GridBagConstraints gbc_jButton_ENABLE_ARP = new GridBagConstraints();
			gbc_jButton_ENABLE_ARP.insets = new Insets(0, 0, 5, 5);
			gbc_jButton_ENABLE_ARP.gridx = 1;
			gbc_jButton_ENABLE_ARP.gridy = 0;
			jPanel1.add(getJButton_ENABLE_ARP(), gbc_jButton_ENABLE_ARP);
			GridBagConstraints gbc_jButton_ENABLE_LEVEL_ADJUSTER = new GridBagConstraints();
			gbc_jButton_ENABLE_LEVEL_ADJUSTER.insets = new Insets(0, 0, 5, 5);
			gbc_jButton_ENABLE_LEVEL_ADJUSTER.gridx = 2;
			gbc_jButton_ENABLE_LEVEL_ADJUSTER.gridy = 0;
			jPanel1.add(getJButton_ENABLE_LEVEL_ADJUSTER(), gbc_jButton_ENABLE_LEVEL_ADJUSTER);
			
			GridBagConstraints gbc_jButton_DISABLE_NOTE_DISPATCHER = new GridBagConstraints();
			gbc_jButton_DISABLE_NOTE_DISPATCHER.insets = new Insets(0, 0, 5, 5);
			gbc_jButton_DISABLE_NOTE_DISPATCHER.gridx = 3;
			gbc_jButton_DISABLE_NOTE_DISPATCHER.gridy = 0;
			jPanel1.add(getJButton_DISABLE_NOTE_DISPATCHER(), gbc_jButton_DISABLE_NOTE_DISPATCHER);
			jPanel1.add(getJButton_SEND_PRESET(), gridBagConstraints21);
			jPanel1.add(getJButton_CopyToAllModulator(), gridBagConstraints1);
			
			jPanel1.add(getJCheckBox_ForceSendNoteOff(), gridBagConstraints);
			jPanel1.add(getJCheckBox_BypassModulators(), gridBagConstraints41);
			GridBagConstraints gridBagConstraints42 = new GridBagConstraints();
			gridBagConstraints42.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints42.insets = new Insets(0, 0, 0, 5);
			gridBagConstraints42.gridx = 1;
			gridBagConstraints42.gridy = 1;
			
			jPanel1.add(getJButton_SendStart(), gridBagConstraints42);
			GridBagConstraints gridBagConstraints43 = new GridBagConstraints();
			gridBagConstraints43.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints43.insets = new Insets(0, 0, 0, 5);
			gridBagConstraints43.gridx = 2;
			gridBagConstraints43.gridy = 1;
			jPanel1.add(getJButton_SendStop(), gridBagConstraints43);
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints11.insets = new Insets(0, 0, 0, 5);
			gridBagConstraints11.gridx = 5;
			gridBagConstraints11.gridy = 1;
			jPanel1.add(getJButton_NoteOff(), gridBagConstraints11);
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints3.insets = new Insets(0, 0, 0, 5);
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.gridx = 6;
			jPanel1.add(getJButton_SendNoteDemo(), gridBagConstraints3);
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints2.insets = new Insets(0, 0, 0, 5);
			gridBagConstraints2.gridy = 1;
			gridBagConstraints2.gridx = 7;
			
			jPanel1.add(getJButton_MasterSync(), gridBagConstraints2);
			
			
		}
		return jPanel1;
	}

	
	private JCheckBox getJButton_ENABLE_LEVEL_ADJUSTER() {
		if (jButton_ENABLE_LEVEL_ADJUSTER == null) {
			jButton_ENABLE_LEVEL_ADJUSTER = new JCheckBox();
			jButton_ENABLE_LEVEL_ADJUSTER.setText("SetInputToMaxVolume");
			jButton_ENABLE_LEVEL_ADJUSTER.setToolTipText("Set the MIDI velocity coming for midi input to be at Maximum instead of the real value");
			jButton_ENABLE_LEVEL_ADJUSTER.setSelected(evt1.isSetTransmitterLevelToMaximum());
			jButton_ENABLE_LEVEL_ADJUSTER.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					boolean b= jButton_ENABLE_LEVEL_ADJUSTER.isSelected();
					evt1.setSetTransmitterLevelToMaximum(b);
				}
			});
		}
		return jButton_ENABLE_LEVEL_ADJUSTER;
	}
	
	
	private JCheckBox getJButton_ENABLE_ARP() {
		if (jButton_ENABLE_ARP == null) {
			jButton_ENABLE_ARP = new JCheckBox();
			jButton_ENABLE_ARP.setText("Enable Arp");
			jButton_ENABLE_ARP.setToolTipText("Enable the arpegiator when note ON is played");
			jButton_ENABLE_ARP.setSelected(evt1.isArpegiatorEnabled());
			jButton_ENABLE_ARP.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					boolean b= jButton_ENABLE_ARP.isSelected();
					evt1.setArpegiatorEnabled(b);
				}
			});
		}
		return jButton_ENABLE_ARP;
	}
	
	private JCheckBox getJButton_DISABLE_NOTE_DISPATCHER() {
		if (jButton_DISABLE_NOTE_DISPATCHER == null) {
			jButton_DISABLE_NOTE_DISPATCHER = new JCheckBox();
			jButton_DISABLE_NOTE_DISPATCHER.setText("Polyphonize");
			jButton_DISABLE_NOTE_DISPATCHER.setToolTipText("If selected, will receive on a channel and send to 5 chan. If not, will resent on channel and retransmit over the same channel.");
			jButton_DISABLE_NOTE_DISPATCHER.setSelected(evt1.isNoteDispatchingOrDirectResentWithVCA());
			jButton_DISABLE_NOTE_DISPATCHER.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					boolean b= jButton_DISABLE_NOTE_DISPATCHER.isSelected();
					evt1.setNoteDispatchingOrDirectResentWithVCA(b);
				}
			});
		}
		return jButton_DISABLE_NOTE_DISPATCHER;
	}

	/**
	 * This method initializes jButton_MasterSync	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton_CopyToAllModulator()
	{
		if (jButton_CopyToAllModulator == null)
		{
			jButton_CopyToAllModulator = new JButton();
			jButton_CopyToAllModulator.setText("Copy to All modulator");
			jButton_CopyToAllModulator.setToolTipText("Copy first modulator settings to the 4 other modulators");
			jButton_CopyToAllModulator.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					evt1.copySettingsToAllModulateurs();
					
					try {
						createModulateurPanels();
					} catch (Exception e1) {
						DialogDivers.Show_dialog(e1, "Doh");
						e1.printStackTrace();
					}
				}
			});
		}
		return jButton_CopyToAllModulator;
	}
	
	private JButton getJButton_SendNoteDemo()
	{
		if (jButton_SendNoteDemo == null)
		{
			jButton_SendNoteDemo = new JButton();
			jButton_SendNoteDemo.setText("Send midi demo to electribe");
			jButton_SendNoteDemo.setToolTipText("Send 5 notes after each other then 5 notes in same time");
			jButton_SendNoteDemo.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					
					try {
						evt1.sendNoteDemo();
					} catch (InvalidMidiDataException e1) {
						DialogDivers.Show_dialog(e1, "Erreur sending notes");
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						DialogDivers.Show_dialog(e1, "Erreur sending notes");
						e1.printStackTrace();
					}
				}
			});
		}
		return jButton_SendNoteDemo;
	}
	
	private JButton getJButton_SendStop()
	{
		if (jButton_SendStop == null)
		{
			jButton_SendStop = new JButton();
			jButton_SendStop.setText("Stop");
			jButton_SendStop.setToolTipText("Send a Stop command");
			jButton_SendStop.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					
					try {
						evt1.sendSTOP();
					} catch (InvalidMidiDataException e1) {
						DialogDivers.Show_dialog(e1, "Erreur sending STOP");
						e1.printStackTrace();
					}
				}
			});
		}
		return jButton_SendStop;
	}
	private JButton getJButton_SendStart()
	{
		if (jButton_SendStart == null)
		{
			jButton_SendStart = new JButton();
			jButton_SendStart.setText("Play");
			jButton_SendStart.setToolTipText("Send a Start command (Play button)");
			jButton_SendStart.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					
					try {
						evt1.sendSTART();
						
					} catch (InvalidMidiDataException e1) {
						DialogDivers.Show_dialog(e1, "Erreur sending START");
						e1.printStackTrace();
					} 
				}
			});
		}
		return jButton_SendStart;
	}
	
	
	
	
	/**
	 * This method initializes jButton_MasterSync	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton_MasterSync()
	{
		if (jButton_MasterSync == null)
		{
			jButton_MasterSync = new JButton();
			jButton_MasterSync.setText("Master Sync");
			jButton_MasterSync.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					for (int i = 0; i < manager.size(); i++) {
						manager.get(i).sync();
					}
				}
			});
		}
		return jButton_MasterSync;
	}

	private JButton getJButton_SEND_PRESET() {
		if (jButton_SEND_PRESET == null) {
			jButton_SEND_PRESET = new JButton();
			jButton_SEND_PRESET.setText("Send preset");
			jButton_SEND_PRESET.setToolTipText("Resent the preset to the electribe");
			jButton_SEND_PRESET.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					evt1.applyPresets();
				}
			});
		}
		return jButton_SEND_PRESET;
	}
	
	private JButton getJButton_NoteOff() {
		if (jButton_NoteOff == null) {
			jButton_NoteOff = new JButton();
			jButton_NoteOff.setText("Notes Off");
			jButton_NoteOff.setToolTipText("Send a general notes off.");
			jButton_NoteOff.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					evt1.sendNoteOff();
				}
			});
		}
		return jButton_NoteOff;
	}
	
	
	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJMenu_File());
			jJMenuBar.add(getJMenu_BackGround());
			
			jJMenuBar.add(getJMenu_View());
			jJMenuBar.add(getJMenu_Help());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jMenu_File	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenu_File() {
		if (jMenu_File == null) {
			jMenu_File = new JMenu();
			jMenu_File.setText("File");
			jMenu_File.add(getJMenuItem_File_New());
			jMenu_File.add(new JSeparator());
			jMenu_File.add(getJMenuItem_Load_Projet());
			jMenu_File.add(getJMenuItem_Save_Project());
			jMenu_File.add(getJMenuItem_Load_Modulators());
			jMenu_File.add(getJMenuItem_Load_General_Presets());
			jMenu_File.add(getJMenuItem_Load_Drums());
			jMenu_File.add(new JSeparator());
			jMenu_File.add(getJMenuItem_Save_Modulators());
			jMenu_File.add(getJMenuItem_Save_Drum_Presets());
			jMenu_File.add(getJMenuItem_Save_General_Presets());
			
			jMenu_File.add(new JSeparator());
			jMenu_File.add(getJMenuItem_quit());
			
		}
		return jMenu_File;
	}

	private JMenu getJMenu_Help() {
		if (jMenu_Help == null) {
			jMenu_Help = new JMenu();
			jMenu_Help.setText("Help");
			jMenu_Help.add(getJMenuItem_Help_Update());
			
		}
		return jMenu_Help;
	}
	
	public File[] getBackGroundList()
	{
		File f = new File("images"+File.separator+"fond"+File.separator);
		File [] fs = f.listFiles();
		return fs;
	}
	
	private JMenu getJMenu_BackGround() {
		if (jMenu_BackGround == null) {
			jMenu_BackGround = new JMenu();
			jMenu_BackGround.setText("BackGround");
			File [] fs = getBackGroundList();
			
			for (int i = 0; i < fs.length; i++) {
				String filename=fs[i].getName();
				if (filename.contains("CVS")) continue;
				jMenu_BackGround.add(getJMenuItem_Load_Modulators(filename));
				
				
			}
			
		}
		return jMenu_BackGround;
	}
	
	
	private JMenuItem getJMenuItem_Load_Modulators(final String filename) {
		JMenuItem jMenuItem_change_background = new JMenuItem();
			jMenuItem_change_background.setText(filename);
			jMenuItem_change_background.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String pathtoimage = "images"+File.separator+"fond"+File.separator+""+filename;
					System.err.println("changed bck to "+pathtoimage);
					Image img = new ImageIcon(pathtoimage).getImage();
					EMXPanelBase.icone=img;
					panel_gui_preset_man_drum.refresh();
					panel_gui_preset_man.refresh();
				}
			});
		
		return jMenuItem_change_background;
	}

	
	/**
	 * This method initializes jMenuItem_Load_Modulators	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem_Load_Modulators() {
		if (jMenuItem_Load_Modulators == null) {
			jMenuItem_Load_Modulators = new JMenuItem();
			jMenuItem_Load_Modulators.setText("Load Modulators");
			jMenuItem_Load_Modulators.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						LoadModulateurPreset();
					} catch (Exception e1) {
						DialogDivers.Show_dialog(e1, "Pb load");
						e1.printStackTrace();
					}
				}
			});
		}
		return jMenuItem_Load_Modulators;
	}
	
	private JMenuItem getJMenuItem_File_New() {
		if (jMenuItem_File_New == null) {
			jMenuItem_File_New = new JMenuItem();
			jMenuItem_File_New.setText("New");
			jMenuItem_File_New.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						create_new();
					} catch (Exception e1) {
						DialogDivers.Show_dialog(e1, "Erreur new : "+e1);
						e1.printStackTrace();
					}
				}
			});
		}
		return jMenuItem_File_New;
	}
	
	

	
	protected void create_new() throws Exception {
		evt1.create_new();		
		panel_gui_preset_man.setPresetManager(evt1.getPm());
		panel_gui_preset_man_drum.setPresetManager(evt1.getPm_drum());
		createModulateurPanels();
		System.gc();
		
		
	}


	private JMenuItem getJMenuItem_Help_Update() {
		if (jMenuItem_Help_Update == null) {
			jMenuItem_Help_Update = new JMenuItem();
			jMenuItem_Help_Update.setText("Search for Update");
			jMenuItem_Help_Update.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					boolean t = evt.checkForNewVersion();
					if (t==false)
					{
						DialogDivers.Show_dialog(null, "No new version aivailable");
					}
					else
						evt.checkandGetForNewVersion();
					
				
				}
			});
		}
		return jMenuItem_Help_Update;
	}


	/**
	 * This method initializes jMenuItem_Save_Modulators	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem_Save_Modulators() {
		if (jMenuItem_Save_Modulators == null) {
			jMenuItem_Save_Modulators = new JMenuItem();
			jMenuItem_Save_Modulators.setText("Save Modulators");
			jMenuItem_Save_Modulators.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						SaveModulateurPreset();
					} catch (IOException e1) {
						DialogDivers.Show_dialog(e1, "Pb save");
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						DialogDivers.Show_dialog(e1, "Pb save");
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						DialogDivers.Show_dialog(e1, "Pb save");
						e1.printStackTrace();
					}
				}
			});
		}
		return jMenuItem_Save_Modulators;
	}
	

	protected void LoadModulateurPreset() throws Exception {
		File f = DialogDivers.LoadDialog(this, "xml", "saves"+File.separator+"modulateurs"+File.separator);
		if (f!=null)
		{
			LoadModulateurPreset(f.getAbsolutePath());
		}
	}
	protected void LoadModulateurPreset(String file) throws Exception {
		
		evt1.load(file);
		manager=evt1.getModulateurs();
		
		createModulateurPanels();
		
	}
	
	protected void SaveModulateurPreset() throws IOException, ClassNotFoundException, InterruptedException {
		String f = DialogDivers.SaveDialog(this, "xml", "saves"+File.separator+"modulateurs"+File.separator, "Save modulators presets");
		if (f!=null)
		{
			evt1.save(f);
			
		}
	}
	protected void SaveGeneralPresets() throws IOException {
		String f = DialogDivers.SaveDialog(this, "xml", "saves"+File.separator+"presets"+File.separator, "Save general presets");
		if (f!=null)
		{
			evt1.save_presets(f);
			
		}
	}
	protected void SaveDrumPreset() throws IOException {
		String f = DialogDivers.SaveDialog(this, "xml", "saves"+File.separator+"drum_presets"+File.separator, "Save drum presets");
		if (f!=null)
		{
			evt1.save_drum_presets(f);
			
		}
	}
	

	
	protected void SaveProjet() throws IOException {
		String f = DialogDivers.SaveDialog(this, "xml", "saves"+File.separator+"projects"+File.separator, "Save Project");
		if (f!=null)
		{
			evt1.saveProjet(f);
			
		}
	}
	protected void LoadGeneralPresets() throws IOException, ClassNotFoundException, InterruptedException {
		File f = DialogDivers.LoadDialog(this, "xml", "saves"+File.separator+"presets"+File.separator);
		if (f!=null)
		{
			LoadGeneralPresets(f.getAbsolutePath());
		}
	}
	
	protected void LoadGeneralPresets(String file) throws IOException, ClassNotFoundException, InterruptedException {
		panel_gui_preset_man.setPresetManager(evt1.loadPresets(file));
		
	}
	
	protected void LoadDrumPresets() throws IOException, ClassNotFoundException, InterruptedException {
		File f = DialogDivers.LoadDialog(this, "xml", "saves"+File.separator+"drum_presets"+File.separator);
		if (f!=null)
		{
			LoadDrumPresets(f.getAbsolutePath());
		}
	}
	
	protected void LoadDrumPresets(String file) throws IOException, ClassNotFoundException, InterruptedException {
		panel_gui_preset_man_drum.setPresetManager(evt1.loadDrumPresets(file));
		
	}
	

	
	protected void LoadProjet() throws Exception {
		File f = DialogDivers.LoadDialog(this, "xml", "saves"+File.separator+"projects"+File.separator);
		if (f!=null)
		{
			evt1.loadProjet(f.getAbsolutePath());
			manager=evt1.getModulateurs();
			createModulateurPanels();
			panel_gui_preset_man.setPresetManager(evt1.getPm());
			
		}
	}
	

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.addTab("Drum Presets", null, getGUI_PRESETMANAGER_DRUM(), null);
			jTabbedPane.addTab("Presets", null, getGUI_PRESETMANAGER(), null);
			jTabbedPane.addTab("Modulators", null, new JScrollPane(getJPanel()), null);
			jTabbedPane.addTab("Octavizer", null, new JScrollPane(getGUI_Chords()), null);
			jTabbedPane.addTab("Arpegiators", null, new JScrollPane(getGUI_Arps()), null);
			jTabbedPane.setSelectedIndex(1);
			
		}
		return jTabbedPane;
	}


	/**
	 * This method initializes jMenuItem_Load_General_Presets	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem_Load_General_Presets() {
		if (jMenuItem_Load_General_Presets == null) {
			jMenuItem_Load_General_Presets = new JMenuItem();
			jMenuItem_Load_General_Presets.setText("Load General Presets");
			jMenuItem_Load_General_Presets
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							try {
								LoadGeneralPresets();
							} catch (IOException e1) {
								DialogDivers.Show_dialog(e1, "Pb load");
								e1.printStackTrace();
							} catch (ClassNotFoundException e1) {
								DialogDivers.Show_dialog(e1, "Pb load");
								e1.printStackTrace();
							} catch (InterruptedException e1) {
								DialogDivers.Show_dialog(e1, "Pb load");
								e1.printStackTrace();
							}
						}
					});
		}
		return jMenuItem_Load_General_Presets;
	}


	


	/**
	 * This method initializes jMenuItem_Save_General_Presets	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem_Save_General_Presets() {
		if (jMenuItem_Save_General_Presets == null) {
			jMenuItem_Save_General_Presets = new JMenuItem();
			jMenuItem_Save_General_Presets.setText("Save General Presets");
			jMenuItem_Save_General_Presets
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							try {
								SaveGeneralPresets();
							} catch (IOException e1) {
								DialogDivers.Show_dialog(e1, "Pb save");
								e1.printStackTrace();
							}
						}
					});
		}
		return jMenuItem_Save_General_Presets;
	}


	

	/**
	 * This method initializes jMenuItem_Load_Projet	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem_Load_Projet() {
		if (jMenuItem_Load_Projet == null) {
			jMenuItem_Load_Projet = new JMenuItem();
			jMenuItem_Load_Projet.setText("Load Project");
			jMenuItem_Load_Projet.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						LoadProjet();
					} catch (IOException e1) {
						e1.printStackTrace();
						DialogDivers.Show_dialog(e1, "Error during load");
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
						DialogDivers.Show_dialog(e1, "Error during load");
					} catch (InterruptedException e1) {
						e1.printStackTrace();
						DialogDivers.Show_dialog(e1, "Error during load");
					} catch (Exception e1) {
						DialogDivers.Show_dialog(e1, "Error during load");
						e1.printStackTrace();
						
						
					}
				}
			});
		}
		return jMenuItem_Load_Projet;
	}

	/**
	 * This method initializes jMenuItem_Save_Project	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem_Save_Project() {
		if (jMenuItem_Save_Project == null) {
			jMenuItem_Save_Project = new JMenuItem();
			jMenuItem_Save_Project.setText("Save Project");
			jMenuItem_Save_Project.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						SaveProjet();
					} catch (IOException e1) {
						e1.printStackTrace();
						DialogDivers.Show_dialog(e1, "Error during Save");
					}
				}
			});
		}
		return jMenuItem_Save_Project;
	}

	/**
	 * This method initializes jPanel_ListProjet	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel_ListProjet() {
		if (jPanel_ListProjet == null) {
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.fill = GridBagConstraints.BOTH;
			gridBagConstraints4.weighty = 1.0;
			gridBagConstraints4.weightx = 1.0;
			jPanel_ListProjet = new JPanel();
			jPanel_ListProjet.setLayout(new GridBagLayout());
			jPanel_ListProjet.add(getJList_Projets(), gridBagConstraints4);
		}
		return jPanel_ListProjet;
	}

	/**
	 * This method initializes jCheckBox_DISPLAY_PROJET	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJCheckBox_DISPLAY_PROJET() {
		if (jCheckBox_DISPLAY_PROJET == null) {
			jCheckBox_DISPLAY_PROJET = new JCheckBox();
			jCheckBox_DISPLAY_PROJET.setSelected(getJPanel_ListProjet().isVisible());
			jCheckBox_DISPLAY_PROJET.setText("Show Projects List");
			jCheckBox_DISPLAY_PROJET.addActionListener(new java.awt.event.ActionListener() {   
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jPanel_ListProjet.setVisible(jCheckBox_DISPLAY_PROJET.isSelected());
				}
			
			});
		}
		return jCheckBox_DISPLAY_PROJET;
	}

	/**
	 * This method initializes jList_Projets	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJList_Projets() {
		if (jList_Projets == null) {
			list_projets = new DefaultListModel();
			try {
				refresh_list_projets(list_projets);
			} catch (FileNotFoundException e2) {
				DialogDivers.Show_dialog(e2, "Warning");
				e2.printStackTrace();
			}
			jList_Projets = new JList(list_projets);
			jList_Projets.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
				public void valueChanged(javax.swing.event.ListSelectionEvent e) {
					
					File f = evt1.getLoaded_Projet();
					if ((f==null) || ((jList_Projets.getSelectedValue().equals(evt1.getLoaded_Projet().getName())==false)))
					{
						System.err.println(""+jList_Projets.getSelectedValue());
						File f2 = new File("saves"+File.separator+"projects"+File.separator+""+jList_Projets.getSelectedValue());
						try {
							evt1.loadProjet(f2.getAbsolutePath());
							
							panel_gui_preset_man.setPresetManager(evt1.getPm());
							createModulateurPanels();
							
						} catch (IOException e1) {
							DialogDivers.Show_dialog(e1, "I cannot found the file");
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							DialogDivers.Show_dialog(e1, "Classnotfound when loading the file");
							e1.printStackTrace();
						} catch (InterruptedException e1) {
							DialogDivers.Show_dialog(e1, "Interrupted exception when loading the file");
							e1.printStackTrace();
						} catch (Exception e1) {
							DialogDivers.Show_dialog(e1, "Unknown exception");
							e1.printStackTrace();
						}
					}
					
					
				}
			});
		}
		return jList_Projets;
	}

	private void refresh_list_projets(DefaultListModel list_projets2) throws FileNotFoundException {
		String dir = "saves"+File.separator+"projects"+File.separator;
		File f = new File(dir);
		if (f.exists()==true)
		{
			FilenameFilter filter= new XMLFileFilter();
			File [] files = f.listFiles(filter);
			
			for (int i = 0; i < files.length; i++) {
				File fM = files[i].getAbsoluteFile();
				list_projets2.add(i, fM.getName());
			}
		}
		else throw new FileNotFoundException("I cannot find the directory "+dir+ " in order to load list of project...");
	}
	
	public static LoadSplash ls;
	private JMenu jMenu_View = null;
	private JCheckBox jCheckBox_ApplyPresetOnLoad = null;  //  @jve:decl-index=0:visual-constraint="686,113"
	private JMenuItem jMenuItem_Save_Drum_Presets = null;
	private JMenuItem jMenuItem_Load_Drums = null;
	private JCheckBox jCheckBox_ForceSendNoteOff = null;
	private JCheckBox jCheckBox_BypassModulators = null;
	private JMenuItem jMenuItem_quit = null;

	/**
	 * This method initializes jMenu_View	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenu_View() {
		if (jMenu_View == null) {
			jMenu_View = new JMenu();
			jMenu_View.setText("View");
			jMenu_View.add(getJCheckBox_DISPLAY_PROJET());
		}
		return jMenu_View;
	}

	/**
	 * This method initializes jCheckBox_ApplyPresetOnLoad	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJCheckBox_ApplyPresetOnLoad() {
		if (jCheckBox_ApplyPresetOnLoad == null) {
			jCheckBox_ApplyPresetOnLoad = new JCheckBox();
			jCheckBox_ApplyPresetOnLoad.setText("SendPresetToEmx");
			jCheckBox_ApplyPresetOnLoad.setText("Send preset to emx when loading new patch");
			jCheckBox_ApplyPresetOnLoad.setSelected(evt1.isApplyPresetOnLoad());
			jCheckBox_ApplyPresetOnLoad.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					evt1.setApplyPresetOnLoad(jCheckBox_ApplyPresetOnLoad.isSelected());
				}
			});
		}
		return jCheckBox_ApplyPresetOnLoad;
	}

	/**
	 * This method initializes jMenuItem_Save_Drum_Presets	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem_Save_Drum_Presets() {
		if (jMenuItem_Save_Drum_Presets == null) {
			jMenuItem_Save_Drum_Presets = new JMenuItem();
			jMenuItem_Save_Drum_Presets.setText("Save Drums");
			jMenuItem_Save_Drum_Presets.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						SaveDrumPreset();
					} catch (IOException e1) {
						DialogDivers.Show_dialog(e1, "Pb save");
						e1.printStackTrace();
					}
				}
			});
		}
		return jMenuItem_Save_Drum_Presets;
	}


	/**
	 * This method initializes jMenuItem_Load_Drums	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem_Load_Drums() {
		if (jMenuItem_Load_Drums == null) {
			jMenuItem_Load_Drums = new JMenuItem();
			jMenuItem_Load_Drums.setText("Load Drums");
			jMenuItem_Load_Drums.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						LoadDrumPresets();
					} catch (IOException e1) {
						DialogDivers.Show_dialog(e1, "Pb load");
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						DialogDivers.Show_dialog(e1, "Pb load");
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						DialogDivers.Show_dialog(e1, "Pb load");
						e1.printStackTrace();
					}
				}
			});
		}
		return jMenuItem_Load_Drums;
	}


	/**
	 * This method initializes jCheckBox_ForceSendNoteOff	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJCheckBox_ForceSendNoteOff() {
		if (jCheckBox_ForceSendNoteOff == null) {
			jCheckBox_ForceSendNoteOff = new JCheckBox();
			jCheckBox_ForceSendNoteOff.setSelected(evt1.isForceSendNoteOFF());
			jCheckBox_ForceSendNoteOff.setText("Force Send Note Off");
			jCheckBox_ForceSendNoteOff.setToolTipText("Force send note off when reiceving note off on midi input. Use if when you got a synth with a VCA and you don't want to use electribulator VCA.");
			jCheckBox_ForceSendNoteOff.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					evt1.setForceSendNoteOFF(jCheckBox_ForceSendNoteOff.isSelected());
				}
			});
		}
		return jCheckBox_ForceSendNoteOff;
	}

	/**
	 * This method initializes jCheckBox_BypassModulators	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJCheckBox_BypassModulators() {
		if (jCheckBox_BypassModulators == null) {
			jCheckBox_BypassModulators = new JCheckBox();
			jCheckBox_BypassModulators.setSelected(CCManager.isBypassModulators());
			jCheckBox_BypassModulators.setText("BypassModulators");
			jCheckBox_BypassModulators.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					CCManager.setBypassModulators(jCheckBox_BypassModulators.isSelected());
				}
			});
		}
		return jCheckBox_BypassModulators;
	}

	/**
	 * This method initializes jMenuItem_quit	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem_quit() {
		if (jMenuItem_quit == null) {
			jMenuItem_quit = new JMenuItem();
			jMenuItem_quit.setText("Quit");
			jMenuItem_quit.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					quit();
					
				}
			});
		}
		return jMenuItem_quit;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				String os = System.getProperty("os.name");
				if ((os.contains("Windows"))
						|| (os.contains("windows"))
						|| (os.contains("win"))
						|| (os.contains("vista"))
					)
					try {
						UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InstantiationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedLookAndFeelException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				
				
				Version.set(0,2,4,"SplineInterpolation");
				ls=new LoadSplash("images"+System.getProperty("file.separator")+"splash.jpg", true, false, Version.getVersionString(), "Warnotte Renaud");
				ls.setVisible(true);
				
				evt evt1= null;
				try {
					evt1 =new evt();
					
				CCManager m1 = new CCManager(evt1.getPm());
				ArrayList<CCManager> m = new ArrayList<CCManager>();
				m.add(m1);
				MainFrame thisClass;
				
					thisClass = new MainFrame(evt1);

					thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					thisClass.setVisible(true);

					Thread_Modulateurs t = new Thread_Modulateurs(m1, 100);
					t.start();
					
					ls.set_ProgressBarValue(100);
					ls.setVisible(false);
				} 
				catch (Exception e)
				{
					ls.setVisible(false);
					e.printStackTrace();
					DialogDivers.Show_dialog(e, "Error initializing");
					System.exit(-1);
				}
			}
		});
	}

	/**
	 * This is the default constructor
	 * @throws Exception 
	 */
	public MainFrame(evt evt12) throws Exception
	{
		super();
		evt1 = evt12;
		this.manager = evt12.getModulateurs();
		initialize();
		this.addWindowListener(this);
		this.setTitle("Wax Electribulator "+Version.getVersionString());
		this.setSize(1100, 950);
		Image img = new ImageIcon("images"+File.separator+"fond"+File.separator+"EMX_Wax.png").getImage();
		EMXPanel.icone=img;
		

		
		
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 * @throws Exception 
	 */
	private void initialize() throws Exception
	{
		this.setSize(800, 400);
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("ControlWin");
		createModulateurPanels();
		
		
		
	}

	private void createModulateurPanels() throws Exception {
		jPanel.removeAll();
		manager=evt1.getModulateurs();
		for (int i = 0; i < manager.size(); i++) {
			CCManager c = manager.get(i); 
			JXTaskPaneContainer cp = createPanels(c);
			jPanel.add(cp);
		}
		jPanel.revalidate();
		jPanel.doLayout();
	}

	private JXTaskPaneContainer createPanels(CCManager manager) throws Exception
	{
		JXTaskPaneContainer collector = new JXTaskPaneContainer();
	//	collector.setLayout(new BoxLayout(collector, BoxLayout.Y_AXIS));
		
		JWPanel panel2 = (JWPanel) ParseurAnnotations.CreatePanelFromObject("Man", manager,false);
		collector.add(panel2);
		
		
		for (int i = 0; i < manager.getGens().size(); i++)
		{
			final SignGenBase sg = manager.getGens().get(i);
			JPanel p = null;
			
			if ((sg instanceof SignGen_VCA_2ND) == false)
			{
				final JWPanel panel = (JWPanel) ParseurAnnotations.CreatePanelFromObject(sg.getName(), sg,false);
				
				panel.addMyEventListener(new MyEventListener()
				{
					public void myEventOccurred(MyChangedEvent e)
					{
						try {
							ParseurAnnotations.Refresh_PanelEditor_For_Object(sg.getName(), panel, sg,panel, false);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				p=panel;
			}
			else
			{
				Panel_VCA2ND panel = new Panel_VCA2ND((SignGen_VCA_2ND) sg);
				p= panel;
			}
			
			JXTaskPane pane = new JXTaskPane();
			pane.setTitle(sg.getName());
			pane.add(p);
			collector.add(pane);
		}
		
		return collector;
		
		
	}

	private Color getRandomColor() {
		
		return new Color(rand .nextInt(255),rand.nextInt(255),rand.nextInt(255));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane()
	{
		if (jContentPane == null)
		{
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJPanel1(), BorderLayout.NORTH);
			jContentPane.add(getJTabbedPane(), BorderLayout.CENTER);
			jContentPane.add(getJPanel_ListProjet(), BorderLayout.EAST);
			
			
			
		}
		return jContentPane;
	}


	
	public void windowActivated(WindowEvent windowevent) {
		// TODO Auto-generated method stub
		
	}


	
	public void windowClosed(WindowEvent windowevent) {
	
		
	}


	private void quit() {
		evt1.close();
		System.exit(-1);
	}


	
	public void windowClosing(WindowEvent windowevent) {
		quit();
		
	}


	
	public void windowDeactivated(WindowEvent windowevent) {
		// TODO Auto-generated method stub
		
	}


	
	public void windowDeiconified(WindowEvent windowevent) {
		// TODO Auto-generated method stub
		
	}


	
	public void windowIconified(WindowEvent windowevent) {
		// TODO Auto-generated method stub
		
	}


	
	public void windowOpened(WindowEvent windowevent) {
		// TODO Auto-generated method stub
		
	}

}
