package orw.warnotte.elecribulator.GUI;


import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Classe qui va contenir des dialog divers telles que le SaveAs bien connu ou
 * le Open
 * 
 * @author Warnotte Renaud.
 */
public class DialogDivers
{

	public static File LoadDialog(JFrame frame, String extension)
	{
		return LoadDialog(frame, extension, "./");
	}

	public static File LoadDialog(JFrame frame, String extension, String directory)
	{
		return LoadDialog(frame, extension, directory, "Chargement d'un fichier *." + extension);
	}

	public static File LoadDialog(JFrame frame, String extension, String directory, String title)
	{
		File selFile = null;
		FileFiltre mfi = new FileFiltre(new String[] { extension }, "les fichiers (*." + extension + ")");

		JFileChooser fc = new JFileChooser(directory);
		fc.setDialogTitle(title);
		fc.addChoosableFileFilter(mfi);

		// Show open dialog; this method does not return until the dialog is
		// closed
		fc.showOpenDialog(frame);
		selFile = fc.getSelectedFile();
		if (selFile != null)
		{
			return selFile;
		}

		return null;
	}

	public static String SaveDialog(JFrame frame, String extension)
	{
		return SaveDialog(frame, extension, "./", "Sauvegarde sous");
	}

	/*
	 * Offre une boite de dialogue sauvegarder sous avec demande d'ecrasement
	 * @param extension
	 * @param frame
	 * @return NULL ou un nom de fichier avec son path complet
	 */
	public static String SaveDialog(JFrame frame, String extension, String directory, String title)
	{
		File selFile = null;
		JFileChooser fc = new JFileChooser(directory);
		FileFiltre mfi = new FileFiltre(new String[] { extension }, "*." + extension);
		fc.addChoosableFileFilter(mfi);
		fc.setDialogTitle(title);

		// Show open dialog; this method does not return until the dialog is
		// closed
		fc.showSaveDialog(frame);
		selFile = fc.getSelectedFile();

		if (selFile != null)
		{
			try
			{
				String str = selFile.getPath();
				if (str.toLowerCase().endsWith(extension) == false)
					str = str + "." + extension;
				// Verifier que ca existe pas encore
				File f = new File(str);
				// int check =1;

				if (f.exists() == true)
				{

					String message = "Voulez-vous écraser le fichier déja existant (" + str + ") avant ?";
					int answer = JOptionPane.showConfirmDialog(frame, message);
					if (answer == JOptionPane.YES_OPTION)
					{
						return f.getAbsoluteFile().toString();
					} else if (answer == JOptionPane.NO_OPTION)
					{
						return null;
					} else if (answer == JOptionPane.CANCEL_OPTION)
					{
						return null;
					}
				}
				return f.getAbsoluteFile().toString();

			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void Show_dialog(Exception e, String dialog)
	{
		Show_dialog(e, dialog, new JFrame());
	}

	public static void Show_dialog(Exception e, String dialog, JFrame frame)
	{
		if (frame == null)
			frame = new JFrame();
		// frame.setAlwaysOnTop(true);
		// frame.setVisible(true);
		// center(frame);
		if (e == null)
			JOptionPane.showMessageDialog(frame, dialog, dialog, 1);
		else
			JOptionPane.showMessageDialog(frame, dialog + "\r\n\r\nException :" + e, dialog, 0); //$NON-NLS-1$
	}

	/**
	 * @param frame
	 */
	public static void center(JFrame frame)
	{
		// Semble déconner.
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Point center = ge.getCenterPoint();
		// Rectangle bounds = ge.getMaximumWindowBounds();
		int w = frame.getWidth();
		int h = frame.getHeight();
		int x = center.x - w / 2, y = center.y - h / 2;
		frame.setLocation(x, y);
		frame.validate();

	}

	/**
	 * Return true if yes.
	 * 
	 * @param string
	 * @return
	 */
	public static boolean Show_YesNoDialog(String string)
	{
		JFrame frame = new JFrame();
		// frame.setAlwaysOnTop(true);
		// frame.setVisible(true);
		// center(frame);
		int value = JOptionPane.showConfirmDialog(frame, string);
		if (value == JOptionPane.YES_OPTION)
			return true;

		return false;
	}

	public static void Show_OkDialog(String string)
	{
		Object[] options = { "OK" };
		JOptionPane.showOptionDialog(null, string, "Success", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

	}

	public static void Show_ErrorDialog(String string)
	{
		Object[] options = { "Doh!" };
		JOptionPane.showOptionDialog(null, string, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
	}
}
