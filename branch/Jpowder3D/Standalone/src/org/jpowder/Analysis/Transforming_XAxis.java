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
 * Author(s):  M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.Analysis;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Collections;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.data.xy.XYDataset;
import org.jpowder.InfoPanel;
import org.jpowder.Jpowder;
import org.jpowder.InernalFrame.JpowderInternalframe2D;
import org.jpowder.dataset.GSASInstrument_Reader;

/**
 * this class is for converting the values of x axis from 2ө to d using the
 * braggs law (2dSinө=λ), where λ is the wavelength of the X-rays (and moving
 * electrons, protons and neutrons), d is the spacing between the planes in the
 * atomic lattice, and θ is the angle between the incident ray and the scattering
 * planes.
 *
 */
public class Transforming_XAxis extends javax.swing.JPanel implements InfoPanel {

    private ToolsIcon2D toolsIcon;
    private String[][] dataSetAndWaveLength;
    private String[][] gSASData;
    private double newWaveLength;
    private String columnsName[] = {"Plot(s)", "Wavelength"};
    private String columnsNameGSAS[] = {"Plot(s)", "DifC", "DifA", "Zero"};
    private DefaultTableModel defaultTableModel;
    private DefaultTableModel defaultTableModel2;
//    private GSASTable gSASTable = new GSASTable();

    /** Creates new form BraggsLow */
    public Transforming_XAxis(ToolsIcon2D analysisIcon) {
        initComponents();

        this.toolsIcon = analysisIcon;

    }

    @Override
    public void update() {

        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() == 0) {
//            setComponentEnable();
            if (defaultTableModel != null) {
                defaultTableModel.getDataVector().removeAllElements();//remove all the rows from table
                defaultTableModel2.getDataVector().removeAllElements();
            }

            return;
        }

        for (int i = 0; i < inFocus.getXYPlot().getDatasetCount(); i++) {
            if (inFocus.getPowderDataSet().get(i).getXUnit().equals("2θ")) {

                bragsPanel.setVisible(true);
                tablePanel.add(bragsPanel);
                gsaspanel.setVisible(false);

            }

        }
        for (int i = 0; i < inFocus.getXYPlot().getDatasetCount(); i++) {
            if (inFocus.getPowderDataSet().get(i).getXUnit().equals("TOF")) {

                gsaspanel.setVisible(true);
                tablePanel.add(gsaspanel);
                bragsPanel.setVisible(false);
                comboboxSelection();

            }

        }

        defaultTableModel2 = new DefaultTableModel(getGSASData(), columnsNameGSAS) {

            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        };
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
        gsastable.setModel(defaultTableModel2);
        setSizeOfColumn();

        defaultTableModel.addTableModelListener(new TableListenerBraggsLaw());
        defaultTableModel2.addTableModelListener(new TableListenerGSAS(gsastable));

        dataTable.getColumn(dataTable.getColumnName(0)).setCellRenderer(new TableRenderer());
        gsastable.getColumn(dataTable.getColumnName(0)).setCellRenderer(new TableRenderer());

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

        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
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

    public String[][] getGSASData() {
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        int size = inFocus.getXYPlot().getDatasetCount();
        gSASData = new String[size][4];
        for (int i = 0; i < size; i++) {

            String[] row = new String[4];
            row[0] = inFocus.getPowderDataSet().elementAt(i).getFileName();

            if (inFocus.getPowderDataSet().elementAt(i).getGSAS_Instrument() != null) {
                row[1] = "" + inFocus.getPowderDataSet().elementAt(i).getGSAS_Instrument().getDifC();
                row[2] = "" + inFocus.getPowderDataSet().elementAt(i).getGSAS_Instrument().getDifA();
                row[3] = "" + inFocus.getPowderDataSet().elementAt(i).getGSAS_Instrument().getZero();
            } else {

                row[1] = "";
                row[2] = "";
                row[3] = "";
            }
            gSASData[i] = row;


        }
        return gSASData;
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
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
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
        gsastable.getColumnModel().getColumn(0).setPreferredWidth(width);
    }

    public void setDomainAxis() {
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        NumberAxis numberAxis = (NumberAxis) inFocus.getXYPlot().getDomainAxis();
        numberAxis.setRange(0, 1);
    }

    public void resetXaxis() {
        double maxX = 0;
        double minX = 0;
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        for (int i = 0; i < inFocus.getXYPlot().getDatasetCount(); i++) {
            maxX = (Double) Collections.max(inFocus.getPowderDataSet().elementAt(i).getX());
            minX = (Double) Collections.min(inFocus.getPowderDataSet().elementAt(i).getX());
//            System.out.println("max y" + maxY);
        }
        NumberAxis axis = (NumberAxis) inFocus.getXYPlot().getDomainAxis();
        axis.setLowerBound(minX);
        axis.setUpperBound(maxX);
        axis.setAutoRangeIncludesZero(false);


//        System.out.println("MinX : "+minX);

        inFocus.getChart().setNotify(true);
    }

    public void comboboxSelection() {
        Object obj = unitComboBox1.getSelectedItem(); // item1
        unitComboBox1.setSelectedItem("TOF");
        obj = unitComboBox1.getSelectedItem();
    }

    public void setColumn(String[] columns) {
        this.columnsName = columns;
    }

    public String[] getColumns() {
        return columnsName;
    }

    public static JTable getBragstable() {
        return dataTable;
    }

    /**
     * This method is used so user dont need to press enter after adding
     * wavelength to the Jtable.
     */
    private void stopAllCellEditing() {
        for (int i = 0; i < dataTable.getRowCount(); i++) {
            for (int j = 0; j < dataTable.getColumnCount(); j++) {
                javax.swing.table.TableCellEditor editor = dataTable.getCellEditor(i, j);
                editor.stopCellEditing();
            }
        }
    }

    private void stopAllCellEditingGstable() {
        for (int i = 0; i <gsastable.getRowCount(); i++) {
            for (int j = 0; j < gsastable.getColumnCount(); j++) {
                javax.swing.table.TableCellEditor editor = gsastable.getCellEditor(i, j);
                editor.stopCellEditing();
            }
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

        bragsPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();
        gsaspanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        gsastable = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        tablePanel = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        backButton = new javax.swing.JButton();
        unitComboBox1 = new javax.swing.JComboBox();
        unitComboBox2 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        applyButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        dataTable.setFont(new java.awt.Font("Tahoma", 0, 12));
        dataTable.setToolTipText("Fill The Empty WaveLength Rows");
        jScrollPane3.setViewportView(dataTable);

        javax.swing.GroupLayout bragsPanelLayout = new javax.swing.GroupLayout(bragsPanel);
        bragsPanel.setLayout(bragsPanelLayout);
        bragsPanelLayout.setHorizontalGroup(
            bragsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 306, Short.MAX_VALUE)
            .addGroup(bragsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bragsPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        bragsPanelLayout.setVerticalGroup(
            bragsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 134, Short.MAX_VALUE)
            .addGroup(bragsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bragsPanelLayout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .addGap(3, 3, 3)))
        );

        jScrollPane1.setViewportView(gsastable);

        jButton3.setText("Instrument file");
        jButton3.setToolTipText("import data from instrument file");
        jButton3.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout gsaspanelLayout = new javax.swing.GroupLayout(gsaspanel);
        gsaspanel.setLayout(gsaspanelLayout);
        gsaspanelLayout.setHorizontalGroup(
            gsaspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gsaspanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(gsaspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        gsaspanelLayout.setVerticalGroup(
            gsaspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gsaspanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jButton3))
        );

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

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("---->");

        applyButton.setText("Apply");
        applyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyButtonActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transformxaxis_Large.png"))); // NOI18N

        jLabel4.setText("Change x-axis from 2Ө to d and vice-versa.");

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
                .addContainerGap(103, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(unitComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                        .addComponent(unitComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(applyButton))
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
                .addComponent(applyButton)
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
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() == 0) {
            applyButton.setSelected(false);
            return;
        }


        if (bragsPanel.isVisible()) {
       

//        for (int i = 0; i < inFocus.getXYPlot().getDatasetCount(); i++) {
//            if (inFocus.getPowderDataSet().get(i).getFileName().endsWith("gss")) {
//                javax.swing.JOptionPane.showMessageDialog(null, "Can not transform TOF to 2Ө.");
//                return;
//            }
//
//        }
         
            stopAllCellEditing();
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


        if (gsaspanel.isVisible()) {

            stopAllCellEditingGstable();
            for (int i = 0; i < inFocus.getXYPlot().getDatasetCount(); i++) {

                inFocus.getPowderDataSet().elementAt(i).getX();
                XYDataset ds = inFocus.getXYPlot().getDataset(i);

                for (int j = 0; j < ds.getItemCount(i); j++) {

                    Double X = (Double) inFocus.getPowderDataSet().elementAt(i).getX().get(j);
                    if (inFocus.getPowderDataSet().get(i).getGSAS_Instrument() == null) {
                        return;
                    }
                    double newXDspacing = inFocus.getPowderDataSet().get(i).getGSAS_Instrument().toDspacing(X);
                    double newXTOF = inFocus.getPowderDataSet().get(i).getGSAS_Instrument().toTOF(X);

                    if (unitComboBox1.getSelectedItem().toString().equals("TOF") &&
                            unitComboBox2.getSelectedItem().toString().equals("d")) {
                        inFocus.getPowderDataSet().elementAt(i).getX().setElementAt(newXDspacing, j);
                        inFocus.getXYPlot().getDomainAxis().setLabel("d [Å]");
                       

                    }
                    if (unitComboBox1.getSelectedItem().toString().equals("d") &&
                            unitComboBox2.getSelectedItem().toString().equals("TOF")) {
                          inFocus.getXYPlot().getDomainAxis().setLabel("TOF");
                        inFocus.getPowderDataSet().elementAt(i).getX().setElementAt(newXTOF, j);

                    }
                }
            }

            resetXaxis();
        }


        inFocus.getChartPanel().restoreAutoBounds();
        inFocus.getChart().setNotify(true);

    }//GEN-LAST:event_applyButtonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JFileChooser chooser = new JFileChooser();
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        chooser.setMultiSelectionEnabled(false);
        int returnVal = chooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {

            defaultTableModel2.getDataVector().removeAllElements();
            File file = chooser.getSelectedFile();
            GSASInstrument_Reader.read(inFocus.getPowderDataSet(), file);
            defaultTableModel2 = new DefaultTableModel(getGSASData(), columnsNameGSAS);
            gsastable.setModel(defaultTableModel2);
            gsastable.getColumn(dataTable.getColumnName(0)).setCellRenderer(new TableRenderer());
        } else {
            return;
        }
    }//GEN-LAST:event_jButton3ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applyButton;
    private javax.swing.JButton backButton;
    private javax.swing.JPanel bragsPanel;
    public static javax.swing.JTable dataTable;
    private javax.swing.JPanel gsaspanel;
    private javax.swing.JTable gsastable;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    static javax.swing.JPanel tablePanel;
    private javax.swing.JComboBox unitComboBox1;
    private javax.swing.JComboBox unitComboBox2;
    // End of variables declaration//GEN-END:variables
}
