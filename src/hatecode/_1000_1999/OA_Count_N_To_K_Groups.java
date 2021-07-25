package hatecode._1000_1999;
// Java implementation to count the
// number of ways to divide N in
// groups such that each group
// has K number of elements
import java.util.*;
public class OA_Count_N_To_K_Groups{
 
/*
 * Problem statement:
 * 
 * Given two integers N and K, the task is to count the number of ways to divide
 *  N into K groups of positive integers such that their sum is N and 
 *  the number of elements in groups follows a non-decreasing order 
 *  (i.e group[i] <= group[i+1]).
 * return number of combinations?
 * 
 * N = 8, k = 4.
 * output = 5, 
 * [1, 1, 1, 5], [1, 1, 2, 4], [1, 1, 3, 3], [1, 2, 2, 3], [2, 2, 2, 2]
 */
    
    //thinking process: O(N^K)
    //this function is to say, for current curGroupIdx, we want to at least 
    //allocate prevGroupSize to this group, and we totally have left people, how 
    //many combinations 
    static int helper(int curGroupIdx, int prevGroupSize, int left, int k) {
        // Base Case, when we reach group k, so we check and return
        if (curGroupIdx == k) {
            if (left == 0) return 1;
            else return 0;
        }
        // If N is divides completely into less than k groups
        if (left == 0) return 0;

        int res = 0;

        // Put all possible values greater equal to prev
        //start from helper(1, 1, n - 1, k)
        for (int i = prevGroupSize; i <= left; i++) {
            res += helper(curGroupIdx + 1, i, left - i, k);
        }
        return res;
    }

// Function to count the number of
// ways to divide the number N
    static int countWaystoDivide(int n, int k) {
        //return helper(0, 1, n, k);
        
     // Initialize DP Table as -1
        for (int i = 0; i < 500; i++)
        {
            for (int j = 0; j < 500; j++)
            {
                for (int l = 0; l < 500; l++)
                    mem[i][j][l] = -1;
            }
        }
        return helper2(0, 1, n, k);
    }
    
 // dp[pos][prev][left]  means for current group
    static int [][][]mem = new int[500][500][500];
      
    // thinking process: (O(N^3 * K))
    
    //
    static int helper2(int pos, int prev,int left, int k)
    {
        // Base Case
        if (pos == k) {
            if (left == 0) return 1;
            else return 0;
        }
       
        // if N is divides completely into less than k groups
        if (left == 0) return 0;
      
        // If the subproblem has been solved, use the value
        if (mem[pos][prev][left] != -1) return mem[pos][prev][left];
      
        int res = 0;
       
        // put all possible values
        // greater equal to prev
        for (int i = prev; i <= left; i++)
        {
            res += helper2(pos + 1, i,left - i, k);
        }
        mem[pos][prev][left] = res;
        return mem[pos][prev][left];
    }

// Driver Code
    public static void main(String[] args) {

        System.out.println("5=="  + countWaystoDivide(8, 4));
        System.out.println("164=="+ countWaystoDivide(24, 5));
    }
}