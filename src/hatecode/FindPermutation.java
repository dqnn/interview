package hatecode;
import java.util.*;
public class FindPermutation {
/*
484. Find Permutation
By now, you are given a secret signature consisting of character 'D' and 'I'. 'D' represents a decreasing relationship between two numbers, 'I' represents an increasing relationship between two numbers. And our secret signature was constructed by a special integer array, which contains uniquely all the different number from 1 to n (n is the length of the secret signature plus 1). For example, the secret signature "DI" can be constructed by array [2,1,3] or [3,1,2], but won't be constructed by array [3,2,4] or [2,1,3,4], which are both illegal constructing special string that can't represent the "DI" secret signature.

On the other hand, now your job is to find the lexicographically smallest permutation of [1, 2, ... n] could refer to the given secret signature in the input.

Example 1:
Input: "I"
Output: [1,2]

Input: "DI"
Output: [2,1,3]
*/
    //[2,1,3]
    //up =[1, 1, 2],down =[1,2, 2]
    // 
    public int[] findPermutation(String s) {
        if (s == null || s.length() < 1) return new int[0];
        
        int n = s.length();
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i<=n;i++) {
            if (i == n || s.charAt(i) == 'I') {
                for(int j = i+1, temp = res.size(); j>temp;j--) {
                    res.add(j);
                }
            }
        }
        return res.stream().mapToInt(x->x).toArray();
    }
}