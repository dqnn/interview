package hatecode._1000_1999;

import java.util.*;
import java.util.stream.Collectors;

import hatecode._0001_0999.TreeNode;
public class _1110DeleteNodesAndReturnForest {
/*
1110. Delete Nodes And Return Forest
Given the root of a binary tree, each node in the tree has a distinct value.

After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

Return the roots of the trees in the remaining forest.  You may return the result in any order

Example 1:
           1
        /    \ 
       2      3
      / \    / \
     4   5  6   7
Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]
*/
    
    
    //O(n)/O(k) k is the size of to_del
    //thinking process:
    //given a binary tree with a set of values, node needs to remove,
    //return the set of nodes which become a alone forest
    
    //from the above example, we can see 1, 2, 4, null. 6.  7. they become 
    //the forest, first set has a null because the parent still there
    
    //there was no dup value in the tree, and it is a tree problem, so
    //we suppose we use 3 ways of visiting to see whether we can find a way
    
    //when we visiting the tree, we need to do some cutting on the tree,
    //so 1. if the node is in the remove set, then we need to visit the tree like a new one
    
    // 2. if the node is not in the remove set, then it must be a node in a new forest,but 
    //we only want the root node of that forest, so we need a flag to pass from context this is root, 
    //if the previous stack is in removed set, then this one must be root, else not
    //last difficulty one is the remove the leaf node but we need return null
    public List<TreeNode> delNodes(TreeNode root, int[] to_del) {
        List<TreeNode> res = new ArrayList<>();
        if(root == null) return res;
        Set<Integer> rm = new HashSet<>(Arrays.stream(to_del).boxed().collect(Collectors.toList()));
        
        helper(root, res, rm, true);
        
        return res;
    }
    
    /*
     * 
     */
    private TreeNode helper(TreeNode node, List<TreeNode> res, Set<Integer> rm,
            boolean is_root) {
        if(node == null) return null;
        boolean deleted = rm.contains(node.val);
        if(is_root && !deleted) res.add(node);
        
        node.left = helper(node.left, res, rm, deleted);
        node.right =  helper(node.right, res, rm, deleted);
        return deleted ? null : node;
    }
}