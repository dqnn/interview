package hatecode._0001_0999;

import java.util.*;
public class _856ScoreOfParentheses {
/*
856. Score of Parentheses
Given a balanced parentheses string s, return the score of the string.

The score of a balanced parentheses string is based on the following rule:

"()" has score 1.
AB has score A + B, where A and B are balanced parentheses strings.
(A) has score 2 * A, where A is a balanced parentheses string.
 

Example 1:

Input: s = "()"
Output: 1
Example 2:

Input: s = "(())"
Output: 2
Example 3:

Input: s = "()()"
Output: 2
*/
    /*
     * thinking process: O(n)/O(n)
     * 
     * the problem is to say: given a balanced string of parethesis, 
     * () --> 1 
     * ()() --> 2
     * ()()() --> 3
     * (()) --> 2* 1= 2
     * ((())) --> 2*2* 1= 4
     * return the score of the string.
     * 
     * it is easy to think about stack, but it is same problem with decode string,
     * calculator, etc
     */
    public int scoreOfParentheses_Stack(String s) {
        Stack<Integer> stack = new Stack<>();
        
        int score = 0;
        for(char c: s.toCharArray()) {
            if (c =='(') {
                stack.push(score);
                score = 0;
            } else {
                /*
                 * we have 2 cases:
                 * 1. ((), previous is 0, then we just get 1
                 * 2. (()(), we need + previous, so use pop, then )) case
                 */
                score = stack.pop() + Math.max(2 * score, 1);
            }
        }
        
        return score;
    }
    
    /*
     * O(n)/O(1)
     * 
     * using an array as stack to store the temrary things
     */
    public int scoreOfParentheses(String S) {
        int res[] = new int[50], i = 0;
        for (char c : S.toCharArray())
            if (c == '(') res[++i] = 0;
            else res[i - 1] += Math.max(res[i--] * 2, 1);
        return res[0];
    }
}