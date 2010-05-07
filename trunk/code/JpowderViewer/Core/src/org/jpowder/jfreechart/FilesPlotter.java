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
 * FilesPlotter.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  Kreecha Puphaiboon, Computer Science Lecturer, Kasem Bundit University
 *             M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *             Anders Marvardsen, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.jfreechart;

import org.jpowder.dataset.*;
import java.awt.Color;
import java.awt.Paint;
import java.text.DecimalFormat;
import java.util.Vector;
import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.ui.RectangleInsets;

/**
 * Implements DatasetPlotter interface. Creates chart container data from
 * one or more powder diffraction files.
 *
 */
public class FilesPlotter extends DatasetPlotter {

    private static Vector<DataSet> datasets;
    public static XYPlot plot;
    private static JFreeChart chart;
    public static Paint[] allSeriescolors = {Color.BLUE, Color.RED, ChartColor.VERY_DARK_GREEN,
        Color.ORANGE, Color.CYAN, Color.MAGENTA, ChartColor.DARK_YELLOW, Color.BLACK,
        Color.PINK, Color.LIGHT_GRAY, Color.GRAY, ChartColor.DARK_BLUE, ChartColor.DARK_RED, ChartColor.DARK_GREEN, Color.yellow, ChartColor.DARK_CYAN, ChartColor.DARK_GRAY, ChartColor.VERY_DARK_BLUE, ChartColor.VERY_DARK_RED, Color.GREEN, ChartColor.VERY_DARK_YELLOW, ChartColor.VERY_DARK_CYAN, ChartColor.VERY_DARK_MAGENTA, ChartColor.VERY_LIGHT_BLUE, ChartColor.VERY_LIGHT_RED, ChartColor.VERY_LIGHT_GREEN, ChartColor.VERY_LIGHT_YELLOW, ChartColor.VERY_LIGHT_CYAN, ChartColor.VERY_LIGHT_MAGENTA};

    /**
     *
     * @param d
     */
    public FilesPlotter(Vector<DataSet> d) {
        super(d);
        FilesPlotter.datasets = d;

    }

    /**
     *
     * @param d
     */
    public FilesPlotter(DataSet d) {
        super(d);
        datasets = new Vector<DataSet>();
        FilesPlotter.datasets.addElement(d);

    }

    public static Paint getSeriesColors(int i) {

        return allSeriescolors[i % allSeriescolors.length];
    }

    public String description() {
        return "Multiple Files Plotter";
    }

    /**
     * creating the chart panel
     * @return chartPanel
     */
    @Override
    public ChartPanel createPowderChart() {
        // get chart
        chart = createChart();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);


        // create panel from chart and set some panel attributes
        ChartPanel chartPanel = new ChartPanel(chart, true);

//    chartPanel.setMaximumSize(new java.awt.Dimension(300, 300));
        chartPanel.setDisplayToolTips(false);
        chartPanel.getChartRenderingInfo().setEntityCollection(null);
//        chartPanel.add(new JpowderPopupMenu(chartPanel));
        //User click and it brings up a new Frame for editing the chart.
        chartPanel.addChartMouseListener(new PowderChartMouseObserver(chartPanel));
        return chartPanel;
    }

    public static XYPlot getPlot() {
        return plot;
    }

    public static Vector<DataSet> getDataSets() {
        return datasets;
    }

    public static JFreeChart getChart() {
        return chart;
    }

    /**
     * Creates the chart containing data from one or more powder diffraction files
     * @return chart
     */
    @SuppressWarnings("static-access")
    public JFreeChart createChart() {
        String x = "2\u03D1";//unicode 2thetha
        NumberAxis xAxis = new NumberAxis(x.toUpperCase());
        NumberAxis yAxis = new NumberAxis("Intensity");
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

//         xAxis.setAxisLineVisible(false);
//        yAxis.setAxisLineVisible(false);

        // get a reference to the plot for further customisation...
        JpowderXYLineAndShapeRender renderer1 = new JpowderXYLineAndShapeRender();
        renderer1.setSeriesPaint(0, getSeriesColors(0));
        JpowderXYErrorRender renderer2 = new JpowderXYErrorRender();
        renderer2.setSeriesPaint(0, getSeriesColors(0));
        //Displaying the X&Y in Tooltip
        XYToolTipGenerator tooltip = new StandardXYToolTipGenerator(
                "{1},{2}", new DecimalFormat("0.000"), new DecimalFormat("0.000"));
        if (datasets.elementAt(0) instanceof DataSetNoErrors) {

            plot = new XYPlot(
                    new JpowderXYDataset(datasets.elementAt(0)),
                    xAxis, yAxis, renderer1);

        } else {
            plot = new XYPlot(new JpowderInternvalXYDataset((DataSetWithErrors) datasets.elementAt(0)),
                    xAxis, yAxis, renderer2);

            renderer2.setToolTipGenerator(tooltip);
        }
        plot.setBackgroundPaint(ChartColor.lightGray);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        for (int i = 1; i < datasets.size(); i++) {
            JpowderXYLineAndShapeRender renderer3 = new JpowderXYLineAndShapeRender();
            renderer3.setSeriesPaint(0, getSeriesColors(i));
            JpowderXYErrorRender renderer4 = new JpowderXYErrorRender();
            renderer4.setSeriesPaint(0, getSeriesColors(i));

//            NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
//            rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());


            if (datasets.elementAt(i) instanceof DataSetNoErrors) {

                plot.setDataset(i, new JpowderXYDataset(datasets.elementAt(i)));

                plot.setRenderer(i, renderer3);
            } else {

                plot.setDataset(i, new JpowderInternvalXYDataset((DataSetWithErrors) datasets.elementAt(i)));
                plot.setRenderer(i, renderer4);
            }
        }
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
                plot.setAxisOffset(RectangleInsets.ZERO_INSETS);
        chart = new JFreeChart(null, null, plot, false);// for getting the chart header
        chart.setBackgroundPaint(Color.white);
        return chart;
    }

    /**
     * for adding the dataset to the already existing plot.
     * @param plot
     * @param data
     */
    public static void addDataToJpowderInternalFrame(XYPlot plot, Vector<DataSet> data) {

        for (int i = 0; i < data.size(); i++) {
            int newPlotIndex = plot.getDatasetCount();
            JpowderXYLineAndShapeRender renderer3 = new JpowderXYLineAndShapeRender();
            JpowderXYErrorRender renderer4 = new JpowderXYErrorRender();
            renderer3.setSeriesPaint(0, getSeriesColors(plot.getDatasetCount()));
            renderer4.setSeriesPaint(0, getSeriesColors(plot.getDatasetCount()));
            // change the auto tick unit selection to integer units only...
            NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
            rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

            if (data.elementAt(i) instanceof DataSetNoErrors) {
                plot.setDataset(newPlotIndex, new JpowderXYDataset(data.elementAt(i)));
                plot.setRenderer(newPlotIndex, renderer3);
            } else {
                plot.setDataset(newPlotIndex, new JpowderInternvalXYDataset((DataSetWithErrors) data.elementAt(i)));
                plot.setRenderer(newPlotIndex, renderer4);
            }
        }

        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

    }
}
