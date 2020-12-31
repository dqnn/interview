package hatecode._1000_1999;

import java.util.*;
public class _1222_QueensThatCanAttackTheKing {
/*
1222. Queens That Can Attack the King
On an 8x8 chessboard, there can be multiple Black Queens and one White King.

Given an array of integer coordinates queens that represents the positions of the Black Queens, and a pair of coordinates king that represent the position of the White King, return the coordinates of all the queens (in any order) that can attack the King.

Input: queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0]
Output: [[0,1],[1,0],[3,3]]
*/
    
    //thinking process:
    
    //
    public static String COLON = ":";
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> rslt = new ArrayList<>();
        
        Set<String> qset = new HashSet<>();
        for(int[] q:queens){
            qset.add(q[0] + COLON + q[1]);
        }
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                for(int k = 1; k <= 8; k++){
                    int xpos = king[0] + i*k;
                    int ypos = king[1] + j*k;
                    String ts = xpos + COLON + ypos;
                    if (qset.contains(ts)){
                        rslt.add(Arrays.asList(xpos,ypos));
                        break;
                    }
                }
            }
        }
        return rslt;
    }
}