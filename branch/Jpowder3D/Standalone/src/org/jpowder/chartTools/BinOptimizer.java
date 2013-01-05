package org.jpowder.chartTools;

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
 * BinOptimizer.java
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.GrayPaintScale;
import org.jfree.chart.renderer.PaintScale;
import org.jfree.data.xy.XYDataset;
import org.jpowder.InternalFrame.JpowderInternalframe3D;
import org.jpowder.dataset.DataSet;
import org.jpowder.jfreechart.JpowderXYBlockRenderer;
import org.jpowder.util.VectorMiscUtil;

public class BinOptimizer {

    private JpowderInternalframe3D internal3dFrame;
    private JFreeChart jFreeChart;
    private int factor;
    private ChartPanel panel;

    public BinOptimizer(JFreeChart jf) {
        this.jFreeChart = jf;
    }

    public BinOptimizer(JFreeChart jf, String text) {
        this.jFreeChart = jf;
        this.factor = Integer.parseInt(text);
    }

    public BinOptimizer(String text) {
        this.factor = Integer.parseInt(text);
    }

    public BinOptimizer(ChartPanel chartPanel, String text) {
        this.panel = chartPanel;
        this.factor = Integer.parseInt(text);
    }

    public BinOptimizer(int selectedValue) {
        this.factor = selectedValue;
    }

    private BinOptimizer() {
    }

    /**
     * 
     * @param frame
     */
    public void execute(JpowderInternalframe3D frame) {
 
        // 1. get the existing data.
        this.internal3dFrame = frame;

        int dSize = this.internal3dFrame.getPowderDataSet().size();
        //System.out.println("in " + this.getClass().getName() + " size = " + dSize);
        Vector<Vector<Double>> binData = new Vector<Vector<Double>>();

        // 2. Find newly Binning value.
        for (int i = 0; i < dSize; i++) {
            Vector<Vector> original = this.internal3dFrame.getPowderDataSet().elementAt(i).getData();
            Vector<Vector> copy = VectorMiscUtil.copyBeforeLastColumnsOf2DVector(original);

            // To store new calculated binning values.
            Vector outCome = new Vector(binVector(copy, this.factor));
            //System.out.println("Outcome " + outCome);
            for (int x = 0; x < outCome.size(); x++) {
                binData.add((Vector<Double>) outCome.get(x));
            }
        }

        System.out.println("in " + this.getClass().getName() + " with factor of " + factor + " binData =  " + binData);
        System.out.println("Bin size = " + binData.size());

        //Loop for each file dataset
        for (int y = 0; y < dSize; y++) {
            //Create a new Dataset by Copying the old one
            //and modify with the new rebinning data
            DataSet ds = this.internal3dFrame.getPowderDataSet().elementAt(y);
            System.out.println("in " + this.getClass().getName() + " DataSet " + y + " size =  "  + ds.getData());
            System.out.println("in " + this.getClass().getName() + " DataSet size =  " + ds.getData().size());
            
            //Replace original data values with newly rebinned values
            int dsSize = ds.getData().size();
            for (int ii = 0; ii < dsSize; ii++) {
                Vector v = ds.getData();
                //this.internal3dFrame.getPowderDataSet().elementAt(ii).getY().setElementAt(yNew.get(ii), ii);
            }
        }

        //this.internal3dFrame.setPowderDataSet(binData);
    }

    /**
     *
     * @param theArray
     * @param theBinningNumber
     * @return
     */
    public static String[][] binArray(String[][] theArray, int theBinningNumber) {
        //Determine the size (length) of new array
        int newSize = theArray.length / theBinningNumber;
        if (theArray.length % theBinningNumber != 0) {
            newSize++;
        }

        //Define new array
        String[][] newArray = new String[newSize][];
        int theNewIndex = 0;

        for (int index = 0; index < theArray.length; index += theBinningNumber) {
            String[] ar = new String[]{"", ""};
            double value = 0;
            for (int binIndex = index;
                    binIndex < (index + theBinningNumber) && binIndex < theArray.length;
                    binIndex++) {
                value = value + Double.parseDouble(theArray[binIndex][1]);
                ar[0] = ar[0] + " " + theArray[binIndex][0];
                ar[1] = String.valueOf(value);
            }
            newArray[theNewIndex++] = ar;
        }
        return newArray;
    }

    /**
     *
     * @param theArray
     * @param theBinningNumber
     * @param a for testing
     * @return
     */
    public Vector<Vector> binVector(Vector<Vector> theArray, int theBinningNumber) {

        //Define new array
        Vector<Vector> newArray = new Vector<Vector>();

        for (int row = 0; row < theArray.size(); row += theBinningNumber) {
            Vector vRow = (Vector) (theArray.elementAt(row));
            newArray.add(vRow);
        }
        return newArray;
    }

    public static void main(String[] args) {
        String[][] theArray = {
            {"word1", "3.5"},
            {"word2", "2.4"},
            {"word3", "1.2"},
            {"word4", "0.5"},
            {"word5", "0.2"}
        };

        String newArray[][] = binArray(theArray, 2);

        for (String[] ar : newArray) {
            System.out.println(ar[0] + " " + ar[1]);
        }

        //my test
        BinOptimizer bo = new BinOptimizer();
        Vector input = VectorMiscUtil.initXYData2();
        Vector output = bo.binVector(input, 5);

        for (Object ar : output) {
            System.out.println(ar);
        }
        System.out.println("Output = " + output);
    }

    public static int[] calcHistogram(double[] data, double min, double max, int numBins) {
        //        double[] data = {2, 4, 6, 7, 8, 9};
//        int[] histogram = calcHistogram(data, 0, 10, 4);
//// This is a histogram with 4 bins, 0-2.5, 2.5-5, 5-7.5, 7.5-10.
//        assert histogram[0] == 1; // one point (2) in range 0-2.5
//        assert histogram[1] == 1; // one point (4) in range 2.5-5.

        final int[] result = new int[numBins];

        final double binSize = (max - min) / numBins;

        for (double d : data) {
            int bin = (int) ((d - min) / numBins);

            if (bin < 0) {
                /* this data is smaller than min */
            } else if (bin >= numBins) {
                /* this data point is bigger than max */
            } else {
                result[bin] += 1;
            }
        }
        return result;
    }

    public void convertDatasetToVector() {
//        JpowderInternalframe3D internalframe = new JpowderInternalframe3D(
//                powderFrame.getDataVisibleInChartPanel(), existingDatasets, plotAsFunctionOf);
//        //internalframe.setTitle(powderFrame.getNames());
//
//        Jpowder.updateJPowderInternalFrame(internalframe);
//        //InternalFrameListener internalFrameListener = new InternalFrameIconifyListener(dataVisibleInChart);
//        //internalframe.addInternalFrameListener(internalFrameListener);
//        Jpowder.getChartPlotter3D().add(internalframe);
    }
}


