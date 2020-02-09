package hatecode._0001_0999;
public class PeakIndexInAMountainArray {
/*
 852. Peak Index in a Mountain Array
Let's call an array A a mountain if the following properties hold:

A.length >= 3
There exists some 0 < i < A.length - 1 such that 
A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
Given an array that is definitely a mountain, return any i 
such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1].

Example 1:

Input: [0,1,0]
Output: 1
Example 2:

Input: [0,2,1,0]
Output: 1
*/
    //one pass
    public int peakIndexInMountainArray(int[] A) {
        if (A == null || A.length < 3) {
            return -1;
        }
        for(int i =1;i < A.length;i++){
            if (A[i-1] > A[i]) {
                return i -1;
            }
        }
        return -1;
    }
    //two pinters, fastest one
    public int peakIndexInMountainArray2(int[] A) {
        int i = 0,j=A.length-1 ;
        for (;i<j;){
            if (A[i+1]>A[i]) {
                i++;
            } else {
                return i;
            }
            if (A[j-1]>A[j]) {
                j --;
            } else {
                return j;
            }
        }
        //to avoid [0,1,0] case
        return i;
    }
}