package org.jpowder;
/*
 * DataFileTableModel.java
 *
 * Created on 20 February 2007, 09:39
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

import javax.swing.DefaultListModel;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import java.util.*;

public class DataFileTableModel extends DefaultListModel implements TableModel {

    private Vector dataVec,  columnNames;
    private String fileName;//for displaying the file in the log.
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

        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            //this.fireTableDataChanged();
            return;
        }
    };

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


        LogPanel.addLogText("\nYou have " + this.getRowCount() + " rows in file: " +
                this.fileName + ".");
    }
    //My additional methods
    public java.util.Vector getData() {
        return this.dataVec;
    }

    public void setNewData(Vector dumData, String theFileName) {
        this.dataVec = dumData;
        this.fileName = theFileName;
        LogPanel.addLogText("\nYou have " + this.getRowCount() + " rows" + " in file " + fileName + ".");
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

    /*private void fireTableDataChanged() {
    throw new UnsupportedOperationException("Not yet implemented");
    }*/
    @Override
    protected void finalize() throws Throwable {
        this.dataVec = null;
        this.columnNames = null;
        this.tableModel = null;
    }
}
