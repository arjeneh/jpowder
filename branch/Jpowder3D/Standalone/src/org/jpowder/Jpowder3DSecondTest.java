package org.jpowder;

import org.jpowder.jfreechart.RainBowPaintScale;
import org.jpowder.InernalFrame.JpowderInternalframe2D;
import java.awt.Color;
import java.util.Collections;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBlockRenderer;
import org.jfree.chart.title.PaintScaleLegend;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;

/**
 *
 * @author Arjeneh, Dan, Anders
 */
public class Jpowder3DSecondTest extends ApplicationFrame {

    private static JFreeChart chart;
    private static XYPlot plot;

    public Jpowder3DSecondTest() {
        super("");
        ChartPanel chartPanel = (ChartPanel) createDemoPanel();
        chartPanel.setDisplayToolTips(false);
        chartPanel.setPreferredSize(new java.awt.Dimension(640, 480));
        setContentPane(chartPanel);
        pack();
        setVisible(true);

    }

    private static JFreeChart createChart(XYDataset dataset) {
        NumberAxis xAxis = new NumberAxis("x Axis");
        xAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        xAxis.setLowerMargin(0.0);
        xAxis.setUpperMargin(0.0);

        NumberAxis yAxis = new NumberAxis("y Axis");
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        yAxis.setLowerMargin(0.0);
        yAxis.setUpperMargin(0.0);

//        yAxis.setRange(WIDTH, WIDTH);


//        yAxis.setTickUnit(new NumberTickUnit(1.0));

        XYBlockRenderer renderer = new XYBlockRenderer();
        double maxY = 0;
        double minY = 0;
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus;
        for (int i = 0; i < inFocus.getXYPlot().getDatasetCount(); i++) {
            maxY = (Double) Collections.max(inFocus.getPowderDataSet().elementAt(i).getY());
            minY = (Double) Collections.min(inFocus.getPowderDataSet().elementAt(i).getY());
//            System.out.println("max y" + maxY);
        }

//       PaintScale lps = new GrayPaintScale(0, maxY);
//        LookupPaintScale lps = new LookupPaintScale(minY, maxY, Color.GRAY);
//        GreenishPaintScale lps = new GreenishPaintScale(maxY);
        RainBowPaintScale lps = new RainBowPaintScale(minY, maxY, Color.BLACK);

        renderer.setPaintScale(lps);
//        renderer.setBlockHeight(10);
        renderer.setBlockAnchor(RectangleAnchor.BOTTOM);

//        r.setBlockHeight(1.0f);
//        r.setBlockWidth(1.0f);
        plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        JFreeChart charts = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        charts.removeLegend();
        NumberAxis scaleAxis = new NumberAxis("");


        PaintScaleLegend legend = new PaintScaleLegend(lps,
                scaleAxis);
//        legend.setAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        legend.setMargin(new RectangleInsets(0, 0, 5, 0));
        legend.setPadding(new RectangleInsets(0, 40, 0, 10));
        legend.setStripWidth(10);
        legend.setPosition(RectangleEdge.BOTTOM);



//        legend.setBackgroundPaint(Color.WHITE);
        charts.addSubtitle(legend);
        charts.setBackgroundPaint(Color.white);


        return charts;
    }

    public static XYZDataset createDataset() {

        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus;

        DefaultXYZDataset dataset = new DefaultXYZDataset();


        for (int i = 0; i < inFocus.getXYPlot().getDatasetCount(); i++) {

            XYDataset ds = inFocus.getXYPlot().getDataset(i);
            double[][] data = new double[3][ds.getItemCount(i)];
            for (int j = 0; j < ds.getItemCount(i); j++) {

                data[0][j] = (Double) inFocus.getPowderDataSet().elementAt(i).getX().get(j);//x
//                data[1][10] = Jpowder_Reader.getLocalData().get(i).get(1);//x reading from Jpowder File
                data[1][j] = i;//x by file number..
                data[2][j] = (Double) inFocus.getPowderDataSet().elementAt(i).getY().get(j);//Colour

            }
            dataset.addSeries("Serie " + i, data);

        }
       

        return dataset;
    }

    public static JPanel createDemoPanel() {
        //chart = createChartEmpty(createDataset());
        chart = createChart(createDataset());



        createDataset().addChangeListener(new DatasetChangeListener() {

            public void datasetChanged(DatasetChangeEvent arg0) {
            }
        });

        //seting tool tips
        chart.getXYPlot().getRenderer().setBaseToolTipGenerator(new XYToolTipGenerator() {

            public String generateToolTip(XYDataset dataset, int series, int item) {
                XYZDataset xyzDataset = (XYZDataset) dataset;
                double x = xyzDataset.getXValue(series, item);
                double y = xyzDataset.getYValue(series, item);
                double z = xyzDataset.getZValue(series, item);
                return ("" + x + " | " + y + " | " + z);
            }
        });

        return new ChartPanel(chart);
    }

    public static XYPlot getPlot(){
        return plot;
    }
}


