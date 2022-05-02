package hatecode._1000_1999;

import java.util.*;
public class _1291SequentialDigits {
/*
1291. Sequential Digits
An integer has sequential digits if and only if each digit in the number is one more than the previous digit.

Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.


Example 1:

Input: low = 100, high = 300
Output: [123,234]
Example 2:

Input: low = 1000, high = 13000
Output: [1234,2345,3456,4567,5678,6789,12345]
*/
    
    //thinking process: O(1)/O(1)
    
    //the problem is to say, given a range, you will find integers in the given range that
    //each integer has increasing digits wiht delta 1, like 123, etc
    
    
    //at first, maybe thinking about DFS/BFS, that would work, 
    //but taking depth that the qualified numbers are limited, 
    
    //below solution is to calculate all possible integers between l and h
    public List<Integer> sequentialDigits_Best(int low, int high) {
        
        List<Integer> res = new ArrayList<>();
        for(int i = 1; i<=9; i++) {
            int temp = i;
            for(int j = i + 1; j<=9;j++) {
                temp = temp * 10 + j;
                if(temp >= low && temp <= high) {
                    res.add(temp);
                }
            }
        }
        Collections.sort(res);
        return res;
    }
    
    //using BFS to find the qualified digits, 
    public List<Integer> sequentialDigits(int l, int h) {
        List<Integer> res = new ArrayList<>();
        
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i<=9; i++) q.offer(i);
        
        while(!q.isEmpty()) {
            int temp = q.poll();
            //in the range,
            if(temp >= l && temp <=h) res.add(temp);
            //drop the one not in range
            if (temp > h) continue;
            
            int last = temp % 10;
            if (last < 9) {
                q.offer(temp* 10 + last + 1);
            }
        }
        
        return res;
    }
    
    
    
    //simplest logic, just for reference
    public List<Integer> sequentialDigits_Simple(int low, int high) {
        int[] allNums = {12,23,34,45,56,67,78,89,
                         123,234,345,456,567,678,789,
                         1234,2345,3456,4567,5678,6789,
                         12345,23456,34567,45678,56789,
                         123456,234567,345678,456789,
                         1234567,2345678,3456789,
                         12345678,23456789,
                         123456789};
        List<Integer> res = new ArrayList<>();
        int n = allNums.length;
        for (int i = 0; i < n; i++) {
            if (allNums[i] < low) continue;
            if (allNums[i] > high) break;
            res.add(allNums[i]);
        }
        return res;
    }
    
    
    //Sliding windows
    public List<Integer> sequentialDigits_SlidingWindow(int low, int high) {
        String seqNum = "123456789";
         List<Integer> list = new ArrayList<>();

        int minWindow = String.valueOf(low).length();
        int maxWindow = String.valueOf(high).length();
        for(;minWindow <= maxWindow; minWindow++){
            for(int j = 0; j + minWindow <= 9; j++){
                String str = seqNum.substring(j, j + minWindow);
                int num = Integer.parseInt(str);
                if(num > high) break;
                if(low <= num && high >= num){
                    list.add(num);
                }
            }
        }
        return list;
    }
    
   
}