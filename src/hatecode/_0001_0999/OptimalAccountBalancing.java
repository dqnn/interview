package hatecode._0001_0999;
import java.util.*;
public class OptimalAccountBalancing {
    /*
     * 465. Optimal Account Balancing A group of friends went on holiday and
     * sometimes lent each other money. For example, Alice paid for Bill's lunch for
     * $10. Then later Chris gave Alice $5 for a taxi ride. We can model each
     * transaction as a tuple (x, y, z) which means person x gave person y $z.
     * Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2
     * are the person's ID), the transactions can be represented as [[0, 1, 10], [2,
     * 0, 5]].
     * 
     * Given a list of transactions between a group of people, return the minimum
     * number of transactions required to settle the debt.
     * 
     * Note:
     * 
     * A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
     * Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we
     * could also have the persons 0, 2, 6. Input: [[0,1,10], [1,0,1], [1,2,5],
     * [2,0,5]]
     * 
     * Output: 1
     * 
     * 
     */
    
//top down to execute the logic, 
/*
[[0,1,10], [1,0,1], [1,2,5], [2,0,5]]
debt = [-4,4,0]
            /  \         \ 
    -4 | 4,0   -4,4|0     -4,4,0|
     /
  -4 | 0, 0      
 
 and then we skip i+1 since it is 0, until last we return 0
*/
    //has 3 types 
    //1 min transactions as this problem
    //2. min transfer amount
    //3 min transfer amount in min transactions
    //https://www.win.tue.nl/~wstomv/publications/INFE023.pdf 
    //interview friendly, 
    //thinking process: 
    //no matter which type, we will calc each person's debt[]
    //debt[i] > 0 means his credit, <0 means he owe, =0 means he is done
    //so we think about there are two list, one is positive ones, another one is 
    //negative ones, so we want to know for these 2 lists, to make them all 0, how many
    //add(+) needs to happen, or min add
    
    //we can sort each list, and we can matched ones, which should be fastest
    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int[] t : transactions) {
            m.put(t[0], m.getOrDefault(t[0], 0) - t[2]);
            m.put(t[1], m.getOrDefault(t[1], 0) + t[2]);
        }
        //we can filter 0 first
        
        return settle(0, new ArrayList<>(m.values()));
    }

 
    private int settle(int start, List<Integer> debt) {
        while (start < debt.size() && debt.get(start) == 0) start++;
        if (start == debt.size()) return 0;
        int r = Integer.MAX_VALUE;
        for (int i = start + 1, prev = 0; i < debt.size(); i++) {
            //not understand why need prev
            if (prev != debt.get(i) &&  debt.get(start) * debt.get(i) < 0) {
                //reset data on position i 
                debt.set(i, debt.get(i) + debt.get(start));
                // + 1 beause 1 tansaction happened
                r = Math.min(r, 1 + settle(start + 1, debt));
                //reset back
                debt.set(i, debt.get(i) - debt.get(start));
                prev = debt.get(i);
            }
        }
        return r < Integer.MAX_VALUE ? r : 0;
    }
}