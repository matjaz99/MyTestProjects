package com.kumuluz.ee.metricssb.controllers;

import com.kumuluz.ee.metricssb.MessagingStorage;
import com.kumuluz.ee.metricssb.exceptions.StoreFullException;
import com.kumuluz.ee.metricssb.models.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

/**
 * Kontroler REST, ki izpostavlja funkcionalnosti aplikacije preko vmesnika HTTP.
 *
 * @author UL FRI
 */
@RestController
@RequestMapping("msg")
public class MessagingController {

    private final MessagingStorage storage;

    public MessagingController(MessagingStorage storage) {
        this.storage = storage;
    }

    /**
     * Prebere sporocilo z vrste. Ce je vrsta prazna, vrne 204 No Content.
     */
    @GetMapping
    public ResponseEntity<Message> retrieveMessage() {
        Message msg = storage.retrieveMessage();

        if (msg == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        msg.setDateRetrieved(Instant.now());

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    /**
     * Ustvari novo sporocilo (prejeto v telesu zahtevka). Ce je vrsta polna, vrne 500 Server Error.
     */
    @PostMapping
    public ResponseEntity<String> createMessage(@RequestBody Message msg) {
        msg.setDateCreated(Instant.now());
        msg.setDateRetrieved(null);

        try {
            storage.storeMessage(msg);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (StoreFullException e) {
            return new ResponseEntity<>("Store is full", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
