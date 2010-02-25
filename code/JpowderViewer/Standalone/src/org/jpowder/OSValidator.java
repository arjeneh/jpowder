/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jpowder;

/**
 *
 * @author qyt21516
 */
public class OSValidator{



	public static boolean isWindows(){

		String os = System.getProperty("os.name").toLowerCase();
		//windows
	    return (os.indexOf( "win" ) >= 0);

	}

	public static boolean isMac(){

		String os = System.getProperty("os.name").toLowerCase();
		//Mac
	    return (os.indexOf( "mac" ) >= 0);

	}

	public static boolean isUnix(){

		String os = System.getProperty("os.name").toLowerCase();
		//linux or unix
	    return (os.indexOf( "nix") >=0 || os.indexOf( "nux") >=0);

	}
}
