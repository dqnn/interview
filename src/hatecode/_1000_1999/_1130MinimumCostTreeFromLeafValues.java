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
    public int mctFromLeafValues(int[] A) {
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