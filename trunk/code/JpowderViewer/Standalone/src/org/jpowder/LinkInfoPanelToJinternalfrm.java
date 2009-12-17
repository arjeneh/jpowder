/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder;

/**
 *
 * @author qyt21516
 */
public class LinkInfoPanelToJinternalfrm {

  private InfoPanel infoPanelInfocus = null;
  private JpowderInternalframe frameInfocus = null;

  public void update(JpowderInternalframe frame) {
    this.frameInfocus = frame;
    if (infoPanelInfocus != null) {
      this.infoPanelInfocus.update(frame);
    }
  }

  public void update(InfoPanel panel) {
    this.infoPanelInfocus = panel;
  }
}
