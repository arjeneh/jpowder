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
 * RescaleYdata.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *             Anders Marvardsen, ISIS, Rutherford Appleton Laboratory
 *             Dan Badham, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.Analysis;

import java.util.Collections;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.jfree.data.xy.XYDataset;
import org.jpowder.InfoPanel;
import org.jpowder.Jpowder;
import org.jpowder.InternalFrame.JpowderInternalframe2D;
import org.jpowder.chartTools.ComboBoxRenderer;

/**
 * Move and rescale a plot along the y axis.
 * 
 */
public class RescaleYdata extends javax.swing.JPanel implements InfoPanel {

    private ToolsIcon2D toolsIcon;
    private String[] string;

    /** Creates new form RescaleYdata */
    public RescaleYdata(ToolsIcon2D analysisIcon) {
        initComponents();
        this.toolsIcon = analysisIcon;
    }

    public void update() {

        if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() != 0) {
            dataSetComboBox.setModel(new javax.swing.DefaultComboBoxModel(addDataSet()));
        }
        if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() == 0) {
            String labels[] = {"No Chart Added"};
            dataSetComboBox.setModel(new javax.swing.DefaultComboBoxModel(labels));
        }
    }

    /**
     * This Label is for adding the
     * @return rescaled label
     */
    public JLabel getRescaleLabel() {
        return rescaleLabel;
    }

    public String[] addDataSet() {
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        int size = inFocus.getXYPlot().getDatasetCount();
        string = new String[size];
        for (int i = 0; i < size; i++) {
            string[i] = inFocus.getPowderDataSet().elementAt(i).getFileName();

            dataSetComboBox.setRenderer(new ComboBoxRenderer());
        }
        return string;
    }

    public void applyRescaling() {
        if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() == 0) {
            return;
        }
        for (int i = 0; i < 4; i++) {
            String[] zero = {"0", "0.0", ".0", "0."};
            if (constantField.getText().equals(zero[i])) {
                JOptionPane.showMessageDialog(null, "Enter valid number.");
                return;
            }
        }
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        int seriescount = inFocus.getXYPlot().getDatasetCount();
        for (int i = 0; i < seriescount; i++) {
            if (inFocus.getPowderDataSet().elementAt(i).getFileName().equals(
                    dataSetComboBox.getSelectedItem())) {
                inFocus.getPowderDataSet().elementAt(i).getY();
                XYDataset ds = inFocus.getXYPlot().getDataset(i);
                for (int j = 0; j < ds.getItemCount(i); j++) {
                    try {
                        Double y = (Double) inFocus.getPowderDataSet().elementAt(i).getY().get(j);
                        double newY = Double.parseDouble(constantField.getText());
                        if (operationComboBox.getSelectedItem().toString().equals("+")) {

                            inFocus.getPowderDataSet().elementAt(i).getY().setElementAt(y + newY, j);
                        }
                        if (operationComboBox.getSelectedItem().toString().equals("-")) {

                            inFocus.getPowderDataSet().elementAt(i).getY().setElementAt(y - newY, j);
                        }
                        if (operationComboBox.getSelectedItem().toString().equals("x")) {

                            inFocus.getPowderDataSet().elementAt(i).getY().setElementAt(y * newY, j);
                        }
                        if (operationComboBox.getSelectedItem().toString().equals("/")) {

                            inFocus.getPowderDataSet().elementAt(i).getY().setElementAt(y / newY, j);
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Enter valid number.");
                        return;
                    }
                }
            }
        }
        inFocus.getChartPanel().restoreAutoRangeBounds();
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
        dataSetComboBox = new javax.swing.JComboBox();
        operationComboBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        constantField = new javax.swing.JTextField();
        backButton = new javax.swing.JButton();
        rescaleLabel = new javax.swing.JLabel();
        autoRescaling = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(320, 400));

        dataSetComboBox.setEditable(true);
        dataSetComboBox.setMaximumRowCount(20);
        dataSetComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataSetComboBoxActionPerformed(evt);
            }
        });

        operationComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "+", "-", "x", "/" }));
        operationComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                operationComboBoxActionPerformed(evt);
            }
        });

        jLabel2.setText("Plot (s):");

        jLabel3.setText("Operation:");

        jLabel4.setText("Constant:");

        constantField.setText("1");
        constantField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                constantFieldActionPerformed(evt);
            }
        });

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

        rescaleLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Rescale_Large.png"))); // NOI18N
        rescaleLabel.setToolTipText("Rescale And Move The Plot");

        autoRescaling.setText("Auto Rescaling");
        autoRescaling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoRescalingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rescaleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dataSetComboBox, 0, 244, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(constantField, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(operationComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 41, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(autoRescaling)
                .addContainerGap(205, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(rescaleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dataSetComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(operationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(constantField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(27, 27, 27)
                .addComponent(autoRescaling)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed

        this.setVisible(false);
}//GEN-LAST:event_backButtonActionPerformed

    private void dataSetComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataSetComboBoxActionPerformed
        applyRescaling();
    }//GEN-LAST:event_dataSetComboBoxActionPerformed

    private void operationComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_operationComboBoxActionPerformed
        applyRescaling();
    }//GEN-LAST:event_operationComboBoxActionPerformed

    private void constantFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_constantFieldActionPerformed
        applyRescaling();
    }//GEN-LAST:event_constantFieldActionPerformed

    private void autoRescalingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autoRescalingActionPerformed
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        double maxY, minY, resultant;
        Vector<Double> peaks = new Vector<Double>();
        for (int i = 0; i < inFocus.getXYPlot().getDatasetCount(); i++) {

            maxY = (Double) Collections.max(inFocus.getPowderDataSet().elementAt(i).getY());
            minY = (Double) Collections.min(inFocus.getPowderDataSet().elementAt(i).getY());
            peaks.add(minY);

        }

//           for(double item:peaks)
        resultant = Collections.max(peaks) - Collections.min(peaks);
//        System.out.println("X2 : " + Collections.max(peaks));
//        System.out.println("X1 : " + Collections.min(peaks));
//        System.out.println("Resultant : " + resultant);



        int seriescount = inFocus.getXYPlot().getDatasetCount();
        for (int i = 0; i < seriescount; i++) {

            inFocus.getPowderDataSet().elementAt(i).getY();
            XYDataset ds = inFocus.getXYPlot().getDataset(i);
            for (int j = 0; j < ds.getItemCount(i); j++) {
                try {
                    Double y = (Double) inFocus.getPowderDataSet().elementAt(i).getY().get(j);
                    double newY = y-resultant;
                    if(newY>=0){
                    inFocus.getPowderDataSet().elementAt(i).getY().setElementAt(newY, j);
                    }

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Enter valid number.");
                    return;
                }
            }

  inFocus.getChartPanel().restoreAutoRangeBounds();
        }

    }//GEN-LAST:event_autoRescalingActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton autoRescaling;
    private javax.swing.JButton backButton;
    private javax.swing.JTextField constantField;
    private javax.swing.JComboBox dataSetComboBox;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox operationComboBox;
    private javax.swing.JLabel rescaleLabel;
    // End of variables declaration//GEN-END:variables
}

