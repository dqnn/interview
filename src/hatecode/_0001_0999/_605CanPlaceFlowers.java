package hatecode._0001_0999;

/**
 * Project Name : Leetcode Package Name : leetcode File Name : CanPlaceFlowers
 * Creator : professorX Date : Aug, 2018 Description : TODO
 */
public class _605CanPlaceFlowers {
    /**
     * 605. Can Place Flowers
     * Suppose you have a long flowerbed in which some of the plots are planted
     * and some are not. However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.

     Given a flowerbed (represented as an array containing 0 and 1, where
     0 means empty and 1 means not empty), and a number n, return if n new flowers can be
     planted in it without violating the no-adjacent-flowers rule.

     Input: flowerbed = [1,0,0,0,1], n = 1
     Output: True
     Input: flowerbed = [1,0,0,0,1], n = 2
     Output: False

     time : O(n);
     space : O(1);
     * @param flowerbed
     * @param n
     * @return
     */
    
    //thinking process: O(n)/O(1)
    
    /*
     * the problem is to say: given a integer array with 0 or 1 as value of its elements,
     * 1 means flower, 0 means empty, we must plant flower in adjacent cell. return whether 
     * we can plant n flowers more.
     * 
     *first need to clarify input and edge cases.
     * if n == 0 or A is empty, then we just return true. 
     * 
     * then both edges we can plant if its adjacent are not empty.
     * 
     * just plant as many as possible, it is greedy problem
     */
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        // this templates needed to be remembered
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0) {
                if (i == 0 || flowerbed[i - 1] == 0) {
                    if (i == flowerbed.length - 1 || flowerbed[i + 1] == 0) {
                        flowerbed[i] = 1;
                        count++;
                    }
                }
            }
        }
        return count >= n;
    }
}
