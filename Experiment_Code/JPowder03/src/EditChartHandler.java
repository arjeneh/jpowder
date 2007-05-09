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
import javax.swing.JScrollPane;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;


/*
 * DeleteChartHandler.java
 *
 * Created on 24 April 2007, 12:24
 *
 * @param MainApplet pass in references
 */

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
    
    private MainApplet mainApplet;
    
    public EditChartHandler(){
    }
    
    public EditChartHandler(MainApplet m){
        this.vecPanel = m.getChartList();
        this.chartPanel = m.getChartPanel();
        this.mainApplet = m;
    }
    
    //This array of object is put into JOptionPane, since it does not allow Vector
    public Object[] insertChartNames(){
        int itsNum =  mainApplet.getChartList().size();
        
        
        
        String[] chartArray  = new String[itsNum];
        for (int dex = 0; dex < chartArray.length; dex++) {
            LineChartPanel jf = (LineChartPanel) mainApplet.getChartList().get(dex);
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
        EditChartDialog dialog = new EditChartDialog((String[]) chartNames,
                mainApplet,
                displayLocation);
    }
}//end DeleteChartHandler


//throws HeadlessException
class EditChartDialog extends JFrame   {
    
    private String[] petNames;
    private int selectedIndex;
    private JComboBox petList;
    private MainApplet mainApplet;
    private JDialog dialog;
    private JOptionPane op;
    
    /**
     * DeleteChartDialog, constructor.
     *
     * Let the user select a chart to be deleted.
     *
     * @param theStrings, the message for the dialog box to be selected.
     * @param theMainApplet, mainApplet to delete some charts.
     * @param displayLocation, where to appear.
     **/
    
    public EditChartDialog(String[] theStrings, MainApplet theMainApplet, Point displayLocation) {
        this.petNames = theStrings;
        this.mainApplet = theMainApplet;
        
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
            public void actionPerformed(ActionEvent event){
                System.out.println("You selected chart index number " + petList.getSelectedIndex());
                
                //------ DO AFTER CLICK OK ----------------
                try {
                    //mainApplet.deleteChartList(selectedIndex);//the KEY CALLING MAINAPPLET TO DELETE CHART
                    //setVisible(false);
                    //dispose();
                    //----------Copy the chat-------------------
                    javax.swing.JPanel container = (javax.swing.JPanel)mainApplet.getChartPanel();
                    int counter = container.getComponentCount();
                    System.out.println("The number of ChartPanel in here is " + counter);
                    
                    //TODO: Loop to find the specified component in the panel.
                    /*JFreeChart plot_2 = (JFreeChart) chartMouseEvent.getChart().clone();
                    JFrame frame = new JFrame("Edit chart");
                    JScrollPane pane = new JScrollPane();
                    
                    ChartPanel chartPanel_1 = new ChartPanel(plot_2);
                    pane.setViewportView(chartPanel_1);
                    pane.setPreferredSize(new java.awt.Dimension( 300, 120 ) );
                    frame.getContentPane().add(pane, java.awt.BorderLayout.CENTER);
                    frame.setPreferredSize(new java.awt.Dimension(600, 370));
                    frame.setSize(800,600);
                    //frame.setLocationRelativeTo(this);
                    frame.setVisible(true);*/
                    
                    //------------------------------------------
                    //Redraw the frame
                    //LineChartPanel cop = (LineChartPanel) realChart.clone();
                    //editChartPanel.addChartToBigChartPanel(cop);
                    //editChartPanel.revalidate();
                    //cl.show(cardPanel, EDIT_CHART_PANEL);
                    //super.repaint();
                
                } catch (Exception ex){
                    ex.printStackTrace();
                }
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
        
        Object[] msg = {"Which chart would you like to edit?", petList};//this text can be passed in the constructor
        
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



