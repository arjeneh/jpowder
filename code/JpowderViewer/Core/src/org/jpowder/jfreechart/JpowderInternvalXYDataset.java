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
 * JpowderInternvalXYDataset.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  Anders Marvardsen, ISIS, Rutherford Appleton Laboratory
 *             M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *      
 *                 
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.jfreechart;

import java.util.Vector;
import org.jfree.data.xy.AbstractIntervalXYDataset;
import org.jpowder.dataset.DataSetWithErrors;

/**
 *
 * 
 */
public class JpowderInternvalXYDataset extends AbstractIntervalXYDataset {

    private Vector<Double> x;
    private Vector<Double> y;
    private Vector<Double> e;
    //private Vector<Double> yLower;
    //private Vector<Double> yUpper;

    public JpowderInternvalXYDataset(DataSetWithErrors dataset) {
        this.x = dataset.getX();
        this.y = dataset.getY();
        this.e = dataset.getE();
        // this.yLower=dataset.getYLower();
        // this.yUpper=dataset.getYUpper();
    }

    @Override
    public double getStartXValue(int series, int item) {
        return this.x.elementAt(item);

    }
    // Returns the end value for the x-interval.

    @Override
    public double getEndXValue(int series, int item) {
        return this.x.elementAt(item);
    }

    /// Returns the start value for the y-interval.
    @Override
    public double getStartYValue(int series, int item) {
        return this.y.elementAt(item) - this.e.elementAt(item);
    }

    @Override
    public double getEndYValue(int series, int item) {
        return this.y.elementAt(item) + this.e.elementAt(item);
    }

    @Override
    public int getSeriesCount() {
        return 1;
    }

    @Override
    public Comparable getSeriesKey(int i) {
        Comparable retval = new Comparable() {

            public int compareTo(Object o) {
                return 0;
            }
        };
        return retval;
    }

    public int getItemCount(int i) {
        return this.x.size();
    }

    public Number getX(int series, int item) {
        return this.x.elementAt(item);
    }

    public Number getY(int series, int item) {
        return this.y.elementAt(item);
    }

    public Number getStartX(int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Number getEndX(int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Number getStartY(int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Number getEndY(int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
