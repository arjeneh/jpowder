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
 * RainBowPaintScale.java
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
import org.jfree.chart.renderer.LookupPaintScale;

/**
 * Creating RainBow colour bar for 3D plot.
 *
 */
public class RainBowPaintScale extends LookupPaintScale {

/**
 * 
 * @param min
 * @param max
 * @param color
 */
    public RainBowPaintScale(double min, double max, Color color) {

      super(min,max,color);
      int red = 0;
      int blue = 0;
      int green = 0;
      double value = min;
      double valInc = (max-min)/(5*255);
      red = 0;
      blue = 0;
      green = 0;
      for (blue = 0; blue < 255; blue++) {
         Color c = new Color(red, green, blue);
         this.add(value = value + valInc, c);
      }
      red = 0;
      blue = 255;
      green = 0;
      for (green = 0; green < 255; green++) {
         Color c = new Color(red, green, blue);
         this.add(value = value + valInc, c);
      }
      red = 0;
      blue = 255;
      green = 255;
      for (blue = 255; blue > 0; blue--) {
         Color c = new Color(red, green, blue);
        this.add(value = value + valInc, c);
      }
      red = 0;
      green = 255;
      blue = 0;
      for (red = 0; red < 255; red++) {
         Color c = new Color(red, green, blue);
         this.add(value = value + valInc, c);
      }
      red = 255;
      green = 255;
      blue = 0;
      for (green = 255; green > 0; green--) {
         Color c = new Color(red, green, blue);
         this.add(value = value + valInc, c);
      }
//      this.add(0, new Color(255, 255, 255));  //white for 0
    }
}
