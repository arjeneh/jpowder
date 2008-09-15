package datasetfactory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
// #[regen=yes,id=DCE.D48C3641-7DCA-878C-C8B1-8761BA038DD2]
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYErrorRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.IntervalXYDataset;
// </editor-fold> 
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.YIntervalSeries;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;

public class XY_XYE extends DataSet {

    private Vector data;
    private static int serial = 0;//class serial number.
    private final String fileTitle;
    
    public XY_XYE(Vector data, String fileName) {
        super(data, fileName);
        this.data = data;
        this.fileTitle = fileName;
    }


    public DatasetPlotter createDatasetPlotter() {
        return new MultiFilePlotter(this);
    }


    public String description() {
        return "Mix of XY and XYE datasets.";
    }



    /**
     * Creates the chart with multiple datasets.
     * 
     * @return The chart.
     */
    /*
    private JFreeChart createChart() {
    
    //YIntervalSeriesCollection dataset = new YIntervalSeriesCollection();
    XYDataset dataset1 = createDataset("Series 1", new Vector());
    
    JFreeChart chart = ChartFactory.createTimeSeriesChart(
    "Multiple Axis Demo 1",
    "Time of Day",
    "Primary Range Axis",
    dataset1,
    true,
    true,
    false);
    
    chart.setBackgroundPaint(Color.white);
    chart.addSubtitle(new TextTitle("Four datasets and four range axes."));
    
    XYPlot plot = (XYPlot) chart.getPlot();
    plot.setOrientation(PlotOrientation.VERTICAL);
    plot.setBackgroundPaint(Color.lightGray);
    plot.setDomainGridlinePaint(Color.white);
    plot.setRangeGridlinePaint(Color.white);
    
    plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
    plot.getRangeAxis().setFixedDimension(15.0);
    XYItemRenderer renderer = plot.getRenderer();
    renderer.setSeriesPaint(0, Color.black);
    
    // AXIS 2
    NumberAxis axis2 = new NumberAxis("Range Axis 2");
    axis2.setFixedDimension(10.0);
    axis2.setAutoRangeIncludesZero(false);
    axis2.setLabelPaint(Color.red);
    axis2.setTickLabelPaint(Color.red);
    plot.setRangeAxis(1, axis2);
    plot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_LEFT);
    
    //dataset number 2. 
    XYDataset dataset2 = createDataset("Series 2", 1000.0, new Minute(),
    170);
    plot.setDataset(1, dataset2);
    plot.mapDatasetToRangeAxis(1, 1);
    
    XYItemRenderer renderer2 = new StandardXYItemRenderer();
    renderer2.setSeriesPaint(0, Color.red);
    plot.setRenderer(1, renderer2);
    
    
    return chart;
    }
     */
    /**
     * Creates a sample dataset.
     * 
     * @param name  the dataset name.
     * @param base  the starting value.
     * @param start  the starting period.
     * @param count  the number of values to generate.
     *
     * @return The dataset.
     */
    private IntervalXYDataset createDataset(String name, Vector dataset) {
        //how many files
        int fileNum = dataset.size();

        YIntervalSeries seriesX = null;
        //XYSeries series1 = new XYSeries(bottomTitle);
        //the name at the bottom of the chart.

        for (int i = 0; i < fileNum; i++) {
            seriesX = new YIntervalSeries("Serie " + i);
        //if 2 columns use TwoColumnsPlotter.
        //XYSeries series1 = new XYSeries(bottomTitle);
        //the name at the bottom of the chart.

        //if 3 columns use ThreeColumnsPlotter.
        //YIntervalSeries seriesX = new YIntervalSeries("Serie " + i);
        //seriesX.add(X, Y);//this can be formated as s1.add(x, y, minusY, addY);
        }



        /*TimeSeries series = new TimeSeries(name, start.getClass());
        RegularTimePeriod period = start;
        double value = base;
        for (int i = 0; i < count; i++) {
        series.add(period, value);
        period = period.next();
        value = value * (1 + (Math.random() - 0.495) / 10.0);
        }
        
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series);*/

        return (IntervalXYDataset) dataset;

    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        Vector xy = VectorMiscUtil.initXYData();
        Vector xye = VectorMiscUtil.initXYEData();
        Vector all = new Vector();
        all.add(xy);
        all.add(xye);

        XY_XYE demo = new XY_XYE(all, "Multiple File 1");

    }
}

