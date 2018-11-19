package leetcode;

import java.util.*;

/**
 * Date 11/18/2018
 * @author Denny Du
 *
 * Help Karp method of finding tour of traveling salesman.
 *
 * Time complexity - O(2^n * n^2)
 * Space complexity - O(2^n)
 *
 * https://en.wikipedia.org/wiki/Held%E2%80%93Karp_algorithm
 */
public class TravelingSalesmanHeldKarp {

    private static int INFINITY = 100000000;

    private static class Index {
        int currentVertex;
        Set<Integer> vertexSet;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Index index = (Index) o;

            if (currentVertex != index.currentVertex) return false;
            return !(vertexSet != null ? !vertexSet.equals(index.vertexSet) : index.vertexSet != null);
        }

        @Override
        public int hashCode() {
            int result = currentVertex;
            result = 31 * result + (vertexSet != null ? vertexSet.hashCode() : 0);
            return result;
        }

        private static Index createIndex(int vertex, Set<Integer> vertexSet) {
            Index i = new Index();
            i.currentVertex = vertex;
            i.vertexSet = vertexSet;
            return i;
        }
    }

    public int minCost(int[][] distance) {

        //stores intermediate values in map
        Map<Index, Integer> minCostDP = new HashMap<>();
        Map<Index, Integer> parent = new HashMap<>();
        //why we distance.length - 1 because we want one city becomes the starting point
        //allSets: {},{1},{2},{3},{1,2},{1,3},{2,3}
        List<Set<Integer>> allSets = generateCombination(distance.length - 1);

        for(Set<Integer> set : allSets) {
            //row scan
            for(int dstCity = 1; dstCity < distance.length; dstCity++) {
                if(set.contains(dstCity)) {
                    continue;
                }
                Index index = Index.createIndex(dstCity, set);
                int minCost = Integer.MAX_VALUE;
                int minPrevVertex = 0;
                //to avoid ConcurrentModificationException copy set into another set while iterating
                Set<Integer> dstCitiesCopySet = new HashSet<>(set);
                //if set = [1,2], we scan from top to bottom for each column,
                //preCity->DesCity
                for(int fromCity : set) {
                    int cost = distance[fromCity][dstCity] 
                            + getCost(dstCitiesCopySet, fromCity, minCostDP);
                    if(cost < minCost) {
                        minCost = cost;
                        minPrevVertex = fromCity;
                    }
                }
                //this happens for empty subset
                if(set.size() == 0) {
                    minCost = distance[0][dstCity];
                }
                minCostDP.put(index, minCost);
                parent.put(index, minPrevVertex);
            }
        }

        Set<Integer> set = new HashSet<>();
        for(int i=1; i < distance.length; i++) {
            set.add(i);
        }
        int min = Integer.MAX_VALUE;
        int prevVertex = -1;
        //to avoid ConcurrentModificationException copy set into another set while iterating
        Set<Integer> copySet = new HashSet<>(set);
        for(int k : set) {
            int cost = distance[k][0] + getCost(copySet, k, minCostDP);
            if(cost < min) {
                min = cost;
                prevVertex = k;
            }
        }

        parent.put(Index.createIndex(0, set), prevVertex);
        printTour(parent, distance.length);
        return min;
    }

    private void printTour(Map<Index, Integer> parent, int totalVertices) {
        Set<Integer> set = new HashSet<>();
        for(int i=0; i < totalVertices; i++) {
            set.add(i);
        }
        Integer start = 0;
        Deque<Integer> stack = new LinkedList<>();
        while(true) {
            stack.push(start);
            set.remove(start);
            start = parent.get(Index.createIndex(start, set));
            if(start == null) {
                break;
            }
        }
        StringJoiner joiner = new StringJoiner("->");
        stack.forEach(v -> joiner.add(String.valueOf(v)));
        System.out.println("\nTSP tour");
        System.out.println(joiner.toString());
    }

    private int getCost(Set<Integer> set, int prevVertex, Map<Index, Integer> minCostDP) {
        set.remove(prevVertex);
        Index index = Index.createIndex(prevVertex, set);
        int cost = minCostDP.get(index);
        set.add(prevVertex);
        return cost;
    }

    private List<Set<Integer>> generateCombination(int n) {
        int input[] = new int[n];
        for(int i = 0; i < input.length; i++) {
            input[i] = i+1;
        }
        List<Set<Integer>> allSets = new ArrayList<>();
        int result[] = new int[input.length];
        generateCombination(input, 0, 0, allSets, result);
        Collections.sort(allSets, (a, b)->(a.size() - b.size()));
        return allSets;
    }

    private void generateCombination(int input[], int start, int pos, List<Set<Integer>> allSets, int result[]) {
        if(pos == input.length) {
            return;
        }
        Set<Integer> set = createSet(result, pos);
        allSets.add(set);
        for(int i=start; i < input.length; i++) {
            result[pos] = input[i];
            generateCombination(input, i+1, pos+1, allSets, result);
        }
    }

    private static Set<Integer> createSet(int input[], int pos) {
        if(pos == 0) {
            return new HashSet<>();
        }
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < pos; i++) {
            set.add(input[i]);
        }
        return set;
    }
    
    public static void main(String args[]) {

        TravelingSalesmanHeldKarp ht = new TravelingSalesmanHeldKarp();
        //so we have 0,1,2,3 cities, and the matrix describes the distance
/*
allSets: {},{1},{2},{3},{1,2},{1,3},{2,3}
 <--, [{},{}] = 0  [1,{}] = 1, [2,{}] = 15, [3,{}] = 6
 <--, [2, {1, {}}] = 7 + 1 = 8, [3, {1,{}}] = 3 + 1 = 4, 
      [1, {2,{}}] = 6 + 15 = 21, [3, {2,{}}] = 12 + 15 = 27
      [1, {3,{}}] = 4 + 6 = 10,  [2, {3,{}}] = 8 + 6 = 14
<--,  
      
  
 */
        int distance[][] = {{0, 1, 15, 6},
                            {2, 0, 7, 3},
                            {9, 6, 0, 12},
                            {10, 4, 8, 0},
        };

        int minCost = ht.minCost(distance);
        System.out.println("Min cost is " + minCost);
    }
}



