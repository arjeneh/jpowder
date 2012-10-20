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
 * MarkerShapes.java
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

import org.jpowder.chartTools.*;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import org.jfree.util.ShapeUtilities;

/**
 * Array of marker shapes.
 * 
 */
public class MarkerShapes {


    public static Shape[] markesShape() {

        double dble = Double.parseDouble(ChangePlotStyle.getMarkerSizeSpinner().getValue().toString());
        float flot = Float.valueOf(ChangePlotStyle.getMarkerSizeSpinner().getValue().toString());
        Line2D line2D1 = new Line2D.Float(0, 0, -5f, 5f);
        Line2D line2D2 = new Line2D.Float(0, 0, 5f, 5f);
        return new Shape[]{
        new Rectangle2D.Double(-dble / 2, -dble / 2, dble, dble),//Square
        new Ellipse2D.Double(-dble / 2, -dble / 2, dble, dble),//circle

        ShapeUtilities.createDiamond(flot),
        ShapeUtilities.createDownTriangle(flot),

        new Ellipse2D.Double(-dble / 2, -dble / 2, dble, dble / 2),//oval
        new Rectangle2D.Double(-dble / 2, -dble / 2, dble, dble / 2),//Rectangle
         
        ShapeUtilities.createUpTriangle(flot),
        ShapeUtilities.createRegularCross(flot, 0),
        ShapeUtilities.createDiagonalCross(flot, 0),
        ShapeUtilities.createLineRegion(line2D2, flot),
        ShapeUtilities.createLineRegion(line2D1, flot)
 


        };

    }
}
