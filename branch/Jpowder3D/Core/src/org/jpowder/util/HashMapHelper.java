/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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

    public static void main(String[] args) {

        HashMap<Integer, Human> newMap = new HashMap<Integer, Human>();

        Human one = new Human("1");
        Human two = new Human("2");
        Human three = new Human("3");
        Human four = new Human("4");
        Human five = new Human("5") ;
        Human six = new Human("6");

        newMap.put(1, one);
        newMap.put(2, two);
        newMap.put(3, three);
        newMap.put(4, four);
        newMap.put(5, five);
        newMap.put(6, six);

        System.out.println(getKeyByValue(newMap, five));

    }
}
