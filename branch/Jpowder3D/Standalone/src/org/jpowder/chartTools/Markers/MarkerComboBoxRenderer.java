/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.chartTools.Markers;

/**
 *
 * @author Arjeneh
 */

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

/**
 *
 * @author Arjeneh
 */
public class MarkerComboBoxRenderer extends JLabel implements ListCellRenderer {

    private JComboBox cb;
    private Border border;

    public MarkerComboBoxRenderer(JComboBox cb) {
        this.cb = cb;
        setIconTextGap(10);
        setOpaque(true);
        border = BorderFactory.createLineBorder(Color.RED, 1);

    }

    public Component getListCellRendererComponent(JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {
        MarkesIcons markesIcons = (MarkesIcons) value;
//        setText(markesIcons.getName());
        setIcon(markesIcons.getMarkerIcon());
        setBorder(border);


        if (isSelected) {
            setBackground(new Color(0, 0, 0));
            setForeground(Color.GREEN);

        } else {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        }


        return this;
    }
}
