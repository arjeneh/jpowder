/*
 * 
 */

package org.jpowder;

/**
 *
 *
 */
import javax.swing.tree.*;
import javax.swing.event.*;
import java.io.*;

class TreeFiles implements TreeModel {

    public File root;

    public TreeFiles(File rt) {
        root = rt;
    }

    public Object getRoot() {

        return root;
    }

    public boolean isLeaf(Object node) {

        File leaf = (File) node;
        return leaf.isFile();
    }

    public int getChildCount(Object parent) {
        File p = (File) parent;
        if (p.isDirectory()) {
            //System.out.println("Total Files=" + p.list().length);
            return p.list().length;
        } else {
            return 0;
        }

    }


    public Object getChild(Object parent, int index) {

        File p = (File) parent;
        String[] children = p.list();
        File child = new File(p, children[index]);

        try {
            return child;
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getIndexOfChild(Object parent, Object child) {
        String cname;
        String[] children = ((File) parent).list();

        if (children.length > 0) {

            cname = ((File) child).getName();
            for (int i = 0; i < children.length; i++) {
                if (cname.equals(children[i])) {
                    return i;
                }
            }
        } else {

            return -1;
        }
        return -1;
    }

    public void valueForPathChanged(TreePath path, Object newvalue) {
    }

    public void addTreeModelListener(TreeModelListener l) {
    }

    public void removeTreeModelListener(TreeModelListener l) {
    }
}
