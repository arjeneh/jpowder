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
 * JpowderInternalframe2D.java
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
package org.jpowder.InernalFrame;

import org.jpowder.*;
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
import org.jfree.chart.JFreeChart;
import org.jpowder.dataset.DataSet;
import org.jpowder.jfreechart.FilesPlotter;
import org.jpowder.fileCabinet.PowderFileCabinet;

import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.XYPlot;

/**
 *
 * InternalFrame 2D
 */
public class JpowderInternalframe2D extends JpowderInternalframe implements DropTargetListener {

    private DataVisibleInChart dataVisibleInChartPanel;
    private Vector<DataSet> m_data;
    private java.awt.dnd.DropTarget dropTarget;  // to drop to this frame
    private XYPlot xYPlot;  // hold reference to plot created from dataset in constructor
    private Vector<Double> markedPeakPosition = new Vector<Double>();
    private List<Marker> peakRangeMarker = new ArrayList<Marker>();
    private List<Marker> peakDomainMarker = new ArrayList<Marker>();
    private int decimalPlaces;
    private NumberAxis xAxis;
    private NumberAxis yAxis;
    private JFreeChart chart;

    /**
     *
     * @param dataVisibleInChartPanel
     * @param data
     */
    public JpowderInternalframe2D(DataVisibleInChart dataVisibleInChartPanel, Vector<DataSet> data) {
        super(dataVisibleInChartPanel, data);
        m_data = data;
        this.dataVisibleInChartPanel = dataVisibleInChartPanel;
        xYPlot = this.getXYPlot();
        chart = FilesPlotter.getChart();
        dropTarget = new DropTarget(this, this);

    }


    public static int getnumberOfJpowderInternalframe() {
        return Jpowder.getChartPlotter2D().getAllFrames().length;
    }

    /**
     *
     * @param peaks
     */
    public void addMarkedPeakPosition(double peaks) {
        markedPeakPosition.add(peaks);
    }

    public Vector<Double> getMarkedPeakPosition() {
        return markedPeakPosition;
    }

    public void removeMarkedPeakPosition(double peaks) {
        markedPeakPosition.remove(peaks);
    }

    public void removeAllMarkedPeakPosition() {
        markedPeakPosition.clear();
    }

    public List<Marker> getPeakRangeMarker() {

        return peakRangeMarker;
    }


    public List<Marker> getPeakDomainMarker() {
        return peakDomainMarker;
    }

    public double getXAxis() {
        xAxis = (NumberAxis) getXYPlot().getDomainAxis();
        return xAxis.getTickUnit().getSize();
    }

    public double getYAxis() {
        yAxis = (NumberAxis) getXYPlot().getRangeAxis();
        return yAxis.getTickUnit().getSize();
    }

    public JFreeChart getChart() {
        return chart;
    }

    public int getDecimalPlaces() {
        return decimalPlaces;
    }

    public void setDecimalPlaces(int decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
    }

    /**
     *
     * @param dtde
     */
    @Override
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
            if (allDatasets == null) {
                return;
            }
            for (int iSet = 0; iSet < allDatasets.size(); iSet++) {
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

