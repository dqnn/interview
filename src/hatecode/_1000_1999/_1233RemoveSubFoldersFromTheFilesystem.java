package hatecode._1000_1999;

import java.util.*;
import java.util.stream.Collectors;

public class _1233RemoveSubFoldersFromTheFilesystem {
/*
1233. Remove Sub-Folders from the Filesystem
Given a list of folders folder, return the folders after removing all sub-folders in those folders. You may return the answer in any order.

If a folder[i] is located within another folder[j], it is called a sub-folder of it.

The format of a path is one or more concatenated strings of the form: '/' followed by one or more lowercase English letters.

For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string and "/" are not.
 

Example 1:

Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
Output: ["/a","/c/d","/c/f"]
*/
    
    class TrieNode{
        Map<String, TrieNode> map = new HashMap<>();
        String word;
    }
    
    List<String> res = new ArrayList<>();
    TrieNode root = new TrieNode();
    List<String> getParentNodes(TrieNode root) {
        if (root == null) {
            return res;
        }
        
        if (root.word != null && root.word.length() > 0) res.add(root.word);
        else {
            for(Map.Entry<String, TrieNode> entry: root.map.entrySet()) {
                getParentNodes(entry.getValue());
            }
        }
        
        
        return res;
    }
    
    void buildTrie(String in){
        TrieNode cur = root;
        String[] chs = in.split("/");
        System.out.println(Arrays.deepToString(chs));
        for(int i = 1; i< chs.length; i++) {
            if (!cur.map.containsKey(chs[i])) {
              TrieNode n = new TrieNode();
              cur.map.put(chs[i], n);
            }
            
            cur = cur.map.get(chs[i]);
            if (i == chs.length -1) cur.word = in;
        }
    }
    
    //thinking process: 
    
    //the problem is to say: give list of directories, 
    //return list of folders which does not have any subfolders.
    //eg: /a/b, /a, /c/b, /c/f, --> /a, /c/b, /c/f, we cannot return /c because there is no /c folder
    // in the list
    //this is academic solution, use trie to store the strings, also
    //use word to end the search in advance! 
    
    public List<String> removeSubfolders(String[] fs) {
        for(String s: fs) {
            buildTrie(s);
        }
        
        return getParentNodes(root);
    }
    

    /*
     * O(nm) one easy way to find the parent folder, we use set to record all folders 
     * 
     */
    public List<String> removeSubfolders_Set(String[] A) {
        if(A == null || A.length < 1) return new ArrayList<>();
        
        Set<String> set = new HashSet<>(Arrays.asList(A));
        
        Set<String> res = new HashSet<>();
        for(String s: A) {
            if(!helper(s, set)) {
                res.add(s);
            }
        }
        
        return res.stream().collect(Collectors.toList());
    }
    
    //decide whether s is sub-folder, return true if yes, 
    private boolean helper(String s, Set<String> set) {
        if(s.length() == 2) return false;
        
        set.remove(s);
        String ss =s;
        while(ss.length() >= 2) {
            if(set.contains(ss)) {
                return true;
            }
            
            ss = ss.substring(0, ss.lastIndexOf('/'));
        }
        set.add(s);
        return false;
    }
    
    //this is tricky solution while it is not the purpose of the question
    public List<String> removeSubfolders2(String[] fs) {
        //Arrays.sort(fs, (a, b)->(a.length() - b.length()));
        Arrays.sort(fs);
        //System.out.println(Arrays.deepToString(fs));
        
        List<String> res = new ArrayList<>();
        for(String dir: fs) {
            if (res.size() == 0 || !dir.startsWith(res.get(res.size() - 1) + "/")) {
                res.add(dir);
            }
        }
        return res;
    }
}