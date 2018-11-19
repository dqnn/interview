package leetcode;

import java.util.Arrays;
import java.util.Stack;

public class FindTheShortestSuperstring {
/*
 * 943. Find the Shortest Superstring
Travelling Salesman Problem

Given an array A of strings, find any smallest string that contains 
each string in A as a substring.

We may assume that no string in A is substring of another string in A.

 
Example 1:

Input: ["alex","loves","leetcode"]
Output: "alexlovesleetcode"
Explanation: All permutations of "alex","loves","leetcode" would also be accepted.
Example 2:

Input: ["catg","ctaagt","gcta","ttca","atgcatc"]
Output: "gctaagttcatgcatc"
 

Note:

1 <= A.length <= 12
1 <= A[i].length <= 20
 */
    /*
distance calc rule:
if A.equals(B) == true, then it is A.length() or B .length()
else we would choose B.length() - A.length() + common part length
         ["catg","ctaagt","gcta","ttca","atgcatc"]
  catg    [[4,       6,      3,     4,      4], 
  ctaagt   [4,       6,      4,     3,      7], 
  gcta     [4,       3,      4,     4,      6], 
  ttca     [2,       6,      4,     4,      6], 
  atgcatc  [3,       5,      4,     4,      7]]
 
for DP matrix, we process only i[1,31] && (1 << j[0,5))) > 0 
which means are i = 1,2,4,8,16
others we skip until i = 31, we use min to track the min cost.
and we update last = j;

Path here means 
 
Time complexity: O(n^2 * 2^n)
     */
/*
graph[i][j] means the length of string to append when A[i] followed by A[j]. 
eg. A[i] = abcd, A[j] = bcde, then graph[i][j] = 1
Then the problem becomes to: find the shortest path in this graph which 
visits every node exactly once. This is a Travelling Salesman Problem.
Apply TSP DP solution. Remember to record the path.
 */
    public static String shortestSuperstring(String[] A) {
        int inStrArrLen = A.length;
        int[][] graph = new int[inStrArrLen][inStrArrLen];
        // build the graph, "catg" 0  -> "ctaagt" 1, common substring's length 
        //g[0][1] = 6,g[0][1] = 4
        for (int i = 0; i < inStrArrLen; i++) {
            for (int j = 0; j < inStrArrLen; j++) {
                graph[i][j] = calc(A[i], A[j]);
                graph[j][i] = calc(A[j], A[i]);
            }
        }
        //1 << n means Math.pow(2, 5) = 32
        int mLen = 1 << inStrArrLen;
        int[][] dp = new int[mLen][inStrArrLen];
        //2D matrix to show from ""
        int[][] path = new int[mLen][inStrArrLen];
        int last = -1, min = Integer.MAX_VALUE;
        
        //why we did not initialize dp and path
        // start TSP DP, from 1 to pow(2,5) - 1, permutations
        for (int i = 1; i < mLen; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            for (int j = 0; j < inStrArrLen; j++) {
                // i means the power of 2,
                int tempJ = 1 << j;
                //only true when 
                if ((i & tempJ) > 0) {
                    System.out.println("i-J-tempJ:" + i + "-" + j + "-" +tempJ);
                    //prev here means the 
                    int prev = i - tempJ;
                    if (prev == 0) {
                        //
                        dp[i][j] = A[j].length();
                    } else {
                        for (int k = 0; k < inStrArrLen; k++) {
                            int newCost = dp[prev][k] + graph[k][j];
                            if (dp[prev][k] < Integer.MAX_VALUE 
                                    && newCost < dp[i][j]) {
                                dp[i][j] = newCost;
                                path[i][j] = k;
                            }
                        }
                    }
                }
                if (i == (1 << inStrArrLen) - 1 && dp[i][j] < min) {
                    min = dp[i][j];
                    last = j;
                }
            }
        }
		
        // build the path
        StringBuilder sb = new StringBuilder();
        int cur = mLen - 1;
        Stack<Integer> stack = new Stack<>();
        while (cur > 0) {
            stack.push(last);
            int temp = cur;
            cur -= (1 << last);
            last = path[temp][last];
        }

        // build the result
        int i = stack.pop();
        sb.append(A[i]);
        while (!stack.isEmpty()) {
            int j = stack.pop();
            sb.append(A[j].substring(A[j].length() - graph[i][j]));
            i = j;
        }
        return sb.toString();
    }
    static int calc(String a, String b) {
        for (int i = 1; i < a.length(); i++) {
            if (b.startsWith(a.substring(i))) {
                return b.length() - a.length() + i;
            }
        }
        return b.length();
    }
    
    public static void main(String[] args) {
        String[] input = new String[5];
        input[0] = "catg";
        input[1] = "ctaagt";
        input[2] = "gcta";
        input[3] = "ttca";
        input[4] = "atgcatc";
        
        System.out.print(shortestSuperstring(input));
    }
}