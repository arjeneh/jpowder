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
 * ProportionalDimension.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  Kreecha Puphaiboon, Computer Science Lecturer, Kasem Bundit University

 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.util;

import java.awt.Dimension;


public class ProportionalDimension extends Dimension {

    public ProportionalDimension(Dimension d, double proportion) {
        double x = d.width;
        double y = d.height;

        if (x / y < proportion) {
            width = (int) (y * proportion);
            height = (int) y;
        } else {
            width = (int) x;
            height = (int) (x / proportion);
        }

//        System.out.println("In " + this.getClass().getName() +
//                " x is: " + width + " y is: " +  height );

    }
}
