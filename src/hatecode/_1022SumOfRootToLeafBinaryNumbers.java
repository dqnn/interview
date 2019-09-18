package hatecode;

public class _1022SumOfRootToLeafBinaryNumbers {
/*
1022. Sum of Root To Leaf Binary Numbers
Given a binary tree, each node has value 0 or 1.  Each root-to-leaf path represents a binary number starting with the most significant bit.  For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.

For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.

Return the sum of these numbers.

Input: [1,0,1,0,1,0,1]
Output: 22
*/
    //thinking process:
    //
    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }

    public int dfs(TreeNode root, int val) {
        if (root == null) return 0;
        val = val * 2 + root.val;
        return root.left == root.right ? val : dfs(root.left, val) + dfs(root.right, val);
    }
}