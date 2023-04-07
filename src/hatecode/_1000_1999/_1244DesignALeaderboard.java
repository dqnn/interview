package hatecode._1000_1999;

import java.util.*;

public class _1244DesignALeaderboard {
    /*
    1244. Design A Leaderboard
    Design a Leaderboard class, which has 3 functions:
    
    addScore(playerId, score): Update the leaderboard by adding score to the given player's score. If there is no player with such id in the leaderboard, add him to the leaderboard with the given score.
    top(K): Return the score sum of the top K players.
    reset(playerId): Reset the score of the player with the given id to 0 (in other words erase it from the leaderboard). It is guaranteed that the player was added to the leaderboard before calling this function.
    Initially, the leaderboard is empty.
    
     
    
    Example 1:
    
    Input: 
    ["Leaderboard","addScore","addScore","addScore","addScore","addScore","top","reset","reset","addScore","top"]
    [[],[1,73],[2,56],[3,39],[4,51],[5,4],[1],[1],[2],[2,51],[3]]
    Output: 
    [null,null,null,null,null,null,73,null,null,null,141]
    */
    
        //use two treemap to store playerId and score
        TreeMap<Integer, Integer> player2score;
        TreeMap<Integer, Set<Integer>> score2player;
        public _1244DesignALeaderboard() {
            player2score = new TreeMap<>();
            score2player = new TreeMap<>();
        }
        
        public void addScore(int playerId, int score) {
            if (!player2score.containsKey(playerId)) {
                player2score.put(playerId, score);
                score2player.computeIfAbsent(score, v->new HashSet<>()).add(playerId);
                return;
            }
            
            int v = player2score.get(playerId);
            player2score.put(playerId, score+ v);
            
            if (score2player.get(v).size() == 1) {
                score2player.remove(v);
            } else {
                score2player.get(v).remove(playerId);
            }
            
            score2player.computeIfAbsent(score+v, e->new HashSet<>()).add(playerId);
            
            
        }
        
        public int top(int K) {
            int res = 0;
            Map<Integer, Set<Integer>> map = score2player.descendingMap();
            for(Map.Entry<Integer, Set<Integer>> entry: map.entrySet()) {
                if (K - entry.getValue().size() > 0) {
                    res += entry.getValue().size() * entry.getKey();
                    K -= entry.getValue().size();
                } else {
                    res += K * entry.getKey();
                    break;
                }
            }
            return res;
        }
        
        public void reset(int playerId) {
            int score = player2score.get(playerId);
            player2score.remove(playerId);
            Set<Integer> set = score2player.get(score);
            if (set.size() <= 1) {
                score2player.remove(score);
            } else {
                set.remove(playerId);
                score2player.put(score, set);
            }
        }
    }
    
