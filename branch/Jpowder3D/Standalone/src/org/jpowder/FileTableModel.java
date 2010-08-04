/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder;

import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author qyt21516
 */
public class FileTableModel extends AbstractTableModel {

    protected Vector data;
    protected Vector columnNames;
    protected String datafile;

    public FileTableModel() {

        data = new Vector();
        columnNames = new Vector();

        addHeader();
        addRow();
    }

    public void addHeader() {
        columnNames.add("XXX");
        columnNames.add("XX");
        columnNames.add("X");
    }

    public void addRow() {
        data.add("1");
        data.add("2");
        data.add("3");


    }

    @Override
    public int getRowCount() {
        return data.size() / getColumnCount();
    }

    @Override
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

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return (String) data.elementAt((rowIndex * getColumnCount()) + columnIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        return;
    }

    public static void main(String[] args) {
        FileTableModel fileTableModel = new FileTableModel();
        JFrame frm = new JFrame();
        JTable table = new JTable(fileTableModel);
        frm.add(table);
        frm.setVisible(true);
        frm.setSize(100, 100);
        frm.setDefaultCloseOperation(3);

    }
}
