/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.chartTools;

/**
 *
 * @author Arjeneh
 */
import java.awt.*;



import javax.swing.*;

public class MarkerGUITest extends JFrame {

    public MarkerGUITest(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 5, 5);
        layout.setAlignOnBaseline(true);
        getContentPane().setLayout(layout);

//        Country[] carray= createCountriesArray();
        MarkerArray array = new MarkerArray();
        MarkesIcons[] carray = array.createCountriesArray();


        JList list = new JList(carray);
//      list.setEnabled (false);
        list.setCellRenderer(new MarkerComboBoxRenderer(null));
        list.setVisibleRowCount(8);
        list.setSelectedIndex(0);
        getContentPane().add(new JScrollPane(list));

        JComboBox cb = new JComboBox(carray);
//      cb.setEnabled (false);
        cb.setRenderer(new MarkerComboBoxRenderer(cb));
        getContentPane().add(cb);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {

            public void run() {

                new MarkerGUITest("Countries");
            }
        };
        EventQueue.invokeLater(r);
    }
}
