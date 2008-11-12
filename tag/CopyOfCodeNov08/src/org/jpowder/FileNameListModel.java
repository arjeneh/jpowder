package org.jpowder;

import org.jpowder.JCheckboxList.CheckableFileItem;
import org.jpowder.fileCabinet.Subject;
import org.jpowder.fileCabinet.PowderFileCabinet;
import org.jpowder.fileCabinet.PowderFileObserver;

/**
 * This class assumes that the string used to initialize
 * fullPath has a directory path, filename, and extension.
 * The methods won't work if it doesn't match.
 */
public class FileNameListModel extends javax.swing.DefaultListModel implements PowderFileObserver {
    
    private java.util.Vector<CheckableFileItem> fileNameModelVec;

    //private java.util.Vector<String> fileNameModelVec;

    public FileNameListModel() {
        //this.fileNameModelVec = new java.util.Vector<String>();
        this.fileNameModelVec = new java.util.Vector<CheckableFileItem>();
    }

    @Override
    public int getSize() {
        return fileNameModelVec.size();
    }

    @Override
    public Object getElementAt(int index) {
        return fileNameModelVec.toArray()[index];
    }

    public void addCheckableFile(Object element) {
        
        //CheckableFileItem c = (CheckableFileItem) element;
        if (fileNameModelVec.add( (CheckableFileItem) element )) {
            fireContentsChanged(this, 0, getSize());
        }
        
        /*if (fileNameModelVec.add((String) element)) {
            fireContentsChanged(this, 0, getSize());
        }*/
    }

    public void addAll(Object elements[]) {
        java.util.Collection c = java.util.Arrays.asList(elements);
        fileNameModelVec.addAll(c);
        fireContentsChanged(this, 0, getSize());
    }

    //Clear and update changes of of file names.
    @Override
    public void clear() {
        fileNameModelVec.clear();
        fireContentsChanged(this, 0, getSize());
    }

    @Override
    public boolean contains(Object element) {
        return fileNameModelVec.contains(element);
    }

    @Override
    public Object firstElement() {
        return fileNameModelVec.firstElement();
    }

    public java.util.Iterator iterator() {
        return fileNameModelVec.iterator();
    }

    @Override
    public Object lastElement() {
        return fileNameModelVec.lastElement();
    }

    @Override
    public boolean removeElement(Object element) {
        boolean removed = fileNameModelVec.remove(element);
        if (removed) {
            fireContentsChanged(this, 0, getSize());
        }
        return removed;
    }

    public void powderFileCabinetUpdate(Subject data) {

        PowderFileCabinet pfc = (PowderFileCabinet) data;
        System.out.println("From FileNameListModel.java PowderFileCabinet is updated as " + pfc.getData().size());

        java.util.HashMap hm = pfc.getData();
        //clear
        this.clear();

        //To get all keys stored in HashMap use keySet method. 
        //Signature of the keysSet method is, Set keySet()
        java.util.Iterator iterator = hm.keySet().iterator();
        while (iterator.hasNext()) {
            CheckableFileItem a = new CheckableFileItem((String) iterator.next() );
            addCheckableFile(a);
            //add((String) iterator.next());
        }
    }
    /*
    
    To get all values stored in HashMap use entrySet() method. 
    Signature of the entrySet() method is, Set entrySet()
    
    System.out.println("Retriving all values from the HashMap");
    
    iterator = hashMap.entrySet().iterator();
    while( iterator. hasNext() ){
    System.out.println( iterator.next() );
    }*/
    
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
}
