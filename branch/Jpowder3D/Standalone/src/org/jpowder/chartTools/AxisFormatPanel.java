/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AxisFormatPanel.java
 *
 * Created on Aug 10, 2010, 3:25:04 PM
 */
package org.jpowder.chartTools;

import java.awt.Color;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.ui.RectangleInsets;
import org.jpowder.Analysis.ToolsIcon2D;
import org.jpowder.InernalFrame.JpowderInternalframe2D;
import org.jpowder.InfoPanel;
import org.jpowder.Jpowder;

/**
 *
 * @author Arjeneh
 */
public class AxisFormatPanel extends javax.swing.JPanel implements InfoPanel {

    private ToolsIcon2D toolsIcon2D;
    private JpowderTickUnitSource jpowderTickUnitSource = new JpowderTickUnitSource();
    private static String decimal = "";

    /** Creates new form AxisFormatPanel */
    public AxisFormatPanel(ToolsIcon2D toolsIcon2D) {
        initComponents();
        this.toolsIcon2D = toolsIcon2D;
    }

    @Override
    public void update() {
    }

    public static String getDecimalPattern() {
//        decimal = decimalPlacesSpinner.getValue().toString();
        return "0." + decimal + "E0";
    }

    public void xAxisScientificNotation() {
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;

        if (xScientific.isSelected()) {
            inFocus.getXYPlot().getDomainAxis().setStandardTickUnits(jpowderTickUnitSource);
        }
        if (!xScientific.isSelected()) {
            inFocus.getXYPlot().getDomainAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        }

    }

    public void yAxisScientificNotation() {
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        if (yScientific.isSelected()) {
            inFocus.getXYPlot().getRangeAxis().setStandardTickUnits(jpowderTickUnitSource);


        }
        if (!yScientific.isSelected()) {
            inFocus.getXYPlot().getRangeAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        }

    }

    public void fillTheXTickField() {
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        NumberAxis xAxis = (NumberAxis) inFocus.getXYPlot().getDomainAxis();
        xTickField.setText(Double.toString(xAxis.getTickUnit().getSize()));
    }

    public void fillTheYTickField() {
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        NumberAxis yAxis = (NumberAxis) inFocus.getXYPlot().getRangeAxis();
        yTickField.setText(Double.toString(yAxis.getTickUnit().getSize()));
    }

    /**
     * 
     */
    public void applyTickSpacing() {
        if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() == 0) {
            tickSpacingApplyButton.setSelected(false);
            return;
        }


        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        try {
            double newX = Double.parseDouble(xTickField.getText());
            double newY = Double.parseDouble(yTickField.getText());
            NumberAxis xAxis = (NumberAxis) inFocus.getXYPlot().getDomainAxis();
            NumberAxis yAxis = (NumberAxis) inFocus.getXYPlot().getRangeAxis();

            xAxis.setTickUnit(new NumberTickUnit(newX));
            yAxis.setTickUnit(new NumberTickUnit(newY));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Enter valid number.");
            tickSpacingApplyButton.setSelected(false);
            fillTheXTickField();
            fillTheYTickField();
            return;
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        decimalPlacesSpinner = new javax.swing.JSpinner();
        xScientific = new javax.swing.JCheckBox();
        yScientific = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        tickSpacingApplyButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        yTickField = new javax.swing.JTextField();
        xTickField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jButton2 = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        jLabel4.setText("Decimal places:");

        decimalPlacesSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                decimalPlacesSpinnerStateChanged(evt);
            }
        });

        xScientific.setText("X axis scientific format");
        xScientific.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xScientificActionPerformed(evt);
            }
        });

        yScientific.setText("Y axis scientific format");
        yScientific.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yScientificActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(decimalPlacesSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(xScientific)
                            .addComponent(yScientific))))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(decimalPlacesSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(xScientific)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(yScientific)
                .addContainerGap(158, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Number", jPanel1);

        tickSpacingApplyButton.setText("Apply");
        tickSpacingApplyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tickSpacingApplyButtonActionPerformed(evt);
            }
        });

        jLabel10.setText("Y Axis:");

        yTickField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yTickFieldActionPerformed(evt);
            }
        });

        xTickField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                xTickFieldMouseClicked(evt);
            }
        });

        jLabel9.setText("X Axis:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 248, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(xTickField))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(yTickField, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tickSpacingApplyButton))))
                .addContainerGap(120, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 245, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(xTickField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(yTickField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(tickSpacingApplyButton)
                .addContainerGap(147, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tick Spacing", jPanel3);

        jLabel2.setText("Set Axis Offset");

        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });

        jButton2.setText("Axis Colour");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(55, 55, 55)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(185, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Appearance", jPanel2);

        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Back.PNG"))); // NOI18N
        backButton.setText("Back");
        backButton.setAlignmentY(0.0F);
        backButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        backButton.setIconTextGap(2);
        backButton.setMargin(new java.awt.Insets(2, 0, 2, 0));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed

        this.setVisible(false);
}//GEN-LAST:event_backButtonActionPerformed

    private void decimalPlacesSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_decimalPlacesSpinnerStateChanged
        //        decimal = decimalPlacesSpinner.getValue().toString();
        decimal = "";
        int dec = (int) Integer.parseInt(decimalPlacesSpinner.getValue().toString());
        for (int i = 0; i < dec; i++) {
            decimal += "0";
        }
        if (xScientific.isSelected()) {
            xAxisScientificNotation();
        }
        if (yScientific.isSelected()) {
            yAxisScientificNotation();
        }
    }//GEN-LAST:event_decimalPlacesSpinnerStateChanged

    private void xScientificActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xScientificActionPerformed
        xAxisScientificNotation();
}//GEN-LAST:event_xScientificActionPerformed

    private void yScientificActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yScientificActionPerformed
        yAxisScientificNotation();
}//GEN-LAST:event_yScientificActionPerformed

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;


        double offset = Double.parseDouble(jSpinner1.getValue().toString());

        inFocus.getXYPlot().setAxisOffset(new RectangleInsets(offset, offset, offset, offset));
    }//GEN-LAST:event_jSpinner1StateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        Color c = JColorChooser.showDialog(
                this, "Gridline Colour", Color.white);
        if (c == null) {
            return;
        }
        inFocus.getXYPlot().getDomainAxis().setAxisLinePaint(c);
        inFocus.getXYPlot().getRangeAxis().setAxisLinePaint(c);
        inFocus.getXYPlot().getDomainAxis().setTickMarkPaint(c);
        inFocus.getXYPlot().getRangeAxis().setTickMarkPaint(c);
}//GEN-LAST:event_jButton2ActionPerformed

    private void tickSpacingApplyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tickSpacingApplyButtonActionPerformed
        applyTickSpacing();
}//GEN-LAST:event_tickSpacingApplyButtonActionPerformed

    private void yTickFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yTickFieldActionPerformed
        applyTickSpacing();
}//GEN-LAST:event_yTickFieldActionPerformed

    private void xTickFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xTickFieldMouseClicked
        applyTickSpacing();
}//GEN-LAST:event_xTickFieldMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private static javax.swing.JSpinner decimalPlacesSpinner;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton tickSpacingApplyButton;
    private javax.swing.JCheckBox xScientific;
    private javax.swing.JTextField xTickField;
    private javax.swing.JCheckBox yScientific;
    private javax.swing.JTextField yTickField;
    // End of variables declaration//GEN-END:variables
}
