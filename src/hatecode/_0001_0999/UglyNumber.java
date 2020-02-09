package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : UglyNumber
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 263. Ugly Number
 */
public class UglyNumber {
    /**
Write a program to check whether a given number is an ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.

Example 1:

Input: 6
Output: true
Explanation: 6 = 2 × 3
Example 2:

Input: 8
Output: true
Explanation: 8 = 2 × 2 × 2
Example 3:

Input: 14
Output: false 
Explanation: 14 is not ugly since it includes another prime factor 7.
Note:

1 is typically treated as an ugly number.
Input is within the 32-bit signed integer range: [−231,  231 − 1].

     Note that 1 is typically treated as an ugly number.

     time : O(n)
     space : O(1)

     * @param num
     * @return
     */
    // thinking process: we only think about num is 2, 3, 5 numbers, so
    // firstly we thinking about edge case, then we use 3 while loop to make sure they can be 
    // % 2, 3,5, last check it equals 1 or not. 
    
    //this is math problem
    public boolean isUgly(int num) {
        if (num == 1) return true;
        if (num == 0) return false;
        while (num % 2 == 0) num /= 2;
        while (num % 3 == 0) num /= 3;
        while (num % 5 == 0) num /= 5;
        return num == 1;
    }

    public boolean isUgly2(int num) {
        for (int i = 2; i < 6 && num > 0; i++) {
            while (num % i == 0) {
                num /= i;
            }
        }
        return num == 1;
    }
}
