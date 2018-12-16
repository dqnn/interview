package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : Sqrt
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 69. Sqrt(x)
 */
public class Sqrt {
    // 367	Valid Perfect Square

    /**
     * time : O(logn) space : O(1)
     * @param x
     * @return
     */
    //we use logx to identify the result，
    
    //in a sorted array, binary search is most efficient
    
    //please note we use bianry search first templates, low <=high
    //and last we handle high + 1 = low
    public int mySqrt(int x) {
        if (x <= 0) return 0;
        int low = 1, high = x;
        while (low <= high) {
            long mid = (high - low) / 2 + low;
            if (mid * mid == x) {
                return (int) mid;
            } else if (mid * mid < x) {
                low = (int) mid + 1;
            } else {
                high = (int) mid - 1;
            }
        }
        if (high * high < x) {
            return (int) high;
        } else {
            return (int) low;
        }
    }

    // Newton Method time : 不知道 space : O(1);
    public int mySqrt2(int x) {
        long res = x;
        while (res * res > x) {
            res = (res + x / res) / 2;
        }
        return (int) res;
    }
}
