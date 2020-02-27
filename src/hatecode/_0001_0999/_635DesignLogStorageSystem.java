package hatecode._0001_0999;
import java.util.*;
/*
635. Design Log Storage System
put(1, "2017:01:01:23:59:59");
put(2, "2017:01:01:22:59:59");
put(3, "2016:01:01:00:00:00");
retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"); // return [1,2,3], because you need to return all logs within 2016 and 2017.
retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"); // return [1,2], because you need to return all logs start from 2016:01:01:01 to 2017:01:01:23, where log 3 is left outside the range.
*/
public class _635DesignLogStorageSystem {
    private String minTemplate, maxTemplate;
    private HashMap<String, Integer> timestampIndexMap;
    private TreeMap<String, List<Integer>> logs;
    public _635DesignLogStorageSystem() {
        minTemplate = "2000:01:01:00:00:00";
        maxTemplate = "2017:12:31:23:59:59";
        timestampIndexMap = new HashMap<>();
        timestampIndexMap.put("Year", 4);
        timestampIndexMap.put("Month", 7);
        timestampIndexMap.put("Day", 10);
        timestampIndexMap.put("Hour", 13);
        timestampIndexMap.put("Minute", 16);
        timestampIndexMap.put("Second", 19);
        logs = new TreeMap<>();
    }

    public void put(int id, String timestamp) {
        logs.computeIfAbsent(timestamp, v->new ArrayList<>()).add(id);
    }

    public List<Integer> retrieve(String s, String e, String gra) {
        List<Integer> res = new ArrayList<>();
        if (s.compareTo(e) > 0) return res;
        
        int index = timestampIndexMap.get(gra);
        String start = s.substring(0, index)+minTemplate.substring(index), end = e.substring(0, index)+maxTemplate.substring(index);
        //TODO: why s = '2016' is not equals to 2016:00:00:00:00:00
        Map<String, List<Integer>> submap = logs.subMap(start, true, end, true);
        
        submap.values().forEach(ele->res.addAll(ele));
        return res;
    }
    
    //another solution is to use Trie to store the timestamp here, but that time complexity would 
    //be higher
}