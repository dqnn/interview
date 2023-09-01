package hatecode._0001_0999;

import java.util.*;
/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : FindPeakElement
 * Creator : professorX
 * Date : Sep, 2017
 * Description : TODO
 */
public class _162FindPeakElement {

    /**
     * 162. Find Peak Element
     * A peak element is an element that is greater than its neighbors.

     Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

     The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

     You may imagine that num[-1] = num[n] = -∞.

     For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.


     1 2 3 1

     1 2 3 2 1

     time : O(logn);
     space : O(1);
     * @param nums
     * @return
     */
    //since it is peak, which means left is increase while right is decrease so we can use BS
    //it is standard BS

    /*
     * the problem is to  ask return any index of peak elements, Note: there will be multiple peaks there.
     * the reason why we can use BS is becz that A[i] != A[i+1], so there definitely peak when m placed.
     */
    public int findPeakElement(int[] A) {
        if (A == null || A.length < 1) return -1;
        
        int l = 0, r = A.length - 1;
        while(l + 1 < r) {
            int mid = l + (r - l) / 2;
            if(A[mid] < A[mid+1]) {
                l = mid;
            } else {
                r = mid;
            }
        }
        if(A[r] > A[l]) return r;
        else return l;
    }
    
    //bruth-force way to solve the problem
    public int findPeakElement2(int[] nums) {
        if (nums == null || nums.length < 1) {
            return -1;
        }
        
        if (nums.length == 1) {
            return 0;
        }
        
        if (nums.length == 2) {
            return nums[0] < nums[1] ? 1: 0;
        }
        
        // sovle the left and right issues
        if (nums[0] > nums[1]) {
            return 0;
        }
        
        if (nums[nums.length - 1] > nums[nums.length - 2]) {
            return nums.length - 1;
        }
        

        for(int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return -1;
    }


/*
 * follow up: 
 * 
 * return how many peak elements in array A, peak element means there are at least k elemnents on its left and right
 * for example A=[1,2,8,5,3,4] we return 2 because only 8 and 5
 */
    public static int findPeakElement(int[] A, int k) {
        if (A == null || A.length < 1) return 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b)->(Integer.compare(b, a)));

        int[] l = new int[A.length];
        int n = A.length;
        Arrays.fill(l, -1);

        for(int i = 0; i< n; i++) {
            if(i >= 2 && i <= n-3 && pq.size() >=k && pq.peek() < A[i]) {
                l[i] = 1;
            }
            pq.add(A[i]);
            if (pq.size() > k) pq.poll();
        }

        pq.clear();
        int res = 0;
        for(int i = n-1; i >=0; i--) {
            if(i >= 2 && i <= n-3 && pq.size() >=k && pq.peek() < A[i] && l[i] > 0) {
                res++;
            }
            pq.add(A[i]);
            if (pq.size() > k) pq.poll();
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(findPeakElement(new int[]{1,2,8,5,3,4,1,1}, 2));
    }
}
