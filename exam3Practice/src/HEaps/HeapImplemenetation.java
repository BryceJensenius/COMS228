package HEaps;

public class HeapImplemenetation {
	
	private int size = 0;
	private int[] arr;
	public void heapSort(int[] arr, int numSize) {
		for(int i = numSize/2 - 1; i >= 0; i--) {
			percolateDown(i, numSize);//i is current, numSize is the end
		}
		
		for(int i = numSize - 1; i > 0; i--) {
			int temp = arr[i];
			arr[i] = arr[0];
			arr[0] = temp;
			percolateDown(0, i);
		}
	}
	
	public void heapity() {
		int current = size/2 - 1;
		
		while(current >= 0) {
			percolateDown(current);
			current--;
			
		}
	}
	
	public void heapify() {
		int cur = size/2 - 1;
		
		while(cur >= 0) {
			percolateDown(cur);
			cur--;
		}
	}
	
	public void heapify() {
		int current = size/2 - 1;
		while(current >= 0) {
			//percolate down all ancestors
			percolateDown(current);
			current--;
		}
	}
	
	public void percolateUp(int[] data, int current) {
		int parent = (current-1)/2;
		while(current > 0 && data[current] < data[parent]) {
			int temp = data[parent];
			data[parent] = data[current];
			data[current] = temp;
			current = parent;
			parent = (current-1)/2;
		}
	}


	public void add(int data) {
		arr[size] = data;
		size++;
		percolateUp(arr, size-1);
	}
	
	public void remove() {
		arr[0] = arr[size-1];
		percolateDown(0);
	}
	
	public void percolateDown(int cur) {
		int child = cur*2 + 1;
		while(child < size) {
			if(child+1 < size) {
				if(arr[child] > arr[child+1]) {
					child++;
				}
			}
			if(arr[cur] > arr[child]) {
				int temp = arr[cur];
				arr[cur] = arr[child];
				arr[child] = temp;
			}
			cur = child;
			child = child*2 +1;
		}
	}
}
