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
 * Axis.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Original Author:Kreecha Puphaiboon
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.tree;

import java.io.File;
import java.util.Comparator;

/**
 * Purpose: A class to sort File with different sorting types.
 * @author Kreecha Puphaiboon
 */
public class FileComparator implements Comparator {

    private SORTBY property;
    private Comparator comparator;

    public FileComparator(SORTBY t) {
        this.property = t;
    }

    public FileComparator() {
        comparator = new FileNameComparator();
    }

    public int compare(Object o1, Object o2) {

        // Get the file of the objct value.
        File value1 = (File) o1;
        File value2 = (File) o2;

        switch (this.property) {
            case FILE_NAME:
                FileNameComparator fnc = new FileNameComparator();
                return fnc.compare(value1, value2);
            case FILE_SIZE:
                FileSizeComparator fsc = new FileSizeComparator();
                return fsc.compare(value1, value2);
            default:
                comparator = new FileNameComparator();
                return comparator.compare(value1, value2);
        }

        //return comparator.compare(value1, value2);
    }
}
