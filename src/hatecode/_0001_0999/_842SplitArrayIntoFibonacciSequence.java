package hatecode._0001_0999;

import java.util.*;
public class _842SplitArrayIntoFibonacciSequence {
/*
842. Split Array into Fibonacci Sequence
Given a string S of digits, such as S = "123456579", we can split it into a Fibonacci-like sequence [123, 456, 579].

Formally, a Fibonacci-like sequence is a list F of non-negative integers such that:

0 <= F[i] <= 2^31 - 1, (that is, each integer fits a 32-bit signed integer type);
F.length >= 3;
and F[i] + F[i+1] = F[i+2] for all 0 <= i < F.length - 2.
Also, note that when splitting the string into pieces, each piece must not have extra leading zeroes, except if the piece is the number 0 itself.

Return any Fibonacci-like sequence split from S, or return [] if it cannot be done.

Example 1:

Input: "123456579"
Output: [123,456,579]
*/
    
    //thinking process: O()O()
    
    //the problem is to say: given one string s, you can cut the string into several parts,
    //each part will be a number, the number sequence will be fib sequence, return our answer
    
    //typical dfs,  
    public List<Integer> splitIntoFibonacci(String s) {
        
        List<Integer> res = new ArrayList<>();
        helper(res, s, 0);
        
        return res;
    }
    
    
    public boolean helper(List<Integer> res, String s, int start) {
        if (start == s.length() && res.size() >= 3)
            return true;
        long num = 0;
        for (int i = start; i < s.length(); i++) {
            if (s.charAt(start) == '0' && i > start)
                break;
            num = num * 10 + (s.charAt(i) - '0');
            //pruning, we can remove, only to improve perf
            if (num > Integer.MAX_VALUE || res.size() >= 2 && res.get(res.size() - 1) + res.get(res.size() - 2) < num)
                break;
            //this is normal case, 
            //1 is only 1 integer in current list or no integer
            //2. if already 2 number, then we need to find out more numbers has such character
            if (res.size() <= 1 || res.get(res.size() - 1) + res.get(res.size() - 2) == num) {
                res.add((int) num);
                if (helper(res, s, i + 1)) return true;
                
                res.remove(res.size() - 1);
            }
        }
        return false;
    }
}