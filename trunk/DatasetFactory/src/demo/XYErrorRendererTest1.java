package datasetfactory;

import java.awt.Color;

import java.util.Vector;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import javax.swing.JPopupMenu;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYErrorRenderer;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.YIntervalSeries;
import org.jfree.data.xy.YIntervalSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A line chart with error bars.
 */
public class XYErrorRendererTest1 extends ApplicationFrame {

    //static JPopupMenu popup;
    //JMenuItem item;
    ChartPanel chartPanel;

    /**
     * Constructs the demo application.
     *
     * @param title  the frame title.
     */
    public XYErrorRendererTest1(String title) {
        super(title);
        JPanel panel = createDemoPanel();
        panel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(panel);
    }

    /**
     * Creates a chart.
     * @param dataset  the dataset.
     * @return The chart.
     */
    private static JFreeChart createChart(IntervalXYDataset dataset) {

        NumberAxis xAxis = new NumberAxis("X");
        NumberAxis yAxis = new NumberAxis("Y");

        XYErrorRenderer renderer = new XYErrorRenderer();
        renderer.setBaseLinesVisible(true);
        renderer.setBaseShapesVisible(false);
        renderer.setDrawYError(false);

        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);

        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        JFreeChart chart = new JFreeChart("XYErrorRenderer Test 1", plot);
        chart.setBackgroundPaint(Color.white);

        return chart;
    }

    /** 
     * Creates a sample dataset.
     * @param v1[] is the vector of file names
     * @param v2 is the dataset
     * */
    private static IntervalXYDataset createDataset() {
        YIntervalSeriesCollection dataset = new YIntervalSeriesCollection();

        YIntervalSeries s1 = new YIntervalSeries("Dataset 1");
        //-- TESTING PROTOCAL ---------------------------------------
        Vector data = VectorMiscUtil.initXYEData();
        Vector last = VectorMiscUtil.getLastColumnOf2DVector(data);
        Vector twoColumn = VectorMiscUtil.copyBeforeLastColumnsOf2DVector(data);
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
            //series2.add(t, u);
            }//end for 2
        }//end for 1
        //END --TESTING

        //s1.add(1.0, 10.0, 9.0, 11.0);
        //s1.add(10.0, 6.1, 4.34, 7.54);
        //s1.add(17.8, 4.5, 3.1, 5.8);


        YIntervalSeries s2 = new YIntervalSeries("Dataset 2");
        s2.add(3.0, 7.0, 6.0, 8.0);
        s2.add(13.0, 13.0, 11.5, 14.5);
        s2.add(24.0, 16.1, 14.34, 17.54);

        //add data to dataset
        dataset.addSeries(s1);
        dataset.addSeries(s2);
        return dataset;
    }

    /**
     * Creates a panel for the demo.
     *  
     * @return A panel.
     */
    public JPanel createDemoPanel() {
        chartPanel = new ChartPanel(createChart(createDataset()));
        return chartPanel;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        XYErrorRendererTest1 demo = new XYErrorRendererTest1(
                "XYErrorRenderer Test 1");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

        JPopupMenu popup = demo.chartPanel.getPopupMenu();
        JMenuItem item = new JMenuItem("Disable");
        popup.add(item);
        
        //If you add a new method to the class that allows the data to be updated,
        //make sure the method fires a DatasetChangeEvent to all registered listeners. The 
    }
}
