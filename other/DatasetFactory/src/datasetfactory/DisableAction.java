/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datasetfactory;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Kreecha
 */
public class DisableAction extends AbstractAction {

    /**
    Constructs a color action.
    @param name the name to show on the button
    @param icon the icon to display on the button
    @param c the background color 
     */
    public DisableAction(String name, Icon icon, Color c) {
        this.putValue(Action.NAME, name);
        this.putValue(Action.SMALL_ICON, icon);
        this.putValue(Action.SHORT_DESCRIPTION, "Set panel color to " + name.toLowerCase());
        this.putValue("color", c);
    }

    public DisableAction(String name, Object c) {
        this.putValue(Action.NAME, name);
        this.putValue(Action.SHORT_DESCRIPTION, "Set panel color to " + name.toLowerCase());
        this.putValue("color", c);
    }

    public void actionPerformed(ActionEvent arg0) {
        Color c = (Color) getValue("color");
    //setBackground(c);
    }
}
