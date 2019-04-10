package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MinimumWindowSubstring
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : 76. Minimum Window Substring
 */
public class MinimumWindowSubstring {
    /**
     * sliding window
     * Given a string S and a string T, find the minimum window in S which will contain all the characters
     * in T in complexity O(n).

     For example,
     S = "ADOBECODEBANC"
     T = "ABC"
     Minimum window is "BANC".

     Note:
     If there is no such window in S that covers all characters in T, return the empty string "".

     If there are multiple such windows, you are guaranteed that there will always be only one unique
     minimum window in S.

     test case:
     S = "ADOBECODEBANC"
     T = "ABC"

     ADOBEC
     DOBECODEBA
     OBECODEBA
     BECODEBA
     ECODEBA
     CODEBA
     ODEBANC
     DEBANC
     EBANC
     BANC

            A B C D E F G H    O
     count: 0 0 0 -1-1         -1
            0 1 2 3 4 5 6 7 ...

     A D O B E C O D E B A N C
               i
     CODEBA
     total = 1

     time : O(n)
     space : O(1)

     * @param s
     * @param t
     * @return
     */
    //thinking process: 
    public static String minWindow(String s, String t) {
        if (s == null || t == null) {
            return null;
        }
        int[] cnt = new int[128];
        for (char c : t.toCharArray()) {
            cnt[c]++;
        }
        int from = 0;
        int total = t.length();
        int min = Integer.MAX_VALUE;
        //templates for all susbstirngs
        for (int r = 0, l = 0; r < s.length(); r++) {
            //if we found one in map, so we reduce --
            if (cnt[s.charAt(r)]-- > 0) total--;
            // s if totally == 0 so means we find all chars
            // this while mainly focus on moving j
            while (total == 0) {
                // the length, form i->j
                if (r - l + 1 < min) {
                    min = r - l + 1;
                    from = l;
                }
                // so if we move j to next position to whether we can find one char in the array 
                // so we have move from beginning to end, like i to end, so we can get overall shortest one
                if (++cnt[s.charAt(l++)] > 0) total++;
            }
        }
        // this means there is no such chars in S so we can return ""
        return (min == Integer.MAX_VALUE) ? "" : s.substring(from, from + min);
    }
    
    //this is template code for all substring ,sub array, and related sliding window questions
    public String minWindow_Template(String s, String t) {
        int[] map = new int[128];

        for (char c : t.toCharArray())
            map[c]++;

        int count = t.length(), begin = 0, end = 0, d = Integer.MAX_VALUE, head = 0;

        while (end < s.length()) {
            if (map[s.charAt(end)] > 0){
                count--;
            }
            map[s.charAt(end)]--;
            end++;
            while (count == 0) {
                if (end - begin < d) {
                    d = end - begin;
                    head = begin;
                }
                if (map[s.charAt(begin)] == 0)
                    count++;
                map[s.charAt(begin)]++;
                begin++;
            }
        }

        return d == Integer.MAX_VALUE ? "" : s.substring(head, head + d);

    }
    
    public static void main(String[] args) {
        String S = "AOBECOEBANC", T = "ABC";
        System.out.println(minWindow(S, T));
    }
}
