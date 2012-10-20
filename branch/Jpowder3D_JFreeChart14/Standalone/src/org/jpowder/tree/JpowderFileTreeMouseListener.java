/* ===========================================================
 * This file is part of Jpowder, see <http://www.jpowder.org/>
 * ===========================================================
 *
 * Jpowder is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jpowder is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * ---------
 * JpowderFileTreeMouseListener.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  Kreecha Puphaiboon, Computer Science Lecturer, Kasem Bundit University
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
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

            }

        }
    }
}
