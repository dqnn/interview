package hatecode._0001_0999;

import java.util.*;

/**
 * Project Name : Leetcode Package Name : leetcode File Name :
 * DifferentWaystoAddParentheses
 * 
 * Creator : professorX Date : July, 2018 Description
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
    /*
     * thinking process: O(2^)/O(2^n) each position we have 2 options, add ( or not add
     * but it has nothing to do with parenthese
     * 
     * we break the string when we encounter the operator, it is like where we insert closed parenthesis
     * 
     * 
     */

     Map<String, List<Integer>> map = new HashMap<>();
     public List<Integer> diffWaysToCompute(String s) {
         if (map.containsKey(s)) return map.get(s);
         
         List<Integer> res = new ArrayList<>();
         for(int i = 0; i<s.length(); i++) {
             char c = s.charAt(i);
             
             if (c == '+' || c== '-' || c=='*' || c=='/') {
                 List<Integer> a1 = diffWaysToCompute(s.substring(0, i));
                 List<Integer> b1 = diffWaysToCompute(s.substring(i+1));
                 for(int x : a1) {
                     for(int y : b1) {
                         if (c =='+') {
                             res.add(x + y);
                         } else if (c =='-'){
                             res.add(x - y);
                         } else if (c== '*'){
                             res.add(x * y);
                         } else {
                             res.add(x / y);
                         }
                     }
                 }
             }
         }
         
         if (res.size() == 0) return Arrays.asList(Integer.valueOf(s));
         
         map.put(s, res);
         return res;
    
    /*
     * similiar question in Googe interview 一个只有正整数的list， 
     * 其中插入+， * 或者（），求得到式子最大的值。
     * e.g. [1，2，1，2 ]-> (1+2)*(1+2)=9. dp解， follow up， 如果有负数该怎么办， 如果想要拿到最大的式子该怎么办。
     * 思路：类似burst balloon dp[i][j] = max of for (k : i ~ j max(dp[i][k - 1] *
     * dp[k][j], dp[i][k - 1] + dp[k][j]))
     * 
     * follow up: 
     1. 如果有负数该怎么办， 
     2. 如果想要拿到最大的式子该怎么办
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
