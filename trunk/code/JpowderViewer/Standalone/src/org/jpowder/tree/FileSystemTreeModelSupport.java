package org.jpowder.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

/**
 * This class takes care of the model required by TreeModel.
 *
 * ----------------------
 * FileSystemTreeModelSupport.java
 * ----------------------
 *
 * Changes
 * -------
 * 03-March-2010 : Version 1 (KP);
 *
 *
 * @author Toshiba
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
