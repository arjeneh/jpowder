package org.jpowder;

import java.awt.Cursor;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import org.jpowder.fileCabinet.Subject;
import org.jpowder.fileCabinet.PowderFileCabinet;
import org.jpowder.fileCabinet.PowderFileObserver;
import org.jpowder.JCheckboxList.JCheckBoxJList;
//
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.jpowder.JCheckboxList.CheckableFileItem;
import org.jpowder.dataset.DatasetPlotter;
import org.jpowder.dataset.XY_XYE;

/**
 * User can add or drag/drop a dataset file. It brings up a dialog
 * so the user can navigate to the file and the program will process chart ploting.
 *
 * @author Kreecha Puphaiboon
 * @since 21 May 2007, 09:53
 *
*/
public class FileChooserPanel extends javax.swing.JPanel
        implements PowderFileObserver, DropTargetListener {

    // Commented out by Anders 16/1/09
    //private java.awt.dnd.DropTarget dt;
    
    //
    private PowderFileCabinet mPowderFileCabinet;
    private FileNameListModel listModel;
    private JCheckBoxJList checkboxList;

    private javax.swing.JScrollPane file_sp;
    private JPowder jPowderMain;//where this class located in


    //This constructor is for self-testing in the main method of this file.
    public FileChooserPanel() {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e);
        }
        //
        initComponents();

        //create a list model to put in the JList
        listModel = new FileNameListModel();
        checkboxList = new JCheckBoxJList(listModel);
        checkboxList.setFont(new java.awt.Font("Tahoma", 0, 10));
        checkboxList.setMinimumSize(new java.awt.Dimension(210, 274));
        checkboxList.setPreferredSize(new java.awt.Dimension(210, 274));

        file_sp = new javax.swing.JScrollPane(checkboxList, javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        file_sp.setMinimumSize(new java.awt.Dimension(260, 284));
        file_sp.setPreferredSize(new java.awt.Dimension(260, 284));
        file_sp.setViewportView(checkboxList);

        java.awt.GridBagConstraints gridBagConstraints;
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        add(file_sp, gridBagConstraints);

        // Commented out by Anders 16/1/09
        //dt = new java.awt.dnd.DropTarget(this.checkboxList, this);
        
        //UTILISE OBSERVER PATTERN.
        mPowderFileCabinet = new PowderFileCabinet();
        mPowderFileCabinet.registerObserver(this);
        mPowderFileCabinet.registerObserver((PowderFileObserver) listModel);

    }//FileChooserPanel

    //This constructor is called from JPowder's main method.
    //@param jPowderMain: JPowder
    public FileChooserPanel(JPowder jPowderMain) {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        this.jPowderMain = jPowderMain;
        //
        initComponents();

        //create a list model to put in the JList
        listModel = new FileNameListModel();
        checkboxList = new JCheckBoxJList(listModel);
        checkboxList.setFont(new java.awt.Font("Tahoma", 0, 10));
        checkboxList.setMinimumSize(new java.awt.Dimension(210, 214));
        checkboxList.setPreferredSize(new java.awt.Dimension(210, 214));

        //See how many selected, if greater or equal to 2 then enable the button.
        checkboxList.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent evt) {
                if (evt.getValueIsAdjusting()) {

                    ListModel model = checkboxList.getModel();
                    int n = model.getSize();

                    for (int i = 0; i < n; i++) {
                        CheckableFileItem item = (CheckableFileItem) model.getElementAt(i);
                        if (item.isSelected()) {
                            System.out.println("Selected items: " + item.toString());
                        }
                    }//for
                }//if adjust
            }
        });

        file_sp = new javax.swing.JScrollPane(checkboxList, javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        file_sp.setMinimumSize(new java.awt.Dimension(260, 240));
        file_sp.setViewportView(checkboxList);

        java.awt.GridBagConstraints gridBagConstraints;
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);

        add(file_sp, gridBagConstraints);
        
        // Commented out by Anders 16/1/09
        //dt = new java.awt.dnd.DropTarget(this.checkboxList, this);
        
        //UTILISE OBSERVER PATTERN.
        mPowderFileCabinet = new PowderFileCabinet();

        mPowderFileCabinet.registerObserver(this);
        mPowderFileCabinet.registerObserver((PowderFileObserver) listModel);
        mPowderFileCabinet.registerObserver((JPowder) jPowderMain);

    }//FileChooserPanel

    public void powderFileCabinetUpdate(Subject data) {
        PowderFileCabinet pfc = (PowderFileCabinet) data;
        System.out.println("From FileChooserPanel.java PowderFileCabinet is updated as " + pfc.getData().size());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        addFile_btn = new javax.swing.JButton();
        plotFiles_btn = new javax.swing.JButton();
        deleteFile_btn1 = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "File Selection Panel (Drop file here)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102))); // NOI18N
        setMinimumSize(new java.awt.Dimension(265, 340));
        setPreferredSize(new java.awt.Dimension(265, 340));
        setLayout(new java.awt.GridBagLayout());

        addFile_btn.setFont(new java.awt.Font("Tahoma", 0, 10));
        addFile_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/addFile.gif"))); // NOI18N
        addFile_btn.setText("Add file(s)");
        addFile_btn.setToolTipText("Add file");
        addFile_btn.setMaximumSize(new java.awt.Dimension(120, 23));
        addFile_btn.setMinimumSize(new java.awt.Dimension(120, 23));
        addFile_btn.setPreferredSize(new java.awt.Dimension(120, 23));
        addFile_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFile_btnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 5, 2);
        add(addFile_btn, gridBagConstraints);

        plotFiles_btn.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        plotFiles_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PlotChart.gif"))); // NOI18N
        plotFiles_btn.setText("Plot datasets");
        plotFiles_btn.setToolTipText("Plot datasets");
        plotFiles_btn.setMaximumSize(new java.awt.Dimension(50, 23));
        plotFiles_btn.setMinimumSize(new java.awt.Dimension(50, 23));
        plotFiles_btn.setPreferredSize(new java.awt.Dimension(50, 23));
        plotFiles_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plotFiles_btnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 5, 2);
        add(plotFiles_btn, gridBagConstraints);

        deleteFile_btn1.setFont(new java.awt.Font("Tahoma", 0, 10));
        deleteFile_btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/del_small.gif"))); // NOI18N
        deleteFile_btn1.setText("Delete file");
        deleteFile_btn1.setToolTipText("Delete file");
        deleteFile_btn1.setMaximumSize(new java.awt.Dimension(50, 23));
        deleteFile_btn1.setMinimumSize(new java.awt.Dimension(50, 23));
        deleteFile_btn1.setPreferredSize(new java.awt.Dimension(50, 23));
        deleteFile_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteFile_btn1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 5, 2);
        add(deleteFile_btn1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    /** 
     * For drag and drop of files into Jpowder
     */
    public void dragEnter(java.awt.dnd.DropTargetDragEvent dtde) {}

    /**
     * For drag and drop of files into Jpowder
     */
    public void dragExit(java.awt.dnd.DropTargetEvent dte) {
        System.out.println("Source: " + dte.getSource());
    //System.out.println("Drag Exit");
    }

    /**
     * For drag and drop of files into Jpowder
     */
    public void dragOver(java.awt.dnd.DropTargetDragEvent dtde) {}

    /**
     * For drag and drop of files into Jpowder
     */
    public void dropActionChanged(java.awt.dnd.DropTargetDragEvent dtde) {}

    /**
     * For drag and drop of files into Jpowder
     */
    public void drop(java.awt.dnd.DropTargetDropEvent dtde) {

        try {
            // Ok, get the dropped object and try to figure out what it is
            Transferable tr = dtde.getTransferable();
            DataFlavor[] flavors = tr.getTransferDataFlavors();
            Vector localData = null;

            for (int i = 0; i < flavors.length; i++) {
                System.out.println("Possible flavor: " + flavors[i].getMimeType());
                // Check for file lists specifically
                if (flavors[i].isFlavorJavaFileListType()) {
                    // Great!  Accept copy drops...
                    dtde.acceptDrop(DnDConstants.ACTION_COPY);
                    System.out.println("Successful file list drop.\n\n");

                    // And add the list of file names to our text area
                    java.util.List list = (java.util.List) tr.getTransferData(flavors[i]);

                    for (int j = 0; j < list.size(); j++) {
                        File file = (File) list.get(j);
                        String fileName = file.getName().toLowerCase();

                        if (mPowderFileCabinet.checkAcceptedFileType(fileName)) {

                            mPowderFileCabinet.setLastUpdateFileName(fileName);
                            localData = mPowderFileCabinet.getLocalFile(file);
                            System.out.println("localData = " + localData);

                            if (localData != null) {
                                mPowderFileCabinet.addFile(mPowderFileCabinet.getLastUpdateFileName(), localData);
                            }

                        } else {
                            javax.swing.JOptionPane.showMessageDialog(this, "Only ASCII file please.");
                        //end if extension matched
                        }
                    }

                    // If we made it this far, everything worked.
                    dtde.dropComplete(true);
                    return;
                }
            }
            // Hmm, the user must not have dropped a file list
            System.out.println("Drop failed: " + dtde);
            dtde.rejectDrop();
        } catch (Exception e) {
            e.printStackTrace();
            dtde.rejectDrop();
        }//drop
    }

private void plotFiles_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plotFiles_btnActionPerformed
    // TODO: Plot mulitple files//GEN-LAST:event_plotFiles_btnActionPerformed
        java.util.HashMap dataHm = mPowderFileCabinet.getData();
        //
        ListModel model = checkboxList.getModel();
        ArrayList<String> nameList = new ArrayList<String>();
        int n = model.getSize();
        //
        for (int i = 0; i < n; i++) {
            CheckableFileItem item = (CheckableFileItem) model.getElementAt(i);
            if (item.isSelected()) {
                nameList.add(item.toString());
            }//if
        }//for

        System.out.println("Selectded items: " + nameList.toString() + " and size = " + nameList.size() );
        //not enough dataset 2 at least.
        if (nameList.size() <= 1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select at least 2 files.");
            return;
        }//less than two.

        System.out.println("CheckableFileItem: " + nameList.toString());

        /* 1 Get the file data that match with nameList
         * 2 start plot from the file nameList one by one
         * 3 add the chart to JPowder one with jPowderMain.getChartPanel()
         */
        Vector dat = new Vector();
        for (int i = 0; i < nameList.size(); i++) {
            String fileName = nameList.get(i);
            Vector lData = (Vector) dataHm.get(fileName);
            dat.add(lData);
        }

        System.out.println("Data of selected files: " + dat.toString());
        System.out.println("Data size: " + dat.size());
        System.out.println("Selected files: " + nameList.toString());

        //plot multiple files.
        XY_XYE xy_xye = new XY_XYE(dat, nameList.toString());
        DatasetPlotter plotMultiCol = xy_xye.createDatasetPlotter();
        JPanel powderChartPanel = jPowderMain.getChartPanel();
        powderChartPanel.add(plotMultiCol.createPowderChart());
        powderChartPanel.revalidate();
    }

private void addFile_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFile_btnActionPerformed

    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

    // We're going to do something that takes potentially a long time, so we
    // spin off a thread and update the display when we're done.
    Thread worker = new Thread() {

        @Override
        public void run() {
            // Load the powder diffraction files which are selected by the user
            // into the file cabinet
            mPowderFileCabinet.loadFiles();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
            }

            // Report the result using invokeLater().
            SwingUtilities.invokeLater(new Runnable() {

                public void run() {
                    setCursor(null); //turn off the wait cursor when done.
                }
            });
        }
    };

    worker.start(); // So we don't hold up the dispatch thread.

}//GEN-LAST:event_addFile_btnActionPerformed

private void deleteFile_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteFile_btn1ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_deleteFile_btn1ActionPerformed

public static void main(String args[]) {
    javax.swing.JFrame frame = new javax.swing.JFrame("File chooser");
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.add(new FileChooserPanel(), java.awt.BorderLayout.NORTH);
        frame.setSize(300, 200);
        frame.setVisible(true);
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addFile_btn;
    private javax.swing.JButton deleteFile_btn1;
    private javax.swing.JButton plotFiles_btn;
    // End of variables declaration//GEN-END:variables
  
}
