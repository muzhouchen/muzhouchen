package binarysearchtree;
/**
 * 
 * @author muzhouchen
 * @para Node: Each node of binary search tree has element, leftChild and rightChild;
 *  This class includes the structure of tree and couple methods;
 */
public class Tree {
	// The class to build node; Element is determined to be integer.;
	public static class Node{
		//field
		private Integer element;
		private Node leftChild;
		private Node rightChild;
		public Node(int e) {
			this.element = e;
			this.leftChild = null;
			this.rightChild = null;
		}
		//the getElement method which can get the integer value from the node;
		public int getElement() {
			return this.element;
		}
		//setElement method to set value, however i didn't use it for this project;
		public int setElement() {
			return this.getElement();
		}
		//important method which allow us to get leftChild if we locate at the parent which has leftChild;
		public Node getLeftChild() {
			return this.leftChild;
		}
		//this method allow us to set value to leftChild;
		public void setLeftChild(Node l) {
			this.leftChild = l;
		}
		//same as getLeftChild;
		public Node getRightChild() {
			return this.rightChild;
		}
		//same as setLeftChild;
		public void setRightChild(Node r) {
			this.rightChild = r;
		}	
	}
	// we initialize a root as the start of tree and let it be null;
	private Node root;
	public Tree() {
		this.root = null;
	}
	//method to get root;
	public Node getRoot() {
		return this.root;
	}
	//to start the insert method with what we want to insert e, and start at root;
	public void insert(int e) {
		this.root = this.recInsert(root, e);
	}
	//decide where to place the new element by using a recursive function;
	private Node recInsert(Node node, int e) {
		//base condition just in case;
		if(node == null) {
			return new Node(e);
		}
		//if the value is smaller than the element we have in the node, we go leftChild and call 
		//the function itself to make a recursion;
		if( node.getElement() > e){
			node.setLeftChild(this.recInsert(node.getLeftChild(), e));
			return node;
		}
		//another situation when the value is bigger than the element we have in the node;
		else {
			node.setRightChild(this.recInsert(node.getRightChild(), e));
			return node;
		}
	}
	//use boolean to decide if the tree includes the key we want to check, start at root;
	public boolean search(int key) {
		return this.recSearch(root,key);
	}
	//use the recursive function to move leftChild and rightChild so that make the method effective O(logn);
	private boolean recSearch(Node node, int key) {
		if(node == null) {
			return false;
		}
		//return true when we find the key in tree;
		if(node.getElement() == key) {
			return true;
		}
		//if key is larger than current element, go right and do a recursion;
		else if(node.getElement() < key) {
			return this.recSearch(node.getRightChild(), key);
		}
		//if key is smaller than current element, go left and do a recursion;
		else{
			return this.recSearch(node.getLeftChild(), key);
		}
	}
	//the method to delete specific element in the tree;
	public void delete(int key) {
		this.root = recDelete(root, key);
	}
	//also use the recursive function to make it more effective;
	private Node recDelete(Node node, int key) {
		if(node == null) {
			return null;
		}
		//start deleting when we find the key in node
		if(node.getElement() == key) {
			//case 1: the node we want to delete has no child; just let it equals to null in order to delete;
			if(node.getLeftChild() == null && node.getRightChild() == null) {
				node = null;
			}
			//case 2: the node has a rightChild. we just let the current node replaced by it's rightChild;
			else if(node.getLeftChild() == null && node.getRightChild() != null) {
				node = node.getRightChild();
			}
			//same as case 2, replace the node by it's leftChild;
			else if(node.getLeftChild() != null && node.getRightChild() == null) {
				node = node.getLeftChild();
			}
			//case 3: when we found that the node we need to delete has 2 children; 
			else {
				//let the inOrder string become a inOrder integer list;
				String[] tempString = temp.split(" ");
				int[] tempInt = new int[tempString.length];
				//define parent as an integer;
				int parent = 0;
				for(int i = 0; i < tempString.length; i++) {
					tempInt[i] = Integer.parseInt(tempString[i]);
				}
				//let parent store the value of the inOrder successor of key, so that we can swap the value
				for(int i = 0; i < tempString.length;i++) {
					if(key == tempInt[i]) {
						 parent = tempInt[i + 1];
					}
				}
				//we need to delete the node which has integer parent, if we give the value parent to the delete node
				//the function would be an error of out of bound;
				this.recDelete(root, parent);
				//after delete the original node with parent, we replace the node we want to delete with value parent
				node.element = parent;
				// what we did above is swap inOrder successor and delete so the tree would connected well after deleting;
			}
			return node;			
		}
		//go right when key is bigger as we did in searching method;
		else if(node.getElement() < key) {
			node.setRightChild(this.recDelete(node.getRightChild(), key));
			return node;
		}
		//go left when key is smaller as we did in searching method;
		else {
			node.setLeftChild(this.recDelete(node.getLeftChild(), key));
			return node;
		}
	}

	public static String temp;
	//the inOrder method, we set all nodes in the order of (leftChild), (root), (rightChild);
	public String inOrder() {
		temp = this.recInOrder(root);
		return temp;
	}
	private String recInOrder(Node node) {
		if(node == null) {
			return "";
		}
		else {
			return this.recInOrder(node.getLeftChild())+ node.getElement()+ " " +this.recInOrder(node.getRightChild());
		}
	}
	//the preOrder method, we set all nodes in the order of (root) ,(leftChild), (rightChild)
	public String preOrder() {
		return this.recPreOrder(root);
	}
	private String recPreOrder(Node node) {
		if(node == null) {
			return "";
		}
		else {
			return node.getElement() +" "+ this.recPreOrder(node.getLeftChild())  + this.recPreOrder(node.getRightChild());
		}
	}
	//the postOrder method, we set all nodes in the order of (leftChild), (rightChild), (Root)
	public String postOrder() {
		return this.RecPostOrder(root);
	}
	private String RecPostOrder(Node node) {
		if(node == null) {
			return "";
		}
		else {
			return this.RecPostOrder(node.getLeftChild()) + this.RecPostOrder(node.getRightChild()) + " " + node.getElement();
		}
	}
	//toString method which used to print the whole tree and let it to be visualized;
	//I print the tree in this way: (leftChild)root(rightChild)
	//each child can be another subtree if they have child;
	public String toString() {
		return "("+ this.RecToString(root) +")";
	}
	private String RecToString(Node node) {
		if(node == null) {
			return "";
		}
		else {
			return "("+this.RecToString(node.getLeftChild())+")" + node.getElement() + "(" + this.RecToString(node.getRightChild())+")";
		}
	}
}
