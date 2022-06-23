package companies.mainchief;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class DeliveryManager implements Runnable, StatusReport{

    private DelayQueue<DeliveryTask> delayQueue;
    private volatile boolean isReady = true;
    private ShelfManager shelfManager;
    Logger log = Logger.getLogger(DeliveryManager.class.getName());
    
    private AtomicInteger deliveredTaskCount = new AtomicInteger();
    private AtomicInteger expiredTaskCount = new AtomicInteger();
    private AtomicInteger failedTaskCount = new AtomicInteger();
    
    public DeliveryManager(ShelfManager shelfManager) {
        this.delayQueue = new DelayQueue<>();
        this.shelfManager = shelfManager;
    }
    
    private void process() {
        while(isReady) {
            DeliveryTask task = null;
            
            try {
                Thread.sleep(100);
                if (delayQueue.size() == 0) continue;
                task = delayQueue.take();
            }catch (Exception e) {
                log.info("get exception when get delivery task");
            }
            if (task != null) {
                Order order =task.getOrder();
                if (order.calcOrderValue() <= 0) {
                    log.info(String.format("at timestamp %s, found order value become 0, detail: %s", System.currentTimeMillis(),order));
                    expiredTaskCount.incrementAndGet();
                    continue;
                }
                if (shelfManager.removeOrderFromShelf(order)) {
                    deliveredTaskCount.getAndIncrement();
                    log.info(String.format("at timestamp %s, delivered order: %s", System.currentTimeMillis(),order));
                } else {
                    log.info(String.format("cannot find order  %s on shelf", order));
                    failedTaskCount.getAndIncrement();
                }
            } else {
                log.info("task is null");
            }
        }
        log.info("DeliveryManager is exiting!!!");
    }
    
    public void put(Order order) {
        DeliveryTask task = buildDelayedTaskFromOrder(order);
        delayQueue.put(task);
        log.info(String.format("successfully put a deliveryTask=%s", task));
    }
    
    private DeliveryTask buildDelayedTaskFromOrder(Order order) {
        return new DeliveryTask(order, 1000* (2 + new Random().nextInt(4)));
    }
    
    public void shutdown() {
        isReady = false;
    }

    @Override
    public void run() {
        process();
    }
    
    public static void main(String[] args) {
        System.out.println(2 + new Random().nextInt(4));
    }

    @Override
    public String report() {
        return String.format("DeliveryManager [queueSize: %d, deliveredTaskCount: %d, expiredTaskCount: %d, failedTaskCount: %d]", 
                delayQueue.size(), deliveredTaskCount.get(), expiredTaskCount.get(), failedTaskCount.get());
    }
    
}
