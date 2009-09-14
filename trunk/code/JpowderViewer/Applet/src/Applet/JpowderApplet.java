/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Applet;

import java.applet.Applet;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * This demo shows a simple bar chart created using the {@link XYSeriesCollection} dataset.
 */
public class JpowderApplet extends ApplicationFrame {

  /**
   * Creates a new demo instance.
   *
   * @param title  the frame title.
   */
  public JpowderApplet(final String title) {
    super(title);
    JFreeChart chart = createChart();
    final ChartPanel chartPanel = new ChartPanel(chart);
    setContentPane(chartPanel);
   
  }

  private JFreeChart createChart() {

    JFileChooser chooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            ".Ser ", "Ser");
    chooser.setFileFilter(filter);
    int returnVal = chooser.showOpenDialog(this);
    File fileName = chooser.getSelectedFile();
    JFreeChart serializedChart = null;
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      try {
        FileInputStream f = new FileInputStream(fileName);
        ObjectInputStream charts = new ObjectInputStream(f);
        serializedChart = (JFreeChart) charts.readObject();
      } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Invalid file!");
      }
    } else {
      return null;
    }
    return serializedChart;
  }
  public static void main(final String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
    }
    final JpowderApplet jpowderapplet = new JpowderApplet("JPowder Crystallograhy Demo");
    jpowderapplet.pack();
    RefineryUtilities.centerFrameOnScreen(jpowderapplet);
    jpowderapplet.setVisible(true);
    jpowderapplet.setDefaultCloseOperation(EXIT_ON_CLOSE);

  }
}

