import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.StringTokenizer;
import java.util.Vector;
import java.net.URL;
import javax.swing.DefaultListModel;
import java.lang.String ;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JViewport;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;

/*
 * MainApplet.java
 *
 * Created on 07 March 2007, 13:51
 */

/**
 * @author  Kreecha Puphaiboon
 */

//TODO: Button to delete the graph in graph Panel
//TODO: Another window to view details of each graph individually by double clicking.
//TODO: Change tooltip background color.

//TODO: Create a help screen.
//TODO: Create an info screen.

public class MainApplet extends JApplet implements ListSelectionListener{
    //Initializes the applet MainApplet
    
    private URL source;//url of the file.
    private Vector<File> fileToReadVector = new Vector<File>();//all actual xye files.
    private Vector data;
    private Vector<LineChartPanel> chartList = new Vector<LineChartPanel>();//reference of plotted charts.
    
    private DefaultListModel listModel;
    private DataFileTableModel model;
    
    private LineChartPanel realChart;
    private static final int CHART_HIEGHT_FIX_SIZE = 270;
    private static final int FRAME_WIDTH = 870;
    private static final int FRAME_HIEGHT = 550;
    
    private String[] acceptFileTypes = {"xye", "txt"};//list of acceptable file types
    private String currentFile;
    private boolean InBrowser = true;
    
    public static void main(String[] args) {
        // *** Modify these values for the particular program.
        
        // *** Modify applet subclass name to that of the program
        MainApplet applet = new MainApplet();
        applet.InBrowser = false;
        
        // Note that init () invoked before adding to frame.
        // So don't use width/height info in init () since those parameters not yet set.
        applet.init();
        
        // Following anonymous class used to close window & exit program
        javax.swing.JFrame f = new javax.swing.JFrame("Crstallograhy Demo");
        f.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        
        // Add applet to the frame
        f.getContentPane().add(applet);
        f.setSize(new Dimension(FRAME_WIDTH, FRAME_HIEGHT));
        f.setVisible(true);
    } //main
    
    public Vector getChartList(){
        return chartList;
    }
    
    public JPanel getChartPanel(){
        return chartPanel;
    }
    
    //call from DeleteChartHandler
    public void deleteChartList(int index){
        //TODO: After deletetion it must shrink down on the Y axis.
        //redraw the srollbar to the new position. We could use AdjustmentListener
        System.out.println("Enter Index was " + index);
        
        chartPanel.remove((Component)chartList.get(index));
        chartPanel.validate();
        chartList.remove(index);
        
        // - CHART_HIEGHT_FIX_SIZE
        //Rectangle rect = chartPanel.getBounds();
        //System.out.println("After deleteion, size of ChartPanel is: " + rect.height);
        //chart_scrp.getVerticalScrollBar().setValue(chartPanel.getHeight());//rect.height);       
        super.repaint();
    }
    
    public Vector getFileToReadVector(){
        return fileToReadVector;
    }
    
    //Set currentFile var.
    public void setCurrentFile(String str){
        int pos = str.lastIndexOf('/');
        currentFile = str.substring(pos+1);
    }
    
    //the name of current file being used in the graph.
    public String getCurrentFile(){
        return currentFile;
    }
    
    //init the Applet
    public void init() {
        final long s_time =  System.currentTimeMillis();//time of loading
        
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    //initURLSource();//my adding.
                    initComponents();//NetBeans generated.
                    setButtonsEnable();//disable buttons
                    setPreferredSize(new Dimension(765, 520));
                    setMinimumSize(new Dimension(765, 520));
                    
                    long t = System.currentTimeMillis() - s_time;
                    log_ta.append("\nElapsed time for loading the applet is "  + t + " milliseconds.");
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//end init
    
    public void setButtonsEnable(){
        JButton[] buttons = new JButton[5];
        buttons[0] = deleteFile_btn;
        buttons[1] = plotFile_btn;
        buttons[2] = print_btn;
        buttons[3] = statCompute_btn;
        buttons[4] = saveStat_btn;
        
        listModel.addListDataListener(new MyListDataListener(buttons));
    }//end setButtonsEnable
    
    /* Use first to initialise model for table, xye data and xye files GOOD FOR EXAMPLE ONLY*/
    /*private void initURLSource(){
        fileToReadVector = new Vector();
        fileToReadVector.addElement("BM16_C6-H6-N6-O4_H2O.xye");
        try {
            //read the first xye file's data and put it in the table DataTablePanel.
            source = new java.net.URL(getCodeBase(), "data/" + (String)fileToReadVector.firstElement());
            readURLFileToTable(source);//put the vector into data vector.
            setCurrentFile(source.toString());
            model = new DataFileTableModel(data);
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL = " + e);
            return;
        } //end try
    }//initURLSource*/
    
    public void readLocalFileToTable(File aFile) {
        String aLine;
        data = new Vector();
        File file = aFile;
        double filter = 0;
        
        try {
            final long s_time =  System.currentTimeMillis();//time of loading
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new  BufferedReader(new  InputStreamReader(fis));
            
            while ((aLine = br.readLine()) != null) {
                // create a vector to hold the field values
                Vector newRow = new Vector();
                StringTokenizer st2 =  new StringTokenizer(aLine);
                int numToken = st2.countTokens();
                for (int i = 0 ; i < numToken-1; i++){//ignore the last STD by minusing 1.
                    String stoken = st2.nextToken();
                    double compare = Double.parseDouble(stoken);//check number or not, if yes add element
                    if(compare >= filter){
                        newRow.addElement(stoken);
                    }else{
                        System.out.println("You have an alphabet contained in the file.");
                    }//if else
                } //for
                data.addElement(newRow);
            }//while readLine
            
            drawTable(data);
            
            fis.close();
            br.close();
            
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    long t = System.currentTimeMillis() - s_time;
                    log_ta.append("\nElapsed time for loading file "  + currentFile + " into table is " + t + " milliseconds.");
                }
            });
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL = " + e);
            return;
        } catch(IOException io){
            System.out.println("IOException throws " + io);
            return;
        }
    }//end readLocalFileToTable
    
    //For users, the example is given. It read from our net url.
    public void readURLFileToTable(java.net.URL codeURL) {
        String aLine;
        data = new Vector();
        URL source = codeURL;
        
        try {
            BufferedReader br = new  BufferedReader(new  InputStreamReader(source.openStream()));
            while ((aLine = br.readLine()) != null) {
                // create a vector to hold the field values
                Vector newRow = new Vector();
                StringTokenizer st2 =  new StringTokenizer(aLine);
                int numToken = st2.countTokens();
                for (int i = 0 ; i < numToken-1; i++){//ignore the last STD by minusing 1.
                    newRow.addElement(st2.nextToken());
                } //for
                data.addElement(newRow);
            }//while readLine
            
            if (model!= null){
                drawTable(data);
            }
            
            br.close();
            
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL = " + e);
            return;
        } catch(IOException io){
            System.out.println("IOException throws " + io);
            return;
        }
    }// readURLFileToTable()
    
    //Detecting what user's selected in the JList.
    public void valueChanged(javax.swing.event.ListSelectionEvent e) {
        //javax.swing.event.ListSelectionModel lsm = (javax.swing.event.ListSelectionModel)e.getSource();
        if (e.getValueIsAdjusting() == false) {
            if (file_lst.getSelectedIndex() == -1) {
                log_ta.append("\n No file is selected.");
                return;
            } else {
                int selected = file_lst.getSelectedIndex();
                currentFile = file_lst.getSelectedValue().toString();
                readLocalFileToTable((File)fileToReadVector.get(selected));//listModel.get();
            }
        }
    }//end valueChanged
    
    /* Use to redraw the table when selection of combobox is activated.
     * Creates a new tableModel.
     * @ param newData the new vector data.
     */
    private void drawTable(java.util.Vector newData) {
        if(model!= null){
            model.setNewData(newData);
            super.repaint();
        }else{
            model = new DataFileTableModel(newData);
            data_tb.setModel(model);
            super.repaint();
        }
        //log_ta.append("\nCurrent file is " + currentFile + " " + "and number of rows is " + model.getRowCount() + ".");
    }//end drawTable
    
    /*
     * Creates a vector fileToReadVector which holds the file names
     * recieved from JSP and applet param and value. used in init() before readURLFileToTable()
     */
    /*public void readAppletParam(){
        fileToReadVector = new java.util.Vector();
        String value;
        int i = 0;
        while ((value = getParameter("file" + i))!= null) {
            fileToReadVector.addElement(value);
            i++;
        }//end while
    }//end  readAppletParam()*/
    
    /** This method is called from within the init() method to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        splitPane = new javax.swing.JSplitPane();
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(0.1);
        topPanel = new javax.swing.JPanel();
        fileChooserPanel = new javax.swing.JPanel();
        file_sp = new javax.swing.JScrollPane();
        file_lst = new javax.swing.JList();

        //create a list model to put in the JList
        listModel = new javax.swing.DefaultListModel();
        //listModel.addElement("BM16_C6-H6-N6-O4_H2O.xye");
        //listModel.addListDataListener(new MyListDataListener());

        file_lst.setModel(listModel);
        file_lst.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        file_lst.addListSelectionListener(this);//list selction

        addFile_btn = new javax.swing.JButton();
        deleteFile_btn = new javax.swing.JButton();
        tablePanel = new javax.swing.JPanel();
        data_sp = new javax.swing.JScrollPane();
        data_tb = new javax.swing.JTable();
        statCompute_btn = new javax.swing.JButton();
        plotFile_btn = new javax.swing.JButton();
        statPanel = new javax.swing.JPanel();
        statBluePanel = new javax.swing.JPanel();
        stats_sp = new javax.swing.JScrollPane();
        stats_ta = new javax.swing.JTextArea();
        print_btn = new javax.swing.JButton();
        saveStat_btn = new javax.swing.JButton();
        chart_scrp = new javax.swing.JScrollPane();
        chartPanel = new javax.swing.JPanel();
        graphButPanel = new javax.swing.JPanel();
        deleteGraph_btn = new javax.swing.JButton();
        deleteGraph_btn.addActionListener(new DeleteChartHandler(this));
        openApp_btn = new javax.swing.JButton();
        zoomInGraph_btn = new javax.swing.JButton();
        zoomOutGraph_btn = new javax.swing.JButton();
        help_btn = new javax.swing.JButton();
        about_btn = new javax.swing.JButton();
        logPanel = new javax.swing.JPanel();
        log_sp = new javax.swing.JScrollPane();
        log_ta = new javax.swing.JTextArea();

        getContentPane().setLayout(new java.awt.CardLayout());

        setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        setForeground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        splitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        splitPane.setResizeWeight(0.9);
        splitPane.setAutoscrolls(true);
        splitPane.setMinimumSize(new java.awt.Dimension(840, 580));
        splitPane.setPreferredSize(new java.awt.Dimension(840, 550));
        topPanel.setLayout(new java.awt.GridBagLayout());

        topPanel.setMinimumSize(new java.awt.Dimension(850, 480));
        topPanel.setPreferredSize(new java.awt.Dimension(850, 480));
        fileChooserPanel.setLayout(new java.awt.GridBagLayout());

        fileChooserPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "File Selection Panel", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102)));
        fileChooserPanel.setMaximumSize(new java.awt.Dimension(270, 140));
        fileChooserPanel.setMinimumSize(new java.awt.Dimension(270, 140));
        fileChooserPanel.setPreferredSize(new java.awt.Dimension(270, 140));
        file_sp.setMinimumSize(new java.awt.Dimension(260, 84));
        file_sp.setPreferredSize(new java.awt.Dimension(260, 84));
        file_lst.setFont(new java.awt.Font("Tahoma", 0, 10));
        file_lst.setMinimumSize(new java.awt.Dimension(210, 84));
        file_lst.setPreferredSize(new java.awt.Dimension(210, 84));
        file_sp.setViewportView(file_lst);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        fileChooserPanel.add(file_sp, gridBagConstraints);

        addFile_btn.setFont(new java.awt.Font("Tahoma", 0, 10));
        addFile_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/addFile.gif")));
        addFile_btn.setText("Add file");
        addFile_btn.setToolTipText("Add file");
        addFile_btn.setMaximumSize(new java.awt.Dimension(120, 23));
        addFile_btn.setMinimumSize(new java.awt.Dimension(120, 23));
        addFile_btn.setPreferredSize(new java.awt.Dimension(120, 23));
        addFile_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFile_btnActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        fileChooserPanel.add(addFile_btn, gridBagConstraints);

        deleteFile_btn.setFont(new java.awt.Font("Tahoma", 0, 10));
        deleteFile_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/del_small.gif")));
        deleteFile_btn.setText("Delete file");
        deleteFile_btn.setToolTipText("Delete file");
        deleteFile_btn.setEnabled(false);
        deleteFile_btn.setMaximumSize(new java.awt.Dimension(50, 23));
        deleteFile_btn.setMinimumSize(new java.awt.Dimension(50, 23));
        deleteFile_btn.setPreferredSize(new java.awt.Dimension(50, 23));
        deleteFile_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteFile_btnActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        fileChooserPanel.add(deleteFile_btn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        topPanel.add(fileChooserPanel, gridBagConstraints);

        tablePanel.setLayout(new java.awt.GridBagLayout());

        tablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Table Panel", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102)));
        tablePanel.setMaximumSize(new java.awt.Dimension(270, 2147483647));
        tablePanel.setMinimumSize(new java.awt.Dimension(270, 180));
        tablePanel.setPreferredSize(new java.awt.Dimension(270, 180));
        data_sp.setMinimumSize(new java.awt.Dimension(260, 115));
        data_sp.setPreferredSize(new java.awt.Dimension(260, 115));
        data_tb.setMinimumSize(new java.awt.Dimension(230, 150));
        data_sp.setViewportView(data_tb);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        tablePanel.add(data_sp, gridBagConstraints);

        statCompute_btn.setFont(new java.awt.Font("Tahoma", 0, 10));
        statCompute_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Stats.gif")));
        statCompute_btn.setText("Compute Stats");
        statCompute_btn.setEnabled(false);
        statCompute_btn.setIconTextGap(2);
        statCompute_btn.setMaximumSize(new java.awt.Dimension(250, 23));
        statCompute_btn.setMinimumSize(new java.awt.Dimension(120, 23));
        statCompute_btn.setPreferredSize(new java.awt.Dimension(1203, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        tablePanel.add(statCompute_btn, gridBagConstraints);

        plotFile_btn.setFont(new java.awt.Font("Tahoma", 0, 10));
        plotFile_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PlotChart.gif")));
        plotFile_btn.setText("Plot Chart");
        plotFile_btn.setEnabled(false);
        plotFile_btn.setMaximumSize(new java.awt.Dimension(90, 23));
        plotFile_btn.setMinimumSize(new java.awt.Dimension(90, 23));
        plotFile_btn.setPreferredSize(new java.awt.Dimension(90, 23));
        plotFile_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plotFile_btnActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        tablePanel.add(plotFile_btn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        topPanel.add(tablePanel, gridBagConstraints);

        statPanel.setLayout(new java.awt.GridBagLayout());

        statPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Statistics Panel", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102)));
        statPanel.setMaximumSize(new java.awt.Dimension(270, 32767));
        statPanel.setMinimumSize(new java.awt.Dimension(270, 180));
        statPanel.setPreferredSize(new java.awt.Dimension(270, 180));
        statBluePanel.setLayout(new java.awt.GridBagLayout());

        statBluePanel.setMaximumSize(new java.awt.Dimension(260, 2147483647));
        statBluePanel.setMinimumSize(new java.awt.Dimension(260, 158));
        statBluePanel.setPreferredSize(new java.awt.Dimension(260, 158));
        stats_sp.setMinimumSize(new java.awt.Dimension(260, 120));
        stats_sp.setPreferredSize(new java.awt.Dimension(260, 120));
        stats_ta.setColumns(20);
        stats_ta.setRows(5);
        stats_ta.setText("Mean | Standard Deviation\n----------------------\n6.04 | 0.343        ");
        stats_ta.setMinimumSize(new java.awt.Dimension(1400, 1000));
        stats_ta.setPreferredSize(new java.awt.Dimension(1400, 1000));
        stats_sp.setViewportView(stats_ta);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        statBluePanel.add(stats_sp, gridBagConstraints);

        print_btn.setFont(new java.awt.Font("Tahoma", 0, 10));
        print_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer.gif")));
        print_btn.setEnabled(false);
        print_btn.setLabel("Print stats");
        print_btn.setMaximumSize(new java.awt.Dimension(100, 23));
        print_btn.setMinimumSize(new java.awt.Dimension(100, 23));
        print_btn.setPreferredSize(new java.awt.Dimension(120, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        statBluePanel.add(print_btn, gridBagConstraints);

        saveStat_btn.setFont(new java.awt.Font("Tahoma", 0, 10));
        saveStat_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/savas_small.gif")));
        saveStat_btn.setEnabled(false);
        saveStat_btn.setLabel("Save stats");
        saveStat_btn.setPreferredSize(new java.awt.Dimension(100, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        statBluePanel.add(saveStat_btn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        statPanel.add(statBluePanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        topPanel.add(statPanel, gridBagConstraints);

        chart_scrp.setBorder(null);
        chart_scrp.setAutoscrolls(true);
        chart_scrp.setMinimumSize(new java.awt.Dimension(530, 480));
        chart_scrp.setPreferredSize(new java.awt.Dimension(530, 480));
        chartPanel.setLayout(new javax.swing.BoxLayout(chartPanel, javax.swing.BoxLayout.X_AXIS));

        chartPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chart", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102)));
        chartPanel.setMaximumSize(new java.awt.Dimension(520, 32767));
        chartPanel.setMinimumSize(new java.awt.Dimension(520, 500));
        chartPanel.setPreferredSize(new java.awt.Dimension(520, 500));
        chart_scrp.setViewportView(chartPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        topPanel.add(chart_scrp, gridBagConstraints);

        graphButPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        graphButPanel.setMaximumSize(new java.awt.Dimension(50, 33));
        graphButPanel.setMinimumSize(new java.awt.Dimension(50, 37));
        graphButPanel.setPreferredSize(new java.awt.Dimension(50, 37));
        deleteGraph_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/remov_small.gif")));
        deleteGraph_btn.setToolTipText("delete the graph");
        deleteGraph_btn.setIconTextGap(2);
        deleteGraph_btn.setMaximumSize(new java.awt.Dimension(40, 23));
        deleteGraph_btn.setMinimumSize(new java.awt.Dimension(40, 23));
        deleteGraph_btn.setPreferredSize(new java.awt.Dimension(40, 23));
        deleteGraph_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteGraph_btnActionPerformed(evt);
            }
        });

        graphButPanel.add(deleteGraph_btn);

        openApp_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/openApps_small.gif")));
        openApp_btn.setToolTipText("Open the graph in a new window");
        openApp_btn.setIconTextGap(2);
        openApp_btn.setMaximumSize(new java.awt.Dimension(40, 23));
        openApp_btn.setMinimumSize(new java.awt.Dimension(40, 23));
        openApp_btn.setPreferredSize(new java.awt.Dimension(40, 23));
        openApp_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openApp_btnActionPerformed(evt);
            }
        });

        graphButPanel.add(openApp_btn);

        zoomInGraph_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/zoomi_small.gif")));
        zoomInGraph_btn.setToolTipText("Zoom in the graph");
        zoomInGraph_btn.setIconTextGap(2);
        zoomInGraph_btn.setMaximumSize(new java.awt.Dimension(40, 23));
        zoomInGraph_btn.setMinimumSize(new java.awt.Dimension(40, 23));
        zoomInGraph_btn.setPreferredSize(new java.awt.Dimension(40, 23));
        graphButPanel.add(zoomInGraph_btn);

        zoomOutGraph_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/zoomo_small.gif")));
        zoomOutGraph_btn.setToolTipText("Zoom out the graph");
        zoomOutGraph_btn.setIconTextGap(2);
        zoomOutGraph_btn.setMaximumSize(new java.awt.Dimension(40, 23));
        zoomOutGraph_btn.setMinimumSize(new java.awt.Dimension(40, 23));
        zoomOutGraph_btn.setPreferredSize(new java.awt.Dimension(40, 23));
        graphButPanel.add(zoomOutGraph_btn);

        help_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/help.png")));
        help_btn.setToolTipText("Help about the application");
        help_btn.setIconTextGap(2);
        help_btn.setMaximumSize(new java.awt.Dimension(40, 26));
        help_btn.setMinimumSize(new java.awt.Dimension(40, 26));
        help_btn.setPreferredSize(new java.awt.Dimension(40, 26));
        graphButPanel.add(help_btn);

        about_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/about_small.gif")));
        about_btn.setToolTipText("About this application");
        about_btn.setIconTextGap(2);
        about_btn.setMaximumSize(new java.awt.Dimension(40, 23));
        about_btn.setMinimumSize(new java.awt.Dimension(40, 23));
        about_btn.setPreferredSize(new java.awt.Dimension(40, 23));
        graphButPanel.add(about_btn);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        topPanel.add(graphButPanel, gridBagConstraints);

        splitPane.setLeftComponent(topPanel);

        logPanel.setPreferredSize(new java.awt.Dimension(850, 200));
        log_sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        log_sp.setMinimumSize(new java.awt.Dimension(825, 200));
        log_sp.setPreferredSize(new java.awt.Dimension(825, 200));
        log_sp.setViewport(null);
        log_ta.setColumns(50);
        log_ta.setForeground(new java.awt.Color(51, 51, 51));
        log_ta.setLineWrap(true);
        log_ta.setRows(1000);
        log_ta.setText("This is the log area of your activities.");
        log_ta.setWrapStyleWord(true);
        log_ta.setMargin(new java.awt.Insets(0, 15, 0, 0));
        log_ta.setMinimumSize(new java.awt.Dimension(800, 540));
        log_sp.setViewportView(log_ta);

        logPanel.add(log_sp);

        splitPane.setRightComponent(logPanel);

        getContentPane().add(splitPane, "card2");

    }// </editor-fold>//GEN-END:initComponents
    
    private void openApp_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openApp_btnActionPerformed
// TODO add your handling code here:
        System.out.println("ChartList in MainApplet is: " + chartList.size());
    }//GEN-LAST:event_openApp_btnActionPerformed
    
    private void deleteGraph_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteGraph_btnActionPerformed
        
    }//GEN-LAST:event_deleteGraph_btnActionPerformed
    
    private void deleteFile_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteFile_btnActionPerformed
        //if no nothing selected then alert.
        if (file_lst.getSelectedIndex() == -1) {
            javax.swing.JOptionPane.showMessageDialog(MainApplet.this, "To delete, please select a file first.");
            return;
        }
        
        //if the list is empty then do nothing
        if (listModel.getSize() <= 0) {
            javax.swing.JOptionPane.showMessageDialog(MainApplet.this, "Please add a file first.");
            return;
        }
        
        //do the actual deletion.
        if (listModel.getSize() > 0){
            int index = file_lst.getSelectedIndex();
            listModel.removeElementAt(index);
            fileToReadVector.removeElementAt(index);
        }
    }//GEN-LAST:event_deleteFile_btnActionPerformed
    //end deleteFile_btnActionPerformed
    
    //Checking whether file type is allowed
    public boolean checkAcceptedFileType(String filenames){
        boolean result = true;
        for(int i = acceptFileTypes.length-1; i>=0;i--){
            if(filenames.endsWith(acceptFileTypes[i])){//ternary ?:
                //System.out.println("\nAccepted File Type.");
                result = true;
            } else{
                result = false;
            }
        }//end for
        return result;
    }//end checkAcceptedFileType
    
    private void addFile_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFile_btnActionPerformed
        // Browse for a file on user machine
        // Create a list of files to be choosen and keep the actual data in fileToReadVector.
        // restrict to .xye to be read.
        
        final JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(new AcceptFileFilter(acceptFileTypes, "ASCII file (*.xye, *.txt)"));
        fc.setAcceptAllFileFilterUsed(false);//only ASCII will be seen.
        
        int returnVal = fc.showOpenDialog(MainApplet.this);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String filename = file.getName().toLowerCase();
            
            //This is a real application would restrict to 'xye'.
            
            if (checkAcceptedFileType(filename)){
                
                readLocalFileToTable(file);
                
                fileToReadVector.addElement(file);//was filename (String type)
                listModel.addElement(filename);//add to DefaultListModel
                currentFile = filename;
                
            } else {
                javax.swing.JOptionPane.showMessageDialog(MainApplet.this, "Only ASCII file and all numaric data are  are acceptted.");
                //end if extension matched
            }
            
        } else {
            log_ta.append("\nOpen command cancelled by user." );
        }
        
    }//GEN-LAST:event_addFile_btnActionPerformed
    
    // When user click they can see the graph from what ever data in the table.
    private void plotFile_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plotFile_btnActionPerformed
        if(model==null)return;
        
        try{
            //TODO: BufferedImage protection.
            // record the current time and plot the file.
            final long s_time =  System.currentTimeMillis();//time of loading
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            
            //create the chart
            realChart = new LineChartPanel(data, currentFile);
            java.awt.Dimension area = chartPanel.getSize();
            area.height = area.height + CHART_HIEGHT_FIX_SIZE;//update to a bigger size.
            chartPanel.setLayout(new java.awt.FlowLayout());
            chartPanel.setPreferredSize(area);//new size
            
            chartList.add(realChart);//put in Vector
            chartPanel.add(realChart);//put on the screen
            chartPanel.revalidate();
            
            //redraw the srollbar to the new position
            Rectangle rect = chartPanel.getBounds();
            chart_scrp.getVerticalScrollBar().setValue(rect.height);
            
            //report taken time of ploting the graph.
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    long t = System.currentTimeMillis() - s_time;
                    log_ta.append("\nElapsed time for plotting " + currentFile +" graph is " + t + " milliseconds.");
                }
            });
            
        } catch (java.lang.Exception e){
            e.printStackTrace();
        }//end try and catch
    }//GEN-LAST:event_plotFile_btnActionPerformed
    //end plotFile_btnActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton about_btn;
    private javax.swing.JButton addFile_btn;
    private javax.swing.JPanel chartPanel;
    private javax.swing.JScrollPane chart_scrp;
    private javax.swing.JScrollPane data_sp;
    private javax.swing.JTable data_tb;
    private javax.swing.JButton deleteFile_btn;
    private javax.swing.JButton deleteGraph_btn;
    private javax.swing.JPanel fileChooserPanel;
    private javax.swing.JList file_lst;
    private javax.swing.JScrollPane file_sp;
    private javax.swing.JPanel graphButPanel;
    private javax.swing.JButton help_btn;
    private javax.swing.JPanel logPanel;
    private javax.swing.JScrollPane log_sp;
    private javax.swing.JTextArea log_ta;
    private javax.swing.JButton openApp_btn;
    private javax.swing.JButton plotFile_btn;
    private javax.swing.JButton print_btn;
    private javax.swing.JButton saveStat_btn;
    private javax.swing.JSplitPane splitPane;
    private javax.swing.JPanel statBluePanel;
    private javax.swing.JButton statCompute_btn;
    private javax.swing.JPanel statPanel;
    private javax.swing.JScrollPane stats_sp;
    private javax.swing.JTextArea stats_ta;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JPanel topPanel;
    private javax.swing.JButton zoomInGraph_btn;
    private javax.swing.JButton zoomOutGraph_btn;
    // End of variables declaration//GEN-END:variables
    
}//end MainApplet
