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
 *  MetaData.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  Kreecha Puphaiboon, Computer Science Department, Kasem Bundit University
 *             Anders Marvardsen, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.dataset;

/**
 * Hold any type of data.
 *
 * V.1 Double and String.
 *
 */
public class MetaData<T> {

    private T value;

    public MetaData(T id) {
        this.value = id;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T id) {
        this.value = id;
    }

    /**
     * Intended only for debugging.
     *
     * <P>Here, the contents of every field are placed into the result, with
     * one field per line.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");

        result.append(this.getClass().getName() + " Object {" + NEW_LINE);
        result.append(" value: " + this.getValue() + NEW_LINE);
        result.append("}");

        return result.toString();
    }

    public static void main(String[] args) {
        MetaData<String> mString = new MetaData<String>("Pressure");
        mString.setValue("LOW");
        System.out.printf("Value after setting value: %s%n", mString.getValue());
        //output:  id after setting id: id2

        MetaData<Integer> mInteger = new MetaData<Integer>(1);
        mInteger.setValue(2);
        MetaData fisse = mInteger;
        Integer uu = (Integer) fisse.getValue();
        System.out.printf("Value after setting value: %d%n", fisse.getValue());
        System.out.printf("Value after setting value: %s%n", fisse.getValue().getClass().getName());
        //output:  id after setting id: 2
    }
}
