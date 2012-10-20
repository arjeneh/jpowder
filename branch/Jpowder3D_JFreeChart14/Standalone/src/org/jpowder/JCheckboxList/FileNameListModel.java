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
 * FileNameListModel.java
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

/**
 * Get the file name and add to JList
 */
public class FileNameListModel extends javax.swing.DefaultListModel {

    private java.util.Vector<CheckableFileItem> fileNameModelVec;

    public FileNameListModel() {
        this.fileNameModelVec = new java.util.Vector<CheckableFileItem>();
    }

    @Override
    public int getSize() {
        return fileNameModelVec.size();
    }

    @Override
    public Object getElementAt(int index) {
        return fileNameModelVec.toArray()[index];
    }

    public void addCheckableFile(Object element) {
        
        if (fileNameModelVec.add((CheckableFileItem) element)) {
            fireContentsChanged(this, 0, getSize());
        }

    }

    public void addAll(Object elements[]) {
        java.util.Collection c = java.util.Arrays.asList(elements);
        fileNameModelVec.addAll(c);
        fireContentsChanged(this, 0, getSize());
    }

    //Clear and update changes of of file names.
    @Override
    public void clear() {
        fileNameModelVec.clear();
        fireContentsChanged(this, 0, getSize());
    }
    /*
    @Override
    public boolean contains(Object element) {
    return fileNameModelVec.contains(element);
    }
     */

    @Override
    public Object firstElement() {
        return fileNameModelVec.firstElement();
    }

    public java.util.Iterator iterator() {
        return fileNameModelVec.iterator();
    }

    @Override
    public Object lastElement() {
        return fileNameModelVec.lastElement();
    }
}
