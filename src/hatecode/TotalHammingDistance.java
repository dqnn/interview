package hatecode;
public class TotalHammingDistance {
/*
477. Total Hamming Distance
For each bit position 1-32 in a 32-bit integer, we count the number of integers in the array which have that bit set. Then, if there are n integers in the array and k of them have a particular bit set and (n-k) do not, then that bit contributes k*(n-k) hamming distance to the total.
*/
    
    //thinking process:
    //given an array, find all of the Hamming distance, the distance definition is one element to all others
    //how many bits are different on same position, 
    
    //C(n,2), 
    
    //for every number bits on position j, suppose m has 1, so n-m does not have, the distance will be 
    //m *(n-m). so we add 32 positions
    public int totalHammingDistance(int[] nums) {
        int total = 0, n = nums.length;
        for (int j=0;j<32;j++) {
            int bitCount = 0;
            for (int i=0;i<n;i++) 
                bitCount += (nums[i] >> j) & 1;
            total += bitCount*(n - bitCount);
        }
        return total;
    }
/*
The first solution came to my mind is brute forcely iterate through each pair, then sum all Integer.bitCount(x ^ y) like what I mentioned here https://discuss.leetcode.com/topic/72093/java-1-line-solution-d But as you can imagine, it TLE...

Let's think about this problem another way. We can separate the calculation to do one bit at a time. For example, look at the rightmost bit of all the numbers in nums. Suppose that i numbers have a rightmost 0-bit, and j numbers have a 1-bit. Then out of the pairs, i * j of them will have 1 in the rightmost bit of the XOR. This is because there are i * j ways to choose one number that has a 0-bit and one that has a 1-bit. These bits will therefore contribute i * j towards the total of all the XORs.

Apply above algorithm to each bit (31 bits in total as specified in the problem), sum them together then we get the answer.

Reference: http://stackoverflow.com/questions/21388448/sum-of-xor-values-of-all-pairs
*/
    public int totalHammingDistance2(int[] nums) {
        int n = 31;
        int len = nums.length;
        int[] countOfOnes = new int[n];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < n; j++) {
                countOfOnes[j] += (nums[i] >> j) & 1;
            }
        }
        int sum = 0;
        for (int count: countOfOnes) {
            sum += count * (len - count);
        }
        return sum;
    }
}