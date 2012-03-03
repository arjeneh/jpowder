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
 * Author(s):  M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder;

import org.jpowder.TableHelper.TableTransferHandler;
import org.jpowder.InernalFrame.JpowderInternalframe3D;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;
import org.jpowder.InernalFrame.InternalFrameIconifyListener;
import org.jpowder.dataset.DataSet;
import org.jpowder.dataset.IO_MetaFile;
import org.jpowder.fileCabinet.PowderFileCabinet;
import org.jpowder.util.VectorMiscUtil;

/**
 * A table which contains File names, File paths and meta data of powder
 * data files which are going to be plotted in 3D.
 *
 *
 */

// TODO: Convert Double to String after 
public class Import3DFilesTable extends javax.swing.JFrame {

    private static DefaultTableModel defaultTableModel;
    JFileChooser chooser = new JFileChooser(new File("C:/Documents and Settings/qyt21516/Desktop/My Dropbox"));
    private Vector<String> columnNames = new Vector<String>();
    private Vector<String> row = new Vector<String>();
    private static File afile;
    private DataVisibleInChart dataVisibleInChart = new DataVisibleInChart();
    private Vector<Vector<String>> tableDataVector = new Vector<Vector<String>>();
    private Vector<String> metaColumnesName = new Vector<String>();

    /** Creates new form FilesTable */
    public Import3DFilesTable(DataVisibleInChart dvic) {
        this.dataVisibleInChart = dvic;

        if (defaultTableModel != null) {
            defaultTableModel.getDataVector().removeAllElements();//remove all the rows from table
            }
        //columnNames.add("Index");
        columnNames.add("Name");
        columnNames.add("Path");

        metaColumnesName.add("Name");
        metaColumnesName.add("Path");

        defaultTableModel = new DefaultTableModel(row, columnNames) {

            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 1) {
                    return false;
                } else {
                    return true;
                }
            }
        };

        initComponents();

        importData3DTable.setModel(defaultTableModel);
        
        //-- moveable rows 25/02/2012 - KP
        importData3DTable.setDragEnabled(true);
        importData3DTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        importData3DTable.setTransferHandler(new TableTransferHandler());
        //-- moveable rows 25/02/2012 - KP

        importData3DTable.getTableHeader().addMouseListener(new TablePopUpListener(columnHeaderPopMenu));

        //plotAsComboBox.setModel(new DefaultComboBoxModel(getPlotableColumnNames(columnNames)));
        importData3DTable.getTableHeader().setReorderingAllowed(false);
        importData3DTable.addMouseListener(new TablePopUpListener(rowHeaderPopMenu));

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent event) {
                while (defaultTableModel.getRowCount() > 0) {
                    defaultTableModel.removeRow(0);
                }
            }
        });
    }

    //for unit testing purpose  03/03/2012 - KP
    public Import3DFilesTable() {
        //System.out.println(System.getProperty("user.home"));

        initComponents();
        //-- test rows 03/03/2012 - KP
        //addDummyFiles("C:\\Users\\Toshiba\\Desktop\\ZopDih\\");
        addDummyData();

        //-- moveable rows 25/02/2012 - KP
    }

    public void addDummyData() {
        Vector data = VectorMiscUtil.initXYData();

        Vector columns = new Vector();
        columns.add("Column Kreecha");
        columns.add("Column 2");

        defaultTableModel = new DefaultTableModel();
        defaultTableModel.setDataVector(data, columns);

        importData3DTable.getTableHeader().setReorderingAllowed(false);
        importData3DTable.setDragEnabled(true);
        importData3DTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        importData3DTable.setTransferHandler(new TableTransferHandler());
        importData3DTable.setModel(defaultTableModel);

    }

    //unit test
    private void addDummyFiles(String path) {
        //03/03/2012 - KP
        //addFilesToTable("C:\\Users\\Toshiba\\Desktop\\ZopDih\\");
        File dir = new File(path);
        String[] children = dir.list();
        if (children == null) {
            // Either dir does not exist or is not a directory
        } else {
            for (int i = 0; i < children.length; i++) {
                // Get filename of file or directory
                String filename = children[i];
            }
        }

        // It is also possible to filter the list of returned files.
        // This example does not return any files that start with `.'.
        FilenameFilter filter = new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return !name.startsWith(".");
            }
        };
        children = dir.list(filter);

        // The list of files can also be retrieved as File objects
        File[] files = dir.listFiles();
        // This filter only returns directories
        files = dir.listFiles();
        addFilesToTable(files);
        //03/03/2012 - KP
    }

    /**
     * Return the plotable column names
     */
    private Vector<String> getPlotableColumnNames(Vector<String> names) {
        Vector<String> retVal = new Vector<String>();
        for (int i = 0; i < names.size(); i++) {
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
        Object[] options = {"Yes",
            "No"};
        int n = JOptionPane.showOptionDialog(null,
                textField,
                "Set Column Name",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.DEFAULT_OPTION,
                null, //do not use a custom Icon
                options, //the titles of buttons
                options[0]); //default button title
        if (n == 0) {
            if (!textField.getText().isEmpty()) {
                defaultTableModel.addColumn(textField.getText());
                metaColumnesName.add(textField.getText());
            } else {
                JOptionPane.showMessageDialog(null, "Please enter column's name.");
                addColumn();
            }

        } else {
            return;
        }
//        firstColumn();

        importData3DTable.moveColumn(importData3DTable.getColumnCount() - 1,
                (importData3DTable.getColumnCount() + 2) - importData3DTable.getColumnCount());
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
            defaultTableModel.addRow(row);
        }
    }

    /**
     * Adding array of files to the table.
     * @param afile
     */
    public void addaFileToTable(File afile) {
//        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
        row = new Vector<String>();
//        rowsFiles.add(String.valueOf(defaultTableModel.getRowCount()));
        row.add(afile.getName());
//        rowsFiles.add(String.valueOf(afile.length() / 1024) + " KB");
//        rowsFiles.add(chooser.getTypeDescription(afile));
//        rowsFiles.add(dateFormat.format(new Date(afile.lastModified())));
        row.add(afile.toString());
        defaultTableModel.addRow(row);
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
        columnHeaderPopMenu = new javax.swing.JPopupMenu();
        addColumn = new javax.swing.JMenuItem();
        removeColumn = new javax.swing.JMenuItem();
        rowHeaderPopMenu = new javax.swing.JPopupMenu();
        addRow = new javax.swing.JMenuItem();
        removeRow = new javax.swing.JMenuItem();
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
        importData3DTable = new javax.swing.JTable();

        addColumn.setText("Add Column");
        addColumn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addColumnActionPerformed(evt);
            }
        });
        columnHeaderPopMenu.add(addColumn);

        removeColumn.setText("Remove Column");
        removeColumn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeColumnActionPerformed(evt);
            }
        });
        columnHeaderPopMenu.add(removeColumn);

        addRow.setText("Add Row");
        rowHeaderPopMenu.add(addRow);

        removeRow.setText("Remove Row");
        rowHeaderPopMenu.add(removeRow);

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
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
                    .addComponent(jLabel1))
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

        Vector<DataSet> datasets = new Vector<DataSet>();
        // map linking filename and path.
        // why LinkedHaspMap is used? --Kreecha P.
        HashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        PowderFileCabinet mPowderFileCabinet = new PowderFileCabinet();
        String fileName;
        String paths;

        // loop over the selected file
        for (int i = 0, n = defaultTableModel.getRowCount(); i < n; i++) {
            // assume in this loop for now that path the 2nd column
            linkedHashMap.put(String.valueOf(defaultTableModel.getValueAt(i, 0)),
                    String.valueOf(defaultTableModel.getValueAt(i, 1)));

        }//for

        for (Map.Entry<String, String> entry : linkedHashMap.entrySet()) {
            fileName = entry.getKey();
            paths = entry.getValue();
            System.out.println("File name is: " + fileName + " and Path is: " + paths);

            //System.out.println(afile);
            if (mPowderFileCabinet.checkAcceptedFileType(fileName)) {
                Vector<DataSet> allDatasets = PowderFileCabinet.createDataSetFromPowderFile(paths.trim());
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
                javax.swing.JOptionPane.showMessageDialog(null, "Only ASCII file please.");
                break;
            }
        }
        //Setting meta-data hashmap in dataset
        for (int i = 0; i < datasets.size(); i++) {
            HashMap<String, Double> hm = new HashMap<String, Double>();
            // For Name column for now label filenames artificially as 0,1,2...
            hm.put("Name", (double) i);
            // ignore Path column but see if any other meta data defined
            for (int iCol = 2; iCol < importData3DTable.getColumnCount(); iCol++) {
                hm.put(defaultTableModel.getColumnName(iCol),
                        Double.parseDouble(defaultTableModel.getValueAt(i, iCol).toString()));
            }
            datasets.get(i).addMetaData(hm);
        }

        // finally plot the data
        String plotAsFunctionOf = plotAsComboBox.getSelectedItem().toString();
        JpowderInternalframe3D internalframe = new JpowderInternalframe3D(dataVisibleInChart, datasets,
                plotAsFunctionOf);
        Jpowder.jpowderInternalFrameUpdate(internalframe);
        InternalFrameListener internalFrameListener = new InternalFrameIconifyListener(dataVisibleInChart);
        internalframe.addInternalFrameListener(internalFrameListener);
        Jpowder.getChartPlotter3D().add(internalframe);
        this.setVisible(false);

    }//GEN-LAST:event_plotButtonActionPerformed

    private void removeRowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeRowButtonActionPerformed

        int[] rows = importData3DTable.getSelectedRows();

        if (rows.length > 0) {
            for (int i = 0; i < rows.length; i++) {
                defaultTableModel.removeRow(rows[i] - i);
            }//for
        }//if
    }//GEN-LAST:event_removeRowButtonActionPerformed

    private void removeAllRowsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeAllRowsButtonActionPerformed
        while (defaultTableModel.getRowCount() > 0) {
            defaultTableModel.removeRow(0);
        }
    }//GEN-LAST:event_removeAllRowsButtonActionPerformed

    private void addColumnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addColumnButtonActionPerformed
        addColumn();
    }//GEN-LAST:event_addColumnButtonActionPerformed

    private void addColumnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addColumnActionPerformed
        addColumn();
    }//GEN-LAST:event_addColumnActionPerformed

    private void removeColumnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeColumnActionPerformed
        importData3DTable.removeColumn(importData3DTable.getColumnModel().getColumn(importData3DTable.getSelectedColumn()));
        System.out.println(importData3DTable.getSelectedColumn());
    }//GEN-LAST:event_removeColumnActionPerformed

    private void saveMetaFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMetaFileButtonActionPerformed

        chooser.setMultiSelectionEnabled(false);
        chooser.showSaveDialog(chooser);
        File file = chooser.getSelectedFile();

        IO_MetaFile metaFile = new IO_MetaFile(defaultTableModel, columnNames, row);
        metaFile.save_MetaFile(file);
    }//GEN-LAST:event_saveMetaFileButtonActionPerformed

    private void loadMetaFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadMetaFileButtonActionPerformed

        chooser.setMultiSelectionEnabled(false);
        chooser.showOpenDialog(chooser);
        File file = chooser.getSelectedFile();
        //columnNames.clear();
        IO_MetaFile metaFile = new IO_MetaFile(defaultTableModel, columnNames, row);
        metaFile.read_MetaFile(file);

        plotAsComboBox.setModel(new DefaultComboBoxModel(getPlotableColumnNames(columnNames)));
    }//GEN-LAST:event_loadMetaFileButtonActionPerformed

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed

        /*JpowderInternalframe3D inFocus = Jpowder.internalFrameInFocus3D;

        System.out.println(plotAsComboBox.getSelectedIndex());
        System.out.println(defaultTableModel.getValueAt(0, plotAsComboBox.getSelectedIndex()));
        HashMap<String, Double> hm = new LinkedHashMap<String, Double>();
        if (plotAsComboBox.getSelectedIndex() == 0 || plotAsComboBox.getSelectedIndex() == 1) {
        System.out.println("xxxxxxxxx");
        hm.put((String) defaultTableModel.getValueAt(0, 0), 0.0);
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

    private void showPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            columnHeaderPopMenu.show(e.getComponent(), e.getX(),
                    e.getY());

        }
    }

    //for unit testing purpose  03/03/2012 - KP
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        Import3DFilesTable frame = new Import3DFilesTable();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Display the window.
        frame.pack();
        frame.setVisible(true);

    }

    public static void getList(String directory) {
        File path = new File(directory);
        String[] list = path.list();
        String element;

        for (int i = 0; i < list.length; i++) {
            element = list[i];

        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addColumn;
    private javax.swing.JButton addColumnButton;
    private javax.swing.JMenuItem addRow;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPopupMenu columnHeaderPopMenu;
    private javax.swing.JButton helpButton;
    private javax.swing.JTable importData3DTable;
    private javax.swing.JButton importFileButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loadMetaFileButton;
    private javax.swing.JComboBox plotAsComboBox;
    private javax.swing.JButton plotButton;
    private javax.swing.JButton removeAllRowsButton;
    private javax.swing.JMenuItem removeColumn;
    private javax.swing.JMenuItem removeRow;
    private javax.swing.JButton removeRowButton;
    private javax.swing.JPopupMenu rowHeaderPopMenu;
    private javax.swing.JButton saveMetaFileButton;
    // End of variables declaration//GEN-END:variables
}
