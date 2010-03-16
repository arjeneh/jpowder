/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RescaleYdata.java
 *
 * Created on 02-Feb-2010, 15:08:00
 */
package org.jpowder.Analysis;

import java.awt.Color;
import java.awt.Component;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;
import org.jfree.data.xy.XYDataset;
import org.jpowder.InfoPanel;
import org.jpowder.JPowder;
import org.jpowder.JpowderInternalframe;
import org.jpowder.jfreechart.FilesPlotter;

/**
 *
 * @author qyt21516
 */
public class RescaleYdata extends javax.swing.JPanel implements InfoPanel {

    private AnalysisIcon analysisIcon;
    private String[] string;
    private int index;
    private Color color = (Color) FilesPlotter.getSeriescolors(0);

    /** Creates new form RescaleYdata */
    public RescaleYdata(AnalysisIcon analysisIcon) {
        initComponents();
        this.analysisIcon = analysisIcon;



    }

    public void update() {
        System.out.println("updattttttt");
        dataSetComboBox.setModel(new javax.swing.DefaultComboBoxModel(addDataSet()));


    }
/**
 * This Label is for adding the
 * @return
 */
    public JLabel getRescalLabel() {
        return rescaleLabel;
    }

    public String[] addDataSet() {
        JpowderInternalframe inFocus = JPowder.internalFrameInFocus;
        int size = inFocus.getXYPlot().getDatasetCount();
        string = new String[size];
        for (int i = 0; i < size; i++) {
            string[i] = inFocus.getPowderDataSet().elementAt(i).getFileName();


//  dataSetComboBox.setForeground((Color) FilesPlotter.allseriescolors[i]);
            dataSetComboBox.setRenderer(new ListCellRenderer() {

                public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

                    JLabel j = new JLabel(value.toString());
                    j.setOpaque(isSelected);
                    if (index >= 0) {
                        j.setForeground((Color) FilesPlotter.allseriescolors[index]);
//                        j.setBackground((Color) FilesPlotter.allseriescolors[index]);
                    }

                    if (isSelected) {
                        j.setBorder(LineBorder.createBlackLineBorder());
                    
                    }


                    return j;


                }
            });

//    dataSetComboBox.setBackground((Color) FilesPlotter.allseriescolors[i]);

        }
        return string;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        dataSetComboBox = new javax.swing.JComboBox();
        OperationComboBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        constantField = new javax.swing.JTextField();
        applyButt = new javax.swing.JButton();
        Back = new javax.swing.JButton();
        rescaleLabel = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Rescale YData");

        dataSetComboBox.setEditable(true);
        dataSetComboBox.setFont(new java.awt.Font("Tahoma", 1, 10));
        dataSetComboBox.setMaximumRowCount(20);

        OperationComboBox.setFont(new java.awt.Font("Tahoma", 1, 12));
        OperationComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "+", "-", "x", "/" }));

        jLabel2.setText("DataSet:");

        jLabel3.setText("Operation:");

        jLabel4.setText("Constant:");

        constantField.setText("1");

        applyButt.setText("Apply");
        applyButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyButtActionPerformed(evt);
            }
        });

        Back.setText("Back");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(13, 13, 13)
                                .addComponent(dataSetComboBox, 0, 206, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(constantField)
                                    .addComponent(OperationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rescaleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(13, 13, 13))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(Back))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(applyButt)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rescaleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(dataSetComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(OperationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(constantField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(applyButt)
                .addGap(46, 46, 46)
                .addComponent(Back)
                .addContainerGap(70, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed

        this.setVisible(false);
}//GEN-LAST:event_BackActionPerformed

    private void applyButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyButtActionPerformed
        JpowderInternalframe inFocus = JPowder.internalFrameInFocus;

        int seriescount = inFocus.getXYPlot().getDatasetCount();
        for (int i = 0; i < seriescount; i++) {
            if (inFocus.getPowderDataSet().elementAt(i).getFileName().equals(
                    dataSetComboBox.getSelectedItem())) {
                inFocus.getPowderDataSet().elementAt(i).getY();
                XYDataset ds = inFocus.getXYPlot().getDataset(i);
                for (int j = 0; j < ds.getItemCount(i); j++) {

                    Double y = (Double) inFocus.getPowderDataSet().elementAt(i).getY().get(j);
                    double newY = Double.parseDouble(constantField.getText());
                    if (OperationComboBox.getSelectedItem().toString().equals("+")) {

                        inFocus.getPowderDataSet().elementAt(i).getY().setElementAt(y + newY, j);
                    }
                    if (OperationComboBox.getSelectedItem().toString().equals("-")) {

                        inFocus.getPowderDataSet().elementAt(i).getY().setElementAt(y - newY, j);
                    }
                    if (OperationComboBox.getSelectedItem().toString().equals("x")) {

                        inFocus.getPowderDataSet().elementAt(i).getY().setElementAt(y * newY, j);
                    }
                    if (OperationComboBox.getSelectedItem().toString().equals("/")) {

                        inFocus.getPowderDataSet().elementAt(i).getY().setElementAt(y / newY, j);
                    }
                }
            }

        }
//        inFocus.getChartPanel().restoreAutoBounds();
        inFocus.getChartPanel().restoreAutoRangeBounds();
        inFocus.getchart().setNotify(true);

    }//GEN-LAST:event_applyButtActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back;
    private javax.swing.JComboBox OperationComboBox;
    private javax.swing.JButton applyButt;
    private javax.swing.JTextField constantField;
    private javax.swing.JComboBox dataSetComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel rescaleLabel;
    // End of variables declaration//GEN-END:variables
}

