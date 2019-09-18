package hatecode;

import java.util.*;
public class _1013PartitionArrayIntoThreePartsWithEqualSum {
/*
1013. Partition Array Into Three Parts With Equal Sum
Given an array A of integers, return true if and only if we can partition the array into three non-empty parts with equal sums.

Formally, we can partition the array if we can find indexes i+1 < j with (A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1])

 

Example 1:

Input: [0,2,1,-6,6,-7,9,1,2,0,1]
Output: true
*/
    //thinking process:
    //
    public boolean canThreePartsEqualSum(int[] A) {
        if (A == null || A.length < 1) return false;
        
        int n = A.length;
        int sum = Arrays.stream(A).sum();
        if (sum % 3 != 0) return false;
        int iSum = 0;
        for(int i = 0; i< n; i++) {
            iSum += A[i];
            int jSum = 0;
            for(int j = i + 1; j < n; j++) {
                jSum += A[j];
                if (iSum == jSum && iSum == sum / 3) return true;
            }
        }
        
        return false;
    }
}