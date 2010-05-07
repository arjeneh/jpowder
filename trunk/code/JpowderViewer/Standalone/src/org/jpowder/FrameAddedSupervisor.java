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
 * FrameAddedSupervisor.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  Kreecha Puphaiboon, Computer Science Lecturer, Kasem Bundit University
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
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

