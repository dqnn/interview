package hatecode._0001_0999;
import java.util.*;
public class _439TernaryExpressionParser {
/*
439. Ternary Expression Parser
Example 1:
Input: "T?2:3"
Output: "2"

Explanation: If true, then result is 2; otherwise result is 3.
Example 2:

Input: "F?1:T?4:5"
Output: "4"
Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:

             "(F ? 1 : (T ? 4 : 5))"                   "(F ? 1 : (T ? 4 : 5))"
          -> "(F ? 1 : 4)"                 or       -> "(T ? 4 : 5)"
          -> "4"                                    -> "4"
Example 3:
Input: "T?T?F:5:3"
Output: "F"

*/
    public String parseTernary(String s) {
        if (s == null || s.length() < 1) return s;
        
        int len = s.length();
        Stack<Character> stack = new Stack<>();
        for(int i = len-1; i>=0;i--) {
            char ch = s.charAt(i);
            if (!stack.isEmpty() && stack.peek() == '?') {
                stack.pop();//pop ?
                char first  = stack.pop();
                stack.pop();//pop :
                char sec = stack.pop();
                if (ch == 'T') stack.push(first);
                else stack.push(sec);
            } else {
                stack.push(ch);
            }
        }
        
        return String.valueOf(stack.peek());
    }
}