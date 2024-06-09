package sortingAlgorithms;

public class mergeSort2 {
	public static void main(String[] args) {
		int[] arr = {213,4,5,45,43,43,35,573,2454,3,23,4,5,6,321,34};
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
		
		int mid = (max + min)/2;
		
		mergeSortRec(arr, min, mid);
		mergeSortRec(arr, mid+1, max);
		merge(arr, min, mid, max);
	}
	
	private static void merge(int[] arr, int min, int mid, int max) {
		int d1 = mid - min + 1;
		int d2 = max - mid;
		int curIndex = min;
		
		int[] left = new int[d1];
		int[] right = new int[d2];
		
		for(int i = 0; i < d1; i++) {
			left[i] = arr[min+i];
		}
		for(int j = 0; j < d2; j++) {
			right[j] = arr[mid + j + 1];
		}
		int i = 0, j = 0;
		
		while(i < d1 && j < d2) {
			if(left[i] <= right[j]) {
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
