
package org.jpowder.dataset.jfreechart;

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

public class EditChartFrame extends javax.swing.JFrame {

    private org.jfree.chart.JFreeChart jFreeChart;
    private org.jfree.chart.ChartPanel chartPanel;
    private EditChartFrame singletonObject;

    /** Creates new form EditChartFrame
     * @param chart JFreeChart Object
     */
    public EditChartFrame(org.jfree.chart.JFreeChart chart) {
        initComponents();
        jFreeChart = chart;
        chartPanel = new org.jfree.chart.ChartPanel(jFreeChart);
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

        zoomOutGraph_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/zoomo_small.gif"))); // NOI18N
        zoomOutGraph_btn.setToolTipText("Zoom out the graph");
        zoomOutGraph_btn.setIconTextGap(2);
        zoomOutGraph_btn.setMaximumSize(new java.awt.Dimension(40, 23));
        zoomOutGraph_btn.setMinimumSize(new java.awt.Dimension(40, 23));
        zoomOutGraph_btn.setPreferredSize(new java.awt.Dimension(40, 23));
        butPanel.add(zoomOutGraph_btn);

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
