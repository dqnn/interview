package hatecode._0001_0999;
public class LongestRepeatingCharacterReplacement {
/*
424. Longest Repeating Character Replacement
Given a string that consists of only uppercase English letters, you can replace any letter in the string with another letter at most k times. Find the length of a longest substring containing all repeating letters you can get after performing the above operations.

Note:
Both the string's length and k will not exceed 104.

Example 1:

Input:
s = "ABAB", k = 2

Output:
4

Explanation:
Replace the two 'A's with two 'B's or vice versa.
*/
    //thinking process:
    
    //the brute-force is to replace A->with another, and each char has such 
    //chance, so it would be n * 2^k status.
    
    //TODO: one improvement is to try burst bollons DP way, one character left is l, right r, so whole will be 
    //l + r + 1, then n-> to be max. 
    
    //dp? dp[i] means 0-i max length of all repeating subarrays
    public int characterReplacement2(String s, int k) {
        if (s == null || s.length() < 1 || k < 0) return 0;
        int len = s.length();
        int[] count = new int[26];
        int start = 0, maxCnt = 0, maxLen = 0;
        for(int end = 0; end < len;end++) {
            maxCnt = Math.max(maxCnt, ++count[s.charAt(end) - 'A']);
            while(end - start + 1 - maxCnt > k) {
                count[s.charAt(start) - 'A']--;
                start ++;
            }
            maxLen = Math.max(maxLen, end - start + 1);
        }
        return maxLen;
        
    }
    //find most frequently character
    private boolean ok(char[] ch, int k, int mid) {
        int[] cnt = new int[26];
        for (int i = 0; i < ch.length; i++) {
            if (i >= mid) cnt[ch[i - mid] - 'A']--;
            
            cnt[ch[i] - 'A']++;
            if (i >= mid - 1) {
                int max = 0;
                //find most frequently char
                for (int j : cnt) max = Math.max(max, j);
                
                if (mid - max <= k) return true;
            }
        }
        return false;
    }

    // very interesting solution,
    public int characterReplacement(String s, int k) {
        if (s.length() == 0 || k >= s.length() - 1)
            return s.length();
        // why?
        int left = 1, right = s.length() + 1;
        char[] ch = s.toCharArray();
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (ok(ch, k, mid)) left = mid;
            else right = mid;
        }
        return left;
    }
}