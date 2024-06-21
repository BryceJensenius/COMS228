package linkedListIteration;

import java.util.NoSuchElementException;

public class Iterator<T> implements Iterator<T>{
	private Node<T> cursor;
	private int direction = 0;
	private int index;
	
	public Iterator(int addIndex) {
		cursor = head;
		index = addIndex;
		
		for(int i = 0; i < addIndex; i++) {
			cursor = cursor.next;
		}
	}
	
	public boolean hasNext() {
		return cursor.next != null;
	}
	
	public boolean hasPrevious() {
		return cursor.previous != null;
	}
	
	public T next() {
		if(!hasNext()) {
			throw new NoSuchElementException();
		}
		
		T data = cursor.data;
		cursor = cursor.next;
		direction = -1;
		return data;
	}
	
	public T previous() {
		if(!hasPrevious()) {
			throw new NoSuchElementException();
		}
		
		cursor = cursor.previous;
		return cursor.data;
	}
	
	public void remove() {
		if(direction == 0) {
			throw new IllegalStateException();
		}
		
		size--;
	}
}
