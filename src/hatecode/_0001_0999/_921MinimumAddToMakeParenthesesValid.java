package hatecode._0001_0999;
import java.util.*;
public class _921MinimumAddToMakeParenthesesValid {
/*
921. Minimum Add to Make Parentheses Valid
Given a string S of '(' and ')' parentheses, we add 
the minimum number of parentheses ( '(' or ')', and in any positions ) 
so that the resulting parentheses string is valid.

Formally, a parentheses string is valid if and only if:

It is the empty string, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
Given a parentheses string, return the minimum number of parentheses we 
must add to make the resulting string valid.

 

Example 1:

Input: "())"
Output: 1
*/
  //O(n)/O(1)
    //l mean ( we need to add
  //r means current opened, ( not matched
    
    //there was one similar problem in LC which is to say with K add/replace/delete, 
    //it could be valid parentheses, forget the problem number....WTF
    public int minAddToMakeValid(String s) {
        int l = 0, r = 0;
        for (char c: s.toCharArray()) {
            // (())
            if(c =='(') r++;
            //((())
            else if (r > 0) r--;
            //))
            else l ++;
        }

        return l + r;
    }
/*
1. if encounter '(', push '(' into stack;
2. otherwise, ')', check if there is a matching '(' on top of stack; 
if no, increase the counter by 1; if yes, pop it out;
3. after the loop, count in the un-matched characters remaining in the stack.
 */
    public int minAddToMakeValid2(String S) {
        //only store "("
        Stack<Character> stack = new Stack<>();
        //count )
        int count = 0;
        for (char c : S.toCharArray()) {
            if (c == '(') { stack.push(c); }
            else if (stack.isEmpty()) { ++count; }
            //if we find good mapping then pop(), like ()
            else { stack.pop(); }
        }
        return count + stack.size();
    }
}