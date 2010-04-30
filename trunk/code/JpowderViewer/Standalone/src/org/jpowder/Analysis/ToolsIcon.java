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
import org.jpowder.Jpowder;
import org.jpowder.JpowderIcon;
import org.jpowder.chartTools.ChangePlotStyle;
import org.jpowder.chartTools.ZoomAndPan;

/**
 * this class contains all the Jpowder icons.
 * @author M Arjeneh
 */
public class ToolsIcon extends javax.swing.JPanel {

    private JpowderIcon jpowderIcon;
    private List<JButton> buttons = new ArrayList<JButton>();
    private String[] title = {"Selecting Peaks", "Rescale and Moving Plots","Changing Plot's Appearance", "Zooming Information","Apply Bragg's Law To Change The XAxis"};
    private String[] imgdir = {"Peaks_Small.png", "Rescale_Small.png","Appearance_Small.png", "Zoom_Small.png","ChangeXAxis_Small.png"};
    private MarkPeakPosition markPeakPosition = new MarkPeakPosition(this);
    private RescaleYdata rescaleYdata = new RescaleYdata(this);
    private ChangePlotStyle changePlotStyle = new ChangePlotStyle(this);
    private ZoomAndPan zoomAndPan = new ZoomAndPan(this);
    private BraggsLaw braggsLow= new BraggsLaw(this);
    private Jpowder jpowder;

    /** Creates new form AnalysisIcon */
    public ToolsIcon(Jpowder jpowder) {
        initComponents();
        this.jpowder = jpowder;
        buttons.add(peakButton);
        buttons.add(rescaleButton);
        buttons.add(appearanceButton);
        buttons.add(zoomButton);
        buttons.add(changeXAxisButton);
  


//        jpowderIcon = new JpowderIcon(buttons, imgdir, title);
//        jpowderIcon.set_imgdir(imgdir);
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
        changeXAxisButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(320, 420));

        peakButton.setBackground(javax.swing.UIManager.getDefaults().getColor("CheckBox.highlight"));
        peakButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Peaks_Small.png"))); // NOI18N
        peakButton.setPreferredSize(new java.awt.Dimension(80, 80));
        peakButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                peakButtonActionPerformed(evt);
            }
        });

        rescaleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Rescale_Small.png"))); // NOI18N
        rescaleButton.setPreferredSize(new java.awt.Dimension(80, 80));
        rescaleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rescaleButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("Peaks");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel2.setText("Rescale & Move");
        jLabel2.setToolTipText("");

        zoomButton.setBackground(javax.swing.UIManager.getDefaults().getColor("ComboBox.disabledBackground"));
        zoomButton.setForeground(new java.awt.Color(255, 255, 255));
        zoomButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Zoom_Small.png"))); // NOI18N
        zoomButton.setOpaque(false);
        zoomButton.setPreferredSize(new java.awt.Dimension(80, 80));
        zoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomButtonActionPerformed(evt);
            }
        });

        appearanceButton.setBackground(javax.swing.UIManager.getDefaults().getColor("ComboBox.disabledBackground"));
        appearanceButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Appearance_Small.png"))); // NOI18N
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

        changeXAxisButton.setBackground(javax.swing.UIManager.getDefaults().getColor("ComboBox.disabledBackground"));
        changeXAxisButton.setForeground(new java.awt.Color(255, 255, 255));
        changeXAxisButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ChangeXAxis_Small.png"))); // NOI18N
        changeXAxisButton.setOpaque(false);
        changeXAxisButton.setPreferredSize(new java.awt.Dimension(80, 80));
        changeXAxisButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeXAxisButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel5.setText("Transform x-axis");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(peakButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(26, 26, 26)
                            .addComponent(rescaleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(changeXAxisButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(appearanceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(8, 8, 8)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(zoomButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel3)
                        .addGap(56, 56, 56)
                        .addComponent(jLabel4)))
                .addContainerGap(65, Short.MAX_VALUE))
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
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(appearanceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zoomButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(changeXAxisButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
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
        Jpowder.jpowderInfoPanelUpdate(markPeakPosition);

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
        Jpowder.jpowderInfoPanelUpdate(rescaleYdata);
    }//GEN-LAST:event_rescaleButtonActionPerformed

    private void zoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoomButtonActionPerformed

        jpowder.getanalysistab().add(zoomAndPan, "1");
        zoomAndPan.setVisible(true);
        this.setVisible(false);
        Jpowder.jpowderInfoPanelUpdate(zoomAndPan);
}//GEN-LAST:event_zoomButtonActionPerformed

    private void appearanceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appearanceButtonActionPerformed
 
        jpowder.getanalysistab().add(changePlotStyle, "1");
        changePlotStyle.setVisible(true);
        this.setVisible(false);

        Jpowder.jpowderInfoPanelUpdate(changePlotStyle);
}//GEN-LAST:event_appearanceButtonActionPerformed

    private void changeXAxisButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeXAxisButtonActionPerformed
       jpowder.getanalysistab().add(braggsLow, "1");
        braggsLow.setVisible(true);
        this.setVisible(false);

        Jpowder.jpowderInfoPanelUpdate(braggsLow);
    }//GEN-LAST:event_changeXAxisButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton appearanceButton;
    private javax.swing.JButton changeXAxisButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton peakButton;
    private javax.swing.JButton rescaleButton;
    private javax.swing.JButton zoomButton;
    // End of variables declaration//GEN-END:variables
}
