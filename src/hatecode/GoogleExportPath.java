package hatecode;

public class GoogleExportPath {
    /*
     * From google doc, https://docs.google.com/document/d/
     * 1qxA2wps0IhVRWULulQ55W4SGPMu2AE5MkBB37h8Dr58/edit# Problem statement:
     * 给一个路径列表，找出每个路径在列表里的parent路径。例子：exports = [“/a/b/c”, “/abc/foo”, “/a”, “/abc”,
     * “/a/b”, “/foo/abc”]，return [[“/a/b”, “/a”], [“/abc”], [], [], [“/a”], []]
     * 
     * 思路：建立trie，在node里有map of children和boolean
     * isValid。然后遍历exports，到最后一个node把isValid设为true。再遍历exports，
     * 如果碰到isValid为true的parent node就把当前string加入结果。时间复杂度O(m*n)，m为路径个数，
     * n为每个路径里“/”的个数。
     * 
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
