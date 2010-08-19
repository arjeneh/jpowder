/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.chartTools;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 * A test Class
 * @author Arjeneh
 */
public class ColorPanel extends JPanel implements MouseListener {

    public static Color highLightColor = new Color(246, 169, 30);

    public ColorPanel(Color color) {
        this.setBackground(color);
        this.setSize(10, 10);
        this.setPreferredSize(new Dimension(20, 20));
        this.addMouseListener(this);
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println(this.getBackground());
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        setBorder(new javax.swing.border.LineBorder(highLightColor, 2, true));
    }

    public void mouseExited(MouseEvent e) {
        setBorder(null);

    }


}
