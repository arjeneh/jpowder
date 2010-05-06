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
 * Original Author:  Kreecha Puphaiboon;
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.jfreechart;

import org.jpowder.util.ScreenUtil;

/*
 * Class name: EditChartFrame.java
@author: Kreecha Puphaiboon
Date: Created on 22 May 2007, 10:51
Modf:
Description:
 *
 * A frame allocated a chart from the main JPowder class. With functionalities
 * such as zoom, save, delete, edit and etc. But none has yet implemented!
 *
 * called by PowderChartMouseObserver.java method
 * chartMouseClicked(ChartMouseEvent chartMouseEvent)

 */
public class EditChartFrame extends javax.swing.JFrame {

    private org.jfree.chart.JFreeChart jFreeChart;
    private org.jfree.chart.ChartPanel chartPanel;
    private EditChartFrame singletonObject;

    /** Creates new form EditChartFrame
     * @param chart JFreeChart Object
     */
    public EditChartFrame(org.jfree.chart.JFreeChart chart) {
        initComponents();
        jFreeChart = chart;
        chartPanel = new org.jfree.chart.ChartPanel(jFreeChart);
//        chartPanel.add(new JpowderPopupMenu(chartPanel));
        bigChartPanel.add(chartPanel);
    }

    public EditChartFrame(org.jfree.chart.JFreeChart chart, org.jfree.chart.ChartPanel cp) {
        initComponents();
        jFreeChart = chart;
        chartPanel = cp;
        chartPanel.setPreferredSize(bigChartPanel.getSize());
        bigChartPanel.add(chartPanel);
    }

    public EditChartFrame getSingletonObject(org.jfree.chart.JFreeChart jFreeChart) {
        //if (singletonObject == null) {
        singletonObject = new EditChartFrame(jFreeChart);
        //--}
        return singletonObject;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    @Override
    protected void finalize() throws Throwable {
        jFreeChart = null;
        chartPanel = null;
        singletonObject = null;
        //this = null;
    }

    private void initComponents() {
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();




        bigChart_sp = new javax.swing.JScrollPane();
        bigChartPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);






        bigChart_sp.setMinimumSize(new java.awt.Dimension(800, 465));
        bigChart_sp.setPreferredSize(new java.awt.Dimension(screenSize.width, 465));

        bigChartPanel.setLayout(new javax.swing.BoxLayout(bigChartPanel, javax.swing.BoxLayout.Y_AXIS));
        bigChartPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        bigChartPanel.setMaximumSize(new java.awt.Dimension(1800, 1465));
        bigChartPanel.setMinimumSize(new java.awt.Dimension(800, 465));
        bigChartPanel.setPreferredSize(new java.awt.Dimension(800, 0));
        bigChart_sp.setViewportView(bigChartPanel);

        getContentPane().add(bigChart_sp, java.awt.BorderLayout.CENTER);

        pack();
        setAlwaysOnTop(true);
        setSize(800, 600);
        setLocationRelativeTo(this);
        setVisible(true);
        ScreenUtil.centerFrame(this);
        //setBounds(0, 0, screenSize.width, screenSize.height); //fill screen

    }
    // Variables declaration - do not modify
    private javax.swing.JPanel bigChartPanel;
    private javax.swing.JScrollPane bigChart_sp;
    // End of variables declaration
}
