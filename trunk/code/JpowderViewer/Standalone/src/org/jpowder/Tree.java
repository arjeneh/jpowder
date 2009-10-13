/*
 * 
 */
package org.jpowder;

/**
 *
 *
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;


public class Tree extends JPanel implements ActionListener {

    File[] rt;
    File drive;
    private DefaultComboBoxModel comboModel;
    TreeFiles model;
    File root = null;
    JTree tree;

    public Tree() {
        initComponents();
    }

    public void initComponents() {
        setLayout(new BorderLayout());
        panelB = new javax.swing.JPanel();
        panelB.setLayout(new BorderLayout());
        

        comboModel = new DefaultComboBoxModel();
        selectDrive = new JComboBox(comboModel);

        rt = File.listRoots();


        for (int i = 0; i < rt.length; i++) {
            drive = rt[i];
            comboModel.addElement(drive);
        }
        selectDrive.setModel(comboModel);

        selectDrive.addActionListener(this);
        selectDrive.setActionCommand("sel_drive");
        tree = new JTree();
        tscrollPane = new JScrollPane(tree);
        displayTree();

        panelB.add(selectDrive, BorderLayout.NORTH);
        panelB.add(tscrollPane, BorderLayout.CENTER);

        add(panelB, BorderLayout.NORTH);

    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals("sel_drive")) {
            displayTree();

        }
    }

    public void displayTree() {
        Object obj = comboModel.getSelectedItem();
        String str = obj.toString();
        File f = new File(str);

        model = new TreeFiles(f);
        tree.setModel(model);
       
    }
/**
 * this main method is just for testing purpose
 * @param args
 */
    public static void main(String args[]) {
        JFrame frame = new JFrame("File chooser");
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.add(new Tree());
        frame.pack();
        
        frame.setVisible(true);

    }
    public JComboBox selectDrive;
    public JPanel panelB;
    public JScrollPane tscrollPane;
}
