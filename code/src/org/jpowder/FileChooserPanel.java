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
import java.util.Vector;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.jpowder.JCheckboxList.CheckableFileItem;

/**
 *
 * FileChooserPanel.java
 * Created on 21 May 2007, 09:53
 * @author  Kreecha Puphaiboon
 */
public class FileChooserPanel extends javax.swing.JPanel
        implements PowderFileObserver, DropTargetListener { //ListSelectionListener

    private java.awt.dnd.DropTarget dt;
    //
    private PowderFileCabinet mPowderFileCabinet;
    private FileNameListModel listModel;
    private JCheckBoxJList checkboxList;
    //
    //private JCheckBoxJList checkboxList;
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

        dt = new java.awt.dnd.DropTarget(this.checkboxList, this);
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
        //
        initComponents();

        //create a list model to put in the JList
        listModel = new FileNameListModel();
        checkboxList = new JCheckBoxJList(listModel);
        checkboxList.setFont(new java.awt.Font("Tahoma", 0, 10));
        checkboxList.setMinimumSize(new java.awt.Dimension(210, 214));
        checkboxList.setPreferredSize(new java.awt.Dimension(210, 214));

        //listen to activate the button. 
        //See how many selected, if greater or equal to 2 then enable the button.
        checkboxList.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent evt) {
                if (evt.getValueIsAdjusting()) {

                    ListModel model = checkboxList.getModel();
                    int n = model.getSize();
                    int numSelectedItem = 0;
                    for (int i = 0; i < n; i++) {
                        CheckableFileItem item = (CheckableFileItem) model.getElementAt(i);
                        if (item.isSelected()) {
                            numSelectedItem++;
                            //textArea.append(item.toString());
                            //textArea.append(System.getProperty("line.separator"));
                            System.out.println("Selected items: " + item.toString());
                        }
                    }//for
                }//if adjust
            }
        });

        file_sp = new javax.swing.JScrollPane(checkboxList, javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        file_sp.setMinimumSize(
                new java.awt.Dimension(
                260, 240));
        file_sp.setViewportView(checkboxList);

        java.awt.GridBagConstraints gridBagConstraints;
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);

        add(file_sp, gridBagConstraints);
        dt = new java.awt.dnd.DropTarget(this.checkboxList, this);            //UTILISE OBSERVER PATTERN.
        mPowderFileCabinet = new PowderFileCabinet();

        mPowderFileCabinet.registerObserver(this);

        mPowderFileCabinet.registerObserver((PowderFileObserver) listModel);
        mPowderFileCabinet.registerObserver(
                (JPowder) jPowderMain);

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
        deleteFile_btn = new javax.swing.JButton();
        multiPlot_btn = new javax.swing.JButton();

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

        deleteFile_btn.setFont(new java.awt.Font("Tahoma", 0, 10));
        deleteFile_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/del_small.gif"))); // NOI18N
        deleteFile_btn.setText("Delete file");
        deleteFile_btn.setToolTipText("Delete file");
        deleteFile_btn.setMaximumSize(new java.awt.Dimension(50, 23));
        deleteFile_btn.setMinimumSize(new java.awt.Dimension(50, 23));
        deleteFile_btn.setPreferredSize(new java.awt.Dimension(50, 23));
        deleteFile_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteFile_btnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 5, 2);
        add(deleteFile_btn, gridBagConstraints);

        multiPlot_btn.setText("Plot datasets");
        multiPlot_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                multiPlot_btnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 5, 2);
        add(multiPlot_btn, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    /** ALL DRAGE AND DROP THING */
    public void dragEnter(java.awt.dnd.DropTargetDragEvent dtde) {
        //System.out.println("Drag Enter");
    }

    public void dragExit(java.awt.dnd.DropTargetEvent dte) {
        System.out.println("Source: " + dte.getSource());
    //System.out.println("Drag Exit");
    }

    public void dragOver(java.awt.dnd.DropTargetDragEvent dtde) {
        //System.out.println("Drag Over");
    }

    public void dropActionChanged(java.awt.dnd.DropTargetDragEvent dtde) {
        //System.out.println("Drop Action Changed");
    }

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
                            localData = mPowderFileCabinet.getLocalFile(file, null);
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

private void deleteFile_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteFile_btnActionPerformed
    //if no nothing selected then alert.
    /*if (checkboxList.getSelectedIndex() == -1) {
    javax.swing.JOptionPane.showMessageDialog(jpowder, "To delete, please select a file first.");
    return;
    }
    
    //if the list is empty then do nothing
    if (this.listModel.getSize() <= 0) {
    javax.swing.JOptionPane.showMessageDialog(jpowder, "Please add a file first.");
    return;
    }
    
    //do the actual deletion.
    if (this.listModel.getSize() > 0) {
    System.out.println(this.listModel.getSize());
    int index = checkboxList.getSelectedIndex();
    this.listModel.removeElementAt(index);
    this.fileToReadVector.removeElementAt(index);
    }*/
}//GEN-LAST:event_deleteFile_btnActionPerformed

private void addFile_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFile_btnActionPerformed

    //partNoEnd_tf.setText("Loading....");
    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

    // We're going to do something that takes a long time, so we
    // spin off a thread and update the display when we're done.
    Thread worker = new Thread() {

        @Override
        public void run() {
            // Something that takes a long time . . . 
            //doLongWork();
            mPowderFileCabinet.loadFiles();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
            }

            // Report the result using invokeLater().
            SwingUtilities.invokeLater(new Runnable() {

                public void run() {
                    //partNoEnd_tf.setText("Done");
                    setCursor(null); //turn off the wait cursor when done.
                }
            });
        }
    };

    worker.start(); // So we don't hold up the dispatch thread.

}//GEN-LAST:event_addFile_btnActionPerformed

private void multiPlot_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_multiPlot_btnActionPerformed
// TODO: Plot mulitple files
    int selections[] = checkboxList.getSelectedIndices();
    Object selectedValues[] = checkboxList.getSelectedValues();

    for (int i = 0, n = selections.length; i < n; i++) {
        if (i == 0) {
            System.out.println("Not enough selections.");
        }
        System.out.println("Index number: " + selections[i] + " , file name: " + selectedValues[i] + " ");
    }//for

}//GEN-LAST:event_multiPlot_btnActionPerformed

public static void main(String args[]) {
    javax.swing.JFrame frame = new javax.swing.JFrame("File chooser");
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.add(new FileChooserPanel(), java.awt.BorderLayout.NORTH);
        frame.setSize(300, 200);
        frame.setVisible(true);
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addFile_btn;
    private javax.swing.JButton deleteFile_btn;
    private javax.swing.JButton multiPlot_btn;
    // End of variables declaration//GEN-END:variables
  
}
