package hatecode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class BinaryTreeVisit {

//this is used for being familiar with 3 recursive and 3 while loop way
    public static void preOrderTree(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val +" ");
        preOrderTree(root.left);
        preOrderTree(root.right);
    }
    public static void inOrderTree(TreeNode root) {
        if (root == null) return;
        inOrderTree(root.left);
        System.out.print(root.val+" ");
        inOrderTree(root.right);
    }
    public static void postOrderTree(TreeNode root) {
        if (root == null) return;
        postOrderTree(root.left);
        postOrderTree(root.right);
        System.out.print(root.val+" ");
    }
    
    public static void preOrderTreeByStack(TreeNode root) {
        if (root == null) return;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);

        while (!stack.empty()) {
            TreeNode n = stack.pop();
            System.out.printf("%d ", n.val);

            if (n.right != null) {
                stack.push(n.right);
            }
            if (n.left != null) {
                stack.push(n.left);
            }
        }
    }

    public static void inOrderTreeByStack(TreeNode root) {
        if (root == null)
            return;

        Stack<TreeNode> s = new Stack<TreeNode>();
        TreeNode curNode = root;

        while (!s.empty() || curNode != null) {
            if (curNode != null) {
                s.push(curNode);
                curNode = curNode.left;
            } else {
                TreeNode n = s.pop();
                System.out.printf("%d ", n.val);
                curNode = n.right;
            }
        }
    }
    public static void postOrderTreeByStack(TreeNode root) {
        if (root == null) return;

        Stack<TreeNode> s = new Stack<TreeNode>();
        TreeNode curNode = root;

        while (true) {
            if (curNode != null) {
                if (curNode.right != null) {
                    s.push(curNode.right);
                }
                s.push(curNode);
                curNode = curNode.left;
                continue;
            }

            if (s.isEmpty()) return;
            curNode = s.pop();

            if (curNode.right != null && !s.isEmpty() && curNode.right == s.peek()) {
                s.pop();
                s.push(curNode);
                curNode = curNode.right;
            } else {
                System.out.print(curNode.val + " ");
                curNode = null;
            }
        }
    }
    
    public  static void postOrderTreeByStack2(TreeNode root){
        Deque<TreeNode> deqList1 = new LinkedList<TreeNode>();
        Deque<TreeNode> deqList2 = new LinkedList<TreeNode>();
        deqList1.addFirst(root);
        while(!deqList1.isEmpty()){
            root = deqList1.pollFirst();
            if(root.left != null){
                deqList1.addFirst(root.left);
            }
            if(root.right != null){
                deqList1.addFirst(root.right);
            }
            deqList2.addFirst(root);
        }
        while(!deqList2.isEmpty()){
            System.out.print(deqList2.pollFirst().val + " ");
        }
    }
    
    public static TreeNode createBinaryTree() {
/*
          6
         / \
        8  1
      /  \
     0   3
        / \
       2  5
 */
 
        TreeNode rootNode =new TreeNode(6);
        TreeNode node8=new TreeNode(8);
        TreeNode node1=new TreeNode(1);
        TreeNode node0=new TreeNode(0);
        TreeNode node3=new TreeNode(3);
        TreeNode node2=new TreeNode(2);
        TreeNode node5=new TreeNode(5);
 
        rootNode.left=node8;
        rootNode.right=node1;
 
        node8.left=node0;
        node8.right=node3;
 
        node3.left=node2;
        node3.right=node5;
 
        return rootNode;
    }
    public static void main(String[] args) {
        // Creating a binary tree
        TreeNode rootNode=createBinaryTree();
        System.out.println("Using Recursive solution:");
        System.out.println("preOrderTree:");
        preOrderTree(rootNode);
        System.out.println("inOrderTree:");
        inOrderTree(rootNode);
        System.out.println("postOrderTree:");
        postOrderTree(rootNode);
 

        System.out.println("Using Iterative solution:");
        System.out.println("preOrderTreeByStack:");
        preOrderTreeByStack(rootNode);
        System.out.println("inOrderTreeByStack:");
        inOrderTreeByStack(rootNode);
        System.out.println("postOrderTreeByStack:");
        postOrderTreeByStack(rootNode);
        System.out.println("postOrderTreeByStack2:");
        postOrderTreeByStack2(rootNode);

    }
}
