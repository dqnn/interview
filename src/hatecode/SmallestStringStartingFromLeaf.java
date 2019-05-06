package hatecode;

import java.util.*;
public class SmallestStringStartingFromLeaf {
/*
988. Smallest String Starting From Leaf
Given the root of a binary tree, each node has a value from 0 to 25 representing the letters 'a' to 'z': a value of 0 represents 'a', a value of 1 represents 'b', and so on.

Find the lexicographically smallest string that starts at a leaf of this tree and ends at the root.

(As a reminder, any shorter prefix of a string is lexicographically smaller: for example, "ab" is lexicographically smaller than "aba".  A leaf of a node is a node that has no children.)
*/
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
            return (null == node.left) ? dfs(node.right, suffix) : dfs(node.left, suffix);
        }

        String left = dfs(node.left, suffix);
        String right = dfs(node.right, suffix);

        return left.compareTo(right) <= 0 ? left : right;
    }

    private String ans = "~"; // dummy value '~' > 'z'

    public String smallestFromLeaf_Better(TreeNode root) {
        return helper(root, "");
    }

    private String helper(TreeNode n, String str) {
        if (n == null)
            return ans;
        str = (char) ('a' + n.val) + str;
        if (n.left == null && n.right == null && str.compareTo(ans) < 0) {
            ans = str;
        }
        dfs(n.left, str);
        dfs(n.right, str);
        return ans;
    }
    //this is wrong for 4,0,1,1, it would return be, it should be bae
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