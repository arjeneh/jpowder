/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.Analysis;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author qyt21516
 */
public class BragsTableModel extends AbstractTableModel {

   private String[] columnNames = {"File Name",
        "DifC",
        "DifA",
        "Zero",};
   private Object[][] data = {
	    {new String(), new Double(5.5),
	     new Double(5.5), new Double(5.5)}
	 

        };
//    private Vector<String> column = new Vector<String>();
//    private Vector<String> rdat = new Vector<String>();

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }


    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    /*
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     */
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 2) {
            return false;
        } else {
            return true;
        }
    }
    public void setColumnName(Object value,int col){
        columnNames[col]=(String) value;
         fireTableCellUpdated(col, col);

    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col) {


        data[row][col] = value;
        fireTableCellUpdated(row, col);


    }

    private void printDebugData() {
        int numRows = getRowCount();
        int numCols = getColumnCount();

        for (int i = 0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j = 0; j < numCols; j++) {
                System.out.print("  " + data[i][j]);
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
}
