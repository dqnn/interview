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
 */


 /*
  * interview friendly O(m + n)/O(n), m = s.length(), n = q.length();

  the problem is to say: given two strings s and q, return the first match index,
  
  for example 
  "cabcdeabrdabcdeabcd"
            "abcdeabcd"

return 10

KMP, there are two questions around KMP, 
1. why we need to use KMP
2. How?

1. if we do not use KMP, the complexity will be O(mn)
 i
"cabcdeabrdabcdeabcd"
"abcdeabcd"
 j
         i
"cabcdeabrdabcdeabcd"
 "abcdeabcd"
         j

   i
"cabcdeabrdabcdeabcd"
  "abcdeabcd"
   j

you can see i need to back to next stop where j should from q again, this result in O(mn) complexity 

How can we avoid?

In above step 2, i would never fallback, it just keep rolling, j return to some place which it has same prefix and suffix in query string itself
         i
"cabcdeabrdabcdeabcd"
      "abcdeabcd"
         j


we need to generate an array which could instruct how to move j if i and j does not match, it called LPS(longest prefix sufffix ) array,
how to generate LPS?

1. initialize i = 0, j = 1
ij
abcdeabcd

2. if char(i) == char(j), which means we found one prefix and suffix match, then lps[j] = i+1, then i++, j++
   else not match, then 
   a. if i > 0. then i = lps[i-1], because we do not match, we have to fallback to previous location
      else j++

abcdeabcd
000001234

abcdaabcd
000011234


  */
 public int strStr_KMP(String s, String q) {
    if(s == null || q== null) return -1;
    
    int[] lps = helper(q);
    
    int i = 0, j = 0;
    
    while(i < s.length()) {
        if (s.charAt(i) == q.charAt(j)) {
            if (j == q.length() -1) {
                return i - q.length() + 1;
            } 
            
            i++;
            j++;
        } else {
            if(j > 0) j = lps[j-1];
            else i++;
        }
    }
    
    
    return -1;
    
}


private int[] helper(String q) {
    int i = 0, j = 1;
    int n = q.length();
    int[] lps = new int[n];
    while(j < n) {
        if(q.charAt(i) == q.charAt(j)) {
            lps[j] = i + 1;
            i++;
            j++;
        } else {
            if(i == 0) {
                j++;
            } else i = lps[i-1];
        }
    }
    
    return lps;
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
    public static int strStr(String s, String q) {
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


    public static void main(String[] args) {
        System.out.println(strStr("AABAABAAA", "BAA"));
    }
}
