public class _2713MaximumStrictlyIncreasingCellsInAMatrix {
    /*
    2713. Maximum Strictly Increasing Cells in a Matrix
    Given a 1-indexed m x n integer matrix mat, you can select any cell in the matrix as your starting cell.
    
    From the starting cell, you can move to any other cell in the same row or column, but only if the value of the destination cell is strictly greater than the value of the current cell. You can repeat this process as many times as possible, moving from cell to cell until you can no longer make any moves.
    
    Your task is to find the maximum number of cells that you can visit in the matrix by starting from some cell.
    
    Return an integer denoting the maximum number of cells that can be visited.
    
     
    
    Example 1:
    
    
    
    Input: mat = [[3,1],[3,4]]
    Output: 2
    Explanation: The image shows how we can visit 2 cells starting from row 1, column 2. It can be shown that we cannot visit more than 2 cells no matter where we start from, so the answer is 2. 
    Example 2:
    
    
    
    Input: mat = [[1,1],[1,1]]
    Output: 1
    Explanation: Since the cells must be strictly increasing, we can only visit one cell in this example. 
    Example 3:
    
    
    
    Input: mat = [[3,1,6],[-9,5,7]]
    Output: 4
    Explanation: The image above shows how we can visit 4 cells starting from row 2, column 1. It can be shown that we cannot visit more than 4 cells no matter where we start from, so the answer is 4. 
    */

    /*
    thinking process: O(rc * lgrc)/O(rc + r + c)

    the problem is to say: given one matrix A, you can start from any cell and jump to another cell
    as long as it is same col or row and it is strictly greater than previous cell.

    return how many steps you can go from 1st cell.  counting from 1
    


     */ 
        public int maxIncreasingCells(int[][] A) {
            if (A == null || A.length < 1 || A[0].length < 1) return 0;
            
            int r = A.length, c = A[0].length;
            TreeMap<Integer, List<int[]>> map = new TreeMap<>();
            
            for(int i = 0; i< r; i++) {
                for(int j = 0; j< c;j++) {
                    map.computeIfAbsent(A[i][j], v->new ArrayList<>()).add(new int[]{i, j});
                }
            }
            
            int[] row = new int[r];
            int[] col = new int[c];
            int res = 0;
            for(Integer k: map.keySet()) {
                List<int[]> list = map.get(k);
                int l = list.size();
                int[] max = new int[l];
                
                for(int i = 0; i<l; i ++) {
                    int x = list.get(i)[0], y = list.get(i)[1];
                    max[i] = Math.max(row[x], col[y]) + 1;
                    res = Math.max(res, max[i]);
                }
                
                //avoid all are the same but some not updated
                for(int i= 0; i<l;i++) {
                    int x = list.get(i)[0], y = list.get(i)[1];
                    col[y] = Math.max(col[y], max[i]);
                    row[x] = Math.max(row[x], max[i]);
                }
            }
            
            return res;
        }



    // BF solution
    int res = 0;
    public int maxIncreasingCells_BF(int[][] A) {
        if (A == null || A.length < 1 || A[0].length < 1) return 0;
        
        int r = A.length, c = A[0].length;
        int[][] visited = new int[r][c];
        
        for(int i = 0; i< r; i++) {
            for(int j = 0; j< c;j++) {
                helper(A, i, j, visited, r, c, 1);
            }
        }
        
        return res;
    }
    
    private void helper(int[][] A, int x, int y, int[][] visited, int r, int c, int step) {
        if (visited[x][y] >= step) return;
        
        visited[x][y] = step;
        res = Math.max(res, step);
        
        for(int i = 0; i< r; i++) {
            if (i == x) continue;
            if (A[i][y] > A[x][y]) {
                helper(A, i, y, visited, r,c, step + 1);
            }
        }
        
        for(int j = 0; j<c; j++) {
            if (j == y) continue;
            if (A[x][j] > A[x][y]) {
                helper(A, x, j, visited, r,c, step + 1);
            }
        }
    }
        
    }