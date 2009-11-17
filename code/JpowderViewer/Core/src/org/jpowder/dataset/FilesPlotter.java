package org.jpowder.dataset;

import org.jpowder.dataset.jfreechart.PowderChartMouseObserver;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.Vector;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYErrorRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.YIntervalSeries;
import org.jfree.data.xy.YIntervalSeriesCollection;
import org.jfree.ui.RectangleInsets;
import org.jpowder.dataset.jfreechart.XYE_PopupMenu;

/**
 * Implements DatasetPlotter interface. Creates chart container data from
 * one or more powder diffraction files.
 *
 */
public class FilesPlotter extends DatasetPlotter {

  private Vector<DataSet> datasets;
  private int datasetIndex = 0;
  private XYPlot plot;

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
  public JFreeChart createChart() {
    NumberAxis xAxis = new NumberAxis("X");
    NumberAxis yAxis = new NumberAxis("Y");
    // get a reference to the plot for further customisation...
    XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer();
    XYErrorRenderer renderer2 = new XYErrorRenderer();


    renderer1.setBaseLinesVisible(true);//for turning the line on and off
    renderer1.setBaseShapesVisible(false);//responsible for turning the marker off/on
    renderer2.setBaseLinesVisible(true);
    renderer2.setBaseShapesVisible(false);//responsible for turning the marker off/on

    //Displaying the X&Y in Tooltip
    XYToolTipGenerator tooltip = new StandardXYToolTipGenerator(
            "{1},{2}", new DecimalFormat("0.000"), new DecimalFormat("0.000"));
    renderer1.setToolTipGenerator(tooltip);
    renderer2.setToolTipGenerator(tooltip);


    if (datasets.elementAt(0) instanceof DataSetNoErrors) {
      plot = new XYPlot(createXYSeriesCollectionFromDataset(datasets.elementAt(0)),
              xAxis, yAxis, renderer1);
    } else {
      plot = new XYPlot(createYIntervalSeriesCollectionFromDataset(datasets.elementAt(0)),
              xAxis, yAxis, renderer2);
    }

    plot.setBackgroundPaint(Color.lightGray);
    plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
    plot.setDomainGridlinePaint(Color.white);
    plot.setRangeGridlinePaint(Color.white);
    for (int i = 1; i < datasets.size(); i++) {
      XYLineAndShapeRenderer renderer3 = new XYLineAndShapeRenderer();
      XYErrorRenderer renderer4 = new XYErrorRenderer();
      renderer3.setBaseLinesVisible(true);//for turning the line on and off
      renderer3.setBaseShapesVisible(false);//responsible for turning the marker off/on
      renderer4.setBaseLinesVisible(true);
      renderer4.setBaseShapesVisible(false);//responsible for turning the marker off/on
      // change the auto tick unit selection to integer units only...
      NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
      rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
      //XYErrorRenderer renderer2 = new XYErrorRenderer();
      if (datasets.elementAt(i) instanceof DataSetNoErrors) {
        plot.setDataset(i, createXYSeriesCollectionFromDataset(datasets.elementAt(i)));
        plot.setRenderer(i, renderer3);
      } else {
        plot.setDataset(i, createYIntervalSeriesCollectionFromDataset(datasets.elementAt(i)));
        plot.setRenderer(i, renderer4);
      }

    }
    plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
    JFreeChart chart = new JFreeChart(plot);//"Chart: " + this.d.getFileName() for getting the chart header

    chart.setBackgroundPaint(Color.white);
    return chart;
  }
  /**
   * Create collection which contains a XYSeriesCollection container for each
   * DataSetNoErrors
   * @param dataset
   * @return datasetCol
   */
  private XYSeriesCollection createXYSeriesCollectionFromDataset(DataSet dataset) {
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
  private YIntervalSeriesCollection createYIntervalSeriesCollectionFromDataset(DataSet dataset) {
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

  public int getSeriesCount() {
    System.out.println("get the series count" + getSeriesCount());
    return 1;
  }
}

