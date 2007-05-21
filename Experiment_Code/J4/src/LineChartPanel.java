import java.awt.BasicStroke;
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
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.entity.ChartEntity;

import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.plot.PlotOrientation;

import org.jfree.chart.title.TextTitle;

import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;

public class LineChartPanel extends JPanel implements Cloneable{
    
    private Vector data;
    private XYSeries series1;
    private final String fileTitle;
    private ChartPanel chartPanel;
    private JFreeChart chart;
    private static int serial = 0;//class serial number
    
    /**
     * Creates a chart.
     * @param theData  the vector data.
     * @param theFileTitle  the file name.
     */
    public LineChartPanel(Vector theData, String theFileTitle) {
       data = theData;
        fileTitle = theFileTitle;
        XYDataset dataset = createDataset(theFileTitle);//1
        JFreeChart chart = createChart(dataset);//2
        //chart.addProgressListener();
        
        ChartPanel chartPanel = new ChartPanel(chart, true);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        chartPanel.setDisplayToolTips(false);
        chartPanel.getChartRenderingInfo().setEntityCollection(null);
        
        chartPanel.addChartMouseListener(new ChartMouseObserver());
        
        
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
    
    //TODO: synchronized since user can click on the chart and jvm says ERROR.
    
    
    //@Override
    protected Object clone() throws CloneNotSupportedException {
        
        LineChartPanel clone = (LineChartPanel) super.clone();
        clone.data = this.data;
        clone.chart= this.chart;
        clone.chartPanel = this.chartPanel;
        clone.series1 = this.series1;
        //clone.fileTitle  = fileTitle;
        clone.serial     = this.serial;//class serial number
        
        //this.add(chartPanel);
        this.setEnabled(true);       
        return clone;
    }
    
    /*protected void finalize()throws Throwable {
        data       = null;
        series1    = null;
        chartPanel = null;
        chart      = null;
    }*/
       
    private class ChartMouseObserver implements ChartMouseListener {
        public void chartMouseMoved(ChartMouseEvent chartMouseEvent) {}
        
        public void chartMouseClicked(ChartMouseEvent chartMouseEvent) {
            if(chartMouseEvent.getTrigger().getClickCount() == 2){
                try{
                    JFreeChart plot_2 = (JFreeChart) chartMouseEvent.getChart().clone();
                    
                    JFrame frame = new JFrame("Edit chart");
                    JScrollPane pane = new JScrollPane();
                    
                    ChartPanel chartPanel_1 = new ChartPanel(plot_2);
                    pane.setViewportView(chartPanel_1);
                    pane.setPreferredSize(new Dimension( 300, 120 ) );
                    frame.getContentPane().add(pane,BorderLayout.CENTER);
                    frame.setPreferredSize(new java.awt.Dimension(600, 370));
                    frame.setSize(800,600);
                    //frame.setLocationRelativeTo(this);
                    frame.setVisible(true);
                }  catch (Exception e){
                    e.printStackTrace();
                }
            }
        }//end if
    }//end ChartMouseObserver
    
} //end LineChartPanel class
