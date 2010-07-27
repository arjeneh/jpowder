/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ColorTable.java
 *
 * Created on 08-Jun-2010, 12:15:32
 */
package org.jpowder.chartTools;

//import com.sun.awt.AWTUtilities;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.Color;
import java.awt.geom.RoundRectangle2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JColorChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author qyt21516
 */
public class ColorTable extends javax.swing.JFrame {

    /** Creates new form ColorTable */
    public ColorTable() {
        initComponents();
//           AWTUtilities.setWindowShape(this, new
//                   RoundRectangle2D.Double(0, 0, this.getWidth(), this.getHeight(), 15, 15));
//Samples Colours
        ColorPanel cp1 = new ColorPanel(new Color(0, 0, 102));
        ColorPanel cp5 = new ColorPanel(new Color(153, 0, 0));
        ColorPanel cp9 = new ColorPanel(new Color(0, 102, 51));
        ColorPanel cp13 = new ColorPanel(new Color(102, 102, 0));
        ColorPanel cp25 = new ColorPanel(new Color(128, 79, 5));
        ColorPanel cp29 = new ColorPanel(new Color(51, 51, 51));
        ColorPanel cp33 = new ColorPanel(new Color(0, 102, 102));
        ColorPanel cp37 = new ColorPanel(new Color(102, 0, 102));


        ColorPanel cp2 = new ColorPanel(new Color(0, 0, 153));
        ColorPanel cp6 = new ColorPanel(new Color(204, 0, 0));
        ColorPanel cp10 = new ColorPanel(new Color(51, 153, 0));
        ColorPanel cp14 = new ColorPanel(new Color(153, 153, 0));
        ColorPanel cp26 = new ColorPanel(new Color(159, 110, 36));
        ColorPanel cp30 = new ColorPanel(new Color(102, 102, 102));
        ColorPanel cp34 = new ColorPanel(new Color(0, 153, 153));
        ColorPanel cp38 = new ColorPanel(new Color(153, 0, 153));


        ColorPanel cp3 = new ColorPanel(new Color(102, 102, 255));
        ColorPanel cp7 = new ColorPanel(new Color(255, 51, 51));
        ColorPanel cp11 = new ColorPanel(new Color(0, 255, 0));
        ColorPanel cp15 = new ColorPanel(new Color(204, 204, 0));
        ColorPanel cp27 = new ColorPanel(new Color(247, 162, 35));
        ColorPanel cp31 = new ColorPanel(new Color(153, 153, 153));
        ColorPanel cp35 = new ColorPanel(new Color(51, 204, 255));
        ColorPanel cp39 = new ColorPanel(new Color(153, 0, 255));



        ColorPanel cp4 = new ColorPanel(new Color(204, 204, 255));
        ColorPanel cp8 = new ColorPanel(new Color(255, 102, 102));
        ColorPanel cp12 = new ColorPanel(new Color(153, 255, 153));
        ColorPanel cp24 = new ColorPanel(new Color(255, 255, 153));
        ColorPanel cp28 = new ColorPanel(new Color(207, 206, 147));
        ColorPanel cp32 = new ColorPanel(new Color(204, 204, 204));
        ColorPanel cp36 = new ColorPanel(new Color(204, 255, 255));
        ColorPanel cp40 = new ColorPanel(new Color(255, 204, 255));









//Main Colours
        ColorPanel cp16 = new ColorPanel(Color.BLUE);
        ColorPanel cp17 = new ColorPanel(Color.RED);
        ColorPanel cp18 = new ColorPanel(Color.GREEN);
        ColorPanel cp19 = new ColorPanel(Color.YELLOW);
        ColorPanel cp20 = new ColorPanel(Color.ORANGE);
        ColorPanel cp21 = new ColorPanel(Color.BLACK);
        ColorPanel cp22 = new ColorPanel(Color.CYAN);
        ColorPanel cp23 = new ColorPanel(Color.MAGENTA);




        colorPaneLlist.add(cp1);
        colorPaneLlist.add(cp5);
        colorPaneLlist.add(cp9);
        colorPaneLlist.add(cp13);
        colorPaneLlist.add(cp25);
        colorPaneLlist.add(cp29);
        colorPaneLlist.add(cp33);
        colorPaneLlist.add(cp37);

        colorPaneLlist.add(cp2);
        colorPaneLlist.add(cp6);
        colorPaneLlist.add(cp10);
        colorPaneLlist.add(cp14);
        colorPaneLlist.add(cp26);
        colorPaneLlist.add(cp30);
        colorPaneLlist.add(cp34);
        colorPaneLlist.add(cp38);


        colorPaneLlist.add(cp3);
        colorPaneLlist.add(cp7);
        colorPaneLlist.add(cp11);
        colorPaneLlist.add(cp15);
        colorPaneLlist.add(cp27);
        colorPaneLlist.add(cp31);
        colorPaneLlist.add(cp35);
        colorPaneLlist.add(cp39);


        colorPaneLlist.add(cp4);
        colorPaneLlist.add(cp8);
        colorPaneLlist.add(cp12);
        colorPaneLlist.add(cp24);
        colorPaneLlist.add(cp28);
        colorPaneLlist.add(cp32);
        colorPaneLlist.add(cp36);
        colorPaneLlist.add(cp40);



        mainColourPanel.add(cp16);
        mainColourPanel.add(cp17);
        mainColourPanel.add(cp18);
        mainColourPanel.add(cp19);
        mainColourPanel.add(cp20);
        mainColourPanel.add(cp21);
        mainColourPanel.add(cp22);
        mainColourPanel.add(cp23);




        this.setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        colorPaneLlist = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        mainColourPanel = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(204, 204, 255));
        setForeground(java.awt.Color.darkGray);
        setResizable(false);
        setUndecorated(true);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        colorPaneLlist.setLayout(new java.awt.GridLayout(4, 8, 5, 5));

        jButton1.setText("More Options...");
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel1.setText("           Main Colours");

        mainColourPanel.setLayout(new java.awt.GridLayout(1, 8, 5, 5));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(mainColourPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(colorPaneLlist, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addComponent(mainColourPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(colorPaneLlist, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Color c = JColorChooser.showDialog(rootPane, null, Color.yellow);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
    this.setVisible(false);
        System.out.println("CLICKEDDDDDDDDDDDDDDDDDDD");
    }//GEN-LAST:event_formMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    UIManager.setLookAndFeel(new WindowsLookAndFeel());
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(ColorTable.class.getName()).log(Level.SEVERE, null, ex);
                }
//                 JFrame.setDefaultLookAndFeelDecorated(true);
                ColorTable colorTable = new ColorTable();

//                colorTable.getLayeredPane().getComponent(1).setPreferredSize(new Dimension(0,0));
             
//                colorTable.setLocation(200, 200);
                colorTable.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel colorPaneLlist;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel mainColourPanel;
    // End of variables declaration//GEN-END:variables
}
