/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.TransferHandler;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Toshiba
 */
public abstract class KaiTableTransferHandler extends TransferHandler {

    //protected abstract DefaultTableModel exportTableModel(DefaultTableModel tableModel);

    protected abstract String exportString(JComponent c);

    protected abstract void importString(JComponent c, String str);

    protected abstract void cleanup(JComponent c, boolean remove);

    @Override
    protected Transferable createTransferable(JComponent c) {
        JTable jt = (JTable) c;
        System.out.println("createTransferable.................." + jt.toString());
        return new StringSelection(exportString(c));
    }

    @Override
    public int getSourceActions(JComponent c) {
        JTable jt = (JTable) c;
        System.out.println("getSourceActions.................." + jt.toString());
        return COPY_OR_MOVE;
    }

    @Override
    public boolean importData(JComponent comp, Transferable t) {
        JTable table = (JTable) comp;

        //25/02/2012 -- move to the last row, which is not correct.
        //should move to the index we pointed to.
        DefaultTableModel dt = (DefaultTableModel) table.getModel();
        dt.fireTableDataChanged();
        //25/02/2012

        System.out.println("importData.................." + table.getModel().toString());

        if (canImport(comp, t.getTransferDataFlavors())) {
            System.out.println("I can be imported.................." );
            try {
                String str = (String) t.getTransferData(DataFlavor.stringFlavor);
                importString(comp, str);
                return true;
            } catch (UnsupportedFlavorException ufe) {
            } catch (IOException ioe) {
            }
        }

        return false;
    }

    @Override
    protected void exportDone(JComponent c, Transferable data, int action) {
        cleanup(c, action == MOVE);
    }

    @Override
    public boolean canImport(JComponent c, DataFlavor[] flavors) {
        for (int i = 0; i < flavors.length; i++) {
            if (DataFlavor.stringFlavor.equals(flavors[i])) {
                return true;
            }
        }
        return false;
    }
}


