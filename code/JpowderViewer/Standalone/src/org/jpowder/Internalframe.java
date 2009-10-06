/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jpowder;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;


/**
 *
 * @author qyt21516
 */
public class Internalframe extends JInternalFrame {

  private JPanel chtpnl;

  public Internalframe(JPanel chtpnl){
    super();
    this.chtpnl=chtpnl;
    this.setClosable(true);
      this.setMaximizable(true);
      this.setResizable(true);
      this.add(chtpnl);
      this.setVisible(true);
  }
  /**
   * @return the chtpnl
   */
  public JPanel getChtpnl() {
    return chtpnl;
  }
  /**
   * @param chtpnl the chtpnl to set
   */
  public void setChtpnl(JPanel chtpnl) {
    this.chtpnl = chtpnl;
  }
}
