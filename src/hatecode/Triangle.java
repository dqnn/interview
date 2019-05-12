package hatecode;

import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : Triangle
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 120. Triangle
 */
public class Triangle {
    /**
     * Given a triangle, find the minimum path sum from top to bottom. 
     * Each step you may move to adjacent numbers
     * on the row below.

     For example, given the following triangle
     [
        [2],
       [3,4],
      [6,5,7],
     [4,1,8,3]
     ]
     The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

     i : j
     i + 1 : j, j + 1

     res = [4, 1, 8, 3, 0]
     res = [7, 6, 10]
     res = [9, 10]
     res = [2]

     time : O(n^2)
     space : O(n)


     * @param triangle
     * @return
     */
    /*
    This problem is quite well-formed in my opinion. The triangle has a tree-like structure, 
    which would lead people to think about traversal algorithms such as DFS. However, 
    if you look closely, you would notice that the adjacent nodes always share a 'branch'. 
    In other word, there are overlapping subproblems. Also, suppose x and y are 'children' of k. 
    Once minimum paths from x and y to the bottom are known, the minimum path starting from k can be 
    decided in O(1), that is optimal substructure. Therefore, dynamic programming would be the best 
    solution to this problem in terms of time complexity.

    What I like about this problem even more is that the difference between 'top-down' and 'bottom-up' 
    DP can be 'literally' pictured in the input triangle. For 'top-down' DP, starting from the node on 
    the very top, we recursively find the minimum path sum of each node. When a path sum is calculated, 
    we store it in an array (memoization); the next time we need to calculate the path sum of the same node, 
    just retrieve it from the array. However, you will need a cache that is at least the same size as the 
    input triangle itself to store the pathsum, which takes O(N^2) space. With some clever thinking, 
    it might be possible to release some of the memory that will never be used after a particular point,
     but the order of the nodes being processed is not straightforwardly seen in a recursive solution, 
     so deciding which part of the cache to discard can be a hard job.

    'Bottom-up' DP, on the other hand, is very straightforward: we start from the nodes on the bottom row; 
    the min pathsums for these nodes are the values of the nodes themselves. From there, the min pathsum 
    at the ith node on the kth row would be the lesser of the pathsums of its two children plus the value 
    of itself, i.e.:

    minpath[k][i] = min( minpath[k+1][i], minpath[k+1][i+1]) + triangle[k][i];
    Or even better, since the row minpath[k+1] would be useless after minpath[k] is computed, 
    we can simply set minpath as a 1D array, and iteratively update itself:

    For the kth level:
    minpath[i] = min( minpath[i], minpath[i+1]) + triangle[k][i]; 
    Thus, we have the following solution
    */
    /*
     * [
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
we start from last row, 
res[j] = Math.min(res[j], res[j+1]) + t.get(i).get(j), 
so in last row, res[j] and res[j+1] will be 0, so after this loop, res= [4, 1, 8, 3, 0]

then we jump one row, then you can see we always made this row to plus smaller than on previous row by above code, and 
we put the result to res[j] because res[j+1] needs to compare with next, res = [7, 6, 10, 3, 0]
then next, we add [3,4], it will be res = [9, 10,10,3,0]
last we add 2, then it will be res=[11,10,10,3,0]
     */
    
        public int minimumTotal(List<List<Integer>> triangle) {
            if (triangle == null || triangle.size() < 1) {
                return 0;
            }
            //dp
            int[] dp = new int[triangle.size() + 1];
            for (int i = triangle.size() - 1; i >= 0; i--) {
                for (int j = 0; j < triangle.get(i).size(); j++) {
                    //so we start from last level, it will be same as last level
                    //when go above, min(res[j], res[j+1])  + above level i, j, 
                    //for example res[1] = min(res[1], res[2]) + above level n-2 and j = 0; 
                    // j starts from 0, so we min(res[0], res[1]) + number[j],
                    //if we are in one level above, then we use lower level number min 
                    //between to plus current ones
                    
                    // j and j+1 are adjacent numbers
                    dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
                }
            }
            return dp[0];
        }
        
        //no extra mem used
        public int minimumTotal2(List<List<Integer>> triangle) {
            int len = triangle.size();
            for(int i=len-2; i>=0; i--){
                for(int j=0; j<=i; j++){
                    triangle.get(i).set(j, triangle.get(i).get(j) + 
                            Math.min(triangle.get(i+1).get(j), triangle.get(i+1).get(j+1)));
                }
            }
            return triangle.get(0).get(0);
        }
        
        //backtracking, TLE version, O(2^n)/O(n)
        //since every number would have two branches
        int min = Integer.MAX_VALUE;
        public int minimumTotal_BackTracking(List<List<Integer>> in) {
            if (in == null || in.size() < 1) return 0;
            
            helper(in, 0, 0, 0);
            return min;
        }
        
        private void helper(List<List<Integer>> in, int i, int j, int cur) {
            if (i == in.size() - 1) {
                min = Math.min(min, cur + in.get(i).get(j));
                return;
            }
            
            helper(in, i+1, j, cur+ in.get(i).get(j));
            helper(in, i+1, j + 1, cur+ in.get(i).get(j));
        }
        
        
        //re-write the array
        public int minimumTotal_RewriteArray(List<List<Integer>> triangle) {
            int[] A = new int[triangle.size() + 1];
            
            for(int i = triangle.size() - 1; i >= 0; i--) {
                for(int j = 0; j < triangle.get(i).size(); j++) {
                    A[j] = Math.min(A[j], A[j+1]) + triangle.get(i).get(j);
                }
            }
            return A[0];
        }

}
