/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.dataset;

import java.util.Vector;

/**
 *
 * @author qyt21516
 */
public class GSAS_Instrument {

    private double difC;
    private double difA;
    private double zero;
    private double d;
    private Vector<Double> newX;

    public GSAS_Instrument(double difc, double difa, double zero) {


        this.difA = difa;
        this.difC = difc;
        this.zero = zero;

    }

    public void setDifC(double difc) {
        difC = difc;
    }

    public double getDifC() {
        return difC;
    }

    public void setDifA(double difa) {
        difA = difa;
    }

    public double getDifA() {
        return difA;
    }

    public void setZero(double zro) {
        zero = zro;
    }

    public double getZero() {
        return zero;
    }

    public double toDspacing(double tof) {



        d = Math.pow(difC, 2) - 4 * (difA * (zero - tof));

        if (d < 0) {
            System.out.println("there is no real solution");
            javax.swing.JOptionPane.showMessageDialog(null, "Invalid data.");
        } else if (d == 0) {
            System.out.print("there is only one solution: ");
            System.out.println(-difC / 2 * difA);
            return -difC / 2 * difA;
        } else {
            double x1 = (-difC + Math.sqrt(d)) / (2 * difA);
            double x2 = (-difC - Math.sqrt(d)) / (2 * difA);

            if (x1 > x2) {
                return x1;
            }
            if (x1 < x2) {
                return x2;
            }

        }
//        System.out.println("HIIIIIIIII" + d);
        return d;
    }

    
//  public Vector<Double> toDspacing(Vector<Double> tof) {
//
//      
//   d = Math.pow(difC, 2) - 4 * (difA * (zero - tof.get(index)));
//
//        if (d < 0) {
//            System.out.println("there is no real solution");
//            javax.swing.JOptionPane.showMessageDialog(null, "Invalid data.");
//        } else if (d == 0) {
//            System.out.print("there is only one solution: ");
//            System.out.println(-difC / 2 * difA);
//            return -difC / 2 * difA;
//        } else {
//            double x1 = (-difC + Math.sqrt(d)) / (2 * difA);
//            double x2 = (-difC - Math.sqrt(d)) / (2 * difA);
//
//            if (x1 > x2) {
//                return x1;
//            }
//            if (x1 < x2) {
//                return x2;
//            }
//
//        }
//
//        return newX;
//      }
}
