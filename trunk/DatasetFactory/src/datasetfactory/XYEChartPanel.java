package datasetfactory;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYErrorRenderer;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.YIntervalSeries;
import org.jfree.data.xy.YIntervalSeriesCollection;
import org.jfree.ui.RefineryUtilities;

/**
 * A line chart with error bars.
 */
public class XYEChartPanel extends JPanel {

    //private XYE dataset;
    private Vector data;
    private final String fileName;
    private ChartPanel chartPanel;
    private JFreeChart chart;
    private static int serial = 0;//class serial number.

    /**
     * Constructs the demo application.
     *
     * @param title  the frame title.
     */
    public XYEChartPanel(Vector theData, String theFileitle) {
        data = theData;
        fileName = theFileitle;

        chart = createChart(createDataset(this.data));
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

    public JPopupMenu createDisablePopupMenu() {
        JPopupMenu popup = chartPanel.getPopupMenu();
        JMenuItem item = new JMenuItem("Disable");
        popup.add(item);
        return popup;
    }

    /**
     * Creates a chart.
     * @param dataset  the dataset.
     * @return The chart.
     */
    private JFreeChart createChart(IntervalXYDataset dataset) {
        // update serial
        serial++;
        NumberAxis xAxis = new NumberAxis("X");
        NumberAxis yAxis = new NumberAxis("Y");

        //XYLineAndShapeRenderer enable connecting lines to be on/off.

        XYErrorRenderer renderer = new XYErrorRenderer();
        renderer.setBaseLinesVisible(true);
        renderer.setBaseShapesVisible(false);
        //renderer.setDrawYError(true);//show Y error bar.

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
     * Creates a sample dataset.
     * @param v1[] is the vector of file names
     * @param v2 is the dataset
     * */
    private IntervalXYDataset createDataset(Vector theData) {
        
        //IntervalXYDataset is an interface.
        YIntervalSeriesCollection dataset = new YIntervalSeriesCollection();

        YIntervalSeries s1 = new YIntervalSeries("Dataset 1"); //(x,y, -y, +y)

        //-- TESTING PROTOCAL ---------------------------------------
        Vector thedata = theData;
        Vector last = VectorMiscUtil.getLastColumnOf2DVector(thedata);
        Vector twoColumn = VectorMiscUtil.copyBeforeLastColumnsOf2DVector(thedata);
        Vector outputOfMinusAdd = VectorMiscUtil.do_Minus_Addition_Y(twoColumn, last);
        Vector result = VectorMiscUtil.getResultOfAddingTwoVectors(twoColumn, outputOfMinusAdd);

        for (int rowIndex = 0; rowIndex < result.size(); rowIndex++) {
            Vector row = (Vector) result.elementAt(rowIndex);
            for (int colIndex = 0; colIndex < row.size(); colIndex++) {
                Double x = Double.parseDouble(row.elementAt(0).toString()); //works
                Double y = Double.parseDouble(row.elementAt(1).toString());  //works
                Double minusY = Double.parseDouble(row.elementAt(2).toString());  //works
                Double addY = Double.parseDouble(row.elementAt(3).toString());  //works
                s1.add(x, y, minusY, addY);
            }//end for 2
        }//end for 1
        //END --TESTING

        //add data to dataset
        dataset.addSeries(s1);

        return dataset;
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
        
        XYEChartPanel demo = new XYEChartPanel(xye,
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

        XYEChartPanel clone = (XYEChartPanel) super.clone();
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
