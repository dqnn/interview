package hatecode._1000_1999;
public class _1131MaximumOfAbsoluteValueExpression {
/*
1131. Maximum of Absolute Value Expression
Given two arrays of integers with equal lengths, return the maximum value of:

|arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|

where the maximum is taken over all 0 <= i, j < arr1.length.

 

Example 1:

Input: arr1 = [1,2,3,4], arr2 = [-1,4,5,6]
Output: 13
*/
    
    //thinking process: O(n)/O(1), like best time to sell stocks
    
    //longest manhantance distance, in java code, 
    //{-1, 1} means the directions, 
    //Take |arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| as Manhattan distance of two points.
    //arr1 is the x, arr2 is y, then 
   /*
    * For 3 points on the plane, we always have 
    * |AO| - |BO| <= |AB|.
When AO and BO are in the same direction, 
we have ||AO| - |BO|| = |AB|.

We take 4 points for point O, left-top, left-bottom, 
right-top and right-bottom.
Each time, for each point B, and find the closest 
A point to O,
//suppose current x is starting point, we have +/-1 cases, 
//so we get the diff
    */
    public int maxAbsValExpr(int[] x, int[] y) {
        int res = 0, n = x.length, P[] = {-1,1};
        for (int p : P) {
            for (int q : P) {
                int closest = p * x[0] + q * y[0] + 0;
                for (int i = 1; i < n; ++i) {
                    int cur = p * x[i] + q * y[i] + i;
                    res = Math.max(res, cur - closest);
                    closest = Math.min(closest, cur);
                }
            }
        }
        return res;
    }
    //thinking process: O(n)/O(1)
    public int maxAbsValExpr_Math(int[] arr1, int[] arr2) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        int max4 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int min3 = Integer.MAX_VALUE;
        int min4 = Integer.MAX_VALUE;
        int n = arr1.length;
        for (int i = 0; i < n; i++){
            // st scenario arr1[i] + arr2[i] + i
            max1 = Integer.max(arr1[i]+arr2[i]+i, max1);
            min1 = Integer.min(arr1[i]+arr2[i]+i, min1);
            // nd scenario arr1[i] + arr2[i] - i
            max2 = Integer.max(arr1[i] + arr2[i] - i, max2);
            min2 = Integer.min(arr1[i] + arr2[i] - i, min2);
            // rd scenario arr1[i] - arr2[i] - i
            max3 = Integer.max(arr1[i] - arr2[i] - i, max3);
            min3 = Integer.min(arr1[i] - arr2[i] - i, min3);
            // th scenario arr1[i] - arr2[i] + i
            max4 = Integer.max(arr1[i] - arr2[i] + i, max4);
            min4 = Integer.min(arr1[i] - arr2[i] + i, min4);
        }
        int diff1 = max1 - min1;
        int diff2 = max2 - min2;
        int diff3 = max3 - min3;
        int diff4 = max4 - min4;
        return Integer.max(Integer.max(diff1,diff2),Integer.max(diff3,diff4));
    }
}