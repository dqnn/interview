package hatecode._0001_0999;
import java.util.*;
import java.util.stream.Collectors;
public class _465OptimalAccountBalancing {
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
    //thinking process: worst case: O(n!)/O(n)
    //no matter which type, we will calc each person's debt[]
    //debt[i] > 0 means his credit, <0 means he owe, =0 means he is done
    
    
    
    int res = Integer.MAX_VALUE;
    public int minTransfers(int[][] T) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int[] t : T) {
            map.put(t[0], map.getOrDefault(t[0], 0) - t[2]);
            map.put(t[1], map.getOrDefault(t[1], 0) + t[2]);
        }
        
        List<Integer> list = map.values().stream().filter(e->e != 0).collect(Collectors.toList());
        helper(list, 0, 0);
        
        return res;
    }
    
    private void helper(List<Integer> debt, int s, int count) {
        //ignore leading 0 in the debt array
        while(s < debt.size() && debt.get(s) == 0) s++;
        if (s == debt.size()) {
            res = Math.min(res, count);
            return;
        }
        
        for(int i = s + 1; i < debt.size(); i++) {
            if (debt.get(s) * debt.get(i) < 0) {
                debt.set(i, debt.get(i) + debt.get(s));
                //here s has to start from s+1
                helper(debt, s+1, count+1);
                debt.set(i, debt.get(i) - debt.get(s));
            }
        }
    }
    
    
    /*
     * //so we think about there are two list, one is positive ones, another one is 
    //negative ones, so we want to know for these 2 lists, to make them all 0, how many
    //add(+) needs to happen, or min add
    
    //we can sort each list, and we can matched ones, which should be fastest
     */
}