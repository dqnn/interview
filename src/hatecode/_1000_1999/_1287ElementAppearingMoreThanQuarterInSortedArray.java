package hatecode._1000_1999;
public class _1287ElementAppearingMoreThanQuarterInSortedArray {
/*
1287. Element Appearing More Than 25% In Sorted Array
Given an integer array sorted in non-decreasing order, there is exactly one integer in the array that occurs more than 25% of the time, return that integer.

 

Example 1:

Input: arr = [1,2,2,6,6,6,6,7,10]
Output: 6
*/
    //O(n), linear search
    public int findSpecialInteger(int[] A) {
        
        if(A == null || A.length < 1) return -1;
        
        if(A.length < 4) return A[0];
        
        int windowSize = A.length/4;
        int l =0, r = windowSize;
        
        while(r < A.length) {
            if(A[l] == A[r]) return A[l];
            l++;
            r++;
        }
        
        return -1;
    }
    //interview friendly, 
    //thinking process: O(lgn)/O(1)
    public int findSpecialInteger_BS(int[] arr) {
        int len = arr.length / 4 + 1;
        int[] candidateIdx = new int[]{len - 1, len * 2 - 1, len * 3 - 1};
        for (int i : candidateIdx) {
            if (i >= arr.length) continue;
            int l = findLeftMostIndex(arr, arr[i], i - len + 1, i);
            int r = l + len - 1;
            if (r < arr.length && arr[l] == arr[r]) {
                return arr[l];
            }
        }
        return -1;
    }

    private int findLeftMostIndex(int[] arr, int target, int l, int r){
        while (l < r) {
            int m = l + (r - l) / 2;
            if (arr[m] < target) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }
}