package hatecode._1000_1999;

import java.util.*;
public class _1130MinimumCostTreeFromLeafValues {
/*
1130. Minimum Cost Tree From Leaf Values
Given an array arr of positive integers, consider all binary trees such that:

Each node has either 0 or 2 children;
The values of arr correspond to the values of each leaf in an in-order traversal of the tree.  (Recall that a node is a leaf if and only if it has 0 children.)
The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.
Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.  It is guaranteed this sum fits into a 32-bit integer.

 

Example 1:

Input: arr = [6,2,4]
Output: 32
*/
    //thinking process: O(n)/O(n)
    
    //the problem is to say: given an integer array A, it represents in-order visit
    //leaf sequence of a tree, so non-leaf value is product of its largest left subtree leaf
    //and largest right subtree leaf
    //      36          32
    /*      24          24
     *     /  \       /    \
     *    12  4       6    8
     *   /  \            /   \
     *   6  2            2   4        
     */
    //so from above two trees, we can see we want to move big number to upper level as up as 
    //possible, so for the array, A[i], we would like to for every 3 integers(why 3? because the array is in-order visit,
    //there are only two displays, like above, which means the middle one must be in lowest level, 
    //), so the question is to detect A[i-1] or A[i+1] is bigger
    //previous in array, then 
    public int mctFromLeafValues(int[] A) {
        if(A == null || A.length < 1) return 0;
        
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.MAX_VALUE);
        for (int a : A) {
            while (stack.peek() <= a) {
                int mid = stack.pop();
                res += mid * Math.min(stack.peek(), a);
            }
            stack.push(a);
        }
        while (stack.size() > 2) {
            res += stack.pop() * stack.peek();
        }
        return res;
    }
    
    //O(n^3)/O(n^2)
    public int mctFromLeafValues_DP(int[] arr) {
        int n = arr.length;

        int[][] max = new int[n][n];
        for(int l = 1; l <= n; l++) {
            for(int r = 0; r < n - l + 1; r++) {
                int c = r + l - 1;
                if(l == 1) max[r][c] = arr[r];
                else max[r][c] = Math.max(max[r][c - 1], max[r + 1][c]);
            }
        }

        return helper(0, n - 1, arr, max, new int[n][n]);
    }

    private int helper(int s, int e, int[] ar, int[][] max, int[][] memo) {
        if(s >= e) return 0;
        if(memo[s][e] > 0) return memo[s][e];

        int res = Integer.MAX_VALUE;
        for(int k = s; k < e; k++) {
            int left = helper(s, k, ar, max, memo);
            int right = helper(k + 1, e, ar, max, memo);

            res = Math.min(res, left + right + (max[s][k] * max[k + 1][e]));
        }

        memo[s][e] = res;
        return res;
    }
}