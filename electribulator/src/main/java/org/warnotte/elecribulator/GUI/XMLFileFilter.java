package org.warnotte.elecribulator.GUI;

import java.io.File;
import java.io.FilenameFilter;

class XMLFileFilter implements FilenameFilter {
    public boolean accept(File dir, String name) {
        return (name.endsWith(".xml"));
    }
}