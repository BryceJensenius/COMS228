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
			mergeSortRec4(arr, 0, arr.length - 1);
		}
	}
	
	private static void mergeSortRec4(int[] arr, int l, int r) {
		if(l >= r) {
			return;
		}
		
		int mid = (r+l)/2;
		mergeSortRec4(arr, l, mid);
		mergeSortRec4(arr, mid+1, r);
		merge4(arr, l, mid, r);
	}
	
	private static void merge4(int[] arr, int l, int mid, int r) {
		int d1 = mid - l + 1;
		int d2 = r - mid;
		
		int[] left = new int[d1];
		int[] right = new int[d2];
		
		for(int i = 0; i < d1; i++) {
			left[i] = arr[l+i];
		}
		for(int j = 0; j < d2; j++) {
			right[j] = arr[j + mid + 1];
		}
		
		int curIndex = l;
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void mergeSortRec3(int[] arr, int l, int r) {
		if(l >= r) {
			return;
		}
		
		int mid = (r + l)/2;
		
		mergeSortRec3(arr, l, mid);
		mergeSortRec3(arr, mid+1, r);
		merge3(arr, l, mid, r);
	}
	
	public static void merge3(int[] arr, int l, int mid, int r) {
		int d1 = mid - l + 1;
		int d2 = r - mid;
		
		int[] left = new int[d1];
		int[] right = new int[d2];
		
		for(int i = 0; i < d1; i++) {
			left[i] = arr[i+l];
		}
		for(int j = 0; j < d2; j++) {
			right[j] = arr[j + mid + 1];
		}
		int i = 0, j = 0;
		int curIndex = l;
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
	
	private static void mergeSortRec2(int[] arr, int l, int r) {
		if(l >= r) {
			return;
		}
		int mid = (r + l)/2;
		
		mergeSortRec2(arr, l, mid);
		mergeSortRec2(arr, mid+1, r);
		merge2(arr, l, mid, r);
	}
	
	private static void merge2(int[] arr, int l, int mid, int r) {
		int d1 = mid - l + 1;
		int d2 = r - mid;
		int[] left = new int[d1];
		int[] right = new int[d2];
		
		for(int i = 0; i < d1; i++) {
			left[i] = arr[i+l];
		}
		for(int j = 0; j < d2; j++) {
			right[j] = arr[j + mid + 1];
		}
		
		int i = 0, j = 0;
		int curIndex = l;
		
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
