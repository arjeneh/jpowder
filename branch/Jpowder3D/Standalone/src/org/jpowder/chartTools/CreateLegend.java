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
 * CreateLegend.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
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
import org.jpowder.Jpowder;
import org.jpowder.InernalFrame.JpowderInternalframe2D;

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

public class CreateLegend {

    private Shape shape = new Rectangle(10, 10);
    private LegendTitle legend;
    private  JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;



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
                    inFocus.getChart().addSubtitle(legend);



    }
    public LegendTitle getLegendTitle(){
        return legend;
    }
}

