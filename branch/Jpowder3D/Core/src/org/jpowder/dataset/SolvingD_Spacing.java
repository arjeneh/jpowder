/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jpowder.dataset;

import java.util.Scanner;

/**
 *
 * @author qyt21516
 */
public class SolvingD_Spacing {
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author qyt21516
 */


    public static void main(String[] args) {

        System.out.println("\nprogram Quadratic equation................");
        Scanner mo = new Scanner(System.in);// store data where mo is varible


        for (int i = 0; i < 5; i++) {

            double a, b, c, d;

            System.out.print("enter value for a: ");
            a = mo.nextDouble();// this mo.nextDouble stores anything that u input into the a
            System.out.print("enter value for b: ");
            b = mo.nextDouble();
            System.out.print("enter value for c: ");
            c = mo.nextDouble();

            d = (Math.pow(b, 2)) - (4 * a * c);
            System.out.println(d);


            if (d < 0) {
                System.out.println("there is no real solution");
            } else if (d == 0) {
                System.out.print("there is only one solution: ");
                System.out.println(-b / (2 * a));
            } else {
                 System.out.print("there are two solution: ");
                double x1=(-b + Math.sqrt(d)) / (2 * a);
                double x2=(-b - Math.sqrt(d)) / (2 * a);

//                System.out.println("X1 = " + (-b + Math.sqrt(d)) / (2 * a));
//                System.out.println("X2 = " + (-b - Math.sqrt(d)) / (2 * a));
                  if(x1>x2){
                      System.out.println("X1: "+x1);
            }if(x1<x2){
                System.out.println("X2: "+x2);
            }

            }
            System.out.println("to start again perss 6");
            System.out.println("press anything to terminate");
            //good stuff
            String answer;
            answer = mo.next();

            if (answer.equals("6")) {
                System.out.println("We now continue");
            } else {
                break;
            }

        }
    }
}


