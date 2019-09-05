package hatecode;

import java.util.*;
public class NumbersWithSameConsecutiveDifferences {
/*
967. Numbers With Same Consecutive Differences
Return all non-negative integers of length N such that the absolute difference between every two consecutive digits is K.

Note that every number in the answer must not have leading zeros except for the number 0 itself. For example, 01 has one leading zero and is invalid, but 0 is valid.

You may return the answer in any order.

 

Example 1:

Input: N = 3, K = 7
Output: [181,292,707,818,929]
*/
    //thinking process: given N as length of digits, K as diff between 2 digits in a number
    //return all possible numbers, see above examples, 181->1,8,1->|1-8| = |8-1|
    
    //O(2^n)/O(2^n) every number we have most 2 choices for that 
    //digit
    
    //brute force solution
    public int[] numsSameConsecDiff(int N, int K) {
        if(N <= 0) return new int[0];
        
        if(N == 1) return new int[]{0,1,2,3,4,5,6,7,8,9};
        List<Integer> cur = Arrays.asList(1,2,3,4,5,6,7,8,9);
        //we use cur as first digit, then we start from 2, 
        //and for each number in cur, we only get last digit and make it + K or -K, so we have a new array, 
        //cur2, 
        for(int i =2;i <= N; i++) {
            List<Integer> cur2 = new ArrayList<>();
            for(int x : cur) {
                int y = x % 10;
                if(y + K < 10) {
                    cur2.add(x * 10 + y + K);
                }
                //we get a new digit only when previous is bigger than current
                if(y- K >=0 && K!=0) {
                    cur2.add(x*10 + y -K);
                }
            }
            cur = cur2;
        }
        
        return cur.stream().mapToInt(e->e).toArray();
        
    }
    
    
    
    
}