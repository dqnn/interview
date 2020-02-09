package hatecode._0001_0999;

import java.util.*;
import java.util.stream.*;
public class EmployeeImportance {
/*
690. Employee Importance
You are given a data structure of employee information, which includes the employee's unique id, his importance value and his direct subordinates' id.

For example, employee 1 is the leader of employee 2, and employee 2 is the leader of employee 3. They have importance value 15, 10 and 5, respectively. Then employee 1 has a data structure like [1, 15, [2]], and employee 2 has [2, 10, [3]], and employee 3 has [3, 5, []]. Note that although employee 3 is also a subordinate of employee 1, the relationship is not direct.

Now given the employee information of a company, and an employee id, you need to return the total importance value of this employee and all his subordinates.

Example 1:

Input: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
Output: 11
*/
    class Employee {
        int id;
        int importance;
        List<Integer> subordinates;
    }
    public int getImportance(List<Employee> employees, int id) {
        if (employees == null || employees.size() < 1) return 0;
        
        Map<Integer, Employee> map = employees.stream().collect(Collectors.toMap(e->e.id, e->e));
        
        //Map<Integer, Employee> map = new HashMap<>();
        //for(Employee e: employees) map.put(e.id, e);
        
        int res = 0;
        Queue<Employee> q = new LinkedList<>();
        q.offer(map.get(id));
        while(!q.isEmpty()) {
            Employee e = q.poll();
            res += e.importance;
            for(int d : e.subordinates) {
                q.offer(map.get(d));
            }
        }
        
        return res;
    }
}