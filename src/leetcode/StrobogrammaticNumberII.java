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
    //the problem is to output list of strobogrammatic numbers 
    //which is length less than n
    
    //so from I we can know there will two pointers, but here if we continue to use two pointers, 
    //we need several for loop and base n = 1 to solve this problem, TODO: why we use recursive instead of for loop
    
    //here we use different way to solve this problem, we use f(n) = 4 * f(n - 2), the anonying part is to handle 
    // "0" case, so if it is not the outest loop, then we need to add "0" as valid case, if it is, then we do not need 
    public List<String> findStrobogrammatic(int n) {
        return helper(n, n);
    }
    public List<String> helper(int n, int m) {
        if (n == 0) return new ArrayList<>(Arrays.asList(""));
        if (n == 1) return new ArrayList<>(Arrays.asList("0", "1", "8"));

        // n - 2 because 
        List<String> list = helper(n - 2, m);
        List<String> res = new ArrayList<>();
        //note: list at least has 1 size, "" 
        // 
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            //It is to disallow zeros as first and last digit. 
            //We want numbers of length 'n'.
            //because in next loop, it maybe add 1 outside 0 s 0
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
