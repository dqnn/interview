package hatecode._0001_0999;
import java.util.*;

public class _444SequenceReconstruction {
    /*tag: indegree, graph, topsort
     * 444. Sequence Reconstruction, awesome question
     * Check whether the original sequence org can be uniquely reconstructed from
     * the sequences in seqs. The org sequence is a permutation of the integers from
     * 1 to n, with 1 ≤ n ≤ 104. Reconstruction means building a shortest common
     * supersequence of the sequences in seqs (i.e., a shortest sequence so that all
     * sequences in seqs are subsequences of it). Determine whether there is only
     * one sequence that can be reconstructed from seqs and it is the org sequence.
     * 
     * Example 1:
     * 
     * Input: org: [1,2,3], seqs: [[1,2],[1,3]]
     * tt
     * Output: false
     * 
     * Explanation: [1,2,3] is not the only one sequence that can be reconstructed,
     * because [1,3,2] is also a valid sequence that can be reconstructed.
     */
    public static boolean sequenceReconstruction2(int[] org, List<List<Integer>> seqs) {
        int idx[] = new int[org.length + 1], pairs = org.length - 1;
        for (int i = 0; i < org.length; i++)
            idx[org[i]] = i;
        boolean seen[] = new boolean[org.length + 1], isEmpty = true;
        for (List<Integer> seq : seqs)
            for (int i = 0; i < seq.size(); i++) {
                isEmpty = false;
                int curr = seq.get(i);
                if (curr < 1 || curr > org.length)
                    return false;
                if (i == 0) continue;
                int prev = seq.get(i - 1);
                if (idx[prev] + 1 == idx[curr]) {
                    if (!seen[prev]) {
                        seen[prev] = true;
                        pairs--;
                    }
                } else if (idx[prev] >= idx[curr])
                    return false;
            }
        return pairs == 0 && !isEmpty;
    }
    //most voted solution, top sort
    
    //interview friendly solution, 
    //thinking process:
    //the problem is to say given one origin sequence, and list of sub sequence, we would like to 
    //know whether we can conclude the the origin sequence from sub sequence, here "conclude" means
    //the digit relative order is the same in origin seq and each sub sequence
    //for example, org: [1,2,3], seqs: [[1,2],[1,3]]
    //we cannot infer seqs->org, because 1,3,2 can also have same pattern to seqs

    //this use map to store the direction, Map<Integer, Set<Integer>> to store the mapping
    //indegree to store the indegree
    // the problem is to say: given a org sequence and list of seqs, 
    //to judge whether org is the only one from seqs which can reconstruct. 
    //org is the supersequence from seqs, which means each sequence in seqs is the 
    //sub sequence in org, so we need to see whether seqs will be able to construct the only 
    //one, 
    //O(nm)
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        
        for(List<Integer> seq: seqs) {
            if(seq.size() == 1) {
                // this is to avoid case  seqs = [[1,2,3,4,5],[1,2,3,4],[1,2,3],[1],[4],[5]]
                //if we do not have this check, latter [1], [4], [5] indegree will be override
                if(!map.containsKey(seq.get(0))) {
                    map.put(seq.get(0), new HashSet<>());
                    indegree.put(seq.get(0), 0);
                }
            } else {
                //add to the map  for every two points, 
                for(int i = 0; i < seq.size() - 1; i++) {
                    if(!map.containsKey(seq.get(i))) {
                        map.put(seq.get(i), new HashSet<>());
                        indegree.put(seq.get(i), 0);
                    }

                    if(!map.containsKey(seq.get(i + 1))) {
                        map.put(seq.get(i + 1), new HashSet<>());
                        indegree.put(seq.get(i + 1), 0);
                    }
                    // if we can add the next integer to the set, which means
                    //next point will have one more indegree, if we already have the path, then we 
                    //won't add twice
                    if(map.get(seq.get(i)).add(seq.get(i+1))) {
                        indegree.put(seq.get(i+1), indegree.get(seq.get(i+1)) + 1);
                    }
                }
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(var entry: indegree.entrySet()) {
            //add all first starters into queue
            if(entry.getValue() == 0) queue.offer(entry.getKey());
        }

        int index = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            //if we can construct multiple super sequence then it means not the only one
            //so return false
            if(size > 1) return false;

            int curr = queue.poll();
            /*
             * [1,2,3]   [[1,2],[1,3],[2,3]]
             * 
             */
            if(index == org.length || curr != org[index++]) return false;
            for(int next: map.get(curr)) {
                indegree.put(next, indegree.get(next) - 1);
                if(indegree.get(next) == 0) queue.offer(next);
            }
        }
        return index == org.length && index == map.size(); //check cycle
    }
}