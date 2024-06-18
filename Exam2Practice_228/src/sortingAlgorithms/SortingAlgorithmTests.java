package sortingAlgorithms;


public class SortingAlgorithmTests {
	public static void main(String[] args) {
		String[] str = {"bryce", "Heather", "Elijah", "Anna", "Carly", "Emma"};
		Integer[] ints = {6,4,7,90,45,24,5,87,4,234,87,54,2,6543};
		QuickSortComparable.quickSort(str);
		QuickSortComparable.quickSort(ints);
		
		for(String s : str) {
			System.out.print(s + " ");
		}
		for(Integer i : ints) {
			System.out.print(i + " ");
		}
	}
}
