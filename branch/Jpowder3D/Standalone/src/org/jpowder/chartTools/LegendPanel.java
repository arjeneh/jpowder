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
 * LegendPanel.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.chartTools;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import org.jfree.ui.RectangleEdge;
import org.jpowder.Analysis.TableRenderer;
import org.jpowder.Analysis.ToolsIcon2D;
import org.jpowder.InernalFrame.JpowderInternalframe2D;
import org.jpowder.InfoPanel;
import org.jpowder.Jpowder;

/**
 *
 *  Enabling legend and set position and change the legend.
 */
public class LegendPanel extends javax.swing.JPanel implements InfoPanel {

    private ToolsIcon2D toolsIcon;
    private DefaultTableModel defaultTableModel;
    private String columnsName[] = {"Plot(s)"};
    private String[][] fileName;
    private String newLegend;

    /** Creates new form LegendPanel */
    public LegendPanel(ToolsIcon2D analysisIcon) {
        initComponents();
        this.toolsIcon = analysisIcon;
        legendTable.setVisible(false);

    }

    @Override
    public void update() {

        if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() == 0) {
            legendCheckBox.setEnabled(false);
            legendPositionPanel.setVisible(false);
            if (defaultTableModel != null) {
                defaultTableModel.getDataVector().clear();

            }
            return;
        } else {
            legendCheckBox.setEnabled(true);
        }
        legendPositionButtom.setSelected(true);

        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        if (inFocus.getChart().getLegend() != null) {
            legendCheckBox.setSelected(true);
        } else {
            legendCheckBox.setSelected(false);
        }
        setLegentPositionPanel();


        defaultTableModel = new DefaultTableModel(getFileName(), columnsName);
        legendTable.setModel(defaultTableModel);
        defaultTableModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
                int size = inFocus.getXYPlot().getDatasetCount();
                for (int i = 0; i < size; i++) {

                    if (!defaultTableModel.getValueAt(i, 0).equals("")) {

                        newLegend = legendTable.getModel().getValueAt(i, 0).toString();
                        //Double.parseDouble(dataTable.getModel().getValueAt(i, 1).toString());
                        inFocus.getPowderDataSet().get(i).setFileName(newLegend);


                    }
                }


            }
        });
        legendTable.getColumn(legendTable.getColumnName(0)).setCellRenderer(new TableRenderer());

        if (inFocus.getChart().getLegend() != null) {



            if (inFocus.getChart().getLegend().getPosition().toString().contains("BOTTOM")) {
                legendPositionButtom.setSelected(true);
            }
            if (inFocus.getChart().getLegend().getPosition().toString().contains("TOP")) {
                legendPositionTop.setSelected(true);
            }
            if (inFocus.getChart().getLegend().getPosition().toString().contains("LEFT")) {
                legendPositionLeft.setSelected(true);
            }
            if (inFocus.getChart().getLegend().getPosition().toString().contains("RIGHT")) {
                legendPositionRight.setSelected(true);
            }
        }
    }

    public String[][] getFileName() {

        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        int size = inFocus.getXYPlot().getDatasetCount();
        fileName = new String[size][2];
        for (int i = 0; i < size; i++) {

            String[] row = new String[2];
            row[0] = inFocus.getPowderDataSet().elementAt(i).getFileName();


            fileName[i] = row;

        }
        return fileName;
    }

    public void setLegentPositionPanel() {
        boolean check = legendCheckBox.isSelected();
        legendPositionPanel.setVisible(check);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        legendTable = new javax.swing.JTable();
        legendCheckBox = new javax.swing.JCheckBox();
        backButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        legendPositionPanel = new javax.swing.JPanel();
        legendPositionRight = new javax.swing.JRadioButton();
        legendPositionLeft = new javax.swing.JRadioButton();
        legendPositionTop = new javax.swing.JRadioButton();
        legendPositionButtom = new javax.swing.JRadioButton();

        legendTable.setModel(new DefaultTableModel());
        legendTable.setEnabled(false);
        jScrollPane1.setViewportView(legendTable);

        legendCheckBox.setText("Enable Legend");
        legendCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                legendCheckBoxActionPerformed(evt);
            }
        });

        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Back.PNG"))); // NOI18N
        backButton.setText("Back");
        backButton.setAlignmentY(0.0F);
        backButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        backButton.setIconTextGap(2);
        backButton.setMargin(new java.awt.Insets(2, 0, 2, 0));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        legendPositionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Legend Position", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 204))); // NOI18N

        buttonGroup1.add(legendPositionRight);
        legendPositionRight.setText("Right");
        legendPositionRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                legendPositionRightActionPerformed(evt);
            }
        });

        buttonGroup1.add(legendPositionLeft);
        legendPositionLeft.setText("Left");
        legendPositionLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                legendPositionLeftActionPerformed(evt);
            }
        });

        buttonGroup1.add(legendPositionTop);
        legendPositionTop.setText("Top");
        legendPositionTop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                legendPositionTopActionPerformed(evt);
            }
        });

        buttonGroup1.add(legendPositionButtom);
        legendPositionButtom.setSelected(true);
        legendPositionButtom.setText("Buttom");
        legendPositionButtom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                legendPositionButtomActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout legendPositionPanelLayout = new javax.swing.GroupLayout(legendPositionPanel);
        legendPositionPanel.setLayout(legendPositionPanelLayout);
        legendPositionPanelLayout.setHorizontalGroup(
            legendPositionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(legendPositionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(legendPositionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(legendPositionRight)
                    .addComponent(legendPositionLeft)
                    .addComponent(legendPositionTop)
                    .addComponent(legendPositionButtom))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        legendPositionPanelLayout.setVerticalGroup(
            legendPositionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(legendPositionPanelLayout.createSequentialGroup()
                .addComponent(legendPositionButtom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(legendPositionTop)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(legendPositionLeft)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(legendPositionRight)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(legendCheckBox)
                                        .addGap(103, 103, 103))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(legendPositionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(legendCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(legendPositionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void legendPositionRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_legendPositionRightActionPerformed
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;

        if (legendPositionRight.isSelected()) {
            inFocus.getChart().getLegend().setPosition(RectangleEdge.RIGHT);

        }
}//GEN-LAST:event_legendPositionRightActionPerformed

    private void legendPositionLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_legendPositionLeftActionPerformed
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        if (legendPositionLeft.isSelected()) {
            inFocus.getChart().getLegend().setPosition(RectangleEdge.LEFT);
        }
}//GEN-LAST:event_legendPositionLeftActionPerformed

    private void legendPositionTopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_legendPositionTopActionPerformed
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        if (legendPositionTop.isSelected()) {
            inFocus.getChart().getLegend().setPosition(RectangleEdge.TOP);

        }
}//GEN-LAST:event_legendPositionTopActionPerformed

    private void legendPositionButtomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_legendPositionButtomActionPerformed

        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        if (legendPositionButtom.isSelected()) {

            inFocus.getChart().getLegend().setPosition(RectangleEdge.BOTTOM);

        }

}//GEN-LAST:event_legendPositionButtomActionPerformed

    private void legendCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_legendCheckBoxActionPerformed
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() == 0) {
            return;
        }
        if (legendCheckBox.isSelected()) {
            CreateLegend createLegend = new CreateLegend();
            createLegend.setLegend();
            legendTable.setEnabled(true);
            legendTable.setVisible(true);
            setLegentPositionPanel();

        }
        if (!legendCheckBox.isSelected()) {
            inFocus.getChart().removeLegend();

            legendTable.setEnabled(false);
            legendTable.setVisible(false);
            setLegentPositionPanel();
        }
}//GEN-LAST:event_legendCheckBoxActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed

        this.setVisible(false);
    }//GEN-LAST:event_backButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JCheckBox legendCheckBox;
    private javax.swing.JRadioButton legendPositionButtom;
    private javax.swing.JRadioButton legendPositionLeft;
    private javax.swing.JPanel legendPositionPanel;
    private javax.swing.JRadioButton legendPositionRight;
    private javax.swing.JRadioButton legendPositionTop;
    private javax.swing.JTable legendTable;
    // End of variables declaration//GEN-END:variables
}
