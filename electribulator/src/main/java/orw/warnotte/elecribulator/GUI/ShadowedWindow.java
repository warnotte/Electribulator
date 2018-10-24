
package orw.warnotte.elecribulator.GUI;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import javax.swing.ImageIcon;
import javax.swing.JWindow;
import javax.swing.Timer;

public class ShadowedWindow extends JWindow implements MouseListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3074181409980160036L;
	protected static BufferedImage splash_orig = null;
	protected static BufferedImage splash = null;
	private BufferedImage image_source = null;

	public ShadowedWindow(BufferedImage image) {
		image_source = image;
    	createShadowPicture(image);
	    this.addMouseListener(this);
	    setBlur(0.5f);
    }
	
	public ShadowedWindow(String name) {
		this(name, true);
		setBlur(0.5f);
    }
	
	public ShadowedWindow(String name, boolean create_Shadow) {
    	this.addMouseListener(this);
	//	try {
			  ImageIcon icon = new ImageIcon(name);
			  int w = icon.getIconWidth();
			  int h = icon.getIconHeight();
			  image_source = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			  Graphics2D imageGraphics = image_source.createGraphics();
			  imageGraphics.drawImage(icon.getImage(), 0, 0, null);
			  
			//image_source = ImageIO.read(ClassLoader.getSystemResourceAsStream(name));
	/*	} catch (IOException e) {
			e.printStackTrace();
		}*/
			  createShadowPicture(image_source,create_Shadow);
    }
    
	public void refresh_Background()
	{
		super.setVisible(false);
	//	WindowUtils.setWindowAlpha(this,(float)0);
		createShadowPicture(image_source,true);
	//	WindowUtils.setWindowAlpha(this,(float)100);
		super.setVisible(true);
	}
    
    
    public void createShadowPicture()
    {
    //	this.setVisible(false);
    	System.err.println("Createshadow()");
    	createShadowPicture(image_source);
    //	this.setVisible(true);
    }
    

    @Override
	public void paint(Graphics g) {
        if (splash != null) {
        	 g.drawImage(splash_orig, 0, 0, null);
        }
    }
    
    private void createShadowPicture(BufferedImage image)
    {
    	createShadowPicture(image, true);
    }
    
    private void createShadowPicture(BufferedImage image, boolean createShadow) {
        int width = image.getWidth();
        int height = image.getHeight();
        float multiplier = 2;
        int extra = (int) (14*multiplier);

        setSize(new Dimension(width + extra, height + extra));
        setLocationRelativeTo(null);
        Rectangle windowRect = getBounds();

        splash_orig = new BufferedImage(width + extra, height + extra, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) splash_orig.getGraphics();

        try {
            Robot robot = new Robot(getGraphicsConfiguration().getDevice());
            BufferedImage capture = robot.createScreenCapture(new Rectangle(windowRect.x, windowRect.y, windowRect.width + extra, windowRect.height + extra));
            g2.drawImage(capture, null, 0, 0);
        } catch (AWTException e) { }

        if (createShadow==true)
        {
        BufferedImage shadow = new BufferedImage(width + extra, height + extra, BufferedImage.TYPE_INT_ARGB); 
        Graphics g = shadow.getGraphics();
        g.setColor(new Color(0.0f, 0.0f, 0.0f, 0.3f));
        g.fillRoundRect((int)(6*multiplier), (int)(6*multiplier), width, height, (int)(12*multiplier), (int)(12*multiplier));

        g2.drawImage(shadow, getBlurOp((int)(7*multiplier)), 0, 0);
    }
        g2.drawImage(image, 0, 0, this);
        g2.dispose();
        
        splash = new BufferedImage(width + extra, height + extra, BufferedImage.TYPE_INT_RGB);
        Graphics2D imageGraphics = splash.createGraphics();
		imageGraphics.drawImage(splash_orig, 0,0, this);		
		
        
    }

    private ConvolveOp getBlurOp(int size) {
        float[] data = new float[size * size];
        float value = 1 / (float) (size * size);
        for (int i = 0; i < data.length; i++) {
            data[i] = value;
        }
        return new ConvolveOp(new Kernel(size, size, data));
    }
    
    public static BufferedImage Blur(float val, BufferedImage bufferedImage)
    {
    float[ ] matrice = {
            0.1f*val, 0.1f*val, 0.1f*val,
            0.1f*val, 0.2f*val, 0.1f*val,
            0.1f*val, 0.1f*val, 0.1f*val
            };
    BufferedImageOp op = new ConvolveOp(new Kernel(3,3,matrice));
    BufferedImage nouvelleImage = op.filter(bufferedImage, null);
    return nouvelleImage;
    }

    public void setBlur(float val)
    {
    	if (val==0.0) val = 0.000001f;
    	if (image_source.getWidth()!=-1)
    	{
    		System.err.println("Blur set to "+val);
    		splash_orig=Blur(val, image_source);
    	}
    	repaint();
    	
    }
    
    public static void main(String[] args) {
       // try {
        	//BufferedImage image = ImageIO.read(ShadowedWindow.class.getResourceAsStream("splash.jpg"));
            final ShadowedWindow window = new ShadowedWindow("splash.jpg");
            window.setVisible(true);
            Timer timer = new Timer(1500, new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                	window.setVisible(false);
                	//System.exit(0);
                }
            });
            timer.start();
            
        
            
           
              
       
    }
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mouseReleased(MouseEvent arg0) {
	//	this.setVisible(false);
	}
}
