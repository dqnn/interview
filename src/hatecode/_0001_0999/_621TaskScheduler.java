package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
621. Task Scheduler
Given a char array representing tasks CPU need to do. 
It contains capital letters A to Z where different letters 
represent different tasks.Tasks could be done without original 
order. Each task could be done in one interval. For each interval, 
CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means 
between two same tasks, there must be at least n intervals that 
CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will 
take to finish all the given tasks.

 

Example:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 

Note:

The number of tasks is in the range [1, 10000].
The integer n is in the range [0, 100].
 */
public class _621TaskScheduler {
    /*
     * interview friendly
     * thinking process:  O(n)/O(1)
     */
    //the problem is to say we have list of tasks which described in 
    //upper case, and if same task, cpu has to be cool down for n =2 time,
    //each task could take 1 unit time. so given list of tasks, 
    //figure out the least of time needed to finish all tasks
    
    //suppose AAAABBB n =2, AXXAXXAXXAXX, X means empty space,
    //we want to replace with other tasks, so if different then CPU
    //can work without resting, if all same tasks, then it would be 
    // (n + 1) * (max - 1), n(cooling time) + 1 because add task A's 1 unit time
    // count - 1 because we want count max - 1 A's chunk of time, here
    //4 -1 =3. 
    
    /*
     * this solution has 3 tricks;
     * 1. (n+1)*(count-1), this counts base time sum, note, we exclude 1 because 
     * for now we not sure they are the last one or not
     * 2. we have to loop cnt  because we want to add if there is a tie in original 
     * char array.
     * 3. for the edge case, n = 0, we have to return original length b
     */
    
    // then we want to know how many chars to have same max count, 
    //and we only need to count task for last chunk no need to be idle
    //so we only need to replace all chars here this could be how many 
    //chars which has same max count
    public int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length < 1) {
            return 0;
        }
        int[] cnt = new int[26];
        int count = 0;
        for(char ch : tasks) {
            cnt[ch - 'A'] +=1; 
            count = Math.max(count, cnt[ch-'A']);
        }
        //n + 1 positions for count - 1 units, 
        //AAAABBB n =2 --> it will be 3 * 3 = 9
        int res = (n + 1) * (count - 1);
        //because if we have other characters have same count, it has be outside count-1, so we need to add
        //them as 1 more position
        for(int temp : cnt) {
            if (temp == count) {
                res +=1;
            }
        }
        //this is for AAABCDEFG or AAA,BBB,CCC, DD, E case
        /*
         * A_ _ A
         * 
         * original algorithms (n + 1) * (max -1) only applies to the use cases where the max Task will be show up in every 
         * n+1 length of sequence A _ _
         * 
         * but AAABCDEFG is not the case, because every unit is occupied with task, we do not need to arrange them
         * also ABCD, AB
         */
        return Math.max(tasks.length, res);
    }
    
    //interview friendly, follow up: how about output the task sequence
    public int leastInterval2(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0)
            return 0;
        // build map to sum the amount of each task
        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch : tasks) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // build queue, sort from descending
        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>(
                (a, b) -> (b.getValue() - a.getValue()));
        queue.addAll(map.entrySet());

        int cnt = 0;
        // when queue is not empty, there are remaining tasks
        while (!queue.isEmpty()) {
            // for each interval
            int interval = n + 1;
            // list used to update queue
            List<Map.Entry<Character, Integer>> list = new ArrayList<>();

            // fill the intervals with the next high freq task
            while (interval > 0 && !queue.isEmpty()) {
                Map.Entry<Character, Integer> entry = queue.poll();
                entry.setValue(entry.getValue() - 1);
                list.add(entry);
                // interval shrinks
                interval--;
                // one slot is taken
                cnt++;
            }

            // update the value in the map
            for (Map.Entry<Character, Integer> entry : list) {
                // when there is left task
                if (entry.getValue() > 0)
                    queue.offer(entry);
            }
            // job done
            if (queue.isEmpty())
                break;
            // if interval is > 0, then the machine can only be idle
            cnt += interval;
        }

        return cnt;
    }
}