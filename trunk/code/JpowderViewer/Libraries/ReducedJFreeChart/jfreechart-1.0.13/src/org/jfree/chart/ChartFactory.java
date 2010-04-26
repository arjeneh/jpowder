/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2008, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc.
 * in the United States and other countries.]
 *
 * -----------------
 * ChartFactory.java
 * -----------------
 * (C) Copyright 2001-2009, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Serge V. Grachov;
 *                   Joao Guilherme Del Valle;
 *                   Bill Kelemen;
 *                   Jon Iles;
 *                   Jelai Wang;
 *                   Richard Atkinson;
 *                   David Browning (for Australian Institute of Marine
 *                       Science);
 *                   Benoit Xhenseval;
 *
 * Changes
 *
 */

package org.jfree.chart;


import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;

/**
 * A collection of utility methods for creating some standard charts with
 * JFreeChart.
 */
public abstract class ChartFactory {

    /** The chart theme. */
    private static ChartTheme currentTheme = new StandardChartTheme("JFree");

    /**
     * Returns the current chart theme used by the factory.
     *
     * @return The chart theme.
     *
     * @see #setChartTheme(ChartTheme)
     * @see ChartUtilities#applyCurrentTheme(JFreeChart)
     *
     * @since 1.0.11
     */
    public static ChartTheme getChartTheme() {
        return currentTheme;
    }

    /**
     * Sets the current chart theme.  This will be applied to all new charts
     * created via methods in this class.
     *
     * @param theme  the theme (<code>null</code> not permitted).
     *
     * @see #getChartTheme()
     * @see ChartUtilities#applyCurrentTheme(JFreeChart)
     *
     * @since 1.0.11
     */
    public static void setChartTheme(ChartTheme theme) {
        if (theme == null) {
            throw new IllegalArgumentException("Null 'theme' argument.");
        }
        currentTheme = theme;

        // here we do a check to see if the user is installing the "Legacy"
        // theme, and reset the bar painters in that case...
        if (theme instanceof StandardChartTheme) {
            StandardChartTheme sct = (StandardChartTheme) theme;
       
        }
    }





    /**
     * Creates a scatter plot with default settings.  The chart object
     * returned by this method uses an {@link XYPlot} instance as the plot,
     * with a {@link NumberAxis} for the domain axis, a  {@link NumberAxis}
     * as the range axis, and an {@link XYLineAndShapeRenderer} as the
     * renderer.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param xAxisLabel  a label for the X-axis (<code>null</code> permitted).
     * @param yAxisLabel  a label for the Y-axis (<code>null</code> permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param orientation  the plot orientation (horizontal or vertical)
     *                     (<code>null</code> NOT permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return A scatter plot.
     */
    public static JFreeChart createScatterPlot(String title, String xAxisLabel,
            String yAxisLabel, XYDataset dataset, PlotOrientation orientation,
            boolean legend, boolean tooltips, boolean urls) {

        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
        NumberAxis yAxis = new NumberAxis(yAxisLabel);
        yAxis.setAutoRangeIncludesZero(false);

        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, null);

        XYToolTipGenerator toolTipGenerator = null;
        if (tooltips) {
            toolTipGenerator = new StandardXYToolTipGenerator();
        }


        XYItemRenderer renderer = new XYLineAndShapeRenderer(false, true);
        renderer.setBaseToolTipGenerator(toolTipGenerator);
        plot.setRenderer(renderer);
        plot.setOrientation(orientation);

        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;

    }


    /**
     * Creates a line chart (based on an {@link XYDataset}) with default
     * settings.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param xAxisLabel  a label for the X-axis (<code>null</code> permitted).
     * @param yAxisLabel  a label for the Y-axis (<code>null</code> permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param orientation  the plot orientation (horizontal or vertical)
     *                     (<code>null</code> NOT permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return The chart.
     */
    public static JFreeChart createXYLineChart(String title,
                                               String xAxisLabel,
                                               String yAxisLabel,
                                               XYDataset dataset,
                                               PlotOrientation orientation,
                                               boolean legend,
                                               boolean tooltips,
                                               boolean urls) {

        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
        NumberAxis yAxis = new NumberAxis(yAxisLabel);
        XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setOrientation(orientation);
        if (tooltips) {
            renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
        }
   

        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;

    }

  


    /**
     * Creates and returns a time series chart.  A time series chart is an
     * {@link XYPlot} with a {@link DateAxis} for the x-axis and a
     * {@link NumberAxis} for the y-axis.  The default renderer is an
     * {@link XYLineAndShapeRenderer}.
     * <P>
     * A convenient dataset to use with this chart is a
     * {@link org.jfree.data.time.TimeSeriesCollection}.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param timeAxisLabel  a label for the time axis (<code>null</code>
     *                       permitted).
     * @param valueAxisLabel  a label for the value axis (<code>null</code>
     *                        permitted).
     * @param dataset  the dataset for the chart (<code>null</code> permitted).
     * @param legend  a flag specifying whether or not a legend is required.
     * @param tooltips  configure chart to generate tool tips?
     * @param urls  configure chart to generate URLs?
     *
     * @return A time series chart.
     */
    public static JFreeChart createTimeSeriesChart(String title,
                                                   String timeAxisLabel,
                                                   String valueAxisLabel,
                                                   XYDataset dataset,
                                                   boolean legend,
                                                   boolean tooltips,
                                                   boolean urls) {

       

        NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
        valueAxis.setAutoRangeIncludesZero(false);  // override default
        XYPlot plot = new XYPlot(dataset, null, valueAxis, null);

        XYToolTipGenerator toolTipGenerator = null;
        if (tooltips) {
            toolTipGenerator
                = StandardXYToolTipGenerator.getTimeSeriesInstance();
        }

  
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true,
                false);
        renderer.setBaseToolTipGenerator(toolTipGenerator);

        plot.setRenderer(renderer);

        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;

    }

 

  
}
