package hatecode._1000_1999;

public class _1254NumberOfClosedIslands {
    
    /*
    1254. Number of Closed Islands
    Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.
    
    Return the number of closed islands.
    
     
    
    Example 1:
    
    
    
    Input: grid = [
                    [1,1,1,1,1,1,1,0],
                    [1,0,0,0,0,1,1,0],
                    [1,0,1,0,1,1,1,0],
                    [1,0,0,0,0,1,0,1],
                    [1,1,1,1,1,1,1,0]
                ]
    Output: 2
    */

    /*
     * thinking process: O(mn)/O(mn)
     * 
     * the problem is to say: given one matrix (0 land, 1 water), closed islands means 0 are surrouded with water 1.
     * (u, d,l,r) return how many there 
     * 
     * flood fill, starting from each cell 0, we can dfs for 4 directions, help is to determine whether one cell(i,j)
     * is surrounded water.it will change the 0 to 1 to avoid repeating count
     * 
     * 
     */
      
        public int closedIsland(int[][] A) {
            int r = A.length, c =A[0].length;
            
            int res = 0;
            for(int i = 0; i<r;i++) {
                for(int j =0; j< c;j++) {
                    if (A[i][j] == 0){
                        res +=helper(A, i,j);
                    }
                }
            }
            
            return res;
        }
        
        /*
         * helper is to decide whether current cell (i,j) is a closed island or not, if yes return 1 else 0.
         * we do not need worry about the connected cells (land) because they are same island
         * it mark all land as water after visit,
         * even two land cells, like below (1,1) and (2,1), if we start from (1,1) then we will 
         * mark (2,1) as water, it should be fine because they are connected.
         *  1 1 0
         *  0 0 0
         *  0 0 0
         *  1 1 1
         */
        private int helper(int[][] A, int i, int j) {
            int r = A.length, c = A[0].length;
            if (i <0 || i >=r || j < 0 || j>=c) {
                return 0;
            }
            
            if(A[i][j] == 1) return 1;
            
            A[i][j] = 1;
            
            int u = helper(A, i-1, j);
            int d = helper(A, i+1, j);
            int l = helper(A, i, j-1);
            int rt = helper(A, i, j+1);
            
            return u & d & l & rt;
        }
 }