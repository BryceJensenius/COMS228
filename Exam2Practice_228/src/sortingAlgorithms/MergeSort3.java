package sortingAlgorithms;

import java.util.Comparator;
import comparators.IntegerDistanceComparator;

public class MergeSort3 {
	
	public static void main(String[] args) {
		Integer[] arr = {1,6,9,4,7,2,4,7889,854,33,57,89,54,3};
		String[] str = {"Bryce", "Elijah", "Heather", "Grace"};
		mergeSort(arr, new IntegerDistanceComparator(5));
		//mergeSort(str);
		
		for(int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
		for(String s : str) {
			System.out.print(s + " ");
		}
	}
	
	public static <T> void mergeSort(T[] str, Comparator<? super T> comp) {
		mergeSortRec(str, 0, str.length - 1, comp);
	}
	
	private static <T> void mergeSortRec(T[] arr, int l, int r, Comparator<? super T> comp){
		if(l >= r) {
			return;
		}
		
		int mid = (l+r)/2;
		mergeSortRec(arr, l, mid, comp);
		mergeSortRec(arr, mid+1, r, comp);
		merge(arr, l, mid, r, comp);
	}
	
	private static <T> void merge(T[] arr, int l, int mid, int r, Comparator<? super T> comp) {
		int d1 = mid - l + 1;
		int d2 = r - mid;
		int curIndex = l;
		
		T[] left = (T[])new Object[d1];//This is the only acceptable warning in this class, we need it
		T[] right = (T[])new Object[d2];
		
		for(int i = 0; i < d1; i++) {
			left[i] = arr[i + l];
		}
		
		for(int i = 0; i < d2; i++) {
			right[i] = arr[i + mid + 1];
		}
		
		int i = 0, j = 0;
		while(i < d1 && j < d2) {
			if(comp.compare(left[i], right[j]) <= 0) {
				arr[curIndex] = left[i++];
			}else{
				arr[curIndex] = right[j++];
			}
			curIndex++;
		}
		
		while(i < d1) {
			arr[curIndex++] = left[i++];
		}
		while(j < d2) {
			arr[curIndex++] = right[j++];
		}
	}
}
