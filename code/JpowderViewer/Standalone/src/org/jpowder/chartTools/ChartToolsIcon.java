/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ChartToolsIcon.java
 *
 * Created on 11-Dec-2009, 10:59:18
 */
package org.jpowder.chartTools;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.jpowder.JPowder;
import org.jpowder.JpowderIcon;

/**
 *
 * @author qyt21516
 */
public class ChartToolsIcon extends javax.swing.JPanel {

  private JpowderIcon jpowderIcon;
  private List<JButton> buttons = new ArrayList<JButton>();
  private String[] title = {"Logo.gif", "PlotChart.gif",
    "printer.gif"};
  private String[] imgdir = {"Logo.gif", "PlotChart.gif",
    "printer.gif"};
  private ChangePlotStyle changePlotStyle = new ChangePlotStyle(this);
  private ZoomAndPan zoomAndPan = new ZoomAndPan(this);
  private JPowder jpowder;

  /** Creates new form ChartToolsIcon */
  public ChartToolsIcon(JPowder jpowder) {
    initComponents();
    this.jpowder = jpowder;
    buttons.add(chartToolButton);
    buttons.add(chartToolButton1);
    buttons.add(chartToolButton2);


    jpowderIcon = new JpowderIcon(buttons, title, imgdir);
    jpowderIcon.set_imgdir(imgdir);
  }

  public JPanel getChartToolsIcon() {
    return this;
  }

  /**
   * This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel2 = new javax.swing.JLabel();
    jLabel1 = new javax.swing.JLabel();
    chartToolButton = new javax.swing.JButton();
    chartToolButton2 = new javax.swing.JButton();
    chartToolButton1 = new javax.swing.JButton();
    jLabel3 = new javax.swing.JLabel();

    setOpaque(false);

    jLabel2.setText("Zoom And Pan");

    jLabel1.setText("Change plot style");

    chartToolButton.setPreferredSize(new java.awt.Dimension(80, 80));
    chartToolButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        chartToolButtonActionPerformed(evt);
      }
    });

    chartToolButton2.setPreferredSize(new java.awt.Dimension(80, 80));

    chartToolButton1.setPreferredSize(new java.awt.Dimension(80, 80));
    chartToolButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        chartToolButton1ActionPerformed(evt);
      }
    });

    jLabel3.setText("Set Legend");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(20, 20, 20)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(20, 20, 20)
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(chartToolButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(chartToolButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(30, 30, 30)
            .addComponent(chartToolButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
          .addGroup(layout.createSequentialGroup()
            .addComponent(chartToolButton, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(13, 13, 13))
          .addGroup(layout.createSequentialGroup()
            .addComponent(chartToolButton1, 0, 0, Short.MAX_VALUE)
            .addGap(10, 10, 10)))
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel1)
          .addComponent(jLabel2))
        .addGap(9, 9, 9)
        .addComponent(chartToolButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel3)
        .addContainerGap(74, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents

    private void chartToolButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chartToolButtonActionPerformed
      System.out.println("hi chartToolButton clicked");

      jpowder.getChartToolstab().add(changePlotStyle, "1");
      jpowder.getChartToolstab().setComponentZOrder(changePlotStyle, 0);
      jpowder.getCardLayout().first(jpowder.getChartToolstab());
      changePlotStyle.setVisible(true);

      JPowder.jpowderInfoPanelUpdate(changePlotStyle);
    }//GEN-LAST:event_chartToolButtonActionPerformed

    private void chartToolButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chartToolButton1ActionPerformed

      jpowder.getChartToolstab().add(zoomAndPan, "1");
      jpowder.getChartToolstab().setComponentZOrder(zoomAndPan, 0);
      jpowder.getCardLayout().first(jpowder.getChartToolstab());
      zoomAndPan.setVisible(true);
    }//GEN-LAST:event_chartToolButton1ActionPerformed
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton chartToolButton;
  private javax.swing.JButton chartToolButton1;
  private javax.swing.JButton chartToolButton2;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  // End of variables declaration//GEN-END:variables
}
