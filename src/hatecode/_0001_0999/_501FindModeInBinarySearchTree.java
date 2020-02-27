package hatecode._0001_0999;
import java.util.*;
public class _501FindModeInBinarySearchTree {
/*
501. Find Mode in Binary Search Tree O(1) space

Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.
 

For example:
Given BST [1,null,2,2],

   1
    \
     2
    /
   2
 

return [2].


*/
    //thinking process: 
    //the problem is to say in a BST (equal is allowed), we want to know most frequency node value, if there are
    //mutiple results, return all of them in O(1) space, stack space is ignored
    
    //so given stack space is ignored and O(1), so we need to use variable like morris travel to 
    // remember the previos node value, BST is visited by inorder visit so the output will be 
    //sorted, so if we remember the previos node value as prev, then 
    //we are able to know how many node value are repeated, if not, and we are sure we never see
    //it again. 
    Integer prev  = null;
    int count = 1;
    int max = 0;
    public int[] findMode(TreeNode root) {
        if (root == null) return new int[]{};
        List<Integer> list = new ArrayList<>();
        
        dfs(root, list);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public void dfs(TreeNode node, List<Integer> list) {
        if (node == null) return;
        dfs(node.left, list);
        if (prev != null) {
            if (prev == node.val) count++;
            else count = 1;
        }
        //update max
        if (count > max) {
            list.clear();//list.removeAll()
            max = count;
            list.add(node.val);
        } else if (count == max) {
            list.add(node.val);
        }
        prev = node.val;
        dfs(node.right, list);
    }
}