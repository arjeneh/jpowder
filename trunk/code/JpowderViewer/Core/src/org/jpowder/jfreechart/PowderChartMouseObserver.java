/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.jfreechart;

import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

/**
 * This class support the click of the mouse on the chart so it brings up a Frame 
 * and user can edit.
 * 
 * @author Toshiba
 */
public class PowderChartMouseObserver implements ChartMouseListener {

    private ChartPanel chartPanel;

    public PowderChartMouseObserver() {
    }

    public PowderChartMouseObserver(ChartPanel cp) {

        chartPanel = cp;
        //chartPanel.add

    }

    public void chartMouseMoved(ChartMouseEvent chartMouseEvent) {
    }

    public void chartMouseClicked(ChartMouseEvent chartMouseEvent) {
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
        }//end if
    }//end chartMouseClicked
}//end ChartMouseObserver

