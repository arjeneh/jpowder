/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.jfreechart;

import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

/**
 *
 * @author M Arjeneh
 */
/**
 * A renderer that connects data points with lines and/or draws shapes at each data point.
 */
public class JpowderXYLineAndShapeRender extends XYLineAndShapeRenderer {

    /**
     * Creates a new renderer.
     *
     * @param colors  the colors.
     */
    public JpowderXYLineAndShapeRender() {

        this.setBaseLinesVisible();
        this.setBaseShapesVisible();

    }

    /**
     * this method sets the line visible
     */
    public void setBaseLinesVisible() {
        super.setBaseLinesVisible(true);
    }

    /**
     * this method sets the marker visible
     */
    public void setBaseShapesVisible() {
        super.setBaseShapesVisible(false);
    }
}

