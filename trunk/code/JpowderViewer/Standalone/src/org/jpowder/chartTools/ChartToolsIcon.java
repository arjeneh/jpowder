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
    private String[] title = {"real_12.png", "3_4u.png"};
    private String[] imgdir = {"Appearance-Small.PNG", "zoompanning_26x26.png"};
    private ChangePlotStyle changePlotStyle = new ChangePlotStyle(this);
    private ZoomAndPan zoomAndPan = new ZoomAndPan(this);
    private JPowder jpowder;

    /** Creates new form ChartToolsIcon */
    public ChartToolsIcon(JPowder jpowder) {
        initComponents();
        this.jpowder = jpowder;
        buttons.add(chartToolButton);
        buttons.add(chartToolButton1);
     

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
        chartToolButton1 = new javax.swing.JButton();

        setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("Zoom And Pan");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("Appearance");

        chartToolButton.setPreferredSize(new java.awt.Dimension(80, 80));
        chartToolButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chartToolButtonActionPerformed(evt);
            }
        });

        chartToolButton1.setPreferredSize(new java.awt.Dimension(80, 80));
        chartToolButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chartToolButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chartToolButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chartToolButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                .addGap(76, 76, 76))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {chartToolButton, chartToolButton1});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(chartToolButton1, 0, 88, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chartToolButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)))
                .addGap(263, 263, 263))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {chartToolButton, chartToolButton1});

    }// </editor-fold>//GEN-END:initComponents

    private void chartToolButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chartToolButtonActionPerformed
        System.out.println("hi chartToolButton clicked");
        Object source = evt.getSource();
        if (source instanceof JButton) {
            JButton button = (JButton) source;
//        jpowderIcon.setHeight(35);
//        jpowderIcon.setWidth(35);
            changePlotStyle.getChangePlotStyleLabel().setIcon(button.getIcon());
        }
        jpowder.getChartToolstab().add(changePlotStyle, "1");
        changePlotStyle.setVisible(true);
        this.setVisible(false);

        JPowder.jpowderInfoPanelUpdate(changePlotStyle);
    }//GEN-LAST:event_chartToolButtonActionPerformed

    private void chartToolButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chartToolButton1ActionPerformed
        Object source = evt.getSource();
        if (source instanceof JButton) {
            JButton button = (JButton) source;
//        jpowderIcon.setHeight(35);
//        jpowderIcon.setWidth(35);
            zoomAndPan.getZoomLable().setIcon(button.getIcon());
        }
        jpowder.getChartToolstab().add(zoomAndPan, "1");
        zoomAndPan.setVisible(true);
        this.setVisible(false);
        JPowder.jpowderInfoPanelUpdate(zoomAndPan);
    }//GEN-LAST:event_chartToolButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chartToolButton;
    private javax.swing.JButton chartToolButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
