/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.jfreechart;

import java.awt.Color;
import java.awt.Paint;

/**
 * This class Contains all the colours for the series paint.
 * 
 */
public class DefaultSeriesColours {

    public final static Color lightGray = new Color(192, 192, 192);
    public final static Color gray = new Color(128, 128, 128);
    public final static Color darkGray = new Color(64, 64, 64);
    public final static Color black = new Color(0, 0, 0);
    public final static Color red = new Color(255, 0, 0);
    public final static Color pink = new Color(255, 175, 175);
    public final static Color orange = new Color(255, 200, 0);
    public final static Color yellow = new Color(255, 255, 0);
    public final static Color green = new Color(0, 255, 0);
    public final static Color magenta = new Color(255, 0, 255);
    public final static Color cyan = new Color(0, 255, 255);
    public final static Color blue = new Color(0, 0, 255);
    public static final Color VERY_DARK_RED = new Color(0x80, 0x00, 0x00);
    public static final Color DARK_RED = new Color(0xc0, 0x00, 0x00);
    public static final Color LIGHT_RED = new Color(0xFF, 0x40, 0x40);
    public static final Color VERY_LIGHT_RED = new Color(0xFF, 0x80, 0x80);
    public static final Color VERY_DARK_YELLOW = new Color(0x80, 0x80, 0x00);
    public static final Color DARK_YELLOW = new Color(0xC0, 0xC0, 0x00);
    public static final Color LIGHT_YELLOW = new Color(0xFF, 0xFF, 0x40);
    public static final Color VERY_LIGHT_YELLOW = new Color(0xFF, 0xFF, 0x80);
    public static final Color VERY_DARK_GREEN = new Color(0x00, 0x80, 0x00);
    public static final Color DARK_GREEN = new Color(0x00, 0xC0, 0x00);
    public static final Color LIGHT_GREEN = new Color(0x40, 0xFF, 0x40);
    public static final Color VERY_LIGHT_GREEN = new Color(0x80, 0xFF, 0x80);
    public static final Color VERY_DARK_CYAN = new Color(0x00, 0x80, 0x80);
    public static final Color DARK_CYAN = new Color(0x00, 0xC0, 0xC0);
    public static final Color LIGHT_CYAN = new Color(0x40, 0xFF, 0xFF);
    public static final Color VERY_LIGHT_CYAN = new Color(0x80, 0xFF, 0xFF);
    public static final Color VERY_DARK_BLUE = new Color(0x00, 0x00, 0x80);
    public static final Color DARK_BLUE = new Color(0x00, 0x00, 0xC0);
    public static final Color LIGHT_BLUE = new Color(0x40, 0x40, 0xFF);
    public static final Color VERY_LIGHT_BLUE = new Color(0x80, 0x80, 0xFF);
    public static final Color VERY_DARK_MAGENTA = new Color(0x80, 0x00, 0x80);
    public static final Color DARK_MAGENTA = new Color(0xC0, 0x00, 0xC0);
    public static final Color LIGHT_MAGENTA = new Color(0xFF, 0x40, 0xFF);
    public static final Color VERY_LIGHT_MAGENTA = new Color(0xFF, 0x80, 0xFF);

//    public DefaultColours(int r, int g, int b) {
//        super(r, g, b);
//    }
    /**
     * array of colours
     * @return array of colour
     */
    public static Paint[] JpowderPaintArray() {

        return new Paint[]{
                    blue, red, VERY_DARK_GREEN, orange, cyan, magenta, DARK_YELLOW, black, DARK_RED, DARK_GREEN, yellow, DARK_CYAN, darkGray, VERY_DARK_BLUE, VERY_DARK_RED, VERY_LIGHT_MAGENTA, VERY_LIGHT_BLUE,
                    VERY_LIGHT_RED, VERY_LIGHT_GREEN, VERY_LIGHT_YELLOW, VERY_LIGHT_CYAN, VERY_LIGHT_MAGENTA
                };
    }

    /**
     *this method is for to make sure that we dont get array out of bound exception.
     * @param i
     * @return
     */
    public static Paint getSeriesColors(int i) {

        return JpowderPaintArray()[i % JpowderPaintArray().length];
    }
}
