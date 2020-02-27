package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : GuessNumberHigherorLower
 * Creator : duqiang
 * Date : Sep, 2017
 * Description : TODO
 */
public class _374GuessNumberHigherorLower {
    /**
     * 374. Guess Number Higher or Lower
     *
     * We are playing the Guess Game. The game is as follows:

     I pick a number from 1 to n. You have to guess which number I picked.

     Every time you guess wrong, I'll tell you whether the number is higher or lower.

     You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):

     -1 : My number is lower
     1 : My number is higher
     0 : Congrats! You got it!


     * time : O(logn);
     * space : O(1);
     * @param n
     * @return
     */
    public int guessNumber(int n) {
        int l = 1;
        int r = n;
        while (l + 1 < r) {
            int mid = (r - l) / 2 + l;
            if (guess((mid)) == 0) {
                return mid;
            } else if (guess(mid) == 1) {
                l = mid;
            } else {
                r = mid;
            }
        }
        if (guess(l) == 0) return l;
        return r;
    }


    // 防止编译器报错函数
    public int guess(int num) {
        return 0;
    }
    
    public int guessNumber2(int n) {
        if (n < 1) {
            return -1;
        }
        int l = 1, r = n;
        while (l < r) {
            int mid = l + (r -l) / 2;
            int g = guess(mid);
            if (g == 1) {
                l = mid + 1;
            } else if (g == -1) {
                r = mid - 1;
            }  else {
                return mid;
            }
        }
        if (guess(l) == 0) return l;
        return r;
    }
}
