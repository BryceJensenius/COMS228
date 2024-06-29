package binaryTrees;

import java.util.Queue;

public class BinaryTree<E extends Comparable<? super E>> {
	protected Node root;
	protected int size;
	
	protected class Node {
		public Node left;
		public Node right;
		public Node parent;
		public E data;
		
		public Node(E data, Node parent) {
			this.data = data;
			this.parent = parent;
		}
	}
	
	/*
	 * Default height call of root node
	 */
	public int getHeight() {
		return getHeight(root);
	}
	
	/*
	 * get the height of a given Node
	 */
	public int getHeight(Node cur) {
		if(cur == null) {
			return -1;
		}
		
		return 1 + Math.max(getHeight(cur.left), getHeight(cur.right));
	}
	
	public boolean add(E key) {
		if(root == null) {
			root = new Node(key, null);
			size++;
			return true;
		}
	}
	
	public Node binarySearch(E data) {
		if(data == null) {
			return null;
		}
		
		Node current = root;
		
		while(current != null) {
			int comp = data.compareTo(current.data);
			if(comp == 0) {
				return current;
			}else if(comp < 0) {
				current = current.left;
			}else {
				current = current.right;
			}
		}
		return current;
	}
	
	public void preOrderTraversal() {
		preOrdertraversalRec(root);
	}
	
	private void preOrdertraversalRec(Node cur) {
		if(cur == null) {
			return;
		}
		
		System.out.println(cur.data);
		preOrdertraversalRec(cur.left);
		preOrdertraversalRec(cur.right);
	}
	
	public void levelOrdertTraversal() {
		levelOrdertTraversalRec(root);
	}
	
	private void levelOrdertTraversalRec(Node cur){
		if(cur == null) {
			return;
		}
		Queue<Node> q = new QueueImplementation<Node>();
		
		q.add(cur);
		
		while(!q.isEmpty()) {
			Node current = q.poll();
			
			System.out.println(current.data);
			
			q.add(current.left);
			q.add(current.right);
		}
		
	}
}
