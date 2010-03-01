/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AnalysisIcon.java
 *
 * Created on 11-Dec-2009, 11:31:51
 */
package org.jpowder.Analysis;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import org.jpowder.JPowder;
import org.jpowder.JpowderIcon;

/**
 *
 * @author qyt21516
 */
public class AnalysisIcon extends javax.swing.JPanel {

    private JpowderIcon jpowderIcon;
    private List<JButton> buttons = new ArrayList<JButton>();
    private String[] title = {"Logo", "PlotChart"};
    private String[] imgdir = {"PlotChart.gif",
        "printer.gif"};
    private MarkPeakPosition markPeakPosition = new MarkPeakPosition(this);
    private RescaleYdata rescaleYdata = new RescaleYdata(this);
    private JPowder jpowder;

    /** Creates new form AnalysisIcon */
    public AnalysisIcon(JPowder jpowder) {
        initComponents();
        this.jpowder = jpowder;
        buttons.add(AnalysisButton);
        buttons.add(AnalysisButton1);


        jpowderIcon = new JpowderIcon(buttons, title, imgdir);
        jpowderIcon.set_imgdir(imgdir);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    AnalysisButton = new javax.swing.JButton();
    AnalysisButton1 = new javax.swing.JButton();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();

    AnalysisButton.setPreferredSize(new java.awt.Dimension(80, 80));
    AnalysisButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        AnalysisButtonActionPerformed(evt);
      }
    });

    AnalysisButton1.setPreferredSize(new java.awt.Dimension(80, 80));
    AnalysisButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        AnalysisButton1ActionPerformed(evt);
      }
    });

    jLabel1.setText("Peak Position");

    jLabel2.setText("Rescale Ydata");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(24, 24, 24)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(layout.createSequentialGroup()
            .addComponent(AnalysisButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(26, 26, 26)
            .addComponent(AnalysisButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addGap(47, 47, 47))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(AnalysisButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(AnalysisButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(14, 14, 14)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1)
          .addComponent(jLabel2))
        .addContainerGap(220, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents

    private void AnalysisButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnalysisButtonActionPerformed
        System.out.println("analysisbutt is clicked");
//         jpowderIcon.setHeight(35);
//        jpowderIcon.setWidth(35);
        Object source = evt.getSource();
        if (source instanceof JButton) {
            JButton button = (JButton) source;


            markPeakPosition.getPeakLabel().setIcon(button.getIcon());
        }
        jpowder.getanalysistab().add(markPeakPosition, "1");
        markPeakPosition.setVisible(true);
        this.setVisible(false);
        JPowder.jpowderInfoPanelUpdate(markPeakPosition);

    }//GEN-LAST:event_AnalysisButtonActionPerformed

    private void AnalysisButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnalysisButton1ActionPerformed

        Object source = evt.getSource();
        if (source instanceof JButton) {
            JButton button = (JButton) source;
            rescaleYdata.getRescalLabel().setIcon(button.getIcon());
        }

        jpowder.getanalysistab().add(rescaleYdata, "1");
        rescaleYdata.setVisible(true);
        this.setVisible(false);
        JPowder.jpowderInfoPanelUpdate(rescaleYdata);
    }//GEN-LAST:event_AnalysisButton1ActionPerformed
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton AnalysisButton;
  private javax.swing.JButton AnalysisButton1;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  // End of variables declaration//GEN-END:variables
}
