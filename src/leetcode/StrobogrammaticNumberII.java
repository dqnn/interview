package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : StrobogrammaticNumberII
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 247. Strobogrammatic Number II
 */
public class StrobogrammaticNumberII {
    /**
     * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

     Find all strobogrammatic numbers that are of length = n.

     For example,
     Given n = 2, return ["11","69","88","96"].

     n = 3 :  ["101","609","808","906","111","619","818","916","181","689","888","986"]


     time : O(n^2) 不确定
     space : O(n)

     * @param n
     * @return
     */
    //thinking process:
    // so if n = 1, will output 3 strings, n is the length of the string
    // so it is more like palidrome, since we have limited numbers
    //which has such character, so we need  a map. 
    
    // we use recursive to solve this problem from 1,6,8, 0
    //n stands for length of string, m is parameter to decide 
    //whether we are at outest digit, it would not 0 there. 
    public List<String> findStrobogrammatic(int n) {
        return helper(n, n);
    }
    public List<String> helper(int n, int m) {
        if (n == 0) return new ArrayList<>(Arrays.asList(""));
        if (n == 1) return new ArrayList<>(Arrays.asList("0", "1", "8"));

        List<String> list = helper(n - 2, m);
        List<String> res = new ArrayList<>();
        //note: list at least has 1 size, "" 
        // 
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            //It is to disallow zeros as first and last digit. 
            //We want numbers of length 'n'.
            if (n != m) {
                res.add("0" + s + "0");
            }
            res.add("1" + s + "1");
            res.add("6" + s + "9");
            res.add("8" + s + "8");
            res.add("9" + s + "6");
        }
        return res;
    }
}
