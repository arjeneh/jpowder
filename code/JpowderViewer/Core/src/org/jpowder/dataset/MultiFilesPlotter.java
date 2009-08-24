package org.jpowder.dataset;

import org.jpowder.dataset.jfreechart.PowderChartMouseObserver;
import java.awt.Color;
import java.util.Vector;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.YIntervalSeries;
import org.jfree.ui.RectangleInsets;

/*
DataSet xye = new XY_XYE(lData, fileName);
DatasetPlotter plot3Col = xye.createDatasetPlotter();
powderChartPanel.add(plot3Col.createPowderChart());
 */
import org.jpowder.dataset.jfreechart.XY_PopupMenu;
//

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
        //
        XYDataset JFreeChart_datasets = createDataset();

        System.out.println("createPowderChart() in MultiFilesPlotter.java is called ");

        JFreeChart chart = createChart(JFreeChart_datasets);

        ChartPanel chartPanel = new ChartPanel(chart, true);
        chartPanel.setMaximumSize(new java.awt.Dimension(500, 270));
        chartPanel.setDisplayToolTips(false);
        chartPanel.getChartRenderingInfo().setEntityCollection(null);
        //User clicks and popup a dialog.
        chartPanel.add(new XY_PopupMenu(chartPanel));

        //User click and it brings up a new Frame for editing the chart.
        chartPanel.addChartMouseListener(new PowderChartMouseObserver(chartPanel));

        return chartPanel;
    }

    /**
     * Creates the chart with multiple datasets.
     * 
     * @return The chart.
     */
    private JFreeChart createChart(XYDataset dataSet) {
  

        // create the chart...
        JFreeChart chart = ChartFactory.createXYLineChart(
                "" ,//this.d.getFileName(), // chart title
                "X", // x axis label
                "Y", // y axis label
                dataSet, // data
                PlotOrientation.VERTICAL,
                true, // include legend
                false, // tooltips
                false // urls
                );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

        

        chart.setBackgroundPaint(Color.white);
        chart.addSubtitle(new TextTitle("")); ///this.d.getFileName()));

        //add the copyright.
        /*
        TextTitle source = new TextTitle("Created by Kreecha Puphaiboon and Anders Markvardsen");
        source.setFont(new Font("SansSerif", Font.PLAIN, 10));
        source.setPosition(RectangleEdge.BOTTOM);
        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(source);*/

        // get a reference to the plot for further customisation...
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setBaseLinesVisible(true);
        renderer.setBaseShapesVisible(false);//responsible for turning the marker off/on

        // change the auto tick unit selection to integer units only...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        return chart;
    }

    /**
     * 
     * @param dataVec  dataVec.
     * @param name  the file name list came as [pd_0010.xy, SNBL_zeolite_VPI-9.xye]
     *
     * @return The dataset.
     */
  private XYDataset createDataset() {

    XYSeriesCollection datasetCol = new XYSeriesCollection();

    for (int f = 0; f < datasets.size(); f++) {
      DataSet lDataset = datasets.elementAt(f);

      //new file
      XYSeries series = new XYSeries(lDataset.getFileName());

      Vector x = lDataset.getX();
      Vector y = lDataset.getY();

      //if (lDataset instanceof XY) {
        for (int rowIndex = 0; rowIndex < x.size(); rowIndex++) {
          series.add(
                  Double.parseDouble(x.elementAt(rowIndex).toString()),
                  Double.parseDouble(y.elementAt(rowIndex).toString()));
        }
        datasetCol.addSeries(series);
      //}
      /*} else if (lDataset instanceof XYE) {
        YIntervalSeries s1 = new YIntervalSeries(lDataset.getFileName());

        XYE xye = (XYE) lDataset;

        Vector minusY = xye.getYLower();
        Vector addY = xye.getYUpper();

        for (int rowIndex = 0; rowIndex < x.size(); rowIndex++) {
          s1.add(Double.parseDouble(x.elementAt(rowIndex).toString()),
                  Double.parseDouble(y.elementAt(rowIndex).toString()),
                  Double.parseDouble(minusY.elementAt(rowIndex).toString()),
                  Double.parseDouble(addY.elementAt(rowIndex).toString()));
        }
        datasetCol.addSeries(s1);
      }*/
       //else {
        // do a window not recognised
       // }

      }//outer for

      return datasetCol;

    }//createDataset
}//class

