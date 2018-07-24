package leetcode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : HIndexII
 * Creator : duqiang
 * Date : Nov, 2017
 * Description : 275. H-Index II
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
        while (start <= end) {
            int mid = (end - start) / 2 + start;
            if (citations[mid] == len - mid) {
                return len - mid;
            } else if (citations[mid] < len - mid) {
                start = mid + 1;
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
