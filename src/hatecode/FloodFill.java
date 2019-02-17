package hatecode;
public class FloodFill {
/*
733. Flood Fill
An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).

Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.

To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the newColor.

At the end, return the modified image.

Example 1:
Input: 
image = [[1,1,1],[1,1,0],[1,0,1]]
sr = 1, sc = 1, newColor = 2
Output: [[2,2,2],[2,2,0],[2,0,1]]
*/
    //O(MN)/O(MN)
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length < 1 || image[0].length < 1) return new int[0][0];
        if (image[sr][sc] == newColor) return image;
        helper(image, sr, sc, newColor, image[sr][sc]);
        
        return image;
    }
    
    public void helper(int[][] image, int x, int y, int newColor, int old) {
        image[x][y] = newColor;
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
        for(int[] dir : dirs) {
            int i = x + dir[0];
            int j = y + dir[1];
            if (i >= 0 && i < image.length && j >= 0 && j < image[0].length && image[i][j] == old)
                helper(image, i, j, newColor, old);
        }
    }
//another way is to use BFS, this is text-book problem
}