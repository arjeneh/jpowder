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
 * Axis.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Original Author:  Kreecha Puphaiboon
 * Contributor(s):   
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
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

