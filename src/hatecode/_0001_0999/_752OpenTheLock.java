package hatecode._0001_0999;
import java.util.*;
public class _752OpenTheLock {
/*
752. Open the Lock

The lock as 4 wheels and start is 0000, each move is move one wheel to next digit
Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
Output: 6
Explanation:
A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
because the wheels of the lock become stuck after the display becomes the dead end "0102".
*/
    //thinking process: 
    //TODO: understand the complexity, O(n^2 * A^N + D)/O(A^N + D), A is number of digits in alphebet,
    //N is number of digits in lock, D is size of deadlock
    
    //BFS is better than DFS here, because BFS can get the answer then stop while DFS still have to 
    //search, so we choose bfs as correct and fast approch
    public int openLock(String[] deadends, String target) {
        Set<String> begin = new HashSet<>();
        Set<String> end = new HashSet<>();
        //
        Set<String> visited = new HashSet<>(Arrays.asList(deadends));
        
        begin.add("0000");
        
        end.add(target);
        int level = 0;
        Set<String> nextSet;
        while (!begin.isEmpty() && !end.isEmpty()) {
            // here is the trick, we start from begin, but then begin is default to nextSet like a 
            //queue then we compare to end, we always assign begin to be smaller in begin, end
            //then end = nextSet, so begin and end size will be more closem this could make dfs faster
            if (begin.size() > end.size()) {
                nextSet = begin;
                begin = end;
                end = nextSet;
            }
            nextSet = new HashSet<>();
            for (String s : begin) {
                //if found then return no need to continue, highest priority
                if (end.contains(s)) return level;
                if (visited.contains(s)) continue;
                
                visited.add(s);
                StringBuilder sb = new StringBuilder(s);
                //so each time, we only have 4 wheels and each wheel have one chance to move, 
                //each chance will have 2 move directions, so we will have 8 new string each time
                for (int i = 0; i < 4; i++) {
                    char c = sb.charAt(i);
                    //this either 0 or "0", we cannot write as '0' because it will considered as 
                    //add not strings
                    String s1 = sb.substring(0, i) + (c == '9' ? 0 : c - '0' + 1) + sb.substring(i + 1);
                    String s2 = sb.substring(0, i) + (c == '0' ? 9 : c - '0' - 1) + sb.substring(i + 1);

                    if (!visited.contains(s1)) nextSet.add(s1);
                    if (!visited.contains(s2)) nextSet.add(s2);
                }
            }
            level++;
            begin = nextSet;
        }
        return -1;
    }


    /*
     * BFS version 
     * 
     */
    public int openLock_BFS(String[] d, String t) {
        if (t == null || t.length() < 1) return -1;
        
        Set<String> end = new HashSet<>(Arrays.asList(d));
        
        Queue<int[]> q = new LinkedList<>();
        int[] s = new int[]{0,0,0,0};
        q.offer(s);
        
        Set<String> visited = new HashSet<>();
        visited.add(getKey(s));
        
        int step = 0;
        
        while(!q.isEmpty()) {
            int size = q.size();
            
            while(size-- > 0) {
                int[] code = q.poll();
                String key = getKey(code);
                if (key.equals(t)) return step;
                
                if(end.contains(key)) continue;
                for(int i =0 ;i < 4; i++) {
                    int[] temp = Arrays.copyOfRange(code, 0, code.length);
                    temp[i] = (temp[i] + 1) % 10;
                    String nKey = getKey(temp);
                    
                    if(!visited.contains(nKey)) {
                        q.offer(temp);
                        visited.add(nKey);
                    }
                    
                    int[] temp2 = Arrays.copyOfRange(code, 0, code.length);
                    temp2[i] = (temp2[i] - 1 + 10) % 10;
                    String nKey2 = getKey(temp2);
                    
                    if(!visited.contains(nKey2)) {
                        q.offer(temp2);
                        visited.add(nKey2);
                    }
                 }
                
            }
            
            step++;
        }
        
        
        return -1;
    }
    
    private String getKey(int[] A) {
        StringBuilder sb = new StringBuilder();
        
        Arrays.stream(A).forEach(e->sb.append(e));
        return sb.toString();
    }
}