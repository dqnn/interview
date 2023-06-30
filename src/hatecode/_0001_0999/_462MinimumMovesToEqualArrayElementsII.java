import java.util.*;

public class _462MinimumMovesToEqualArrayElementsII {
    /*
    462. Minimum Moves to Equal Array Elements II
    
    Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.
    
    In one move, you can increment or decrement an element of the array by 1.
    
    Test cases are designed so that the answer will fit in a 32-bit integer.
    
     
    
    Example 1:
    
    Input: nums = [1,2,3]
    Output: 2
    */

       /*
        * thinking process: O(n)/O(1)

        the problem is to say: given one array A, you  can increment or decrement element by 1, we can this action as one move, 
        we want to make all elements in A to be the same, return the min moves totally. 


        ------min--------------------max----------------

        suppose min and max are min and max of the A,  any element outside [min, max] will make results bigger than one between min 
        and max, 

        so the element must be inside,  median, 

        we can try to find the median by partition array since we only need to know the median without soring the array


        */
        public int minMoves2(int[] A) {
            if (A == null || A.length < 1) return -1;
            
            
            int n = A.length;
            int l = 0, r = n -1;
            
            while(true) {
                int m = partition(A, l ,r);
                if (m == n /2) {
                    return calc(A, A[m]);
                } else if (m < n/2) {
                    l = m + 1;
                } else r = m;
            }
        }
        
        private int calc(int[] A, int m) {
            int res = 0;
            
            for(int a : A) {
                res += Math.abs(a - m);
            }
            
            return res;
        }
        
        
        private int partition(int[] A, int left, int right) {
            int p = left;
            
            int l = p + 1, r = right;
            
            while(l <= r) {
                if (A[l] > A[p] && A[r] < A[p]) swap(A, l, r);
                
                if (A[l] <= A[p]) l++;
                if (A[r] >= A[p]) r--;
            }
            
            swap(A, p, r);
            return r;
        }
        
        private void swap(int[] A, int l, int r) {
            int temp = A[l];
            A[l] = A[r];
            A[r] = temp;
        }
    }