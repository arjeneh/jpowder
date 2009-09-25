/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Applet;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javax.swing.JApplet;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author qyt21516
 */
public class JpowderApplet extends JApplet {


    /**
     * Initialization method that will be called after the applet is loaded
     * into the browser.
     */
  private JFreeChart createChart() {

    JFreeChart serializedChart = null;
    String filename = getParameter("filename");
    File fileName;
    if (filename==null)
   {
      JFileChooser chooser = new JFileChooser();
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
              ".Ser ", "Ser");
      chooser.setFileFilter(filter);
      int returnVal = chooser.showOpenDialog(this);

      if (returnVal == JFileChooser.APPROVE_OPTION) {
        fileName = chooser.getSelectedFile();
        try {
          FileInputStream f = new FileInputStream(fileName);
          ObjectInputStream charts = new ObjectInputStream(f);
          serializedChart = (JFreeChart) charts.readObject();
        } catch (Exception e) {
          JOptionPane.showMessageDialog(this, "Invalid file!");
        }
      }
    } else {
      try {
        FileInputStream f = new FileInputStream(filename);
        ObjectInputStream charts = new ObjectInputStream(f);
        serializedChart = (JFreeChart) charts.readObject();
      } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Invalid file!");
      }
    }
    return serializedChart;
  }
  @Override
    public void init() {
     try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
    }
    JFreeChart chart = createChart();
    final ChartPanel chartPanel = new ChartPanel(chart);
    add(chartPanel);
    }
}


