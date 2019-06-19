package hatecode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SerializeandDeserializeBST
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : TODO
 */
public class SerializeandDeserializeBST {
    /**
     * 449. Serialize and Deserialize BST
Serialization is the process of converting a data structure or object into a 
sequence of bits so that 
it can be stored in a file or memory buffer, or transmitted across a network 
connection link to be reconstructed later in the same or another computer 
environment.

Design an algorithm to serialize and deserialize a binary search tree. 
There is no restriction on how your serialization/deserialization algorithm 
should work. You just need to ensure that a binary search tree can be serialized 
to a string and this string can be deserialized to the original tree structure.

The encoded string should be as compact as possible.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
     *
          5
         / \
        4   6
       /     \
      1       8

     time : O(n);
     space : O(n);
     * @param root
     * @return
     */

    // Encodes a tree to a single string.
    // for this question, we cannot use queue to serialize the tree because 
    // 3,1,4,null,2 if we use stack, it will be 3,1,2,4, if it is queue: 
    // it will be 3,1,4,2, but when we deserialize using getNode() we use recursive way to 
    // get left and right tree, which means left tree should be together, so we use queue
    // it will lost node 2 to right tree
    
    //we do not want to use null to use more space, we just use level traverse to add each node value. 
    //from left to right
    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder res = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            res.append(cur.val + " ");
            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
        }
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == "") return null;
        String[] str = data.split(" ");
        Queue<Integer> queue = new LinkedList<>();
        //add all of then into queue
        for (String s : str) {
            queue.offer(Integer.parseInt(s));
        }
        return getNode(queue);
    }
    //we use BST tree characteristic left < root < right, so we split the 
    //queue into 2 left and right and recursive to setup the tree
    public TreeNode getNode(Queue<Integer> queue) {
        if (queue.isEmpty()) return null;
        TreeNode root = new TreeNode(queue.poll());
        Queue<Integer> smallerQ = new LinkedList<>();
        //this is all left tree
        while (!queue.isEmpty() && queue.peek() < root.val) {
            smallerQ.offer(queue.poll());
        }
        root.left = getNode(smallerQ);
        //then left in queue is right tree
        root.right = getNode(queue);
        return root;
    }
}
