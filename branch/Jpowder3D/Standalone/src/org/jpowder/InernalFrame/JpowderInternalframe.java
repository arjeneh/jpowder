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
import org.jpowder.dataset.DataSet;
import org.jpowder.dataset.DatasetPlotter;

/**
 * Super class which contains all the common methods and fields
 * for 2D and 3D.
 */
public class JpowderInternalframe extends JInternalFrame {

    private static int INTERNALFRAME_WIDTH = 500, INTERNALFRAME_HEIGHT = 300;
    public Stack<JInternalFrame> internalframeStackes = new Stack<JInternalFrame>();
    private Preferences preferences = Preferences.userRoot();
    private Preferences myPrefs = preferences.node("Jpowder/InternalFrame/Dimension");
    private static final String key1 = "Width", key2 = "Highet";
    private DataVisibleInChart dataVisibleInChartPanel;
    private Vector<DataSet> m_data;
    private XYPlot xYPlot;  // hold reference to plot created from dataset in constructor
    private DatasetPlotter plotMultiCol;
    private ChartPanel jfreeChartPanel;
    private String name = new String();

    public JpowderInternalframe(DataVisibleInChart dataVisibleInChartPanel, Vector<DataSet> data) {

        this.dataVisibleInChartPanel = dataVisibleInChartPanel;
        m_data = data;
        if (Jpowder.getPlotsTab().getSelectedIndex() == 0) {
            plotMultiCol = DatasetPlotter.createDatasetPlotter(data);
        }
        if (Jpowder.getPlotsTab().getSelectedIndex() == 1) {
            plotMultiCol = DatasetPlotter.createDatasetPlotter(data, "");
        }

        ChartPanel jfreeChartPanels = plotMultiCol.createPowderChart();
        jfreeChartPanels.add(new JpowderPopupMenu(jfreeChartPanels));
        this.jfreeChartPanel = jfreeChartPanels;

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
        this.setSize(myPrefs.getInt(key1, Jpowder.getChartPlotter2D().getWidth() / 2),
                myPrefs.getInt(key2, INTERNALFRAME_HEIGHT));
        this.setLocation((int) Jpowder.getDropLocationX(), (int) Jpowder.getDropLocationY());
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                select();
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
     * moves the last created internal frame to the front and sets it selected.
     */
    public void select() {
        try {
            this.moveToFront();
            this.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }
    }


    public JInternalFrame getFrame() {
        return this;
    }

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
        return m_data;
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
    public DataVisibleInChart getDataVisibleInChartPanel() {
        return dataVisibleInChartPanel;
    }


    public Vector<DataSet> addDataset() {
        return m_data;
    }

}

