package leetcode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : NthDigit
 * Creator : duqiang
 * Date : Oct, 2017
 * Description : 400. Nth Digit
 */
public class NthDigit {
    /**
Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...

Note:
n is positive and will fit within the range of a 32-bit signed integer (n < 231).

Example 1:

Input:
3

Output:
3
Example 2:

Input:
11

Output:
0

Explanation:
The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 1
     10 - 99 : 90 * 2 = 180

     13:
     n = 13 - 9 = 4;
     len = 2;
     count = 90
     start = 11

     time : < O(n)
     space : O(1)


     * @param n
     * @return
     */
    /*
    分析
    1,2 ... 8,9, 10, 11, 12 ... 98, 99, 100, 101, 102 ... 998, 999, 1000 ...
    observation: 假设我们要找第2886 个位 (就target数 是998，target bit 是8)
    1~9, 10 ~ 99, 100 ~ 999
    9个 910个 91010个
    9bit 910*2 bit 9 * 10 * 10 * 3 bit

    那么2886 - 9 - 9 * 10 * 2 = 2697 < 9*10*10*3 = 2700
    那么target 就落在了区域3中( 100- 999 )

    我们知道区域3 是由3bit的数组成的
    所以target数是以 100 为起始数，(2697 - 1)/3 = 898 为100以后的数
    target 数 = 100 + 898 = 998
    (2697-1) % 3 = 2 就是 998 的target bit 
    target bit = 998.charAt( 2 ) = 8;

    解题思路
    2886 = 9*1 + 90 * 2 + 2697
    */
    //12345, 
    // 12345 - 9 - 2 * 90 - 3 * 900 = 9546 = n
    // at this time,  len = 4, count = 9000, start = 1000 
    public int findNthDigit(int n) {
        int len = 1;
        // 1-9, 10-99,100-999 so we use 9 as base counter
        long count = 9;
        int start = 1;
        // this is simple templates to locate where is n if n should be in some range
        while (n > len * count) { //判断n落在的区间
            n -= len * count; 
            len += 1;   //len 用来记录target 数的长度
            count *= 10;
            start *= 10;  //循环的时候不用，等会用来重组target 数
        }
        // start  = 1000 + (9456 - 1) / 4 = 1000 + 2363 = 3363
      //减1是因为start 自己算一个数，要把start 从计算中抠掉, start here is 1000, from 1000
        // 1000 is 4 digits, and len = 4 so we need to exlcude 1000
        start += (n - 1) / len; 
        
        String s = Integer.toString(start);
        return s.charAt((n - 1) % len) - '0'; //在target 中的位置
    }
    
    public int findNthDigit2(int n) {
        // For 1, 2 .., 9, return the result directly
        if(n <= 9){
            return n;
        }
        
        int base = 1;

        // Determine the range 
        // 10, 11, ..., 99:  90 * 2 digits in total, base = 2
        // 101, 102, 103, ..., 999:  900 * 3  digits in total, base = 3
        // ...
        while(n > 9 * Math.pow(10, base - 1) * base)
        {
            n = n - 9 * (int)Math.pow(10, base - 1) * base;
            base++;
        }
        
        // Now we should find out which number the answer follows. eg. 
        //if the input is 15, the answer should follow on number "12", that's the variable number for.
      //减1是因为start 自己算一个数，要把start 从计算中抠掉
        int number = (int)Math.pow(10, base - 1) + (n - 1) / base;

        // Then we should find out which specific in the number "12". that's what index for, for input 15, index = 0
        int index = (n - 1) % base;

        // The answer is the index-th digit of the variable number
        return Integer.toString(number).charAt(index) - '0';
    }
}
