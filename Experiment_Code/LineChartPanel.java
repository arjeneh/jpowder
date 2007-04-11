import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
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
import org.jfree.chart.event.ChartChangeEvent;
import org.jfree.chart.event.ChartChangeListener;
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

public class LineChartPanel extends JPanel{

    private Vector data;
    private URL source;
    private XYSeries series1;
    private final String fileTitle;
    private ChartPanel chartPanel;
    private JFreeChart chart;
    
    
    /**
     * Creates a chart.
     * @param theData  the vector data.
     * @param theSource  the url.
     * @param theFileTitle  the file name.
     */
    public LineChartPanel(Vector theData, URL theSource, String theFileTitle) {
        
        data = theData;
        source = theSource;
        fileTitle = theFileTitle;
        
      
        XYDataset dataset = createDataset(fileTitle);//1
        JFreeChart chart = createChart(dataset);//2
        
        chart.addProgressListener(new ChartProgressListener(){
            public void chartProgress(ChartProgressEvent event){
                if (event.getType() == ChartProgressEvent.DRAWING_FINISHED) {
                    return;
                }
                
                int percent = event.getPercent();
                
                System.out.println("Render " + percent + " percents.");
                
                if (percent >= 100) {
                    System.out.println("Render completed.");
                    //remove(progressBar);
                    //add(chartPanel);
                }//end if
            }//end chartProgress
        });//end addProgressListener
        
        ChartPanel chartPanel = new ChartPanel(chart, true);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        chartPanel.setDisplayToolTips(false);
        chartPanel.getChartRenderingInfo().setEntityCollection(null);
        
        add(chartPanel);
    }
    
    
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
    
    public void getDataFromFile() {
        String aLine;
        
        try {
            //for applet it got to be getCodeBase(), but for JPanel passing para is enough.
            //source = new URL(getCodeBase(), fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(source.openStream()));
            
            while ((aLine = br.readLine()) != null) {
                // create a vector to hold the field values
                Vector newRow = new Vector();
                StringTokenizer st2 =  new StringTokenizer(aLine);
                int numToken = st2.countTokens();
                
                for (int i = 0 ; i < numToken-1; i++){//ignore the last STD by using -1
                    newRow.addElement(st2.nextToken());
                } //for
                
                data.addElement(newRow);
            }//while readLine
            
            br.close();
            //return dumData;
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL = " + e);
            //return null;
        } catch(IOException io){
            System.out.println("IOException throws " + io);
            //return null;
        }
        
        
    }//readFileToTable()
    
    /**
     * Creates a chart.
     *
     * @param dataset  the data for the chart.
     *
     * @return a chart.
     */
    private JFreeChart createChart(XYDataset dataset) {
        // create the chart...
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Visualisation of Powder Diffraction Data",      // chart title
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
    
} //end LineChartPanel class
