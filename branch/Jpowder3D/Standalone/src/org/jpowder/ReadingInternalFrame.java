/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder;

/**
 *
 * @author qyt21516
 */
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple internal frame demo.
 *
 */
public class ReadingInternalFrame extends ApplicationFrame {

    private JInternalFrame frame;
    private JDesktopPane desktopPane;
    private JMenuBar menuBar;
    private JMenu fileMenu;


    public ReadingInternalFrame(final String title) {
        super(title);
        desktopPane = new JDesktopPane();
        menuBar = new JMenuBar();

        // File Menu, F - Mnemonic
        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);


        // File->New, N - Mnemonic
        JMenuItem newMenuItem = new JMenuItem("Open");
        fileMenu.add(newMenuItem);
        this.setJMenuBar(menuBar);
        desktopPane.setPreferredSize(new Dimension(600, 400));
        getContentPane().add(desktopPane);
        desktopPane.setVisible(true);
        newMenuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JInternalFrame chart = ReadWorkSpace();
                desktopPane.add(chart);
                chart.setVisible(true);
            }
        });

    }

    public JInternalFrame ReadWorkSpace() {
        
        JInternalFrame serializedFrame = null;
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {

                String filename = chooser.getSelectedFile().getPath();
                FileInputStream buffer = new FileInputStream(filename);
                ObjectInput out = new ObjectInputStream(buffer);
                try {

                    serializedFrame = (JInternalFrame) out.readObject();

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ReadingInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                out.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Invalid file!",
                        "error", JOptionPane.ERROR_MESSAGE);
                
            }
        } else {
        }
        return serializedFrame;
    }

    public static void main(final String[] args) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());

        } catch (Exception j) {
            j.printStackTrace();
        }
        final ReadingInternalFrame demo = new ReadingInternalFrame("Internal Frame Demo");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }
}

