package org.warnotte.elecribulator.GUI;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import org.warnotte.elecribulator.PresetManager.ControlValue;
import org.warnotte.elecribulator.PresetManager.PresetManager;
import org.warnotte.waxaudiomiditools.CControlers.KnowMidiList;
import org.warnotte.waxlibswingcomponents.Swing.Component.WaxSlider.WRoundSlider;

public class EMXPanelBase extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public PresetManager pmB = null;
	boolean drumpanel = false;
	public static Image icone = null;  //  @jve:decl-index=0:
	public static Image icone_drum = null;  //  @jve:decl-index=0:
	
	public void loadBackGround(URL filename)
	{
		if (drumpanel==false)
		{
				icone = new ImageIcon(filename).getImage();
		}
		else
			icone_drum = new ImageIcon(filename).getImage();
		
		repaint();
	}
	
	@Override
	public void paint(Graphics g)
	{
		if (drumpanel==false)
		{
			if (icone!=null)
				g.drawImage(icone ,0, 0, null);
				}
		else
			if (icone_drum!=null)
				g.drawImage(icone_drum ,0, 0, null);
				
		super.paint(g);
		
	  /* */
	}
	
	@Override
	public void paintComponent(Graphics g) {
		if (drumpanel==false)
		{
			if (icone!=null)
			     g.drawImage(icone ,0, 0, null);
		}
		else
		{
			if (icone_drum!=null)
			     g.drawImage(icone_drum ,0, 0, null);
		}
	}
	
	public EMXPanelBase(boolean drum_panel)
	{
		super();
		this.drumpanel=drum_panel;
		this.pmB = new PresetManager(new KnowMidiList());
	}
	public EMXPanelBase(PresetManager p,boolean drum_panel)
	{
		super();
		this.drumpanel=drum_panel;
		this.pmB = p;
	}

	protected WRoundSlider createSlider(String name, float value) {
		return createSlider(name, value, 0,127, 310, 115);
	}
	protected WRoundSlider createSlider(String name, float value, int min, int max) {
		return createSlider(name, value, min,max, 310, 115);
	}
	
	
	
	protected WRoundSlider createSlider(String name, float value, int min, int max, int maxangle, int angleoffset) {
		WRoundSlider slider = new WRoundSlider();
		slider.setName(name);
		slider.setEnableTickString(false);
		slider.setEnableTick(false);
		slider.setMaximumAngle(maxangle);
		slider.setAngleOffset(angleoffset);
		slider.setMinimum(min);
		slider.setMaximum(max);
		slider.setValue((int) value);
		return slider;
	}
	
	

	protected WRoundSlider getSlider(int cc) {
		String name = pmB.getPresetByCC(cc).getLabel();
		
		WRoundSlider round = getSliderByName(name);
		return round;
	}
	protected JToggleButton getBouton(int cc) {
		String name = pmB.getPresetByCC(cc).getLabel();
		
		JToggleButton round = getJToggleButtonByName(name);
		return round;
	}
	

	protected JComponent getControlByName(String name)
	{
		WRoundSlider w = getSliderByName(name);
		if (w!=null) return w;
		return getJToggleButtonByName(name);
	}
	
	protected JToggleButton getJToggleButtonByName(String name) {
		int sz = getComponentCount();
		
		for (int i = 0; i < sz; i++) {
			if (getComponent(i) instanceof JToggleButton)
			{
				JToggleButton c = (JToggleButton) getComponent(i);
				if ((c!=null) && (c.getName()!=null))
				{
				if (c.getName().equalsIgnoreCase(name))
					return c;
				}
				else
				{
				//	System.err.println("getJToggleButtonByName(\""+name+"\") couldn't be found.");
				}
			}
		}
		//System.err.println("getJToggleButtonByName(\""+name+"\") couldn't be found.");
		return null;
	}
	
	protected WRoundSlider getSliderByName(String name) {
		int sz = getComponentCount();
		
		for (int i = 0; i < sz; i++) {
			if (getComponent(i) instanceof WRoundSlider)
			{
				WRoundSlider c = (WRoundSlider) getComponent(i);
				if (c.getName().equalsIgnoreCase(name))
					return c;
			}
		}
		
		return null;
	}

	protected void refreshSlider(String name, float value) {
		
		WRoundSlider slider = getSliderByName(name); //new WRoundSlider();
	//	slider.setName(name);
	//	slider.setEnableTickString(false);
	//	slider.setEnableTick(false);
		slider.setValue((int) value);
	}
	
	public void refreshComponents()
	{
		for (int i = 0; i < pmB.size(); i++) {
			ControlValue cv = pmB.get(i);
			String cvLabel = cv.getLabel();
		//	System.err.println("CV "+cvLabel);
			Component cp = getControlByName(cvLabel);

			if (cp==null)
			{
				System.err.println("I cannot found the component for "+cvLabel);
			}
			if (cp instanceof WRoundSlider)
			{
				WRoundSlider w = (WRoundSlider)cp;
				w.setValue((int)cv.getValue());
			}
			
			if (cp instanceof JToggleButton)
			{
				JToggleButton w = (JToggleButton)cp;
				float value = cv.getValue();
				w.setSelected(((value>63)?true:false));
			}
			
			
			
		}
	}
}
