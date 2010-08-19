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
 * AxisFormatPanel.java
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
import java.awt.Font;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.text.DefaultFormatter;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.ui.RectangleInsets;
import org.jpowder.Analysis.ToolsIcon2D;
import org.jpowder.InernalFrame.JpowderInternalframe2D;
import org.jpowder.InfoPanel;
import org.jpowder.Jpowder;

/**
 * Formatting the axis like change the colour, Width and NumberFormat.
 *
 */
public class AxisFormatPanel extends javax.swing.JPanel implements InfoPanel {

    private ToolsIcon2D toolsIcon2D;
    private JpowderTickUnitSourceXAxis jpowderTickUnitSourceX = new JpowderTickUnitSourceXAxis();
    private JpowderTickUnitSourceYAxis jpowderTickUnitSourceY = new JpowderTickUnitSourceYAxis();
    private static String decimal = "";

    /** Creates new form AxisFormatPanel */
    public AxisFormatPanel(ToolsIcon2D toolsIcon2D) {
        initComponents();
        this.toolsIcon2D = toolsIcon2D;
    }

    @Override
    public void update() {
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;

        if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() == 0) {
            Component[] np = numberPanel.getComponents();
            Component[] tsp = tickSpacingPanel.getComponents();
            Component[] op = OffsetPanel.getComponents();
            for (Component comp : np) {
                comp.setEnabled(false);
            }
            for (Component comp : tsp) {
                comp.setEnabled(false);
            }
            for (Component comp : op) {
                comp.setEnabled(false);
            }

            return;
        } else {
            Component[] np = numberPanel.getComponents();
            Component[] tsp = tickSpacingPanel.getComponents();
            Component[] op = OffsetPanel.getComponents();
            for (Component comp : np) {
                comp.setEnabled(true);
            }
            for (Component comp : tsp) {
                comp.setEnabled(true);
            }
            for (Component comp : op) {
                comp.setEnabled(true);
            }

            decimalPlacesSpinner.setValue(inFocus.getDecimalPlaces());
        }


        xScientific.setSelected(inFocus.getXYPlot().getDomainAxis().getStandardTickUnits().toString().contains("JpowderTickUnitSourceXAxis"));
        yScientific.setSelected(inFocus.getXYPlot().getRangeAxis().getStandardTickUnits().toString().contains("JpowderTickUnitSourceYAxis"));

        fillTheXTickField();
        fillTheYTickField();
//this bit of code is for disabling jspinner from any charctor being added to it.
        ((DefaultFormatter) ((JSpinner.DefaultEditor) decimalPlacesSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(true);
        ((DefaultFormatter) ((JSpinner.DefaultEditor) axisOffserSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(true);

    }
/**
 * get Decimal places for scientfic format.
 * @return String
 */
    public static String getDecimalPattern() {
//        decimal = decimalPlacesSpinner.getValue().toString();
        return "0." + decimal + "E0";
    }
/**
 * Change the xAxis to scientific format.
 */
    public void xAxisScientificNotation() {
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;

        if (xScientific.isSelected()) {
            inFocus.getXYPlot().getDomainAxis().setStandardTickUnits(jpowderTickUnitSourceX);
        }
        if (!xScientific.isSelected()) {
            inFocus.getXYPlot().getDomainAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        }

    }
/**
 * change yAxis to scientific format.
 */
    public void yAxisScientificNotation() {
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        if (yScientific.isSelected()) {
            inFocus.getXYPlot().getRangeAxis().setStandardTickUnits(jpowderTickUnitSourceY);


        }
        if (!yScientific.isSelected()) {
            inFocus.getXYPlot().getRangeAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        }

    }
/**
 * fill the text files with xAxis tickUnit size
 */
    public void fillTheXTickField() {
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        NumberAxis xAxis = (NumberAxis) inFocus.getXYPlot().getDomainAxis();
        xTickField.setText(Double.toString(xAxis.getTickUnit().getSize()));
    }
/**
 * file the yAxis with range tickUnit size.
 */
    public void fillTheYTickField() {
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        NumberAxis yAxis = (NumberAxis) inFocus.getXYPlot().getRangeAxis();
        yTickField.setText(Double.toString(yAxis.getTickUnit().getSize()));
    }

    /**
     * apply tick spacing to the axes.
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
        appearancePanel = new javax.swing.JTabbedPane();
        numberPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        xScientific = new javax.swing.JCheckBox();
        yScientific = new javax.swing.JCheckBox();
        decimalPlacesSpinner = new javax.swing.JSpinner();
        tickSpacingPanel = new javax.swing.JPanel();
        tickSpacingApplyButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        yTickField = new javax.swing.JTextField();
        xTickField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        OffsetPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        axisOffserSpinner = new javax.swing.JSpinner();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        axisOffserSpinner1 = new javax.swing.JSpinner();
        backButton = new javax.swing.JButton();

        jLabel4.setText("Decimal places:");

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

        decimalPlacesSpinner.setModel(new javax.swing.SpinnerNumberModel(0, -10, 10, 1));
        decimalPlacesSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                decimalPlacesSpinnerStateChanged(evt);
            }
        });

        javax.swing.GroupLayout numberPanelLayout = new javax.swing.GroupLayout(numberPanel);
        numberPanel.setLayout(numberPanelLayout);
        numberPanelLayout.setHorizontalGroup(
            numberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(numberPanelLayout.createSequentialGroup()
                .addGroup(numberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(numberPanelLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(decimalPlacesSpinner))
                    .addGroup(numberPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(numberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(xScientific)
                            .addComponent(yScientific))))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        numberPanelLayout.setVerticalGroup(
            numberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(numberPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(numberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(decimalPlacesSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(xScientific)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(yScientific)
                .addContainerGap(158, Short.MAX_VALUE))
        );

        appearancePanel.addTab("Number", numberPanel);

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

        javax.swing.GroupLayout tickSpacingPanelLayout = new javax.swing.GroupLayout(tickSpacingPanel);
        tickSpacingPanel.setLayout(tickSpacingPanelLayout);
        tickSpacingPanelLayout.setHorizontalGroup(
            tickSpacingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tickSpacingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tickSpacingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(tickSpacingPanelLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(xTickField))
                    .addGroup(tickSpacingPanelLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tickSpacingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(yTickField, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tickSpacingApplyButton))))
                .addContainerGap(120, Short.MAX_VALUE))
        );
        tickSpacingPanelLayout.setVerticalGroup(
            tickSpacingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tickSpacingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tickSpacingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(xTickField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tickSpacingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(yTickField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(tickSpacingApplyButton)
                .addContainerGap(147, Short.MAX_VALUE))
        );

        appearancePanel.addTab("Tick Spacing", tickSpacingPanel);

        jLabel2.setText("Axis Offset:");

        axisOffserSpinner.setModel(new javax.swing.SpinnerNumberModel(5.0d, -50.0d, 50.0d, 1.0d));
        axisOffserSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                axisOffserSpinnerStateChanged(evt);
            }
        });

        jButton2.setText("Axis Colour");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Axis Width:");

        axisOffserSpinner1.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(1.0f), Float.valueOf(0.0f), Float.valueOf(10.0f), Float.valueOf(0.25f)));
        axisOffserSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                axisOffserSpinner1StateChanged(evt);
            }
        });

        javax.swing.GroupLayout OffsetPanelLayout = new javax.swing.GroupLayout(OffsetPanel);
        OffsetPanel.setLayout(OffsetPanelLayout);
        OffsetPanelLayout.setHorizontalGroup(
            OffsetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OffsetPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OffsetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addGroup(OffsetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(OffsetPanelLayout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(axisOffserSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, OffsetPanelLayout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(axisOffserSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(119, Short.MAX_VALUE))
        );
        OffsetPanelLayout.setVerticalGroup(
            OffsetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OffsetPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OffsetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(axisOffserSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(OffsetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(axisOffserSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(159, Short.MAX_VALUE))
        );

        appearancePanel.addTab("Appearance", OffsetPanel);

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
                    .addComponent(appearancePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
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
                .addComponent(appearancePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed

        this.setVisible(false);
}//GEN-LAST:event_backButtonActionPerformed

    private void xScientificActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xScientificActionPerformed
        xAxisScientificNotation();
}//GEN-LAST:event_xScientificActionPerformed

    private void yScientificActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yScientificActionPerformed
        yAxisScientificNotation();
}//GEN-LAST:event_yScientificActionPerformed

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

    private void axisOffserSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_axisOffserSpinnerStateChanged

        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        double offset = Double.parseDouble(axisOffserSpinner.getValue().toString());
        inFocus.getXYPlot().setAxisOffset(new RectangleInsets(offset, offset, offset, offset));

}//GEN-LAST:event_axisOffserSpinnerStateChanged

    private void decimalPlacesSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_decimalPlacesSpinnerStateChanged
//        System.out.println("XXXXXXXXXXXXXXXXXX"+decimalPlacesSpinner.getValue().toString());
//        String str =decimalPlacesSpinner.getValue().toString();
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        decimal = "";
        int dec = (int) Integer.parseInt(decimalPlacesSpinner.getValue().toString());
        for (int i = 0; i < dec; i++) {
            decimal += "0";
        }
        inFocus.setDecimalPlaces(dec);
        if (xScientific.isSelected()) {
            xAxisScientificNotation();
        }
        if (yScientific.isSelected()) {
            yAxisScientificNotation();
        }
    }//GEN-LAST:event_decimalPlacesSpinnerStateChanged

    private void axisOffserSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_axisOffserSpinner1StateChanged
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;

        float axisWidth = Float.parseFloat(axisOffserSpinner1.getValue().toString());
        
        //axis stroke
        inFocus.getXYPlot().getDomainAxis().setAxisLineStroke(new BasicStroke(axisWidth));
        inFocus.getXYPlot().getRangeAxis().setAxisLineStroke(new BasicStroke(axisWidth));
       
        //tick unit stroke

        inFocus.getXYPlot().getDomainAxis().setTickMarkStroke(new BasicStroke(axisWidth));
        inFocus.getXYPlot().getRangeAxis().setTickMarkStroke(new BasicStroke(axisWidth));

        //setting axis Font
        inFocus.getXYPlot().getDomainAxis().setTickLabelFont(new Font("SansSerif",Font.PLAIN, (int)axisWidth+10));
        inFocus.getXYPlot().getRangeAxis().setTickLabelFont(new Font("SansSerif",Font.PLAIN, (int)axisWidth+10));

    }//GEN-LAST:event_axisOffserSpinner1StateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel OffsetPanel;
    private javax.swing.JTabbedPane appearancePanel;
    private javax.swing.JSpinner axisOffserSpinner;
    private javax.swing.JSpinner axisOffserSpinner1;
    private javax.swing.JButton backButton;
    private javax.swing.JSpinner decimalPlacesSpinner;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel numberPanel;
    private javax.swing.JButton tickSpacingApplyButton;
    private javax.swing.JPanel tickSpacingPanel;
    private javax.swing.JCheckBox xScientific;
    private javax.swing.JTextField xTickField;
    private javax.swing.JCheckBox yScientific;
    private javax.swing.JTextField yTickField;
    // End of variables declaration//GEN-END:variables
}
