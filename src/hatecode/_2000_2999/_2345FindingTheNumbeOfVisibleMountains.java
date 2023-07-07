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
     * thinking process: O(nlgn)/O(1) interview friendly
     * 
     * the problem is to say: given a 2D array [x, y] as mountain peak coordination, left and right have same distance with the height.
     * 
     * return how many montains can be visible,  a mountain can be visible if it is no included in another montain from left right
     * 
     * it is easy to thinking about stack, but stack is not good for process right part which means not in stack. 
     * 
     * we convert A to intervals, [start, end], we do not need to care about peak, so the problem could be:
     * 1. previous interval contains next,  like following 
     * 
     *  |_______________________|
     *  |______|
     *    |_________|
     *       |__________________|
     * 
     * 2. all contained, both will be dropped
     *   |__________|
     *   |__________|
     * 
     * 3. following  cases will be good 
     * 
     * |___________|
     *   |____________|
     * 
     * so it is essentially one variant of interval problem 
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

            // no need to compare. no overlap on 'end', we can return early
            if (i == n - 1) return res;
            //means previous one will be out, nex one will be determined by right condition
            if (A[i][0] == A[i+1][0]) res--;
            int curEnd = A[i][1];
            // here is to process right
            while(i < n - 1 && curEnd >= A[i+1][1]) i++;
            i++;
        }
        
        return res;
        
    }


    /*
     * thinking process: O(nlgn)/O(n)
     * 
     * we use stack to store the qualified mountains, whether one mountain conains another one
     * 
     * 
     * 
     */
    public int visibleMountains(int[][] peaks) {
        Arrays.sort(peaks,(a,b)->a[0]==b[0]?a[1]-b[1]:a[0]-b[0]);
        Stack<Integer> stack=new Stack<>();
        
        for(int i=0;i<peaks.length;i++){
            while(!stack.isEmpty() && helper(stack.peek(),i,peaks)){
                stack.pop();
            }
            if(!stack.isEmpty() && helper(i,stack.peek(),peaks) || i>0 && helper(i,i-1,peaks) ) continue;
            stack.push(i);
        }
        return stack.size();
      }
  
  
      private boolean helper(int prev ,int curr,int[][] peaks){
        int x1=peaks[prev][0];
        int y1=peaks[prev][1];
        int x2=peaks[curr][0];
        int y2=peaks[curr][1];
        return x2+y2>=x1+y1 && x2-y2<=x1-y1;
      }
    
}