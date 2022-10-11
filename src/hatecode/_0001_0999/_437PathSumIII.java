package hatecode._0001_0999;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Date : Sep, 2018
 * Description : 437. Path Sum III
 */
public class _437PathSumIII {
    /**
     * You are given a binary tree in which each node contains an integer value.

     Find the number of paths that sum to a given value.

     The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

     The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

     Example:

     root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

           10
          /  \
         5   -3
        / \    \
       3   2   11
      / \   \
     3  -2   1

     Return 3. The paths that sum to 8 are:

     1.  5 -> 3
     2.  5 -> 2 -> 1
     3. -3 -> 11

     1, DFS
     2, DFS + Memoization : HashMap<Integer, Integer>  <curSum, Num>

     10 + -3 + 11 = 18   -3 + 11 = 8
     (10,1) (7,1) 18 - 8 = 10 (a,b,c) = x  (d,e) = 8  x = curSum - 8

     * @param root
     * @param sum
     * @return
     */

    // time : O(n ^ 2) space : O(n)
    //Time: O(n^2) in worst case (no branching); O(nlogn) in best case (balanced tree).
    /*
     * it is starting from every node  i->j 
     *  For each node, three searches are needed, and the time cost for each search 
     *  is the height of the tree, that's O(h). 
     *  worst case: tree is like Linked List, h == n, O(h) = O(n); 
     *  Best case:  Tree is Balanced, which means height == logn, O(h) == O(logn);
    Since the number of nodes is n, for each node search is O(h) =>
    best case: O(nlogn)
    worst case: O(nn)
     */
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        // so we need to start from every node so we will not miss any nodes sum
        return helper(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    
    public int helper(TreeNode node, int target) {
        if (node == null) {
            return 0;
        }
        int res = 0;
        // we already find a path, so we need to +1
        if (node.val == target) {
            res++;
        }
        // we add all others together
        res += helper(node.left, target - node.val) + helper(node.right, target - node.val);
        return res;
    }

    /*
     * interview friendly, O(n)/O(n)
     * 
     * the problem is to say: given one binary tree and one target integer, find the path count
     * which their sum equals to target, the node not have to start with root, but it should down
     */
    public int pathSum2(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        //currentSum - sum --> frequency, already appeared when visiting 
        //these nodes
        Map<Integer, Integer> map = new HashMap<>();
        
        // this means we already have answer to 1 
        map.put(0, 1);
        return helper(root, 0, sum, map);
    }
    // helper will return  how many paths beginning from this node 
    //which satisfy the requirements
    public int helper(TreeNode node, int curSum, int sum, Map<Integer, Integer> map) {
        if (node == null) {
            return 0;
        }
        curSum += node.val;
        int res = map.getOrDefault(curSum - sum, 0);
        map.put(curSum, map.getOrDefault(curSum, 0) + 1);
        // this is using backtracking, so we use map as memory to go 
        //through the rest nodes,
        res += helper(node.left, curSum, sum, map) + helper(node.right, curSum, sum, map);
        map.put(curSum, map.get(curSum) - 1);
        return res;
    }
    
    //below cannot work because it will dup 
    class Solution_Incorrect {
        int res = 0;
        public int pathSum(TreeNode root, int targetSum) {
            helper(root, 0, targetSum);
            
            return res;
        }
        
        private void helper(TreeNode root, int curSum, int targetSum) {
            if (root == null) return;
           
            if (curSum + root.val == targetSum) res++;
            
            helper(root.left, curSum + root.val, targetSum);
            helper(root.right, curSum + root.val, targetSum);
            
            helper(root.left, root.val, targetSum);
            helper(root.right, root.val, targetSum);
        }
    }
}
