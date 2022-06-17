package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Project Name : Leetcode
 * Package ˇName : leetcode
 * File Name : SimplifyPath
 * Creator : professorX
 * Date : Sep, 2018
 * Description : 71. Simplify Path
 */
public class SimplifyPath {
    /**
     * tags: facebook
     * Given an absolute path for a file (Unix-style), simplify it.

     For example,
     path = "/home/", => "/home"
     path = "/a/./b/../../c/", => "/c"

     time : O(n)
     space :O(n)


     * @param path
     * @return
     */
    //interview friendly
    //thinking process: we use a stack to calculate the "real path", such 
    //like "././" will ignore ., also for ".././../" in stack,
    //we will pop and calculate the correct last path
    
    //there are 1 edge case
    //1 in /, we still /../../../.. actually we will stop at / 
    //2 stack has nothing which means the input is just / or .. to top
    
    //so we parse the text into a stack
    //and we form the correct path from stack
    
    //The key is the result of the stack is only part of the string, 
    //why we think about stack because it has .. which will erase some characters
    public String simplifyPath(String path) {
        if (path == null || path.length() < 1) {
            return "";
        }
        Stack<String> stack = new Stack<>();
        //here is the key, we may have dup / but it is able to process this case
        String[] paths = path.split("/+");
        for (String s : paths) {
            if (s.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!s.equals(".") && !s.equals("")) {
                stack.push(s);
            }
        }
        //output from back to front
        String res = "";
        while (!stack.isEmpty()) {
            res = "/" + stack.pop() + res;
        }
        //note, here is one edge case 
        if (res.length() == 0) {
            return "/";
        }
        return res;
    }
    //another stack solutions
    public String simplifyPath2(String path) {
        Stack<String> stack = new Stack<>();
        String[] p = path.split("/");
        for (int i = 0; i < p.length; i++) {
            if (!stack.empty() && p[i].equals(".."))
                stack.pop();
            else if (!p[i].equals(".") && !p[i].equals("") && !p[i].equals(".."))
                stack.push(p[i]);
        }
        List<String> list = new ArrayList(stack);
        return "/"+String.join("/", list);
    }
}
