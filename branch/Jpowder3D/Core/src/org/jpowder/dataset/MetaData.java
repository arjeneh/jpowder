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
