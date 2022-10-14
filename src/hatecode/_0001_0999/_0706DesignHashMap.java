package hatecode._0001_0999;

import java.util.*;
public class _0706DesignHashMap {

    /*
    
    706. Design HashMap
    
    Design a HashMap without using any built-in hash table libraries.

Implement the MyHashMap class:

MyHashMap() initializes the object with an empty map.
void put(int key, int value) inserts a (key, value) pair into the HashMap. If the key already exists in the map, update the corresponding value.
int get(int key) returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.
 

Example 1:

Input
["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
[[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
Output
[null, null, null, 1, -1, null, 1, null, -1]
Some of the questions which can be asked to the interviewer before implementing the solution

For simplicity, are the keys integers only?
For collision resolution, can we use chaining?
Do we have to worry about load factors?
Can we assume inputs are valid or do we have to validate them?
Can we assume this fits memory?
    */
    
    /*
     * the problem is to design a hashMap, now i used a 
     * ArrayList[size] 
     * 
     * List<int[]>[] list =new ArrayList[size]
     * 
     *  we use key % size as index, so we do not need allocate more resource
     *  
     *  
     */

    //Load factor = 10000/size = 0.75
    List<int[]>[] lists; //using List<List<int[]>> lists is fine as well.
    int size = 13000;

    //O(size)
    /** Initialize your data structure here. */
    public _0706DesignHashMap() {
        lists = new ArrayList[size];
        for(int i=0; i<lists.length; i++)
            lists[i] = new ArrayList<>(); //don't use linkedlist in my version which makes put() O(L^2)
    }
    
    public int getHashcode(int key)
    {
        return key%size;
    }
    
    //O(L)
    /** value will always be non-negative. */
    public void put(int key, int value) {
        int index = getHashcode(key);
        for(int i=0; i<lists[index].size(); i++)
        {
            if(lists[index].get(i)[0]==key)
            {
                lists[index].get(i)[1]=value;
                return;
            }
        }
        lists[index].add(new int[]{key, value});
    }
    
    //O(L)
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int index = getHashcode(key);
        for(int i=0; i<lists[index].size(); i++)
        {
            if(lists[index].get(i)[0]==key)
                return lists[index].get(i)[1];
        }
        return -1;    
    }
    
    //O(L)
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int index = getHashcode(key);
        for(int i=0; i<lists[index].size(); i++)
        {
            if(lists[index].get(i)[0]==key)
                lists[index].remove(i);
        }
        return;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */