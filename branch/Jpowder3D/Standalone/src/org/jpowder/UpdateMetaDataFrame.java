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
 * UpdateMetaDataFrame.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  Kreecha Puphaiboon, Computer Science Lecturer, Kasem Bundit University
 *             
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder;

import java.awt.Cursor;
import org.jpowder.InernalFrame.JpowderInternalframe3D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.jpowder.InernalFrame.InternalFrameIconifyListener;
import org.jpowder.TableHelper.SortableTableModel;
import org.jpowder.dataset.DataSet;
import org.jpowder.util.NaturalOrderComparator;
import org.jpowder.util.StringUtil;
import org.jpowder.dataset.MetaData;

/**
 * A table which contains File names, File filePaths to add MetaData value of 3D powder
 * data files which are plotted in JpowderInternalframe3D.
 *
 * It is taken off from Import3DFilesTable.java.
 * 19/10/2012
 *
 */
public class UpdateMetaDataFrame extends javax.swing.JFrame {

    JFileChooser chooser = new JFileChooser(new File("C:/Documents and Settings/qyt21516/Desktop/My Dropbox"));
    private Vector<String> columnNames = new Vector<String>();
    private Vector<String> row = new Vector<String>();
    //
    private DataVisibleInChartPanel dataVisibleInChart;
    //
    private JpowderInternalframe3D powderFrame;
    private Vector<String> metaColumnesName = new Vector<String>();
    //
    private Cursor waitCursor = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
    //
    private SortableTableModel stm; // Sortable file in NaturalOrder.
    private TableColumnModel tableColumnModel;
    private TableRowSorter<TableModel> sorter;
    //from another Window.
    private Vector<DataSet> existingDatasets;

    public UpdateMetaDataFrame() {
        //
    }

    /**
     * 
     * @param jif
     */
    public UpdateMetaDataFrame(JpowderInternalframe3D jif) {

        initComponents();


        //it closes the JVM - BUG!, need this adapter to override.
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                JFrame frame = (JFrame) e.getSource();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        powderFrame = jif;

        columnNames.add("MetaData Name");
        columnNames.add("MetaData Value");

        stm = new SortableTableModel(row, columnNames);
        table.setModel(stm);

        sorter = new TableRowSorter<TableModel>(stm);
        sorter.setComparator(0, new NaturalOrderComparator());
        sorter.sort();
        table.setRowSorter(sorter);

        existingDatasets = powderFrame.getPowderDataSet();

        if (existingDatasets == null) {
            return;
        }

        try {
            existingDatasets = powderFrame.getPowderDataSet();
        } catch (NullPointerException e) {
            return;
        }

        for (int i = 0; i < existingDatasets.size(); i++) {
            //add column name
            HashMap<String, MetaData> hm = existingDatasets.get(i).getMeta();

            Set keys = hm.keySet();
            for (Iterator a = keys.iterator(); a.hasNext();) {
                String key = (String) a.next();
                MetaData value = (MetaData) hm.get(key);
                System.out.println("Fetch Meta data and value is: " + key + " = " + value.getValue());

                Vector x = existingDatasets.get(i).getX();
                System.out.println("Value in X is: " + x);
                Vector y = existingDatasets.get(i).getY();
                System.out.println("Value in Y is: " + y);

                row = new Vector<String>();
                row.add(key);

                try {
                    Double metaD = Double.parseDouble(value.getValue().toString());
                    row.add(String.valueOf(metaD));
                } catch (NumberFormatException e) {
                    row.add(value.getValue().toString());
                }
            }
            stm.addRow(row);
        }

        //Setting meta-data hashmap in dataset
//        for (int i = 0; i < existingDatasets.size(); i++) {
//            HashMap<String, MetaData> hm = new HashMap<String, MetaData>();
//            hm.put("Name", new MetaData<String>(stm.getValueAt(i, 0).toString()));
//            // ignore Path column but see if any other meta data defined
//            for (int iCol = 2; iCol < table.getColumnCount(); iCol++) {
//                try {
//                    Double metaD = Double.parseDouble(stm.getValueAt(i, iCol).toString());
//                    hm.put(stm.getColumnName(iCol), new MetaData<Double>(metaD));
//                } catch (NumberFormatException e) {
//                    hm.put(stm.getColumnName(iCol), new MetaData<String>(stm.getValueAt(i, iCol).toString()));
//                }
//            }
//            existingDatasets.get(i).addMetaData(hm);
//        }
        table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

        //KP 28 April second try to reordering rows.
        table.setDragEnabled(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        table.getTableHeader().setReorderingAllowed(true);


        //-- ComboboxModel --
        plotAsComboBox.setModel( new DefaultComboBoxModel(getPlotableColumnNames(columnNames)));

        addWindowListener(
                new WindowAdapter() {

                    @Override
                    public void windowClosing(WindowEvent event) {
                        stm.setRowCount(0);
                    }
                });

    }

    /**
     * Return the plotable column names
     */
    private Vector<String> getPlotableColumnNames(Vector<String> names) {
        Vector<String> retVal = new Vector<String>();
        for (int i = 0; i <
                names.size(); i++) {
            String bob = names.get(i);
            // add just Name
            if (bob.trim().equalsIgnoreCase("MetaData Name")) {
                retVal.add("Name");
            } else if (bob.trim().equalsIgnoreCase("MetaData Value")) {
                //Dont add it.
            } else {
                // accept everything.
                retVal.add(names.get(i));
            }

        }
        return retVal;
    }

    public void firstColumn() {
        table.getColumnModel().getColumn(0).setCellRenderer(
                table.getTableHeader().getDefaultRenderer());
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(0).setResizable(false);
        table.setPreferredScrollableViewportSize(getPreferredSize());
    }

    /**
     * adding column to the table.
     */
    public void addColumn() {
        JTextField textField = new JTextField();
        Object[] options = {"Yes", "No"};
        int n = JOptionPane.showOptionDialog(this,
                ///null,
                textField,
                "Set Column Name",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.DEFAULT_OPTION,
                null, //do not use a custom Icon
                options, //the titles of buttons
                "i");
        //options[0]); //default button title
        if (n == 0) {

            Double defaultMetaColumnValue = 0.0;

            if (!textField.getText().isEmpty()) {
                //update column name to DefaultTable model.
                int rows = table.getRowCount();
                //Double[] values = new Double[rows];
                String[] valueString = new String[rows];

                for (int j = 0; j <
                        rows; j++) {
                    defaultMetaColumnValue++;
                    valueString[j] = defaultMetaColumnValue.toString();
                }

                String capFirstCharString = StringUtil.capitalize(textField.getText());

                // Make first character Uppercase.
                stm.addColumn(capFirstCharString, valueString);
                // update Name to ComboBox model.
                plotAsComboBox.addItem(capFirstCharString);
                // set renderer to the lastest added column.
                for (int i = 0; i <
                        columnNames.size(); i++) {
                    //set renderer to the column.
                    //tableColumnModel.getColumn(i).setHeaderRenderer(renderer);
                    sorter.setComparator(i, new NaturalOrderComparator());
                }

                metaColumnesName.add(capFirstCharString);
            } else {
                JOptionPane.showMessageDialog(null, "Please enter column's name.");
                addColumn();

            }
        } else {
            return;
        }

    }

    /**
     * Add file to table by filePaths
     * @param filePath
     */
    public void addFilesToTable(String filePath) {
        addaFileToTable(new File(filePath));
    }

    /**
     * adding a single file at the time to the table.
     * @param file
     */
    public void addFilesToTable(File[] file) {

        for (int i = 0; i <
                file.length; i++) {
            row = new Vector<String>();
            row.add(file[i].getName());
            row.add(file[i].toString());
            stm.addRow(row);
        }

    }

    /**
     * Adding array of files to the table.
     * @param afile
     */
    public void addaFileToTable(File afile) {
        row = new Vector<String>();
        row.add(afile.getName());
        row.add(afile.toString());
        stm.addRow(row);
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
        plotButton = new javax.swing.JButton();
        removeRowButton = new javax.swing.JButton();
        removeAllRowsButton = new javax.swing.JButton();
        addColumnButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        plotAsComboBox = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        statusText = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Update Meta Data 3D");
        setIconImage(new ImageIcon(getClass().getResource("/images/JpowderLogo.png")).getImage());

        plotButton.setText("Plot");
        plotButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plotButtonActionPerformed(evt);
            }
        });

        removeRowButton.setText("Remove ");
        removeRowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeRowButtonActionPerformed(evt);
            }
        });

        removeAllRowsButton.setText("Remove All");
        removeAllRowsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeAllRowsButtonActionPerformed(evt);
            }
        });

        addColumnButton.setText("Add Column");
        addColumnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addColumnButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("as a function of");

        plotAsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plotAsComboBoxActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(removeRowButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeAllRowsButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(statusText, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(plotButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(plotAsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 539, Short.MAX_VALUE)
                        .addComponent(addColumnButton)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {removeAllRowsButton, removeRowButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(addColumnButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(plotAsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeRowButton)
                    .addComponent(removeAllRowsButton)
                    .addComponent(plotButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(statusText))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void plotButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plotButtonActionPerformed

        if (table.isEditing()) {
            table.getCellEditor().stopCellEditing();
        }

        setCursor(waitCursor);
        statusText.setText("Plotting . . . ");
        plotButton.setEnabled(false);
        // We're going to do something that takes a long time, so we spin off a
        // thread and update the display when we're done.
        Thread worker = new Thread() {

            @Override
            public void run() {
                // Something that takes a long time, change cursor.
                try {
                    //Setting meta-data hashmap in dataset
                    for (int i = 0; i < existingDatasets.size(); i++) {

                        //TODO: get the existing X and Y values
                        HashMap<String, MetaData> hm = new HashMap<String, MetaData>();
                        hm.put("Name", new MetaData<String>(stm.getValueAt(i, 0).toString()));

                        for (int iCol = 2; iCol <
                                table.getColumnCount(); iCol++) {
                            try {
                                Double metaD = Double.parseDouble(stm.getValueAt(i, iCol).toString());
                                hm.put(stm.getColumnName(iCol), new MetaData<Double>(metaD));
                            } catch (NumberFormatException e) {
                                hm.put(stm.getColumnName(iCol), new MetaData<String>(stm.getValueAt(i, iCol).toString()));
                            }

                        }
                        existingDatasets.get(i).addMetaData(hm);
                    }

                    // finally plot the data from existing data.
                    String plotAsFunctionOf = plotAsComboBox.getSelectedItem().toString();

                    for (int i = 0; i < existingDatasets.size(); i++) {
                        Vector x = existingDatasets.get(i).getX();
                        System.out.println("Value in X is: " + x);

                        Vector y = existingDatasets.get(i).getY();
                        System.out.println("Value in Y is: " + y);
                    }


                    JpowderInternalframe3D internalframe = new JpowderInternalframe3D(
                            powderFrame.getDataVisibleInChartPanel(), existingDatasets, plotAsFunctionOf);
                    internalframe.setTitle(powderFrame.getNames());

                    Jpowder.updateJPowderInternalFrame(internalframe);
                    InternalFrameListener internalFrameListener = new InternalFrameIconifyListener(dataVisibleInChart);
                    internalframe.addInternalFrameListener(internalFrameListener);
                    Jpowder.getChartPlotter3D().add(internalframe);

                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                }
                // Report the result using invokeLater( ).
                SwingUtilities.invokeLater(
                        new Runnable() {

                            @Override
                            public void run() {
                                statusText.setText("Ready");
                                plotButton.setEnabled(true);
                                setVisible(false);
                                setCursor(defaultCursor);
                            }
                        });
            }
        };
        worker.start(); // So we don't hold up the dispatch thread

    }//GEN-LAST:event_plotButtonActionPerformed

    private void removeRowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeRowButtonActionPerformed
        int[] rows = table.getSelectedRows();
        if (rows.length > 0) {
            for (int i = 0; i <
                    rows.length; i++) {
                stm.removeRow(rows[i] - i);
            }//for

        }//if
    }//GEN-LAST:event_removeRowButtonActionPerformed

    private void removeAllRowsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeAllRowsButtonActionPerformed

        while (stm.getRowCount() > 0) {
            stm.removeRow(0);
        }
    }//GEN-LAST:event_removeAllRowsButtonActionPerformed

    private void addColumnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addColumnButtonActionPerformed
        addColumn();
    }//GEN-LAST:event_addColumnButtonActionPerformed

    private void plotAsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plotAsComboBoxActionPerformed
    }//GEN-LAST:event_plotAsComboBoxActionPerformed

    //for unit testing purpose  03/03/2012 - KP
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
//        UpdateMetaDataFrame frame = new UpdateMetaDataFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        //Display the window.
//        frame.pack();
//        frame.setVisible(true);
    }

    public static void getList(String directory) {
        File path = new File(directory);
        String[] list = path.list();
        String element;

        for (int i = 0; i <
                list.length; i++) {
            element = list[i];

        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addColumnButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox plotAsComboBox;
    private javax.swing.JButton plotButton;
    private javax.swing.JButton removeAllRowsButton;
    private javax.swing.JButton removeRowButton;
    private javax.swing.JLabel statusText;
    private final javax.swing.JTable table = new javax.swing.JTable();
    // End of variables declaration//GEN-END:variables
}
