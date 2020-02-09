package hatecode._0001_0999;

public class _549BinaryTreeLongestConsecutiveSequenceII {
/*
549. Binary Tree Longest Consecutive Sequence II
Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.

Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.

Example 1:
Input:
        1
       / \
      2   3
Output: 2
Explanation: The longest consecutive path is [1, 2] or [2, 1].
*/
    static int maxval = 0;
    public static int longestConsecutive2(TreeNode root) {
        longestPath(root);
        return maxval;
    }
    public static int[] longestPath(TreeNode root) {
        if (root == null) return new int[] {0,0};
        //increase consecutive counter and decrease consecutive counters
        int inr = 1, dec = 1;
        if (root.left != null) {
            //top down view, l stands for: from this node.left, most left leaf node
            //first means incr counters, 2nd means dec counts
            int[] l = longestPath(root.left);
            // from root to left child(l child is smaller) ,dec + 1
            if (root.val == root.left.val + 1) dec = l[1] + 1;
            //from root to left but increase(l child is bigger),so incr + 1
            else if (root.val == root.left.val - 1) inr = l[0] + 1;
        }
        //right branch is different because the consecutive can be from inorder, or just one tree
        //branch down
        //so if longestPath return the value, we can only choose 
        if (root.right != null) {
            int[] r = longestPath(root.right);
            if (root.val == root.right.val + 1)
                dec = Math.max(dec, r[1] + 1);
            else if (root.val == root.right.val - 1)
                inr = Math.max(inr, r[0] + 1);
        }
        //this means we want to connect left and right half together, so we can get max
        //
        maxval = Math.max(maxval, dec + inr - 1);
        return new int[] {inr, dec};
    }

    //post order visit, this is interview friendly 
    //we first visit left, 
    static int  max = 0;

    static class Result {
        TreeNode node;
        int inc;
        int des;
    }

    public static int longestConsecutive(TreeNode root) {
        traverse(root);
        return max;
    }

    private static Result traverse(TreeNode node) {
        if (node == null) return null;
        //suppose node is leaf node, so both are null, 
        //then we down-top to construct the result
        //inc and des = 1
        Result left = traverse(node.left);
        Result right = traverse(node.right);

        Result curr = new Result();
        curr.node = node;
        curr.inc = 1;
        curr.des = 1;
        if (left != null) {
            if (node.val - left.node.val == 1) {
                curr.inc = Math.max(curr.inc, left.inc + 1);
            } else if (node.val - left.node.val == -1) {
                curr.des = Math.max(curr.des, left.des + 1);
            }
        }
        if (right != null) {
            if (node.val - right.node.val == 1) {
                curr.inc = Math.max(curr.inc, right.inc + 1);
            } else if (node.val - right.node.val == -1) {
                curr.des = Math.max(curr.des, right.des + 1);
            }
        }
        //consider each node as root node, we always choose to connect them
        max = Math.max(max, curr.inc + curr.des - 1);
        return curr;
    }
    public static void main(String[] args) {
        
    }
}