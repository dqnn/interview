package companies.mainchief;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class ShelfManager implements StatusReport{
    
    private ShelfLinkedBlockingQueue<Order> hotShelf;
    private ShelfLinkedBlockingQueue<Order> codeShelf;
    private ShelfLinkedBlockingQueue<Order> frozenShelf;
    private ShelfLinkedBlockingQueue<Order> overFlowShelf;
    Logger log = Logger.getLogger(ShelfManager.class.getName());
    
    private int OVERFLOWSHELF_MAX_CAPACITY;
    private AtomicInteger remove_Count = new AtomicInteger();

    public ShelfManager(
                        int hotSlefSize, 
                        int codeShelfSize,
                        int fronzeShelfSize, 
                        int overFlowShelfSize ) {
        hotShelf = new ShelfLinkedBlockingQueue<>(1, hotSlefSize);
        codeShelf = new ShelfLinkedBlockingQueue<>(1, codeShelfSize);
        frozenShelf = new ShelfLinkedBlockingQueue<>(1, fronzeShelfSize);
        overFlowShelf = new ShelfLinkedBlockingQueue<>(2, overFlowShelfSize);
        OVERFLOWSHELF_MAX_CAPACITY = overFlowShelfSize;
    }
    
    public void put(List<Order> list) {
        list.forEach(order -> put(order));
    }
    
    public boolean put(Order order) {
        
        boolean res = false;
        ShelfLinkedBlockingQueue<Order> shelfToPut = getQueueHandler(order);
        
        if (shelfToPut.offer(order)) {
            log.info(String.format("at timestamp %s, successfully put order %s to queue %s", System.currentTimeMillis(), order.toString(), shelfToPut.toString()));
            res = true;
        } else {
            //put to overflow shelf
            shelfToPut = overFlowShelf;
            if (shelfToPut.offer(order)) {
                log.info(String.format("at timestamp %s, 1st successfully try to put order %s to overflow queue %s", System.currentTimeMillis(),order.toString(), shelfToPut.toString()));
                res = true;
             //overflowShelf is full
            } else {
                try {
                    removeOneEleFromOverFlowShelfByRandom();
                } catch(Exception e) {
                    log.info("got exception when removing elements");
                }
                
                if (shelfToPut.offer(order)) {
                    log.info(String.format("at timestamp %s, after removeOneElementByRandom, successfully try to put order %s to queue %s", System.currentTimeMillis(), order.toString(), shelfToPut.toString()));
                    res = true;
                } else {
                    log.info(String.format("failed to fulfil order %s after removeOneElementByRandom", order.toString()));
                }
            }
            
        }
        
        return res;
    }
    
    private void removeOneEleFromOverFlowShelfByRandom() {
        log.info("overflow shelf is full, remove one random element");
        Random rnd =new Random();
        int next = rnd.nextInt(OVERFLOWSHELF_MAX_CAPACITY);
        Iterator<Order> it = overFlowShelf.iterator();
        int index = 1;
        while (index < next) {
            index++;
            it.next();
        }
        it.remove();
        remove_Count.incrementAndGet();
    }
    
    private ShelfLinkedBlockingQueue<Order> getQueueHandler(Order order) {
        ShelfLinkedBlockingQueue<Order> shelfToPut = null;
        switch(order.temp.toLowerCase()) {
        case "hot":
            shelfToPut = hotShelf;
            break;
        case "cold":
            shelfToPut = codeShelf;
            break;
        case "frozen":
            shelfToPut = frozenShelf;
            break;
        default:
        }
        
        return shelfToPut;
    }
    
    public boolean removeOrderFromShelf(Order order) {
        if (this.overFlowShelf.contains(order)) {
            this.overFlowShelf.remove(order);
            return true;
        } else if (this.getQueueHandler(order).contains(order)) {
            this.getQueueHandler(order).remove(order);
            return true;
        }
        return false;
    }

    @Override
    public String report() {
        return String.format("ShelfManager [remove_Count=%d, hotShelf=%s, codeShelf=%s, frozenShelf=%s, overFlowShelf=%s]", 
                remove_Count.get(), hotShelf.toString(), codeShelf.toString(),frozenShelf.toString(),overFlowShelf.toString());
    }
}
