package org.jpowder.dataset;

import java.util.Iterator;
import java.util.Vector;
import org.jpowder.util.VectorMiscUtil;


/**
 * Base class for holding the content of a powder diffraction file
 *
 *
 */
public abstract  class DataSet
{

    private Vector x;
    private Vector y;
    
    public boolean hasErrorbars = false;

    private String fileName;
  

    //@param $data: recieve data to plot as 2D vector.
    //@param $fileName: recieve the file name.
    public DataSet(Vector<Vector> data, String filename) {
        this.fileName = filename;
      //  this.data = data;
        //obtain data and assign x and y values to this class
        this.x = VectorMiscUtil.getColumn(data, 0);
        this.y = VectorMiscUtil.getColumn(data, 1);
    }

    public DataSet() {
    }

    public void addDataset(DataSet dataset) {
    }

    public Iterator iterator() {
        return null;
    }

    public abstract String description();

 

    public String getFileName() {
        return this.fileName;
    }

    public Vector getX() {
        return x;
    }

    public void setX(Vector x) {
        this.x = x;
    }

    public Vector getY() {
        return y;
    }

    public void setY(Vector y) {
        this.y = y;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

  
}

