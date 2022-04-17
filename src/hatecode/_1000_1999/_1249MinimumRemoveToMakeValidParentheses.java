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
    
    //the problem is to say: given one string s, which contains letters and 
    // ( ), if we want valid parenthesis with min removal of the parenthesis, 
    //return the string.
    
    //if we want to remove invalid parenthesis, we can only remove ( or ), 
    //the key is how to find the incorrect ones, we can start from examples, since the 
    //letters does not impact the results, we can start from simple example, 
    // ((), suppose we can close the smallest unit, then we can simplify the problem,
    // it will be ( left, this reminds us the data strcutre of Stack. 
    
    //if we have another case, like )(), we cannot close the first one, then we need a counter 
    //to record the  how many left or right parenthesis we have. 
    public String minRemoveToMakeValid(String s) {
        int count = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i <s.length(); i++) {
            char c = s.charAt(i);
           
            if (c == '(') {
                count++;
                stack.push(i);
            } else if (c==')') {
                //here is )() case, we only pop when count > 0, here
                //count = 0 but we do have one ( in stack
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