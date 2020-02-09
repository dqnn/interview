package hatecode._0001_0999;

import java.util.*;
public class _988SmallestStringStartingFromLeaf {
/*
988. Smallest String Starting From Leaf
Given the root of a binary tree, each node has a value from 0 to 25 representing the letters 'a' to 'z': a value of 0 represents 'a', a value of 1 represents 'b', and so on.

Find the lexicographically smallest string that starts at a leaf of this tree and ends at the root.

(As a reminder, any shorter prefix of a string is lexicographically smaller: for example, "ab" is lexicographically smaller than "aba".  A leaf of a node is a node that has no children.)
      a
    /  \
   b    c
  / \   / \
 d  e  d   e
Input: [0,1,2,3,4,3,4]
Output: "dba"
*/
    //thinking process:
    
    //from leaf to root, find most smalest lex order string
    
    //so when we visit the tree from root to leaf, we append string 
    //as prefix to previous strings, so each node consider as tree,
    //top down, each node will give answer when the node is root, which
    //is the smaller lex string, until we return back to root
    public String smallestFromLeaf(TreeNode root) {
        return dfs(root, "");
    }

    public String dfs(TreeNode node, String suffix) {
        if (null == node) return suffix;
        suffix = "" + (char) ('a' + node.val) + suffix;
        if (null == node.left && null == node.right) {
            return suffix;
        }
        if (null == node.left || null == node.right) {
            return (null == node.left) ? 
                    dfs(node.right, suffix) : dfs(node.left, suffix);
        }

        String left = dfs(node.left, suffix);
        String right = dfs(node.right, suffix);

        return left.compareTo(right) <= 0 ? left : right;
    }
    //best performance
    private String res = "~"; // dummy value '~' > 'z'

    public String smallestFromLeaf_Better(TreeNode root) {
        return helper(root, "");
    }

    private String helper(TreeNode node, String str) {
        if (node == null) return res;
        str = (char) ('a' + node.val) + str;
        if (node.left == null && node.right == null && str.compareTo(res) < 0) {
            res = str;
        }
        dfs(node.left, str);
        dfs(node.right, str);
        return res;
    }
    
    //this is wrong for 4,0,1,1, it would return be, it should be bae,
    
    //this is top-down
    public String smallestFromLeaf_WrongAnswer(TreeNode root) {
        if (root == null) return null;
        return dfs(root);
    }
    
    private String dfs(TreeNode node) {
        char c = (char) (node.val + 'a');
        String l = null, r = null;
        if (node.left != null) {
            l = dfs(node.left);
        }
        if (node.right != null) {
            r = dfs(node.right);
        }
        if (l == null && r == null) return "" + c;
        if (l == null) return r + c;
        if (r == null) return l + c;
        return (l.compareTo(r) > 0 ? r : l) + c;
    }
}