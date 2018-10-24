package org.warnotte.elecribulator.GUI;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FileFiltre extends FileFilter {

	String[] lesSuffixes;
	String laDescription;

	public FileFiltre(String[] lesSuffixes, String laDescription) {

		this.lesSuffixes = lesSuffixes;
		this.laDescription = laDescription;

	}

	boolean appartient(String suffixe) {

		for (int i = 0; i < lesSuffixes.length; ++i) {

			if (suffixe.endsWith(lesSuffixes[i]))//||
				 //(suffixe.contains(lesSuffixes[i])))
				return true;

		}

		return false;

	}

	@Override
	public boolean accept(File f) {

		if (f.isDirectory()) {

			return true;

		}

		String suffixe = null;

		String s = f.getName();

		int i = s.indexOf('.');

		if (i > 0 && i < s.length() - 1) {

			suffixe = s.substring(i + 1).toLowerCase();

		}

		return suffixe != null && appartient(suffixe);

	}

	// la description du filtre

	@Override
	public String getDescription() {

		return laDescription;

	}

}