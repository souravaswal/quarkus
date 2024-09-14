package org.acme.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.acme.messaging.KafkaConsumer;
import org.acme.messaging.KafkaProducer;

@Path("/kafka")
public class KafkaResource {

    @Inject
    KafkaProducer kafkaProducer;

    @Inject
    KafkaConsumer kafkaConsumer;

    @GET
    @Path("/send")
    public String sendMessage(@QueryParam("message") String message) {
        kafkaProducer.sendMessage(message);
        return "Message sent: " + message;
    }

    @GET
    @Path("/receive")
    public String getMessage(@QueryParam("message") String message) {
        kafkaConsumer.consume(message);
        return "Message sent: " + message;
    }
}

