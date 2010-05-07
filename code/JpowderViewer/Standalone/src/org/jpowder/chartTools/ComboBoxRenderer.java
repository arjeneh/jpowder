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
 * ComboBoxRenderer.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
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
 * Rendrer  for adding plots name to the ComboBoxes.
 * @author M Arjeneh
 */
public class ComboBoxRenderer implements ListCellRenderer {

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JpowderInternalframe inFocus = Jpowder.internalFrameInFocus;
        JLabel label = new JLabel(value.toString());
        label.setOpaque(isSelected);
        if (index >= 0) {

            label.setForeground((Color) inFocus.getXYPlot().getRenderer(index).getSeriesPaint(0));
        }
        if (isSelected) {
            label.setBorder(LineBorder.createBlackLineBorder());

        }
        return label;
    }
}
