package hatecode._2000_2999;

import java.util.*;
public class _2033MinimumOperationsToMakeAUniValueGrid {
/*
2033. Minimum Operations to Make a Uni-Value Grid
You are given a 2D integer grid of size m x n and an integer x. In one operation, you can add x to or subtract x from any element in the grid.

A uni-value grid is a grid where all the elements of it are equal.

Return the minimum number of operations to make the grid uni-value. If it is not possible, return -1.

 

Example 1:


Input: grid = [[2,4],[6,8]], x = 2
Output: 4
Explanation: We can make every element equal to 4 by doing the following: 
- Add x to 2 once.
- Subtract x from 6 once.
- Subtract x from 8 twice.
A total of 4 operations were used.
*/
  public int minOperations(int[][] grid, int x) {
        int r = grid.length, c =grid[0].length;
        if (r *c <= 1) return 0;
        int[] A =new int[r* c];
        for(int i = 0; i < r; i++) {
            for(int j = 0; j<c; j++) {
                A[i * c + j] = grid[i][j];
            }
        }
        
        Arrays.sort(A);
        
        int res = 0, med = A[r*c/2];
        for(int i = 0; i<r*c; i++) {
            if (Math.abs(A[i] - med) % x != 0) return -1;
            
            res += Math.abs(A[i] - med) / x;
        }
        
        return res;
    }
    
    public int minOperations_quickSelect_TLE(int[][] grid, int x) {
        int r = grid.length, c =grid[0].length;
        if (r *c <= 1) return 0;
        int[] A =new int[r* c];
        for(int i = 0; i < r; i++) {
            for(int j = 0; j<c; j++) {
                A[i * c + j] = grid[i][j];
            }
        }
    
                         
       int med = findMed(A, 0, A.length - 1);
                         
       int res = 0;
       for(int i = 0; i <A.length; i++) {
           if (Math.abs(A[i] - A[med]) % x != 0) return -1;
           
           res += Math.abs(A[i] - A[med])/x;
       }
                         
       return res;
    }
    
    private int findMed(int[] A, int l, int r) {
        int len = A.length - 1;
        while(true) {
            
            int med = partition(A, l, r);
            System.out.println(l + "----" +med+"---"+ r);
            if (med  == len / 2) return med;
            else if (med > len/ 2) r = med - 1;
            else l= med + 1;
        }
    }
                         
    
    private int partition(int[] A, int l, int r) {
        int pivot = A[l];
        int left = l;
        l += 1;
        
        while(l <= r) {
            if (A[l] > pivot && A[r] < pivot) {
                swap(A, l++, r--);
            } 
            
            if (A[l] <= pivot) l++;
            if (A[r] >= pivot) r--;
        }
        
        swap(A, left, r);
        
        return r;
    }
                         
    private void swap(int[] A, int l, int r) {
        int temp = A[l];
        A[l] = A[r];
        A[r] = temp;
    }
                         
    
}