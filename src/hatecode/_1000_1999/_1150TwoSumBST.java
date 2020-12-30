package hatecode._1000_1999;

import hatecode._0001_0999.TreeNode;
import java.util.*;
public class _1150TwoSumBST {
    /*
     * 1150. Two Sum BST
    Given two binary search trees, return True if and only if there is a node 
    in the first tree and a node in the second tree whose values sum up 
    to a given integer target.
    
    Input: root1 = [2,1,4], root2 = [1,0,3], target = 5
    Output: true
    Explanation: 2 and 3 sum up to 5.
       2              1
      /  \           / \
    1     4         0   3
   
   Constraints:
Each tree has at most 5000 nodes.
-10^9 <= target, node.val <= 10^9
*/
    
    //thinking process: O(n+m)/O(lg(max(h1, h2))
    
    //this TC is high,
    // O(lgh1 + n*lgh1)
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        if(root1 == null || root2==null) return false;
        
        // 利用root2的当前节点去root1中找到和为target的值。
        // 若没找到，继续用root2的左右子节点去找
        return help(root1, root2.val, target)
            || twoSumBSTs(root1, root2.left, target)
            || twoSumBSTs(root1, root2.right, target);
    }

    boolean help(TreeNode root, int value, int target) {
        // 如果root1为空返回false
        if (root == null)
            return false;
        // 计算两棵树当前节点和
        int sum = root.val + value;
        // 如果sum等于target，返回true
        if (sum == target) return true;
        // 如果sum大于target，减小root1的值
        // 使用root1左子节点的值继续递归
        if (sum > target) {
            return help(root.left, value, target);
        } else { // 反之使用root1右子节点的值继续递归
            return help(root.right, value, target);
        }
    }
    
    //brute force solution, 
    //thinking process:  O(n+m)/O(lg(max(h1, h2))
    public boolean twoSumBSTs_BruteForce(TreeNode root1, TreeNode root2, int target) {
        if (root1 == null || root2 == null) return false;
        Set<Integer> set1 = new HashSet<>(), set2 = new HashSet<>();
        helper(root1, set1);
        helper(root2, set2);
        
        for(int ele: set1) {
            if (set2.contains(target - ele)) return true;
        }
        return false;
    }
    
    private void helper(TreeNode root, Set<Integer> set) {
        if (root == null) return;
        set.add(root.val);
        helper(root.left, set);
        helper(root.right, set);
    }
    
    
}

