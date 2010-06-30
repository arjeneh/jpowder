/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jpowder.chartTools;

/**
 *
 * @author Arjeneh
 */

import java.awt.Color;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;

/**
 *
 * @author Arjeneh
 */
class MarkerComboBoxRenderer extends JLabel implements ListCellRenderer {

    private JComboBox cb;

    public MarkerComboBoxRenderer(JComboBox cb) {
        this.cb = cb;
        setIconTextGap(15);
        setOpaque(true);

    }

    public Component getListCellRendererComponent(JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {
        MarkesIcons markesIcons = (MarkesIcons) value;
//        setText(markesIcons.getName());
        setIcon(markesIcons.getMarkerIcon());
        setBorder(new LineBorder(Color.red, 1));
//          UIManager.put("ComboBox.selectionBackground", Color.yellow);
//          updateUI();
        if (isSelected) {
                 setBackground(Color.GREEN);
            setForeground(Color.GREEN);
     
        } else {
              setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        }

        return this;
    }
}
