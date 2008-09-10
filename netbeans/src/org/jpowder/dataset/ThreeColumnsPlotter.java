package org.jpowder.dataset;

import java.awt.Color;
import java.util.Vector;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYErrorRenderer;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.YIntervalSeries;
import org.jfree.data.xy.YIntervalSeriesCollection;
//
import org.jpowder.PowderPopupMenu;
import org.jpowder.util.VectorMiscUtil;

public class ThreeColumnsPlotter extends DatasetPlotter {

    private DataSet d;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9BCDAAA5-48C4-DA54-051D-F87E256B8859]
    // </editor-fold> 
    public ThreeColumnsPlotter(DataSet d) {
        super(d);
        this.d = d;
    }

    public String description() {
        return "Three Columns Plotter" +
                " data is " + this.d.description();
    //this.d.description();
    }
    /**
     * Creates a chart with (x,y, -y, +y).
     * @param dataset  the dataset.
     * @return The chart.
     */
    private JFreeChart createChart(IntervalXYDataset dataset) {
        // update serial

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
                "Chart: " + this.d.getFileName(), plot);
        XYE_Chart.setBackgroundPaint(Color.white);
        return XYE_Chart;
    }

    private IntervalXYDataset createDataset(java.util.Vector theData) {

        //IntervalXYDataset is an interface.
        YIntervalSeriesCollection lDataset = new YIntervalSeriesCollection();

        YIntervalSeries s1 = new YIntervalSeries("Dataset:" + this.d.getFileName()); //(x,y, -y, +y)

        //-- TESTING PROTOCAL ---------------------------------------
        //Double d = (Double) ((List)x.get(0)).get(0); 
        Vector thedata = theData;
        Vector last = VectorMiscUtil.getLastColumnOf2DVector(thedata);
        //
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

        //Add data to dataset
        lDataset.addSeries(s1);

        return lDataset;
    }

    @Override
    public ChartPanel createPowderChart() {
        JFreeChart chart = createChart( createDataset( this.d.getData() ) );
        
        ChartPanel chartPanel = new ChartPanel(chart, true);
        chartPanel.setMaximumSize(new java.awt.Dimension(500, 270));
        chartPanel.setDisplayToolTips(false);
        chartPanel.getChartRenderingInfo().setEntityCollection(null);
        //user clicks popup a dialog.
        chartPanel.addChartMouseListener(new PowderChartMouseObserver());

        //add popup menu.
        PowderPopupMenu pop = new PowderPopupMenu(chartPanel);
 
        return chartPanel;
    }
}

