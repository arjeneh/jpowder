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
//TODO: Email us the result.
//TODO: Exit software.
//TODO: Create a help screen.
//TODO: Create an info screen.
//TODO: When no data in the stats_ta, print button should be disable.

public class MainApplet extends JApplet implements ListSelectionListener{
    //Initializes the applet MainApplet
    
    private URL source;//url of the file.
    private Vector<File> fileToReadVector = new Vector<File>();//all actual xye files.
    private Vector data;
    private Vector<LineChartPanel> chartList = new Vector<LineChartPanel>();//reference of plotted charts.
    
    private LogPanel logPanel = new LogPanel();
    private EditChartPanel editChartPanel = new EditChartPanel();
    
    private static final String LOG_PANEL = "YOUR LOG";
    private static final String HOME_PANEL = "card5";
    private static final String EDIT_CHART_PANEL = "YOUR CHART EDITOR";
    private java.awt.CardLayout cl;
    
    private DefaultListModel listModel;
    private DataFileTableModel model;
    
    private LineChartPanel realChart;
    private static final int CHART_HIEGHT_FIX_SIZE = 270;
    private static final int FRAME_WIDTH = 870;
    private static final int FRAME_HIEGHT = 570;
    
    private static final String[] ACCEPTED_FILE_TYPE = {"xye", "txt"};//list of acceptable file types
    private String currentFileName;
    private boolean InBrowser = true;
    
    protected void finalize()throws Throwable {
        System.runFinalization();//beg the jvm to gbc;
    }
    
    public static void main(String[] args) {       
        // *** Modify applet subclass name to that of the program
        MainApplet applet = new MainApplet();
        applet.InBrowser = false;
        
        // Note that init () invoked before adding to frame.
        // So don't use width/height info in init () since those parameters not yet set.
        applet.init();
        
        // Following anonymous class used to close window & exit program
        javax.swing.JFrame f = new javax.swing.JFrame("Crystallograhy Demo");
        f.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        
        // Add applet to the frame
        f.getContentPane().add(applet);
        f.setSize(new Dimension(FRAME_WIDTH, FRAME_HIEGHT));
        f.setVisible(true);
    } //main
    
    public Vector getChartList(){
        return this.chartList;
    }
    
    //return the panel which has all charts
    public JPanel getChartPanel(){
        return this.myChartPanel;
    }
    
    //call from DeleteChartHandler
    public void deleteChartList(int index){
        //TODO: After deletetion it must shrink down on the Y axis.
        //redraw the srollbar to the new position. We could use AdjustmentListener
        this.myChartPanel.remove((Component)chartList.get(index));
        this.myChartPanel.validate();
        this.chartList.remove(index);
        super.repaint();
    }
    //the file user' has uploaded or played with
    public Vector<File> getFileToReadVector(){
        return this.fileToReadVector;
    }
    
    //Set currentFileName var.
    public void setCurrentFile(String str){
        int pos = str.lastIndexOf('/');
        this.currentFileName = str.substring(pos+1);
    }
    
    //the name of current file being used in the graph.
    public String getCurrentFile(){
        return this.currentFileName;
    }
    
    public void setCartLayout(){//CARD LAYOUT THINGS
        cardPanel.add(homePanel, HOME_PANEL);
        cardPanel.add(logPanel, LOG_PANEL);
        cardPanel.add(editChartPanel, EDIT_CHART_PANEL);
        
        cl = (java.awt.CardLayout)(cardPanel.getLayout());
    }
    
    //init the Applet
    public void init() {
        final long s_time =  System.currentTimeMillis();//time of loading
        
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    //initURLSource();//my adding for reading a file as an example.
                    initComponents();//NetBeans generated.
                    setCartLayout();
                    setButtonsEnable();//disable buttons
                    
                    long t = System.currentTimeMillis() - s_time;
                    LogPanel.addLogText("\nElapsed time for loading the applet is "  + t + " milliseconds.");
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//end init
    //set all the button at start to be diasable
    public void setButtonsEnable(){
        JButton[] buttons = new JButton[5];
        buttons[0] = deleteFile_btn;
        buttons[1] = plotFile_btn;
        buttons[2] = print_btn;
        buttons[3] = statCompute_btn;
        buttons[4] = saveStat_btn;
        
        this.listModel.addListDataListener(new MyListDataListener(buttons));
    }//end setButtonsEnable
    
    //Detecting what user's selected in the JList.
    public void valueChanged(javax.swing.event.ListSelectionEvent e) {
        //javax.swing.event.ListSelectionModel lsm = (javax.swing.event.ListSelectionModel)e.getSource();
        if (e.getValueIsAdjusting() == false) {
            if (file_lst.getSelectedIndex() == -1) {
                LogPanel.addLogText("\n No file is selected in the list.");
                return;
            } else {
                int selected = file_lst.getSelectedIndex();
                this.currentFileName = file_lst.getSelectedValue().toString();
                this.data = ReadWriteFileUtil.getLocalFileToTable((File)fileToReadVector.get(selected), this);
                if(this.data != null){
                    drawTable(this.data);
                }
            }
        }
    }//end valueChanged
    
    /* Use to redraw the table when selection of combobox is activated.
     * Creates a new tableModel.
     *
     * @ param newData the new vector data.
     */
    private void drawTable(java.util.Vector newData) {
        final long s_time =  System.currentTimeMillis();//time of loading
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        
        if(this.model != null){
            this.model.setNewData(newData, currentFileName);
            super.repaint();
        }else{
            this.model = new DataFileTableModel(newData, currentFileName);
            this.data_tb.setModel(model);
            super.repaint();
        }
        //report taken time to plot a graph.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                long t = System.currentTimeMillis() - s_time;
                LogPanel.addLogText("\nElapsed time for loading data of " + currentFileName +" to table is " + t + " milliseconds.");
            }
        });
    }//end drawTable
    
    /** This method is called from within the init() method to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        cardPanel = new javax.swing.JPanel();

        homePanel = new javax.swing.JPanel();
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
        myChartPanel = new javax.swing.JPanel();
        butPanel = new javax.swing.JPanel();
        deleteGraph_btn = new javax.swing.JButton();
        deleteGraph_btn.addActionListener(new DeleteChartHandler(this));
        openApp_btn = new javax.swing.JButton();
        about_btn = new javax.swing.JButton();
        help_btn = new javax.swing.JButton();
        email_btn = new javax.swing.JButton();
        exit_btn = new javax.swing.JButton();
        home_btn = new javax.swing.JButton();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        setForeground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        cardPanel.setLayout(new java.awt.CardLayout());

        homePanel.setLayout(new java.awt.GridBagLayout());

        homePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Home", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153)));
        homePanel.setMinimumSize(new java.awt.Dimension(810, 530));
        homePanel.setPreferredSize(new java.awt.Dimension(810, 530));
        fileChooserPanel.setLayout(new java.awt.GridBagLayout());

        fileChooserPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "File Selection Panel", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102)));
        fileChooserPanel.setMaximumSize(new java.awt.Dimension(265, 140));
        fileChooserPanel.setMinimumSize(new java.awt.Dimension(265, 140));
        fileChooserPanel.setPreferredSize(new java.awt.Dimension(265, 140));
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
        homePanel.add(fileChooserPanel, gridBagConstraints);

        tablePanel.setLayout(new java.awt.GridBagLayout());

        tablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Table Panel", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102)));
        tablePanel.setMaximumSize(new java.awt.Dimension(270, 2147483647));
        tablePanel.setMinimumSize(new java.awt.Dimension(265, 170));
        tablePanel.setPreferredSize(new java.awt.Dimension(265, 140));
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
        statCompute_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statCompute_btnActionPerformed(evt);
            }
        });

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
        homePanel.add(tablePanel, gridBagConstraints);

        statPanel.setLayout(new java.awt.GridBagLayout());

        statPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Statistics Panel", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102)));
        statPanel.setMaximumSize(new java.awt.Dimension(270, 32767));
        statPanel.setMinimumSize(new java.awt.Dimension(265, 180));
        statPanel.setPreferredSize(new java.awt.Dimension(265, 180));
        statBluePanel.setLayout(new java.awt.GridBagLayout());

        statBluePanel.setMaximumSize(new java.awt.Dimension(260, 2147483647));
        statBluePanel.setMinimumSize(new java.awt.Dimension(260, 158));
        statBluePanel.setPreferredSize(new java.awt.Dimension(260, 158));
        stats_sp.setMinimumSize(new java.awt.Dimension(260, 115));
        stats_sp.setPreferredSize(new java.awt.Dimension(260, 115));
        stats_ta.setColumns(20);
        stats_ta.setRows(5);
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
        homePanel.add(statPanel, gridBagConstraints);

        chart_scrp.setBorder(null);
        chart_scrp.setAutoscrolls(true);
        chart_scrp.setMinimumSize(new java.awt.Dimension(530, 480));
        chart_scrp.setPreferredSize(new java.awt.Dimension(530, 480));
        myChartPanel.setLayout(new javax.swing.BoxLayout(myChartPanel, javax.swing.BoxLayout.X_AXIS));

        myChartPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chart", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102)));
        myChartPanel.setMaximumSize(new java.awt.Dimension(520, 32767));
        myChartPanel.setMinimumSize(new java.awt.Dimension(520, 530));
        myChartPanel.setPreferredSize(new java.awt.Dimension(520, 530));
        chart_scrp.setViewportView(myChartPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        homePanel.add(chart_scrp, gridBagConstraints);

        cardPanel.add(homePanel, "card5");

        getContentPane().add(cardPanel, new java.awt.GridBagConstraints());

        butPanel.setLayout(new java.awt.GridBagLayout());

        butPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        butPanel.setMaximumSize(new java.awt.Dimension(50, 530));
        butPanel.setMinimumSize(new java.awt.Dimension(50, 530));
        butPanel.setPreferredSize(new java.awt.Dimension(50, 530));
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

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        butPanel.add(deleteGraph_btn, gridBagConstraints);

        openApp_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/openApps_small.gif")));
        openApp_btn.setToolTipText("Open the graph in a new window");
        openApp_btn.setIconTextGap(2);
        openApp_btn.setMaximumSize(new java.awt.Dimension(40, 23));
        openApp_btn.setMinimumSize(new java.awt.Dimension(40, 23));
        openApp_btn.setPreferredSize(new java.awt.Dimension(40, 23));
        openApp_btn.addActionListener(new EditChartHandler(this));
        openApp_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openApp_btnActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        butPanel.add(openApp_btn, gridBagConstraints);

        about_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/about_small.gif")));
        about_btn.setToolTipText("About this application");
        about_btn.setIconTextGap(2);
        about_btn.setMaximumSize(new java.awt.Dimension(40, 23));
        about_btn.setMinimumSize(new java.awt.Dimension(40, 23));
        about_btn.setPreferredSize(new java.awt.Dimension(40, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        butPanel.add(about_btn, gridBagConstraints);

        help_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/help.png")));
        help_btn.setToolTipText("Help about the application");
        help_btn.setIconTextGap(2);
        help_btn.setMaximumSize(new java.awt.Dimension(40, 26));
        help_btn.setMinimumSize(new java.awt.Dimension(40, 26));
        help_btn.setPreferredSize(new java.awt.Dimension(40, 26));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        butPanel.add(help_btn, gridBagConstraints);

        email_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/email_small.gif")));
        email_btn.setToolTipText("Give us feedback.");
        email_btn.setMaximumSize(new java.awt.Dimension(40, 23));
        email_btn.setMinimumSize(new java.awt.Dimension(40, 23));
        email_btn.setPreferredSize(new java.awt.Dimension(40, 23));
        email_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                email_btnActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        butPanel.add(email_btn, gridBagConstraints);

        exit_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit_small.gif")));
        exit_btn.setToolTipText("Exit the system");
        exit_btn.setMaximumSize(new java.awt.Dimension(40, 23));
        exit_btn.setMinimumSize(new java.awt.Dimension(40, 23));
        exit_btn.setPreferredSize(new java.awt.Dimension(40, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        butPanel.add(exit_btn, gridBagConstraints);

        home_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home_small.gif")));
        home_btn.setMaximumSize(new java.awt.Dimension(40, 23));
        home_btn.setMinimumSize(new java.awt.Dimension(40, 23));
        home_btn.setPreferredSize(new java.awt.Dimension(40, 23));
        home_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                home_btnActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        butPanel.add(home_btn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(butPanel, gridBagConstraints);

    }// </editor-fold>//GEN-END:initComponents
    
    private void home_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_home_btnActionPerformed
        cl.show(cardPanel, HOME_PANEL);
    }//GEN-LAST:event_home_btnActionPerformed
    
    private void email_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_email_btnActionPerformed
        //LogPanel logPanel = new LogPanel();
        //cardPanel.add(logPanel, LOG_PANEL);
        cl.show(cardPanel, LOG_PANEL);
    }//GEN-LAST:event_email_btnActionPerformed
    
    private void openApp_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openApp_btnActionPerformed
        
    }//GEN-LAST:event_openApp_btnActionPerformed
    
    private void deleteGraph_btnActionPerformed(java.awt.event.ActionEvent evt){
        
    }
    
    private void statCompute_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statCompute_btnActionPerformed
        
        if(this.model==null)return;
        
        final int MAX_ROW = model.getRowCount();
        final int MAX_COL = model.getColumnCount()-1;//column starts from 1 not 0.
        
        double[] x = new double[MAX_ROW];
        double[] y = new double[MAX_ROW];
        
        for(int row = 0; row < MAX_ROW; row++){
            x[row] = Double.parseDouble((String) model.getValueAt(row, MAX_COL-1));
            y[row] = Double.parseDouble((String) model.getValueAt(row, MAX_COL));
        }
        
        String msg = "The number of rows is " + MAX_ROW + ". ";
        final String MIN_X = String.valueOf(StatsUtil.min(x));
        final String MAX_X = String.valueOf(StatsUtil.max(x));
        
        final String MIN_Y = String.valueOf(StatsUtil.min(y));
        final String MAX_Y = String.valueOf(StatsUtil.max(y));
        
        final String MEAN_X = String.valueOf(StatsUtil.mean(x));
        final String MEAN_Y = String.valueOf(StatsUtil.mean(y));
        
        final String STD_X = String.valueOf(StatsUtil.stddev(x));
        final String STD_Y = String.valueOf(StatsUtil.stddev(y));
        
        msg = msg + "\nThe min of X is " + MIN_X + ".";
        msg = msg + "\nThe max of X is " + MAX_X + ".";
        msg = msg + "\nThe min of Y is " + MIN_Y + ".";
        msg = msg + "\nThe max of Y is " + MAX_Y + ".";
        
        msg = msg + "\nThe mean of X is " + MEAN_X + ".";
        msg = msg + "\nThe mean of Y is " + MEAN_Y + ".";
        
        msg = msg + "\nThe standard deviation of X is " + STD_X + ".";
        msg = msg + "\nThe standard deviation of Y is " + STD_Y + ".";
        
        appendText(stats_ta, msg);
    }//GEN-LAST:event_statCompute_btnActionPerformed
    
    private void appendText(javax.swing.JTextArea ta, String msg){
        ta.append("\n" + msg);
    }
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
    
    /*Checking whether file type is allowed
     *@ param filenames
     **/
    public boolean checkAcceptedFileType(String filenames){
        boolean result = true;
        for(int i = ACCEPTED_FILE_TYPE.length-1; i>=0;i--){
            if(filenames.endsWith(ACCEPTED_FILE_TYPE[i])){//ternary ?:
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
        fc.addChoosableFileFilter(new AcceptFileFilter(ACCEPTED_FILE_TYPE, "ASCII file (*.xye, *.txt)"));
        fc.setAcceptAllFileFilterUsed(false);//only ASCII will be seen.
        
        int returnVal = fc.showOpenDialog(MainApplet.this);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String filename = file.getName().toLowerCase();
            
            //This is a real application would restrict to 'xye'.
            if (checkAcceptedFileType(filename)){
                this.data = ReadWriteFileUtil.getLocalFileToTable(file, this);
                if(this.data != null){
                    currentFileName = filename;
                    drawTable(this.data);
                    fileToReadVector.addElement(file);//was filename (String type)
                    listModel.addElement(filename);//add to DefaultListModel
                }
            } else {
                javax.swing.JOptionPane.showMessageDialog(MainApplet.this, "Only ASCII file please.");
                //end if extension matched
            }
        } else {
            LogPanel.addLogText("\nOpen file command cancelled by user.");
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
            realChart = new LineChartPanel(data, currentFileName);
            java.awt.Dimension area = myChartPanel.getSize();
            area.height = area.height + CHART_HIEGHT_FIX_SIZE;//update to a bigger size.
            myChartPanel.setLayout(new java.awt.FlowLayout());
            myChartPanel.setPreferredSize(area);//new size
            
            chartList.add(realChart);//put in Vector
            myChartPanel.add(realChart);//put on the screen
            myChartPanel.revalidate();
            
            //redraw the srollbar to the new position
            Rectangle rect = myChartPanel.getBounds();
            chart_scrp.getVerticalScrollBar().setValue(rect.height);
            
            //report taken time to plot a graph.
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    long t = System.currentTimeMillis() - s_time;
                    LogPanel.addLogText("\nElapsed time for plotting " + currentFileName +" graph is " + t + " milliseconds.");
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
    private javax.swing.JPanel butPanel;
    private javax.swing.JPanel cardPanel;
    private javax.swing.JScrollPane chart_scrp;
    private javax.swing.JScrollPane data_sp;
    private javax.swing.JTable data_tb;
    private javax.swing.JButton deleteFile_btn;
    private javax.swing.JButton deleteGraph_btn;
    private javax.swing.JButton email_btn;
    private javax.swing.JButton exit_btn;
    private javax.swing.JPanel fileChooserPanel;
    private javax.swing.JList file_lst;
    private javax.swing.JScrollPane file_sp;
    private javax.swing.JButton help_btn;
    private javax.swing.JPanel homePanel;
    private javax.swing.JButton home_btn;
    private javax.swing.JPanel myChartPanel;
    private javax.swing.JButton openApp_btn;
    private javax.swing.JButton plotFile_btn;
    private javax.swing.JButton print_btn;
    private javax.swing.JButton saveStat_btn;
    private javax.swing.JPanel statBluePanel;
    private javax.swing.JButton statCompute_btn;
    private javax.swing.JPanel statPanel;
    private javax.swing.JScrollPane stats_sp;
    private javax.swing.JTextArea stats_ta;
    private javax.swing.JPanel tablePanel;
    // End of variables declaration//GEN-END:variables
    
}//end MainApplet
