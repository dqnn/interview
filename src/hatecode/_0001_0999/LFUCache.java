package hatecode._0001_0999;

import java.util.*;

public class LFUCache {
/*
Design and implement a data structure for Least Frequently Used (LFU) cache. 
It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, 
otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache 
reaches its capacity, it should invalidate the least frequently used item before inserting 
a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys
 that have the same frequency), the least recently used key would be evicted.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LFUCache cache = new LFUCache(2);

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.get(3);       // returns 3.
cache.put(4, 4);    // evicts key 1.
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
*/
    //thinking process: 
    //we use Double linked list to connect all nodes,so we can have O(1) on list remove from
    //low frquency list to high frequency. 
    
    //another key is to maintain lowest frequency number, how to maintain low:
    //1: if new kv inserted, low = 1;
    //2: if get(key), if key->DLinkedList count ==  low, then low ++
    //3. if evict(low),  if key->DLinkedList count ==  low, then low ++
    class DLinkedList {
        Node head;
        Node tail;
        int size;
        public DLinkedList(){
            this.head = new Node();
            this.tail = new Node();
            head.next = tail;
            tail.prev = head;
        }
        public void offer(Node newHead) {
            if (newHead == null) return;
            Node curNext = head.next;
            
            head.next = newHead;
            //newHead change
            newHead.prev = head;
            newHead.next = curNext;
            curNext.prev = newHead;
        }
        public Node remove(Node node) {
            Node prev = node.prev;
            Node next = node.next;
            
            prev.next = next;
            next.prev = prev;
            
            node.prev = null;
            node.next = null;
            return node;
        }
        public Node removeLast() {
            if (isEmpty()) return null;
            return remove(tail.prev);
        }
        public boolean isEmpty() {
            return head.next == tail && tail.prev == head;
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node cur = head.next;
            while(cur != tail) {
                sb.append(String.format("%s=%s", cur.key, cur.value));
                cur = cur.next;
            }
            return sb.toString();
        }
    }
    class Node {
        int key;
        int value;
        Node prev, next;
        int fre;
        public Node(){}
        public Node(int key, int value, int fre) {
            this.key = key;
            this.value = value;
            this.fre = fre;
        }
    }

    private Map<Integer, Node> kvMap;
    private Map<Integer, DLinkedList> fre2DNodeMap;
    int size;
    int capacity;
    int low;
    public LFUCache(int capacity) {
        this.kvMap = new HashMap<>();
        this.fre2DNodeMap = new HashMap<>();
        this.size = 0;
        this.capacity = capacity;
        this.low = 0;
    }
    
    public int get(int key) {
        if (!kvMap.containsKey(key)) return -1;
        
        Node node = kvMap.get(key);
        //remove from old list
        DLinkedList dlist = fre2DNodeMap.get(node.fre);
        dlist.remove(node);
        //handle low, two use cases:
        //low point to current fre and only element, in this case, low++
        //otherwise which means current fre is not smallest, low point to other and 
        //remain the same.
        if (node.fre == low && dlist.isEmpty()) {
            low += 1;
        }
        //remove if empty Dlist
        if (dlist.isEmpty()) {
            fre2DNodeMap.remove(node.fre);
        }
        
        //add to new fre mapping
        node.fre = node.fre + 1;
        fre2DNodeMap.computeIfAbsent(node.fre, v->new DLinkedList()).offer(node);
       
        
        
        return node.value;
    }
    
    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }
        if (kvMap.containsKey(key)) {
            Node node = kvMap.get(key);
            //remove from old list
            DLinkedList dlist = fre2DNodeMap.get(node.fre);
            dlist.remove(node);
            if (node.fre == low && dlist.isEmpty()) {
                low += 1;
            }
            if (dlist.isEmpty()) {
                fre2DNodeMap.remove(node.fre);
            }
            //add to new fre mapping
            node.fre +=1;
            node.value = value;
            fre2DNodeMap.computeIfAbsent(node.fre, v->new DLinkedList()).offer(node);
        } else {
            if (size + 1 > capacity) {
                DLinkedList leastList = fre2DNodeMap.get(low);
                Node tobeRMed = leastList.removeLast();
                if (tobeRMed.fre == low && leastList.isEmpty()) {
                    low += 1;
                }
                if (leastList.isEmpty()) {
                    fre2DNodeMap.remove(tobeRMed.fre);
                }
                //remove from kvMap
                kvMap.remove(tobeRMed.key);
                size--;
            }
            //real insert
            Node node = new Node(key, value, 1);
            kvMap.put(key, node);
            fre2DNodeMap.computeIfAbsent(node.fre, v->new DLinkedList()).offer(node);
            size += 1;
            low = 1;
        }
    }
    
    //interview friendly versions, but need to ask whether we can use LinkedHashSet<>
    public class LFUCacheV2 {
        HashMap<Integer, Integer> kvMap;
        HashMap<Integer, Integer> counts;
        HashMap<Integer, LinkedHashSet<Integer>> fre2KeySets;
        int cap;
        int min = -1;
        public LFUCacheV2(int capacity) {
            cap = capacity;
            kvMap = new HashMap<>();
            counts = new HashMap<>();
            fre2KeySets = new HashMap<>();
        }
        
        public int get(int key) {
            if(!kvMap.containsKey(key)) return -1;
            int count = counts.get(key);
            counts.put(key, count+1);
            fre2KeySets.get(count).remove(key);
            if(count==min && fre2KeySets.get(count).size()==0)
                min++;
            fre2KeySets.computeIfAbsent(count+1, V->new LinkedHashSet<>()).add(key);
            return kvMap.get(key);
        }
        
        public void set(int key, int value) {
            if(cap<=0) return;
            if(kvMap.containsKey(key)) {
                kvMap.put(key, value);
                get(key);
                return;
            } 
            if(kvMap.size() >= cap) {
                int evit = fre2KeySets.get(min).iterator().next();
                fre2KeySets.get(min).remove(evit);
                kvMap.remove(evit);
            }
            kvMap.put(key, value);
            counts.put(key, 1);
            min = 1;
            fre2KeySets.computeIfAbsent(1, V->new LinkedHashSet<>()).add(key);
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */