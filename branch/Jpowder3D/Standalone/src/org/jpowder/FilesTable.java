/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FilesTable.java
 *
 * Created on 09-Jun-2010, 13:27:28
 */
package org.jpowder;

//import com.generationjava.io.xml.AbstractXmlWriter;
import org.jpowder.InernalFrame.JpowderInternalframe3D;
import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;
import org.jpowder.InernalFrame.InternalFrameIconifyListener;
import org.jpowder.dataset.DataSet;
import org.jpowder.dataset.IO_MetaFile;
import org.jpowder.fileCabinet.PowderFileCabinet;

/**
 *
 * @author qyt21516
 */
public class FilesTable extends javax.swing.JFrame {

    private static DefaultTableModel defaultTableModel;
    JFileChooser chooser = new JFileChooser(new File("C:/Documents and Settings/qyt21516/Desktop/My Dropbox"));
    private Vector<String> columnNames = new Vector<String>();
    private Vector<String> row = new Vector<String>();
    private static File afile;
    private DataVisibleInChart dataVisibleInChart = new DataVisibleInChart();
    private Vector<String> comboBoxList = new Vector<String>();
    private Vector<Vector<String>> tableDataVector = new Vector<Vector<String>>();

    /** Creates new form FilesTable */
    public FilesTable(DataVisibleInChart dvic) {

        this.dataVisibleInChart = dvic;

        if (defaultTableModel != null) {
            defaultTableModel.getDataVector().removeAllElements();//remove all the rows from table
            }
        columnNames.add("Index");
        columnNames.add("Name");
        columnNames.add("Size");
        columnNames.add("Type");
        columnNames.add("Date Modified");
        columnNames.add("Path");

        defaultTableModel = new DefaultTableModel(row, columnNames) {

            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0 || column == 1 || column == 2 || column == 3) {
                    return false;
                } else {
                    return true;
                }
            }
        };

        initComponents();

        importData3DTable.getTableHeader().addMouseListener(new TablePopUpListener(columnHeaderPopMenu));
        comboBoxList.add("Index");
        plotAsComboBox.setModel(new DefaultComboBoxModel(comboBoxList));
        importData3DTable.getTableHeader().setReorderingAllowed(false);
        importData3DTable.addMouseListener(new TablePopUpListener(rowHeaderPopMenu));

        firstColumn();

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent event) {

                while (defaultTableModel.getRowCount() > 0) {
                    defaultTableModel.removeRow(0);
                }

            }
        });


    }

    public void firstColumn() {

        importData3DTable.getColumnModel().getColumn(0).setCellRenderer(
                importData3DTable.getTableHeader().getDefaultRenderer());
        importData3DTable.getColumnModel().getColumn(0).setPreferredWidth(10);
        importData3DTable.getColumnModel().getColumn(0).setResizable(false);
        importData3DTable.setPreferredScrollableViewportSize(getPreferredSize());

    }

    public static File getAFile() {
        return afile;
    }

    public static DefaultTableModel getDefaultTableModel() {
        return defaultTableModel;
    }

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
            defaultTableModel.addColumn(textField.getText());

            comboBoxList.add(textField.getText());
          

        } else {
            return;
        }
        firstColumn();

//        importData3DTable.moveColumn( importData3DTable.getColumnCount() - 1,
//                (importData3DTable.getColumnCount() + 2) - importData3DTable.getColumnCount());
    }

    public void addFilesToTable(File[] file) {

        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
        for (int i = 0; i < file.length; i++) {
            row = new Vector<String>();

            row.add(String.valueOf(i));
            row.add(file[i].getName());
            row.add(String.valueOf(file[i].length() / 1024) + " KB");
            row.add(chooser.getTypeDescription(file[i]));
            row.add(dateFormat.format(new Date(file[i].lastModified())));
            row.add(file[i].toString());

            defaultTableModel.addRow(row);
        }
    }

    public void addaFileToTable(File afile) {
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");

        row = new Vector<String>();

        row.add(String.valueOf(defaultTableModel.getRowCount()));

        row.add(afile.getName());
        row.add(String.valueOf(afile.length() / 1024) + " KB");
        row.add(chooser.getTypeDescription(afile));
        row.add(dateFormat.format(new Date(afile.lastModified())));
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        importData3DTable = new javax.swing.JTable();
        plotButton = new javax.swing.JButton();
        removeRowButton = new javax.swing.JButton();
        removeAllRowsButton = new javax.swing.JButton();
        fileChooserButton = new javax.swing.JButton();
        loadMetaFileButton = new javax.swing.JButton();
        saveMetaFileButton = new javax.swing.JButton();
        addColumnButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        plotAsComboBox = new javax.swing.JComboBox();
        jButton6 = new javax.swing.JButton();

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

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        importData3DTable.setAutoCreateRowSorter(true);
        importData3DTable.setModel(defaultTableModel);
        //jTable1.getColumnModel().removeColumn(importData3DTable.getColumn("File"));
        importData3DTable.setGridColor(new java.awt.Color(255, 255, 255));
        importData3DTable.setSelectionBackground(new java.awt.Color(90, 145, 233));
        importData3DTable.setShowHorizontalLines(false);
        importData3DTable.setShowVerticalLines(false);
        jScrollPane1.setViewportView(importData3DTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
        );

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

        fileChooserButton.setText("ImportFiles");
        fileChooserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileChooserButtonActionPerformed(evt);
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

        plotAsComboBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                plotAsComboBoxMouseEntered(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 12));
        jButton6.setText("?");
        jButton6.setFocusPainted(false);
        jButton6.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton6MouseEntered(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(removeRowButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeAllRowsButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                        .addComponent(plotButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(plotAsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(fileChooserButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(loadMetaFileButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saveMetaFileButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 253, Short.MAX_VALUE)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(jButton6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addColumnButton))
                    .addComponent(fileChooserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

    private void fileChooserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileChooserButtonActionPerformed

        chooser.setMultiSelectionEnabled(true);
        chooser.showOpenDialog(chooser);
        addFilesToTable(chooser.getSelectedFiles());

    }//GEN-LAST:event_fileChooserButtonActionPerformed

    private void plotButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plotButtonActionPerformed

        System.out.println(importData3DTable.getColumnCount());
//        Jpowder.getChartPlotter().remove(Jpowder.getMessageLabel());
        System.out.println(defaultTableModel.getValueAt(0, columnNames.size()-2));
        System.out.println(columnNames.size());
        Vector<DataSet> datasets = new Vector<DataSet>();
        HashMap<String, String> hashMap = new LinkedHashMap<String, String>();
        PowderFileCabinet mPowderFileCabinet = new PowderFileCabinet();
        String fileName;
        String paths;
        // loop over the selected file
        for (int i = 0, n = defaultTableModel.getRowCount(); i < n; i++) {
//            afile = new File(String.valueOf(defaultTableModel.getValueAt(i, 5)));
//            System.out.println(afile);
//            fileName = afile.getName();
            hashMap.put(String.valueOf(defaultTableModel.getValueAt(i, 5)),
                    String.valueOf(defaultTableModel.getValueAt(i, 5)));

        }//for

        for (Map.Entry<String, String> entry : hashMap.entrySet()) {

            fileName = entry.getKey();
            paths = entry.getValue();

//            System.out.println(afile);
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

        // finally plot the data
        JpowderInternalframe3D internalframe = new JpowderInternalframe3D(dataVisibleInChart, datasets);
        Jpowder.jpowderInternalFrameUpdate(internalframe);
        InternalFrameListener internalFrameListener = new InternalFrameIconifyListener(dataVisibleInChart);
        internalframe.addInternalFrameListener(internalFrameListener);
        Jpowder.getChartPlotter3D().add(internalframe);
        this.setVisible(false);

    }//GEN-LAST:event_plotButtonActionPerformed

    private void removeRowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeRowButtonActionPerformed

        if (defaultTableModel.getColumnCount() - 1 != 0) {
            defaultTableModel.removeRow(importData3DTable.getSelectedRow());
            System.out.println(defaultTableModel.getRowCount() - 1);
            importData3DTable.setRowSelectionInterval(defaultTableModel.getRowCount() - 1, defaultTableModel.getRowCount() - 1);
        } else {
            return;
        }

    }//GEN-LAST:event_removeRowButtonActionPerformed

    private void removeAllRowsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeAllRowsButtonActionPerformed
        while (defaultTableModel.getRowCount() > 0) {
            defaultTableModel.removeRow(0);
        }


    }//GEN-LAST:event_removeAllRowsButtonActionPerformed

    private void jButton6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseEntered
        jButton6.setRolloverEnabled(true);

        Graphics g = jButton6.getGraphics();
        Graphics2D g2 = (Graphics2D) g.create();
        g.setColor(Color.BLACK);
        if (jButton6.getModel().isRollover()) {
            g2.setColor(Color.RED);
        }
        jButton6.paint(g2);

    }//GEN-LAST:event_jButton6MouseEntered

    private void addColumnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addColumnButtonActionPerformed
        addColumn();
    }//GEN-LAST:event_addColumnButtonActionPerformed

    private void plotAsComboBoxMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plotAsComboBoxMouseEntered
        plotAsComboBox.setModel(new DefaultComboBoxModel(comboBoxList));
    }//GEN-LAST:event_plotAsComboBoxMouseEntered

    private void addColumnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addColumnActionPerformed
        addColumn();
    }//GEN-LAST:event_addColumnActionPerformed

    private void removeColumnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeColumnActionPerformed
//        importData3DTable.removeColumn(importData3DTable.getColumnModel().getColumn(importData3DTable.getSelectedColumn()));
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

        columnNames.clear();
        IO_MetaFile metaFile = new IO_MetaFile(defaultTableModel, columnNames, row);
        metaFile.read_MetaFile(file);

    }//GEN-LAST:event_loadMetaFileButtonActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        IO_MetaFile metaFile = new IO_MetaFile(defaultTableModel, columnNames, row);
        metaFile.save_MetaFile(new File("C:/Documents and Settings/qyt21516/Desktop/xxx.xml"));
    }//GEN-LAST:event_jButton6ActionPerformed

    private void showPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            columnHeaderPopMenu.show(e.getComponent(), e.getX(),
                    e.getY());

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FilesTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
//                new FilesTable().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addColumn;
    private javax.swing.JButton addColumnButton;
    private javax.swing.JMenuItem addRow;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPopupMenu columnHeaderPopMenu;
    private javax.swing.JButton fileChooserButton;
    private javax.swing.JTable importData3DTable;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
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
