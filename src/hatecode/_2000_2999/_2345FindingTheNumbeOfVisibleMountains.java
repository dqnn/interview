public class _2345FindingTheNumbeOfVisibleMountains {
    
    /*
    2345. Finding the Number of Visible Mountains

    You are given a 0-indexed 2D integer array peaks where peaks[i] = [xi, yi] states that mountain i has a peak at coordinates (xi, yi). A mountain can be described as a right-angled isosceles triangle, with its base along the x-axis and a right angle at its peak. More formally, the gradients of ascending and descending the mountain are 1 and -1 respectively.

A mountain is considered visible if its peak does not lie within another mountain (including the border of other mountains).

Return the number of visible mountains.

 

Example 1:


Input: peaks = [[2,2],[6,3],[5,4]]
Output: 2
    
    
    */
    /*
     * thinking process: O(n)/O(n)
     * 
     * the problem is to say: given a 2D array [x, y] as mountain peak coordination, left and right have same distance with the height.
     * 
     * return how many montains can be visible,  a mountain can be visible if it is no included in another montain from left right
     * 
     * it is easy to thinking about stack, but stack is not good for 
     */
    public int visibleMountains_Best(int[][] A) {
        if (A == null || A.length < 1 || A[0].length < 1) return 0;
        
        int n = A.length;
        for(int i = 0; i< n; i++) {
            int temp = A[i][0];
            A[i][0] -= A[i][1];
            A[i][1] += temp;
        }
        
        Arrays.sort(A, (a ,b)->(a[0] == b[0] ? Integer.compare(a[1], b[1]) :Integer.compare(a[0], b[0]) ));
        
        int res = 0;
        int i = 0;
        
        while(i < n) {
            res++;
            if (i == n - 1) return res;
            
            if (A[i][0] == A[i+1][0]) res--;
            int curEnd = A[i][1];
            // here is to process right
            while(i < n - 1 && curEnd >= A[i+1][1]) i++;
            i++;
        }
        
        return res;
        
    }
    
}