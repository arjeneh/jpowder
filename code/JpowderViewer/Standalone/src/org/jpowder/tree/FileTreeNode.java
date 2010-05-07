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
 * FileTreeNode.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  Kirill Grouchnikov
 *             Kreecha Puphaiboon, Computer Science Lecturer, Kasem Bundit University
 *             M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.tree;

import java.io.File;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import javax.swing.tree.TreeNode;

/**
 * A node in the file tree.
 *
 *
 */
public class FileTreeNode implements TreeNode {

    /**
     * Node file.
     */
    public File file;
    /**
     * Children of the node file.
     */
    private File[] children;
    /**
     * Parent node.
     */
    private TreeNode parent;
    /**
     * Indication whether this node corresponds to a file system root.
     */
    public boolean isFileSystemRoot;

    /**
     * Creates a new file tree node.
     *
     * @param file
     *            Node file
     * @param isFileSystemRoot
     *            Indicates whether the file is a file system root.
     * @param parent
     *            Parent node.
     */
    public FileTreeNode(File file, boolean isFileSystemRoot, TreeNode parent) {
        this.file = file;
        this.isFileSystemRoot = isFileSystemRoot;
        this.parent = parent;
        this.children = this.file.listFiles();
        if (this.children == null) {
            this.children = new File[0];
        }
    }

    @Override
    public String toString() {
        if (file == null) {
            return "null";
        } else {
            return file.getAbsolutePath();
        }

    }

    /**
     * Creates a new file tree node.
     *
     * @param children
     *            Children files.
     */
    public FileTreeNode(File[] children) {
        this.file = null;
        this.parent = null;
        this.children = children;
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.swing.tree.TreeNode#children()
     */
    public Enumeration<?> children() {
        final int elementCount = this.children.length;
        return new Enumeration<File>() {

            int count = 0;

            /*
             * (non-Javadoc)
             *
             * @see java.util.Enumeration#hasMoreElements()
             */
            public boolean hasMoreElements() {
                return this.count < elementCount;
            }

            /*
             * (non-Javadoc)
             *
             * @see java.util.Enumeration#nextElement()
             */
            public File nextElement() {
                if (this.count < elementCount) {
                    return FileTreeNode.this.children[this.count++];
                }
                throw new NoSuchElementException("Vector Enumeration");
            }
        };

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.swing.tree.TreeNode#getAllowsChildren()
     */
    public boolean getAllowsChildren() {
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.swing.tree.TreeNode#getChildAt(int)
     */
    public TreeNode getChildAt(int childIndex) {
        return new FileTreeNode(this.children[childIndex],
                this.parent == null, this);
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.swing.tree.TreeNode#getChildCount()
     */
    public int getChildCount() {
        return this.children.length;
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.swing.tree.TreeNode#getIndex(javax.swing.tree.TreeNode)
     */
    public int getIndex(TreeNode node) {
        FileTreeNode ftn = (FileTreeNode) node;
        for (int i = 0; i < this.children.length; i++) {
            if (ftn.file.equals(this.children[i])) {
                return i;
            }
        }
        return -1;
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.swing.tree.TreeNode#getParent()
     */
    public TreeNode getParent() {
        return this.parent;
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.swing.tree.TreeNode#isLeaf()
     */
    public boolean isLeaf() {
        return (this.getChildCount() == 0);
    }
}

