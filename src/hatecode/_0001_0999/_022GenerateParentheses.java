package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by professorX on 21/07/2018.
 */
public class _022GenerateParentheses {
    /**
     * 22. Generate Parentheses
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

     For example, given n = 3, a solution set is:
     [
     "((()))",
     "(()())",
     "(())()",
     "()(())",
     "()()()"
     ]

     卡特兰数：
     (0,n-1) (1,n-2) (2,n-3) ... (n-1,0)

     * @param n
     * @return
     */

    //interview friendly:
    //O(4^n/n^1/2)/O(4^n/n^1/2) see LC solution
    
    /*
     * left and right means how many "(" and ")" need to be added to string
     */
    public List<String> generateparentheses(int n) {
        List<String> res = new ArrayList<>();
        if (n < 1) return res;
        
        helper(res, "", n, 0);
        return res;
    }
    
    public void helper(List<String> res, String pre, int left, int right) {
        // this is to avoid that ) is more than (  which is the wrong answer
       // if (left > right) return;
        if (left == 0 && right == 0) {
            res.add(pre);
            return;
        }
        //means left and right still need to be added to results, we do not need 
        //worry about the dup results
        if (left > 0) helper(res, pre + "(", left - 1, right + 1);
        if (right > 0) helper(res, pre + ")", left, right - 1);
    }
    
    public List<String> generateparentheses2(int n) {
        List<String> list = new ArrayList<String>();
        backtrack(list, "", 0, 0, n);
        return list;
    }
    
    public void backtrack(List<String> list, String str, int open, int close, int max){
        
        if(str.length() == max*2){
            list.add(str);
            return;
        }
        
        if(open < max) backtrack(list, str+"(", open+1, close, max);
        if(close < open) backtrack(list, str+")", open, close+1, max);
    }
    
    
    public List<String> generateparentheses3(int n) {
        List<String> res = new ArrayList<>();
        if (n < 1) {
            return res;
        }
        
        helper(res, "", n, 0);
        return res;
    }
    // here n means how many ( left to be added and righ means how many ) to be added
    // in this case we don't need to check left > right
    public void helper3(List<String> res, String pre, int left, int right) {
        if (left == 0 && right == 0) {
            res.add(pre);
            return;
        }
        //so when we add 1 more (, we have to make sure we add another ) 
        if (left > 0) {
            helper3(res, pre + "(", left - 1, right + 1);
        }
        
        if (right > 0) {
            helper3(res, pre + ")", left, right - 1);
        }
    }
}
