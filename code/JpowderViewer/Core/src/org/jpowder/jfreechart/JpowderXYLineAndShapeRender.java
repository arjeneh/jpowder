/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.jfreechart;

import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

/**
 *
 * @author qyt21516
 */
/**
 * A renderer that connects data points with lines and/or draws shapes at each data point.
 */
public class JpowderXYLineAndShapeRender extends XYLineAndShapeRenderer {

    private FilesPlotter file;
    private static int seriescreated = 0;
    // private XYPlot plot;

//   public  Paint[] allcolors={Color.blue, Color.red, Color.green,
//                Color.orange, Color.cyan,
//                Color.magenta,Color.yellow, Color.BLACK,Color.pink};
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
     * Returns the paint for an item.  Overrides the default behaviour inherited from
     * AbstractSeriesRenderer.
     *
     * @param row  the series.
     * @param column  the category.
     *
     * @return The item color.
     */
    // @Override
    //       public Paint getItemPaint(final int row, final int column) {
    //           return XYLineAndShapeRender.allcolors[2];
    //       }
    /**
     * setting the legend the same colour as series
     * @param series
     * @return
     */
//  @Override
//  public Paint lookupSeriesPaint(int series){
//    return XYLineAndShapeRender.allcolors[2];
//  }
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
//  @Override
//   public void setSeriesPaint(int series, java.awt.Paint paint){
//
//     super.setSeriesPaint(0,Color.BLUE);
//     super.setSeriesPaint(1,Color.BLACK);
//     this.setSeriesPaint(2,Color.BLUE);
//
//   }
//    @Override
//   public Paint getSeriesPaint(int series){
//
//      return series;
//   }
}

