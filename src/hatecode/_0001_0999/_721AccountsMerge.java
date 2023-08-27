package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class _721AccountsMerge {
/*
 * 721 Account Merge
Given a list accounts, each element accounts[i] is a list of strings, where the first element 
accounts[i][0] is a name, and the rest of the elements are emails representing emails of 
the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same 
person if there is some email that is common to both accounts. Note that even if two 
accounts have the same name, they may belong to different people as people could have 
the same name. A person can have any number of accounts initially, but all of their 
accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first 
element of each account is the name, and the rest of the elements are emails in sorted order. 
The accounts themselves can be returned in any order.

Example 1:
Input: 
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
Explanation: 
The first and third John's are the same person as they have the common email 
"johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are 
used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], 
['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still 
be accepted.
 */
    //Time Complexity: O(AlogA), where A = SUM(ai)  Space: O(A)
    /*
     * the problem is to say: 
     * give list of name with email, one person may have multiple emails, we need to merge them into one 
     * 
     * essentially we need to connect emais together to simplify the questions. union find is the greatest tool
     *  we first need to create id for each email and union them, tricks in this problem.
     * 
     * 1. we always union with first email, if there is one email, we union it with itself.  it simplifies code 
     * 2. we create id for each email, so later we can find them easily
     * 3. when we composite the result, we only have root, so we need to have a id mapping to name, but since we already have 
     * email to name, so we can just get first element out and email2Name to get its name.
     * 
     * 
     * overall, the thinking process is not simple, need to pratice more
     */
    
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        if (accounts == null || accounts.size() < 1) return new ArrayList<>();
        
        Unionhelper dsu = new Unionhelper();
        //email --> name mapping
        Map<String, String> emailToName = new HashMap<>();
        //email mapping to ID, id is from 0, 
        Map<String, Integer> emailToID = new HashMap<>();
        int id = 0;
        
        
        
        
        for (List<String> account: accounts) {
            String name = "";
            //["John", "johnsmith@mail.com", "john00@mail.com"],
            //so 
            
            for (String email: account) {
                if (name == "") {
                    name = email;
                    //we ignore the name since name was always first
                    continue;
                }
                //here will put John=John, johnsmith@mail.com = John, 
                //and john00@mail.com= John
                emailToName.put(email, name);
                //johnsmith@mail.com =0, john00@mail.com = 1;
                if (!emailToID.containsKey(email)) {
                    emailToID.put(email, id++);
                }
                //System.out.println("email2ID: " + emailToID);
                //System.out.println("email2Name: " +emailToName);
                //fist email address and each eachId
                //this is johnsmith@mail.com-->1 and latter = 0,1,2
                //we want to mark johnsmith@mail.com --> is each parent
                dsu.union(emailToID.get(account.get(1)), emailToID.get(email));
            }
        }

        Map<Integer, List<String>> res = new HashMap<>();
        for (String email: emailToName.keySet()) {
            int index = dsu.find(emailToID.get(email));
            res.computeIfAbsent(index, x-> new ArrayList<>()).add(email);
        }
        for (List<String> component: res.values()) {
            Collections.sort(component);
            component.add(0, emailToName.get(component.get(0)));
        }
        return new ArrayList<>(res.values());
    }
class Unionhelper {
    int[] parent;
    public Unionhelper() {
        parent = new int[101];
        for (int i = 0; i <= 100; ++i)
            parent[i] = i;
    }
    //to find parent ,oringinally not changed 
    public int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }
    //first we set itself for each email,parent[0] = 0, parent[0] = 1;
    //to set parent(x) = y, for ["John", "johnsmith@mail.com", "john00@mail.com"]
    //we can see paret[0] , 0, 
    //later, when we visit ["John", "johnsmith@mail.com", "john_newyork@mail.com"]
    //we will union(0, 0) again, but parent[0] = 1. so we need find parent[1]
    //then parent[1] = 1 so it will be parent[1] = find(2)=2, so we can see the parent chain
    //0->1->2, here means y's parent is x
    public void union(int x, int y) {
        parent[find(x)] = find(y);
    }
 }

/*
 * we visit A twice to simplify the code when we union the array
 */
 public List<List<String>> accountsMerge_Simple(List<List<String>> A) {
        if (A == null || A.size() < 1) return new ArrayList<>();
        
        
        Map<String, Integer> email2Id = new HashMap<>(); 
        Map<String, String> email2Name = new HashMap<>(); 
        int sum = 0;
        int id = 0; 
        for(List<String> list : A) {
            sum += list.size() - 1;
            String name = list.get(0);
            for(int i = 1;i< list.size(); i++) {
                String email = list.get(i);
                if(!email2Id.containsKey(email)) {
                    email2Id.put(email, id);
                    email2Name.put(email, name);
                }
                id++;
            }
        }
        DSU dsu = new DSU(sum);
       
        for(List<String> list: A) {
            String name = list.get(0);
            for(int i = 1; i< list.size() ; i++) {
                String email = list.get(i);
                int root = dsu.find(email2Id.get(email));
                dsu.union(email2Id.get(email), email2Id.get(list.get(1)));
            }
        }
        
        Map<Integer, TreeSet<String>> map = new HashMap<>();
        for(List<String> list: A) {
            String name = list.get(0);
            for(int i = 1; i< list.size() ; i++) {
                String email = list.get(i);
                int root = dsu.find(email2Id.get(email));
                map.computeIfAbsent(root, v->new TreeSet<>()).add(email);
            }
        }
        
        List<List<String>> res = new ArrayList<>();
        for(var entry : map.entrySet()) {
            List<String> tmp = new ArrayList<>();
            tmp.addAll(entry.getValue());
            tmp.add(0, email2Name.get(entry.getValue().first()));
            res.add(tmp);
        }
        
        return res;
        
        
    }

  public static void main(String[] args) {
      List<String>[] temp =new List[4];
      temp[0] = new ArrayList<>(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
      temp[1] = new ArrayList<>(Arrays.asList("John", "johnnybravo@mail.com"));
      temp[2] = new ArrayList<>(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
      temp[3] = new ArrayList<>(Arrays.asList("Mary", "mary@mail.com"));
      List<List<String>> input = new ArrayList<>(Arrays.asList(temp));
      _721AccountsMerge t= new _721AccountsMerge();
      t.accountsMerge(input);
      
  }
}