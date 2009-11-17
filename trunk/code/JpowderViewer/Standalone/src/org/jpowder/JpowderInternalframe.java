/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jpowder.JCheckboxList.JCheckBoxJList;
import org.jpowder.dataset.DataSet;
import org.jpowder.dataset.DatasetPlotter;
import org.jpowder.fileCabinet.PowderFileCabinet;

/**
 *
 * @author qyt21516
 */
public class JpowderInternalframe extends JInternalFrame implements DropTargetListener {

  private DataVisibleInChart dataVisibleInChartPanel;
  private JCheckBoxJList plot;
  private Vector<DataSet> m_data;
  private java.awt.dnd.DropTarget droptraget;
  private PowderFileCabinet mPowderFileCabinet;
  private JPowder decktopane;
  //public DataVisibleInChart DVIC;
  public org.jfree.chart.ChartPanel jfreeChartPanel;


  /**
   *
   * @param chartPanel
   * @param dataVisibleInChartPanel
   * @param data
   */
  public JpowderInternalframe(DataVisibleInChart dataVisibleInChartPanel, Vector<DataSet> data) {
    super("JPowder");

    javax.swing.JPanel chartPanel = new javax.swing.JPanel();
    this.dataVisibleInChartPanel = dataVisibleInChartPanel;
    this.add(chartPanel);
    m_data = data;
    DatasetPlotter plotMultiCol = DatasetPlotter.createDatasetPlotter(data);
    chartPanel.setLayout(new BorderLayout());
    jfreeChartPanel = plotMultiCol.createPowderChart();
    chartPanel.add(jfreeChartPanel);
    this.setClosable(true);
    this.setMaximizable(true);
    this.setResizable(false);
    this.setIconifiable(false);
    this.setEnabled(true);
    //this.setTransferHandler(new TransferHandler(null));
    droptraget = new DropTarget(this, this);


    this.setPreferredSize(new Dimension(300, 300));
    System.out.println("Internalframe created");
    this.setVisible(true);
  }

//  @Override
//  public boolean isSelected(){
//  return true;
//  }
  /**
   *
   * @return
   */
  public Vector<DataSet> getPowderDataSet() {
    return m_data;
  }

  @Override
  public Component add(Component comp) {
    return super.add(comp);
  }

  /**
   * @return the chtpnl
   */
  public DataVisibleInChart getDataVisibleInChartPanel() {
    return dataVisibleInChartPanel;
  }

  public void dragEnter(DropTargetDragEvent dtde) {
  }

  public void dragOver(DropTargetDragEvent dtde) {
  }

  public void dropActionChanged(DropTargetDragEvent dtde) {
  }

  public void dragExit(DropTargetEvent dte) {
  }

  public void drop(DropTargetDropEvent dtde) {
    System.out.println("i am getting called nice..");
    DataSet oneDataset = null;
    ArrayList<File> allfiles = new ArrayList<File>();
    ArrayList<String> allfilesName = new ArrayList<String>();
    Vector<DataSet> datasets = new Vector<DataSet>();
    Transferable transfeable = dtde.getTransferable();
    DataFlavor[] flavors = transfeable.getTransferDataFlavors();
    dtde.acceptDrop(DnDConstants.ACTION_COPY);

    // Find all filenames which have been draged into the chart plotter area
    // and to allFilenames

    for (int i = 0; i < flavors.length; i++) {
      if (flavors[i].isFlavorJavaFileListType()) {
        try {
          // populate allFilenames
          java.util.List<File> list = (java.util.List) transfeable.getTransferData(flavors[i]);
          for (int j = 0; j < list.size(); j++) {
            File file = list.get(j);
            String fileName = file.getName().toLowerCase();
            allfiles.add(file);
            allfilesName.add(fileName);
          }
          System.out.println("files added to Internalframe " + allfiles);
        } catch (UnsupportedFlavorException ex) {
          Logger.getLogger(JPowder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
          Logger.getLogger(JPowder.class.getName()).log(Level.SEVERE, null, ex);
        }
      } else if (flavors[i].equals(DataFlavor.stringFlavor)) {

        try {
          // populate allFilenames
          String fileName;
          fileName = (String) transfeable.getTransferData(DataFlavor.stringFlavor);
          String[] array = fileName.split("\n");
          int n = array.length;
          for (int j = 0; j < n; j++) {
            System.out.println("\n" + array[j] + "\n");
            File file = new File(array[j]);
            String fileNames = file.getName().toLowerCase();
            allfiles.add(file);
            allfilesName.add(fileNames);
          }
          System.out.println("files added to Internalframe \n" + allfiles);
        } catch (Exception ex) {
        }
      } else {
      }
    }

    // create vector of DataSet
    for (int i = 0; i < allfiles.size(); i++) {
      if (mPowderFileCabinet.checkAcceptedFileType(allfilesName.get(i))) {
          oneDataset = null;
          oneDataset = mPowderFileCabinet.createDataSetFromPowderFile(allfiles.get(i));
        if (oneDataset != null) {
          datasets.add(oneDataset);
        }
      } else {
        javax.swing.JOptionPane.showMessageDialog(null, "Only ASCII file please.");
        break;
      }
    }
    // finally plot the data

    setVisible(true);


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
    //XYPlot noGoodProgramming = jpowderinternalframe.jfreeChartPanel.getChart().getXYPlot();
    dvic.newChartInFocus(jpowderinternalframe.jfreeChartPanel.getChart().getXYPlot(),
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
