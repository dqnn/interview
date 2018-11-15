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
public class LRUCache2 {

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

 */
    class Node {
        int key, value;
        Node next, pre;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
        public Node(){}
    }
    
    private Map<Integer, Node> map;
    private int capacity;
    private Node head, tail, dummy;
    private int size = 0;
    

    public LRUCache2(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        head = null;
        tail = null;
        dummy = new Node();
        dummy.next = head;
    }
    
    public int get(int key) {
        Node node = map.get(key);
        System.out.println("get key: " 
                           + key + "-" + (node == null ? -1 : node.value));
        
        if (node == null) {
            return -1;
        }
        
        if (node == dummy.next) {
            return node.value;
        }
        
        remove(node); 
        
        moveToHead(node);
        
        Node temp = dummy.next;
        while(temp != null) {
            System.out.println("current list:" + String.format("key-%s: value-%s", temp.key, temp.value));
            temp = temp.next;
        }
        //return value
        return node.value;
    }

    private void moveToHead(Node node) {
        //first node
        if (dummy.next == null) {
            dummy.next = node;
            node.pre = dummy;
            head = node;
            tail = node;
        }
        //node is head
        if (node == dummy.next) {
            return;
        }
        //tail node
        if (node == tail && tail != null) {
            Node newTail = tail.pre;
            tail.pre = null;
            newTail.next = null;
            tail = newTail;
        }
        
        //insert to head
        dummy.next = node;
        node.pre = dummy;
        node.next = head;
        head.pre = node;
        //move head to 
        head = dummy.next;
    }

    private void remove(Node node) {
        //remove node from list
        Node pre = node.pre;
        Node next = node.next;
        pre.next = next;
        if (next != null) {
            next.pre = pre;
        }
        
        if (tail == node) {
            tail = tail.pre;
        }
    }
    
    public void put(int key, int value) {
        Node node = map.get(key);
        // this is the same as get 
        if (node != null) {
            //update the value
            node.value = value;
            if (node == dummy.next) {
                return;
            }
            
            remove(node); 
        
            moveToHead(node);
        } else {
            node = new Node(key,value);
            //insert to head
            
            moveToHead(node);
            
            if (tail == null) {
                tail = node;
            }
            
            //we already added one
            size += 1;
            if (size > capacity) {
                //remove tail
                Node newTail = tail.pre;
                tail.pre = null;
                newTail.next = null;
                map.remove(tail.key);
                tail = newTail;
                if (tail == dummy) {
                    head = null;
                    tail = null;
                }
            }
        }
        map.put(key, node);
        
        Node temp = dummy.next;
        System.out.println("insert key-value :" + key + "-" + value);
        while(temp != null) {
            System.out.println(String.format("key-%s: value-%s", temp.key, temp.value));
            temp = temp.next;
        }
    }
    public static void main(String[] args) {
        LRUCache2 lru = new LRUCache2(2);
        lru.put(2,1);
        lru.put(1,1);
        lru.put(2,3);
        lru.put(4,1);
        lru.get(1);
        lru.get(2);
    }
  
}
