/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.util;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Toshiba
 */
public class StringUtil {

    /**
     * Capitalize the first character of the given string.
     *
     * @param string     String to capitalize.
     * @return           Capitalized string.
     *
     * @throws IllegalArgumentException    String is <kk>null</kk> or empty.
     */
    public static String capitalize(final String string) {
        if (string == null) {
            throw new NullPointerException("string");
        }
        if (string.equals("")) {
            throw new NullPointerException("string");
        }

        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }

    public static ArrayList<String> createStringList(String... values) {
        ArrayList<String> results = new ArrayList<String>();
        Collections.addAll(results, values);
        return results;
    }

    public static String[] convertToStringArray(ArrayList list) {
        return (String[]) list.toArray(new String[0]);
    }

    public static String[] removeItemFromArray(String[] array, String item) {
        ArrayList list = createStringList(array);
        list.remove(item);
        return convertToStringArray(list);
    }

    public static String[] removeItemFromArray(String[] array, int index) {
        ArrayList list = createStringList(array);
        list.remove(index);
        return convertToStringArray(list);
    }
}
