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
 * This class implements the version of the quicksort algorithm presented in the lecture.   
 *
 */

public class QuickSorter extends AbstractSorter
{
	
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *   
	 * @param pts   input array of integers
	 */
	public QuickSorter(Point[] pts)
	{
		super(pts);
		algorithm = "quicksort";
	}
		

	/**
	 * Carry out quicksort on the array points[] of the AbstractSorter class.  
	 * 
	 */
	@Override 
	public void sort()
	{
		quickSortRec(0, points.length - 1);
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last. 
	 * 
	 * @param first  starting index of the subarray
	 * @param last   ending index of the subarray
	 */
	private void quickSortRec(int first, int last)
	{
		if(first >= last) {//only one element so it is sorted
			return;
		}
		
		int p = partition(first, last);//grab and arrange for partition
		quickSortRec(first, p-1);//sort first half less than partition
		quickSortRec(p+1, last);//sort second half greater than partition
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return i+1 - the index of the partition
	 */
	private int partition(int first, int last)
	{
		Point pivot = points[last];
		int i = first - 1;
		for(int j = first; j < last; j++) {
			if(pointComparator.compare(points[j], pivot) <= 0) {//if less than or equal to the pivot, swap to next arranged spot left of pivot
				i++;
				swap(i,j);
			}
		}
		swap(i+1, last);//put pivot after the last element that is less than or equal to it
		return i+1;
	}	
}
