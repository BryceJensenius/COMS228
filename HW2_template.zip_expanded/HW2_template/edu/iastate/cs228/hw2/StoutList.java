package edu.iastate.cs228.hw2;

import java.util.AbstractSequentialList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 *  
 * @author Bryce Jensenius
 *
 */

/**
 * Implementation of the list interface based on linked nodes
 * that store multiple items per node.  Rules for adding and removing
 * elements ensure that each node (except possibly the last one)
 * is at least half full.
 */
public class StoutList<E extends Comparable<? super E>> extends AbstractSequentialList<E>
{
  /**
   * Default number of elements that may be stored in each node.
   */
  private static final int DEFAULT_NODESIZE = 4;
  
  /**
   * Number of elements that can be stored in each node.
   */
  private final int nodeSize;
  
  /**
   * Dummy node for head.  It should be private but set to public here only  
   * for grading purpose.  In practice, you should always make the head of a 
   * linked list a private instance variable.  
   */
  public Node head;
  
  /**
   * Dummy node for tail.
   */
  private Node tail;
  
  /**
   * Number of elements in the list.
   */
  private int size;
  
  /**
   * Constructs an empty list with the default node size.
   */
  public StoutList()
  {
    this(DEFAULT_NODESIZE);
  }

  /**
   * Constructs an empty list with the given node size.
   * @param nodeSize number of elements that may be stored in each node, must be 
   *   an even number
   */
  public StoutList(int nodeSize)
  {
    if (nodeSize <= 0 || nodeSize % 2 != 0) throw new IllegalArgumentException();
    
    // dummy nodes
    head = new Node();
    tail = new Node();
    head.next = tail;
    tail.previous = head;
    this.nodeSize = nodeSize;
  }
  
  /**
   * Constructor for grading only.  Fully implemented. 
   * @param head
   * @param tail
   * @param nodeSize
   * @param size
   */
  public StoutList(Node head, Node tail, int nodeSize, int size)
  {
	  this.head = head; 
	  this.tail = tail; 
	  this.nodeSize = nodeSize; 
	  this.size = size; 
  }

  @Override
  public int size()
  {
    return size;
  }
  
  @Override
  public boolean add(E item)
  {
    if(item == null) {
    	throw new NullPointerException();
    }
    
    add(size, item);//add item at the end
    
    return true;
  }
  
  @Override
  public void add(int pos, E item)
  {
	  if(pos < 0 || pos > size) {
		  throw new IllegalArgumentException();
	  }
	  
	  if(item == null) {
		  throw new NullPointerException();
	  }
	  
	  //get the node and position where we need to add
	  NodeInfo nodeInfo = find(pos);
	  
	  //helper method to handle complex logic of inserting, shifting nodes
	  addElement(item, nodeInfo.node, nodeInfo.offset);
  }

  @Override
  public E remove(int pos)
  {
    if(pos < 0 || pos >= size) {
    	throw new IndexOutOfBoundsException();
    }
    
    NodeInfo nodeInfo = find(pos);//find the element so it can be removed with helper method
    return remove(nodeInfo);
  }

  /**
   * Sort all elements in the stout list in the NON-DECREASING order. You may do the following. 
   * Traverse the list and copy its elements into an array, deleting every visited node along 
   * the way.  Then, sort the array by calling the insertionSort() method.  (Note that sorting 
   * efficiency is not a concern for this project.)  Finally, copy all elements from the array 
   * back to the stout list, creating new nodes for storage. After sorting, all nodes but 
   * (possibly) the last one must be full of elements.  
   *  
   * Comparator<E> must have been implemented for calling insertionSort().    
   */
  public void sort()
  {
	  E[] elem = (E[]) new Comparable[size()];
	  
	  Iterator<E> iter = iterator();//make iterator at start index
	  
	  for(int i = 0; i < size; i++) {//put every element into the array
		  elem[i] = iter.next();
	  }
	  
	  size = 0;//clear StoutList by removing pointers
	  head.next = tail;
	  tail.previous = head;
	  
	  insertionSort(elem, new ElementComparator());//sort with the comparator implemented
	  
	  //add all the elements in the sorted list back into StoutList, method from AbstractSequentialList<E>
	  this.addAll(Arrays.asList(elem));
  }
  
  /**
   * Sort all elements in the stout list in the NON-INCREASING order. Call the bubbleSort()
   * method.  After sorting, all but (possibly) the last nodes must be filled with elements.  
   *  
   * Comparable<? super E> must be implemented for calling bubbleSort(). 
   */
  public void sortReverse() 
  {
	  E[] elem = (E[]) new Comparable[size()];
	  
	  Iterator<E> iter = iterator();//make iterator at start index
	  
	  for(int i = 0; i < size; i++) {//put every element into the array
		  elem[i] = iter.next();
	  }
	  
	  size = 0;//clear StoutList by removing pointers
	  head.next = tail;
	  tail.previous = head;
	  
	  bubbleSort(elem);
	  
	  //add all the elements in the sorted list back into StoutList
	  this.addAll(Arrays.asList(elem));
  }
  
  @Override
  public Iterator<E> iterator()
  {
    return listIterator();
  }

  @Override
  public ListIterator<E> listIterator()
  {
    return listIterator(0);
  }

  @Override
  public ListIterator<E> listIterator(int index)
  {
    if(index >= 0 && index <= size) {
    	return new StoutListIterator(index);
    }
    
    throw new IndexOutOfBoundsException();
  }
  
  /**
   * Returns a string representation of this list showing
   * the internal structure of the nodes.
   */
  public String toStringInternal()
  {
    return toStringInternal(null);
  }

  /**
   * Returns a string representation of this list showing the internal
   * structure of the nodes and the position of the iterator.
   *
   * @param iter
   *            an iterator for this list
   */
  public String toStringInternal(ListIterator<E> iter) 
  {
      int count = 0;
      int position = -1;
      if (iter != null) {
          position = iter.nextIndex();
      }

      StringBuilder sb = new StringBuilder();
      sb.append('[');
      Node current = head.next;
      while (current != tail) {
          sb.append('(');
          E data = current.data[0];
          if (data == null) {
              sb.append("-");
          } else {
              if (position == count) {
                  sb.append("| ");
                  position = -1;
              }
              sb.append(data.toString());
              ++count;
          }

          for (int i = 1; i < nodeSize; ++i) {
             sb.append(", ");
              data = current.data[i];
              if (data == null) {
                  sb.append("-");
              } else {
                  if (position == count) {
                      sb.append("| ");
                      position = -1;
                  }
                  sb.append(data.toString());
                  ++count;

                  // iterator at end
                  if (position == size && count == size) {
                      sb.append(" |");
                      position = -1;
                  }
             }
          }
          sb.append(')');
          current = current.next;
          if (current != tail)
              sb.append(", ");
      }
      sb.append("]");
      return sb.toString();
  }
  
  /*
   * Locates the item at the real index of pos returning the node and index in that node
   * @param pos - real position to find
   * @return node object and index in that object of the item found
   */
  
  private NodeInfo find(int pos) {
	  
	  //getting first element, head
	  if(pos == -1) {
		  return new NodeInfo(head, 0);
	  }
	  
	  //getting last element, tail
	  if(pos == size) {
		  return new NodeInfo(tail, 0);
	  }
	  
	  Node curNode = head.next;
	  
	  //keeps track of the real index in StoutList
	  //start at index of last element in first node, -1 to account for 0 start index
	  int curIndex = curNode.count - 1;
	  
	  //continue until you get to the position real index or the tail
	  while(curIndex < pos && curNode != tail) {
		  curNode = curNode.next;//go to next element
		  curIndex += curNode.count;//real index adds total number of elements in this node
	  }
	  
	  //size - (curIndex - Position) - 1 simplified to calculate index
	  int indexInNode = curNode.count + pos - curIndex - 1;
	  return new NodeInfo(curNode, indexInNode);
  }
  
  /*
   * Adds item to index of the node
   * Handles shifting to overflow elements
   * @param item - item to add
   * @param node - node location
   * @param index - index location within node
   */
  private void addElement(E item, Node node, int index) {
	  if(item == null) {
		  throw new NullPointerException();
	  }
	  
	  if(index != 0) {
		  if(node.count < nodeSize) {//still room in node so add at given index
			  node.addItem(index, item);
		  }else {//no room left in this node
			  Node addNode = new Node();
			  link(node, addNode);
			  
			  //this node is now full so put split the elements between this and the second node, keeping order
			  for(int i = nodeSize - 1; i >= nodeSize - nodeSize/2; i--) {
				    addNode.addItem(0, node.data[i]);//from end of node to middle, move the element to new node
				    node.removeItem(i);
			  }
			  if(index <= nodeSize/2) {//it needs to be added to first half
				  node.addItem(index, item);
			  }else {//add to second half, new node
				  addNode.addItem(index - nodeSize/2, item);//calculate how far into second node
			  }
		  }
	  }else {
		  if(size == 0) {
			  Node newNode = new Node();
			  newNode.addItem(item);
			  connectTwoNodes(head, newNode);//connect newNode to after head node
		  }else if(node.previous.count < nodeSize && node.previous != head) {//add to previous node if there is room left, unless previous is head
			  node.previous.addItem(item);
		  }else if(node == tail && node.previous.count == nodeSize) {//add new node at tail because previous is full and current is tail
			  Node newNode = new Node();
			  newNode.addItem(item);
			  connectTwoNodes(tail.previous, newNode);//connect new note to after last node
		  }else {
			  //because of how find and add are implemented, there will always be room for this element here
			  node.addItem(index, item);
		  }
	  }
	  size++;//number of elements increments
  }
  
  /*
   * Connects the two input nodes, adding node n2 after node n1
   * @param n1 - connect to previous of n2
   * @param n2 - connect to next of n1
   */
  private void connectTwoNodes(Node n1, Node n2) {
	  n2.next = n1.next;
	  n2.previous = n1;
	  n1.next.previous = n2;
	  n1.next = n2;
  }

  /**
   * Node type for this list.  Each node holds a maximum
   * of nodeSize elements in an array.  Empty slots
   * are null.
   */
  private class Node
  {
    /**
     * Array of actual data elements.
     */
    // Unchecked warning unavoidable.
    public E[] data = (E[]) new Comparable[nodeSize];
    
    /**
     * Link to next node.
     */
    public Node next;
    
    /**
     * Link to previous node;
     */
    public Node previous;
    
    /**
     * Index of the next available offset in this node, also 
     * equal to the number of elements in this node.
     */
    public int count;

    /**
     * Adds an item to this node at the first available offset.
     * Precondition: count < nodeSize
     * @param item element to be added
     */
    void addItem(E item)
    {
      if (count >= nodeSize)
      {
        return;
      }
      data[count++] = item;
      //useful for debugging
      //      System.out.println("Added " + item.toString() + " at index " + count + " to node "  + Arrays.toString(data));
    }
  
    /**
     * Adds an item to this node at the indicated offset, shifting
     * elements to the right as necessary.
     * 
     * Precondition: count < nodeSize
     * @param offset array index at which to put the new element
     * @param item element to be added
     */
    void addItem(int offset, E item)
    {
      if (count >= nodeSize)
      {
    	  return;
      }
      for (int i = count - 1; i >= offset; --i)
      {
        data[i + 1] = data[i];
      }
      ++count;
      data[offset] = item;
      //useful for debugging 
//      System.out.println("Added " + item.toString() + " at index " + offset + " to node: "  + Arrays.toString(data));
    }
    

    /**
     * Deletes an element from this node at the indicated offset, 
     * shifting elements left as necessary.
     * Precondition: 0 <= offset < count
     * @param offset
     */
    void removeItem(int offset)
    {
      E item = data[offset];
      for (int i = offset + 1; i < nodeSize; ++i)
      {
        data[i - 1] = data[i];
      }
      data[count - 1] = null;
      --count;
    }    
  }
 
  private class StoutListIterator implements ListIterator<E>
  {
	// constants you possibly use ...   
	  
	/*
	 * current Index the cursor is on
	 */
	private int cursorIndex;
	
	/*
	 * the last accessed nodes information
	 */
	private NodeInfo pending;
	
	/*
	 * Previous move direction
	 * 0 = none
	 * 1 = backward
	 * -1 = forward
	 */
	private int direction = 0;
	  
    /**
     * Default constructor 
     */
    public StoutListIterator()
    {
    	cursorIndex = 0;
    	pending = null;
    }

    /**
     * Constructor finds node at a given position.
     * @param pos
     */
    public StoutListIterator(int pos)
    {
    	if(pos < 0 || pos > size) {
    		throw new IndexOutOfBoundsException();
    	}
    	cursorIndex = pos;
    	pending = null;
    }

    @Override
    public boolean hasNext()
    {
    	return (cursorIndex < size);
    }

    @Override
    public E next()
    {
    	if(hasNext()){
    		pending = find(cursorIndex++);
    		direction = -1;
    		return pending.node.data[pending.offset];
    	}
    	throw new NoSuchElementException();
    }

	@Override
    public void remove()
    {
    	if(direction == 0) {//no element to be removed
    		throw new IllegalStateException();
    	}
 
    	if(direction == -1) {
    		cursorIndex--;//removed from left so it gets shifted one
    	}
    	
    	StoutList.this.remove(pending);
    	pending = null;
    	direction = 0;
    }

	@Override
	public boolean hasPrevious() {
		return cursorIndex > 0;
	}

	@Override
	public E previous() {
		if(hasPrevious()) {
			NodeInfo nodeInfo;
			direction = 1;
			nodeInfo = find(--cursorIndex);
			
			if(cursorIndex + 1 != size) {//if it wasn't at the last element set to pending
				pending = nodeInfo;
			}
			
			return nodeInfo.node.data[nodeInfo.offset];
		}
		
		throw new NoSuchElementException();
	}

	/*
	 * index of element returned if next is used
	 */
	@Override
	public int nextIndex() {
		return cursorIndex;
	}

	/*
	 * Index of element returned if previous is used
	 */
	@Override
	public int previousIndex() {
		return cursorIndex - 1;
	}

	/*
	 * sets the previously returned element's data to e
	 * @param e - data to be set to
	 */
	@Override
	public void set(E e) {
		if(e == null) {
			throw new NullPointerException();
		}
		if(direction == 0) {
			throw new IllegalStateException();
		}
		pending.node.data[pending.offset] = e; 
	}

	/*
	 * Add a new data element where the cursor is
	 * @param e - data point to add
	 */
	@Override
	public void add(E e) {
		if(e == null) {
			throw new NullPointerException();
		}
		direction = 0;//can no longer remove/set
		StoutList.this.add(cursorIndex++, e);//add at the cursors index, push cursor forward 1
	}    
  }

  /**
   * Sort an array arr[] using the insertion sort algorithm in the NON-DECREASING order. 
   * @param arr   array storing elements from the list 
   * @param comp  comparator used in sorting 
   */
  private void insertionSort(E[] arr, Comparator<? super E> comp)
  {
	  for(int i = 1; i < arr.length; i++) {
		  E temp = arr[i];
		  int j = i - 1;
		  
		  while(j >= 0 && comp.compare(temp, arr[j]) <= 0) {
			  arr[j+1] = arr[j];
			  j--;
		  }
		  arr[j+1] = temp;
	  }
  }
  
  /**
   * Sort arr[] using the bubble sort algorithm in the NON-INCREASING order. For a 
   * description of bubble sort please refer to Section 6.1 in the project description. 
   * You must use the compareTo() method from an implementation of the Comparable 
   * interface by the class E or ? super E. 
   * @param arr  array holding elements from the list
   */
  private void bubbleSort(E[] arr)
  {
	  boolean sorted;
	  for(int i = 1; i < arr.length; i++) {
		  sorted = true;
		  for(int j = 0; j < arr.length - i; j++) {
			  if(arr[j].compareTo(arr[j+1]) < 0) {//if element on left is less, swap it to right
				  sorted = false;
				  E temp = arr[j];
				  arr[j] = arr[j+1];
				  arr[j+1] = temp;
			  }
		  }
		  
		  if(sorted) {
			  return;
		  }
	  }
  }

  /*
   * Store location of a specific item within a node
   */
  private class NodeInfo{
	  public Node node;
	  public int offset;
	  public NodeInfo(Node node, int offset) {
		  this.node = node;
		  this.offset = offset;
	  }
  }
  
  /*
   * Connects Node n2 just after Node n1
   * @param n1 - node to left
   * @param n2 - node to add on right
   */
  private void link(Node n1, Node n2) {
	  n2.previous = n1;
	  n2.next = n1.next;
	  n1.next.previous = n2;
	  n1.next = n2;
  }
  
  /*
   * Remove given node from the StoutList
   * @param n - node to be removed
   */
  private void unlink(Node n) {
	  n.previous.next = n.next;
	  n.next.previous = n.previous;
  }
  
  /*
   * Given element info with node and index, remove that element from the stoutList
   * @param n - location of element to be removed
   */
  private E remove(NodeInfo n) {
	  E data = n.node.data[n.offset];//grab the data before removal
	  
	  if(n.node.next == tail && n.node.count == 1) {//last node, one element, easy case of directly remove element
		  unlink(n.node);
	  }else if(n.node.count > nodeSize/2 || n.node.next == tail) {//node is last node or has enough element to safely just remove
		  n.node.removeItem(n.offset);
	  }else {//Complicated, less than or half full node, not last node
		  
		  //remove the element and then add element from next node
		  n.node.removeItem(n.offset);
		  
		  if(n.node.next.count > nodeSize / 2) {//node after node needing another element is big enough to have one taken
			  n.node.addItem(n.node.next.data[0]);//take first element from next node and put it in this node
			  n.node.next.removeItem(0);
		  }else {//both nodes are half or less full, so we can combine into one \-('0')-/
			  for(E elem : n.node.next.data) {//for every element in next node, add to this node
				  if(elem == null) {//safety check, do nothing
					  
				  }else {
					  n.node.addItem(elem);
				  }
			  }
			  unlink(n.node.next);//Remove node we pulled all the elements from
		  }
	  }
	  size--;//update size becasue element was removed
	  return data;
  }
  
  /*
   * Comparator to be used for comparing elements that are Comparable
   */
  private static class ElementComparator<E extends Comparable<? super E>> implements Comparator<E>{
	  @Override
	  public int compare(E e1, E e2) {
		  return e1.compareTo(e2);
	  }
  }
}