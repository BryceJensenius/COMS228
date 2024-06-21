package practiceExamCode;

public class Problem4 {
	//next and previous move ahead and behind like normal, returning the element
	//Remove removes the last element returned, returns direction to normal, regardless of previous use
	//iterator can be initialized at any index from 0 to size(), default is beginning
	  //it goes just before the element at given index
	//set cannot be used after remove or add or itself
	//remove cannot be used right after set or remove, only after a next or previous
	//add always adds at cursor
	
	
	| A B C D E X None
	
	
	A B | C D E 
	A | B C D E
	A | C D E
	0
	
	
	A B C D E |
	A B C D | E
	A B C D E |   E
	A B C D | E
	A B C | D E   D
	              C
	              B
	              A
	| A B C D E
	
	
	B C D E
	| B C D E
	B | C D E
	X | C D E
	IllegalStateException
	
	
	| A B C D E
	| A | B C D E
	A | | B C D E
	B | B | C D E
	| B B | C D E    B
	B | B C | D E
	| C B C | D E    C
	C | B C | D E
	| D B C D | E    D
	| E B C D E |    E
	
	
	A B C | D E
	A B | C D E
	A B | D E
	A | D E
	
	NoSuchElementException
	
	O(N) to find the element
	
	O(N) to find the element
	
	O(N) outer loop always + O(N) = O(N^2)
	
	
	StackReorder<T extends Comparable<? super T>>
	
	PureStack<E> tempStk = new PureStack<E>();
	SimpleList tempList = new SimpleList();
	
	E item;
	while(!stk.isEmpty()) {
		item = stk.pop();
		if(item.compareTo(key) == 0) {//equal to key so add to temp
			tempStk.push(item);
		}else {//not equal to key so add to 
			tempList.add(item);
		}
	}
	while(!tempList.isEmpty()) {
		stk.push(tempList.remove());
	}
	
	while(!tempStk.isEmpty()) {
		stk.push(tempStk.pop());
	}
	
	
	size = 0;
	head = new Node(null);
	head.next = null;
	
	void add(E item) {
		Node temp = new Node(item);
		temp.next = head.next;
		head.next = temp;
		size++;
	}
	
	E remove() {
		if(size == 0) {
			throw new IllegalStateException();
		}
		
		Node dataNode = head.next;
		head.next = dataNode.next;
		size--;
		return dataNode.data;
	}
	
	boolean isEmpty() {
		return size == 0;
	}
}
