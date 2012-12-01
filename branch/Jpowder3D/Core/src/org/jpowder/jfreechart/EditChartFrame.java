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
 * EditChartFrame.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  Kreecha Puphaiboon, Computer Science Lecturer, Kasem Bundit University
 *
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.jfreechart;

import org.jfree.chart.JFreeChart;
import org.jpowder.util.ScreenUtil;
import org.jpowder.util.StringUtil;

/*
 * Pops-up chart when double click
 *
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
        System.out.println("In class: " + this.getClass().getName() + " Copy named: "
                + chartPanel.getName()
               // + StringUtil.getFileTitle(FilesPlotter3D.fileNames)
                );

        //chartPanel.add(new JpowderPopupMenu(chartPanel));
        bigChartPanel.add(chartPanel);
    }

    public EditChartFrame(org.jfree.chart.JFreeChart plot_copy, org.jfree.chart.ChartPanel cp) {
        initComponents();
        jFreeChart = plot_copy;
        chartPanel = cp;
         System.out.println("In class: " + this.getClass().getName() + " Copy named: "
                + chartPanel.getName()
               // + StringUtil.getFileTitle(FilesPlotter3D.fileNames)
                );
        chartPanel.setPreferredSize(bigChartPanel.getSize());
        bigChartPanel.add(chartPanel);
        setTitle( chartPanel.getName()  );
    }

    /**
     *
     * @param plot_copy of JFreechart
     * @param name name to appear in the JFrame
     */
    public EditChartFrame(JFreeChart plot_copy, String name) {
         initComponents();
        jFreeChart = plot_copy;
        chartPanel = new org.jfree.chart.ChartPanel(jFreeChart);
        chartPanel.setName(name);
        chartPanel.setPreferredSize(bigChartPanel.getSize());
        bigChartPanel.add(chartPanel);
        setTitle( chartPanel.getName()  );
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
