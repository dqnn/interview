package hatecode;

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

     Find the median of the two sorted arrays. The overall run time 
     complexity should be O(log (m+n)).

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

     * @param n1
     * @param n2
     * @return
     */
    //shorter Binary search solution
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j-1] > A[i]){
                iMin = i + 1; // i is too small
            }
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = i - 1; // i is too big
            }
            else { // i is perfect
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
    
    
    //best performance:
    // so this problem is thinking from different perspective, we cut one array into two parts l-> L1 and L2
    // L1 <= L2, so the median is avg ( max(L1) + min(L2) ) and we do not need to care about the order of the
    // elements in L1 and L2. so if we sort, will be O((m + n)lg(m + n)), O(1)
    // so we want to get rid of sorting and get the median of the two arrays. 
    
    // this is to setup a virtually binary search situation
    
    //when in interview
    //1. demonstrate the whole idea detail first: two arrays, all sorted, so we don't need to re-sort and we only
    // need to find out the correct position in array 1 which satisfy L1 <= R2 and L2 <= R1 and len(L1 + L2) = 
    //len (R1 + R2)
    // so we use binary search to find the correct position, we virtually setup a situation to visit each element in array1
    //since it is smaller, why?
    // we add Integer.MIN_VALUE and integer.MAX_VALUE to be placed beginning and end of two arrays
    public double findMedianSortedArrays2(int[] n1, int[] n2) {
      //edge case
        if (n1 == null && n2 == null) {
            return -1.0;
        } else if (n1 == null || n2 == null){
            int[] res = n1 == null ? n2 : n1;
            int len = res.length;
            if (len % 2 == 0) {
                return (double)(res[len/2] + res[len/2 + 1]) / 2.0;
            } else {
                return res[(len + 1)/2];
            }
        }
        // this is to make sure N1 is smaller one, why?
        // 1. since n1 is smaller so it would be faster when we calc the correct position
        // 2. n1 is smaller so when we calc the n2 L2 and R2 there would be no indexOutofBoundary error
        if (n1.length > n2.length) {
            return findMedianSortedArrays(n2, n1);
        }
        int len = n1.length + n2.length;
        int middle1 = 0, middle2 = 0;
        //start and end means the middle1 value range, middle1 position is the middle of start and end, 
        // it is like start and end in binary search
        int start = 0;
        // end is on nums1 most right position
        int end = n1.length;
        // so we want to find a correct position in array 1, the position can give us
        // L1 <= R2 and L2 <= R1 so we are easy to know the median
        while (middle1 <= n1.length) {
            // middle1 means the cut position, firstly it will be in the middle of nums1
            middle1 = (end - start) / 2 + start;
            // we need to keep cut length(left) = length(right), so 
            // we need to len / 2 - cut1
            middle2 = len / 2 - middle1;
            // we use double since we want to do calculation so we dont need to care about overflow
            // L1 means the left element nearest to the cut1
            double L1 = (middle1 == 0) ? Integer.MIN_VALUE : n1[middle1 - 1];
            // L2 means the left element nearest to the cut2
            double L2 = (middle2 == 0) ? Integer.MIN_VALUE : n2[middle2 - 1];
            //R1 means the right element nearest to cut1
            double R1 = (middle1 == n1.length) ? Integer.MAX_VALUE : n1[middle1];
            //R2 means the right element nearest to cut2
            double R2 = (middle2 == n2.length) ? Integer.MAX_VALUE : n2[middle2];
            //so we find Cut1 position is bigger than nums2 right element,so we need to move,
            // we only cared about cut1 or L1 because L2 is decided by L1
            
            //L1 > R2 means array 1 cut need to move back which means cutR need to move left
            if (L1 > R2) {
                // we move cutR to cut1 left 1 since
                //1 nums array are already sorted
                //2 move cut1 to left by 1
                end = middle1 - 1;
            } else if (L2 > R1) {
                // we found that in L2 is bigger than right, so cutL 
                start = middle1 + 1;
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
