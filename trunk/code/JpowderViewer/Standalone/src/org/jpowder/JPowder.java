package org.jpowder;

import java.awt.Dimension;
import java.util.Vector;
import javax.swing.JPanel;

import org.jpowder.dataset.DataSet;
import org.jpowder.dataset.DatasetPlotter;
import org.jpowder.dataset.XY;
import org.jpowder.dataset.XYE;
import org.jpowder.util.ScreenUtil;
import org.jpowder.util.VectorMiscUtil;

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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * File change history is stored at: <https://jpowder.org/svn/Jpowder>
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
    private static final int CHART_HEIGHT_FIX_SIZE = 270;
    private static final int FRAME_WIDTH = 1070;
    private static final int FRAME_HEIGHT = 670;
    private boolean InBrowser = true;

  
    public void powderFileCabinetUpdate(org.jpowder.fileCabinet.Subject data) {
        org.jpowder.fileCabinet.PowderFileCabinet pfc = (org.jpowder.fileCabinet.PowderFileCabinet) data;

        // Get copy of all data! Is this really necessary
        java.util.HashMap dataHm = pfc.getData();
        System.out.println("From JPowder.powderFileCabinetUpdate, HashMap Data in PowderFileCabinet is: " + dataHm);

        // Get new dataset
        String fileName = pfc.getLastUpdateFileName();
        Vector lData = (Vector) dataHm.get(fileName);

        // comment: update to a bigger size by getting the current size and add some amount.
        java.awt.Dimension area = powderChartPanel.getSize();
        area.height = area.height + CHART_HEIGHT_FIX_SIZE;
        powderChartPanel.setLayout(new javax.swing.BoxLayout(powderChartPanel, javax.swing.BoxLayout.Y_AXIS));
        powderChartPanel.setPreferredSize(area);

        // Determine how many columns there are
        int countColumn = VectorMiscUtil.countColumnsOf2DVector(lData);

        // plot new dataset.
        switch (countColumn) {
            case 1:
                System.out.println("Data not support yet");
                break;
            case 2:
                DataSet xy = new XY(lData, fileName);
                DatasetPlotter plot2Col = xy.createDatasetPlotter();
                powderChartPanel.add(plot2Col.createPowderChart());
                break;
            case 3:
                //3
                DataSet xye = new XYE(lData, fileName);
                DatasetPlotter plot3Col = xye.createDatasetPlotter();
                powderChartPanel.add(plot3Col.createPowderChart());
                break;
            default:
                DataSet xye3 = new XYE(lData, fileName);
                DatasetPlotter plot3Cols = xye3.createDatasetPlotter();
                powderChartPanel.add(plot3Cols.createPowderChart());
                break;
        }

        // add seperator
        JPanel seperatePanel = new JPanel();
        seperatePanel.setBackground(new java.awt.Color(240, 240, 240));
        seperatePanel.setMinimumSize(new Dimension(550, 8));
        seperatePanel.setPreferredSize(new Dimension(550, 8));
        seperatePanel.setMaximumSize(new Dimension(550, 8));
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
        f.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        // Add applet to the frame
        f.getContentPane().add(applet);
        f.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        f.pack();
        f.setVisible(true);
        //calling Screen.java to center the frame on user screen.
        ScreenUtil.centerFrame(f);

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
        openFileMenuItem = new javax.swing.JMenuItem();
        saveFileMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

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

        openFileMenuItem.setText("Open");
        openFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openFileMenuItem);

        saveFileMenuItem.setText("Save");
        fileMenu.add(saveFileMenuItem);

        jMenuBar1.add(fileMenu);

        editMenu.setText("Edit Chart");

        jMenuItem3.setText("Edit");
        editMenu.add(jMenuItem3);

        jMenuItem4.setText("Delete");
        editMenu.add(jMenuItem4);

        jMenuBar1.add(editMenu);

        helpMenu.setText("Help");

        jMenuItem1.setText("Content");
        helpMenu.add(jMenuItem1);

        jMenuItem6.setText("About");
        helpMenu.add(jMenuItem6);

        jMenuBar1.add(helpMenu);

        setJMenuBar(jMenuBar1);
    }// </editor-fold>//GEN-END:initComponents

    private void openFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileMenuItemActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_openFileMenuItemActionPerformed
    // When user click they can see the graph from what ever data in the table.    //end plotFile_btnActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cardPanel;
    private javax.swing.JScrollPane chart_scrp;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JPanel homePanel;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem6;
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
