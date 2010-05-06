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
 * Original Author:  Kreecha Puphaiboon
 * Contributor(s):   
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.fileCabinet;

import javax.swing.filechooser.*;
import java.io.File;

/**
 * <code>AcceptFileFilter</code> extends FileFilter.
 * 
 * Class name: AcceptFileFilter.java
 * @see javax.swing.filechooser.FileFilter
 * @author Kai
 * Date: 28/11/08
 * Description:
 *   Used to check if file extension is acceptable.
 *  Called by PowderFileCabinet.java
 *  fileChooser.addChoosableFileFilter(new AcceptFileFilter(ACCEPTED_FILE_TYPE, "ASCII file (*.xye, *.txt)"));
 */
public class AcceptFileFilter extends FileFilter {

    String[] extensions;  // store file extentions (in lowercase!)
    String description;   // One description for all the extensions

    /**
     * Kai: What is this constructor good for? What is 'ext' here used for??
     */
    public AcceptFileFilter(String ext) {
        this(new String[]{ext}, null);
    }

    /**
     * Constructor
     */
    public AcceptFileFilter(String[] exts, String descr) {
        // Clone and lowercase the extensions
        extensions = new String[exts.length];
        for (int i = exts.length - 1; i >= 0; i--) {
            extensions[i] = exts[i].toLowerCase();
        }
        // Make sure to have a none empty description of extensions
        description = (descr == null ? exts[0] + " files" : descr);
    }

    /**
     * Whether the given file (or directory) is accepted by this filter.
     */
    public boolean accept(File f) {
        // We always allow directories, regardless of their extension
        if (f.isDirectory()) {
            return true;
        }

        // Ok, it’s a regular file, so check the extension
        String name = f.getName().toLowerCase();
        for (int i = extensions.length - 1; i >= 0; i--) {
            if (name.endsWith(extensions[i])) {
                return true;
            }
        }

        return false;
    }

    /**
     * Return the description of this filter.
     */
    public String getDescription() {
        return description;
    }
}
