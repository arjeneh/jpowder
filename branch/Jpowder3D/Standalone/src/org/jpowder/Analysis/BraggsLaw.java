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
 * BraggsLaw.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  Milad Arjeneh, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.Analysis;

import java.util.Collections;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.data.xy.XYDataset;
import org.jpowder.InfoPanel;
import org.jpowder.Jpowder;
import org.jpowder.InernalFrame.JpowderInternalframe2D;

/**
 * this class is for converting the values of x axis from 2ө to d using the
 * braggs law (2dSinө=λ), where λ is the wavelength of the X-rays (and moving
 * electrons, protons and neutrons), d is the spacing between the planes in the
 * atomic lattice, and θ is the angle between the incident ray and the scattering
 * planes.
 *
 */
public class BraggsLaw extends javax.swing.JPanel implements InfoPanel {

    private ToolsIcon toolsIcon;
    private String[][] dataSetAndWaveLength;
    private String columnsName[] = {"Plot(s)", "Wavelength"};
    private DefaultTableModel defaultTableModel;
    private double newWaveLength;
    private GSASTable gSASTable = new GSASTable();

    /** Creates new form BraggsLow */
    public BraggsLaw(ToolsIcon analysisIcon) {
        initComponents();

        this.toolsIcon = analysisIcon;



    }

    public void update() {

        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus;
        if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() == 0) {
//            setComponentEnable();
            if (defaultTableModel != null) {
                defaultTableModel.getDataVector().removeAllElements();//remove all the rows from table
                gSASTable.getGSASDefaultTableModel().getDataVector().removeAllElements();
            }
            dataTable.updateUI();
            GSASTable.getGSASTable().updateUI();
            return;
        }

        for (int i = 0; i < inFocus.getXYPlot().getDatasetCount(); i++) {
            if (inFocus.getPowderDataSet().get(i).getXUnit().equals("2θ")) {

                // clear the table
                // get all dataset in plot
                // loop over dataset
                // if GSAS_Instrument = null then add no numbers
                // otherwise add numbers


                bragsPanel.setVisible(true);
                tablePanel.add(bragsPanel);
                gSASTable.setVisible(false);

            }

        }
        for (int i = 0; i < inFocus.getXYPlot().getDatasetCount(); i++) {
            if (inFocus.getPowderDataSet().get(i).getXUnit().equals("TOF")) {

                gSASTable.setVisible(true);
                gSASTable.populateTable2();
    

                tablePanel.add(gSASTable);
                bragsPanel.setVisible(false);
            }

        }



        defaultTableModel = new DefaultTableModel(getDataSetAndWaveLength(), columnsName) {

            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        };





        dataTable.setModel(defaultTableModel);
        setSizeOfColumn();

        defaultTableModel.addTableModelListener(new TableListener(newWaveLength));


//        defaultTableModel.addTableModelListener(new TableModelListener() {
//
//            public void tableChanged(TableModelEvent e) {
//                JpowderInternalframe inFocus = Jpowder.internalFrameInFocus;
//                int size = inFocus.getXYPlot().getDatasetCount();
//                for (int i = 0; i < size; i++) {
//
//                    if (!defaultTableModel.getValueAt(i, 1).equals("")) {
//
//                        newWaveLength = Double.parseDouble(dataTable.getModel().getValueAt(i, 1).toString());
//                        inFocus.getPowderDataSet().get(i).setWaveLength(newWaveLength);
//
//                    }
//                }
//
//
//            }
//        });

        dataTable.getColumn(dataTable.getColumnName(0)).setCellRenderer(new TableRenderer());


//        dataTable.setEditingColumn(1);
        //printing the data in the tables
//        printStringArray(getDataSetAndWaveLength());

        requestMessage();
    }

    /**
     * This method create a two dimensional array which holds the file names and
     * and the WaveLengths if it exist in the data files.
     * @return dataSetAndWaveLength
     */
    public String[][] getDataSetAndWaveLength() {

        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus;
        int size = inFocus.getXYPlot().getDatasetCount();
        dataSetAndWaveLength = new String[size][2];
        for (int i = 0; i < size; i++) {

            String[] row = new String[2];
            row[0] = inFocus.getPowderDataSet().elementAt(i).getFileName();
            row[1] = "" + inFocus.getPowderDataSet().elementAt(i).getWaveLength();

            dataSetAndWaveLength[i] = row;

        }
        return dataSetAndWaveLength;
    }

    /**
     * 
     * @param dataSetAndWaveLength
     */
    public void setDataSetAndWaveLength(String[][] dataSetAndWaveLength) {
        this.dataSetAndWaveLength = dataSetAndWaveLength;
    }

    /**
     *  this method prints the data which is in the table.
     * @param printArray
     */
    public void printStringArray(String[][] printArray) {

        for (int i = 0; i < printArray.length; i++) {
            for (int j = 0; j < printArray[i].length; j++) {
                if (j == 0) {
                    System.out.print(printArray[i][j]);
                } else {
                }
            }
        }


    }

    /**
     * Adding a message to table to ask user to add a value for the wave length.
     */
    public void requestMessage() {
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus;
        for (int i = 0; i < inFocus.getXYPlot().getDatasetCount(); i++) {
            if (!inFocus.getPowderDataSet().get(i).getFileName().endsWith(".cif")) {

                if (defaultTableModel.getValueAt(i, 1).equals("0.0")) {
                    defaultTableModel.setValueAt("", i, 1);
                }

            }
        }
    }

    /**
     * sets the size of the first column of the table which it holds the
     * file names.
     */
    public void setSizeOfColumn() {
        //seting the column width
        TableColumn column = dataTable.getColumnModel().getColumn(0);
        int width = 220;
        column.setPreferredWidth(width);

    }

    public void setDomainAxis() {
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus;
        NumberAxis numberAxis = (NumberAxis) inFocus.getXYPlot().getDomainAxis();
        numberAxis.setRange(0, 1);
    }

    public void resetXaxis() {
        double maxX = 0;
        double minX = 0;
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus;
        for (int i = 0; i < inFocus.getXYPlot().getDatasetCount(); i++) {
            maxX = (Double) Collections.max(inFocus.getPowderDataSet().elementAt(i).getX());
            minX = (Double) Collections.min(inFocus.getPowderDataSet().elementAt(i).getX());
//            System.out.println("max y" + maxY);
        }
        NumberAxis axis = (NumberAxis) inFocus.getXYPlot().getDomainAxis();
        axis.setLowerBound(minX);
        axis.setUpperBound(maxX);
      
//        System.out.println("MinX : "+minX);

        inFocus.getchart().setNotify(true);
    }

    public void setColumn(String[] columns) {
        this.columnsName = columns;
    }

    public String[] getColumns() {
        return columnsName;
    }

    public String[][] getRowData() {

        return dataSetAndWaveLength;
    }

    public static JTable getBragstable() {
        return dataTable;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bragsPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        tablePanel = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        backButton = new javax.swing.JButton();
        unitComboBox1 = new javax.swing.JComboBox();
        unitComboBox2 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        applyButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        dataTable.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        dataTable.setModel(new DefaultTableModel());
        dataTable.setToolTipText("Fill The Empty WaveLength Rows");
        jScrollPane3.setViewportView(dataTable);

        javax.swing.GroupLayout bragsPanelLayout = new javax.swing.GroupLayout(bragsPanel);
        bragsPanel.setLayout(bragsPanelLayout);
        bragsPanelLayout.setHorizontalGroup(
            bragsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(bragsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bragsPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        bragsPanelLayout.setVerticalGroup(
            bragsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(bragsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bragsPanelLayout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addGap(3, 3, 3)))
        );

        jButton1.setText("jButton1");

        setFocusCycleRoot(true);

        tablePanel.setLayout(new java.awt.BorderLayout());

        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Back.PNG"))); // NOI18N
        backButton.setText("Back");
        backButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        backButton.setIconTextGap(2);
        backButton.setInheritsPopupMenu(true);
        backButton.setMargin(new java.awt.Insets(2, 0, 2, 0));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        unitComboBox1.setFont(new java.awt.Font("Tahoma", 0, 14));
        unitComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2Ө", "d", "TOF" }));

        unitComboBox2.setFont(new java.awt.Font("Tahoma", 0, 14));
        unitComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "d", "2Ө", "TOF" }));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel3.setText("---->");

        applyButton.setText("Apply");
        applyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyButtonActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transformxaxis_Large.png"))); // NOI18N

        jLabel4.setText("Change x-axis from 2ө to d and vice-versa.");

        jButton2.setText("resc");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(230, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(105, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(unitComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                        .addComponent(unitComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                        .addComponent(applyButton)))
                .addGap(16, 16, 16))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unitComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unitComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(applyButton)
                    .addComponent(jButton2))
                .addGap(78, 78, 78)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {unitComboBox1, unitComboBox2});

    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        this.setVisible(false);
}//GEN-LAST:event_backButtonActionPerformed
    /**
     *   d=λ/2Sinө
     * @param evt
     */
    private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyButtonActionPerformed
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus;


        if (bragsPanel.isVisible()) {
            if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() == 0) {
                applyButton.setSelected(false);
                return;
            }
//        for (int i = 0; i < inFocus.getXYPlot().getDatasetCount(); i++) {
//            if (inFocus.getPowderDataSet().get(i).getFileName().endsWith("gss")) {
//                javax.swing.JOptionPane.showMessageDialog(null, "Can not transform TOF to 2Ө.");
//                return;
//            }
//
//        }

            String x = "2\u03D1";//unicode 2thetha
            dataTable.clearSelection();
            if (unitComboBox1.getSelectedItem().toString().equals("2Ө") &&
                    unitComboBox2.getSelectedItem().toString().equals("2Ө")) {
                return;
            }
            if (unitComboBox1.getSelectedItem().toString().equals("d") &&
                    unitComboBox2.getSelectedItem().toString().equals("d")) {
                return;
            }
            int seriescount = inFocus.getXYPlot().getDatasetCount();
            if (inFocus.getXYPlot().getDomainAxis().getLabel().equals("d [Å]") &&
                    unitComboBox1.getSelectedItem().toString().equals("2Ө")) {
                javax.swing.JOptionPane.showMessageDialog(null, "The unit is already in  d [Å].");
                return;
            }
            if (inFocus.getXYPlot().getDomainAxis().getLabel().equals(x.toUpperCase()) &&
                    unitComboBox1.getSelectedItem().toString().equals("d")) {
                javax.swing.JOptionPane.showMessageDialog(null, "The unit is already in 2Ө.");
                return;
            }
            // testing if all wavelenghts have been set




            for (int i = 0; i < seriescount; i++) {
                try {
                    double waveLength = Double.parseDouble(dataTable.getValueAt(i, 1).toString());
                    if (dataTable.getValueAt(i, 1).equals("Value")) {
                        javax.swing.JOptionPane.showMessageDialog(null, "Please enter value for Wavelength.");
                        return;
                    }
                } catch (NumberFormatException e) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Please enter value for Wavelength.");
                    return;
                }
            }


            for (int i = 0; i < seriescount; i++) {
//            try {
                inFocus.getPowderDataSet().elementAt(i).getX();
                XYDataset ds = inFocus.getXYPlot().getDataset(i);

                for (int j = 0; j < ds.getItemCount(i); j++) {

                    Double X = (Double) inFocus.getPowderDataSet().elementAt(i).getX().get(j);
                    double waveLength = Double.parseDouble(dataTable.getValueAt(i, 1).toString());
                    double spacing = waveLength / (2 * Math.sin(Math.toRadians(X / 2)));
                    double theta = Math.toDegrees(Math.asin((waveLength / (2 * X)))) * 2;


                    if (unitComboBox1.getSelectedItem().toString().equals("2Ө") &&
                            unitComboBox2.getSelectedItem().toString().equals("d")) {
                        inFocus.getPowderDataSet().elementAt(i).getX().setElementAt(spacing, j);
                        inFocus.getXYPlot().getDomainAxis().setLabel("d [Å]");
                        setDomainAxis();

                    }


                    if (unitComboBox1.getSelectedItem().toString().equals("d") &&
                            unitComboBox2.getSelectedItem().toString().equals("2Ө")) {
                        inFocus.getPowderDataSet().elementAt(i).getX().setElementAt(theta, j);

                        inFocus.getXYPlot().getDomainAxis().setLabel(x.toUpperCase());
                        setDomainAxis();
                    }



                }

            }
        }


        if (gSASTable.isVisible()) {
            for (int i = 0; i < inFocus.getXYPlot().getDatasetCount(); i++) {

                inFocus.getPowderDataSet().elementAt(i).getX();
                XYDataset ds = inFocus.getXYPlot().getDataset(i);

                for (int j = 0; j < ds.getItemCount(i); j++) {

                    Double X = (Double) inFocus.getPowderDataSet().elementAt(i).getX().get(j);
                    double newX = inFocus.getPowderDataSet().get(i).getGSAS_Instrument().toDspacing(X);
                    if (unitComboBox1.getSelectedItem().toString().equals("TOF") &&
                            unitComboBox2.getSelectedItem().toString().equals("d")) {
                        inFocus.getPowderDataSet().elementAt(i).getX().setElementAt(newX, j);
                       resetXaxis();

//            System.out.println("max y" + maxY);

                    }
                }

            }
        }
        inFocus.getChartPanel().restoreAutoBounds();
        inFocus.getchart().setNotify(true);

    }//GEN-LAST:event_applyButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        resetXaxis();
    }//GEN-LAST:event_jButton2ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applyButton;
    private javax.swing.JButton backButton;
    private javax.swing.JPanel bragsPanel;
    public static javax.swing.JTable dataTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    static javax.swing.JPanel tablePanel;
    private javax.swing.JComboBox unitComboBox1;
    private javax.swing.JComboBox unitComboBox2;
    // End of variables declaration//GEN-END:variables
}
