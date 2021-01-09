package hatecode._1000_1999;

import java.util.*;
public class _1224MaximumEqualFrequency {
/*
1224. Maximum Equal Frequency
Given an array nums of positive integers, return the longest possible 
length of an array prefix of nums, such that it is possible to remove 
exactly one element from this prefix so that every number that has 
appeared in it will have the same number of occurrences.

If after removing one element there are no remaining elements, 
it's still considered that every appeared number has the same number 
of ocurrences (0).

 

Example 1:

Input: nums = [2,2,1,1,5,3,3,5]
Output: 7

2 <= nums.length <= 10^5
1 <= nums[i] <= 10^5
*/
    //thinking process: 
 
    
    //the problem is to say:given one positive integer array, we can only remove 1 element,
    //so left elements have same occurrency.
    
    //the problem is to say: given one positive integer array, 
    //we can only remove 1 element, return the longest prefix sub array, 
    //which every element has same occurrence, 
    
    //
    public static int maxEqualFreq(int[] A) {
        int[] cnt = new int[100001], freq = new int[100001];
        int maxF = 0, res = 0;
        for (int i = 0; i < A.length; i++) {
            int num = A[i];
            cnt[num]++;
            freq[cnt[num] - 1]--;
            freq[cnt[num]]++;
            maxF = Math.max(maxF, cnt[num]);
            
            if (maxF * freq[maxF] == i 
                    || (maxF - 1) * (freq[maxF - 1] + 1) == i 
                    || maxF == 1)
                res = i + 1;
        }
        return res;
    }
    
    
    public static int maxEqualFreq_SlidingWindow(int[] A) {
        int n = A.length, m = 100_001, res = 0;
        int[] cnt = new int[m], freq = new int[m];
        for (int i = 0; i < n; i ++) {
            int val = A[i];
            freq[cnt[val]] --;
            int c = ++cnt[val];
            freq[cnt[val]] ++;
            
            if (freq[c] * c == i + 1 && i + 1 < n) {
                res = i + 2;
            }
            int d = i + 1 - freq[c] * c;
            if (freq[d] == 1 &&(d == c + 1 || d == 1)) {
                res = i + 1;
            }
             System.out.printf("i = %d, val = %d, c = %d, d = %d, count = %s, freq = %s\n", i, val, c, d, Arrays.toString(Arrays.copyOfRange(cnt, 0, 10)), Arrays.toString(Arrays.copyOfRange(freq, 0, 10)));
        }
        return res;
    }
    
    public static void main(String[] args) {
        System.out.println(maxEqualFreq(new int[] {1,1,1,2,2,2,3}));
        System.out.println(maxEqualFreq_SlidingWindow(new int[] {1,1,1,2,2,2,3}));
    }
}