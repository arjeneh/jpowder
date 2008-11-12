package org.jpowder.dataset;

//
import java.awt.Color;
import java.awt.Font;
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
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
//
import org.jpowder.XY_PopupMenu;

public class TwoColumnsPlotter extends DatasetPlotter {

    private DataSet d;

    public TwoColumnsPlotter(DataSet d) {
        super(d);
        this.d = d;
    }

    public String description() {
        return "Two Columns Plotter" +
                " data is " + this.d.description();
    //this.d.description();
    }

    @Override
    public ChartPanel createPowderChart() {
        //throw new UnsupportedOperationException("Not supported yet.");
        JFreeChart chart = createChart(createDataset(this.d.getFileName()));

        ChartPanel chartPanel = new ChartPanel(chart, true);
        chartPanel.setMaximumSize(new java.awt.Dimension(500, 270));
        chartPanel.setDisplayToolTips(false);
        chartPanel.getChartRenderingInfo().setEntityCollection(null);
        //user clicks and popup a dialog.
        chartPanel.add(new XY_PopupMenu(chartPanel));

        //User click and it brings up a new Frame for editing the chart.
        chartPanel.addChartMouseListener(new PowderChartMouseObserver(chartPanel));

        return chartPanel;
    }

    /**
     * Creates a chart.
     * @param dataset  the data for the chart.
     * @return a chart.*/
    private JFreeChart createChart(XYDataset dataset) {

        // create the chart...
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Chart: " + this.d.getFileName(), // chart title
                "X", // x axis label
                "Y", // y axis label
                dataset, // data
                PlotOrientation.VERTICAL,
                true, // include legend
                false, // tooltips
                false // urls
                );


        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);
        chart.addSubtitle(new TextTitle("Data of " + this.d.getFileName()));

        //add the copyright.
        TextTitle source = new TextTitle("Created by Kreecha Puphaiboon and Anders Markvardsen");
        source.setFont(new Font("SansSerif", Font.PLAIN, 10));
        source.setPosition(RectangleEdge.BOTTOM);
        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(source);

        // get a reference to the plot for further customisation...
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setBaseLinesVisible(true);
        renderer.setBaseShapesVisible(true);

        // change the auto tick unit selection to integer units only...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
      
        return chart;
    }//end createPowderChart

    // Creates a sample dataset.
    // @param name  the name at the bottom of the chart.
    // @return a sample dataset.
    public XYDataset createDataset(String name) {
        String bottomTitle = name;
        
        XYSeries series1 = new XYSeries(bottomTitle);//the name at the bottom of the chart.

        XY xy = (XY) this.d;

        Vector x = xy.getX();
        Vector y = xy.getY();
        //
        for (int rowIndex = 0; rowIndex < x.size(); rowIndex++) {
            series1.add(
                    Double.parseDouble(x.elementAt(rowIndex).toString()),
                    Double.parseDouble(y.elementAt(rowIndex).toString()));
        }

        XYSeriesCollection xyCollection = new XYSeriesCollection();
        xyCollection.addSeries(series1);
        //xyCollection.addSeries(series2);//for multiple files.
        return xyCollection;
    }//end createDataset
}

