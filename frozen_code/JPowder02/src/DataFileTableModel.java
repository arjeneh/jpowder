/*
 * DataFileTableModel.java
 *
 * Created on 20 February 2007, 09:39
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

import java.awt.event.*;
import javax.swing.DefaultListModel;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import java.io.*;
import java.util.*;
import java.net.*;

public class DataFileTableModel extends DefaultListModel implements TableModel {
    private Vector dataVec;
    private Vector columnNames;    
    private TableModel tableModel = new AbstractTableModel(){
        public int getRowCount() {         
            return dataVec.size();
        }
       
        public int getColumnCount(){
            return columnNames.size();
        }
        
        public String getColumnName(int columnIndex) {
            String colName = "";
            if (columnIndex <= getColumnCount())
                colName = (String)columnNames.elementAt(columnIndex);
            return colName;
        }
        
        public Class getColumnClass(int columnIndex){
            return String.class;
        }
        
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
        
        public Object getValueAt(int rowIndex, int columnIndex) {
            Vector row = (Vector)dataVec.elementAt(rowIndex);
            return row.elementAt(columnIndex);
        }
        
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            return;
        }
    };
    
    public DataFileTableModel(Vector dumData){
        super();
        dataVec = dumData;
        
        columnNames = new Vector();
        columnNames.addElement("X");
        columnNames.addElement("Y");
        //columnNames.addElement("STD");//disable the last STD bit
    }
    
    //My additional methods
    public java.util.Vector getData(){
        return dataVec;
    }
    public void setNewData(Vector dumData){
        dataVec = dumData;
    }
    //end my additional methods
    
    //Implement the TableModel interface.
    public int getRowCount() {
        return tableModel.getRowCount();
    }
    public int getColumnCount() {
        return tableModel.getColumnCount();
    }
    public String getColumnName(int columnIndex) {
        return tableModel.getColumnName(columnIndex);
    }
    public Class getColumnClass(int columnIndex) {
        return tableModel.getColumnClass(columnIndex);
    }
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return tableModel.isCellEditable(rowIndex, columnIndex);
    }
    public Object getValueAt(int rowIndex, int columnIndex) {
        return tableModel.getValueAt(rowIndex, columnIndex);
    }
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        tableModel.setValueAt(aValue, rowIndex, columnIndex);
    }
    public void addTableModelListener(TableModelListener l) {
        tableModel.addTableModelListener(l);
    }
    public void removeTableModelListener(TableModelListener l) {
        tableModel.removeTableModelListener(l);
    }

    /*private void fireTableDataChanged() {
        throw new UnsupportedOperationException("Not yet implemented");
    }*/    
}
