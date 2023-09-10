package hatecode._0001_0999;
public class _498DiagonalTraverse {
    /*
    498. Diagonal Traverse
Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.

 

Example 1:


Input: mat = [[1,2],[4,5]]
Output: [1,2,4,7,5,3,6,8,9]
      1. 2
      3. 4
    */
    
    //thinking process: O(mn)/O(1)
    
    //diag line has some laws like even or odd number, here we leverage this
    //to decide which direction, up right or left down. 
    
    //note that in if else we process edge case
    public int[] findDiagonalOrder(int[][] m) {
        if (m == null || m.length < 1 || m[0].length < 1) return new int[0];
        
        
        int i = 0, j= 0;
        int r = m.length, c=m[0].length;
        int[] res = new int[r * c];
        
        for(int idx = 0; idx< r * c; idx++) {
            res[idx] = m[i][j];
            //up right
            //diag
            if ( (i + j) % 2 == 0) {
                // here means top right, we need to process j first because
                //if put line 44 here, index will overflow because j ++
                if (j == c - 1) {
                    i++;
                } else if (i == 0) {
                    j++;
                } else {
                    i--;
                    j++;
                }
            //left down
            } else {
                //here means 
                if (i == r - 1) {
                    j++;
                } else if (j == 0) {
                    i++;
                } else {
                    i++;
                    j--;
                }
            }
        }
        
        return res;
    }

    /*
     * there is the direction control way, for such kind of problem, just use direction so it will be easier to control
     */
    public int[] findDiagonalOrder_Direction_Control(int[][] A) {
        if(A == null || A.length < 1 || A[0].length < 1) return new int[0];
        
        int m = A.length; 
        int n = A[0].length;
        
        int i = 0, j = 0;
        int id = 0;
        int[] res = new int[m *n];
        char d = 'U';
        while(i >=0 && i < m && j >=0 && j < n) {
            res[id++] = A[i][j];
            
            switch(d) {
                case 'U':
                    if(j == n -1 || i == 0) {
                        d = 'D';
                        if(j == n-1) {
                            i++;
                        } else j++;
                    } else {
                        i--;
                        j++;
                    }
                    break;
                case 'D':
                    if(i == m - 1 
|| j == 0) {
                        d = 'U';
                        if(i == m-1) {
                            j++;
                        } else i++;
                    } else {
                        i++;
                        j--;
                    }
                    break;
                default:break;
            }
        }
        
        return res;
    }
}