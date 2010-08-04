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
 * CheckFileListRenderer.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  Kreecha Puphaiboon, Computer Science Lecturer, Kasem Bundit University
 *             M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *                  
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */

package org.jpowder.JCheckboxList;

import org.jpowder.InernalFrame.JpowderInternalframe2D;
import org.jpowder.*;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import org.jpowder.InernalFrame.JpowderInternalframe3D;

/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class CheckFileListRenderer extends CheckFileRenderer implements ListCellRenderer {


    @Override
    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean hasFocus) {

        setEnabled(list.isEnabled());
        label.setText(value.toString());
        check.setSelected(((CheckableFileItem) value).isSelected());
      
//        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
//                 label.setForeground((Color) inFocus.getXYPlot().getRenderer(index).getSeriesPaint(0));
   
        label.setSelected(true);//hilighting the string
        label.setFocus(hasFocus);
        //Jpowder.jpowderInternalFrameUpdate(inFocus);

        return this;
    }
}

