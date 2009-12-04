
package org.jpowder.JCheckboxList;


import java.awt.Color;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import org.jpowder.jfreechart.FilesPlotter;

/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class CheckFileListRenderer extends CheckFileRenderer implements ListCellRenderer {

    private Icon commonIcon;
    private javax.swing.ListCellRenderer ren = null;

    public CheckFileListRenderer() {
    }

    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean hasFocus) {

        // TODO: Modify for draw zebra stripe.
        //final java.awt.Component c = ren.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
        /*if (!isSelected && drawStripes) {
            c.setBackground( rowColors[index & 1] );
        }*/
        setEnabled(list.isEnabled());
        //
        check.setSelected(((CheckableFileItem) value).isSelected());
        //
       // label.setFont(list.getFont());
        label.setForeground((Color) FilesPlotter.allseriescolors[index]);
        label.setText(value.toString());
        label.setSelected(true);//hilighting the string
        label.setFocus(hasFocus);
        Icon icon = ((CheckableFileItem) value).getIcon();
        if (icon == null) {
            icon = commonIcon;
        }
        label.setIcon(icon);
        return this;
    }
}
