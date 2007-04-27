import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.net.URL;

class MyListDataListener implements ListDataListener {
    
    private JButton[] buttons;
    static private String newline = "\n";
    
    //listModel.addListDataListener(new MyListDataListener());
    MyListDataListener(JButton[] enable){
        this.buttons = enable;
    }
    
    public void setEnable(){
        for(int i = 0; i < buttons.length; i++){
            buttons[i].setEnabled(true);
        }
    } 
    
     public void setDisable(){
        for(int i = 0; i < buttons.length; i++){
            buttons[i].setEnabled(false);
        }
    }  
    
    public void contentsChanged(ListDataEvent e) {
        System.out.println("contentsChanged: " + e.getIndex0() +  ", " + e.getIndex1() + newline);
        
        JList listy = (JList)e.getSource();
        
        System.out.println("contentsChanged: " + listy.getModel().getSize());
        
        if ( listy.getModel().getSize() <= 0) 
            setDisable();

    }
    
    public void intervalAdded(ListDataEvent e) {
        System.out.println("intervalAdded: " + e.getIndex0() +  ", " + e.getIndex1() + newline);
        setEnable();
    }
    
    public void intervalRemoved(ListDataEvent e) {
        System.out.println("intervalRemoved: " + e.getIndex0() +  ", " + e.getIndex1() + newline);
        DefaultListModel listy = (DefaultListModel)e.getSource();
   
        if ( listy.getSize() <= 0) 
            setDisable();
        
    }
}
