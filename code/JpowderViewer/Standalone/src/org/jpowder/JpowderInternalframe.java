/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import org.jpowder.dataset.DataSet;
import org.jpowder.dataset.DatasetPlotter;
import org.jpowder.jfreechart.FilesPlotter;
import org.jpowder.fileCabinet.PowderFileCabinet;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;

/**
 *
 * @author qyt21516
 */
public class JpowderInternalframe extends JInternalFrame implements DropTargetListener {

  private DataVisibleInChart dataVisibleInChartPanel;
  private Vector<DataSet> m_data;
  private java.awt.dnd.DropTarget droptraget;  // to drop to this frame
  private XYPlot xyPlot;  // hold reference to plot created from dataset in constructor
  private static int numberOfJpowderInternalframe = 0;
  private DatasetPlotter plotMultiCol;
  private ChartPanel jfreeChartPanel;
  private JFreeChart chart;
  private JPowder jpowder = new JPowder();
  private Vector<Double> markedPeakPosition = new Vector<Double>();
  private static int left;
  private static int top;
  public Stack<JInternalFrame> internalframeStackes = new Stack<JInternalFrame>();

  /**
   *
   * @param dataVisibleInChartPanel
   * @param data
   */
  public JpowderInternalframe(DataVisibleInChart dataVisibleInChartPanel, Vector<DataSet> data) {
    super("JPowder");
    numberOfJpowderInternalframe++;
    System.out.println("\n\n" + numberOfJpowderInternalframe);
    internalframeStackes.push(this);
    javax.swing.JPanel chartPanel = new javax.swing.JPanel();

    this.dataVisibleInChartPanel = dataVisibleInChartPanel;
    this.add(chartPanel);
    m_data = data;
    plotMultiCol = DatasetPlotter.createDatasetPlotter(data);
    chartPanel.setLayout(new BorderLayout());
    ChartPanel jfreeChartPanels = plotMultiCol.createPowderChart();
    this.jfreeChartPanel = jfreeChartPanels;
    chart = FilesPlotter.getchart();
    xyPlot = jfreeChartPanels.getChart().getXYPlot();
    chartPanel.add(jfreeChartPanels);


    this.addComponentListener(new ComponentAdapter() {

      @Override
      public void componentMoved(ComponentEvent evt) {
        jpowder.internalFrameMoved(evt);
      }
    });
    this.setClosable(true);
    this.setMaximizable(true);
    this.setResizable(false);
    this.setIconifiable(false);
    this.setEnabled(true);
      JPowder.jpowderInternalFrameUpdate(this);
    System.out.println("Internalframe created");
    droptraget = new DropTarget(this, this);
    this.setPreferredSize(new Dimension(300, 300));
    this.setMaximumSize(new Dimension(900, 900));
    dataVisibleInChartPanel.newChartInFocus(xyPlot,
            this.getPowderDataSet());
    this.setVisible(true);

  }

  private static void incr() {
    left += 30;
    top += 30;
    if (top == 150) {
      top = 0;
    }
  }

  /**
   *
   * @return
   */
  public ChartPanel getChartPanel() {
    return jfreeChartPanel;
  }

  /**
   *
   * @return
   */
  public XYPlot getXYPlot() {
    return xyPlot;
  }

  /**
   *
   * @return
   */
  public static int getnumberOfJpowderInternalframe() {
    return numberOfJpowderInternalframe;
  }

  /**
   *
   */
  public static void decrementnumberOfJpowderInternalframe() {
    numberOfJpowderInternalframe--;
  }

  /**
   *
   * @return
   */
  public Vector<DataSet> getPowderDataSet() {
    return m_data;
  }

  /**
   *
   * @param comp
   * @return
   */
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

  /**
   *
   * @return
   */
  public Vector<DataSet> addDataset() {
    return m_data;
  }

  /**
   *
   * @param peaks
   */
  public void addMarkedPeakPosition(double peaks) {
    markedPeakPosition.add(peaks);
  }


  /**
   *
   * @return
   */
  public Vector<Double> getMarkedPeakPosition() {
    return markedPeakPosition;
  }

   public Vector<Double> removeMarkedPeakPosition() {
    return markedPeakPosition;
  }

  public void removeMarkedPeakPosition(double peaks) {
    markedPeakPosition.remove(peaks);
  }
//    public Vector<Double> MarkedPeakPosition (){
//    return markedPeakPosition.removeAll(Vector<Double> true);
//  }

  /**
   *
   * @return
   */
  public JFreeChart getchart() {
    return chart;
  }

  public void dragEnter(DropTargetDragEvent dtde) {
  }

  public void dragOver(DropTargetDragEvent dtde) {
  }

  public void dropActionChanged(DropTargetDragEvent dtde) {
  }

  public void dragExit(DropTargetEvent dte) {
  }

  /**
   *
   * @param dtde
   */
  public void drop(DropTargetDropEvent dtde) {
    System.out.println("hashcodeeeeeeeeeee" + hashCode());
    System.out.println("i am getting called nice..");
    JPowder.jpowderInternalFrameUpdate(this);
    DataSet oneDataset = null;
    ArrayList<File> allfiles = new ArrayList<File>();
    ArrayList<String> allfilesName = new ArrayList<String>();
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
    int numGoodFilenames = 0;
    Vector<DataSet> toPass = new Vector<DataSet>();
    for (int i = 0; i < allfiles.size(); i++) {

      oneDataset = null;
      oneDataset = PowderFileCabinet.createDataSetFromPowderFile(allfiles.get(i));
      if (oneDataset != null) {
        m_data.add(oneDataset);
        numGoodFilenames = numGoodFilenames + 1;
        toPass.add(oneDataset);
      } else {
        javax.swing.JOptionPane.showMessageDialog(null, "Only ASCII file please.");
        break;
      }
    }
    if (numGoodFilenames > 0) {
      FilesPlotter.addDataToJpowderInternalFrame(xyPlot,
              toPass);
      dataVisibleInChartPanel.newChartInFocus(xyPlot,
              this.getPowderDataSet());
    }

  }
}

/**
 *
 * @author
 */
class InternalFrameIconifyListener extends InternalFrameAdapter {

  private DataVisibleInChart dvic;
  private JpowderInternalframe jpowderinternalframe;

  /**
   *
   * @param DVIC
   */
  public InternalFrameIconifyListener(DataVisibleInChart dvic) {
    this.dvic = dvic;
  }

  /**
   *
   * @param e
   */
  @Override
  public void internalFrameClosed(InternalFrameEvent e) {
    System.out.println("widows is Closed");
    JPowder.jPowderStackUndo.push(jpowderinternalframe);
    JpowderInternalframe.decrementnumberOfJpowderInternalframe();
    System.out.println("the number of internalframe in the descktop pane"
            + JpowderInternalframe.getnumberOfJpowderInternalframe());

    if (JpowderInternalframe.getnumberOfJpowderInternalframe() > 1
            || JpowderInternalframe.getnumberOfJpowderInternalframe() == 0) {
      dvic.clear();
    }

    JPowder.jpowderInternalFrameUpdate(jpowderinternalframe);
  }

  /**
   *
   * @param e
   */
  @Override
  public void internalFrameActivated(InternalFrameEvent e) {

    System.out.println("widows is Activated");

    jpowderinternalframe = (JpowderInternalframe) e.getInternalFrame();
    JPowder.jpowderInternalFrameUpdate(jpowderinternalframe);
    jpowderinternalframe.moveToFront();
    DataVisibleInChart DVIC = jpowderinternalframe.getDataVisibleInChartPanel();
    DVIC.newChartInFocus(jpowderinternalframe.getXYPlot(),
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
