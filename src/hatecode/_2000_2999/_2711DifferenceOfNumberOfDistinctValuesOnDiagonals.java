public class _2711DifferenceOfNumberOfDistinctValuesOnDiagonals {

/*
2711. Difference of Number of Distinct Values on Diagonals

    Given a 0-indexed 2D grid of size m x n, you should find the matrix answer of size m x n.

The value of each cell (r, c) of the matrix answer is calculated in the following way:

Let topLeft[r][c] be the number of distinct values in the top-left diagonal of the cell (r, c) in the matrix grid.
Let bottomRight[r][c] be the number of distinct values in the bottom-right diagonal of the cell (r, c) in the matrix grid.
Then answer[r][c] = |topLeft[r][c] - bottomRight[r][c]|.

Return the matrix answer.

A matrix diagonal is a diagonal line of cells starting from some cell in either the topmost row or leftmost column and going in the bottom-right direction until reaching the matrix's end.

A cell (r1, c1) belongs to the top-left diagonal of the cell (r, c), if both belong to the same diagonal and r1 < r. Similarly is defined bottom-right diagonal.

 

Example 1:



Input: grid = [[1,2,3],[3,1,5],[3,2,1]]
Output: [[1,1,0],[1,0,1],[0,1,1]]
    */

   /*
    thinking process: O(mn) 
   */
    
    public int[][] differenceOfDistinctValues(int[][] A) {
        int r = A.length, c = A[0].length;
        
        int[][] res = new int[r][c];
        for(int j = 0; j< c; j++) {
            helper(A, 0, j, res);
        }
        
        for(int i = 1; i < r; i++) {
            helper(A, i, 0, res);
        }
        
        return res;
    }
    
    private void helper(int[][] A, int i, int j, int[][] res) {
        int r = A.length,  c = A[0].length;
        Set<Integer> tl = new HashSet<>();
        Set<Integer> br = new HashSet<>();
        for(int d = 0; i+d < r && j +d < c; d++) {
            res[i+d][j+d] = tl.size();
            tl.add(A[i+d][j+d]);
        }
        
        for (int d = Math.min(r - i, c - j) - 1; d >= 0; --d) {
            res[i+d][j+d] = Math.abs(res[i+d][j+d] - br.size());
            br.add(A[i+d][j+d]);
        }
    }
    
    
    // BF 
    /*
       O(rc*max(r,c))/O(min(r, c))
    */
    public int[][] differenceOfDistinctValues_BF(int[][] A) {
        int r = A.length, c = A[0].length;
        
        int[][] res = new int[r][c];
        for(int i = 0; i< r; i++) {
            for(int j = 0; j< c; j++) {
                Set<Integer> topLeft = new HashSet<>();
                int p = i-1, q = j - 1;
                while(p >=0 && q >= 0) {
                    topLeft.add(A[p][q]);
                    p--;
                    q--;
                }
                
                Set<Integer> br = new HashSet<>();
                p = i+1;
                q = j + 1;
                while(p <r && q <c) {
                    br.add(A[p][q]);
                    p++;
                    q++;
                }
                
                res[i][j] = Math.abs(topLeft.size() - br.size());
            }
        }
        
        return res;
    }
    
    
    
}