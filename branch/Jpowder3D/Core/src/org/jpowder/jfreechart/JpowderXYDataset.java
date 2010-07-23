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
 * JpowderXYDataset.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  Anders Marvardsen, ISIS, Rutherford Appleton Laboratory
 *             M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *                   
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.jfreechart;

import java.util.Vector;
import org.jfree.data.xy.AbstractXYDataset;
import org.jpowder.dataset.DataSet;

/**
 *
 * 
 */
public class JpowderXYDataset extends AbstractXYDataset {

    private Vector<Double> x;
    private Vector<Double> y;


    public JpowderXYDataset(Vector<Double> x, Vector<Double> y) {
        this.x = x;
        this.y = y;
    }

    public JpowderXYDataset(DataSet dataset) {
        this.x = dataset.getX();
        this.y = dataset.getY();


    }

    /// Returns the x-value. This method relies on the getX() method being implemented.
    @Override
    public double getXValue(int series, int item) {
        return this.x.elementAt(item);

    }

    /// Returns the y-value. If the value is missing or unknown, this method will return Double.NaN.
    @Override
    public double getYValue(int series, int item) {
        return this.y.elementAt(item);

    }

    @Override
    public int getSeriesCount() {
        return 1;
    }

    @Override
    ///To get the key (unique identifier) for a series
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

    /// Returns the x-value for an item within a series (never null). Some implementations will create
    /// a new Number instance every time this method is called, so it is usually more efficient to call
    /// getXValue(series, item) instead.
    public Number getX(int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Number getY(int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /*
    public static void main(String[] args) {

    Vector<Double> x = new Vector<Double>();
    Vector<Double> y = new Vector<Double>();

    x.addElement(0.0);
    x.addElement(5.0);
    x.addElement(20.0);

    y.addElement(0.0);
    y.addElement(1.0);
    y.addElement(2.0);

    JpowderXYDataset data = new JpowderXYDataset(x,y);
    JFreeChart chart = ChartFactory.createXYLineChart(
    "Cast Vector to XY Series Demo",
    "X",
    "Y",
    data,
    PlotOrientation.VERTICAL,
    true,
    true,
    false
    );

    ChartFrame frame = new ChartFrame("Cast Vector to XY Series Demo", chart);
    frame.pack();
    frame.setVisible(true);

    y.setElementAt(0.5, 2);


    }
     */
}
