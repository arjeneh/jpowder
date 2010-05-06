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
 * Contributor(s):   ;
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.dataset;

import java.util.Vector;
import org.jpowder.util.VectorMiscUtil;

/**
 * Hold a powder diffraction dataset that contains errors, i.e. a powder
 * diffraction dataset with the xye format
 */
public class DataSetWithErrors extends DataSet {

    private Vector<Double> e;
    private Vector<Double> yLower;
    private Vector<Double> yUpper;

    public DataSetWithErrors(Vector data, String fileName) {
        super(data, fileName);

        this.e = VectorMiscUtil.getColumn(data, 2);

        this.yLower = new Vector<Double>();
        this.yUpper = new Vector<Double>();

        Vector twoColumn = VectorMiscUtil.copyBeforeLastColumnsOf2DVector(data);
        Vector outputOfMinusAdd = VectorMiscUtil.do_Minus_Addition_Y(twoColumn, this.e);
        Vector result = VectorMiscUtil.getResultOfAddingTwoVectors(twoColumn, outputOfMinusAdd);

        for (int rowIndex = 0; rowIndex < result.size(); rowIndex++) {
            Vector row = (Vector) result.elementAt(rowIndex);
            Double minusY = Double.parseDouble(row.elementAt(2).toString());
            yLower.add(minusY);
            Double plusY = Double.parseDouble(row.elementAt(3).toString());
            yUpper.add(plusY);
        }

    }

    @Override
    /// Do we really need this method
    public String description() {
        return "DataSetNoErrors contains powder data with no errors";
    }

    public Vector<Double> getE() {
        return e;
    }

    public void setE(Vector<Double> e) {
        this.e = e;
    }

    public Vector<Double> getYLower() {
        return yLower;
    }

    public void setYLower(Vector<Double> yLower) {
        this.yLower = yLower;
    }

    public Vector<Double> getYUpper() {
        return yUpper;
    }

    public void setYUpper(Vector yUpper) {
        this.yUpper = yUpper;
    }
}

