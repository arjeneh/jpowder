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
 * StringUtil.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * @author     Kreecha Puphaiboon, Computer Science Lecturer, Kasem Bundit University
 * @version 2. Support for parsing string
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

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

    /**
     *
     * @param str
     * @param chr
     * @return bits of string
     */
    public static String[] getSplit(String str, String chr) {
        Pattern p = Pattern.compile(chr, Pattern.LITERAL);
        String[] temp = p.split(str);

//        for (int i = 0; i < temp.length; i++) {
//            System.out.println(temp[i]);
//        }
        return temp;
    }
}
