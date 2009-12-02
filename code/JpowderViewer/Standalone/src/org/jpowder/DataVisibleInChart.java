/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder;

import java.awt.BorderLayout;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import org.jfree.chart.plot.XYPlot;
import org.jpowder.JCheckboxList.CheckableFileItem;
import org.jpowder.JCheckboxList.JCheckBoxJList;
import org.jpowder.dataset.DataSet;
import org.jpowder.fileCabinet.PowderFileCabinet;


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
    checkboxList.setFont(new java.awt.Font("Tahoma", 0, 10));

    file_sp = new JScrollPane(checkboxList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
 
    file_sp.setPreferredSize(new java.awt.Dimension(270, 260));
   // file_sp.setViewportView(checkboxList);
   // file_sp.setLayout(new ScrollPaneLayout());

    java.awt.GridBagConstraints gridBagConstraints;
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
    add(file_sp, gridBagConstraints);

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

//  public void powderFileCabinetUpdate(Subject data) {
//    org.jpowder.fileCabinet.PowderFileCabinet pfc = (org.jpowder.fileCabinet.PowderFileCabinet) data;
//
//    String fileName = pfc.getLastUpdateFileName();
//    System.out.println("get the file name" + fileName);
//    listModel.clear();
//    CheckableFileItem a = new CheckableFileItem(fileName);
//    listModel.addCheckableFile(a);
//
//    ListModel model = checkboxList.getModel();
//    ArrayList<String> nameList = new ArrayList<String>();
//    int n = model.getSize();
//
//    for (int i = 0; i < n; i++) {
//      CheckableFileItem item = (CheckableFileItem) model.getElementAt(i);
//      if (item.isSelected()) {
//        nameList.add(item.toString());
//        System.out.println("multible file " + item.toString());
//      }//if
//    }//for
//
//  }
}
