package companies;

import java.util.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StripQuestions {
/*
电面的题目非常简单，就跟他说的一样，不考alg，只看你的思路.
第一问：一个database，以array of hashmap的形式储存，每一个hashmap是一个record，给你一个key，
要你输出database里对应这个key的value最小的record, key is not there means it has a value 0 
第二问：给一个key，和一个direction("asc", "desc")，如果是asc输出最小的record，desc输出最大的
第三问：用第二问的function实现第一问
第四问：写一个comparator来实现第二问
第五问：给一个sortorder 2-d string， 每一个都是key， direction，就是先根据第一个的key，direction排序，
有tie再根据第二个，第三个...
第六位：用第五问的function来实现第二问
 */

    //第四问
     public class SortByRecordsComparator implements Comparator<Map<String, Integer>> {
         private String key;
         private String dir;
         public SortByRecordsComparator(String key, String dir) {
             this.dir = dir;
             this.key = key;
         }
         @Override
         public int compare(Map<String, Integer> left, Map<String, Integer> right) {
             if (left == null || right == null) return Integer.MIN_VALUE;
             int first = left.get(key) == null ? 0 : left.get(key);
             int sec = right.get(key) == null ? 0 : right.get(key);
             return dir.equals("asc") ? first - sec : sec- first;
         }
     }
     //the input is valid
     public void sortFirstByOrder(LinkedHashMap<String, String> sortedOrder, 
             List<Map<String, Integer>> records){ 
         if (sortedOrder == null || sortedOrder.size() < 1 
                 || records == null || records.size() < 1) return;
         for(String key : sortedOrder.keySet()) {
             Collections.sort(records, new SortByRecordsComparator(key, sortedOrder.get(key)));
         }
     }
    
   //第二问
    public static Map<String, Integer> firstByKey(String key, String dir, List<Map<String, Integer>> records) {
        Map<String, Integer> res = new HashMap<>();
        if (key == null || records == null || records.size() < 1) {
            return res;
        }
        Integer preVal = null;
        for(Map<String, Integer> map: records) {
            if (map == null) continue;
            int curVal = map.get(key) == null ? 0: map.get(key) ;
            if (dir.equals("desc")) {
                if (preVal == null) preVal = Integer.MIN_VALUE;
                if (preVal < curVal) {
                    preVal = curVal;
                    res = map;
                }
            } else if (dir.equals("asc")) {
                if (preVal == null) preVal = Integer.MAX_VALUE;
                if (preVal > curVal) {
                    preVal = curVal;
                    res = map;
                }
            } else {}
        }
        return res;
    }
    //第三问
    public static Map<String, Integer> minByKey(String key, List<Map<String, Integer>> records) {
        return firstByKey(key, "asc", records);
    }
    //第一问
    public static Map<String, Integer> minByKey2(String key, List<Map<String, Integer>> records) {
        Map<String, Integer> res = new HashMap<>();
        if (key == null || records == null || records.size() < 1) {
            return res;
        }
        int min = Integer.MAX_VALUE;
        
        for(Map<String, Integer> map: records) {
            if (map == null) continue;
            int val = map.get(key) == null ? 0: map.get(key) ;
            if (min > val) {
                min = val;
                res = map;
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
        testMinByKey();
        testFirstByKey();
    }
    
    
    public static <T> void assertEqual(T expected, T actual) {
        if (expected == null && actual == null || actual != null && actual.equals(expected)) {
          System.out.println("PASSED");
        } else {
          throw new AssertionError("Expected:\n  " + expected + "\nActual:\n  " + actual + "\n");
        }
      }
    
    public static void testFirstByKey() {
        List<Map<String, Integer>> example1 = Arrays.asList(Maps.of("a", 1));
        List<Map<String, Integer>> example2 = Arrays.asList(
          Maps.of("b", 1),
          Maps.of("b", -2),
          Maps.of("a", 10)
        );
        List<Map<String, Integer>> example3 = Arrays.asList(
          Maps.of(),
          Maps.of("a", 10, "b", -10),
          Maps.of(),
          Maps.of("a", 3, "c", 3)
        );

        System.out.println("firstByKey");
        assertEqual(example1.get(0), firstByKey("a", "asc", example1));
        assertEqual(example2.get(0), firstByKey("a", "asc", example2));  // example2.get(1) ok too
        assertEqual(example2.get(2), firstByKey("a", "desc", example2));
        assertEqual(example2.get(1), firstByKey("b", "asc", example2));
        assertEqual(example2.get(0), firstByKey("b", "desc", example2));
        assertEqual(example3.get(1), firstByKey("a", "desc", example3));
      }
    

      public static void testMinByKey() {
        List<Map<String, Integer>> example1 = Arrays.asList(
          Maps.of("a", 1, "b", 2),
          Maps.of("a", 2)
        );
        List<Map<String, Integer>> example2 = Arrays.asList(example1.get(1), example1.get(0));
        List<Map<String, Integer>> example3 = Arrays.asList(Maps.of());
        List<Map<String, Integer>> example4 = Arrays.asList(
          Maps.of("a", -1),
          Maps.of("b", -1)
        );

        System.out.println("minByKey");
        assertEqual(example1.get(0), minByKey("a", example1));
        assertEqual(example2.get(1), minByKey("a", example2));
        assertEqual(example1.get(1), minByKey("b", example1));
        assertEqual(example3.get(0), minByKey("a", example3));
        assertEqual(example4.get(1), minByKey("b", example4));
      }
    }

    class Maps {
      public static <K, V> Map<K, V> of() {
        return new HashMap<K, V>();
      }

      public static <K, V> Map<K, V> of(K k1, V v1) {
        Map<K, V> map = new HashMap<K, V>();
        map.put(k1, v1);
        return map;
      }

      public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2) {
        Map<K, V> map = new HashMap<K, V>();
        map.put(k1, v1);
        map.put(k2, v2);
        return map;
      }

      public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
        Map<K, V> map = new HashMap<K, V>();
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        return map;
      }

      public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        Map<K, V> map = new HashMap<K, V>();
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        return map;
      }

      public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        Map<K, V> map = new HashMap<K, V>();
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        map.put(k5, v5);
        return map;
      }

      public static <K, V> LinkedHashMap<K, V> ordered(K k1, V v1) {
        LinkedHashMap<K, V> map = new LinkedHashMap<K, V>();
        map.put(k1, v1);
        return map;
      }

      public static <K, V> LinkedHashMap<K, V> ordered(K k1, V v1, K k2, V v2) {
        LinkedHashMap<K, V> map = new LinkedHashMap<K, V>();
        map.put(k1, v1);
        map.put(k2, v2);
        return map;
      }

      public static <K, V> LinkedHashMap<K, V> ordered(K k1, V v1, K k2, V v2, K k3, V v3) {
        LinkedHashMap<K, V> map = new LinkedHashMap<K, V>();
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        return map;
      }

}
