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
 * Import3DFilesTable.java
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
package org.jpowder;

import java.awt.Cursor;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import org.jpowder.TableHelper.TableTransferHandler;
import org.jpowder.InernalFrame.JpowderInternalframe3D;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.jpowder.InernalFrame.InternalFrameIconifyListener;
import org.jpowder.MetaFile.IO_MetaFile;
import org.jpowder.TableHelper.HeaderListener;
import org.jpowder.TableHelper.ListRowHeader;
import org.jpowder.TableHelper.SortButtonRenderer;
import org.jpowder.TableHelper.SortableTableModel;
import org.jpowder.dataset.DataSet;
//import org.jpowder.dataset.IO_MetaFile;
import org.jpowder.fileCabinet.PowderFileCabinet;
import org.jpowder.util.HashMapHelper;
import org.jpowder.util.NaturalOrderComparator;
import org.jpowder.util.StringUtil;
import org.jpowder.dataset.MetaData;

/**
 * A table which contains File names, File filePaths and meta data of powder
 * data files which are going to be plotted in 3D.
 *
 *
 */
public class Import3DFilesTable extends javax.swing.JFrame {

    JFileChooser chooser = new JFileChooser(new File("C:/Documents and Settings/qyt21516/Desktop/My Dropbox"));
    private Vector<String> columnNames = new Vector<String>();
    private Vector<String> row = new Vector<String>();
    //private static File afile;
    private DataVisibleInChart dataVisibleInChart = new DataVisibleInChart();
    //private Vector<Vector<String>> tableDataVector = new Vector<Vector<String>>();
    private Vector<String> metaColumnesName = new Vector<String>();
    private Cursor waitCursor = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
    private SortableTableModel stm; // Sortable file in NaturalOrder.
    private TableColumnModel tableColumnModel;
    private TableRowSorter<TableModel> sorter;

    /** Creates new form FilesTable */
    public Import3DFilesTable(DataVisibleInChart dvic) {
        this.dataVisibleInChart = dvic;

        if (stm != null) {
            stm.getDataVector().removeAllElements();//remove all the rows from table
            }

        columnNames.add("Name");
        columnNames.add("Path");

        metaColumnesName.add("Name");
        metaColumnesName.add("Path");

        initComponents();

        stm = new SortableTableModel(row, columnNames);
        importData3DTable.setModel(stm);

        sorter = new TableRowSorter<TableModel>(stm);
        sorter.setComparator(0, new NaturalOrderComparator());
        sorter.sort();
        importData3DTable.setRowSorter(sorter);

        //KP 28 April second try to reordering rows.
        importData3DTable.setDragEnabled(true);
        importData3DTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        //TODO: KP This reorder
        importData3DTable.setTransferHandler(new TransferHandler() {

            private int sourceRow;
            private int destination;

            @Override
            public int getSourceActions(JComponent c) {
                return DnDConstants.ACTION_COPY_OR_MOVE;
            }

            @Override
            public Transferable createTransferable(JComponent comp) {
                final JTable table = (JTable) comp;
                int row = table.getSelectedRow();
                //converter.
                int selection = table.convertRowIndexToModel(row);
                sourceRow = selection;

                StringBuffer buff = new StringBuffer();
                System.out.println("In createTransferable(), selected row is: " + sourceRow);
                int colCount = table.getColumnCount();

                for (int j = 0; j < colCount; j++) {
                    Object val = stm.getValueAt(sourceRow, j);

                    buff.append(val == null ? "" : val.toString());
                    //System.out.println("In NewTry(), my value is: " + val.toString());
                    if (j != colCount - 1) {
                        buff.append(",");
                    }
                }

                StringSelection transferable = new StringSelection(buff.toString());
                //KP 01/05/2012..
                //Make it blank, if wanted.
                //table.getModel().setValueAt(null, sourceRow, 0);
                return transferable;
            }

            @Override
            public boolean importData(TransferSupport support) {
                if (!support.isDrop()) {
                    return false;
                }
                if (!canImport(support)) {
                    return false;
                }

                JTable table = (JTable) support.getComponent();
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

                JTable.DropLocation dl = (JTable.DropLocation) support.getDropLocation();

                int dropRow = dl.getRow();
                int selection = table.convertRowIndexToModel(dropRow);
                int col = dl.getColumn();
                destination = selection;

                System.out.println("In importData(), dropped rows are:.." + destination + " ... ");

                String data;
                try {
                    data = (String) support.getTransferable().getTransferData(DataFlavor.stringFlavor);
                    System.out.println("In importData(), data is:.." + data);

                    int colCount = stm.getColumnCount();
                    StringBuffer dataInDest = new StringBuffer();
                    //append strings in the row.
                    for (int j = 0; j < colCount; j++) {
                        //Object val = table.getModel().getValueAt(destination, j);
                        Object val = stm.getValueAt(destination, j);
                        dataInDest.append(val == null ? "" : val.toString());

                        if (j != colCount - 1) {
                            dataInDest.append(",");
                        }
                    }
                    System.out.println("In importData(), data in destination is: " + dataInDest.toString());
                } catch (UnsupportedFlavorException e) {
                    return false;
                } catch (IOException e) {
                    return false;
                }

                //Swap rows.
                stm.moveRow(sourceRow, sourceRow, destination);
                //TODO: KP needs to revise index in SortTableModel.
                //tableModel.setValueAt(data, dropRow, col);
                return true;
            }

            @Override
            public boolean canImport(TransferHandler.TransferSupport info) {
                if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    return false;
                }
                info.setShowDropLocation(true);


                return true;
            }
        });

        importData3DTable.getTableHeader().setReorderingAllowed(true);
        //importData3DTable.setAutoCreateColumnsFromModel(false);
        //importData3DTable.addMouseListener(new TablePopUpListener(rowHeaderPopMenu));
        //-- moveable rows 25/02/2012 - KP

        //KP 28 April 2012
        ListRowHeader listRow = new ListRowHeader(importData3DTable);

        //-- ComboboxModel --
        plotAsComboBox.setModel(new DefaultComboBoxModel(getPlotableColumnNames(columnNames)));

        addWindowListener(new WindowAdapter() {
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
            if (bob.trim().equalsIgnoreCase("Path")) {
            } else {
                retVal.add(names.get(i));
            }
        }
        return retVal;
    }

    public void firstColumn() {

        importData3DTable.getColumnModel().getColumn(0).setCellRenderer(
                importData3DTable.getTableHeader().getDefaultRenderer());
        importData3DTable.getColumnModel().getColumn(0).setPreferredWidth(10);
        importData3DTable.getColumnModel().getColumn(0).setResizable(false);
        importData3DTable.setPreferredScrollableViewportSize(getPreferredSize());

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
                int rows = importData3DTable.getRowCount();
                //Double[] values = new Double[rows];
                String[] valueString = new String[rows];

                for (int j = 0; j < rows; j++) {
                    defaultMetaColumnValue++;
                    valueString[j] = defaultMetaColumnValue.toString();
                }

                String capFirstCharString = StringUtil.capitalize(textField.getText());

                // Make first character Uppercase.
                //TODO: addColumn to TableModel and it does not get Double Class.
                //It becomes
                stm.addColumn(capFirstCharString, valueString);
                // update Name to ComboBox model.
                plotAsComboBox.addItem(capFirstCharString);
                // set renderer to the lastest added column.
                for (int i = 0; i < columnNames.size(); i++) {
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

//        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
        for (int i = 0; i < file.length; i++) {
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
        importFileButton = new javax.swing.JButton();
        loadMetaFileButton = new javax.swing.JButton();
        saveMetaFileButton = new javax.swing.JButton();
        addColumnButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        plotAsComboBox = new javax.swing.JComboBox();
        helpButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        statusText = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Plot Data 3D");
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

        importFileButton.setText("ImportFiles");
        importFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importFileButtonActionPerformed(evt);
            }
        });

        loadMetaFileButton.setText("Load Meta File");
        loadMetaFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadMetaFileButtonActionPerformed(evt);
            }
        });

        saveMetaFileButton.setText("Save Meta File");
        saveMetaFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMetaFileButtonActionPerformed(evt);
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

        helpButton.setFont(new java.awt.Font("Tahoma", 1, 12));
        helpButton.setText("?");
        helpButton.setFocusPainted(false);
        helpButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        importData3DTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(importData3DTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
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
                        .addComponent(importFileButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(loadMetaFileButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saveMetaFileButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 259, Short.MAX_VALUE)
                                .addComponent(helpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(addColumnButton))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {removeAllRowsButton, removeRowButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(loadMetaFileButton)
                            .addComponent(saveMetaFileButton)
                            .addComponent(helpButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addColumnButton))
                    .addComponent(importFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
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

    private void importFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importFileButtonActionPerformed

        chooser.setMultiSelectionEnabled(true);
        chooser.showOpenDialog(chooser);
        addFilesToTable(chooser.getSelectedFiles());
    }//GEN-LAST:event_importFileButtonActionPerformed

    private void plotButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plotButtonActionPerformed

        setCursor(waitCursor);
        statusText.setText("Plotting . . . ");
        plotButton.setEnabled(false);
        // We're going to do something that takes a long time, so we spin off a
        // thread and update the display when we're done.
        Thread worker = new Thread() {

            @Override
            public void run() {
                // Something that takes a long time. In real life, this might be a DB
                // query, remote method invocation, etc.
                try {
                    //multiple datasets
                    Vector<DataSet> datasets = new Vector<DataSet>();
                    // map linking filenames and paths.
                    // LinkedHaspMap is used in the order in which the entries were put into the map
                    HashMap<String, String> fileNameAndPathMap = new LinkedHashMap<String, String>();
                    PowderFileCabinet mPowderFileCabinet = new PowderFileCabinet();
                    String fileName, filePaths;

                    // loop over selected files
                    for (int i = 0, n = stm.getRowCount(); i <  n; i++) {
                        // assume in this loop for now that path the 2nd column
                        fileNameAndPathMap.put(String.valueOf(stm.getValueAt(i, 0)),
                                String.valueOf(stm.getValueAt(i, 1)));

                    }//for

                    for (Map.Entry<String, String> entry : fileNameAndPathMap.entrySet()) {
                        fileName = entry.getKey();
                        filePaths =
                                entry.getValue();
                        System.out.println("File name is: " + fileName + " and Path is: " + filePaths);

                        //System.out.println(afile);
                        if (mPowderFileCabinet.checkAcceptedFileType(fileName)) {
                            //This enforces to be in a path only? Is this right?
                            Vector<DataSet> allDatasets = PowderFileCabinet.createDataSetFromPowderFile(filePaths.trim());
                            if (allDatasets == null) {
                                return;
                            }

                            for (int iSet = 0; iSet < allDatasets.size(); iSet++) {
                                DataSet oneDataset = allDatasets.elementAt(iSet);
                                if (oneDataset != null) {
                                    datasets.add(oneDataset);
                                }
                            }

                        } else {
                            //TODO: Check this needed why?
                            javax.swing.JOptionPane.showMessageDialog(null, "Only ASCII file please.");
                            break;
                        }
                    }

                    //Setting meta-data hashmap in dataset
                    for (int i = 0; i < datasets.size(); i++) {
                        HashMap<String, MetaData> hm = new HashMap<String, MetaData>();
                        hm.put("Name", new MetaData<String>(stm.getValueAt(i, 0).toString()));
                        // ignore Path column but see if any other meta data defined
                        for (int iCol = 2; iCol < importData3DTable.getColumnCount(); iCol++) {
                            try {
                                Double metaD = Double.parseDouble(stm.getValueAt(i, iCol).toString());
                                hm.put(stm.getColumnName(iCol), new MetaData<Double>(metaD));
                            } catch (NumberFormatException e) {
                                hm.put(stm.getColumnName(iCol), new MetaData<String>(stm.getValueAt(i, iCol).toString()));
                            }
                        }
                        datasets.get(i).addMetaData(hm);
                    }

                    // finally plot the data
                    String plotAsFunctionOf = plotAsComboBox.getSelectedItem().toString();
                    //Plot in 3D

                    //Plot 3D with extra fileNameAndPath 10/03/2012.
                    JpowderInternalframe3D internalframe = new JpowderInternalframe3D(
                            dataVisibleInChart, datasets, plotAsFunctionOf, fileNameAndPathMap);

                    //make sure the file name from start - finish is displayed on the title bar.
                    String[] files = HashMapHelper.convertKeyToArray(fileNameAndPathMap);
                    String first = files[0];
                    String last = files[files.length - 1];

                    internalframe.setTitle(first + " - " + last);

                    //
                    Jpowder.updateJPowderInternalFrame(internalframe);
                    InternalFrameListener internalFrameListener = new InternalFrameIconifyListener(dataVisibleInChart);
                    internalframe.addInternalFrameListener(internalFrameListener);
                    Jpowder.getChartPlotter3D().add(internalframe);
                    

                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                }
                // Report the result using invokeLater( ).
                SwingUtilities.invokeLater(new Runnable() {
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

        int[] rows = importData3DTable.getSelectedRows();

        if (rows.length > 0) {
            for (int i = 0; i <
                    rows.length; i++) {
                //defaultTableModel.removeRow(rows[i] - i);
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

    private void saveMetaFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMetaFileButtonActionPerformed

        chooser.setMultiSelectionEnabled(false);
        chooser.showSaveDialog(chooser);
        File file = chooser.getSelectedFile();

        IO_MetaFile metaFile = new IO_MetaFile(stm, columnNames, row);
        metaFile.save_MetaFile(file);
    }//GEN-LAST:event_saveMetaFileButtonActionPerformed

    private void loadMetaFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadMetaFileButtonActionPerformed

        chooser.setMultiSelectionEnabled(false);
        chooser.showOpenDialog(chooser);
        File file = chooser.getSelectedFile();

        //IO_MetaFile metaFile = new IO_MetaFile(stm, columnNames, row);
        IO_MetaFile metaFile = new IO_MetaFile(importData3DTable, columnNames, row);
        metaFile.read_MetaFile(file);

        // TODO: We need to set or clear plotAsComboBox, and add structure of the file to the models.
        plotAsComboBox.setModel(new DefaultComboBoxModel(getPlotableColumnNames(columnNames)));
        // TODO: Deselect the file selected in the left panel.
    }//GEN-LAST:event_loadMetaFileButtonActionPerformed

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed

        /*JpowderInternalframe3D inFocus = Jpowder.internalFrameInFocus3D;

        System.out.println(plotAsComboBox.getSelectedIndex());
        System.out.println(defaultTableModel.getValueAt(0, plotAsComboBox.getSelectedIndex()));
        HashMap<String, Double> hm = new LinkedHashMap<String, Double>();
        if (plotAsComboBox.getSelectedIndex() == 0 || plotAsComboBox.getSelectedIndex() == 1) {
        System.out.println("xxxxxxxxx");
        hm.put((String) stm.getValueAt(0, 0), 0.0);
        } else {
        }
        System.out.println(inFocus.getPowderDataSet().get(0).getMetaData(defaultTableModel.getValueAt(0, 0).toString()));
         * */
//        System.out.println(inFocus.getPowderDataSet().get(0).getFileName());
//        inFocus.getPowderDataSet().get(0).addMetaData(null);
    }//GEN-LAST:event_helpButtonActionPerformed

    private void plotAsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plotAsComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_plotAsComboBoxActionPerformed

    //for unit testing purpose  03/03/2012 - KP
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
//        Import3DFilesTable frame = new Import3DFilesTable();
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
    private javax.swing.JButton helpButton;
    private final javax.swing.JTable importData3DTable = new javax.swing.JTable();
    private javax.swing.JButton importFileButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loadMetaFileButton;
    private javax.swing.JComboBox plotAsComboBox;
    private javax.swing.JButton plotButton;
    private javax.swing.JButton removeAllRowsButton;
    private javax.swing.JButton removeRowButton;
    private javax.swing.JButton saveMetaFileButton;
    private javax.swing.JLabel statusText;
    // End of variables declaration//GEN-END:variables
}
