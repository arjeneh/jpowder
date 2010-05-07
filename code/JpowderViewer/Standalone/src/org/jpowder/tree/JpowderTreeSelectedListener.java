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
 * JpowderTreeSelectedListener.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  Kreecha Puphaiboon, Computer Science Lecturer, Kasem Bundit University
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.tree;

import java.io.File;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

/**
 * If anyone wants to know what file in the tree is selected, then use this.
 *
 * ----------------------
 * JpowderTreeSelectedListener.java
 * ----------------------
 *
 * Changes
 * -------
 * 04-March-2010 : Version 1 (KP);
 */
public class JpowderTreeSelectedListener implements TreeSelectionListener {

    DirectoryModel model;

    /**
     * Create a directory model.
     * This results in displaying a subset of
     * the actual file directory.
     *
     * @param mdl The DirectoryModel implementation
     */
    public JpowderTreeSelectedListener(DirectoryModel mdl) {
        model = mdl;
    }

    public void valueChanged(TreeSelectionEvent e) {
        File fileSysEntity = (File) e.getPath().getLastPathComponent();
        if (fileSysEntity.isDirectory()) {
            model.setDirectory(fileSysEntity);
        } else {
            model.setDirectory(null);
        }
    }
}