
package org.jpowder.dataset;

//
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
import org.jfree.ui.RectangleInsets;
import org.jpowder.dataset.jfreechart.XY_PopupMenu;
/**
 *
 * @author ............
 */
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
                "", // chart title  + this.d.getFileName()
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
        chart.addSubtitle(new TextTitle(""));//"Data of " + this.d.getFileName()) is for sub header

        //add the copyright.
        /*
        TextTitle source = new TextTitle("Created by Kreecha Puphaiboon and Anders Markvardsen");
        source.setFont(new Font("SansSerif", Font.PLAIN, 10));
        source.setPosition(RectangleEdge.BOTTOM);
        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(source); */

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
    }//end createPowderChart

    /**
     * Creates a JFreeChart dataset object from Jpowder Dataset
     *
     * @param name The name to appear at the bottom of the chart
     * @return a JFreeChart dataset
     */
    public XYDataset createDataset(String name) {
        
        XYSeries series1 = new XYSeries(name); //the name at the bottom of the chart.

        Vector x = this.d.getX();
        Vector y = this.d.getY();
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

