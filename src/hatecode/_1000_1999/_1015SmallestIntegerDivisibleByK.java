package hatecode._1000_1999;
import java.util.*;
public class _1015SmallestIntegerDivisibleByK {
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
    //1 % K, 11 %K, 1111111%K, so the 0<=remainder <= K -1, if we do not have 0 which means they 
    //have a loop, otherwise return;
    //if we have loop to K  1s, then we do not need continue, because we already have a repeated loop,
    //in this circle, we can double r + 1, but the remainder is the same, so they will repeated again
    
    //learning:
    //1. (a + b) %n = [(a %n) + (b %n)] %n, to use a loop to generate the numbers
    public int smallestRepunitDivByK(int K) {
       //this line can be removed, just for performance improvement 
        Set<Integer> set = new HashSet<>(Arrays.asList(1,3,7,9));
        if (K % 2 == 0 || K % 5 == 0 || !set.contains(K % 10)) return -1;
        int r = 0;
        //(a + b) %n = [(a %n) + (b %n)] %n
        /*
            11%k = (10 % k + 1 % k) % k = ((1 % k) * 10 + 1) % k
            111%k = (100 % k + 10 % k + 1 % k) % k = ((((1 % k) * 10 + 1) % k) * 10 + 1) % k
            1111%k = (1000 % k + 100 % k + 10 % k + 1 % k) % k
        */
        //r is like 1, 11, 111, 1111,.....
        for (int N = 1; N <= K; ++N) {
            r = (r * 10 + 1) % K;
            if (r == 0) return N;
        }
        return -1; 
    }
}