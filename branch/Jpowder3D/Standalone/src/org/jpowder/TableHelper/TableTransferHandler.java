/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.TableHelper;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.stream.events.StartDocument;

/**
 *
 * @author Toshiba
 */
public class TableTransferHandler extends StringTransferHandler {

    private int[] rows = null;
    private int addIndex = -1; //Location where items were added
    private int addCount = 0; //Number of items added.

    @Override
    protected String exportString(JComponent c) {
        JTable table = (JTable) c;
        rows = table.getSelectedRows();
        System.out.println("In exportString(), selected rows are: " + rows.length);
        int colCount = table.getColumnCount();

        StringBuffer buff = new StringBuffer();

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < colCount; j++) {
                Object val = table.getValueAt(rows[i], j);
                System.out.println("In exportString(), I am type " + val.getClass().getName());

                buff.append(val == null ? "" : val.toString());
                System.out.println("In exportString(), my value is: " + val.toString());
                if (j != colCount - 1) {
                    buff.append(",");
                }
            }
            if (i != rows.length - 1) {
                buff.append("\n");
            }
        }

        System.out.println("In exportString(), done value is: " + buff.toString());
        System.out.println("Finished In exportString() ..............................................................................................................................");

        return buff.toString();
    }

    @Override
    protected void importString(JComponent c, String str) {
        JTable target = (JTable) c;
        DefaultTableModel model = (DefaultTableModel) target.getModel();

        int index = target.getSelectedRow();//model index.
        System.out.println("In importString(), selected rows are:.." + index);

        System.out.println("selectedRowCount=" + target.getSelectedRowCount());

        int index2 = target.convertRowIndexToModel(index);
        System.out.println("In importString(), target.convertRowIndexToModel are:.." + index2);
//        if (index != -1) {
//            index = target.convertRowIndexToModel(index);
//            //String value = (String) model.getValueAt(rowIndex, columnIndex);
//        }

        

        //Prevent the user from dropping data back on itself.
        //For example, if the user is moving rows #4,#5,#6 and #7 and
        //attempts to insert the rows after row #5, this would
        //be problematic when removing the original rows.
        //So this is not allowed.
        if (rows != null && index >= rows[0] - 1 && index <= rows[rows.length - 1]) {
            rows = null;
            return;
        }

        int max = model.getRowCount();
        System.out.println("In importString(), row count is: ...." + max);

        if (index < 0) {
            index = max;
        } else {
            index++;
            if (index > max) {
                index = max;
            }
        }

        addIndex = index;
        System.out.println("In importString(), Add index is:..." + addIndex);

        String[] values = str.split("\n");
        System.out.println("In importString(), String[] values are:..." + values);
        addCount = values.length;
        System.out.println("In importString(), Count column is:..." + addCount);

        int colCount = target.getColumnCount();
        //System.out.println("In importString(), Column count is:..." + colCount );

        for (int i = 0; i < values.length && i < colCount; i++) {
            model.insertRow(index++, values[i].split(","));
            // System.gc();
            // MoveRow attempt.
            //model.moveRow(start, end, to);
            System.out.println("In importString(), Insert to row index......" + index + "..................");
        }

        System.out.println("Finished in importString() ..........................");
    }

    @Override
    protected void cleanup(JComponent c, boolean remove) {
        JTable source = (JTable) c;
        if (remove && rows != null) {
            DefaultTableModel model = (DefaultTableModel) source.getModel();

            //If we are moving items around in the same table, we
            //need to adjust the rows accordingly, since those
            //after the insertion point have moved.
            if (addCount > 0) {
                // System.out.print("Hello........................................");
                for (int i = 0; i < rows.length; i++) {
                    if (rows[i] > addIndex) {
                        rows[i] += addCount;
                    }
                }
            }

            for (int i = rows.length - 1; i >= 0; i--) {
                model.removeRow(rows[i]);
            }
        }
        rows = null;
        addCount = 0;
        addIndex = -1;

        System.out.print("Finished cleanup() ........................................");
    }
}
