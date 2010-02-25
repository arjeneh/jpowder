/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jpowder;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.TransferHandler;
import javax.swing.UIManager;
import javax.swing.event.InternalFrameListener;
import org.jpowder.dataset.DataSet;
import org.jpowder.fileCabinet.PowderFileCabinet;

/**
 *
 * @author qyt21516
 */
public class JpowderGuiTest extends JFrame implements DropTargetListener{


  private JDesktopPane dp = new JDesktopPane();
  private static int left;
  private static int top;
  private static int width=200;
  private org.jfree.chart.JFreeChart chart;
  private LookAndFeel LAF = new LookAndFeel(this);
  public DataVisibleInChart DVIC = new DataVisibleInChart();
  private PowderFileCabinet mPowderFileCabinet;
  private java.awt.dnd.DropTarget dt;
  private TransferHandler th;
  public static JpowderInternalframe internalFrameInFocus;
    private DefaultListModel listModel = new DefaultListModel();
  private JList list = new JList(listModel);

  public JpowderGuiTest() {

//    JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, list, dp);
//    sp.setDividerLocation(120);
//    getContentPane().add(sp);
    mPowderFileCabinet = new PowderFileCabinet();
    dt = new DropTarget(dp, this);
    this.add(dp);
    setVisible(true);
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
    DataSet oneDataset = null;
    Vector<DataSet> datasets = new Vector<DataSet>();
    HashMap<String, File> hash = new HashMap<String, File>();

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
            hash.put(fileName, file);

          }
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
            hash.put(fileNames, file);
          }
        } catch (Exception ex) {
        }
      } else {
      }
    }
    // create vector of DataSet

    for (Map.Entry<String, File> entry : hash.entrySet()) {
      String fileName = entry.getKey();
      File file = entry.getValue();
      if (mPowderFileCabinet.checkAcceptedFileType(fileName)) {

        oneDataset = PowderFileCabinet.createDataSetFromPowderFile(file);
        if (oneDataset != null) {
          datasets.add(oneDataset);
        }
      } else {
        javax.swing.JOptionPane.showMessageDialog(null, "Only ASCII file please.");
        break;
      }
    }
    // finally plot the data
    JpowderInternalframe internalframe = new JpowderInternalframe(DVIC, datasets);
    try {
      internalframe.setSelected(true);
      internalframe.isSelected();
    } catch (PropertyVetoException ex) {
      Logger.getLogger(JpowderGuiTest.class.getName()).log(Level.SEVERE, null, ex);
    }
    JPowder.jpowderInternalFrameUpdate(internalframe);

    InternalFrameListener internalFrameListener = new InternalFrameIconifyListener(DVIC);
    internalframe.addInternalFrameListener(internalFrameListener);
    dp.add(internalframe);
    setVisible(true);
  }



    public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {

    

      public void run() {
          try {
          UIManager.setLookAndFeel(new WindowsLookAndFeel());
        } catch (Exception e) {
        }
        if (isWindows()) {
          System.out.println("This is Windows");
        } else if (isMac()) {
          System.out.println("This is Mac");
        } else if (isUnix()) {
          System.out.println("This is Unix or Linux");
        } else {
          System.out.println("Your OS is not support!!");
        }

        JpowderGuiTest JPOWDER = new JpowderGuiTest();
        JPOWDER.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPOWDER.setSize(800, 600);
        JPOWDER.setLocationRelativeTo(null);
        JPOWDER.setVisible(true);
      }
    });
  }
  public static boolean isWindows() {

    String os = System.getProperty("os.name").toLowerCase();
    //windows
    return (os.indexOf("win") >= 0);

  }

  public static boolean isMac() {

    String os = System.getProperty("os.name").toLowerCase();
    //Mac
    return (os.indexOf("mac") >= 0);

  }

  public static boolean isUnix() {

    String os = System.getProperty("os.name").toLowerCase();
    //linux or unix
    return (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0);

  }
}
