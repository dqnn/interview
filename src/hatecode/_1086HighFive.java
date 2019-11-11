package hatecode;

import java.util.*;
public class _1086HighFive {
/*
1086. High Five
Given a list of scores of different students, return the average score of each student's top five scores in the order of each student's id.

Each entry items[i] has items[i][0] the student's id, and items[i][1] the student's score.  The average score is calculated using integer division.

 

Example 1:

Input: [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
Output: [[1,87],[2,88]]
*/
    //thinking process:O(nlgn)/O(n), so map use n people, n here is generail mean linear 
    
    //given 2D array items, item[0][0] means id, item[0][1] means score, so 
    //return another 2D array with each student top 5 average
    
    //we use List to store all courses score and map to each student id,
    //then just compute on first 5, i used sublist here, so i don;t use priorityQUeue
    public int[][] highFive(int[][] items) {
        if(items == null || items.length <1 || items[0].length < 1) return new int[0][0];
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int[] item : items) {
            map.computeIfAbsent(item[0], v->new ArrayList<>()).add(item[1]);
        }
        int n = map.size();
        int[][] res = new int[n][2];
        int idx = 0;
        for(Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int id = entry.getKey();
            List<Integer> list = entry.getValue();
            Collections.sort(list, (a, b)->(b - a));
            
            if(list.size() > 5) list = list.subList(0, 5);
            System.out.println(list);
            double score = list.stream().mapToInt(i->i).average().getAsDouble();
            res[idx][0] = id;
            res[idx++][1] = (int)score;
        }
        return res;
    }
}