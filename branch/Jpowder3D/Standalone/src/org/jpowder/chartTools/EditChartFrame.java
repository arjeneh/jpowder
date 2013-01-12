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
 * EditChartFrame_Bkk.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  Kreecha Puphaiboon, Computer Science Lecturer, Kasem Bundit University
 *
 *
 * Pops-up chart when double click
 *
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 * EditChartFrame.java
 *
 * Created on 15-Dec-2012, 14:02:08
 */
package org.jpowder.chartTools;

import org.jpowder.jfreechart.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.*;
import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jpowder.InternalFrame.JpowderInternalframe3D;
import org.jpowder.Jpowder;
import org.jpowder.util.ProportionalDimension;
import org.jpowder.util.ScreenUtil;

public class EditChartFrame extends javax.swing.JFrame {

    private JFreeChart jFreeChart;
    private ChartPanel chartPanel;
    private EditChartFrame singletonObject;
    private double proportion;
    //
    private static final int N = 8;
    private static final String title = "Scatter Add Demo";
    private static final Random rand = new Random();
    private XYSeries added = new XYSeries("Added");

    public EditChartFrame(JFreeChart plot_copy, String name) {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setAlwaysOnTop(true);
        setSize(800, 600);
        setLocationRelativeTo(this);
        setVisible(true);
        ScreenUtil.centerFrame(this);

        jFreeChart = plot_copy;
        chartPanel = new org.jfree.chart.ChartPanel(jFreeChart);
        chartPanel.setName(name);
        bigChart_sp.getViewport().add(chartPanel);
        setTitle(chartPanel.getName());

        //    Check to see the size changed and adjust to the right proportion.
        //TODO: still does not work.
        this.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                Component c = (Component) e.getSource();

                jFreeChart.setNotify(false);
                super.componentResized(e);
            }
        });
    }

    private EditChartFrame() {
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setAlwaysOnTop(true);
        setSize(800, 600);
        setLocationRelativeTo(this);
        setVisible(true);
        ScreenUtil.centerFrame(this);
    }

    private ChartPanel createDemoPanel() {
        JFreeChart jfreechart = ChartFactory.createScatterPlot(
                title, "X", "Y", createSampleData(),
                PlotOrientation.VERTICAL, true, true, false);

        XYPlot xyPlot = (XYPlot) jfreechart.getPlot();
        xyPlot.setDomainCrosshairVisible(true);
        xyPlot.setRangeCrosshairVisible(true);

        XYItemRenderer renderer = xyPlot.getRenderer();
        renderer.setSeriesPaint(0, Color.blue);

        NumberAxis domain = (NumberAxis) xyPlot.getDomainAxis();
        domain.setVerticalTickLabels(true);
        return new ChartPanel(jfreechart);
    }

    private XYDataset createSampleData() {
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        XYSeries series = new XYSeries("Random");
        for (int i = 0; i < N * N; i++) {
            double x = rand.nextGaussian();
            double y = rand.nextGaussian();
            series.add(x, y);
        }
        xySeriesCollection.addSeries(series);
        xySeriesCollection.addSeries(added);
        return xySeriesCollection;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jLabel2 = new javax.swing.JLabel();
        widthTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        hieghtTxt = new javax.swing.JTextField();
        proportionButton = new javax.swing.JButton();
        bigChart_sp = new javax.swing.JScrollPane();
        binButton = new javax.swing.JButton();
        binFactorTxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jLabel2.setText("Width");
        jToolBar1.add(jLabel2);

        widthTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                widthTxtActionPerformed(evt);
            }
        });
        jToolBar1.add(widthTxt);

        jLabel1.setText("Hieght");
        jToolBar1.add(jLabel1);
        jToolBar1.add(hieghtTxt);

        proportionButton.setText("Proportion");
        proportionButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        proportionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proportionButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(proportionButton);

        binButton.setText("Bin");
        binButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        binButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                binButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(binFactorTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(binButton, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(bigChart_sp, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(binButton)
                        .addComponent(binFactorTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bigChart_sp, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void widthTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_widthTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_widthTxtActionPerformed

    private void proportionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proportionButtonActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        Dimension d = chartPanel.getPreferredSize();
        double x = d.width;
        double y = d.height;

        //Find the proportion factor.
        final double proportion = x / y;

        Dimension curD = this.getSize();
        Dimension newSize = new ProportionalDimension(curD, proportion);
        //c.setSize(newSize);
        int w = Integer.parseInt(widthTxt.getText());
        int h = Integer.parseInt(hieghtTxt.getText());
        this.setSize(w, h);
    }//GEN-LAST:event_proportionButtonActionPerformed

    private void binButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_binButtonActionPerformed
        // TODO add your handling code here:
        JpowderInternalframe3D inFocus = Jpowder.internalFrameInFocus3D;

        BinOptimizer bo = new BinOptimizer(this.chartPanel, binFactorTxt.getText());
        //BinOptimizer bo = new BinOptimizer(this.jFreeChart, binFactorTxt.getText());
        bo.execute(inFocus);
    }//GEN-LAST:event_binButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new EditChartFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane bigChart_sp;
    private javax.swing.JButton binButton;
    private javax.swing.JTextField binFactorTxt;
    private javax.swing.JTextField hieghtTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton proportionButton;
    private javax.swing.JTextField widthTxt;
    // End of variables declaration//GEN-END:variables
}
