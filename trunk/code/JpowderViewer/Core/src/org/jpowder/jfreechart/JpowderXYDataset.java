/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jpowder.jfreechart;

import java.util.Vector;
import org.jfree.data.xy.AbstractXYDataset;
import org.jpowder.dataset.DataSet;
import org.jpowder.dataset.DataSetWithErrors;

/**
 *
 * @author ajm64
 */
public class JpowderXYDataset extends AbstractXYDataset {

  private Vector<Double> x;
  private Vector<Double> y;

    public JpowderXYDataset(Vector<Double> x, Vector<Double> y) {
        this.x=x;
        this.y=y;
    }

    public JpowderXYDataset(DataSet dataset) {
        this.x=dataset.getX();
        this.y=dataset.getY();


    }


  /// Returns the x-value. This method relies on the getX() method being implemented.
  @Override
  public double getXValue(int series, int item)
  {
      return this.x.elementAt(item);

  }

  /// Returns the y-value. If the value is missing or unknown, this method will return Double.NaN.
  @Override
  public double getYValue(int series, int item)
  {
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


/**
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
