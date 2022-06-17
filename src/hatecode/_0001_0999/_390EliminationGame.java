package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : EliminationGame
 * Creator : professorX
 * Date : Oct, 2017
 * Description : 390. Elimination Game
 */
public class _390EliminationGame {
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
            // so if from left, then res must be next one. 
            //and if from right, and remaining is odd, the first will be removed. like 2,4,6,8,10-->4,8
            // so these cases we have to add the delta move
            if (left || remaining % 2 == 1) {
                res = res + step;
            }
            // this will be always the same no matter it is even or odd
            remaining = remaining / 2;
            // this is tricky part, so if we can scan from left to right, 
            // the next position is 2 then from right to left, 
            // this is not easy to think about
            // 
            step = step * 2;
            left = !left;
        }
        return res;
    }
    
    public int lastRemaining2(int n) {
        return helper(1, n, 1, true);
    }

    // start is the current first number
    // num is count of remaining numbers
    // diff is the interval between two next-to-each-other numbers
    // isFromLeft is from left or from right
    private int helper(int start, int num, int diff, boolean isFromLeft) {
        if (num == 1) {
            return start;
        } else {
            // if isFromLeft, new start is the second number
            // if isFromRight, new start is the first number if count is even
            // new start is the second number if count is odd
            int newStart = (isFromLeft || num % 2 == 1) ? (start + diff) : start;
            return helper(newStart, num / 2, diff * 2, !isFromLeft);
        }
    }
    
    
    
    
    //TODO: to understand more about the recursive, why it could work when rightToleft() - 1
    public int lastRemaining3(int n) {
        return leftToRight(n);
    }

    // eliminate [1...n] first from left to right, then alternate
    // this functions to return leftToright last element we think
    private int leftToRight(int n) {
        if (n == 1)
            return 1;
        // scan from left to right is simple, the length of array doesn't matter
        // [1, 2, 3, 4] -> 2 * [1, 2], 就是假设数组里面的值缩小一倍, then what it returned also need to amplified by 2
        // [1, 2, 3, 4, 5] -> 2 * [1, 2], the same as above
        return 2 * rightToLeft(n / 2);
    }

    // eliminate [1...n] first from right to left, then alternate
    // this is special case, 
    private int rightToLeft(int n) {
        if (n == 1)
            return 1;
        // if the length of array is even, we will get only odd number
        // [1, 2, 3, 4] -> [1, 3] = 2 * [1, 2] - 1 what we need to is the final answer 
        //so at this case, the final answer is the even number - 1;
        if (n % 2 == 0)
            return 2 * leftToRight(n / 2) - 1;
        // else if the length of array is odd, we will get only even number
        // [1, 2, 3, 4, 5] -> [2, 4] = 2 * [1, 2]
        else
            return 2 * leftToRight(n / 2); // this is same as previous one
    }
}
