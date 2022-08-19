package hatecode._1000_1999;

import java.util.*;


public class _1522DiameterofNAryTree {
    class Node {
        public int val;
        public List<Node> children;

        
        public Node() {
            children = new ArrayList<Node>();
        }
        
        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }
        
        public Node(int _val,ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    }
/*
1522. Diameter of N-Ary Tree
Given a root of an N-ary tree, you need to compute the length of the diameter of the tree.

The diameter of an N-ary tree is the length of the longest path between any two nodes in the tree. This path may or may not pass through the root.

(Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value.)

 

Example 1:



Input: root = [1,null,3,2,4,null,5,6]
Output: 3
Explanation: Diameter is shown in red color.
*/
    //thinking process: O(nlg2)/O(h)
    /*
     * the problem is to say: given one N-trie tree, return the diameter of the tree.
     * 
     * it is the same as  543. Diameter of Binary Tree
     * 
     * we here use PQ to sort the tree
     */
    int res = Integer.MIN_VALUE;
    public int diameter(Node root) {
        helper(root);
        return res == Integer.MIN_VALUE ? 0 : res;
    }
    
    
    private int helper(Node root) {
        if(root == null) return 0;
        if (root.children == null || root.children.size() == 0) return 1;
        
        PriorityQueue<Integer> pq= new PriorityQueue<>((a, b)->(a -b));
        for(Node e: root.children) { 
            int path = helper(e);
            if (path > 0) pq.offer(path);
            if (pq.size() > 2) {
                pq.poll();
            }
        }
        
        int l = pq.size() > 0 ? pq.poll(): 0;
        int r = pq.size() > 0 ? pq.poll(): 0;
        res = Math.max(res, l + r);
        return Math.max(l, r) + 1;
       
    }
}