package org.jpowder;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.FontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Rectangle;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.beans.PropertyVetoException;
import java.io.OutputStream;
import org.jpowder.tree.Tree;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.geom.Rectangle2D;
import java.io.BufferedOutputStream;
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
import javax.swing.event.InternalFrameListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jfree.chart.JFreeChart;
import org.jpowder.Analysis.AnalysisIcon;
import org.jpowder.chartTools.ChartToolsIcon;
import org.jpowder.dataset.DataSet;
import org.jpowder.fileCabinet.PowderFileCabinet;
import org.jpowder.util.ScreenUtil;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.*;
import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartPanel;
import org.jpowder.fileCabinet.AcceptFileFilter;

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

  private org.jfree.chart.JFreeChart chart;
  private Tree tr = new Tree();
  private LookAndFeel LAF = new LookAndFeel(this);
  private OSValidator oSValidator = new OSValidator();
  public DataVisibleInChart DVIC = new DataVisibleInChart();
  private PowderFileCabinet mPowderFileCabinet;
  private java.awt.dnd.DropTarget dt;
  private TransferHandler th;
  private ButtonGroup buttonGroup = new ButtonGroup();
  private ChartToolsIcon icons = new ChartToolsIcon(this);
  private AnalysisIcon icon = new AnalysisIcon(this);
  private CardLayout cardLayout;
  public static JpowderInternalframe internalFrameInFocus;
  private JPowderDesktopManager jPowderDesktopManager = new JPowderDesktopManager();
  public static InfoPanel infoPanelInfocus;
  public static JPowderStack jPowderStackUndo = new JPowderStack(9);
  public static JPowderStack jPowderStackRedo = new JPowderStack(9);

//  private stackInternalFrames;
  /**
   * JVM starting point
   *
   * @param args
   */
  public JPowder() {

    initComponents();

    mPowderFileCabinet = new PowderFileCabinet();

//    chartPlotter.setLayout(new GridLayout(3, 3, 30, 30));

//    chartPlotter.setLayout(null);
    dt = new DropTarget(chartPlotter, this);
    dataVisibleInChartPanel.add(DVIC);
    treetab.add(tr, "1");
    analysistab.add(icon, "1");
    chartToolstab.add(icons, "1");
  }

  /**
   * 
   * @return chartPlotter
   */
  public JDesktopPane getChartPlotter() {
    return chartPlotter;
  }

  /**
   *
   * @return analysistab.
   */
  public JPanel getanalysistab() {
    return analysistab;
  }

  /**
   *
   * @return
   */
  public JPanel getChartToolstab() {
    return chartToolstab;
  }

  public JPanel getThumbnail() {
    return thumbnailPanel;
  }

  /**
   *
   * @return
   */
  public CardLayout getCardLayout() {
    return cardLayout;
  }

  /**
   *
   * @param info
   */
  public static void jpowderInfoPanelUpdate(InfoPanel info) {
    infoPanelInfocus = info;
  }

  /**
   *
   * @param internalFrame
   */
  public static void jpowderInternalFrameUpdate(JpowderInternalframe internalFrame) {

    if (internalFrame != internalFrameInFocus) {
      internalFrameInFocus = internalFrame;
    }
    if (infoPanelInfocus != null) {
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

    jScrollPane2 = new javax.swing.JScrollPane();
    home = new javax.swing.JPanel();
    Tabs = new javax.swing.JTabbedPane();
    treetab = new javax.swing.JPanel();
    analysistab = new javax.swing.JPanel();
    cardLayout = new CardLayout();
    chartToolstab = new javax.swing.JPanel();
    dataVisibleInChartPanel = new javax.swing.JPanel();
    thumbnailPanel = new javax.swing.JPanel();
    chartPlotter = new javax.swing.JDesktopPane();
    jMenuBar1 = new javax.swing.JMenuBar();
    fileMenu = new javax.swing.JMenu();
    New = new javax.swing.JMenuItem();
    jSeparator2 = new javax.swing.JSeparator();
    Open = new javax.swing.JMenuItem();
    saveAs = new javax.swing.JMenu();
    appletMenu = new javax.swing.JMenuItem();
    imageMenu = new javax.swing.JMenuItem();
    pDfMenu = new javax.swing.JMenuItem();
    workspaceMenu = new javax.swing.JMenuItem();
    jSeparator1 = new javax.swing.JSeparator();
    Print = new javax.swing.JMenu();
    jPrintMenu = new javax.swing.JMenuItem();
    jPrintPublishingMenu = new javax.swing.JMenuItem();
    jSeparator4 = new javax.swing.JSeparator();
    Exit = new javax.swing.JMenuItem();
    editMenu = new javax.swing.JMenu();
    undoMenu = new javax.swing.JMenuItem();
    redoMenu = new javax.swing.JMenuItem();
    jSeparator5 = new javax.swing.JSeparator();
    copyMenu = new javax.swing.JMenuItem();
    clossAllMenu = new javax.swing.JMenuItem();
    moveMenu = new javax.swing.JCheckBoxMenuItem();
    jSeparator6 = new javax.swing.JSeparator();
    proptiesMenu = new javax.swing.JMenuItem();
    lookAndFeelMenu = new javax.swing.JMenu();
    Windows = new javax.swing.JRadioButtonMenuItem();
    WindowClassic = new javax.swing.JRadioButtonMenuItem();
    MacLookAndFeel = new javax.swing.JRadioButtonMenuItem();
    Motif = new javax.swing.JRadioButtonMenuItem();
    Nimbus = new javax.swing.JRadioButtonMenuItem();
    Metal = new javax.swing.JRadioButtonMenuItem();
    LinuxSolaris = new javax.swing.JRadioButtonMenuItem();
    helpMenu = new javax.swing.JMenu();
    Content = new javax.swing.JMenuItem();
    OnlieDocsandSupport = new javax.swing.JMenuItem();
    jSeparator3 = new javax.swing.JSeparator();
    About = new javax.swing.JMenuItem();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("JPowder Crystallography Demo");
    setLocationByPlatform(true);

    jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

    home.setPreferredSize(new java.awt.Dimension(320, 727));

    Tabs.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
    Tabs.setFont(new java.awt.Font("Kartika", 0, 18));
    Tabs.setMaximumSize(new java.awt.Dimension(327, 32767));
    Tabs.setPreferredSize(new java.awt.Dimension(275, 800));

    treetab.setPreferredSize(new java.awt.Dimension(275, 400));
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

    thumbnailPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    thumbnailPanel.setPreferredSize(new java.awt.Dimension(300, 150));

    javax.swing.GroupLayout thumbnailPanelLayout = new javax.swing.GroupLayout(thumbnailPanel);
    thumbnailPanel.setLayout(thumbnailPanelLayout);
    thumbnailPanelLayout.setHorizontalGroup(
      thumbnailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 296, Short.MAX_VALUE)
    );
    thumbnailPanelLayout.setVerticalGroup(
      thumbnailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 138, Short.MAX_VALUE)
    );

    javax.swing.GroupLayout homeLayout = new javax.swing.GroupLayout(home);
    home.setLayout(homeLayout);
    homeLayout.setHorizontalGroup(
      homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homeLayout.createSequentialGroup()
        .addGroup(homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addGroup(javax.swing.GroupLayout.Alignment.LEADING, homeLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(dataVisibleInChartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
          .addGroup(javax.swing.GroupLayout.Alignment.LEADING, homeLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(thumbnailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          .addComponent(Tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))
        .addContainerGap())
    );
    homeLayout.setVerticalGroup(
      homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(homeLayout.createSequentialGroup()
        .addComponent(Tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(thumbnailPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(dataVisibleInChartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
        .addContainerGap())
    );

    jScrollPane2.setViewportView(home);

    chartPlotter.setDesktopManager(jPowderDesktopManager);

    jMenuBar1.setFont(new java.awt.Font("Tahoma", 0, 36));

    fileMenu.setText("File");
    fileMenu.setFont(new java.awt.Font("Tahoma", 0, 14));

    New.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
    New.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/NewImages/New.png"))); // NOI18N
    New.setText("New...");
    New.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        NewActionPerformed(evt);
      }
    });
    fileMenu.add(New);
    fileMenu.add(jSeparator2);

    Open.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
    Open.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/NewImages/Open.png"))); // NOI18N
    Open.setText("Open");
    Open.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        OpenActionPerformed(evt);
      }
    });
    fileMenu.add(Open);

    saveAs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/NewImages/saveas_16x16.png"))); // NOI18N
    saveAs.setText("Save As");

    appletMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/NewImages/java16x16.png"))); // NOI18N
    appletMenu.setText("Jpowder Applet File");
    appletMenu.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        appletMenuActionPerformed(evt);
      }
    });
    saveAs.add(appletMenu);

    imageMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/NewImages/Image.png"))); // NOI18N
    imageMenu.setText("Image");
    imageMenu.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        imageMenuActionPerformed(evt);
      }
    });
    saveAs.add(imageMenu);

    pDfMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/NewImages/exportdirecttopdf_16x16.png"))); // NOI18N
    pDfMenu.setText("PDF");
    pDfMenu.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        pDfMenuActionPerformed(evt);
      }
    });
    saveAs.add(pDfMenu);

    workspaceMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/NewImages/10627_16x16.png"))); // NOI18N
    workspaceMenu.setText("WorkSpace");
    workspaceMenu.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        workspaceMenuActionPerformed(evt);
      }
    });
    saveAs.add(workspaceMenu);

    fileMenu.add(saveAs);
    fileMenu.add(jSeparator1);

    Print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/NewImages/05504_16x16.png"))); // NOI18N
    Print.setText("Print As");

    jPrintMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
    jPrintMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/NewImages/Print.png"))); // NOI18N
    jPrintMenu.setText("Print");
    jPrintMenu.setToolTipText("Print ");
    jPrintMenu.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jPrintMenuActionPerformed(evt);
      }
    });
    Print.add(jPrintMenu);

    jPrintPublishingMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
    jPrintPublishingMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/NewImages/Print1.png"))); // NOI18N
    jPrintPublishingMenu.setText("Print Publishing");
    jPrintPublishingMenu.setToolTipText("Print with white background");
    jPrintPublishingMenu.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jPrintPublishingMenuActionPerformed(evt);
      }
    });
    Print.add(jPrintPublishingMenu);

    fileMenu.add(Print);
    fileMenu.add(jSeparator4);

    Exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/NewImages/20557_16x16.png"))); // NOI18N
    Exit.setText("Exit");
    Exit.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        ExitActionPerformed(evt);
      }
    });
    fileMenu.add(Exit);

    jMenuBar1.add(fileMenu);

    editMenu.setText("Edit");
    editMenu.setFont(new java.awt.Font("Tahoma", 0, 14));

    undoMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
    undoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/NewImages/undo_26x26.png"))); // NOI18N
    undoMenu.setText("Undo");
    undoMenu.setToolTipText("Undo closed frame");
    undoMenu.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        undoMenuActionPerformed(evt);
      }
    });
    editMenu.add(undoMenu);

    redoMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
    redoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/NewImages/redo_26x26.png"))); // NOI18N
    redoMenu.setText("Redo");
    redoMenu.setToolTipText("Redo closed frame");
    redoMenu.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        redoMenuActionPerformed(evt);
      }
    });
    editMenu.add(redoMenu);
    editMenu.add(jSeparator5);

    copyMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
    copyMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/NewImages/05711_16x16.png"))); // NOI18N
    copyMenu.setText("Copy");
    copyMenu.setToolTipText("Copy the selected frame");
    copyMenu.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        copyMenuActionPerformed(evt);
      }
    });
    editMenu.add(copyMenu);

    clossAllMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
    clossAllMenu.setText("Close All");
    clossAllMenu.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        clossAllMenuActionPerformed(evt);
      }
    });
    editMenu.add(clossAllMenu);

    moveMenu.setText("Move");
    moveMenu.setToolTipText("To move hold CTRL and press &drag the mouse");
    moveMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/NewImages/arrowshapes.quad-arrow_16x16.png"))); // NOI18N
    moveMenu.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        moveMenuActionPerformed(evt);
      }
    });
    editMenu.add(moveMenu);
    editMenu.add(jSeparator6);

    proptiesMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
    proptiesMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/NewImages/selectall_16x16.png"))); // NOI18N
    proptiesMenu.setText("Propoties");
    proptiesMenu.setToolTipText("set propeties of the selected frame");
    proptiesMenu.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        proptiesMenuActionPerformed(evt);
      }
    });
    editMenu.add(proptiesMenu);

    jMenuBar1.add(editMenu);

    lookAndFeelMenu.setText("LookAndFeel");
    lookAndFeelMenu.setFont(new java.awt.Font("Tahoma", 0, 14));

    buttonGroup
    .add(Windows);
    Windows.setText("Windows");
    Windows.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        WindowsActionPerformed(evt);
      }
    });
    lookAndFeelMenu.add(Windows);

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
    lookAndFeelMenu.add(WindowClassic);

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
    lookAndFeelMenu.add(MacLookAndFeel);

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
    lookAndFeelMenu.add(Motif);

    buttonGroup.add(Nimbus);
    Nimbus.setText("Nimbus");
    Nimbus.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        NimbusActionPerformed(evt);
      }
    });
    lookAndFeelMenu.add(Nimbus);

    buttonGroup.add(Metal);
    Metal.setText("Metal");
    Metal.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        MetalActionPerformed(evt);
      }
    });
    lookAndFeelMenu.add(Metal);

    buttonGroup.add(LinuxSolaris);
    LinuxSolaris.setText("LinuxandSolaris");
    LinuxSolaris.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        LinuxSolarisActionPerformed(evt);
      }
    });
    lookAndFeelMenu.add(LinuxSolaris);

    jMenuBar1.add(lookAndFeelMenu);

    helpMenu.setText("Help");
    helpMenu.setFont(new java.awt.Font("Tahoma", 0, 14));

    Content.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
    Content.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/NewImages/help_16x16.png"))); // NOI18N
    Content.setText("Content");
    helpMenu.add(Content);

    OnlieDocsandSupport.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, java.awt.event.InputEvent.CTRL_MASK));
    OnlieDocsandSupport.setText("Online Docs and Support");
    OnlieDocsandSupport.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        OnlieDocsandSupportActionPerformed(evt);
      }
    });
    helpMenu.add(OnlieDocsandSupport);
    helpMenu.add(jSeparator3);

    About.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
    About.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/NewImages/info_16x16.png"))); // NOI18N
    About.setText("About");
    About.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        AboutActionPerformed(evt);
      }
    });
    helpMenu.add(About);

    jMenuBar1.add(helpMenu);

    setJMenuBar(jMenuBar1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(chartPlotter, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(chartPlotter, javax.swing.GroupLayout.DEFAULT_SIZE, 843, Short.MAX_VALUE)
          .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 843, Short.MAX_VALUE))
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  /**
   * open the browser to Jpowder web site for addition helps.
   * @param evt
   */
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
  /**
   * call the about panel.
   * @param evt
   */
    private void AboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutActionPerformed
      new about().setVisible(true);
    }//GEN-LAST:event_AboutActionPerformed
  /**
   * exit the JPowder.
   * @param evt
   */
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

      Vector<DataSet> datasets = new Vector<DataSet>();
      HashMap<String, File> hash = new HashMap<String, File>();
      String fileName;
      File file;
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setMultiSelectionEnabled(true);

      DataSet oneDataset = null;

      // Set the accepted powder diffraction file extensions
      // and open a file chooser window for the user to select powder
      // diffraction file
      fileChooser.addChoosableFileFilter(new AcceptFileFilter(PowderFileCabinet.ACCEPTED_FILE_TYPE, "File (*.xy, *.xye, *.txt,*cif)"));
      fileChooser.setAcceptAllFileFilterUsed(false);
      int returnVal = fileChooser.showOpenDialog(null);

      if (returnVal == JFileChooser.APPROVE_OPTION) {
        // get the selected files
        File selectedFiles[] = fileChooser.getSelectedFiles();

        // loop over the selected file
        for (int i = 0, n = selectedFiles.length; i < n; i++) {
          file = selectedFiles[i];
          fileName = selectedFiles[i].getName();
          hash.put(fileName, file);
        }//for
        for (Map.Entry<String, File> entry : hash.entrySet()) {
          fileName = entry.getKey();
          file = entry.getValue();
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
        chartPlotter.add(internalframe);
        setVisible(true);

      }//if open approved


    }//GEN-LAST:event_OpenActionPerformed
  /**
   * Create an instace of the JPowder.
   * @param evt
   */
    private void NewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewActionPerformed
      JPowder newJPowder = new JPowder();
      ScreenUtil.centerFrame(newJPowder);
      newJPowder.setVisible(true);

    }//GEN-LAST:event_NewActionPerformed
  /**
   * set look and feel to windows classic.
   * @param evt
   */
    private void WindowClassicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WindowClassicActionPerformed
      LAF.windosclassic();
    }//GEN-LAST:event_WindowClassicActionPerformed
  /**
   * set look and feel to Apple Mac.
   * @param evt
   */
    private void MacLookAndFeelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MacLookAndFeelActionPerformed
      LAF.appleLook();
    }//GEN-LAST:event_MacLookAndFeelActionPerformed
  /**
   * Set look and feel to Motif.
   * @param evt
   */
    private void MotifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MotifActionPerformed
      LAF.motif();
    }//GEN-LAST:event_MotifActionPerformed
  /**
   * Set look and feel to Nimbus.
   * @param evt
   */
    private void NimbusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NimbusActionPerformed
      LAF.nimbus();
    }//GEN-LAST:event_NimbusActionPerformed
  /**
   * set look and feel to original java look and feel
   * @param evt
   */
    private void MetalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MetalActionPerformed
      LAF.metal();
    }//GEN-LAST:event_MetalActionPerformed
  /**
   * set look and feel to current windows operation system.
   * @param evt
   */
    private void WindowsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WindowsActionPerformed
      LAF.windows();
    }//GEN-LAST:event_WindowsActionPerformed
  /**
   * set look and feel to the Linux and Solaries operating system.
   * @param evt
   */
    private void LinuxSolarisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LinuxSolarisActionPerformed
      LAF.LinuxandSolaris();
    }//GEN-LAST:event_LinuxSolarisActionPerformed
  /**
   *
   * @param evt
   */
    private void workspaceMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_workspaceMenuActionPerformed
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
    }//GEN-LAST:event_workspaceMenuActionPerformed
  /**
   *
   * @param evt
   */
    private void appletMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appletMenuActionPerformed
      System.out.println("Save as serialize");
      JFileChooser chooser = new JFileChooser();
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
              ".Ser ", "Ser");
      chooser.setFileFilter(filter);
      int returnVal = chooser.showSaveDialog(chooser);
      File fileName = chooser.getSelectedFile();
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        try {
          FileOutputStream buffer = new FileOutputStream(fileName);
          final ObjectOutput out = new ObjectOutputStream(buffer);
          out.writeObject(chart);
        } catch (IOException e) {
          JOptionPane.showMessageDialog(null, "Can Not Save!",
                  "error", JOptionPane.ERROR_MESSAGE);
        }
      } else {
        JOptionPane.showMessageDialog(null, "Can Not Save!",
                "error", JOptionPane.ERROR_MESSAGE);
      }
    }//GEN-LAST:event_appletMenuActionPerformed
  /**
   *
   * @param evt
   */
    private void imageMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imageMenuActionPerformed
      try {
        internalFrameInFocus.getChartPanel().doSaveAs();
      } catch (IOException ex) {
        Logger.getLogger(JPowder.class.getName()).log(Level.SEVERE, null, ex);
      }
    }//GEN-LAST:event_imageMenuActionPerformed
  /**
   *
   * @param evt
   */
    private void pDfMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pDfMenuActionPerformed
      System.out.println("saved as pdf");

      JFileChooser chooser = new JFileChooser();
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
              ".pdf ", "pdf");
      chooser.setFileFilter(filter);

      int returnVal = chooser.showSaveDialog(chooser);
      File fileName = chooser.getSelectedFile();
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        try {
          saveChartAsPDF(fileName, internalFrameInFocus.getchart(), 10000, 10000, new DefaultFontMapper());
          System.out.println(fileName.getPath());
          try //try statement
          {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + fileName.getPath());   //open the file chart.pdf

          } catch (Exception e) //catch any exceptions here
          {
            System.out.println("Error" + e);  //print the error
          }
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
      }
    }//GEN-LAST:event_pDfMenuActionPerformed
  /**
   *  Call method from Library to bring on the print panel up.
   * @param evt
   */
    private void copyMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyMenuActionPerformed

      if (JpowderInternalframe.getnumberOfJpowderInternalframe() != 0) {
        internalFrameInFocus.getChartPanel().doCopy();

      } else {
        javax.swing.JOptionPane.showMessageDialog(null, "There is nothing to COPY!");
      }




    }//GEN-LAST:event_copyMenuActionPerformed

    private void proptiesMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proptiesMenuActionPerformed


      if (JpowderInternalframe.getnumberOfJpowderInternalframe() != 0) {
        internalFrameInFocus.getChartPanel().doEditChartProperties();
      } else {
        javax.swing.JOptionPane.showMessageDialog(null, "There is Plotted data");
      }
    }//GEN-LAST:event_proptiesMenuActionPerformed

    private void undoMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoMenuActionPerformed

      JpowderInternalframe frame = jPowderStackUndo.pop();
      if (frame != null) {

        internalFrameInFocus = frame;

        internalFrameInFocus.setVisible(true);
        jPowderStackRedo.push(internalFrameInFocus);
      }

    }//GEN-LAST:event_undoMenuActionPerformed

    private void redoMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redoMenuActionPerformed

      JpowderInternalframe internalFrame = jPowderStackRedo.pop();

      if (internalFrame != null) {
        internalFrame.setVisible(false);


        jPowderStackUndo.push(internalFrame);
      }
    }//GEN-LAST:event_redoMenuActionPerformed

    private void jPrintMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPrintMenuActionPerformed
      if (JpowderInternalframe.getnumberOfJpowderInternalframe() != 0) {
        internalFrameInFocus.getChartPanel().createChartPrintJob();
      } else {
        javax.swing.JOptionPane.showMessageDialog(null, "There is nothing to PRINT!");
      }
    }//GEN-LAST:event_jPrintMenuActionPerformed

    private void jPrintPublishingMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPrintPublishingMenuActionPerformed



      internalFrameInFocus.getXYPlot().setBackgroundPaint(ChartColor.white);

      PrinterJob job = PrinterJob.getPrinterJob();
      PageFormat pf = job.defaultPage();
      PageFormat pf2 = job.pageDialog(pf);
      if (pf2 != pf) {

        job.setPrintable(internalFrameInFocus.getChartPanel(), pf2);
        if (job.printDialog()) {
          try {
            job.print();
          } catch (PrinterException e) {
            JOptionPane.showMessageDialog(this, e);
          }
        }
      }
      internalFrameInFocus.getXYPlot().setBackgroundPaint(ChartColor.LIGHT_GRAY);
    }//GEN-LAST:event_jPrintPublishingMenuActionPerformed

    private void moveMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveMenuActionPerformed
      if (moveMenu.isSelected()) {
        internalFrameInFocus.getXYPlot().setDomainPannable(true);
        internalFrameInFocus.getXYPlot().setRangePannable(true);
      } else {
        internalFrameInFocus.getXYPlot().setDomainPannable(false);
        internalFrameInFocus.getXYPlot().setRangePannable(false);

      }
    }//GEN-LAST:event_moveMenuActionPerformed
  /**
   * To close all the internal frames which are in the ChartPlotter(jDesktopPane).
   * @param evt
   */
    private void clossAllMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clossAllMenuActionPerformed

      JInternalFrame jInternalFrames[] = chartPlotter.getAllFrames(); // get all open frames
      for (int i = 0; i < jInternalFrames.length; i++) {
        try {
          jInternalFrames[i].setClosed(true);
        } catch (PropertyVetoException ex) {
          Logger.getLogger(JPowder.class.getName()).log(Level.SEVERE, null, ex);
        }

      }
    }//GEN-LAST:event_clossAllMenuActionPerformed

  /**
   * responsible for saving and writng the chart as pdf
   * @param file
   * @param chart
   * @param width
   * @param height
   * @param mapper
   * @throws IOException
   */
  public void saveChartAsPDF(File file,
          JFreeChart chart,
          int width,
          int height,
          FontMapper mapper) throws IOException {
    OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
    writeChartAsPDF(out, chart, 1500, 2500, mapper);
    out.close();
  }

  /**
   * write the chart into the pdf and setting the size of the pdf document.
   * @param out
   * @param chart
   * @param width
   * @param height
   * @param mapper
   * @throws IOException
   */
  public void writeChartAsPDF(OutputStream out,
          JFreeChart chart,
          int width,
          int height,
          FontMapper mapper) throws IOException {
    Rectangle pagesize = new Rectangle(width, height);
    Document document = new Document(pagesize, 50, 50, 50, 50);
    try {
      PdfWriter writer = PdfWriter.getInstance(document, out);
      document.open();
      PdfContentByte cb = writer.getDirectContent();
      PdfTemplate tp = cb.createTemplate(width, height);
      Graphics2D g2 = tp.createGraphics(width, height, mapper);
      Rectangle2D r2D = new Rectangle2D.Double(0, 0, width, height);
      chart.draw(g2, r2D);
      g2.dispose();
      cb.addTemplate(tp, 0, 0);
    } catch (DocumentException de) {
      System.err.println(de.getMessage());
    }
    document.close();
  }

  public void dragEnter(DropTargetDragEvent dtde) {
  }

  public void dragOver(DropTargetDragEvent dtde) {
  }

  public void dropActionChanged(DropTargetDragEvent dtde) {
  }

  /**
   *
   * @param dte
   */
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



    chartPlotter.add(internalframe);
    setVisible(true);
    ChartPanel chartPanel = null;
    try {

      chartPanel = new ChartPanel((JFreeChart) internalframe.getchart().clone());
      chartPanel.setSize(thumbnailPanel.getSize());
      thumbnailPanel.add(chartPanel);
      System.out.println("internalframes" + JpowderInternalframe.getnumberOfJpowderInternalframe());
    } catch (CloneNotSupportedException ex) {
      Logger.getLogger(JPowder.class.getName()).log(Level.SEVERE, null, ex);
    }


  }

  /**
   *
   * @param args the command line arguments
   */
  public static void main(String args[]) {

    if (OSValidator.isWindows()) {
      try {
        UIManager.setLookAndFeel(
                new WindowsLookAndFeel());
      } catch (Exception j) {
        j.printStackTrace();
      }
    }
    if (OSValidator.isMac()) {
      try {
        UIManager.setLookAndFeel("com.apple.mrj.swing.MacLookAndFeel");

      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Need Mac operating system ",
                "error", JOptionPane.ERROR_MESSAGE);
      }
    }

    if (OSValidator.isUnix()) {
      try {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");

      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Need Linux operating system ",
                "error", JOptionPane.ERROR_MESSAGE);
      }
    } else {
      try {
        UIManager.getCrossPlatformLookAndFeelClassName();

      } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
      }
    }
    java.awt.EventQueue.invokeLater(new Runnable() {

      JPowder jpowder = new JPowder();

      public void run() {
        jpowder.setLocationRelativeTo(null);
        jpowder.setDefaultCloseOperation(JPowder.EXIT_ON_CLOSE);
        jpowder.setVisible(true);
      }
    });
  }
//  public void internalFrameMoved(ComponentEvent evt) {
//    component = evt.getComponent();
//    stayInContainer();
//    chartPlotter.repaint();
//    java.awt.Rectangle cRect = component.getBounds();
//    cRect.setRect(cRect.getX(), cRect.getY(), cRect.getWidth() + 5, cRect.getHeight() + 5);
//    chartPlotter.scrollRectToVisible(cRect);
//  }
//
//  private boolean isInContainer() {
//    c = component.getParent();
//    java.awt.Rectangle r = new java.awt.Rectangle(c.getWidth(), c.getHeight());
//    wC = (int) r.getWidth();
//    hC = (int) r.getHeight();
//    top_leftPointC = r.getLocation();
//    top_rightPointC = new Point((int) top_leftPointC.getX() + wC, (int) top_leftPointC.getY());
//    bottom_leftPointC = new Point((int) top_leftPointC.getX(), (int) top_leftPointC.getY() + hC);
//    bottom_rightPointC = new Point((int) top_leftPointC.getX() + wC, (int) top_leftPointC.getY() + hC);
//    w = component.getWidth();
//    h = component.getHeight();
//    top_leftPoint = component.getLocation();
//    top_rightPoint = new Point((int) top_leftPoint.getX() + w, (int) top_leftPoint.getY());
//    bottom_leftPoint = new Point((int) top_leftPoint.getX(), (int) top_leftPoint.getY() + h);
//    bottom_rightPoint = new Point((int) top_leftPoint.getX() + w, (int) top_leftPoint.getY() + h);
//    if (!r.contains(top_leftPoint)) {
//      return false;
//    }
//    if (!r.contains(top_rightPoint)) {
//      return false;
//    }
//    if (!r.contains(bottom_leftPoint)) {
//      return false;
//    }
//    if (!r.contains(bottom_rightPoint)) {
//      return false;
//    }
//    return true;
//  }
//
//  public void stayInContainer() {
//    // if the internalframe is too far in right-direction, resize desktop
//    if (!isInContainer()) {
//      double x = top_rightPoint.getX();
//      double xC = top_rightPointC.getX();
//      if (x > xC) {
//        Dimension size = new Dimension((int) (chartPlotter.getWidth() + x - xC), chartPlotter.getHeight());
//        chartPlotter.setPreferredSize(size);
//      }
//    }
////     if the internalframe is too far in left-direction, move it back
//    if (!isInContainer()) {
//      double x = top_leftPoint.getX();
//      double xC = top_leftPointC.getX();
//      if (x < xC) {
//        component.setLocation((int) (top_leftPoint.getX() + xC - x), (int) top_leftPoint.getY());
//      }
//    }
//    // if the internalframe is too far in top-direction, move it back
//    if (!isInContainer()) {
//      double y = top_leftPoint.getY();
//      double yC = top_leftPointC.getY();
//      if (y < yC) {
//        component.setLocation((int) top_leftPoint.getX(), (int) (top_leftPoint.getY() + yC - y));
//      }
//    }
//    /* if the internalframe is is too far in bottom-direction, resize desktop */
//    if (!isInContainer()) {
//      double y = bottom_leftPoint.getY();
//      double yC = bottom_leftPointC.getY();
//      if (y > yC) {
//        component.setLocation((int) top_leftPoint.getX(), (int) (top_leftPoint.getY() + yC - y));
//        Dimension size = new Dimension(chartPlotter.getWidth(), (int) (chartPlotter.getHeight() + y - yC));
//        chartPlotter.setPreferredSize(size);
//      }
//    }
//  }
//  private Component component;
//  private Container c;
//  private Point top_leftPoint, top_rightPoint, bottom_leftPoint, bottom_rightPoint;
//  private Point top_leftPointC, top_rightPointC, bottom_leftPointC, bottom_rightPointC;
//  private int w, h, wC, hC;
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JMenuItem About;
  private javax.swing.JMenuItem Content;
  private javax.swing.JMenuItem Exit;
  private javax.swing.JRadioButtonMenuItem LinuxSolaris;
  private javax.swing.JRadioButtonMenuItem MacLookAndFeel;
  private javax.swing.JRadioButtonMenuItem Metal;
  private javax.swing.JRadioButtonMenuItem Motif;
  private javax.swing.JMenuItem New;
  private javax.swing.JRadioButtonMenuItem Nimbus;
  private javax.swing.JMenuItem OnlieDocsandSupport;
  private javax.swing.JMenuItem Open;
  private javax.swing.JMenu Print;
  private javax.swing.JTabbedPane Tabs;
  private javax.swing.JRadioButtonMenuItem WindowClassic;
  private javax.swing.JRadioButtonMenuItem Windows;
  private javax.swing.JPanel analysistab;
  private javax.swing.JMenuItem appletMenu;
  private javax.swing.JDesktopPane chartPlotter;
  private javax.swing.JPanel chartToolstab;
  private javax.swing.JMenuItem clossAllMenu;
  private javax.swing.JMenuItem copyMenu;
  private javax.swing.JPanel dataVisibleInChartPanel;
  private javax.swing.JMenu editMenu;
  private javax.swing.JMenu fileMenu;
  private javax.swing.JMenu helpMenu;
  private javax.swing.JPanel home;
  private javax.swing.JMenuItem imageMenu;
  private javax.swing.JMenuBar jMenuBar1;
  private javax.swing.JMenuItem jPrintMenu;
  private javax.swing.JMenuItem jPrintPublishingMenu;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JSeparator jSeparator1;
  private javax.swing.JSeparator jSeparator2;
  private javax.swing.JSeparator jSeparator3;
  private javax.swing.JSeparator jSeparator4;
  private javax.swing.JSeparator jSeparator5;
  private javax.swing.JSeparator jSeparator6;
  private javax.swing.JMenu lookAndFeelMenu;
  private javax.swing.JCheckBoxMenuItem moveMenu;
  private javax.swing.JMenuItem pDfMenu;
  private javax.swing.JMenuItem proptiesMenu;
  private javax.swing.JMenuItem redoMenu;
  private javax.swing.JMenu saveAs;
  private javax.swing.JPanel thumbnailPanel;
  private javax.swing.JPanel treetab;
  private javax.swing.JMenuItem undoMenu;
  private javax.swing.JMenuItem workspaceMenu;
  // End of variables declaration//GEN-END:variables
}
