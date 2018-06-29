package leetcode;

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
        Stack<Integer> stack = new Stack<>();
        int sign = 1;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                int num = s.charAt(i) - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                res += num * sign;
            } else if (s.charAt(i) == '+') {
                sign = 1;
            } else if (s.charAt(i) == '-') {
                sign = -1;
            } else if (s.charAt(i) == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
            } else if (s.charAt(i) == ')') {
                res = res * stack.pop() + stack.pop();// first pop is sign, second pop is res
            }
        }
        return res;
    }
}
