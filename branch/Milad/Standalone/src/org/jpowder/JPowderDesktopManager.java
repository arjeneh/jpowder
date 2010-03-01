/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jpowder;

import javax.swing.DefaultDesktopManager;
import javax.swing.JInternalFrame;

/**
 *
 * @author qyt21516
 */
public class JPowderDesktopManager extends DefaultDesktopManager{

private JpowderInternalframe jpowderInternalframe;


  @Override
  public void closeFrame(JInternalFrame f){
    f=jpowderInternalframe;
    System.out.println("JPowderDesktopManager");
  }
  public boolean closing(){
  closeFrame(jpowderInternalframe);
    return true;
  }

//  @Override
//  public void maximizeFrame(JInternalFrame f){
//     f=jpowderInternalframe;
//     System.out.println("maximizeFrame");
//  }

 // @Override
 // public void minimizeFrame(JInternalFrame f){
 //   System.out.println("minimizeFrame");
 // }
}
