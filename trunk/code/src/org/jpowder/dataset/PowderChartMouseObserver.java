/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jpowder.dataset;

import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.JFreeChart;
import org.jpowder.EditChartFrame;

/**
 * This class support the click of the mouse on the chart so it brings up a Frame 
 * and user can edit.
 * 
 * @author Toshiba
 */
    
    public class PowderChartMouseObserver implements ChartMouseListener {
        
        public void chartMouseMoved(ChartMouseEvent chartMouseEvent) {}
        
        public void chartMouseClicked(ChartMouseEvent chartMouseEvent) {
            if(chartMouseEvent.getTrigger().getClickCount() == 2){
                try {
                    //----------Copy the chart-------------------
                    final JFreeChart plot_copy = (JFreeChart) chartMouseEvent.getChart().clone();
                    //Thread safe by seperating it in case editing and modification.
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            EditChartFrame obj = new EditChartFrame(plot_copy);
                            System.out.println(plot_copy.toString() + " is clicked from PowderChartMouseObserver class.");
                        }
                    });
                } catch (Exception ex){
                    ex.printStackTrace();
                }//end catch
            }//end if
        }//end chartMouseClicked
    }//end ChartMouseObserver