package hatecode._0001_0999;
public class _661ImageSmoother {
/*
661. Image Smoother
An image smoother is a filter of the size 3 x 3 that can be applied to each cell of an image by rounding down the average of the cell and the eight surrounding cells (i.e., the average of the nine cells in the blue smoother). If one or more of the surrounding cells of a cell is not present, we do not consider it in the average (i.e., the average of the four cells in the red smoother).


Given an m x n integer matrix img representing the grayscale of an image, return the image after applying the smoother on each cell of it.
*/
    /*
     * thinking process: O(mn)/O(mn)
     * 
     * A[i][j]= sum(9 cells around (i, j))/count
     */
    public int[][] imageSmoother(int[][] A) {
        int r = A.length, c = A[0].length;
        
        int[][] res = new int[r][c];
        int[][] dirs = {{-1, 0}, {-1, 1}, {-1,-1}, {0, -1}, {0, 1}, {1, 1}, {1, -1}, {1, 0}};
        for(int i = 0; i< r; i++) {
            for(int j = 0; j<c; j++) {
                int sum = A[i][j], count = 1;
                for(int[] d:dirs) {
                    int x = i +d[0];
                    int y = j +d[1];
                    if (x >=0 &&x < r && y >=0 && y < c) {
                        sum += A[x][y];
                        count++;
                    }
                    
                }
                
                res[i][j] = sum/count;
            }
        }
        return res;
    }
    /*
     * better since it is using middle 16 bytest o store sum
     */
    public int[][] imageSmoother_Better(int[][] M) {
        // in-place solution
        int m = M.length, n = M[0].length;
        if (m == 0 || n == 0)
            return new int[0][0];
        int[][] dirs = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j) {
                int sum = M[i][j], cnt = 1;
                for (int k = 0; k < dirs.length; ++k) {
                    int x = i + dirs[k][0], y = j + dirs[k][1];
                    if (x < 0 || x > m - 1 || y < 0 || y > n - 1)
                        continue;
                    sum += (M[x][y] & 0xFF);
                    cnt++;
                }
                M[i][j] |= ((sum / cnt) << 8);
            }

        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                M[i][j] >>= 8;
        return M;
    }
}