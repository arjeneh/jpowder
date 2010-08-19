/* ===========================================================
 * This file is part of Jpowder, see <http://www.jpowder.org/>
 * ===========================================================
 *
 * Jpowder is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jpowder is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * ---------
 * GSAS_Instrument.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.dataset;

/**
 *This class holds all the GSAS instrument file data and the calculate the
 * value of Dspacing in order to convert data from TOF to Dspacing.
 * 
 */
public class GSAS_Instrument {

    private double difC;
    private double difA;
    private double zero;
    private double d;
    private double tof;

    /**
     *
     * @param difc
     * @param difa
     * @param zero
     */
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
/**
 * Solves the quadratic equation (Ad^2+C*d+(zero-t)=0) for d
 * if Quadratic equation has more than one answer then it returns
 * any which is bigger.
 * @param tof
 * @return d
 */
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

   /**
    * Solves quadratic equation for TOF. So data can be converted back
    * into the original form.
    *
    * @param dspacing
    * @return tof
    */
    public double toTOF(double dspacing){

        tof = (difA*Math.pow(dspacing, 2))+(difC*dspacing)+zero;
 
        return tof;
    }


}
