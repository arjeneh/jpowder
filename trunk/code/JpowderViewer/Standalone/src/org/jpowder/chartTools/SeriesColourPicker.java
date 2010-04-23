/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.chartTools;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import org.jpowder.Jpowder;
import org.jpowder.JpowderInternalframe;

/**
 * This Class allow user to select a colour for a plot.
 * @author M Arjeneh
 */
public class SeriesColourPicker {

    public SeriesColourPicker() {

        final JColorChooser chooser = new JColorChooser();

        ActionListener okListener = new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

                JpowderInternalframe inFocus = Jpowder.internalFrameInFocus;
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
