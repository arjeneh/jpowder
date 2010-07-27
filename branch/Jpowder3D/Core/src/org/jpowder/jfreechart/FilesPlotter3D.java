/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.jfreechart;

import java.awt.Color;
import java.util.Collections;
import java.util.Vector;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.GrayPaintScale;
import org.jfree.chart.renderer.PaintScale;
import org.jfree.chart.renderer.xy.XYBlockRenderer;
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
 *
 * @author qyt21516
 */
public class FilesPlotter3D extends DatasetPlotter {

    private static Vector<DataSet> datasets;
    private static JFreeChart chart;
    private static XYPlot plot;

    public FilesPlotter3D(Vector<DataSet> d, String meta) {
        super(d);
        FilesPlotter3D.datasets = d;
    }

    public FilesPlotter3D(DataSet d) {
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
        return chartPanel;
    }

    public JFreeChart createChart(XYDataset dataset) {

        NumberAxis xAxis = new NumberAxis("X");
        xAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        xAxis.setLowerMargin(0.0);
        xAxis.setUpperMargin(0.0);

        NumberAxis yAxis = new NumberAxis("Y");
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        yAxis.setLowerMargin(0.0);
        yAxis.setUpperMargin(0.0);

        NumberAxis zAxis = new NumberAxis("");

        XYBlockRenderer renderer = new XYBlockRenderer();


//        r.setBlockHeight(1.0f);
//        r.setBlockWidth(1.0f);
        plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        chart.removeLegend();
        double maxY = 0;
        double minY = 0;

        for (int i = 0; i < plot.getDatasetCount(); i++) {
            maxY = (Double) Collections.max(datasets.elementAt(i).getY());
            minY = (Double) Collections.min(datasets.elementAt(i).getY());
//            System.out.println("max y" + maxY);
        }

        PaintScale lps = new GrayPaintScale(minY, maxY);


        renderer.setPaintScale(lps);
//        renderer.setBlockHeight(10);
        renderer.setBlockAnchor(RectangleAnchor.BOTTOM);
        PaintScaleLegend legend = new PaintScaleLegend(lps,
                zAxis);
//        legend.setAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        legend.setMargin(new RectangleInsets(0, 0, 5, 0));
        legend.setPadding(new RectangleInsets(0, 40, 0, 10));
        legend.setStripWidth(10);
        legend.setPosition(RectangleEdge.BOTTOM);
//        legend.setBackgroundPaint(Color.WHITE);
        chart.addSubtitle(legend);
        chart.setBackgroundPaint(Color.white);

        return chart;
    }

    public static XYZDataset createDataset() {


        DefaultXYZDataset defaultXYZDataset = new DefaultXYZDataset();
        //int xSize = datasets.elementAt(i).size();
        for (int i = 0; i < datasets.size(); i++) {
//            plot = new XYPlot(new JpowderXYZDataset(datasets.elementAt(i)), xAxis, yAxis, renderer);
            //XYDataset ds = plot.getDataset(i);
            double[][] data = new double[3][datasets.elementAt(i).getX().size()];
            for (int j = 0; j < datasets.elementAt(i).getX().size(); j++) {

                data[0][j] = (Double) datasets.elementAt(i).getX().get(j);//x
//                data[1][10] = Jpowder_Reader.getLocalData().get(i).get(1);//x reading from Jpowder File
                data[1][j] = i;//x by file number..
                data[2][j] = (Double) datasets.elementAt(i).getY().get(j);//Colour

            }
            defaultXYZDataset.addSeries("Serie " + i, data);
        }


        return defaultXYZDataset;
    }
}
