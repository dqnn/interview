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
    
    public int[] rearrangeBarcodes(int[] barcodes) {
        if (barcodes == null || barcodes.length == 0) return barcodes;
        Map<Integer, Integer> counter = new HashMap<>();
        int max_fre = 0;
        for (int num : barcodes) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
            if (counter.get(num) > max_fre) {
                max_fre = counter.get(num);
            }
        }
        List<Integer>[] buckets = new ArrayList[max_fre + 1];
        for (int num : counter.keySet()) {
            int c = counter.get(num);
            if (buckets[c] == null) {
                buckets[c] = new ArrayList<>();
            }
            buckets[c].add(num);
        }
        
        int index = 0;
        int[] res = new int[barcodes.length];
        for (int i = max_fre; i >= 1; i--) {
            List<Integer> b = buckets[i];
            if (b == null) continue;
            for (int num : b) {
                int j = i;
                while (j > 0) {
                    res[index] = num;
                    index = index + 2 < barcodes.length ? index + 2 : 1;
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