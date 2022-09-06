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
    public int scoreOfParentheses_Stack(String s) {
        Stack<Integer> stack = new Stack<>();
        
        int score = 0;
        for(char c: s.toCharArray()) {
            if (c =='(') {
                stack.push(score);
                score = 0;
            } else {
                score = stack.pop() + Math.max(2 * score, 1);
            }
        }
        
        return score;
    }
    
    public int scoreOfParentheses(String S) {
        int res[] = new int[50], i = 0;
        for (char c : S.toCharArray())
            if (c == '(') res[++i] = 0;
            else res[i - 1] += Math.max(res[i--] * 2, 1);
        return res[0];
    }
}