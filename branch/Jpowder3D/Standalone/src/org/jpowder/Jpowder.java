/* ===========================================================
 * This file is part of Jpowder, see <http://www.jpowder.org/>
 * ===========================================================
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * ---------
 * Jpowder.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  Kreecha Puphaiboon, Computer Science Lecturer, Kasem Bundit University
 *             M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *             Anders Marvardsen, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder;

import org.jpowder.InernalFrame.JpowderInternalframe2D;
import org.jpowder.InernalFrame.JpowderInternalframe;
import java.beans.PropertyVetoException;
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
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.InternalFrameListener;
import org.jpowder.Analysis.ToolsIcon2D;
import org.jpowder.dataset.DataSet;
import org.jpowder.fileCabinet.PowderFileCabinet;
import org.jpowder.util.ScreenUtil;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import org.jfree.chart.ChartColor;
import org.jpowder.Analysis.ToolsIcon3D;
import org.jpowder.InernalFrame.InternalFrameIconifyListener;
import org.jpowder.InernalFrame.JpowderInternalframe3D;
import org.jpowder.tree.JpowderFileSystemTreeModel;

/**
 * Mainly used for putting all the GUIs together for Jpowder.
 *
 */
public class Jpowder extends JFrame implements DropTargetListener {

    private JpowderFileSystemTreeModel treeModel = new JpowderFileSystemTreeModel();
    private Tree tree = new Tree(treeModel);
    public DataVisibleInChart dataVisibleInChart = new DataVisibleInChart();
    private PowderFileCabinet mPowderFileCabinet;
    private java.awt.dnd.DropTarget dropTarget;    // for desktop dragging and dropping
    private ToolsIcon2D toolsIcon2D = new ToolsIcon2D(this);
    private ToolsIcon3D toolsIcon3D = new ToolsIcon3D(this);
    private CardLayout cardLayout;  // layout for tools tab
    public static JpowderInternalframe2D internalFrameInFocus2D;
    public static JpowderInternalframe3D internalFrameInFocus3D;
    public static String plotAreaInFocus;
    private JPowderDesktopManager jPowderDesktopManager = new JPowderDesktopManager();
    public static InfoPanel infoPanelInfocus;
    public static JPowderStack jPowderStackUndo = new JPowderStack(3);
    public static JPowderStack jPowderStackRedo = new JPowderStack(3);
    private static double dropLocationX, dropLocationY;
    /** A flag that controls whether or not file extensions are enforced. */
    private boolean enforceFileExtensions = true;

 
 
    public Jpowder() {
        initComponents();

        mPowderFileCabinet = new PowderFileCabinet();

        dropTarget = new DropTarget(chartPlotterPane2D, this);

        dataVisibleInChartPanel.add(dataVisibleInChart);
        dropTarget = new DropTarget(chartPlotterPane3D, new DragAndDrop3D(dataVisibleInChart));
        explorertab.add(tree, "1");
        toolstab2D.add(toolsIcon2D, "1");
        toolstab3D.add(toolsIcon3D, "1");
        messageLabel2D.setLocation(chartPlotterPane2D.getWidth() / 4, chartPlotterPane2D.getHeight() / 2);

        //to keep newly added JInternalFrame inside the JDesktopPane (KP)
        chartPlotterPane2D.addContainerListener(new FrameAddedSupervisor());
        chartPlotterPane3D.addContainerListener(new FrameAddedSupervisor());
        ScreenUtil.adjustBounds(this);


//        JButton butt = new JButton();
//        plotsTab.add(butt);
        for (int i = 0; i < 2; i++) {
            plotsTab.setTabComponentAt(i,
                    new PlotsTabButton(plotsTab));
        }
    }
//    @Override
//    public void paint(Graphics g) {
//            Graphics2D g2 = (Graphics2D) g;
//                  Rectangle bounds = getBounds();
//
//      // Set Paint for filling Shape
//       GradientPaint gradientPaint = new GradientPaint(75, 75, Color.BLACK, 95, 95,
//        Color.gray, true);
//      g2.setPaint(gradientPaint);
//      g2.fillRect(0, 0, bounds.width, bounds.height);
////       g2.fill(e);
//
//    }

    /**
     *
     * @return chartPlotter
     */
    public static JDesktopPane getChartPlotter2D() {
        return chartPlotterPane2D;
    }

    /**
     *
     * @return chartPlotter
     */
    public static JDesktopPane getChartPlotter3D() {
        return chartPlotterPane3D;
    }

    /**
     *
     * @return analysistab.
     */
    public JPanel getToolsTab2D() {
        return toolstab2D;
    }

    public JPanel getToolsTab3D() {
        return toolstab3D;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    /**
     * this method return the x location of whereever the file is droped.
     * @return dropLocationX
     */
    public static double getDropLocationX() {
        return dropLocationX;
    }

    /**
     * this method return the y location of the where the file is droped.
     * @return dropLocationY
     */
    public static double getDropLocationY() {
        return dropLocationY;
    }

    /**
     *
     * @param info
     */
    public static void jpowderInfoPanelUpdate(InfoPanel info) {
        infoPanelInfocus = info;
        infoPanelInfocus.update();

    }

    /** A new plot area has been put in focus by user. Update accordingly
     *
     * @param whichOne Is the plot area in focus 2D or 3D
     */
    public static void jpowderPlotAreaUpdate(String whichOne) {
        plotAreaInFocus = whichOne;

        if (plotAreaInFocus.equals("3D")) {
            Jpowder.jpowderInternalFrameUpdate(internalFrameInFocus3D);
        }
        if (plotAreaInFocus.equals("2D")) {
            Jpowder.jpowderInternalFrameUpdate(internalFrameInFocus2D);
        }

    }

    /** A new internal frame has been put in focus by user. Update accordingly
     *
     * @param internalFrame
     */
    public static void jpowderInternalFrameUpdate(JpowderInternalframe internalFrame) {

        if (internalFrame instanceof JpowderInternalframe2D) {
            if (internalFrame != internalFrameInFocus2D) {
                internalFrameInFocus2D = (JpowderInternalframe2D) internalFrame;
//            chartInFocus = internalFrame.getchart();
            }
        }
        if (internalFrame instanceof JpowderInternalframe3D) {
            if (internalFrame != internalFrameInFocus3D) {
                internalFrameInFocus3D = (JpowderInternalframe3D) internalFrame;
//            chartInFocus = internalFrame.getchart();
            }
        }


        if (infoPanelInfocus != null) {
            infoPanelInfocus.update();
        }

        if (internalFrame != null) {
            internalFrame.getDataVisibleInChartPanel().newChartInFocus(internalFrame.getXYPlot(), internalFrame.getPowderDataSet());
        }
    }

    /**
     * this methods checks for internalframe inside the ChartPlotterPane and if it
     * exist then removes the jlabel from ChartPlotterPane.
     */
    public void displayingMessageLabel() {

        if (plotsTab.getSelectedIndex() == 0) {
            if (chartPlotterPane2D.getAllFrames().length == 1) {
                chartPlotterPane2D.remove(messageLabel2D);
                chartPlotterPane2D.repaint();
            }
        }
        if (plotsTab.getSelectedIndex() == 1) {
            if (chartPlotterPane3D.getAllFrames().length == 1) {
                chartPlotterPane3D.remove(messageLabel3D);
                chartPlotterPane3D.repaint();
            }


        }
    }

    public static JLabel getMessageLabel() {
        return messageLabel2D;
    }

    /**
     * this methods provides a bit memory management
     */
    public static void memoryChecker() {

        double oneByte = (1024 * 1024);  // 1M bytes

        long totalM = (long) (Runtime.getRuntime().totalMemory() / oneByte);
        long freeM = (long) (Runtime.getRuntime().freeMemory() / oneByte);
        //long MaxM = (long) (Runtime.getRuntime().maxMemory() / oneByte);

        if ((totalM - freeM) > 900) {
            // create a double internal frame and add to Plot Area
            // and then delete it to trick JWM
            // print out new memory size

            JOptionPane.showMessageDialog(null,
                    "Close some of charts " +
                    "you are about to run out of memory.",
                    "Memory warning",
                    JOptionPane.WARNING_MESSAGE);
            JInternalFrame frame = new JInternalFrame("Memory release");
            chartPlotterPane2D.add(frame);
            chartPlotterPane2D.remove(frame);

// potential useful comments
//            this.setExtendedState(JFrame.ICONIFIED);
//            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
//            this.setSize(1154, 888);

        }

//        System.out.println("total M : " + totalM);
//        System.out.println("free M  : " + freeM);
//        System.out.println("Max M  : " + MaxM);
//        System.out.println("total- free M : " + (totalM - freeM));
        
    }

    public boolean isEnforceFileExtensions() {
        return this.enforceFileExtensions;
    }

    public static JTabbedPane getPlotsTab() {
        return plotsTab;
    }

    public DataVisibleInChart getDataVisibleInChart() {
        return dataVisibleInChart;
    }

    /** This method is called from within the constructor to
     * initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        homePanelScrollPane = new javax.swing.JScrollPane();
        homePanel = new javax.swing.JPanel();
        tabs = new javax.swing.JTabbedPane();
        explorertab = new javax.swing.JPanel();
        cardLayout = new CardLayout();
        toolstab2D = new javax.swing.JPanel();
        toolstab3D = new javax.swing.JPanel();
        dataVisibleInChartPanel = new javax.swing.JPanel();
        plotsTab = new javax.swing.JTabbedPane();
        chartPlotterPane2D = new javax.swing.JDesktopPane();
        messageLabel2D = new javax.swing.JLabel();
        chartPlotterPane3D = new javax.swing.JDesktopPane();
        messageLabel3D = new javax.swing.JLabel();
        JpowderMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        oPenMenu = new javax.swing.JMenuItem();
        saveWorksPaceMenu = new javax.swing.JMenuItem();
        saveAsMenu = new javax.swing.JMenu();
        appletMenu = new javax.swing.JMenuItem();
        imageMenu = new javax.swing.JMenuItem();
        pDfMenu = new javax.swing.JMenuItem();
        pdfForPublication = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        printMenu = new javax.swing.JMenu();
        basicPrintMenu = new javax.swing.JMenuItem();
        printPublishingMenu = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        closeFrameMenu = new javax.swing.JMenuItem();
        clossAllMenu = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JSeparator();
        exitMenu = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        undoMenu = new javax.swing.JMenuItem();
        redoMenu = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JSeparator();
        copyMenu = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JSeparator();
        propertiesMenu = new javax.swing.JMenuItem();
        importTable3D = new javax.swing.JMenuItem();
        windowMenu = new javax.swing.JMenu();
        defaultCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        tileCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        tileHorizontallyCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        tileVerticallyCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        cascadeCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        helpMenu = new javax.swing.JMenu();
        onlineDocsAndSupportMenu = new javax.swing.JMenuItem();
        aboutMenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jpowder");
        setIconImage(new ImageIcon(getClass().getResource("/images/JpowderLogo.png")).getImage());
        setLocationByPlatform(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        homePanelScrollPane.setBackground(new java.awt.Color(153, 153, 255));
        homePanelScrollPane.setBorder(null);
        homePanelScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        homePanelScrollPane.setMinimumSize(new java.awt.Dimension(10, 5));
        homePanelScrollPane.getHorizontalScrollBar().setSize(0,0);

        homePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        homePanel.setPreferredSize(new java.awt.Dimension(320, 727));

        tabs.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tabs.setFont(new java.awt.Font("Tahoma", 0, 14));
        tabs.setMaximumSize(new java.awt.Dimension(327, 32767));
        tabs.setPreferredSize(new java.awt.Dimension(275, 800));
        tabs.setVerifyInputWhenFocusTarget(false);

        explorertab.setPreferredSize(new java.awt.Dimension(275, 400));
        explorertab.setLayout(new java.awt.CardLayout());
        tabs.addTab("Explorer", explorertab);

        toolstab2D.setPreferredSize(new java.awt.Dimension(270, 320));
        toolstab2D.setLayout(new java.awt.CardLayout());

        toolstab2D.setLayout(cardLayout);

        tabs.addTab(" Tools 2D", toolstab2D);

        toolstab3D.setLayout(new java.awt.CardLayout());
        tabs.addTab("Tools 3D", toolstab3D);

        dataVisibleInChartPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Plot(s) Visible In Chart"));
        dataVisibleInChartPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout homePanelLayout = new javax.swing.GroupLayout(homePanel);
        homePanel.setLayout(homePanelLayout);
        homePanelLayout.setHorizontalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
            .addComponent(dataVisibleInChartPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
        );
        homePanelLayout.setVerticalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homePanelLayout.createSequentialGroup()
                .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dataVisibleInChartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        homePanelScrollPane.setViewportView(homePanel);

        plotsTab.setFont(new java.awt.Font("Tahoma", 0, 14));
        plotsTab.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                plotsTabStateChanged(evt);
            }
        });

        chartPlotterPane2D.setBackground(new java.awt.Color(236, 233, 216));
        chartPlotterPane2D.setForeground(new java.awt.Color(102, 102, 102));
        chartPlotterPane2D.setDesktopManager(jPowderDesktopManager);
        chartPlotterPane2D.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                chartPlotterPane2DComponentResized(evt);
            }
        });
        chartPlotterPane2D.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                chartPlotterPane2DComponentAdded(evt);
            }
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                chartPlotterPane2DComponentRemoved(evt);
            }
        });

        messageLabel2D.setFont(new java.awt.Font("Arial", 0, 36));
        messageLabel2D.setForeground(new java.awt.Color(153, 153, 153));
        messageLabel2D.setText("Drag & Drop Files Here.");
        messageLabel2D.setEnabled(false);
        messageLabel2D.setBounds(240, 370, 400, 50);
        chartPlotterPane2D.add(messageLabel2D, javax.swing.JLayeredPane.DEFAULT_LAYER);

        plotsTab.addTab("2D Plots", chartPlotterPane2D);

        chartPlotterPane3D.setBackground(new java.awt.Color(236, 233, 216));
        chartPlotterPane3D.setDesktopManager(jPowderDesktopManager);
        chartPlotterPane3D.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                chartPlotterPane3DComponentResized(evt);
            }
        });
        chartPlotterPane3D.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                chartPlotterPane3DComponentAdded(evt);
            }
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                chartPlotterPane3DComponentRemoved(evt);
            }
        });

        messageLabel3D.setFont(new java.awt.Font("Arial", 0, 36));
        messageLabel3D.setForeground(new java.awt.Color(153, 153, 153));
        messageLabel3D.setText("Drag & Drop Files Here.");
        messageLabel3D.setEnabled(false);
        messageLabel3D.setBounds(240, 370, 400, 50);
        chartPlotterPane3D.add(messageLabel3D, javax.swing.JLayeredPane.DEFAULT_LAYER);

        plotsTab.addTab("3D Plots", chartPlotterPane3D);
        //chartPlotterPane3D.add(messageLabel2D, javax.swing.JLayeredPane.DEFAULT_LAYER);

        JpowderMenuBar.setFont(new java.awt.Font("Tahoma", 0, 36));

        fileMenu.setText("File");
        fileMenu.setFont(new java.awt.Font("Tahoma", 0, 14));

        oPenMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        oPenMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Open1.png"))); // NOI18N
        oPenMenu.setText("Open");
        oPenMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oPenMenuActionPerformed(evt);
            }
        });
        fileMenu.add(oPenMenu);

        saveWorksPaceMenu.setText("Save WorkSpace");
        saveWorksPaceMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveWorksPaceMenuActionPerformed(evt);
            }
        });
        fileMenu.add(saveWorksPaceMenu);

        saveAsMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/saveas_16x16.png"))); // NOI18N
        saveAsMenu.setText("Save Chart As");

        appletMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exportto_16x16.png"))); // NOI18N
        appletMenu.setText("Jpowder-Applet Format");
        appletMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appletMenuActionPerformed(evt);
            }
        });
        saveAsMenu.add(appletMenu);

        imageMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Image.png"))); // NOI18N
        imageMenu.setText("Image");
        imageMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imageMenuActionPerformed(evt);
            }
        });
        saveAsMenu.add(imageMenu);

        pDfMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exportdirecttopdf_16x16.png"))); // NOI18N
        pDfMenu.setText("PDF");
        pDfMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pDfMenuActionPerformed(evt);
            }
        });
        saveAsMenu.add(pDfMenu);

        pdfForPublication.setText("PDF For Publication");
        pdfForPublication.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdfForPublicationActionPerformed(evt);
            }
        });
        saveAsMenu.add(pdfForPublication);

        fileMenu.add(saveAsMenu);
        fileMenu.add(jSeparator2);

        printMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Print.png"))); // NOI18N
        printMenu.setText("Print Chart As");

        basicPrintMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        basicPrintMenu.setText("Print");
        basicPrintMenu.setToolTipText("Print ");
        basicPrintMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                basicPrintMenuActionPerformed(evt);
            }
        });
        printMenu.add(basicPrintMenu);

        printPublishingMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        printPublishingMenu.setText("Print For Publication");
        printPublishingMenu.setToolTipText("Print with white background");
        printPublishingMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printPublishingMenuActionPerformed(evt);
            }
        });
        printMenu.add(printPublishingMenu);

        fileMenu.add(printMenu);
        fileMenu.add(jSeparator1);

        closeFrameMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Close.png"))); // NOI18N
        closeFrameMenu.setText("Close Chart");
        closeFrameMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeFrameMenuActionPerformed(evt);
            }
        });
        fileMenu.add(closeFrameMenu);

        clossAllMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        clossAllMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/CloseAll.png"))); // NOI18N
        clossAllMenu.setText("Close All");
        clossAllMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clossAllMenuActionPerformed(evt);
            }
        });
        fileMenu.add(clossAllMenu);
        fileMenu.add(jSeparator4);

        exitMenu.setText("Exit");
        exitMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenu);

        JpowderMenuBar.add(fileMenu);

        editMenu.setText("Edit");
        editMenu.setFont(new java.awt.Font("Tahoma", 0, 14));

        undoMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        undoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/undo_26x26.png"))); // NOI18N
        undoMenu.setText("Undo Closed Window");
        undoMenu.setToolTipText("Undo closed window.");
        undoMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoMenuActionPerformed(evt);
            }
        });
        editMenu.add(undoMenu);

        redoMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        redoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/redo_26x26.png"))); // NOI18N
        redoMenu.setText("Redo Closed Window");
        redoMenu.setToolTipText("Redo closed window.");
        redoMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redoMenuActionPerformed(evt);
            }
        });
        editMenu.add(redoMenu);
        editMenu.add(jSeparator5);

        copyMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        copyMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/copy_16x16.png"))); // NOI18N
        copyMenu.setText("Copy To Clipboard");
        copyMenu.setToolTipText("Copy selected chart to clipboard.");
        copyMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyMenuActionPerformed(evt);
            }
        });
        editMenu.add(copyMenu);
        editMenu.add(jSeparator6);

        propertiesMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        propertiesMenu.setText("Chart Properties");
        propertiesMenu.setToolTipText("set propeties of selected chart.\n");
        propertiesMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                propertiesMenuActionPerformed(evt);
            }
        });
        editMenu.add(propertiesMenu);

        importTable3D.setText("Create 3D Plot");
        importTable3D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importTable3DActionPerformed(evt);
            }
        });
        editMenu.add(importTable3D);

        JpowderMenuBar.add(editMenu);

        windowMenu.setText("Window");
        windowMenu.setFont(new java.awt.Font("Tahoma", 0, 14));

        buttonGroup1.add(defaultCheckBoxMenuItem);
        defaultCheckBoxMenuItem.setSelected(true);
        defaultCheckBoxMenuItem.setText("Default");
        defaultCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                defaultCheckBoxMenuItemActionPerformed(evt);
            }
        });
        windowMenu.add(defaultCheckBoxMenuItem);

        buttonGroup1.add(tileCheckBoxMenuItem);
        tileCheckBoxMenuItem.setText("Tile");
        tileCheckBoxMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Tile.png"))); // NOI18N
        tileCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tileCheckBoxMenuItemActionPerformed(evt);
            }
        });
        windowMenu.add(tileCheckBoxMenuItem);

        buttonGroup1.add(tileHorizontallyCheckBoxMenuItem);
        tileHorizontallyCheckBoxMenuItem.setText("Tile Windows Horizontally ");
        tileHorizontallyCheckBoxMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/TileHorizontally.png"))); // NOI18N
        tileHorizontallyCheckBoxMenuItem.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/TileHorizontally.png"))); // NOI18N
        tileHorizontallyCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tileHorizontallyCheckBoxMenuItemActionPerformed(evt);
            }
        });
        windowMenu.add(tileHorizontallyCheckBoxMenuItem);

        buttonGroup1.add(tileVerticallyCheckBoxMenuItem);
        tileVerticallyCheckBoxMenuItem.setText("Tile Windows Vertically");
        tileVerticallyCheckBoxMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/TileVertically.png"))); // NOI18N
        tileVerticallyCheckBoxMenuItem.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/TileVertically.png"))); // NOI18N
        tileVerticallyCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tileVerticallyCheckBoxMenuItemActionPerformed(evt);
            }
        });
        windowMenu.add(tileVerticallyCheckBoxMenuItem);

        buttonGroup1.add(cascadeCheckBoxMenuItem);
        cascadeCheckBoxMenuItem.setText("Cascade");
        cascadeCheckBoxMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/TileCascade.png"))); // NOI18N
        cascadeCheckBoxMenuItem.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/TileCascade.png"))); // NOI18N
        cascadeCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cascadeCheckBoxMenuItemActionPerformed(evt);
            }
        });
        windowMenu.add(cascadeCheckBoxMenuItem);

        JpowderMenuBar.add(windowMenu);

        helpMenu.setText("Help");
        helpMenu.setFont(new java.awt.Font("Tahoma", 0, 14));

        onlineDocsAndSupportMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, java.awt.event.InputEvent.CTRL_MASK));
        onlineDocsAndSupportMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Web.png"))); // NOI18N
        onlineDocsAndSupportMenu.setText("Online Docs and Support");
        onlineDocsAndSupportMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onlineDocsAndSupportMenuActionPerformed(evt);
            }
        });
        helpMenu.add(onlineDocsAndSupportMenu);

        aboutMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        aboutMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/info_16x16.png"))); // NOI18N
        aboutMenu.setText("About");
        aboutMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenu);

        JpowderMenuBar.add(helpMenu);

        setJMenuBar(JpowderMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(homePanelScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(plotsTab, javax.swing.GroupLayout.DEFAULT_SIZE, 853, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(plotsTab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE)
                    .addComponent(homePanelScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * open the browser to Jpowder web site for addition helps.
     * @param evt
     */
    private void onlineDocsAndSupportMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onlineDocsAndSupportMenuActionPerformed
        try {

            java.net.URI uri = new URI("http://www.jpowder.org");
            java.awt.Desktop.getDesktop().browse(uri);
        } catch (URISyntaxException ex) {
            JOptionPane.showMessageDialog(null,
                    "Error attempting to launch web browser\n" + ex.toString());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "Error attempting to launch web browser\n" + ex.toString());
        }
    }//GEN-LAST:event_onlineDocsAndSupportMenuActionPerformed
    /**
     * call the about panel.
     * @param evt
     */
    private void aboutMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuActionPerformed
        //this bit of the code search for the files

        new About().setVisible(true);
        BuildDate buidDate = new BuildDate();
        About.getDateTextField().setText(buidDate.getVersion());

    }//GEN-LAST:event_aboutMenuActionPerformed
    /**
     *
     * @param evt
     */
    private void copyMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyMenuActionPerformed
        if (plotsTab.getSelectedIndex() == 0) {
            if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() != 0) {

                internalFrameInFocus2D.getChartPanel().doCopy();

            } else {
                return;
            }
        }
        if (plotsTab.getSelectedIndex() == 1) {
            if (JpowderInternalframe3D.getnumberOfJpowderInternalframe() != 0) {

                internalFrameInFocus3D.getChartPanel().doCopy();

            } else {
                return;
            }
        }
    }//GEN-LAST:event_copyMenuActionPerformed

    private void propertiesMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_propertiesMenuActionPerformed
        if (plotsTab.getSelectedIndex() == 0) {

            if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() != 0) {
                internalFrameInFocus2D.getChartPanel().doEditChartProperties();
            } else {
                return;
            }
        }
        if (plotsTab.getSelectedIndex() == 1) {

            if (JpowderInternalframe3D.getnumberOfJpowderInternalframe() != 0) {
                internalFrameInFocus3D.getChartPanel().doEditChartProperties();
            } else {
                return;
            }
        }
    }//GEN-LAST:event_propertiesMenuActionPerformed

    private void undoMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoMenuActionPerformed

        JpowderInternalframe frame = jPowderStackUndo.pop();
        if (frame != null) {

//            internalFrameInFocus = frame;
            internalFrameInFocus2D.setVisible(true);
            jPowderStackRedo.push(internalFrameInFocus2D);

            chartPlotterPane2D.add(internalFrameInFocus2D);
        }
    }//GEN-LAST:event_undoMenuActionPerformed
    /**
     * 
     * @param evt
     */
    private void redoMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redoMenuActionPerformed

        JpowderInternalframe internalFrame = jPowderStackRedo.pop();

        if (internalFrame != null) {
            internalFrame.setVisible(false);
            jPowderStackUndo.push(internalFrame);
            chartPlotterPane2D.remove(internalFrame);
        }
    }//GEN-LAST:event_redoMenuActionPerformed
    /**
     * To close all the internal frames which are in the ChartPlotter(jDesktopPane).
     * @param evt
     */
    private void defaultCheckBoxMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_defaultCheckBoxMenuItemActionPerformed


        if (plotsTab.getSelectedIndex() == 0) {

            chartPlotterPane2D.setLayout(null);
        }
        if (plotsTab.getSelectedIndex() == 1) {
            chartPlotterPane3D.setLayout(null);
        }
    }//GEN-LAST:event_defaultCheckBoxMenuItemActionPerformed

    private void tileCheckBoxMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tileCheckBoxMenuItemActionPerformed

        if (plotsTab.getSelectedIndex() == 0) {
            if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() > 0) {
                JInternalFrame jInternalFrames[] = chartPlotterPane2D.getAllFrames(); // get all open frames
                chartPlotterPane2D.setLayout(new GridLayout(jInternalFrames.length, jInternalFrames.length, 0, 0));
                chartPlotterPane2D.updateUI();
            } else {
                return;
            }

        }
        if (plotsTab.getSelectedIndex() == 1) {
            if (JpowderInternalframe3D.getnumberOfJpowderInternalframe() > 0) {
                JInternalFrame jInternalFrames[] = chartPlotterPane3D.getAllFrames(); // get all open frames
                chartPlotterPane3D.setLayout(new GridLayout(jInternalFrames.length, jInternalFrames.length, 0, 0));
                chartPlotterPane3D.updateUI();
            } else {
                return;
            }
        }
    }//GEN-LAST:event_tileCheckBoxMenuItemActionPerformed

    private void tileHorizontallyCheckBoxMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tileHorizontallyCheckBoxMenuItemActionPerformed
        if (plotsTab.getSelectedIndex() == 0) {
            if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() > 0) {
                JInternalFrame jInternalFrames[] = chartPlotterPane2D.getAllFrames(); // get all open frames
                chartPlotterPane2D.setLayout(new GridLayout(jInternalFrames.length, 1, 0, 0));
                chartPlotterPane2D.updateUI();
            } else {
                return;
            }
        }
        if (plotsTab.getSelectedIndex() == 1) {
            if (JpowderInternalframe3D.getnumberOfJpowderInternalframe() > 0) {
                JInternalFrame jInternalFrames[] = chartPlotterPane3D.getAllFrames(); // get all open frames
                chartPlotterPane3D.setLayout(new GridLayout(jInternalFrames.length, 1, 0, 0));
                chartPlotterPane3D.updateUI();
            } else {
                return;
            }
        }
    }//GEN-LAST:event_tileHorizontallyCheckBoxMenuItemActionPerformed

    private void tileVerticallyCheckBoxMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tileVerticallyCheckBoxMenuItemActionPerformed
        if (plotsTab.getSelectedIndex() == 0) {
            if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() > 0) {
                JInternalFrame jInternalFrames[] = chartPlotterPane2D.getAllFrames(); // get all open frames
                chartPlotterPane2D.setLayout(new GridLayout(1, jInternalFrames.length, 0, 0));
                chartPlotterPane2D.updateUI();
            } else {
                return;
            }
        }
        if (plotsTab.getSelectedIndex() == 1) {
            if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() > 0) {
                JInternalFrame jInternalFrames[] = chartPlotterPane3D.getAllFrames(); // get all open frames
                chartPlotterPane3D.setLayout(new GridLayout(1, jInternalFrames.length, 0, 0));
                chartPlotterPane3D.updateUI();
            } else {
                return;
            }
        }
    }//GEN-LAST:event_tileVerticallyCheckBoxMenuItemActionPerformed

    private void cascadeCheckBoxMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cascadeCheckBoxMenuItemActionPerformed
        if (plotsTab.getSelectedIndex() == 0) {
            if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() > 0) {
                chartPlotterPane2D.setLayout(null);
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        JInternalFrame jInternalFrames[] = chartPlotterPane2D.getAllFrames(); // get all open frames
                        int top = 0;
                        int left = 0;
                        for (int i = 0; i < jInternalFrames.length; i++) {

                            SwingUtilities.invokeLater(new Runnable() {

                                @Override
                                public void run() {
                                    try {
                                        internalFrameInFocus2D.setSelected(true);
                                        internalFrameInFocus2D.moveToBack();
                                    } catch (PropertyVetoException ex) {
                                        Logger.getLogger(Jpowder.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });
                            jInternalFrames[i].setSize(chartPlotterPane2D.getWidth() / 2, 300);
                            jInternalFrames[i].setLocation(top, left);
                            top += 30;
                            left += 30;
                        }
                    }
                });
            } else {
                return;
            }
        }


        if (plotsTab.getSelectedIndex() == 1) {
            if (JpowderInternalframe3D.getnumberOfJpowderInternalframe() > 0) {
                chartPlotterPane2D.setLayout(null);
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        JInternalFrame jInternalFrames[] = chartPlotterPane3D.getAllFrames(); // get all open frames
                        int top = 0;
                        int left = 0;
                        for (int i = 0; i < jInternalFrames.length; i++) {

                            SwingUtilities.invokeLater(new Runnable() {

                                @Override
                                public void run() {
                                    try {
                                        internalFrameInFocus3D.setSelected(true);
                                        internalFrameInFocus3D.moveToBack();
                                    } catch (PropertyVetoException ex) {
                                        Logger.getLogger(Jpowder.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });
                            jInternalFrames[i].setSize(chartPlotterPane3D.getWidth() / 2, 300);
                            jInternalFrames[i].setLocation(top, left);
                            top += 30;
                            left += 30;
                        }
                    }
                });
            } else {
                return;
            }
        }

    }//GEN-LAST:event_cascadeCheckBoxMenuItemActionPerformed

    private void exitMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuActionPerformed
        System.exit(0);
}//GEN-LAST:event_exitMenuActionPerformed

    private void clossAllMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clossAllMenuActionPerformed
        if (plotsTab.getSelectedIndex() == 0) {
            if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() > 0) {
                JInternalFrame jInternalFrames[] = chartPlotterPane2D.getAllFrames(); // get all open frames
                for (int i = 0; i < jInternalFrames.length; i++) {
                    try {
                        jInternalFrames[i].setClosed(true);
                    } catch (PropertyVetoException ex) {
                        Logger.getLogger(Jpowder.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            } else {
                return;
            }
        }
        if (plotsTab.getSelectedIndex() == 1) {
            if (JpowderInternalframe3D.getnumberOfJpowderInternalframe() > 0) {
                JInternalFrame jInternalFrames[] = chartPlotterPane3D.getAllFrames(); // get all open frames
                for (int i = 0; i < jInternalFrames.length; i++) {
                    try {
                        jInternalFrames[i].setClosed(true);
                    } catch (PropertyVetoException ex) {
                        Logger.getLogger(Jpowder.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            } else {
                return;
            }
        }
}//GEN-LAST:event_clossAllMenuActionPerformed

    private void closeFrameMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeFrameMenuActionPerformed

        if (plotsTab.getSelectedIndex() == 0) {
            if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() != 0) {
                try {
                    internalFrameInFocus2D.setClosed(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(Jpowder.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                return;
            }
        }

        if (plotsTab.getSelectedIndex() == 1) {
            if (JpowderInternalframe3D.getnumberOfJpowderInternalframe() != 0) {
                try {
                    internalFrameInFocus3D.setClosed(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(Jpowder.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                return;
            }
        }
}//GEN-LAST:event_closeFrameMenuActionPerformed

    private void printPublishingMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printPublishingMenuActionPerformed
               if (plotsTab.getSelectedIndex() == 0) {
            JpowderPrint jpowderPrint = new JpowderPrint(internalFrameInFocus2D.getChartPanel());
            jpowderPrint.printForPublication();
               }
           if (plotsTab.getSelectedIndex() == 1) {
            JpowderPrint jpowderPrint = new JpowderPrint(internalFrameInFocus3D.getChartPanel());
            jpowderPrint.printForPublication();
               }
}//GEN-LAST:event_printPublishingMenuActionPerformed

    private void basicPrintMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_basicPrintMenuActionPerformed
         if (plotsTab.getSelectedIndex() == 0) {
        JpowderPrint jpowderPrint = new JpowderPrint(internalFrameInFocus2D.getChartPanel());
        jpowderPrint.basicPrint();
         }
           if (plotsTab.getSelectedIndex() == 1) {
        JpowderPrint jpowderPrint = new JpowderPrint(internalFrameInFocus3D.getChartPanel());
        jpowderPrint.basicPrint();
         }
}//GEN-LAST:event_basicPrintMenuActionPerformed

    private void pDfMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pDfMenuActionPerformed
        if (plotsTab.getSelectedIndex() == 0) {
            if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() != 0) {
                JpowderPopupMenu jpowderPopupMenu = new JpowderPopupMenu(internalFrameInFocus2D.getChartPanel());
                jpowderPopupMenu.pDF();
            } else {
                return;
            }
        }
        if (plotsTab.getSelectedIndex() == 1) {
            if (JpowderInternalframe3D.getnumberOfJpowderInternalframe() != 0) {
                JpowderPopupMenu jpowderPopupMenu = new JpowderPopupMenu(internalFrameInFocus3D.getChartPanel());
                jpowderPopupMenu.pDF();
            } else {
                return;
            }
        }
    }//GEN-LAST:event_pDfMenuActionPerformed

    private void imageMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imageMenuActionPerformed
        if (plotsTab.getSelectedIndex() == 0) {
            if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() != 0) {
                try {
                    internalFrameInFocus2D.getChartPanel().doSaveAs();
                } catch (IOException ex) {
                    Logger.getLogger(Jpowder.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                return;
            }
        }
        if (plotsTab.getSelectedIndex() == 1) {
            if (JpowderInternalframe3D.getnumberOfJpowderInternalframe() != 0) {
                try {
                    internalFrameInFocus3D.getChartPanel().doSaveAs();
                } catch (IOException ex) {
                    Logger.getLogger(Jpowder.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                return;
            }
        }
}//GEN-LAST:event_imageMenuActionPerformed

    private void appletMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appletMenuActionPerformed
        if (plotsTab.getSelectedIndex() == 0) {
            if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() != 0) {
                JpowderPopupMenu jpowderPopupMenu = new JpowderPopupMenu(internalFrameInFocus2D.getChartPanel());
                jpowderPopupMenu.saveAsJpowderApplet();
            } else {
                return;
            }
        }
        if (plotsTab.getSelectedIndex() == 1) {
            if (JpowderInternalframe3D.getnumberOfJpowderInternalframe() != 0) {
                JpowderPopupMenu jpowderPopupMenu = new JpowderPopupMenu(internalFrameInFocus3D.getChartPanel());
                jpowderPopupMenu.saveAsJpowderApplet();
            } else {
                return;
            }
        }
}//GEN-LAST:event_appletMenuActionPerformed

    private void oPenMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oPenMenuActionPerformed

        Vector<DataSet> datasets = new Vector<DataSet>();
        HashMap<String, File> hashMap = new HashMap<String, File>();
        String fileName;
        File file;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);

        FileFilter filter2 = new FileFilter() {

            @Override
            public boolean accept(File f) {
                String fileName = f.getName();
                if (fileName.endsWith(".cif")) {
                    return true;
                }

                return false;

            }

            @Override
            public String getDescription() {
                return "File (*.cif)";
            }
        };
        FileFilter filter3 = new FileFilter() {

            @Override
            public boolean accept(File f) {
                String fileName = f.getName();
                if (fileName.endsWith(".xye")) {
                    return true;
                }

                return false;

            }

            @Override
            public String getDescription() {
                return "File (*.xye)";
            }
        };
        FileFilter filter4 = new FileFilter() {

            @Override
            public boolean accept(File f) {
                String fileName = f.getName();
                if (fileName.endsWith(".xy")) {
                    return true;
                }

                return false;

            }

            @Override
            public String getDescription() {
                return "File (*.xy)";
            }
        };
        FileFilter filter5 = new FileFilter() {

            @Override
            public boolean accept(File f) {
                String fileName = f.getName();
                if (fileName.endsWith(".gss")) {
                    return true;
                }

                return false;

            }

            @Override
            public String getDescription() {
                return "File (*.gss)";
            }
        };
        FileFilter filter = new FileFilter() {

            @Override
            public boolean accept(File f) {
                String fileName = f.getName();
                if (fileName.endsWith(".cif")) {
                    return true;
                }
                if (fileName.endsWith(".xye")) {
                    return true;
                }
                if (fileName.endsWith(".xy")) {
                    return true;
                }
                if (fileName.endsWith(".gss")) {
                    return true;
                }

                return false;

            }

            @Override
            public String getDescription() {
                return "File (*.xy, *.xye,*.cif,*.gss)";
            }
        };

        fileChooser.addChoosableFileFilter(filter2);
        fileChooser.addChoosableFileFilter(filter3);
        fileChooser.addChoosableFileFilter(filter4);
        fileChooser.addChoosableFileFilter(filter5);
        fileChooser.addChoosableFileFilter(filter);


        //DataSet oneDataset = null;

        // Set the accepted powder diffraction file extensions
        // and open a file chooser window for the user to select powder
        // diffraction file

        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            // get the selected files
            File selectedFiles[] = fileChooser.getSelectedFiles();

            // loop over the selected file
            for (int i = 0, n = selectedFiles.length; i < n; i++) {
                file = selectedFiles[i];
                fileName = selectedFiles[i].getName();
                hashMap.put(fileName, file);
            }//for
            for (Map.Entry<String, File> entry : hashMap.entrySet()) {
                fileName = entry.getKey();
                file = entry.getValue();
                if (mPowderFileCabinet.checkAcceptedFileType(fileName)) {


                    Vector<DataSet> allDatasets = PowderFileCabinet.createDataSetFromPowderFile(file);
                    if (allDatasets == null) {
                        return;
                    }
                    for (int iSet = 0; iSet < allDatasets.size(); iSet++) {
                        DataSet oneDataset = allDatasets.elementAt(iSet);
                        if (oneDataset != null) {
                            datasets.add(oneDataset);
                        }
                    }
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, "Only ASCII file please.");
                    break;
                }

            }
            // finally plot the data
            JpowderInternalframe2D internalframe;
            JpowderInternalframe3D internalframe3D;

            if (plotsTab.getSelectedIndex() == 1) {

                Import3DFilesTable filesTable = new Import3DFilesTable(dataVisibleInChart);
                  filesTable.addFilesToTable(selectedFiles);
                  filesTable.setVisible(true);


//                internalframe3D = new JpowderInternalframe3D(dataVisibleInChart, datasets);
//                chartPlotterPane3D.add(internalframe3D);
//
//                Jpowder.jpowderInternalFrameUpdate(internalframe3D);
//                InternalFrameListener internalFrameListener = new InternalFrameIconifyListener(dataVisibleInChart);
//                internalframe3D.addInternalFrameListener(internalFrameListener);
            }
            if (plotsTab.getSelectedIndex() == 0) {
                internalframe = new JpowderInternalframe2D(dataVisibleInChart, datasets);
                chartPlotterPane2D.add(internalframe);
                Jpowder.jpowderInternalFrameUpdate(internalframe);
                InternalFrameListener internalFrameListener = new InternalFrameIconifyListener(dataVisibleInChart);
                internalframe.addInternalFrameListener(internalFrameListener);
            }




            setVisible(true);
            memoryChecker();
            displayingMessageLabel();

        }//if open approved

    }//GEN-LAST:event_oPenMenuActionPerformed

    private void pdfForPublicationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdfForPublicationActionPerformed
        if (plotsTab.getSelectedIndex() == 0) {
            if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() != 0) {
                JpowderPopupMenu jpowderPopupMenu = new JpowderPopupMenu(internalFrameInFocus2D.getChartPanel());

                internalFrameInFocus2D.getXYPlot().setBackgroundPaint(ChartColor.white);
                internalFrameInFocus2D.getXYPlot().setOutlinePaint(ChartColor.white);
                jpowderPopupMenu.pDF();
                internalFrameInFocus2D.getXYPlot().setBackgroundPaint(ChartColor.LIGHT_GRAY);
                internalFrameInFocus2D.getXYPlot().setOutlinePaint(ChartColor.LIGHT_GRAY);
            } else {
                return;
            }
        }
        if (plotsTab.getSelectedIndex() == 1) {
            if (JpowderInternalframe3D.getnumberOfJpowderInternalframe() != 0) {
                JpowderPopupMenu jpowderPopupMenu = new JpowderPopupMenu(internalFrameInFocus3D.getChartPanel());

                internalFrameInFocus3D.getXYPlot().setBackgroundPaint(ChartColor.white);
                internalFrameInFocus3D.getXYPlot().setOutlinePaint(ChartColor.white);
                jpowderPopupMenu.pDF();
                internalFrameInFocus3D.getXYPlot().setBackgroundPaint(ChartColor.LIGHT_GRAY);
                internalFrameInFocus3D.getXYPlot().setOutlinePaint(ChartColor.LIGHT_GRAY);
            } else {
                return;
            }
        }

    }//GEN-LAST:event_pdfForPublicationActionPerformed

    private void importTable3DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importTable3DActionPerformed
        new Import3DFilesTable(dataVisibleInChart).setVisible(true);
        plotsTab.setSelectedComponent(chartPlotterPane3D);
    }//GEN-LAST:event_importTable3DActionPerformed

    private void saveWorksPaceMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveWorksPaceMenuActionPerformed
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        JInternalFrame jInternalFrames[] = chartPlotterPane2D.getAllFrames(); // get all open frames
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showSaveDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {


            try {

                String filename = chooser.getSelectedFile().getPath();
                FileOutputStream buffer = new FileOutputStream(filename);
                ObjectOutput out = new ObjectOutputStream(buffer);
                out.writeObject(jInternalFrames[0]);

            } catch (IOException e) {
                System.out.println(e);
            }
        }



    }//GEN-LAST:event_saveWorksPaceMenuActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized

        int w = (int) evt.getComponent().getSize().getWidth();
        messageLabel2D.setFont(new java.awt.Font("Arial", 0, (w / 36)));
        messageLabel3D.setFont(new java.awt.Font("Arial", 0, (w / 36)));
    }//GEN-LAST:event_formComponentResized

    private void chartPlotterPane2DComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_chartPlotterPane2DComponentRemoved

        if (chartPlotterPane2D.getAllFrames().length == 0) {

            chartPlotterPane2D.add(messageLabel2D);
            chartPlotterPane2D.repaint();

        }
}//GEN-LAST:event_chartPlotterPane2DComponentRemoved

    private void chartPlotterPane2DComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_chartPlotterPane2DComponentAdded
        displayingMessageLabel();
}//GEN-LAST:event_chartPlotterPane2DComponentAdded

    private void chartPlotterPane2DComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_chartPlotterPane2DComponentResized
        int w = (int) evt.getComponent().getSize().getWidth();
        int h = (int) evt.getComponent().getSize().getHeight();
        messageLabel2D.setLocation(w / 4, h / 2);
    }//GEN-LAST:event_chartPlotterPane2DComponentResized

    private void plotsTabStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_plotsTabStateChanged
        if (plotsTab.getSelectedIndex() == 1) {

            tabs.remove(toolstab2D);
            tabs.add(" Tools 3D ", toolstab3D);
            Jpowder.jpowderPlotAreaUpdate("3D");
        }
        if (plotsTab.getSelectedIndex() == 0) {

            tabs.add(" Tools 2D ", toolstab2D);
            tabs.remove(toolstab3D);
            Jpowder.jpowderPlotAreaUpdate("2D");
        }

    }//GEN-LAST:event_plotsTabStateChanged

    private void chartPlotterPane3DComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_chartPlotterPane3DComponentResized
        int w = (int) evt.getComponent().getSize().getWidth();
        int h = (int) evt.getComponent().getSize().getHeight();
        messageLabel3D.setLocation(w / 4, h / 2);
    }//GEN-LAST:event_chartPlotterPane3DComponentResized

    private void chartPlotterPane3DComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_chartPlotterPane3DComponentAdded
        displayingMessageLabel();
    }//GEN-LAST:event_chartPlotterPane3DComponentAdded

    private void chartPlotterPane3DComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_chartPlotterPane3DComponentRemoved
        if (chartPlotterPane3D.getAllFrames().length == 0) {

            chartPlotterPane3D.add(messageLabel3D);
            chartPlotterPane3D.repaint();

        }
    }//GEN-LAST:event_chartPlotterPane3DComponentRemoved

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
    }

    /**
     *
     * @param dte
     */
    @Override
    public void dragExit(DropTargetEvent dte) {
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {


        double x = dtde.getLocation().getX();
        double y = dtde.getLocation().getY();
        dropLocationX = x;
        dropLocationY = y;



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
                    Logger.getLogger(Jpowder.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Jpowder.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (flavors[i].equals(DataFlavor.stringFlavor)) {

                try {
                    // populate allFilenames
                    String fileName;
                    fileName = (String) transfeable.getTransferData(DataFlavor.stringFlavor);
                    String[] array = fileName.split("\n");
                    int n = array.length;
                    for (int j = 0; j < n; j++) {

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
//            if (mPowderFileCabinet.checkAcceptedFileType(fileName)) {


            Vector<DataSet> allDatasets = PowderFileCabinet.createDataSetFromPowderFile(file);
            if (allDatasets == null) {
                return;
            }
            for (int iSet = 0; iSet < allDatasets.size(); iSet++) {
                if (allDatasets.elementAt(iSet) != null) {
                    datasets.add(allDatasets.elementAt(iSet));
                } else {
                    return;
                }
            }
//            } else {
//                //return so no chart object is created and Expception is not thrown.
//                javax.swing.JOptionPane.showMessageDialog(null, "Only ASCII file please.");
//                return;
//            }
        }

        // finally plot the data
        JpowderInternalframe2D internalframe = new JpowderInternalframe2D(dataVisibleInChart, datasets);
//        Jpowder.jpowderInternalFrameUpdate(internalframe);

        InternalFrameListener internalFrameListener = new InternalFrameIconifyListener(dataVisibleInChart);

        internalframe.addInternalFrameListener(internalFrameListener);

        getChartPlotter2D().add(internalframe);
        setVisible(true);
        displayingMessageLabel();
        memoryChecker();
    }

    /**
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        if (OSValidator.windows()) {
            LookAndFeel.windows();
        }
        if (OSValidator.mac()) {
            LookAndFeel.appleLook();
        }

        if (OSValidator.linux()) {
            LookAndFeel.LinuxandSolaris();
        } else {
            LookAndFeel.java();
        }


        Jpowder jpowder = new Jpowder();
        jpowder.setLocationRelativeTo(null);
        jpowder.setVisible(true);


    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar JpowderMenuBar;
    private javax.swing.JMenuItem aboutMenu;
    private javax.swing.JMenuItem appletMenu;
    private javax.swing.JMenuItem basicPrintMenu;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBoxMenuItem cascadeCheckBoxMenuItem;
    private static javax.swing.JDesktopPane chartPlotterPane2D;
    private static javax.swing.JDesktopPane chartPlotterPane3D;
    private javax.swing.JMenuItem closeFrameMenu;
    private javax.swing.JMenuItem clossAllMenu;
    private javax.swing.JMenuItem copyMenu;
    private javax.swing.JPanel dataVisibleInChartPanel;
    private javax.swing.JCheckBoxMenuItem defaultCheckBoxMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenu;
    private javax.swing.JPanel explorertab;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JPanel homePanel;
    private javax.swing.JScrollPane homePanelScrollPane;
    private javax.swing.JMenuItem imageMenu;
    private javax.swing.JMenuItem importTable3D;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private static javax.swing.JLabel messageLabel2D;
    private static javax.swing.JLabel messageLabel3D;
    private javax.swing.JMenuItem oPenMenu;
    private javax.swing.JMenuItem onlineDocsAndSupportMenu;
    private javax.swing.JMenuItem pDfMenu;
    private javax.swing.JMenuItem pdfForPublication;
    private static javax.swing.JTabbedPane plotsTab;
    private javax.swing.JMenu printMenu;
    private javax.swing.JMenuItem printPublishingMenu;
    private javax.swing.JMenuItem propertiesMenu;
    private javax.swing.JMenuItem redoMenu;
    private javax.swing.JMenu saveAsMenu;
    private javax.swing.JMenuItem saveWorksPaceMenu;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JCheckBoxMenuItem tileCheckBoxMenuItem;
    private javax.swing.JCheckBoxMenuItem tileHorizontallyCheckBoxMenuItem;
    private javax.swing.JCheckBoxMenuItem tileVerticallyCheckBoxMenuItem;
    private javax.swing.JPanel toolstab2D;
    private javax.swing.JPanel toolstab3D;
    private javax.swing.JMenuItem undoMenu;
    private javax.swing.JMenu windowMenu;
    // End of variables declaration//GEN-END:variables
}
