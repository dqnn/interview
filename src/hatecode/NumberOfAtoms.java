package hatecode;
import java.util.*;
public class NumberOfAtoms {
/*
726. Number of Atoms
Example 1:
Input: 
formula = "H2O"
Output: "H2O"
Explanation: 
The count of elements are {'H': 2, 'O': 1}.
Example 2:
Input: 
formula = "Mg(OH)2"
Output: "H2MgO2"
Explanation: 
The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.
*/
    
    //interview friendly, "K4(ON(SO3)2)2"--> 
    //
    public String countOfAtoms(String f) {
        if (f == null || f.length() < 1) return "";
        
        Stack<Map<String, Integer>> stack = new Stack<>();
        
        Map<String, Integer> map = new HashMap<>();
        int i = 0, n = f.length();
        while(i < n) {
            char c = f.charAt(i); i++;
            if (c == '(') {
                stack.push(map);
                map = new HashMap<>();
            } else if (c == ')') {
                int val = 0;
                while(i < n && Character.isDigit(f.charAt(i))) {
                    val = val * 10 + f.charAt(i) - '0';
                    i++;
                }
                if (val == 0) val = 1;
                Map<String, Integer> temp = map;
                map = stack.pop();
                
                for(String key : temp.keySet()) {
                    map.put(key, map.getOrDefault(key, 0) + temp.get(key) *val);
                }
            //process Character
            } else {
                //since i advanced no matter which character
                int l = i - 1;
                while(i < n && Character.isLowerCase(f.charAt(i))) {
                    i++;
                }
                
                String s = f.substring(l, i);
                int val=0;
                while(i<n && Character.isDigit(f.charAt(i))) val=val*10+ f.charAt(i++)-'0';
                if(val==0) val=1;
                map.put(s,map.getOrDefault(s,0)+val);
            }
        }
        
        StringBuilder sb= new StringBuilder();
        List<String> list= new ArrayList<>(map.keySet());
        Collections.sort(list);
        for(String key: list){ 
            sb.append(key);
          if(map.get(key)>1) sb.append(map.get(key));
        }
        return sb.toString();
        
    }
}