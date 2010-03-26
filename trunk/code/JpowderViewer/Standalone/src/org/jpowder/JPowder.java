package org.jpowder;


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
import org.jpowder.Analysis.ToolsIcon;
import org.jpowder.dataset.DataSet;
import org.jpowder.fileCabinet.PowderFileCabinet;
import org.jpowder.util.ScreenUtil;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.*;
import org.jfree.chart.ChartColor;
import org.jpowder.fileCabinet.AcceptFileFilter;
import org.jpowder.jfreechart.JpowderPopupMenu;
import org.jpowder.tree.JpowderFileSystemTreeModel;

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
 * @author Kreecha Puphaiboon (KP)
 * @since 07
 *
 *  * Changes
 * -------
 * 22-August-2007: Version 1 (KP);
 * 04-March-2010 : Add JTree and its model (KP).
 *
 */
public class JPowder extends JFrame implements DropTargetListener {

    private org.jfree.chart.JFreeChart chart;
    private JpowderFileSystemTreeModel treeModel = new JpowderFileSystemTreeModel();
    private Tree tree = new Tree(treeModel);
    public DataVisibleInChart dataVisibleInChart = new DataVisibleInChart();
    private PowderFileCabinet mPowderFileCabinet;
    private java.awt.dnd.DropTarget dropTarget;
    private ToolsIcon analysisIcon = new ToolsIcon(this);
    private CardLayout cardLayout;
    public static JpowderInternalframe internalFrameInFocus;
    private JPowderDesktopManager jPowderDesktopManager = new JPowderDesktopManager();
    public static InfoPanel infoPanelInfocus;
    public static JPowderStack jPowderStackUndo = new JPowderStack(5);
    public static JPowderStack jPowderStackRedo = new JPowderStack(5);
    private static double dropLocationX, dropLocationY;

    //  private stackInternalFrames;
    /**
     * JVM starting point
     *
     * @param args
     */
    public JPowder() {
        initComponents();

        mPowderFileCabinet = new PowderFileCabinet();

        dropTarget = new DropTarget(chartPlotterPane, this);
        dataVisibleInChartPanel.add(dataVisibleInChart);
        explorertab.add(tree, "1");
        toolstab.add(analysisIcon, "1");
        messageLabel.setLocation(chartPlotterPane.getWidth() / 3, chartPlotterPane.getHeight() / 2);

        //to keep newly added JInternalFrame inside the JDesktopPane (KP)
        chartPlotterPane.addContainerListener(new FrameAddedSupervisor());

        ScreenUtil.adjustBounds(this);
    }

    /**
     *
     * @return chartPlotter
     */
    public JDesktopPane getChartPlotter() {
        return chartPlotterPane;
    }

    /**
     *
     * @return analysistab.
     */
    public JPanel getanalysistab() {
        return toolstab;
    }

    /**
     *
     * @return
     */
    public CardLayout getCardLayout() {
        return cardLayout;
    }

    /**
     * this method return the x location of whereever the file is droped.
     * @return
     */
    public static double getDropLocationX() {
        return dropLocationX;
    }

    /**
     * this method return the y location of the where the file is droped.
     * @return
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

    public void displayingMessageLabel() {


        if (JpowderInternalframe.getnumberOfJpowderInternalframe() == 1) {
            chartPlotterPane.remove(messageLabel);
            chartPlotterPane.repaint();
        }
        if (JpowderInternalframe.getnumberOfJpowderInternalframe() == 0) {
            chartPlotterPane.add(messageLabel);
            chartPlotterPane.repaint();
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
        homePanel = new javax.swing.JPanel();
        tabs = new javax.swing.JTabbedPane();
        explorertab = new javax.swing.JPanel();
        cardLayout = new CardLayout();
        toolstab = new javax.swing.JPanel();
        dataVisibleInChartPanel = new javax.swing.JPanel();
        chartPlotterPane = new javax.swing.JDesktopPane();
        messageLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newMenu = new javax.swing.JMenuItem();
        oPenMenu = new javax.swing.JMenuItem();
        saveAsMenu = new javax.swing.JMenu();
        appletMenu = new javax.swing.JMenuItem();
        imageMenu = new javax.swing.JMenuItem();
        pDfMenu = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        printMenu = new javax.swing.JMenu();
        jPrintMenu = new javax.swing.JMenuItem();
        jPrintPublishingMenu = new javax.swing.JMenuItem();
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
        windowMenu = new javax.swing.JMenu();
        tileMenu = new javax.swing.JMenuItem();
        tileHorizontallyMenu = new javax.swing.JMenuItem();
        tileVerticallyMenu = new javax.swing.JMenuItem();
        cascadeMenu = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        onlieDocsandSupportMenu = new javax.swing.JMenuItem();
        aboutMenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JPowder Crystallography Demo");
        setLocationByPlatform(true);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        homePanel.setPreferredSize(new java.awt.Dimension(320, 727));

        tabs.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tabs.setFont(new java.awt.Font("Kartika", 0, 18));
        tabs.setMaximumSize(new java.awt.Dimension(327, 32767));
        tabs.setPreferredSize(new java.awt.Dimension(275, 800));
        tabs.setVerifyInputWhenFocusTarget(false);

        explorertab.setPreferredSize(new java.awt.Dimension(275, 400));
        explorertab.setLayout(new java.awt.CardLayout());
        tabs.addTab("Explorer", explorertab);

        toolstab.setPreferredSize(new java.awt.Dimension(270, 320));
        toolstab.setLayout(new java.awt.CardLayout());

        toolstab.setLayout(cardLayout);

        tabs.addTab(" Tools ", toolstab);

        dataVisibleInChartPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Visible In Chart"));
        dataVisibleInChartPanel.setPreferredSize(new java.awt.Dimension(270, 260));
        dataVisibleInChartPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout homePanelLayout = new javax.swing.GroupLayout(homePanel);
        homePanel.setLayout(homePanelLayout);
        homePanelLayout.setHorizontalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homePanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(dataVisibleInChartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
        );
        homePanelLayout.setVerticalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homePanelLayout.createSequentialGroup()
                .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(dataVisibleInChartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane2.setViewportView(homePanel);

        chartPlotterPane.setBackground(new java.awt.Color(236, 233, 216));
        chartPlotterPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Plot Area"));
        chartPlotterPane.setForeground(new java.awt.Color(102, 102, 102));
        chartPlotterPane.setDesktopManager(jPowderDesktopManager);
        chartPlotterPane.setOpaque(false);

        messageLabel.setFont(new java.awt.Font("Arial", 0, 36));
        messageLabel.setForeground(new java.awt.Color(153, 153, 153));
        messageLabel.setText("Drag & Drop Files Here.");
        messageLabel.setEnabled(false);
        messageLabel.setBounds(240, 370, 400, 50);
        chartPlotterPane.add(messageLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jMenuBar1.setFont(new java.awt.Font("Tahoma", 0, 36));

        fileMenu.setText("File");
        fileMenu.setFont(new java.awt.Font("Tahoma", 0, 14));

        newMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/newdoc2_16x16.png"))); // NOI18N
        newMenu.setText("New...");
        newMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMenuActionPerformed(evt);
            }
        });
        fileMenu.add(newMenu);

        oPenMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        oPenMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Open1.png"))); // NOI18N
        oPenMenu.setText("Open");
        oPenMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oPenMenuActionPerformed(evt);
            }
        });
        fileMenu.add(oPenMenu);

        saveAsMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/saveas_16x16.png"))); // NOI18N
        saveAsMenu.setText("Save As");

        appletMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exportto_16x16.png"))); // NOI18N
        appletMenu.setText("Jpowder Applet ");
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

        fileMenu.add(saveAsMenu);
        fileMenu.add(jSeparator2);

        printMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Print.png"))); // NOI18N
        printMenu.setText("Print As");

        jPrintMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jPrintMenu.setText("Print");
        jPrintMenu.setToolTipText("Print ");
        jPrintMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPrintMenuActionPerformed(evt);
            }
        });
        printMenu.add(jPrintMenu);

        jPrintPublishingMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jPrintPublishingMenu.setText("Print For Publication");
        jPrintPublishingMenu.setToolTipText("Print with white background");
        jPrintPublishingMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPrintPublishingMenuActionPerformed(evt);
            }
        });
        printMenu.add(jPrintPublishingMenu);

        fileMenu.add(printMenu);
        fileMenu.add(jSeparator1);

        closeFrameMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Close.png"))); // NOI18N
        closeFrameMenu.setText("Close Window");
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

        jMenuBar1.add(fileMenu);

        editMenu.setText("Edit");
        editMenu.setFont(new java.awt.Font("Tahoma", 0, 14));

        undoMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        undoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/undo_26x26.png"))); // NOI18N
        undoMenu.setText("Undo Closed Window");
        undoMenu.setToolTipText("Undo closed frame");
        undoMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoMenuActionPerformed(evt);
            }
        });
        editMenu.add(undoMenu);

        redoMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        redoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/redo_26x26.png"))); // NOI18N
        redoMenu.setText("Redo Closed Window");
        redoMenu.setToolTipText("Redo closed frame");
        redoMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redoMenuActionPerformed(evt);
            }
        });
        editMenu.add(redoMenu);
        editMenu.add(jSeparator5);

        copyMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        copyMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/copy_16x16.png"))); // NOI18N
        copyMenu.setText("Copy");
        copyMenu.setToolTipText("Copy the selected frame");
        copyMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyMenuActionPerformed(evt);
            }
        });
        editMenu.add(copyMenu);
        editMenu.add(jSeparator6);

        propertiesMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        propertiesMenu.setText("Propeties");
        propertiesMenu.setToolTipText("set propeties of the selected frame");
        propertiesMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                propertiesMenuActionPerformed(evt);
            }
        });
        editMenu.add(propertiesMenu);

        jMenuBar1.add(editMenu);

        windowMenu.setText("Window");
        windowMenu.setFont(new java.awt.Font("Tahoma", 0, 14));

        tileMenu.setText("Tile");
        tileMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tileMenuActionPerformed(evt);
            }
        });
        windowMenu.add(tileMenu);

        tileHorizontallyMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/TileHorizontally.png"))); // NOI18N
        tileHorizontallyMenu.setText("Tile Windows Horizontally ");
        tileHorizontallyMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tileHorizontallyMenuActionPerformed(evt);
            }
        });
        windowMenu.add(tileHorizontallyMenu);

        tileVerticallyMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/TileVertically.png"))); // NOI18N
        tileVerticallyMenu.setText("Tile Windows Vertically");
        tileVerticallyMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tileVerticallyMenuActionPerformed(evt);
            }
        });
        windowMenu.add(tileVerticallyMenu);

        cascadeMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/TileCascade.png"))); // NOI18N
        cascadeMenu.setText("Cascade");
        cascadeMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cascadeMenuActionPerformed(evt);
            }
        });
        windowMenu.add(cascadeMenu);

        jMenuBar1.add(windowMenu);

        helpMenu.setText("Help");
        helpMenu.setFont(new java.awt.Font("Tahoma", 0, 14));

        onlieDocsandSupportMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, java.awt.event.InputEvent.CTRL_MASK));
        onlieDocsandSupportMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Web.png"))); // NOI18N
        onlieDocsandSupportMenu.setText("Online Docs and Support");
        onlieDocsandSupportMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onlieDocsandSupportMenuActionPerformed(evt);
            }
        });
        helpMenu.add(onlieDocsandSupportMenu);

        aboutMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        aboutMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/info_16x16.png"))); // NOI18N
        aboutMenu.setText("About");
        aboutMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenu);

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
                .addComponent(chartPlotterPane, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chartPlotterPane, javax.swing.GroupLayout.DEFAULT_SIZE, 843, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 843, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * open the browser to Jpowder web site for addition helps.
     * @param evt
     */
    private void onlieDocsandSupportMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onlieDocsandSupportMenuActionPerformed
        Runtime rt = Runtime.getRuntime();
        String[] callAndArgs = {"explorer.exe",
            "http://code.google.com/p/jpowder"};
        try {

            Process poress = rt.exec(callAndArgs);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "No Internet Conection!",
                    "error", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_onlieDocsandSupportMenuActionPerformed
    /**
     * call the about panel.
     * @param evt
     */
    private void aboutMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuActionPerformed
        new about().setVisible(true);
    }//GEN-LAST:event_aboutMenuActionPerformed
    /**
     * exit the JPowder.
     * @param evt
     */
    private void exitMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuActionPerformed
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
    }//GEN-LAST:event_exitMenuActionPerformed

    private void oPenMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oPenMenuActionPerformed

        Vector<DataSet> datasets = new Vector<DataSet>();
        HashMap<String, File> hashMap = new HashMap<String, File>();
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
                hashMap.put(fileName, file);
            }//for
            for (Map.Entry<String, File> entry : hashMap.entrySet()) {
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
            JpowderInternalframe internalframe = new JpowderInternalframe(dataVisibleInChart, datasets);
            JPowder.jpowderInternalFrameUpdate(internalframe);

            InternalFrameListener internalFrameListener = new InternalFrameIconifyListener(dataVisibleInChart);
            internalframe.addInternalFrameListener(internalFrameListener);
            chartPlotterPane.add(internalframe);
            setVisible(true);

        }//if open approved


    }//GEN-LAST:event_oPenMenuActionPerformed
    /**
     * Create an instace of the JPowder.
     * @param evt
     */
    private void newMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newMenuActionPerformed
        JPowder newJPowder = new JPowder();
        newJPowder.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ScreenUtil.centerFrame(newJPowder);
        newJPowder.setVisible(true);

    }//GEN-LAST:event_newMenuActionPerformed

    private void appletMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appletMenuActionPerformed
      JpowderPopupMenu.saveAsJpowderApplet();
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
        JpowderPopupMenu.pDF();
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

    private void propertiesMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_propertiesMenuActionPerformed


        if (JpowderInternalframe.getnumberOfJpowderInternalframe() != 0) {
            internalFrameInFocus.getChartPanel().doEditChartProperties();
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "There is Plotted data");
        }
    }//GEN-LAST:event_propertiesMenuActionPerformed

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
        internalFrameInFocus.getXYPlot().setOutlinePaint(ChartColor.white);
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
        internalFrameInFocus.getXYPlot().setOutlinePaint(ChartColor.LIGHT_GRAY);
    }//GEN-LAST:event_jPrintPublishingMenuActionPerformed
    /**
     * To close all the internal frames which are in the ChartPlotter(jDesktopPane).
     * @param evt
     */
    private void clossAllMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clossAllMenuActionPerformed

        JInternalFrame jInternalFrames[] = chartPlotterPane.getAllFrames(); // get all open frames
        for (int i = 0; i < jInternalFrames.length; i++) {
            try {
                jInternalFrames[i].setClosed(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(JPowder.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_clossAllMenuActionPerformed

    private void tileHorizontallyMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tileHorizontallyMenuActionPerformed

        JInternalFrame jInternalFrames[] = chartPlotterPane.getAllFrames(); // get all open frames
        chartPlotterPane.setLayout(new GridLayout(jInternalFrames.length, 1, 0, 0));
        chartPlotterPane.updateUI();


    }//GEN-LAST:event_tileHorizontallyMenuActionPerformed

    private void tileVerticallyMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tileVerticallyMenuActionPerformed
        JInternalFrame jInternalFrames[] = chartPlotterPane.getAllFrames(); // get all open frames
        chartPlotterPane.setLayout(new GridLayout(1, jInternalFrames.length, 0, 0));
        chartPlotterPane.updateUI();
    }//GEN-LAST:event_tileVerticallyMenuActionPerformed

    private void cascadeMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cascadeMenuActionPerformed


        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                JInternalFrame jInternalFrames[] = chartPlotterPane.getAllFrames(); // get all open frames
                int top = 0;
                int left = 0;
                for (int i = 0; i < jInternalFrames.length; i++) {
                    chartPlotterPane.setLayout(null);
                    internalFrameInFocus.select();
                    jInternalFrames[i].setSize(internalFrameInFocus.getSize());
                    jInternalFrames[i].setLocation(top, left);
                    top += 30;
                    left += 30;
                    chartPlotterPane.updateUI();
                    internalFrameInFocus.updateUI();
                }
            }
        });



    }//GEN-LAST:event_cascadeMenuActionPerformed

    private void tileMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tileMenuActionPerformed
        JInternalFrame jInternalFrames[] = chartPlotterPane.getAllFrames(); // get all open frames
        chartPlotterPane.setLayout(new GridLayout(jInternalFrames.length, jInternalFrames.length, 20, 20));
        chartPlotterPane.updateUI();
    }//GEN-LAST:event_tileMenuActionPerformed

    private void closeFrameMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeFrameMenuActionPerformed
        try {
            internalFrameInFocus.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(JPowder.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_closeFrameMenuActionPerformed

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
                //return so no chart object is created and Expception is not thrown.
                javax.swing.JOptionPane.showMessageDialog(null, "Only ASCII file please.");
                return;
            }
        }

        // finally plot the data
        JpowderInternalframe internalframe = new JpowderInternalframe(dataVisibleInChart, datasets);
        JPowder.jpowderInternalFrameUpdate(internalframe);

        InternalFrameListener internalFrameListener = new InternalFrameIconifyListener(dataVisibleInChart);
        internalframe.addInternalFrameListener(internalFrameListener);

        chartPlotterPane.add(internalframe);
        setVisible(true);
        displayingMessageLabel();
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

        java.awt.EventQueue.invokeLater(new Runnable() {

            JPowder jpowder = new JPowder();

            public void run() {
                jpowder.setLocationRelativeTo(null);
                jpowder.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenu;
    private javax.swing.JMenuItem appletMenu;
    private javax.swing.JMenuItem cascadeMenu;
    private javax.swing.JDesktopPane chartPlotterPane;
    private javax.swing.JMenuItem closeFrameMenu;
    private javax.swing.JMenuItem clossAllMenu;
    private javax.swing.JMenuItem copyMenu;
    private javax.swing.JPanel dataVisibleInChartPanel;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenu;
    private javax.swing.JPanel explorertab;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JPanel homePanel;
    private javax.swing.JMenuItem imageMenu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jPrintMenu;
    private javax.swing.JMenuItem jPrintPublishingMenu;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JMenuItem newMenu;
    private javax.swing.JMenuItem oPenMenu;
    private javax.swing.JMenuItem onlieDocsandSupportMenu;
    private javax.swing.JMenuItem pDfMenu;
    private javax.swing.JMenu printMenu;
    private javax.swing.JMenuItem propertiesMenu;
    private javax.swing.JMenuItem redoMenu;
    private javax.swing.JMenu saveAsMenu;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JMenuItem tileHorizontallyMenu;
    private javax.swing.JMenuItem tileMenu;
    private javax.swing.JMenuItem tileVerticallyMenu;
    private javax.swing.JPanel toolstab;
    private javax.swing.JMenuItem undoMenu;
    private javax.swing.JMenu windowMenu;
    // End of variables declaration//GEN-END:variables
}
