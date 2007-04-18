/*
 * LoadFilePanel.java
 *
 * Created on 22 February 2007, 10:03
 */

/**
 *
 * @author  regtrain
 */
public class LoadFilePanel extends javax.swing.JPanel {
    
    private javax.swing.JLabel load_lb;
    private javax.swing.JComboBox file_cmb;
    private java.util.Vector fileList;
    
    //Iterator i = fileList.iterator();
    //while (i.hasNext()) {
    //  System.out.println(i.next());
    //}
    
    public LoadFilePanel() {
        fileList = this.getFiles("http://elc.kbu.ac.th/kreecha/java/PowderDataTable/", "xye");
        initComponents();
    }
    
    public java.util.Vector getFiles(String directory, String extension)    {
    	java.util.Vector names = new java.util.Vector();
        
        try {	            
            java.io.File f = new java.io.File(directory);
            boolean flag =  f.isDirectory();
            
            if(flag){
            	
                java.io.File fs[] = f.listFiles();
                for(int i=0;i<fs.length;i++) {
                    if(!fs[i].isDirectory())
                    {
                        String filename = fs[i].getName();
                        if(filename.endsWith(extension.trim())){
                        	names.addElement(filename);
                        }//end if extension matched
                    }//end not dir
                }//end for   
            }//end if directory
        }//end try
        catch (Exception e)
        {
            e.printStackTrace();
        }       
        return names;
    }   

    private void initComponents() {
        load_lb = new javax.swing.JLabel();
        file_cmb = new javax.swing.JComboBox(fileList);
        
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.X_AXIS));

        load_lb.setText("Load file: ");
        add(load_lb);
        add(file_cmb);

    }
}
