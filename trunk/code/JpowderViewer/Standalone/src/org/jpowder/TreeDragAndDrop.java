/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jpowder;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import javax.activation.DataHandler;
import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import org.jfree.chart.ChartTransferable;
import org.jfree.chart.JFreeChart;
import sun.awt.datatransfer.ClipboardTransferable;
import sun.awt.dnd.SunDropTargetContextPeer;

/**
 *
 * @author qyt21516
 */
public class TreeDragAndDrop extends TransferHandler {

    /**
     *   @Override
    public boolean importData(JComponent comp, Transferable t) {
      boolean retValue;
      try {
        JTree tree = (JTree) comp;
        Object transfered = t.getTransferData(DataFlavor.stringFlavor);
        MutableTreeNode root = (MutableTreeNode) tree.getModel().getRoot();
        root.insert(new DefaultMutableTreeNode(transfered),root.getChildCount());
        ((DefaultTreeModel)tree.getModel()).reload();
        return true;
      } catch (UnsupportedFlavorException ufe) {
        return false;
      } catch (java.io.IOException ufe) {
        return false;
      }

    }

     * @param comp
     * @param t
     * @return
     */
  
    @Override
    public  void exportDone(JComponent source, Transferable data, int action) {
      if (action == java.awt.dnd.DnDConstants.ACTION_MOVE) {
        JTree tree = (JTree) source;
        TreePath selected = tree.getSelectionPath();
        MutableTreeNode node = (MutableTreeNode) selected.getLastPathComponent();
        node.removeFromParent();
        ((DefaultTreeModel)tree.getModel()).reload();
      }
      super.exportDone(source, data, action);
    }
/**
 *  @Override
    public boolean canImport(JComponent comp, DataFlavor[] transferFlavors) {
      for (int i = 0, ii = transferFlavors.length; i < ii; i++) {
        if (transferFlavors[i] == DataFlavor.stringFlavor)
          return true;
      }
      return false;
    }
 * @param comp
 * @param transferFlavors
 * @return
 */
   

    @Override
    public int getSourceActions(JComponent c) {
      return java.awt.dnd.DnDConstants.ACTION_MOVE;
    }

    @Override
    public  Transferable createTransferable(JComponent c) {

          JTree t = (JTree) c;

    
      if (t.getSelectionPath() == null)
        return null;
      System.out.println("transferbaledataaaaaa"+ t.getSelectionPath().getLastPathComponent().toString());
      
     return new StringSelection( t.getSelectionPath().getLastPathComponent().toString() );

    }

  }


