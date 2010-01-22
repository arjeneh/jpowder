

package org.jpowder;

import java.awt.CardLayout;
import org.jpowder.tree.Tree;
import java.awt.FlowLayout;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.TransferHandler;
import javax.swing.event.InternalFrameListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jfree.chart.ChartPanel;
import org.jpowder.Analysis.AnalysisIcon;
import org.jpowder.chartTools.ChartToolsIcon;
import org.jpowder.dataset.DataSet;
import org.jpowder.fileCabinet.PowderFileCabinet;
import org.jpowder.util.ScreenUtil;



/**
 * Jpowder is the starting class for the Jpowder project {@link www.jpowder.org}.
 * It setups the main GUI for this application, which draws charts of powder
 * diffraction data.
 * <P>
 * This file is part of Jpowder.
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
 * along with this program.  If not, see <a target="_blank" href=http://www.gnu.org/licenses/>here</a>.
 * <p>
 * File change history is stored at: <a target="_blank" href=https://jpowder.org/svn/Jpowder>www.jpowder.org/svn/Jpowder</a>
 *
 * @author  Kreecha Puphaiboon
 * @since 07
 *
 *
 */
public class JPowder extends JFrame implements DropTargetListener {


  private Tree tr = new Tree();
  private LookAndFeel LAF = new LookAndFeel(this);
  public DataVisibleInChart DVIC = new DataVisibleInChart();
  private PowderFileCabinet mPowderFileCabinet;
  private java.awt.dnd.DropTarget dt;
  private TransferHandler th;
  private ButtonGroup buttonGroup = new ButtonGroup();
  private ChartToolsIcon icons = new ChartToolsIcon(this);
  private AnalysisIcon icon = new AnalysisIcon(this);
  private CardLayout cardLayout;

  public static JpowderInternalframe internalFrameInFocus;
  public static InfoPanel infoPanelInfocus;



    
  /**
   * JVM starting point
   *
   * @param args
   */

  public JPowder() {

    initComponents();
    mPowderFileCabinet = new PowderFileCabinet();
    dt = new DropTarget(ChartPlotter, this);
    dataVisibleInChartPanel.add(DVIC);
    treetab.add(tr,"1");
    analysistab.add(icon,"1");
    chartToolstab.add(icons,"1");
  }
 public JPanel getanalysistab(){
    return analysistab;
  }
  public  JPanel getChartToolstab(){
    return chartToolstab;
  }
 
  public CardLayout getCardLayout(){
    return cardLayout;
  }

  public static void jpowderInfoPanelUpdate(InfoPanel info){
    infoPanelInfocus=info;
  }

  public static void jpowderInternalFrameUpdate(JpowderInternalframe internalFrame){
    if(internalFrame!=internalFrameInFocus){
      internalFrameInFocus=internalFrame;
      if (infoPanelInfocus != null)
        infoPanelInfocus.update();
    } 
  }
  /** This method is called from within the constructor to
   * initialise the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jSplitPane1 = new javax.swing.JSplitPane();
    home = new javax.swing.JPanel();
    Tabs = new javax.swing.JTabbedPane();
    treetab = new javax.swing.JPanel();
    analysistab = new javax.swing.JPanel();
    cardLayout = new CardLayout();
    chartToolstab = new javax.swing.JPanel();
    dataVisibleInChartPanel = new javax.swing.JPanel();
    ChartPlotter = new javax.swing.JDesktopPane();
    jMenuBar1 = new javax.swing.JMenuBar();
    jMenu1 = new javax.swing.JMenu();
    New = new javax.swing.JMenuItem();
    jSeparator2 = new javax.swing.JSeparator();
    Open = new javax.swing.JMenuItem();
    Save = new javax.swing.JMenuItem();
    jSeparator1 = new javax.swing.JSeparator();
    Exit = new javax.swing.JMenuItem();
    LookAndFeel = new javax.swing.JMenu();
    Windows = new javax.swing.JRadioButtonMenuItem();
    WindowClassic = new javax.swing.JRadioButtonMenuItem();
    MacLookAndFeel = new javax.swing.JRadioButtonMenuItem();
    Motif = new javax.swing.JRadioButtonMenuItem();
    Nimbus = new javax.swing.JRadioButtonMenuItem();
    Metal = new javax.swing.JRadioButtonMenuItem();
    LinuxSolaris = new javax.swing.JRadioButtonMenuItem();
    jMenu2 = new javax.swing.JMenu();
    Content = new javax.swing.JMenuItem();
    OnlieDocsandSupport = new javax.swing.JMenuItem();
    jSeparator3 = new javax.swing.JSeparator();
    About = new javax.swing.JMenuItem();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("JPowder Crystallography Demo");
    setLocationByPlatform(true);

    jSplitPane1.setDividerLocation(310);
    jSplitPane1.setDividerSize(8);
    jSplitPane1.setAutoscrolls(true);

    Tabs.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
    Tabs.setFont(new java.awt.Font("Kartika", 0, 18));
    Tabs.setMaximumSize(new java.awt.Dimension(327, 32767));

    treetab.setLayout(new java.awt.CardLayout());
    Tabs.addTab(" Tree ", treetab);

    analysistab.setPreferredSize(new java.awt.Dimension(270, 320));
    analysistab.setLayout(new java.awt.CardLayout());

    analysistab.setLayout(cardLayout);

    Tabs.addTab("Analysis", analysistab);

    chartToolstab.setLayout(new java.awt.CardLayout());
    chartToolstab.setLayout(cardLayout);
    Tabs.addTab("ChartTools", chartToolstab);

    dataVisibleInChartPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("DataVisibleInChart"));
    dataVisibleInChartPanel.setPreferredSize(new java.awt.Dimension(270, 260));
    dataVisibleInChartPanel.setLayout(new java.awt.BorderLayout());

    javax.swing.GroupLayout homeLayout = new javax.swing.GroupLayout(home);
    home.setLayout(homeLayout);
    homeLayout.setHorizontalGroup(
      homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homeLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(Tabs, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
          .addComponent(dataVisibleInChartPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE))
        .addContainerGap())
    );
    homeLayout.setVerticalGroup(
      homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homeLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(Tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(dataVisibleInChartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );

    jSplitPane1.setLeftComponent(home);

    ChartPlotter.setBackground(new java.awt.Color(240, 240, 240));
    ChartPlotter.setOpaque(false);
    jSplitPane1.setRightComponent(ChartPlotter);

    jMenuBar1.setFont(new java.awt.Font("Tahoma", 0, 36));

    jMenu1.setText("File");
    jMenu1.setFont(new java.awt.Font("Tahoma", 0, 14));

    New.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
    New.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/new_small.gif"))); // NOI18N
    New.setText("New...");
    New.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        NewActionPerformed(evt);
      }
    });
    jMenu1.add(New);
    jMenu1.add(jSeparator2);

    Open.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
    Open.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/addFile.gif"))); // NOI18N
    Open.setText("Open");
    Open.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        OpenActionPerformed(evt);
      }
    });
    jMenu1.add(Open);

    Save.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
    Save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/savas_small.gif"))); // NOI18N
    Save.setText("Save");
    Save.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        SaveActionPerformed(evt);
      }
    });
    jMenu1.add(Save);
    jMenu1.add(jSeparator1);

    Exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
    Exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/del_small.gif"))); // NOI18N
    Exit.setText("Exit");
    Exit.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        ExitActionPerformed(evt);
      }
    });
    jMenu1.add(Exit);

    jMenuBar1.add(jMenu1);

    LookAndFeel.setText("LookAndFeel");
    LookAndFeel.setFont(new java.awt.Font("Tahoma", 0, 14));

    buttonGroup
    .add(Windows);
    Windows.setText("Windows");
    Windows.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        WindowsActionPerformed(evt);
      }
    });
    LookAndFeel.add(Windows);

    buttonGroup

    .add(WindowClassic);
    WindowClassic.setText("WindowClassic");
    WindowClassic.setContentAreaFilled(false);
    WindowClassic.setFocusPainted(true);
    WindowClassic.setHideActionText(true);
    WindowClassic.setMargin(new java.awt.Insets(4, 4, 4, 4));
    WindowClassic.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        WindowClassicActionPerformed(evt);
      }
    });
    LookAndFeel.add(WindowClassic);

    buttonGroup.add(MacLookAndFeel);
    MacLookAndFeel.setText("MacLookAndFeel");
    MacLookAndFeel.setContentAreaFilled(false);
    MacLookAndFeel.setFocusPainted(true);
    MacLookAndFeel.setHideActionText(true);
    MacLookAndFeel.setMargin(new java.awt.Insets(4, 4, 4, 4));
    MacLookAndFeel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        MacLookAndFeelActionPerformed(evt);
      }
    });
    LookAndFeel.add(MacLookAndFeel);

    buttonGroup.add(Motif);
    Motif.setText("Motif");
    Motif.setContentAreaFilled(false);
    Motif.setFocusPainted(true);
    Motif.setHideActionText(true);
    Motif.setMargin(new java.awt.Insets(4, 4, 4, 4));
    Motif.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        MotifActionPerformed(evt);
      }
    });
    LookAndFeel.add(Motif);

    buttonGroup.add(Nimbus);
    Nimbus.setText("Nimbus");
    Nimbus.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        NimbusActionPerformed(evt);
      }
    });
    LookAndFeel.add(Nimbus);

    buttonGroup.add(Metal);
    Metal.setText("Metal");
    Metal.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        MetalActionPerformed(evt);
      }
    });
    LookAndFeel.add(Metal);

    buttonGroup.add(LinuxSolaris);
    LinuxSolaris.setText("LinuxandSolaris");
    LinuxSolaris.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        LinuxSolarisActionPerformed(evt);
      }
    });
    LookAndFeel.add(LinuxSolaris);

    jMenuBar1.add(LookAndFeel);

    jMenu2.setText("Help");
    jMenu2.setFont(new java.awt.Font("Tahoma", 0, 14));

    Content.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
    Content.setText("Content");
    jMenu2.add(Content);

    OnlieDocsandSupport.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, java.awt.event.InputEvent.CTRL_MASK));
    OnlieDocsandSupport.setText("Online Docs and Support");
    OnlieDocsandSupport.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        OnlieDocsandSupportActionPerformed(evt);
      }
    });
    jMenu2.add(OnlieDocsandSupport);
    jMenu2.add(jSeparator3);

    About.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
    About.setText("About");
    About.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        AboutActionPerformed(evt);
      }
    });
    jMenu2.add(About);

    jMenuBar1.add(jMenu2);

    setJMenuBar(jMenuBar1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE)
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void OnlieDocsandSupportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OnlieDocsandSupportActionPerformed
      Runtime rt = Runtime.getRuntime();
      String[] callAndArgs = {"explorer.exe",
        "http://code.google.com/p/jpowder"};
      try {

        Process poress = rt.exec(callAndArgs);
      } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "No Internet Conection!",
                "error", JOptionPane.ERROR_MESSAGE);

      }
    }//GEN-LAST:event_OnlieDocsandSupportActionPerformed

    private void AboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutActionPerformed
      new about().setVisible(true);
    }//GEN-LAST:event_AboutActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
      int res = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "JPowderFinalGui",
              JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
      switch (res) {
        case JOptionPane.YES_OPTION:
          System.exit(0);
          break;
        case JOptionPane.NO_OPTION:
          break;
        case JOptionPane.CANCEL_OPTION:
          break;
        case JOptionPane.CLOSED_OPTION:
          break;
      }
    }//GEN-LAST:event_ExitActionPerformed

    private void OpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenActionPerformed
      mPowderFileCabinet.loadFiles();
    }//GEN-LAST:event_OpenActionPerformed

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
      boolean[] JPowder = new boolean[16654];
      for (int i = 0; i < 16654; i++) {
      }
      JFileChooser chooser = new JFileChooser();
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
              ".ISIS ", "ISIS");
      chooser.setFileFilter(filter);
      int returnVal = chooser.showSaveDialog(chooser);
      File fileName = chooser.getSelectedFile();
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        try {
          FileOutputStream buffer = new FileOutputStream(fileName);
          final ObjectOutput out = new ObjectOutputStream(buffer);
          out.writeObject(JPowder);

        } catch (IOException e) {
          JOptionPane.showMessageDialog(this, "Invalid file!",
                  "error", JOptionPane.ERROR_MESSAGE);
        }
      }
    }//GEN-LAST:event_SaveActionPerformed

    private void NewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewActionPerformed
      JPowder jpowderfinalgui = new JPowder();
      ScreenUtil.centerFrame(jpowderfinalgui);
      jpowderfinalgui.setVisible(true);

    }//GEN-LAST:event_NewActionPerformed

    private void WindowClassicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WindowClassicActionPerformed
      LAF.windosclassic();
    }//GEN-LAST:event_WindowClassicActionPerformed

    private void MacLookAndFeelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MacLookAndFeelActionPerformed
      LAF.appleLook();
    }//GEN-LAST:event_MacLookAndFeelActionPerformed

    private void MotifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MotifActionPerformed
      LAF.motif();
    }//GEN-LAST:event_MotifActionPerformed

    private void NimbusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NimbusActionPerformed
//      LAF.nimbus();
    }//GEN-LAST:event_NimbusActionPerformed

    private void MetalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MetalActionPerformed
      LAF.metal();
    }//GEN-LAST:event_MetalActionPerformed

    private void WindowsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WindowsActionPerformed
      LAF.windows();
    }//GEN-LAST:event_WindowsActionPerformed

    private void LinuxSolarisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LinuxSolarisActionPerformed
      LAF.LinuxandSolaris();
    }//GEN-LAST:event_LinuxSolarisActionPerformed
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

    JPowder.jpowderInternalFrameUpdate(internalframe);

    InternalFrameListener internalFrameListener = new InternalFrameIconifyListener(DVIC);
    internalframe.addInternalFrameListener(internalFrameListener);

    ChartPlotter.setLayout(new FlowLayout());
    ChartPlotter.add(internalframe);
    setVisible(true);
  }

  /**
   *
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
     JPowder jpowder = new JPowder();
      public void run() {
        jpowder.setLocationRelativeTo(null);
        jpowder.setDefaultCloseOperation(JPowder.EXIT_ON_CLOSE);
        jpowder.setVisible(true);
      }
    });
  }
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JMenuItem About;
  private javax.swing.JDesktopPane ChartPlotter;
  private javax.swing.JMenuItem Content;
  private javax.swing.JMenuItem Exit;
  private javax.swing.JRadioButtonMenuItem LinuxSolaris;
  private javax.swing.JMenu LookAndFeel;
  private javax.swing.JRadioButtonMenuItem MacLookAndFeel;
  private javax.swing.JRadioButtonMenuItem Metal;
  private javax.swing.JRadioButtonMenuItem Motif;
  private javax.swing.JMenuItem New;
  private javax.swing.JRadioButtonMenuItem Nimbus;
  private javax.swing.JMenuItem OnlieDocsandSupport;
  private javax.swing.JMenuItem Open;
  private javax.swing.JMenuItem Save;
  private javax.swing.JTabbedPane Tabs;
  private javax.swing.JRadioButtonMenuItem WindowClassic;
  private javax.swing.JRadioButtonMenuItem Windows;
  private javax.swing.JPanel analysistab;
  private javax.swing.JPanel chartToolstab;
  private javax.swing.JPanel dataVisibleInChartPanel;
  private javax.swing.JPanel home;
  private javax.swing.JMenu jMenu1;
  private javax.swing.JMenu jMenu2;
  private javax.swing.JMenuBar jMenuBar1;
  private javax.swing.JSeparator jSeparator1;
  private javax.swing.JSeparator jSeparator2;
  private javax.swing.JSeparator jSeparator3;
  private javax.swing.JSplitPane jSplitPane1;
  private javax.swing.JPanel treetab;
  // End of variables declaration//GEN-END:variables
}
