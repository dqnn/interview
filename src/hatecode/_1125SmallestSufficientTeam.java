package hatecode;

import java.util.*;

public class _1125SmallestSufficientTeam {
/*
 * 1125. Smallest Sufficient Team
 * In a project, you have a list of required skills req_skills, and a list of people.  The i-th person people[i] contains a list of skills that person has.

Consider a sufficient team: a set of people such that for every required skill in req_skills, there is at least one person in the team who has that skill.  We can represent these teams by the index of each person: for example, team = [0, 1, 3] represents the people with skills people[0], people[1], and people[3].

Return any sufficient team of the smallest possible size, represented by the index of each person.

You may return the answer in any order.  It is guaranteed an answer exists.

 

Example 1:

Input: req_skills = ["java","nodejs","reactjs"], people = [["java"],["nodejs"],["nodejs","reactjs"]]
Output: [0,2]
Example 2:

Input: req_skills = ["algorithms","math","java","reactjs","csharp","aws"], people = [["algorithms","math","java"],["algorithms","math","reactjs"],["java","csharp","aws"],["reactjs","csharp"],["csharp","math"],["aws","java"]]
Output: [1,2]
 */

    public int[] smallestSufficientTeam(String[] skills, List<List<String>> people) {
        int sLen = skills.length, pLen = people.size();
        
        Map<String, Integer> skmap = new HashMap<>();
        for(int i = 0; i < sLen; i++)
            skmap.put(skills[i], i);
        
        Set<Integer>[] skillArr = new Set[1 << sLen];
        skillArr[0] = new HashSet<>();
        
        for(int ppl = 0; ppl < pLen; ppl++){
            int pplSkill = 0;
            for(String sks : people.get(ppl)){
                pplSkill |= 1 << (skmap.get(sks));
            }
            
            for(int k = 0; k < skillArr.length; k++){
                if(skillArr[k] == null) continue;
                Set<Integer> currSkills = skillArr[k];
                int combined = k | pplSkill;
                if(combined == k) continue;
                if(skillArr[combined] == null || skillArr[combined].size() > currSkills.size() + 1){
                    Set<Integer> cSkills = new HashSet<>(currSkills);
                    cSkills.add(ppl);
                    skillArr[combined] = cSkills;
                }
            }
        }
        
        Set<Integer> resSet = skillArr[(1 << sLen) - 1];
        int[] res = new int[resSet.size()];
        int pos = 0;
        for(Integer n : resSet)
            res[pos++] = n;
        
        return res;
    }
    
    
    public int[] smallestSufficientTeam_Bitmask(String[] req_skills, List<List<String>> people) {
        //1. Create hashmap to transfer skill state satisfaction info to bits and store in skillStates integer
        Map<String,Integer> skillToIndexMap = new HashMap<>();
        for(int i = 0;i<req_skills.length;i++){
            skillToIndexMap.put(req_skills[i],i);
        }
        int skillStates = (1<<req_skills.length)-1;//Initialize to 111...111, each bit corresponding to one skill
        //2. transfer people to int[] array data structure
        int lenState = 1<<req_skills.length;
        int[] peopleBinary = new int[people.size()];
        for(int i = 0;i<people.size();i++){
            int skill = 0;
            for(String s:people.get(i)){
                skill |=(1<<skillToIndexMap.get(s));
            }
            peopleBinary[i] = skill;
        }
        //3. banned people[j] if skills_i cover all skills_j
        boolean[] isBannedArr = calculateDuplicate(peopleBinary,req_skills.length);
        //4. BFS/DP to store and update teamList when meet with people[0],[1],[2]...
        
        List<Integer>[] teamList = new ArrayList[lenState];
        teamList[0] = new ArrayList<Integer>();          //Initialize state 0, which means no skill is satisfied
        for(int i = 0;i< peopleBinary.length;i++){
            if(isBannedArr[i]) continue;
        //4.1 create temp list to avoid state update twice by people[i]
            List<Integer>[] nextTeamList = new ArrayList[lenState];
            for(int j = 0;j<lenState;j++){
                if(teamList[j] != null) nextTeamList[j] = new ArrayList<>(teamList[j]);
            }
        //4.2 loop all 2^skills state previous state info
            for(int j = 0;j<lenState;j++){
                if(teamList[j] == null 
                   || teamList[lenState-1] != null && teamList[j].size() >= teamList[lenState-1].size()) continue;
                int newState = j|peopleBinary[i];
        //4.3 typical BFS, if we can reach "new state" or it cost less people to reach previous state, we update the list
                if(nextTeamList[newState] == null || teamList[j].size() + 1 < nextTeamList[newState].size()){
                    nextTeamList[newState] = new ArrayList<>(teamList[j]);
                    nextTeamList[newState].add(i);
                }
            }
            teamList = nextTeamList;
        }
        int[] ans = new int[teamList[lenState-1].size()];
        for(int i = 0;i<ans.length;i++){
            ans[i] = teamList[lenState-1].get(i);
        }
        return ans;
    }
    private boolean[] calculateDuplicate(int[] peopleBinary, int len){
        boolean[] isDuplicateArr = new boolean[peopleBinary.length];
    //compare people[i] and people[j], if skill_i > skill_j means people_i have at least one skill people_j doesn't have, and vice versa
        for(int i = 0;i<peopleBinary.length;i++){
            for(int j = i+1;j<peopleBinary.length;j++){
                if(peopleBinary[i] == peopleBinary[j]) isDuplicateArr[j] = true;
                else if(peopleBinary[i] > peopleBinary[j] && isPeopleContainsAll(peopleBinary[i],peopleBinary[j],len)){
                    isDuplicateArr[j] = true;
                }else if(peopleBinary[j] > peopleBinary[i] && isPeopleContainsAll(peopleBinary[j],peopleBinary[i],len)){
                    isDuplicateArr[i] = true;
                }
            }
        }
        return isDuplicateArr;
    }
    private boolean isPeopleContainsAll(int skill1, int skill2, int len){
        int mask = 1;
        for(int i = 0;i<len;i++){
            if( (skill1 & mask) == 0 && (skill2 & mask) > 0) return false;
            mask <<= 1;
        }
        return true;
    }
    
    
    
    List<Integer> sol = new ArrayList<>();
    public int[] smallestSufficientTeam_bestPerformance(String[] req_skills, List<List<String>> people) {
        Map<String, Integer> idx = new HashMap<>(); 
        int n = 0;
        for (String s : req_skills) idx.put(s, n++);///skills are represented by 0, 1, 2....
        int[] pe = new int[people.size()];
        for (int i = 0; i < pe.length; i++) {
            for (String p : people.get(i)) {
                int skill = idx.get(p);
                pe[i] += 1 << skill;
            }
        } // each person is transferred to a number, of which the bits of 1 means the guy has the skill
        boolean[] isBannedArr = calculateDuplicate2(pe,req_skills.length);
        search(0, pe, new ArrayList<Integer>(), n,isBannedArr);  
        int[] ans = new int[sol.size()];
        for (int i = 0; i < sol.size(); i++) ans[i] = sol.get(i);
        return ans;
    }
    
    public void search(int cur, int[] pe, List<Integer> onesol, int n,boolean[] isBannedArr) { 
        if (cur == (1<<n) - 1) {  // when all bits are 1, all skills are coverred
            if (sol.size() == 0 || onesol.size() < sol.size()) {
                sol = new ArrayList<>(onesol);
            }
            return;
        }
        if (sol.size() != 0 && onesol.size() >= sol.size()) return;    //pruning
        
        int zeroBit = 0;
        while (((cur>>zeroBit)&1) == 1) zeroBit++;   
        for (int i = 0; i < pe.length; i++) {
            if(isBannedArr[i]) continue;
            int per = pe[i];
            if (((per>>zeroBit)&1) == 1) {
                onesol.add(i); // when a person can cover a zero bit in the current number, we can add him
                search(cur|per, pe, onesol, n,isBannedArr);
                onesol.remove(onesol.size() - 1);  //search in a backtracking way
            }
        } 
    }
    private boolean[] calculateDuplicate2(int[] peopleBinary, int len){
        boolean[] isDuplicateArr = new boolean[peopleBinary.length];
    //compare people[i] and people[j], if skill_i > skill_j means people_i have at least one skill people_j doesn't have, and vice versa
        for(int i = 0;i<peopleBinary.length;i++){
            for(int j = i+1;j<peopleBinary.length;j++){
                if(peopleBinary[i] == peopleBinary[j]) isDuplicateArr[j] = true;
                else if(peopleBinary[i] > peopleBinary[j] && isPeopleContainsAll(peopleBinary[i],peopleBinary[j],len)){
                    isDuplicateArr[j] = true;
                }else if(peopleBinary[j] > peopleBinary[i] && isPeopleContainsAll(peopleBinary[j],peopleBinary[i],len)){
                    isDuplicateArr[i] = true;
                }
            }
        }
        return isDuplicateArr;
    }
    private boolean isPeopleContainsAll2(int skill1, int skill2, int len){
        int mask = 1;
        for(int i = 0;i<len;i++){
            if( (skill1 & mask) == 0 && (skill2 & mask) > 0) return false;
            mask <<= 1;
        }
        return true;
    }

}