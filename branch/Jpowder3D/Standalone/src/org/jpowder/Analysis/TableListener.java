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
public class TableListener implements TableModelListener {

    private double newWaveLength;
    private double newDifC;
    private double newDifA;
    private double newZero;

    public TableListener(double newWavelength) {

        this.newWaveLength = newWavelength;

    }

    public TableListener(double newDifC, double newDifA, double newZero) {
        this.newDifC = newDifC;
        this.newDifA = newDifA;
        this.newZero = newZero;

    }

    public void tableChanged(TableModelEvent e) {
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        int size = inFocus.getXYPlot().getDatasetCount();
        for (int i = 0; i < size; i++) {


         
    
                if (!BraggsLaw.getBragstable().getValueAt(i, 1).equals("")) {
                    newWaveLength = Double.parseDouble(BraggsLaw.getBragstable().getModel().
                            getValueAt(i, 1).toString());
                    inFocus.getPowderDataSet().get(i).setWaveLength(newWaveLength);

                }
            
//                for (int j = 0; j < 4; j++) {
//                    if (GSASTable.getGSASTable().getValueAt(i, j).equals("")) {
//                        newDifC = Double.parseDouble(BraggsLaw.getBragstable().getModel().
//                                getValueAt(i, 1).toString());
//                        newDifA = Double.parseDouble(BraggsLaw.getBragstable().getModel().
//                                getValueAt(i, 2).toString());
//                        newZero = Double.parseDouble(BraggsLaw.getBragstable().getModel().
//                                getValueAt(i, 3).toString());
//
//                        inFocus.getPowderDataSet().get(i).getGSAS_Instrument().setDifC(newDifC);
//                        inFocus.getPowderDataSet().get(i).getGSAS_Instrument().setDifA(newDifA);
//                        inFocus.getPowderDataSet().get(i).getGSAS_Instrument().setZero(newZero);
//
//
//                    }
//                }
            



        }
    }
}
