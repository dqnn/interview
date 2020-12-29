package hatecode._1000_1999;
public class _1201UglyNumberIII {
/*
1201. Ugly Number III
Write a program to find the n-th ugly number.

Ugly numbers are positive integers which are divisible by a or b or c.

 

Example 1:

Input: n = 3, a = 2, b = 3, c = 5
Output: 4
Explanation: The ugly numbers are 2, 3, 4, 5, 6, 8, 9, 10... The 3rd is 4.
*/
    //thinking process: 
    
    //so return the 3-th number, which can be divised by a || b || c
    //there is two forumla to solve this problem:
    //1. lcm = a*b/gcd
    //2. the set for no dup ugly number is m / a + m / b + m / c - m / lcmab - m / lcmac - m / lcmbc + m / lcmabc
    //so every time if we use m to get how many ugly numbers,compared to n, then we know we should make m bigger or smaller, 
    //every move, n should be closer to mid
    public int nthUglyNumber(int n, int a, int b, int c) {
        long l = 1, r = 2000000000; // 定义左右指针
        long lcmab = lcm(a, b); // a和b的最小公倍数
        long lcmac = lcm(a, c); // a和c的最小公倍数
        long lcmbc = lcm(b, c); // b和c的最小公倍数
        long lcmabc = lcm(lcmab, c); // a和b和c的最小公倍数
        
        while (l < r) { // 开始二分查找
            long m = (l + r) / 2; // 计算mid
            // 计算出1到mid之间丑数的个数
            int count = (int) (m / a + m / b + m / c - m / lcmab - m / lcmac - m / lcmbc + m / lcmabc);
            if (count < n) { // 如果丑数个数小于n
                l = m + 1; // 扩大搜索范围
            } else { // 反之减小搜索范围
                r = m;
            }
        }
        return (int) r;
    }

// 求a和b的最大公约数
// 递归方法，用两数相除的余数与两数较小的数继续递归，
// 直到较小的数字变为0，较大数即是结果。
    long gcd(long a, long b) {
        if (a > b)
            return gcd(b, a);
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

// 求a和b的最小公倍数
// 最小公倍数 = a * b / 最大公约数;
    long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }
}