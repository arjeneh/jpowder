package org.jpowder.fileCabinet;

public interface Subject {

    public void registerObserver (PowderFileObserver o);

    public void removeObserver (PowderFileObserver o);

    public void notifyObservers ();

}

