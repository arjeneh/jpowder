/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.chartTools;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import org.jfree.util.ShapeUtilities;

/**
 *
 * @author qyt21516
 */
public class MarkerShapes {


    public static Shape[] markesShape() {

        double dble = Double.parseDouble(ChangePlotStyle.getMarkerSizeTextField().getText());
        float flot = Float.valueOf(ChangePlotStyle.getMarkerSizeTextField().getText());
        Line2D line2D1 = new Line2D.Float(0, 0, -5f, 5f);
        Line2D line2D2 = new Line2D.Float(0, 0, 5f, 5f);
        return new Shape[]{
        new Rectangle2D.Double(-dble / 2, -dble / 2, dble, dble),//Square
        new Ellipse2D.Double(-dble / 2, -dble / 2, dble, dble),//circle
        new Ellipse2D.Double(-dble / 2, -dble / 2, dble, dble / 2),//oval
        new Rectangle2D.Double(-dble / 2, -dble / 2, dble, dble / 2),//Rectangle
        ShapeUtilities.createDiamond(flot),
        ShapeUtilities.createDownTriangle(flot),
        ShapeUtilities.createUpTriangle(flot),
        ShapeUtilities.createRegularCross(flot, 0),
        ShapeUtilities.createLineRegion(line2D1, 10f),
        ShapeUtilities.createLineRegion(line2D2, 10f)


        };

    }
}
