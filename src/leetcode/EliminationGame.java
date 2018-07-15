package leetcode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : EliminationGame
 * Creator : duqiang
 * Date : Oct, 2017
 * Description : 390. Elimination Game
 */
public class EliminationGame {
    /**
     * There is a list of sorted integers from 1 to n. Starting from left to right,
     * remove the first number and every other number afterward until you reach the
     * end of the list.
     * 
     * Repeat the previous step again, but this time from right to left, remove the
     * right most number and every other number from the remaining numbers.
     * 
     * We keep repeating the steps again, alternating left to right and right to
     * left, until a single number remains.
     * 
     * Find the last number that remains starting with a list of length n.
     * 
     * Example:
     * 
     * Input:
n = 9,
1 2 3 4 5 6 7 8 9
2 4 6 8
2 6
6

Output:
6
     */
    /*


     Input:
     n = 8,
     1 2 3 4 5 6 7 8
       2   4   6   8 res = 2
       2       6     res = 2
               6     res = 6


     1 2 3 4 5 6 7
       2   4   6
           4

     Output:
     6

     time : O(logn)
     space : O(1)

     * @param n
     * @return
     */
    public int lastRemaining(int n) {
        boolean left = true;
        int remaining = n;
        int step = 1;
        int res = 1; // head
        //when remaining = 1 exit
        while (remaining > 1) {
            if (left || remaining % 2 == 1) {
                res = res + step;
            }
            // this will be always the same no matter it is even or odd
            remaining = remaining / 2;
            // this is tricky part, so if we can scan from left to right, 
            // the next position is 2 then from right to left, 
            step = step * 2;
            left = !left;
        }
        return res;
    }
}
