package companies.mainchief;

import java.util.List;
import java.util.logging.Logger;

public class StatusManager implements Runnable {
    
    private volatile boolean  isReady = true;
    private List<StatusReport> list;
    Logger log = Logger.getLogger(StatusManager.class.getName());

    public StatusManager(List<StatusReport> reportList) {
        this.list = reportList;
    }

    @Override
    public void run() {
        StringBuilder sb= new StringBuilder();
        while(isReady) {
            for(StatusReport sr: list) {
                sb.append(sr.report() + "---");
            }
            log.info(sb.toString());
            sb.setLength(0);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.info("StatusManager thread interupted while sleep");
            }
        }
        log.info("exiting StatusManager");
    }
    
    public void shutdown() {
        isReady = false;
    }

}
