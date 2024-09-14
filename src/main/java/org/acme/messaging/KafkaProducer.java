package org.acme.messaging;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class KafkaProducer {

    @Inject
    @Channel("my-kafka-producer")
    Emitter<String> emitter;

    public void sendMessage(String message) {
        emitter.send(message);
        System.out.println("Sent message: " + message);
    }
}

