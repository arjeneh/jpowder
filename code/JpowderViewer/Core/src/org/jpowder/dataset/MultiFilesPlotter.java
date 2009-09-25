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
import org.jpowder.dataset.jfreechart.XY_PopupMenu;
/*
DataSet xye = new XY_XYE(lData, fileName);
DatasetPlotter plot3Col = xye.createDatasetPlotter();
powderChartPanel.add(plot3Col.createPowderChart());
 */

public class MultiFilesPlotter extends DatasetPlotter {

  private Vector<DataSet> datasets;

  public MultiFilesPlotter(Vector<DataSet> d) {
    super(d);
    this.datasets = d;
    System.out.println("MultiFilesPlotter is called ");
  }

  public String description() {
    return "Multiple Files Plotter";
  }

  @Override
  public ChartPanel createPowderChart() {

    XYSeriesCollection XY_type_datasets = createDataset1();
    YIntervalSeriesCollection XYE_type_datasets = createDataset2();

    JFreeChart chart = createChart(XY_type_datasets, XYE_type_datasets);

    ChartPanel chartPanel = new ChartPanel(chart, true);
    chartPanel.setMaximumSize(new java.awt.Dimension(500, 270));
    chartPanel.setDisplayToolTips(false);
    chartPanel.getChartRenderingInfo().setEntityCollection(null);
    chartPanel.add(new XYE_PopupMenu(chartPanel));
    //User click and it brings up a new Frame for editing the chart.
    chartPanel.addChartMouseListener(new PowderChartMouseObserver(chartPanel));
    return chartPanel;
  }

  /**
   * Creates the chart with multiple datasets.
   *
   * @return The chart.
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
   *
   * @param dataVec  dataVec.
   * @param name  the file name list came as [pd_0010.xy, SNBL_zeolite_VPI-9.xye]
   *
   * @return The dataset.
   */
  private XYSeriesCollection createDataset1() {

    XYSeriesCollection datasetCol = new XYSeriesCollection();

    for (int i = 0; i < datasets.size(); i++) {
      if (datasets.elementAt(i) instanceof DataSetNoErrors) {
        //new file
        XYSeries series = new XYSeries(datasets.elementAt(i).getFileName());

        Vector x = datasets.elementAt(i).getX();
        Vector y = datasets.elementAt(i).getY();
        //if (lDataset instanceof XY) {
        for (int rowIndex = 0; rowIndex < x.size(); rowIndex++) {
          series.add(
                  Double.parseDouble(x.elementAt(rowIndex).toString()),
                  Double.parseDouble(y.elementAt(rowIndex).toString()));
        }//for
        datasetCol.addSeries(series);
      }//if
    }//for
    return datasetCol;
  }//create dataset1

  private YIntervalSeriesCollection createDataset2() {
    //IntervalXYDataset is an interface.
    YIntervalSeriesCollection datasetCol = new YIntervalSeriesCollection();
    for (int i = 0; i < datasets.size(); i++) {
      if (datasets.elementAt(i) instanceof DataSetWithErrors) {
        YIntervalSeries s1 = new YIntervalSeries(datasets.elementAt(i).getFileName());
        DataSetWithErrors xye = (DataSetWithErrors) datasets.elementAt(i);
        Vector x = xye.getX();
        Vector y = xye.getY();
        Vector minusY = xye.getYLower();
        Vector addY = xye.getYUpper();
        for (int rowIndex = 0; rowIndex < x.size(); rowIndex++) {
          s1.add(Double.parseDouble(x.elementAt(rowIndex).toString()),
                  Double.parseDouble(y.elementAt(rowIndex).toString()),
                  Double.parseDouble(minusY.elementAt(rowIndex).toString()),
                  Double.parseDouble(addY.elementAt(rowIndex).toString()));
        }//for
        datasetCol.addSeries(s1);
      }//if
    }// for
    return datasetCol;
  }//createDataset2
}//class

