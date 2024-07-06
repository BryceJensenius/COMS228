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
 * 
 * prints encoding of each character
 * Prints Statistics on compression at end
 */
public class MsgTree {
	
	/*
	 * The character contained in this node
	 */
	public char payloadChar;
	
	/*
	 * node to the left of this node
	 */
	public MsgTree left;
	
	/*
	 * node to the right of this node
	 */
	public MsgTree right;
	
	/*
	 * String of bits that are an encoded message
	 */
	private static String encodedMessage;
	
	/*
	 * Create a tree from string input, ^ is inner node, other is leaf
	 * Uses pre-order traversal
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
	 * @return number of characters in the decoded message
	 */
	public static int decode(MsgTree codes, String msg) {
		if(codes == null || msg == null) {
			throw new NullPointerException();
		}
		
		//set to root node, or start of code scheme
		MsgTree curNode = codes;
		int i = 0;
		int messageLength = 0;//keep track of encoding length
		
		
		while(i < msg.length()) {
			if(msg.charAt(i) == '0') {
				if(curNode.left != null) {//there is element to left so havn't reached character
					curNode = curNode.left;
					i++;
					continue;
				}else {//reached the character
					System.out.print(curNode.payloadChar);
					curNode = codes;//go back to root node to begin reading next character
					messageLength++;
				}
			}else {
				if(curNode.right != null) {//there is element to left so havn't reached character
					curNode = curNode.right;
					i++;
					continue;
				}else {//reached the character
					System.out.print(curNode.payloadChar);
					curNode = codes;//go back to root node to begin reading next character
					messageLength++;
				}
			}
		}
		return messageLength;
	}
	
	/*
	 * given a file name, assembles the 1 or 2 lines of coding scheme into string returned
	 * and puts the encoded message in global variable
	 * @param fileName
	 * @return coder scheme
	 * @throws FileNotFoundException
	 */
	public static String readSchemeAndEcodedMessage(String fileName) throws FileNotFoundException{
		Scanner fscnr = new Scanner(new File(fileName));
			
		String scheme = fscnr.nextLine();//grab first line guaranteed to be scheme
		
		String temp = fscnr.nextLine();
		
		//if first two characters are not 0 or 1, this is also part of the code scheme
		//must check 2 characters because it is possible for coding scheme first character to be 0 or 1
		if(temp.charAt(0) != '0' && temp.charAt(0) != '1'
				&& temp.charAt(1) != '0' && temp.charAt(1) != '1') {//line was coding scheme
			//newline character
			scheme += '\n' + temp;
			encodedMessage = fscnr.nextLine();
		}else {//line grabbed was the encoded message
			encodedMessage = temp;
		}
		
		fscnr.close();
		return scheme;
	}
	
	/*
	 * Prints statistics of compression using encoded message and decoded message length
	 * @param length - length of decoded message
	 */
	public static void printStatistics(int length) {
		double bitsPerChar = encodedMessage.length()/(double)length;
		System.out.println("\n\nSTATISTICS:");
		System.out.print("Avg bits/char:   \t\t");
		System.out.printf("%.1f\n", bitsPerChar);
		System.out.println("Total characters:\t\t" + length);
		System.out.print("Space savings:   \t\t");
		System.out.printf("%.1f%%\n", (1 - (((double) encodedMessage.length()) / (length*16))) * 100);
	}
	
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		String codeScheme = null;
		String fileName;
		
		System.out.print("Please enter filename to decode: ");
		fileName = scnr.next();
		scnr.close();
		
		try{
			codeScheme = readSchemeAndEcodedMessage(fileName);
		}catch(FileNotFoundException e) {
			System.out.println("File " + fileName + " is invalid.\nMake sure to include .arch at end of file name.");
			return;
		}
		
		MsgTree rootMsgTreeNode = new MsgTree(codeScheme);//construct tree from string with scheme
		
		System.out.println("\ncharacter  code");
		System.out.println("-------------------------");
		
		printCodes(rootMsgTreeNode, "");//print out tree with preorder Traversal
		
		System.out.println("\nMESSAGE:");
		int length = decode(rootMsgTreeNode, encodedMessage);
		printStatistics(length);
		
		scnr.close();
	}
}
