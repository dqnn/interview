package hatecode;

import java.util.*;
public class _1044LongestDuplicateSubstring {
/*
1044. Longest Duplicate Substring

Given a string S, consider all duplicated substrings: (contiguous) substrings of S that occur 2 or more times.  (The occurrences may overlap.)

Return any duplicated substring that has the 
longest possible length.  (If S does not have a 
duplicated substring, the answer is "".)

 

Example 1:

Input: "banana"
Output: "ana"
*/
    //thinking process:O(nlgn)/O(n)
    
    //given a string s, mainly to find the longest substring which
    //repeat 2 or more times, overlap is allowed
    //for example banana, we can see ana repeat 2 times even there overlap
    
    //so we use binary search to find the longest repeated substring, 
    //
    public String longestDupSubstring(String S) {
        // edge case
        if (S == null) {
            return null;
        }
        // binary search the max length
        int l = 0;
        int r = S.length() - 1;
        int mid;
        //if we find repeated substring then we move mid ->right, so to find a longer
        //substring, if we cannot which means we should lower down the length
        while (l < r - 1) {
            mid = (l + r) / 2;
            if (searchForLength(S, mid) != null) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        String str = searchForLength(S, r);
        if (str != null) {
            return str;
        } else {
            return searchForLength(S, l);
        }
    }
    //search substring from [0, len]
    //we use hash value as string value, we use a function to calc
    //the positive integer hash value 
    String searchForLength(String str, int len) {
        // rolling hash method
        if (len == 0) {
            return "";
        } else if (len >= str.length()) {
            return null;
        }
        Map<Long, List<Integer>> map = new HashMap<>();    // hashcode -> list of all starting idx with identical hash
        long p = (1 << 31) - 1;  // prime number
        long base = 256;
        long hash = 0;
        //calculate the string hash
        for (int i = 0; i < len; ++i) {
            hash = (hash * base + str.charAt(i)) % p;
        }
        long multiplier = 1;
        for (int i = 1; i < len; ++i) {
            multiplier = (multiplier * base) % p;
        }
        // first substring
        //TODO, just use string.hash() to rewrite this part code
        
        //this is like bloom filter
        List<Integer> idxList = new ArrayList<Integer>();
        idxList.add(0);
        map.put(hash, idxList);
        // other substrings
        int s = 0;
        int e = len;
        while (e < str.length()) {
            hash = ((hash + p - multiplier * str.charAt(s++) % p) * base + str.charAt(e++)) % p;
            idxList = map.get(hash);
            if (idxList == null) {
                idxList = new ArrayList<Integer>();
                map.put(hash, idxList);
            } else {
                for (int i0: idxList) {
                    if (str.substring(s, e).equals(str.substring(i0, i0 + len))) {
                        return str.substring(i0, i0 + len);
                    }
                }
            }
            idxList.add(s);
        }
        return null;
    }
    
    //fastest solution in lc, just for reference
    private class BSTNode {
        int low, high;
        BSTNode left, right;

        BSTNode(int low, int high) {
            this.low = low;
            this.high = high;
        }
    }

    public String longestDupSubstring_Most_Efficient(String S) {
        char[] s = S.toCharArray();
        int len = s.length;
        s = Arrays.copyOf(s, len + 1);
        s[len] = '*';

        int[] dp = new int[len], chs = new int[26];
        for (int i = 0; i < len; i++) {
            dp[i] = i;
            chs[s[i] - 'a'] += 1;
        }

        for (int i = 0; i < 26; i++) {
            if (chs[i] == len) {
                return S.substring(1);
            }
        }

        BSTNode[] trees = new BSTNode[len];
        int[] r = helper(s, dp, 0, len, trees);
        return String.valueOf(s, r[0], r[1]);
    }

    private int[] helper(char[] s, int[] dp, int l, int r, BSTNode[] trees) {
        int pos = 0, max = -1;
        while (l < r) {
            int nl = partitionAndMoveForward(s, dp, l, r);
            if (nl - l == 1) {
                if (0 > max) {
                    pos = dp[l] - 1;
                    max = 0;
                }
            } else if (nl - l == 2) {
                int count = 1, a = dp[l], b = dp[l + 1];
                if (s[a] == s[b]) {
                    int low = Math.min(a, b), high = Math.max(a, b);
                    count = searchAndUpdate(s, low - 1, high - low, trees);
                }
                if (count > max) {
                    pos = dp[l] - 1;
                    max = count;
                }
            } else {
                int[] m = helper(s, dp, l, nl, trees);
                if (m[1] + 1 > max) {
                    pos = m[0] - 1;
                    max = m[1] + 1;
                }
            }
            l = nl;
        }
        return new int[]{pos, max};
    }

    private int searchAndUpdate(char[] s, int v, int gap, BSTNode[] trees) {
        BSTNode p = trees[gap];
        while (p != null) {
            if (v < p.high && v >= p.low) {
                return p.high - v;
            }
            if (v >= p.high && p.right != null) {
                p = p.right;
                continue;
            } else if (v < p.low && p.left != null) {
                p = p.left;
                continue;
            }
            break;
        }

        int len = s.length, a = v, b = v + gap;
        while (b < len && s[a] == s[b]) {
            a += 1;
            b += 1;
        }
        int high = a;
        a = v;
        b = v + gap;
        while (a >= 0 && s[a] == s[b]) {
            a -= 1;
            b -= 1;
        }
        int low = a + 1;

        if (p == null) {
            trees[gap] = new BSTNode(low, high);
        } else if (v >= p.high) {
            p.right = new BSTNode(low, high);

        } else {
            p.left = new BSTNode(low, high);
        }

        return high - v;
    }

    private int partitionAndMoveForward(char[] s, int[] dp, int l, int r) {
        char target = s[dp[l]];
        while (l < r) {
            if (s[dp[l]] == target) {
                dp[l] += 1;
                l += 1;
                continue;
            }

            if (s[dp[r - 1]] != target) {
                r -= 1;
                continue;
            }

            int tmp = dp[l];
            dp[l] = dp[r - 1] + 1;
            dp[r - 1] = tmp;
            l += 1;
            r -= 1;
        }
        return l;
    }
}