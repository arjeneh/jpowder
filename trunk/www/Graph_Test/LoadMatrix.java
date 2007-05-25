/**  contains starter code to load a 2D matrix from a file.
  *  Assumes file is a square matrix in the following format:
  *    The first integer in the file is the number of items in each row.
  *    The next integers are the numbers from the matrix in a row-by-row
  *    order, from left to right in each row.
  *
  */

import java.util.*;
import java.io.*;


public class LoadMatrix {

  public static void main(String[] args) throws java.io.FileNotFoundException
  {
	Scanner keyboardscanner = new Scanner(System.in);
	
	String filename;
	
	if (args.length==1)
			filename = args[0]; // input file name in command line argument
	else {
			// ask for input file name
		System.out.println("Enter input file name (for example, test1.txt)");
		filename = keyboardscanner.nextLine();
    }
    
    int input[][] = read2DIntArrayfromFile(filename);
}

// Reads integers from the file whos name is given by parameter filename
// Returns a 2D integer array.
// The first integer is the number of rows, which is assumed to also be
//  the number of columns.
//  The next integers in filename are then read in order, and placed into a 2D
//  array of integers. The array is filled row by row, left to right.
// Reports an error if not enough integers are in the file.
  public static int[][] read2DIntArrayfromFile(String filename) throws java.io.FileNotFoundException
  {
    Scanner filescanner;
	InputStream	instream;
	System.out.println("Reading file " + filename);

	instream = new FileInputStream(filename);
	filescanner = new Scanner(instream);

	int numRows, numCols; // read the number of rows and columns.
	numRows = filescanner.nextInt();
    numCols = numRows;
    int inputArray[][] = new int[numRows][numCols];
    int i=0;
    int j=0;
    System.out.println(
    		numRows + " x " + numCols + " array.");
	int counter =0;
    // read all the data
    for (i=0; i < numRows; i++) {
		for (j=0; j< numCols; j++) {
			if (filescanner.hasNextInt()) {
                  inputArray[i][j] = filescanner.nextInt();
                  counter++;
			}
			else {
				System.out.println("Error reading input; cannot read integer for location ["+//
							i + "][" + j +"].");
				return null;
			}
		}
    }
    
    System.out.println("Read "+ (counter) + " ints into " +	numRows + " x " + numCols + " array.");

   // debugging statements.
  /*  for (i=0; i < numRows; i++) {
		for (j=0; j< numCols; j++)
			System.out.print(inputArray[i][j] + " ");
		System.out.println("");
	} */
    return inputArray;
  }

}