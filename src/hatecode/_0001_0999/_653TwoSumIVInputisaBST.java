package hatecode._0001_0999;

import java.util.HashSet;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : TwoSumIVInputisaBST
 * Date : Sep, 2018
 * Description : TODO
 */
public class _653TwoSumIVInputisaBST {

    /**
     * 653. Two Sum IV - Input is a BST
     Given a Binary Search Tree and a target number, return true if there exist two
     elements in the BST such that their sum is equal to the given target.

     Example 1:
     Input:
         5
        / \
       3   6
      / \   \
     2   4   7

     Target = 9

     Output: True
     * @param root
     * @param k
     * @return
     */

    //time : O(n), space : O(n)
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        HashSet<Integer> set = new HashSet<>();
        return helper(root, k, set);
    }

    public boolean helper(TreeNode root, int k, HashSet<Integer> set) {
        if (root == null) return false;
        if (set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val);
        return helper(root.left, k, set) || helper(root.right, k, set);
    }

    //time : O(nlogn) space : O(h)  h : logn ~ n
    // 3 use cases
    //1 in left  2 in right, both has 1
    //so we have first and second
    public boolean findTarget2(TreeNode root, int k) {
        return firstDfs(root, root, k);
    }
    //try to find first number
    public boolean firstDfs(TreeNode first, TreeNode second, int k) {
        if (first == null) return false;
        // find second number, if first one is root, or both number in left tree, or in right tree
        return secondDfs(first, second, k - first.val)
                || firstDfs(first.left, second, k)
                || firstDfs(first.right, second, k);
    }
    //find second num
    public boolean secondDfs(TreeNode first, TreeNode second, int k) {
        if (second == null) return false;
        return (second.val == k) && (first != second)
                || (second.val > k) && secondDfs(first, second.left, k)
                || (second.val < k) && secondDfs(first, second.right, k);
    }

}
