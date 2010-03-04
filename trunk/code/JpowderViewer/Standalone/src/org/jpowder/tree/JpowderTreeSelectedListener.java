package org.jpowder.tree;

import java.io.File;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

/**
 * If anyone wants to know what file in the tree is selected, then use this.
 *
 * ----------------------
 * JpowderTreeSelectedListener.java
 * ----------------------
 *
 * Changes
 * -------
 * 04-March-2010 : Version 1 (KP);
 *
 * 
 * @author Kreecha Puphaiboon (KP)
 */
public class JpowderTreeSelectedListener implements TreeSelectionListener {

    DirectoryModel model;

    /**
     * Create a directory model.
     * This results in displaying a subset of
     * the actual file directory.
     *
     * @param mdl The DirectoryModel implementation
     */
    public JpowderTreeSelectedListener(DirectoryModel mdl) {
        model = mdl;
    }

    public void valueChanged(TreeSelectionEvent e) {
        File fileSysEntity = (File) e.getPath().getLastPathComponent();
        if (fileSysEntity.isDirectory()) {
            model.setDirectory(fileSysEntity);
        } else {
            model.setDirectory(null);
        }
    }
}