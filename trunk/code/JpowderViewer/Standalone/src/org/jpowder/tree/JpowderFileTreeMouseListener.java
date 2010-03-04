/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.tree;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.TreePath;

/**
 * Handle when a node of a tree is double click, it will copy the file
 * and move to File org.jpowder.fileCabinet.
 * ----------------------
 *  JpowderFileTreeMouseListener.java
 * ----------------------
 *
 * Changes
 * -------
 * 03-March-2010 : Version 1 (KP);
 *
 *
 * @author Kreecha Puphaiboon (KP)
 */
public class JpowderFileTreeMouseListener extends MouseAdapter {

    class CopyHandler extends TransferHandler {

        @Override
        public void exportToClipboard(JComponent comp, Clipboard clip, int action) {
            JTree tree = (JTree) comp;
            TreePath path = tree.getSelectionPath();
            if (path != null) {
                clip.setContents(new StringSelection(path.toString()), null);
            }
        }
    }

    public JpowderFileTreeMouseListener() {
    }

    @Override
    public void mousePressed(MouseEvent e) {

        JTree tree = (JTree) e.getComponent();
        JpowderFileSystemTreeModel tm = (JpowderFileSystemTreeModel) tree.getModel();

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        tree.getTransferHandler().exportToClipboard(tree,
                clipboard, TransferHandler.COPY);


        int selRow = tree.getRowForLocation(e.getX(), e.getY());

        TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());

        if (selRow != -1) {
            if (e.getClickCount() == 1) {
                //mySingleClick(selRow, selPath);
            } else if (e.getClickCount() == 2) {
                System.out.println("I see double click.");
            }

        }
    }
}
