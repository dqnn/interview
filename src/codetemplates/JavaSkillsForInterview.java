package codetemplates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.Stack;
import java.util.TreeMap;

public class JavaSkillsForInterview {

    public static void main(String[] args) {
        
        TreeMap<Integer, String> t = new TreeMap<>();
        t.put(1, "a");
        t.put(2, "b");
        t.put(3, "c");
        t.put(4, "d");
        t.put(5, "e");
        //subMap(K fromKey,K toKey) inclusive, exclusive, output: 1=a, 2=b
        System.out.println("subMap:" + t.subMap(1, 3));
        //ubMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive), 
        System.out.println("subMap:" + t.subMap(1, true, 3, true)); // 1=a, 2=b, 3 = c
        //tailMap<K> return >= k view
        System.out.println("subMap:" + t.tailMap(2)); //2=b, 3 = c 4=d  5= e
        // String
        String s = "abc";
        s.charAt(0);
        s.length();
        s.substring(0, 1);
        s.substring(1);
        s.equals("b");
        s = s.trim();
        s.indexOf("a");
        s.indexOf("a", 1);
        s.lastIndexOf("a");
        s.indexOf("a", 1);
        s.toCharArray();
        //Integer.valueOf(s); // returns an Integer object
        //Integer.parseInt(s); // returns an int primitive
        String.valueOf(s); // integer to string
        // StringBuilder

        StringBuilder sb = new StringBuilder();
        sb.append("a");
        sb.insert(0, "a");
        sb.deleteCharAt(sb.length() - 1);
        sb.reverse();
        sb.toString();
        // Array
        int[] a = new int[10];
        char[] b = {
                'a', 'b' };
        int[][] c = new int[10][10];
        int m = a.length;
        int n = c[0].length;
        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;
        Arrays.sort(a);
        for (int i = 0; i < c.length; i++) {
            System.out.println(c[i]);
        }
        // List
        List<Integer> list = new ArrayList<>();
        list.add(0);
        // this can be used as stack, latter will be as first always
        list.add(0, 1);

        list.get(0);
        list.size();
        list.remove(list.size() - 1);
        Collections.sort(list);
        Collections.sort(list, Collections.reverseOrder());
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;// 0‐>1
                // return o2‐o1; 1‐>0
            }
        });
        //removeIf means they can run the lambda first before they do remove ops
        /**
         * 删除集合中符合条件的成员，empty集合也可以，但是null就炸啦。
         */
            List<String> list2 = new ArrayList<>(Arrays.asList("1","12","13","14","15","0"));
            System.out.println("初始时："+ list.toString());
            list2.removeIf(e -> e.contains("1")); //removed all string which contains 1
            System.out.println("过滤完：" + list2.toString()); //["0"]
        /*
         *
         */

        // Stack
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        stack.pop();
        stack.peek();
        stack.isEmpty();
        stack.size();
        // Queue add ‐‐‐‐‐‐> remove, peek
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(0);
        q.remove();
        q.peek();
        q.isEmpty();
        q.size();
        // HashMap
        Map<Character, List<String>> map1 = new HashMap<>();
        map1.computeIfAbsent('k', v->new ArrayList<>()).add("xxxx");
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('c', 1);
        map.get('c');
        if (map.containsKey('c')) {
        }
        if (map.containsValue(1)) {
        }
        for (Character d : map.keySet()) {
        }
        for (Integer i : map.values()) {
        }
        map.isEmpty();
        map.size();
        // HashSet
        Set<Integer> set = new HashSet<Integer>();
        set.add(0);
        set.remove(0);
        if (set.contains(0)) {
        }
        set.isEmpty();
        set.size();

        // mini heap
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        pq.add(0);
        pq.remove();
        pq.peek();
        pq.isEmpty();
        pq.size();
        while (!pq.isEmpty()) {
        }

    }

}
