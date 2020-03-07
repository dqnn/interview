package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : StrobogrammaticNumberIII
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 248. Strobogrammatic Number III
 */
public class _248StrobogrammaticNumberIII {
    /**
     * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down
     * right-side up or upside down.).

     Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

     For example,
     Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.

     time : 不知道
     space : O(n)

     */
    //thinking process:
    
    //so based on 2, we can know how many combinations with len <=n, so if we have a range, we just need 
    //a.comparedTo(b) > 0 means a > b, a b are strings, so we can filter some we don't need
    public int strobogrammaticInRange(String low, String high){
        if (low == null || high == null || low.length() < 1 || high.length() < 1) {
            return 0;
        }
        int res = 0;
        List<String> list = new ArrayList<>();
        for (int i = low.length(); i <= high.length(); i++) {
            list.addAll(helper(i, i));
        }
        // a.comparesTo(b) > 0 if a lex order after b.
        for (String num : list) {
            if ((num.length() == low.length() && num.compareTo(low) < 0) 
                    || (num.length() == high.length() && num.compareTo(high) > 0)) {
                continue;
            }
            res++;
        }
        return res;
    }

    private List<String> helper(int cur, int max) {
        if (cur == 0) return new ArrayList<>(Arrays.asList(""));
        if (cur == 1) return new ArrayList<>(Arrays.asList("1", "8", "0"));

        List<String> res = new ArrayList<>();
        List<String> center = helper(cur - 2, max);

        for (int i = 0; i < center.size(); i++) {
            String tmp = center.get(i);
            if (cur != max) res.add("0" + tmp + "0");
            res.add("1" + tmp + "1");
            res.add("8" + tmp + "8");
            res.add("6" + tmp + "9");
            res.add("9" + tmp + "6");
        }
        return res;
    }
    
    
    //here used a two pointer solutions to get all possible combinations, tree. 
    
    //so we use DFS to find all possible combinations count
    private static final char[][] pairs = {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};

    public int strobogrammaticInRange2(String low, String high) {
        int[] count = {0};
        for (int len = low.length(); len <= high.length(); len++) {
            char[] c = new char[len];
            dfs(low, high, c, 0, len - 1, count);
        }
        return count[0];
    }
    //so c is len char as map to store each character, here use DFS to add all possible chars
    public void dfs(String low, String high , char[] c, int left, int right, int[] count) {
        if (left > right) {
            String s = new String(c);
            //low and high is used to exclude reduntant computations
            if ((s.length() == low.length() && s.compareTo(low) < 0) ||
                (s.length() == high.length() && s.compareTo(high) > 0)) {
                return;
            }
            count[0]++;
            return;
        }
        //we used one for loop and dfs to get the functionality, this is also one example 
        //how to base on paris to build all possible combinations
        for (char[] p : pairs) {
            c[left] = p[0];
            c[right] = p[1];
            if (c.length != 1 && c[0] == '0') {
                continue;
            }
            if (left == right && p[0] != p[1]) {
                continue;
            }
            dfs(low, high, c, left + 1, right - 1, count);
        }
    }
}
