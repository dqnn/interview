package hatecode._2000_2999;

public class _2101DetonateTheMaximumBombs {
    /*
    2101. Detonate the Maximum Bombs
    Input: bombs = [[2,1,3],[6,1,4]]
    Output: 2
    You are given a list of bombs. The range of a bomb is defined as the area where its effect can be felt. This area is in the shape of a circle with the center as the location of the bomb.
    
    The bombs are represented by a 0-indexed 2D integer array bombs where bombs[i] = [xi, yi, ri]. xi and yi denote the X-coordinate and Y-coordinate of the location of the ith bomb, whereas ri denotes the radius of its range.
    
    You may choose to detonate a single bomb. When a bomb is detonated, it will detonate all bombs that lie in its range. These bombs will further detonate the bombs that lie in their ranges.
    
    Given the list of bombs, return the maximum number of bombs that can be detonated if you are allowed to detonate only one bomb.
    
    */
        
        /*
         * thinking process: O(n^2/O(n))
         * 
         * the problem is to say: 
         * given tuple as [i, j, r] as one circle as bomb and r means the radius, you can detonate on
         */
        public int maximumDetonation(int[][] A) {
            int max = 1;
            for(int i = 0; i<A.length; i++) {
                max = Math.max(max, helper(A,i, new boolean[A.length]));
                count = 0;
            }
            
            return max;
        }
        
        int count  = 0;
        private int helper(int[][] A, int i, boolean[] visited) {
            count++;
            int n = A.length;
            visited[i] = true;
            
            for(int j = 0; j < n; j++) {
                if (i == j || visited[j]) continue;
                long dx = A[i][0] - A[j][0], dy = A[i][1] - A[j][1], r = A[i][2];
                if ((dx * dx + dy* dy) <= r * r) {
                    visited[j] = true;
                    helper(A, j, visited);
                }
                
            }
            return count;
        }
    }
