package hatecode._0001_0999;

import java.util.*;

public class _711NumberOfDistinctIslandsII {
    /*
    711. Number of Distinct Islands II
    You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
    
    An island is considered to be the same as another if they have the same shape, or have the same shape after rotation (90, 180, or 270 degrees only) or reflection (left/right direction or up/down direction).
    
    Return the number of distinct islands.
    Input: grid = [[1,1,0,0,0],[1,0,0,0,0],[0,0,0,0,1],[0,0,0,1,1]]
    Output: 1
    Explanation: The two islands are considered the same because if we make a 180 degrees clockwise rotation on the first island, then two islands will have the same shapes.
    
    */
        public int numDistinctIslands2(int[][] A) {
            int r = A.length, c= A[0].length;
            
            Set<String> set = new HashSet<>();
            
            for(int i = 0; i< r; i++) {
                for(int j = 0; j<c; j++) {
                    if (A[i][j] == 1) {
                        List<int[]> list = new ArrayList<>();
                        helper(A, i,j, list);
                        String key = normalize(list);
                        set.add(key);
                    }
                }
            }
            
            return set.size();
        }
        
        private void helper(int[][] A, int i, int j, List<int[]> list) {
            int r = A.length, c= A[0].length;
            list.add(new int[]{i,j});
            A[i][j] = 0;
            
            int[][] dirs = {{-1,0}, {1, 0}, {0,1}, {0, -1}};
            for(int[] d: dirs) {
                int x = i + d[0];
                int y = j + d[1];
                if (x >=0 && x <r && y >=0 && y < c && A[x][y] == 1) {
                    helper(A, x, y, list);
                }
            }
        }
        
        private String normalize(List<int[]> A) {
            
            
            int[][] dirs = {{-1,1}, {1,1}, {1,-1}, {-1,-1}};
            /*
            旋转0度，90度，180度，270度后的坐标分别为：(r, c), (-c, r), (-r, -c), (c, -r)
            x轴镜像，y轴镜像，斜线(y = x)镜像，反斜线(y = -x)镜像的坐标分别为：(r, -c), (-r, c), (c, r), (-c, -r)
            */
            List<String> forms = new ArrayList<>();
            for(int[] d : dirs) {
                
                List<int[]> list1 = new ArrayList();
                List<int[]> list2 = new ArrayList();
                for(int[] a : A) {
                    int x = a[0], y = a[1];
                    list1.add(new int[]{x * d[0], y * d[1]});
                    list2.add(new int[]{y * d[1], x * d[0]});
                }
                
                forms.add(getKey(list1));
                forms.add(getKey(list2));
            }
            Collections.sort(forms);
            return forms.get(0);
            
        }
        
        public String getKey(List<int[]> cells) {
            // Sort the cells and the take the first cell in sorted order as the origin
            Collections.sort(cells, (a, b)->(a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0])));
    
            StringBuilder key = new StringBuilder();
    
            int originX = cells.get(0)[0], originY = cells.get(0)[1];
            // Generate the key 
            for (int[] cell : cells) {
                key.append((cell[0] - originX) + ":" + (cell[1] - originY));
            }
            return key.toString();
        }
    }