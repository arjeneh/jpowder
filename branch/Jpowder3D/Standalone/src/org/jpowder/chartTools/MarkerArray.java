/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jpowder.chartTools;

import java.util.ArrayList;

/**
 *
 * @author Arjeneh
 */
public class MarkerArray {

    public MarkesIcons[] createCountriesArray() {
        
         String[] citems = {
              "square,s",
            "circle,c",
            "diamond,d",
            "down_traingle,dt",
             "oval,o",
              "rectangle,r",       
            "triangle,t"
          
            
        };

        ArrayList<MarkesIcons> clist = new ArrayList<MarkesIcons>();
        for (String citem : citems) {

            String[] cdata = citem.split(",");
            clist.add(new MarkesIcons(citem,
                    getClass().getResource("/images/Markers/" +
                    cdata[0].toLowerCase()+
                    ".png")));
        }

        MarkesIcons[] carray = clist.toArray(new MarkesIcons[0]);
        return carray;

    }


}
