/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.chartTools;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;
import org.jpowder.Jpowder;
import org.jpowder.JpowderInternalframe;

/**
 *
 * @author qyt21516
 */
public class ComboBoxRenderer implements ListCellRenderer {

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JpowderInternalframe inFocus = Jpowder.internalFrameInFocus;
        JLabel label = new JLabel(value.toString());
        label.setOpaque(isSelected);
        if (index >= 0) {
//                        j.setForeground((Color) FilesPlotter.allSeriescolors[index]);
//            label.setForeground((Color) FilesPlotter.getPlot().getRenderer(index).getSeriesPaint(0));
             label.setForeground((Color) inFocus.getXYPlot().getRenderer(index).getSeriesPaint(0));
        }
        if (isSelected) {
            label.setBorder(LineBorder.createBlackLineBorder());

        }
        return label;
    }
}
