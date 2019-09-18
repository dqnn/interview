package hatecode;

import java.util.*;
public class _1003CheckIfWordIsValidAfterSubstitutions {
/*
1003. Check If Word Is Valid After Substitutions
We are given that the string "abc" is valid.

From any valid string V, we may split V into two pieces X and Y such that X + Y (X concatenated with Y) is equal to V.  (X or Y may be empty.)  Then, X + "abc" + Y is also valid.

If for example S = "abc", then examples of valid strings are: "abc", "aabcbc", "abcabc", "abcabcababcc".  Examples of invalid strings are: "abccba", "ab", "cababc", "bac".

Return true if and only if the given string S is valid.

 

Example 1:

Input: "aabcbc"
Output: true
*/
    //brute force, O(n^2)
    public boolean isValid_BF(String s) {
        if (s == null || s.length() < 3) return false;
        while(s.indexOf("abc") > -1) s = s.replaceAll("abc", "");
        return s.equals("") || s.equals("abc"); 
    }
    
    //O(n)/O(n)
    //use a stack to match, when we meet 'c', we pop a and b, 
    //thinking process: 
    //given "abc" = X + Y, so X + abc + Y will be valid, 
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c: s.toCharArray()) {
            if (c == 'c') {
                if (stack.isEmpty() || stack.pop() != 'b') return false;
                if (stack.isEmpty() || stack.pop() != 'a') return false;
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
    
}