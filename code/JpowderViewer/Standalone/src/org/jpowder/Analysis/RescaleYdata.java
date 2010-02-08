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
import java.util.List;
import java.util.Vector;
import javax.swing.JLabel;
import org.jfree.data.xy.XYDataset;
import org.jpowder.InfoPanel;
import org.jpowder.JPowder;
import org.jpowder.JpowderInternalframe;
import org.jpowder.dataset.DataSet;
import org.jpowder.jfreechart.FilesPlotter;

/**
 *
 * @author qyt21516
 */
public class RescaleYdata extends javax.swing.JPanel implements InfoPanel {

  private AnalysisIcon analysisIcon;
  private String[] string;
  private Vector vector= new Vector();

  Color color = (Color) FilesPlotter.getSeriescolors(0);

  /** Creates new form RescaleYdata */
  public RescaleYdata(AnalysisIcon analysisIcon) {
    initComponents();
    this.analysisIcon = analysisIcon;



  }

  public void update() {
    System.out.println("updattttttt");
    dataSetComboBox.setModel(new javax.swing.DefaultComboBoxModel(addDataSet()));


  }

  public JLabel getRescalLabel() {
    return rescaleLabel;
  }

  public void addingDataSetToComboBox() {
    JpowderInternalframe inFocus = JPowder.internalFrameInFocus;
    inFocus.getXYPlot().getLegendItems().get(0).getDescription();

//   Color color = (Color) FilesPlotter.getSeriescolors(0);
    Color color1 = (Color) FilesPlotter.getSeriescolors(1);
    Color color2 = (Color) FilesPlotter.getSeriescolors(2);

    dataSetComboBox.addItem(color);
    dataSetComboBox.addItem(color1);
    dataSetComboBox.addItem(color2);

//    dataSetComboBox.setRenderer(new ListCellRenderer() {
//
//      public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
//         if (value instanceof Color)
//      color= (Color) value;
//          return dataSetComboBox;
//      }
//    });
  }

  public String[] addDataSet() {
    JpowderInternalframe inFocus = JPowder.internalFrameInFocus;
    int size = inFocus.getXYPlot().getDatasetCount();
    string = new String[size];
    for (int i = 0; i < size; i++) {

      string[i] = inFocus.getXYPlot().getLegendItems().get(i).getDescription();

//      dataSetComboBox.setForeground((Color) FilesPlotter.allseriescolors[i]);
      dataSetComboBox.setBackground((Color) FilesPlotter.allseriescolors[i]);
      System.out.println("legend   " + string);
    }
    return string;
  }

  public void setVector(Vector nVector) {
    this.vector = nVector;
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
    dataSetComboBox.setFont(new java.awt.Font("Tahoma", 0, 10));

    OperationComboBox.setFont(new java.awt.Font("Tahoma", 1, 14));
    OperationComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "+", "-" }));

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
              .addGroup(layout.createSequentialGroup()
                .addComponent(rescaleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(jLabel1))
              .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
              .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dataSetComboBox, 0, 195, Short.MAX_VALUE))
              .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jLabel3)
                  .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                  .addComponent(constantField)
                  .addComponent(OperationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
              .addComponent(applyButt)))
          .addGroup(layout.createSequentialGroup()
            .addGap(92, 92, 92)
            .addComponent(Back)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1)
          .addComponent(rescaleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(applyButt)
        .addGap(50, 50, 50)
        .addComponent(Back)
        .addContainerGap(68, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed

      this.setVisible(false);
}//GEN-LAST:event_BackActionPerformed

    private void applyButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyButtActionPerformed
      JpowderInternalframe inFocus = JPowder.internalFrameInFocus;

      List chartList = new Vector<Double>();
      //Multiple series in case of series in a chart.
      int seriescount = inFocus.getXYPlot().getDatasetCount();
      for (int i = 0; i < seriescount; i++) {
        Vector<Double> Y = new Vector<Double>();

        DataSet oneDataset = new DataSet(vector, inFocus.getXYPlot().getLegendItems().get(0).getDescription()) {

          @Override
          public String description() {
            return "this is stupid method";
          }
        };
//        chartList.add(i, vector);
        System.out.println("chartList" + chartList);
        XYDataset ds = inFocus.getXYPlot().getDataset(i);

        for (int j = 0; j < ds.getItemCount(0); j++) {
          Y.add(ds.getYValue(0, j));


        }
        System.out.println("Y" + Y);
        for (int j = 0; j < ds.getItemCount(0); j++) {

          if (OperationComboBox.getSelectedItem().toString().equals("+")) {

            double newY = Y.elementAt(j) + Double.parseDouble(constantField.getText());
            vector.add(newY);
          }
          if (OperationComboBox.getSelectedItem().toString().equals("-")) {

            double newY = Y.elementAt(j) - Double.parseDouble(constantField.getText());
            vector.add(newY);
          }
          setVector(vector);
          oneDataset.setY(vector);
          System.out.println("newY" + vector);
        }
        chartList.add(i, vector);
      }
      System.out.println("chartList" + chartList + "elemment at" + chartList.get(0));
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

