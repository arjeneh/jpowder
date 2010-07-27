/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jpowder.jfreechart;

import java.awt.Color;
import java.awt.Paint;
import org.jfree.chart.renderer.PaintScale;

/**
 *
 * @author qyt21516
 */
class LevelPaintScale implements PaintScale {

    private double upperBound;

    public LevelPaintScale(double upperBound) {
        this.upperBound = upperBound;
    }

    public double getLowerBound() {
        return 0.0;
    }

    public double getUpperBound() {
        return upperBound;
    }

    public Paint getPaint(double value) {
        System.out.println("");
        if (value < 0.0 || value >= upperBound) {
            return Color.GRAY;
        }
        System.out.println("v : " + value);
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