/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jpowder.InernalFrame;

import org.jpowder.*;
import java.util.Vector;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jpowder.dataset.DataSet;
import org.jpowder.jfreechart.FilesPlotter3D;

/**
 *
 * @author qyt21516
 */
public class JpowderInternalframe3D extends JpowderInternalframe{
    private XYPlot xYPlot;  // hold reference to plot created from dataset in constructor
    private JFreeChart chart;
    /**
     *
     * @param dataVisibleInChartPanel
     * @param data
     */
    public JpowderInternalframe3D(DataVisibleInChart dataVisibleInChartPanel, Vector<DataSet> data) {
        super(dataVisibleInChartPanel,data);
        xYPlot = this.getXYPlot();
        chart=FilesPlotter3D.getChart();
        
    }


    /**
     *
     * @return
     */
    public static int getnumberOfJpowderInternalframe() {
        return Jpowder.getChartPlotter3D().getAllFrames().length;
    }

    public JFreeChart getChart(){
        return chart;
    }
}
