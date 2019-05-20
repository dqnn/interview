package hatecode;

import java.util.*;
public class StickersToSpellWord {
/*
691. Stickers to Spell Word
We are given N different types of stickers. Each sticker has a lowercase English word on it.

You would like to spell out the given target string by cutting individual letters from your collection of stickers and rearranging them.

You can use each sticker more than once if you want, and you have infinite quantities of each sticker.

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
   //
   public int minStickers_DP(String[] stickers, String target) {
        int m = stickers.length;
        int[][] mp = new int[m][26];
        Map<String, Integer> dp = new HashMap<>();
        for (int i = 0; i < m; i++) 
            for (char c:stickers[i].toCharArray()) mp[i][c-'a']++;
        dp.put("", 0);
        return helper(dp, mp, target);
    }
    private int helper(Map<String, Integer> dp, int[][] mp, String target) {
        if (dp.containsKey(target)) return dp.get(target);
        int ans = Integer.MAX_VALUE, n = mp.length;
        int[] tar = new int[26];
        for (char c:target.toCharArray()) tar[c-'a']++;
        // try every sticker
        for (int i = 0; i < n; i++) {
            // optimization
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
            if (tmp != -1) ans = Math.min(ans, 1+tmp);
        }
        dp.put(target, ans == Integer.MAX_VALUE? -1:ans);
        return dp.get(target);
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