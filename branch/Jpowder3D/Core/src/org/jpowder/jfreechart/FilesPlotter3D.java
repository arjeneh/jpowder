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
 * FilePlotter3D.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  Kreecha Puphaiboon, Computer Science Department, Kasem Bundit University
 *             M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *             Anders Marvardsen, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.jfreechart;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Collections;
import java.util.Vector;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.GrayPaintScale;
import org.jfree.chart.title.PaintScaleLegend;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jpowder.dataset.DataSet;
import org.jpowder.dataset.DatasetPlotter;
import org.jpowder.dataset.MetaData;
import org.jpowder.util.ProportionalDimension;
import org.jpowder.util.StringUtil;
import org.jpowder.util.VectorMiscUtil;

/**
 * Plotting the 3D data.
 *
 */
public class FilesPlotter3D extends DatasetPlotter {

    private static Vector<DataSet> datasets;
    private static JFreeChart chart;
    private static XYPlot plot;
    private static String selectedMetaItem;
    private static Vector<String> fileNames = new Vector<String>();
    private boolean isMetaNameEqualName = false;

    /**
     * @param d
     * @param meta
     */
    public FilesPlotter3D(Vector<DataSet> d, String meta) {
        super(d);
        FilesPlotter3D.datasets = d;
        selectedMetaItem = meta;
        System.out.println("in " + this.getClass().getName() + " DataSet is: " + datasets.toString());

        for (int i = 0; i < FilesPlotter3D.datasets.size(); i++) {
            fileNames.add(FilesPlotter3D.datasets.get(i).getFileName());
        }

        if (selectedMetaItem.equalsIgnoreCase("name")) {
            isMetaNameEqualName = true;
            System.out.println("Plot using Name metaData in the constructor()");
        }

        System.out.println("in " + this.getClass().getName() + " DataSet is: " + fileNames);
    }

    public FilesPlotter3D(DataSet d) {
        super(d);
        datasets = new Vector<DataSet>();
        FilesPlotter3D.datasets.addElement(d);
    }

    /*
    creating chart
    @param d is a dataset
    @param fileNames is list of file names to be added on the Y axis
     * */
    public FilesPlotter3D(DataSet d, Vector fileNames) {
        super(d);
        datasets = new Vector<DataSet>();
        FilesPlotter3D.datasets.addElement(d);
    }

    @Override
    public String description() {
        return "";
    }

    @Override
    public ChartPanel createPowderChart() {
        final double proportion;

        chart = createChart(createDataset());
        /* bad performance
        chart.setAntiAlias(true);
        chart.setNotify(true);
         * */

        chart.setAntiAlias(false);
        chart.setNotify(false);// to see it will stop rendering while stretched.

        for (int i = 0; i < plot.getDatasetCount(); i++) {
            if (datasets.get(i).getFileName().endsWith("gss")) {
                plot.getDomainAxis().setLabel("TOF");
            }
        }

        final ChartPanel chartPanel = new ChartPanel(chart, true);
        chartPanel.setName(StringUtil.getFileTitle(fileNames));
        chartPanel.setRefreshBuffer(true);

        chartPanel.setPreferredSize(new Dimension(640, 480));
        chartPanel.setDisplayToolTips(false);
        chartPanel.getChartRenderingInfo().setEntityCollection(null);
        chartPanel.addChartMouseListener(new PowderChartMouseObserver(chartPanel));

        chartPanel.setMinimumDrawWidth(0);
        chartPanel.setMinimumDrawHeight(0);
//        chartPanel.setMaximumDrawWidth(640);
//        chartPanel.setMaximumDrawHeight(480);

        //get the prefered size.
        Dimension d = chartPanel.getPreferredSize();
        double x = d.width;
        double y = d.height;

        //find the proportion factor.
        proportion = x / y;

        //Check to see the size changed and adjust to the right proportion.
        // TODO: still does not work.
        chartPanel.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                Component c = (Component) e.getSource();
                Dimension curD = c.getSize();

                Dimension newSize = new ProportionalDimension(curD, proportion);
                c.setSize(newSize);
                System.out.println("In " + this.getClass().getName() +
                        " new size is: " + newSize + " proportion is: " + proportion);
                super.componentResized(e);
            }
        });
        return chartPanel;
    }

    /**
     * creating chart
     * @param dataset
     * @return
     */
    public JFreeChart createChart(XYDataset dataset) {

        // Setup the values which will determine the block height values
        // These will be based on the meta data values and how the blocks
        // is centered around each meta data value
        // first read in the meta data

        boolean isMetaDataString = false;
        int numDataset = datasets.size();
        //System.out.println("In FilePlotter3D Vector<DataSet> datasets has " + numDataset + " in it.");

        Vector<MetaData> metaValues = new Vector<MetaData>();

        for (int i = 0; i < numDataset; i++) {
            metaValues.add(datasets.get(i).getMetaData(selectedMetaItem));
        }
        //KP - this can be in above logic.
        for (int i = 0; i < numDataset; i++) {
            if (metaValues.elementAt(i).getValue() instanceof String) {
                isMetaDataString = true;
            }
        }

        // set up the x-axis
        NumberAxis xAxis = new NumberAxis("2\u0398");
        xAxis.setLowerMargin(0.0);
        xAxis.setUpperMargin(0.0);
        xAxis.setAutoRangeIncludesZero(false);

        // set up the y-axis
        // Importantly setup the y-axis so that it matches the block heights
        // specified above
        final ValueAxis yAxis;

        // Create the lower and upper values for each dataset block height
        Vector<Double> blockHeigth_minus = new Vector<Double>();
        Vector<Double> blockHeigth_plus = new Vector<Double>();

        //if the metaname is equal Name. 21/04/2012
        if (isMetaDataString) {
            //display file names on the Y axis instead of 0..n.
            //yAxis = new SymbolAxis("", HashMapHelper.convertKeyToArray(fileNames));
            String[] strings = new String[numDataset];
            for (int i = 0; i < numDataset; i++) {
                strings[i] = metaValues.elementAt(i).getValue().toString();
            }

            yAxis = new SymbolAxis(selectedMetaItem, strings);
            // set width of each dataset, i.e. in fact it height (y-axis width)

            // here dataset block height selected such data each y-axis label
            // will be at the centre of a dataset block height
            for (int i = 0; i < numDataset; i++) {
                blockHeigth_minus.add(0.5);
                blockHeigth_plus.add(0.5);
            }

            yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
            yAxis.setRange(0 - blockHeigth_minus.firstElement(),
                    numDataset - 1 + blockHeigth_plus.lastElement());
            System.out.println("Plot using Name metaData");

        } else {
            //if the metaname is equal Number
            Vector<Double> metaDouble = new Vector<Double>();
            for (int i = 0; i < numDataset; i++) {
                metaDouble.add((Double) metaValues.elementAt(i).getValue());
            }

            if (numDataset == 1) {
                // for the special case of just one dataset we simply for now
                // just position the block +- 0.5 around the meta value
                blockHeigth_minus.add(0.5);
                blockHeigth_plus.add(0.5);
            } else {
                // aim to have meta data value centered somewhere near-ish to the centre of block
                // other suggestions welcome here
                blockHeigth_minus.add((metaDouble.elementAt(1) - metaDouble.elementAt(0)) / 2.0);
                blockHeigth_plus.add((metaDouble.elementAt(1) - metaDouble.elementAt(0)) / 2.0);
                for (int i = 1; i < numDataset - 1; i++) {
                    blockHeigth_minus.add((metaDouble.elementAt(i) - metaDouble.elementAt(i - 1)) / 2.0);
                    blockHeigth_plus.add((metaDouble.elementAt(i + 1) - metaDouble.elementAt(i)) / 2.0);
                }
                blockHeigth_minus.add((metaDouble.elementAt(numDataset - 1) - metaDouble.elementAt(numDataset - 2)) / 2.0);
                blockHeigth_plus.add((metaDouble.elementAt(numDataset - 1) - metaDouble.elementAt(numDataset - 2)) / 2.0);
            }//end numDataset is equal to 1

            yAxis = new NumberAxis(selectedMetaItem);
            // try to fix the actual y-axis range to the correct length, although
            // would autorange work - not sure - setting it manual you would think
            // it the safest option
            // the below for some change reason current has stopped actually
            // setting the values specified..... which may be due to autorange
            // being called somewhere else?

            yAxis.setRange(metaDouble.firstElement() - blockHeigth_minus.firstElement(),
                    metaDouble.lastElement() + blockHeigth_plus.lastElement());
        }//end if the metaname is equal Number

        // Setup the block renderer
        JpowderXYBlockRenderer renderer = new JpowderXYBlockRenderer();
        // If any data available, set for now, the block-width equal to the
        // spacing between the first to x-values of the first series in dataset.
        // When the x-values, as may typically be the case, are
        // equally spaced this will work, since it will mean that
        // the width of each block will exactly match the spacing between
        // data point and there will be no overlap between the blocks. Overlap
        // been blocks as we have seen causes side effects such as the lines
        // do to appear at the correct x-values
        // Note as I am writing this comment I realize me may have a more
        // serious problem - perhaps. The above will not work when the x-values
        // are not equally spaced in a dataset, and I am not sure how easy it
        // is to set different width for each data point, when the x-values
        // are not equally spaced. If the widths are too large we get the
        // side effect we have already seen. If the widths are too small we
        // will get funny looking plots with white areas between the data points
        if (dataset.getSeriesCount() >= 1) {
            double width1stDataPoint = dataset.getXValue(0, 1) - dataset.getXValue(0, 0);
            if (width1stDataPoint <= 0.0) {
                width1stDataPoint = 1.0;
            }
            renderer.setBlockWidth(width1stDataPoint * 0.1);
        }

        renderer.setBlockHeight(blockHeigth_minus, blockHeigth_plus);
        // don't know what this one is for?
        renderer.clearSeriesPaints(true);

        plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.getRangeAxis().setAutoRange(true); //-- comment out by KP 18/08/2012 to plot properly.

        chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, plot, false);


        // Find the min and max y value and use these values
        // to set the colour scale

        double maxY = 0;
        double minY = 0;
        for (int i = 0; i < plot.getDatasetCount(); i++) {
            maxY = (Double) Collections.max(datasets.elementAt(i).getY());
            minY = (Double) Collections.min(datasets.elementAt(i).getY());
        }

        GrayPaintScale colourScale = new GrayPaintScale(minY, maxY);
        renderer.setPaintScale(colourScale);

        // Setup and add colorbar to chart
        NumberAxis zAxis = new NumberAxis("");
        PaintScaleLegend legend = new PaintScaleLegend(colourScale, zAxis);
        //legend.setAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        legend.setMargin(new RectangleInsets(0, 0, 5, 0));
        legend.setPadding(new RectangleInsets(0, 40, 0, 10));
        legend.setPadding(new RectangleInsets(0, 40, 0, 10));
        legend.setStripWidth(10);
        legend.setPosition(RectangleEdge.RIGHT);
        chart.addSubtitle(legend);

        chart.setBackgroundPaint(new GradientPaint(0, 0, Color.white, 0, 1000, Color.BLACK, true));
        legend.setBackgroundPaint(chart.getBackgroundPaint());
        return chart;
    }

    public static JFreeChart getChart() {
        return chart;
    }

    /**
     *  Creating XYZDataset
     * @return XYZDataset
     */
    public static XYZDataset createDataset() {

        System.out.println(" ----------- XYZDataset createDataset() -------- ");

        DefaultXYZDataset defaultXYZDataset = new DefaultXYZDataset();

        boolean isMetaDataString = false;
        int numDataset = datasets.size();
        Vector<MetaData> metaValues = new Vector<MetaData>();
        for (int i = 0; i < numDataset; i++) {
            metaValues.add(datasets.get(i).getMetaData(selectedMetaItem));
        }
        for (int i = 0; i < numDataset; i++) {
            if (metaValues.elementAt(i).getValue() instanceof String) {
                isMetaDataString = true;
            }
        }

        for (int i = 0; i < datasets.size(); i++) {

            double[][] data = new double[3][datasets.elementAt(i).getX().size()];
            for (int j = 0; j < datasets.elementAt(i).getX().size(); j++) {

                data[0][j] = (Double) datasets.elementAt(i).getX().get(j);  // x
                MetaData meta = datasets.get(i).getMetaData(selectedMetaItem);
                if (isMetaDataString) {
                    data[1][j] = (double) i; // y (meta value, including possible file-number)
                } else {
                    data[1][j] = (Double) meta.getValue(); // y (meta value, including possible file-number)
                }
                data[2][j] = (Double) datasets.elementAt(i).getY().get(j);  // Colour

            }
            defaultXYZDataset.addSeries("Serie " + i, data);
        }

        return defaultXYZDataset;
    }

    public static void main(String[] args) {

        //add problematic 3d files to create datasets
        Vector files = VectorMiscUtil.loadFileData("C:\\Users\\Toshiba\\Desktop\\Test");

        Vector<DataSet> testDatasets = null;

        for (int i = 0, n = files.size(); i < n; i++) {
            testDatasets = VectorMiscUtil.createDataSetFromPowderFile((String) files.get(i));
        }
        //FilesPlotter3D

        //JFreeChart chart = createChart(XYDataset dataset)
        JFreeChart charter = createChart2(createDataset2());
        //JFreeChart charter = createChart2((XYDataset) files);

        ChartFrame frame = new ChartFrame("XYZ Test", charter);
        frame.pack();
        frame.setVisible(true);

    }

    public static JFreeChart createChart2(XYDataset dataset) {

        NumberAxis xAxis = new NumberAxis("2\u0398");
        xAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        xAxis.setLowerMargin(0.0);
        xAxis.setUpperMargin(0.0);
        xAxis.setAutoRangeIncludesZero(false);

        //NumberAxis yAxis = new NumberAxis("Y");
        //yAxis.setLabel(selectedMetaItem);
        //ValueAxis yAxis = new SymbolAxis("Symbol", HashMapHelper.convertKeyToArray(fileNames));
        ValueAxis yAxis = new SymbolAxis("Symbol", (String[]) fileNames.toArray());
        //yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        yAxis.setLowerMargin(0.0);
        yAxis.setUpperMargin(0.0);
        //yAxis.setAutoRange(true); --does not work

        NumberAxis zAxis = new NumberAxis("");
        JpowderXYBlockRenderer renderer = new JpowderXYBlockRenderer();
        renderer.clearSeriesPaints(true);

        plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, plot, false);
        double maxY = 0;
        double minY = 0;

        for (int i = 0; i < plot.getDatasetCount(); i++) {
            maxY = (Double) Collections.max(datasets.elementAt(i).getY());
            minY = (Double) Collections.min(datasets.elementAt(i).getY());
            // System.out.println("max y" + maxY);
        }

        GrayPaintScale colourScale = new GrayPaintScale(minY, maxY);
        renderer.setPaintScale(colourScale);

        //renderer.setBlockAnchor(RectangleAnchor.BOTTOM);

        Vector<Double> widthsLow = new Vector<Double>();
        Vector<Double> widthsUpper = new Vector<Double>();
        for (int i = 0; i < datasets.size() - 1; i++) {
            //widthsUpper.add(datasets.get(i + 1).getMetaData(selectedMetaItem) - datasets.get(i).getMetaData(selectedMetaItem));
            widthsLow.add(0.0);
        }

        if (datasets.size() - 1 > 0) {
            widthsUpper.add(widthsUpper.lastElement());
        } else {
            widthsUpper.add(1.0);
        }

        widthsLow.add(0.0);
        renderer.setBlockHeight(widthsLow, widthsUpper);
        PaintScaleLegend legend = new PaintScaleLegend(colourScale,
                zAxis);
        ////legend.setAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        legend.setMargin(new RectangleInsets(0, 0, 5, 0));
        legend.setPadding(new RectangleInsets(0, 40, 0, 10));
        legend.setPadding(new RectangleInsets(0, 40, 0, 10));

        legend.setStripWidth(10);
        legend.setPosition(RectangleEdge.BOTTOM);
        chart.addSubtitle(legend);

//      legend.setFrame(new BlockBorder(Color.red));
        chart.setBackgroundPaint(new GradientPaint(0, 0, Color.white, 0, 1000, Color.BLACK, true));
        legend.setBackgroundPaint(chart.getBackgroundPaint());
        return chart;
    }

    private static XYDataset createDataset2() {

        XYSeries series1 = new XYSeries("First");
        series1.add(1.0, 1.0);
        series1.add(2.0, 4.0);
        series1.add(3.0, 3.0);
        series1.add(4.0, 5.0);
        series1.add(5.0, 5.0);
        series1.add(6.0, 7.0);
        series1.add(7.0, 7.0);
        series1.add(8.0, 8.0);

        XYSeries series2 = new XYSeries("Second");
        series2.add(1.0, 5.0);
        series2.add(2.0, 7.0);
        series2.add(3.0, 6.0);
        series2.add(4.0, 8.0);
        series2.add(5.0, 4.0);
        series2.add(6.0, 4.0);
        series2.add(7.0, 2.0);
        series2.add(8.0, 1.0);

        XYSeries series3 = new XYSeries("Third");
        series3.add(3.0, 4.0);
        series3.add(4.0, 3.0);
        series3.add(5.0, 2.0);
        series3.add(6.0, 3.0);
        series3.add(7.0, 6.0);
        series3.add(8.0, 3.0);
        series3.add(9.0, 4.0);
        series3.add(10.0, 3.0);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);

        return dataset;

    }

    /**
     * refresh the chart.
     */
    private void refreshChart() {
//        jPanel_GraphicsTop.removeAll();
//        jPanel_GraphicsTop.revalidate(); // This removes the old chart
//        aChart = createChart();
//        aChart.removeLegend();
//
//        ChartPanel chartPanel = new ChartPanel(aChart);
//        jPanel_GraphicsTop.setLayout(new BorderLayout());
//        jPanel_GraphicsTop.add(chartPanel);
//        jPanel_GraphicsTop.repaint(); // This method makes the new chart appear
    }
}
