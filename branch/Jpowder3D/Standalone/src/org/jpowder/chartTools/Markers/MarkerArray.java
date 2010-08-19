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
 * markerArray.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.chartTools.Markers;

import java.util.ArrayList;

/**
 *
 * Array of markers name
 */
public class MarkerArray {

    public MarkesIcons[] createCountriesArray() {

        String[] markers = {
            "Square,s",
            "Circle,c",
            "Diamond,d",
            "DownTraingle,dt",
            "Oval,o",
            "Rectangle,r",
            "UpTraingle,t",
            "RegularCross,rc",
            "DiagnalCross,dc",
            "LeftLine,lf",
            "RightLine,rl"
        };

        ArrayList<MarkesIcons> markesIconses = new ArrayList<MarkesIcons>();

        for (String citem : markers) {

            String[] cdata = citem.split(",");
            markesIconses.add(new MarkesIcons(cdata[0],
                    getClass().getResource("/images/Markers/" +
                    cdata[0].toLowerCase() +
                    ".png")));
        }

        MarkesIcons[] carray = markesIconses.toArray(new MarkesIcons[0]);
        return carray;

    }
}
