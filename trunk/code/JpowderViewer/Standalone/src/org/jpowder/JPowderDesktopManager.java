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
 * Author(s):  M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *             Kreecha Puphaiboon, Computer Science Lecturer, Kasem Bundit University
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder;

import java.awt.Dimension;
import javax.swing.DefaultDesktopManager;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 * This class for change some of jDesktopPane's defaults behaviour.
 *
 */
public class JPowderDesktopManager extends DefaultDesktopManager {

    private JpowderInternalframe jpowderInternalframe;

    @Override
    public void closeFrame(JInternalFrame f) {
        f = jpowderInternalframe;

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
