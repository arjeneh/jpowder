package org.jpowder.JCheckboxList;

import javax.swing.Icon;

/**
 *
 */
public class CheckableFileItem {

    private String str;
    private boolean isSelected;
    private Icon icon;

    public CheckableFileItem(String str) {
        this.str = str;
        isSelected = true;

    }

    public void setSelected(boolean b) {
        isSelected = b;

    }

    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public String toString() {
        return str;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public Icon getIcon() {
        return icon;
    }
}
