    package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ExpressionAddOperators
 * Creator : duqiang
 * Date : Dec, 2017
 * Description : 282. Expression Add Operators
 */
public class ExpressionAddOperators {
    
    /**
     * Given a string that contains only digits 0-9 and a target value, 
     * return all possibilities to add binary operators (not unary) +, -, or * 
     * between the digits so they evaluate to the target value.

     Examples: 
     "123", 6 -> ["1+2+3", "1*2*3"] 
     "232", 8 -> ["2*3+2", "2+3*2"]
     "105", 5 -> ["1*0+5","10-5"]
     "00", 0 -> ["0+0", "0-0", "0*0"]
     "3456237490", 9191 -> []

     4 represents  how many operators we can place in this position, + - * and space, space means we can 
     combine them together
     T(n) = 3 * T(n-1) + 3 * T(n-2) + 3 * T(n-3) + ... + 3 *T(1);
     T(n-1) = 3 * T(n-2) + 3 * T(n-3) + ... 3 * T(1);    
     T(n) = 3 * 4( T(n-2) + T(n-3)+... T(0)) = 3*4^2(T(n-3) +...+T(0))
          = 3 * 4^(n-1) T(0)
     
     time : O(n * 4 ^ n) 
     space : O(n)

     
     * @param num
     * @param target
     * @return
     */

    // pic to demonstrate how code process
    //interview friendly: 
    public static List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        if (num == null || num.length() < 1) {
            return res;
        }
        // DFS to find all possibilities
        helper(res, "", num, target, 0, 0, 0);
        return res;
    }
     // path: str needed to be in res set
    // num is the original number, string
    // target is the target value
    //pos is current index when recursive functionality happens, 
    //val is current total integer results from 0 to pos
    // pre is previous integer result when we parse the string num, 
    //not eval number, must be with signed, for example, 1+2, 2 is pre number, 1+2*3, pre = 6
    private static void helper(List<String> res, String path, String num, int target, int pos, long val, long pre) {
        if (pos == num.length()) {
            if (target == val) {
                res.add(path);
                return;
            }
        }
        // this is permutation templates, for loop visit each position which we can 
        // use for + - * or space to form integer or binary expressions
        for(int i = pos; i < num.length(); i++) {
            // this is to remove digit like 0X, like 05, 06, for example 105,5 as input
            //if we remove this line, then we will get 1*0 + 5, 1*5, 10-5, but 1*5 is incorrect,
            //because we this line will have 05-5, cur = 5. so we will cut all nodes from the runtime
            //tree
            String str = num.substring(pos, i+1);
            if (str.startsWith("0") && str.length() > 1) break;
            
            Long cur = Long.valueOf(str);
            if (pos == 0) {
                // here cur changed to pre-evaluation results
                helper(res, path + cur, num, target, i+1, cur, cur);
            } else {
                // 
                helper(res, path + "+" + cur, num, target, i + 1, val + cur, cur);
                // 1+2-3,   so we need to take with sign to be pre value here. 
                helper(res, path + "-" + cur, num, target, i + 1, val - cur, -cur);
                // for example 1+2*3
                // we have added 2 in val, but * priveledge is higher than +, so we need to get pre 2 out of val
                // and then add true result: pre * cur
                helper(res, path + "*" + cur, num, target, i + 1, val - pre + pre * cur, pre * cur);
            }
            
        }
    }
    
    public static void main(String[] args) {
        System.out.print(addOperators("105", 5));
    }
}
