package leetcode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MedianofTwoSortedArrays
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : 4. Median of Two Sorted Arrays
 */
public class MedianofTwoSortedArrays {

    /**
     * There are two sorted arrays nums1 and nums2 of size m and n respectively.

     Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

     Example 1:
     nums1 = [1, 3]
     nums2 = [2]

     The median is 2.0
     Example 2:
     nums1 = [1, 2]
     nums2 = [3, 4]

     The median is (2 + 3)/2 = 2.5


     O(log(min(m,n)))

     参考博客：http://blog.csdn.net/chen_xinjia/article/details/69258706

     index: 0	1	2	3	4	5
               L1   R1
     num1:	3 	5 |	8 	9    	      4  cut1：左有几个元素
     num2:	1	2 	7 | 10  11  12     6  cut2：左有几个元素
                    L2  R2

     num3:  1 2 3 5 7 | 8 9 10 11 12

     num3 -> num1 + num2 -> num1

     L1 <= R2
     L2 <= R1

     (cutL, cutR)

     L1 > R2 cut1 <<  (cutL, cut1 - 1)
     L2 > R1 cut2 >>  (cut1 + 1, cutR)

     index: 0	1	2	3	4	5
               L1   R1
     num1:	3 	5 |	8 	9   |	      4  cut1：左有几个元素
     num2:	1	2 	7 | 10  11  12     6  cut2：左有几个元素
                    L2  R2

     num3:  1 2 3 5 7 | 8 9 10 11 12

     int cut1 = 2;
     int cut2 = 3;
     int cutL = 0;
     int cutR = 4;


     time : O(log(min(m,n)))
     space : O(1)

     * @param nums1
     * @param nums2
     * @return
     */
   // so this problem is thinking from different perspective, we cut one array into two parts l-> L1 and L2
    // L1 <= L2, so the median is avg ( max(L1) + min(L2) ) and we do not need to care about the order of the
    // elements in L1 and L2. so if we sort, will be O((m + n)lg(m + n)), O(1)
    // so we want to get rid of sorting and get the median of the two arrays. 
    
    // this is to setup a virtually binary search situation
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int len = nums1.length + nums2.length;
        int cut1 = 0;
        int cut2 = 0;
        //cutL and cutR means the cut1 value range, cur1 position is the middle of cutL and cutR, 
        // it is like start and end in binary search
        int cutL = 0;
        // cutR1 is on nums1 most right position
        int cutR = nums1.length;
        // so we want to find a correct position in array 1, the position can give us
        // L1 <= R2 and L2 <= R1 so we are easy to know the median
        while (cut1 <= nums1.length) {
            // cut1 means the cut position, firstly it will be in the middle of nums1
            cut1 = (cutR - cutL) / 2 + cutL;
            // we need to keep cut length(left) = length(right), so 
            // we need to len / 2 - cut1
            cut2 = len / 2 - cut1;
            // we use double since we want to do calculation so we dont need to care about overflow
            // L1 means the left element nearest to the cut1
            double L1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            // L2 means the left element nearest to the cut2
            double L2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];
            //R1 means the right element nearest to cut1
            double R1 = (cut1 == nums1.length) ? Integer.MAX_VALUE : nums1[cut1];
            //R2 means the right element nearest to cut2
            double R2 = (cut2 == nums2.length) ? Integer.MAX_VALUE : nums2[cut2];
            //so we find Cut1 position is bigger than nums2 right element,so we need to move,
            // we only cared about cut1 or L1 because L2 is decided by L1
            
            //L1 > R2 means array 1 cut need to move back which means cutR need to move left
            if (L1 > R2) {
                // we move cutR to cut1 left 1 since
                //1 nums array are already sorted
                //2 move cut1 to left by 1
                cutR = cut1 - 1;
            } else if (L2 > R1) {
                // we found that in L2 is bigger than right, so cutL 
                cutL = cut1 + 1;
            // here handles L1 = R2 or L2 == R1
                //since they already sorted and L1 + L2 = L /2, 
                //so we already find the correct place
            } else {
                // if len is even, so we just need two avg value
                if (len % 2 == 0) {
                    L1 = L1 > L2 ? L1 : L2;
                    R1 = R1 < R2 ? R1 : R2;
                    return (L1 + R1) / 2;
                } else {
                    // if it is odd, we just need to find the smaller one, since we use L/2, which means 
                    // the middle left one, so median is the on the right, if they merge, then smaller one 
                    //will become the median
                    R1 = (R1 < R2) ? R1 : R2;
                    return R1;
                }
            }
        }
        return -1;
    }
}
