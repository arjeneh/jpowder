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
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jpowder.dataset.DataSet;
import org.jpowder.dataset.DatasetPlotter;

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

    //to display file names on the Y axis for 3D image.
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

    public String[] convertKeyToArray(HashMap hm) {
        String[] keys =
                (String[]) (hm.keySet().toArray(new String[hm.size()]));
        return keys;
    }
    //

    /**
     * creating chart
     * @param dataset
     * @return
     */
    public JFreeChart createChart(XYDataset dataset) {

        NumberAxis xAxis = new NumberAxis("2\u0398");
        xAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        xAxis.setLowerMargin(0.0);
        xAxis.setUpperMargin(0.0);
        xAxis.setAutoRangeIncludesZero(false);

        //NumberAxis yAxis = new NumberAxis("Y");
        //yAxis.setLabel(selectedMetaItem);
        ValueAxis yAxis = new SymbolAxis("Symbol", this.convertKeyToArray(fileNames));
        //yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        yAxis.setLowerMargin(0.0);
        yAxis.setUpperMargin(0.0);

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
        //legend.setAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        legend.setMargin(new RectangleInsets(0, 0, 5, 0));
        legend.setPadding(new RectangleInsets(0, 40, 0, 10));

        legend.setStripWidth(10);
        legend.setPosition(RectangleEdge.BOTTOM);
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
        DefaultXYZDataset defaultXYZDataset = new DefaultXYZDataset();
        //int xSize = datasets.elementAt(i).size();
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
}
