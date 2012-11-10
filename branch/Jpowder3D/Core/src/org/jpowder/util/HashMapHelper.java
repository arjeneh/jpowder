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
 * HashMapHelper.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * @author     Kreecha Puphaiboon, Computer Science Lecturer, Kasem Bundit University
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HashMapHelper {

    public static String[] convertKeyToArray(HashMap hm) {
        String[] keys =
                (String[]) (hm.keySet().toArray(new String[hm.size()]));
        return keys;
    }

    /**
     *<P>If your data structure has many-to-one mapping
     * between keys and values you should iterate over entries
     * and pick all suitable keys:
     */
    public static <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) {
        Set<T> keys = new HashSet<T>();
        for (Entry<T, E> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    /**
     *<P>For one-to-one relationship you can return the first matched key:
     */
    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Entry<T, E> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static String[][] convertMapTo2DArray(Map map) {
        Map<String, String> memoMap = map;
        String[][] data = new String[memoMap.size()][];
        int ii = 0;
        for (Map.Entry<String, String> entry : memoMap.entrySet()) {
            data[ii++] = new String[]{entry.getKey(), entry.getValue()};
        }
        return data;
        //final DefaultTableModel model = new DefaultTableModel(data, new String[]{"Memo", "ID"});
    }
//    public static void main(String[] args) {
//
//        HashMap<Integer, Human> newMap = new HashMap<Integer, Human>();
//
//        Human one = new Human("1");
//        Human two = new Human("2");
//        Human three = new Human("3");
//        Human four = new Human("4");
//        Human five = new Human("5") ;
//        Human six = new Human("6");
//
//        newMap.put(1, one);
//        newMap.put(2, two);
//        newMap.put(3, three);
//        newMap.put(4, four);
//        newMap.put(5, five);
//        newMap.put(6, six);
//
//        System.out.println(getKeyByValue(newMap, five));
//
//    }
}
