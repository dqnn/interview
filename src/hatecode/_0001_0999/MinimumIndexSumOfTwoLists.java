package hatecode._0001_0999;
import java.util.*;
public class MinimumIndexSumOfTwoLists {
/*
599. Minimum Index Sum of Two Lists
Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.

You need to help them find out their common interest with the least list index sum. If there is a choice tie between answers, output all of them with no order requirement. You could assume there always exists an answer.

Example 1:
Input:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
Output: ["Shogun"]
*/
    public String[] findRestaurant(String[] list1, String[] list2) {
        if (list1 == null || list2 == null) {
            return new String[0];
        }
        int sum = Integer.MAX_VALUE;
        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i< list1.length; i++) map.put(list1[i],i);
        
        for(int i = 0; i< list2.length;i++) {
            if (map.containsKey(list2[i])) {
                if (i + map.get(list2[i]) < sum) {
                    res.clear();
                    sum = i+ map.get(list2[i]);
                    res.add(list2[i]);
                } else if (i + map.get(list2[i]) == sum) {
                    res.add(list2[i]);
                }
            }
        }
        
        return res.toArray(new String[res.size()]);
        
    }
}