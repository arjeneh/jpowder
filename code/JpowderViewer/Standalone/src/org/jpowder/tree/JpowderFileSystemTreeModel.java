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
 * Axis.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Original Author:Kreecha Puphaiboon
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.tree;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.TreePath;

/**
 * This class takes care of listing file system in a user computer of all platforms.
 * It extends PowderFileSystemModel.java which handles listener and TreeModel implementation.
 *
 * To get a default directory, we could use:
 * JFileChooser chooser = new JFileChooser();
 * FileSystemView view = chooser.getFileSystemView();
 * System.out.println(view.getDefaultDirectory());
 * 
 */
public class JpowderFileSystemTreeModel extends PowderFileSystemModel {

    //default sort is by name, if want to sort by size use SORTBY.FILE_SIZE
    private Comparator<File> sorter = new FileComparator(SORTBY.FILE_NAME);
    private FileSystemTreeModelSupport treeListeners;
    private Object falseRoot = new Object();
    private File[] roots;
    private FileSystemView fsv;
    private boolean hiddenVisible;
    private HashMap<File, List<File>> sortedChildren;
    private HashMap<File, Long> lastModifiedTimes;

    /**
     * Create a tree model using the specified file system view and
     * the specified roots. This results in displaying a subset of
     * the actual filesystem.
     *
     * @param fsv The FileSystemView implementation
     * @param roots Root files
     */
    public JpowderFileSystemTreeModel(FileSystemView fsv, File[] roots) {
        this.fsv = fsv;
        this.roots = roots;
        treeListeners = new FileSystemTreeModelSupport();
        sortedChildren = new HashMap<File, List<File>>();
        lastModifiedTimes = new HashMap<File, Long>();
    }

    /**
     * Create a tree model using the specified file system view.
     *
     * @param fsv The FileSystemView implementation
     */
    public JpowderFileSystemTreeModel(FileSystemView fsv) {
        this(fsv, fsv.getRoots());
    }

    /**
     * Create a tree model using the default file system view for this
     * platform.
     */
    public JpowderFileSystemTreeModel() {
        this(FileSystemView.getFileSystemView());
    }

    public Object getRoot() {
        return falseRoot;
    }

    public Object getChild(Object parent, int index) {
        if (parent == falseRoot) {
            return roots[index];
        } else {
            List children = (List) sortedChildren.get(parent);
            return children == null ? null : children.get(index);
        }
    }

    public int getChildCount(Object parent) {
        if (parent == falseRoot) {
            return roots.length;
        } else {
            File file = (File) parent;
            if (!fsv.isTraversable(file)) {
                return 0;
            }

            File[] children = fsv.getFiles(file, !hiddenVisible);
            int nChildren = children == null ? 0 : children.length;

            long lastModified = file.lastModified();
            boolean isFirstTime = lastModifiedTimes.get(file) == null;
            boolean isChanged = false;

            if (!isFirstTime) {
                Long modified = (Long) lastModifiedTimes.get(file);
                long diff = Math.abs(modified.longValue() - lastModified);

                isChanged = diff > 4000;
            }

            // Sort and register children info
            if (isFirstTime || isChanged) {
                lastModifiedTimes.put(file, new Long(lastModified));

                TreeSet<File> sorted = new TreeSet<File>(sorter);
                for (int i = 0; i < nChildren; i++) {
                    sorted.add(children[i]);
                }

                sortedChildren.put(file, new ArrayList<File>(sorted));
            }

            // Notify treeListeners, if changes
            if (isChanged) {
                TreeModelEvent event = new TreeModelEvent(
                        this, getTreePath(file));
                fireTreeStructureChanged(event);
            }

            return nChildren;
        }
    }

    private Object[] getTreePath(Object obj) {
        List<Object> path = new ArrayList<Object>();
        while (obj != falseRoot) {
            path.add(obj);
            obj = fsv.getParentDirectory((File) obj);
        }

        path.add(falseRoot);

        int nElements = path.size();
        Object[] treePath = new Object[nElements];

        for (int i = 0; i < nElements; i++) {
            treePath[i] = path.get(nElements - i - 1);
        }

        return treePath;
    }

    public boolean isLeaf(Object node) {
        if (node == falseRoot) {
            return false;
        } else {
            return !fsv.isTraversable((File) node);
        }
    }

    public void valueForPathChanged(TreePath path, Object newValue) {
    }

    public int getIndexOfChild(Object parent, Object child) {
        List children = (List) sortedChildren.get(parent);
        return children.indexOf(child);
    }

    @Override
    public void addTreeModelListener(TreeModelListener listener) {   
            treeListeners.addTreeModelListener(listener);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener listener) {       
            treeListeners.removeTreeModelListener(listener);
    }

    @Override
    public void fireTreeNodesChanged(TreeModelEvent e) {
        treeListeners.fireTreeNodesChanged(e);
    }

    @Override
    public void fireTreeNodesInserted(TreeModelEvent event) {
        treeListeners.fireTreeNodesInserted(event);
    }

    @Override
    public void fireTreeNodesRemoved(TreeModelEvent event) {
        treeListeners.fireTreeNodesRemoved(event);
    }

    @Override
    public void fireTreeStructureChanged(TreeModelEvent event) {
        treeListeners.fireTreeStructureChanged(event);
    }
}

