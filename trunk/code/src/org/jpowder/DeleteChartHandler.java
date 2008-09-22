package org.jpowder;

import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Vector;


/*
 * DeleteChartHandler.java
 *
 * Created on 24 April 2007, 12:24
 *
 * @param MainApplet pass in references
 */
import javax.swing.JButton;

/**
 *
 * @author Kreecha Puphaiboon
 */
// TODO: need to implement finalize() due to JDialog bug.
// TODO: Headless implementation.

public class DeleteChartHandler implements ActionListener {
    
    private Container ref;//use as static utility
    private JPanel panelToDelete;//use as static utility
    
    private Vector vecPanel;
    private JPanel chartPanel;
    
    private JPowder jpowder;
    
    public DeleteChartHandler(){
    }
    
    public DeleteChartHandler(JPowder m){
        this.vecPanel = m.getChartList();
        this.chartPanel = m.getChartPanel();
        this.jpowder = m;
    }
    
    //This array of object is put into JOptionPane, since it does not allow Vector
    public Object[] insertChartNames(){
        int itsNum =  jpowder.getChartList().size();
        
        String[] chartArray  = new String[itsNum];
        for (int dex = 0; dex < chartArray.length; dex++) {
            JPowderChartPanel jf = (JPowderChartPanel) jpowder.getChartList().get(dex);
            chartArray[dex] = jf.getName();//another way to add number to it + "#" + dex;
        }

        return chartArray;
    }
    
    //when the user select, we delete the object reference.
    //check if no chart, we alert.
    //otherwise bring up an input dialog.
    public void actionPerformed(ActionEvent actionEvent) {
        Component source = (Component) actionEvent.getSource();
        Object[] chartNames = insertChartNames();
        
        //check if no chart, we alert.
        if((chartNames==null) || (chartNames.length == 0)){
            JOptionPane.showMessageDialog(source, "No chart to delete.");
            return;
        }
        
        Point displayLocation = source.getLocationOnScreen();
        displayLocation.x = displayLocation.x - 200;
        
        //Bring up a dialog box for selection.
        DeleteChartDialog dialog = new DeleteChartDialog((String[]) chartNames,jpowder,
                displayLocation);
    }

}//end DeleteChartHandler


//throws HeadlessException
class DeleteChartDialog extends JFrame   {
    
    private String[] petNames;
    private int selectedIndex;
    private JComboBox petList;
    private JPowder jpowder;
    private JDialog dialog;
    private JOptionPane op;
    
    /**
     * DeleteChartDialog, constructor.
     *
     * Let the user select a chart to be deleted.
     *
     * @param theStrings - the title of the dialog
     * @param theMainApplet - all data from JPowder 
     * @param displayLocation - where the dialog display on the screen
     * 
     * 
     **/
    
    public DeleteChartDialog(String[] theStrings, JPowder theMainApplet, Point displayLocation) {
        this.petNames = theStrings;
        this.jpowder = theMainApplet;
        
        final JComboBox petList = new JComboBox(this.petNames);
        petList.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JComboBox cb = (JComboBox)e.getSource();
                String petName = (String)cb.getSelectedItem();
                selectedIndex = cb.getSelectedIndex();
                System.out.println("Combobox index " + petName + " was selected.");
            }
        });
        
        JButton[] options = new JButton[2];
        options[0] = new JButton("OK");
        options[0].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("You selected chart index number " + petList.getSelectedIndex());
                //jpowder.deleteChartList(selectedIndex);//the KEY CALLING MAINAPPLET TO DELETE CHART
                setVisible(false);
                dispose();
            }
        });
        
        options[1] = new JButton( "Cancel" );
        options[1].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("You selected cancel.");
                setVisible(false);
                dispose();
            }
        });
        
        Object[] msg = {"Which chart would you like to delete?", petList};//this text can be passed in the constructor
        
        JOptionPane op = new JOptionPane(msg,
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION,
                null,
                options);
        
        JDialog dialog = op.createDialog(this, "Please select a chart to be deleted.");//this text also can be passed in the constructor
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocation(displayLocation);
        dialog.setVisible(true);
        dialog.pack();
        
    }//DeleteChartDialog constructor
}//end DeleteChartDialog



