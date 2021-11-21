package hatecode._1000_1999;

import java.util.*;
public class _1366RankTeamsByVotes {
/*
1366. Rank Teams by Votes
In a special ranking system, each voter gives a rank from highest to lowest to all teams participated in the competition.

The ordering of teams is decided by who received the most position-one votes. If two or more teams tie in the first position, we consider the second position to resolve the conflict, if they tie again, we continue this process until the ties are resolved. If two or more teams are still tied after considering all positions, we rank them alphabetically based on their team letter.

Given an array of strings votes which is the votes of all voters in the ranking systems. Sort all teams according to the ranking system described above.

Return a string of all teams sorted by the ranking system.

 

Example 1:

Input: votes = ["ABC","ACB","ABC","ACB","ACB"]
Output: "ACB"
*/
    //thinking process: O(26n)/O(1)
    
    //the problem is to say: given array of strings, one string is like 
    //"ABCCD", its length will be less than 26, we want to get a final string
    public String rankTeams(String[] v) {
        Map<Character, int[]> map = new HashMap<>();
        
        int l = v[0].length();
        for(String s: v) {
            for(int j = 0; j<l;j++) {
                char c = s.charAt(j);
                map.computeIfAbsent(c, e->new int[l]);
                map.get(c)[j]++;
            }
        }
        
        List<Character> list = new ArrayList<>(map.keySet());
        Collections.sort(list, (a, b)-> {
            for(int i = 0; i< l; i++) {
                if (map.get(a)[i] != map.get(b)[i]) {
                    return map.get(b)[i] - map.get(a)[i];
                }
            }
            return a - b;
        });
        
        StringBuilder sb = new StringBuilder();
        for(char c: list) {
            sb.append(c);
        }
        
        return sb.toString();
    }
}