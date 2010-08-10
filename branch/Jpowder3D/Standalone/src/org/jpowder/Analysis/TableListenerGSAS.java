/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.Analysis;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import org.jpowder.InernalFrame.JpowderInternalframe2D;
import org.jpowder.Jpowder;
import org.jpowder.dataset.GSASInstrument_Reader;
import org.jpowder.dataset.GSAS_Instrument;

/**
 *
 * @author qyt21516
 */
public class TableListenerGSAS implements TableModelListener {

    private double newDifC;
    private double newDifA;
    private double newZero;
    private JTable gSasTable;
    private GSAS_Instrument gsasi;

    public TableListenerGSAS(JTable gSASTable) {
        this.gSasTable = gSASTable;
        gsasi = new GSAS_Instrument(newDifC, newDifC, newZero);
    }

//    public TableListenerGSAS(double newDifC, double newDifA, double newZero) {
//        this.newDifC = newDifC;
//        this.newDifA = newDifA;
//        this.newZero = newZero;
//
//    }
    @Override
    public void tableChanged(TableModelEvent e) {
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        int size = inFocus.getXYPlot().getDatasetCount();

        for (int i = 0; i < size; i++) {



            for (int j = 1; j < 4; j++) {


                if (!gSasTable.getValueAt(i, 1).equals("")) {
                    newDifC = Double.parseDouble(gSasTable.getModel().
                            getValueAt(i, 1).toString());

                }
                if (!gSasTable.getValueAt(i, 2).equals("")) {
                    newDifA = Double.parseDouble(gSasTable.getModel().
                            getValueAt(i, 2).toString());
                }
                if (!gSasTable.getValueAt(i, 3).equals("")) {

                    newZero = Double.parseDouble(gSasTable.getModel().
                            getValueAt(i, 3).toString());
                }
                System.out.println(newDifC);


                if (newDifC != 0) {

//                        inFocus.getPowderDataSet().get(i).getGSAS_Instrument().setDifC(newDifC);
                    gsasi.setDifC(newDifC);
                }
                if (newDifA != 0) {
//                        inFocus.getPowderDataSet().get(i).getGSAS_Instrument().setDifA(newDifA);
                    gsasi.setDifA(newDifA);
                }
                if (newZero != 0) {
//                        inFocus.getPowderDataSet().get(i).getGSAS_Instrument().setZero(newZero);
                    gsasi.setZero(newZero);
                }

                inFocus.getPowderDataSet().get(i).setGSAS_Instrument(gsasi);

//System.out.println(GSASTable.getGSASTable().getModel().
//                                getValueAt(i, 1).toString());
//                        System.out.println(GSASTable.getGSASTable().getModel().
//                                getValueAt(i, 2).toString());
//                        System.out.println(GSASTable.getGSASTable().getModel().
//                                getValueAt(i, 3).toString());

            }
        }
    }
}
    


