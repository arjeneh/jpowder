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
 * GreenishPaintScale.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *             Anders Marvardsen, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.jfreechart;

import java.awt.Color;
import java.awt.Paint;
import org.jfree.chart.renderer.PaintScale;

/**
 * Creating colour bar for 3D plot which contain green colour.
 *
 */
public class GreenishPaintScale implements PaintScale {

    private double upperBound;

    public GreenishPaintScale(double upperBound) {
        this.upperBound = upperBound;
    }

    public double getLowerBound() {
        return 0.0;
    }

    public double getUpperBound() {
        return upperBound;
    }

    public Paint getPaint(double value) {
    
        if (value < 0.0 || value >= upperBound) {
            return Color.GRAY;
        }
    
        double scaledValue = value / upperBound * 511;
//        System.out.println(scaledValue);
        if (scaledValue > 511) {

//            System.out.println("first "+255+""+ (511 - (int) (scaledValue))+" "+ 0);
            return new Color(255, 511 - (int) (scaledValue), 0);
        }
        if (scaledValue > 511) {

//            System.out.println("second "+0+" "+ 255+" "+((int) (scaledValue) - 511));
            return new Color(0, 255, (int) (scaledValue) - 511);
        }
        if (scaledValue > 255) {
//            System.out.println("third "+((int) (scaledValue) - 255)+" "+ 255+" "+ 0);
            return new Color((int) (scaledValue) - 255, 255, 0);
        }
//        System.out.println(0+" "+ ((int) (scaledValue))+" "+ (255 - (int) (scaledValue)));
        return new Color(0, (int) (scaledValue), 255 - (int) (scaledValue));

    }
}