/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder;

import org.jpowder.JCheckboxList.FileNameListModel;
import java.awt.BorderLayout;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import org.jfree.chart.plot.XYPlot;
import org.jpowder.JCheckboxList.CheckableFileItem;
import org.jpowder.JCheckboxList.JCheckBoxJList;
import org.jpowder.dataset.DataSet;



/**
 * @author Kreecha Puphaiboon
 *
 */
public class DataVisibleInChart extends JPanel  {

  public FileNameListModel listModel;
  private JCheckBoxJList checkboxList;
  private javax.swing.JScrollPane file_sp;


  public DataVisibleInChart() {


    listModel = new FileNameListModel();

    checkboxList = new JCheckBoxJList(listModel);
    checkboxList.setLayout(new BorderLayout());
    checkboxList.setFont(new java.awt.Font("Tahoma", 0, 12));

    file_sp = new JScrollPane(checkboxList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
   file_sp.setPreferredSize(new java.awt.Dimension(270, 230));
    add(file_sp);

  }

  /**
   * clear the selection from the checkboxlist.
   */
  public void clear() 
  {
    listModel.clear();
  }

  /**
   * To inform DataVisibleInChart that a new chart has been highlighted and
   * pass it information so that DataVisibleInChart can update its checkbox list
   * @param xyplot
   * @param datasets
   */
  public void newChartInFocus(XYPlot xyplot,Vector<DataSet> datasets) {
       clear();
    for (DataSet d : datasets) {
      CheckableFileItem a = new CheckableFileItem(d.getFileName());
      
      listModel.addCheckableFile(a);
    }
    
       checkboxList.setm_plot(xyplot);
    System.out.println("chartInFocus chartInFocus chartInFocus");
  }
}
