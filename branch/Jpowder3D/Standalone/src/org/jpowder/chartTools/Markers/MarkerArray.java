/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.chartTools.Markers;

import java.util.ArrayList;

/**
 *
 * @author Arjeneh
 */
public class MarkerArray {

    public MarkesIcons[] createCountriesArray() {

        String[] markers = {
            "Square,s",
            "Circle,c",
            "Diamond,d",
            "DownTraingle,dt",
            "Oval,o",
            "Rectangle,r",
            "UpTraingle,t",
            "RegularCross,rc",
            "DiagnalCross,dc",
            "LeftLine,lf",
            "RightLine,rl"
        };

        ArrayList<MarkesIcons> markesIconses = new ArrayList<MarkesIcons>();

        for (String citem : markers) {

            String[] cdata = citem.split(",");
            markesIconses.add(new MarkesIcons(cdata[0],
                    getClass().getResource("/images/Markers/" +
                    cdata[0].toLowerCase() +
                    ".png")));
        }

        MarkesIcons[] carray = markesIconses.toArray(new MarkesIcons[0]);
        return carray;

    }
}
