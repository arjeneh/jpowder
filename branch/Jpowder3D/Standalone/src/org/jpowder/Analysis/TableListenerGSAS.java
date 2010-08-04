/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jpowder.Analysis;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import org.jpowder.InernalFrame.JpowderInternalframe2D;
import org.jpowder.Jpowder;

/**
 *
 * @author qyt21516
 */
public class TableListenerGSAS implements TableModelListener{


    private double newDifC;
    private double newDifA;
    private double newZero;


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



//                for (int j = 1; j < 4; j++) {
//                    if (!GSASTable.getGSASTable().getValueAt(i, j).equals("")) {

        
                        newDifC = Double.parseDouble(GSASTable.getGSASTable().getModel().
                                getValueAt(i, 1).toString());
                        
              
                        newDifA = Double.parseDouble(GSASTable.getGSASTable().getModel().
                                getValueAt(i, 2).toString());
                        
              
                        newZero = Double.parseDouble(GSASTable.getGSASTable().getModel().
                                getValueAt(i, 3).toString());
                        

           if(newDifC!=0){
               System.out.println(newDifC);
                        inFocus.getPowderDataSet().get(i).getGSAS_Instrument().setDifC(newDifC);
           }if(newDifA!=0){
                        inFocus.getPowderDataSet().get(i).getGSAS_Instrument().setDifA(newDifA);
           }if(newZero!=0){
                        inFocus.getPowderDataSet().get(i).getGSAS_Instrument().setZero(newZero);
           }

                        System.out.println(inFocus.getPowderDataSet().get(i).getGSAS_Instrument().getDifC());
//System.out.println(GSASTable.getGSASTable().getModel().
//                                getValueAt(i, 1).toString());
//                        System.out.println(GSASTable.getGSASTable().getModel().
//                                getValueAt(i, 2).toString());
//                        System.out.println(GSASTable.getGSASTable().getModel().
//                                getValueAt(i, 3).toString());

//                    }
//                }


        }
    }

}
