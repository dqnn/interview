package hatecode;

import java.util.Stack;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : EvaluateReversePolishNotation
 * Creator : duqiang
 * Date : Sep, 2017
 * Description : 150. Evaluate Reverse Polish Notation
 */
public class EvaluateReversePolishNotation {
    /**
     * Valid operators are +, -, *, /. Each operand may be an integer or another expression.

     Some examples:
     ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
     ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6

     time : O(n)
     space : O(n)

     * @param tokens
     * @return
     */
    
    // this is standard Post order visit operation and is the same as calculator
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String s : tokens) {
            if (s.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (s.equals("-")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a - b);
            } else if (s.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (s.equals("/")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a / b);
            } else {
                stack.push(Integer.parseInt(s));
            }
        }
        return stack.pop();
    }
    
    //one question from google interview doc, not from LC,
    //given two noes, return their result is same or not
    /* root A,    root B, same as A
       +
     /    \
    a      -
         /   \
        c     d
     */
    //follow up is *, / with ()
    
    //if it only has + and -,then we just use hashmap to calcuate the frequency
    public boolean evaluateTree(TreeNode A, TreeNode B) {
        return false;
    }
}
