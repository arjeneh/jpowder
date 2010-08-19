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
 * InternalFrame3D.java
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

import org.jpowder.*;
import java.util.Vector;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jpowder.dataset.DataSet;
import org.jpowder.jfreechart.FilesPlotter3D;

/**
 *
 * Internal Frame 3D
 */
public class JpowderInternalframe3D extends JpowderInternalframe{
    private XYPlot xYPlot;  // hold reference to plot created from dataset in constructor
    private JFreeChart chart;
    /**
     *
     * @param dataVisibleInChartPanel
     * @param data
     */
    public JpowderInternalframe3D(DataVisibleInChart dataVisibleInChartPanel, Vector<DataSet> data) {
        super(dataVisibleInChartPanel,data);
        xYPlot = this.getXYPlot();
        chart=FilesPlotter3D.getChart();
        
    }

    public static int getnumberOfJpowderInternalframe() {
        return Jpowder.getChartPlotter3D().getAllFrames().length;
    }

    public JFreeChart getChart(){
        return chart;
    }
}
