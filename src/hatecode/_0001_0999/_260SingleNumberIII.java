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
     * this problem is tricky, 
     * 
     * A=[1,2,1,3,2,5]
     * 
     * first diff = 3^5, because others appear twice then XOR will be 0, so diff = 110
     * then diff &=~diff, it will be 010
     * 
     * here 010 means number 3 and 5 are different at 2nd bit from right, so if one number & 010 == 0 then it will be 
     * in 3 or 5 group, another group number will be 1
     * 
     * so we use this digit to group number, for example 
     * 
     *    
     *     010
     * 1   001
     * 5   101
     * 
     * 1 and 5 will be one group
     * 
     * 2 and 3 in another group
     * 2   010
     * 3   011
     * 
     * 
     * since 3 only appear once in [2,2,3], so 3 will be showup in res[1]
     * since 5 only appear once in [1,1,5], so 5 will be showup in res[0]  
     * 
     */
    public static int[] singleNumber(int[] A) {
        int diff = 0;
        for (int a : A) {
            diff ^= a;
        }
        // Get its last set bit， 6 & -6 => 0010 //this is used as a mask to separate 3 and 5 from the A[]
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


    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{1,2,1,3,2,5}));
    }
}
