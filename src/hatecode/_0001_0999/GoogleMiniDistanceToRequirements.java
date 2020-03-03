package hatecode._0001_0999;

import java.util.*;
public class GoogleMiniDistanceToRequirements {
/*
 *  From goole interiview, not from LC
    *Input:1. 给一条路，路上的不同位置有不同的设施，有多个设施在不同位置的情况,List<Set<String>>
    *2. 给一个需求设施的set
    *Output：希望给出一个位置，距离所有设施的距离最近的和
    *
    *Example:
    *Road:
    {
    *           [bookstore, school],
    *           [grocery] ,
    *           [],
    *           [],
    *           [bookstore, library],
    *           []
    *           [grocery]
    *        }
    *Requires:[bookstore,library,grocery]

    *Output:
    the best place is 4, to bookstore and   lib is 0, and grocery is 2, so in sum is 2.
*/

    //mini of mini distance to requirement
    //thinking process:
    
    //given a list of roads, find the min path sum of mini distance
    //small improvement to use TreeSet instead of arraylist
    //O(nk)/O(n)
    
    //another improvement is to setup 2D matrix, which row i means road block, row j means facility,
    //so g[i][j] means on block i it has facility j, each column represent a facility
    
    //so the problem turns the problem into a best meeting point LC. 296, not exactly the same, 
    //296 requires the middle point on x and y, here just needs x
    public static int findBestLocationn(List<Set<String>> road, List<String> requires) {
        
        Map<String, TreeSet<Integer>> roadMap = new HashMap<>();
        for (int i = 0; i < road.size(); i++) {
            for (String facility : road.get(i)) {
                roadMap.computeIfAbsent(facility, v->new TreeSet<>()).add(i);
            }
        }
        
        int minSum = Integer.MAX_VALUE, index = 0;
        for (int i = 0; i < road.size(); i++) {
            int sum = 0;
            for (String facility: requires) {
                TreeSet<Integer> tree = roadMap.get(facility);
                Integer floor = tree.floor(i), ceiling = tree.ceiling(i);//log(frequency)
                if (floor == null) sum += ceiling - i;
                else if (ceiling == null) sum += i - floor;
                else sum += Math.min(ceiling - i, i - floor);
            }
            if (sum < minSum) {
                minSum = sum;
                index = i;
            }
        }
        return index;
    }
    //this methods is disgarded here, i di not remove just for reference to know the bad way
    private static int getMinLen(Map<String, TreeSet<Integer>> roadMap, String require, int index) {
        TreeSet<Integer> list = roadMap.get(require);
        int minLen = Integer.MAX_VALUE;
        for (int pos : list) {
            minLen = Math.min(minLen, Math.abs(pos - index));
        }
        return minLen;
    }
    
    public static void main(String[] args) {
        List<Set<String>> in = new ArrayList<>();
        in.add(new HashSet<>(Arrays.asList("bookstore", "school")));
        in.add(new HashSet<>(Arrays.asList("grocery")));
        in.add(new HashSet<>());
        in.add(new HashSet<>());
        in.add(new HashSet<>(Arrays.asList("bookstore", "lib")));
        in.add(new HashSet<>());
        in.add(new HashSet<>(Arrays.asList("grocery")));
        
        System.out.println(findBestLocationn(in, Arrays.asList("bookstore", "lib", "grocery")));
    }
}
