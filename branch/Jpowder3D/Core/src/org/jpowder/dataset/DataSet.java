/* ===========================================================
 * This file is part of Jpowder, see <http://www.jpowder.org/>
 * ===========================================================
 *
 * Jpowder is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jpowder is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * ---------
 * DataSet.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  Kreecha Puphaiboon, Computer Science Lecturer, Kasem Bundit University
 *             M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.dataset;

import java.util.HashMap;
import java.util.Vector;
import org.jpowder.util.VectorMiscUtil;

/**
 * Base class for holding the content of a powder diffraction file
 *
 */
public abstract class DataSet {

    private Vector<Double> x;
    private String xUnit = "2\u03D1"; // other units "TOF" etc
    private Vector<Double> y;
    private String fileName;
    private double waveLength;
    private GSAS_Instrument gsasInst = null;
    private boolean isTOF = false;
    private Vector<Double> z;
    // holds meta data for the DataSet in pairs
    // of name of meta-data-item, e.g. temperature, and
    // its value
    private HashMap<String, Double> meta;
    //private HashMap<String, String> metaString;

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

    public void setZ(Vector<Double> z) {
        this.z = z;
    }

    public Vector<Double> getZ() {
        return z;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public double getWaveLength() {
        return waveLength;
    }

    public void setWaveLength(double waveLength) {
        this.waveLength = waveLength;
    }

    public String getXUnit() {
        return xUnit;
    }

    public GSAS_Instrument getGSAS_Instrument() {
        return gsasInst;
    }

    public void setGSAS_Instrument(GSAS_Instrument inst) {
        gsasInst = inst;
    }

    public void setXUnit(String xunit) {
        this.xUnit = xunit;
    }

    public void addMetaData(HashMap<String, Double> meta) {
        this.meta = meta;

    }

    public double getMetaData(String name) {
        double data = meta.get(name);
        return data;
    }

//    public static void main(String[] args) {
//       DataSet ds = new DataSet() {
//
//            @Override
//            public String description() {
//                throw new UnsupportedOperationException("Not supported yet.");
//            }
//        };
//    }
//
//    public DataSet() {
//        HashMap<String, Double> hm = new HashMap<String, Double>();
//        hm.put("A", new Double(3.34));
//        hm.put("B", new Double(1.22));
//        hm.put("C", new Double(1.00));
//        addMetaData(hm);
////        getMetaData("C");
//        System.out.println(getMetaData("B"));
//
//    }
}

