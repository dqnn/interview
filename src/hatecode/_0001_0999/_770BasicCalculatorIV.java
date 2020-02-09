package hatecode._0001_0999;
import java.util.*;
public class _770BasicCalculatorIV {
/*
770. Basic Calculator IV
Given an expression such as expression = "e + 8 - a + 5" and an evaluation map such as 
{"e": 1} (given in terms of evalvars = ["e"] and evalints = [1]), return a list of tokens 
representing the simplified expression, such as ["-1*a","14"]

An expression alternates chunks and symbols, with a space separating each chunk and symbol.
A chunk is either an expression in parentheses, a variable, or a non-negative integer.
A variable is a string of lowercase letters (not including digits.) Note that variables 
can be multiple letters, and note that variables never have a leading coefficient or unary 
operator like "2x" or "-x".
Expressions are evaluated in the usual order: brackets first, then multiplication, 
then addition and subtraction. For example, expression = "1 + 2 * 3" has an answer of ["7"].

The format of the output is as follows:

For each term of free variables with non-zero coefficient, we write the free variables within a term in sorted order lexicographically. For example, we would never write a term like "b*a*c", only "a*b*c".
Terms have degree equal to the number of free variables being multiplied, 
counting multiplicity. (For example, "a*a*b*c" has degree 4.) We write the 
largest degree terms of our answer first, breaking ties by lexicographic order 
ignoring the leading coefficient of the term.
The leading coefficient of the term is placed directly to the left with an asterisk 
separating it from the variables (if they exist.)  A leading coefficient of 1 is still 
printed.
An example of a well formatted answer is ["-2*a*a*a", "3*a*a*b", "3*b*b", "4*a", "5*c", "-6"] 
Terms (including constant terms) with coefficient 0 are not included.  For example, 
an expression of "0" has an output of [].
Examples:

Input: expression = "e + 8 - a + 5", evalvars = ["e"], evalints = [1]
Output: ["-1*a","14"]

Input: expression = "e - 8 + temperature - pressure",
evalvars = ["e", "temperature"], evalints = [1, 12]
Output: ["-1*pressure","5"]

Input: expression = "(e + 8) * (e - 8)", evalvars = [], evalints = []
Output: ["1*e*e","-64"]

Input: expression = "7 - 7", evalvars = [], evalints = []
Output: []

Input: expression = "a * b * c + b * a * c * 4", evalvars = [], evalints = []
Output: ["5*a*b*c"]

Input: expression = "((a - b) * (b - c) + (c - a)) * ((a - b) + (b - c) * (c - a))",
evalvars = [], evalints = []
Output: ["-1*a*a*b*b","2*a*a*b*c","-1*a*a*c*c","1*a*b*b*b","-1*a*b*b*c","-1*a*b*c*c",
"1*a*c*c*c","-1*b*b*b*c","2*b*b*c*c","-1*b*c*c*c","2*a*a*b","-2*a*a*c","-2*a*b*b",
"2*a*c*c","1*b*b*b","-1*b*b*c","1*b*c*c","-1*c*c*c","-1*a*a","1*a*b","1*a*c","-1*b*c"]

*/
/*
Solution:
Basic Calculator IV: Solutions for this version, however, require some extra effort apart 
from the general structure in section III. Due to the presence of variables (free variables, 
to be exact), the partial results for each level of calculations may not be pure numbers, 
but instead expressions (simplified, of course). So we have to come up with some structure 
to represent these partial results.

Though multiple options exist as to designing this structure, we do want it to support 
operations like addition, subtraction and multiplication with relative ease. Here I 
represent each expression as a collection of mappings from terms to coefficients, where 
each term is just a list of free variables sorted in lexicographic order. Some quick 
examples:

"2 * a * b - d * c": this expression contains two terms, where the first term is ["a", "b"] 
and the second is ["c", "d"]. The former has a coefficient of 2 while the latter has 
a coefficient of -1. So we represent the expression as two mappings: ["a", "b"] --> 2 
and ["c", "d"] --> -1.

"4": this expression contains a single term, where the term has zero free variables and 
thus will be written as []. The coefficient is 4 so we have the mapping [] --> 4. More 
generally, for any expression formed only by a pure number num, we have [] --> num.

See below for a detailed definition of the Term and Expression classes.

The Term class will support operations for comparing two terms according to their 
degrees as well as for generating a customized string representation.

The Expression class will support operations for adding additional terms to the 
existing mapping.

Addition of two expressions is done by adding all mappings in the second expression 
to those of the first one, and if possible, combine the coefficients of duplicate terms.

Subtraction of two expressions is implemented by first negating the coefficients of 
terms in the second expression and then applying addition (and thus can be combined 
with addition).

Multiplication of two expressions is done by collecting terms formed by merging every 
pair of terms from the two expressions (as well as their coefficients).

Lastly to conform to the format of the output, we have a dedicated function to convert 
the mappings in the result expression to a list of strings, where each string consists 
of the coefficient and the term connected by the * operator. Note that terms with 0 
coefficient are ignored and terms without free variables contains numbers only.

As to complexity analysis, the nominal runtime complexity of this solution is similar 
to the recursive one for Basic Calculator III -- O(n^2). It is also possible to use 
stacks to simulate the recursion process and cut the nominal time complexity down to 
O(n) (I will leave this as an exercise for you).

Here I used the word "nominal" because the above analyses assumed that the addition, 
subtraction and multiplication operations of two expressions take constant time, 
as is the case when the expressions are pure numbers. Apparently this assumption no 
longer stands true, since the number of terms may grow exponentially (think about this 
expression (a + b) * (c + d) * (e + f) * ...). Nevertheless, this solution should work 
against average/general test cases.
*/
/**
    @Term represents a list of variables: example: a * a * b * c * d -> [a, a, b, c, d]
*/
    private static class Term implements Comparable<Term> {
        List<String> vars;
        //this is for pure numbers, like e = 1
        static final Term C = new Term(Arrays.asList()); // this is the term for pure numbers

        Term(List<String> vars) {
            this.vars = vars;
        }

        public int hashCode() {
            return vars.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Term)) return false;

            Term other = (Term) obj;
            return this.vars.equals(other.vars);
        }

        public String toString() {
            return String.join("*", vars);
        }

        /**
         * Compares two Terms according to the following: 1. returns 0 if the two Terms
         * has the same number of variables and all corresponding variables are the
         * same. 2. return +ve if either: i. @that term has more variables than @this
         * term, otherwise, ii. if @this term is lexicorgraphically smaller than @that
         * 3. return -ve otherwise.
         */
        public int compareTo(Term other) {
            if (this.vars.size() != other.vars.size()) {
                //we cannot change the order, but it only affected result order
                return Integer.compare(other.vars.size(), this.vars.size());
            } else {
                for (int i = 0; i < this.vars.size(); i++) {
                    int cmp = this.vars.get(i).compareTo(other.vars.get(i));
                    if (cmp != 0)
                        return cmp;
                }
            }
            return 0;
        }
    }
    //stands for C1 * Term1 + C2 * Term2,  C1, C2 表示系数
   /*  2* 7 + 3
             exp
           /    \
         term  +   term
        / \        /  
     var *  var   var   
     /       |     |
    2        7     3
    */
    private class Expression {
        Map<Term, Integer> terms;

        Expression(Term term, int coeff) {
            terms = new HashMap<>();
            terms.put(term, coeff);
        }

        /**
         * Adds a term to this expression: 1- If the term already exists in this
         * expression then its coefficient is updated (added to the new coeeficient) 2-
         * If the coefficient is/becomes zero then this term is removed from the
         * expression
         */
        void addTerm(Term term, int coeff) {
            terms.put(term, coeff + terms.getOrDefault(term, 0));
        }
    }
    //two terms -> into one
    private Term merge(Term term1, Term term2) {
        List<String> vars = new ArrayList<>();

        vars.addAll(term1.vars);
        vars.addAll(term2.vars);
        Collections.sort(vars);

        return new Term(vars);
    }
/** Add terms of the @ex expression to the current expression
Example: Ex1 = C1 * Term1  + C2 * Term2 + C3 * Term3, 
         Ex2 = C4 * Term2  + C5 * Term8 + C6 * Term9
         Result = C1 * Term1  + (C2 + C4) * Term2 + C3 * Term3 + C5 * Term8  + C6 * Term9
*/
    private Expression add(Expression expression1, Expression expression2, int sign) {
        for (Map.Entry<Term, Integer> e : expression2.terms.entrySet()) {
            expression1.addTerm(e.getKey(), sign * e.getValue());
        }

        return expression1;
    }

    // multiply two expressions: ex: @a = C1 * Term1, @b = C2 * Term2
    // --> @ex = (C1 * C2) * (Term1 * Term2)
    private  Expression mult(Expression expression1, Expression expression2) {
        Expression res = new Expression(Term.C, 0);

        for (Map.Entry<Term, Integer> e1 : expression1.terms.entrySet()) {
            for (Map.Entry<Term, Integer> e2 : expression2.terms.entrySet()) {
                res.addTerm(merge(e1.getKey(), e2.getKey()), e1.getValue() * e2.getValue());
            }
        }

        return res;
    }
    //question: how to do divide /

    private Expression calculate(String s, Map<String, Integer> map) {
        Expression l1 = new Expression(Term.C, 0);
        int o1 = 1;

        Expression l2 = new Expression(Term.C, 1);
        // we don't need 'o2' because the precedence level two operators contain '*'
        // we did not process "*" since default we think it is mult
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // calc the number,like 10,20
            if (Character.isDigit(c)) { // this is a number
                int num = c - '0';

                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + (s.charAt(++i) - '0');
                }
                //C is shared instance which store all variables, this means for 
                //all variables in C, will all have its coeff num
                l2 = mult(l2, new Expression(Term.C, num));

            } else if (Character.isLowerCase(c)) { // this is a variable
                int j = i;
                //extract variables, so here we only consider single character or like word 
                //temp, a * b will be considered as two variables
                while (i + 1 < s.length() && Character.isLowerCase(s.charAt(i + 1)))
                    i++;
                //we extract the variable name,not sure why we have multiple 
                //chars for one variable
                String var = s.substring(j, i + 1);
                //if it is number, then it should be in C or we will have a new TERM
                Term term = map.containsKey(var) ? Term.C : new Term(Arrays.asList(var));
                int num = map.getOrDefault(var, 1);
                //here we will add num to term
                l2 = mult(l2, new Expression(term, num));
            } else if (c == '(') { // this is a subexpression
                int j = i;
                //to find the ")" closure to recursive calculate
                for (int cnt = 0; i < s.length(); i++) {
                    if (s.charAt(i) == '(')  cnt++;
                    if (s.charAt(i) == ')')  cnt--;
                    if (cnt == 0) break;
                }
                //recursive to calc the expressions in ()
                l2 = mult(l2, calculate(s.substring(j + 1, i), map));
            } else if (c == '+' || c == '-') { // level one operators
                l1 = add(l1, l2, o1);
                o1 = (c == '+' ? 1 : -1);
                //l2 starts new
                l2 = new Expression(Term.C, 1);
            }
        }
        return add(l1, l2, o1);
    }

    private  List<String> format(Expression expression) {
        List<Term> terms = new ArrayList<>(expression.terms.keySet());
        Collections.sort(terms);
        List<String> res = new ArrayList<>(terms.size());

        for (Term term : terms) {
            int coeff = expression.terms.get(term);
            if (coeff == 0) continue;
            res.add(coeff + (term.equals(Term.C) ? "" : "*" + term.toString()));
        }
        return res;
    }

    public  List<String> basicCalculatorIV(String expression, String[] evalvars, int[] evalints) {
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < evalvars.length; i++) {
            map.put(evalvars[i], evalints[i]);
        }

        return format(calculate(expression, map));
    }
    
    public static void main(String[] args) {
        _770BasicCalculatorIV s= new _770BasicCalculatorIV();
        String in = "e * 8 - a * 5";
        String[] evalvars = {"e"};
        int[] evalints = {1};
        
        System.out.println(s.basicCalculatorIV(in, evalvars, evalints));
    }
    
}