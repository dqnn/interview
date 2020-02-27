package hatecode._0001_0999;
import java.util.*;
public class _475Heaters {
/*
475. Heaters
Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all houses could be covered by those heaters.

So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius standard of heaters.

Note:
Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
As long as a house is in the heaters' warm radius range, it can be warmed.
All the heaters follow your radius standard and the warm radius will the same.
Example 1:
Input: [1,2,3],[2]
Output: 1
Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
*/
    public int findRadius2(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        
        int i = 0, j = 0, res = 0;
        while (i < houses.length) {
            while (j < heaters.length - 1
                && Math.abs(heaters[j + 1] - houses[i]) <= Math.abs(heaters[j] - houses[i])) {
                j++;
            }
            res = Math.max(res, Math.abs(heaters[j] - houses[i]));
            i++;
        }
        
        return res;
    }
/*
The idea is to leverage decent Arrays.binarySearch() function provided by Java.

For each house, find its position between those heaters (thus we need the heaters array to be sorted).
Calculate the distances between this house and left heater and right heater, get a MIN value of those two values. Corner cases are there is no left or right heater.
Get MAX value among distances in step 2. It's the answer.
Time complexity: max(O(nlogn), O(mlogn)) - m is the length of houses, n is the length of heaters.
*/
     public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int result = Integer.MIN_VALUE;
        
        for (int house : houses) {
            //to see where i can put house in the heaters
            int index = Arrays.binarySearch(heaters, house);
            if (index < 0)  index = -(index + 1);
            //find house distance to index - 1
            int dist1 = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE;
            //house distance to index,
            int dist2 = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;
        
            result = Math.max(result, Math.min(dist1, dist2));
        }
        
        return result;
    }

     //treeSet, just for reference
     public int findRadius3(int[] houses, int[] heaters) {
         TreeSet<Integer> treeset = new TreeSet<>();
         for (int heater : heaters) treeset.add(heater);
         int res = 0;
         for (int house : houses) {
             Integer upper = treeset.ceiling(house); //>=
             Integer lower = treeset.floor(house);//<=
             res = Math.max(res, Math.min(upper == null ? Integer.MAX_VALUE : upper - house, 
                     lower == null ? Integer.MAX_VALUE : house - lower));
         }
         return res;
     }
}