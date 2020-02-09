package hatecode._0001_0999;

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

    static class Index {
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

        public Index(int vertex, Set<Integer> vertexSet) {
            this.currentVertex = vertex;
            this.vertexSet = vertexSet;
        }
        public String toString() {
            return "[index:" + this.currentVertex + "," + "set:" + this.vertexSet + "]";
        }
    }

    public int minCost(int[][] distance) {

        //stores Index to minimal cost
        Map<Index, Integer> minIndexToCostMap = new HashMap<>();
        //store the Index to city number
        Map<Index, Integer> parent = new HashMap<>();
        //why we distance.length - 1 because we want one city becomes the starting point
        //allSets: {},{1},{2},{3},{1,2},{1,3},{2,3}
        List<Set<Integer>> srcCitySets = generateCombination(distance.length - 1);

        for(Set<Integer> srcCitySet : srcCitySets) {
            //column scan, the matrix is n x n, this would include all cities except 0 
            for(int dstCity = 1; dstCity < distance.length; dstCity++) {
                if(srcCitySet.contains(dstCity)) {
                    continue;
                }
                //dst City as index,srcCitySet as source
                Index index = new Index(dstCity, srcCitySet);
                //here minCost means srcCitySet to dstCity min cost
                int minCost = Integer.MAX_VALUE;
                int minPreSrcCity = 0;
                //to avoid ConcurrentModificationException copy set into another set while iterating
                Set<Integer> srcCityCloneSet = new HashSet<>(srcCitySet);
                //if set = [1,2], we scan from top to bottom for each column,
                //srcCity->DesCity, column mode scan
                for(int srcCity : srcCitySet) {
                    int cost = distance[srcCity][dstCity] 
                            + getCost(srcCityCloneSet, srcCity, minIndexToCostMap);
                    if(cost < minCost) {
                        minCost = cost;
                        minPreSrcCity = srcCity;
                    }
                }
                //this happens for empty subset, this is [0,1]
                if(srcCitySet.size() == 0) {
                    minCost = distance[0][dstCity];
                }
                //map stores from 1->[2,3], the min cost
                minIndexToCostMap.put(index, minCost);
                //parent here stores 1->[2,3] which dstCity is minimal cost
                parent.put(index, minPreSrcCity);
            }
        }

        //flying back to 0
        Set<Integer> srcCitySet = new HashSet<>();
        //1,2,3
        for(int i=1; i < distance.length; i++) {
            srcCitySet.add(i);
        }
        int min = Integer.MAX_VALUE;
        int preCity = -1;
        //to avoid ConcurrentModificationException copy set into another set while iterating
        Set<Integer> srcCityCloneSet = new HashSet<>(srcCitySet);
        for(int srcCity : srcCitySet) {
            //first column
            int cost = distance[srcCity][0] 
                    //previous srcCity is the dstCity
                    + getCost(srcCityCloneSet, srcCity, minIndexToCostMap);
            if(cost < min) {
                min = cost;
                preCity = srcCity;
            }
        }
        
        parent.put(new Index(0, srcCitySet), preCity);
        printTour(parent, distance.length);
        return min;
    }
    //to get from srcCityset->dstCity minimal cost
    private int getCost(Set<Integer> srcCityset, int dstCity, 
                        Map<Index, Integer> minIndexToCostMap) {
        srcCityset.remove(dstCity);
        Index index = new Index(dstCity, srcCityset);
        int cost = minIndexToCostMap.get(index);
        srcCityset.add(dstCity);
        return cost;
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
            start = parent.get(new Index(start, set));
            if(start == null) {
                break;
            }
        }
        StringJoiner joiner = new StringJoiner("->");
        stack.forEach(v -> joiner.add(String.valueOf(v)));
        System.out.println("\nTSP tour");
        System.out.println(joiner.toString());
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
        //0->2 = 15 here means distance[0][2] = 15
        int distance[][] = {{0, 1, 15, 6},
                            {2, 0, 7, 3},
                            {9, 6, 0, 12},
                            {10, 4, 8, 0},
        };

        int minCost = ht.minCost(distance);
        System.out.println("Min cost is " + minCost);
    }
}



