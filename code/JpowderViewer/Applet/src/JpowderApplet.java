/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import javax.swing.JApplet;
import javax.swing.JPanel;

/**
 *
 * @author qyt21516
 */
public class JpowderApplet extends JApplet {

    /**
     * Initialization method that will be called after the applet is loaded
     * into the browser.
     */
    public void init() {
        // TODO start asynchronous download of heavy resources
      JPanel panel = new JPanel();
      panel.setBackground(Color.red);

      add(panel);

    }

    // TODO overwrite start(), stop() and destroy() methods

}
