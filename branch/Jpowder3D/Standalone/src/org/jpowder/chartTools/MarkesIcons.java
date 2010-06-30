/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jpowder.chartTools;

import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author Arjeneh
 */
public class MarkesIcons {

    private String name;
    private ImageIcon markerIcon;
    private URL path;

    public MarkesIcons(String name, URL path) {
        this.name = name;
        this.path = path;
    }

   public String getName() {
        return name;
    }

    public ImageIcon getMarkerIcon() {


        if (markerIcon == null) {
            markerIcon = new ImageIcon(path);

        }

        return markerIcon;
    }


}
