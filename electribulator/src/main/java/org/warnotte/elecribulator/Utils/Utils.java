package org.warnotte.elecribulator.Utils;

import org.warnotte.elecribulator.CControlers.CCManager;
import org.warnotte.elecribulator.DTOs.Projet;
import org.warnotte.elecribulator.PresetManager.ControlValue;
import org.warnotte.waxaudiomiditools.CControlers.SignGen_VCA;
import org.warnotte.waxaudiomiditools.CControlers.SignGen_VCA_2ND;
import org.warnotte.waxaudiomiditools.CControlers.SignGen_VCO;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Utils
{
	static XStream xstream;
	
	public static XStream getXStreamInstance()
	{
		if (xstream==null)
		{
			xstream = new XStream(new DomDriver());
			// Ajout pour passer de la version 0.2.4 ou 0.2.5 j'sais plus a la 0.5.0 mavenifi� etc etc etc...
			xstream.alias("PresetManager.ControlValue", ControlValue.class);
			xstream.alias("CControlers.CCManager", CCManager.class);
			xstream.alias("CControlers.SignGen_VCA_2ND", SignGen_VCA_2ND.class);
			xstream.alias("CControlers.SignGen_VCA", SignGen_VCA.class);
			xstream.alias("CControlers.SignGen_VCO", SignGen_VCO.class);
			xstream.alias("DTOs.Projet", Projet.class);
			
		}
		return xstream;
	}
	
}
