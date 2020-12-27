package hatecode._1000_1999;

import java.util.*;
public class _1190ReverseSubstringsBetweenEachPairOfParentheses {
/*
1190. Reverse Substrings Between Each Pair of Parentheses
You are given a string s that consists of lower case English letters and brackets. 

Reverse the strings in each pair of matching parentheses, starting from the innermost one.

Your result should not contain any brackets.

 

Example 1:

Input: s = "(abcd)"
Output: "dcba"
*/
    //thinking process: O(n)/O(n), interview friendly for best solution
    
    
    
    //the problem is to say: given one string s, it contains character and ( )s, 
    //when we enter (), we need to reverse the string, return the final string.
    
    // ->(<--    ->)-->
    //take a look at above digram, so we want to note down  parenthese the index,
    //the mapping, eg: (abcd)
    //pair = [5,0,0,0,0,0]
    //then when we 2nd pass visit s, then when we encounter '(' or ')' then we want to 
    //look up in pair array, we will jump to its mapping parenthese index, also we change
    //our direction, visit back, then go on.
    public String reverseParentheses(String s) {
        int n = s.length();
        Stack<Integer> opened = new Stack<>();
        int[] pair = new int[n];
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '(')
                opened.push(i);
            if (s.charAt(i) == ')') {
                int j = opened.pop();
                pair[i] = j;
                pair[j] = i;
            }
        }
        
        //d means direction
        StringBuilder sb = new StringBuilder();
        for (int i = 0, d = 1; i < n; i += d) {
            if (s.charAt(i) == '(' || s.charAt(i) == ')') {
                i = pair[i];
                d = -d;
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
    
    //this is the brute forth solution,O(n^2)/O(n)
    
    public String reverseParentheses_BruteForth(String s) {
        Stack<String> st = new Stack<>();
        String str = "";
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z'){
                str += s.charAt(i);
            }else if(s.charAt(i) == '('){
                st.push(str);
                str = "";
            }else{
                String p = st.pop();
                String r = new StringBuilder(str).reverse().toString();
                str = p + r;               
            }                
        }
        return str;
    }
}