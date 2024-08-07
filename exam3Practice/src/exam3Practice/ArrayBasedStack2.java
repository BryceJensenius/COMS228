package exam3Practice;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayBasedStack2<E> implements PureStack<E>{
	private static final int DEFAULT_SIZE = 10;
	  
	  /**
	   * Index of next available cell in array.
	   */
	  private int top;
	  
	  /**
	   * The data store.
	   */
	  private E[] data;
	  
	  /**
	   * Constructs an empty stack.
	   */
	  public ArrayBasedStack2()
	  {
	    // Unchecked warning is unavoidable
	    data = (E[]) new Object[DEFAULT_SIZE];
	  }
	  
	  @Override
	  public boolean isEmpty()
	  {
	    return top == 0;
	  }

	  @Override
	  public E peek()
	  {
	    if (top == 0) throw new NoSuchElementException();
	    return data[top - 1];
	  }

	  @Override
	  public E pop()
	  {
	    if (top == 0) throw new NoSuchElementException();
	    E ret = data[--top];
	    data[top] = null;
	    return ret;
	  }

	  @Override
	  public void push(E item)
	  {
	    checkCapacity();
	    data[top++] = item;
	  }

	  @Override
	  public int size()
	  {
	    return top;
	  }

	  /**
	   * Ensures that the backing array has space to store at least 
	   * one additional element.
	   */
	  private void checkCapacity()
	  {
	    if (top == data.length)
	    {
	      // create a copy of the data array with double the capacity
	      data = Arrays.copyOf(data, data.length * 2);
	    }
	  }
}
