package edu.iastate.cs228.hw1;

import java.io.File;

/**
 * 
 * @author  Bryce Jensenius
 *
 */

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * 
 * This class sorts all the points in an array of 2D points to determine a reference point whose x and y 
 * coordinates are respectively the medians of the x and y coordinates of the original points. 
 * 
 * It records the employed sorting algorithm as well as the sorting time for comparison. 
 *
 */
public class PointScanner  
{
	private Point[] points; //array of points that are inputed
	
	private Point medianCoordinatePoint;  // point whose x and y coordinates are respectively the medians of 
	                                      // the x coordinates and y coordinates of those points in the array points[].
	private Algorithm sortingAlgorithm;    
		
	protected long scanTime; 	       // execution time in nanoseconds. 
	
	/**
	 * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy 
	 * the points into the array points[].
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException
	{
		if(pts == null || pts.length == 0) {
			throw new IllegalArgumentException();
		}
		points = new Point[pts.length];
		
		for(int i = 0; i < pts.length; i++) {
			Point temp = new Point(pts[i]);
			points[i] = temp;
		}
		
		sortingAlgorithm = algo;
	}

	
	/**
	 * This constructor reads points from a file. 
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   if the input file contains an odd number of integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException
	{
		File fp = new File(inputFileName);
		Scanner length_scnr = new Scanner(fp);//two scanners because we need to know the length and verify it is even before initializing Points[]
		Scanner val_scnr = new Scanner(fp);
		int valCounter = 0;
		sortingAlgorithm = algo;
		
		while(length_scnr.hasNextInt()) {//search through file with first scanner to get length and see if even
			length_scnr.nextInt();
			valCounter++;
		}
		
		if(valCounter % 2 != 0) {//not even number of ints so exit
			length_scnr.close();
			val_scnr.close();
			throw new InputMismatchException();
		}
		
		points = new Point[valCounter/2];
		
		for(int i = 0; i < points.length; i++) {//run through file with second scanner to input ints in point list
			points[i] = new Point(val_scnr.nextInt(), val_scnr.nextInt());
		}
		
		length_scnr.close();
		val_scnr.close();
	}

	
	/**
	 * Carry out two rounds of sorting using the algorithm designated by sortingAlgorithm as follows:  
	 *    
	 *     a) Sort points[] by the x-coordinate to get the median x-coordinate. 
	 *     b) Sort points[] again by the y-coordinate to get the median y-coordinate.
	 *     c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates.     
	 *  
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter,
	 * or QuickSorter to carry out sorting.       
	 * @param algo
	 * @return
	 */
	public void scan()
	{
		// TODO  
		AbstractSorter aSorter; 
		
		//assign to correct sort algorithm
		if(sortingAlgorithm == Algorithm.SelectionSort) {
			aSorter = new SelectionSorter(points);
		}else if(sortingAlgorithm == Algorithm.InsertionSort) {
			aSorter = new InsertionSorter(points);
		}else if(sortingAlgorithm == Algorithm.MergeSort) {
			aSorter = new MergeSorter(points);
		}else {
			aSorter = new QuickSorter(points);
		}
		
		Point xSortMedian;
		Point ySortMedian;
		
		/*
		 * Grab time before scanning with System.nanoTime()
		 * subtract System.nanoTime() of right after the sort 
		 * with start sort time to get total sort time
		 */
		aSorter.setComparator(0);
		long startTime = System.nanoTime();
		aSorter.sort();//sort points by x
		long sortTime1 = System.nanoTime() - startTime;
		xSortMedian = aSorter.getMedian();//grab median for x value
		
		aSorter.setComparator(1);
		startTime = System.nanoTime();
		aSorter.sort();//sort Points by y
		long sortTime2 = System.nanoTime() - startTime;
		ySortMedian = aSorter.getMedian();//grab median for y value
		
		medianCoordinatePoint = new Point(xSortMedian.getX(), ySortMedian.getY());//make point with x sort median and y sort median
		scanTime = sortTime1 + sortTime2;
	}
	
	
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description. 
	 */
	public String stats()
	{
		//establish String and print writers to format string output with precision
		//could do manually with if else but I wanted to test this
		StringWriter stringWrite = new StringWriter();//memory stream to be written to
		PrintWriter printWrite = new PrintWriter(stringWrite);//allows writing to the string stream of stringwrite
		
		printWrite.printf("%-15s", sortingAlgorithm.toString());//left align and print 15 characters total so everything lines up
		printWrite.printf(" %-8d%d", points.length, scanTime);//left align and print in consistent format
		
		return stringWrite.toString();
	}
	
	
	/**
	 * Write MCP after a call to scan(),  in the format "MCP: (x, y)"   The x and y coordinates of the point are displayed on the same line with exactly one blank space 
	 * in between. 
	 */
	@Override
	public String toString()
	{
		return "MCP: " + medianCoordinatePoint.toString();
	}

	
	/**
	 *  
	 * This method, called after scanning, writes point data into a file by outputFileName. The format 
	 * of data in the file is the same as printed out from toString().  The file can help you verify 
	 * the full correctness of a sorting result and debug the underlying algorithm. 
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile() throws FileNotFoundException
	{
		
		try {
			FileWriter fWrite = new FileWriter(sortingAlgorithm.toString() + "MCPOutput.txt");
			fWrite.write(this.toString());
			fWrite.close();
		} catch (IOException e) {
			System.out.println("Failed to Create Output File.");
			System.out.println("Continuing Execution.");
		}
		
	}		
}
