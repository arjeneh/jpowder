/*
 * DataFileTableModel.java
 *
 * Created on 20 February 2007, 09:39
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

import java.awt.event.*;
import javax.swing.table.*;
import java.io.*;
import java.util.*;
import java.net.*;

public class DataFileTableModel extends AbstractTableModel {    
    private Vector dataVec;
    private Vector columnNames;
    
    public DataFileTableModel(Vector dumData){
        dataVec = dumData;
        
        columnNames = new Vector();
        columnNames.addElement("X");
        columnNames.addElement("Y");
        //columnNames.addElement("STD");//disable the last STD bit
    }
    
    public int getRowCount() {
        return dataVec.size();//problem here.
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

    public java.util.Vector getData(){
        return dataVec;
    }
    
}
