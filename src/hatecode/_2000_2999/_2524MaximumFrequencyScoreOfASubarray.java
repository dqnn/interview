package hatecode._2000_2999;

import java.util.*;

public class  _2524MaximumFrequencyScoreOfASubarray {
    /*
    2524. Maximum Frequency Score of a Subarray
    You are given an integer array nums and a positive integer k.
    
    The frequency score of an array is the sum of the distinct values in the array raised to the power of their frequencies, taking the sum modulo 109 + 7.
    
    For example, the frequency score of the array [5,4,5,7,4,4] is (43 + 52 + 71) modulo (109 + 7) = 96.
    Return the maximum frequency score of a subarray of size k in nums. You should maximize the value under the modulo and not the actual value.
    
    A subarray is a contiguous part of an array.
    
     
    
    Example 1:
    
    Input: nums = [1,1,1,2,1,2], k = 3
    Output: 5
    */
        /*
         * thinking process: O(n)/O(n)
         * 
         * the problem is to say: given one integer array A, and one integer k as window size, 
         * you will need return the max frequency score  for each window,
         * frequency score , eg: [5,4,5,7,4,4] is (4^3 + 5^2 + 7^1) % (1e9 + 7) = 96
         * 
         * so use a Map<Integer, Map<Integer, Long>> to store each integer and its mod results, 
         * for example. 
         * A = [1,1,1,2,1,2], k = 3
         * 
         * map will be like 
         * 1 - {[1=1, 2 = 1, 3 = 1, 4 = 1]}
         * 2 - {[1=2, 2=4]}
         * 
         * so next step, we will use a sliding window in the array A, move from left to right, 
         * when we add a new element to window from right, we will update the map, 
         * 
         * sum + current new memeber - previous memeber  + mod (avoid negative number)
         * 
         * 
         */
        public int maxFrequencyScore(int[] A, int k) {
            long mod = (long)1e9 + 7L;
            
            Map<Integer, Integer> map = new HashMap<>();
            Arrays.stream(A).forEach(e->map.put(e, map.getOrDefault(e, 0) + 1));
            
            Map<Integer, HashMap<Integer, Long>> pw = new HashMap<>();
            
            //setup the lookup map
            for(int key : map.keySet()) {
                int cnt = map.get(key);
                long p = 1;
                for(int i = 1; i<=cnt; i++) {
                    p = p * key % mod;
                    pw.computeIfAbsent(key, v->new HashMap<>()).put(i, p);
                }
            }
            
            map.clear();
            long res = 0, sum = 0; 
            for(int i = 0, j = 0; j<A.length; j++) {
                int b = A[j], cnt = map.getOrDefault(a, 0);
                map.put(b, cnt+1);
                sum = ((sum + pw.get(b).get(cnt+1)) % mod - pw.get(b).getOrDefault(cnt, 0L) + mod) % mod;
                
                if (j - i + 1 > k) {
                    int a = A[i++];
                    map.put(a, map.get(a) - 1);
                    int acnt = map.get(a);
                    sum = ((sum + pw.get(a).getOrDefault(acnt, 0L)) % mod - pw.get(a).get(acnt + 1) + mod) % mod;
                }
                
                if (j - i + 1 == k) res = Math.max(res, sum);
            }
            
            return (int)res;
            
        }


    }