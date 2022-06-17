package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : Sqrt
 * Creator : professorX
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
    //refactor the code
    public int mySqrt(int x) {
        if(x < 0) return -1;
        long l = 0, r = x;
        while(l + 1 < r) {
            long mid = l + (r-l)/ 2;
            long cur = mid * mid;
            if( cur == x) return (int)mid;
            else if(mid * mid > x) r =  mid;
            else l = mid;
        }
        
        if(r * r <= x) return (int)r;
        else return (int)l;
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
