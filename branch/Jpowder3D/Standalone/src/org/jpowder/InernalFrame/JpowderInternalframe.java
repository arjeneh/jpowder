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
 * JpowderInternalFrme.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.InernalFrame;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.jpowder.*;
import java.util.Stack;
import java.util.Vector;
import java.util.prefs.Preferences;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.XYPlot;
import org.jpowder.Annotation.BalloonFrame;
import org.jpowder.dataset.DataSet;
import org.jpowder.dataset.DatasetPlotter;
import org.jpowder.util.NaturalOrderComparator;

/**
 * Super class which contains all the common methods and fields
 * for 2D and 3D.
 */
public class JpowderInternalframe extends JInternalFrame {

    public Stack<JInternalFrame> internalframeStackes = new Stack<JInternalFrame>();
    //
    private Preferences preferences = Preferences.userRoot();
    private Preferences myPrefs = preferences.node("Jpowder/InternalFrame/Dimension");
    private static final String key1 = "Width", key2 = "Highet";
    //
    private DataVisibleInChartPanel dataVisibleInChartPanel;
    protected Vector<DataSet> vectorDatasets;
    protected XYPlot xYPlot;  // hold reference to plot created from dataset in constructor.
    //
    protected DatasetPlotter plotMultiCol;
    //
    protected ChartPanel jfreeChartPanel;
    //Balloontip to be used with BalloonFrame.java for editing and deleting Annotation
    //in JpowderInternalframe2D/3D frames.
    protected ArrayList<BalloonFrame> balloonTips = new ArrayList<BalloonFrame>();//this should not be new but for testing 23/06/2012
//    protected BalloonAnnotation balloonPanel = new BalloonAnnotation();

    /**
     * ....
     * @param selectedMetaItem For 3D plotting
     */
    protected void doStuff(String selectedMetaItem) {
        //TODO: if nothing or null then prevent error.
        String str = selectedMetaItem;
        System.out.println("Str of Meta Data Item slected is: " + str);

        if (str == null && str.isEmpty()) {
            return;
        }
        // if 2D
        if (Jpowder.getPlotsTab().getSelectedIndex() == 0) {
            plotMultiCol = DatasetPlotter.createDatasetPlotter(vectorDatasets);
        }
        // if 3D
        if (Jpowder.getPlotsTab().getSelectedIndex() == 1) {
            plotMultiCol = DatasetPlotter.createDatasetPlotter(vectorDatasets, selectedMetaItem);
        }

        ChartPanel jfreeChartPanels = plotMultiCol.createPowderChart();
        jfreeChartPanels.add(new JpowderPopupMenu(jfreeChartPanels));
        this.jfreeChartPanel = jfreeChartPanels;
        //need to add name for the chart panel, so it can be a reference to Annotation later.
        this.jfreeChartPanel.setName(getNames());

        xYPlot = jfreeChartPanels.getChart().getXYPlot();
        this.add(jfreeChartPanels);

        internalframeStackes.push(this);

        this.setTitle(getNames());
        this.setVisible(true);
        this.setClosable(true);
        this.setMaximizable(true);
        this.setResizable(true);
        this.setIconifiable(true);
        this.setFrameIcon(new ImageIcon(getClass().getResource("/images/JpowderLogo.png")));

//        this.setSize(
//                myPrefs.getInt(key1, Jpowder.getChartPlotter2D().getWidth() / 2),
//                myPrefs.getInt(key2, INTERNALFRAME_HEIGHT)
//                );
        this.setLocation((int) Jpowder.getDropLocationX(), (int) Jpowder.getDropLocationY());
        this.pack();
        
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                makeActiveMoveFront();
            }
        });

        addInternalFrameListener(new InternalFrameAdapter() {

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                myPrefs.putInt(key1, getWidth());
                myPrefs.putInt(key2, getHeight());

            }
        });
    }

    /**
     * @param selectedMetaItem For 3D plotting
     * @param fileNameAndPath For file names displayed on Y axis.
     */
    protected void doStuff(String selectedMetaItem, HashMap fileNameAndPath) {
        //TODO: if nothing or null then prevent error.
        String str = selectedMetaItem;
        System.out.println("Str of Meta Data Item slected is: " + str);

        if (str == null && str.isEmpty()) {
            return;
        }
        // if 2D
        if (Jpowder.getPlotsTab().getSelectedIndex() == 0) {
            plotMultiCol = DatasetPlotter.createDatasetPlotter(vectorDatasets);
        }
        // if 3D
        if (Jpowder.getPlotsTab().getSelectedIndex() == 1) {
            //plot by name as a default.
            plotMultiCol = DatasetPlotter.createDatasetPlotter(vectorDatasets, selectedMetaItem);
        }

        ChartPanel jfreeChartPanels = plotMultiCol.createPowderChart();
        jfreeChartPanels.add(new JpowderPopupMenu(jfreeChartPanels));
        this.jfreeChartPanel = jfreeChartPanels;
        //need to add name for the chart panel, so it can be a reference to Annotation later.
        this.jfreeChartPanel.setName(getNames());

        xYPlot = jfreeChartPanels.getChart().getXYPlot();
        this.add(jfreeChartPanels);

        internalframeStackes.push(this);
        this.setTitle(getNames());
        this.setVisible(true);
        this.setClosable(true);
        this.setMaximizable(true);
        this.setResizable(true);//disable this to false to make the JFreeChart not redraw.
        this.setIconifiable(true);
        this.setFrameIcon(new ImageIcon(getClass().getResource("/images/JpowderLogo.png")));
        this.setLocation((int) Jpowder.getDropLocationX(), (int) Jpowder.getDropLocationY());
        this.pack();

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                makeActiveMoveFront();
            }
        });

        addInternalFrameListener(new InternalFrameAdapter() {

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                myPrefs.putInt(key1, getWidth());
                myPrefs.putInt(key2, getHeight());
            }
        });
    }

    /**
     *
     * @param dataVisibleInChartPanel
     * @param data
     */
    public JpowderInternalframe(DataVisibleInChartPanel dataVisibleInChartPanel, Vector<DataSet> data) {
        this.dataVisibleInChartPanel = dataVisibleInChartPanel;
        vectorDatasets = data;
        //this.makeActiveMoveFront(); //move the frame to the front.
    }

    /**
     *
     * @param data
     */
    public JpowderInternalframe(Vector<DataSet> data) {
        vectorDatasets = data;
        //this.makeActiveMoveFront(); //move the frame to the front.
    }

    /**
     * moves the last created internal frame to the front and sets it selected.
     */
    public void makeActiveMoveFront() {
        try {
            moveToFront();
            setVisible(true);
            setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }
        requestFocus();
    }

    public JInternalFrame getFrame() {
        return this;
    }

    /**
     * Loops over the names of the file which are plotted.
     * @return returning the name of files added.
     */
    public String getNames() {
        String god = "";

        // if one file
        int i = vectorDatasets.size();

        if (i <= 1) {
            god = vectorDatasets.firstElement().getFileName();
        } else {

            List<String> list = new ArrayList<String>();
            for (int index = 0; index < vectorDatasets.size(); index++) {
                list.add(vectorDatasets.get(index).getFileName());
            }

            Collections.sort(list, new NaturalOrderComparator());
            //if more than one file
            String first = list.get(0);
            String last = list.get(list.size() - 1);

            god = first + " - " + last;
        }
        return god;
    }

    public String getNames(int i) {
        return vectorDatasets.elementAt(i).getFileName();
    }

    /**
     * this methods return ChartPanel.
     * @return jfreeChartPanel
     */
    public ChartPanel getChartPanel() {
        return jfreeChartPanel;
    }

    public XYPlot getXYPlot() {
        return xYPlot;
    }

    public Vector<DataSet> getPowderDataSet() {
        return vectorDatasets;
    }

    /**
     *
     * @param comp
     * @return super.add(comp)
     */
    @Override
    public Component add(Component comp) {
        return super.add(comp);
    }

    /**
     * @return the chtpnl
     */
    public DataVisibleInChartPanel getDataVisibleInChartPanel() {
        return dataVisibleInChartPanel;
    }

    public Vector<DataSet> addDataset() {
        return vectorDatasets;
    }
}

