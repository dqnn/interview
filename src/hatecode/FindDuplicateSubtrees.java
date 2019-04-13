package hatecode;

import java.util.*;
public class FindDuplicateSubtrees {
/*
652. Find Duplicate Subtrees
Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with same node values.

Example 1:

        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
{3->2->4->#->#->#->4->#->#=1, 
4->#->#=3, 
1->2->4->#->#->#->3->2->4->#->#->#->4->#->#=1, 
2->4->#->#->#=2}


*/
    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new LinkedList<>();
        
        Map<String, Integer> map = new HashMap<>();
        postorder(root, map, res);
        System.out.println(map);
        return res;
    }

    //print the track since we asked for the pattern, like all left
    public static String postorder(TreeNode cur, Map<String, Integer> map, List<TreeNode> res) {
        if (cur == null) return "#";
        
        String serial = cur.val + "->" + postorder(cur.left, map, res) + "->" + postorder(cur.right, map, res);
        //find similiar tree, then add to result
        if (map.getOrDefault(serial, 0) == 1) res.add(cur);
        
        map.put(serial, map.getOrDefault(serial, 0) + 1);
        return serial;
    }
}