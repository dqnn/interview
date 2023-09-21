package hatecode._1000_1999;

import java.util.*;
import java.util.stream.*;

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
    // ( ), if we want valid parentheses with min removal of the parentheses, 
    //return the string.
    
    //if we want to remove invalid parentheses, we can only remove ( or ), 
    //the key is how to find the incorrect ones, we can start from examples, since the 
    //letters does not impact the results, we can start from simple example, 
    // ((), suppose we can close the smallest unit, then we can simplify the problem,
    // it will be ( left, this reminds us the data strcutre of Stack. 
    
    //if we have another case, like )(), we cannot close the first one, then we need a counter 
    //to record the  how many left or right parentheses we have. 
    
    //similar question: 921,
    //one follow up: return all possible valid string
    
    //O(n)/O(1)
    
    //the idea is to say: we count l ('(') and r (')'), 
    //first we know how many ) in string s, then in 2nd loop 
    //we want to make sure whether each ( ) are matched, if they
    //are matched then we need it, if not, we just remove them.

    /* l: at position i, count of unmatched '(' from 0 ->i.
       r: at position i, how many ) are unmatched from i + 1 --> A.length - 1, waited to be matched
     * 
     * for '(' it is easy because we know how many ) totally, we just make sure r > 0 then we 
     * can append to stringBuilder
     * 
     * for ')' it would take more steps:
     * 1. if l > 0, then it is matched, we decrement l and append to string builder
     * 2. if l <= 0, it is not matched, so we need to remove it while decrease r; 
     * 
     * see ))((,
     * 
     *  i  l   r   sb
     *  0  0   1   []
     *  1  0   0   []
     *  2  1   0   []
     *  3  2   0   []
     */
    public String minRemoveToMakeValid(String s) {
        if (s == null || s.length() < 1) return s;
        
        int l = 0;
        long r = IntStream.range(0, s.length()).filter(i->s.charAt(i)==')').count();
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (c =='(') {
                l++;
                if (r > 0) {
                    r --;
                    sb.append(c);
                } 
            } else if (c ==')') {
                //because previous already matched, so we don;t need r--;
                if (l > 0) {
                    l--;
                    sb.append(c);
                // here is the key, we have to skip this ) becz this is unmatched, "))(("
                } else r--;
            } else sb.append(c);
        }
        
        return sb.toString();
    }
    
    //O(n)/O(n)
    public String minRemoveToMakeValid_BF(String s) {
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