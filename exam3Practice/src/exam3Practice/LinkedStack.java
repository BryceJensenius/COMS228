package exam3Practice;

import java.util.NoSuchElementException;

/**
 * Sample implementation of a stack using singly-linked nodes.
 */
public class LinkedStack<E> implements PureStack<E>
{
  /**
   * Node containing top element on the stack; null
   * when the stack is empty.
   */
  private Node top;
  
  /**
   * Number of elements in the stack.
   */
  private int size;

  /**
   * Constructs an empty stack.
   */
  public LinkedStack()
  {
    top = null;
    size = 0;
  }

  @Override
  public int size()
  {
    return size;
  }

  @Override
  public boolean isEmpty()
  {
     return size == 0;
  }

  @Override
  public void push(E element)
  {
    Node toAdd = new Node();
    toAdd.data = element;
    toAdd.next = top;
    top = toAdd;
    size++;
  }

  @Override
  public E pop()
  {
    if ( top == null ) throw new NoSuchElementException();    
    E returnVal = top.data;
    top = top.next;
    size--;
    return returnVal;
  }

  @Override
  public E peek()
  {
    if ( top == null ) throw new NoSuchElementException();   
    return top.data;
  }
  
  /**
   * Node type for this stack implementation.
   */
  private class Node
  {
    public E data;
    public Node next;
  }

}
