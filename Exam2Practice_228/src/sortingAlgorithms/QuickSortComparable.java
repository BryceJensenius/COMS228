package sortingAlgorithms;

public class QuickSortComparable {
	/*
	 * Get cooking on a quick sort algorithm using Comparable objects :)
	 */
	public static <T extends Comparable<? super T>> void quickSort(T[] arr) {
		quickSortRec(arr, 0, arr.length - 1);//arr.length - 1!!!!!
	}
	
	private static <T extends Comparable<? super T>> void quickSortRec(T[] arr, int l, int r) {
		if(l >= r) {
			return;
		}
		
		int p = partition(arr, l, r);
		quickSortRec(arr, l, p-1);
		quickSortRec(arr, p+1, r);
	}
	
	private static <T extends Comparable<? super T>> int partition(T[] arr, int l, int r) {
		T pivot = arr[r];
		
		int i = l - 1;
		
		for(int j = l; j < r; j++) {
			if(arr[j].compareTo(pivot) < 0) {
				T temp = arr[j];
				arr[j] = arr[++i];
				arr[i] = temp;
			}
		}
		
		arr[r] = arr[i+1];
		arr[i + 1] = pivot;
		return i+1;//I + 1 !!!!
	}
}
