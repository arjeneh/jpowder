/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jpowder.InernalFrame;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import org.jpowder.DataVisibleInChart;
import org.jpowder.Jpowder;

/**
 *
 * @author qyt21516
 */
public class InternalFrameIconifyListener extends InternalFrameAdapter {

    private DataVisibleInChart dataVisibleInChart;
    private JpowderInternalframe jpowderinternalframe;

    /**
     *
     * @param dataVisibleInChart
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
