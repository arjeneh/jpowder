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
 * GridLinesPanel.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.chartTools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JColorChooser;
import org.jpowder.Analysis.ToolsIcon2D;
import org.jpowder.InfoPanel;
import org.jpowder.Jpowder;
import org.jpowder.InernalFrame.JpowderInternalframe2D;

/**
 * Enabling and disabling grid lines and set width and colour of them.
 */
public class GridLinesPanel extends javax.swing.JPanel implements InfoPanel {

    private ToolsIcon2D toolsIcon;

    /** Creates new form GridLinesPanel */
    public GridLinesPanel(ToolsIcon2D analysisIcon) {

        initComponents();
        this.toolsIcon = analysisIcon;
    }

    @Override
    public void update() {
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() == 0) {
            Component[] c = jPanel1.getComponents();
            for (Component comp : c) {
                comp.setEnabled(false);
            }

            return;
        } else {
            Component[] c = jPanel1.getComponents();
            for (Component comp : c) {
                comp.setEnabled(true);
            }
        }
        majorX.setSelected(inFocus.getXYPlot().isDomainGridlinesVisible());
        majorY.setSelected(inFocus.getXYPlot().isRangeGridlinesVisible());
        minorX.setSelected(inFocus.getXYPlot().isDomainMinorGridlinesVisible());
        minorY.setSelected(inFocus.getXYPlot().isRangeMinorGridlinesVisible());



    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox();
        buttonGroup1 = new javax.swing.ButtonGroup();
        backButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        majorX = new javax.swing.JCheckBox();
        majorY = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        gridlineWidth = new javax.swing.JSpinner();
        minorX = new javax.swing.JCheckBox();
        minorY = new javax.swing.JCheckBox();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Back.PNG"))); // NOI18N
        backButton.setText("Back");
        backButton.setAlignmentY(0.0F);
        backButton.setBorderPainted(false);
        backButton.setFocusable(false);
        backButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        backButton.setIconTextGap(2);
        backButton.setMargin(new java.awt.Insets(2, 0, 2, 0));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Jpowder Second Version Extera Features.");

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        majorX.setSelected(true);
        majorX.setText("Set Major X Grids");
        majorX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                majorXActionPerformed(evt);
            }
        });

        majorY.setSelected(true);
        majorY.setText("Set Major Y Grids");
        majorY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                majorYActionPerformed(evt);
            }
        });

        jButton1.setText("Pick Colour");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Width:");

        gridlineWidth.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.5f), Float.valueOf(0.0f), Float.valueOf(5.0f), Float.valueOf(0.5f)));
        gridlineWidth.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                gridlineWidthStateChanged(evt);
            }
        });

        minorX.setText("Set Minor X Grids");
        minorX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minorXActionPerformed(evt);
            }
        });

        minorY.setText("Set Minor Y Grids");
        minorY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minorYActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(minorY)
                    .addComponent(minorX)
                    .addComponent(majorY)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(majorX, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gridlineWidth, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(majorX)
                    .addComponent(jLabel3)
                    .addComponent(gridlineWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(majorY)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(minorX)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(minorY)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(108, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("GridLines", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jTabbedPane1, 0, 0, Short.MAX_VALUE))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed

        this.setVisible(false);
    }//GEN-LAST:event_backButtonActionPerformed

    private void majorXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_majorXActionPerformed

        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        boolean check = inFocus.getXYPlot().isDomainGridlinesVisible();
        inFocus.getXYPlot().setDomainGridlinesVisible(!check);

    }//GEN-LAST:event_majorXActionPerformed

    private void majorYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_majorYActionPerformed
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        boolean check = inFocus.getXYPlot().isRangeGridlinesVisible();
        inFocus.getXYPlot().setRangeGridlinesVisible(!check);
    }//GEN-LAST:event_majorYActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
//        JColorChooser colorChooser = new JColorChooser(Color.WHITE);
//        JDialog dialog = JColorChooser.createDialog(this, "Gridline Colour", true, colorChooser, null, null);

        Color c = JColorChooser.showDialog(
                this, "Gridline Colour", Color.white);
        if (c == null) {
            return;
        }

        if (majorX.isSelected()) {
            inFocus.getXYPlot().setDomainGridlinePaint(c);
        }
        if (majorY.isSelected()) {
            inFocus.getXYPlot().setRangeGridlinePaint(c);
        }
        if (minorX.isSelected()) {
            inFocus.getXYPlot().setDomainMinorGridlinePaint(c);
        }
        if (minorY.isSelected()) {
            inFocus.getXYPlot().setRangeMinorGridlinePaint(c);
        } else {
            return;
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void minorXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minorXActionPerformed
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        boolean check = inFocus.getXYPlot().isDomainMinorGridlinesVisible();
        inFocus.getXYPlot().setDomainMinorGridlinesVisible(!check);
    }//GEN-LAST:event_minorXActionPerformed

    private void minorYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minorYActionPerformed
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        boolean check = inFocus.getXYPlot().isRangeMinorGridlinesVisible();
        inFocus.getXYPlot().setRangeMinorGridlinesVisible(!check);
    }//GEN-LAST:event_minorYActionPerformed

    private void gridlineWidthStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_gridlineWidthStateChanged
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        float gridwidth = (float) Double.parseDouble(gridlineWidth.getValue().toString());

        if (majorX.isSelected()) {
            inFocus.getXYPlot().setDomainGridlineStroke(new BasicStroke(gridwidth));
        }
        if (majorY.isSelected()) {
            inFocus.getXYPlot().setRangeGridlineStroke(new BasicStroke(gridwidth));
        }
        if (minorX.isSelected()) {
            inFocus.getXYPlot().setDomainMinorGridlineStroke(new BasicStroke(gridwidth));
        }
        if (minorY.isSelected()) {
            inFocus.getXYPlot().setRangeMinorGridlineStroke(new BasicStroke(gridwidth));
        }
    }//GEN-LAST:event_gridlineWidthStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JSpinner gridlineWidth;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JCheckBox majorX;
    private javax.swing.JCheckBox majorY;
    private javax.swing.JCheckBox minorX;
    private javax.swing.JCheckBox minorY;
    // End of variables declaration//GEN-END:variables
}
