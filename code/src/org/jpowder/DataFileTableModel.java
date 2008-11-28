/*
 * DataFileTableModel.java
 *
 */

package org.jpowder;

import org.jpowder.fileCabinet.Subject;
import org.jpowder.fileCabinet.PowderFileCabinet;
import org.jpowder.fileCabinet.PowderFileObserver;
import javax.swing.DefaultListModel;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import java.util.*;

/**

 * Class name: FileNameListModel.java
 * Date: 28/11/08
 * Description:
 * DataFileTableModel stores powder data in a model of multiple columns
 * with the purpose of displaying them in a table. So user can see details of data.
 * But this is not yet used!
 *
 * Interface:
 * @see javax.swing.table.TableModel
 * @see javax.swing.DefaultListModel
 * @see PowderFileObserver
 *
 * @author Kreecha Puphaiboon
 */

public class DataFileTableModel extends DefaultListModel implements TableModel, PowderFileObserver {

    private Vector dataVec;    // to hold the data in the table
    private Vector columnNames; // to hold column labels, like 'X', 'Y'
    
    //for displaying the file in the log.
    private String fileName;

    /**
     * Kai: how come you use @override for some of the methods of TableModel
     * below but not all?
     */    
    private TableModel tableModel = new AbstractTableModel() {

        public int getRowCount() {
            return dataVec.size();
        }

        public int getColumnCount() {
            return columnNames.size();
        }

        @Override
        public String getColumnName(int columnIndex) {
            String colName = "";
            if (columnIndex <= getColumnCount()) {
                colName = (String) columnNames.elementAt(columnIndex);
            }
            return colName;
        }

        @Override
        public Class getColumnClass(int columnIndex) {
            return String.class;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            //BUG: change file which contain 3 columns to 2 columns, 
            //if use 2 lines below.
            //Vector row = (Vector) dataVec.elementAt(rowIndex);
            //return row.elementAt(columnIndex);
            return null;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            return;
        }
    };

    /**
     * Kai: why is the first argument called dumData?
     */     
    public DataFileTableModel(Vector dumData, String theFileName) {
        super();
        this.dataVec = dumData;
        this.fileName = theFileName;
        

        Vector vrow = (Vector) (this.dataVec.elementAt(0));
        int col = vrow.size();
        System.out.println("\nYou have " + col + " columns.");

        // 2 columns then X, Y else xye
        if (col == 2) {
            this.columnNames = new Vector();
            this.columnNames.addElement("X");
            this.columnNames.addElement("Y");
        } else {
            this.columnNames = new Vector();
            this.columnNames.addElement("X");
            this.columnNames.addElement("Y");
            this.columnNames.addElement("E");//disable the last STD bit
        }

    }
    
    //My additional methods - Kai what do you mean by this comment?
    public java.util.Vector getData() {
        return this.dataVec;
    }

    public void setNewData(Vector dumData, String theFileName) {
        this.dataVec = dumData;
        this.fileName = theFileName;
        System.out.println("\nYou have " + this.getRowCount() + " rows" + " in file " + fileName + ".");
    }

    public void setFileName(String theFileName) {
        this.fileName = theFileName;
    }

    //Implement the TableModel interface.
    public int getRowCount() {
        return this.tableModel.getRowCount();
    }

    public int getColumnCount() {
        return this.tableModel.getColumnCount();
    }

    public String getColumnName(int columnIndex) {
        return this.tableModel.getColumnName(columnIndex);
    }

    public Class getColumnClass(int columnIndex) {
        return this.tableModel.getColumnClass(columnIndex);
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return this.tableModel.isCellEditable(rowIndex, columnIndex);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.tableModel.getValueAt(rowIndex, columnIndex);
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        this.tableModel.setValueAt(aValue, rowIndex, columnIndex);
    }

    public void addTableModelListener(TableModelListener l) {
        this.tableModel.addTableModelListener(l);
    }

    public void removeTableModelListener(TableModelListener l) {
        this.tableModel.removeTableModelListener(l);
    }

    private void fireTableDataChanged() {
    throw new UnsupportedOperationException("Not yet implemented");
    }
    
    @Override
    protected void finalize() throws Throwable {
        this.dataVec = null;
        this.columnNames = null;
        this.tableModel = null;
    }

    public void powderFileCabinetUpdate(Subject data) {
        
        PowderFileCabinet pfc = (PowderFileCabinet) data;
        System.out.println("From FileNameListModel.java PowderFileCabinet is updated as " + pfc.getData().size());

        java.util.HashMap hm = pfc.getData();
        //clear
        clear();

        //To get all keys stored in HashMap use keySet method. 
        //Signature of the keysSet method is, Set keySet()
        java.util.Iterator iterator = hm.keySet().iterator();
        while (iterator.hasNext()) {
            // TODO:
            //add((String) iterator.next());
        }
    }
}
