package hatecode._1000_1999;

import java.util.*;

public class _1033MovingStonesUntilConsecutive {
/*
1033. Moving Stones Until Consecutive
Three stones are on a number line at positions a, b, and c.

Each turn, you pick up a stone at an endpoint (ie., either the lowest or highest position stone), and move it to an unoccupied position between those endpoints.  Formally, let's say the stones are currently at positions x, y, z with x < y < z.  You pick up the stone at either position x or position z, and move that stone to an integer position k, with x < k < z and k != y.

The game ends when you cannot make any more moves, ie. the stones are in consecutive positions.

When the game ends, what is the minimum and maximum number of moves that you could have made?  Return the answer as an length 2 array: answer = [minimum_moves, maximum_moves]

 

Example 1:

Input: a = 1, b = 2, c = 5
Output: [1,2]
*/
    //thinking process: 
/*
不妨设a < b < c，首先分析最小情况：
对于任意三个数，我们总可以在两步之内将其变成连续的三个数，如a,b,c
将a变成b-1，将c变成b+1。
如果a,b,c已经有序，那么最小移动次数是0次
如果a,b,c满足已经有两个数临近或间隔为2，也就是 1,2,x 或 1,3,x的情况，那么最小移动次数是1次
其余情况全为2次

分析最大情况：
因为每次移动之后三个数总要靠近，那么每一次只靠近一步就是最大次数
对于a,b,c，最大移动次数为 c-b-1 + b-a-1
 */
    public int[] numMovesStones(int a, int b, int c) {
        int[] s = {a, b, c };
        Arrays.sort(s);
        int minV = 0, maxV= s[2] - s[1] - 1 + s[1] - s[0] - 1;
        if (s[0] == s[1] - 1 && s[1]==s[2]-1) minV = 0;
        else if (s[1] - s[0] <= 2 || s[2] - s[1] <= 2) minV = 1;
        else minV = 2;
        
        return new int[]{minV, maxV};
    }

    public int[] numMovesStones2(int a, int b, int c) {
        int[] nums = {
                a, b, c };
        Arrays.sort(nums);
        int maxCount = nums[2] - nums[0] - 2;
        int minCount = 2;
        if (nums[2] - nums[1] < 3 || nums[1] - nums[0] < 3)
            minCount = 1;
        if (nums[2] - nums[1] == 1 && nums[1] - nums[0] == 1)
            minCount = 0;
        return new int[] {
                minCount, maxCount };
    }
}