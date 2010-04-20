
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.URL;
import javax.swing.JApplet;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author qyt21516
 */
public class JpowderApplet extends JApplet {

    private JFreeChart createChart() {

   JFreeChart serializedChart = null;
//        JFileChooser chooser = new JFileChooser();
//        FileNameExtensionFilter filter = new FileNameExtensionFilter(
//                ".Ser ", "Ser");
//        chooser.setFileFilter(filter);
//        int returnVal = chooser.showSaveDialog(this);
//        File fileName = chooser.getSelectedFile();

//        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
//                FileInputStream f = new FileInputStream("AppletTest1.ser");
                     URL source = new URL(getCodeBase(), "AppletTest1.ser");
//                       BufferedReader br =
//                    new BufferedReader
//                    (new InputStreamReader(source.openStream()));
                ObjectInputStream charts = new ObjectInputStream(source.openStream());
                serializedChart = (JFreeChart) charts.readObject();
            } catch (Exception e) {
//                JOptionPane.showMessageDialog(this, e.toString());
                e.printStackTrace();
            }
//        } else {
//            return null;
//        }
        return serializedChart;
    }

    @Override
    public void init() {

        JFreeChart chart = createChart();
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setSize(300, 300);
        add(chartPanel);
        setVisible(true);

    }
}
