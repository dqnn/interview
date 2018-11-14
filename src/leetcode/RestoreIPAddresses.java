package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : helperAddresses
 * Creator : duqiang
 * Date : Sep, 2018
 * Descrstion : 93. Restore IP Addresses
 */
public class RestoreIPAddresses {
    /**
     * Given a string containing only digits, restore it by returning 
     * all possible valid s address combinations.

     For example:
     Given "25525511135",

     return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)

     time : O(3^4) => O(1) => O(3^n)
     space : O(n)
     
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        helper(res, s, 0, "", 0);
        return res;
    }
    public void helper(List<String> res, String s, int index, String ret, int count) {
        if (count > 4) return;
        if (count == 4 && index == s.length()) {
            res.add(ret);
            return;
        }
        for (int i = 1; i < 4; i++) {
            if (index + i > s.length()) break;
            String temp = s.substring(index, index + i);
            if ((temp.startsWith("0") && temp.length() > 1) 
                    || (i == 3 && Integer.parseInt(temp) >= 256)) continue;
            helper(res, s, index + i, ret + temp + (count == 3 ? "" : "."), count + 1);
        }
    }
    
    
    // interview friendly, wrote by myself
    //backtracking or BFS templates
    public List<String> restoreIpAddresses2(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() < 1) {
            return res;
        }
        helper2(res, s, "", 0, 4);
        return res;
    }
    //templates
    public void helper2(List<String> res, String s, String cur, int index, int dim) {
        // dim means dimension, 4 parts for ip address
        if (dim < 0 || s.length() - index > dim * 3 || s.length() - index < dim) {
            return;
        }
        
        if (index == s.length() && dim == 0) {
            res.add(cur.substring(0, cur.length()-1));
            return;
        }
        for(int i = index; i < index + 3; i++) {
            if (i >= s.length()) break;
            String ip = s.substring(index, i+1);
            if (Integer.valueOf(ip) > 255 || ip.startsWith("0") && ip.length() > 1) {
                continue;
            }
            helper2(res, s, cur + ip +".", i + 1, dim - 1);
        }
    }
}
