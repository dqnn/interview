package hatecode._0001_0999;
import java.util.*;
public class _432AllOOneDataStructure {
/*
 * tag: double linked list, bucket sort, lfu, lru, AllOne
432. All O`one Data Structure
Implement a data structure supporting the following operations:

Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. 
Key is guaranteed to be a non-empty string.
Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise 
decrements an existing key by 1. If the key does not exist, this function does nothing. 
Key is guaranteed to be a non-empty string.
GetMaxKey() - Returns one of the keys with maximal value. If no element exists, 
return an empty string "".
GetMinKey() - Returns one of the keys with minimal value. If no element exists, 
return an empty string "".
Challenge: Perform all these in O(1) time complexity.

Mainly to use double linked list, each node is bucket, it contains count-> string keys mapping
 */
    // maintain a doubly linked list of Buckets
    private Bucket head;
    private Bucket tail;
    // for accessing a specific Bucket among the Bucket list in O(1) time
    private Map<Integer, Bucket> countBktMap;
    // keep track of count of keys, count--> key
    private Map<String, Integer> kcMap;

    // each Bucket contains all the keys with the same count
    private class Bucket {
        int count;
        Set<String> keySet;
        Bucket next;
        Bucket pre;
        public Bucket(int cnt) {
            count = cnt;
            //do not need linkedHashSet since dec on key  string
            keySet = new HashSet<>();
        }
    }

    /** Initialize your data structure here. */
    public _432AllOOneDataStructure() {
        head = new Bucket(Integer.MIN_VALUE);
        tail = new Bucket(Integer.MAX_VALUE);
        head.next = tail;
        tail.pre = head;
        countBktMap = new HashMap<>();
        kcMap = new HashMap<>();
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if (kcMap.containsKey(key)) {
            changeKey(key, 1);
        } else {
            kcMap.put(key, 1);
            if (head.next.count != 1) 
                addBucketAfter(new Bucket(1), head);
            head.next.keySet.add(key);
            countBktMap.put(1, head.next);
        }
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if (kcMap.containsKey(key)) {
            int count = kcMap.get(key);
            if (count == 1) {
                kcMap.remove(key);
                removeKeyFromBucket(countBktMap.get(count), key);
            } else {
                changeKey(key, -1);
            }
        }
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        return tail.pre == head ? "" : (String) tail.pre.keySet.iterator().next();
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        return head.next == tail ? "" : (String) head.next.keySet.iterator().next();        
    }
    
    // helper function to make change on given key according to offset
    private void changeKey(String key, int offset) {
        int count = kcMap.get(key);
        kcMap.put(key, count + offset);
        Bucket curBucket = countBktMap.get(count);
        Bucket newBucket;
        if (countBktMap.containsKey(count + offset)) {
            // target Bucket already exists
            newBucket = countBktMap.get(count + offset);
        } else {
            // add new Bucket
            newBucket = new Bucket(count + offset);
            countBktMap.put(count + offset, newBucket);
            addBucketAfter(newBucket, offset == 1 ? curBucket : curBucket.pre);
        }
        newBucket.keySet.add(key);
        removeKeyFromBucket(curBucket, key);
    }

    private void removeKeyFromBucket(Bucket bucket, String key) {
        bucket.keySet.remove(key);
        if (bucket.keySet.size() == 0) {
            removeBucketFromList(bucket);
            countBktMap.remove(bucket.count);
        }
    }

    private void removeBucketFromList(Bucket bucket) {
        bucket.pre.next = bucket.next;
        bucket.next.pre = bucket.pre;
        bucket.next = null;
        bucket.pre = null;
    }

    // add newBucket after preBucket
    private void addBucketAfter(Bucket newBucket, Bucket preBucket) {
        newBucket.pre = preBucket;
        newBucket.next = preBucket.next;
        preBucket.next.pre = newBucket;
        preBucket.next = newBucket;
    }
}

class AllOne {


    class Node {
        int v;
        Node prev, next;
        Set<String> keys;
        public Node(int v) {
            this.v = v;
            this.keys = new HashSet<>();
        }

        public String toString(){
            return ""+ v + "--" + keys + "--prev:" + prev + "--next: " + next;
        }
    }

    Map<String, Integer> k2vMap;
    Map<Integer, Node> vMap;
    Node head, tail;
    public AllOne() {
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.prev = head;

        k2vMap = new HashMap<>();
        vMap = new HashMap<>();
    }

    private void insertAfter(Node tmp, Node head) {
        Node next = head.next;

        head.next = tmp;
        tmp.prev = head;

        tmp.next = next;
        next.prev = tmp;
    }
    /*
    1. if not in k2vMap
      a. 
    */

    private void remove(Node tmp) {
        Node prev = tmp.prev;
        Node next = tmp.next;

        prev.next = next;
        next.prev = prev;

        tmp.prev = null;
        tmp.next = null;
    }

    public void inc(String k) {
        if(!k2vMap.containsKey(k)) {
            Node tmp = new Node(1);
            tmp.keys.add(k);
            k2vMap.put(k, 1);
            
            if(!vMap.containsKey(1)) {
                vMap.put(1, tmp);
                insertAfter(tmp, head);
            } else {
                Node exist = vMap.get(1);
                exist.keys.add(k);
            }
        } else {
            int prevCount = k2vMap.get(k);
            //k2vMap
            k2vMap.remove(k);
            k2vMap.put(k, prevCount+1);
            //vMap
            Node cur = vMap.get(prevCount);
            //double linked list
            if(vMap.containsKey(prevCount+1)) {
                vMap.get(prevCount+1).keys.add(k);
            } else {
                Node tmp = new Node(prevCount+1);
                tmp.keys.add(k);
                insertAfter(tmp, cur);
            }

            //process vMap
            if(cur.keys.size() == 1) {
                remove(cur);
                vMap.remove(1);
            } else {
                cur.keys.remove(k);
            }
        }
    }
    
    public void dec(String k) {
        int prevCount = k2vMap.get(k);
        if(prevCount == 1) {
            k2vMap.remove(k);
        } else {
            k2vMap.put(k, prevCount-1);
        }

        Node cur = vMap.get(prevCount);
        if(vMap.containsKey(prevCount -1)) {
            vMap.get(prevCount -1).keys.add(k);
        } else {
            Node tmp = new Node(prevCount - 1);
            insertAfter(tmp, cur.prev);
        }

        if(cur.keys.size() == 1) {
            remove(cur);
        } else {
            cur.keys.remove(k);
        }
    }
    
    public String getMaxKey() {
        Node maxNode = tail.prev;
        return maxNode.keys.size() == 0 ? "" : maxNode.keys.iterator().next();
    }
    
    public String getMinKey() {
        Node minNode = head.next;
        return minNode.keys.size() == 0 ? "" : minNode.keys.iterator().next();
    }

    public static void main(String[] args) {
        AllOne test = new AllOne();
        test.inc("hello");
        test.inc("goodbye");
        test.inc("hello");
        test.inc("hello");
        test.inc("hello");
        System.out.println(test.getMaxKey());
        test.inc("leet");
         test.inc("code");
         test.dec("leet");
         test.inc("leet");
         test.inc("hello");
         test.inc("leet");
    }
}
