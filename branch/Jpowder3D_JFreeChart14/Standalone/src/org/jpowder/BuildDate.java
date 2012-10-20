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
 * BuidDate .java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */

package org.jpowder;

import java.util.ResourceBundle;

/**
 * this class gets the buid date which has been stored in the
 * Jpowderversion.properties file. the properties file gets updated each time
 * the project is buid.
 * 
 */
public class BuildDate {

    public String getVersion() {
        
        String buidDate = ResourceBundle.getBundle("org/jpowder/Jpowderversion").getString("version.date");

        return buidDate;

    }

//    public static void main(String[] args) {
//        BuidDate buidDate = new BuidDate();
//        buidDate.getVersion();
//    }
}
