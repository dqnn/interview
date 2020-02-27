package hatecode._0001_0999;

import java.util.*;

/**
 * Project Name : Leetcode Package Name : leetcode File Name :
 * DifferentWaystoAddParentheses
 * 
 * Creator : duqiang Date : July, 2018 Description
 * 
 * 
 * : 241. Different Ways to Add Parentheses
 */
public class _241DifferentWaystoAddParentheses {
    /**
     * Given a string of numbers and operators, return all possible results from
     * computing all the different possible ways to group numbers and operators. The
     * valid operators are +, - and *.
     */

/*
     Example 1
     Input: "2-1-1".

     ((2-1)-1) = 0
     (2-(1-1)) = 2
     Output: [0, 2]


     Example 2
     Input: "2*3-4*5"

     (2*(3-(4*5))) = -34
     ((2*3)-(4*5)) = -14
     ((2*(3-4))*5) = -10
     (2*((3-4)*5)) = -10
     (((2*3)-4)*5) = 10
     Output: [-34, -14, -10, -10, 10]

     time : O(n^3) 不确定
     space : O(n)


     * @param input
     * @return
     */
    // recursive is pretty important method to write code,
    // main problem = split into two sub problems and we can use their results to
    // get
    // to the root node, this is top down recursive
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            // if it contains "/" then also should be here, need more investigation?
            // i think here avoid /0 case, so we did not introduce "/" in this problem
            if (c == '-' || c == '+' || c == '*' || c == '/') {
                String a = input.substring(0, i);
                String b = input.substring(i + 1);
                // recursive to get the result of first half
                List<Integer> al = diffWaysToCompute(a);
                List<Integer> bl = diffWaysToCompute(b);
                for (int x : al) {
                    for (int y : bl) {
                        if (c == '-') {
                            res.add(x - y);
                        } else if (c == '+') {
                            res.add(x + y);
                        } else if (c == '*') {
                            res.add(x * y);
                        }
                    }
                }
            }
        }
        // just one integer in expressions, here is exception case
        if (res.size() == 0) {
            res.add(Integer.valueOf(input));
        }
        return res;
    }
    
    /*
     * similiar question in Googe interview 一个只有正整数的list， 其中插入+， * 或者（），求得到式子最大的值。
     * e.g. [1，2，1，2 ]-> (1+2)*(1+2)=9. dp解， follow up， 如果有负数该怎么办， 如果想要拿到最大的式子该怎么办。
     * 思路：类似burst balloon dp[i][j] = max of for (k : i ~ j max(dp[i][k - 1] *
     * dp[k][j], dp[i][k - 1] + dp[k][j]))
     * 
     * follow up: 1. 如果有负数该怎么办， 2. 如果想要拿到最大的式子该怎么办
     */
    //O(n^3)/O(n^2)
    public static int maxExprResult(int[] A) {  
        int n = A.length;  
        int[][] dp = new int[n][n];  
        for(int i=0; i<n; i++) {  
            dp[i][i] = A[i];  
        }  
        //burst ballons also use this
        for(int w=2; w<=n; w++) { // window size of the sliding window  
            for(int i=0, j=w-1; j<n; i++, j++) {  
                for(int k=i; k<j; k++) {  
                    dp[i][j] = Math.max(dp[i][j], Math.max(dp[i][k]+dp[k+1][j], dp[i][k]*dp[k+1][j]));  
                }  
            }  
        }
        return dp[0][n-1];  
    }
    //O(n)/O(n)
    public static int maxExprResult_Better(int[] A) {  
        int n = A.length;  
        int[] dp = new int[n];  
        dp[0] = A[0];  

        for (int i = 1; i < n; i++) {  
            dp[i] = Math.max(dp[i - 1] * A[i], (i >= 2 ? dp[i - 2] : 1) * (A[i - 1] + A[i]));  

            if (i >= 2) {  
                dp[i] = Math.max(dp[i], (i >= 3 ? dp[i - 3] : 1) * (A[i - 2] + A[i - 1] + A[i]));  
            }  
        }  
        return dp[n-1];  
    }  
    public static void main(String[] args) {
        int[] in = {2,2,-2};
        System.out.println(maxExprResult_Better(in));
        System.out.println(maxExprResult(in));
    }
}
