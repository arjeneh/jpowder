/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.jfreechart;

import java.util.Vector;
import org.jfree.data.xy.AbstractIntervalXYDataset;
import org.jpowder.dataset.DataSetWithErrors;

/**
 *
 * @author ajm64
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
