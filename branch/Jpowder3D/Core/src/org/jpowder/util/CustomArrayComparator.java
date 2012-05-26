/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Toshiba
 */
public class CustomArrayComparator implements Comparator<String> {

    private String[] pointsArray;
    private Map<String, Integer> idsMap;

    public CustomArrayComparator(String[] pointsArray, String[] countriesArray) {
        this.pointsArray = pointsArray;
        idsMap = new HashMap<String, Integer>();
        for (int i = 0; i < pointsArray.length; i++) {
            idsMap.put(countriesArray[i], i);
        }
    }

    @Override
    public int compare(String s1, String s2) {
        return pointsArray[idsMap.get(s2)].compareTo(pointsArray[idsMap.get(s1)]);
    }

    public static void main(String[] args) {
        String[] order = {"1", "5", "2", "4", "3"};
        String[] files = {"nepal", "japan", "finland", "brazil", "spain"};

        CustomArrayComparator comparator = new CustomArrayComparator(order, files);
        Arrays.sort(files, comparator);
        Arrays.sort(order, Collections.reverseOrder());

        System.out.println(Arrays.toString(order));
        System.out.println(Arrays.toString(files));
    }
}

