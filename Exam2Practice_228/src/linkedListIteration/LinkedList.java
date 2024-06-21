package linkedListIteration;

public class LinkedList {
	Node head;
	Node tail;
	
	public LinkedList() {
		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.previous = head;
	}
	
	public void addNode() {
		ArrayBasedStack<String> myStack = new ArrayBasedStack<String>();
	}
}