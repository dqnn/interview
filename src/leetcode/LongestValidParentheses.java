package leetcode;

import java.util.Stack;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LongestValidParentheses
 * Creator : duqiang
 * Date : Dec, 2017
 * Description : 32. Longest Valid Parentheses
 */
public class LongestValidParentheses {
    /**
     Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

Example 1:

Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"
Example 2:

Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"


     0 1 2 3 4 5
     ( ( ) )

     time : O(n)
     space : O(n)


     * @param s
     * @return
     */

    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        
        Stack<Integer> stack = new Stack<>();
        int start = -1, res = 0;
        for(int i = 0; i< s.length(); i++) {
            //System.out.println(stack);
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                // so if stack is empty, then we know we already matched all "C", so current i as idx 
                // the position in S is end pointer, 
                //means we need to start new round of matching or just end
                if (stack.isEmpty()) {
                    start = i;
                } else {
                    //if stack is not empty, means we still have "C" not matched, so we continue to move and  
                    // use top in stack as start pointer to calc the length of the qualified string
                    stack.pop();
                    if (stack.isEmpty()) {
                        res = Math.max(res, i - start);
                    // this is if stack is not empty, we calc from the current top ( idx
                    } else {
                        res = Math.max(res, i - stack.peek());
                    }
                }
            }
        }
        
        return res;
    }
    
    
    /*
     * My solution uses DP. The main idea is as follows: I construct a array longest[], for any longest[i], it stores the longest length of valid parentheses which is end at i.

And the DP idea is :

If s[i] is '(', set longest[i] to 0,because any string end with '(' cannot be a valid one.

Else if s[i] is ')'

     If s[i-1] is '(', longest[i] = longest[i-2] + 2

     Else if s[i-1] is ')' and s[i-longest[i-1]-1] == '(', longest[i] = longest[i-1] + 2 + longest[i-longest[i-1]-2]

For example, input "()(())", at i = 5, longest array is [0,2,0,0,2,0], longest[5] = longest[4] + 2 + longest[1] = 6.
     */
    public int longestValidParentheses2(String s) {
        if (s == null || s.length() == 0) return 0;
        
        // build && init dp[i]: longest valid string that ends at index i
        int n = s.length();
        int[] dp = new int[n];

        // calculate dp && track global max
        int max = 0;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == '(') continue;

            boolean consecutiveEnd = s.charAt(i - 1) == '(';
            int priorIndex = consecutiveEnd ? i - 1 : i - dp[i - 1] - 1;
            if (priorIndex < 0 || s.charAt(priorIndex) != '(') continue;

            dp[i] = 2 + addPriorLength(priorIndex, dp);
            dp[i] += consecutiveEnd ? 0 : dp[i - 1]; //add length from (priorIndex, i - 1]
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    private int addPriorLength(int priorIndex, int[] dp) {
        return (priorIndex - 1 >= 0) ? dp[priorIndex - 1] : 0;
    }
}
