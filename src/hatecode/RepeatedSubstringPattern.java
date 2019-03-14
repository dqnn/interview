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
    
    //
    public boolean repeatedSubstringPattern_Smart(String str) {
        String s = str + str;
        return s.substring(1, s.length() - 1).contains(str);
    }
    
    //
    public boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        if (len < 2) {
            return false;
        }
        char lastChar = s.charAt(len-1);
        int index = s.indexOf(lastChar);
        // 思想是： 找到最后一个字符所在的位置，那么如果是pattern，则一定有
        // len％（index＋1）＝＝ 0。 那么pattern应该是从0到index位置的子串。
        while (index >=0 && index < len-1) {
            if (len % (index+1) == 0) {
                String pattern = s.substring(0, index+1);
                if (foundPattern(s, pattern)) {
                    return true;
                } else {
                    //否则找下一个出现lastChar的位置
                    index = s.indexOf(lastChar, index+1);
                }
            } else {
                //否则找下一个出现lastChar的位置
                index = s.indexOf(lastChar, index+1);
            }
        }
        return false;  
    }
        
    private boolean foundPattern(String s, String pattern) {
        int fromIndex = pattern.length();
        int n = fromIndex;
        while (fromIndex < s.length()) {
            if (!pattern.equals(s.substring(fromIndex, fromIndex + n))) {
                return false;
            }
            fromIndex += fromIndex;
        }
        return true;
    }
}