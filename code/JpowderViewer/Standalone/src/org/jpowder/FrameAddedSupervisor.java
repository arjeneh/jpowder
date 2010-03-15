/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 * To keep newly added JInternalFrame inside the JDesktopPane
 * @author Kreecha Puphaiboon
 * created 15 March 2010.
 *
 */
public class FrameAddedSupervisor implements ContainerListener {

    public FrameAddedSupervisor() {

    }

    /* See if the newly added is JInternalFrame, if yes process to make it in the desktop.
     * @param e  the event.
     */
    public void componentAdded(ContainerEvent e) {
        Component child = e.getChild();
        if (child instanceof JInternalFrame) {
            //System.out.println("Added");

            JInternalFrame frame = (JInternalFrame) child;
            JDesktopPane desk = frame.getDesktopPane();
            Dimension d = desk.getSize();

            final int x = frame.getX();
            final int y = frame.getY();
            //System.out.println("Added at x " + x + " y = " + y);

            final int maxX = desk.getWidth() - frame.getWidth();
            final int maxY = desk.getHeight() - frame.getHeight();

            frame.setLocation(Math.min(Math.max(0, x), maxX),
                    Math.min(Math.max(0, y), maxY));
        }
    }


    public void componentRemoved(ContainerEvent e) {
        Component child = e.getChild();
        if (child instanceof JInternalFrame) {
            return;
        }
    }
}

