package hatecode._0001_0999;

public class _441ArrangingCoins {
    /*
    441. Arranging Coins
    You have n coins and you want to build a staircase with these coins. The staircase consists of k rows where the ith row has exactly i coins. The last row of the staircase may be incomplete.
    
    Given the integer n, return the number of complete rows of the staircase you will build.
    
     
    
    Example 1:
    
    
    Input: n = 5
    Output: 2
    */
    /*
     * thinking process; O(lgn)/O(1)
     * 
     * the problem is to say: given n coins, so need to make stairs up to bottom, 
     * return the correct value
     *   n = 5
     * 
     *    1
     *    1  1
     *    1  1  
     * 
     * return 2, starts as 0 index
     */
        public int arrangeCoins(int n) {
            if (n == 1) return 1;
            
            long l=1, r = n;
            
            while(l < r) {
                long m = l + (r-l)/2;
                //how many left, bigger than next th
                if ((n - m * (m+1)/2) >= m + 1) {
                    l = m + 1;
                } else r = m;
            }
            
            if (l * (l-1)/2 == (long)n) return (int)l + 1;
            else return (int)l;
            
        }
        
        /*
         * so use math, we can see the result should be less than sqrt(n).
         * 
         * so we use bit to store 
         */
        public int arrangeCoins_Bit(int n) {
            int mask = 1 << 15;
            long result = 0;
            while (mask > 0) {
                result |= mask;
                if (result * (result + 1) / 2 > n) {
                    result ^= mask;
                }
                mask >>= 1;
            }
            return (int) result;
        }
    }
