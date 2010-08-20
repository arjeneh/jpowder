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
 * MarkerComobBoxRenderer.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.chartTools.Markers;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

/**
 * Rendering the comboBox with markers inside.
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

    @Override
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
