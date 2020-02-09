package hatecode._0001_0999;
import java.util.*;
public class ParseLispExpression {
    /*
     * 736. Parse Lisp Expression You are given a string expression representing a
     * Lisp-like expression to return the integer value of.
     * 
     * The syntax for these expressions is given as follows.
     * 
     * An expression is either an integer, a let-expression, an add-expression, a
     * mult-expression, or an assigned variable. Expressions always evaluate to a
     * single integer. (An integer could be positive or negative.) A let-expression
     * takes the form (let v1 e1 v2 e2 ... vn en expr), where let is always the
     * string "let", then there are 1 or more pairs of alternating variables and
     * expressions, meaning that the first variable v1 is assigned the value of the
     * expression e1, the second variable v2 is assigned the value of the expression
     * e2, and so on sequentially; and then the value of this let-expression is the
     * value of the expression expr. An add-expression takes the form (add e1 e2)
     * where add is always the string "add", there are always two expressions e1,
     * e2, and this expression evaluates to the addition of the evaluation of e1 and
     * the evaluation of e2. A mult-expression takes the form (mult e1 e2) where
     * mult is always the string "mult", there are always two expressions e1, e2,
     * and this expression evaluates to the multiplication of the evaluation of e1
     * and the evaluation of e2. For the purposes of this question, we will use a
     * smaller subset of variable names. A variable starts with a lowercase letter,
     * then zero or more lowercase letters or digits. Additionally for your
     * convenience, the names "add", "let", or "mult" are protected and will never
     * be used as variable names. Finally, there is the concept of scope. When an
     * expression of a variable name is evaluated, within the context of that
     * evaluation, the innermost scope (in terms of parentheses) is checked first
     * for the value of that variable, and then outer scopes are checked
     * sequentially. It is guaranteed that every expression is legal. Please see the
     * examples for more details on scope. Evaluation Examples: Input: (add 1 2)
     * Output: 3
     * 
     * Input: (mult 3 (add 2 3)) Output: 15
     * 
     * Input: (let x 2 (mult x 5)) Output: 10
     * 
     */
    //thinking process: 
    //this has something similiar with calculator, but this difference is that 
    // it will output number finally while calc IV will output vars
    
    //similiar problem:  770. Basic Calculator IV
    public static int evaluate(String expression) {
        return eval(expression, new HashMap<>());
    }

    private static int eval(String exp, Map<String, Integer> parent) {
        //lisp must start with ( or it is just number
        if (exp.charAt(0) != '(') {
            // just a number or a symbol
            if (Character.isDigit(exp.charAt(0)) || exp.charAt(0) == '-')
                return Integer.parseInt(exp);
            return parent.get(exp);
        }
        // create a new scope, add add all the previous values to it,
        //for example let x 5, then x will be key. 5 is value
        Map<String, Integer> map = new HashMap<>();
        map.putAll(parent);
        //last is ), so exclude last )
        //mult add let, thats why mult use 6, others use 5
        List<String> tokens 
            = parse(exp.substring(exp.charAt(1) == 'm' ? 6 : 5, exp.length() - 1));
        //recuesive process the left strings
        if (exp.startsWith("(a")) { // add
            return eval(tokens.get(0), map) + eval(tokens.get(1), map);
        } else if (exp.startsWith("(m")) { // mult
            return eval(tokens.get(0), map) * eval(tokens.get(1), map);
        } else { // let
            for (int i = 0; i < tokens.size() - 2; i += 2)
                map.put(tokens.get(i), eval(tokens.get(i + 1), map));
            return eval(tokens.get(tokens.size() - 1), map);
        }
    }

    //parse is only to parse the first layer of parentness,
    //str = x 2 (mult x (let x 3 y 4 (add x y)))
    private static List<String> parse(String str) {
        // seperate the values between two parentheses
        List<String> res = new ArrayList<>();
        //pareness count
        int par = 0;
        StringBuilder sb = new StringBuilder();

        for (char c : str.toCharArray()) {
            if (c == '(') par++;
            if (c == ')') par--;
            if (par == 0 && c == ' ') {
                res.add(sb.toString());
                sb = new StringBuilder();
            } else  sb.append(c);
        }
        if (sb.length() > 0) res.add(new String(sb));

        return res;
    }
    //(let x 2 (mult x (let x 3 y 4 (add x y))))-->
    //tokens = [x, 2, (mult x (let x 3 y 4 (add x y)))]
    public static void main(String[] args) {
        String in ="(let x 2 (mult x (let x 3 y 4 (add x y))))";
        System.out.print(evaluate(in));
    }
}