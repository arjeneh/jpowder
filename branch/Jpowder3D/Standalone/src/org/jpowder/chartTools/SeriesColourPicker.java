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
 * SeriesColourPicker.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.chartTools;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import org.jpowder.Jpowder;
import org.jpowder.InernalFrame.JpowderInternalframe2D;

/**
 * This Class allow user to select a colour for a plot.
 * 
 */
public class SeriesColourPicker {

    public SeriesColourPicker() {

        final JColorChooser chooser = new JColorChooser();

        ActionListener okListener = new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

                JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus;
                Color newColor = chooser.getColor();
                for (int i = 0; i < inFocus.getXYPlot().getDatasetCount(); i++) {
                    if (inFocus.getPowderDataSet().elementAt(i).getFileName().equals(
                            ChangePlotStyle.getSeriesColourComboBox().getSelectedItem())) {
                        inFocus.getXYPlot().getRenderer(i).setSeriesPaint(0, newColor);
                    }
                }
            }
        };


        ActionListener cancelListener = new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                return;
            }
        };

        
        JDialog dialog = JColorChooser.createDialog(chooser, null, true, chooser, okListener, cancelListener);
        dialog.setVisible(true);
    }

}
