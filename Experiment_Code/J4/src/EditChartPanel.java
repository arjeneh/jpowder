/*
 * EditChartPanel.java
 *
 * Created on 28 April 2007, 17:46
 */

/**
 *
 * @author  regtrain
 */
public class EditChartPanel extends javax.swing.JPanel {
    
    /** Creates new form EditChartPanel */
    public EditChartPanel() {
        initComponents();
    }
    //TODO: Look into PanScrollZoomDemo.java to see how to disable some popupmenu action
    //Look at Code: ChartPanel.doSaveAs(), ChartPanel.createChartPrintJob() 
    
    public void addChartToBigChartPanel(javax.swing.JPanel panel){
        bigChartPanel.add(panel);
        bigChartPanel.revalidate();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        butPanel = new javax.swing.JPanel();
        zoomOutGraph_btn = new javax.swing.JButton();
        zoomInGraph_btn = new javax.swing.JButton();
        save_btn = new javax.swing.JButton();
        editGraph_btn = new javax.swing.JButton();
        bigChart_sp = new javax.swing.JScrollPane();
        bigChartPanel = new javax.swing.JPanel();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Edit Chart", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153)));
        setMinimumSize(new java.awt.Dimension(800, 530));
        setPreferredSize(new java.awt.Dimension(800, 530));
        setLayout(new java.awt.GridBagLayout());

        butPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        butPanel.setMinimumSize(new java.awt.Dimension(798, 40));
        butPanel.setPreferredSize(new java.awt.Dimension(800, 40));

        zoomOutGraph_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/zoomo_small.gif"))); // NOI18N
        zoomOutGraph_btn.setToolTipText("Zoom out the graph");
        zoomOutGraph_btn.setIconTextGap(2);
        zoomOutGraph_btn.setMaximumSize(new java.awt.Dimension(40, 23));
        zoomOutGraph_btn.setMinimumSize(new java.awt.Dimension(40, 23));
        zoomOutGraph_btn.setPreferredSize(new java.awt.Dimension(40, 23));
        butPanel.add(zoomOutGraph_btn);

        zoomInGraph_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/zoomi_small.gif"))); // NOI18N
        zoomInGraph_btn.setToolTipText("Zoom in the graph");
        zoomInGraph_btn.setIconTextGap(2);
        zoomInGraph_btn.setMaximumSize(new java.awt.Dimension(40, 23));
        zoomInGraph_btn.setMinimumSize(new java.awt.Dimension(40, 23));
        zoomInGraph_btn.setPreferredSize(new java.awt.Dimension(40, 23));
        butPanel.add(zoomInGraph_btn);

        save_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/savas_small.gif"))); // NOI18N
        save_btn.setToolTipText("Zoom in the graph");
        save_btn.setIconTextGap(2);
        save_btn.setMaximumSize(new java.awt.Dimension(40, 23));
        save_btn.setMinimumSize(new java.awt.Dimension(40, 23));
        save_btn.setPreferredSize(new java.awt.Dimension(40, 23));
        butPanel.add(save_btn);

        editGraph_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit_small.gif"))); // NOI18N
        editGraph_btn.setToolTipText("Zoom in the graph");
        editGraph_btn.setIconTextGap(2);
        editGraph_btn.setMaximumSize(new java.awt.Dimension(40, 23));
        editGraph_btn.setMinimumSize(new java.awt.Dimension(40, 23));
        editGraph_btn.setPreferredSize(new java.awt.Dimension(40, 23));
        butPanel.add(editGraph_btn);

        add(butPanel, new java.awt.GridBagConstraints());

        bigChart_sp.setMinimumSize(new java.awt.Dimension(800, 465));
        bigChart_sp.setPreferredSize(new java.awt.Dimension(800, 465));

        bigChartPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        bigChartPanel.setMaximumSize(new java.awt.Dimension(800, 465));
        bigChartPanel.setMinimumSize(new java.awt.Dimension(800, 465));
        bigChartPanel.setPreferredSize(new java.awt.Dimension(800, 465));
        bigChart_sp.setViewportView(bigChartPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(bigChart_sp, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bigChartPanel;
    private javax.swing.JScrollPane bigChart_sp;
    private javax.swing.JPanel butPanel;
    private javax.swing.JButton editGraph_btn;
    private javax.swing.JButton save_btn;
    private javax.swing.JButton zoomInGraph_btn;
    private javax.swing.JButton zoomOutGraph_btn;
    // End of variables declaration//GEN-END:variables
    
}
