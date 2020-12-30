package hatecode._1000_1999;

import java.util.*;

public class _1215_SteppingNumbers {
/*
 * 1215. Stepping Numbers
    A Stepping Number is an integer such that all of its adjacent 
    digits have an absolute difference of exactly 1. For example, 
    321 is a Stepping Number while 421 is not.
    
    Given two integers low and high, find and return a sorted list 
    of all the Stepping Numbers in the range [low, high] inclusive.
    
    Example 1:
    
    Input: low = 0, high = 21
    Output: [0,1,2,3,4,5,6,7,8,9,10,12,21]
    Constraints:
    
    0 <= low <= high <= 2 * 10^9
*/
    //thinking process: 
    
    //
    public static List<Integer> countSteppingNumbers(int low, int high) {
        List<Integer> res = new ArrayList<>();
        if(low > high) return res;

        Queue<Integer> que = new LinkedList<>();
        for(int i = 1; i <= 9; i++) que.add(i);

        if(low == 0) res.add(0);
        while(!que.isEmpty()){
            int cur = que.poll();
            if(cur >= low && cur <= high) res.add(cur);

            if(cur <= high/10){
                int lastDigit = cur%10;
                if(lastDigit > 0) que.add(cur*10 + lastDigit - 1);
                if(lastDigit < 9) que.add(cur*10 + lastDigit + 1);
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
        System.out.println(countSteppingNumbers(0, 21));
    }
}

