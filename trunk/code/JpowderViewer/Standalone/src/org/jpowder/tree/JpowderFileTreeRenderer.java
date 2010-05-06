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
 * Axis.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Original Author:Kreecha Puphaiboon
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
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
 *
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
