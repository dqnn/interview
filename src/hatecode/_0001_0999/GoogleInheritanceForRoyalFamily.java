package hatecode._0001_0999;

import java.util.*;
public class GoogleInheritanceForRoyalFamily {
/*
 * this is Google interview question, not from LC, 
 * https://www.1point3acres.com/bbs/thread-429208-1-1.html
 * 
 * Problem statement:
 * implement 3 APIs, 
 * void birth(String parent, String name) 父亲名字和孩子名字，生个娃
 * void death(String name) name to be dead
 * List<String> getOrder() 返回当前的继承顺序，string array/list

inheritance must be that node and its sub tree all dead, then it will go to second from left to right
 */
    
    //thinking process:
    
    //one key here is that we do not want to remove the dead nodes, becuase that is not easy to maintain 
    //the tree, 
    //but if interview would like to remove the dead nodes, then we should consider to merge the sub trees
    Map<String, List<String>> map ;
    Set<String> dead;
    String root;
    public GoogleInheritanceForRoyalFamily() {
        map = new HashMap<>();
        dead = new HashSet<>();
        root = "King";
        map.computeIfAbsent(root, v->new ArrayList<>());
    }
    public void birth(String p, String name) {
       if (!map.containsKey(p)) return; //this should handle some exceptions
       map.computeIfAbsent(p, v->new ArrayList<>()).add(name);
   }
   public void death(String name) {
       if (dead.contains(name)) return; //handle some exceptions
       dead.add(name);
   }
   
   public List<String> getOrder() {
       List<String> res = new ArrayList<>();
       helper(res, root);
       return res;
   }
   
   private void helper(List<String> res, String name) {
       if (!dead.contains(name)) res.add(name);
       for(String node: map.get(name)) {
           helper(res, node);
       }
   }

}
