package hatecode._1000_1999;
public class _1891CuttingRibbons {
/*
1891. Cutting Ribbons
You are given an integer array ribbons, where 
ribbons[i] represents the length of the ith ribbon, 
and an integer k. You may cut any of the ribbons into 
any number of segments of positive integer lengths, or 
perform no cuts at all.

For example, if you have a ribbon of length 4, you can:
Keep the ribbon of length 4,
Cut it into one ribbon of length 3 and one ribbon of length 1,
Cut it into two ribbons of length 2,
Cut it into one ribbon of length 2 and two ribbons of length 1, or
Cut it into four ribbons of length 1.
Your goal is to obtain k ribbons of all the same positive integer length. You are allowed to throw away any excess ribbon as a result of cutting.

Return the maximum possible positive integer length that you can obtain k ribbons of, or 0 if you cannot obtain k ribbons of the same length.

 

Example 1:

Input: ribbons = [9,7,5], k = 3
Output: 5
*/
    //thinking process: O(nlg(10^5))/O(1)
    
    /*
     * the problem is to say: given a integer array and integer k,
     * you can cut integer A[i] = a + b + c, so you can at least 
     * have k same value of integers, 
     * 
     * return the max value among these,
     * A = [9,7,5], k = 3
     * you can cut 7 = 2 + 5, 9 = 4 + 5, you can can have 
     * 3 5, max value is 5, so return 5, you can throw others away
     * 
     * we use binary search to finish the work,
     * binary search start from 1, right value is the max(A),
     * 
     * every time you can try isCutPossible() to cut integers in A to length,
     *  if the count >= k which means length can be bigger so we move
     *  //l = m + 1, if not, smaller
     *  
     *  TODO:
     *  1.understand 419 and 1891, helper methods in 419?
     *  2. in 1891, why l cannot be sum/k as left value?
     *  3. in 1891, why last still have to call isCutPossible()
     */
    public int maxLength(int[] A, int k) {
        
        long sum = 0, max = 0;
        for(int a : A) {
            sum += a;
            max = Math.max(max, a);
        }
        
        if(sum < k) return 0;

        long l = 1;
        //int r = Arrays.stream(A).max().getAsInt();
        long r = sum;
        
        while(l  + 1 < r) {
            long m = l + (r-l)/2;
            if (isCutPossible(A, m, k)) {
                l = m + 1;
            } else r = m ;
        }
        
        //here is key, l = r, we did not try l actually, becaus we want amx one 
        //A =[9,7,5], k = 3, l=5, r =6, 
        //

        if(isCutPossible(A, r, k)) return (int)r;
        else return (int)l;
    }
    
    
    private boolean isCutPossible(int[] A, long len, int k) {
        int res = 0;
        for(int a : A) {
            res += a/len;
        }
        
        return res >=k;
    }
}