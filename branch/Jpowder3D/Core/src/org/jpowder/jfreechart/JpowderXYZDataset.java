/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.jfreechart;

import java.util.Vector;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.GrayPaintScale;
import org.jfree.chart.renderer.xy.XYBlockRenderer;
import org.jfree.chart.title.PaintScaleLegend;
import org.jfree.data.xy.AbstractXYZDataset;
import org.jpowder.dataset.DataSet;

/**
 *
 * @author qyt21516
 */
public class JpowderXYZDataset extends AbstractXYZDataset {

    private Vector<Double> x;
    private Vector<Double> y;
    private Vector<Double> z;
//    private double[][] data;
//
//    public JpowderXYZDataset(double[][] data) {
//        this.data = data;
//
//
//    }
    public JpowderXYZDataset(DataSet dataset) {

        this.x = dataset.getX();
        this.y = dataset.getY();
    }

    public JpowderXYZDataset(Vector<Double> x, Vector<Double> y, Vector<Double> z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public int getSeriesCount() {
        return 1;
    }

    @Override
    public Comparable getSeriesKey(int arg0) {
        Comparable retval = new Comparable() {

            public int compareTo(Object o) {
                return 0;
            }
        };
        return retval;
    }

    public int getItemCount(int arg0) {
        return this.x.size();
    }

    public Number getX(int arg0, int arg1) {
        return null;
    }

    public Number getY(int arg0, int arg1) {
        return null;
    }

    public Number getZ(int arg0, int arg1) {
        return null;
    }

    public static void main(String[] args) {

        JFreeChart chart = createXYZChart();

        ChartFrame frame = new ChartFrame("XYZ Test", chart);
        frame.pack();
        frame.setVisible(true);

    }

    private static JFreeChart createXYZChart() {

        NumberAxis xAxis = new NumberAxis("x Axis");
        NumberAxis yAxis = new NumberAxis("x Axis");

        Vector<Double> x = new Vector<Double>();
        Vector<Double> y = new Vector<Double>();
        Vector<Double> z = new Vector<Double>();

        x.addElement(0.0);
        x.addElement(5.0);
        x.addElement(20.0);

        y.addElement(0.0);
        y.addElement(0.0);
        y.addElement(1.0);

        z.addElement(12.0);
        z.addElement(18.0);
        z.addElement(20.0);


//           double[][] data = new double[][]{
//            {22615.0, 6941.0, 3127.0, 3150.0, 6906.0, 2423.0},
//            {46972.0, 1510.0, 665.0, 698.0, 1330.0, 1018.0},
//            {12582.0, 651.0, 343.0, 304.0, 403.0, 119.0},
//            {2754.0, 103.0, 43.0, 48.0, 34.0, 0.0},
//            {0.0, 0.0, 0.0, 0.0, 0.0, 0.0}};
        JpowderXYZDataset xyzset = new JpowderXYZDataset(x, y, z);
//        XYZDataset xyzset =new JpowderXYZDataset(x, y, z);
//         JpowderXYZDataset xyzset = new JpowderXYZDataset(data);
//        XYZDataset xyzset = new DefaultXYZDataset();
        XYPlot plot = new XYPlot(xyzset, xAxis, yAxis, null);

        XYBlockRenderer r = new XYBlockRenderer();


        GrayPaintScale ps = new GrayPaintScale(0, 0.8);
        r.setBlockHeight(1.0f);
        r.setBlockWidth(1.0f);
        r.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
        plot.setRenderer(r);

        JFreeChart chart = new JFreeChart("Testing 3d",
                JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        NumberAxis scaleAxis = new NumberAxis("");
        PaintScaleLegend ps_legend = new PaintScaleLegend(ps, scaleAxis);
        ps_legend.setAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        chart.addSubtitle(ps_legend);

        return chart;
    }
}
