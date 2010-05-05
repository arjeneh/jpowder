/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder;

import java.util.ResourceBundle;

/**
 * this class gets the buid date which has been stored in the
 * Jpowderversion.properties file. the properties file gets updated each time
 * the project get buid.
 * @author qyt21516
 */
public class BuidDate {

    public String getVersion() {
        String buidDate = ResourceBundle.getBundle("org/jpowder/Jpowderversion").getString("version.date");

        return buidDate;

    }

//    public static void main(String[] args) {
//        BuidDate buidDate = new BuidDate();
//        buidDate.getVersion();
//    }
}
