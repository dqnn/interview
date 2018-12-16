package hatecode;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name : Leetcode Package Name : leetcode File Name :
 * DifferentWaystoAddParentheses
 * 
 * Creator : duqiang Date : July, 2018 Description
 * 
 * 
 * : 241. Different Ways to Add Parentheses
 */
public class DifferentWaystoAddParentheses {
    /**
     * Given a string of numbers and operators, return all possible results from
     * computing all the different possible ways to group numbers and operators. The
     * valid operators are +, - and *.
     */

/*
     Example 1
     Input: "2-1-1".

     ((2-1)-1) = 0
     (2-(1-1)) = 2
     Output: [0, 2]


     Example 2
     Input: "2*3-4*5"

     (2*(3-(4*5))) = -34
     ((2*3)-(4*5)) = -14
     ((2*(3-4))*5) = -10
     (2*((3-4)*5)) = -10
     (((2*3)-4)*5) = 10
     Output: [-34, -14, -10, -10, 10]

     time : O(n^3) 不确定
     space : O(n)


     * @param input
     * @return
     */
    // recursive is pretty important method to write code,
    // main problem = split into two sub problems and we can use their results to
    // get
    // to the root node, this is top down recursive
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            // if it contains "/" then also should be here, need more investigation?
            // i think here avoid /0 case, so we did not introduce "/" in this problem
            if (c == '-' || c == '+' || c == '*' || c == '/') {
                String a = input.substring(0, i);
                String b = input.substring(i + 1);
                // recursive to get the result of first half
                List<Integer> al = diffWaysToCompute(a);
                List<Integer> bl = diffWaysToCompute(b);
                for (int x : al) {
                    for (int y : bl) {
                        if (c == '-') {
                            res.add(x - y);
                        } else if (c == '+') {
                            res.add(x + y);
                        } else if (c == '*') {
                            res.add(x * y);
                        }
                    }
                }
            }
        }
        // just one integer in expressions, here is exception case
        if (res.size() == 0) {
            res.add(Integer.valueOf(input));
        }
        return res;
    }
}
