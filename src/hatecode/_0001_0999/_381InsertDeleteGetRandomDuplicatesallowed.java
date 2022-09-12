package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : InsertDeleteGetRandomDuplicatesallowed
 * Creator : professorX
 * Date : Sep, 2017
 * Description : TODO
 */
public class _381InsertDeleteGetRandomDuplicatesallowed {
    /**
     * 381. Insert Delete GetRandom O(1) - Duplicates allowed
     * 
     * Design a data structure that supports all following operations in average O(1) time.

Note: Duplicate elements are allowed.
insert(val): Inserts an item val to the collection.
remove(val): Removes an item val from the collection if present.
getRandom: Returns a random element from current collection of elements. 
The probability of each element being returned is linearly related to the number of 
same value the collection contains.

here linear related means 2, 2, 2,2 ,2,  if one number is n, so the 5 number probability is 5 * n

// Init an empty collection.
RandomizedCollection collection = new RandomizedCollection();

// Inserts 1 to the collection. Returns true as the collection did not contain 1.
collection.insert(1);

// Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
collection.insert(1);

// Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
collection.insert(2);

// getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
collection.getRandom();

// Removes 1 from the collection, returns true. Collection now contains [1,2].
collection.remove(1);

// getRandom should return 1 and 2 both equally likely.
collection.getRandom();
     * time : O(1)
     */

    //since we use iterator, so linkedHashSet should be better theoretically 
    //thinking process:
    //the problem is to say: implement a getRandom() and insert() function, 
    //getRandom will return us a 
    //value which equally possibility to be picked up by their value frequency in list
    
    //map will record the value and the index of the value in list, 
    //if no deletion, the world is good, but if we have remove(val), we can easily remove one var element in 
    //list, but the problem for others are incorrect, we need to correct, 
    //How we fix this: 
    //we just choose last element in list, so we want to insert this elment into a correct position, so that
    //each one element will be correct position again,
    
    //we can always pick last one in list, it is O(1), then we replce the idx with this value, and update
    //the index in map, so everyone is happy~
    HashMap<Integer, HashSet<Integer>> map;
    ArrayList<Integer> list;
    Random rmd;

    public _381InsertDeleteGetRandomDuplicatesallowed() {
        map = new HashMap<>();
        list = new ArrayList<>();
        rmd = new Random();
    }

    /** Inserts a value to the collection. Returns true if the collection did 
     * not already contain the specified element. */
    public boolean insert(int val) {
        boolean ret = map.containsKey(val);
        map.computeIfAbsent(val, v->new LinkedHashSet<>()).add(list.size());
        list.add(val);
        return !ret;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    // so if duplicated one here remains so we only want to remove 
    //one no matter where they are and which index
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        
      //remove last index elements in map's list 
        int index = map.get(val).iterator().next();
        map.get(val).remove(index);
        // remove the value if there is no more left, 
        if (map.get(val).size() == 0) {
            map.remove(val);
        }

        //now process the list then, recover
        int lastVal = list.remove(list.size() - 1);
     // means lastVal is not the value we want to remove
        if (index != list.size()) {
            //replace O(1)
            list.set(index, lastVal);
         // remove lastVal original last one, list.size() here already decreased 1 because we removed last one
            map.get(lastVal).remove(list.size());
            map.get(lastVal).add(index);
        }
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(rmd.nextInt(list.size()));
    }
    
    
    public static void main(String[] args) {
        Random rmd = new Random();
        System.out.println(rmd.nextInt(0));
    }


    public class RandomizedCollection2 {
        ArrayList<Integer> nums;
        HashMap<Integer, Set<Integer>> locs;
        java.util.Random rand = new java.util.Random();
        /** Initialize your data structure here. */
        public RandomizedCollection2() {
            nums = new ArrayList<Integer>();
            locs = new HashMap<Integer, Set<Integer>>();
        }
        
        /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
        public boolean insert(int val) {
            boolean contain = locs.containsKey(val);
            if ( ! contain ) locs.put( val, new LinkedHashSet<Integer>() ); 
            locs.get(val).add(nums.size());        
            nums.add(val);
            return ! contain ;
        }
        
        /** Removes a value from the collection. Returns true if the collection contained the specified element. */
        public boolean remove(int val) {
            boolean contain = locs.containsKey(val);
            if ( ! contain ) return false;
            int loc = locs.get(val).iterator().next();
            locs.get(val).remove(loc);
            // here use nums.size() -1 because remove the last value after this
            if (loc < nums.size() - 1 ) {
               int lastone = nums.get( nums.size()-1 );
               nums.set( loc , lastone );
               locs.get(lastone).remove( nums.size()-1);
               locs.get(lastone).add(loc);
            }
            nums.remove(nums.size() - 1);
           
            if (locs.get(val).isEmpty()) locs.remove(val);
            return true;
        }
        
        /** Get a random element from the collection. */
        public int getRandom() {
            return nums.get( rand.nextInt(nums.size()) );
        }
    }


}