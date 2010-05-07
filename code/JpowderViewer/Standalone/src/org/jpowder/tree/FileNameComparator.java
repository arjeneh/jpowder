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
 * FileNameComparator.java
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
import java.text.Collator;
import java.util.Comparator;

/**
 *
 * 
 */
public class FileNameComparator implements Comparator {

    public int compare(Object o1, Object o2) {
        File a = (File)o1;
        File b = (File)o2;

        Collator collator = Collator.getInstance();
            if (a.isDirectory() && b.isFile()) return -1;
            else if (a.isFile() && b.isDirectory()) return +1;

            int result = collator.compare(a.getName(), b.getName());



            if (result != 0) return result;

            result = collator.compare(
                    a.getAbsolutePath(), b.getAbsolutePath());
            return result;
    }

}
