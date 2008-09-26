/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder;

import org.jfree.chart.renderer.xy.XYErrorRenderer;

/**
 * 
 * @author Toshiba
 */
public class XYE_PopupMenu extends XY_PopupMenu {

    private javax.swing.JCheckBoxMenuItem offErrorBar;
    private org.jfree.chart.plot.XYPlot plot;
    private javax.swing.JMenu changePlotStyle;

    public XYE_PopupMenu(org.jfree.chart.ChartPanel chartPanel) {
        super(chartPanel);

        //Change Plot Style
        changePlotStyle = super.getChangePlotStyle();
        plot = super.getPlot();

        // TODO: Throw Casting exception
        offErrorBar = new javax.swing.JCheckBoxMenuItem("turn off errorbars", true);
        offErrorBar.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent event) {
                //get the plot, get renderer, set to what i want.
                org.jfree.chart.renderer.xy.XYErrorRenderer renderer = (XYErrorRenderer) plot.getRenderer();
                boolean status = renderer.getDrawYError();
                System.out.println("Render Error bar is " + status);
                renderer.setDrawYError(!status);//show opposite Y error bar.

            }
        });


     
        //ADD THESE THREE ONTO changePlotStyle
        changePlotStyle.add(offErrorBar);
        

    }
}
