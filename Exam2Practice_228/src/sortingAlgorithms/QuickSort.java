package sortingAlgorithms;

public class QuickSort {
	public static void main(String[] args) {
		int[] arr = {54,7,4,22,46,47,898,446,654,4343,67,4235,54,};
		
		quickSort(arr);
		
		for(int i : arr) {
			System.out.println(i + " ");
		}
	}
	
	public static void quickSort(int[] arr) {
		quickSortRec3(arr, 0, arr.length - 1);
	}
	
	private static void quickSortRec3(int[] arr, int l, int r) {
		if(l >= r) {
			return;
		}
		int p = partition3(arr, l, r);
		quickSortRec3(arr, l, p-1);
		quickSortRec3(arr, p+1, r);
	}
	
	private static int partition3(int[] arr, int l, int r) {
		int pivot = arr[r];
		int i = l-1;
		for(int j = l; j < r; j++) {
			if(arr[j] <= pivot) {
				int temp = arr[j];
				arr[j] = arr[++i];
				arr[i] = temp;
			}
		}
		arr[r] = arr[i+1];
		arr[i+1] = pivot;
		return i;
	}	
	
	private static void quickSortRec2(int[] arr, int l, int r) {
		if(l >= r) {
			return;
		}
		int p = partition2(arr, l, r);
		quickSortRec2(arr, l, p-1);
		quickSortRec2(arr, p+1, r);
	}
	
	private static int partition2(int[] arr, int l, int r) {
		int p = arr[r];
		int i = l-1;
		
		for(int j = l; j < r; j++) {
			if(arr[j] < p) {
				int temp = arr[j];
				arr[j] = arr[++i];
				arr[i] = temp;
			}
		}
		arr[r] = arr[++i];
		arr[i] = p;
		return i;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void quickSortRec(int[] arr, int l, int r) {
		if(l >= r) {
			return;
		}
		
		int p = partition(arr, l, r);
		quickSortRec(arr, l, p-1);
		quickSortRec(arr, p+1, r);
	}

	private static int partition(int[] arr, int l, int r) {
		int pivot = arr[r];
		int i = l-1;
		for(int j = l; j < r; j++) {
			if(arr[j] < pivot) {
				int temp = arr[++i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		int temp = arr[++i];
		arr[i] = pivot;
		arr[r] = temp;
		return i;
	}
}
