package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : BulbSwitcher
 * Creator : duqiang
 * Date : Dec, 2017
 * Description : 319. Bulb Switcher
 */
public class BulbSwitcher {
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
