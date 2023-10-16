package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SingleNumberIII
 * Creator : professorX
 * Date : Dec, 2017
 * Description : 260. Single Number III
 */
public class _260SingleNumberIII {
    /**
     * Given an array of numbers nums, in which exactly two elements appear only once and
     * all the other elements appear exactly twice. Find the two elements that appear only once.

     For example:

     Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

     A B : 二进制数字有且至少有一位是不相同

     3 : 011
     5 : 101

     3 ^ 5 : 110  -- 6
     -6 : 11111111111111111111111111111010
     6 & -6 : 000010

     1, 2, 1, 3, 2, 5

     diff = 3 ^ 5

     time : O(n)
     space : O(1)


     * @param A
     * @return
     */
    //thinking process: two numbers appear once while others appear twice, find the two numbers
    
    /*
     * O(n)/O(1)
     * 
     * 
     */
    public int[] singleNumber(int[] A) {
        int diff = 0;
        for (int a : A) {
            diff ^= a;
        }
        diff &= -diff;
        int[] res = new int[2];
        for (int a : A) {
            if ((a & diff) == 0) {
                res[0] ^= a;
            } else {
                res[1] ^= a;
            }
        }
        return res;
    }
}
