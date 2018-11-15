package leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LRUCache
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : 146. LRU Cache
 */
public class LRUCache {

    /**
     * Design and implement a data structure for Least Recently Used (LRU) cache.
     * It should support the following operations: get and put.

     get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
     put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
     it should invalidate the least recently used item before inserting a new item.

     Follow up:
     Could you do both operations in O(1) time complexity?

     Example:

     LRUCache cache = new LRUCache( 2  capacity  );

    cache.put(1, 1);
    cache.put(2, 2);
    cache.get(1);       // returns 1
    cache.put(3, 3);    // evicts key 2
    cache.get(2);       // returns -1 (not found)
    cache.put(4, 4);    // evicts key 1
    cache.get(1);       // returns -1 (not found)
    cache.get(3);       // returns 3
    cache.get(4);       // returns 4

    HashMap + Double Linked List

     插入：1，不存在 -> capacity -> 1,head = null 2,head != null
          2，存在
     取出：1，不存在
          2，存在
     => 排序

     time : O(1)
     **/
/*
 * 1.The key to solve this problem is using a double linked list which enables us to quickly move nodes.
2.The LRU cache is a hash table of keys and double linked nodes. The hash table makes the time of get() to be O(1). 
The list of double linked nodes make the nodes adding/removal operations O(1).

class Node {
int key;
int value;
Node pre;
Node next;
 */
    class Node {
        int key, value;
        Node next, pre;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private Map<Integer, Node> map;
    private int capacity;
    private Node head, tail;
    
    // interview friendly 
    //
    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        head = null;
        tail = null;
    }
    
    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        // means it has pre and next, if it equals tail, 
        //then we just return the tail 
        // 
        if (node != tail) {
            // how could it be head?
            if (node == head) {
                head = head.next;
            } else { // this is to remove the node from the chain
                // node.pre point to node.next
                node.pre.next = node.next;
                // node next pre point to node.pre
                node.next.pre = node.pre;
            }
            // insert node into tail
            tail.next = node;
            node.pre = tail;
            node.next = null;
            tail = node;
        }
        return node.value;
    }
    
    public void put(int key, int value) {
        Node node = map.get(key);
        // this is the same as get 
        if (node != null) {
            node.value = value;
            if (node != tail) {
                if (node == head) {
                    head = head.next;
                } else {
                    node.pre.next = node.next;
                    node.next.pre = node.pre;
                }
                tail.next = node;
                node.next = null;
                node.pre = tail;
                tail = node;
            }
        } else {
            Node newNode = new Node(key, value);
            // capacity means how many capactiy we left for new elements,if 0 then we need to remove
            // from double list, we remove the first one, head
            if (capacity == 0) {
                Node temp = head;
                head = head.next;
                map.remove(temp.key);
                capacity++;
            }
            //means first one
            if (head == null || tail == null) {
                head = newNode;
            // if not we just add to the tail
            } else {
                tail.next = newNode;
                newNode.pre = tail;
                newNode.next = null;
            }
            // this part of code is shared with if (head == null || tail == null)  
            tail = newNode;
            map.put(key, newNode);
            capacity--;
        }
    }
    
    /*
     * In the constructor, the third boolean parameter specifies the ordering mode. 
     * If we set it to true, it will be in access order. 
     * (https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html#LinkedHashMap-int-float-boolean-)
By overriding removeEldestEntry in this way, we do not need to take care of it ourselves. 
It will automatically remove the least recent one when the size of map exceeds the specified
 capacity.(https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html#removeEldestEntry-java.util.Map.Entry-)
     */
    class LRUCacheLazy {
        private Map<Integer, Integer> map;
        private int capacity;

        public LRUCacheLazy(int capacity) {
            this.capacity = capacity;
            this.map = new LinkedHashMap(capacity, 0.75f, true) {
                protected boolean removeEldestEntry(Map.Entry eldest) {
                        return size() > capacity;
                    }
            };
        }
        
        public int get(int key) {
            return map.getOrDefault(key, -1);
        }
        
        public void put(int key, int value) {
            map.put(key, value);
        }
    }

  
}
