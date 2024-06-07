package edu.iastate.cs228.hw1;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;

/**
 *  
 * @author Bryce Jensenius
 *
 */

/**
 * 
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	// Other private instance variables if needed
	
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		super(pts);
		algorithm = "mergesort";
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 */
	@Override 
	public void sort()
	{
		mergeSortRec(points);
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
		if(pts.length < 2) {
			return;
		}
		Point[] left = new Point[pts.length / 2];
		Point[] right = new Point[pts.length - left.length];
		
		for(int i = 0; i < left.length; i++) {
			left[i] = pts[i];
		}
		for(int j = 0; j < right.length; j++) {
			right[j] = pts[left.length + j];
		}
		mergeSortRec(left);
		mergeSortRec(right);
		merge(pts, left, right);
	}

	
	private void merge(Point[] sortPoints, Point[] pts, Point[] pts2) {
		int firstLeft = 0;
		int firstRight = 0;
		int curIndex = 0;
		
		while(firstLeft < pts.length && firstRight < pts2.length) {
			if(pointComparator.compare(pts[firstLeft], pts2[firstRight]) <= 0) {
				sortPoints[curIndex] = pts[firstLeft];
				firstLeft++;
			}else {
				sortPoints[curIndex] = pts[firstRight];
				firstRight++;
			}
			curIndex++;
		}
		
		while(firstLeft < pts.length) {
			sortPoints[curIndex] = pts[firstLeft++];
			curIndex++;
		}
		while(firstRight < pts2.length) {
			sortPoints[curIndex] = pts2[firstRight++];
			curIndex++;
		}
	}
}
