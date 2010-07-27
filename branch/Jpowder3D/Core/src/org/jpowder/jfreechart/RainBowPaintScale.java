/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.jfreechart;

import java.awt.Color;
import org.jfree.chart.renderer.LookupPaintScale;

/**
 *
 * @author Arjeneh
 */



public class RainBowPaintScale extends LookupPaintScale {


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
