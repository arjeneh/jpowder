/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BraggsLow.java
 *
 * Created on 29-Mar-2010, 15:53:26
 */
package org.jpowder.Analysis;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.data.xy.XYDataset;
import org.jpowder.InfoPanel;
import org.jpowder.Jpowder;
import org.jpowder.JpowderInternalframe;
import org.jpowder.jfreechart.FilesPlotter;

/**
 * this class is for converting the values of x axis from 2ө to d using the
 * braggs law (2dSinө=λ), where λ is the wavelength of the X-rays (and moving
 * electrons, protons and neutrons), d is the spacing between the planes in the
 * atomic lattice, and θ is the angle between the incident ray and the scattering
 * planes.
 *
 * @author qyt21516
 */
public class BraggsLaw extends javax.swing.JPanel implements InfoPanel {

    private ToolsIcon toolsIcon;
    private String[][] dataSetAndWaveLength;
    private String columnsName[] = {"Plot(s)", "Wavelength"};
    private DefaultTableModel defaultTableModel;
    private double newWaveLength;

    /** Creates new form BraggsLow */
    public BraggsLaw(ToolsIcon analysisIcon) {
        initComponents();

        this.toolsIcon = analysisIcon;

    }

    public void update() {

        JpowderInternalframe inFocus = Jpowder.internalFrameInFocus;
        if (JpowderInternalframe.getnumberOfJpowderInternalframe() == 0) {

            if (defaultTableModel != null) {
                defaultTableModel.getDataVector().removeAllElements();//remove all the rows from table
            }
            dataTable.updateUI();
            return;
        }


        defaultTableModel = new DefaultTableModel(getDataSetAndWaveLength(), columnsName);
        dataTable.setModel(defaultTableModel);
        setSizeOfColumn();

        defaultTableModel.addTableModelListener(new TableModelListener() {

            public void tableChanged(TableModelEvent e) {
                JpowderInternalframe inFocus = Jpowder.internalFrameInFocus;
                int size = inFocus.getXYPlot().getDatasetCount();
                for (int i = 0; i < size; i++) {

                    if (!defaultTableModel.getValueAt(i, 1).equals("")) {

                        newWaveLength = Double.parseDouble(dataTable.getModel().getValueAt(i, 1).toString());
                        inFocus.getPowderDataSet().get(i).setWaveLength(newWaveLength);

                    }
                }


            }
        });


        dataTable.getColumn(dataTable.getColumnName(0)).setCellRenderer(new TableCellRenderer() {

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
               JpowderInternalframe inFocus = Jpowder.internalFrameInFocus;
                JLabel fileName = new JLabel(value.toString());


                     fileName.setForeground((Color) inFocus.getXYPlot().getRenderer(row).getSeriesPaint(0));
                return fileName;


            }
        });


        //printing the data in the tables
        printStringArray(getDataSetAndWaveLength());

        requestMessage();




    }

    /**
     * This method create a two dimensional array which holds the file names and
     * and the WaveLengths if it exist in the data files.
     * @return dataSetAndWaveLength
     */
    public String[][] getDataSetAndWaveLength() {

        JpowderInternalframe inFocus = Jpowder.internalFrameInFocus;
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
                    System.out.println(" wave = " + printArray[i][j]);
                }
            }
        }


    }

    /**
     * Adding a message to table to ask user to add a value for the wave length.
     */
    public void requestMessage() {
        JpowderInternalframe inFocus = Jpowder.internalFrameInFocus;
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
        JpowderInternalframe inFocus = Jpowder.internalFrameInFocus;
        NumberAxis numberAxis = (NumberAxis) inFocus.getXYPlot().getDomainAxis();
        numberAxis.setRange(0, 1);
    }

    public void setColumn(String[] columns) {
        this.columnsName = columns;
    }

    public String[] getColumns() {
        return columnsName;
    }

    public String[][] getRowData() {
        System.out.println("");
        return dataSetAndWaveLength;
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
        jScrollPane3 = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();
        backButton = new javax.swing.JButton();
        unitComboBox1 = new javax.swing.JComboBox();
        unitComboBox2 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        applyButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setFocusCycleRoot(true);

        dataTable.setFont(new java.awt.Font("Tahoma", 0, 12));
        dataTable.setModel(new DefaultTableModel());
        dataTable.setToolTipText("Fill The Empty WaveLength Rows");
        jScrollPane3.setViewportView(dataTable);

        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Back.PNG"))); // NOI18N
        backButton.setText("Back");
        backButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        backButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        backButton.setIconTextGap(2);
        backButton.setInheritsPopupMenu(true);
        backButton.setMargin(new java.awt.Insets(2, 0, 2, 0));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        unitComboBox1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        unitComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2ө", "d" }));

        unitComboBox2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        unitComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "d", "2ө" }));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("---->>");

        applyButton.setText("Apply");
        applyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyButtonActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Transformxaxis_Large.png"))); // NOI18N

        jLabel4.setText("Change x-axis from 2ө to d and vice-versa.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(226, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(101, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(applyButton)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(unitComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                                .addComponent(unitComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6)))
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
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
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
        if (JpowderInternalframe.getnumberOfJpowderInternalframe() == 0) {
            applyButton.setSelected(false);
            return;
        }
        JpowderInternalframe inFocus = Jpowder.internalFrameInFocus;
        String x = "2\u03D1";//unicode 2thetha
        dataTable.clearSelection();
        int seriescount = inFocus.getXYPlot().getDatasetCount();
        if (inFocus.getXYPlot().getDomainAxis().getLabel().equals("d [Å]") &&
                unitComboBox1.getSelectedItem().toString().equals("2ө")) {
            javax.swing.JOptionPane.showMessageDialog(null, "The Unit Is Already In  d [Å]! ");
            return;
        }
        if (inFocus.getXYPlot().getDomainAxis().getLabel().equals(x.toUpperCase()) &&
                unitComboBox1.getSelectedItem().toString().equals("d")) {
            javax.swing.JOptionPane.showMessageDialog(null, "The Unit Is Already In 2ө! ");
            return;
        }
        // testing if all wavelenghts have been set
        for (int i = 0; i < seriescount; i++) {
            try {
                double waveLength = Double.parseDouble(dataTable.getValueAt(i, 1).toString());
                if (dataTable.getValueAt(i, 1).equals("Value")) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Please Enter Value For Wave Length.");
                    return;
                }
            } catch (NumberFormatException e) {
                javax.swing.JOptionPane.showMessageDialog(null, "Please Enter Value For Wave Length.");
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


                if (unitComboBox1.getSelectedItem().toString().equals("2ө") &&
                        unitComboBox2.getSelectedItem().toString().equals("d")) {
                    inFocus.getPowderDataSet().elementAt(i).getX().setElementAt(spacing, j);
                    inFocus.getXYPlot().getDomainAxis().setLabel("d [Å]");
                    setDomainAxis();

                }


                if (unitComboBox1.getSelectedItem().toString().equals("d") &&
                        unitComboBox2.getSelectedItem().toString().equals("2ө")) {
                    inFocus.getPowderDataSet().elementAt(i).getX().setElementAt(theta, j);

                    inFocus.getXYPlot().getDomainAxis().setLabel(x.toUpperCase());
                    setDomainAxis();
                }

            }
//            } catch (NumberFormatException e) {
//                JOptionPane.showMessageDialog(null, "Enter Valid Number.");
//            }
        }

        inFocus.getChartPanel().restoreAutoBounds();
        inFocus.getchart().setNotify(true);
    }//GEN-LAST:event_applyButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applyButton;
    private javax.swing.JButton backButton;
    private javax.swing.JTable dataTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox unitComboBox1;
    private javax.swing.JComboBox unitComboBox2;
    // End of variables declaration//GEN-END:variables
}
