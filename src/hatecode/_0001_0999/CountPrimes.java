package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : CountPrimes
 * Creator : duqiang
 * Date : Nov, 2017
 * Description : 204. Count Primes
 */
public class CountPrimes {
    /**
     * 厄拉多塞筛法，求一组质数，时间复杂度仅有O(nloglogn)
     * 如果从1到n-1分别判断质数，时间复杂度为O(nsqrt(n))）
     * 1   2  3  4  5  6  7  8  9 10
     * 11 12 13 14 15 16 17 18 18 20
     *
     * @param n
     * @return
     */
    // Space O(n), here used a way how to record computing results
    public int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];
        int res = 0;
        for (int i = 2; i < n; i++) {
            if (notPrime[i] == false) {
                res++;
                // 2*2, 2*3, 2*4......
                // 3*2, 3*3, 3*4,
                // 2*j we solve the even numbers, the other half is odd numbers
                // 3* j solved most of them,
                for (int j = 2; i * j < n; j++) {
                    notPrime[i * j] = true;
                }
            }
        }
        return res;
    }

    // here is a ways about
    // Time O(n*sqrt(n)) Space: O(1)
    public int countPrimes2(int n) {
        // edge case
        if (n <= 2) {
            return 0;
        }
        int cnt = 0;
        for (int i = 2; i < n; i++) {
            if (i == 2 || i == 3) {
                cnt += 1;
                continue;
            }
            int num = 0;
            for (int j = 2; j <= (int) Math.sqrt(i) + 1; j++) {
                if (i % j == 0) {
                    num++;
                    break;
                }
            }
            if (num == 0) {
                cnt += 1;
            }
        }
        return cnt;
    }
}
