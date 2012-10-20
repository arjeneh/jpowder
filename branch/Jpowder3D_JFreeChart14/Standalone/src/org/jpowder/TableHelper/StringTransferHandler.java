/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.TableHelper;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.TransferHandler;

/**
 *
 * @author Toshiba
 */
public abstract class StringTransferHandler extends TransferHandler {

    protected abstract String exportString(JComponent c);

    protected abstract void importString(JComponent c, String str);

    protected abstract void cleanup(JComponent c, boolean remove);

    @Override
    protected Transferable createTransferable(JComponent c) {
        System.out.println("createTransferable..................");
        return new StringSelection(exportString(c));
    }

    @Override
    public int getSourceActions(JComponent c) {
        System.out.println("getSourceActions....................");
        return COPY_OR_MOVE;
    }

//    @Override
//    public boolean importData(TransferSupport support) {
//        if (!support.isDrop()) {
//            return false;
//        }
//
//        if (!canImport(support)) {
//            return false;
//        }
//
//        JTable table = (JTable) support.getComponent();
//        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
//
//        JTable.DropLocation dl = (JTable.DropLocation) support.getDropLocation();
//        int row = dl.getRow();
//        int col = dl.getColumn();
//
//        String data;
//        try {
//            data = (String) support.getTransferable().getTransferData(DataFlavor.stringFlavor);
//        } catch (UnsupportedFlavorException e) {
//            return false;
//        } catch (IOException e) {
//            return false;
//        }
//
//        tableModel.setValueAt(data, row, col);
//        return true;
//    }

    @Override
    public boolean importData(JComponent c, Transferable t) {
        JTable target = (JTable) c;
  
        //JTable.DropLocation dl = (JTable.DropLocation) t..getDropLocation();

        if
         (canImport(c, t.getTransferDataFlavors())) {
            try {
                String str = (String) t.getTransferData(DataFlavor.stringFlavor);
                System.out.println("str imported is ............." + str);
                importString(c, str);
                return true;
            } catch (UnsupportedFlavorException ufe) {
            } catch (IOException ioe) {
            }
        }

        return false;
    }

    @Override
    protected void exportDone(JComponent c, Transferable data, int action) {
        System.out.println("exportDone");
        cleanup(c, action == MOVE);
    }

    @Override
    public boolean canImport(JComponent c, DataFlavor[] flavors) {
        //System.out.println("canImport");
        for (int i = 0; i < flavors.length; i++) {
            if (DataFlavor.stringFlavor.equals(flavors[i])) {
                return true;
            }
        }
        return false;
    }
}
