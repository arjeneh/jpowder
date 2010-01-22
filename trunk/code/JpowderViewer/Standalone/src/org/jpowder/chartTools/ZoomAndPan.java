/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ZoomAndPan.java
 *
 * Created on Dec 17, 2009, 2:53:21 PM
 */
package org.jpowder.chartTools;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.ButtonGroup;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.Range;
import org.jpowder.JPowder;
import org.jpowder.JpowderInternalframe;
import org.jpowder.dataset.DatasetPlotter;



/**
 *
 * @author Arjeneh
 */
public class ZoomAndPan extends javax.swing.JPanel {

  


  private ChartToolsIcon chartToolsIcon;
  private static final double ZOOM_FACTOR = 10000;
  private ButtonGroup checkboxGroup = new ButtonGroup();
  private ButtonGroup buttonGroup = new ButtonGroup();




  /**
   *
   * @param chartToolsIcon
   */
  public ZoomAndPan(ChartToolsIcon chartToolsIcon) {
    initComponents();
    this.chartToolsIcon = chartToolsIcon;
  }

    /**
     * Sets the pan mode.
     *
     * @param val  a boolean.
     */
    private void setPanMode(boolean val) {

        JpowderInternalframe inFocus = JPowder.internalFrameInFocus;
        if (val) {
            inFocus.getChartPanel().setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
     
        }
        else {
            inFocus.getChartPanel().setCursor(Cursor.getDefaultCursor());
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

    jLabel1 = new javax.swing.JLabel();
    jSeparator1 = new javax.swing.JSeparator();
    backButt = new javax.swing.JButton();
    jToggleButton2 = new javax.swing.JToggleButton();
    panButt = new javax.swing.JToggleButton();
    fitButt = new javax.swing.JToggleButton();
    jCheckBoxDomain = new javax.swing.JCheckBox();
    jCheckBoxRange = new javax.swing.JCheckBox();
    jCheckBoxBoth = new javax.swing.JCheckBox();
    zoomIn = new javax.swing.JToggleButton();
    zoomOut = new javax.swing.JToggleButton();

    setPreferredSize(new java.awt.Dimension(297, 347));

    jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
    jLabel1.setText("Zoom And Pan");

    backButt.setText("Back");
    backButt.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        backButtActionPerformed(evt);
      }
    });

    jToggleButton2.setText("pan");

    panButt.setText("Pan");
    panButt.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        panButtActionPerformed(evt);
      }
    });

    fitButt.setText("Fit");
    fitButt.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        fitButtActionPerformed(evt);
      }
    });

    checkboxGroup
    .add(jCheckBoxDomain);
    jCheckBoxDomain.setText("Zoom in X Axis");

    checkboxGroup.add(jCheckBoxRange);
    jCheckBoxRange.setText("Zoom in Y Axis");

    checkboxGroup.add(jCheckBoxBoth);
    jCheckBoxBoth.setText("Zoom in Both Axis");

    buttonGroup.add(zoomIn);
    zoomIn.setFont(new java.awt.Font("Arial Narrow", 1, 12)); // NOI18N
    zoomIn.setText("+");
    zoomIn.setMinimumSize(new java.awt.Dimension(30, 25));
    zoomIn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        zoomInActionPerformed(evt);
      }
    });

    buttonGroup.add(zoomOut);
    zoomOut.setFont(new java.awt.Font("Arial Narrow", 1, 12)); // NOI18N
    zoomOut.setText("-");
    zoomOut.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        zoomOutActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGap(144, 144, 144)
            .addComponent(jLabel1))
          .addGroup(layout.createSequentialGroup()
            .addGap(39, 39, 39)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jCheckBoxBoth)
              .addComponent(jCheckBoxRange)
              .addComponent(jCheckBoxDomain)))
          .addGroup(layout.createSequentialGroup()
            .addGap(19, 19, 19)
            .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(layout.createSequentialGroup()
            .addGap(104, 104, 104)
            .addComponent(backButt))
          .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(fitButt, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, 0)
            .addComponent(panButt, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(zoomOut)
            .addGap(0, 0, 0)
            .addComponent(zoomIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addGap(19, 19, 19))
    );

    layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {zoomIn, zoomOut});

    layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {fitButt, panButt});

    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel1)
        .addGap(27, 27, 27)
        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGap(95, 95, 95)
            .addComponent(jToggleButton2))
          .addGroup(layout.createSequentialGroup()
            .addGap(18, 18, 18)
            .addComponent(jCheckBoxDomain)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jCheckBoxRange)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jCheckBoxBoth)))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(fitButt)
          .addComponent(panButt)
          .addComponent(zoomOut)
          .addComponent(zoomIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(100, 100, 100)
        .addComponent(backButt)
        .addGap(26, 26, 26))
    );

    layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {zoomIn, zoomOut});

  }// </editor-fold>//GEN-END:initComponents

    private void backButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtActionPerformed
      System.out.println("backbutt has been cliked");
      chartToolsIcon.setComponentZOrder(this, 1);
      chartToolsIcon.add(this);
      chartToolsIcon.setVisible(true);

    }//GEN-LAST:event_backButtActionPerformed

    private void panButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_panButtActionPerformed

      if(panButt.isSelected()){
      setPanMode(true);
    }
      
      if(!panButt.isSelected()){
      setPanMode(false);
    }
    }//GEN-LAST:event_panButtActionPerformed

    private void fitButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fitButtActionPerformed
      JpowderInternalframe inFocus = JPowder.internalFrameInFocus;
        inFocus.getChartPanel().restoreAutoBounds();
        fitButt.setSelected(false);
    }//GEN-LAST:event_fitButtActionPerformed

    private void zoomOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoomOutActionPerformed
    JpowderInternalframe inFocus = JPowder.internalFrameInFocus;
      if(jCheckBoxDomain.isSelected()){

     
        inFocus.getChartPanel().zoomOutDomain(0,0);

    

      }
     if(jCheckBoxRange.isSelected()){
      inFocus.getChartPanel().zoomOutRange(ZOOM_FACTOR, ZOOM_FACTOR);
      }
     if(jCheckBoxBoth.isSelected()){
      inFocus.getChartPanel().zoomOutBoth(ZOOM_FACTOR, ZOOM_FACTOR);
      }

    
    }//GEN-LAST:event_zoomOutActionPerformed

    private void zoomInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoomInActionPerformed
          JpowderInternalframe inFocus = JPowder.internalFrameInFocus;
      if(jCheckBoxDomain.isSelected()){
      inFocus.getChartPanel().zoomInDomain(0,0);
         inFocus.getChartPanel().setZoomInFactor(0.99);

    

      }
           if(jCheckBoxRange.isSelected()){
      inFocus.getChartPanel().zoomInRange(ZOOM_FACTOR, ZOOM_FACTOR);
      }
           if(jCheckBoxBoth.isSelected()){
      inFocus.getChartPanel().zoomInBoth(ZOOM_FACTOR, ZOOM_FACTOR);
      }
}//GEN-LAST:event_zoomInActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton backButt;
  private javax.swing.JToggleButton fitButt;
  private javax.swing.JCheckBox jCheckBoxBoth;
  private javax.swing.JCheckBox jCheckBoxDomain;
  private javax.swing.JCheckBox jCheckBoxRange;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JSeparator jSeparator1;
  private javax.swing.JToggleButton jToggleButton2;
  private javax.swing.JToggleButton panButt;
  private javax.swing.JToggleButton zoomIn;
  private javax.swing.JToggleButton zoomOut;
  // End of variables declaration//GEN-END:variables
}
