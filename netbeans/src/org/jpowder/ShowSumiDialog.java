package org.jpowder;

import org.jpowder.util.ScreenUtil;

/**
 * "ShowSumiDialog" implements a dialog which asks the user to confirm
 * their score and send it to me by email; kreecha_pu_at_yahoo.com.
 *
 * @author Kreecha Puphaiboon
 * @version 1.0
 * @since 25/5/2007
 */
public class ShowSumiDialog extends javax.swing.JFrame {
    //TODO: Pop up in a better position.
    
    private javax.swing.JFrame   frame    = null;
    private String score                  = null;
    
    private javax.swing.JPanel              butPanel;
    private javax.swing.JButton             cancelBut;
    private javax.swing.JLabel              instruction_lb;
    private javax.swing.JButton             okBut;
    private javax.swing.JPanel              resultPanel;
    private javax.swing.JScrollPane         result_sp;
    private javax.swing.JTextArea           result_ta;    
    
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
        
        resultPanel = new javax.swing.JPanel();
        result_sp = new javax.swing.JScrollPane();
        result_ta = new javax.swing.JTextArea();
        butPanel = new javax.swing.JPanel();
        okBut = new javax.swing.JButton();
        cancelBut = new javax.swing.JButton();
        instruction_lb = new javax.swing.JLabel();
        
        setLayout(new java.awt.GridBagLayout());
        
        resultPanel.setPreferredSize(new java.awt.Dimension(200, 100));
        resultPanel.setLayout(new java.awt.GridBagLayout());
        
        result_sp.setMinimumSize(new java.awt.Dimension(400, 23));
        result_sp.setPreferredSize(new java.awt.Dimension(400, 100));
        
        result_ta.setText(score);//put score in the text.
        result_ta.setColumns(200);
        result_ta.setRows(5);
        result_sp.setViewportView(result_ta);
        
        resultPanel.add(result_sp, new java.awt.GridBagConstraints());
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        add(resultPanel, gridBagConstraints);
        
        butPanel.setLayout(new java.awt.GridBagLayout());
        
        okBut.setText("Submit");
        okBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButActionPerformed(evt);
            }
        });
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        butPanel.add(okBut, gridBagConstraints);
        
        cancelBut.setText("Cancel");
        cancelBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButActionPerformed(evt);
            }
        });
        butPanel.add(cancelBut, new java.awt.GridBagConstraints());
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(butPanel, gridBagConstraints);
        
        instruction_lb.setText("Please email the information below to kreecha_pu@yahoo.com");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        add(instruction_lb, gridBagConstraints);
    }
    
    private void cancelButActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        dispose();
    }
    
    private void okButActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        dispose();
    }
    
    /**
     * "ShowSumiDialog( Frame, boolean )"
     * @param strScore the computed score strings of user format as:
     * 1111322123 1231231231 123111222333 1111322123 1231231231
     */
    public ShowSumiDialog(String strScore) {
        score = strScore;
        
        initComponents();
        
        ScreenUtil.centerFrame(this);
        setTitle( "UI Evaluation Collector" );
        setSize( 500, 240 );
        setVisible( true );
        
        WindowInterface wi = new WindowInterface();
        this.addWindowListener( wi );
        
    }
    /**
     * "WindowInterface" class for handling system commands.
     */
    class WindowInterface extends java.awt.event.WindowAdapter {
        /**
         * "windowClosing( WindowEvent )" processes a window closing system command.
         *
         *
         * @param event
         */
        public void windowClosing( java.awt.event.WindowEvent event ) {
            Object object = event.getSource();
            
            if( object == ShowSumiDialog.this )
                dispose();
        }// windowClosing
    }//WindowInterface
}
