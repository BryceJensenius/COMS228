package sortingAlgorithms;

public class MergeSort {
	
	public static void main(String[] args) {
		int[] arr = {213,4,5,45,43,43,26,7};
		mergeSort(arr);
		for(int a : arr) {
			System.out.println(a + " ");
		}
	}
	
	public static void mergeSort(int[] arr) {
		if(arr != null) {
			mergeSortRec(arr, 0, arr.length - 1);
		}
	}
	
	private static void mergeSortRec(int[] arr, int min, int max) {
		if(min >= max) {
			return;
		}
		
		int mid = (min + max)/2;
		
		mergeSortRec(arr, min, mid);
		mergeSortRec(arr, mid+1, max);
		
		merge(arr, min, mid, max);
	}
	
	public static void merge(int[] arr, int min, int mid, int max) {
		int end1 = mid - min + 1;//length of first half array
		int end2 = max - mid;//length of second half array
		
		int[] left = new int[end1];
		int[] right = new int[end2];
		
		for(int i = 0; i < end1; i++) {//fill in left temp array
			left[i] = arr[i + min];// + min so it starts at right spot in array **
		}
		for(int i = 0; i < end2; i++) {//fill in right temp array
			right[i] = arr[mid + i + 1];
		}
		
		int i = 0, j = 0;
		int curIndex = min;//index to input elements into arr
		
		while(i < end1 && j < end2) {
			if(left[i] <= right[j]) {
				arr[curIndex] = left[i++];
			}else {
				arr[curIndex] = right[j++];
			}
			curIndex++;
		}
		
		while(i < end1) {
			arr[curIndex++] = left[i++];
		}
		while(j < end2) {
			arr[curIndex++] = right[j++];
		}
	}
}
