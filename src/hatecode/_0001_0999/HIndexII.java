package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : HIndexII
 * Creator : duqiang
 * Date : Nov, 2017
 * Description : 275. H-Index II
 * Given an array of citations sorted in ascending order (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."
 */
public class HIndexII {
    /**
     * [0, 1, 4, 5, 6]
     *
     * time : O(logn) space : O(1)
     *
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        int len = citations.length;
        int start = 0, end = len - 1;
        // it is guaranteed that right+1 is the one we need to find 
        //(i.e. len-(right+1) papars have at least len-(righ+1) citations)
        while (start <= end) {
            int mid = (end - start) / 2 + start;
            // len - mid means length
            //citations[n] increases with n, but len-n decrease with n. So if citations[mid] == (len-mid), 
            //then mid is the largest number with citations[n]<=len-n.
            //there are citations[mid] papers that have at least citations[mid] citations.
            
            // citations is sorted asc, so this value citations[mid], len - mid means number of papers 
            // citations are at least citations[mid]
            if (citations[mid] == len - mid) {
                return len - mid;
            //citations[mid] < len-mid, we should continue searching in the right side
            } else if (citations[mid] < len - mid) {
                start = mid + 1;
            // it means there are citations[mid] papers that have moret than citations[mid] citations, 
            //so we should continue searching in the left half
            } else {
                end = mid - 1;
            }
        }
        return len - start;
    }
    // O(n)  O(1)
    public int hIndex2(int[] c) {
        if (c == null || c.length < 1) {
            return 0;
        }
        
        int res = 0;
        while ((res < c.length) && c[c.length - 1 - res] > res) {
            res += 1;
        }
        
        return res;
    }
}
