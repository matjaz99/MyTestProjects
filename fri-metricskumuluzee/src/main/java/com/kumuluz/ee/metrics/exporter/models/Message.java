package com.kumuluz.ee.metrics.exporter.models;

import java.time.Instant;

public class Message {

    private Instant dateCreated;
    private Instant dateRetrieved;
    private String msg;

    public Instant getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Instant getDateRetrieved() {
        return dateRetrieved;
    }

    public void setDateRetrieved(Instant dateRetrieved) {
        this.dateRetrieved = dateRetrieved;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
