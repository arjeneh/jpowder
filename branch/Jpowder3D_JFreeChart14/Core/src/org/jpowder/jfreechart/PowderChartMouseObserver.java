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
 * PowderChartMouseObserver.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * @author  Kreecha Puphaiboon, Computer Science Lecturer, Kasem Bundit University
 * @version 2 support for Annotation when click once.
 * 
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.jfreechart;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.ui.RectangleEdge;

/**
 * This class support the click of the mouse on the chart so it brings up a Frame 
 * and user can edit.
 * 
 */
public class PowderChartMouseObserver implements ChartMouseListener {

    private ChartPanel chartPanel;

    public PowderChartMouseObserver() {
    }

    public PowderChartMouseObserver(ChartPanel cp) {
        chartPanel = cp;
    }

    public void chartMouseMoved(ChartMouseEvent chartMouseEvent) {
    }

    public void chartMouseClicked(ChartMouseEvent chartMouseEvent) {

        if (chartMouseEvent.getTrigger().getClickCount() == 1) {
            int mouseX = chartMouseEvent.getTrigger().getX();
            int mouseY = chartMouseEvent.getTrigger().getY();
            //System.out.println("In class: " + this.getClass().getName() + " Co-ordination x = " + mouseX + ", y = " + mouseY);

            //System.out.println("In class: " + this.getClass().getName() + " ChartPanel name is " + chartPanel.getName() + "  ***************") ;

            Point2D p = chartPanel.translateScreenToJava2D(new Point(mouseX, mouseY));
            XYPlot plot = (XYPlot) chartPanel.getChart().getPlot();
            Rectangle2D plotArea = chartPanel.getScreenDataArea();
            ValueAxis domainAxis = plot.getDomainAxis();
            RectangleEdge domainAxisEdge = plot.getDomainAxisEdge();
            ValueAxis rangeAxis = plot.getRangeAxis();
            RectangleEdge rangeAxisEdge = plot.getRangeAxisEdge();

            double chartX = domainAxis.java2DToValue(p.getX(), plotArea, domainAxisEdge);
            double chartY = rangeAxis.java2DToValue(p.getY(), plotArea, rangeAxisEdge);
            //System.out.println("In class: " + this.getClass().getName() + " Value of dataset in the Chart: x = " + chartX + ", y = " + chartY);

            EditAnnotationFrame enf = EditAnnotationFrame.getInstance();
            enf.addAnnotation(mouseX, mouseY, chartX, chartY, chartPanel.getName());
            enf.setLocation(mouseX, mouseY);
            enf.setVisible(true);
        }//end if 1 click

        if (chartMouseEvent.getTrigger().getClickCount() == 2) {
            try {
                //----------Copy the chart-------------------
                final JFreeChart plot_copy = (JFreeChart) chartMouseEvent.getChart().clone();

                //Thread safe by seperating it in case editing and modification.
                java.awt.EventQueue.invokeLater(new Runnable() {

                    public void run() {
                        EditChartFrame editChartFrame = new EditChartFrame(plot_copy);
                    }
                });
            } catch (Exception ex) {
                ex.printStackTrace();
            }//end catch
        }//end if 2 click
    }//end chartMouseClicked
}//end ChartMouseObserver

