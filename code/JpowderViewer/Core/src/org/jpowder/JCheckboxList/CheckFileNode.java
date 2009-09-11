/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.JCheckboxList;

/**
 *
 */
import java.util.*;
import javax.swing.tree.*;
/*
public class CheckFileNode extends DefaultMutableTreeNode {

    public final static int SINGLE_SELECTION = 0;
    public final static int DIG_IN_SELECTION = 4;
    protected int selectionMode;
    protected boolean isSelected;

    public CheckFileNode() {
        this(null);
    }

    public CheckFileNode(Object userObject) {
        this(userObject, true, false);
    }

    public CheckFileNode(Object userObject, boolean allowsChildren, boolean isSelected) {
        super(userObject, allowsChildren);
        this.isSelected = isSelected;
        setSelectionMode(DIG_IN_SELECTION);
    }

    public void setSelectionMode(int mode) {
        selectionMode = mode;
    }

    public int getSelectionMode() {
        return selectionMode;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;

        if ((selectionMode == DIG_IN_SELECTION) && (children != null)) {
            Enumeration menum = children.elements();
            while (menum.hasMoreElements()) {
                CheckFileNode node = (CheckFileNode) menum.nextElement();
                node.setSelected(isSelected);
            }
        }
    }

    public boolean isSelected() {
        return isSelected;
    }// If you want to change "isSelected" by CellEditor,
/*
    public void setUserObject(Object obj) {
    if (obj instanceof Boolean) {
    setSelected(((Boolean)obj).booleanValue());
    } else {
    super.setUserObject(obj);
    }
    }
     */

