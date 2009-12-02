/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jpowder.dataset.jfreechart;

import org.jfree.chart.renderer.xy.XYErrorRenderer;

/**
 *
 * @author qyt21516
 */

/**
 * A line and shape renderer that can also display x and/or y-error values.
 */
 public class XYErrorRender extends XYErrorRenderer {

    public XYErrorRender() {
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


