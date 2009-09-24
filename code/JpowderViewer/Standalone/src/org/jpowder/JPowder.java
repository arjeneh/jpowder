package org.jpowder;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.dnd.DropTargetDropEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jpowder.dataset.DatasetPlotter;
import org.jpowder.util.ScreenUtil;
import org.jpowder.util.Stopwatch;
import org.jpowder.dataset.DataSet;
import org.jpowder.fileCabinet.PowderFileCabinet;
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
 */
public class JPowder extends javax.swing.JApplet implements org.jpowder.fileCabinet.PowderFileObserver {

    // Top right hand panel. Panel where files selected.
    // Also this file panel holds an instance of PowderFileCabinet
    // where e.g. this instance gets registered as an observer
    private FileChooserPanel fileChooserPanel = new FileChooserPanel(this);

    // Some hard coded custom dimensions GUI dimensions
    // What are they exactly?
    private static final int CHART_HEIGHT_FIX_SIZE =270;
    private static final int FRAME_WIDTH = 10700;
    private static final int FRAME_HEIGHT = 6700;
    private boolean InBrowser = true;

    private PowderFileCabinet mPowderFileCabinet;
    
  
    public void powderFileCabinetUpdate(org.jpowder.fileCabinet.Subject data) {
        org.jpowder.fileCabinet.PowderFileCabinet pfc = (org.jpowder.fileCabinet.PowderFileCabinet) data;


        // comment: update to a bigger size by getting the current size and add some amount.
        java.awt.Dimension area = powderChartPanel.getSize();
        area.height = area.height+( CHART_HEIGHT_FIX_SIZE);
        powderChartPanel.setLayout(new javax.swing.BoxLayout(powderChartPanel, javax.swing.BoxLayout.Y_AXIS));
        powderChartPanel.setPreferredSize(area);

        HashMap<String, DataSet> allData = pfc.getData();

        // Get new dataset
        String fileName = pfc.getLastUpdateFileName();
        DataSet lastAddedDataset = allData.get(fileName);

         Stopwatch lStopwatch = new Stopwatch();
         lStopwatch.start();
        DatasetPlotter plot = DatasetPlotter.createDatasetPlotter(lastAddedDataset);
        powderChartPanel.add(plot.createPowderChart());
        System.out.println("\nTime it took to create chart " + fileName);
                System.out.println(lStopwatch.getElapsedTime());
                lStopwatch.reset();
        // add seperator
        JPanel seperatePanel = new JPanel();
        //seperatePanel.setBackground(new java.awt.Color(240, 240, 240));
        seperatePanel.setMinimumSize(new Dimension(550, 4));
        seperatePanel.setPreferredSize(new Dimension(550, 4));
        seperatePanel.setMaximumSize(new Dimension(550, 4));
        powderChartPanel.add(seperatePanel);
        powderChartPanel.revalidate();

        java.awt.Rectangle rect = powderChartPanel.getBounds();
        chart_scrp.getVerticalScrollBar().setValue(rect.height);
    }// powderFileCabinetUpdate

    /**
     * JVM starting point
     *
     * @param args
     */
    public static void main(String[] args) {

        JPowder applet = new JPowder();
        applet.InBrowser = false;

        // Note that init () invoked before adding to frame.
        // So don't use width/height info in init () since those parameters not yet set.
        applet.init();

        // Following anonymous class used to close window & exit program
        javax.swing.JFrame f = new javax.swing.JFrame("JPowder Crystallograhy Demo");
        f.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);

        // Add applet to the frame
        f.getContentPane().add(applet);
       // f.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        f.pack();
        
        //calling Screen.java to center the frame on user screen.
       ScreenUtil.centerFrame(f);
       f.setVisible(true);
    } //main

    
    /**
     * Get hold of the JPanel were the powder diffraction data are plotted in charts
     *
     * @return The JPanel where the data are plotted
     */
    public JPanel getChartPanel() {
        return this.powderChartPanel;
    }

    /**
     * Initialise the GUI
     */
    @Override
    public void init() {
      mPowderFileCabinet = new PowderFileCabinet();
      mPowderFileCabinet.registerObserver(this);
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, e.getMessage());
        }

        initComponents();
    }//end init

    /** This method is called from within the init() method to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    java.awt.GridBagConstraints gridBagConstraints;

    cardPanel = new javax.swing.JPanel();

    homePanel = new javax.swing.JPanel();
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    homePanel.add(fileChooserPanel, gridBagConstraints);
    statPanel = new javax.swing.JPanel();
    statBluePanel = new javax.swing.JPanel();
    stats_sp = new javax.swing.JScrollPane();
    stats_ta = new javax.swing.JTextArea();
    print_btn = new javax.swing.JButton();
    saveStat_btn = new javax.swing.JButton();
    chart_scrp = new javax.swing.JScrollPane();
    powderChartPanel = new javax.swing.JPanel();
    jMenuBar1 = new javax.swing.JMenuBar();
    fileMenu = new javax.swing.JMenu();
    New = new javax.swing.JMenuItem();
    openFileMenuItem = new javax.swing.JMenuItem();
    saveFileMenuItem = new javax.swing.JMenuItem();
    Exit = new javax.swing.JMenuItem();
    editMenu = new javax.swing.JMenu();
    Edit = new javax.swing.JMenuItem();
    Delete = new javax.swing.JMenuItem();
    helpMenu = new javax.swing.JMenu();
    Content = new javax.swing.JMenuItem();
    Docs = new javax.swing.JMenuItem();
    About = new javax.swing.JMenuItem();

    setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
    setForeground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
    getContentPane().setLayout(new java.awt.GridBagLayout());

    cardPanel.setLayout(new java.awt.CardLayout());

    homePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Home", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N
    homePanel.setMinimumSize(new java.awt.Dimension(1010, 530));
    homePanel.setPreferredSize(new java.awt.Dimension(1010, 530));
    homePanel.setLayout(new java.awt.GridBagLayout());

    statPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Statistics Panel", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102))); // NOI18N
    statPanel.setMaximumSize(new java.awt.Dimension(270, 32767));
    statPanel.setMinimumSize(new java.awt.Dimension(265, 180));
    statPanel.setPreferredSize(new java.awt.Dimension(265, 180));
    statPanel.setLayout(new java.awt.GridBagLayout());

    statBluePanel.setMaximumSize(new java.awt.Dimension(260, 2147483647));
    statBluePanel.setMinimumSize(new java.awt.Dimension(260, 158));
    statBluePanel.setPreferredSize(new java.awt.Dimension(260, 158));
    statBluePanel.setLayout(new java.awt.GridBagLayout());

    stats_sp.setMinimumSize(new java.awt.Dimension(260, 115));
    stats_sp.setPreferredSize(new java.awt.Dimension(260, 115));

    stats_ta.setColumns(20);
    stats_ta.setRows(5);
    stats_ta.setMinimumSize(new java.awt.Dimension(1400, 1000));
    stats_ta.setPreferredSize(new java.awt.Dimension(1400, 1000));
    stats_sp.setViewportView(stats_ta);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
    statBluePanel.add(stats_sp, gridBagConstraints);

    print_btn.setFont(new java.awt.Font("Tahoma", 0, 10));
    print_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer.gif"))); // NOI18N
    print_btn.setLabel("Print stats");
    print_btn.setMaximumSize(new java.awt.Dimension(100, 23));
    print_btn.setMinimumSize(new java.awt.Dimension(100, 23));
    print_btn.setPreferredSize(new java.awt.Dimension(120, 23));
    print_btn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        print_btnActionPerformed(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
    statBluePanel.add(print_btn, gridBagConstraints);

    saveStat_btn.setFont(new java.awt.Font("Tahoma", 0, 10));
    saveStat_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/savas_small.gif"))); // NOI18N
    saveStat_btn.setLabel("Save stats");
    saveStat_btn.setPreferredSize(new java.awt.Dimension(100, 25));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
    statBluePanel.add(saveStat_btn, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    statPanel.add(statBluePanel, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
    homePanel.add(statPanel, gridBagConstraints);

    chart_scrp.setBorder(null);
    chart_scrp.setAutoscrolls(true);
    chart_scrp.setMinimumSize(new java.awt.Dimension(630, 480));
    chart_scrp.setPreferredSize(new java.awt.Dimension(630, 480));

    powderChartPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chart", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102))); // NOI18N
    powderChartPanel.setAutoscrolls(true);
    powderChartPanel.setMaximumSize(new java.awt.Dimension(520, 32767));
    powderChartPanel.setMinimumSize(new java.awt.Dimension(630, 540));
    powderChartPanel.setPreferredSize(new java.awt.Dimension(630, 400));
    powderChartPanel.setLayout(new javax.swing.BoxLayout(powderChartPanel, javax.swing.BoxLayout.Y_AXIS));
    chart_scrp.setViewportView(powderChartPanel);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
    homePanel.add(chart_scrp, gridBagConstraints);

    cardPanel.add(homePanel, "card5");

    getContentPane().add(cardPanel, new java.awt.GridBagConstraints());

    fileMenu.setText("File");

    New.setText("New...");
    New.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        NewActionPerformed(evt);
      }
    });
    fileMenu.add(New);

    openFileMenuItem.setText("Open");
    openFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        openFileMenuItemActionPerformed(evt);
      }
    });
    fileMenu.add(openFileMenuItem);

    saveFileMenuItem.setText("Save");
    saveFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        saveFileMenuItemActionPerformed(evt);
      }
    });
    fileMenu.add(saveFileMenuItem);

    Exit.setText("Exit");
    Exit.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        ExitActionPerformed(evt);
      }
    });
    fileMenu.add(Exit);

    jMenuBar1.add(fileMenu);

    editMenu.setText("Edit Chart");

    Edit.setText("Edit");
    editMenu.add(Edit);

    Delete.setText("Delete");
    editMenu.add(Delete);

    jMenuBar1.add(editMenu);

    helpMenu.setText("Help");

    Content.setText("Content");
    helpMenu.add(Content);

    Docs.setText("Online Docs and Support");
    Docs.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        DocsActionPerformed(evt);
      }
    });
    helpMenu.add(Docs);

    About.setText("About");
    About.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        AboutActionPerformed(evt);
      }
    });
    helpMenu.add(About);

    jMenuBar1.add(helpMenu);

    setJMenuBar(jMenuBar1);
  }// </editor-fold>//GEN-END:initComponents

    private void openFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileMenuItemActionPerformed
     
   mPowderFileCabinet.loadFiles();


}//GEN-LAST:event_openFileMenuItemActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        int res = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "JPowder",
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

    private void AboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutActionPerformed
        new about().setVisible(true);       
    }//GEN-LAST:event_AboutActionPerformed

    private void DocsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DocsActionPerformed

        Runtime rt = Runtime.getRuntime();
        String[] callAndArgs = {"explorer.exe",
            "http://code.google.com/p/jpowder"};
        try {

            Process child = rt.exec(callAndArgs);
        } catch (IOException e) {
        }

    }//GEN-LAST:event_DocsActionPerformed

    private void print_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_print_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_print_btnActionPerformed

    private void saveFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveFileMenuItemActionPerformed

      boolean[] JPowder = new boolean[16654];
      for (int i=0; i<16654;i++){
      }
      
    JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                ".ISIS ", "ISIS");

        chooser.setFileFilter(filter);

        int returnVal = chooser.showSaveDialog(chooser);
        File fileName = chooser.getSelectedFile();
        if (returnVal == JFileChooser.APPROVE_OPTION) 
          try {
            FileOutputStream buffer = new FileOutputStream(fileName);
            final ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(JPowder);


          } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Invalid file!",
                    "error", JOptionPane.ERROR_MESSAGE);
          }
         
      
     

    }//GEN-LAST:event_saveFileMenuItemActionPerformed

    private void NewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewActionPerformed
      JPowder applet = new JPowder();
      applet.init();
      JFrame f = new JFrame("JPowder Crystallograhy Demo");
      f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      f.getContentPane().add(applet);
      f.pack();
      f.setLocation(100, 100);
      f.setVisible(true);
}//GEN-LAST:event_NewActionPerformed
    // When user click they can see the graph from what ever data in the table.    //end plotFile_btnActionPerformed
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JMenuItem About;
  private javax.swing.JMenuItem Content;
  private javax.swing.JMenuItem Delete;
  private javax.swing.JMenuItem Docs;
  private javax.swing.JMenuItem Edit;
  private javax.swing.JMenuItem Exit;
  private javax.swing.JMenuItem New;
  private javax.swing.JPanel cardPanel;
  private javax.swing.JScrollPane chart_scrp;
  private javax.swing.JMenu editMenu;
  private javax.swing.JMenu fileMenu;
  private javax.swing.JMenu helpMenu;
  private javax.swing.JPanel homePanel;
  private javax.swing.JMenuBar jMenuBar1;
  private javax.swing.JMenuItem openFileMenuItem;
  private javax.swing.JPanel powderChartPanel;
  private javax.swing.JButton print_btn;
  private javax.swing.JMenuItem saveFileMenuItem;
  private javax.swing.JButton saveStat_btn;
  private javax.swing.JPanel statBluePanel;
  private javax.swing.JPanel statPanel;
  private javax.swing.JScrollPane stats_sp;
  private javax.swing.JTextArea stats_ta;
  // End of variables declaration//GEN-END:variables
}//end
