package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SingleNumberII
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 137. Single Number II
 */
public class _137SingleNumberII {
    /**
     * Given an array of integers, every element appears three times except for one,
     *  which appears exactly once.
     * Find that single one

     0 -> 1 -> 2 -> 0
     00 -> 01 -> 10 -> 00
     00 -> 10 -> 01 -> 00

     ones twos
      0     0
     0 -> 1 0 -> 0
     1 -> 0 0 -> 1
     0 -> 0 1 -> 0

     1, 存入ones里
     2，清空ones 存入twos
     3，twos进行清空

     time : O(n)
     space : O(1)


     * @param nums
     * @return
     */
    //thinking process: 
    //array, one number appear once whilter others appear 3 times, find the one
    
/*
我们把数组中数字的每一位累加起来对3取余，剩下的结果就是那个单独数组该位上的数字，由于我们累加的过程都要对3取余，
那么每一位上累加的过程就是0->1->2->0，换成二进制的表示为00->01->10->00，那么我们可以写出对应关系：

00 (+) 1 = 01

01 (+) 1 = 10

10 (+) 1 = 00 ( mod 3)

那么我们用ab来表示开始的状态，对于加1操作后，得到的新状态的ab的算法如下：

b = b xor r & ~a;

a = a xor r & ~b;

我们这里的ab就是上面的三种状态00，01，10的十位和各位，刚开始的时候，a和b都是0，当此时遇到数字1的时候，
b更新为1，a更新为0，就是01的状态；再次遇到1的时候，b更新为0，a更新为1，就是10的状态；
再次遇到1的时候，b更新为0，a更新为0，就是00的状态，相当于重置了；最后的结果保存在b中。明白了上面的分析过程，就能写出代码如下；
 */
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for (int i = 0; i < nums.length; i++) {
            // & ~twos means + twos
            ones = (ones ^ nums[i]) & ~twos;
            twos = (twos ^ nums[i]) & ~ones;
        }
        return ones;
    }
    
    /** There are only 32 bits for a number in Java. We can traverse all numbers and 
     * count number of 1's in every 
     * bit index (totally 32). If number of 1's in current bit index can be divided by 3, then current bit index 
     * must not be the effective index of that single number. So we only need to find those bit index that have 
     * countBit % 3 != 0, it will be the result single number. */

    public int singleNumber_COOL(int[] nums) {
        int result = 0;
        int occurTime = 3;
        for (int i = 0; i < 32; i++) {
            int countBit = 0;
            int curIndex = 1 << i;
            for (int num : nums) {
                if ((num & curIndex) != 0) {
                    countBit++;
                }
            }
            if (countBit % occurTime != 0) {
                result |= curIndex;
            }
        }
        return result;
    }
}
