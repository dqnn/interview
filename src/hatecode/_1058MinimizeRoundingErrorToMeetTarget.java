package hatecode;

import java.util.*;
public class _1058MinimizeRoundingErrorToMeetTarget {
/*
1058. Minimize Rounding Error to Meet Target
Given an array of prices [p1,p2...,pn] and a target, round each price pi to Roundi(pi) so that the rounded array [Round1(p1),Round2(p2)...,Roundn(pn)] sums to the given target. Each operation Roundi(pi) could be either Floor(pi) or Ceil(pi).

Return the string "-1" if the rounded array is impossible to sum to target. Otherwise, return the smallest rounding error, which is defined as Σ |Roundi(pi) - (pi)| for i from 1 to n, as a string with three places after the decimal.

 

Example 1:

Input: prices = ["0.700","2.800","4.900"], target = 8
Output: "1.000"
*/
    //thinking process: 
    
    //the problem is to say: given string array as float, you can 
    //round up (ceiling), round down (floor), if all these integer can sum to 
    //target then find out min of SUM(round(p) - p)
    
    //we use PQ to store ceiling(p) -p -(floor(p) - p), 
    //so as we add to PQ, we use target - floor(p), res += p - low
    //
    public String minimizeError_Greedy(String[] prices, int target) {
        float res = 0;
        PriorityQueue<Double> diffHeap = new PriorityQueue<>();
        
        for (String s : prices) {
            float f = Float.valueOf(s);
            double low = Math.floor(f);
            double high = Math.ceil(f);
            
            //here we add 4-3.8 -(3.8 -3) = -0.6
            if (low != high)
                diffHeap.offer((high-f)-(f-low));
            //res is to add the diff between f to floor
            res += f-low;
            target -= low;
        }
        
        if (target < 0 || target > diffHeap.size())
            return "-1";
        
        while (target-- > 0)
            res += diffHeap.poll();
        
        return String.format("%.3f", res);
    }
    
    //dp solution
    public String minimizeError(String[] prices, int target) {
        int n=prices.length;
        int lower=0;
        int noneZeroCount=0;
        String res="-1";
        int[] dp=new int[n];
        for(int i=0;i<n;i++){
            String value=prices[i];
            int len=value.length();
            lower+=Integer.parseInt(value.substring(0, len-4));
            dp[i]=Integer.parseInt(value.substring(len-3));
            if (dp[i]>0) noneZeroCount++;
        }
        
        if (target>=lower && target<=lower+noneZeroCount){
            int error=0;
            Arrays.sort(dp);
            for(int i=0;i<n;i++){
                if (i<n-target+lower){
                    error+=dp[i];
                }else{
                    error+=1000-dp[i];
                }
            }
            int f=error % 1000;
            res=(error/1000)+"."+ (f<10 ? "00"+f: f<100? "0"+f : f);
        }
        return res;
    }
}