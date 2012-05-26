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
 * TabelListenerBrags.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.Analysis;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import org.jpowder.Jpowder;
import org.jpowder.InernalFrame.JpowderInternalframe2D;

/**
 * listen for change event in the table and if value is changed then
 * it is set as the new WaveLength value in DataSet Class as well.
 * 
 */
public class TableListenerBraggsLaw implements TableModelListener {

    private double newWaveLength;

    @Override
    public void tableChanged(TableModelEvent e) {
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        int size = inFocus.getXYPlot().getDatasetCount();

        for (int i = 0; i < size; i++) {   
               if (!Transforming_XAxis.getBragstable().getValueAt(i, 1).equals("")) {
                    newWaveLength = Double.parseDouble(Transforming_XAxis.getBragstable().getModel().
                            getValueAt(i, 1).toString());
                    inFocus.getPowderDataSet().get(i).setWaveLength(newWaveLength);
                }
        }
    }
}
