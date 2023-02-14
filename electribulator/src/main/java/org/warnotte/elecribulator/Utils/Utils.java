package org.warnotte.elecribulator.Utils;

import java.util.Collection;

import org.warnotte.elecribulator.CControlers.CCManager;
import org.warnotte.elecribulator.DTOs.Projet;
import org.warnotte.elecribulator.PresetManager.ControlValue;
import org.warnotte.waxaudiomiditools.CControlers.SignGen_VCA;
import org.warnotte.waxaudiomiditools.CControlers.SignGen_VCA_2ND;
import org.warnotte.waxaudiomiditools.CControlers.SignGen_VCO;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

public class Utils
{
	static XStream xstream;
	
	public static XStream getXStreamInstance()
	{
		if (xstream==null)
		{
			xstream = new XStream(new DomDriver());
			
			xstream.autodetectAnnotations(true);
			// clear out existing permissions and set own ones
			xstream.addPermission(NoTypePermission.NONE);
			xstream.addPermission(AnyTypePermission.ANY);
			// NOT GOOD IDEA ...
			// allow some basics
			xstream.addPermission(NullPermission.NULL);
			xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
			xstream.allowTypeHierarchy(Collection.class);
			// allow any type from the same package
			xstream.allowTypesByWildcard(new String[] {
					"org.warnotte.electribulator.**",
					"org.warnotte.electribulator.PresetManager.**",
					"io.github.warnotte.waxlib3.waxlibswingcomponents.Utils.**"
			});
			
			xstream.allowTypes(new Class[] {
					io.github.warnotte.waxlib3.waxlibswingcomponents.Utils.Curve.Vca_Preset.class,
					io.github.warnotte.waxlib3.waxlibswingcomponents.Utils.Curve.Vca_Preset.class
					    });
			
			// 
			
			
			
			// Ajout pour passer de la version 0.2.4 ou 0.2.5 j'sais plus a la 0.5.0 mavenifié etc etc etc...
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
