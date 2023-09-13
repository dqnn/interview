package hatecode._1000_1999;

import java.util.*;
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
*/

public class _1650LowestCommonAncestorofABinaryTreeIII {
/*
1650. Lowest Common Ancestor of a Binary Tree III
Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).

Each node will have a reference to its parent node. The definition for Node is below:

According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)."
*/
    
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }
    
    //thinking process: 
    
    //if two runs in a circle with different speed, 
    //they will meet eventually, LC 160
    /*
     * here they have same speed not in circile, they will eventually meet the point of intersection why?
     * because their distance are the same, speed is the same, so they will meet at the intersection which is the end
     *     S1       S3
     *  ---------|-----
     *           |
     *  ---------
     *    S2
     * 
     * 
     * S1 != S2 just no way to draw the line, a is on S1, b is on S2, a's total disance = s1+ s3 + s2 since it will back to b's 
     * starting point
     * same as b
     */   
    
    //s = v * t, v are the same, time is also the same
    //S = (q->root + p->root), they have same distance finally
    public Node lowestCommonAncestor(Node p, Node q) {
        if (p == null || q == null) return null;
        
        Node a = p, b = q;
        
        //we change to another node if reach null
        while(a !=b) {
            a = a == null? q: a.parent;
            b = b == null ? p: b.parent;
        }
        
        return a;
       
    }
    
    //interview friendly for BF
    public Node lowestCommonAncestor_BF(Node p, Node q) {
        if (p == null || q == null) return null;
        
        Set<Node> set = new HashSet<>();
        Node cur = p;
        while(cur != null) {
            set.add(cur);
            cur = cur.parent;
        }
        
        while(q!=null) {
            if (set.contains(q)) return q;
            q = q.parent;
        }
        
        return null;
    }
}