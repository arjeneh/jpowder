/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jpowder;

import java.io.File;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author qyt21516
 */
public class FileTableModel  extends AbstractTableModel{

    protected Vector data;
    protected Vector columnNames ;
    protected String datafile;
   JFileChooser chooser = new JFileChooser(new File("C:/Documents and Settings/qyt21516/Desktop/My Dropbox/jpowder/trunk/data/xy-format data"));


    public FileTableModel(String f) {

        datafile=f;
        initVectors();
    }

        public void initVectors() {

        data = new Vector();
        columnNames = new Vector();


            // extract column names

                columnNames.addElement("Name");
            // extract data

               for(int i=0;i<chooser.getSelectedFiles().length;i++){
                   data.add(i, chooser.getSelectedFiles()[i].getName());
               }

        }








   public int getRowCount() {
        return data.size() / getColumnCount();
    }

    public int getColumnCount(){
        return columnNames.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        String colName = "";

        if (columnIndex <= getColumnCount())
            colName = (String)columnNames.elementAt(columnIndex);

        return colName;
    }

    @Override
    public Class getColumnClass(int columnIndex){
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return (String)data.elementAt( (rowIndex * getColumnCount()) + columnIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        return;
    }

}
