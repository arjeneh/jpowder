package org.jpowder;

import java.awt.Color;
/*
 * QuestionSetPanel.java
 *
 * Created on 05 May 2007, 19:44
 *
 * This class is for creating questions with radio buttons and questions.
 * It will be used b y QNairePanel which will iteratively build this class.
 */

/**
 *
 * @author Kreecha Puphaiboon
 */
public class QuestionSetPanel extends javax.swing.JPanel{
    
    private java.awt.GridBagConstraints gridBagConstraints;
    private javax.swing.ButtonGroup buttonGroup1 = new javax.swing.ButtonGroup();
    
    private javax.swing.JLabel question_lb = new javax.swing.JLabel();
    private javax.swing.JRadioButton rb1 = new javax.swing.JRadioButton();
    private javax.swing.JRadioButton rb2 = new javax.swing.JRadioButton();
    private javax.swing.JRadioButton rb3 = new javax.swing.JRadioButton();
    private javax.swing.JRadioButton rb4 = new javax.swing.JRadioButton();
    private javax.swing.JRadioButton rb5 = new javax.swing.JRadioButton();
    
    private String question;
    
    public QuestionSetPanel(String q){
        this.question = q;
        
        buttonGroup1.add(rb1);
        buttonGroup1.add(rb2);
        buttonGroup1.add(rb3);
                
        setLayout(new java.awt.GridBagLayout());
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153)));
        setMinimumSize(new java.awt.Dimension(800, 60));
        setPreferredSize(new java.awt.Dimension(800, 60));
        this.setAlignmentX(0.0f);
        
        this.setBackground(Color.WHITE);
  
        question_lb.setFont(new java.awt.Font("Courier", 0, 13));
        question_lb.setText(this.question);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 8, 0);
        add(question_lb, gridBagConstraints);
        
        buttonGroup1.add(rb1);
        rb1.setFont(new java.awt.Font("Courier", 0, 13));
        rb1.setSelected(true);
        rb1.setActionCommand("1");
        rb1.setText("Agree");
        rb1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rb1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rb1.setBackground(Color.WHITE);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        add(rb1, gridBagConstraints);
        
        buttonGroup1.add(rb2);
        rb2.setFont(new java.awt.Font("Courier", 0, 13));
        rb2.setActionCommand("2");
        rb2.setText("Undecided");
        rb2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rb2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rb2.setBackground(Color.WHITE);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        add(rb2, gridBagConstraints);
        
        buttonGroup1.add(rb3);
        rb3.setFont(new java.awt.Font("Courier", 0, 13));
        rb3.setActionCommand("3");
        rb3.setText("Disagree");
        rb3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rb3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rb3.setBackground(Color.WHITE);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        add(rb3, gridBagConstraints);
        
    }//   
}//