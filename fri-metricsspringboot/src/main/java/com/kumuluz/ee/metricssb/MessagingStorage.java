package com.kumuluz.ee.metricssb;

import com.kumuluz.ee.metricssb.exceptions.StoreFullException;
import com.kumuluz.ee.metricssb.models.Message;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tags;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Storitev za shranjevanje in branje sporocil (v spominu).
 *
 * @author UL FRI
 */
@Service
@ApplicationScope
public class MessagingStorage {

    // stevci, ki bodo izpostavljeni
    // koliko je uspesno shranjenih sporocil
    private Counter successfulStores = Metrics.counter("successfulStores");
    // koliko je neuspesno shranjenih sporocil (polna vrsta)
    private Counter failedStores = Metrics.counter("failedStores");
    // koliko je uspesnih branj
    private Counter successfulRetrieves = Metrics.counter("successfulRetrieves");
    // koliko je "praznih" branj (prazna vrsta)
    private Counter emptyRetrieves = Metrics.counter("emptyRetrieves");

    // velikost vrste
    private static final int MAX_MESSAGES = 5;

    // metrika velikosti vrste
    private Queue<Message> store = Metrics.gaugeCollectionSize("queueSize", Tags.empty(), new LinkedList<>());

    /**
     * Shrani sporocilo v vrsto.
     */
    public synchronized void storeMessage(Message msg) throws StoreFullException {
        if (store.size() >= MAX_MESSAGES) {
            // vrsta je prepolna, povecamo steven neuspesnih shranjevanj
            failedStores.increment();
            throw new StoreFullException();
        }

        store.add(msg);
        // shranjevanje uspesno, povecamo stevec uspesnih shranjevanj
        successfulStores.increment();
    }

    /**
     * Prebere sporocilo iz vrste.
     */
    public synchronized Message retrieveMessage() {
        Message msg = store.poll();

        if (msg == null) {
            // vrsta je prazna, povecamo stevec "praznih" branj
            emptyRetrieves.increment();
        } else {
            // branje je uspesno, povecamo stevec uspesnih branj
            successfulRetrieves.increment();
        }

        return msg;
    }
}
