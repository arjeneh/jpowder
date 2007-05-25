package org.jpowder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JScrollPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import org.jfree.ui.RectangleInsets;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;

/**
 *
 * @author Kreecha Puphaiboon
 */

public class LineChartPanel extends JPanel implements Cloneable{
    
    private Vector data;
    private XYSeries series1;
    private XYDataset dataset;
    private final String fileTitle;
    private ChartPanel chartPanel;
    private JFreeChart chart;
    private static int serial = 0;//class serial number.
    
    /** Class constructor specifying data and file name to be ploted.
     * @param theData  the vector data.
     * @param theFileTitle  the file name.
     */
    public LineChartPanel(Vector theData, String theFileTitle) {
        data = theData;
        fileTitle = theFileTitle;
        dataset = createDataset(theFileTitle);//1
        chart = createChart(dataset);//2
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        
        chartPanel = new ChartPanel(chart, true);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        chartPanel.setDisplayToolTips(false);
        chartPanel.getChartRenderingInfo().setEntityCollection(null);
        
        chartPanel.addChartMouseListener(new ChartMouseObserver());//user clicks popup a dialog.
        
        add(chartPanel);
        this.setName("Chart number: " + serial);//importantly use in DeleteComponentHandler
    }
    
    //------------------------------ALL JFREECHART STUFFS------------------------------
    /**
     * Creates a sample dataset.
     * @param name  the name at the bottom of the chart.
     * @return a sample dataset.
     */
    public XYDataset createDataset(String name) {
        String bottomTitle = name;
        XYSeries series1 = new XYSeries(bottomTitle);//the name at the bottom of the chart.
        
        //-------------------------------------------------------------
        for (int rowIndex = 0; rowIndex < data.size(); rowIndex++) {
            Vector row = (Vector)data.elementAt(rowIndex);
            for (int colIndex = 0; colIndex < row.size(); colIndex++){
                Double x =  Double.parseDouble(row.elementAt(0).toString()); //works
                Double y = Double.parseDouble(row.elementAt(1).toString());  //works
                series1.add(x, y);
                //series2.add(t, u);
            }//end for 2
        }//end for 1
        
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        //dataset.addSeries(series2);//for multiple files.
        return dataset;
    }//end createDataset
    
    /**
     * Creates a chart.
     *
     * @param dataset  the data for the chart.
     *
     * @return a chart.
     */
    private JFreeChart createChart(XYDataset dataset) {
        // update serial
        serial++;
        // create the chart...
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Chart number: " + String.valueOf(serial) + " ",      // chart title
                "X",                      // x axis label
                "Y",                      // y axis label
                dataset,                  // data
                PlotOrientation.VERTICAL,
                true,                     // include legend
                false,                    // tooltips
                false                     // urls
                );
        
        
        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);
        chart.addSubtitle(new TextTitle("Data of " + fileTitle));
        
        //add the copyright.
        TextTitle source = new TextTitle("Created by Kreecha Puphaiboon and Anders Markvardsen");
        source.setFont(new Font("SansSerif", Font.PLAIN,10));
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
        renderer.setShapesVisible(false);//the horrible squarel
        
        // change the auto tick unit selection to integer units only...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // OPTIONAL CUSTOMISATION COMPLETED.
        return chart;
    }//end createChart
    
    //@Override
    protected Object clone() throws CloneNotSupportedException {
        
        LineChartPanel clone = (LineChartPanel) super.clone();
        clone.data = this.data;
        clone.chart= this.chart;
        clone.chartPanel = this.chartPanel;
        clone.series1 = this.series1;
        clone.serial     = this.serial;//class serial number
        
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
    
    protected void finalize()throws Throwable {
        data       = null;
        series1    = null;
        chartPanel = null;
        chart      = null;
    }
    
    private class ChartMouseObserver implements ChartMouseListener {
        public void chartMouseMoved(ChartMouseEvent chartMouseEvent) {}
        
        public void chartMouseClicked(ChartMouseEvent chartMouseEvent) {
            if(chartMouseEvent.getTrigger().getClickCount() == 2){
                try {
                    //----------Copy the chart-------------------
                    final JFreeChart plot_copy = (JFreeChart) chartMouseEvent.getChart().clone();                           
                    //Thread safe by seperating it in case editing and modification.
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            EditChartFrame frame = new EditChartFrame(plot_copy);
                        }
                    });                  
                } catch (Exception ex){
                    ex.printStackTrace();
                }//end catch    
            }//end if
        }//end chartMouseClicked
    }//end ChartMouseObserver
    
} //end LineChartPanel class
