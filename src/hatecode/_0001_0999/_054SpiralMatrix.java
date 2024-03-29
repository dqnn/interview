package hatecode._0001_0999;

import java.util.*;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SpiralMatrix
 * Creator : professorX
 * Date : Sep, 2018
 * Description : 54. Spiral Matrix
 */
public class _054SpiralMatrix {
    /**
     * For example,
     Given the following matrix:

     [
     [ 1, 2, 3 ],
     [ 4, 5, 6 ],
     [ 7, 8, 9 ]
     ]
     You should return [1,2,3,6,9,8,7,4,5].

     time : O(n * m)     n * m : 总元素个数
     space : O(n * m)

     * @param g
     * @return
     */
    
    /*
     * anothe interview friendly soliution
     * 
     * so we use L R U D to describe the boundary when we visit the matrix, d means the direction 
     * one tricky part here is to understand  when we reach the limit, we need to change the boundary,
     * 
     * for example ,
     * if we reach the most right column, it means we can not back to that row, then row (u += 1).
     * if we reach the most bottom row, it means we can not back to that most right column, then R (R -= 1).
     * 
     */
    public List<Integer> spiralOrder_Easy(int[][] A) {
        if (A == null || A.length < 1 || A[0].length < 1) return new ArrayList<>();
        
        int m = A.length, n = A[0].length;
        
        int i = 0, j = 0;
        int L = 0, R = n - 1, U = 0, D = m - 1;
        
        char d ='R';
        List<Integer> res = new ArrayList<>();
        while(i >=U && i <= D && j >=L && j <= R) {
            res.add(A[i][j]);
            switch(d) {
                case 'R':
                    if(j == R) {
                        d = 'D';
                        i++;
                        U= U + 1;
                    } else j++;
                    break;
                case 'D':
                    if(i == D) {
                        d ='L';
                        j--;
                        R = R - 1;
                    }else i++;
                    break;
                case 'L':
                    if (j == L) {
                        d = 'U';
                        i--;
                        D = D - 1;
                    } else j--;
                    break;
                case 'U':
                    if (i == U) {
                        d = 'R';
                        j++;
                        L = L + 1;
                    } else i --;
                break;
            }
        }
        
        return res;
    }
    
    
    
     //interview friendly, 
    //this is much simpler solution, up means top start point, down means bottom start point, 
    // this means direction, same to left and right
    public List<Integer> spiralOrder(int[][] g) {
        List<Integer> res = new LinkedList<>(); 
        if (g == null || g.length == 0) return res;
        int r = g.length, c = g[0].length;
        int up = 0,  down = r - 1;
        int left = 0, right = c - 1;
        while (res.size() < r * c) {
            for (int j = left; j <= right && res.size() < r * c; j++)
                res.add(g[up][j]);
            
            for (int i = up + 1; i <= down - 1 && res.size() < r * c; i++)
                res.add(g[i][right]);
                     
            for (int j = right; j >= left && res.size() < r * c; j--)
                res.add(g[down][j]);
                        
            for (int i = down - 1; i >= up + 1 && res.size() < r * c; i--) 
                res.add(g[i][left]);
                
            left++; right--; up++; down--; 
        }
        return res;
    }
    
    //thinking process:
    
    //the problem is to add element in a 2D matrix to a list by a 
    //spiral pattern
    
    //so we found for each circle, each col row will -- for each loop, 
    //and second is the same loop as previous one, so what we need to is to
    //calc the i and j
    
    //since i and j will change inside the loop so we use while as top loop
    //inside the top loop, we use 4 for loop to get each element,
    //this 4 loop controls the directions. 
    
    //there is one exception case that rowBegin, rowEnd, colBegin, colEnd
    //will overlap, so if equals, we still need to print.
    public List<Integer> spiralOrder_Old(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return res;
        }
        int rowBegin = 0;
        int rowEnd = matrix.length - 1;
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;

        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            for (int i = colBegin; i <= colEnd; i++) {
                res.add(matrix[rowBegin][i]);
            }
            rowBegin++;

            for (int i = rowBegin; i <= rowEnd; i++) {
                res.add(matrix[i][colEnd]);
            }
            colEnd--;
            
            //in some case like colE > rowE
            
            //so we want to print the bottom row since rowB++ so 
            //we want to check that this row never be printed
            //if we don't do this check, we will have duplicate number
/*
[1,2,3,4],
[5,6,7,8],
[9,10,11,12]

if we have if vs no if
[1,2,3,4,8,12,11,10,9,5,6,7,6]
[1,2,3,4,8,12,11,10,9,5,6,7]
 */
            if (rowBegin <= rowEnd) {
                for (int i = colEnd; i >= colBegin; i--) {
                    res.add(matrix[rowEnd][i]);
                }
            }
            //no matter we can enter 68 or not, we still need rowE--
            //because we have to enter another case.
            rowEnd--;

            if (colBegin <= colEnd) {
                for (int i = rowEnd; i >= rowBegin; i--) {
                    res.add(matrix[i][colBegin]);
                }
            }
            colBegin++;
        }

        return res;
    }
}
