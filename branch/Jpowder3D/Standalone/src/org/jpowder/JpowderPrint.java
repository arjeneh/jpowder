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
 * About.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  Milad Arjeneh, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder;

import org.jpowder.InernalFrame.JpowderInternalframe2D;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.Serializable;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;
import org.jpowder.InernalFrame.JpowderInternalframe;
import org.jpowder.chartTools.CreateLegend;

/**
 *This class prints the chart
 *
 */
public class JpowderPrint implements Serializable{

    private ChartPanel chartPanel;
    private XYPlot plot;
    private JFreeChart chart;


    public JpowderPrint(final ChartPanel chartPanel) {


        this.chartPanel=chartPanel;
        chart = this.chartPanel.getChart();
        this.plot = (XYPlot) chart.getPlot();



    }



    /**
     * this method invokes on print out which enable users to add legend to the
     * chart.
     */
    public void basicPrint() {
//        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;

        if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() != 0) {
            NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
            NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
//            xAxis.setTickUnit(new NumberTickUnit(inFocus.getXAxis()));
//            yAxis.setTickUnit(new NumberTickUnit(inFocus.getYAxis()));

            Object[] options = {"Yes",
                "No"};
            int n = JOptionPane.showOptionDialog(null,
                    "Would you like to append legend(s) to printout?",
                    "Set legend",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, //do not use a custom Icon
                    options, //the titles of buttons
                    options[0]); //default button title
            if (n == 0) {

                CreateLegend createLegend = new CreateLegend();
                createLegend.setLegend();
                chartPanel.createChartPrintJob();
                chart.removeLegend();
                xAxis.setAutoTickUnitSelection(true);
                yAxis.setAutoTickUnitSelection(true);
            }
            if (n == 1) {
                chartPanel.createChartPrintJob();
                xAxis.setAutoTickUnitSelection(true);
                yAxis.setAutoTickUnitSelection(true);
            }
        } else {
            return;
        }
    }

    /**
     * this method is design for easy print for publication which needs to
     * be printed in white background.
     */
    public void printForPublication() {
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() != 0) {
            NumberAxis xAxis = (NumberAxis) inFocus.getXYPlot().getDomainAxis();
            NumberAxis yAxis = (NumberAxis) inFocus.getXYPlot().getRangeAxis();
            xAxis.setTickUnit(new NumberTickUnit(inFocus.getXAxis()));
            yAxis.setTickUnit(new NumberTickUnit(inFocus.getYAxis()));
            inFocus.getXYPlot().setBackgroundPaint(ChartColor.white);
            inFocus.getXYPlot().setOutlinePaint(ChartColor.white);
            PrinterJob job = PrinterJob.getPrinterJob();
            PageFormat pf = job.defaultPage();
            PageFormat pf2 = job.pageDialog(pf);
            if (pf2 != pf) {
                job.setPrintable(inFocus.getChartPanel(), pf2);
                if (job.printDialog()) {
                    try {
                        job.print();
                    } catch (PrinterException e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                }
            }

            xAxis.setAutoTickUnitSelection(true);
            yAxis.setAutoTickUnitSelection(true);
            inFocus.getXYPlot().setBackgroundPaint(ChartColor.LIGHT_GRAY);
            inFocus.getXYPlot().setOutlinePaint(ChartColor.LIGHT_GRAY);
        } else {
            return;
        }
    }



    public void print1(JpowderInternalframe internalframe){



    }

}
