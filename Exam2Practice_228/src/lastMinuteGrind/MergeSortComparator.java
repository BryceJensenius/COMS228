package lastMinuteGrind;

import comparators.IntegerDistanceComparator;
import java.util.Comparator;

public class MergeSortComparator {
	
	public static void main(String[] args) {
		Integer[] arr = {1,6,9,4,7,2,4,7889,854,33,57,89,54,3};
		quickSort(arr, new IntegerDistanceComparator(5));
		for(int i : arr) {
			System.out.print(i + " ");
		}
	}
	
	public static <T> void quickSort(T[] arr, Comparator<? super T> comp) {
		quickSortRec(arr, 0, arr.length - 1, comp);
	}
	
	private static <T> void quickSortRec(T[] arr, int l, int r, Comparator<? super T> comp) {
		if(l >= r) {
			return;
		}
		
		int p = partition(arr, l, r, comp);
		quickSortRec(arr, l, p-1, comp);
		quickSortRec(arr, p+1, r, comp);
	}
	
	private static <T> int partition(T[] arr, int l, int r, Comparator<? super T> comp) {
		T pivot = arr[r];
		int i = l - 1;
		
		for(int j = l; j < r; j++) {
			if(comp.compare(arr[j], pivot) <= 0) {
				T temp = arr[++i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		arr[r] = arr[i + 1];
		arr[i+1] = pivot;
		return i + 1;
	}
}
