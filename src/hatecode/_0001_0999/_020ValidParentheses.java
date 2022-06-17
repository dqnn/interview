package hatecode._0001_0999;

import java.util.Stack;

/**
 * Created by professorX on 27/07/2017.
 */
public class _020ValidParentheses {
    /**
     * 20. Valid Parentheses
     * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

     The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

     case1 : ()[]{}
     stack :

     case2 : ([)]
     stack :

     case3 : }
     stack :

     time : O(n);
     space : O(n);
     * @param s
     * @return
     */
    //thinking process: 
    
    //the problem is to validate the parentthese are valid, so we use a stack to store the 
    //correspondence parenthese in the stack and compare each with input
    public static boolean isValid(String s) {

        if (s == null || s.length() == 0) return true;
        Stack<Character> stack = new Stack<>();
        for (Character ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(')');
            } else if (ch == '[') {
                stack.push(']');
            } else if (ch == '{') {
                stack.push('}');
            } else {
                if (stack.isEmpty() || stack.pop() != ch) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
    
    public boolean isValid2(String s) {
        char[] stack = new char[s.length()];
        int head = 0;
        for(char c : s.toCharArray()) {
            switch(c) {
                case '{':
                case '[':
                case '(':
                    stack[head++] = c;
                    break;
                case '}':
                    if(head == 0 || stack[--head] != '{') return false;
                    break;
                case ')':
                    if(head == 0 || stack[--head] != '(') return false;
                    break;
                case ']':
                    if(head == 0 || stack[--head] != '[') return false;
                    break;
            }
        }
        return head == 0;

    }
}
