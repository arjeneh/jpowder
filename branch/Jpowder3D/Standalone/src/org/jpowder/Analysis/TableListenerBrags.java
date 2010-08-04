/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.Analysis;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import org.jpowder.Jpowder;
import org.jpowder.InernalFrame.JpowderInternalframe2D;

/**
 *
 * @author qyt21516
 */
public class TableListenerBrags implements TableModelListener {

    private double newWaveLength;


//    public TableListenerBrags(double newWavelength) {
//
//        this.newWaveLength = newWavelength;
//
//    }


    @Override
    public void tableChanged(TableModelEvent e) {
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        int size = inFocus.getXYPlot().getDatasetCount();

        for (int i = 0; i < size; i++) {
    
                if (!BraggsLaw.getBragstable().getValueAt(i, 1).equals("")) {
                    newWaveLength = Double.parseDouble(BraggsLaw.getBragstable().getModel().
                            getValueAt(i, 1).toString());
                    inFocus.getPowderDataSet().get(i).setWaveLength(newWaveLength);

                }
            

        }
    }
}
