package si.iskratel.cdr;

import java.util.Iterator;
import java.util.Map;

public class CleanerThread extends Thread {

    public void run() {

        while (true) {

            // clean subscribers that are not in the call anymore
            long now = System.currentTimeMillis();
            int removedCount = 0;
            try {
                Iterator it = Test.callsInProgress.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, Long> pair = (Map.Entry) it.next();
                    if (pair.getValue() < now) {
                        removedCount++;
                        it.remove(); // avoids a ConcurrentModificationException
                    }
                }
                System.out.println("removed=" + removedCount + ", callsInProgress.size=" + Test.callsInProgress.size());
            } catch (Exception e) {
                System.err.println("Error cleaning table callsInProgress");
            }



            try {
                sleep(2000);
            } catch (InterruptedException e) {
            }

        }

    }

}
