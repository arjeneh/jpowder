package org.jpowder;

import java.util.Stack;

/**
 *
 * @author Dan Badham.
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
