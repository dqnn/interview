package hatecode;
public class CampusBikesII {
    /*
     * 1066. Campus Bikes II 
     * On a campus represented as a 2D grid, there are N
     * workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on
     * this grid.
     * 
     * We assign one unique bike to each worker so that the sum of the Manhattan
     * distances between each worker and their assigned bike is minimized.
     * 
     * The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) =
     * |p1.x - p2.x| + |p1.y - p2.y|.
     * 
     * Return the minimum possible sum of Manhattan distances between each worker
     * and their assigned bike.
     * 
     * Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]] Output: 6
     */
    //Same as 1057 but this requires overall sum is the minimal, so we have to try every 
    //for this question, we have PQ solution and DP solution but they should have the same 
    //complexity on Time, 
    public int assignBikes(int[][] workers, int[][] bikes) {
        helper(workers, 0, bikes, new boolean[bikes.length], 0);
        return min;
    }

    //global variable to record the min overall distance sum 
    int min = Integer.MAX_VALUE;
    
    //helper here is to choose anyone of them, what's the minimal sum of the combinations
    //Time Complexity: T(w, b) = b * T(w-1, b-1), 
    void helper(int[][] workers, int i, int[][] bikes, boolean[] used, int sum) {
        //for every combination, we try to find the min
        if (i == workers.length) {
            min = Math.min(min, sum);
            return;
        }
        
        if (sum > min) return;  // early termination
        
        for (int j = 0; j < bikes.length; ++j) {
            if (used[j]) continue;
            used[j] = true;
            helper(workers, i+1, bikes, used, sum + calcDistance(workers[i], bikes[j]));
            used[j] = false;
        }
    }
    
    private int calcDistance(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }

}