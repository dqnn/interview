package hatecode;

import java.util.Stack;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : BasicCalculator
 * Creator : duqiang
 * Date : Sep, 2017
 * Description : 224. Basic Calculator

Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, 
non-negative integers and empty spaces .

Example 1:

Input: "1 + 1"
Output: 2
Example 2:

Input: " 2-1 + 2 "
Output: 3
Example 3:

Input: "(1+(4+5+2)-3)+(6+8)"
Output: 23
Note:
You may assume that the given expression is always valid.
Do not use the eval built-in library function.
 */
public class BasicCalculator {
    /**

     "1 + 1" = 2
     " 2-1 + 2 " = 3
     "(1+(4+5+2)-3)+(6+8)" = 23

     time : O(n)
     space : O(n)

     * @param s
     * @return
     */
    public int calculate(String s) {
        if (s == null) return 0;
        
        Stack<Integer> stack = new Stack<>();
        int sign = 1, res = 0;
        for(int i = 0; i< s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                int start = i;
                while(i+1< s.length() && Character.isDigit(s.charAt(i+1))) i++;
                int num = Integer.valueOf(s.substring(start, i +1));
                res += sign * num;
            } else if(c == '+') {
                sign = 1;
            } else if (c =='-') {
                sign = -1;
            } else if(c == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
            //here is the key, when we 1-(2-3), here means res = 0, sign = 1, 
                //stack will 1 and -1,  and we process res = -1, so here it will be 
                // -1 * -1 + 1 = 2
            } else if(c == ')') {
                res = res * stack.pop() + stack.pop();
            }
        }
        return res;
        
    }
}
