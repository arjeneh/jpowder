/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GSASTableTest.java
 *
 * Created on 15-Jul-2010, 13:31:01
 */
package org.jpowder.Analysis;

import java.io.File;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jpowder.Jpowder;
import org.jpowder.InernalFrame.JpowderInternalframe2D;
import org.jpowder.dataset.GSASInstrument_Reader;

/**
 *
 * @author qyt21516
 */
public class GSASTable extends javax.swing.JPanel {

    BragsTableModel bragsTableModel = new BragsTableModel();
    private static DefaultTableModel defaultTableModel;
    Vector<String> columnNames = new Vector<String>();
    Vector<String> row = new Vector<String>();

    /** Creates new form GSASTableTest */
    public GSASTable() {

        columnNames.add("Plot(s)");
        columnNames.add("DifC");
        columnNames.add("DifA");
        columnNames.add("Zero");
//        rowData.addElement(getFileName());
        defaultTableModel = new DefaultTableModel(row, columnNames);

        initComponents();


    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        gSASTabel = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        gSASTabel.setModel(defaultTableModel);
        TableColumn column = gSASTabel.getColumnModel().getColumn(0);
        column.setPreferredWidth(160);
        jScrollPane1.setViewportView(gSASTabel);

        jButton1.setText("Impot data");
        jButton1.setToolTipText("import data from instrument file");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser chooser = new JFileChooser();
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus;
        chooser.setMultiSelectionEnabled(false);
        int returnVal = chooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            GSASInstrument_Reader.read(inFocus.getPowderDataSet(), file);
            removeAllrows();
            populateTable();
        }
        else{
            return;
        }
     

   

    }//GEN-LAST:event_jButton1ActionPerformed
    public void populateTable() {
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus;
        for (int i = 0; i < inFocus.getXYPlot().getDatasetCount(); i++) {
            row = new Vector<String>();
            row.add(inFocus.getPowderDataSet().get(i).getFileName());
            row.add(String.valueOf(inFocus.getPowderDataSet().get(i).getGSAS_Instrument().getDifC()));
            row.add(String.valueOf(inFocus.getPowderDataSet().get(i).getGSAS_Instrument().getDifA()));
            row.add(String.valueOf(inFocus.getPowderDataSet().get(i).getGSAS_Instrument().getZero()));
            defaultTableModel.addRow(row);
        }
        addRenderer();
    }

    public void populateTable2() {
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus;
        if (inFocus != null) {
            for (int i = 0; i < inFocus.getXYPlot().getDatasetCount(); i++) {
                row = new Vector<String>();
                row.add(inFocus.getPowderDataSet().get(i).getFileName());
                row.add("");
                row.add("");
                row.add("");
                defaultTableModel.addRow(row);
            }
        }
        addRenderer();
    }

    public void removeAllrows() {
        defaultTableModel.getDataVector().removeAllElements();
    }
    public void addRenderer(){
             gSASTabel.getColumn(gSASTabel.getColumnName(0)).setCellRenderer(new TableRenderer());
    }

    public static JTable getGSASTable() {
        return gSASTabel;
    }

    public DefaultTableModel getGSASDefaultTableModel() {
        return defaultTableModel;

    }
    public void addTableListener(){
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable gSASTabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {
        JFrame frm = new JFrame();
        frm.add(new GSASTable());
        frm.setVisible(true);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setSize(300, 200);
    }
}
