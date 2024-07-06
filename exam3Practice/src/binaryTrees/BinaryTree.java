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
	
	public void levelOrderTraversal2(Node n) {
		if(n == null) {
			return;
		}
		Queue<Node> q = new QueueImplementation<>();
		q.add(n);
		while(!q.isEmpty()) {
			Node cur = q.poll();
			System.out.println(cur.data);
			if(cur.left != null) {
				q.add(cur.left);
			}
			if(cur.right != null) {
				q.add(cur.right);
			}
		}
	}
	
	public boolean add2(E key) {
		if(root == null) {
			root = new Node(key, null);
			size++;
			return true;
		}
		
		Node cur = root;
		while(true) {
			int comp = key.compareTo(cur.data);
			if(comp == 0) {
				return false;
			}else if(comp < 0) {
				if(cur.left == null) {
					cur.left = new Node(key, cur);
					size++;
					return true;
				}
				cur = cur.left;
			}else {
				if(cur.right == null) {
					cur.right = new Node(key, cur);
					size++;
					return true;
				}
				cur = cur.right;
			}
		}
	}
	
	public Node binarySearchTree2(E key) {
		if(key == null) {
			return null;
		}
		
		Node cur = root;
		while(cur != null) {
			int comp = key.compareTo(cur.data);
			if(comp == 0) {
				return cur;
			}else if(comp < 0) {
				cur = cur.left;
			}else {
				cur = cur.right;
			}
		}
		return cur;
	}
	
	public Node successor2(Node n) {
		if(n == null) {
			return null;
		}
		Node s = null;
		if(n.right != null) {
			s = n.right;
			while(s.left != null) {
				s = s.left;
			}
		}else {
			s = n.parent;
			Node child = n;
			while(s != null && child == s.right) {
				child = s;
				s = s.parent;
			}
		}
		return s;
	}
	
	public void unlink2(E key) {
		Node n = binarySearchTree2(key);
		if(n == null) {
			return;
		}
		
		if(n.right != null && n.left != null) {
			Node s = successor2(n);
			n.data = s.data;
			n = s;
		}
		
		Node replacement = null;
		if(n.right != null) {
			replacement = n.right;
		}else if(n.left != null) {
			replacement = n.left;
		}
		
		if(n.parent == null) {
			root = replacement;
		}else {
			if(n == n.parent.right) {
				n.parent.right = replacement;
			}else {
				n.parent.left = replacement;
			}
		}
		
		if(replacement != null) {
			replacement.parent = n.parent;
		}
		
		size--;
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
	
	public void unlink(Node n) {
		if(n == null) {
			throw new NullPointerException();
		}
		
		if(n.right != null && n.left != null) {
			Node s = successor(n);
			n.data = s.data;
			n = s;
		}
		Node replacement = null;
		if(n.left != null) {
			replacement = n.left;
		}else if(n.right != null) {
			replacement = n.right;
		}
		
		if(n.parent == null) {
			root = replacement;
		}else {
			if(n == n.parent.left) {
				n.parent.left = replacement;
			}else {
				n.parent.right = replacement;
			}
		}
		
		if(replacement != null) {
			replacement.parent = n.parent;
		}
		size--;
	}		
	
	public Node successor(Node n) {
		if(n == null) {
			return null;
		}
		Node s = null;
		if(n.right != null) {
			s = n.right;
			while(s.left != null) {
				s = s.left;
			}
		}else {
			s = n.parent;
			Node child = n;
			while(s != null && child == s.right) {
				child = s;
				s = s.parent;
			}
		}
		return s;
	}
	
	public boolean add(E key) {
		if(root == null) {
			root = new Node(key, null);
			size++;
			return true;
		}
		
		Node cur = root;
		while(true) {
			int comp = key.compareTo(cur.data);
			if(comp == 0) {
				return false;
			}else if(comp < 0) {
				if(cur.left == null) {
					cur.left = new Node(key, cur);
					size++;
					return true;
				}
				cur = cur.left;
			}else {
				if(cur.right == null) {
					cur.right = new Node(key, cur);
					size++;
					return true;
				}
				cur = cur.right;
			}
		}
	}
	
	public Node binarySearch(E data) {
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
		levelOrderTraversal(root);
	}
	
	public void levelOrderTraversal(Node n) {
		if(n == null) {
			return;
		}
		
		Queue<Node> q = new QueueImplementation<>();
		q.add(n);
		while(!q.isEmpty()) {
			Node cur = q.poll();
			System.out.println(cur.data);
			if(cur.left != null) {
				q.add(cur.left);
			}
			if(cur.right != null) {
				q.add(cur.right);
			}
		}
	}
}
