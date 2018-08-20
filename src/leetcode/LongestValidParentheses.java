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
}
