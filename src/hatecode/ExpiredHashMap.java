package hatecode;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
//Thinking process:
/*
 * design expired hashmap, 
   put(key, value, expiredTime), expiredTime is a time in future, 
   get(key)
   
   //brute force solution is to have 2 hashmaps, 
    Map<Key, value> map1
    Map<Key, expireTime> map2,
    every time when we run get, we will check current entry is expired or not, 
 */
public class ExpiredHashMap<K, V> {
    private Map<K, V>                  map;
    private Map<K, Long>               time;
    private static final int   DEFAULT_CAPACITY    = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    
    private Thread  expireEntryCleaner  = new Thread(new Runnable() {
                                                       @Override
                                                       public void run() {
                                                           while (true) {
                                                               try {
                                                                   Thread.sleep(5000);
                                                               } catch (Exception e) {
                                                                   e.printStackTrace();
                                                               }
                                                               for (K key : map.keySet())
                                                                   get(key);
                                                           }
                                                       }

                                                   });

    public ExpiredHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public ExpiredHashMap(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    public ExpiredHashMap(int capacity, float loadFactor) {
        map = new ConcurrentHashMap<>(capacity, loadFactor);
        time = new ConcurrentHashMap<>(capacity, loadFactor);
        expireEntryCleaner.start();
    }

    public synchronized V get(K key) {
        long now = System.currentTimeMillis();
        Long expired = time.get(key);
        if (expired == null) return null;
        
        if (Double.compare(now, expired) > 0) {
            map.remove(key);
            time.remove(key);
            return null;
        } else {
            return map.get(key);
        }
    }

    public V put(K key, V value, long duration) {
        long now = System.currentTimeMillis();
        long expired = now + duration;
        time.put(key, expired);
        return map.put(key, value);
    }
}
