import junit.framework.*;
import java.awt.Color;
import java.util.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JApplet;
import java.net.*;
import java.awt.Font;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import javax.swing.event.ChangeListener;
import org.jfree.chart.event.ChartProgressEvent;
import org.jfree.chart.event.ChartProgressListener;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
/*
 * LineChartPanelTest.java
 * JUnit based test
 *
 * Created on 30 March 2007, 11:31
 */

/**
 *
 * @author regtrain
 */
public class LineChartPanelTest extends TestCase {
    
    public LineChartPanelTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(LineChartPanelTest.class);
        
        return suite;
    }

    /**
     * Test of createDataset method, of class LineChartPanel.
     */
    public void testCreateDataset() {
        System.out.println("createDataset");
        
        String name = "";
        LineChartPanel instance = null;
        
        XYDataset expResult = null;
        XYDataset result = instance.createDataset(name);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDataFromFile method, of class LineChartPanel.
     */
    public void testGetDataFromFile() {
        System.out.println("getDataFromFile");
        
        LineChartPanel instance = null;
        
        instance.getDataFromFile();
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of chartProgress method, of class LineChartPanel.
     */
    public void testChartProgress() {
        System.out.println("chartProgress");
        
        ChartProgressEvent event = null;
        LineChartPanel instance = null;
        
        instance.chartProgress(event);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
