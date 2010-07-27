/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.Analysis;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import org.jpowder.Jpowder;
import org.jpowder.InernalFrame.JpowderInternalframe2D;

/**
 *
 * @author qyt21516
 */
public class TableRenderer implements TableCellRenderer {

    public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        JLabel fileName = new JLabel(value.toString());
        fileName.setForeground((Color) inFocus.getXYPlot().getRenderer(row).getSeriesPaint(0));
        return fileName;


    }
}
