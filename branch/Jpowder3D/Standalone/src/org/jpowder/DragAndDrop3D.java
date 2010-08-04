/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arjeneh
 */
public class DragAndDrop3D implements DropTargetListener {

    private DataVisibleInChart dvic = new DataVisibleInChart();

    public DragAndDrop3D(DataVisibleInChart dvic) {
        this.dvic=dvic;
    }




    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {

        FilesTable filesTable = new FilesTable(dvic);
        
        Transferable transfeable = dtde.getTransferable();
        DataFlavor[] flavors = transfeable.getTransferDataFlavors();
        dtde.acceptDrop(DnDConstants.ACTION_COPY);

        for (int i = 0; i < flavors.length; i++) {
            if (flavors[i].isFlavorJavaFileListType()) {
                try {
                    // populate allFilenames
                    java.util.List<File> list = (java.util.List) transfeable.getTransferData(flavors[i]);

                    for (int j = 0; j < list.size(); j++) {

                        filesTable.addaFileToTable(list.get(j));
//                        System.out.println(file[j].getName());

                    }

                } catch (UnsupportedFlavorException ex) {
                    Logger.getLogger(Jpowder.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Jpowder.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (flavors[i].equals(DataFlavor.stringFlavor)) {

                try {
                    // populate allFilenames

                    String fileName = (String) transfeable.getTransferData(DataFlavor.stringFlavor);

                    String[] array = fileName.split("\n");
                    int n = array.length;
                    for (int j = 0; j < n; j++) {

                        File files = new File(array[j]);

                        filesTable.addaFileToTable(files);

                    }

                } catch (Exception ex) {
                }
            } else {
            }
        }
        filesTable.setVisible(true);

    }
}
