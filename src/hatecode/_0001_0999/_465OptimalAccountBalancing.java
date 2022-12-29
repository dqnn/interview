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
    //thinking process: worst case: O(n!)/O(n), 
    /*
    F(n)= (n-1) * F(n-1)

    there are 0, 1, 2, ... n - 1 persons, denote F(n) as its TC, so lets seperate 0, then 
    you have 1, 2,... n-1, it is F(n-1)

    for the first loop, we need to visit n-1 times since n- 1 elements in 1,2,,,n-1.
    */
    
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
    
    /*
     * 
     */
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
    
    
    /* Interview friendly, O(n*2^n)/O(2^n)
     * //so we think about there are two list, one is positive ones, another one is 
    //negative ones, so we want to know for these 2 lists, to make them all 0, how many
    //add(+) needs to happen, or min add
    
    //we can sort each list, and we can matched ones, which should be fastest
     */
    
    /*
     * for example, [2,-7,4,3,-2]
     * 
     * --> [2,-2] , [-7,4,3]
     * so the least transaction is 1 + 2 = 3, 
     * suppose we have N accounts with none 0 balance and M set which sum(set) = 0, and set cannot be 
     * divided again, then answer is N - M
     * 
     */
    public int minTransfers_interview_friendly(int[][] T) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int[] t : T) {
            map.put(t[0], map.getOrDefault(t[0], 0) - t[2]);
            map.put(t[1], map.getOrDefault(t[1], 0) + t[2]);
        }
        
        List<Integer> debts = map.values().stream().filter(e->e != 0).collect(Collectors.toList());
        int n = debts.size();
        
         /*
         dp[mask] = number of sets whose sum = 0 when mask in [0, mask]
         mask: one subset of origin set n, for example, n elements have 2^n -1 sets
          */
        int[] dp = new int[1 << n];
        // sums[mask] = sum of numbers in mask
        int[] sums = new int[1 << n];
        for (int cur = 0; cur < (1 << n); cur++) {
            int setBit = 1;
            for (int i = 0; i < n; i++) {
                //this means current subset cur does not have person setBit, if setBit in this set, 
                //we just continue
                if ((cur & setBit) == 0) {
                    int next = cur | setBit; // next: next subset. 
                    sums[next] = sums[cur] + debts.get(i);
                    if (sums[next] == 0) {
                        dp[next] = Math.max(dp[next], dp[cur] + 1);
                    } else {
                        dp[next] = Math.max(dp[next], dp[cur]);
                    }
                }
                setBit <<= 1;
            }
        }
        return n - dp[dp.length - 1];
    }
    
}