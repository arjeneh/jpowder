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
 */
public class FilesPlotter extends DatasetPlotter {

  private Vector<DataSet> datasets;

  public FilesPlotter(Vector<DataSet> d) {
    super(d);
    this.datasets = d;
    System.out.println("MultiFilesPlotter is called ");
  }

  public FilesPlotter(DataSet d) {
    super(d);
    datasets = new Vector<DataSet>();
    this.datasets.addElement(d);
    System.out.println("MultiFilesPlotter is called ");
  }

  public String description() {
    return "Multiple Files Plotter";
  }

  @Override
  public ChartPanel createPowderChart() {

    XYSeriesCollection XY_type_datasets = createDataset1();
    YIntervalSeriesCollection XYE_type_datasets = createDataset2();

    // get chart
    JFreeChart chart = createChart(XY_type_datasets, XYE_type_datasets);

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
   *
   * @return A JFreeChart
   */
  private JFreeChart createChart(XYSeriesCollection XY_dataSets, YIntervalSeriesCollection XYE_datasets) {
    NumberAxis xAxis = new NumberAxis("X");
    NumberAxis yAxis = new NumberAxis("Y");
    // get a reference to the plot for further customisation...
    XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer();
    renderer1.setBaseLinesVisible(true);
    renderer1.setBaseShapesVisible(false);//responsible for turning the marker off/on

    //Displaying the X&Y in Tooltip
    XYToolTipGenerator tooltip = new StandardXYToolTipGenerator(
            "{1},{2}", new DecimalFormat("0.000"), new DecimalFormat("0.000"));
    renderer1.setToolTipGenerator(tooltip);

    XYPlot plot = new XYPlot(XY_dataSets, xAxis, yAxis, renderer1);
    plot.setBackgroundPaint(Color.lightGray);
    plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
    plot.setDomainGridlinePaint(Color.white);
    plot.setRangeGridlinePaint(Color.white);

    // change the auto tick unit selection to integer units only...
    NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

    XYErrorRenderer renderer2 = new XYErrorRenderer();
    renderer2.setBaseLinesVisible(true);
    renderer2.setBaseShapesVisible(false);//responsible for turning the marker off/on
    renderer2.setToolTipGenerator(tooltip);

    plot.setDataset(1, XYE_datasets);
    plot.setRenderer(1, renderer2);

    plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
    //   plot.setRenderer(1,(XYItemRenderer) data2);
    JFreeChart chart = new JFreeChart(plot);//"Chart: " + this.d.getFileName() for getting the chart header
    chart.setBackgroundPaint(Color.white);

    return chart;
  }

  /**
   * Create collection which contains a XYSeries container for each DataSetNoErrors
   *
   * @return collection
   */
private XYSeriesCollection createDataset1() {

    XYSeriesCollection datasetCol = new XYSeriesCollection();

    for (int i = 0; i < datasets.size(); i++) {
      if (datasets.elementAt(i) instanceof DataSetNoErrors) {

        XYSeries series = new XYSeries(datasets.elementAt(i).getFileName());

        Vector<Double> x = datasets.elementAt(i).getX();
        Vector<Double> y = datasets.elementAt(i).getY();
        
        for (int rowIndex = 0; rowIndex < x.size(); rowIndex++) {
          series.add(x.elementAt(rowIndex), y.elementAt(rowIndex));
        }//for
        datasetCol.addSeries(series);
      }//if
    }//for
    return datasetCol;
  }

  /**
   * Create collection which contains a YIntervalSeries container for each
   * DataSetWithErrors
   *
   * @return collection
   */
  private YIntervalSeriesCollection createDataset2() {
    //IntervalXYDataset is an interface.
    YIntervalSeriesCollection datasetCol = new YIntervalSeriesCollection();
    for (int i = 0; i < datasets.size(); i++) {
      if (datasets.elementAt(i) instanceof DataSetWithErrors) {
        YIntervalSeries s1 = new YIntervalSeries(datasets.elementAt(i).getFileName());
        DataSetWithErrors xye = (DataSetWithErrors) datasets.elementAt(i);
        Vector<Double> x = xye.getX();
        Vector<Double> y = xye.getY();
        Vector<Double> minusY = xye.getYLower();
        Vector<Double> addY = xye.getYUpper();
        for (int rowIndex = 0; rowIndex < x.size(); rowIndex++) {
          s1.add(x.elementAt(rowIndex), y.elementAt(rowIndex),
                  minusY.elementAt(rowIndex), addY.elementAt(rowIndex));
        }//for
        datasetCol.addSeries(s1);
      }//if
    }// for
    return datasetCol;
  }
}

