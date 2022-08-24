package hatecode._0001_0999;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : BasicCalculatorII
 * Date : Sep, 2017
 * Description : 772. Basic Calculator III
 * 
 * Implement a basic calculator to evaluate a simple expression string. 
The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, 
non-negative integers and empty spaces . 
The expression string contains only non-negative integers, +, -, *, / operators , 
open ( and closing parentheses ) and empty spaces . The integer division should truncate 
toward zero. You may assume that the given expression is always valid. All intermediate results 
will be in the range of [-2147483648, 2147483647]

Do not use the eval built-in library function.
样例

"1 + 1" = 2
" 6-4 / 2 " = 4
"2*(5+5*2)/3+(6/2+8)" = 21
"(2+6* 3+5- (3*14/7+2)*5)+3"=-12


 follow up: how could we extend to + - * / and () and empty spaces: 
 */
public class _772BasicCalculatorIII {
/*
 * 224. Basic Calculator
227. Basic Calculator II
772. Basic Calculator III
770. Basic Calculator IV
 * calculate expressions which contains + - * / ( ), like "6-4/2"-->4

In this section, I will specify the general rules for carrying out the actual 
evaluations of the expression.

Separation rule:

We separate the calculations into two different levels corresponding to the two 
precedence levels.

For each level of calculation, we maintain two pieces of information: the partial 
result and the operator in effect.

For level one, the partial result starts from 0 and the initial operator in effect 
is +; 
for level two, the partial result starts from 1 and the initial operator 
in effect is *.

We will use l1 and o1 to denote respectively the partial result and the operator 
in effect for level one; 
l2 and o2 for level two. The operators have the following mapping:
o1 == 1 means +; o1 == -1 means - ;
o2 == 1 means *; o2 == -1 means /.
By default we have l1 = 0, o1 = 1, and l2 = 1, o2 = 1.

Precedence rule:

Each operand in the expression will be associated with a precedence of level two 
by default, meaning they can only take part in calculations of precedence level two, 
not level one.

The operand can be any of the aforementioned types (number, variable or subexpression), 
and will be evaluated together with l2 under the action prescribed by o2.

Demotion rule:

The partial result l2 of precedence level two can be demoted to level one. 
Upon demotion, l2 becomes the operand for precedence level one and will be 
evaluated together with l1 under the action prescribed by o1.

The demotion happens when either a level one operator (i.e., + or -) is hit or 
the end of the expression is reached. After demotion, l2 and o2 will be reset for 
following calculations.
 */
    //iterator version, really good one
    public static int calculate4(String s) {
        int l1 = 0, o1 = 1;
        int l2 = 1, o2 = 1;

        Deque<Integer> stack = new ArrayDeque<>(); // stack to simulate recursion
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                int num = c - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + (s.charAt(++i) - '0');
                }
                l2 = (o2 == 1 ? l2 * num : l2 / num);

            } else if (c == '(') {
                // First preserve current calculation status
                stack.offerFirst(l1); stack.offerFirst(o1);
                stack.offerFirst(l2); stack.offerFirst(o2);
                
                // Then reset it for next calculation
                l1 = 0; o1 = 1;
                l2 = 1; o2 = 1;

            } else if (c == ')') {
                // First preserve the result of current calculation
                int num = l1 + o1 * l2;

                // Then restore previous calculation status
                o2 = stack.poll(); l2 = stack.poll();
                o1 = stack.poll(); l1 = stack.poll();
                
                // Previous calculation status is now in effect
                l2 = (o2 == 1 ? l2 * num : l2 / num);
            } else if (c == '*' || c == '/') {
                o2 = (c == '*' ? 1 : -1);

            } else if (c == '+' || c == '-') {
                l1 = l1 + o1 * l2;
                o1 = (c == '+' ? 1 : -1);

                l2 = 1; o2 = 1;
            }
        }
        return (l1 + o1 * l2);
    }

    // O(n^2)/O(n)recursive, A + B, we recursive to get A and B, understand its templates
    //worst case: (((1+1))), we have to be in else for every time
    public int calculate(String s) {
         int res = 0, curRes = 0, num = 0;
         char op = '+';
         for(int i = 0; i <= s.length() - 1; i++) {
              Character ch = s.charAt(i);
              if (Character.isDigit(ch)) {
                  num = ch - '0';
                  while(i + 1 < s.length() && Character.isDigit(ch)) {
                      num = num * 10 + s.charAt(i + 1) - '0';
                      i++;
                  }
              } else { 
                   if (ch == '(') { // handle "(" and ")"
                       int j = i + 1, cnt = 1; //cnt us is used to indicate ( )
                       for(;j < s.length(); j ++) {
                           if (s.charAt(j) == '(') {
                               cnt ++;
                           } else if (s.charAt(j) == ')') {
                            cnt--;
                           } else {
                              //continue
                           }
                           
                           if (cnt == 0) {
                               break;
                           }
                       }
                       num = calculate(s.substring(i + 1, j));
                   }
              }
            // here is to handle 5 * 3 + 2, so op default is +,
            // then res = 5, and res = res + 5 * 3,
            // ch here just indicate we need to get A op B, to get A's result;
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || i == s.length() - 1) {// handle other operators
                if (op == '+') {
                    curRes += num;
                }
                if (op == '-') {
                    curRes -= num;
                }
                if (op == '*') {
                    curRes *= num;
                }
                if (op == '/') {
                    curRes /= num;
                }
                // so for * and /, we cannot + previous value since it must be delayed.
                if (ch == '+' || ch == '-' || i == s.length() - 1) { // 5 + 3 * 2, when visit 2, op = X, but we have to
                                                                     // add them and no op.
                    res += curRes;
                    curRes = 0;
                }
                op = ch;
                num = 0;
            }

        }
         return res;
     }

    public static void main(String[] args) {
        String temp = "-3+(2+1*2)";
        System.out.println(_772BasicCalculatorIII.calculate_Polish(temp));
    }
    
    //use reverse polish notation to calculate expressions
    /*
"2*(5+5*2)/3+(6/2+8)"
[]-----[]
[2]-----[]
[2]-----[*]
[2]-----[*, (]
[2, 5]-----[*, (]
[2, 5]-----[*, (, +]
[2, 5, 5]-----[*, (, +]
[2, 5, 5]-----[*, (, +, *]
[2, 5, 5, 2]-----[*, (, +, *]
[2, 15]-----[*]
[30]-----[/]
[30, 3]-----[/]
[10]-----[+]
[10]-----[+, (]
[10, 6]-----[+, (]
[10, 6]-----[+, (, /]
[10, 6, 2]-----[+, (, /]
[10, 3]-----[+, (, +]
[10, 3, 8]-----[+, (, +]
     */
    static Map<Character, Integer> map;
    public static int calculate_Polish(String s) {
        
        //format s to reversed polish notion
        s = s.replace(" ", "");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //add 0 before -3+(2+1) or -(1+2)
            if ((c == '+' || c == '-') && (i == 0 || s.charAt(i - 1) == '(')) {
                sb.append(0);
            }
            sb.append(c);
        }
        s = sb.toString();
        System.out.println(s);
        
        //
        Deque<Integer> operands = new ArrayDeque<>();
        Deque<Character> operators = new ArrayDeque<>();
        map = new HashMap<>();
        map.put('(', -1);
        map.put('+', 0);
        map.put('-', 0);
        map.put('*', 1);
        map.put('/', 1);
        
        int n = s.length();
        for (int i = 0; i < n; i++) {
            System.out.println(operands + "-----" + operators);
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                
                //calculate the number before operator
                int val = c - '0';
                while (i + 1 < n && Character.isDigit(s.charAt(i + 1))) {
                    val = val * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                operands.push(val);
            } else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                while (operators.peek() != '(') {
                    int a = operands.pop();
                    int b = operands.pop();
                    operands.push(operate(b, a, operators.pop()));
                }
                operators.pop();
            } else {
                while (!operators.isEmpty() && comparePrecedence(operators.peek(), c) >= 0) {
                    int a = operands.pop();
                    int b = operands.pop();
                    operands.push(operate(b, a, operators.pop()));
                }
                operators.push(c);
            }
        }
        while (!operators.isEmpty()) {
            int a = operands.pop();
            int b = operands.pop();
            operands.push(operate(b, a, operators.pop()));
        }
        return operands.pop();
    }
    
    private static int operate(int a, int b, char c) {
        switch(c) {
            case '+' :
                return a + b;
            case '-' :
                return a - b;
            case '/' :
                return a / b;
            case '*' :
                return a * b;
            default :
                return a + b;
        }
    }
    
    private static int comparePrecedence(char a, char b) {
        return map.get(a) - map.get(b);
    }

}
