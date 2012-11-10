package org.jpowder.util;

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
 * CheckDoubleValue.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  Kreecha Puphaiboon, Computer Science Lecturer, Kasem Bundit University
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */


/**
 *
 * @author Kreecha Puphaiboon
 */
public class CheckDoubleValue {

    public boolean checkIfNumber(String in) {

        try {

            Double.parseDouble(in);//.parseInt(in);

        } catch (NumberFormatException ex) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        String str = "A2222";

        CheckDoubleValue v = new CheckDoubleValue();
        boolean value = v.checkIfNumber(str);
        if (value) {
            System.out.println("Value is Double!");
        } else {
            System.out.println("Value is not Double!");
        }

    }
}
