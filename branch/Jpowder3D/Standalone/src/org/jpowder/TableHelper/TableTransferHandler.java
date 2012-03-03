/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.TableHelper;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
        System.out.println("In exportString, selected rows are: " + rows.length);
        int colCount = table.getColumnCount();

        StringBuffer buff = new StringBuffer();

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < colCount; j++) {
                Object val = table.getValueAt(rows[i], j);
                System.out.println("I am type" + val.getClass().getName());

                buff.append(val == null ? "" : val.toString());
                if (j != colCount - 1) {
                    buff.append(",");
                }
            }
            if (i != rows.length - 1) {
                buff.append("\n");
            }
        }

        return buff.toString();
    }

    @Override
    protected void importString(JComponent c, String str) {
        JTable target = (JTable) c;
        DefaultTableModel model = (DefaultTableModel) target.getModel();
        //TODO : Note that "+ 1" is for Vector type, if array then remove "+ 1"
        int index = target.getSelectedRow() + 1;
        System.out.println("Selected rows are:.." + index);

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
        System.out.println("How many row count....." + max + "..................");

        if (index < 0) {
            index = max;
        } else {
            index++;
            if (index > max) {
                index = max;
            }
        }

        addIndex = index;
        System.out.println("Add index is:..." + addIndex + "..................");

        String[] values = str.split("\n");
        addCount = values.length;
        System.out.println("Add count is:..." + addCount + "..................");

        int colCount = target.getColumnCount();
        for (int i = 0; i < values.length && i < colCount; i++) {
            model.insertRow(index++, values[i].split(","));
            System.out.println("Jeep............" + index + "..................");
        }

        System.out.println("Hello..importString() is called in ExtendedDnDDemo..................");
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
                System.out.print("Hello........................................");
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
    }
}
