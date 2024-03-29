package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.List;

/*
 * Author professorX
 * Date July 2018
 * 869. Reordered Power of 2
 * Starting with a positive integer N, we reorder the digits in any order (including the original order) such that 
 * the leading digit is not zero.

Return true if and only if we can do this in a way such that the resulting number is a power of 2.

 

Example 1:

Input: 1
Output: true
Example 2:

Input: 10
Output: false
Example 3:

Input: 16
Output: true
Example 4:

Input: 24
Output: false
Example 5:

Input: 46
Output: true
 

Note:

1 <= N <= 10^9
 */
public class _869ReorderedPowerOf2 {
    public boolean reorderedPowerOf2(int N) {
        //edge case
        if (N < 1) {
            return false;
        }
        if (N == 1) {
            return true;
        }

        String m = String.valueOf((long)N);
        List<Character> perm = new ArrayList<>();
        List<Long> permuRes = new ArrayList<>();
        helper(permuRes, m.toCharArray(), 0, m.length());
        for(Long le : permuRes) {
            if (isPower(le)) {
                return true;
            }
        }
        
        return false;
    }
    
    public void helper(List<Long> res, char[] perm, int start, int end) {
        if (start >= end) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < perm.length; i++) {
                if (i == 0 && perm[0] == '0') {
                    return;
                }
                sb.append(perm[i]);
            }
            res.add(Long.valueOf(sb.toString()));
            return;
        }
        
        for(int i = start; i< end; i++) {
            swap(perm, i, start);
            helper(res, perm, start + 1, end);
            swap(perm, start, i);
        }
    }
    
    public void swap(char[] str, int i, int j) {
        char t = str[i];
        str[i] = str[j];
        str[j] = t;
    }
    
    public boolean isPower(long l) {
        return l != 0 && ((l & (l-1)) == 0);
    }
}