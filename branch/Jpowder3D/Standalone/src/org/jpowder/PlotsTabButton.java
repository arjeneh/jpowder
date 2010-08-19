/* ===========================================================
 * This file is part of Jpowder, see <http://www.jpowder.org/>
 * ===========================================================
 *
 * Jpowder is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jpowder is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * ---------
 * PlotsTabButton.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder;

/**
 * Creating button to be added to the 2D and 3D tabs, which contains a desktop pane
 * 
 */
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;

/**
 * Component to be used as tabComponent;
 * Contains a JLabel to show the text and
 * a JButton to close the tab it belongs to
 */
public class PlotsTabButton extends JPanel {

    private final JTabbedPane tabbedPane;

    public PlotsTabButton(final JTabbedPane jtp) {
        //unset default FlowLayout' gaps
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
        if (jtp == null) {
            throw new NullPointerException("TabbedPane is null");
        }
        this.tabbedPane = jtp;
        setOpaque(false);

        //make JLabel read titles from JTabbedPane
        JLabel label = new JLabel() {

            @Override
            public String getText() {
                int i = jtp.indexOfTabComponent(PlotsTabButton.this);
                if (i != -1) {
                    return jtp.getTitleAt(i);
                }
                return null;
            }
        };

        add(label);
    
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
 
        JButton button = new TabButton();
        JButton butt = new TabButton();
        tabbedPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
//        add(butt);
        add(button);
        setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
        
    }

    private class TabButton extends JButton implements ActionListener {

        public TabButton() {
            int size = 18;
            setPreferredSize(new Dimension(size, size));
            setToolTipText("XXXX");
            setUI(new BasicButtonUI());
            setContentAreaFilled(false);
            setFocusable(true);
            setBorder(BorderFactory.createEtchedBorder());
            setBorderPainted(false);
            addMouseListener(buttonMouseListener);
            setRolloverEnabled(true);
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int i = tabbedPane.indexOfTabComponent(PlotsTabButton.this);
//            if (i != -1) {
//                pane.remove(i);
//            }
//            java.awt.Dimension[width=853,height=727]
//            final JFrame frm = new JFrame();
//            frm.add(tabbedPane.getComponentAt(i));
//            frm.setVisible(true);
////            frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frm.setSize(853, 727);
//           frm.addWindowListener(new WindowAdapter() {
//                @Override
//             public void windowClosing(WindowEvent e) {
//                  int i = tabbedPane.indexOfTabComponent(PlotsTabButton.this);
//                 tabbedPane.add(tabbedPane.getTitleAt(0),frm.getComponent(0));
//
//             }
//           });
           
   
        }

        //paint the cross
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            //shift the image for pressed buttons
            if (getModel().isPressed()) {
                g2.translate(1, 1);
            }
            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.BLACK);
            if (getModel().isRollover()) {
                g2.setColor(Color.RED);
            }
            int delta = 6;
            //cross
//            g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta - 1);
//            g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta - 1);
            //Square
            g2.drawRect(delta - 3, delta - 3, getWidth() - delta - 2, getHeight() - delta - 2);
            g2.drawLine(delta-3, getHeight()-2*delta-1, getWidth()-5, getHeight()-2*delta-1);
            g2.dispose();
        }
    }
    private final static MouseListener buttonMouseListener = new MouseAdapter() {

        @Override
        public void mouseEntered(MouseEvent e) {
            Component component = e.getComponent();
            if (component instanceof AbstractButton) {
                AbstractButton button = (AbstractButton) component;
                button.setBorderPainted(true);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            Component component = e.getComponent();
            if (component instanceof AbstractButton) {
                AbstractButton button = (AbstractButton) component;
                button.setBorderPainted(false);
            }
        }
    };
}

