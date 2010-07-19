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
 * CheckableFileItem.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  Kreecha Puphaiboon, Computer Science Lecturer, Kasem Bundit University
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */

package org.jpowder.JCheckboxList;

import java.io.Serializable;
import javax.swing.Icon;

/**
 *
 */
public class CheckableFileItem implements Serializable{

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
