/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.tree;

import java.awt.Component;
import java.io.File;
import javax.swing.JTree;
import javax.swing.ToolTipManager;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * This class takes care of the rendering for TreeModel in JPowder.
 * It shows a tooltip with path of the file and its size in kilobyte,
 * as an additional information.
 * @author Toshiba
 */
public class JpowderFileTreeRenderer extends DefaultTreeCellRenderer {

    private FileSystemView fsv;

    public JpowderFileTreeRenderer(FileSystemView fsv) {
        this.fsv = fsv;
    }

    public JpowderFileTreeRenderer() {
        this(FileSystemView.getFileSystemView());
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree,
            Object value, boolean sel, boolean expanded, boolean leaf,
            int row, boolean hasFocus) {

        if (!(value instanceof File)) {
            return super.getTreeCellRendererComponent(tree, value,
                    sel, expanded, leaf, row, hasFocus);
        }

        //Tooltip if it is a file.
        File f = (File)value;

        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) tree.getCellRenderer();

        ToolTipManager.sharedInstance().registerComponent(tree);
        renderer.setToolTipText(
                f.getAbsolutePath() + ", size of " +
                Long.toString(f.length()) + " kb"
                );
        //end
        
        super.getTreeCellRendererComponent(
                tree, value, sel, expanded, leaf, row, hasFocus);

        setText(fsv.getSystemDisplayName((File) value));
        setIcon(fsv.getSystemIcon((File) value));

        return this;

    }
}
