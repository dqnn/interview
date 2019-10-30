package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : NumberofDigitOne
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : 233. Number of Digit One
 */
public class _233NumberofDigitOne {
    /**
     * Given an integer n, count the total number of digit 1 appearing in all 
     * non-negative integers less than or equal to n.

     For example:
     Given n = 13,
     Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.

     10 : 1    个位
     100 : 10  十位
     1000: 100 百位

     例子:
     以算百位上1为例子:   假设百位上是0, 1, 和 >=2 三种情况:
     case 1: n=3141092, a= 31410, b=92. 计算百位上1的个数应该为 3141 *100 次.
     case 2: n=3141192, a= 31411, b=92. 计算百位上1的个数应该为 3141 *100 + (92+1) 次.
     case 3: n=3141592, a= 31415, b=92. 计算百位上1的个数应该为 (3141+1) *100 次.

     http://blog.csdn.net/xudli/article/details/46798619
     intuitive: 每10个数, 有一个个位是1, 每100个数, 有10个十位是1, 每1000个数, 有100个百位是1.  
     做一个循环, 每次计算单个位上1得总个数(个位,十位, 百位).  

     time : < O(n)
     space : O(1)

     * @param n
     * @return
     */
    // still not full understand， pure math problem
    public int countDigitOne(int n) {
        int res = 0;
        for (long m = 1; m <= n; m *= 10) {
            long a = n / m;
            long b = n % m;
            res += (a + 8) / 10 * m;
            if (a % 10 == 1) res += b + 1;
        }
        return res;
    }
    
    /*
     * The idea is to calculate occurrence of 1 on every digit. 
     * There are 3 scenarios, for example

if n = xyzdabc
and we are considering the occurrence of one on thousand, 
it should be:

(1) xyz * 1000                     if d == 0
(2) xyz * 1000 + abc + 1           if d == 1
(3) xyz * 1000 + 1000              if d > 1
iterate through all digits and sum them all will give the final answer
     */
    public static int countDigitOne2(int n) {
        if (n <= 0) return 0;
        int q = n, x = 1, res = 0;
        do {
            // digit is the left on second digit of n from right
            int digit = q % 10;
            q /= 10;
            res += q * x;
            //here n % x is to get the digit on 
            //10, 100, 1000... 
            if (digit == 1) res += n % x + 1;
            if (digit > 1) res += x;
            x *= 10;
        } while (q > 0);
        return res;
    }
    
    public static void main(String[] args) {
        System.out.println(countDigitOne2(11));
    }
}
