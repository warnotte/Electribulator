package  orw.warnotte.elecribulator.GUI;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;


/** KOPKOK simple Splash screen. */
// TODO : Il faut en faire un singleton
public class LoadSplash extends ShadowedWindow
{
	Color ProgressBarColor = new Color(255,0,0,64);
	Color BackProgressBarColor = new Color(255,255,255,127);
	private static final long serialVersionUID = 1L;
	private int				  load_value = 1; // 0 -> 100
	protected ImageIcon		  disc;

	String VersionNumber = null;
	String CopyrightNotes = null;  //  @jve:decl-index=0:
	
	Font font_normal = new Font("arial", Font.PLAIN, 12);  //  @jve:decl-index=0:
	Font font_gros =new Font("impact", Font.PLAIN, 16);
	
	BufferedImage tmp = null;
	
	//Thread /eImage = null;  //  @jve:decl-index=0:
    
	
	public LoadSplash(String fileName)
	{
	    this(fileName, true, false);
		this.setAlwaysOnTop(true);
	}
	
	public LoadSplash(String fileName, boolean Create_Shadow, boolean FadingSplash)
	{
		this(fileName, Create_Shadow, FadingSplash, null,null);
	}
	
	public LoadSplash(String fileName, boolean Create_Shadow, boolean FadingSplash, String VersionNum, String CopyrightNotes)
	{
		super(fileName, Create_Shadow);
		this.VersionNumber=VersionNum;
		this.CopyrightNotes=CopyrightNotes;
	//	System.setProperty("sun.java2d.noddraw", "true");
		this.setAlwaysOnTop(true);
		this.setLayout(null);
		tmp = new BufferedImage(splash_orig.getWidth(), splash_orig.getHeight(), BufferedImage.TYPE_INT_RGB);
		set_ProgressBarValue(0);
	}

	
    @Override
	public void paint(Graphics g) {
    	//super.paint(g);
    	// Dessine la barre de progression
     	g.drawImage(tmp, 0,0, this);
    }
	
	private void computeImage(/*Graphics g*/)
	{
		
		Graphics2D g = tmp.createGraphics();
		g.drawImage(splash_orig, 0,0, LoadSplash.this);
		
		
	g.setFont(font_normal);
	FontMetrics metrique = g.getFontMetrics();
	int minY = (this.getHeight()-55);
	int maxX = (this.getWidth()-50);
	int minX = 10;
	int maxY = 15;
	g.setColor(Color.black);
	g.drawRect(minX, minY, (int)(100*(float)maxX/100), maxY);
	g.setColor(BackProgressBarColor);
	g.fillRect(minX+1, minY+1, (int)(100*(float)(maxX-1)/100), maxY-1);
	g.setColor(ProgressBarColor);
	g.fillRect(minX+1, minY+1, (int)(load_value*(float)(maxX-1)/100), maxY-1);
	
	int LabelCenteredX = ((minX+1)+(int)(100*(float)(maxX-1)/100))/2;
	//g.setColor(Color.black);
	
	g.setColor(Color.BLACK);
	drawTextHighlight(g,metrique, ""+load_value+" %",12, 1, LabelCenteredX,minY+maxY/2, true);
	
	if (VersionNumber!=null)
	{
//	Rectangle2D r2d = g.getFontMetrics().getStringBounds(VersionNumber, g);
	g.setFont(font_gros);
	metrique = g.getFontMetrics();
	g.setColor(Color.WHITE);
	drawTextHighlight(g,metrique, ""+VersionNumber,12, 1, minX,minY-20, false);
	g.setColor(Color.BLACK);
	g.drawString(""+VersionNumber, minX+12,minY-20+12);
	}

	}

	public synchronized String getVersionNumber()
	{
		return VersionNumber;
	}

	public synchronized void setVersionNumber(String versionNumber)
	{
		VersionNumber = versionNumber;
	}

	public synchronized String getCopyrightNotes()
	{
		return CopyrightNotes;
	}

	public synchronized void setCopyrightNotes(String copyrightNotes)
	{
		CopyrightNotes = copyrightNotes;
	}

	public void set_ProgressBarValue(int p)
	{
		this.load_value = p;
		computeImage();
		repaint();
		/*
		if (computeImage!=null)
			if (computeImage.isAlive()==true)
			{
				computeImage.interrupt();
				//computeImage.stop();
			}
		computeImage = new Thread()
		{
			public void run()
			{	
				computeImage();
				repaint();
			}
		};
		computeImage.setName("Compute Splash");
		computeImage.start();*/
	
		
	}
	
	public static void main(String[] args) {
        try {
        	final LoadSplash window = new LoadSplash("WaxLib\\WaxLibrary\\SplashScreen\\splash.jpg");
        	
        	window.setVersionNumber("Version 4.3");
            window.setVisible(true);
            int value = 0;
            
            for (int i =0;i<20;i++)
            {
              value +=5;
              window.set_ProgressBarValue(value);
               Thread.sleep(500);
            }
            window.setVisible(false);
          //  System.err.println("I wait fadeout ");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      //  System.err.println("I got out ");
        System.exit(-1);
    }
	
	private void drawTextHighlight(Graphics2D g2,FontMetrics metrique, String title,int size, float opacity, int offsx, int offsy, boolean centered) {
			
			Composite old = g2.getComposite();
			
			
			int MX =0;
			int MY =0;
			if (centered)
			{
				
				Rectangle2D r2d = metrique.getStringBounds(title, g2);
			 MX = (int) (-r2d.getWidth()/2);
			 MY = (int) (-r2d.getHeight()/2);
			}
			
			for (int i = -size; i <= size; i++) {
			for (int j = -size; j <= size; j++) {
			double distance = i * i + j * j;
			float alpha = opacity;
			if (distance > 0.0d) {
			alpha = (float) (1.0f /
			((distance * size) * opacity));
			}
			//g2.setComposite(AlphaComposite.SrcOver.derive(alpha));
			g2.drawString(title, i + size+offsx+MX, j + size+ offsy+MY);
			}
			}
			g2.setComposite(old);
			
		
			
			}
	
}


