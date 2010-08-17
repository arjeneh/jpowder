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
 * ToolsIcon.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *             Hannah Guest, ISIS, Rutherford Appleton Laboratory     
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.Analysis;

import org.jpowder.Jpowder;
import org.jpowder.chartTools.AxisFormatPanel;
import org.jpowder.chartTools.BackGroundPanel;
import org.jpowder.chartTools.ChangePlotStyle;
import org.jpowder.chartTools.GridLinesPanel;
import org.jpowder.chartTools.LegendPanel;
import org.jpowder.chartTools.ZoomAndPan;

/**
 * this class contains all the Jpowder icons.
 * 
 */
public class ToolsIcon2D extends javax.swing.JPanel {

    private MarkPeakPosition markPeakPosition = new MarkPeakPosition(this);
    private RescaleYdata rescaleYdata = new RescaleYdata(this);
    private ChangePlotStyle changePlotStyle = new ChangePlotStyle(this);
    private ZoomAndPan zoomAndPan = new ZoomAndPan(this);
    private Transforming_XAxis braggsLow = new Transforming_XAxis(this);
    private GridLinesPanel extraFeatures = new GridLinesPanel(this);
    private LegendPanel legendPanel = new LegendPanel(this);
    private BackGroundPanel backGroundPanel = new BackGroundPanel(this);
    private AxisFormatPanel axisFormatPanel = new AxisFormatPanel(this);
    private Jpowder jpowder;

    /** Creates new form AnalysisIcon */
    public ToolsIcon2D(Jpowder jpowder) {
        initComponents();
        this.jpowder = jpowder;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        peakButton = new javax.swing.JButton();
        rescaleButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        zoomButton = new javax.swing.JButton();
        appearanceButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        changeXAxisButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        axisFormatButton = new javax.swing.JButton();
        legendButton = new javax.swing.JButton();
        backGroundButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(320, 420));

        peakButton.setBackground(javax.swing.UIManager.getDefaults().getColor("CheckBox.highlight"));
        peakButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Peaks_Small.png"))); // NOI18N
        peakButton.setPreferredSize(new java.awt.Dimension(80, 80));
        peakButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                peakButtonActionPerformed(evt);
            }
        });

        rescaleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Rescale_Small.png"))); // NOI18N
        rescaleButton.setPreferredSize(new java.awt.Dimension(80, 80));
        rescaleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rescaleButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Peaks");

        jLabel2.setText("Rescale & Move");
        jLabel2.setToolTipText("");

        zoomButton.setBackground(javax.swing.UIManager.getDefaults().getColor("ComboBox.disabledBackground"));
        zoomButton.setForeground(new java.awt.Color(255, 255, 255));
        zoomButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Zoom_Small.png"))); // NOI18N
        zoomButton.setOpaque(false);
        zoomButton.setPreferredSize(new java.awt.Dimension(80, 80));
        zoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomButtonActionPerformed(evt);
            }
        });

        appearanceButton.setBackground(javax.swing.UIManager.getDefaults().getColor("ComboBox.disabledBackground"));
        appearanceButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Appearance_Small.png"))); // NOI18N
        appearanceButton.setOpaque(false);
        appearanceButton.setPreferredSize(new java.awt.Dimension(80, 80));
        appearanceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appearanceButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Plot Format");

        jLabel4.setText("Zoom & Pan ");

        changeXAxisButton.setBackground(javax.swing.UIManager.getDefaults().getColor("ComboBox.disabledBackground"));
        changeXAxisButton.setForeground(new java.awt.Color(255, 255, 255));
        changeXAxisButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ChangeXAxis_Small.png"))); // NOI18N
        changeXAxisButton.setOpaque(false);
        changeXAxisButton.setPreferredSize(new java.awt.Dimension(80, 80));
        changeXAxisButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeXAxisButtonActionPerformed(evt);
            }
        });

        jLabel5.setText("Transform x-axis");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/GridLines_Small.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setText("Axis Format");

        axisFormatButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/AxisFormat_Small.png"))); // NOI18N
        axisFormatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                axisFormatButtonActionPerformed(evt);
            }
        });

        legendButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Legend_Small.png"))); // NOI18N
        legendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                legendButtonActionPerformed(evt);
            }
        });

        backGroundButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ChartAreaFormat_Small.png"))); // NOI18N
        backGroundButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backGroundButtonActionPerformed(evt);
            }
        });

        jLabel7.setText("Legend");

        jLabel9.setText("GridLines");

        jLabel10.setText("Chart Area Format");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(peakButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(appearanceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel3)))
                        .addGap(12, 12, 12))
                    .addComponent(backGroundButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(4, 4, 4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel9)
                                .addGap(27, 27, 27))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel2))
                            .addComponent(rescaleButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(zoomButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(11, 11, 11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(changeXAxisButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel5))
                    .addComponent(legendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(axisFormatButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel6)))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {appearanceButton, axisFormatButton, backGroundButton, changeXAxisButton, jButton1, legendButton, peakButton, rescaleButton, zoomButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(125, 125, 125)
                                .addComponent(legendButton, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                .addGap(15, 15, 15)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(105, 105, 105)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(changeXAxisButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(129, 129, 129)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(axisFormatButton, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rescaleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(125, 125, 125)
                                .addComponent(zoomButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(peakButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(appearanceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backGroundButton, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {appearanceButton, axisFormatButton, backGroundButton, changeXAxisButton, jButton1, legendButton, peakButton, rescaleButton, zoomButton});

    }// </editor-fold>//GEN-END:initComponents

    private void peakButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_peakButtonActionPerformed
//
//        Object source = evt.getSource();
//        if (source instanceof JButton) {
//            JButton button = (JButton) source;
//
//
//            markPeakPosition.getPeakLabel().setIcon(button.getIcon());
//        }
        jpowder.getToolsTab2D().add(markPeakPosition, "1");
        markPeakPosition.setVisible(true);
        this.setVisible(false);
        Jpowder.jpowderInfoPanelUpdate(markPeakPosition);

    }//GEN-LAST:event_peakButtonActionPerformed

    private void rescaleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rescaleButtonActionPerformed

//        Object source = evt.getSource();
//        if (source instanceof JButton) {
//            JButton button = (JButton) source;
//            rescaleYdata.getRescalLabel().setIcon(button.getIcon());
//        }

        jpowder.getToolsTab2D().add(rescaleYdata, "1");
        rescaleYdata.setVisible(true);
        this.setVisible(false);
        Jpowder.jpowderInfoPanelUpdate(rescaleYdata);
    }//GEN-LAST:event_rescaleButtonActionPerformed

    private void zoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoomButtonActionPerformed

        jpowder.getToolsTab2D().add(zoomAndPan, "1");
        zoomAndPan.setVisible(true);
        this.setVisible(false);
        Jpowder.jpowderInfoPanelUpdate(zoomAndPan);
}//GEN-LAST:event_zoomButtonActionPerformed

    private void appearanceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appearanceButtonActionPerformed

        jpowder.getToolsTab2D().add(changePlotStyle, "1");
        changePlotStyle.setVisible(true);
        this.setVisible(false);

        Jpowder.jpowderInfoPanelUpdate(changePlotStyle);
}//GEN-LAST:event_appearanceButtonActionPerformed

    private void changeXAxisButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeXAxisButtonActionPerformed
        jpowder.getToolsTab2D().add(braggsLow, "1");
        braggsLow.setVisible(true);
        this.setVisible(false);

        Jpowder.jpowderInfoPanelUpdate(braggsLow);
    }//GEN-LAST:event_changeXAxisButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jpowder.getToolsTab2D().add(extraFeatures, "1");
        extraFeatures.setVisible(true);
        this.setVisible(false);

        Jpowder.jpowderInfoPanelUpdate(extraFeatures);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void axisFormatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_axisFormatButtonActionPerformed
           jpowder.getToolsTab2D().add(axisFormatPanel, "1");
        axisFormatPanel.setVisible(true);
        this.setVisible(false);

        Jpowder.jpowderInfoPanelUpdate(axisFormatPanel);
    }//GEN-LAST:event_axisFormatButtonActionPerformed

    private void legendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_legendButtonActionPerformed
        jpowder.getToolsTab2D().add(legendPanel, "1");
        legendPanel.setVisible(true);
        this.setVisible(false);

        Jpowder.jpowderInfoPanelUpdate(legendPanel);
    }//GEN-LAST:event_legendButtonActionPerformed

    private void backGroundButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backGroundButtonActionPerformed
        jpowder.getToolsTab2D().add(backGroundPanel, "1");
        backGroundPanel.setVisible(true);
        this.setVisible(false);

        Jpowder.jpowderInfoPanelUpdate(backGroundPanel);
    }//GEN-LAST:event_backGroundButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton appearanceButton;
    private javax.swing.JButton axisFormatButton;
    private javax.swing.JButton backGroundButton;
    private javax.swing.JButton changeXAxisButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton legendButton;
    private javax.swing.JButton peakButton;
    private javax.swing.JButton rescaleButton;
    private javax.swing.JButton zoomButton;
    // End of variables declaration//GEN-END:variables
}
