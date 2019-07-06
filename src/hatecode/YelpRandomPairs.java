package hatecode;

import java.util.*;
public class YelpRandomPairs {
/*
 * Just Yelp interview questions, not from LC
 */
    public static void main(String[] args) {
        String[] in = {"David", "LILI", "HANMEI", "XILEI"};
        randomPair(in).stream().forEach(e->System.out.println(Arrays.toString(e)));
    }
    
    public static List<String[]> randomPair(String[] list) {
        List<String[]> res = new ArrayList<>();
        if (list == null || list.length < 1 || list.length % 2 == 1) return res;
        
        int n = list.length;
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        Random rnd = new Random();
        int cnt = 0;
        while(cnt < n) {
            int idx = rnd.nextInt(n);
            if(visited.contains(idx)) continue;
            visited.add(idx);
            q.offer(idx);
            cnt++;
        }
        System.out.println(q);
        while(!q.isEmpty()) {
            int f = q.poll();
            int s = q.poll();
            res.add(new String[] {list[f], list[s]});
        }
        
        return res;
    }

}
