package hatecode._1000_1999;
public class _1011CapacityToShipPackagesWithinDDays {
/*

1011. Capacity To Ship Packages Within D Days
A conveyor belt has packages that must be shipped from one port to another within D days.

The i-th package on the conveyor belt has a weight of weights[i].  Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.

Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within D days.

 

Example 1:

Input: weights = [1,2,3,4,5,6,7,8,9,10], D = 5
Output: 15
*/
    //thinking process: 
    //
    public int shipWithinDays_Better(int[] A, int days) {
        int max = 0, sum = 0;
        for(int a: A) {
            sum += a;
            max=Math.max(max, a);
        }
        
        //here r could be more narrowed down.
        // suppose each day we only ship one item, 
        //then we will have A.length -days items left, 
        //so the length of max array will be A.length -days + 1
        int l = max, r = Math.max(1, A.length - days + 1) * max;
        //r = sum;
        
        
        while(l < r) {
            int m = l + (r-l)/2;
            //we can transport A within days with m limit each day
            if (valid(m, A, days)) {
                r = m;
            } else l = m + 1;
        }
        
        return l;
    }
    
       public int shipWithinDays(int[] nums, int m) {
        int max = 0; long sum = 0;
        for (int num : nums) {
            max = Math.max(num, max);
            sum += num;
        }
        if (m == 1) return (int)sum;
        //binary search
        long l = max; long r = sum;
        while (l + 1< r) {
            long mid = (l + r)/ 2;
            if (valid(mid, nums, m)) {
                r = mid;
            } else {
                l = mid;
            }
        }
        if (valid(l, nums, m)) return (int)l;
        else return (int)r;
    }
       
    //all num are non-negative, so suppose all sum, and max number. 
       //and if we can divide 
    //m groups, each group avg(sum) compare to mid = (max + sum) / 2
    // if we found we have more groups if we compare each group to continous sum
    public boolean valid(long target, int[] nums, int m) {
        
        //why initialize as 1?
        /*
         * if whole array < m, and if we groupCnt=0,then result will be 0, 
         *  so actually when we met > m, we already have two sub arrays.
         * 
         * if we assign groupCnt =0, then when we visit last element,
         * if sum < m, we need to +1, because it also one additional sub array
         */
        int curGroupCnt = 1;
        long total = 0;
        for(int num : nums) {
            total += num;
            if (total > target) {
                //we reset total from each group by a new number
                //but this could help to make sure previous group less than mid
                total = num;
                curGroupCnt++;
                //exceed total group number
                if (curGroupCnt > m) return false;
            }
        }
        return true;
    }
}