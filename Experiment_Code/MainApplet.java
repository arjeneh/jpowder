import java.awt.Cursor;
/*
 * MainApplet.java
 *
 * Created on 07 March 2007, 13:51
 */

/**
 * @author  regtrain
 */

public class MainApplet extends javax.swing.JApplet {
    /**
     * Initializes the applet MainApplet
     */
    private java.net.URL source;//url of the file.
    private java.util.Vector fileToReadVector;//all xye files.
    private java.util.Vector data;
    
    private java.lang.String currentFile;
    private DataFileTableModel model;
    private LineChartPanel realChart;
    
    private javax.swing.ProgressMonitor progressMonitor;
    private javax.swing.Timer timer;
    private LongTask task;
    
    public java.util.Vector getFileToReadVector(){
        return fileToReadVector;
    }
    
    //Set currentFile var.
    public void setCurrentFile(String str){
        int pos = str.lastIndexOf('/');
        currentFile = str.substring(pos+1);
    }
    
    public String getCurrentFile(){
        return currentFile;
    }
    
    /*public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new NewJDialog(new javax.swing.JFrame(), true).setVisible(true);
                //new NewJDialog(new javax.swing.JFrame(), true).setVisible(true);
            }
        });
    }*/
    
    public void init() {
        final long s_time =  System.currentTimeMillis();//time of loading
        
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    initSource();//my adding.
                    initComponents();//NetBeans generated.
                    setMaximumSize(new java.awt.Dimension(765, 520));
                    setPreferredSize(new java.awt.Dimension(765, 520));
                    setMinimumSize(new java.awt.Dimension(765, 520));
                    
                    long t = System.currentTimeMillis() - s_time;
                    stats_ta.append("\nElapsed time for loading the applet is "  + t + " milliseconds.");
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /* Use first to initialise model for table, xye data and xye files */
    public void initSource(){
        //create list of files for JCombobox or JList.
        fileToReadVector = new java.util.Vector();
        fileToReadVector.addElement("Daresbury_K-U-O2-PO4_3D2O.xye");
        fileToReadVector.addElement("BM16_C6-H6-N6-O4_H2O.xye");
        fileToReadVector.addElement("BM16_C7-H11-N5-O2.xye");
        
        try {
            //read the first xye file's data and put it in the table DataTablePanel.
            source = new java.net.URL(getCodeBase(), "data/" + (String)fileToReadVector.firstElement());
            readFileToTable(source);//put the vector into data vector.
            setCurrentFile(source.toString());
            model = new DataFileTableModel(data);
        } catch (java.net.MalformedURLException e) {
            System.out.println("Malformed URL = " + e);
            return;
        } //end try
    }//initSource
    
    public void readFileToTable(java.net.URL codeURL) {
        String aLine;
        data = new java.util.Vector();
        java.net.URL source = codeURL;
        
        try {
            java.io.BufferedReader br = new  java.io.BufferedReader(new  java.io.InputStreamReader(source.openStream()));
            while ((aLine = br.readLine()) != null) {
                // create a vector to hold the field values
                java.util.Vector newRow = new java.util.Vector();
                java.util.StringTokenizer st2 =  new java.util.StringTokenizer(aLine);
                
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
            
        } catch (java.net.MalformedURLException e) {
            System.out.println("Malformed URL = " + e);
            return;
        } catch(java.io.IOException io){
            System.out.println("IOException throws " + io);
            return;
        }
    }// readFileToTable()
    
    /* Use to redraw the table when selection of combobox is activated.
     * Creates a new tableModel.
     * @ param newData the new vector data.
     */
    private void drawTable(java.util.Vector newData) {
        data_tb.setModel(new DataFileTableModel(newData));
        super.repaint();
    }
    
    /*
     * Creates a vector fileToReadVector which holds the file names
     * recieved from JSP and applet param and value. used in init() before readFileToTable()
     */
    public void readAppletParam(){
        fileToReadVector = new java.util.Vector();
        String value;
        int i = 0;
        while ((value = getParameter("file" + i))!= null) {
            fileToReadVector.addElement(value);
            i++;
        }//end while
    }//end  readAppletParam()
    
    /** This method is called from within the init() method to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        fileChooserPanel = new javax.swing.JPanel();
        file_sp = new javax.swing.JScrollPane();
        file_lst = new javax.swing.JList();

        //create a list model to put in the JList
        javax.swing.DefaultListModel listModel = new javax.swing.DefaultListModel();
        listModel.addElement("BM16_C6-H6-N6-O4_H2O.xye");
        listModel.addElement("BM16_C7-H11-N5-O2.xye");
        listModel.addElement("Daresbury_K-U-O2-PO4_3D2O.xye");

        file_lst.setModel(listModel);
        file_lst.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        //file_lst.addListSelectionListener(this);

        addFile_btn = new javax.swing.JButton();
        deleteFile_btn = new javax.swing.JButton();
        seeDataTable_btn = new javax.swing.JButton();
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
        chart_scrp = new javax.swing.JScrollPane();
        chartPanel = new javax.swing.JPanel();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        setForeground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        fileChooserPanel.setLayout(new java.awt.GridBagLayout());

        fileChooserPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "File Selection Panel", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102)));
        fileChooserPanel.setMaximumSize(new java.awt.Dimension(230, 140));
        fileChooserPanel.setMinimumSize(new java.awt.Dimension(230, 140));
        fileChooserPanel.setPreferredSize(new java.awt.Dimension(230, 140));
        file_sp.setMinimumSize(new java.awt.Dimension(210, 84));
        file_sp.setPreferredSize(new java.awt.Dimension(210, 84));
        file_lst.setFont(new java.awt.Font("Tahoma", 0, 10));
        file_lst.setMinimumSize(new java.awt.Dimension(210, 84));
        file_lst.setPreferredSize(new java.awt.Dimension(210, 84));
        file_sp.setViewportView(file_lst);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        fileChooserPanel.add(file_sp, gridBagConstraints);

        addFile_btn.setFont(new java.awt.Font("Tahoma", 0, 10));
        addFile_btn.setText("Add file");
        addFile_btn.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        fileChooserPanel.add(addFile_btn, gridBagConstraints);

        deleteFile_btn.setFont(new java.awt.Font("Tahoma", 0, 10));
        deleteFile_btn.setText("Delete file");
        deleteFile_btn.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        fileChooserPanel.add(deleteFile_btn, gridBagConstraints);

        seeDataTable_btn.setFont(new java.awt.Font("Tahoma", 0, 10));
        seeDataTable_btn.setText("See data");
        seeDataTable_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seeDataTable_btnActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        fileChooserPanel.add(seeDataTable_btn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(fileChooserPanel, gridBagConstraints);

        tablePanel.setLayout(new java.awt.GridBagLayout());

        tablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Table Panel", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102)));
        tablePanel.setMinimumSize(new java.awt.Dimension(230, 180));
        tablePanel.setPreferredSize(new java.awt.Dimension(230, 180));
        data_sp.setMinimumSize(new java.awt.Dimension(210, 115));
        data_sp.setPreferredSize(new java.awt.Dimension(210, 115));
        data_tb.setModel(new DataFileTableModel(data));
        data_tb.setMinimumSize(new java.awt.Dimension(230, 150));
        data_sp.setViewportView(data_tb);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        tablePanel.add(data_sp, gridBagConstraints);

        statCompute_btn.setFont(new java.awt.Font("Tahoma", 0, 10));
        statCompute_btn.setText("Compute Stats");
        statCompute_btn.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        tablePanel.add(statCompute_btn, gridBagConstraints);

        plotFile_btn.setFont(new java.awt.Font("Tahoma", 0, 10));
        plotFile_btn.setText("Plot Graph");
        plotFile_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plotFile_btnActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        tablePanel.add(plotFile_btn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        getContentPane().add(tablePanel, gridBagConstraints);

        statPanel.setLayout(new java.awt.GridBagLayout());

        statPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Statistics Panel", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102)));
        statPanel.setMaximumSize(new java.awt.Dimension(230, 32767));
        statPanel.setMinimumSize(new java.awt.Dimension(230, 180));
        statPanel.setPreferredSize(new java.awt.Dimension(230, 180));
        statBluePanel.setLayout(new java.awt.GridBagLayout());

        stats_sp.setMinimumSize(new java.awt.Dimension(210, 130));
        stats_sp.setPreferredSize(new java.awt.Dimension(210, 130));
        stats_ta.setColumns(20);
        stats_ta.setRows(5);
        stats_ta.setText("Mean | Standard Deviation\n----------------------\n6.04 | 0.343        ");
        stats_ta.setMinimumSize(new java.awt.Dimension(1400, 1000));
        stats_ta.setPreferredSize(new java.awt.Dimension(1400, 1000));
        stats_sp.setViewportView(stats_ta);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        statBluePanel.add(stats_sp, gridBagConstraints);

        print_btn.setFont(new java.awt.Font("Tahoma", 0, 10));
        print_btn.setText("Print");
        print_btn.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        statBluePanel.add(print_btn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        statPanel.add(statBluePanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(statPanel, gridBagConstraints);

        chart_scrp.setBorder(null);
        chart_scrp.setMinimumSize(new java.awt.Dimension(530, 480));
        chart_scrp.setPreferredSize(new java.awt.Dimension(537, 490));
        chartPanel.setLayout(new javax.swing.BoxLayout(chartPanel, javax.swing.BoxLayout.X_AXIS));

        chartPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Graph", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102)));
        chartPanel.setMaximumSize(new java.awt.Dimension(535, 32767));
        chartPanel.setMinimumSize(new java.awt.Dimension(535, 500));
        chartPanel.setPreferredSize(new java.awt.Dimension(535, 500));
        chart_scrp.setViewportView(chartPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(chart_scrp, gridBagConstraints);

    }// </editor-fold>//GEN-END:initComponents
    
    //TODO: create a mini frame to report common message.
    private javax.swing.JFrame popUp(){
        javax.swing.JFrame frame = new javax.swing.JFrame();
        int minimum = 0;
        int maximum = 100;
        javax.swing.JProgressBar progressBar = new javax.swing.JProgressBar(minimum, maximum);
        progressBar.setStringPainted(true);
        
        frame.setTitle("Message");
        frame.setSize(200,100);
        
        // Create a horizontal progress bar
        frame.getContentPane().add(progressBar);
        return frame;
    }
    
    private void plotFile_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plotFile_btnActionPerformed
        
        try{
            //TODO: BufferedImage protection.
            
            // record the current time and plot the file.
            final long s_time =  System.currentTimeMillis();//time of loading
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            
            //Create a progress monitor and the task.
            task = new LongTask();
            progressMonitor = new javax.swing.ProgressMonitor(MainApplet.this, "Plotting a Long Task", "", 0, task.getLengthOfTask());
            
            //Create a timer.
            timer = new javax.swing.Timer(1000, new TimerListener());
            task.go();
            timer.start();
        
            
            //create the chart
            LineChartPanel realChart = new LineChartPanel(data, source, currentFile);
            java.awt.Dimension area = chartPanel.getSize();
            area.height = area.height + 270;//update to a bigger size.
            chartPanel.setLayout(new java.awt.FlowLayout());
            chartPanel.setPreferredSize(area);//new size
            
            chartPanel.add(realChart);
            chartPanel.revalidate();//Panel revalidate
            
            //report taken time of ploting the graph.
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    long t = System.currentTimeMillis() - s_time;
                    stats_ta.append("\nElapsed time for plotting " + currentFile +
                            " graph is " + t + " milliseconds");
                }
            });
            
        } catch (java.lang.Exception e){
            e.printStackTrace();
        }//end try and catch
    }//GEN-LAST:event_plotFile_btnActionPerformed
    //end plotFile_btnActionPerformed
    /**
     * The actionPerformed method in this class
     * is called each time the Timer "goes off".
     */
    class TimerListener implements java.awt.event.ActionListener {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            progressMonitor.setProgress(task.getCurrent());
            String s = task.getMessage();
            if (s != null) {
                progressMonitor.setNote(s);
                //taskOutput.append(s + newline);
                //taskOutput.setCaretPosition(taskOutput.getDocument().getLength());
            }
            if (progressMonitor.isCanceled() || task.isDone()) {
                progressMonitor.close();
                task.stop();
                java.awt.Toolkit.getDefaultToolkit().beep();
                timer.stop();
                if (task.isDone()) {
                    //taskOutput.append("Task completed." + newline);
                } else {
                    //taskOutput.append("Task canceled." + newline);
                }
            }
        }
    }
    
    /* When user click on to see the data of the file
     *
     **/
    private void seeDataTable_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seeDataTable_btnActionPerformed
        
        if (file_lst.getSelectedValue() == null) return;
        
        try {
            
            final long s_time =  System.currentTimeMillis();//time of loading
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            
            String selectedFile = file_lst.getSelectedValue().toString();
            source = new java.net.URL(getCodeBase(), "data/" + selectedFile);
            readFileToTable(source);//put the vector into data vector.
            setCurrentFile(source.toString());
            model = new DataFileTableModel(data);
            
            System.out.println("Current file is " + currentFile);
            System.out.println("Number of rows is " + model.getRowCount());
            
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    long t = System.currentTimeMillis() - s_time;
                    stats_ta.append("\nElapsed time for loading file "
                            + currentFile + " into table is " + t + " milliseconds");
                }
            });
            
        } catch (java.net.MalformedURLException e) {
            System.out.println("Malformed URL = " + e);
            return;
        } catch(java.io.IOException io){
            System.out.println("IOException throws " + io);
            return;
        }
        
    }//GEN-LAST:event_seeDataTable_btnActionPerformed
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addFile_btn;
    private javax.swing.JPanel chartPanel;
    private javax.swing.JScrollPane chart_scrp;
    private javax.swing.JScrollPane data_sp;
    private javax.swing.JTable data_tb;
    private javax.swing.JButton deleteFile_btn;
    private javax.swing.JPanel fileChooserPanel;
    private javax.swing.JList file_lst;
    private javax.swing.JScrollPane file_sp;
    private javax.swing.JButton plotFile_btn;
    private javax.swing.JButton print_btn;
    private javax.swing.JButton seeDataTable_btn;
    private javax.swing.JPanel statBluePanel;
    private javax.swing.JButton statCompute_btn;
    private javax.swing.JPanel statPanel;
    private javax.swing.JScrollPane stats_sp;
    private javax.swing.JTextArea stats_ta;
    private javax.swing.JPanel tablePanel;
    // End of variables declaration//GEN-END:variables
    
}
