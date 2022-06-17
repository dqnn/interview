package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : BulbSwitcher
 * Creator : professorX
 * Date : Dec, 2017
 * Description : 319. Bulb Switcher
 */
public class _319BulbSwitcher {
    /**
     * There are n bulbs that are initially off. You first turn on all the bulbs.
     * Then, you turn off every second bulb. On the third round, you toggle every third bulb
     * (turning on if it's off or turning off if it's on). For the ith round, you toggle every i bulb. For the nth round,
     * you only toggle the last bulb. Find how many bulbs are on after n rounds.

     Example:

     Given n = 3.

     At first, the three bulbs are [off, off, off].
     After first round, the three bulbs are [on, on, on].
     After second round, the three bulbs are [on, off, on].
     After third round, the three bulbs are [on, off, off].

     So you should return 1, because there is only one bulb is on.

     time : O(1)
     space : O(1)


     * @param n
     * @return
     */
/*
对于第n个灯泡，只有当次数是n的因子的之后，才能改变灯泡的状态，即n能被当前次数整除，
比如当n为36时，它的因数有(1,36), (2,18), (3,12), (4,9), (6,6), 
可以看到前四个括号里成对出现的因数各不相同，括号中前面的数改变了灯泡状态，后面的数又变回去了，
等于灯泡的状态没有发生变化，只有最后那个(6,6)，在次数6的时候改变了一次状态，
没有对应其它的状态能将其变回去了，所以灯泡就一直是点亮状态的。所以所有平方数都有这么一个相等的因数对，
即所有平方数的灯泡都将会是点亮的状态。

那么问题就简化为了求1到n之间完全平方数的个数，我们可以用force brute来比较从1开始的完全平方数和
n的大小
 */
    // so thinking about this way 10= 1x10 or 2x5, 1 will be ignored, so there are 3
    // times
    // toggle, so we want to know the even time of toggle, only sqrt number
    // how many sqrt numbers are there in n
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }

    // bruth-force , this will time-out
    public int bulbSwitch2(int n) {
        // edge case
        if (n < 0) {
            return 0;
        }

        int[] nums = new int[n];
        for (int i = 2; i < n / 2 + 1; i++) {
            for (int j = 0; j <= n / 2; j++) {
                if ((j + 1) % i == 0) {
                    nums[j] = nums[j] == 0 ? 1 : 0;
                }
            }

        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0)
                res++;
        }

        return res;
    }
}
