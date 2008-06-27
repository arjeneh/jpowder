package datasetfactory;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYErrorRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.YIntervalSeries;
import org.jfree.data.xy.YIntervalSeriesCollection;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

/**
 * A line chart with error bars.
 */
public class XY_XYEChartPanel extends JPanel {

    //private XYE dataset;
    private Vector data;
    private final String fileName;
    private ChartPanel chartPanel;
    private JFreeChart chart;
    private static int serial = 0;//class serial number.

    public void createMultipleDatasetChart(Vector dataset) {
        int fileNum = dataset.size();
        
        for (int i = 0; i < fileNum; i++) {
         //if 2 columns use TwoColumnsPlotter.
        //XYSeries series1 = new XYSeries(bottomTitle);
        //the name at the bottom of the chart.

        //if 3 columns use ThreeColumnsPlotter.
        //YIntervalSeries seriesX = new YIntervalSeries("Serie " + i);
        //seriesX.add(X, Y);//this can be formated as s1.add(x, y, minusY, addY);
        }

    }


    /**
     * Constructs the demo application.
     *
     * @param title  the frame title.
     */
    public XY_XYEChartPanel(Vector theData, String theFileitle) {
        data = theData;
        fileName = theFileitle;

        //1. filter theData in Vector. If
        //2. XY create XY chart, XYE create XYE chart.

        chart = createChart(createDataset(data));
        chartPanel = new ChartPanel(this.chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        chartPanel.setDisplayToolTips(false);
        chartPanel.getChartRenderingInfo().setEntityCollection(null);
        //user clicks popup a dialog.
        chartPanel.addChartMouseListener(new ChartMouseObserver());

        //add disable popup menu.
        JPopupMenu popup = chartPanel.getPopupMenu();
        JMenuItem item = new JMenuItem("Disable");
        popup.add(item);

        add(chartPanel);
        //importantly use in DeleteComponentHandler
        this.setName("Chart number: " + serial);
    }


    private JFreeChart createChart(IntervalXYDataset dataset) {
        // update serial
        serial++;
        NumberAxis xAxis = new NumberAxis("X");
        NumberAxis yAxis = new NumberAxis("Y");

        //XYLineAndShapeRenderer enable connecting lines to be on/off.

        XYErrorRenderer renderer = new XYErrorRenderer();
        renderer.setBaseLinesVisible(true);
        renderer.setBaseShapesVisible(false);
        renderer.setDrawYError(true);//show Y error bar.

        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        JFreeChart XYE_Chart = new JFreeChart(
                "Chart number: " + String.valueOf(serial) + " ", plot);
        XYE_Chart.setBackgroundPaint(Color.white);
        return XYE_Chart;
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

        XY_XYEChartPanel demo = new XY_XYEChartPanel(xye,
                "XYErrorRenderer Test 1");
        JFrame frame = new JFrame("Multiple File Demo");
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.getContentPane().add(demo, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

    //If you add a new method to the class that allows the data to be updated,
    //make sure the method fires a DatasetChangeEvent to all registered listeners. The 
    }

    private IntervalXYDataset createDataset(Vector data) {
        YIntervalSeriesCollection dataset = new YIntervalSeriesCollection();

        YIntervalSeries s1 = new YIntervalSeries("Dataset 1");
        
        return null;
    }

    private class ChartMouseObserver implements ChartMouseListener {

        public void chartMouseMoved(ChartMouseEvent chartMouseEvent) {
        }

        public void chartMouseClicked(ChartMouseEvent chartMouseEvent) {
            if (chartMouseEvent.getTrigger().getClickCount() == 2) {
                try {
                    //----------Copy the chart-------------------
                    final JFreeChart plot_copy = (JFreeChart) chartMouseEvent.getChart().clone();
                    //Thread safe by seperating it in case editing and modification.
                    java.awt.EventQueue.invokeLater(new Runnable() {

                        public void run() {
                            //EditChartFrame obj = new EditChartFrame(plot_copy);
                        }
                    });
                } catch (Exception ex) {
                    ex.printStackTrace();
                }//end catch
            }//end if
        }//end chartMouseClicked
    }//end ChartMouseObserver 

    @SuppressWarnings("static-access")
    @Override
    protected Object clone() throws CloneNotSupportedException {

        XY_XYEChartPanel clone = (XY_XYEChartPanel) super.clone();
        clone.data = this.data;
        clone.chart = this.chart;
        clone.chartPanel = this.chartPanel;

        clone.serial = this.serial;//class serial number

        this.setEnabled(true);
        return clone;
    }

    public ChartPanel getChartPanel() {
        return chartPanel;
    }

    public void setChartPanel(ChartPanel chartPanel) {
        this.chartPanel = chartPanel;
    }

    public JFreeChart getChart() {
        return chart;
    }

    public void setChart(JFreeChart chart) {
        this.chart = chart;
    }

    @Override
    protected void finalize() throws Throwable {
        data = null;
        chartPanel = null;
        chart = null;
    }
}
