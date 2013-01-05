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
 * JCheckBoxJList.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  Kreecha Puphaiboon, Computer Science Lecturer, Kasem Bundit University
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.JCheckboxList;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import org.jfree.chart.plot.XYPlot;
import org.jpowder.InternalFrame.JpowderInternalframe3D;
import org.jpowder.Jpowder;
import org.jpowder.jfreechart.JpowderXYBlockRenderer;

/**
 * Used to display a JList with tickboxes. 
 */
public class JCheckBoxJList extends JList implements Serializable {

    public static final String PROP_SAMPLE_PROPERTY = "sampleProperty";
    private String sampleProperty;
    private PropertyChangeSupport propertySupport;
    private DefaultListModel model;
    private XYPlot m_plot = null;

    public JCheckBoxJList(DefaultListModel model) {
        super(model);
        this.model = model;

        setCellRenderer(new CheckFileListRenderer());
        setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);


        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                // get handle of list containing all the tickboxes
                JCheckBoxJList list = (JCheckBoxJList) e.getSource();

                // get index of the item (tickbox) clicked in list
                int index = list.locationToIndex(e.getPoint());

                // get handle of the item clicked
                CheckableFileItem item = (CheckableFileItem) list.getModel().getElementAt(index);

                // turn off/on tick in tickbox
                item.setSelected(!item.isSelected());

                // this neccessary for checking and unchecking the jcheckbox list
                java.awt.Rectangle rect = list.getCellBounds(index, index);
                list.repaint(rect);

                // check what dataset has been used then try remove the Plot
                // since jfreechart does not support this feature I had to
                // modfied code in the JpowderXYBlockRenderer to try to remove
                // the series.
                String dataSetType = m_plot.getDataset().toString();
                if (!dataSetType.substring(0, dataSetType.indexOf("@")).equals("org.jfree.data.xy.DefaultXYZDataset")) {
                    if (!item.isSelected()) {
                        m_plot.getRenderer(index).setSeriesVisible(0, Boolean.FALSE);
                    }
                    if (item.isSelected()) {
                        m_plot.getRenderer(index).setSeriesVisible(0, Boolean.TRUE);
                    }
                } else {
                    JpowderInternalframe3D inFocus = Jpowder.internalFrameInFocus3D;
                    JpowderXYBlockRenderer renderer = (JpowderXYBlockRenderer) inFocus.getXYPlot().getRenderer();
                    boolean set = renderer.isSeriesVisible(index);
                    renderer.setSeriesVisible(index, !set);
                }
            }
        });

        propertySupport = new PropertyChangeSupport(this);
        this.repaint();
    }

    public XYPlot getm_plot() {
        return m_plot;
    }

    public void setm_plot(XYPlot plot) {
        this.m_plot = plot;
    }

    public String getSampleProperty() {
        return sampleProperty;
    }

    public void setSampleProperty(String value) {
        String oldValue = sampleProperty;
        sampleProperty = value;
        propertySupport.firePropertyChange(PROP_SAMPLE_PROPERTY, oldValue, sampleProperty);
    }
}
