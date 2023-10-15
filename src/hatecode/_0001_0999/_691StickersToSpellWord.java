package hatecode._0001_0999;

import java.util.*;
public class _691StickersToSpellWord {
/*
691. Stickers to Spell Word
We are given N different types of stickers. 
Each sticker has a lowercase English word on it.

You would like to spell out the given target string by cutting individual letters from your collection of 
stickers and rearranging them.You can use each sticker more than once if you want, and you have infinite quantities of each sticker.
What is the minimum number of stickers that you need to spell out the target? If the task is impossible, return -1.

Example 1:

Input:

["with", "example", "science"], "thehat"
Output:

3

*/
/*
dp[s] is the minimum stickers required for string s (-1 if impossible). Note s is sorted.
clearly, dp[""] = 0, and the problem asks for dp[target].

dp[s] = min(1+dp[reduced_s]) for all stickers, 
here reduced_s is a new string after certain sticker applied
 */
   //thinking process: 
   /*
    * the problem is to say:given list of strings ss, and one target string, you  can cut each character from ss[i], arrange them to target string 
    t, return the min stickers you need. for example
    ss = ["with", "example", "science"], t = "thehat"

    you can use 2 "with",  1 "example" to form "thehat"

    how to solve the problem:

    we can start with "thehat"-->{t:2, h:2, e:1, a:1}
    let's use ss[0], then it will be {t:1 h:1 e:1 a:1},
    here we can see this can be same subproblem compare to previous, we want to know how many stickers can form thea, suppose it needs m, then in previous 
    round it will need 1 + m 

    edge case: if target contains character not exist in ss, how we will handle:
    two ways:
    1. if we did not have it, then just skip it or return -1;
    2. use visited array, if one target string we saw before which means we cannot form, then return -1

    */
   public static int minStickers_DP(String[] stickers, String target) {
        int m = stickers.length;
        int[][] mp = new int[m][26];
        Map<String, Integer> dp = new HashMap<>();
        for (int i = 0; i < m; i++) 
            for (char c:stickers[i].toCharArray()) mp[i][c-'a']++;
        dp.put("", 0);
        return helper(dp, mp, target);
    }


    private static int helper(Map<String, Integer> dp, int[][] mp, String target) {
        if (dp.containsKey(target)) return dp.get(target);
        int res = Integer.MAX_VALUE, n = mp.length;
        int[] tar = new int[26];
        for (char c:target.toCharArray()) tar[c-'a']++;
        // try every sticker
        for (int i = 0; i < n; i++) {
            // optimization and it will help to avoid overflow for charcters not exist in mp
            /*
             * another way to avoid is to use visited 
             */
            if (mp[i][target.charAt(0)-'a'] == 0) continue;
            StringBuilder sb = new StringBuilder();
            // apply a sticker on every character a-z
            for (int j = 0; j < 26; j++) {
                if (tar[j] > 0 ) 
                    for (int k = 0; k < Math.max(0, tar[j]-mp[i][j]); k++)
                        sb.append((char)('a'+j));
            }
            String s = sb.toString();
            int tmp = helper(dp, mp, s);
            if (tmp != -1) res = Math.min(res, 1+tmp);
        }
        dp.put(target, res == Integer.MAX_VALUE? -1:res);
        return dp.get(target);
    }

    /*
     * another DP solution, 
     * 
     * 
     */
    public int minStickers_DP_variation(String[] ss, String t) {
        if (t == null || t.length() < 1) return 0;
        if(ss == null || ss.length < 1) return -1;
        
        int[][] map = new int[ss.length][26];
        
        for(int i = 0; i< ss.length; i++) {
            for(char c: ss[i].toCharArray()) {
                map[i][c-'a']++;
            }
        }
        
        Map<String, Integer> memo = new HashMap<>();
        memo.put("", 0);

        helper(ss, t, map, memo);
        
        return memo.get(t)== Integer.MAX_VALUE? -1: memo.get(t);
    }
    
    
    private int helper(String[] ss, String t, int[][] mp, Map<String, Integer>memo) {
        if(memo.containsKey(t)) return memo.get(t);
        
        int[] tar = new int[26];
        for(char c: t.toCharArray()) tar[c-'a']++;
        StringBuilder sb1 = new StringBuilder();
        for(int i = 0; i< 26; i++) {
            for(int j = 0; j< tar[i]; j++){
                sb1.append((char)('a' + i));
            }
        }
        memo.put(sb1.toString(), Integer.MAX_VALUE);
        
        
        int res = Integer.MAX_VALUE;
        for(int i = 0; i< ss.length; i++) {
            
            //if(tar[c-'a'] == 0) continue;
           //if(mp[i][t.charAt(0) - 'a'] == 0) continue;
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j<26;j++) {
                if(tar[j]  > 0) 
                for(int k = 0; k < Math.max(0, tar[j] - mp[i][j]); k++) {
                    sb.append((char)('a' + j));
                }
            }
            
            
            String nt = sb.toString();
            if(nt.length() == t.length()) continue;
            int tmp = helper(ss, nt, mp, memo);
            
            if(tmp != -1) res = Math.min(res, tmp + 1);
        }
        
        res = res == Integer.MAX_VALUE? -1: res;
        memo.put(t, res);
        //System.out.println(memo);
        
        return memo.get(t);
    }


    public static void main(String[] args) {
        System.out.println(minStickers_DP(new String[]{"with", "example", "science"}, "thehatz"));
    }
    
    //best performance ones
    public int minStickers(String[] stickers, String target) {
        int[] order = new int[26];
        Arrays.fill(order, -1);
        int len = 0;
        
        char[] ts = target.toCharArray();
        //int tl = te.length;
        for (char c:ts) {
            int index = c - 'a';
            if (order[index] == -1) {
                order[index] = len++;
            }
        }
        int sl = stickers.length;
        

        int[][] s = new int[sl][len];
        boolean[] sum = new boolean[len];
        for (int i = 0; i < sl; ++i) {
            String ss = stickers[i];
            for (char c:ss.toCharArray()) {
                int index = order[c - 'a'];
                if (index >= 0) {
                    ++s[i][index];
                    sum[index] = true;
                }
            }
        }
        
        int[] t = new int[len];
        for (char c:ts) {
            int od = order[c - 'a'];
            if(!sum[od]){
                return -1;
            }
            ++t[od];
        }
        
        boolean[] remain = new boolean[sl];

        int kk = sl;
        for (int i = 0; i < sl; ++i) {
            if (!remain[i]) {
                for (int j = 0; j < sl; ++j) {
                    if (!remain[j] && i != j) {
                        if (cover(s[i], s[j])) {
                            remain[j] = true;
                            kk -= 1;
                        }
                    }
                }
            }
        }

        int[][] sss = new int[kk][len];
        for (int i = 0, ii = 0; i < sl; ++i) {
            if (!remain[i]) {
                sss[ii++] = s[i];
            }
        }

       

        

        int[] ret = new int[1];
        ret[0] = Integer.MAX_VALUE;
        helper22(sss, t, 0, new int[15], 0, t[0], ret);

        return ret[0];
    }

    private boolean cover(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] < b[i]) {
                return false;
            }
        }
        return true;
    }

    private void helper22(int[][] s, int[] t, int index, int[] path, int count, int remain, int[] ret) {
        if (count >= ret[0]) {
            return;
        }
        
        while (remain <= 0) {
            ++index;
            if (index >= t.length) {
                break;
            }
            remain = t[index];
            for (int i = 0; i < count; i++) {
                remain -= s[path[i]][index];
                if (remain <= 0) {
                    break;
                }
            }
        }
        if (index >= t.length) {
            ret[0] = Math.min(ret[0], count);
            return;
        }

        for (int i = 0; i < s.length; i++) {
            if (s[i][index] > 0) {
                path[count] = i;
                helper22(s, t, index, path, count + 1, remain - s[i][index], ret);
            }
        }
    }
}