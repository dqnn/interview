package hatecode;
import java.util.*;
public class SmallestIntegerDivisibleByK {
/*
1015. Smallest Integer Divisible by K
Given a positive integer K, you need find the smallest positive integer N such that N is divisible by K, and N only contains the digit 1.

Return the length of N.  If there is no such N, return -1.

Example 1:

Input: 1
Output: 1
*/
    //thinking process: 
    //given a K, find a number which n % K == 0 && N = 1, 11, 111, all are 1 digits
    //so
    public int smallestRepunitDivByK(int K) {
       //this line can be removed, just for performance improvement
        Set<Integer> set = new HashSet<>(Arrays.asList(1,3,7,9));
        if (K % 2 == 0 || K % 5 == 0 || !set.contains(K % 10)) return -1;
        
        int r = 0;
        for (int N = 1; N <= K; ++N) {
            r = (r * 10 + 1) % K;
            if (r == 0) return N;
        }
        return -1; 
    }
}