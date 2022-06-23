package companies.mainchief;

import java.io.FileReader;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class IngestingJsonAndOrderproducer implements StatusReport, Runnable {
    
    private volatile boolean isReady = true;
    private ShelfManager shelfManager;
    private DeliveryManager deliveryManager;
    Logger log = Logger.getLogger(IngestingJsonAndOrderproducer.class.getName());
    
    private AtomicInteger IngestSuccess_OrderCount = new AtomicInteger();
    private AtomicInteger failed_putOrders = new AtomicInteger();;

    public IngestingJsonAndOrderproducer(ShelfManager shelfManager, DeliveryManager deliveryManager) {
        this.shelfManager = shelfManager;
        this.deliveryManager = deliveryManager;
    }
    
    @Override
    public void run() {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("C:\\Users\\dendu\\workspace2\\from-gits\\src\\companies\\mainchief\\orders.json"))
        {
            Object o = parser.parse(reader);
            JSONArray orders = (JSONArray)o;
            log.info("order size = " + orders.size());;
            int index = 0;
            while(isReady && index < orders.size()) {
                int batch = Config.INGESTION_ORDER_RATE;
                while(index < orders.size() && batch > 0) {
                    Order order = Order.buildOrderFromJson((JSONObject)orders.get(index));
                    if (shelfManager.put(order)) {
                        IngestSuccess_OrderCount.getAndIncrement();
                        deliveryManager.put(order);
                    } else {
                        failed_putOrders.getAndIncrement();
                    }
                    index++;
                    batch--;
                }
                Thread.sleep(2000);
            }
            log.info("order index = " + index);;
            log.info("IngestingJsonAndOrderproducer is exiting!!!");
            
        } catch(Exception e) {
            e.printStackTrace();
            log.info(e.toString());
        }
    }
    
    public void shutdown() {
        isReady = false;
    }

    @Override
    public String report() {
        return String.format("IngestingJsonAndOrderproducer [IngestSuccess_OrderCount:%d, failed_putOrders:%d]", IngestSuccess_OrderCount.get(), failed_putOrders.get());
    }
}
