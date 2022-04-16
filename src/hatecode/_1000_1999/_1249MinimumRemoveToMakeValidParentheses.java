package hatecode._1000_1999;

import java.util.*;

public class _1249MinimumRemoveToMakeValidParentheses {
    
/*
1249. Minimum Remove to Make Valid Parentheses
Given a string s of '(' , ')' and lowercase English characters.

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
 

Example 1:

Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
*/
    
    //thinking process: O(n)/O(n)
    
    //the problem is to say: given one string s, 
    public String minRemoveToMakeValid(String s) {
        int count = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i <s.length(); i++) {
            char c = s.charAt(i);
           
            if (c == '(') {
                count++;
                stack.push(i);
            } else if (c==')') {
                if (count>0) {
                    stack.pop();
                     count--;
                } else stack.push(i);
            }
            //System.out.println(stack);
        }
        
        
        Set<Integer> set = new HashSet<>(stack);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i <s.length(); i++) {
            if (set.contains(i)) continue;
            sb.append(s.charAt(i));
        }
        
        return sb.toString();
    }
}