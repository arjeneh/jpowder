/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ChangePlotStyle.java
 *
 * Created on 10-Dec-2009, 14:13:58
 */

package org.jpowder.chartTools;

import org.jfree.chart.renderer.xy.XYErrorRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;



/**
 *
 * @author qyt21516
 */
public class ChangePlotStyle extends javax.swing.JPanel {

    private org.jfree.chart.plot.XYPlot plot;
    private ChartToolsIcon chartToolsIcon;
    /** Creates new form ChangePlotStyle */
    public ChangePlotStyle(ChartToolsIcon chartToolsIcon) {
        initComponents();
        this.chartToolsIcon=chartToolsIcon;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jCheckBox1 = new javax.swing.JCheckBox();
    jCheckBox2 = new javax.swing.JCheckBox();
    jCheckBox3 = new javax.swing.JCheckBox();
    jLabel1 = new javax.swing.JLabel();
    jSeparator1 = new javax.swing.JSeparator();
    jScrollPane1 = new javax.swing.JScrollPane();
    jTextArea1 = new javax.swing.JTextArea();
    Back = new javax.swing.JButton();

    jCheckBox1.setText("Turn on the markers");
    jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jCheckBox1ActionPerformed(evt);
      }
    });

    jCheckBox2.setText("Turn off the connecting line");
    jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jCheckBox2ActionPerformed(evt);
      }
    });

    jCheckBox3.setText("Turn off the error bar");
    jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jCheckBox3ActionPerformed(evt);
      }
    });

    jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
    jLabel1.setText("Change The Plot Style");

    jTextArea1.setBackground(new java.awt.Color(236, 233, 216));
    jTextArea1.setColumns(20);
    jTextArea1.setEditable(false);
    jTextArea1.setLineWrap(true);
    jTextArea1.setRows(5);
    jTextArea1.setText("Click on the Check Box to change the plotted data in the frame \nwhich is in focus.This is also \navaliable when you right click on\nthe frame.   ");
    jTextArea1.setOpaque(false);
    jScrollPane1.setViewportView(jTextArea1);

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
      .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap(129, Short.MAX_VALUE)
        .addComponent(jLabel1)
        .addGap(45, 45, 45))
      .addGroup(layout.createSequentialGroup()
        .addGap(10, 10, 10)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jCheckBox2)
          .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jCheckBox3))
        .addContainerGap())
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
        .addContainerGap())
      .addGroup(layout.createSequentialGroup()
        .addGap(105, 105, 105)
        .addComponent(Back)
        .addContainerGap(137, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(34, 34, 34)
        .addComponent(jLabel1)
        .addGap(27, 27, 27)
        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jCheckBox1)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jCheckBox2)
        .addGap(3, 3, 3)
        .addComponent(jCheckBox3)
        .addGap(18, 18, 18)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(Back)
        .addContainerGap(16, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
     org.jfree.chart.renderer.xy.XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) getPlot().getRenderer();
        boolean status = renderer.getBaseShapesVisible();
        System.out.println("the marker is turn on");
        renderer.setBaseShapesVisible(!status);
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
     org.jfree.chart.renderer.xy.XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) getPlot().getRenderer();
        boolean status = renderer.getBaseLinesVisible();
        System.out.println("the bae line is set visible");
        renderer.setBaseLinesVisible(!status);
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
          org.jfree.chart.renderer.xy.XYErrorRenderer renderer = (XYErrorRenderer) getPlot().getRenderer();
                boolean status = renderer.getDrawYError();
                System.out.println("Render Error bar is " + status);
                renderer.setDrawYError(!status);//show opposite Y error bar.
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed

      chartToolsIcon.getChartToolsIcon().remove(this);
//      this.setComponentZOrder( chartToolsIcon, 0);
//      this.validate();
//      this.repaint();


    }//GEN-LAST:event_BackActionPerformed
  public org.jfree.chart.plot.XYPlot getPlot() {
    return plot;
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton Back;
  private javax.swing.JCheckBox jCheckBox1;
  private javax.swing.JCheckBox jCheckBox2;
  private javax.swing.JCheckBox jCheckBox3;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JSeparator jSeparator1;
  private javax.swing.JTextArea jTextArea1;
  // End of variables declaration//GEN-END:variables

}
