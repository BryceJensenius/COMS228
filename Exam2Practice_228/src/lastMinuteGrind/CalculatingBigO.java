package lastMinuteGrind;

public class CalculatingBigO {
	private int counter;
	private int n;
	
	public CalculatingBigO(int n) {
		this.n = n;
	}
	
	public int weirdMethod() {
		for(int i = 0; i < n; i++) {
			for(int j = i; j > 0; j--) {
				otherWeirdMethod(j);
			}
		}
		return counter;
	}
	
	public void otherWeirdMethod(int max) {
		for(int i = 1; i < max; i += i) {
			counter++;
		}
	}
}
