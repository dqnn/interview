package hatecode._0001_0999;

import java.util.Arrays;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : NumberofDigitOne
 * Creator : professorX
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

     http://blog.csdn.net/xudli/article/details/46798619
     intuitive: 每10个数, 有一个个位是1, 每100个数, 有10个十位是1, 每1000个数, 有100个百位是1.  
     做一个循环, 每次计算单个位上1得总个数(个位,十位, 百位).  
     例子:
     以算百位上1为例子:   假设百位上是0, 1, 和 >=2 三种情况:
     case 1: n=3141092, a= 31410, b=92. 计算百位上1的个数应该为 3141 *100 次.
     case 2: n=3141192, a= 31411, b=92. 计算百位上1的个数应该为 3141 *100 + (92+1) 次.
     case 3: n=3141592, a= 31415, b=92. 计算百位上1的个数应该为 (3141+1) *100 次.

     Summary: 
     (a + 8) / 10 * m + (a % 10 == 1) * (b + 1);
     time : O(logn)
     space : O(1)

     * @param n
     * @return
     */
    
     /*
      * thinking process: O()

      the problem is to say, given one integer n, return count of "1" in all integers which <=n. 
      
    111: 
      [[36, -1, -1], [20, 16, -1], [1, 11, 5]] without !isLimit

      [[-1, -1, -1], [20, -1, -1], [1, 11, -1]] with !Limit





      */
    // integer string fromat, like "111"
    char s[];
    //dp[i][j] means for position i -> n, the count  equals j with 1 from 0->i-1,  for example if 111, 

    /*
     * _11, dp[][]
     * 
     */
    int dp[][];
    public int countDigitOne_universal_soluion(int n) {
        s = Integer.toString(n).toCharArray();
        var m = s.length;
        dp = new int[m][m];
        for (var i = 0; i < m; i++) Arrays.fill(dp[i], -1);
        System.out.println(Arrays.deepToString(dp));
        int res = f(0, 0, true);
        return res;
    }
 
    /*
     * 函数f(i: int, cnt1: int, is_limit: bool) 
     * 表示在第i位前填了cnt1个1的前提下，总共有多少个1，
     * 所以我们的目标是f(0,0,true)，表示第0位前填了0个1，这样一个前提。 同时，
     * 在i == m时，f(i,cnt,is_limit)的前提是所有位数都已经填好了数字，并且有cnt个1，所以此时cnt就是总共1的数量
     */
    int f(int i, int cnt1, boolean isLimit) {
        if (i == s.length) return cnt1;
        if (!isLimit && dp[i][cnt1] >= 0) return dp[i][cnt1];
        var res = 0;
        for (int d = 0, up = isLimit ? s[i] - '0' : 9; d <= up; ++d) // 枚举要填入的数字 d
            res += f(i + 1, cnt1 + (d == 1 ? 1 : 0), isLimit && d == up);
        /*
         * 下面代码中 Java/C++/Go 只需要记忆化 (i,cnt 1 ​ ) 这个状态，因为： 
         * 对于一个固定的 (i,cnt1​)，这个状态受到 isLimit isLimit 的约束在整个递归过程中至多会出现一次，没必要记忆化。 
         * 另外，如果只记忆化(i,cnt1)， dp dp 数组的含义就变成在不受到约束时的合法方案数，所以要在 !isLimit 成立时才去记忆化。
         * for example: 
         */
        if (!isLimit) dp[i][cnt1] = res;
        return res;
    }
    
    
    
    
    
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

(1) xyz * 1000                         if d == 0
(2) xyz * 1000 + abc + 1               if d == 1
(3) xyz * 1000 + 1000 =(xyz+1)*1000    if d > 1
iterate through all digits and sum them all will give the final answer
     */
    public static int countDigitOne2(int n) {
        if (n <= 0) return 0;
        int q = n, x = 1, res = 0;
        do {
            // digit is the left on second digit of n from right
            int d = q % 10;
            q /= 10;
            res += q * x;
            //here n % x is to get the digit on 
            //10, 100, 1000...  for example, n = 22, x = 10,
            //then we will get 2 + 1, because 
            if (d == 1) res += n % x + 1;
            if (d > 1) res += x;
            x *= 10;
        } while (q > 0);
        return res;
    }
    
    public static void main(String[] args) {
        System.out.println(countDigitOne2(22));
    }
}
