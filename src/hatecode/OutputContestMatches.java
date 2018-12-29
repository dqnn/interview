package hatecode;
import java.util.*;
public class OutputContestMatches {
/*
544. Output Contest Matches
During the NBA playoffs, we always arrange the rather strong team to play with the rather weak team, like make the rank 1 team play with the rank nth team, which is a good strategy to make the contest more interesting. Now, you're given n teams, you need to output their final contest matches in the form of a string.

The n teams are given in the form of positive integers from 1 to n, which represents their initial rank. (Rank 1 is the strongest team and Rank n is the weakest team.) We'll use parentheses('(', ')') and commas(',') to represent the contest team pairing - parentheses('(' , ')') for pairing and commas(',') for partition. During the pairing process in each round, you always need to follow the strategy of making the rather strong one pair with the rather weak one.

Example 1:
Input: 2
Output: (1,2)
Explanation: 
Initially, we have the team 1 and the team 2, placed like: 1,2.
Then we pair the team (1,2) together with '(', ')' and ',', which is the final answer.
*/
    //interview friendly 
    public String findContestMatch2(int n) {
        String[] m = new String[n];
        for (int i = 0; i < n; i++) {
            m[i] = String.valueOf(i + 1);
        }

        while (n > 1) {
            for (int i = 0; i < n / 2; i++) {
                m[i] = "(" + m[i] + "," + m[n - 1 - i] + ")";
            }
            n /= 2;
        }
        
        return m[0];
    }
    //O(nlgn)/O(n)
    public static String findContestMatch3(int n) {
        Deque<String> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            queue.offer(String.valueOf(i));
        }
        
        while (queue.size() > 1) {
            Deque<String> nextQueue = new ArrayDeque<>();
            while (!queue.isEmpty()) {
                nextQueue.offer("(" + queue.poll() + "," + queue.pollLast() + ")");    
            }
            queue = nextQueue;
        }
        return queue.poll();
    }
    
    //O(n) solution
    static int[] team;
    static int t;
    static StringBuilder ans;
    public static String findContestMatch(int n) {
        team = new int[n];
        t = 0;
        ans = new StringBuilder();
        write(n, Integer.numberOfTrailingZeros(n));
        return ans.toString();
    }
    //we 
    public static void write(int n, int round) {
        if (round == 0) {
            int w = Integer.lowestOneBit(t);
            team[t] = w > 0 ? n / w + 1 - team[t - w] : 1;
            ans.append("" + team[t++]);
        } else {
            ans.append("(");
            write(n, round - 1);
            ans.append(",");
            write(n, round - 1);
            ans.append(")");
        }
    }
    
    public static void main(String[] args) {
        System.out.println(findContestMatch3(3));
    }
}