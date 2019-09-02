package hatecode;

import java.util.*;
public class PrisonCellsAfterNDays {
/*
957. Prison Cells After N Days
There are 8 prison cells in a row, and each cell is either occupied or vacant.

Each day, whether the cell is occupied or vacant changes according to the following rules:

If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
Otherwise, it becomes vacant.
(Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.)

We describe the current state of the prison in the following way: cells[i] == 1 if the i-th cell is occupied, else cells[i] == 0.

Given the initial state of the prison, return the state of the prison after N days (and N such changes described above.)

 

Example 1:

Input: cells = [0,1,0,1,1,0,0,1], N = 7
Output: [0,0,1,1,0,0,0,0]
Explanation: 
The following table summarizes the state of the prison on each day:
Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
*/
    //interview friendly, thinking process: 
    
    //so we have an array, and it will change to 1 if both neighbours
    //are the same, not will be 0, N is the times it needs to change,
    //return the result. note: first and last digit does not change
    
    
    //this is typical question that it will have period，since the
    //states will only have 2^6 = 64 states overall, so the brutal force
    //is just to simulate the whole computation, then thinking about optimization,
    //with period, so we can use Map to store all possible results, if we meet again,
    //which means the diff is the length of the period then we can take advantage of 
    //the period to advance the last cycle, 
    
    //another optimization is 1, 7, 14, currently only brute force
    //no math proof
    public int[] prisonAfterNDays(int[] cells, int N) {
        if (cells == null || cells.length < 1) return new int[0];
        
        Map<String, Integer> visited = new HashMap<>();
        //this is optimization that 
        //for (N = (N - 1) % 14 + 1; N > 0; ) {
        while(N > 0) {
            visited.put(Arrays.toString(cells), N);
            int[] another = new int[8];
            for(int i =1; i< 7; i++) {
                another[i] = cells[i-1] == cells[i+1] ? 1: 0;
            }
            
            cells = another;
            N--;
            if(visited.containsKey(Arrays.toString(cells))) {
                N %= visited.get(Arrays.toString(cells)) - N;
            }
        }
        return cells;
    }
    //this applies dynamic arrays of cells,and we get the cycle without
    //a map
    public int[] prisonAfterNDays_NoMem(int[] c, int N) {
        int[] f_c = new int[c.length], next_cycle = new int[c.length];
        for (int cycle = 0; N-- > 0; c = next_cycle.clone(), ++cycle) {
          for (int i = 1; i < c.length - 1; ++i) 
              next_cycle[i] = (c[i - 1] == c[i + 1] ? 1 : 0);
          
          if (cycle == 0) f_c = next_cycle.clone();
          else if (Arrays.equals(next_cycle, f_c)) N %= cycle;
        }
        return c;
      }
    
    public int[] prisonAfterNDays_BestPerformance(int[] cells, int N) {
        if(N == 0) {
            return cells;
        }
        N = N % 14;
        N = N == 0 ? 14 : N;
        for(int d = 0; d < N; d++) {
            int left = cells[0];
            for(int i = 1; i < 7; i++) {
                int t = cells[i];
                cells[i] = (left ^ cells[i + 1]) ^ 1;
                left = t;
            }
            cells[0] = 0;
            cells[7] = 0;
        }
        return cells;
    }
}