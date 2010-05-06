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
 * Axis.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Original Author:  Kreecha Puphaiboon
 * Contributor(s):   M Arjeneh;
 *                   
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
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

