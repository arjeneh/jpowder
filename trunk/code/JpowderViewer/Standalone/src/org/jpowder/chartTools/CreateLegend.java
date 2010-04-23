/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.chartTools;

import java.awt.Rectangle;
import java.awt.Shape;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jpowder.InfoPanel;
import org.jpowder.Jpowder;
import org.jpowder.JpowderInternalframe;

/**
 *
 * @author M Arjeneh
 */
public class CreateLegend {

    /**
     * label - the label (null not permitted).
    description - the description (not currently used, null permitted).
    toolTipText - the tool tip text (null permitted).
    urlText - the URL text (null permitted).
    shapeVisible - a flag that controls whether or not the shape is displayed.
    shape - the shape (null permitted).
    shapeFilled - a flag that controls whether or not the shape is filled.
    fillPaint - the fill paint (null not permitted).
    shapeOutlineVisible - a flag that controls whether or not the shape is outlined.
    outlinePaint - the outline paint (null not permitted).
    outlineStroke - the outline stroke (null not permitted).
    lineVisible - a flag that controls whether or not the line is visible.
    line - the line.
    lineStroke - the stroke (null not permitted).
    linePaint - the line paint (null not permitted).
     */

    private Shape shape = new Rectangle(10, 10);
    private LegendTitle legend;
    private  JpowderInternalframe inFocus = Jpowder.internalFrameInFocus;



    public void setLegend() {
        
          LegendItemCollection legendItemCollection = new LegendItemCollection();
        for (int i = 0; i < inFocus.getXYPlot().getDatasetCount(); i++) {
             XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) inFocus.getXYPlot().getRenderer(i);
            if (renderer.getBaseShapesVisible()) {
                legendItemCollection.add(new LegendItem(
                        inFocus.getPowderDataSet().elementAt(i).getFileName(),
                        null,
                        null,
                        null,
                        renderer.getBaseShapesVisible(),//
                        inFocus.getXYPlot().getRenderer(i).getSeriesShape(0),
                        renderer.getBaseShapesFilled(),
                        inFocus.getXYPlot().getRenderer(i).getSeriesPaint(0),
                        true,
                        inFocus.getXYPlot().getRenderer(i).getSeriesPaint(0),
                        inFocus.getXYPlot().getRenderer(i).getBaseOutlineStroke(),
                        true,
                        renderer.getLegendLine(),
                        inFocus.getXYPlot().getRenderer(i).getBaseOutlineStroke(),
                        inFocus.getXYPlot().getRenderer(i).getSeriesPaint(0)));
            }
                if (!renderer.getBaseShapesVisible()) {
                    legendItemCollection.add(new LegendItem(
                            inFocus.getPowderDataSet().elementAt(i).getFileName(),
                            null,
                            null,
                            null,
                            false,//
                            shape,
                            false,
                            inFocus.getXYPlot().getRenderer(i).getSeriesPaint(0),
                            true,
                            inFocus.getXYPlot().getRenderer(i).getSeriesPaint(0),
                            inFocus.getXYPlot().getRenderer(i).getBaseOutlineStroke(),
                            true,
                            renderer.getLegendLine(),
                            inFocus.getXYPlot().getRenderer(i).getBaseOutlineStroke(),
                            inFocus.getXYPlot().getRenderer(i).getSeriesPaint(0)));
                }
        }
                    inFocus.getXYPlot().setFixedLegendItems(legendItemCollection);

                     legend = new LegendTitle(inFocus.getXYPlot());

                    legend.setMargin(new RectangleInsets(1.0, 1.0, 1.0, 1.0));
                    legend.setPosition(RectangleEdge.BOTTOM);
                    inFocus.getchart().addSubtitle(legend);
     
            
        
    }
    public LegendTitle getLegendTitle(){
        return legend;
    }

}
