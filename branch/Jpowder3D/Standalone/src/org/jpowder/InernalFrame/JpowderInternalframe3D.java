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
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.jpowder.*;
import java.util.Vector;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jpowder.Annotation.BalloonFrame;
import org.jpowder.dataset.DataSet;
import org.jpowder.jfreechart.EditAnnotationFrame;
import org.jpowder.jfreechart.FilesPlotter3D;
import org.jpowder.jfreechart.PointAnno;

/**
 *
 * Internal Frame 3D
 */
public class JpowderInternalframe3D extends JpowderInternalframe {

    private XYPlot xYPlot;  // hold reference to plot created from dataset in constructor
    private JFreeChart chart;
    private String selectedMetaItem;
    private HashMap fileNameAndPath;

    /**
     *
     * @param dataVisibleInChartPanel
     * @param data
     */
    public JpowderInternalframe3D(DataVisibleInChart dataVisibleInChartPanel,
            Vector<DataSet> data, String selectedMetaItem) {
        //
        super(dataVisibleInChartPanel, data);
        this.doStuff(selectedMetaItem);
        xYPlot = this.getXYPlot();
        chart = FilesPlotter3D.getChart();
        this.selectedMetaItem = selectedMetaItem;
    }

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

        xYPlot = this.getXYPlot();
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
}
