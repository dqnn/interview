package hatecode;
public class RepeatedSubstringPattern {
/*
459. Repeated Substring Pattern
Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

 

Example 1:

Input: "abab"
Output: True
Explanation: It's the substring "ab" twice.
Example 2:

Input: "aba"
Output: False
*/
    //this is has fewest code, O(n)/O(n)
    //we double the input string, and we remove the first and last character to detect
    //original string can repeat in the s
    /*
首先证明充分性：
不妨设S = X1,X2...Xn，则X1X2...Xn∈X2...XnX1...Xn-1，令从Xi处开始匹配（2<=i<=n）
则Xi...XnX1...Xi-1 = X1X2...Xn，令Xi...Xn = a， X1...Xi-1 = b，则ab = ba，则a = b，所以S = aa，所以S可由a重复两次得到。
必要性类似可证。
     */
    public boolean repeatedSubstringPattern_Smart(String str) {
        String s = str + str;
        return s.substring(1, s.length() - 1).contains(str);
    }
    
    //
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        if (n < 2) return false;

        char lastChar = s.charAt(n-1);
        int idx = s.indexOf(lastChar);
        // 思想是： 找到最后一个字符所在的位置，那么如果是pattern，则一定有
        // n％（idx＋1）＝＝ 0。 那么pattern应该是从0到index位置的子串。
        while (idx >=0 && idx < n-1) {
            if (n % (idx+1) == 0) {
                String pattern = s.substring(0, idx+1);
                if (foundPattern(s, pattern))  return true;
              //否则找下一个出现lastChar的位置
                else idx = s.indexOf(lastChar, idx+1);
            //否则找下一个出现lastChar的位置
            } else idx = s.indexOf(lastChar, idx+1);
        }
        return false;  
    }
        
    private boolean foundPattern(String s, String pattern) {
        int start = pattern.length();
        int cur = start;
        while (cur < s.length()) {
            if (!pattern.equals(s.substring(cur, cur + start))) {
                return false;
            }
            cur += cur;
        }
        return true;
    }
}