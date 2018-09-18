/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw4.part2;
import java.util.*;
import java.io.*;

/**
 *
 * @author ASUS
 */
public class Hw4Part2 {

    /**
     * @param args the command line arguments
     */
    
    public static class Node{
        int key, height;
        Node left, right;
        
        public Node(int key){
            this.key = key;
        }
    }
    public static void insert(Node node, int key){ // insert to binary tree with random generator of 0.5 probability for each left and right subtree.
        Random random = new Random();
        int n = random.nextInt(2);
        if (n == 0){
            if (node.left != null){
                insert(node.left, key);
            } else{
                Node temp = new Node(key);
                node.left = temp;
            }
        } else {
            if (node.right != null){
                insert (node.right, key);
            } else{
                Node temp = new Node(key);
                node.right = temp;
            }
        }
        node.height = 1 + max(height(node.left), height(node.right));
    }
    public static boolean isBST(Node node){ // Check for bst order
        return isBST(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    public static boolean isBST(Node node, int min, int max){ // helper method
        if (node == null) return true; // base case
        if (node.key < min || node.key > max) return false;
        return (isBST(node.left, min, node.key - 1) && isBST(node.right, node.key + 1, max));
    }
    
    static int height(Node N){ // get the height of the node
        if (N == null)
                return 0;
        return N.height;
    }
    static int max(int a, int b){ // get max of 2 ints
        return (a > b) ? a :b;
    }
    
    static int getBalance(Node N){ // get the balance of the node
        if (N == null){
            return 0;
        }
        return height(N.left) - height(N.right);
    }
    public static boolean isAVL(Node node){ // check for balance property
        if (node == null) return true;
        if (getBalance(node) < -1 || getBalance(node) > 1) return false;
        else return (isAVL(node.left) && isAVL(node.right));
    }
    
    static void inOrder(Node node){ // method to help print out inorder for checking
        if (node != null){
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }
    
    public static void main(String[] args) {
       Random random = new Random();
       int num = random.nextInt(30); // random keys
       int num2 = random.nextInt(30); // random amount of keys
       Node root = new Node(num);
       int array[] = new int[30]; // create an array to check if the random number has been generated before.
       array[num] = 1;
       for (int i = 0; i < num2; i++){ // insert 10 more keys
           while(true){
               num = random.nextInt(30);
               if (array[num] == 0){
                   array[num] = 1;
                   break;
               }
           }
           insert(root, num);
       }
       inOrder(root);
       if (isBST(root)){
           System.out.println("\nThe tree has BST order property");
       } else  System.out.println("\nThe tree doesnt follow BST order property");
       if (isAVL(root)){
           System.out.println("The tree has AVL balance condition");
       } else  System.out.println("The tree doesnt follow AVL balance condition");
       
    }
    
}
