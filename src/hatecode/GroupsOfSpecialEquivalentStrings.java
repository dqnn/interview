package hatecode;

import java.util.*;
public class GroupsOfSpecialEquivalentStrings {
/*

893. Groups of Special-Equivalent Strings
You are given an array A of strings.

Two strings S and T are special-equivalent if after any number of moves, S == T.

A move consists of choosing two indices i and j with i % 2 == j % 2, and swapping S[i] with S[j].

Now, a group of special-equivalent strings from A is a non-empty subset S of A such that any string not in S is not special-equivalent with any string in S.

Return the number of groups of special-equivalent strings from A.

 

Example 1:

Input: ["a","b","c","a","c","c"]
Output: 3
*/
    //thinking process: 
    //可以对一个字符串的所有奇数位置或者偶数位置进行任意的调换顺序。
    //如果两个字符串在经历了上面的操作之后，可以做到完全相等，那么就属于题目中的一个组。现在就要我们求最终分为几个组。
    
    
    public int numSpecialEquivGroups(String[] A) {
        Set<String> visited = new HashSet<>();
        for (String str: A) {
            int[] count = new int[52];
            for (int i = 0; i < str.length(); ++i) count[str.charAt(i) - 'a' + 26 * (i % 2)]++;
            visited.add(Arrays.toString(count));
        }
        return visited.size();
    }
}