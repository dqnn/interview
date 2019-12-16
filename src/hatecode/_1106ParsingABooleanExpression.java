package hatecode;

import java.util.*;
public class _1106ParsingABooleanExpression {
/*
1106. Parsing A Boolean Expression
770. Basic Calculator IV
tag: good
Return the result of evaluating a given boolean expression, represented as a string.

An expression can either be:

"t", evaluating to True;
"f", evaluating to False;
"!(expr)", evaluating to the logical NOT of the inner expression expr;
"&(expr1,expr2,...)", evaluating to the logical AND of 2 or more inner expressions expr1, expr2, ...;
"|(expr1,expr2,...)", evaluating to the logical OR of 2 or more inner expressions expr1, expr2, ...
 

Example 1:

Input: expression = "!(f)"
Output: true
*/
    
    //thinking process: O(n)/O(n)
    
    //given a string, which is contains "(),!&tf", like "|(&(t,f,t),!(t))"
    //we would like to implement a function to evaluate this string, the result is 
    //a boolean, true or false
    
    //
    public boolean parseBoolExpr_Stack(String s) {
        if(s == null || s.length() < 1) return true;

        //Term, Operator
        Deque<Character> stk = new ArrayDeque<>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == ')') {
                Set<Character> seen = new HashSet<>();
                while (stk.peek() != '(') seen.add(stk.pop());
                stk.pop();// pop out '('.
                char operator = stk.pop(); // get operator for current expression.
                if (operator == '&') {
                    stk.push(seen.contains('f') ? 'f' : 't'); // if there is any 'f', & expression results to 'f'
                } else if (operator == '|') {
                    stk.push(seen.contains('t') ? 't' : 'f'); // if there is any 't', | expression results to 't'
                } else { // ! expression.
                    stk.push(seen.contains('t') ? 'f' : 't'); // Logical NOT flips the expression.
                }
            } else if (c != ',') {
                stk.push(c);
            }
        }
        return stk.pop() == 't';
    }
    
    //thinking process: O(2^n)/O(n), worst case 
    public boolean parseBoolExpr(String expression) {
        return parse(expression, 0, expression.length());
    }
    private boolean parse(String s, int lo, int hi) {
        char c = s.charAt(lo);
        if (hi - lo == 1) return c == 't'; // base case.
        boolean ans = c == '&'; // only when c is &, set ans to true; otherwise false.
        for (int i = lo + 2, start = i, level = 0; i < hi; ++i) {
            char d = s.charAt(i);
            if (level == 0 && (d == ',' || d == ')')) { // locate a valid sub-expression. 
                boolean cur = parse(s, start, i); // recurse to sub-problem.
                start = i + 1; // next sub-expression start index.
                if (c == '&') ans &= cur;
                else if (c == '|') ans |= cur;
                else ans = !cur; // c == '!'.
            }
            if (d == '(') ++level;
            if (d == ')') --level;
        }
        return ans;
    }
    
    // OOP solution, this should be the best solution
    public boolean parseBoolExpr_OOP(String expression) {
        String exp = expression.trim();
        if(exp.isBlank())
            throw new IllegalArgumentException("Expression can't be empty");
        if(exp.length() == 1)
            return exp.equals("t");

        Stack<Expr> stack = new Stack<>();
        TrueExpr trueExpr = new TrueExpr();
        FalseExpr falseExpr = new FalseExpr();
        int idx = 0;
        while(idx < exp.length()) {
            char c = exp.charAt(idx++);
            if(c == '|') stack.push(new OrExpr());
            else if(c == '&') stack.push(new AndExpr());
            else if(c == '!') stack.push(new NegateExpr());
            else if(c == 'f') stack.peek().add(falseExpr);
            else if(c == 't') stack.peek().add(trueExpr);
            else if(c == ')') {
                boolean res = stack.pop().eval();
                if(stack.isEmpty())
                    return res;
                stack.peek().add(res ? trueExpr : falseExpr);
            }
        }

        throw new IllegalStateException();
    }

    private static abstract class Expr {
        List<Expr> list;

        private Expr() {
            list = new LinkedList<>();
        }

        abstract boolean eval();

        void add(Expr e) {
            list.add(e);
        }
    }

    private static class TrueExpr extends Expr {

        @Override
        boolean eval() {
            return true;
        }

        @Override
        void add(Expr e) {
            throw new UnsupportedOperationException();
        }
    }

    private static class FalseExpr extends Expr {

        @Override
        boolean eval() {
            return false;
        }

        @Override
        void add(Expr e) {
            throw new UnsupportedOperationException();
        }
    }

    private static class NegateExpr extends Expr {

        @Override
        boolean eval() {
            if(list.size() != 1)
                throw new IllegalArgumentException("Expected one item, got " + list.size());

            return !list.get(0).eval();
        }
    }

    private static class AndExpr extends Expr {

        @Override
        boolean eval() {
            if(list.isEmpty())
                throw new IllegalArgumentException("Expected at least one item");

            boolean res = list.get(0).eval();
            for(int i = 1; i < list.size(); i++)
                res &= list.get(i).eval();

            return res;
        }
    }

    private static class OrExpr extends Expr {

        @Override
        boolean eval() {
            if(list.isEmpty())
                throw new IllegalArgumentException("Expected at least one item");

            boolean res = list.get(0).eval();
            for(int i = 1; i < list.size(); i++)
                res |= list.get(i).eval();

            return res;
        }
    }
}