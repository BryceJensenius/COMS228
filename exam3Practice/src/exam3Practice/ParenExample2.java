package exam3Practice;

import java.util.NoSuchElementException;

public class ParenExample2 {
	/**
	   * Entry point.  Displays a few strings and the
	   * result of calling checkString().
	   * @param args not used
	   */
	  public static void main(String[] args)
	  {
	    ParenExample2 test = new ParenExample2();
	    String t = "((()()()))";
	    System.out.println(t + " " + test.checkString(t));
	    t = "([()()()])";
	    System.out.println(t + " " + test.checkString(t));
	    t = "([()()())]";
	    System.out.println(t + " " + test.checkString(t));
	    t = "[[]";
	    System.out.println(t + " " + test.checkString(t));
	    t = "[]]";
	    System.out.println(t + " " + test.checkString(t));
	  }
	  
	  public boolean checkString(String args) {
		  PureStack<Character> chars = new ArrayBasedStack2<Character>();
		  
		  for(int i = 0; i < args.length(); i++) {
			  char c = args.charAt(i);
			  if(c == '(' || c == '[') {
				  chars.push(c);
			  }else if(c == ')' || c == ']') {
				  try {
					  char myChar = chars.pop();
					  if((myChar == '(' && c != ')')
							  || (myChar == '[' && c != ']')) {
						 return false;
					  }
				  }catch(NoSuchElementException e) {
					  return false;
				  }
			  }
		  }
		  
		  return chars.isEmpty();
	  }
	  
	  /**
	   * Determines whether the parentheses and brackets
	   * in the given string are properly matched.
	   * @param text the string to be checked
	   * @return true if the parentheses and brackets in the 
	   *   given string are properly matched, false otherwise
	   */
	  public boolean checkString2(String text)
	  {    
	    PureStack<Character> s = new ArrayBasedStack2<Character>();

	    for (int i = 0; i < text.length(); ++i)
	    {
	      char c = text.charAt(i);
	      if (c == '(' || c == '[')
	      {
	        // it's a left delimiter, push it on the stack
	        s.push(c);
	      }
	      else if (c == ')' || c == ']')
	      {
	        // right delimiter should match top of stack
	        try
	        {
	          char left = s.pop();
	          if (left == '(' && c != ')' ||
	              left == '[' && c != ']')
	          {
	            return false;
	          }
	        }
	        catch (NoSuchElementException e)
	        {
	          // stack was empty, so c is unmatched
	          return false;
	        }
	      }
	    }
	    
	    // if s is not empty, there was a left delimiter with no match
	    return s.isEmpty();
	  }
}
