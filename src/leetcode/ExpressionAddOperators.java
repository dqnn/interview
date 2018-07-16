package leetcode;

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
     time : O(n * 4 ^(n-1)) 
     space : O(n)

     
     * @param num
     * @param target
     * @return
     */

    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        if (num == null || num.length() == 0) return res;
        helper(res, "", num, target, 0, 0, 0);
        return res;
    }

    // path: str needed to be in reset set
    // num is the original number, string
    // target is the target value
    //pos is current index when recursive functionality happens, 
    //val 
    private void helper(List<String> res, String path, String num, int target, int pos, long val, long pre) {
        // if we reached the end of num
        // and we find val changed to be the same as target chich means we reached the target
        if (pos == num.length()) {
            if (target == val) {
                res.add(path);
                return;
            }
        }
        // this is permutation templates, for loop visit each position which we can 
        // use for + - * or space to form integer or binary expressions
        for (int i = pos; i < num.length(); i++) {
            if (i != pos && num.charAt(pos) == '0') break;
            long cur = Long.parseLong(num.substring(pos, i + 1));
            if (pos == 0) {
                helper(res, path + cur, num, target, i + 1, cur, cur);
            } else {
                helper(res, path + "+" + cur, num, target, i + 1, val + cur, cur);
                helper(res, path + "-" + cur, num, target, i + 1, val - cur, -cur);
                helper(res, path + "*" + cur, num, target, i + 1, val - pre + pre * cur, pre * cur);
            }
        }
    }
}
