package binarysearchtree;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * 
 * @author muzhouchen
 *  Demo class
 */
public class Demo {
	public static void main(String[]args) {
		//create a result list with a size 100(we can let it bigger or let it become a variable if needed)
		int[] result = new int[100];
		//use scanner to input integers as we did in homework 1;
		try {
			Scanner input = new Scanner(new File("input.txt"));
			while (input.hasNext()) {
				String[] number = input.next().split(",");
				result = new int[number.length];
				for(int i = 0 ; i < number.length; i++) {
					result[i] = Integer.parseInt(number[i]);
				}
			}
			input.close();
		}catch(FileNotFoundException exc) {
		}
		
		Tree binaryTree = new Tree();
		//insert elements from result to binary tree;
		for(int i = 0; i < result.length; i++) {
			binaryTree.insert(result[i]);
		}
		//format
		System.out.println("Visualized Tree: " + binaryTree.toString());
		//System.out.println("Test Search: "+ binaryTree.search(5));
		System.out.println("In-order traversal: "+ binaryTree.inOrder());
		System.out.println("Pre-order traversal: "+ binaryTree.preOrder());
		System.out.println("Post-order traversal: " + binaryTree.postOrder());
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a number to test search in the binary search tree: ");
		System.out.println("Result of testing search: " + binaryTree.search(input.nextInt()));
		System.out.println("Enter a number to delete from the binary search tree: ");
		binaryTree.delete(input.nextInt());
		System.out.println("Visualized Tree after deleting: " + binaryTree.toString());
		input.close();

	}
}
