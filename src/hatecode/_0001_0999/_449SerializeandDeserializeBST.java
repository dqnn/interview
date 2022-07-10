package hatecode._0001_0999;

import java.util.*;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SerializeandDeserializeBST
 * Creator : professorX
 * Date : Sep, 2018
 * Description : TODO
 */
public class _449SerializeandDeserializeBST {
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
    
    public class Codec_BST {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serialize(root, sb);
            return sb.toString();
        }
        //[5,3,6,2,4]-->"5,3,2,4,6"
        /*
         *         5
         *       /   \
         *      3     6
         *     / \
         *    2   4
         */
        public void serialize(TreeNode root, StringBuilder sb) {
            if (root == null) return;
            sb.append(root.val).append(",");
            serialize(root.left, sb);
            serialize(root.right, sb);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.isEmpty()) return null;
            Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
            return deserialize(q, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        
        public TreeNode deserialize(Queue<String> q, int lower, int upper) {
            if (q.isEmpty()) return null;
            String s = q.peek();
            int val = Integer.parseInt(s);
            if (val < lower || val > upper) return null;
            q.poll();
            TreeNode root = new TreeNode(val);
            root.left = deserialize(q, lower, val);
            root.right = deserialize(q, val, upper);
            return root;
        }
    }
    
    public class Codec_BT {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serialize(root, sb);
            return sb.toString();
        }
        //[5,3,6,2,4] ---> 5,3,2,#,#,4,#,#,6,#,#,
        public void serialize(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append("#").append(",");
            } else {
                sb.append(root.val).append(",");
                serialize(root.left, sb);
                serialize(root.right, sb);
            }
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
            return deserialize(q);
        }
        
        public TreeNode deserialize(Queue<String> q) {
            String s = q.poll();
            if (s.equals("#")) return null;
            TreeNode root = new TreeNode(Integer.parseInt(s));
            root.left = deserialize(q);
            root.right = deserialize(q);
            return root;
        }
    }
}
