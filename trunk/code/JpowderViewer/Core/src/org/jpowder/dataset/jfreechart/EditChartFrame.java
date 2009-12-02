
package org.jpowder.dataset.jfreechart;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.Range;
import org.jpowder.util.ScreenUtil;

/*
 * Class name: EditChartFrame.java
    @author: Kreecha Puphaiboon
    Date: Created on 22 May 2007, 10:51
    Modf:
    Description:
 *
 * A frame allocated a chart from the main JPowder class. With functionalities
 * such as zoom, save, delete, edit and etc. But none has yet implemented!
 *
 * called by PowderChartMouseObserver.java method
 * chartMouseClicked(ChartMouseEvent chartMouseEvent)

 */

public class EditChartFrame extends javax.swing.JFrame{

    private org.jfree.chart.JFreeChart jFreeChart;
    private org.jfree.chart.ChartPanel chartPanel;
    private EditChartFrame singletonObject;
      /** The zoom factor. */
    private static final double ZOOM_FACTOR = 0.85;
       /** The min/max values for the primary axis. */
    private double[] primYMinMax = new double[2];

    /** The min/max values for the secondary axis. */
    private double[] secondYMinMax = new double[2];

    /** Creates new form EditChartFrame
     * @param chart JFreeChart Object
     */
    public EditChartFrame(org.jfree.chart.JFreeChart chart) {
        initComponents();
        jFreeChart = chart;
        chartPanel = new org.jfree.chart.ChartPanel(jFreeChart);
        chartPanel.add(new XYE_PopupMenu(chartPanel));
        bigChartPanel.add(chartPanel);
    }

    public EditChartFrame(org.jfree.chart.JFreeChart chart, org.jfree.chart.ChartPanel cp) {
        initComponents();
        jFreeChart = chart;
        chartPanel = cp;
        chartPanel.setPreferredSize(bigChartPanel.getSize());
        bigChartPanel.add(chartPanel);
    }

    public EditChartFrame getSingletonObject(org.jfree.chart.JFreeChart jFreeChart) {
        //if (singletonObject == null) {
        singletonObject = new EditChartFrame(jFreeChart);
        //--}
        return singletonObject;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    @Override
    protected void finalize() throws Throwable {
        jFreeChart = null;
        chartPanel = null;
        singletonObject = null;
    //this = null;
    }

    private void initComponents() {
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        butPanel = new javax.swing.JPanel();
        zoomOutGraph_btn = new javax.swing.JButton();
        zoomInGraph_btn = new javax.swing.JButton();
        save_btn = new javax.swing.JButton();
        editGraph_btn = new javax.swing.JButton();
        bigChart_sp = new javax.swing.JScrollPane();
        bigChartPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Chart");
        getContentPane().setLayout(new java.awt.BorderLayout());

        butPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        butPanel.setMinimumSize(new java.awt.Dimension(800, 40));
        butPanel.setPreferredSize(new java.awt.Dimension(800, 40));


        zoomOutGraph_btn.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent event) {
               ChartRenderingInfo info = chartPanel.getChartRenderingInfo();
                Rectangle2D rect = info.getPlotInfo().getDataArea();
                zoomBoth(rect.getCenterX(), rect.getCenterY(), 1 / ZOOM_FACTOR);

            }
        });

        zoomOutGraph_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/zoomo_small.gif"))); // NOI18N
        zoomOutGraph_btn.setToolTipText("Zoom out the graph");
        zoomOutGraph_btn.setIconTextGap(2);
        zoomOutGraph_btn.setMaximumSize(new java.awt.Dimension(40, 23));
        zoomOutGraph_btn.setMinimumSize(new java.awt.Dimension(40, 23));
        zoomOutGraph_btn.setPreferredSize(new java.awt.Dimension(40, 23));
        butPanel.add(zoomOutGraph_btn);


        zoomInGraph_btn.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent event) {
                ChartRenderingInfo info = chartPanel.getChartRenderingInfo();
                Rectangle2D rect = info.getPlotInfo().getDataArea();
                zoomBoth(rect.getCenterX(), rect.getCenterY(), ZOOM_FACTOR);

            }
        });
        zoomInGraph_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/zoomi_small.gif"))); // NOI18N
        zoomInGraph_btn.setToolTipText("Zoom in the graph");
        zoomInGraph_btn.setIconTextGap(2);
        zoomInGraph_btn.setMaximumSize(new java.awt.Dimension(40, 23));
        zoomInGraph_btn.setMinimumSize(new java.awt.Dimension(40, 23));
        zoomInGraph_btn.setPreferredSize(new java.awt.Dimension(40, 23));
        butPanel.add(zoomInGraph_btn);

        save_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/savas_small.gif"))); // NOI18N
        save_btn.setToolTipText("Zoom in the graph");
        save_btn.setIconTextGap(2);
        save_btn.setMaximumSize(new java.awt.Dimension(40, 23));
        save_btn.setMinimumSize(new java.awt.Dimension(40, 23));
        save_btn.setPreferredSize(new java.awt.Dimension(40, 23));
        butPanel.add(save_btn);

        editGraph_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit_small.gif"))); // NOI18N
        editGraph_btn.setToolTipText("Zoom in the graph");
        editGraph_btn.setIconTextGap(2);
        editGraph_btn.setMaximumSize(new java.awt.Dimension(40, 23));
        editGraph_btn.setMinimumSize(new java.awt.Dimension(40, 23));
        editGraph_btn.setPreferredSize(new java.awt.Dimension(40, 23));
        butPanel.add(editGraph_btn);

        getContentPane().add(butPanel, java.awt.BorderLayout.PAGE_START);

        bigChart_sp.setMinimumSize(new java.awt.Dimension(800, 465));
        bigChart_sp.setPreferredSize(new java.awt.Dimension(screenSize.width, 465));

        bigChartPanel.setLayout(new javax.swing.BoxLayout(bigChartPanel, javax.swing.BoxLayout.Y_AXIS));
        bigChartPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        bigChartPanel.setMaximumSize(new java.awt.Dimension(1800, 1465));
        bigChartPanel.setMinimumSize(new java.awt.Dimension(800, 465));
        bigChartPanel.setPreferredSize(new java.awt.Dimension(800, 0));
        bigChart_sp.setViewportView(bigChartPanel);

        getContentPane().add(bigChart_sp, java.awt.BorderLayout.CENTER);

        pack();
        setAlwaysOnTop(true);
        setSize(800, 600);
        setLocationRelativeTo(this);
        setVisible(true);
        ScreenUtil.centerFrame(this);
    //setBounds(0, 0, screenSize.width, screenSize.height); //fill screen

    }

     /**
     * Sets the pan mode.
     *
     * @param val  a boolean.
     */
    private void setPanMode(boolean val) {

       this.chartPanel.setDomainZoomable(!val);
         chartPanel.setHorizontalAxisTrace(! val);
        //this.chartPanel.setVerticalZoom(!val);
        // chartPanel.setVerticalAxisTrace(! val);

        if (val) {
            this.chartPanel.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        }
        else {
            this.chartPanel.setCursor(Cursor.getDefaultCursor());
        }
    }
    /**
     * Zooms in on an anchor point (measured in Java2D coordinates).
     *
     * @param x  the x value.
     * @param y  the y value.
     * @param zoomFactor  the zoomFactor < 1 == zoom in; else out.
     */
    private void zoomBoth(double x, double y, double zoomFactor) {
        zoomHorizontal(x, zoomFactor);
        zoomVertical(y, zoomFactor);
    }

    /**
     * Decreases the range on the horizontal axis, centered about a Java2D x coordinate.
     * <P>
     * The range on the x axis is multiplied by zoomFactor
     *
     * @param x  the x coordinate in Java2D space.
     * @param zoomFactor  the zoomFactor < 1 == zoom in; else out.
     */
    private void zoomHorizontal(double x, double zoomFactor) {

        JFreeChart chart = this.chartPanel.getChart();
        ChartRenderingInfo info = this.chartPanel.getChartRenderingInfo();
        if (chart.getPlot() instanceof XYPlot) {
            XYPlot hvp = (XYPlot) chart.getPlot();
            ValueAxis axis = hvp.getDomainAxis();
            if (axis != null) {
                double anchorValue = axis.java2DToValue(
                    (float) x, info.getPlotInfo().getDataArea(), hvp.getDomainAxisEdge()
                );
                if (zoomFactor < 1.0) {
                    axis.resizeRange(zoomFactor, anchorValue);
                }
                else if (zoomFactor > 1.0) {
                    Range range = hvp.getDataRange(axis);
                    adjustRange(axis, range, zoomFactor, anchorValue);
                }
            }
        }
    }

    /**
     * Decreases the range on the vertical axis, centered about a Java2D y coordinate.
     * <P>
     * The range on the y axis is multiplied by zoomFactor
     *
     * @param y  the y coordinate in Java2D space.
     * @param zoomFactor  the zoomFactor < 1 == zoom in; else out.
     */
    private void zoomVertical(double y, double zoomFactor) {

        JFreeChart chart = this.chartPanel.getChart();
        ChartRenderingInfo info = this.chartPanel.getChartRenderingInfo();

        // 1. (left) Y-Axis

        if (chart.getPlot() instanceof XYPlot) {
            XYPlot vvp = (XYPlot) chart.getPlot();
            ValueAxis primYAxis = vvp.getDomainAxis();//getRangeAxis();
            if (primYAxis != null) {
                double anchorValue =
                    primYAxis.java2DToValue(
                        (float) y, info.getPlotInfo().getDataArea(), vvp.getRangeAxisEdge()
                    );
                if (zoomFactor < 1.0) {
                    // zoom in
                    primYAxis.resizeRange(zoomFactor, anchorValue);

                }
                else if (zoomFactor > 1.0) {
                    // zoom out
                    Range range = new Range(0, 200000);
                    adjustRange(primYAxis, range, zoomFactor, anchorValue);
                }
            }

            // 2. (right) Y-Axis

            if (chart.getPlot() instanceof XYPlot) {
                XYPlot xyp = (XYPlot) chart.getPlot();
                ValueAxis secYAxis = xyp.getRangeAxis();//SecondaryRangeAxis(0);
                if (secYAxis != null) {
                    double anchorValue =
                        secYAxis.java2DToValue(
                            (float) y,
                            info.getPlotInfo().getDataArea(),
                            xyp.getRangeAxisEdge(0));//getSecondaryRangeAxisEdge(0));
                    if (zoomFactor < 1.0) {
                        // zoom in
                        secYAxis.resizeRange(zoomFactor, anchorValue);

                    }
                    else if (zoomFactor > 1.0) {
                        // zoom out
                        Range range = new Range(0, 200000);
                        adjustRange(secYAxis, range, zoomFactor, anchorValue);
                    }
                }
            }
        }
    }

    /**
     * used for zooming
     *
     * @param axis  the axis.
     * @param range  the range.
     * @param zoomFactor  the zoom factor.
     * @param anchorValue  the anchor value.
     */
    private void adjustRange(ValueAxis axis, Range range, double zoomFactor, double anchorValue) {

        if (axis == null || range == null) {
            return;
        }

        double rangeMinVal = range.getLowerBound() - range.getLength() * axis.getLowerMargin();
        double rangeMaxVal = range.getUpperBound() + range.getLength() * axis.getUpperMargin();
        double halfLength = axis.getRange().getLength() * zoomFactor / 2;
        double zoomedMinVal = anchorValue - halfLength;
        double zoomedMaxVal = anchorValue + halfLength;
        double adjMinVal = zoomedMinVal;
        if (zoomedMinVal < rangeMinVal) {
            adjMinVal = rangeMinVal;
            zoomedMaxVal += rangeMinVal - zoomedMinVal;
        }
        double adjMaxVal = zoomedMaxVal;
        if (zoomedMaxVal > rangeMaxVal) {
            adjMaxVal = rangeMaxVal;
            zoomedMinVal -= zoomedMaxVal - rangeMaxVal;
            adjMinVal = Math.max(zoomedMinVal, rangeMinVal);
        }

        Range adjusted = new Range(adjMinVal, adjMaxVal);
        axis.setRange(adjusted);
    }
    // Variables declaration - do not modify
    private javax.swing.JPanel bigChartPanel;
    private javax.swing.JScrollPane bigChart_sp;
    private javax.swing.JPanel butPanel;
    private javax.swing.JButton editGraph_btn;
    private javax.swing.JButton save_btn;
    private javax.swing.JButton zoomInGraph_btn;
    private javax.swing.JButton zoomOutGraph_btn;
    // End of variables declaration
}
