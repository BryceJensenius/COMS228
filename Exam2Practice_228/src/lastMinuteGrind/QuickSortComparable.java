package lastMinuteGrind;

public class QuickSortComparable {
	public static void main(String[] args) {
		Integer[] arr = {1,6,9,4,7,2,4,7889,854,33,57,89,54,3};
		String[] str = {"Bryce", "Elijah", "Heather", "Grace"};
		//mergeSort(arr, new IntegerDistanceComparator(5));
		quickSort(arr);
		
		for(int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
		for(String s : str) {
			System.out.print(s + " ");
		}
	}
	public static <T extends Comparable<? super T>> void quickSort(T[] arr) {
		mergeSortRec(arr, 0, arr.length - 1);
	}
	
	private static <T extends Comparable<? super T>> void mergeSortRec(T[] arr, int l, int r) {
		if(l >= r) {
			return;
		}
		
		int mid = (l + r)/2;
		mergeSortRec(arr, l, mid);
		mergeSortRec(arr, mid+1, r);
		merge(arr, l, mid, r);
	}
	
	private static <T extends Comparable<? super T>> void merge(T[] arr, int l, int mid, int r) {
		int d1 = mid - l + 1;
		int d2 = r - mid;
		int curIndex = l;
		
		T[] left = (T[])new Comparable[d1];
		T[] right = (T[])new Comparable[d1];
		
		for(int i = 0; i < d1; i++) {
			left[i] = arr[l + i];
		}
		for(int j = 0; j < d2; j++) {
			right[j] = arr[j + mid + 1];
		}
		int i = 0, j = 0;
		
		while(i < d1 && j < d2) {
			if(left[i].compareTo(right[j]) <= 0) {
				arr[curIndex] = left[i++];
			}else {
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
