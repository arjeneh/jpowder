package org.jpowder;

import java.util.Vector;
import javax.swing.DefaultListModel;
/**
 * This class assumes that the string used to initialize
 * fullPath has a directory path, filename, and extension.
 * The methods won't work if it doesn't match.
 */
public class FileNameListModel extends DefaultListModel{
    
    /*private String fullPath;
    private char pathSeparator, extensionSeparator;

    public FileNameListModel(String str, char sep, char ext) {
        fullPath = str;
        pathSeparator = sep;
        extensionSeparator = ext;
    }

    public String extension() {
        int dot = fullPath.lastIndexOf(extensionSeparator);
        return fullPath.substring(dot + 1);
    }

    public String filename() {  // gets filename without extension
        int dot = fullPath.lastIndexOf(extensionSeparator);
        int sep = fullPath.lastIndexOf(pathSeparator);
        return fullPath.substring(sep + 1, dot);
    }

    public String path() {
        int sep = fullPath.lastIndexOf(pathSeparator);
        return fullPath.substring(0, sep);
    }*/
    
    private Vector fileNameVec;

    public FileNameListModel(Vector dumData) {
        fileNameVec = dumData;
    }
}
