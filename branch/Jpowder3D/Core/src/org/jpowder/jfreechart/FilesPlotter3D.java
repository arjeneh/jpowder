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
 * Author(s):  M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *             Anders Marvardsen, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.jfreechart;

import java.awt.Color;
import java.awt.GradientPaint;
import java.util.Collections;
import java.util.HashMap;
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
import org.jpowder.util.HashMapHelper;
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
    private static HashMap fileNames;

    /**
     * @param d
     * @param meta
     */
    public FilesPlotter3D(Vector<DataSet> d, String meta) {
        super(d);
        FilesPlotter3D.datasets = d;
        selectedMetaItem = meta;
    }

        /**
     * @param d
     * @param meta
     *  @param files to display file names on the Y axis for 3D image.
     */
   
    public FilesPlotter3D(Vector<DataSet> d, String meta, HashMap files) {
        super(d);
        FilesPlotter3D.datasets = d;
        selectedMetaItem = meta;
        fileNames = files;
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
        //this.fileNames = fileNames;
    }

    @Override
    public String description() {
        return "";
    }

    @Override
    public ChartPanel createPowderChart() {
        chart = createChart(createDataset());

        plot.setDomainPannable(true);
        plot.setRangePannable(true);

        for (int i = 0; i < plot.getDatasetCount(); i++) {
            if (datasets.get(i).getFileName().endsWith("gss")) {
                plot.getDomainAxis().setLabel("TOF");
            }
        }

        ChartPanel chartPanel = new ChartPanel(chart, true);

        chartPanel.setDisplayToolTips(false);
        chartPanel.getChartRenderingInfo().setEntityCollection(null);
        chartPanel.addChartMouseListener(new PowderChartMouseObserver(chartPanel));
        chartPanel.restoreAutoBounds();
        return chartPanel;
    }

    // TODO: 1) He may use wrong dataset, it should have been XYZDataset???
    // TODO: 2) where is his Z value???
    /**
     * creating chart
     * @param dataset
     * @return
     */
    public JFreeChart createChart(XYDataset dataset) {

        NumberAxis xAxis = new NumberAxis("2\u0398");
        //xAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        xAxis.setLowerMargin(0.0);
        xAxis.setUpperMargin(0.0);
        xAxis.setAutoRangeIncludesZero(false);

        //display file names on the Y axis instead of 0..n.
        ValueAxis yAxis = new SymbolAxis("", HashMapHelper.convertKeyToArray(fileNames));
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        yAxis.setLowerMargin(0.0);
        yAxis.setUpperMargin(0.0);
        //yAxis.setAutoRange(true); --does not work

        NumberAxis zAxis = new NumberAxis("");
        //below could be another possible error of JpowderXYBlockRenderer.
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
        if ( dataset.getSeriesCount() >= 1 )
        {
            double width1stDataPoint = dataset.getXValue(0, 1)-dataset.getXValue(0, 0);
            renderer.setBlockWidth(width1stDataPoint);
        }

        renderer.clearSeriesPaints(true);

        // my trial on 17/03/2012
        plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        // my trial on 17/03/2012
        
        chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, plot, false);
        double maxY = 0;
        double minY = 0;

        for (int i = 0; i < plot.getDatasetCount(); i++) {
            maxY = (Double) Collections.max(datasets.elementAt(i).getY());
            minY = (Double) Collections.min(datasets.elementAt(i).getY());
            // System.out.println("max y" + maxY);
        }

        GrayPaintScale lps = new GrayPaintScale(minY, maxY);
        renderer.setPaintScale(lps);

        Vector<Double> widthsLow = new Vector<Double>();
        Vector<Double> widthsUpper = new Vector<Double>();
        for (int i = 0; i < datasets.size() - 1; i++) {
            widthsUpper.add(datasets.get(i + 1).getMetaData(selectedMetaItem) - datasets.get(i).getMetaData(selectedMetaItem));
            widthsLow.add(0.0);
        }

        if (datasets.size() - 1 > 0) {
            widthsUpper.add(widthsUpper.lastElement());
        } else {
            widthsUpper.add(1.0);
        }

        widthsLow.add(0.0);
        renderer.setBlockHeight(widthsLow, widthsUpper);
        //change zAxis to yAxis, but it shows on the block.
        PaintScaleLegend legend = new PaintScaleLegend(lps, zAxis);
        ////legend.setAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        legend.setMargin(new RectangleInsets(0, 0, 5, 0));
        legend.setPadding(new RectangleInsets(0, 40, 0, 10));
        legend.setPadding(new RectangleInsets(0, 40, 0, 10));

        legend.setStripWidth(10);
        legend.setPosition(RectangleEdge.RIGHT);
        chart.addSubtitle(legend);

//      legend.setFrame(new BlockBorder(Color.red));
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

        for (int i = 0; i < datasets.size(); i++) {

            double[][] data = new double[3][datasets.elementAt(i).getX().size()];
            for (int j = 0; j < datasets.elementAt(i).getX().size(); j++) {

                data[0][j] = (Double) datasets.elementAt(i).getX().get(j);//x
                data[1][j] = datasets.get(i).getMetaData(selectedMetaItem);//x by file number..
                data[2][j] = (Double) datasets.elementAt(i).getY().get(j);//Colour

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
        ValueAxis yAxis = new SymbolAxis("Symbol", HashMapHelper.convertKeyToArray(fileNames));
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

        GrayPaintScale lps = new GrayPaintScale(minY, maxY);
        renderer.setPaintScale(lps);

        //renderer.setBlockAnchor(RectangleAnchor.BOTTOM);

        Vector<Double> widthsLow = new Vector<Double>();
        Vector<Double> widthsUpper = new Vector<Double>();
        for (int i = 0; i < datasets.size() - 1; i++) {
            widthsUpper.add(datasets.get(i + 1).getMetaData(selectedMetaItem) - datasets.get(i).getMetaData(selectedMetaItem));
            widthsLow.add(0.0);
        }

        if (datasets.size() - 1 > 0) {
            widthsUpper.add(widthsUpper.lastElement());
        } else {
            widthsUpper.add(1.0);
        }

        widthsLow.add(0.0);
        renderer.setBlockHeight(widthsLow, widthsUpper);
        PaintScaleLegend legend = new PaintScaleLegend(lps,
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
}
