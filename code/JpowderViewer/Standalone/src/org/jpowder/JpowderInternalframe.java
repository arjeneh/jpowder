/* ===========================================================
 * This file is part of Jpowder, see <http://www.jpowder.org/>
 * ===========================================================
 *
 * Jpowder is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jpowder is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * ---------
 * JpowderInternalframe.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *             Anders Marvardsen, ISIS, Rutherford Appleton Laboratory
 *             Dan Badham, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
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
import java.util.List;
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
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.XYPlot;
import org.jpowder.Analysis.ToolsIcon;


/**
 *
 * @author M Arjeneh
 */
public class JpowderInternalframe extends JInternalFrame implements DropTargetListener {

    private DataVisibleInChart dataVisibleInChartPanel;
    private Vector<DataSet> m_data;
    private java.awt.dnd.DropTarget dropTarget;  // to drop to this frame
    private XYPlot xYPlot;  // hold reference to plot created from dataset in constructor
    //public static int numberOfJpowderInternalframe = 0;
    private DatasetPlotter plotMultiCol;
    private ChartPanel jfreeChartPanel;
    private JFreeChart chart;
    private static int datasetCount = 0;
    private Vector<Double> markedPeakPosition = new Vector<Double>();
    public static int left;
    public static int top;
    private static int INTERNALFRAME_WIDTH = 500, INTERNALFRAME_HEIGHT = 300;
    private String name = new String();
    private DataSet oneDataset = null;
    public Stack<JInternalFrame> internalframeStackes = new Stack<JInternalFrame>();
        private List<Marker> peakRangeMarker = new ArrayList<Marker>();
    private List<Marker> peakDomainMarker = new ArrayList<Marker>();
      private NumberAxis xAxis ;
            private NumberAxis yAxis ;

    /**
     *
     * @param dataVisibleInChartPanel
     * @param data
     */
    public JpowderInternalframe(DataVisibleInChart dataVisibleInChartPanel, Vector<DataSet> data) {
        super();

        dropTarget = new DropTarget(this, this);
        internalframeStackes.push(this);
        javax.swing.JPanel chartPanel = new javax.swing.JPanel();
        this.dataVisibleInChartPanel = dataVisibleInChartPanel;
        this.add(chartPanel);
        m_data = data;
        plotMultiCol = DatasetPlotter.createDatasetPlotter(data);
        chartPanel.setLayout(new BorderLayout());
        ChartPanel jfreeChartPanels = plotMultiCol.createPowderChart();
        jfreeChartPanels.add(new JpowderPopupMenu(jfreeChartPanels));
        this.jfreeChartPanel = jfreeChartPanels;
        chart = FilesPlotter.getChart();
        xYPlot = jfreeChartPanels.getChart().getXYPlot();
        chartPanel.add(jfreeChartPanels);

 
        this.setTitle(getNames());
        this.setVisible(true);
        this.setClosable(true);
        this.setMaximizable(true);
        this.setResizable(true);
        this.setIconifiable(true);
        this.setFrameIcon(new ImageIcon(getClass().getResource("/images/JpowderLogo.png")));
        this.setSize(Jpowder.getChartPlotter().getWidth() / 2, INTERNALFRAME_HEIGHT);

        this.setLocation((int) Jpowder.getDropLocationX(), (int) Jpowder.getDropLocationY());
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

//        System.out.println("top" + top);
//        System.out.println("Left" + left);
        if (top == (INTERNALFRAME_HEIGHT / 2)) {
            left += INTERNALFRAME_WIDTH;
            top = 0;

        }
        if (left == (INTERNALFRAME_WIDTH * 3)) {
            left = 0;
            top += INTERNALFRAME_WIDTH + (INTERNALFRAME_WIDTH / 2.5);
        }
        if (top == (INTERNALFRAME_HEIGHT * 1.9)) {
            left += INTERNALFRAME_WIDTH;
            top = 420;

        }

    }
     * */

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
        return Jpowder.getChartPlotter().getAllFrames().length;
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
    public List<Marker> getPeakRangeMarker() {

        return peakRangeMarker;
    }

    /**
     *
     * @return
     */
    public List<Marker> getPeakDomainMarker() {
        return peakDomainMarker;
    }

    /**
     *
     * @return
     */
    public JFreeChart getchart() {
        return chart;
    }

    public double  getXAxis(){
        xAxis= (NumberAxis) getXYPlot().getDomainAxis();
        return xAxis.getTickUnit().getSize();
    }
    public double  getYAxis(){
        yAxis= (NumberAxis) getXYPlot().getRangeAxis();
        return yAxis.getTickUnit().getSize();
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

        Jpowder.jpowderInternalFrameUpdate(this);
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


                } catch (UnsupportedFlavorException ex) {
                    Logger.getLogger(Jpowder.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Jpowder.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NullPointerException ex) {
                    Logger.getLogger(Jpowder.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (flavors[i].equals(DataFlavor.stringFlavor)) {

                try {
                    // populate allFilenames
                    String fileName;
                    fileName = (String) transfeable.getTransferData(DataFlavor.stringFlavor);
                    String[] array = fileName.split("\n");
                    int n = array.length;
                    for (int j = 0; j < n; j++) {

                        File file = new File(array[j]);
                        String fileNames = file.getName().toLowerCase();
                        allfiles.add(file);
                        allfilesName.add(fileNames);

                    }

                } catch (Exception ex) {
                }
            } else {
            }
        }
        // create vector of DataSet
        int numGoodFilenames = 0;
        Vector<DataSet> toPass = new Vector<DataSet>();
        for (int i = 0; i < allfiles.size(); i++) {

            Vector<DataSet> allDatasets = PowderFileCabinet.createDataSetFromPowderFile(allfiles.get(i));
              if(allDatasets==null){
                  return;
                  }
            for (int iSet = 0; iSet < allDatasets.size(); iSet++)
            {
              if (allDatasets.elementAt(iSet) != null) {
                m_data.add(allDatasets.elementAt(iSet));
                numGoodFilenames = numGoodFilenames + 1;
                toPass.add(allDatasets.elementAt(iSet));
              } else {
//                javax.swing.JOptionPane.showMessageDialog(null, "Only ASCII file please.");
                break;
              }
            }
        }
        if (numGoodFilenames > 0) {
            FilesPlotter.addDataToJpowderInternalFrame(xYPlot,
                    toPass);
            dataVisibleInChartPanel.newChartInFocus(xYPlot,
                    this.getPowderDataSet());
        }
         Jpowder.jpowderInternalFrameUpdate(this);
         Jpowder.moemoryChecker();
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
        Jpowder.jPowderStackUndo.push(jpowderinternalframe);        
        Jpowder.jpowderInternalFrameUpdate(jpowderinternalframe);
        Jpowder.getChartPlotter().remove(jpowderinternalframe);
         if (JpowderInternalframe.getnumberOfJpowderInternalframe()==0) {
            dataVisibleInChart.clear();
       }
    }

    /**
     *
     * @param e
     */
    @Override
    public void internalFrameActivated(InternalFrameEvent e) {

        jpowderinternalframe = (JpowderInternalframe) e.getInternalFrame();
        Jpowder.jpowderInternalFrameUpdate(jpowderinternalframe);

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
