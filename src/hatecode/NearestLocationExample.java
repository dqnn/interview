package hatecode;
import java.util.*;

import org.junit.Test;

public class NearestLocationExample {
/*
top K closet restraunts to this customer, locations is the restaurant coordinations
 */
    static List<List<Integer>> nearestVegetarianRestaurant(int totalRestaurants, List<List<Integer>> allocations,
            int numRestaurants) {
        List<List<Integer>> nearestRestaurants = new ArrayList<List<Integer>>(numRestaurants);

        if (numRestaurants > totalRestaurants) {
            return nearestRestaurants;
        }

        if (numRestaurants > allocations.size()) {
            return nearestRestaurants;
        }

        if (allocations == null || allocations.size() < 1) {
            return nearestRestaurants;
        } else if (allocations.size() == 1) {
            return allocations;
        }

        if (allocations.size() != totalRestaurants) {
            return nearestRestaurants;
        }

        int[] distance = new int[totalRestaurants];

        for (int i = 0; i < totalRestaurants; i++) {
            distance[i] = getDistancePoweredBy2_MAX(allocations.get(i));
        }

        Arrays.sort(distance);

        for (int i = 0; i < totalRestaurants; i++) {
            if (getDistancePoweredBy2_MAX(allocations.get(i)) <= distance[numRestaurants - 1]
                    && getDistancePoweredBy2_MAX(allocations.get(i)) != Integer.MAX_VALUE) {
                nearestRestaurants.add(allocations.get(i));
            }
        }
        return nearestRestaurants;
    }

    private static int getDistancePoweredBy2_MAX(List<Integer> restaurant) {
        //here is to set null as MAX value, to keep last 2 conditions
        if (restaurant == null || restaurant.size() != 2 || restaurant.get(0) == null 
                ||  restaurant.get(1) == null) {
            return Integer.MAX_VALUE;
        }
        //here is to set null as 0
        int x = restaurant.get(0);
        int y = restaurant.get(1);
        return x * x + y * y;
    }
    
    
    private static int getDistancePoweredBy2_Zero(List<Integer> restaurant) {
        //here is to set null as MAX value, to keep last 2 conditions
        if (restaurant == null || restaurant.size() != 2) {
            return Integer.MAX_VALUE;
        }
        //here is to set null as 0
        int x = restaurant.get(0) != null ? restaurant.get(0) : 0;
        int y = restaurant.get(1) != null ? restaurant.get(1) : 0;
        return x * x + y * y;
    }
    
    static List<List<Integer>> nearestVegetarianRestaurant2(int totalRestaurants, List<List<Integer>> allocations,
            int numRestaurants) {
        List<List<Integer>> nearestRestaurants = new ArrayList<List<Integer>>(numRestaurants);

        if (numRestaurants  <= 0) {
            return nearestRestaurants;
        }
        
        if (numRestaurants > totalRestaurants) {
            return nearestRestaurants;
        }

        if (numRestaurants > allocations.size()) {
            return nearestRestaurants;
        }

        if (allocations == null || allocations.size() < 1) {
            return nearestRestaurants;
        } else if (numRestaurants == 1 && allocations.size() == 1) {
            return allocations;
        }

        if (allocations.size() != totalRestaurants) {
            return nearestRestaurants;
        }
        
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>
        ((a, b)->(getDistancePoweredBy2_MAX(a) - getDistancePoweredBy2_MAX(b)));
        
        for(List<Integer> temp : allocations) {
            pq.add(temp);
        }
        while(numRestaurants-- > 0) {
            nearestRestaurants.add(pq.poll());
        }
        
        return nearestRestaurants;
    }

    
    @Test
    public void testSampleInput() {
        List<Integer> p1 = Arrays.asList(0,1);
        List<Integer> p2 = Arrays.asList(0,-1);
        List<Integer> p3 = Arrays.asList(1,0);
        List<Integer> p4 = Arrays.asList(1,1);
        List<List<Integer>> lists = Arrays.asList(p1,p2,p3,p4);
        System.out.println(nearestVegetarianRestaurant2(4, lists, 3));
        System.out.println("old -- " + nearestVegetarianRestaurant(4, lists, 3));
    }
    
    @Test
    public void testSampleInput2() {
        List<Integer> p1 = Arrays.asList(0,null);
        List<Integer> p2 = Arrays.asList(0,-1);
        List<Integer> p3 = Arrays.asList(1,0);
        List<Integer> p4 = Arrays.asList(1,1);
        List<List<Integer>> lists = Arrays.asList(p1,p2,p3,p4);
        System.out.println(nearestVegetarianRestaurant2(4, lists, 3));
        System.out.println("old -- " + nearestVegetarianRestaurant(4, lists, 3));
    }
}

