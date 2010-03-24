/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.jfreechart;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.FontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import java.io.OutputStream;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;

/**
 *@Class name:   XY_PopupMenu.java
@Author: Kreecha Puphaiboon
@Date: 21 May 2007, 09:53
@Modf:
@Description:
 * A general popupmenu which allows the user to enable/disable chart properties e.g.
turn off connecting line, turn off marker,

 * @see javax.swing.JPopupMenu

@Return:
 */
public class XY_PopupMenu extends javax.swing.JPopupMenu {

    private JFreeChart chart;
    private JPopupMenu jFreeChartPopup;
    private ChartPanel chartPanel;
    private XYPlot plot;
    //
    private JMenuItem saveAsSerialize;
    private JMenuItem saveAsPDF;

    public XY_PopupMenu(org.jfree.chart.ChartPanel chartPanel) {

        this.chartPanel = chartPanel;
        this.chart = this.chartPanel.getChart();
        this.jFreeChartPopup = this.chartPanel.getPopupMenu();
        this.plot = (XYPlot) this.chart.getPlot();





        /**
         * Saves the data as a seriliazed object
         */
        //ADD serili
        saveAsSerialize = new javax.swing.JMenuItem("Save as Ser...");
        saveAsSerialize.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent event) {
                System.out.println("Save as serialize");
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        ".Ser ", "Ser");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showSaveDialog(chooser);
                File fileName = chooser.getSelectedFile();
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        FileOutputStream buffer = new FileOutputStream(fileName);
                        final ObjectOutput out = new ObjectOutputStream(buffer);
                        out.writeObject(chart);
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Invalid file!",
                                "error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                }
            }
        });

        /**
         * This Part Save the plotted data as pdf and then open
         * the saved pdf file imdiately'
         */
        //Save As pdf
        saveAsPDF = new javax.swing.JMenuItem("Save as pdf");
        saveAsPDF.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent event) {
                System.out.println("saved as pdf");

                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        ".pdf ", "pdf");
                chooser.setFileFilter(filter);

                int returnVal = chooser.showSaveDialog(chooser);
                File fileName = chooser.getSelectedFile();
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        saveChartAsPDF(fileName, chart, 10000, 10000, new DefaultFontMapper());
                        System.out.println(fileName.getPath());
                        try //try statement
                        {
                            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + fileName.getPath());   //open the file chart.pdf

                        } catch (Exception e) //catch any exceptions here
                        {
                            System.out.println("Error" + e);  //print the error
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

            /**
             * responsible for saving and writng the chart as pdf
             */
            public void saveChartAsPDF(File file,
                    JFreeChart chart,
                    int width,
                    int height,
                    FontMapper mapper) throws IOException {
                OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
                writeChartAsPDF(out, chart, 1500, 2500, mapper);
                out.close();
            }

            public void writeChartAsPDF(OutputStream out,
                    JFreeChart chart,
                    int width,
                    int height,
                    FontMapper mapper) throws IOException {
                Rectangle pagesize = new Rectangle(width, height);
                Document document = new Document(pagesize, 50, 50, 50, 50);
                try {
                    PdfWriter writer = PdfWriter.getInstance(document, out);
                    document.open();
                    PdfContentByte cb = writer.getDirectContent();
                    PdfTemplate tp = cb.createTemplate(width, height);
                    Graphics2D g2 = tp.createGraphics(width, height, mapper);
                    Rectangle2D r2D = new Rectangle2D.Double(0, 0, width, height);
                    chart.draw(g2, r2D);
                    g2.dispose();
                    cb.addTemplate(tp, 0, 0);
                } catch (DocumentException de) {
                    System.err.println(de.getMessage());
                }
                document.close();
            }
        });



        this.jFreeChartPopup.addSeparator();
        this.jFreeChartPopup.add(saveAsSerialize);
        //
        this.jFreeChartPopup.addSeparator();
        this.jFreeChartPopup.add(saveAsPDF);

    }

    public org.jfree.chart.plot.XYPlot getPlot() {
        return plot;
    }

    public void setPlot(org.jfree.chart.plot.XYPlot plot) {
        this.plot = plot;
    }
}
