package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : BasicCalculatorII
 * Creator : duqiang
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
public class BasicCalculatorIII {

    // recursive, A + B, we recursive to get A and B, understand its templates
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

    /*
     * 你需要设定一个栈SOP,和一个线性表 L 。SOP用于临时存储运算符和分界符( ，L用于存储后缀表达式。 遍历原始表达式中的每一个表达式元素
     * （1）如果是操作数，则直接追加到 L中。只有 运算符 或者 分界符（ 才可以存放到 栈SOP中 （2）如果是分界符 Ⅰ 如果是左括号 ( ，
     * 则直接压入SOP，等待下一个最近的 右括号 与之配对。 Ⅱ 如果是右括号），则说明有一对括号已经配对(在表达式输入无误的情况下)。不将它压栈，丢弃它，
     * 然后从SOP中出栈，得到元素e，将e依次追加到L里。一直循环，直到出栈元素e 是 左括号 ( ，同样丢弃他。 （3）如果是运算符（用op1表示）
     * Ⅰ如果SOP栈顶元素（用op2表示） 不是运算符，则二者没有可比性，则直接将此运算符op1压栈。 例如栈顶是左括号 ( ，或者栈为空。
     * Ⅱ如果SOP栈顶元素（用op2表示） 是运算符 ，则比较op1和 op2的优先级。如果op1 > op2 ， 则直接将此运算符op1压栈。 如果op1
     * <= op2，则将op2出栈，并追加到L，重复步骤3。 也就是说，如果在SOP栈中，有2个相邻的元素都是运算符，则他们必须满足：
     * 下层运算符的优先级一定小于上层元素的优先级，才能相邻。
     * 
     * 最后，如果SOP中还有元素，则依次弹出追加到L后，就得到了后缀表达式。
     */
     // re-polish, AST, abstract syntax tree way, this is for formal way
    // Agorithms, data all go to output, while ops and () go to ops or pop, high
    // priviledge will be
    // compute first, op1 as new operator we want to add in, op2 is top op in op
    // stack
    // 1. if digital, go to outout, stack or list
    // 2. if not digital, if op1 == ')', return all things from op stack to output
    // until
    // meet "(",
    // 2.1 if not ")", if op1 <= op2, then pop op2 to output and go to 2. else go to
    // op stack
    //
    // 2.1.2 if bigger  than head of stack, go to op stack,
    // a * (b- c * d) + e ---> abcd*-*e+
    public static int calculate3(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        Map<Character, Integer> opPMap = new HashMap<Character, Integer>() {
            {
            put('+', 1);
            put('-', 1);
            put('*', 2);
            put('/', 2);
            put('(', Integer.MAX_VALUE);
        }};
        
        int res = 0;
        // set up op stack and out put string.
        Stack<Character> opStack = new Stack<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < s.length();) {
            Character ch = s.charAt(i);
            if (Character.isDigit(ch)) { //handle digit
                int j = i + 1;
                while (j < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    j ++;
                }
                list.add(s.substring(i, j));
                i++;
            } else {// handle operator
                if (opStack.isEmpty() || opStack.peek() == '('
                        || opPMap.get(ch) != null && opPMap.get(ch) > opPMap.get(opStack.peek())) {
                    opStack.push(ch);
                    i++;
                } else if (opPMap.get(ch) != null && opPMap.get(ch) <= opPMap.get(opStack.peek())) {
                    while (!opStack.isEmpty() && opPMap.get(ch) <= opPMap.get(opStack.peek())) {
                        list.add(String.valueOf(opStack.pop()));
                    }
                } else if (ch == ')') {
                    while (opStack.peek() != '(') {
                        list.add(String.valueOf(opStack.pop()));
                    }
                    opStack.pop(); // remove the "("
                    i++;
                } else {
                    // should be not be here
                }
            }
        }
        // add rest of the Stack into output list
        while (!opStack.isEmpty()) {
            list.add(String.valueOf(opStack.pop()));
        }

        // parse the output into another stack and do the computation

        Stack<String> computeStack = new Stack<>();
        for (String str : list) { // dump list into this stack and do the computations
            if (!opPMap.containsKey(str.charAt(0))) {
                computeStack.push(str);
            } else {
                int num1 = Integer.valueOf(computeStack.pop());
                int num2 = Integer.valueOf(computeStack.pop());
                if (str.equals("+")) {
                    res += num1 + num2;
                } else if (str.equals("-")) {
                    res += num2 - num1;
                } else if (str.equals("*")) {
                    res += num2 * num1;
                } else if (str.equals("/")) {
                    res += num2 / num1;
                } else {
                    // nothing
                }
                computeStack.push(String.valueOf(res));
                res = 0;
            }
        }
        return Integer.parseInt(computeStack.pop());
     }

     // time : O(n) space : O(1)
    public int calculate2(String s) {
        if (s == null || s.length() == 0) return 0;
        s = s.trim().replaceAll(" +", ""); // remove all spaces
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
        String temp = "3*2+(1+2)";
        System.out.println(BasicCalculatorIII.calculate3(temp));
    }

}
