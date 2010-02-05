/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder;

/**
 *
 * @author qyt21516
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JpowderIcon.java
 *
 * Created on 09-Dec-2009, 15:49:25
 */
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingWorker;

/**
 *
 * @author qyt21516
 */
public class JpowderIcon {

  public int width ;
  private int height ;
  private List<JButton> buttons;
  private ThumbnailAction thumbAction;
//  File f = new File("");
//  private String imagedir = f.getAbsolutePath() + File.separator + "src" + File.separator + "images" + File.separator;
  private String imagedir ="/images/";
  /**
   * List of all the descriptions of the image files. These correspond one to
   * one with the image file names
   */
  private String[] title = {};
  /**
   * List of all the image files to load.
   */
  private String[] imgdir = {};

  public JpowderIcon(List<JButton> buttons, String[] imgdir, String[] title) {
    this.buttons = buttons;
    set_imgdir(imgdir);
    set_title(title);
    // start the image loading SwingWorker in a background thread
    loadimages.execute();


  }

  public int setWidth(int x) {
    this.width = x;
     return x;
  }

  public int setHeight(int y) {
    this.height = y;
    return y;
  }

  public void set_imgdir(String[] imgdir) {
    this.imgdir = imgdir;

  }

  public void set_title(String[] title) {
    this.title = title;

  }

  public Icon getDisplayPhoto() {
    return thumbAction.getDisplayPhoto();
  }

  /**
   * Creates an ImageIcon if the path is valid.
   * @param String - resource path
   * @param String - description of the file
   */
  protected ImageIcon createImageIcon(String path,
          String description) throws IOException {
    try {


//      BufferedImage img = javax.imageio.ImageIO.read(new FileInputStream(getClass().getResource(url)));
      BufferedImage img = javax.imageio.ImageIO.read(getClass().getResource(path));

      if (img != null) {
        return new ImageIcon(img, description);
      } else {
        System.err.println("Couldn't find file: " + path);

      }
    } catch (FileNotFoundException ex) {
      Logger.getLogger(JpowderIcon.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  /**
   * Resizes an image using a Graphics2D object backed by a BufferedImage.
   * @param srcImg - source image to scale
   * @param w - desired width
   * @param h - desired height
   * @return - the new resized image
   */
  private Image getScaledImage(Image srcImg, int w, int h) {
    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    Graphics2D g2 = resizedImg.createGraphics();
    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g2.drawImage(srcImg, 0, 0, w, h, null);
    g2.dispose();
    return resizedImg;
  }
  /**
   * SwingWorker class that loads the images a background thread and calls publish
   * when a new one is ready to be displayed.
   *
   * We use Void as the first SwingWroker param as we do not need to return
   * anything from doInBackground().
   */
  private SwingWorker<Void, ThumbnailAction> loadimages = new SwingWorker<Void, ThumbnailAction>() {

    /**
     * Creates full size and thumbnail versions of the target image files.
     */
    @Override
    protected Void doInBackground() throws Exception {
      for (int i = 0; i < imgdir.length; i++) {
        ImageIcon icon;
        icon = createImageIcon(imagedir + imgdir[i], title[i]);
        thumbAction = null;
        if (icon != null) {

          ImageIcon thumbnailIcon = new ImageIcon(getScaledImage(icon.getImage(), setHeight(80), setWidth(82)));

          thumbAction = new ThumbnailAction(icon, thumbnailIcon, title[i]);

        } else {
        }
        publish(thumbAction);



      }
      done();
      // unfortunately we must return something, and only null is valid to
      // return when the return type is void.
      return null;
    }

    /**
     * Process all loaded images.``
     */
    @Override
    protected void process(List<ThumbnailAction> thumnailist) {
      int b = thumnailist.size();
      for (int i = 0; i < b; i++) {
        buttons.get(i).setAction(thumnailist.get(i));

      }

    }
  };

  private class ThumbnailAction extends AbstractAction {

    /**
     *The icon if the full image we want to display.
     */
    private Icon displayPhoto;

    /**
     * @param Icon - The full size photo to show in the button.
     * @param Icon - The thumbnail to show in the button.
     * @param String - The descriptioon of the icon.
     */
    public ThumbnailAction(Icon photo, Icon thumb, String desc) {
      displayPhoto = thumb;
      // The LARGE_ICON_KEY is the key for setting the
      // icon when an Action is applied to a button.
      putValue(LARGE_ICON_KEY, thumb);
      // The short description becomes the tooltip of a button.
      putValue(SHORT_DESCRIPTION, desc);


    }

    public Icon getDisplayPhoto() {
      return displayPhoto;
    }

    /**
     * Shows the full image in the main area and sets the application title.
     * @param e
     */
    public void actionPerformed(ActionEvent e) {


      setHeight(35);
      setWidth(35);
//     markPeakPosition.getPeakLabel().setIcon(displayPhoto);


    }
  }
}
