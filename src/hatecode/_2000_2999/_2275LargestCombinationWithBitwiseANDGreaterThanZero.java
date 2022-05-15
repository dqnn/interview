package hatecode._2000_2999;


public class _2275LargestCombinationWithBitwiseANDGreaterThanZero {
/*
2275. Largest Combination With Bitwise AND Greater Than Zero
The bitwise AND of an array nums is the bitwise AND of all integers in nums.

For example, for nums = [1, 5, 3], the bitwise AND is equal to 1 & 5 & 3 = 1.
Also, for nums = [7], the bitwise AND is 7.
You are given an array of positive integers candidates. Evaluate the bitwise AND of every combination of numbers of candidates. Each number in candidates may only be used once in each combination.

Return the size of the largest combination of candidates with a bitwise AND greater than 0.

 

Example 1:

Input: candidates = [16,17,71,62,12,24,14]
Output: 4
*/
    //thinking process: O()
    public int largestCombination(int[] A) {
        if (A == null || A.length == 0) return 0;
        if (A.length == 1 ) return 1;
        
        int res = 0;
        for(int i = 0; i<32; i++) {
            int cur = 0;
            for(int a : A) {
                cur += ((a >> i) & 1) > 0 ? 1: 0;
                res = Math.max(res, cur);
            }
        }
        
        return res;
    }
}