// AcceptFileFilter.java
// A straightforward extension-based example of a file filter. 
// In JDK 6, we can use format below.
// FileFilter filter = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg");
// JFileChooser fileChooser = ...;
// fileChooser.addChoosableFileFilter(filter);
//
import javax.swing.filechooser.*;
import java.io.File;

public class AcceptFileFilter extends FileFilter {

  String[] extensions;
  String description;

  public AcceptFileFilter(String ext) {
    this (new String[] {ext}, null);
  }

  public AcceptFileFilter(String[] exts, String descr) {
    // Clone and lowercase the extensions
    extensions = new String[exts.length];
    for (int i = exts.length - 1; i >= 0; i--) {
      extensions[i] = exts[i].toLowerCase();
    }
    // Make sure we have a valid (if simplistic) description
    description = (descr == null ? exts[0] + " files" : descr);
  }

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
    //javax.swing.JOptionPane.showMessageDialog(null, "Only .xye is acceptted.");
    return false;
  }

  public String getDescription() { return description; }
}
