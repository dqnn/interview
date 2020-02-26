package hatecode._0001_0999;
import java.util.*;
public class _466CountTheRepetitions {
/*
466. Count The Repetitions
Define S = [s,n] as the string S which consists of n connected strings s. For example, ["abc", 3] ="abcabcabc".

On the other hand, we define that string s1 can be obtained from string s2 if we can remove some characters from s2 such that it becomes s1. For example, “abc” can be obtained from “abdbec” based on our definition, but it can not be obtained from “acbbe”.

You are given two non-empty strings s1 and s2 (each at most 100 characters long) and two integers 0 ≤ n1 ≤ 106 and 1 ≤ n2 ≤ 106. Now consider the strings S1 and S2, where S1=[s1,n1] and S2=[s2,n2]. Find the maximum integer M such that [S2,M] can be obtained from S1.

Example:

Input:
s1="acb", n1=4
s2="ab", n2=2

Return:
2
*/
    /*
    
How do we know "string s2 can be obtained from string s1"? Easy, use two pointers iterate through s2 and s1. If chars are equal, move both. Otherwise only move pointer1.
We repeat step 1 and go through s1 for n1 times and count how many times can we go through s2.
Answer to this problem is times go through s2 divide by n2.
    */
    
    //the problem is to say how many times n2* s2= S2 in n1*s1, max showed up time.  sub sequence, not substring
    //
    //we comare two mini strings, every time each went to the end, then rewind, we will count
    //how many times s2 appeared in s1, then we would just simply count2/n2;
    public int getMaxRepetitions2(String s1, int n1, String s2, int n2) {
        char[] array1 = s1.toCharArray(), array2 = s2.toCharArray();
        int count1 = 0, count2 = 0, i = 0, j = 0;
        
        while (count1 < n1) {
            if (array1[i] == array2[j]) {
                j++;
                if (j == array2.length) {
                    j = 0;
                    count2++;
                }
            }
            i++;
            if (i == array1.length) {
                i = 0;
                count1++;
            }
        }
        
        return count2 / n2;
    }
    
    //best performance
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        if (s1 == null || s2 == null || n1 <= 0 || n2 <= 0) {
            return 0;
        }
        Map<Integer, Integer> posMap = new HashMap<Integer, Integer>(); // key: the rest position of s2  value:the number of s1
        int[] repTimes = new int[102]; // repTimes[i]: nummer of used s1 is i, repetitions times is repTimes[i]
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int len1 = s1.length();
        int len2 = s2.length();
        int s1Num = 1;
        int posInS2 = 0;
        int times = 0;
        while (s1Num <= n1) {
            for (int j = 0; j < len1; j++) {
                if (chars1[j] == chars2[posInS2]) {
                    posInS2++;
                    if (posInS2 == len2) {
                        times++;
                        posInS2 = 0;
                    }
                }
            }
            repTimes[s1Num] = times;
            if (posMap.containsKey(posInS2)) {
                break;
            }
            posMap.put(posInS2, s1Num);
            s1Num++;
        }
        if (s1Num >= n1) {
            return times / n2;
        }
        int k = posMap.get(posInS2);
        int s1NumInLoop = s1Num - k; // s1 num in each loop
        int s2NumInLoop = repTimes[s1Num] - repTimes[k]; // s2 num in each loop
        int repeatCount = (n1 - k) / s1NumInLoop;
        int n = repeatCount * s2NumInLoop;
        n += repTimes[k + (n1 - k) % s1NumInLoop];
        return n / n2;
    }
}