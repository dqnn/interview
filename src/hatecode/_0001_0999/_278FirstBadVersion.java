package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : FirstBadVersion
 * Creator : duqiang
 * Date : Sep, 2017
 * Description : TODO
 */
public class _278FirstBadVersion {
    /**
     * 278. First Bad Version
     * You are a product manager and currently leading a team to develop a new product. Unfortunately,
     * the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.

     Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one,
     which causes all the following ones to be bad.

     You are given an API bool isBadVersion(version) which will return whether version is bad.
     Implement a function to find the first bad version. You should minimize the number of calls to the API.

                 bad
     1, 2, 3, 4, 5, 6, 7

     time : O(logn)
     space : O(1)
     * @param n
     * @return
     */
    public int firstBadVersion(int n) {
        if (n < 1) {
            return -1;
        }
        //The (left<=right) takes care of the scenarios in which your pivot is essentially left = right. 
        //In that case, if you don't check, you will miss on one element. It is easy to verify it by doing a binary 
        //search in an array of small size such as 1,2 or 1,2,3 etc. About your second issue, 
        //this problem is actually tricker as there is no default condition when you know you have found it. I
        
        //1,2,3,4,5,6,7,8,9   5 is the first version to be broken.
        // round 1: start = 1 end = 9, mid = 5
        //round 2: start = 4, end = 9, mid = 6
        // round 3: start = 4 end = 5, mid = 4
        // round 4: start = 5, end = 5, mid = 5
        //round 6: start = 5, end = 4, so we exit the loop
        int start = 1, end = n;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if (isBadVersion(mid)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        
        if (isBadVersion(start)) {
            return start;
        }
        
        return end;
    }

    public boolean isBadVersion(int mid) {
        return true;
    }
}
