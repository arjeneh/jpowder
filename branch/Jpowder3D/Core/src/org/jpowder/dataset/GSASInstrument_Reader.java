/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.dataset;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author qyt21516
 */
public class GSASInstrument_Reader {

    private static double t = 1;//time of flight
    private static double d;//d spacing
    private static Vector<Vector<String>> aBC = new Vector<Vector<String>>();

    public static void read(Vector<DataSet> dataset, File aFile) {
        FileReader fileReader = null;
        try {
            String aLine;
            fileReader = new FileReader(aFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);


            while ((aLine = bufferedReader.readLine()) != null) {
//                System.out.println(aLine);

                if (!aLine.isEmpty()) {

                    if (aLine.contains("ICONS")) {

//                        System.out.println(aLine);

                        StringTokenizer stringTokenizer = new StringTokenizer(aLine);
                        int numToken = stringTokenizer.countTokens();

                        Vector<String> str = new Vector<String>();

                        for (int i = 0; i < numToken; i++) {

                            String stringToken = stringTokenizer.nextToken();
                            str.add(stringToken);

                        }

                        aBC.addElement(str);


//                          for(String i:splits)
//                          System.out.print(i);
//t=zero+difC*d+difA*d^2
                        //Ad^2+C*d+(zero-t)=0



//
//                         d = (Math.pow(Double.parseDouble(aBC.get(0).get(4)), 2)) -
//                                 (4 * Double.parseDouble(aBC.get(0).get(3)) *
//                                 Double.parseDouble(aBC.get(0).get(5)));
//                         System.out.println(d);


                    }
                }

            }

//            d = Math.pow(Double.parseDouble(aBC.get(0).get(3)), 2) -
//                    4 * (Double.parseDouble(aBC.get(0).get(4)) *
//                    ((Double.parseDouble(aBC.get(0).get(5))) - t));
//            System.out.println(d);
//
//            if (d < 0) {
//                System.out.println("there is no real solution");
//
//            } else if (d == 0) {
//                System.out.print("there is only one solution: ");
//                System.out.println(-Double.parseDouble(aBC.get(0).get(3)) / (2 * Double.parseDouble(aBC.get(0).get(4))));
//            } else {
//                System.out.println("X1 = " + (-Double.parseDouble(aBC.get(0).get(3)) + Math.sqrt(d)) / (2 * Double.parseDouble(aBC.get(0).get(4))));
//                System.out.println("X2 = " + (-Double.parseDouble(aBC.get(0).get(3)) - Math.sqrt(d)) / (2 * Double.parseDouble(aBC.get(0).get(4))));
//            }
            //d=2.08838047456E7
// enter value for a: -0.66
//enter value for b: 4569.88
//enter value for c: 0.58
//X1 = -1.2691799094516847E-4
//X2 = 6924.060732978597

            System.out.println(Double.parseDouble(aBC.get(0).get(4)) + "d^2 + " +
                    Double.parseDouble(aBC.get(0).get(3)) + "d + " +
                    Double.parseDouble(aBC.get(0).get(5)) + "-t" + "=0");
        } catch (IOException ex) {
            Logger.getLogger(GSASInstrument_Reader.class.getName()).log(Level.SEVERE, null, ex);

        }

        //

        for (int i = 0; i < dataset.size(); i++) {
            double difc = Double.parseDouble(aBC.get(i).get(3));
            double difa = Double.parseDouble(aBC.get(i).get(4));
            double zero = Double.parseDouble(aBC.get(i).get(5));

            GSAS_Instrument inst = new GSAS_Instrument(difc, difa, zero);
            dataset.elementAt(i).setGSAS_Instrument(inst);
        }
    }

    public static Vector<Vector<String>> getABC() {
        return aBC;
    }
}
