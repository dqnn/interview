package _2000_2999;

import java.util.*;

public class _2615SumOfDistances {
    /*
    2615. Sum of Distances
    You are given a 0-indexed integer array nums. There exists an array arr of length nums.length, where arr[i] is the sum of |i - j| over all j such that nums[j] == nums[i] and j != i. If there is no such j, set arr[i] to be 0.
    
    Return the array arr.
    
     
    
    Example 1:
    
    Input: nums = [1,3,1,1,2]
    Output: [5,0,3,4,0]
    Explanation: 
    When i = 0, nums[0] == nums[2] and nums[0] == nums[3]. Therefore, arr[0] = |0 - 2| + |0 - 3| = 5. 
    When i = 1, arr[1] = 0 because there is no other index with value 3.
    When i = 2, nums[2] == nums[0] and nums[2] == nums[3]. Therefore, arr[2] = |2 - 0| + |2 - 3| = 3. 
    When i = 3, nums[3] == nums[0] and nums[3] == nums[2]. Therefore, arr[3] = |3 - 0| + |3 - 2| = 4. 
    When i = 4, arr[4] = 0 because there is no other index with value 2. 
    */

     /*
      * thinking process: O(n)/O(n)

       the problem is to say: given one integer array, A[i], return all distance sum for each element, 
       each element distance defintion = |Ai - A0| + |Ai-A1|+ ... + |Ai-Ai-1| ... + |Ai -Ai+1|.. +|An-1 - Ai|
        
       Ai is the index of the element
       so we can find that for each x its index i, for example [1,3,1,1,2]
       for 1->[0,2,3], for element 0, the distsance, |0-2| + |0-3| = 5

       so we can use above forumla to reduce the computation, 
       A0.........Ai-1,Ai,Ai+1........An-1
       |------pre----|    |--post--------|
       |----------total------------------|
       pre = A0 +A1 + ... +Ai-1
       left = i * Ai - (A0 + .. +Ai-1)
       right = An-1 - Ai + ..Ai+1- Ai = Ai+1 + Ai+2... + An-1 - (n-1 - (i+1) + 1) * Ai 
       =  total -pre - Ai - Ai * (n-i-1)
      */
        public long[] distance(int[] A) {
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
                //left
                A[x] += i * y - pre;
                //right
                A[x] += total - pre - y - y * (n-1 - (i + 1) + 1);
                pre += x;
                //System.out.println(Arrays.toString(A));
            }
        }
    }