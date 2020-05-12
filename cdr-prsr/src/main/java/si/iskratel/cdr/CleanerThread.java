package si.iskratel.cdr;

import java.util.Iterator;
import java.util.Map;

public class CleanerThread extends Thread {

    public void run() {

        while (true) {

            // clean subscribers that are not in the call anymore
            long now = System.currentTimeMillis();
            Iterator it = Test.callsInProgress.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Long> pair = (Map.Entry) it.next();
                if (pair.getValue() < now) {
                    System.out.println("removing: " + pair.getKey());
                    it.remove(); // avoids a ConcurrentModificationException
                }
            }

            try {
                sleep(2000);
            } catch (InterruptedException e) {
            }

        }

    }

}
