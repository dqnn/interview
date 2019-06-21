package hatecode;

import java.util.*;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : HappyNumber
 * Creator : duqiang
 * Date : Nov, 2017
 * Description : 202. Happy Number
 */
public class HappyNumber {
    /**
     * Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: 
Starting with any positive integer, 
replace the number by the sum of the squares of its digits, 
and repeat the process until the number 
equals 1 (where it will stay), or it loops endlessly 
in a cycle which does not include 1. Those numbers 
for which this process ends in 1 are happy numbers.

Example: 

Input: 19
Output: true
Explanation: 
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1

     time : 不知道
     space : O(n)
     
     * @param n
     * @return
     */
    //this is simulation to the problem,we use a set to determine whether we tried this number before or
    //it is really neat
    public boolean isHappy(int n) {
        if(n == 1) return true;
        Set<Integer> set = new HashSet<>();
        int sum = 0, remain = 0;
        
        while(set.add(n)) {
            while(n > 0) {
                remain = n % 10;
                sum += remain * remain;
                n /=10;
            }
            if(sum == 1) return true;
            n = sum;
            sum = 0;
        }
        return false;
    }
}
