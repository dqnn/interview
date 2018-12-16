package hatecode;

import java.util.LinkedList;
import java.util.Queue;

public class EventFireAndRegister {
/*
              E
    -------------------------->
    |       |       |       |
    cb1     cb2     cb3     cb4

    // 1. callbacks to execute when the event fires 
// 2. if event is already fired, new registration comes, run the callback immediately
 * 3. event will be only once
 */
    class CallBack {
        String name;

        public CallBack() {
        }

        public CallBack(String name) {
            this.name = name;
        }

        public void call() {
            System.out.println("CallBack Event " + this.name + "is running now");
        }
    }
    
    public class EventFire {
        Queue<CallBack> eventQueue;
        boolean isFired;
        public EventFire() {
            this.eventQueue = new LinkedList<>();
            this.isFired = false;
        }

        public void reg_cb(CallBack cb) {
                if (!isFired) {
                    eventQueue.offer(cb);
                } else {
                    cb.call(); //From 1point 3acres bbs
                }
        }

        public void fire() {
            while (!eventQueue.isEmpty()) {
                eventQueue.poll().call();
            }

            isFired = true;
        }
    }
    //follow up 1: thread safe, if we have a Lock class
    //follow up 2: keep the order of incoming callbacks, like 1 thread is executing poll in 
    // fire while another is executing reg_cb(), but we cannot add into queue
    // so we need to store the another queue as q2, if (q1.size() > 0) then we add into q2,
    //then in fire, after q1 is done, change isFired = true then we poll q2 code
     static class Lock {
        static void lock() {}
        static void unlock() {}
    }
    public class EventFireWithThreadSafe {
        Queue<CallBack> eventQueue;
        //this is to indicate isFired should sync with cpus
        volatile boolean  isFired;
        public EventFireWithThreadSafe() {
            this.eventQueue = new LinkedList<>();
            this.isFired = false;
        }

        public void reg_cb(CallBack cb) {
                if (!isFired) {
                    eventQueue.offer(cb);
                } else {
                    cb.call(); //From 1point 3acres bbs
                }
        }

        public void fire() {
            Lock.lock();
            System.out.println(" it will fire");
         // 假设此时queue只有一个元素
            while (!eventQueue.isEmpty()) {
               CallBack cb = eventQueue.poll();  // queue 已经为空
               Lock.unlock();
               cb.call(); //  假设call 过程中。register函数拿锁。往里push了一个cb 然后释放
               Lock.lock(); // fire重新获得。此时queue不为空  所以继续执行循环
            }
            isFired = true; //循环结束的时候 queue一定是空的。因为从检查queue是否为空到循环结束。fire一直有锁，register注册不了cb了,此时flag为真
            Lock.unlock(); //register函数在拿锁就直接执行cb
            System.out.println(" it has fired already");
         }
    }
}
