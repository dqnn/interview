package hatecode._0001_0999;

import java.util.Stack;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : BasicCalculatorII
 * Date : Sep, 2017
 * Description : 227. Basic Calculator II
 * Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . 
The integer division should truncate toward zero.


 follow up: how could we extend to + - * / and () and empty spaces: 
 */
public class _227BasicCalculatorII {
    /**
     *
     "3+2*2" = 7
     " 3/2 " = 1
     " 3+5 / 2 " = 5

     3*2+4-5/2

     * @param s
     * @return
     */
    //time : O(n)/O(n)
	public int calculate(String s) {
        if (s == null || s.length() < 1) return 0;

        int res = 0, num = 0;
        Character sign = '+';
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i ++) {
            Character ch = s.charAt(i);
            if (Character.isDigit(ch)) { // convert the number iπnto integer
                num = ch - '0';
                while ((i + 1 < s.length()) && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
            } 
            // skip empty space
            if (!Character.isDigit(ch) && ch != ' ' || i == s.length() - 1) {
                if (sign == '+')  {
                    stack.push(num);
                } 
                if (sign == '-')  {
                    stack.push(0 - num);
                }
                if (sign == '*') {
                    stack.push(stack.pop() * num);
                }    
                if (sign == '/') {
                    stack.push(stack.pop() / num);
                } 
                sign = s.charAt(i);
                num = 0;
            
            }
        }
        for(Integer t : stack) {
            res += t;
        }
        return res;
    }

    // time : O(n)  space : O(1)
    public static int calculate2(String s) {
        if (s == null || s.length() == 0) return 0;
        s = s.trim().replaceAll(" +", "");
        int res = 0;
        int preVal = 0;
        int i = 0;
        char sign = '+';
        while (i < s.length()) {
            int curVal = 0;
            while (i < s.length() && Character.isDigit(s.charAt(i))) {
                curVal = curVal * 10 + s.charAt(i) - '0';
                i++;
            }
            if (sign == '+') {
                res += preVal;
                preVal = curVal;
            } else if (sign == '-') {
                res += preVal;
                preVal = -curVal;
            } else if (sign == '*') {
                preVal = preVal * curVal;
            } else if (sign == '/') {
                preVal = preVal / curVal;
            }
            if (i < s.length()) {
                sign = s.charAt(i);
                i++;
            }
        }
        res += preVal;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(calculate2("3/2 + 1"));
    }
}
