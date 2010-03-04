package org.jpowder.fileCabinet;

/**
 * Any class that wants to be a data model. This wants to contain observers
 * that can be updated on demand.
 *
 * @author Kreecha Puphaiboon
 * @author Milad Arjeneh
 * @author Anders Markvardsen
 */
public interface Subject {

    /**
     * Method which registers observers
     *
     * @param o The observer which you want to register.
     */
    public void registerObserver (PowderFileObserver o);

    public void removeObserver (PowderFileObserver o);

    /**
     * Method which notify observers
     */
    public void notifyObservers ();

}

