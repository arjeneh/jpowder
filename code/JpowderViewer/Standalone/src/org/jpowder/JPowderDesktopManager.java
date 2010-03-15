/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder;

import java.awt.Dimension;
import javax.swing.DefaultDesktopManager;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author qyt21516
 *
 * Changes
 * -------
 * 22-August-2007: make sure it does not go out of the frame (KP);
 */
public class JPowderDesktopManager extends DefaultDesktopManager {

    private JpowderInternalframe jpowderInternalframe;

    @Override
    public void closeFrame(JInternalFrame f) {
        f = jpowderInternalframe;
        System.out.println("JPowderDesktopManager");
    }

    public boolean closing() {
        closeFrame(jpowderInternalframe);
        return true;
    }

    /**
     * To handle action when a frame is moved and keeps the frame from leaving the desktop.
     * @param component The JComponent that is being moved.
     * @param xCordinate horizontal location
     * @param yCordinate vertical location
     */
    @Override
    public void dragFrame(JComponent component, int xCordinate, int yCordinate) {
        if (component instanceof JInternalFrame) {  // Deal only w/internal frames
            JInternalFrame frame = (JInternalFrame) component;
            JDesktopPane desk = frame.getDesktopPane();
            Dimension d = desk.getSize();

            // figuring out how to adjust
            // to keep the frame on the desktop.
            if (xCordinate < 0) {              // too far left?
                xCordinate = 0;
            } else {
                if (xCordinate + frame.getWidth() > d.width) {
                    xCordinate = d.width - frame.getWidth();
                }
            }
            if (yCordinate < 0) {              // too high?
                yCordinate = 0;
            } else {
                if (yCordinate + frame.getHeight() > d.height) {
                    yCordinate = d.height - frame.getHeight();
                }
            }
        }
        super.dragFrame(component, xCordinate, yCordinate);
    }

    /**
     * Checks to determine if the component is resized outside of the bounds
     * of the desktop pane.
     *
     * @param component the component that is being resized
     * @param xCordinate horizontal location
     * @param yCordinate vertical location
     * @param w width
     * @param h height
     */
    @Override
    public void resizeFrame(JComponent component, int xCordinate, int yCordinate, int w, int h) {
        if (component instanceof JInternalFrame) {
            JInternalFrame frame = (JInternalFrame) component;
            JDesktopPane desktop = frame.getDesktopPane();
            int width = desktop.getSize().width;
            int height = desktop.getSize().height;

            //Adjust Size
            if (xCordinate < 0) {
                w += xCordinate;
            }
            if (xCordinate + w > width) {
                w = width - xCordinate;
            }
            if (yCordinate < 0) {
                h += yCordinate;
            }
            if (yCordinate + h > height) {
                h = height - yCordinate;
            }
            if (xCordinate < 0) {
                xCordinate = 0;
            }
            if (xCordinate + w > width) {
                w = width - xCordinate;
            }
            if (yCordinate < 0) {
                yCordinate = 0;
            }
            if (yCordinate + h > height) {
                yCordinate = height - h;
            }
        }
        super.resizeFrame(component, xCordinate, yCordinate, w, h);
    }
}
