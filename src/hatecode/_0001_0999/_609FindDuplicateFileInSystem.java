package hatecode._0001_0999;

import java.util.*;
import java.util.stream.Collectors;
public class _609FindDuplicateFileInSystem {
/*
609. Find Duplicate File in System
Example 1:

Input:
["root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"]
Output:  
[["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]
*/
    public List<List<String>> findDuplicate(String[] paths) {
        List<List<String>> res = new ArrayList<>();
        if (paths == null || paths.length < 1) return res;
        
        Map<String, Set<String>> map = new HashMap<>();
        for(String p : paths) {
            String[] each = p.split("\\s+");
            String dir = each[0];
            for(int i = 1; i< each.length; i++) {
                int idx = each[i].indexOf("(");
                String fileName = each[i].substring(0, idx);
                String content = each[i].substring(idx, each[i].length());
                map.computeIfAbsent(content, v->new HashSet<>()).add(dir + "/" + fileName);
            }
        }
        
        for(Set<String> set : map.values()) {
            List<String> list = set.stream().collect(Collectors.toList());
            if (list.size() > 1) res.add(list);
        }
        
        return res;
    }
/*
Imagine you are given a real file system, how will you search files? DFS or BFS?
DFS/BFS, BFS consume more memory, 

If the file content is very large (GB level), how will you modify your solution?
map file to size, then we MD5 on its content, only MD5 is the same, then we will compare its content

If you can only read the file by 1kb each time, how will you modify your solution?
We can create the hash from the 1kb chunks, and then read the entire file if a full byte by byte comparison is required.

What is the time complexity of your modified solution? What is the most time-consuming part and memory consuming part of it? How to optimize?
O(n^2 * k) since in worse case we might need to compare every file to all others. k is the file size
How to make sure the duplicated files you find are not false positive?
We will use several filters to compare: File size, Hash and byte by byte comparisons.
*/
}