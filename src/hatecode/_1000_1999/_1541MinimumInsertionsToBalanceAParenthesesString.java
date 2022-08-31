package hatecode._1000_1999;

import java.util.*;
public class _1541MinimumInsertionsToBalanceAParenthesesString {
/*
1541. Minimum Insertions to Balance a Parentheses String
Given a parentheses string s containing only the characters '(' and ')'. A parentheses string is balanced if:

Any left parenthesis '(' must have a corresponding two consecutive right parenthesis '))'.
Left parenthesis '(' must go before the corresponding two consecutive right parenthesis '))'.
In other words, we treat '(' as an opening parenthesis and '))' as a closing parenthesis.

For example, "())", "())(())))" and "(())())))" are balanced, ")()", "()))" and "(()))" are not balanced.
You can insert the characters '(' and ')' at any position of the string to balance it if needed.

Return the minimum number of insertions needed to make s balanced.

 

Example 1:

Input: s = "(()))"
Output: 1
Explanation: The second '(' has two matching '))', but the first '(' has only ')' matching. We need to add one more ')' at the end of the string to be "(())))" which is balanced.
*/
    /*
     * interview friendly. O(n)/O(1)
     * 
     * the problem is to say:
     * given one string s, only contains ( ), ( )) is balanced. 
     * 
     * so return minimal characters need to issert to make it balanced
     * 
     * 
     * we use open to stands for count of (, res means the final result.
     * 
     * 
     * 
     */
    public int minInsertions_Best(String s) {
        int res = 0, open = 0;
        int n = s.length();
        for(int i = 0;i<n; i++) {
            char c = s.charAt(i);
            if (c =='(') open++;
            else {
                //if latter is ')', we skip it
               if (i+1<n &&s.charAt(i+1)==')') i++;
               else res++; //next still '(', then current ')', so need to insert ')'
               
            //need to insert '('
               if(open==0) res++; 
               else open--;
            }
        }
        return res + open * 2;
    }
    
    public int minInsertions(String s) {
        if (s == null || s.length() < 1) return 0;
        Stack<Character> stack = new Stack<>();
        int res = 0, n = s.length();
        for(int i = 0; i< n; i++) {
            char c = s.charAt(i);
            if (c == '(') stack.push(c);
            else {
                if(i+1 < n && s.charAt(i+1) == ')') i++;
                else res++;
                
                if (stack.isEmpty()) res++;
                else stack.pop();
            }
        }
        
        while(!stack.isEmpty()) {
            res += 2;
            stack.pop();
        }
        
        return res;
    }
}