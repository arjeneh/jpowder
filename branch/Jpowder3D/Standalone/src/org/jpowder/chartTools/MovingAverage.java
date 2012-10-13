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
 * MovingAverage.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  Kreecha Puphaiboon, Computer Science Lecturer, Kasem Bundit University
 *             Anders Marvardsen, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.chartTools;

import java.util.Collections;
import java.util.Vector;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.GrayPaintScale;
import org.jfree.data.xy.XYDataset;
import org.jpowder.InernalFrame.JpowderInternalframe3D;
import org.jpowder.jfreechart.JpowderXYBlockRenderer;

/**
 * Seperate this class out just in case one day we want different implementation for Smoothing
 * or large dataset and we have issues.
 *
 * @author Kreecha
 */
public class MovingAverage {

    private double average;
    private long count;
    private JpowderInternalframe3D internal3dFrame;
    private int numberOfPointToAVGOver;

    public MovingAverage() {
        count = 0;
        average = 0;
    }

     public MovingAverage(int selectedValue) {
        numberOfPointToAVGOver = selectedValue;
    }


    public MovingAverage(JpowderInternalframe3D jf) {
        count = 0;
        average = 0;
        internal3dFrame = jf;
    }

    public MovingAverage(long count, double average) {
        this.count = count;
        this.average = average;
    }

    public void add(double value) {
        if (count == 0) {
            average = value;
            count++;
        } else {
            average = ((average * count) + value) / ++count;
        }
    }

    public void remove(double value) {
        if (count == 0) {
            throw new AssertionError("no value to remove");
        } else {
            average = ((average * count) - value) / --count;
        }
    }

    public double getAverage() {
        return average;
    }

    public long getCount() {
        return count;
    }

    @Override
    public String toString() {
        return Double.toString(average);
    }

    public void execute(JpowderInternalframe3D frame) {

        // Calculate moving average for each dataset
        for (int i = 0; i < frame.getXYPlot().getDatasetCount(); i++) {
            // get number of data points for this dataset
            int numY = frame.getPowderDataSet().elementAt(i).getY().size();

            int numberOfPointsEitherSize = this.numberOfPointToAVGOver;

            // For convenience store y-values in variable y
            Vector<Double> y = frame.getPowderDataSet().elementAt(i).getY();

            // To store new calculated average values
            Vector<Double> yNew = new Vector<Double>();

            // calculate new average value for each data point
            for (int ii = 0; ii < numY; ii++) {
                Double sum = 0.0;
                // here calculate sum over the neighboring points +- numberOfPointsEitherSize either side of point ii
                // Of course where ii is near an edge this sum will be over fewer points as done below
                for (int jj = Math.max(0, ii - numberOfPointsEitherSize); jj <= Math.min(numY - 1, ii + numberOfPointsEitherSize); jj++) {
                    sum += y.get(jj);
                }
                // add new average value
                yNew.add(sum / numberOfPointsEitherSize);
            }

            // finally replace original data values with newly calculated averaged values
            for (int ii = 0; ii < numY; ii++) {
                frame.getPowderDataSet().elementAt(i).getY().setElementAt(yNew.get(ii), ii);
            }
        }


        XYDataset dataset = frame.getChart().getXYPlot().getDataset();
//        //how many in dataset
        int numDataset = dataset.getSeriesCount();
        System.out.println("XYDataset dataset has " + numDataset + " dataset in it.");


        // Create the lower and upper values for each dataset block height
//        Vector<Double> blockHeigth_minus = new Vector<Double>();
//        Vector<Double> blockHeigth_plus = new Vector<Double>();
//
//        for (int i = 0; i < numDataset; i++) {
//            blockHeigth_minus.add(0.5);
//            blockHeigth_plus.add(0.5);
//        }

        JpowderXYBlockRenderer renderer = (JpowderXYBlockRenderer) frame.getChart().getXYPlot().getRenderer();
//        if (dataset.getSeriesCount() >= 1) {
//            double width1stDataPoint = dataset.getXValue(0, 1) - dataset.getXValue(0, 0);
//            if (width1stDataPoint <= 0.0) {
//                width1stDataPoint = 1.0;
//            }
//            renderer.setBlockWidth(width1stDataPoint);
//        }
//        //renderer.setBlockHeight(blockHeigth_minus, blockHeigth_plus);

        //XYPlot plot = (XYPlot) chart.getPlot();
//        //plot.setDataset(dataset);
//        //plot.setRenderer(renderer);
//
        double maxY = 0;
        double minY = 0;

        XYPlot plot = (XYPlot) frame.getChart().getPlot();

        for (int i = 0; i < plot.getDatasetCount(); i++) {
            maxY = (Double) Collections.max(frame.getPowderDataSet().get(i).getY());
            minY = (Double) Collections.min(frame.getPowderDataSet().get(i).getY());
        }

        //System.out.println("I have " + this.data);
        System.out.println("maxY =  " + maxY + " and minY = " + minY);

        GrayPaintScale colourScale = new GrayPaintScale(minY, maxY);
        renderer.setPaintScale(colourScale);

        //Anders may be we have to do BlockHieght, BlockWidth, blockHeigth_minus, blockHeigth_plus??
        //Now i just default everything as 1 which is why it is wrong i am sure.
        renderer.setBlockHeight(1);
        renderer.setBlockWidth(1);
        renderer.clearSeriesPaints(true);
    }
}
