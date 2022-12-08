package hatecode._0001_0999;

import java.util.*;
import java.util.stream.Collectors;
public class _588DesignInMemoryFileSystem {
/*
588. Design In-Memory File System
Design a data structure that simulates an in-memory file system.

Implement the FileSystem class:

FileSystem() Initializes the object of the system.
List<String> ls(String path)
If path is a file path, returns a list that only contains this file's name.
If path is a directory path, returns the list of file and directory names in this directory.
The answer should in lexicographic order.
void mkdir(String path) Makes a new directory according to the given path. The given directory path does not exist. If the middle directories in the path do not exist, you should create them as well.
void addContentToFile(String filePath, String content)
If filePath does not exist, creates that file containing given content.
If filePath already exists, appends the given content to original content.
String readContentFromFile(String filePath) Returns the content in the file at filePath.
 

Example 1:
Input
["FileSystem", "ls", "mkdir", "addContentToFile", "ls", "readContentFromFile"]
[[], ["/"], ["/a/b/c"], ["/a/b/c/d", "hello"], ["/"], ["/a/b/c/d"]]
Output
[null, [], null, null, ["a"], "hello"]

*/

    /*
     * the problem is to say: implement a in-memory file system, contains
     * mkdir, ls, addContent to file, note, it actually append to file
     * 
     * the key of this problem:
     * 1. abstract a file object, directory or file
     * 2. use TreeMap to sort
     * 3. split, first will be always empty.
     */
    class File{
        boolean isD;
        String name;
        String content;
        TreeMap<String, File> children;
        
        public File(boolean isD, String name) {
            this.isD = isD;
            this.name = name;
            children = new TreeMap<>();
        }
    }
    
    File root;
    public _588DesignInMemoryFileSystem() {
        root = new File(true, "/");
    }
    
    public List<String> ls(String path) {
        //System.out.println(path);
        String[] paths = path.split("/");
        File cur = root;
        for(String name: paths) {
            if (name.length() == 0) continue;
            cur = cur.children.get(name);
        }
        
        if (cur.isD) {
            return cur.children.keySet().stream().collect(Collectors.toList());
        }
        
        return Arrays.asList(cur.name);
        
    }
    
    public void mkdir(String path) {
        String[] paths = path.split("/");
        File cur = root;
        for(String name: paths) {
            if (name.length() == 0) continue;
            if (cur.children.containsKey(name)) {
                cur = cur.children.get(name);
            } else {
                //System.out.println("create file:" + name);
                File temp = new File(true, name);
                cur.children.put(name, temp);
                cur = temp;
            }
        }
    }
    
    public void addContentToFile(String filePath, String content) {
        String[] paths = filePath.split("/");
        File cur = root;
        for(int i = 0; i<paths.length - 1; i++) {
            String name = paths[i];
            if (name.length() == 0) continue;
            cur = cur.children.get(name);
        }
        
        String fileName = paths[paths.length-1];
        if (cur.children.containsKey(fileName)) {
            cur = cur.children.get(fileName);
            cur.content = cur.content + content;
        } else {
            File temp = new File(false, fileName);
            temp.content = content;
            cur.children.put(fileName, temp);
        }
        
    }
    
    public String readContentFromFile(String filePath) {
        String[] paths = filePath.split("/");
        File cur = root;
        for(int i = 0; i<paths.length; i++) {
            String name = paths[i];
            if (name.length() == 0) continue;
            cur = cur.children.get(name);
        }
        
        return cur.content;
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */