package hatecode;
import java.util.*;

/*
 * Goole interview question, 
 * Problem statement:
 * 有一个Class叫Logger，它有两个函数，一个是LogStart(int logId, int timestamp)，
 * 一个是LogFinish(int logId, int timestamp)。Log开始时LogStart会被调用，
 * log结束时LogFinish会被调用。要求是实现这两个函数，并打印已经结束的log，打印log时要按log的开始时间排序。


interface Logger { 
  void started(long timestamp, String requestId); 
  void finished(long timestamp, String requestId); 
  void print();
} 

started(100, "1")
started(101, "2")   
finished(102, "2")
started(103, "3")
finished(104, "1")
finished(105, "3")
print()

Expected Output: 
$1 start at 100 end at 104
$2 start at 101 end at 102
$3 start at 103 end at 105

 */

interface Logger {
    public void started(String id, long time);
    public void finished(String id, long time);
}

public class MyLogger implements Logger {
    private class Node {
        String id;
        long   start;
        long   end;
        Node   prev;
        Node   next;

        public Node(String id, long start) {
            this.id = id;
            this.start = start;
            end = -1;
        }
    }

    Node              head, tail;
    Map<String, Node> map;

    public MyLogger() {
        head = new Node("", -1);
        tail = new Node("", -1);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
    }

    @Override
    public void started(String id, long time) {
        Node curr = new Node(id, time);
        map.put(id, curr);
        add(curr);
    }

    @Override
    public void finished(String id, long time) {
        Node curr = map.get(id);
        if (curr != null && curr.end == -1) {
            curr.end = time;
            map.remove(id);
        }
    }

    public void print() {
        Node curr = head.next;
        while(curr != tail) {
            if(curr.end != -1) {
                System.out.println(curr.id + "start at" +curr.start + "end at " + curr.end);
            }
            curr = curr.next;
    }
}

    private void add(Node curr) {
        if (curr == null) return;
        curr.next = tail;
        curr.prev = tail.prev;
        tail.prev.next = curr;
        tail.prev = curr;
    }
}
