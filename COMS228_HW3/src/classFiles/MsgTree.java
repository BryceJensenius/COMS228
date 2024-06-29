package classFiles;

/**
 *  
 * @author Bryce Jensenius
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

/*
 * MsgTree Can read in an encoding scheme to construct the tree
 * Each leaf in the tree will contain a character, the path to this character is the bit code
 * This tree can then be used to read in a stream of bits and decode them into a message
 * based on traversal through the tree
 */
public class MsgTree {
	
	public char payloadChar;
	public MsgTree left;
	public MsgTree right;
	
	/*
	 * Name of the file that is being read from
	 */
	private static String fileName;
	
	/*
	 * Create a tree from string input
	 * @param encodedString - String with characters to put in nodes
	 */
	public MsgTree(String encodedString) {
		Stack<MsgTree> stack = new Stack<MsgTree>();
		int curIndex = 0;
		
		this.payloadChar = encodedString.charAt(curIndex++);//grab first character for root node
		stack.add(this);
		
		while(!stack.isEmpty()) {//continue until all inner nodes have been filled
			MsgTree cur = stack.pop();
			
			MsgTree leftTemp = null;
			if(cur.left == null) {//if left hasn't been filled yet, fill it
				leftTemp = new MsgTree(encodedString.charAt(curIndex++));
				
				cur.left = leftTemp;//set left node before moving on
				
				if(leftTemp.payloadChar == '^') {//if it is an inner Node, hold off on right node and finish left nodes children
					stack.add(cur);
					stack.add(leftTemp);
					continue;
				}
			}
			
			MsgTree rightTemp = new MsgTree(encodedString.charAt(curIndex++));
			if(rightTemp.payloadChar == '^') {//it is a sub node so add to stack for children to be filled
				cur.right = rightTemp;//set right node before moving on
				stack.add(rightTemp);
				//no need to add cur Node to stack because it is always finished at this point
				continue;
			}
			cur.right = rightTemp;
		}
	}
	
	/*
	 * Create a node with null children and character value
	 * @param payloadChar - character to place in node
	 */
	public MsgTree(char payloadChar) {
		this.payloadChar = payloadChar;
		left = null;
		right = null;
	}
	
	/*
	 * Method to print characters and their binary codes
	 * Prints in pre-order traversal
	 * @param root - Subtree to print from
	 * @param code - bit code to get to current root
	 */
	public static void printCodes(MsgTree root, String code) {
		if(root == null) {
			return;
		}
		
		if(root.payloadChar == '^') {//internal Node
			printCodes(root.left, code + "0");//move to left node to print
			printCodes(root.right, code + "1");//move to right child to print
		}else {//leaf so print
			
			if(root.payloadChar == '\n') {
				System.out.print("   \\n       ");
			}else {
				System.out.print("   " + root.payloadChar + "       ");
			}
			System.out.println(code);
		}
	}
	
	/*
	 * Given root node for decoding tree and bit stream, this
	 * decodes the bite stream and prints out the resulting message
	 */
	public void decode(MsgTree codes, String msg) {
		
	}
	
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		Scanner fscnr = null;
		
		System.out.print("Please enter filename to decode: ");
		fileName = scnr.next();
		
		try{
			File fp = new File(fileName);
			fscnr = new Scanner(fp);
			
			fscnr.close();
		}catch(FileNotFoundException e) {
			System.out.println("File name invalid.");
			scnr.close();
			return;
		}
		
		String encodingScheme = "";
		
		MsgTree rootMsgTreeNode = new MsgTree(encodingScheme);//construct tree from string with scheme
		
		System.out.println("character  code");
		System.out.println("-------------------------");
		
		printCodes(rootMsgTreeNode, "");//print out tree with preorder Traversal
		
		System.out.println("MESSAGE:");
		decode(rootMsgTreeNode, bitStream);
		
		scnr.close();
	}
}
