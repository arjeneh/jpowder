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
 * FileSystemTreeModelSupport.java
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

/**
 * This class takes care of the model required by TreeModel.
 *
 */
public class FileSystemTreeModelSupport {

    //Container of subscribe listeners
    private Collection<TreeModelListener> listenerTree = new ArrayList<TreeModelListener>();

    /**
    * subscribe a listener of this tree
    *
    * @param listener The TreeModelListener implementation
    */
    public void addTreeModelListener(TreeModelListener listener) {
        if (listener != null && !listenerTree.contains(listener)) {
            listenerTree.add(listener);
        }
    }

    /**
    * Remove a listener of this tree
    *
    * @param listener The TreeModelListener implementation
    */
    public void removeTreeModelListener(TreeModelListener listener) {
        if (listener != null) {
            listenerTree.remove(listener);
        }
    }
    
    /**
    * Tell all subscribers of the tree of the new change
    *
    * @param e The TreeModelEvent implementation
    */
    public void fireTreeNodesChanged(TreeModelEvent e) {
        Enumeration listeners = (Enumeration) listenerTree;

        while (listeners.hasMoreElements()) {
            TreeModelListener listener = (TreeModelListener) listeners.nextElement();
            listener.treeNodesChanged(e);
        }
    }

    /**
    * Tell all subscribers of the insertion of the treee
    *
    * @param e The TreeModelEvent implementation
    */
    public void fireTreeNodesInserted(TreeModelEvent e) {
        Enumeration listeners = (Enumeration) listenerTree;
        while (listeners.hasMoreElements()) {
            TreeModelListener listener = (TreeModelListener) listeners.nextElement();
            listener.treeNodesInserted(e);
        }
    }
    /**
    * Tell all subscribers of the remove of the tree
    *
    * @param e The TreeModelEvent implementation
    */
    public void fireTreeNodesRemoved(TreeModelEvent e) {
        Enumeration listeners = (Enumeration) listenerTree;
        while (listeners.hasMoreElements()) {
            TreeModelListener listener = (TreeModelListener) listeners.nextElement();
            listener.treeNodesRemoved(e);
        }
    }
    
    /**
    * Tell all subscribers of the tree of the change in terms of structure
    *
    * @param e The TreeModelEvent implementation
    */
    public void fireTreeStructureChanged(TreeModelEvent e) {
        Enumeration listeners = (Enumeration) listenerTree;
        while (listeners.hasMoreElements()) {
            TreeModelListener listener = (TreeModelListener) listeners.nextElement();
            listener.treeStructureChanged(e);
        }
    }
}
