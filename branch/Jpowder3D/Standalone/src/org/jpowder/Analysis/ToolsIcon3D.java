/* ===========================================================
 * This file is part of Jpowder, see <http://www.jpowder.org/>
 * ===========================================================
 *
 * Jpowder is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jpowder is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * ---------
 * ToolsICon3D.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  Kreecha Puphaiboon, Computer Science Lecturer, Kasem Bundit University
               M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.Analysis;

import java.util.Vector;
import javax.swing.JOptionPane;
import org.jpowder.InernalFrame.JpowderInternalframe3D;
import org.jpowder.Jpowder;
import org.jpowder.chartTools.AnnotationPanel;
import org.jpowder.chartTools.YAxisDisplay;
import org.jpowder.chartTools.ColorBar3D;
import org.jpowder.chartTools.SmoothingPanel;
import org.jpowder.dataset.DataSet;

/**
 * This class contains all the Jpowder icons 3D.
 *
 */
public class ToolsIcon3D extends javax.swing.JPanel {

    private ColorBar3D colorBar = new ColorBar3D(this);
    private YAxisDisplay blockHeight = new YAxisDisplay(this);
    private SmoothingPanel smoothing = new SmoothingPanel(this);
    private Transforming_XAxis_3D braggsLow3D = new Transforming_XAxis_3D(this);
    private Jpowder jpowder;
    private AnnotationPanel annoPanel = new AnnotationPanel(this);

    /** Creates new form ToolsIcon3D */
    public ToolsIcon3D(Jpowder jpowder) {
        initComponents();
        this.jpowder = jpowder;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        colourBarButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        yAxisButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        smoothingButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        changeXAxisButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        annotationButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        colourBarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ColourBar.png"))); // NOI18N
        colourBarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colourBarButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel1.setText("3D tools testing..........");

        yAxisButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/3DPlotType.png"))); // NOI18N
        yAxisButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yAxisButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Colour Bar Format");

        jLabel3.setText("Y-axis display");

        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel4.setText("No be decided");

        smoothingButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/MA.png"))); // NOI18N
        smoothingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smoothingButtonActionPerformed(evt);
            }
        });

        jLabel5.setText("Smoothing");

        changeXAxisButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ChangeXAxis_Small.png"))); // NOI18N
        changeXAxisButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeXAxisButtonActionPerformed(evt);
            }
        });

        jLabel6.setText("Transform X-Axis");

        annotationButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Annotation.png"))); // NOI18N
        annotationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annotationButtonActionPerformed(evt);
            }
        });

        jLabel7.setText("Annotate");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(smoothingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(changeXAxisButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel7)
                                .addGap(43, 43, 43))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(annotationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(colourBarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(yAxisButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel4))
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(colourBarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yAxisButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(smoothingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(changeXAxisButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(annotationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel7)))
                .addContainerGap(138, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void colourBarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colourBarButtonActionPerformed
        jpowder.getToolsTab3D().add(colorBar, "1");
        colorBar.setVisible(true);
        this.setVisible(false);
        Jpowder.jpowderInfoPanelUpdate(colorBar);
    }//GEN-LAST:event_colourBarButtonActionPerformed

    private void yAxisButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yAxisButtonActionPerformed
        jpowder.getToolsTab3D().add(blockHeight, "1");
        blockHeight.setVisible(true);
        this.setVisible(false);
        Jpowder.jpowderInfoPanelUpdate(blockHeight);
    }//GEN-LAST:event_yAxisButtonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void smoothingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smoothingButtonActionPerformed
        // TODO add your handling code here:
        jpowder.getToolsTab3D().add(smoothing, "1");
        smoothing.setVisible(true);
        this.setVisible(false);
        Jpowder.jpowderInfoPanelUpdate(smoothing);
    }//GEN-LAST:event_smoothingButtonActionPerformed

    private void changeXAxisButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeXAxisButtonActionPerformed
        // TODO add your handling code here:
        //load the file names.
        //update TableModel with data of files and column.
        JpowderInternalframe3D inFocus = Jpowder.internalFrameInFocus3D;
        Vector<Vector> fileNames = new Vector();
        try {
            // something stupid
            // if not null then get filename
            Vector<DataSet> vec = inFocus.getPowderDataSet();
            for (int i = 0; i < vec.size(); i++) {
                DataSet ds = vec.get(i);
                Vector row = new Vector();
                row.add(ds.getFileName());
                row.add(0.0);
                fileNames.add(row);
                System.out.println("in " + this.getClass().getName() + " file in it is: " + ds.getFileName());
            }

            Vector colName = new Vector();
            colName.add("Plot(s)");
            colName.add("Wavelength");

            jpowder.getToolsTab3D().add(braggsLow3D, "1");
            braggsLow3D.updateTableModel(fileNames, colName);
            braggsLow3D.setVisible(true);
            this.setVisible(false);

        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Plot something first");
            // probably don't bother doing clean up
        } finally {
            // carry on as if nothing went wrong
        }
    }//GEN-LAST:event_changeXAxisButtonActionPerformed

    /**
     * @return the annoPanel
     */
    public AnnotationPanel getAnnoPanel() {
        return annoPanel;
    }

    private void annotationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annotationButtonActionPerformed
        // TODO add your handling code here:
        jpowder.getToolsTab3D().add(getAnnoPanel(), "1");
        annoPanel.setVisible(true);
        this.setVisible(false);

        Jpowder.jpowderInfoPanelUpdate(annoPanel);
    }//GEN-LAST:event_annotationButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton annotationButton;
    private javax.swing.JButton changeXAxisButton;
    private javax.swing.JButton colourBarButton;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JButton smoothingButton;
    private javax.swing.JButton yAxisButton;
    // End of variables declaration//GEN-END:variables
}
