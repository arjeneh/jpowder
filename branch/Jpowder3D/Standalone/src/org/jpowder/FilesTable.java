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
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;
import org.jpowder.Analysis.Jpowder_Reader;
import org.jpowder.dataset.DataSet;
import org.jpowder.fileCabinet.PowderFileCabinet;

/**
 *
 * @author qyt21516
 */
public class FilesTable extends javax.swing.JFrame {

    private static DefaultTableModel defaultTableModel;
    JFileChooser chooser = new JFileChooser(new File("C:/Documents and Settings/qyt21516/Desktop/My Dropbox"));
    Vector<String> columnNames = new Vector<String>();
    Vector<String> row = new Vector<String>();
    DataVisibleInChart dataVisibleInChart = new DataVisibleInChart();
    private static File afile;

    /** Creates new form FilesTable */
    public FilesTable() {


        if (defaultTableModel != null) {
            defaultTableModel.getDataVector().removeAllElements();//remove all the rows from table
            }
        columnNames.add("RN");
        columnNames.add("Name");
        columnNames.add("Size");
        columnNames.add("Type");
        columnNames.add("Date Modified");
        columnNames.add("File");
//        rowData.addElement(getFileName());
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


        plotAsComboBox.setModel(new DefaultComboBoxModel(columnNames));
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
        } else {
            return;
        }
         firstColumn();
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
        potButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        fileChooserButton = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
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

        potButton.setText("Plot");
        potButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                potButtonActionPerformed(evt);
            }
        });

        jButton1.setText("Remove ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Remove All");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        fileChooserButton.setText("ImportFiles");
        fileChooserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileChooserButtonActionPerformed(evt);
            }
        });

        jButton3.setText("Load Meta File");

        jButton4.setText("Save Meta File");

        jButton5.setText("Add Column");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel1.setText("As");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
                        .addComponent(potButton)
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
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 253, Short.MAX_VALUE)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton5))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton4)
                            .addComponent(jButton6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5))
                    .addComponent(fileChooserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(potButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(plotAsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fileChooserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileChooserButtonActionPerformed

        chooser.setMultiSelectionEnabled(true);

        chooser.showOpenDialog(chooser);

        addFilesToTable(chooser.getSelectedFiles());
//        for (int i = 0; i < chooser.getSelectedFiles().length; i++) {
//            row = new Vector<String>();
//
//            row.add(String.valueOf(i));
//            row.add(chooser.getSelectedFiles()[i].getName());
//            row.add(String.valueOf(chooser.getSelectedFiles()[i].length() / 1024) + " KB");
//            row.add(chooser.getTypeDescription(chooser.getSelectedFiles()[i]));
//            row.add(dateFormat.format(new Date(chooser.getSelectedFiles()[i].lastModified())));
//            row.add(chooser.getSelectedFiles()[i].toString());
//
//            defaultTableModel.addRow(row);
//        }


    }//GEN-LAST:event_fileChooserButtonActionPerformed

    private void potButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_potButtonActionPerformed


//        Jpowder.getChartPlotter().remove(Jpowder.getMessageLabel());
        Vector<DataSet> datasets = new Vector<DataSet>();
        HashMap<String, File> hashMap = new LinkedHashMap<String, File>();
        PowderFileCabinet mPowderFileCabinet = new PowderFileCabinet();

        String fileName;



        // loop over the selected file

        for (int i = 0, n = defaultTableModel.getRowCount(); i < n; i++) {
            afile = new File(String.valueOf(defaultTableModel.getValueAt(i, 5)));
            fileName = afile.getName();
            if (fileName.endsWith(".jpowder")) {
                Jpowder_Reader.read(afile);


//                int size = Jpowder_Reader.getLocalData().size();
//                for (int h = 0; h < size; h++) {
//                    System.out.println(Jpowder_Reader.getLocalData().get(h).get(1));
//                }


            }
            if (!fileName.endsWith(".jpowder")) {

                hashMap.put(fileName, afile.getAbsoluteFile());
            }
        }//for



        for (Map.Entry<String, File> entry : hashMap.entrySet()) {
            fileName = entry.getKey();
            afile = entry.getValue();
            if (mPowderFileCabinet.checkAcceptedFileType(fileName)) {

                Vector<DataSet> allDatasets = PowderFileCabinet.createDataSetFromPowderFile(afile.getAbsoluteFile());
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
        JpowderInternalframe2D internalframe = new JpowderInternalframe2D(dataVisibleInChart, datasets);
        Jpowder.jpowderInternalFrameUpdate(internalframe);

        InternalFrameListener internalFrameListener = new InternalFrameIconifyListener(dataVisibleInChart);
        internalframe.addInternalFrameListener(internalFrameListener);
        Jpowder.getChartPlotter2D().add(internalframe);
        setVisible(true);
//
//                for(int i=0;i<defaultTableModel.getRowCount();i++){
//                System.out.println(defaultTableModel.getValueAt(i, 0));
//                File f = new File(String.valueOf(defaultTableModel.getValueAt(i, 4)));
//                    System.out.println(f.getAbsoluteFile());
//        System.out.println(defaultTableModel.getDataVector().get(i));
//                }
// Jpowder3DSecondTest dSecondTest= new Jpowder3DSecondTest("");

        JInternalFrame frame = new JInternalFrame("XYZ");
        frame.add(Jpowder3DSecondTest.createDemoPanel());
        frame.setSize(internalframe.getSize());
        frame.setLocation(500, 300);
        frame.setResizable(true);
        frame.setClosable(true);
        frame.setMaximizable(true);
        frame.setIconifiable(true);
        frame.setVisible(true);

        Jpowder.getChartPlotter3D().add(frame);

        setVisible(true);
    }//GEN-LAST:event_potButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (defaultTableModel.getColumnCount() - 1 != 0) {
            defaultTableModel.removeRow(importData3DTable.getSelectedRow());
            System.out.println(defaultTableModel.getRowCount() - 1);
            importData3DTable.setRowSelectionInterval(defaultTableModel.getRowCount() - 1, defaultTableModel.getRowCount() - 1);
        } else {
            return;
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        while (defaultTableModel.getRowCount() > 0) {
            defaultTableModel.removeRow(0);
        }


    }//GEN-LAST:event_jButton2ActionPerformed

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

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        addColumn();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void plotAsComboBoxMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plotAsComboBoxMouseEntered
        plotAsComboBox.setModel(new DefaultComboBoxModel(columnNames));
    }//GEN-LAST:event_plotAsComboBoxMouseEntered

    private void addColumnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addColumnActionPerformed
        addColumn();
    }//GEN-LAST:event_addColumnActionPerformed

    private void removeColumnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeColumnActionPerformed
//        importData3DTable.removeColumn(importData3DTable.getColumnModel().getColumn(importData3DTable.getSelectedColumn()));
        System.out.println(importData3DTable.getSelectedColumn());
    }//GEN-LAST:event_removeColumnActionPerformed
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

            public void run() {
                new FilesTable().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addColumn;
    private javax.swing.JMenuItem addRow;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPopupMenu columnHeaderPopMenu;
    private javax.swing.JButton fileChooserButton;
    private javax.swing.JTable importData3DTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox plotAsComboBox;
    private javax.swing.JButton potButton;
    private javax.swing.JMenuItem removeColumn;
    private javax.swing.JMenuItem removeRow;
    private javax.swing.JPopupMenu rowHeaderPopMenu;
    // End of variables declaration//GEN-END:variables
}
