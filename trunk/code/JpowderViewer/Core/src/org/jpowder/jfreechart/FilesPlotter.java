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
    public XYPlot plot;
    private static boolean createLegend = false;
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
        System.out.println("MultiFilesPlotter is called ");
    }

    /**
     *
     * @param d
     */
    public FilesPlotter(DataSet d) {
        super(d);
        datasets = new Vector<DataSet>();
        FilesPlotter.datasets.addElement(d);
        System.out.println("MultiFilesPlotter is called ");
    }

    public static Paint getSeriesColors(int i) {

        return allSeriescolors[i % allSeriescolors.length];
    }

    public String description() {
        return "Multiple Files Plotter";
    }

    public boolean getLegend() {
        return createLegend;
    }

    public static void setLegend(boolean displayLegend) {
        createLegend = displayLegend;
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
        chartPanel.add(new JpowderPopupMenu(chartPanel));
        //User click and it brings up a new Frame for editing the chart.
        chartPanel.addChartMouseListener(new PowderChartMouseObserver(chartPanel));
        return chartPanel;
    }

    public static JFreeChart getchart() {
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
        NumberAxis yAxis = new NumberAxis("Y");
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
        System.out.println("the series count" + plot.getSeriesCount());
        for (int i = 1; i < datasets.size(); i++) {
            JpowderXYLineAndShapeRender renderer3 = new JpowderXYLineAndShapeRender();
            renderer3.setSeriesPaint(0, getSeriesColors(i));
            JpowderXYErrorRender renderer4 = new JpowderXYErrorRender();
            renderer4.setSeriesPaint(0, getSeriesColors(i));
            NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
            rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

            if (datasets.elementAt(i) instanceof DataSetNoErrors) {

                plot.setDataset(i, new JpowderXYDataset(datasets.elementAt(i)));

                plot.setRenderer(i, renderer3);
            } else {

                plot.setDataset(i, new JpowderInternvalXYDataset((DataSetWithErrors) datasets.elementAt(i)));
                plot.setRenderer(i, renderer4);
            }
        }
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        chart = new JFreeChart(null, null, plot, getLegend());// for getting the chart header
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
