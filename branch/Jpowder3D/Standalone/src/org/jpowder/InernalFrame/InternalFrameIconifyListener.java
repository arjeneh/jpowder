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
 * InternalFrameIconifyListener.java
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

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import org.jpowder.DataVisibleInChart;
import org.jpowder.Jpowder;

/**
 * Check the state of JpowderInternalFrame and if they added or removed
 * from PlotsArea then updates the Data Visible in chart panel.
 * 
 */
public class InternalFrameIconifyListener extends InternalFrameAdapter {

    private DataVisibleInChart dataVisibleInChart;
    private JpowderInternalframe jpowderinternalframe;

    /**
     *
     * @param visibleInChart
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
        
        Jpowder.getChartPlotter2D().remove(jpowderinternalframe);
        Jpowder.getChartPlotter3D().remove(jpowderinternalframe);
        if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() == 0) {
            dataVisibleInChart.clear();
        }
            if (JpowderInternalframe3D.getnumberOfJpowderInternalframe() == 0) {
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
