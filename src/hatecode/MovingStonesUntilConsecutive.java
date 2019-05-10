package hatecode;

import java.util.*;

public class MovingStonesUntilConsecutive {
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
    //
    public int[] numMovesStones(int a, int b, int c) {
        int[] s = {a, b, c };
        Arrays.sort(s);
        if (s[2] - s[0] == 2) return new int[] {0, 0 };
        return new int[] {
                Math.min(s[1] - s[0], s[2] - s[1]) <= 2 ? 1 : 2, s[2] - s[0] - 2 };
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