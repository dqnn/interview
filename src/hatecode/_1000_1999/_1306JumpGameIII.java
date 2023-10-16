package hatecode._1000_1999;

public class _1306JumpGameIII {
    /*
    1306. Jump Game III
    Given an array of non-negative integers arr, you are initially positioned at start index of the array. 
    When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.
    
    Notice that you can not jump outside of the array at any time.
    
    Example 1:
    
    Input: arr = [4,2,3,0,3,1,2], start = 5
    Output: true
    Explanation: 
    All possible ways to reach at index 3 with value 0 are: 
    index 5 -> index 4 -> index 1 -> index 3 
    index 5 -> index 6 -> index 4 -> index 1 -> index 3 
    */

        /*
         * thinking process: O(n)/O(n)
         * 
         * the problem is to say: given one integer array and one starting index, you can only jump to 2 
         * position, one is i + A[i], another is i - A[i], you cannot jump to outside indexes
         * 
         * return the jump times you meet 0 in A
         * 
         * we use visited to record each element we visited. 
         * Note: if i - A[i] < 0 or i + A[i] >= A.length, you need to prune these branches instead starting from 0 or A.length -1.
         */

    
        boolean[] visited;
        public boolean canReach_mem(int[] A, int i) {
             if (visited == null) {
                visited = new boolean[A.length];
            } 
            
            if (i < 0 || i >= A.length || visited[i]) return false;
            
            if (A[i] == 0) return true;
            //System.out.println(A.length);
            
           
            visited[i] = true;
            
            int s = i - A[i];
            int e = i + A[i];
            //System.out.println(String.format("%d --- %d", s, e));
            return canReach_mem(A, s) || canReach_mem(A, e);
        }
        /*
            interview friendly
         * thinking process: O(n)/O(1)
         */
        public boolean canReach(int[] A, int i) {
             int n = A.length;
             if (i < 0 || i >= n || A[i] >= n) return false;
             
            if (A[i] == 0) return true;
            int jump = A[i];
             
            A[i] += n;
            return  canReach(A, i - jump) || canReach(A, i + jump);
        }
    }
