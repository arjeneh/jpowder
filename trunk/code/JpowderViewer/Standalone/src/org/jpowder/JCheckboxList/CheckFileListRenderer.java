/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jpowder.JCheckboxList;

import org.jpowder.*;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class CheckFileListRenderer extends CheckFileRenderer implements ListCellRenderer {


    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean hasFocus) {
       JpowderInternalframe inFocus = Jpowder.internalFrameInFocus;
        setEnabled(list.isEnabled());
        label.setText(value.toString());
        check.setSelected(((CheckableFileItem) value).isSelected());
        label.setForeground((Color) inFocus.getXYPlot().getRenderer(index).getSeriesPaint(0));
        System.out.println("Color::"+inFocus.getXYPlot().getRenderer(index).getSeriesPaint(0));
        label.setSelected(true);//hilighting the string
        label.setFocus(hasFocus);
        return this;
    }
}

