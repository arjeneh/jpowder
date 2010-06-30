/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.jfreechart;

import java.awt.Color;
import java.awt.Paint;
import org.jfree.chart.ChartColor;

/**
 *
 * @author qyt21516
 */



public class DefaultSeriesColours{


    public final static Color lightGray = new Color(192, 192, 192);
    public final static Color gray      = new Color(128, 128, 128);
    public final static Color darkGray  = new Color(64, 64, 64);
    public final static Color black 	= new Color(0, 0, 0);
    public final static Color red       = new Color(255, 0, 0);
    public final static Color pink      = new Color(255, 175, 175);
    public final static Color orange 	= new Color(255, 200, 0);
    public final static Color yellow 	= new Color(255, 255, 0);
    public final static Color green 	= new Color(0, 255, 0);
    public final static Color magenta	= new Color(255, 0, 255);
    public final static Color cyan 	= new Color(0, 255, 255);
    public final static Color blue 	= new Color(0, 0, 255);

    
//    public DefaultColours(int r, int g, int b) {
//        super(r, g, b);
//    }

    public static Paint[] JpowderPaintArray() {

        return new Paint[]{
        Color.BLUE, Color.RED, ChartColor.VERY_DARK_GREEN,
        Color.ORANGE, Color.CYAN, Color.MAGENTA, ChartColor.DARK_YELLOW, Color.BLACK,
        Color.PINK, Color.LIGHT_GRAY, Color.GRAY, ChartColor.DARK_BLUE,
        ChartColor.DARK_RED, ChartColor.DARK_GREEN, Color.yellow,
        ChartColor.DARK_CYAN, ChartColor.DARK_GRAY, ChartColor.VERY_DARK_BLUE,
        ChartColor.VERY_DARK_RED, Color.GREEN, ChartColor.VERY_DARK_YELLOW,
        ChartColor.VERY_DARK_CYAN, ChartColor.VERY_DARK_MAGENTA, ChartColor.VERY_LIGHT_BLUE,
        ChartColor.VERY_LIGHT_RED, ChartColor.VERY_LIGHT_GREEN, ChartColor.VERY_LIGHT_YELLOW,
        ChartColor.VERY_LIGHT_CYAN, ChartColor.VERY_LIGHT_MAGENTA
        };
    }
}
