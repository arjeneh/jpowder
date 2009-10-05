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
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYErrorRenderer;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.YIntervalSeries;
import org.jfree.data.xy.YIntervalSeriesCollection;
//
import org.jpowder.dataset.jfreechart.XYE_PopupMenu;

/**
 *
 * @Class name:  ThreeColumnsPlotter.java
 * @Author: Kreecha Puphaiboon
 * @Date: 21 July 2008, 19:20
 * @Modf:
 *
 * @Description:
 * A plotter to plot a dataset with Three columns type.
 *
 * @see DatasetPlotter
 *
 * @Return:
 */

public class ThreeColumnsPlotter extends DatasetPlotter {

    private DataSet d;

    /*  xye = new XYE(data, fileName);
        DatasetPlotter plot3Col = xye.createDatasetPlotter();
        powderChartPanel.add(plot3Col.createPowderChart());
     * */
    public ThreeColumnsPlotter(DataSet d) {
        super(d);
        this.d = d;
    }

    public String description() {
        return "Three Columns Plotter" +
                " data is " + this.d.description();
    }

    /**
     * Creates a chart with (x,y, -y, +y).
     * @param dataset  the dataset.
     * @return The chart.
     */
   private JFreeChart createChart(IntervalXYDataset dataset) {

    NumberAxis xAxis = new NumberAxis("X");
    NumberAxis yAxis = new NumberAxis("Y");

    XYErrorRenderer renderer = new XYErrorRenderer();
    renderer.setBaseLinesVisible(true);
    renderer.setBaseShapesVisible(false);//responsible for turning the marker off/on
    renderer.setDrawYError(true);
    //Displaying the X&Y in Tooltip
    XYToolTipGenerator tooltip = new StandardXYToolTipGenerator(
            "{1},{2}", new DecimalFormat("0.000"), new DecimalFormat("0.000"));
    renderer.setToolTipGenerator(tooltip);

    XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
    plot.setBackgroundPaint(Color.lightGray);
    plot.setDomainGridlinePaint(Color.white);
    plot.setRangeGridlinePaint(Color.white);
    plot.setDomainCrosshairVisible(true);

        JFreeChart XYE_Chart = new JFreeChart(plot);//"Chart: " + this.d.getFileName() for getting the chart header
        XYE_Chart.setBackgroundPaint(Color.white);

        return XYE_Chart;
    }

    private IntervalXYDataset createDataset() {

        //IntervalXYDataset is an interface.
        YIntervalSeriesCollection lDataset = new YIntervalSeriesCollection();

        YIntervalSeries s1 = new YIntervalSeries("Dataset:" + this.d.getFileName()); 
        //(x,y, -y, +y)

        DataSetWithErrors xye = (DataSetWithErrors) this.d;
        Vector x = xye.getX();
        Vector y = xye.getY();
        Vector minusY = xye.getYLower();
        Vector addY = xye.getYUpper();

        //System.out.println("addY is " + addY.size());
        //System.out.println("minusY is " + minusY.size());
        //System.out.println("y is " + y.size());
        //System.out.println("x is " + x.size());

        for (int rowIndex = 0; rowIndex < x.size(); rowIndex++) {
            s1.add(Double.parseDouble(x.elementAt(rowIndex).toString()),
                    Double.parseDouble(y.elementAt(rowIndex).toString()),
                    Double.parseDouble(minusY.elementAt(rowIndex).toString()),
                    Double.parseDouble(addY.elementAt(rowIndex).toString())
                    );
        }

        //Add data to dataset
        lDataset.addSeries(s1);

        return lDataset;
    }

    @Override
    public ChartPanel createPowderChart() {

        JFreeChart chart = createChart(createDataset());

        ChartPanel chartPanel = new ChartPanel(chart, true);
        //chartPanel.setMaximumSize(new java.awt.Dimension(500, 270));
        chartPanel.setDisplayToolTips(false);
        chartPanel.getChartRenderingInfo().setEntityCollection(null);
        //User clicks and popup a dialog.
        chartPanel.add(new XYE_PopupMenu(chartPanel));

        //User click and it brings up a new Frame for editing the chart.
        chartPanel.addChartMouseListener(new PowderChartMouseObserver(chartPanel));

        return chartPanel;
    }
}

