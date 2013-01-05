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
 * TableListenerGSAS.java
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

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import org.jpowder.InternalFrame.JpowderInternalframe2D;
import org.jpowder.Jpowder;
import org.jpowder.dataset.GSAS_Instrument;

/**
 *
 * This class get value from the Table and sets values of
 * DifC, DifA and Zero values.
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

            }
        }
    }
}
    


