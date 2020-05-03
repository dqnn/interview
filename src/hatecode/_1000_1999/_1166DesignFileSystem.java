package hatecode._1000_1999;

import java.util.*;
public class _1166DesignFileSystem {
/*
1166. Design File System
You are asked to design a file system which provides two functions:

createPath(path, value): Creates a new path and associates a value to it if possible and returns True. Returns False if the path already exists or its parent path doesn't exist.
get(path): Returns the value associated with a path or returns -1 if the path doesn't exist.
The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters. For example, /leetcode and /leetcode/problems are valid paths while an empty string and / are not.

Implement the two functions.

Please refer to the examples for clarifications.

 

Example 1:

Input: 
["FileSystem","createPath","get"]
[[],["/a",1],["/a"]]
Output: 
[null,true,1]
Explanation: 
FileSystem fileSystem = new FileSystem();

fileSystem.createPath("/a", 1); // return true
fileSystem.get("/a"); // return 1
*/
    
    //thinking process: O(n)/O(n)
    
    //trie is what the interview want, simple solution is just hack, we do not need the map
    //but it do could speed up the looking up
    class TrieNode {
        String name;
        int value;
        Map<String, TrieNode> children;

        TrieNode(String name, int value) {
            this.name = name;
            this.value = value;
            this.children = new HashMap<>();
        }
    }
    private TrieNode root;

    public _1166DesignFileSystem() {
        this.root = new TrieNode("/", -1);
    }
    
    public boolean createPath(String path, int value) {
        String[] folders = path.split("/");

        TrieNode currNode = this.root;
        String name = folders[folders.length - 1];

        for (int i = 0; i < folders.length - 1; i++) {
            if (folders[i].equals("")) {
                continue;
            }
            if (currNode.children.containsKey(folders[i])) {
                currNode = currNode.children.get(folders[i]);
            } else {
                return false;
            }
        }
        
        // check if the path exists
        if (currNode.children.containsKey(name)) {
            return false;
        }

        currNode.children.put(name, new TrieNode(name, value));
        return true;
    }
    
    public int get(String path) {
        String[] folders = path.split("/");
        TrieNode currNode = this.root;

        for (String folder: folders) {
            if (folder.equals("")) {
                continue;
            }
            if (currNode.children.containsKey(folder)) {
                currNode = currNode.children.get(folder);
            } else {
                return -1;
            }
        }

        return currNode.value;
    }
    
    class SimpleSolution {
        Map<String, Integer> file = new HashMap<>(); 
    
    public SimpleSolution() {
        file.put("", -1);
    }
    
    public boolean create(String path, int value) {
        int idx = path.lastIndexOf("/");
        String parent = path.substring(0, idx);
        if (!file.containsKey(parent)) { return false; }
        return file.putIfAbsent(path, value) == null;   
    }
    
    public int get(String path) {
        return file.getOrDefault(path, -1);
    }
    }
}