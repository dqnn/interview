package companies.mainchief;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DeliveryTask implements Delayed{
    
    private Order order;
    private long startTime;
    
    
    public DeliveryTask(Order order, long delayedTimeInms) {
        this.order = order;
        this.startTime = System.currentTimeMillis() + delayedTimeInms;
    }

    @Override
    public int compareTo(Delayed o) {
        long diff = this.getDelay(TimeUnit.NANOSECONDS)
                - o.getDelay(TimeUnit.NANOSECONDS);
        return Long.compare(diff, 0);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = startTime - System.currentTimeMillis();
        return unit.convert(diff, unit);
    }
    

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "DeliveryTask [" + "startTime=" + startTime + " order=" + order + "]";
    }
    

}
