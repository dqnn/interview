package hatecode;
import java.util.*;
public class CouplesHoldingHands {
    /*
     * 765. Couples Holding Hands N couples sit in 2N seats arranged in a row and
     * want to hold hands. We want to know the minimum number of swaps so that every
     * couple is sitting side by side. A swap consists of choosing any two people,
     * then they stand up and switch seats.
     * 
     * The people and seats are represented by an integer from 0 to 2N-1, the
     * couples are numbered in order, the first couple being (0, 1), the second
     * couple being (2, 3), and so on with the last couple being (2N-2, 2N-1).
     * 
     * The couples' initial seating is given by row[i] being the value of the person
     * who is initially sitting in the i-th seat.
     * 
     * Example 1:
     * 
     * Input: row = [0, 2, 1, 3] Output: 1
     */
    //O(N)/O(N)
    //thinking process: 
    
    //given an array, 0,..2N-1, 0,1 are couples, as so on, they are not seat corectly, try to 
    //swap them, then they can seat together, return the minimal swap times
    public static int minSwapsCouples(int[] row) {
        int N = row.length / 2;
        //couples[x] = {i, j} means that
        //couple #x is at couches i and j ( couch starts from 1)
        int[][] couples = new int[N][2];
        
        //add the index to couples 2D array,starts from 1 thats why we +1
        //[4, 2, 5, 1, 3, 0]-->[[2, 3], [1, 3], [1, 2]]
        //we have 6 people, 3 couples, 3 couches, 1,2,3 couches
        //
        for (int i = 0; i < row.length; ++i) add(couples[row[i]/2], i/2 + 1);

        //adj[x] = {i, j} means that
        //x-th couch connected to couches i, j (all 1 indexed) by couples
        //[4, 2, 5, 1, 3, 0]-->[[0, 0], [3, 2], [3, 1], [2, 1]]
        int[][] adj = new int[N+1][2];
        for (int[] couple: couples) {
            add(adj[couple[0]], couple[1]);
            add(adj[couple[1]], couple[0]);
        }

        // The answer will be N minus the number of cycles in adj.
        int res = N;
        // For each couch (1 indexed)
        for (int r = 1; r <= N; ++r) {
            // If this couch has no people needing to be paired, continue
            if (adj[r][0] == 0 && adj[r][1] == 0)
                continue;

            // Otherwise, there is a cycle starting at couch r.
            // We will use two pointers x, y with y faster than x by one turn.
            res--;
            int x = r, y = pop(adj[r]);
            // When y reaches the start 'r', we've reached the end of the cycle.
            while (y != r) {
                // We are at some couch with edges going to 'x' and 'new'.
                // We remove the previous edge, since we came from x.
                remove(adj[y], x);

                // We update x, y appropriately: y becomes new and x becomes y.
                x = y;
                y = pop(adj[y]);
            }
        }
        return res;
    }

    // Replace the next zero element with x.
    public static void add(int[] pair, int x) {
        pair[pair[0] == 0 ? 0 : 1] = x;
    }

    // Remove x from pair, replacing it with zero.
    public static void remove(int[] pair, int x) {
        pair[pair[0] == x ? 0 : 1] = 0;
    }

    // Remove the next non-zero element from pair, replacing it with zero.
    public static int pop(int[] pair) {
        int x = pair[0];
        if (x != 0) {
            pair[0] = 0;
        } else {
            x = pair[1];
            pair[1] = 0;
        }
        return x;
    }
    
   
    
    //union find solutions,
    //so starting from 0 to N-1. N is half of row, we know 2i and 2i+1 value and we union 
    //them together, 
    public int minSwaps2Couples(int[] row) {
        int N = row.length/ 2;
        DSU uf = new DSU(N);
        for (int i = 0; i < N; i++) {
            int a = row[2*i];
            int b = row[2*i + 1];
            uf.union(a/2, b/2);
        }
        // uf.count means how many isolated components
        return N - uf.count;
    }
    
    class DSU {
        int[] parents;
        int count;
        public DSU(int n) {
            parents = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
            count = n;
        }
        
        private int find(int i) {
            if (parents[i] == i) {
                return i;
            }
            parents[i] = find(parents[i]);
            return parents[i];
        }
        
        public void union(int i, int j) {
            int a = find(i);
            int b = find(j);
            if (a != b) {
                parents[a] = b;
                count--;
            }
        }
    }
    //simple hashMap solutions
    //this is super awesome solution,
    //so given a row and we want to re-arrange them to proper seats, 
    //not 1, 0, 2,3 still good, they do not need to be sorted, so first as [4, 2, 5, 1, 3, 0]
    //example, our map will be {0=3, 1=5, 2=4, 3=0, 4=2, 5=1}, mapping row[i]<->row[i+1] i + 2 
    //each time, 
    //then we in another loop, start from 0, we know which is currently next, and we where is its
    //"couple" position, then we change these two position and we go next. 
    
    //but can we prove it is correct? 
    public static int minSwapsCouples3(int[] row) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < row.length; i += 2) {
            map.put(row[i], row[i + 1]);
            map.put(row[i + 1], row[i]);
        }
        //[4, 2, 5, 1, 3, 0] ->map = {0=3, 1=5, 2=4, 3=0, 4=2, 5=1}
        int count = 0;
        for (int index = 0; index < row.length; index += 2) {
            if (map.get(index) != index + 1) {
                int curr = map.get(index);
                int next = map.get(index + 1);
                map.put(curr, next);
                map.put(next, curr);
                // we will never access again, don't need to modify it
                // map.put(index, index + 1);
                // map.put(index + 1, index);
                count++;
            }
        }
        return count;
    }
    
    public static void main(String[] args) {
        //4->3->1->2->5->0->4
        System.out.println(minSwapsCouples(new int[] {4, 2, 5, 1, 3, 0}));
        System.out.println(minSwapsCouples3(new int[] {4, 2, 5, 1, 3, 0}));
    }
}