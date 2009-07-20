package org.jpowder.fileCabinet;

/**
 * Any class that wants to be updated when a new powder diffraction file is read
 * into Jpowder must implement this interface
 *
 * @author Kreecha Puphaiboon
 */
public interface PowderFileObserver {

    /**
     * Method which does the updating
     *
     * @param data powder diffraction data.
     */
    public void powderFileCabinetUpdate (Subject data);

}

