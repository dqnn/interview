package hatecode._0001_0999;
public class _725SplitLinkedListInParts {
/*
725. Split Linked List in Parts
Given a (singly) linked list with head node root, write a function to split the linked list into k consecutive linked list "parts".

The length of each part should be as equal as possible: no two parts should have a size differing by more than 1. This may lead to some parts being null.

The parts should be in order of occurrence in the input list, and parts occurring earlier should always have a size greater than or equal parts occurring later.

Return a List of ListNode's representing the linked list parts that are formed.

Examples 1->2->3->4, k = 5 // 5 equal parts [ [1], [2], [3], [4], null ]
Example 1:
Input: 
root = [1, 2, 3], k = 5
Output: [[1],[2],[3],[],[]]
*/
    //thinking process, split one linked list into k parts, each part diff less than 1 node
    //[1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
    //for this example, first we can get len = 10, n = 3, r = 1,so will be 4,3,3
    //we have node point to 1 and pre = null, then we start loop,
    //this is similiar to Text Justification
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] res = new ListNode[k];
        int len = 0;
        for(ListNode node = root; node!=null;node=node.next) len++;
        
        int n = len / k, r = len % k;
        ListNode node = root, prev = null;
        for(int i = 0; node!=null && i < k; i++, r--) {
            res[i] = node;
            for(int j = 0; j < n + (r>0 ? 1:0); j++) {
                prev = node;
                node =node.next;
            }
            prev.next = null;
        }
        return res;
    }
}