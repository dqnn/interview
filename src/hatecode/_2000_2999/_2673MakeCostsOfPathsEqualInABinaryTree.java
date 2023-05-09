public class _2673MakeCostsOfPathsEqualInABinaryTree {
    /*
    2673. Make Costs of Paths Equal in a Binary Tree
    You are given an integer n representing the number of nodes in a perfect binary tree consisting of nodes numbered from 1 to n. The root of the tree is node 1 and each node i in the tree has two children where the left child is the node 2 * i and the right child is 2 * i + 1.
    
    Each node in the tree also has a cost represented by a given 0-indexed integer array cost of size n where cost[i] is the cost of node i + 1. You are allowed to increment the cost of any node by 1 any number of times.
    
    Return the minimum number of increments you need to make the cost of paths from the root to each leaf node equal.
    
    Note:
    
    A perfect binary tree is a tree where each node, except the leaf nodes, has exactly 2 children.
    The cost of a path is the sum of costs of nodes in the path.
     
    
    Example 1:
    
    
    Input: n = 7, cost = [1,5,2,2,3,3,1]
    Output: 6
    */
    /*
     * thinking process: O(n)/O(lgN)
     * 
     * the problem is to say: given one perfect bianry tree, and one integer array, 
     * integer array is each node cost starting from 1st index. 
     * 
     * to make sum from root to leaf node, make sure every path have same sum, you can add any integer to the node, 
     * 
     * return the min number of increment you have to make to make the sum of all path are the same
     * 
     * all tree can simplified to one tree as below:
     * 
     *          3 
     *        /
     *       1
     *     /   \
     *    5     4
     * 
     * since they have same parent, so the sum to 1 will be the same then difference will come from its child sum,
     * here wile be |sum(left) - sum(right)|, so you will have to increment 4 here to have min increment, 
     * 
     * because on common path we will have min increment, 
     *  
     * when we back to node 3, we need max from "1" tree, so it will be max(left, right)
     */
        
        int res = 0;
        public int minIncrements(int n, int[] A) {
            helper(0, A);
            return res;
        }
        
        private int helper(int i, int[] A) {
            if (i >= A.length) return 0;
            
            int l = helper(2* i+1, A), r = helper(2*i + 2, A);
            
            res += Math.abs(l -r);
            
            return A[i] + Math.max(l, r);
        } 
    }