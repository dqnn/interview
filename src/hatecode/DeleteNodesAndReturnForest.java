package hatecode;

import java.util.*;
import java.util.stream.Collectors;
public class DeleteNodesAndReturnForest {
/*
1110. Delete Nodes And Return Forest
Given the root of a binary tree, each node in the tree has a distinct value.

After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

Return the roots of the trees in the remaining forest.  You may return the result in any order

Example 1:

Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]
*/
    
    //O(n)/O(k) k is the size of to_del
    //thinking process:
    //given a binary tree with a set of values, node needs to remove,
    //return the set of nodes which become a alone forest
    
    //
    public List<TreeNode> delNodes(TreeNode root, int[] to_del) {
        List<TreeNode> res = new ArrayList<>();
        if(root == null) return res;
        Set<Integer> rm = new HashSet<>(Arrays.stream(to_del).boxed().collect(Collectors.toList()));
        
        helper(root, res, rm, true);
        
        return res;
    }
    
    private TreeNode helper(TreeNode node, List<TreeNode> res, Set<Integer> rm, boolean is_root) {
        if(node == null) return null;
        boolean deleted = rm.contains(node.val);
        if(is_root && !deleted) res.add(node);
        
        node.left = helper(node.left, res, rm, deleted);
        node.right =  helper(node.right, res, rm, deleted);
        return deleted ? null : node;
        
    }
}