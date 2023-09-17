package hatecode._1000_1999;

import java.util.*;
public class _1047RemoveAllAdjacentDuplicatesInString {
/*
1047. Remove All Adjacent Duplicates In String
Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters, and removing them.

We repeatedly make duplicate removals on S until we no longer can.

Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.

 

Example 1:

Input: "abbaca"
Output: "ca"
*/
    //thinking process:
    //given a string, remove duplicate character and then formed a new string, continue this 
    //and return last string
    
    //from the description that recursive should help to solve the problem, but recusive, each time the 
    //string changes, the pos number will different, like aaac, pos will have no meaningless. 
    
    //so we use stack to store all visited chars
    //two pointers
    public String removeDuplicates(String s) {
        if (s == null || s.length() < 1) return "";
        char[] chs = s.toCharArray();
        int i =0; 
        //i will 
        for(int j= 0; j< s.length(); j++) {
            if (i > 0 && chs[i-1] == s.charAt(j)) i--;
            else chs[i++] = s.charAt(j);
        }
        return new String(chs, 0, i);
    }
    //interview, goole interview question: but just need to check upperCase or lowerCase
    public String removeDuplicates_Stack(String s) {
        if (s == null || s.length() < 1) return "";
        
        Stack<Character> st = new Stack<>();
        for(char c : s.toCharArray()) {
            if(!st.isEmpty() && c == st.peek()) {
                st.pop();
            // we will ignore the current c if they are the same, if not, push to stack
            } else st.push(c);
        }
        /* this also works
         for(char ch : s.toCharArray()) {
            if(!stack.isEmpty() && ch == stack.peek()) {
                stack.pop();
            } else stack.push(ch);
        }
         */
        StringBuilder sb = new StringBuilder();
        while(!st.isEmpty()) sb.insert(0, st.pop());
        return sb.toString();
        
    }
}