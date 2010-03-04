package org.jpowder.JCheckboxList;

/**
*
*Class name: FileNameListModel.java
*@author: Kreecha Puphaiboon
*@date: 28/11/08
*Used in the FileChooserPanel.java to display dataset files in the program.
*It presents as a list model also updates when the file is deleted and inserted.
*This class assumes that the string used to initialize
*fullPath has a directory path, filename, and extension.
*The methods won't work if it doesn't match.
*/


 public class FileNameListModel extends javax.swing.DefaultListModel {
    
    private java.util.Vector<CheckableFileItem> fileNameModelVec;

    //public javax.swing.JPanel jfreeChartPanel;
   // public XYPlot m_plot = null;


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
/*
    @Override
    public boolean contains(Object element) {
        return fileNameModelVec.contains(element);
    }
*/
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


}
