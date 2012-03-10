/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jpowder.util;

import java.util.HashMap;

/**
 *
 * @author Toshiba
 */
public class HashMapHelper {

        public static String[] convertKeyToArray(HashMap hm) {
        String[] keys =
                (String[]) (hm.keySet().toArray(new String[hm.size()]));
        return keys;
    }

}
