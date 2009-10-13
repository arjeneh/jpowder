/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JPowderFinalGui.java
 *
 * Created on 05-Oct-2009, 09:57:11
 */
package org.jpowder;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jpowder.dataset.DataSet;
import org.jpowder.dataset.DatasetPlotter;
import org.jpowder.fileCabinet.PowderFileCabinet;
import org.jpowder.util.ScreenUtil;
import org.jpowder.util.Stopwatch;

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
public class JPowderFinalGui extends javax.swing.JFrame implements org.jpowder.fileCabinet.PowderFileObserver {

    private FileChooserPanel fileChooserPanel = new FileChooserPanel(this);
    private Tree tr = new Tree();
    //private static final int CHART_HEIGHT_FIX_SIZE = 30;
    private PowderFileCabinet mPowderFileCabinet;

    public void powderFileCabinetUpdate(org.jpowder.fileCabinet.Subject data) {
        org.jpowder.fileCabinet.PowderFileCabinet pfc = (org.jpowder.fileCabinet.PowderFileCabinet) data;


        // comment: update to a bigger size by getting the current size and add some amount.
        /**
        java.awt.Dimension area = ChartPlotter.getSize();
        area.height = area.height+( CHART_HEIGHT_FIX_SIZE);
        ChartPlotter.setLayout(new javax.swing.BoxLayout(ChartPlotter, javax.swing.BoxLayout.Y_AXIS));
        ChartPlotter.setPreferredSize(area);
         */
        HashMap<String, DataSet> allData = pfc.getData();

        // Get new dataset
        String fileName = pfc.getLastUpdateFileName();
        DataSet lastAddedDataset = allData.get(fileName);

        Stopwatch lStopwatch = new Stopwatch();
        lStopwatch.start();
        DatasetPlotter plot = DatasetPlotter.createDatasetPlotter(lastAddedDataset);

//creating the internal frame here
        javax.swing.JPanel chartpanls = new javax.swing.JPanel();
        chartpanls.setLayout(new BorderLayout());
        chartpanls.add(plot.createPowderChart());
        Internalframe internalframe = new Internalframe(chartpanls);
        ChartPlotter.setLayout(new FlowLayout());
        ChartPlotter.add(internalframe);
        setVisible(true);


        System.out.println("\nTime it took to create chart " + fileName);
        System.out.println(lStopwatch.getElapsedTime());
        lStopwatch.reset();
        /**
        // add seperator
        JPanel seperatePanel = new JPanel();
        //seperatePanel.setBackground(new java.awt.Color(240, 240, 240));
        seperatePanel.setMinimumSize(new Dimension(550, 4));
        seperatePanel.setPreferredSize(new Dimension(550, 4));
        seperatePanel.setMaximumSize(new Dimension(550, 4));
        ChartPlotter.add(seperatePanel);
        ChartPlotter.revalidate();
        java.awt.Rectangle rect = ChartPlotter.getBounds();
        //chart_scrp.getVerticalScrollBar().setValue(rect.height);
         * */
    }// powderFileCabinetUpdate

    /**
     * JVM starting point
     *
     * @param args
     */
    public JDesktopPane getChartPanel() {
        return this.ChartPlotter;
    }

    public JPowderFinalGui() {

        mPowderFileCabinet = new PowderFileCabinet();
        mPowderFileCabinet.registerObserver(this);
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, e.getMessage());
        }
        initComponents();
        Filechoose.add(fileChooserPanel);
        fileChooserPanel.setVisible(true);

        Treetab.add(tr);
        tr.setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        Chart = new javax.swing.JPanel();
        ChartPlotter = new javax.swing.JDesktopPane();
        home = new javax.swing.JPanel();
        Filechoose = new javax.swing.JPanel();
        Tabs = new javax.swing.JTabbedPane();
        Treetab = new javax.swing.JPanel();
        Analysis = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jSlider1 = new javax.swing.JSlider();
        jSlider2 = new javax.swing.JSlider();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        New = new javax.swing.JMenuItem();
        Open = new javax.swing.JMenuItem();
        Save = new javax.swing.JMenuItem();
        Exit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        Content = new javax.swing.JMenuItem();
        OnlieDocsandSupport = new javax.swing.JMenuItem();
        About = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("JPowder Crystallography Demo");
        setLocationByPlatform(true);

        jSplitPane1.setDividerLocation(300);
        jSplitPane1.setDividerSize(8);

        Chart.setBorder(javax.swing.BorderFactory.createTitledBorder("Chart"));

        ChartPlotter.setBackground(new java.awt.Color(240, 240, 240));
        ChartPlotter.setBorder(javax.swing.BorderFactory.createTitledBorder("ChartPlot"));
        ChartPlotter.setAutoscrolls(true);
        ChartPlotter.setOpaque(false);

        javax.swing.GroupLayout ChartLayout = new javax.swing.GroupLayout(Chart);
        Chart.setLayout(ChartLayout);
        ChartLayout.setHorizontalGroup(
            ChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChartLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ChartPlotter, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
                .addContainerGap())
        );
        ChartLayout.setVerticalGroup(
            ChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChartLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ChartPlotter, javax.swing.GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane1.setRightComponent(Chart);

        Filechoose.setPreferredSize(new java.awt.Dimension(279, 350));
        Filechoose.setLayout(new java.awt.BorderLayout());

        Tabs.setFont(new java.awt.Font("Kartika", 1, 11));
        Tabs.setMaximumSize(new java.awt.Dimension(327, 32767));

        Treetab.setLayout(new java.awt.BorderLayout());
        Tabs.addTab(" Tree ", Treetab);

        jButton3.setText("jButton1");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SendMail.gif"))); // NOI18N
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jButton4.setText("jButton1");

        jButton5.setText("jButton1");

        jButton6.setText("jButton1");

        jLabel1.setText("delete");

        jLabel2.setText("Email");

        jLabel3.setText("jLabel1");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/del_med.gif"))); // NOI18N

        javax.swing.GroupLayout AnalysisLayout = new javax.swing.GroupLayout(Analysis);
        Analysis.setLayout(AnalysisLayout);
        AnalysisLayout.setHorizontalGroup(
            AnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AnalysisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AnalysisLayout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6))
                    .addGroup(AnalysisLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(AnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(AnalysisLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12))
                            .addGroup(AnalysisLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)))
                        .addGroup(AnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AnalysisLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel2))
                            .addGroup(AnalysisLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(AnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AnalysisLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel3))
                            .addComponent(jButton3))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        AnalysisLayout.setVerticalGroup(
            AnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AnalysisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(238, Short.MAX_VALUE))
        );

        Tabs.addTab("Analysis", Analysis);

        jButton1.setText("jButton1");

        jButton7.setText("jButton7");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton7)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton7)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSlider1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSlider2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jButton1)))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(35, 35, 35)
                .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(162, Short.MAX_VALUE))
        );

        Tabs.addTab("Effects ", jPanel1);

        javax.swing.GroupLayout homeLayout = new javax.swing.GroupLayout(home);
        home.setLayout(homeLayout);
        homeLayout.setHorizontalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Tabs, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                    .addComponent(Filechoose, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        homeLayout.setVerticalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Filechoose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jSplitPane1.setLeftComponent(home);

        jMenu1.setText("File");

        New.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        New.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/new_small.gif"))); // NOI18N
        New.setText("New...");
        New.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewActionPerformed(evt);
            }
        });
        jMenu1.add(New);

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

        jMenu2.setText("Help");

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
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1063, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
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
        JPowderFinalGui jpowderfinalgui = new JPowderFinalGui();
        ScreenUtil.centerFrame(jpowderfinalgui);
        jpowderfinalgui.setVisible(true);

    }//GEN-LAST:event_NewActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JPowderFinalGui().setVisible(true);

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem About;
    private javax.swing.JPanel Analysis;
    private javax.swing.JPanel Chart;
    private javax.swing.JDesktopPane ChartPlotter;
    private javax.swing.JMenuItem Content;
    private javax.swing.JMenuItem Exit;
    private javax.swing.JPanel Filechoose;
    private javax.swing.JMenuItem New;
    private javax.swing.JMenuItem OnlieDocsandSupport;
    private javax.swing.JMenuItem Open;
    private javax.swing.JMenuItem Save;
    private javax.swing.JTabbedPane Tabs;
    private javax.swing.JPanel Treetab;
    private javax.swing.JPanel home;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSlider jSlider2;
    private javax.swing.JSplitPane jSplitPane1;
    // End of variables declaration//GEN-END:variables
}
