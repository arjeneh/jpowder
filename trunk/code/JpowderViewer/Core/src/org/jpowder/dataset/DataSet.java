package org.jpowder.dataset;

import java.util.Vector;
import org.jpowder.util.VectorMiscUtil;

/**
 * Base class for holding the content of a powder diffraction file
 *
 *
 */
public abstract class DataSet {

    private Vector<Double> x;
    private Vector<Double> y;
    private String fileName;
    private double waveLength;

    //@param data: data from a powder diffraction file
    //@param fileName: filename of powder diffraction file.
    public DataSet(Vector<Vector> data, String filename) {
        this.fileName = filename;

        //obtain data and assign x and y values to this class
        this.x = VectorMiscUtil.getColumn(data, 0);
        this.y = VectorMiscUtil.getColumn(data, 1);
    }

    /// Do we really need this method
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
    public double getWaveLength() {
        return waveLength;
    }

    public  void setWaveLength(double waveLength) {
       this.waveLength=waveLength;
    }
}

