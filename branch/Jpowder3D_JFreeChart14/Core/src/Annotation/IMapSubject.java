/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Annotation;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Toshiba
 */
public interface IMapSubject {

    public void addObserver(IMapObserver o);

    public void removeObserver(IMapObserver o);

    public Map getStatusUpdate();

    public void setStatusUpdate(Map state);
}
