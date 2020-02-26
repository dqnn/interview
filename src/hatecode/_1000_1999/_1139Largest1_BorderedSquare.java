package hatecode._1000_1999;
public class _1139Largest1_BorderedSquare {
/*
1139. Largest 1-Bordered Square
Given a 2D grid of 0s and 1s, return the number of elements in the largest square subgrid that has all 1s on its border, or 0 if such a subgrid doesn't exist in the grid.

 

Example 1:

Input: grid = [[1,1,1],[1,0,1],[1,1,1]]
Output: 9
*/
    //thinking process: O(mn)/O(mn)
    
    //so the problem to say given 2D matrix, find the max area where the square
    //border all 1, only 1 or 0 for each cell
    
    //
    public int largest1BorderedSquare(int[][] grid) {
        if (grid == null || grid.length < 1 || grid[0].length < 1) return 0;
        
        int m = grid.length, n = grid[0].length;
        int[][] preSumUp = new int[m+1][n+1];
        int[][] preSumLeft = new int[m+1][n+1];
        for(int j = 0;j<n;j++){
            for(int i = 0;i<m;i++){
                preSumUp[i+1][j+1] = preSumUp[i][j+1] + grid[i][j];
                
            }
        }
        for(int i = 0;i<m;i++){
            for(int j = 0;j<n;j++){
                preSumLeft[i+1][j+1] = preSumLeft[i+1][j] + grid[i][j];
            }
        }
        
        int len = Math.min(m,n);
        while(len > 0){
            for(int i = 0;i<=m-len;i++){
                for(int j = 0;j<=n-len;j++){
                    if(preSumLeft[i+1][j+1+len-1] - preSumLeft[i+1][j] != len) continue;//check top side
                    if(preSumLeft[i+1+len-1][j+1+len-1] - preSumLeft[i+1+len-1][j] != len) continue;//check bottom side
                    if(preSumUp[i+1+len-1][j+1] - preSumUp[i][j+1] != len) continue;//check left side
                    if(preSumUp[i+1+len-1][j+1+len-1] - preSumUp[i][j+1+len-1] != len) continue;//check right side
                    return len*len;
                }
            }
            len--;
        }
        return 0;
    }
    
    public int largest1BorderedSquare_Best_Perf(int[][] grid) {
        int max = 0;
		int r = grid.length;
		int c = grid[0].length;
		int hor[][] = new int[r][c];
		int ver[][] = new int[r][c];
		hor[0][0] = ver[0][0] = 1;

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (grid[i][j] == 0)
					ver[i][j] = hor[i][j] = 0;
				else {
					hor[i][j] = (j == 0) ? 1 : hor[i][j - 1] + 1;
					ver[i][j] = (i == 0) ? 1 : ver[i - 1][j] + 1;
					max = 1;
				}
			}
		}

		for (int i = r - 1; i >= 1; i--) {
			for (int j = c - 1; j >= 1; j--) {
				int small = Math.min(hor[i][j], ver[i][j]);

				while (small > max) {
					if (ver[i][j - small + 1] >= small && hor[i - small + 1][j] >= small) {
						max = small;
					}
					small--;
				}
			}
		}
		return max*max;
    }
}