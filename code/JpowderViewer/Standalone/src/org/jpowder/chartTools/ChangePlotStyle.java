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


import java.awt.geom.Rectangle2D;
import javax.swing.JLabel;
import org.jfree.chart.editor.ChartEditorManager;
import org.jfree.chart.editor.DefaultChartEditorFactory;
import org.jfree.chart.renderer.xy.XYErrorRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jpowder.InfoPanel;
import org.jpowder.JPowder;
import org.jpowder.JpowderInternalframe;
import org.jpowder.dataset.DataSetWithErrors;

/**
 *
 * @author qyt21516
 */
public class ChangePlotStyle extends javax.swing.JPanel implements InfoPanel {

  private ChartToolsIcon chartToolsIcon;



//   JpowderInternalframe inFocus = JPowder.internalFrameInFocus;
  /** Creates new form ChangePlotStyle */
  public ChangePlotStyle(ChartToolsIcon chartToolsIcon) {
    initComponents();
    this.chartToolsIcon = chartToolsIcon;

    }
 public JLabel getChangePlotStyleLabel(){
    return changePlotStyleLabel;
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
    changePlotStyleLabel = new javax.swing.JLabel();

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

    jCheckBox3.setText("Turn off the error bars");
    jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jCheckBox3ActionPerformed(evt);
      }
    });

    jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
    jLabel1.setText("Change The Plot Style");

    jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));

    jTextArea1.setBackground(new java.awt.Color(236, 233, 216));
    jTextArea1.setColumns(20);
    jTextArea1.setEditable(false);
    jTextArea1.setLineWrap(true);
    jTextArea1.setRows(5);
    jTextArea1.setText("Click on the Check Box to change the plotted data in the frame \nwhich is in focus.This is also \navaliable when you right click on\nthe frame.   ");
    jTextArea1.setWrapStyleWord(true);
    jTextArea1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(236, 233, 216), 1, true));
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
        .addContainerGap()
        .addComponent(changePlotStyleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
        .addComponent(jLabel1)
        .addContainerGap())
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
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel1)
          .addComponent(changePlotStyleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
        .addContainerGap(26, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents

  public void update() {
    JpowderInternalframe inFocus = JPowder.internalFrameInFocus;
    XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) inFocus.getXYPlot().getRenderer();

    if (inFocus.getPowderDataSet().get(0) instanceof DataSetWithErrors) {
      XYErrorRenderer renderer1 = (XYErrorRenderer) inFocus.getXYPlot().getRenderer();
      jCheckBox3.setSelected(!renderer1.getDrawYError());
    }
    jCheckBox1.setSelected(renderer.getBaseShapesVisible());
    jCheckBox2.setSelected(!renderer.getBaseLinesVisible());

  
if(JpowderInternalframe.getnumberOfJpowderInternalframe()==0){
    jCheckBox3.setSelected(false);
    jCheckBox1.setSelected(false);
    jCheckBox2.setSelected(false);
}

  }

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed

      JpowderInternalframe inFocus = JPowder.internalFrameInFocus;
      for (int i = 0; i < inFocus.getPowderDataSet().size(); i++) {
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) inFocus.getXYPlot().getRenderer(i);
        boolean status = renderer.getBaseShapesVisible();
      renderer.setSeriesShape( 0, new Rectangle2D.Double( -1.0, -1.0, 3.0, 3.0 ) );
//         renderer.setSeriesShape( 0, new Ellipse2D.Double( -1.0, -1.0, 3.0, 3.0 ) );
     
        renderer.setBaseShapesVisible(!status);
    

 
      }
      


    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
      JpowderInternalframe inFocus = JPowder.internalFrameInFocus;
      for (int i = 0; i < inFocus.getPowderDataSet().size(); i++) {
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) inFocus.getXYPlot().getRenderer(i);
        boolean status = renderer.getBaseLinesVisible();
        renderer.setBaseLinesVisible(!status);
      }
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
      JpowderInternalframe inFocus = JPowder.internalFrameInFocus;
      for (int i = 0; i < inFocus.getPowderDataSet().size(); i++) {

        if (inFocus.getPowderDataSet().get(i) instanceof DataSetWithErrors) {
          XYErrorRenderer renderer = (XYErrorRenderer) inFocus.getXYPlot().getRenderer(i);
          boolean status = renderer.getDrawYError();
          renderer.setDrawYError(!status);//show opposite Y error bar.
        }
      }
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed

      chartToolsIcon.setComponentZOrder(this, 1);
      chartToolsIcon.add(this);
      chartToolsIcon.setVisible(true);
      this.setVisible(false);

    }//GEN-LAST:event_BackActionPerformed
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton Back;
  private javax.swing.JLabel changePlotStyleLabel;
  private javax.swing.JCheckBox jCheckBox1;
  private javax.swing.JCheckBox jCheckBox2;
  private javax.swing.JCheckBox jCheckBox3;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JSeparator jSeparator1;
  private javax.swing.JTextArea jTextArea1;
  // End of variables declaration//GEN-END:variables
}
