/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BalloonFrame.java
 *
 * Created on 30-Jun-2012, 13:16:21
 */
package org.jpowder.chartTools;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.jpowder.jfreechart.EditAnnotationFrame;
//import org.jpowder.jfreechart.EditAnnotationFrame.PointAnno;
import org.jpowder.jfreechart.PointAnno;
import org.jpowder.util.HashMapHelper;

/**
 *
 * @author Toshiba
 */
public class BalloonFrame extends javax.swing.JFrame {

    private PointAnno pa;
    private int refId;

    /** Creates new form BalloonFrame */
    public BalloonFrame() {
        initComponents();
    }

    public BalloonFrame(PointAnno pn) {
        this.pa = pn;
        this.refId = this.pa.getPointName();
        this.setTitle("Anno ID: " + String.valueOf(this.refId));
        
        initComponents();
        this.jTextArea1.setText(this.pa.getComment());
        Color c = new Color(255, 255, 204);//[255,255,204]
        this.getContentPane().setBackground(c);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        deleteBut = new javax.swing.JButton();
        editBut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 204));
        setResizable(false);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 204));
        jScrollPane1.setBorder(null);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setOpaque(false);

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setFont(new java.awt.Font("Arial", 0, 12));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("See how things?");
        jTextArea1.setAutoscrolls(false);
        jTextArea1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextArea1.setHighlighter(null);
        jTextArea1.setOpaque(false);
        jScrollPane1.setViewportView(jTextArea1);

        deleteBut.setForeground(new java.awt.Color(0, 0, 204));
        deleteBut.setText("<html><u>Delete</u><html>");
        deleteBut.setBorder(null);
        deleteBut.setBorderPainted(false);
        deleteBut.setContentAreaFilled(false);
        deleteBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButActionPerformed(evt);
            }
        });

        editBut.setForeground(new java.awt.Color(0, 0, 204));
        editBut.setText("<html><u>Edit</u><html>");
        editBut.setBorder(null);
        editBut.setBorderPainted(false);
        editBut.setContentAreaFilled(false);
        editBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(deleteBut, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(editBut, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteBut)
                    .addComponent(editBut))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButActionPerformed
        // TODO add your handling code here:
        EditAnnotationFrame enf = EditAnnotationFrame.getInstance();
        Map support = enf.getAnnoMap();
        System.out.println("Delete Found ID: " + HashMapHelper.getKeyByValue(support, this.pa) + "************");
        enf.removeAnnotation((Integer) HashMapHelper.getKeyByValue(support, this.pa));
        this.dispose();
}//GEN-LAST:event_deleteButActionPerformed

    /**
     * Save any change to the text and close the wondow.
     * @param evt
     */
    private void editButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButActionPerformed
        // TODO if some is remove then order is wrong:    
        EditAnnotationFrame enf = EditAnnotationFrame.getInstance();
        Map support = enf.getAnnoMap();
        //Map
        System.out.println("Edit Found ID: " + HashMapHelper.getKeyByValue(support, this.pa) + "************");
        enf.showAnnotation(this.pa);

}//GEN-LAST:event_editButActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new BalloonFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteBut;
    private javax.swing.JButton editBut;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
