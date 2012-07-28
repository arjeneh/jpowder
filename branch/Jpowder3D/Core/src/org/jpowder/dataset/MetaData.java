/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.dataset;

/**
 *
 * @author Toshiba
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
        System.out.printf("Value after setting value: %d%n", mInteger.getValue());
        //output:  id after setting id: 2
    }
}
