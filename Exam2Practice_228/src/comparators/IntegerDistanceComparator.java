package comparators;

import java.util.Comparator;

public class IntegerDistanceComparator implements Comparator<Integer>{
	
	private static int num;
	/*
	 * distance values are from num
	 */
	public IntegerDistanceComparator(int num) {
		this.num = num;
	}

	/*
	 * compare by distance from num
	 */
	@Override
	public int compare(Integer o1, Integer o2) {
		int dist1 = Math.abs(o1 - num);
		int dist2 = Math.abs(o2 - num);
		
		if(dist1 == dist2) {
			return 0;
		}else if(dist1 <= dist2) {
			return -1;
		}else {
			return 1;
		}
	}
}
