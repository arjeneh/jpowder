package org.jpowder.dataset;

import org.jpowder.dataset.jfreechart.PowderChartMouseObserver;
import java.awt.Color;
import java.awt.Font;
import java.util.Vector;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.Dataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;

/*
DataSet xye = new XY_XYE(lData, fileName);
DatasetPlotter plot3Col = xye.createDatasetPlotter();
powderChartPanel.add(plot3Col.createPowderChart());
 */
import org.jpowder.dataset.jfreechart.XY_PopupMenu;
//

public class MultiFilesPlotter extends DatasetPlotter {

    private Vector<DataSet> datasets;

    public MultiFilesPlotter(Vector<DataSet> d) {
        super(d);
        this.datasets = d;
        System.out.println("MultiFilesPlotter is called ");
    }

    public String description() {
        return "Multiple Files Plotter";
    }

    @Override
    public ChartPanel createPowderChart() {
        //
        XYDataset JFreeChart_datasets = createDataset();

        System.out.println("createPowderChart() in MultiFilesPlotter.java is called ");

        JFreeChart chart = createChart(JFreeChart_datasets);

        ChartPanel chartPanel = new ChartPanel(chart, true);
        chartPanel.setMaximumSize(new java.awt.Dimension(500, 270));
        chartPanel.setDisplayToolTips(false);
        chartPanel.getChartRenderingInfo().setEntityCollection(null);
        //User clicks and popup a dialog.
        chartPanel.add(new XY_PopupMenu(chartPanel));

        //User click and it brings up a new Frame for editing the chart.
        chartPanel.addChartMouseListener(new PowderChartMouseObserver(chartPanel));

        return chartPanel;
    }

    /**
     * Creates the chart with multiple datasets.
     * 
     * @return The chart.
     */
    private JFreeChart createChart(XYDataset dataSet) {
  

        // create the chart...
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Chart: " ,//this.d.getFileName(), // chart title
                "X", // x axis label
                "Y", // y axis label
                dataSet, // data
                PlotOrientation.VERTICAL,
                true, // include legend
                false, // tooltips
                false // urls
                );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

        

        chart.setBackgroundPaint(Color.white);
        chart.addSubtitle(new TextTitle("Data of " + "")); ///this.d.getFileName()));

        //add the copyright.
        TextTitle source = new TextTitle("Created by Kreecha Puphaiboon and Anders Markvardsen");
        source.setFont(new Font("SansSerif", Font.PLAIN, 10));
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
        renderer.setBaseLinesVisible(true);
        renderer.setBaseShapesVisible(true);

        // change the auto tick unit selection to integer units only...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        return chart;
    }

    /**
     * 
     * @param dataVec  dataVec.
     * @param name  the file name list came as [pd_0010.xy, SNBL_zeolite_VPI-9.xye]
     *
     * @return The dataset.
     */
    private XYDataset createDataset() {

       // Vector<Vector<Double>> localData = dataVec;
        //System.out.println("XYDataset createDataset() in MultiFilesPlotter.java: " + name);

        /*String filename = name;
        int namesChars = names.length();
        //remove '[' and ']'
        String newString = names.substring(1, namesChars - 1);

        String[] files = newString.split(",");*/

        //XY_XYE xy_xye = (XY_XYE) this.d;
        //xy_xye.

        XYSeriesCollection datasetCol = new XYSeriesCollection();

        for (int f = 0; f < datasets.size(); f++) {
            DataSet lDataset = datasets.elementAt(f);

            //new file
            XYSeries series = new XYSeries(lDataset.getFileName());

            Vector x = lDataset.getX();
            Vector y = lDataset.getY();
        
            for (int rowIndex = 0; rowIndex < x.size(); rowIndex++) {
            series.add(
                    Double.parseDouble(x.elementAt(rowIndex).toString()),
                    Double.parseDouble(y.elementAt(rowIndex).toString()));
           } 
            
            /*
            for (int r = 0; r < file.size(); r++) {
                Vector rowData = file.elementAt(r);
                Vector x = VectorMiscUtil.getColumn(rowData, 0);
                Vector y = VectorMiscUtil.getColumn(rowData, 1);

                System.out.println("rowData in MultiFilesPlotter.java: " + rowData);
                System.out.println("X in MultiFilesPlotter.java: " + x);
                System.out.println("Y in MultiFilesPlotter.java: " + y);

                System.out.println("add x to series: " +
                        Double.parseDouble(x.elementAt(r).toString()));
                System.out.println("add y to series: " +
                        Double.parseDouble(y.elementAt(r).toString()));

                for (int col = 0; col < rowData.size(); col++) {
                    series.add(
                            Double.parseDouble(x.elementAt(col).toString()),
                            Double.parseDouble(y.elementAt(col).toString())
                            );
                }

            }//for
 */

            datasetCol.addSeries(series);
        }//outer for

        return datasetCol;

    }//createDataset
}//class

