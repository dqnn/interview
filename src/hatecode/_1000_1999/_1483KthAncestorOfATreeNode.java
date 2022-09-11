package hatecode._1000_1999;

import java.util.*;

public class _1483KthAncestorOfATreeNode {
/*
1483. Kth Ancestor of a Tree Node
You are given a tree with n nodes numbered from 0 to n - 1 in the form 
of a parent array parent where parent[i] is the parent of ith node. 
The root of the tree is node 0. Find the kth ancestor of a given node.

The kth ancestor of a tree node is the kth node in the path from that node 
to the root node.

Implement the TreeAncestor class:

TreeAncestor(int n, int[] parent) Initializes the object with the number 
of nodes in the tree and the parent array.
int getKthAncestor(int node, int k) return the kth ancestor of the given 
node node. If there is no such ancestor, return -1.
 

Example 1:


Input
["TreeAncestor", "getKthAncestor", "getKthAncestor", "getKthAncestor"]
[[7, [-1, 0, 0, 1, 1, 2, 2]], [3, 1], [5, 2], [6, 3]]
Output
[null, 1, 0, -1]

Explanation
TreeAncestor treeAncestor = new TreeAncestor(7, [-1, 0, 0, 1, 1, 2, 2]);
treeAncestor.getKthAncestor(3, 1); // returns 1 which is the parent of 3
treeAncestor.getKthAncestor(5, 2); // returns 0 which is the grandparent of 5
treeAncestor.getKthAncestor(6, 3); // returns -1 because there is no such ancestor
*/
    
    //method 1: dp + binary lifting(subsample in 2_ith instead of store all kk ancestors) (dis+huahua) (60 ms, faster than 95.32%)
/*
dp state: 
    dp[i][j] = i-th node's 2^(j)th ancestor in the path 
dp init: 
    dp[i][j] = dp[i][0] (first parent (2^0) of each node is given)
dp trans:
    dp[i][j] = dp[dp[i][j-1]][j-1] 
        meaning: A(j, 2^i) = A( A(j, 2^i-1), 2^i-1)
            To find the (2^i)-th ancestor of j, 
            recursively find j-th node's 2^(i-1)th ancestor's 2^(i-1)th ancestor. (2^(i) = 2^(i-1) + 2^(i-1))
["TreeAncestor","getKthAncestor","getKthAncestor","getKthAncestor"]
[[15,[-1,0,0,1,1,2,2,3,3,4,4,5,5,6,6]],[3,1],[5,2],[6,3]]

[
 [-1,-1, -1, -1], 
 [0, -1, -1, -1], 
 [0, -1, -1, -1], 
 [1, 0, -1, -1], 
 [1, 0, -1, -1], 
 [2, 0, -1, -1], 
 [2, 0, -1, -1], 
 [3, 1, -1, -1], 
 [3, 1, -1, -1], 
 [4, 1, -1, -1], 
 [4, 1, -1, -1], 
 [5, 2, -1, -1], 
 [5, 2, -1, -1], 
 [6, 2, -1, -1], 
 [6, 2, -1, -1]
 
 ]

*
*
*/
    
    int[][] dp;
    int maxPower;
    //build dp: O(nlogn)
    public _1483KthAncestorOfATreeNode(int n, int[] parent) {
        //log_e change to base 2
        this.maxPower = (int) (Math.log(n) / Math.log(2)) + 1;
        //init dp
        this.dp = new int[n][maxPower];
        for(int i = 0; i< n; i++) {
            dp[i][0] = parent[i];
        }
        //build dp by transition
        for(int i=0; i<n; i++){
            for(int j=1; j<maxPower; j++){
                
                int prev = dp[i][j-1]; 
                //no more ancestors in 1/2 step
                if(prev == -1) dp[i][j] = -1;
                /*
                 * when j = 1, we just climb 1(2^1 - 1) more step,
                 * if j = 5, then it will be dp[i][4], so it will climb 4 steps 
                 */
                else{
                    dp[i][j] = dp[prev][j-1];
                }
            }
        }
        
        System.out.println(Arrays.deepToString(dp));
    }
    
    //Binary search: O(logn)
    public int getKthAncestor(int node, int k) {
        int curPower = this.maxPower; //base 2
        while(k>0 && node>=0){
            //try step from 2^(maxPower), 2^(maxPower-1) ...
            if(k >= (1<<curPower)){
                node = dp[node][curPower];
                k -= (1<<curPower);
            }else{
                //take smaller step
                curPower-=1;
            }
        }
        return node;
    }
}