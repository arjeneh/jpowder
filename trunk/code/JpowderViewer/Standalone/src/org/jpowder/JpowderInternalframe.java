/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder;

import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import org.jpowder.dataset.DataSet;

/**
 *
 * @author qyt21516
 */
public class JpowderInternalframe extends JInternalFrame {

  private DataVisibleInChart dataVisibleInChartPanel;
  private Vector<DataSet> m_data;
/**
 *
 * @param chartPanel
 * @param dataVisibleInChartPanel
 * @param data
 */
  public JpowderInternalframe(JPanel chartPanel, DataVisibleInChart dataVisibleInChartPanel, Vector<DataSet> data) {
    super("JPowder");
  
    this.dataVisibleInChartPanel = dataVisibleInChartPanel;
    this.add(chartPanel);    
    m_data = data;

    this.setClosable(true);
    this.setMaximizable(true);
    this.setResizable(false);
    this.setIconifiable(false);

    this.setPreferredSize(new Dimension(300, 300));
    System.out.println("Internalframe created");
    this.setVisible(true);
  }
/**
 *
 * @return
 */
  public Vector<DataSet> getPowderDataSet() {
    return m_data;
  }

  /**
  @Override
  public void setSelected(boolean selected) {
  moveToFront();
  // System.out.println("Internalframe selected.........");
  }
   */
  /**
   * @return the chtpnl
   */
  public DataVisibleInChart getDataVisibleInChartPanel() {
    return dataVisibleInChartPanel;
  }
}
/**
 *
 * @author qyt21516
 */
class InternalFrameIconifyListener extends InternalFrameAdapter {
/**
 *
 * @param e
 */
  @Override
  public void internalFrameClosed(InternalFrameEvent e) {

    System.out.println("widows is Closed");

  }
/**
 *
 * @param e
 */
  @Override
  public void internalFrameActivated(InternalFrameEvent e) {

    System.out.println("widows is Activated");
    JpowderInternalframe jpowderinternalframe = (JpowderInternalframe) e.getInternalFrame();
    //data datasets = frame.getPowderDataSet();

    DataVisibleInChart dvic = jpowderinternalframe.getDataVisibleInChartPanel();
    dvic.newChartInFocus(jpowderinternalframe.getPowderDataSet());

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
