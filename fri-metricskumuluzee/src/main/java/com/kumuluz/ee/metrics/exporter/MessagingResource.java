package com.kumuluz.ee.metrics.exporter;

import com.kumuluz.ee.metrics.exporter.exceptions.StoreFullException;
import com.kumuluz.ee.metrics.exporter.models.Message;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;

@Path("msg")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessagingResource {

    @Inject
    private MessagingStorage storage;

    @GET
    public Response retrieveMessage() {
        Message msg = storage.retrieveMessage();

        if (msg == null) {
            return Response.noContent().build();
        }

        msg.setDateRetrieved(Instant.now());

        return Response.ok(msg).build();
    }

    @POST
    public Response createMessage(Message msg) {
        msg.setDateCreated(Instant.now());
        msg.setDateRetrieved(null);

        try {
            storage.storeMessage(msg);
            return Response.noContent().build();
        } catch (StoreFullException e) {
            return Response.status(500).entity("Store is full").build();
        }
    }
}
