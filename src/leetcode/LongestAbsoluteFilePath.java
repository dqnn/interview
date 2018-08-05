package leetcode;

import java.util.HashMap;
import java.util.Stack;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LongestAbsoluteFilePath
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : 388. Longest Absolute File Path
 */
public class LongestAbsoluteFilePath {
    /**
Suppose we abstract our file system by a string in the following manner:

The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:

dir
    subdir1
    subdir2
        file.ext
The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.

The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:

dir
    subdir1
        file1.ext
        subsubdir1
    subdir2
        subsubdir2
            file2.ext
The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.

We are interested in finding the longest (number of characters) absolute path to a file within our file system. For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).

Given a string representing the file system in the above format, return the length of the longest absolute path to file in the abstracted file system. If there is no file in the system, return 0.

Note:
The name of a file contains at least a . and an extension.
The name of a directory or sub-directory will not contain a ..
Time complexity required: O(n) where n is the size of the input string.

Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.
     * The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:
     * dir
        subdir1
        subdir2
            file.ext
     dir\ subdir2\ file.ext
      3     7         8    =  20

     dir \tsubdir1 \tsubdir2 \t\tfile.ext


     stack : 0 4 12 21
     dir : level = 0
     \tsubdir1 \tsubdir2 : level = 1
     \t\tfile.ext ; level = 2

     time : O(n)
     space : O(n)


     * @param input
     * @return
     */
    public int lengthLongestPath(String input) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int res = 0;
        // remove \n means we decide the level by \t,
        for (String s : input.split("\n")) {
            // so whole string is divided by \t, so how many \t means the how many
            // directries compared to dir
            // dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"
            // dir \tsubdir1 \t\tfile1.ext \t\tsubsubdir1 \tsubdir2 \t\tsubsubdir2
            // \t\t\tfile2.ext
            // you could see \t means the length compared to dir
            // and all files and directory was together for one dir, so for each string we
            // need to find the level(layer)
            // lastIndexOf \t means the length of last \t, we +1 beacause if we did not find
            // \t means dir, still want to make it 0
            // for dir, level = 0
            // for \tsubdir1 level = 1
            // for \t\tfile1.ext level = 2
            int level = s.lastIndexOf("\t") + 1;
            // we use stack to store the previous dir length
            // so if we are on the same parent directory, which means level + 1 should be
            // equals to
            // the size of the stack, or means that the current subdir all files has been
            // visited
            // we might in another directory or files, we need to pop until we get to be
            // under correct dir for current dir or files
            while (level + 1 < stack.size()) {
                stack.pop();
            }
            // get parent dir length + plus s files or dir length, we minus level because
            // question want / not \t, level already increase 1 so we don't need to - 1.
            int len = stack.peek() + s.length() - level + 1;// 1 means "/" in last final path
            // suppose we s = dir here we put 3, stack has 3 and 0;
            stack.push(len);
            // if it is file, so we may need to replace res
            if (s.contains(".")) {
                res = Math.max(res, len - 1);
            }
        }
        return res;
    }
    
    
    // HashMap solution
    /*
     * hashMap stores (level, the length of the path up to level level) pairs. By default, we use a (0,0) 
     * to initialize the hashmap. But for example "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext".
dir is in level 1, not 0. subdir1 is in level 2, so on...
we update the hashMap using hashMap.get(level) + len + 1 because the current level is level+1, 
previous level is level, we +1 because the additional path separator char \ , if s contains . , 
we update the current max length
     */
    
    public int lengthLongestPath2(String input) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(0, 0);
        int result = 0;
        for (String s : input.split("\n")) {
            int level = s.lastIndexOf('\t') + 1;
            //remove how many \t, so len is the length in absolute file path
            int len = s.length() - level;
            if (s.contains(".")) {
                result = Math.max(result, hashMap.get(level) + len);
            } else {
                hashMap.put(level + 1, hashMap.get(level) + len + 1);
            }
        }
        return result;
    }

    
    public static void main(String[] args) {
        // tab is just 1 size
        System.out.println("\t\t\t\t\tabcdedfg".lastIndexOf("\t"));
    }

}
