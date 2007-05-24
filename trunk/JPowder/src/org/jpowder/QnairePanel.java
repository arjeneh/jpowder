package org.jpowder;

import java.awt.Component;
import java.awt.Container;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/*
 * QnairePanel.java
 *
 * Created on 01 May 2007, 16:25
 *
 * This class is called from LogPanel.java for Usability Questions.
 *
 * It calls a txt file which has n questions
 * then build the n question (QuestionSetPanel).java to build30 GUI.
 *
 */

/**
 *
 * @author  Kreecha Puphaiboon
 */
public class QnairePanel extends javax.swing.JPanel {
    
    private java.util.ArrayList<String> questions = null;
    private volatile static java.util.Vector answers = new java.util.Vector();
    private static int questionSize;
    
    /** Creates new form QnairePanel */
    public QnairePanel() {
        questions = getQuestionsFromFile();
        questionSize = questions.size();
        
        initComponents();
        this.setAlignmentX(0.0f);
        
        for(int x = 0; x < questions.size(); x++){
            QuestionSetPanel questionSet = new QuestionSetPanel(questions.get(x));
            questionSet.setAlignmentX(QuestionSetPanel.LEFT_ALIGNMENT);
            containerPanel.add(questionSet);
            
        }//for
    }//QnairePanel
    
    public ArrayList<String> getQuestionsFromFile(){
        ArrayList<String> dummy = new ArrayList<String>();
        try {
            InputStream stream = this.getClass().getClassLoader().getResourceAsStream("data/qnaire.txt");
            InputStreamReader in = new InputStreamReader(stream);
            BufferedReader readIn = new BufferedReader(in);
            
            String word;
            // reading text
            while ((word = readIn.readLine()) != null) {
                dummy.add(word);
            }
            readIn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dummy;
    }//getQuestionsFromFile
    
//recursively collect the radio button selected.
    public static void getSelectedRadio(final Container ref) {
        Component[] nComponents = ref.getComponents();
        for (int i = 0; i < nComponents.length; i++) {
            if(nComponents[i] instanceof Container) {
                if(nComponents[i] instanceof JPanel) {
                    JPanel jPanel = (JPanel) nComponents[i];
                    Component[] panelComponents = jPanel.getComponents();// get components in the panel.
                    for (int j = 0; j < panelComponents.length; j++) {
                        if(panelComponents[j] instanceof JRadioButton) {                       
                            JRadioButton jrb = (JRadioButton)panelComponents[j];
                            if(jrb.isSelected()){
                                if (answers.size() >= questionSize){
                                    answers.removeAllElements();
                                }
                                answers.add(jrb.getActionCommand());
                            }
                        } //end if JRadioButton
                    }//end for
                    getSelectedRadio((Container)nComponents[i]); //move recursively through the Components and Sub-Containers
                }//if panel
            }//if
        }//for
    }//end getSelectedRadio
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        butPanel = new javax.swing.JPanel();
        submit_btn = new javax.swing.JButton();
        question_sp = new javax.swing.JScrollPane();
        containerPanel = new javax.swing.JPanel();
        request_tf = new javax.swing.JTextArea();

        setMinimumSize(new java.awt.Dimension(800, 500));
        setPreferredSize(new java.awt.Dimension(800, 500));
        setLayout(new java.awt.GridBagLayout());

        submit_btn.setText("Submit");
        submit_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submit_btnActionPerformed(evt);
            }
        });
        butPanel.add(submit_btn);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(butPanel, gridBagConstraints);

        question_sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        question_sp.setMinimumSize(new java.awt.Dimension(780, 320));
        question_sp.setPreferredSize(new java.awt.Dimension(780, 320));

        containerPanel.setMaximumSize(new java.awt.Dimension(800, 4400));
        containerPanel.setMinimumSize(new java.awt.Dimension(800, 4400));
        containerPanel.setPreferredSize(new java.awt.Dimension(800, 4400));
        containerPanel.setLayout(new javax.swing.BoxLayout(containerPanel, javax.swing.BoxLayout.Y_AXIS));
        question_sp.setViewportView(containerPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(question_sp, gridBagConstraints);

        request_tf.setBackground(javax.swing.UIManager.getDefaults().getColor("tab_sel_bottom_border"));
        request_tf.setColumns(100);
        request_tf.setEditable(false);
        request_tf.setFont(new java.awt.Font("Tahoma", 0, 11));
        request_tf.setLineWrap(true);
        request_tf.setRows(5);
        request_tf.setText("This inventory has fifty statements. Please answer every one of them. Against each statement there are three boxes.\n\nYou should mark the first box if you generally AGREE with the statement. Mark the central box if you are UNDECIDED, can\u2019t make up your mind, or if the statement has no relevance to your software or to your situation. Mark the right box if you generally DISAGREE with the statement.\n\nIn marking the left or right box you are not necessarily indicating strong agreement or disagreement but just your general feeling most of the time.\n");
        request_tf.setWrapStyleWord(true);
        request_tf.setMinimumSize(new java.awt.Dimension(780, 95));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        add(request_tf, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    //get what user selected, if not being canceled.
    
    private void submit_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submit_btnActionPerformed
        getSelectedRadio(containerPanel);
        
        try {
            java.io.File file = new java.io.File("output.txt");
            
            // Create file if it does not exist
            boolean success = file.createNewFile();
            
            if (success) {
                ReadWriteFileUtil.setContents(file, makeStringPattern(answers.toString() ) );
                // File did not exist and was created
            } else {
                // File already exists
                ReadWriteFileUtil.setContents(file, makeStringPattern(answers.toString() ) );
            }
        } catch (IOException e) {
        }
    }//GEN-LAST:event_submit_btnActionPerformed
    
    //for output the binary matrices
    public String makeStringPattern(String str){
        String tmp = str.replaceAll(", " , "");
        String noOpen = removeChar(tmp,'[');
        String noEnd = removeChar(noOpen,']');
        
        String string = spacer(noEnd, 5);
        
        return string;
    }//makeStringPattern
    
    //add a space as a seperator
    public String spacer(String str, int atPos){
        String out = "";
        int stopper = 5;
        
        if(str != null){ 
            for(int i = 0; i < str.length(); i++){
                int r = i % atPos; // r = 4
                if ((r == 0) && (i !=0)){
                    out = out + " ";
                    out = out + str.charAt(i); 
                } else{
                    out = out + str.charAt(i); 
                }
                                     
            }
        }
        return out;
    }//spacer    
    
    public String removeChar(String s, char c) {
        String r = "";
        for (int i = 0; i < s.length(); i ++) {
            if (s.charAt(i) != c)
                r += s.charAt(i);
        }
        return r;
    }//removeChar
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel butPanel;
    private javax.swing.JPanel containerPanel;
    private javax.swing.JScrollPane question_sp;
    private javax.swing.JTextArea request_tf;
    private javax.swing.JButton submit_btn;
    // End of variables declaration//GEN-END:variables
    
}

