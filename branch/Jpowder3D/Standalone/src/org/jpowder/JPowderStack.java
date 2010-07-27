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
 * JPowderStack.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  Dan Badham, ISIS, Rutherford Appleton Laboratory
 *             M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder;

import org.jpowder.InernalFrame.JpowderInternalframe;
import java.util.Stack;

/**
 *This class is used for putting certain number of internalfrmaes
 * in stack so they can be retrived on event of close.
 * 
 */
public class JPowderStack {

    private int maxSize;
    private int numberOfFrames = 0;
    private Stack<JpowderInternalframe> stack = new Stack<JpowderInternalframe>();

    /**
     *
     * @param maxSize
     */
    public JPowderStack(int maxSize) {

        this.maxSize = maxSize;


    }

    /**
     * Puts the internalframe on top of the stack if the stack
     * is fulll remove the bottom element from the stack.
     * @param frame
     */
    public void push(JpowderInternalframe frame) {

        if (numberOfFrames == maxSize) {

            JpowderInternalframe[] stackFrames = stack.toArray(new JpowderInternalframe[stack.size()]);
            stack.clear();

            //i = 1 - ignore the first element
            for (int i = 1; i < stackFrames.length; i++) {
                stack.push(stackFrames[i]);

            }
            numberOfFrames = stack.size();

        }
        stack.push(frame);
        numberOfFrames++;

    }

    /**
     * If the stack is empty return null otherwise return the last element that pushed on to the stack.
     *
     * @return stack.pop()
     */
    public JpowderInternalframe pop() {

        if (empty()) {

            return null;

        } else {

            numberOfFrames--;

            return stack.pop();
        }

    }

    /**
     * Get the size of the stack.
     * @return stack.size()
     */
    public int getStackSize() {
        return stack.size();
    }

    /**
     * Tests if this stack is empty.
     * @return stack.empty()
     */
    public boolean empty() {
        return stack.empty();
    }
}
