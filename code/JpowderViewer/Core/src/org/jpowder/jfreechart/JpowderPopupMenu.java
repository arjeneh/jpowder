/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.jfreechart;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;

/**
 *
 * @author Arjeneh
 */
public class JpowderPopupMenu extends JPopupMenu implements ActionListener {

    private JFreeChart chart;
    private JPopupMenu popupMenu;
    private ChartPanel chartPanel;
    private XYPlot plot;
    private double X = -1;
    private JMenu zoomIn, zoomOut, autoRange;
    private JMenuItem menuItem;
    //

    public JpowderPopupMenu(final ChartPanel chartPanel) {

        popupMenu = new JPopupMenu();
        this.chartPanel = chartPanel;
        this.chart = this.chartPanel.getChart();
        chartPanel.setPopupMenu(popupMenu);
        initComponents();
//        this.plot = (XYPlot) this.chart.getPlot();

    }

    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();
        if (e.getActionCommand().contains("Properties")) {
            chartPanel.doEditChartProperties();

        } else if (e.getActionCommand().contains("Copy")) {
            chartPanel.doCopy();
        } //        else if (e.getActionCommand().contains("Save As...")) {
        //            try {
        //                chartPanel.doSaveAs();
        //            } catch (IOException ex) {
        //                Logger.getLogger(JpowderPopupMenu.class.getName()).log(Level.SEVERE, null, ex);
        //            }
        //        } else if (e.getActionCommand().contains("Print As...")) {
        //            chartPanel.createChartPrintJob();
        //        }
        else if (command.equals(ChartPanel.ZOOM_IN_BOTH_COMMAND)) {
            chartPanel.zoomInBoth(X, X);
        } else if (command.equals(ChartPanel.ZOOM_IN_DOMAIN_COMMAND)) {
            chartPanel.zoomInDomain(X, X);
        } else if (command.equals(ChartPanel.ZOOM_IN_RANGE_COMMAND)) {
            chartPanel.zoomInRange(X, X);
        } else if (command.equals(ChartPanel.ZOOM_OUT_BOTH_COMMAND)) {
            chartPanel.zoomOutBoth(X, X);
        } else if (command.equals(ChartPanel.ZOOM_OUT_DOMAIN_COMMAND)) {
            chartPanel.zoomOutDomain(X, X);
        } else if (command.equals(ChartPanel.ZOOM_OUT_RANGE_COMMAND)) {
            chartPanel.zoomOutRange(X, X);
        } else if (command.equals(ChartPanel.ZOOM_RESET_BOTH_COMMAND)) {
            chartPanel.restoreAutoBounds();
        } else if (command.equals(ChartPanel.ZOOM_RESET_DOMAIN_COMMAND)) {
            chartPanel.restoreAutoDomainBounds();
        } else if (command.equals(ChartPanel.ZOOM_RESET_RANGE_COMMAND)) {
            chartPanel.restoreAutoRangeBounds();
        }

    }

    public void initComponents() {


        popupMenu.add(menuItem = new JMenuItem("Properties"));
        menuItem.addActionListener(this);

        popupMenu.add(menuItem = new JMenuItem("Copy"));
        menuItem.addActionListener(this);

//        popupMenu.add(menuItem = new JMenuItem("Save As..."));
//        menuItem.addActionListener(this);
//
//        popupMenu.add(menuItem = new JMenuItem("Print As..."));
//        menuItem.addActionListener(this);


        popupMenu.addSeparator();
        popupMenu.add(zoomIn = new JMenu("Zoom In"));
        zoomIn.add(menuItem = new JMenuItem("Both Axes"));
        menuItem.setActionCommand(ChartPanel.ZOOM_IN_BOTH_COMMAND);
        menuItem.addActionListener(this);
        zoomIn.addSeparator();
        zoomIn.add(menuItem = new JMenuItem("Domain Axis"));
        menuItem.setActionCommand(ChartPanel.ZOOM_IN_DOMAIN_COMMAND);
        menuItem.addActionListener(this);
        zoomIn.add(menuItem = new JMenuItem("Range Axis"));
        menuItem.setActionCommand(ChartPanel.ZOOM_IN_RANGE_COMMAND);
        menuItem.addActionListener(this);


        popupMenu.add(zoomOut = new JMenu("Zoom Out"));
        popupMenu.addSeparator();
        zoomOut.add(menuItem = new JMenuItem("Both Axes"));
        menuItem.setActionCommand(ChartPanel.ZOOM_OUT_BOTH_COMMAND);
        menuItem.addActionListener(this);
        zoomOut.addSeparator();
        zoomOut.add(menuItem = new JMenuItem("Domain Axis"));
        menuItem.setActionCommand(ChartPanel.ZOOM_OUT_DOMAIN_COMMAND);
        menuItem.addActionListener(this);
        zoomOut.add(menuItem = new JMenuItem("Range Axis"));
        menuItem.setActionCommand(ChartPanel.ZOOM_OUT_RANGE_COMMAND);
        menuItem.addActionListener(this);


        popupMenu.add(autoRange = new JMenu("Auto Range"));
        autoRange.add(menuItem = new JMenuItem("Both Axes"));
        menuItem.setActionCommand(ChartPanel.ZOOM_RESET_BOTH_COMMAND);
        menuItem.addActionListener(this);
        autoRange.addSeparator();
        autoRange.add(menuItem = new JMenuItem("Domain Axis"));
        menuItem.setActionCommand(ChartPanel.ZOOM_RESET_DOMAIN_COMMAND);
        menuItem.addActionListener(this);
        autoRange.add(menuItem = new JMenuItem("Range Axis"));
        menuItem.setActionCommand(ChartPanel.ZOOM_RESET_RANGE_COMMAND);
        menuItem.addActionListener(this);
    }
}
