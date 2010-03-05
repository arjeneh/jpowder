/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.tree;

/**
 *
 * 
 * Original Author:  Kirill Grouchnikov.
 * Contributor(s):   -;
 *
 * $Id: Tree.java,v 1.2 2010/03/03 13:58:03 Kreecha Exp $
 *
 * Changes
 * -------
 * 17-Dec-2009 : Version 1;
 * 03-March-2010 : Use TreeModel -> JpowderFileSystemTreeModel (KP);
 *                  add features for open and collapse JTree.
 *                  move FileSystemView to the JpowderFileSystemTreeModel.java
 */
import java.awt.BorderLayout;
import java.io.File;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 */
public class Tree extends JPanel {

    private JpowderFileSystemTreeModel model = new JpowderFileSystemTreeModel();
    private JpowderFileTreeRenderer renderer = new JpowderFileTreeRenderer();
    private java.awt.dnd.DropTarget dt;
    private JTree tree;

    /**
     * Creates the file tree panel.
     * Milad created.
     */
    public Tree() {

        this.setLayout(new BorderLayout());
        File[] roots = File.listRoots();
        FileTreeNode rootTreeNode = new FileTreeNode(roots);
        this.tree = new JTree(rootTreeNode);
        this.tree.setDragEnabled(true);
        this.tree.expandRow(1);
        this.tree.setRootVisible(true);

        final JScrollPane jsp = new JScrollPane(this.tree);
        jsp.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.add(jsp, BorderLayout.CENTER);
    }

    /**
     *
     * Use DataModel for easy manipulation later.
     * KP created.
     * @param model  the TreeModel.
     */
    public Tree(JpowderFileSystemTreeModel model) {
        this.model = model;

        this.setLayout(new BorderLayout());

        this.tree = new JTree(this.model);
        this.tree.setCellRenderer(this.renderer);
        this.tree.setRootVisible(false);
        this.tree.setShowsRootHandles(true);
        this.tree.setDragEnabled(true);

        //TODO: when user double-click, it plot the graph.
        //JpowderFileTreeMouseListener ml = new JpowderFileTreeMouseListener();
        //this.tree.addMouseListener(ml);


        final JScrollPane jsp = new JScrollPane(this.tree);
        jsp.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.add(jsp, BorderLayout.CENTER);
    }

    /**
     * Collapse the tree
     *
     * @param tree  the JTree
     */
    public void collapseAll(JTree tree) {
        int row = tree.getRowCount() - 1;
        while (row >= 0) {
            tree.collapseRow(row);
            row--;
        }
    }

    /**
     * View the last node of the tree
     *
     * @param tree  the JTree
     */
    public void expandToLast(JTree tree) {
        TreeModel data = tree.getModel();
        Object node = data.getRoot();

        if (node == null) {
            return;
        }

        TreePath p = new TreePath(node);
        while (true) {
            int count = data.getChildCount(node);
            if (count == 0) {
                break;
            }
            node = data.getChild(node, count - 1);
            p = p.pathByAddingChild(node);
        }
        tree.scrollPathToVisible(p);
    }

    /**
     * View all nodes of the tree
     *
     * @param tree  the JTree
     */
    public void expandAll(JTree tree) {
        int row = 0;
        while (row < tree.getRowCount()) {
            tree.expandRow(row);
            row++;
        }
    }

    /**
     * Returns the JTree.
     *
     * @param
     *
     * @return The value of the JTree.
     */
    public JTree getTree() {
        return tree;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                JFrame frame = new JFrame("File tree");
                frame.setSize(200, 400);
                frame.setLocationRelativeTo(null);

                JpowderFileSystemTreeModel treeModel = new JpowderFileSystemTreeModel();
                frame.add(new Tree(treeModel));


                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

