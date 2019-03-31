package com.kumuluz.ee.metrics.exporter;

import com.kumuluz.ee.metrics.exporter.exceptions.StoreFullException;
import com.kumuluz.ee.metrics.exporter.models.Message;
import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Metric;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.LinkedList;
import java.util.Queue;

@ApplicationScoped
public class MessagingStorage {

    private static final int MAX_MESSAGES = 5;

    private Queue<Message> store = new LinkedList<>();

    @Inject
    @Metric(absolute = true, description = "Number of successful stores.")
    private Counter successfulStores;

    @Inject
    @Metric(absolute = true, description = "Number of failed stores.")
    private Counter failedStores;

    @Inject
    @Metric(absolute = true, description = "Number of successful retrieves.")
    private Counter successfulRetrieves;

    @Inject
    @Metric(absolute = true, description = "Number of empty retrieves (store was empty).")
    private Counter emptyRetrieves;

    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        queueSize();
    }

    public synchronized void storeMessage(Message msg) throws StoreFullException {
        if (store.size() >= MAX_MESSAGES) {
            failedStores.inc();
            throw new StoreFullException();
        }

        store.add(msg);
        successfulStores.inc();
        queueSize();
    }

    @Counted(absolute = true,
            name = "totalRetrieves",
            description = "Number of retrieves.", monotonic = true)
    public synchronized Message retrieveMessage() {
        Message msg = store.poll();
        queueSize();

        if (msg == null) {
            emptyRetrieves.inc();
        } else {
            successfulRetrieves.inc();
        }

        return msg;
    }

    @Gauge(absolute = true,
            description = "Number of all objects currently in queue.",
            unit = MetricUnits.NONE)
    private int queueSize() {
        return store.size();
    }
}
