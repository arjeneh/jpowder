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
import org.jpowder.chartTools.ChangePlotStyle;
import org.jpowder.chartTools.ZoomAndPan;

/**
 *
 * @author qyt21516
 */
public class ToolsIcon extends javax.swing.JPanel {

    private JpowderIcon jpowderIcon;
    private List<JButton> buttons = new ArrayList<JButton>();
    private String[] title = {"Selecting Peaks", "Rescale and Moving Plots","Changing Plot's Appearance", "Zooming Information"};
    private String[] imgdir = {"Peaks-Small.PNG",
        "Rescale-Small.PNG","Appearance-Small.PNG", "Zoom-Small.PNG"};
    private MarkPeakPosition markPeakPosition = new MarkPeakPosition(this);
    private RescaleYdata rescaleYdata = new RescaleYdata(this);
    private ChangePlotStyle changePlotStyle = new ChangePlotStyle(this);
    private ZoomAndPan zoomAndPan = new ZoomAndPan(this);
    private JPowder jpowder;

    /** Creates new form AnalysisIcon */
    public ToolsIcon(JPowder jpowder) {
        initComponents();
        this.jpowder = jpowder;
        buttons.add(peakButton);
        buttons.add(rescaleButton);
        buttons.add(appearanceButton);
        buttons.add(zoomButton);
  


        jpowderIcon = new JpowderIcon(buttons, imgdir, title);
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

        peakButton = new javax.swing.JButton();
        rescaleButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        zoomButton = new javax.swing.JButton();
        appearanceButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(320, 420));

        peakButton.setBackground(javax.swing.UIManager.getDefaults().getColor("CheckBox.highlight"));
        peakButton.setPreferredSize(new java.awt.Dimension(80, 80));
        peakButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                peakButtonActionPerformed(evt);
            }
        });

        rescaleButton.setPreferredSize(new java.awt.Dimension(80, 80));
        rescaleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rescaleButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel1.setText("Peak Position");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel2.setText("Rescale Plots");
        jLabel2.setToolTipText("");

        zoomButton.setBackground(javax.swing.UIManager.getDefaults().getColor("ComboBox.disabledBackground"));
        zoomButton.setForeground(new java.awt.Color(255, 255, 255));
        zoomButton.setOpaque(false);
        zoomButton.setPreferredSize(new java.awt.Dimension(80, 80));
        zoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomButtonActionPerformed(evt);
            }
        });

        appearanceButton.setBackground(javax.swing.UIManager.getDefaults().getColor("ComboBox.disabledBackground"));
        appearanceButton.setOpaque(false);
        appearanceButton.setPreferredSize(new java.awt.Dimension(80, 80));
        appearanceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appearanceButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel3.setText("Appearance");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel4.setText("Zoom ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(32, 32, 32)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(36, 36, 36)
                            .addComponent(jLabel2, 0, 0, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(appearanceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                    .addComponent(zoomButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(peakButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(26, 26, 26)
                                    .addComponent(rescaleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel3)
                        .addGap(56, 56, 56)
                        .addComponent(jLabel4)))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {appearanceButton, peakButton, rescaleButton, zoomButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rescaleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(peakButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(appearanceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zoomButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(136, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {appearanceButton, peakButton, rescaleButton, zoomButton});

    }// </editor-fold>//GEN-END:initComponents

    private void peakButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_peakButtonActionPerformed
//
//        Object source = evt.getSource();
//        if (source instanceof JButton) {
//            JButton button = (JButton) source;
//
//
//            markPeakPosition.getPeakLabel().setIcon(button.getIcon());
//        }
        jpowder.getanalysistab().add(markPeakPosition, "1");
        markPeakPosition.setVisible(true);
        this.setVisible(false);
        JPowder.jpowderInfoPanelUpdate(markPeakPosition);

    }//GEN-LAST:event_peakButtonActionPerformed

    private void rescaleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rescaleButtonActionPerformed

//        Object source = evt.getSource();
//        if (source instanceof JButton) {
//            JButton button = (JButton) source;
//            rescaleYdata.getRescalLabel().setIcon(button.getIcon());
//        }

        jpowder.getanalysistab().add(rescaleYdata, "1");
        rescaleYdata.setVisible(true);
        this.setVisible(false);
        JPowder.jpowderInfoPanelUpdate(rescaleYdata);
    }//GEN-LAST:event_rescaleButtonActionPerformed

    private void zoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoomButtonActionPerformed

        jpowder.getanalysistab().add(zoomAndPan, "1");
        zoomAndPan.setVisible(true);
        this.setVisible(false);
        JPowder.jpowderInfoPanelUpdate(zoomAndPan);
}//GEN-LAST:event_zoomButtonActionPerformed

    private void appearanceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appearanceButtonActionPerformed
 
        jpowder.getanalysistab().add(changePlotStyle, "1");
        changePlotStyle.setVisible(true);
        this.setVisible(false);

        JPowder.jpowderInfoPanelUpdate(changePlotStyle);
}//GEN-LAST:event_appearanceButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton appearanceButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton peakButton;
    private javax.swing.JButton rescaleButton;
    private javax.swing.JButton zoomButton;
    // End of variables declaration//GEN-END:variables
}
