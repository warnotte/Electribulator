package  org.warnotte.elecribulator.GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JWindow;

/** KOPKOK simple Splash screen. */
public class Splash extends JWindow implements WindowFocusListener, MouseListener{
  /**
     * 
     */

    private static final long serialVersionUID = 1L;
    protected ImageIcon im;

    
public Splash(JFrame f, String progName, String fileName) {
    super();
    // Can't use Swing border on JWindow: not a JComponent.
    // setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    im = new ImageIcon(fileName);
    this.addMouseListener(this);
    this.setAlwaysOnTop(true);
    if (im.getImageLoadStatus() != MediaTracker.COMPLETE)
      JOptionPane.showMessageDialog(f,
        "Warning: can't load image " + fileName + "\n" +
        "Please be sure you have installed " + progName + " correctly",
        "Warning",
        JOptionPane.WARNING_MESSAGE);
    int w = im.getIconWidth(), h = im.getIconHeight();
    setSize(w, h);
    UtilGUI.center(this);
   

  }

public void windowGainedFocus(WindowEvent e){}
public void windowLostFocus(WindowEvent e)
{
setVisible(false);
System.out.println("wat");
}
public Splash(JFrame f, String progName, ImageIcon mm) {
    super();
    this.addMouseListener(this);
    // Can't use Swing border on JWindow: not a JComponent.
    // setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    im = mm;
    this.setAlwaysOnTop(true);
    

    if (im.getImageLoadStatus() != MediaTracker.COMPLETE)
      JOptionPane.showMessageDialog(f,
        "Warning: ",
        "Warning",
        JOptionPane.WARNING_MESSAGE);
    int w = im.getIconWidth(), h = im.getIconHeight();
    setSize(w, h);
    UtilGUI.center(this);
   

  }


@Override
public void paint(Graphics g) {
    im.paintIcon(this, g, 0, 0);
    g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 7, 7);
  }

public void mouseClicked(MouseEvent arg0) {
	
}

public void mouseEntered(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

public void mousePressed(MouseEvent arg0) {
	this.setVisible(false);
	
}

public void mouseReleased(MouseEvent arg0) {
	this.setVisible(false);
	
}


}
class UtilGUI {

  /** Centre a Window, Frame, JFrame, Dialog, etc. */
  public static void centre(Window w) {
    // After packing a Frame or Dialog, centre it on the screen.
    Dimension us = w.getSize(), them = Toolkit.getDefaultToolkit()
        .getScreenSize();
    int newX = (them.width - us.width) / 2;
    int newY = (them.height - us.height) / 2;
    w.setLocation(newX, newY);
  }

  /**
   * Center a Window, Frame, JFrame, Dialog, etc., but do it the American
   * Spelling Way :-)
   */
  public static void center(Window w) {
    UtilGUI.centre(w);
  }

  /** Maximize a window, the hard way. */
  public static void maximize(Window w) {
	 // Dimension us = w.getSize();
	  Dimension them = Toolkit.getDefaultToolkit().getScreenSize();
    w.setBounds(0, 0, them.width, them.height);
  }
}