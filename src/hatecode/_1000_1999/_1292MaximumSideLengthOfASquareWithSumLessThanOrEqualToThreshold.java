package hatecode._1000_1999;
public class _1292MaximumSideLengthOfASquareWithSumLessThanOrEqualToThreshold {
/*    
  
1292. Maximum Side Length of a Square with Sum Less than or Equal to Threshold
    Given a m x n matrix mat and an integer threshold, return the maximum side-length of a square with a sum less than or equal to threshold or return 0 if there is no such square.

 

Example 1:


Input: mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
Output: 2
 */   
    //Best solution,
    
    //thinking process: O(mn)/O(mn)
    
    //the problem is to say: given a 2D matrix of integers and one integer h, we want to
    //know how many square length of their sum of squares in this matrix less than h. 
    
    //please note the problem is to ask how many square size, so even two squares sum less than h,
    //they are still one size, so actually the problem is to want to know the biggest square length size.
    
    //since all integers are positive ones, the if we have fixed top left number, as length increase, the sum will be 
    //be monotonic increasing, we can think about binary search to find the largest square length, 
    //then we can use a prefix sum to store all possible sums,
    //then the problem become a binary search problem
    
    //please note how we calculate the sum of a square
    public int maxSideLength(int[][] m, int h) {
        
        int r =m.length, c = m[0].length;
        int[][] pre = new int[r+1][c+1];
        
        for(int i =1; i<=r;i++) {
            for(int j = 1; j<=c;j++) {
                pre[i][j] = pre[i][j-1] + pre[i-1][j] - pre[i-1][j-1] + m[i-1][j-1];
            }
        }
        
        int lo = 0, hi=Math.max(r, c);
        
        while(lo <= hi) {
            int mid = (lo+hi)/2;
            if (isSquareExist(pre,r,c, mid, h)) {
                lo= mid + 1;
            } else {
                hi= mid-1;
            }
        }
        
        return hi;
    }
    
    //this is special one, since what we need is the longest square length
    //so we can use i j compare to length, because when we calculate each pre[i][j],
    //the qualified square bottom right coordination will only be bigger than length because 
    //we added 0 at first row and first column
    private boolean isSquareExist(int[][] pre, int r,int c, int m, int h) {
        for(int i=m; i<=r;i++) {
            for(int j =m;j<=c; j++){
                if (pre[i][j]-pre[i-m][j] -pre[i][j-m] + pre[i-m][j-m] <=h) return true;
            }
        }
        return false;
    }
    
    
    public int maxSideLength_Best(int[][] m, int h) {
        
        int r =m.length, c = m[0].length;
        int[][] pre = new int[r+1][c+1];
        
        int len = 1;
        int res = 0;
        for(int i =1; i<=r;i++) {
            for(int j = 1; j<=c;j++) {
                pre[i][j] = pre[i][j-1] + pre[i-1][j] - pre[i-1][j-1] + m[i-1][j-1];
                
                if (i >= len && j >= len && 
                   pre[i][j] - pre[i-len][j] -pre[i][j-len] + pre[i-len][j-len] <=h) {
                    res = len++;
                }
            }
        }
        return res;
        
        
    }
    
    //binary search
    
}