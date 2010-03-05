
import java.io.FileInputStream;
import java.io.ObjectInputStream;
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
                FileInputStream f = new FileInputStream("testing.ser");
                ObjectInputStream charts = new ObjectInputStream(f);
                serializedChart = (JFreeChart) charts.readObject();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.toString());
                System.out.println(""+e);
            }
//        } else {
//            return null;
//        }
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
