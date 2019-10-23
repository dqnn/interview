package hatecode;

import java.util.*;
public class _1054DistantBarcodes {
/*
1054. Distant Barcodes
In a warehouse, there is a row of barcodes, where the i-th barcode is barcodes[i].

Rearrange the barcodes so that no two adjacent barcodes are equal.  You may return any answer, and it is guaranteed an answer exists.

 

Example 1:

Input: [1,1,1,2,2,2]
Output: [2,1,2,1,2,1]
*/
    //thinking process : O(n)/O(n)
    
    //this is like LRU thinking, 
    public int[] rearrangeBarcodes(int[] b) {
        if (b == null || b.length == 0) return b;
        
        Map<Integer, Integer> map = new HashMap<>();
        int max_fre = 0;
        for (int num : b) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            max_fre = Math.max(max_fre, map.get(num));
        }
        
        List<Integer>[] buckets = new ArrayList[max_fre + 1];
        for (int num : map.keySet()) {
            int c = map.get(num);
            //to save some space
            if (buckets[c] == null) {
                buckets[c] = new ArrayList<>();
            }
            buckets[c].add(num);
        }
        
        int index = 0;
        int[] res = new int[b.length];
        for (int i = max_fre; i >= 1; i--) {
            if (buckets[i] == null) continue;
            for (int num : buckets[i]) {
                int j = i;
                while (j > 0) {
                    res[index] = num;
                    index = index + 2 < b.length ? index + 2 : 1;
                    j--;
                } 
            }
        }
        return res;
    }
    
    public int[] rearrangeBarcodes_BF(int[] barcodes) {
        if(barcodes == null || barcodes.length < 1) return barcodes;
        int[] res = new int[barcodes.length];
        if (barcodes == null || barcodes.length == 0) return new int[]{};
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : barcodes) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> pq
                = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue() == 0 ? b.getKey() - a.getKey() :
                b.getValue() - a.getValue());
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.offer(entry);
        }
        int counter = 0;
        while (!pq.isEmpty()) {
            Map.Entry<Integer, Integer> first = pq.poll();
            res[counter++] = first.getKey();
            if (!pq.isEmpty()) {
                Map.Entry<Integer, Integer> second = pq.poll();
                res[counter++] = second.getKey();
                if (second.getValue() > 1) {
                    second.setValue(second.getValue() - 1);
                    pq.add(second);
                }
            }
            if (first.getValue() > 1) {
                first.setValue(first.getValue() - 1);
                pq.add(first);
            }
        }
        return res;
    }
}