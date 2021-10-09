package hatecode._1000_1999;

import java.util.*;

public class TikTokOASExpressions {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
    
    // in = {(B,D), (D, E), (A, B), (C,F), (E, G), (A, C)}
    private static String sExpression(String[][] in) {
        
        Map<String, List<String>> map = new HashMap<>();
        Set<String> all = new HashSet<>(), remove = new HashSet<>();
        
        for(String[] s: in) {
            map.computeIfAbsent(s[0], v->new ArrayList<>()).add(s[1]);
            all.add(s[0]);
            remove.add(s[1]);
            
        }
        
        
        
        
        
        map.computeIfAbsent("D", v->new ArrayList<>()).add("E");
        map.computeIfAbsent("A", v->new ArrayList<>()).add("B");
        map.computeIfAbsent("C", v->new ArrayList<>()).add("F");
        map.computeIfAbsent("E", v->new ArrayList<>()).add("G");
        map.computeIfAbsent("A", v->new ArrayList<>()).add("C");
       
        
        //helper();
        return "";
    }
    
    
    private String helper(Map<String, List<String>> map, String cur, String node) {
        if (!map.containsKey(node)) return "";
        
        List<String> val = map.get(node);
        if (val == null || val.size() == 0) {
            return cur;
        }
        for(String ch: val) {
            helper(map, cur+"(" + node+ ")", ch);
        }
        return "";
    }

}
