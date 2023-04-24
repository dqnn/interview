package _1001_1999;

import java.util.*;

public class _1889MinimumSpaceWastedFromPackaging {

    /*
    1889. Minimum Space Wasted From Packaging
    You have n packages that you are trying to place in boxes, one package in each box. There are m suppliers that each produce boxes of different sizes (with infinite supply). A package can be placed in a box if the size of the package is less than or equal to the size of the box.
    
    The package sizes are given as an integer array packages, where packages[i] is the size of the ith package. The suppliers are given as a 2D integer array boxes, where boxes[j] is an array of box sizes that the jth supplier produces.
    
    You want to choose a single supplier and use boxes from them such that the total wasted space is minimized. For each package in a box, we define the space wasted to be size of the box - size of the package. The total wasted space is the sum of the space wasted in all the boxes.
    
    For example, if you have to fit packages with sizes [2,3,5] and the supplier offers boxes of sizes [4,8], you can fit the packages of size-2 and size-3 into two boxes of size-4 and the package with size-5 into a box of size-8. This would result in a waste of (4-2) + (4-3) + (8-5) = 6.
    Return the minimum total wasted space by choosing the box supplier optimally, or -1 if it is impossible to fit all the packages inside boxes. Since the answer may be large, return it modulo 109 + 7.
    
     
    
    Example 1:
    
    Input: packages = [2,3,5], boxes = [[4,8],[2,8]]
    Output: 6
    */
    /*
     m = A.length, n = B.length, p = avg(B[i])
     * thinking process: O(mlgm + m + n (plgp + lgm))
     * 
     * the problem is to say: given list of positive intefer array A as stone volumn, and 2D integer array B as bags array,
     * 
     * you will need to put the stone into bags if bag can hold the stone. return diff between stone 
     * and bag is the waste, return the min waste for B[i]. you can only pick one B[i]
     * 
     * packages = [2,3,5], boxes = [[4,8],[2,8]]
     * 
     * you can put 8 to hold all stones, but waste will be huge, 8-2 + 8-3 + 8-5 = 14
     * you can put 2,3 -> 4, 5 ->8,  then 4-2 + 4-3 + 8- 5 = 6 which is better 
     * 
     * 
     * 
     */
        public int minWastedSpace(int[] A, int[][] B) {
            Arrays.sort(A);
             
            long  res = Long.MAX_VALUE, MOD = (long)1e9 + 7, sum = 0L;
            for (int a : A)
                sum += a;
            
            //sum = Arrays.stream(A).sum();
            
            for(int[] b : B) {
                Arrays.sort(b);
                if (b[b.length - 1] < A[A.length - 1]) continue;
                long temp = 0; int i = 0;
                for(int k = 0; k<b.length; k++) {
                    
                    int j = find(A, b[k], i);
                    //System.out.println(j);
                    if ( j == -1) continue;
                    temp += (long)b[k] * (j - i + 1);
                    i = j + 1;
                    
                }
                res = Math.min(res, temp);
            }
            
            return res < Long.MAX_VALUE ? (int)((res - sum)%MOD) : -1;
        }
        
        
        private int find(int[] A, int t, int i) {
            int l = i, r = A.length - 1;
            
            if (l > A.length - 1) return -1;
            
            while(l + 1 < r) {
                int m = l + (r-l)/2;
                if (A[m] <= t) l = m ;
                else r = m;
            }
            
            if (A[l] > t) return -1;
            else if (A[l] <= t && A[r] > t) return l;
            else return r;
        }
        
        public int minWastedSpace_2(int[] A, int[][] boxes) {
            Arrays.sort(A);
            long inf = (long)1e11, res = inf, mod = (long)1e9 + 7, sumA = 0L;
            for (int a : A)
                sumA += a;
            for (int[] B : boxes) {
                Arrays.sort(B);
                if (B[B.length - 1] < A[A.length - 1]) continue;
                long cur = 0, i = 0, j;
                for (int b : B) {
                    j = binarySearch(A, b + 1);
                    System.out.println(j);
                    cur += b * (j - i);
                    i = j;
                }
                res = Math.min(res, cur);
            }
            return res < inf ? (int)((res - sumA) % mod) : -1;
        }
    
        private int binarySearch(int[] A, int b) {
            int l = 0, r = A.length;
            while (l < r) {
                int m = (l + r) / 2;
                if (A[m] < b)
                    l = m + 1;
                else
                    r = m;
            }
            return l;
        }
    }