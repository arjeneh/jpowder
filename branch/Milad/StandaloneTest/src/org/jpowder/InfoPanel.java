package org.jpowder;

/**
 *
 * @author qyt21516
 */

public interface InfoPanel {

 /** Update information panel. For example when a JpowderInternalFrame is
  *  activated by the user this method is called to reflect that a new
  *  JpowderInternalFrame is in focus.
  */
  public void update();
}
