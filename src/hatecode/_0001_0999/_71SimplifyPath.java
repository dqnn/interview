package hatecode._0001_0999;

import java.util.*;

public class _71SimplifyPath {
/*
71. Simplify Path
Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file system, convert it to the simplified canonical path.

In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the directory up a level, and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'. For this problem, any other format of periods such as '...' are treated as file/directory names.

The canonical path should have the following format:

The path starts with a single slash '/'.
Any two directories are separated by a single slash '/'.
The path does not end with a trailing '/'.
The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..')
Return the simplified canonical path.

 

Example 1:

Input: path = "/home/"
Output: "/home"
*/
    
    //thinking process: O(n)/O(n)
    //given one string, which may contains . .. etc, .. means to 
    //previous directory
    //return the last absolute path.
    public String simplifyPath(String s) {
        Stack<String> stack = new Stack<>();
        
        
        String[] strs = s.split("/");
        
        for(String str: strs) {
            str = str.trim();
            if (str.isEmpty() || str.equals(".")) continue;
            
            if (str.equals("..")) {
                if (!stack.isEmpty()) stack.pop();
                continue;
            }
            
            stack.push(str);
        }
        //System.out.println(stack);
        
        if (stack.isEmpty()) return "/";
        
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.insert(0, stack.pop()).insert(0, "/");
        }
        
        return sb.toString();
    }
}