package hatecode._1000_1999;

import java.util.*;

public class _1177CanMakePalindromeFromSubstring {
    /*
     * 1177. Can Make Palindrome from Substring Given a string s, we make queries on
     * substrings of s.
     * 
     * For each query queries[i] = [left, right, k], we may rearrange the substring
     * s[left], ..., s[right], and then choose up to k of them to replace with any
     * lowercase English letter.
     * 
     * If the substring is possible to be a palindrome string after the operations
     * above, the result of the query is true. Otherwise, the result is false.
     * 
     * Return an array answer[], where answer[i] is the result of the i-th query
     * queries[i].
     * 
     * Note that: Each letter is counted individually for replacement so if for
     * example s[left..right] = "aaa", and k = 2, we can only replace two of the
     * letters. (Also, note that the initial string s is never modified by any
     * query.)
     * 
     * 
     * 
     * Example :
     * 
     * Input: s = "abcda", queries = [[3,3,0],[1,2,0],[0,3,1],[0,3,2],[0,4,1]]
     * Output: [true,false,false,true,true]
     */

    //thinking process:O(n)/O(n) TODO: add the DP solution
    //the problem is to say: given string s and integer 2D array as queries
    //query[i] =[l, r, k], k means the count of replace chars in substring s[l,r]
    //if s[l,r]are palndirom then return true, else false.
    //note, the substring can be arranged. 
    
    //so it is easy to thought the brute force, O(n^2)/O(n)
    //but we can use prefixsum to reduce to O(n)/O(n)
    public List<Boolean> canMakePaliQueries1(String s, int[][] queries) {
        List<Boolean> ans = new ArrayList<>();
        int[][] cnt = new int[s.length() + 1][26];
        for (int i = 0; i < s.length(); ++i) {
            cnt[i + 1] = cnt[i].clone(); // copy previous sum.
            ++cnt[i + 1][s.charAt(i) - 'a'];
        }
        for (int[] q : queries) {
            int sum = 0;
            for (int i = 0; i < 26; ++i) {
                sum += (cnt[q[1] + 1][i] - cnt[q[0]][i]) % 2;
            }
            ans.add(sum / 2 <= q[2]);
        }
        return ans;
    }

    public List<Boolean> canMakePaliQueries2(String s, int[][] queries) {
        List<Boolean> ans = new ArrayList<>();
        boolean[][] odds = new boolean[s.length() + 1][26]; // odds[i][j]: within range [0...i) of s, if the count of
                                                            // (char)(j + 'a) is odd.
        for (int i = 0; i < s.length(); ++i) {
            odds[i + 1] = odds[i].clone();
            odds[i + 1][s.charAt(i) - 'a'] ^= true;
        }
        for (int[] q : queries) {
            int sum = 0;
            for (int i = 0; i < 26; ++i) {
                sum += (odds[q[1] + 1][i] ^ odds[q[0]][i]) ? 1 : 0; // if the count of (char)(i + 'a') in
                                                                    // substring(q[0], q[1] + 1) is odd.
            }
            ans.add(sum / 2 <= q[2]);
        }
        return ans;
    }

    public List<Boolean> canMakePaliQueries3(String s, int[][] queries) {
        List<Boolean> ans = new ArrayList<>();
        int[] odds = new int[s.length() + 1]; // odds[i]: within range [0...i) of s, the jth bit of odd[i] indicates
                                              // even/odd of the count of (char)(j + 'a').
        for (int i = 0; i < s.length(); ++i)
            odds[i + 1] = odds[i] ^ 1 << s.charAt(i) - 'a';
        for (int[] q : queries)
            ans.add(Integer.bitCount(odds[q[1] + 1] ^ odds[q[0]]) / 2 <= q[2]); // odds[q[1] + 1] ^ odds[q[0]] indicates
                                                                                // the count of (char)(i + 'a') in
                                                                                // substring(q[0], q[1] + 1) is
                                                                                // even/odd.
        return ans;
    }

    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        // nested hashmap to reduce calculation, 1st integer is queries[0], 2nd integer
        // is queries[1]
        HashMap<Integer, HashMap<Integer, Integer>> mem = new HashMap<>();
        List<Boolean> list = new LinkedList<>();
        for (int[] qr : queries) {
            if (qr[2] >= 13) { // in the worst case, substring is "abcd...xyz", we need change 13 of them
                list.add(true);
                continue;
            }
            // record the minChange times to reach palindrome of substring(qr[0],qr[1])
            HashMap<Integer, Integer> indexChangeMap = mem.getOrDefault(qr[0], new HashMap<>());
            int minChange = 0;
            // Condition 1: read minChange when mem contains substring(qr[0],qr[1]);
            if (indexChangeMap.containsKey(qr[1])) {
                minChange = indexChangeMap.get(qr[1]);
                // Condition 2: substring(qr[0],qr[1]) hasn't been calculated
            } else {
                minChange = 0;
                int[] count = new int[26];
                for (int i = qr[0]; i <= qr[1]; i++) {
                    count[s.charAt(i) - 'a']++;
                }
                for (int i = 0; i < 26; i++) {
                    if (count[i] % 2 != 0)
                        minChange++;
                }
                if ((qr[1] - qr[0] - 1) % 2 == 1)
                    minChange = (minChange - 1) / 2;
                else
                    minChange = minChange / 2;
                indexChangeMap.put(qr[1], minChange);
                mem.put(qr[0], indexChangeMap);
            }
            // compare minChange with qr[2]
            if (qr[2] >= minChange)
                list.add(true);
            else
                list.add(false);
        }
        return list;
    }

    // DP
    /*
     * Let us create a DP matrix, where dp[i][j] represents the count of i-th
     * character in the substring str[0...j]. Let us fix an i and see the recursive
     * structure.
     * 
     * Suppose, the j - th character is i, then dp[i][j] = dp[i][j-1] + 1. If the
     * j-th character is not i, then dp[i][j] = dp[i][j-1] If j == 0, dp[i][j] would
     * be one if the first character is equal to the i-th character or else zero.
     * For each query, we find out the count of the each character in the substring
     * str[left,...right] by the simple relation, dp[i][right] - dp[i][left] +
     * (str[left] == i + 'a').
     * 
     * 
     */
}