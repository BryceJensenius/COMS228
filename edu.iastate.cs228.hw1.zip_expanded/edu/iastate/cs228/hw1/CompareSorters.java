package edu.iastate.cs228.hw1;

/**
 *  
 * @author Bryce Jensenius
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random; 


public class CompareSorters 
{
	/*
	 * formatting string to be printed out
	 */
	private static final String bar = "-----------------------------------";
	
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{		
		PointScanner[] scanners = new PointScanner[4]; 
		
		Scanner scnr = new Scanner(System.in);//scanner for user input
		
		int response = 0;//store user responses
		int numPoints = 0;//number of points to generate
		int trial = 1;//current sort trial
			
		//print introductory messages once
		System.out.println("Performances of Four Sorting Algorithms in Point Scanning\n");
		System.out.println("keys: 1 (random integers) 2 (file input) 3 (exit)");
		
		while(true) {//intentionally infinite loop that exits when you input 3, or anything but 1 and 2
			String fileName = null;
			boolean validInput = false;
		
			//check which point input method will be used until valid int is given
			while(!validInput) {
				System.out.printf("Trial %d: ", trial);
				
				try {
					response = scnr.nextInt();
					validInput = true;
				}catch(InputMismatchException e) {//failed to give an integer
					System.out.println("Enter 1 2 or 3\n");
					scnr.next();
				}
			}
			
			validInput = false;
			
			if(response == 1) {//generate random numPoints
				while(!validInput) {//run until a valid int is given
					System.out.print("Enter number of random points: ");
					try {
						numPoints = scnr.nextInt();
						validInput = true;
					}catch(InputMismatchException e) {//failed to give an integer
						System.out.println("Enter a positive integer\n");
						scnr.next();
					}
				}
				
				Point[] pointList = generateRandomPoints(numPoints, new Random());
				
				//initialize the 4 scanners each with different algorithm to the generated points
				scanners[0] = new PointScanner(pointList, Algorithm.SelectionSort);
				scanners[1] = new PointScanner(pointList, Algorithm.InsertionSort);
				scanners[2] = new PointScanner(pointList, Algorithm.MergeSort);
				scanners[3] = new PointScanner(pointList, Algorithm.QuickSort);
			}else if(response == 2) {//scan from file
				System.out.println("Points from a file");
				System.out.print("File name: ");
				fileName = scnr.next();
				
				//initialize the 4 scanners each with different algorithm to the file where points are contained
				scanners[0] = new PointScanner(fileName, Algorithm.SelectionSort);
				scanners[1] = new PointScanner(fileName, Algorithm.InsertionSort);
				scanners[2] = new PointScanner(fileName, Algorithm.MergeSort);
				scanners[3] = new PointScanner(fileName, Algorithm.QuickSort);
			}else {//exit program
				scnr.close();
				return;
			}
			
			System.out.println("\nalgorithm\tsize\ttime (ns)");
			System.out.println(bar);

			//run scan to sort with all 4 point scanners then print the statistics
			for(PointScanner p : scanners) {
				p.scan();
				System.out.println(p.stats());
			}
			System.out.println(bar + "\n");
			trial++;
		}
	}
	
	
	/**
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] � [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{ 
		
		if(numPts < 1) {
			throw new IllegalArgumentException();
		}
		
		Point[] points = new Point[numPts];
		
		for(int i = 0; i < numPts; i++) {//initialize each point with random x value and random y value in correct range
			points[i] = new Point(rand.nextInt(101) - 50, rand.nextInt(101) - 50);
		}
		
		return points;
	}
	
}
