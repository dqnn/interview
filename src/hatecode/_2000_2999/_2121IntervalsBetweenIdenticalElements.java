package hatecode._2000_2999;

import java.util.*;

public class _2121IntervalsBetweenIdenticalElements {
    /*
     it is same as 2615. Sum of Distances, please see that problem

    2121. Intervals Between Identical Elements
    You are given a 0-indexed array of n integers arr.
    
    The interval between two elements in arr is defined as the absolute difference between their indices. More formally, the interval between arr[i] and arr[j] is |i - j|.
    
    Return an array intervals of length n where intervals[i] is the sum of intervals between arr[i] and each element in arr with the same value as arr[i].
    
    Note: |x| is the absolute value of x.
    
     
    
    Example 1:
    
    Input: arr = [2,1,3,1,2,3,3]
    Output: [4,2,7,2,4,4,5]
    */
      
        
        public long[] getDistances(int[] A) {
            if (A == null || A.length < 1) return new long[]{};
            
            Map<Integer, List<Integer>> map = new HashMap<>();
            int n = A.length;
            IntStream.range(0, n).forEach(i -> map.computeIfAbsent(A[i], v->new ArrayList<>()).add(i));
            
            //System.out.println(map);
            long[] res = new long[n];
            
            for(var entry: map.entrySet()) {
                fill(res, entry.getValue());
            }
            
            return res;
        }
        
        
        private void fill(long[] A, List<Integer> list) {
            int n = list.size();
            long total = 0;
            for(int i = 0; i<n; i++) {
                total += (long)list.get(i);
            }
            
            //System.out.println(Arrays.toString(sum));
            long pre = 0;
            for(int i = 0; i<n; i++) {
                int x = list.get(i);
                long y = (long)x;
                A[x] += i * y - pre;
                A[x] += total - pre - y - y * (n-1 - (i + 1) + 1);
                pre += x;
                //System.out.println(Arrays.toString(A));
            }
        }
    }