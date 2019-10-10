package hatecode;

import java.util.*;
import java.util.stream.IntStream;
public class _1042FlowerPlantingWithNoAdjacent {
/*
1042. Flower Planting With No Adjacent
You have N gardens, labelled 1 to N.  In each garden, you want to plant one of 4 types of flowers.

paths[i] = [x, y] describes the existence of a bidirectional path from garden x to garden y.

Also, there is no garden that has more than 3 paths coming into or leaving it.

Your task is to choose a flower type for each garden such that, for any two gardens connected by a path, they have different types of flowers.

Return any such a choice as an array answer, where answer[i] is the type of flower planted in the (i+1)-th garden.  The flower types are denoted 1, 2, 3, or 4.  It is guaranteed an answer exists.

 

Example 1:

Input: N = 3, paths = [[1,2],[2,3],[3,1]]
Output: [1,2,3]
*/
  //thinking process: 
    //given one integer N stands for flowers type, another 2D array
    //stands for connected gardens, each garden has different typeof flowers
    //so return one possible flower type as array for all gardens
    
    //each garden has max =3 adjacent neibours, so if we have at least 4 type of 
    //flowers, answer is guaranteed
    
    //we use map G to record each garden and corresponding neighbors,
    //then suppose we have max 4 type of flowers, 1, 2, 3, 4
    //then each time, we start from garden i, each time we remove all its neighbors
    //then we get its first element as answer
    public int[] gardenNoAdj(int N, int[][] paths) {
        //add neighbors
        Map<Integer, Set<Integer>> G = new HashMap<>();
        IntStream.range(0, N).forEach(i->G.put(i, new HashSet<>()));
        for (int[] p : paths) {
            G.get(p[0] - 1).add(p[1] - 1);
            G.get(p[1] - 1).add(p[0] - 1);
        }
        //
        int[] res = new int[N];
        for (int i = 0; i < N; i++) {
            Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3, 4));
            for (int j : G.get(i)) {
                 set.remove(res[j]);
            }
            res[i] = set.iterator().next();
        }
        return res;
    }
}