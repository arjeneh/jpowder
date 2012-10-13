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
 * InternalFrame3D.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.InernalFrame;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.jpowder.*;
import java.util.Vector;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.GrayPaintScale;
import org.jfree.data.xy.XYDataset;
import org.jpowder.Annotation.BalloonFrame;
import org.jpowder.chartTools.MovingAverage;
import org.jpowder.dataset.DataSet;
import org.jpowder.jfreechart.EditAnnotationFrame;
import org.jpowder.jfreechart.FilesPlotter3D;
import org.jpowder.jfreechart.JpowderXYBlockRenderer;
import org.jpowder.jfreechart.PointAnno;

/**
 *
 * Internal Frame 3D
 *
 * This needs to be a listener of DataSetChangeEvent
 */
public class JpowderInternalframe3D extends JpowderInternalframe {

    private JFreeChart chart;
    private String selectedMetaItem;
    private HashMap fileNameAndPath;

    /*
     * @param
     * */
    public JpowderInternalframe3D(DataVisibleInChart dataVisibleInChartPanel,
            Vector<DataSet> data, String selectedMetaItem, HashMap fileNameAndPath) {
        //
        super(dataVisibleInChartPanel, data);

        this.selectedMetaItem = selectedMetaItem;
        this.fileNameAndPath = fileNameAndPath;
        doStuff(this.selectedMetaItem, this.fileNameAndPath);

        chart = FilesPlotter3D.getChart();
    }

    public static int getnumberOfJpowderInternalframe() {
        return Jpowder.getChartPlotter3D().getAllFrames().length;
    }

    public JFreeChart getChart() {
        return chart;
    }

    /**
     *
     * @param frameName the annotation for datasets in the framename only will be displayed.
     *
     * show balloons that have comments of each ones within the dataset.
     */
    public void showAnnotation(final String frameName) {
//        if (this.balloonTips == null) {
//            return;
//        } else {
        Map<Integer, PointAnno> annoMap = EditAnnotationFrame.getInstance().getAnnoMap();
        ArrayList<PointAnno> listAnno = new ArrayList<PointAnno>();

        Set set = annoMap.entrySet(); // Get an iterator
        Iterator it = set.iterator();
        //System.out.println("---------------------------------------------------");

        // Display elements
        while (it.hasNext()) {
            Map.Entry me = (Map.Entry) it.next();
            // System.out.print(me.getKey() + ": "); // System.out.println(" hey " + me.getValue());
            PointAnno p = (PointAnno) me.getValue();

            if (p.getInternalFrameName().equals(frameName)) {
                listAnno.add(p);
                //System.out.println("--------matching added -----------");
            }//if match
        }//while

        //System.out.println("---------------------------------------------------");

        for (int i = 0; i < listAnno.size(); i++) {
            Point p = this.getLocation();
            int oldX = p.x;
            int oldY = p.y;

            PointAnno pa = listAnno.get(i);
            int newX = pa.getMouseX();
            int newY = pa.getMouseY();

            BalloonFrame bf = new BalloonFrame(pa);
            //bf.setUndecorated(true);
            //bf.pack();
            bf.setVisible(true);
            bf.setLocation(oldX + newX, oldY + newY);
        }//for
        //}//if
    }

    /**
     * @return the fileNameAndPath
     */
    public HashMap getFileNameAndPath() {
        return fileNameAndPath;
    }

    /**
     * @param fileNameAndPath the fileNameAndPath to set
     */
    public void setFileNameAndPath(HashMap fileNameAndPath) {
        this.fileNameAndPath = fileNameAndPath;
    }

//    public void setRefreshChart(MovingAverage ma) {
//        ma.execute(this);
//    }


//    public void refreshChart() {
//
//        //get the JFreeChart
////        System.out.println("Hoorey changed " + chart.isNotify());
////        //get the Dataset.
//        XYDataset dataset = chart.getXYPlot().getDataset();
////        //how many in dataset
//        int numDataset = dataset.getSeriesCount();
//        System.out.println("XYDataset dataset has " + numDataset + " dataset in it.");
//
//
//        // Create the lower and upper values for each dataset block height
////        Vector<Double> blockHeigth_minus = new Vector<Double>();
////        Vector<Double> blockHeigth_plus = new Vector<Double>();
////
////        for (int i = 0; i < numDataset; i++) {
////            blockHeigth_minus.add(0.5);
////            blockHeigth_plus.add(0.5);
////        }
//
//        JpowderXYBlockRenderer renderer = (JpowderXYBlockRenderer) chart.getXYPlot().getRenderer();
////        if (dataset.getSeriesCount() >= 1) {
////            double width1stDataPoint = dataset.getXValue(0, 1) - dataset.getXValue(0, 0);
////            if (width1stDataPoint <= 0.0) {
////                width1stDataPoint = 1.0;
////            }
////            renderer.setBlockWidth(width1stDataPoint);
////        }
////        //renderer.setBlockHeight(blockHeigth_minus, blockHeigth_plus);
//
//        //XYPlot plot = (XYPlot) chart.getPlot();
////        //plot.setDataset(dataset);
////        //plot.setRenderer(renderer);
////
//        double maxY = 0;
//        double minY = 0;
//
//        XYPlot plot = (XYPlot) chart.getPlot();
//
//        for (int i = 0; i < plot.getDatasetCount(); i++) {
//            maxY = (Double) Collections.max(super.vectorDatasets.get(i).getY());
//            minY = (Double) Collections.min(super.vectorDatasets.get(i).getY());
//        }
//
//        //System.out.println("I have " + this.data);
//        System.out.println("maxY =  " + maxY + " and minY = " + minY);
//
//        GrayPaintScale colourScale = new GrayPaintScale(minY, maxY);
//        //GrayPaintScale colourScale = new GrayPaintScale(0, 10000.0); //debuging.
//        renderer.setPaintScale(colourScale);
//
//        //Anders may be we have to do BlockHieght, BlockWidth, blockHeigth_minus, blockHeigth_plus??
//        //Now i just default everything as 1 which is why it is wrong i am sure.
//        renderer.setBlockHeight(1);
//        renderer.setBlockWidth(1);
//        renderer.clearSeriesPaints(true);
//    }
}
