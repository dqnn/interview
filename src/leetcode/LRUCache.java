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
3.
one key here is reduce the head and tail code is to use two dummy nodes
head and tail, they don't change, so when we want to remove and move heads in
list, we don't need to worry about null pointer
4. added multiple thread support
 */
    class Node {
        int  key, value;
        Node next, pre;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public Node() {
        }
    }

    private Map<Integer, Node> map;
    private int                capacity = 0;
    //this is the key
    private Node               tail     = new Node(), 
            head = new Node();
    private int                size     = 0;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        head.next = tail;
        tail.pre = head;
    }

    private synchronized void remove(Node node) {
        // remove node from list, and it must be existed
        Node pre = node.pre;
        Node next = node.next;
        pre.next = next;
        next.pre = pre;
    }

    private synchronized void moveToHead(Node node) {
        // insert to head
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }

    public int get(int key) {
        if (capacity == 0) return -1;
        Node node = map.get(key);

        if (node == null) {
            return -1;
        }
        //if only first element, just return
        if (node == head.next) {
            return node.value;
        }

        remove(node);
        moveToHead(node);

        // return value
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;
        Node node = map.get(key);
        // this is the same as get
        if (node != null) {
            // update the value
            node.value = value;
            if (node == head.next) {
                return;
            }

            remove(node);
            moveToHead(node);
            // new node
        } else {
            node = new Node(key, value);
            moveToHead(node);
            size += 1;
            if (size > capacity) {
                // remove tail.pre
                synchronized(map) {
                    map.remove(tail.pre.key);
                }
                remove(tail.pre);
            }
            synchronized(map) {
                map.put(key, node);
            }
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
