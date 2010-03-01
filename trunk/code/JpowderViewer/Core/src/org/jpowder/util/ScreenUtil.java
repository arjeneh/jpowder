package org.jpowder.util;

/**
 * JPowder is the class for all graphics contexts which allow an applet/
 * application to draw charts of crystallography data. It uses JFreechart as a
 * library to plot charts. JPowder's List and Table encapsulates files and the
 * crystallography data state information needed for the various rendering
 * operations.
 *
 * @author      Kreecha Puphaiboon
 * @version     0.7
 * @since       Fabuary 07.
 */
public class ScreenUtil {

    //shrinks w,h if too big, moves x,y if off screen
    public static void adjustBounds(javax.swing.JFrame frame) {
        java.awt.GraphicsEnvironment ge = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment();
        java.awt.Rectangle bounds = ge.getMaximumWindowBounds();
        java.awt.Rectangle frameBounds = frame.getBounds();
        if (frameBounds.x < 0) {
            frameBounds.x = 0;
        }
        if (frameBounds.y < 0) {
            frameBounds.y = 0;
        }
        if (frameBounds.width >= bounds.width) {
            frameBounds.width = bounds.width;
        }
        if (frameBounds.height >= bounds.height) {
            frameBounds.height = bounds.height;
        }
        if (frameBounds.x + frameBounds.width > bounds.width) {
            frameBounds.x = bounds.width - frameBounds.width;
        }
        if (frameBounds.y + frameBounds.height > bounds.height) {
            frameBounds.y = bounds.height - frameBounds.height;
        }
        frame.setBounds(frameBounds);
        if (frameBounds.width == bounds.width && frameBounds.height == bounds.height) {
            frame.setExtendedState(frame.MAXIMIZED_BOTH);
        }
        frame.validate();
    }

    public static void centerFrame(javax.swing.JFrame frame) {
        java.awt.GraphicsEnvironment ge = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment();
        java.awt.Point center = ge.getCenterPoint();
        java.awt.Rectangle bounds = ge.getMaximumWindowBounds();
        int w = Math.max(bounds.width / 2, Math.min(frame.getWidth(), bounds.width));
        int h = Math.max(bounds.height / 2, Math.min(frame.getHeight(), bounds.height));
        int x = center.x - w / 2, y = center.y - h / 2;
        frame.setBounds(x, y, w, h);
        if (w == bounds.width && h == bounds.height) {
            frame.setExtendedState(frame.MAXIMIZED_BOTH);
        }

        frame.validate();
    }
}
