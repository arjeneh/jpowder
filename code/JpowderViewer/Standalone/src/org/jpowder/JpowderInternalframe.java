/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.io.IOException;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import org.jpowder.dataset.DataSet;
import org.jpowder.dataset.DatasetPlotter;
import org.jpowder.jfreechart.FilesPlotter;
import org.jpowder.fileCabinet.PowderFileCabinet;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

/**
 *
 * @author qyt21516
 */
public class JpowderInternalframe extends JInternalFrame implements DropTargetListener {

    private DataVisibleInChart dataVisibleInChartPanel;
    private Vector<DataSet> m_data;
    private java.awt.dnd.DropTarget dropTarget;  // to drop to this frame
    private XYPlot xYPlot;  // hold reference to plot created from dataset in constructor
    private static int numberOfJpowderInternalframe = 0;
    private DatasetPlotter plotMultiCol;
    private ChartPanel jfreeChartPanel;
    private JFreeChart chart;
    private static int datasetCount = 0;
    private Vector<Double> markedPeakPosition = new Vector<Double>();
    public static int left;
    public static int top;
    private static int INTERNALFRAME_WIDTH = 300, INTERNALFRAME_HEIGHT = 300;
    private String name = new String();
    private DataSet oneDataset = null;
    public Stack<JInternalFrame> internalframeStackes = new Stack<JInternalFrame>();

    /**
     *
     * @param dataVisibleInChartPanel
     * @param data
     */
    public JpowderInternalframe(DataVisibleInChart dataVisibleInChartPanel, Vector<DataSet> data) {
        super();

        numberOfJpowderInternalframe++;

        internalframeStackes.push(this);
        javax.swing.JPanel chartPanel = new javax.swing.JPanel();

        this.dataVisibleInChartPanel = dataVisibleInChartPanel;
        this.add(chartPanel);
        m_data = data;
        plotMultiCol = DatasetPlotter.createDatasetPlotter(data);
        chartPanel.setLayout(new BorderLayout());
        ChartPanel jfreeChartPanels = plotMultiCol.createPowderChart();
        this.jfreeChartPanel = jfreeChartPanels;
        chart = FilesPlotter.getchart();
        xYPlot = jfreeChartPanels.getChart().getXYPlot();
        chartPanel.add(jfreeChartPanels);
        JPowder.jpowderInternalFrameUpdate(this);
        System.out.println("Internalframe created");
        dropTarget = new DropTarget(this, this);

        dataVisibleInChartPanel.newChartInFocus(xYPlot,
                this.getPowderDataSet());


        setTitle(getNames());
        this.setVisible(true);
        this.setClosable(true);
        this.setMaximizable(true);
        this.setResizable(true);
        this.setIconifiable(true);
        this.setSize(INTERNALFRAME_WIDTH, INTERNALFRAME_HEIGHT);

        this.setLocation((int) JPowder.getDropLocationX(), (int) JPowder.getDropLocationY());
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                select();
            }
        });
    }

    /**
     *Increment the location of each interframe is created by a certain value.
     *
    public static void increment() {
    left += 30;
    top += 30;

    System.out.println("top" + top);
    System.out.println("Left" + left);
    if (top == (height / 2)) {
    left += width;
    top = 0;

    }
    if (left == (width * 3)) {
    left = 0;
    top += width + (width / 2.5);
    }
    if (top == (height * 1.9)) {
    left += width;
    top = 420;

    }

    }
     */
    /**
     * Loops over the names of the file which are plotted.
     * @return returning the name of files added.
     */
    public String getNames() {
        for (int i = 0; i < xYPlot.getDatasetCount(); i++) {
            name = m_data.elementAt(0).getFileName();
        }
        return name;
    }

    public String getNames(int i) {
        return m_data.elementAt(i).getFileName();
    }

    /**
     * moves the last created internal frame to the front and sets it selected.
     */
    public void select() {
        try {
            this.moveToFront();
            this.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }
    }

    /**
     *
     * @return
     */
//    public static int countNumberDatasetPlotted() {
//        return datasetCount;
//
//    }
//
//    public void setSeriesPaint() {
//        for (int i = 0; i < datasetCount; i++) {
//            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) getXYPlot().getRenderer();
//            renderer.setSeriesPaint(i, FilesPlotter.getSeriesColors(datasetCount));
//        }
//    }
    /**
     * this methods return ChartPanel.
     * @return
     */
    public ChartPanel getChartPanel() {
        return jfreeChartPanel;
    }

    /**
     *
     * @return
     */
    public XYPlot getXYPlot() {
        return xYPlot;
    }

    /**
     *
     * @return
     */
    public static int getnumberOfJpowderInternalframe() {
        return numberOfJpowderInternalframe;
    }

    /**
     *
     */
    public static int decrementnumberOfJpowderInternalframe() {
        numberOfJpowderInternalframe--;
        return numberOfJpowderInternalframe;
    }

    /**
     *
     * @return
     */
    public Vector<DataSet> getPowderDataSet() {
        return m_data;
    }

    /**
     *
     * @param comp
     * @return
     */
    @Override
    public Component add(Component comp) {
        return super.add(comp);
    }

    /**
     * @return the chtpnl
     */
    public DataVisibleInChart getDataVisibleInChartPanel() {
        return dataVisibleInChartPanel;
    }

    /**
     *
     * @return
     */
    public Vector<DataSet> addDataset() {
        return m_data;
    }

    /**
     *
     * @param peaks
     */
    public void addMarkedPeakPosition(double peaks) {
        markedPeakPosition.add(peaks);
    }

    /**
     *
     * @return
     */
    public Vector<Double> getMarkedPeakPosition() {
        return markedPeakPosition;
    }

    public Vector<Double> removeMarkedPeakPosition() {
        return markedPeakPosition;
    }

    public void removeMarkedPeakPosition(double peaks) {
        markedPeakPosition.remove(peaks);
    }

    public void removeAllMarkedPeakPosition() {
        markedPeakPosition.clear();
    }

    /**
     *
     * @return
     */
    public JFreeChart getchart() {
        return chart;
    }

    public void dragEnter(DropTargetDragEvent dtde) {
    }

    public void dragOver(DropTargetDragEvent dtde) {
    }

    public void dropActionChanged(DropTargetDragEvent dtde) {
    }

    public void dragExit(DropTargetEvent dte) {
    }

    /**
     *
     * @param dtde
     */
    public void drop(DropTargetDropEvent dtde) {

        JPowder.jpowderInternalFrameUpdate(this);
        ArrayList<File> allfiles = new ArrayList<File>();
        ArrayList<String> allfilesName = new ArrayList<String>();
        Transferable transfeable = dtde.getTransferable();
        DataFlavor[] flavors = transfeable.getTransferDataFlavors();
        dtde.acceptDrop(DnDConstants.ACTION_COPY);

        // Find all filenames which have been draged into the chart plotter area
        // and to allFilenames

        for (int i = 0; i < flavors.length; i++) {
            if (flavors[i].isFlavorJavaFileListType()) {
                try {
                    // populate allFilenames
                    java.util.List<File> list = (java.util.List) transfeable.getTransferData(flavors[i]);
                    for (int j = 0; j < list.size(); j++) {
                        File file = list.get(j);
                        String fileName = file.getName().toLowerCase();
                        allfiles.add(file);
                        allfilesName.add(fileName);

                    }
                    System.out.println("files added to Internalframe " + allfiles);

                } catch (UnsupportedFlavorException ex) {
                    Logger.getLogger(JPowder.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(JPowder.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NullPointerException ex) {
                    Logger.getLogger(JPowder.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (flavors[i].equals(DataFlavor.stringFlavor)) {

                try {
                    // populate allFilenames
                    String fileName;
                    fileName = (String) transfeable.getTransferData(DataFlavor.stringFlavor);
                    String[] array = fileName.split("\n");
                    int n = array.length;
                    for (int j = 0; j < n; j++) {
                        System.out.println("\n" + array[j] + "\n");
                        File file = new File(array[j]);
                        String fileNames = file.getName().toLowerCase();
                        allfiles.add(file);
                        allfilesName.add(fileNames);

                    }
                    System.out.println("files added to Internalframe \n" + allfiles);
                } catch (Exception ex) {
                }
            } else {
            }
        }
        // create vector of DataSet
        int numGoodFilenames = 0;
        Vector<DataSet> toPass = new Vector<DataSet>();
        for (int i = 0; i < allfiles.size(); i++) {

            oneDataset = null;
            oneDataset = PowderFileCabinet.createDataSetFromPowderFile(allfiles.get(i));
            if (oneDataset != null) {
                m_data.add(oneDataset);
                numGoodFilenames = numGoodFilenames + 1;
                toPass.add(oneDataset);
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "Only ASCII file please.");
                break;
            }
        }
        if (numGoodFilenames > 0) {
            FilesPlotter.addDataToJpowderInternalFrame(xYPlot,
                    toPass);
            dataVisibleInChartPanel.newChartInFocus(xYPlot,
                    this.getPowderDataSet());
        }

    }
}

/**
 *
 * @author
 */
class InternalFrameIconifyListener extends InternalFrameAdapter {

    private DataVisibleInChart dataVisibleInChart;
    private JpowderInternalframe jpowderinternalframe;

    /**
     *
     * @param dataVisibleInChart
     */
    public InternalFrameIconifyListener(DataVisibleInChart visibleInChart) {
        this.dataVisibleInChart = visibleInChart;
    }

    /**
     *
     * @param e
     */
    @Override
    public void internalFrameClosed(InternalFrameEvent e) {
        JPowder.jPowderStackUndo.push(jpowderinternalframe);
        System.out.println("n frame " + JpowderInternalframe.decrementnumberOfJpowderInternalframe());
        if (JpowderInternalframe.getnumberOfJpowderInternalframe() > 1 || JpowderInternalframe.getnumberOfJpowderInternalframe() == 0) {
            dataVisibleInChart.clear();
        }

        JPowder.jpowderInternalFrameUpdate(jpowderinternalframe);
    }

    /**
     *
     * @param e
     */
    @Override
    public void internalFrameActivated(InternalFrameEvent e) {

        jpowderinternalframe = (JpowderInternalframe) e.getInternalFrame();
        JPowder.jpowderInternalFrameUpdate(jpowderinternalframe);

        dataVisibleInChart = jpowderinternalframe.getDataVisibleInChartPanel();
        dataVisibleInChart.newChartInFocus(jpowderinternalframe.getXYPlot(),
                jpowderinternalframe.getPowderDataSet());
    }

    /**
     *
     * @param e
     */
    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {
        //System.out.println("widows is DeActivated");
    }
}
