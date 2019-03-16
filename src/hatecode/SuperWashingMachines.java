package hatecode;
import java.util.*;
public class SuperWashingMachines {
/*
517. Super Washing Machines
You have n super washing machines on a line. Initially, each washing machine has some dresses or is empty.

For each move, you could choose any m (1 ≤ m ≤ n) washing machines, and pass one dress of each washing machine to one of its adjacent washing machines at the same time .

Given an integer array representing the number of dresses in each washing machine from left to right on the line, you should find the minimum number of moves to make all the washing machines have the same number of dresses. If it is not possible to do it, return -1.

Example1

Input: [1,0,5]

Output: 3

Explanation: 
1st move:    1     0 <-- 5    =>    1     1     4
2nd move:    1 <-- 1 <-- 4    =>    2     1     3    
3rd move:    2     1 <-- 3    =>    2     2     2   
*/
    public int findMinMoves_Best(int[] machines) {
        int total = 0; 
        for(int i: machines) total+=i;
        if(total%machines.length!=0) return -1;
        //this would be good
        int avg = total/machines.length, cnt = 0, max = 0;
        for(int load: machines){
            cnt += load-avg; //load-avg is "gain/lose"
            max = Math.max(Math.max(max, Math.abs(cnt)), load-avg);
        }
        return max;
    }
   
    //
    public int findMinMoves_esay_To_Understand(int[] machines) {
        int sum = 0, n = machines.length;
        for (int i = 0; i < machines.length; i++) {
            sum += machines[i];
        }

        if (sum % n != 0) {
            return -1;
        }

        int target = sum / n;

        int toLeft = 0;
        int res = 0;
        for (int i = 0; i < machines.length; i++) {
            int toRight = machines[i] - target - toLeft;
            res = Math.max(res, Math.max(Math.max(toLeft, toRight), toLeft + toRight));
            toLeft = -toRight;
        }
        return res;
    }
    //[0,0,11,5]->dp=[-4,-4,7,1], 
    //throughput: 4,8,1,2
    public int findMinMoves(int[] machines) {
        int n = machines.length;
        int sum = Arrays.stream(machines).sum();

        if (sum % n != 0) return -1;

        int avg = sum / n;

        //dp stores the diff when comparing to avg
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) dp[i] = machines[i] - avg;
        
        int throughput = 0;
        int res = 0;
        
        for (int i = 0; i < n; i++) {
            throughput += dp[i];
            res = Math.max(res, Math.max(dp[i], Math.abs(throughput)));
        }

        return res;
    }
}