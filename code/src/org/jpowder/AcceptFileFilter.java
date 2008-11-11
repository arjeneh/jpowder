/*
 * @(#)AcceptFileFilter.java
 *
 */

package org.jpowder;

import javax.swing.filechooser.*;
import java.io.File;

/**
 * <code>AcceptFileFilter</code> extends FileFilter.
 * <todo>Kai, please describe in more detail the purpose of this class</todo>
 *
 * @see javax.swing.filechooser.FileFilter
 * @author Kai
 */
public class AcceptFileFilter extends FileFilter {

  String[] extensions;  // store file extentions (in lowercase!)
  String description;   // One description for all the extensions

  /**
   * KAI: What is this constructor good for? What is 'ext' here used for??
   */
  public AcceptFileFilter(String ext) {
    this (new String[] {ext}, null);
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
    if (f.isDirectory()) { return true; }

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
  public String getDescription() { return description; }
}
