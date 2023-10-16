package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ImplementstrStr
 * Creator : professorX
 * Date : July, 2018
 * Description : TODO
 * Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2
Example 2:

Input: haystack = "aaaaa", needle = "bba"
Output: -1
Clarification:

What should we return when needle is an empty string? This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
 */
public class _028ImplementstrStr {
    /**
     * 28. Implement strStr()

     time : O(n^2)
     space : O(1)
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr2(String haystack, String needle) {
      //edge case
        if (haystack == null || needle == null) {
            return -1;
        }
        if (needle.length() == 0) return 0;
        // note i <= becz i + needle.length() = haystack.length
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (haystack.substring(i, i + needle.length()).equals(needle)) return i;
        }
        return -1;
    }
    /*
next数组的含义就是一个固定字符串的最长前缀和最长后缀相同的长度。

比如：abcjkdabc，那么这个数组的最长前缀和最长后缀相同必然是abc。 
cbcbc，最长前缀和最长后缀相同是cbc。 
abcbc，最长前缀和最长后缀相同是不存在的。
对于目标字符串ptr，ababaca，长度是7，所以next[0]，next[1]，next[2]，
next[3]，next[4]，next[5]，next[6]分别计算的是 
a，ab，aba，abab，ababa，ababac，ababaca的相同的最长前缀和最长后缀的长度。
由于a，ab，aba，abab，ababa，ababac，ababaca的相同的最长前缀和最长后缀是
“”，“”，“a”，“ab”，“aba”，“”，“a”,所以next数组的值是[-1,-1,0,1,2,-1,0]，
这里-1表示不存在，0表示存在长度为1，2表示存在长度为3。这是为了和代码相对应。
     */
    //KMP
    public int strStr(String s, String q) {
        if (q.length() == 0) return 0;
        if (q.length() > s.length() || s.length() == 0) return -1;
        char[] sc = q.toCharArray();
        char[] qc = s.toCharArray();

        //build partial match table
        int[] pmt = new int[q.length()];
        pmt[0] = -1;
        for (int i = 1, k = -1; i < sc.length; i++) {
            while (k > -1 && sc[k + 1] != sc[i]) k = pmt[k];
            pmt[i] = sc[k + 1] == sc[i] ? ++k : k;
        }
        for (int i = 0, k = -1; i < qc.length; i++) {
            while (k > -1 && sc[k + 1] != qc[i]) k = pmt[k];
            if (sc[k + 1] == qc[i] && ++k == sc.length - 1) return i - k;
        }
        return -1;
    }
}
