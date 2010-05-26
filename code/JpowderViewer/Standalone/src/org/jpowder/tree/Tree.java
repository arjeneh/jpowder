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
 * Tree.java
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

/**
 *
 * 
 * Original Author:  
 * 
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.Serializable;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 */
public class Tree extends JPanel implements Serializable {

    private JpowderFileSystemTreeModel model = new JpowderFileSystemTreeModel();
    private JpowderFileTreeRenderer renderer = new JpowderFileTreeRenderer();
    private java.awt.dnd.DropTarget dt;
    private  JTree tree;

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
//        this.tree.expandRow(0);
//        this.tree.expandRow(1);
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
    public  JTree getTree() {
        return tree;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                JFrame frame = new JFrame("File tree");
                frame.setSize(200, 400);
                frame.setLocationRelativeTo(null);
                final JpowderFileSystemTreeModel treeModel = new JpowderFileSystemTreeModel();
                frame.add(new Tree(treeModel));

                frame.addWindowListener(new WindowAdapter() {

                    @Override
                    public void windowClosing(WindowEvent event) {
                        try {

//                            FileOutputStream buffer = new FileOutputStream("C://Jtree.obj");
//                            final ObjectOutput out = new ObjectOutputStream(buffer);
//                            out.writeObject(new Tree(treeModel));
//                            out.flush();
//                            out.close();

//                            XMLEncoder e = new XMLEncoder(
//                                    new BufferedOutputStream(
//                                    new FileOutputStream("C://Test.xml")));
//
//                            e.writeObject(new Tree(treeModel));
//                            e.close();
//
                        } catch (Exception exception) {
                            System.err.println(exception);
                        }
                    }
                });

               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

            }
        });
    }

}

