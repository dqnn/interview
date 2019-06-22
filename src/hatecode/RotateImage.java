package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : RotateImage
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 48. Rotate Image
 */
public class RotateImage {
    /**
ou are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Note:

You have to rotate the image in-place, which means you have to modify the 
input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

Example 1:

Given input matrix = 
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

rotate the input matrix in-place such that it becomes:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
Example 2:

Given input matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
], 

rotate the input matrix in-place such that it becomes:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]

     time : O(n * m)
     space : O(1)
     * @param matrix
     */
    //this should be easiest and interview friendly 
    //we (i, j)->(j, i) firstly then for each row we change the elements
    public void rotate2(int[][] m) {
        //edge case
        if (m == null || m.length == 1 && m[0].length == 1) {
            return;
        }
        int r = m.length, c = m[0].length;
        // i j <-> j i
        for(int i = 0; i< r; i++) {
            for(int j = i; j < c; j++) {
                int temp = m[i][j];
                m[i][j] = m[j][i];
                m[j][i] = temp;
            }
        }
        // we swap the elements in one row
        for(int i = 0; i< r;i++){
            int left = 0, right = c - 1;
            while(left < right) {
                int temp = m[i][left];
                m[i][left++] = m[i][right];
                m[i][right--] = temp;
            }
        }
    }
}
