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
        
        int res = 0;
        public int minIncrements(int n, int[] A) {
            helper(0, A);
            return res;
        }
        
        private int helper(int i, int[] A) {
            if (i >= A.length) return 0;
            
            int a = helper(2* i+1, A), b = helper(2*i + 2, A);
            
            res += Math.abs(a -b);
            
            return A[i] + Math.max(a, b);
        } 
    }