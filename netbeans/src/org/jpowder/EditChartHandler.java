package org.jpowder;

import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Vector;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author Kreecha Puphaiboon
 */
// TODO: need to implement finalize() due to JDialog bug.
// TODO: Headless implementation.

public class EditChartHandler implements ActionListener {
    
    private Container ref;//use as static utility
    private JPanel panelToDelete;//use as static utility
    
    private Vector vecPanel;
    private JPanel chartPanel;
    
    private JPowder jpowder;
    
    public EditChartHandler(){
    }
    
    public EditChartHandler(JPowder m){
        this.vecPanel = m.getChartList();
        this.chartPanel = m.getChartPanel();
        this.jpowder = m;
    }
    
    //This array of object is put into JOptionPane, since it does not allow Vector
    public Object[] insertChartNames(){
        int itsNum =  jpowder.getChartList().size();
        
        String[] chartArray  = new String[itsNum];
        for (int dex = 0; dex < chartArray.length; dex++) {
            LineChartPanel jf = (LineChartPanel) jpowder.getChartList().get(dex);
            chartArray[dex] = jf.getName();
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
            JOptionPane.showMessageDialog(source, "No chart to edit.");
            return;
        }
        
        Point displayLocation = source.getLocationOnScreen();
        displayLocation.x = displayLocation.x - 200;
        
        //Bring up a dialog box for selection.
        EditChartDialog dialog = new EditChartDialog((String[]) chartNames,jpowder,
                displayLocation);
    }
}//end DeleteChartHandler


//throws HeadlessException
class EditChartDialog extends JFrame   {
    
    private String[] chartNames;
    private int selectedIndex;
    private JComboBox petList;
    private JPowder jpowder;
    private JDialog dialog;
    private JOptionPane op;
    
    /**
     * DeleteChartDialog, constructor.
     *
     * Let the user select a chart to be edited.
     * @param theStrings
     * @param theMainApplet
     * @param displayLocation
     */
    
    public EditChartDialog(String[] theStrings, JPowder theMainApplet, Point displayLocation) {
        this.chartNames = theStrings;
        this.jpowder = theMainApplet;
        
        final JComboBox chartNameList = new JComboBox(this.chartNames);
        chartNameList.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JComboBox cb = (JComboBox)e.getSource();
                String chartName = (String)cb.getSelectedItem();
                selectedIndex = cb.getSelectedIndex();
                System.out.println("Combobox index " + chartName + " was selected.");
            }
        });
        
        JButton[] options = new JButton[2];
        options[0] = new JButton("OK");
        options[0].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                //TODO: Move into EditChartFrame.java
                try {
                    //----------Copy the chart-------------------
                    LineChartPanel plot_2 = (LineChartPanel)jpowder.getLineChart(selectedIndex);
                    final JFreeChart plot_copy = (JFreeChart)plot_2.getChart().clone();
                            
                    //Thread safe by seperating it in case editing and modification.
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            EditChartFrame frame = new EditChartFrame(plot_copy);
                        }
                    });
                   
                    //time for the dialog to disappear.*/
                    setVisible(false);
                    dispose();
                } catch (Exception ex){
                    ex.printStackTrace();
                }//end catch
            }//end actionPerformed
        });//end addActionListener for ok button.
        
        options[1] = new JButton( "Cancel" );
        options[1].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("You selected cancel.");
                setVisible(false);
                dispose();
            }
        });
        
        Object[] msg = {"Which chart would you like to edit?",chartNameList};//this text can be passed in the constructor
        
        JOptionPane op = new JOptionPane(msg,
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION,
                null,
                options);
        
        JDialog dialog = op.createDialog(this, "Please select a chart to edit.");//this text also can be passed in the constructor
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocation(displayLocation);
        dialog.setVisible(true);
        dialog.pack();
        
    }//DeleteChartDialog constructor
}//end DeleteChartDialog



