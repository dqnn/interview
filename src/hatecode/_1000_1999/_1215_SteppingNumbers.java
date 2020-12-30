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
    
    //the problem is to say: given one range [low, high], return all numbers which its digits are
    //diffed by 1, 
    //for example, [0, 21] => [0,1,2,3,4,5,6,7,8,9,10,12,21]
    
    //need to know some basic things, diff 1, only 1 digit, then it would be 0->9, 
    //for 2 digits, 10,12,21,23,32,43,34,54,45.....
    //we can continue for 3 digits, 123,234.....,321,432,
    
    //so 
    public static List<Integer> countSteppingNumbers(int low, int high) {
        List<Integer> res = new ArrayList<>();
        if(low > high) return res;

        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i <= 9; i++) q.add(i);

        if(low == 0) res.add(0);
        while(!q.isEmpty()){
            int cur = q.poll();
            if(cur >= low && cur <= high) res.add(cur);

            if(cur <= high/10){
                int lastDigit = cur%10;
                if(lastDigit > 0) q.add(cur*10 + lastDigit - 1);
                if(lastDigit < 9) q.add(cur*10 + lastDigit + 1);
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
        System.out.println(countSteppingNumbers(0, 100));
    }
}

