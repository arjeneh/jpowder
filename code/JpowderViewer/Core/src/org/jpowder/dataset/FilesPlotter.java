package org.jpowder.dataset;

import java.awt.Color;
import java.awt.Paint;
import java.text.DecimalFormat;
import java.util.Vector;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.YIntervalSeries;
import org.jfree.data.xy.YIntervalSeriesCollection;
import org.jfree.ui.RectangleInsets;
import org.jpowder.dataset.jfreechart.PowderChartMouseObserver;
import org.jpowder.dataset.jfreechart.XYE_PopupMenu;
import org.jpowder.dataset.jfreechart.XYErrorRender;
import org.jpowder.dataset.jfreechart.XYLineAndShapeRender;




/**
 * Implements DatasetPlotter interface. Creates chart container data from
 * one or more powder diffraction files.
 *
 */
public class FilesPlotter extends DatasetPlotter {

  private static Vector<DataSet> datasets;
  private int datasetIndex = 0;
  public XYPlot plot;
  public  static Paint[] allseriescolors={Color.BLUE, Color.RED, Color.GREEN,
                Color.ORANGE, Color.CYAN,Color.MAGENTA,Color.YELLOW, Color.BLACK,
                Color.PINK,Color.WHITE,Color.LIGHT_GRAY,Color.GRAY};

  /**
   *
   * @param d
   */
  public FilesPlotter(Vector<DataSet> d) {
    super(d);
    this.datasets = d;
    System.out.println("MultiFilesPlotter is called ");
  }

  /**
   *
   * @param d
   */
  public FilesPlotter(DataSet d) {
    super(d);
    datasets = new Vector<DataSet>();
    this.datasets.addElement(d);
    System.out.println("MultiFilesPlotter is called ");
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
    JFreeChart chart = createChart();
    // create panel from chart and set some panel attributes
    ChartPanel chartPanel = new ChartPanel(chart, true);
    chartPanel.setMaximumSize(new java.awt.Dimension(300, 300));
    chartPanel.setDisplayToolTips(false);
    chartPanel.getChartRenderingInfo().setEntityCollection(null);
    chartPanel.add(new XYE_PopupMenu(chartPanel));
    //User click and it brings up a new Frame for editing the chart.
    chartPanel.addChartMouseListener(new PowderChartMouseObserver(chartPanel));
    return chartPanel;
  }

  /**
   * Creates the chart containing data from one or more powder diffraction files
   * @return chart
   */
  @SuppressWarnings("static-access")
  public JFreeChart createChart() {
    String x = "2\u03D1";//unicode
    NumberAxis xAxis = new NumberAxis(x.toUpperCase());
    NumberAxis yAxis = new NumberAxis("Y");

    // get a reference to the plot for further customisation...
    XYLineAndShapeRender renderer1 = new XYLineAndShapeRender();
    renderer1.setSeriesPaint(0, allseriescolors[0]);
     XYErrorRender renderer2 = new XYErrorRender();
     renderer2.setSeriesPaint(0, allseriescolors[0]);
    //Displaying the X&Y in Tooltip
    XYToolTipGenerator tooltip = new StandardXYToolTipGenerator(
            "{1},{2}", new DecimalFormat("0.000"), new DecimalFormat("0.000"));
    if (datasets.elementAt(0) instanceof DataSetNoErrors) {
      plot = new XYPlot(createXYSeriesCollectionFromDataset(datasets.elementAt(0)),
              xAxis, yAxis, renderer1);

    } else {
     
      plot = new XYPlot(createYIntervalSeriesCollectionFromDataset(datasets.elementAt(0)),
              xAxis, yAxis, renderer2);
      renderer2.setToolTipGenerator(tooltip);
    }
    plot.setBackgroundPaint(Color.lightGray);
    plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
    plot.setDomainGridlinePaint(Color.white);
    plot.setRangeGridlinePaint(Color.white);
    System.out.println("the series count" + plot.getSeriesCount());
    for (int i = 1; i < datasets.size(); i++) {
      XYLineAndShapeRender renderer3 = new XYLineAndShapeRender();
      renderer3.setSeriesPaint(0,  allseriescolors[i]);
      XYErrorRender renderer4 = new XYErrorRender();
      renderer4.setSeriesPaint(0,  allseriescolors[i]);
      NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
      rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

      if (datasets.elementAt(i) instanceof DataSetNoErrors) {
        plot.setDataset(i, createXYSeriesCollectionFromDataset(datasets.elementAt(i)));
        plot.setRenderer(i, renderer3);
      } else {
        plot.setDataset(i, createYIntervalSeriesCollectionFromDataset(datasets.elementAt(i)));
        plot.setRenderer(i, renderer4);
      }
    }
    plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
    plot.setFixedLegendItems(createLegendItems());//this for setting the legend null
    JFreeChart chart = new JFreeChart(plot);// for getting the chart header
    chart.setBackgroundPaint(Color.white);
    return chart;
  }

  /**
   * Create collection which contains a XYSeriesCollection container for each
   * DataSetNoErrors
   * @param dataset
   * @return datasetCol
   */
  public static XYSeriesCollection createXYSeriesCollectionFromDataset(DataSet dataset) {
    XYSeriesCollection datasetCol = new XYSeriesCollection();
    XYSeries series = new XYSeries(dataset.getFileName());
    Vector<Double> x = dataset.getX();
    Vector<Double> y = dataset.getY();
    for (int rowIndex = 0; rowIndex < x.size(); rowIndex++) {
      series.add(x.elementAt(rowIndex), y.elementAt(rowIndex));
    }//for
    datasetCol.addSeries(series);
    return datasetCol;
  }

  /**
   * Create collection which contains a YIntervalSeries container for each
   * DataSetWithErrors
   * @param dataset
   * @return datasetCol
   */
  public static YIntervalSeriesCollection createYIntervalSeriesCollectionFromDataset(DataSet dataset) {
    YIntervalSeriesCollection datasetCol = new YIntervalSeriesCollection();
    YIntervalSeries s1 = new YIntervalSeries(dataset.getFileName());
    DataSetWithErrors xye = (DataSetWithErrors) dataset;
    Vector<Double> x = xye.getX();
    Vector<Double> y = xye.getY();
    Vector<Double> minusY = xye.getYLower();
    Vector<Double> addY = xye.getYUpper();
    for (int rowIndex = 0; rowIndex < x.size(); rowIndex++) {
      s1.add(x.elementAt(rowIndex), y.elementAt(rowIndex),
              minusY.elementAt(rowIndex), addY.elementAt(rowIndex));
    }//for

    datasetCol.addSeries(s1);

    return datasetCol;
  }

  /**
   * for adding the dataset to the already existing plot.
   * @param plot
   * @param data
   */
  public static void addDataToJpowderInternalFrame(XYPlot plot, Vector<DataSet> data) {

    plot.setBackgroundPaint(Color.lightGray);
    plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
    plot.setDomainGridlinePaint(Color.white);
    plot.setRangeGridlinePaint(Color.white);
    for (int i = 0; i < data.size(); i++) {
 int newPlotIndex = plot.getDatasetCount();
      XYLineAndShapeRender renderer3 = new XYLineAndShapeRender();
      XYErrorRender renderer4 = new XYErrorRender();
      renderer3.setSeriesPaint(0, allseriescolors[plot.getDatasetCount()]);
      renderer4.setSeriesPaint(0, allseriescolors[plot.getDatasetCount()]);
      // change the auto tick unit selection to integer units only...
      NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
      rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

     
      //(data.size()+datasets.size());//Returns the number of datasets in the plot.
      if (data.elementAt(i) instanceof DataSetNoErrors) {
        plot.setDataset(newPlotIndex, FilesPlotter.createXYSeriesCollectionFromDataset(data.elementAt(i)));
        plot.setRenderer(newPlotIndex, renderer3);
      } else {
        plot.setDataset(newPlotIndex, FilesPlotter.createYIntervalSeriesCollectionFromDataset(data.elementAt(i)));
        plot.setRenderer(newPlotIndex, renderer4);
      }
      System.out.println("getDatasetCount()" + newPlotIndex);
      System.out.println("DatasetSize" + data.size() + datasets.size());
    }

    plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

  }

  public int newPlotIndex() {
    int index = plot.getSeriesCount();
    return index;
  }

  /**
   * A collection of legend items, typically returned by the getLegendItems() method in the plot classes.
   * You can create your own collection of legend items and pass it to a CategoryPlot or XYPlot via the
   * setFixedLegendItems() method, as a way of overriding the automatically generated legend items.
   * @return legendItemCollection
   */
  private LegendItemCollection createLegendItems() {
    LegendItemCollection legendItemCollection = new LegendItemCollection();
//    LegendItem item1 = new LegendItem("", Color.BLUE);
//    legendItemCollection.add(item1);
    return legendItemCollection;
  }
}
