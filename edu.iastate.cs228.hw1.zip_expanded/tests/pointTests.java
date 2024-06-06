package tests;

import edu.iastate.cs228.hw1.Point;

public class pointTests {
	public static void main(String[] args) {
		Point p1 = new Point();
		Point p2 = new Point(5,5);
		Point p3 = new Point(5,5);
		Point p4 = new Point(5,0);
		Point p5 = new Point(0,5);
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
		System.out.println(p4);
		System.out.println(p5);
		
		Point.setXorY(true);
		
		System.out.println(p1.compareTo(p2));//-1
		System.out.println(p1.compareTo(p3));//-1
		System.out.println(p1.compareTo(p4));//-1
		System.out.println(p1.compareTo(p5));//0
		System.out.println(p2.compareTo(p3));//0
		System.out.println(p2.compareTo(p4));//1
		System.out.println(p2.compareTo(p5));//1
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		Point.setXorY(false);
		
		System.out.println(p1.compareTo(p2));//false
		System.out.println(p1.compareTo(p3));//false
		System.out.println(p1.compareTo(p4));//true
		System.out.println(p1.compareTo(p5));//false
		System.out.println(p2.compareTo(p3));//0
		System.out.println(p2.compareTo(p4));//1
		System.out.println(p2.compareTo(p5));//0
	}
}
