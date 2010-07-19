/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder;

import java.awt.Color;
import java.awt.Paint;
import java.util.Collections;
import java.util.Vector;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.LookupPaintScale;
import org.jfree.chart.renderer.PaintScale;
import org.jfree.chart.renderer.xy.XYBlockRenderer;
import org.jfree.chart.title.PaintScaleLegend;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jpowder.dataset.DataSet;

/**
 *
 * @author Arjeneh, Dan, Anders
 */
public class Jpowder3DTest extends ApplicationFrame {

    private static JFreeChart chart;
    private static Vector<DataSet> datasets;

    public Jpowder3DTest(String title) {
        super(title);
        ChartPanel chartPanel = (ChartPanel) createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(640, 480));
        chartPanel.setMouseZoomable(true, false);
        setContentPane(chartPanel);
        pack();
        setVisible(true);


    }

    private static JFreeChart createChart(XYDataset dataset) {
        NumberAxis xAxis = new NumberAxis("x Axis");
        NumberAxis yAxis = new NumberAxis("y Axis");
        yAxis.setTickUnit(new NumberTickUnit(1.0));
        XYBlockRenderer r = new XYBlockRenderer();
        double maxY = 0;
        JpowderInternalframe inFocus = Jpowder.internalFrameInFocus;
        for (int i = 0; i < inFocus.getXYPlot().getDatasetCount(); i++) {
            maxY = (Double) Collections.max(inFocus.getPowderDataSet().elementAt(0).getY());
//            System.out.println("max y" + maxY);
        }

        GreenishPaintScale lps = new GreenishPaintScale(maxY);
        
//        PaintScale lps = new GrayPaintScale(0, maxY);

        r.setPaintScale(lps);

        r.setBlockHeight(1.0f);
        r.setBlockWidth(1.0f);
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, r);
        JFreeChart charts = new JFreeChart("Chart Title", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        charts.removeLegend();
        NumberAxis scaleAxis = new NumberAxis("New Scale");

        PaintScaleLegend legend = new PaintScaleLegend(lps,
                scaleAxis);
        legend.setAxisLocation(AxisLocation.TOP_OR_RIGHT);
        legend.setMargin(new RectangleInsets(5, 20, 5, 5));
        legend.setPadding(new RectangleInsets(5, 20, 5, 5));
        legend.setStripWidth(20);
        legend.setPosition(RectangleEdge.RIGHT);
        legend.setBackgroundPaint(Color.WHITE);
        charts.addSubtitle(legend);
        charts.setBackgroundPaint(Color.white);
        return charts;
    }

    private static XYZDataset createDataset() {

        JpowderInternalframe inFocus = Jpowder.internalFrameInFocus;
        DefaultXYZDataset dataset = new DefaultXYZDataset();



        for (int i = 0; i < inFocus.getXYPlot().getDatasetCount(); i++) {



            XYDataset ds = inFocus.getXYPlot().getDataset(i);
            double[][] data = new double[3][ds.getItemCount(i)];
            for (int j = 0; j < ds.getItemCount(i); j++) {
               // Double x = (Double) inFocus.getPowderDataSet().elementAt(i).getX().get(j);
                //Double y = (Double) inFocus.getPowderDataSet().elementAt(i).getY().get(j);
//            Double e = (Double) inFocus.getPowderDataSet().elementAt(0)..get(j);

                data[0][j] = (Double) inFocus.getPowderDataSet().elementAt(i).getX().get(j);
                data[1][j] = i;
                data[2][j] = (Double) inFocus.getPowderDataSet().elementAt(i).getY().get(j);


            }
            dataset.addSeries("Serie " + i, data);

            /* double[][] dats = new double[][]{
            {(Double) inFocus.getPowderDataSet().elementAt(i).getX()},
            {(Double) inFocus.getPowderDataSet().elementAt(i).getY()},
            {(Double) inFocus.getPowderDataSet().elementAt(i).getY()}}; */

        }

        return dataset;
    }

    public static JPanel createDemoPanel() {
        //chart = createChartEmpty(createDataset());
        chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        Jpowder3DTest demo = new Jpowder3DTest(
                "XYZ Demo");
        //demo.pack();
        //RefineryUtilities.centerFrameOnScreen(demo);
        //demo.setVisible(true);
    }
}


class GreenishPaintScale implements PaintScale {

    private double upperBound;

    public GreenishPaintScale(double upperBound) {
        this.upperBound = upperBound;
    }

    public double getLowerBound() {
        return 0.0;
    }

    public double getUpperBound() {
        return upperBound;
    }

    public Paint getPaint(double value) {
        System.out.println("");
        if (value < 0.0 || value >= upperBound) {
            return Color.GRAY;
        }
        double scaledValue = value / upperBound * 511;
        if (scaledValue > 511) {
            //System.out.println("Red-green-blue"+0+","+(1022-(int)(scaledValue))+","+255);
            return new Color(255, 511 - (int) (scaledValue), 0);
        }
        if (scaledValue > 511) {
            //System.out.println("Red-green-blue"+0+","+255+","+((int)(scaledValue)-511));
            return new Color(0, 255, (int) (scaledValue) - 511);
        }
        if (scaledValue > 255) {
            //System.out.println("Red-green-blue"+(510-(int)(scaledValue))+","+255+","+0);
            return new Color((int) (scaledValue) - 255, 255, 0);
        }
        return new Color(0, (int) (scaledValue), 255 - (int) (scaledValue));

    }
}
class PaintTest extends LookupPaintScale {

    private double lowerBound;
    private double upperBound;
    private Paint defaultPaint;

    public PaintTest(double lowerBound,
            double upperBound,
            java.awt.Paint defaultPaint) {


        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.defaultPaint = defaultPaint;

        int rot = 0;
        int blau = 0;
        int gruen = 0;
        int lv = 0;

        this.add(0, new Color(0, 0, 0));
        // ps.add(50000, new Color(0, 0, 0));

        // schwarz bis blau - spektrum
        rot = 0;
        blau = 0;
        gruen = 0;
        for (blau = 0; blau < 255; blau++) {
            Color c = new Color(rot, gruen, blau);
            this.add(lv = lv + 177, c);
        }

        // blau bis türkis - spektrum
        rot = 0;
        blau = 255;
        gruen = 0;
        for (gruen = 0; gruen < 255; gruen++) {
            Color c = new Color(rot, gruen, blau);
            // ps.add(lv, c);
            this.add(lv = lv + 177, c);
        }

        // türkis bis grün - spektrum
        rot = 0;
        blau = 255;
        gruen = 255;
        for (blau = 255; blau > 0; blau--) {
            Color c = new Color(rot, gruen, blau);
            // ps.add(lv, c);
            this.add(lv = lv + 177, c);
        }

        // grün bis gelb - spektrum
        rot = 0;
        gruen = 255;
        blau = 0;
        for (rot = 0; rot < 255; rot++) {
            Color c = new Color(rot, gruen, blau);
            // ps.add(lv, c);
            this.add(lv = lv + 177, c);
        }

        // gelb bis rot - spektrum
        rot = 255;
        gruen = 255;
        blau = 0;
        for (gruen = 255; gruen > 0; gruen--) {
            Color c = new Color(rot, gruen, blau);
            // ps.add(lv, c);
            this.add(lv = lv + 177, c);
        }
    }
}
