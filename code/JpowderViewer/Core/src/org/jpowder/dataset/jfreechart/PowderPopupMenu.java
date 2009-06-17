/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.dataset.jfreechart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.List;
import java.util.Vector;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.renderer.xy.XYErrorRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.LengthAdjustmentType;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;
import org.jpowder.util.VectorMiscUtil;

/**
 * 
 * @Class name:  PowderPopupMenu.java
@Author: Kreecha Puphaiboon
@Date: 21 May 2008, 19:20
@Modf:
@Description:
 * A popupmenu which allows the user to enable/disable chart properties e.g.
    turn off errorbars, turn off connecting line, turn off marker,
 *  mark peak position, Rescale Y data
 * @see javax.swing.JPopupMenu

@Return:
 */

public class PowderPopupMenu extends javax.swing.JPopupMenu {

    private org.jfree.chart.JFreeChart chart;
    private javax.swing.JPopupMenu jFreeChartPopup;
    private org.jfree.chart.ChartPanel chartPanel;
    private org.jfree.chart.plot.XYPlot plot;

    public PowderPopupMenu(org.jfree.chart.ChartPanel chartPanel) {

        this.chartPanel = chartPanel;
        this.chart = this.chartPanel.getChart();
        this.jFreeChartPopup = this.chartPanel.getPopupMenu();
        this.plot = (org.jfree.chart.plot.XYPlot) this.chart.getPlot();

        //Change Plot Style
        javax.swing.JMenu changePlotStyle = new javax.swing.JMenu("Change plot style");
        changePlotStyle.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent event) {
                System.out.println("Change plot style");
            }
        });

        // TODO: Throw Casting exception
        javax.swing.JCheckBoxMenuItem offErrorBar = new javax.swing.JCheckBoxMenuItem("turn off errorbars", true);
        offErrorBar.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent event) {
                //get the plot, get renderer, set to what i want.
                org.jfree.chart.renderer.xy.XYErrorRenderer renderer = (XYErrorRenderer) plot.getRenderer();
                boolean status = renderer.getDrawYError();
                System.out.println("Render Error bar is " + status);
                renderer.setDrawYError(!status);//show opposite Y error bar.

            }
        });


        // TODO: if it is an XY dataset it needs no display.
        javax.swing.JCheckBoxMenuItem offConnectLine = new javax.swing.JCheckBoxMenuItem("turn off connecting line");
        offConnectLine.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent event) {
                //Get the plot, get renderer, set to what i want.
                int seriesCount = plot.getSeriesCount();
                System.out.println("Series are in here = " + seriesCount);
                org.jfree.chart.renderer.xy.XYErrorRenderer renderer = (XYErrorRenderer) plot.getRenderer();

                boolean status = renderer.getBaseLinesVisible();
                renderer.setBaseLinesVisible(!status);

            }
        });


        javax.swing.JCheckBoxMenuItem offMarker = new javax.swing.JCheckBoxMenuItem("turn off marker");
        offMarker.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent event) {
                //get the plot, get renderer, set to what i want.
                int seriesCount = plot.getSeriesCount();
                System.out.println("Series are in here = " + seriesCount);
                org.jfree.chart.renderer.xy.XYErrorRenderer renderer = (XYErrorRenderer) plot.getRenderer();

                boolean status = renderer.getBaseShapesVisible();
                renderer.setBaseShapesVisible(!status);
            }
        });

        //ADD THESE THREE ONTO changePlotStyle
        changePlotStyle.add(offErrorBar);
        changePlotStyle.add(offConnectLine);
        changePlotStyle.add(offMarker);

        // TODO: turn it off too you know.
        //ADD Mark Peak Position
        javax.swing.JMenuItem markPeakPosition = new javax.swing.JMenuItem("Mark peak position");
        markPeakPosition.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent event) {
                //turn off or on Markers.
                //boolean status = plot.getDomainMarkers(arg0);
                
                XYDataset dataset = plot.getDataset();

                List chartList = new Vector();
                Vector tX = new Vector();
                Vector tY = new Vector();

                //Multiple series in case of series in a chart.
                int seriescount = plot.getSeriesCount();
                for (int seriesindex = 0; seriesindex < seriescount; seriesindex++) {

                    for (int itemindex = 0; itemindex < dataset.getItemCount(seriesindex); itemindex++) {
                        tX.add(dataset.getXValue(seriesindex, itemindex));
                        tY.add(dataset.getYValue(seriesindex, itemindex));
                    }
                    chartList.add(tX);
                    chartList.add(tY);
                }

                //System.out.println("Value of chartList = " + chartList);
                //System.out.println("Value of tX = " + tX);
                //System.out.println("Value of tY = " + tY);

                //DRAW a Y line on the max value in Range. 
                //Find the max value and find its position using v.indexOf("max value");
                //Find the x position of the X axis (Domain). 
                Double maxRange = VectorMiscUtil.findMaxElementOf1DVector(tY);                           
                //System.out.println("Max Range value is " + maxRange);
                
                int pos = tY.indexOf( Double.parseDouble( maxRange.toString()) );//not found
                //System.out.println("Max Range position is " + pos);

                //add Marker Higlight for the range.
                Marker peakRangeMarker = new ValueMarker(Double.parseDouble(
                        maxRange.toString()));

                peakRangeMarker.setStroke(new BasicStroke(1.4f));
                peakRangeMarker.setPaint(Color.yellow);
                peakRangeMarker.setLabel("Peak Range");
                peakRangeMarker.setLabelAnchor(RectangleAnchor.TOP_LEFT);
                peakRangeMarker.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
                plot.addRangeMarker(peakRangeMarker);

                //System.out.println("tX.elementAt(pos) = " + tY.elementAt(pos));

                Double maxDomain = (Double) tX.elementAt(pos);

                Marker maxDomainTarget = new ValueMarker(maxDomain);
                maxDomainTarget.setStroke(new BasicStroke(1.4f));
                maxDomainTarget.setPaint(Color.yellow);
                maxDomainTarget.setLabelOffsetType(LengthAdjustmentType.EXPAND);
                maxDomainTarget.setLabel("Peak Domain");
                maxDomainTarget.setLabelAnchor(RectangleAnchor.TOP_RIGHT);
                maxDomainTarget.setLabelTextAnchor(TextAnchor.BOTTOM_RIGHT);
                plot.addDomainMarker(maxDomainTarget);
        
            }
        });

        //ADD Rescale Y data
        javax.swing.JMenuItem scaleYdata = new javax.swing.JMenuItem("Rescale Y data");
        scaleYdata.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent event) {
                System.out.println("rescale Y data");
            }
        });

        this.jFreeChartPopup.addSeparator();
        this.jFreeChartPopup.add(changePlotStyle);
        //
        this.jFreeChartPopup.addSeparator();
        this.jFreeChartPopup.add(markPeakPosition);
        //
        this.jFreeChartPopup.addSeparator();
        this.jFreeChartPopup.add(scaleYdata);

    }
}
