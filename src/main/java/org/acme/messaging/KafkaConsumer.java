package org.acme.messaging;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class KafkaConsumer {

    @Incoming("my-kafka-topic")
    public void consume(String message) {
        System.out.println("Received message: " + message);
        // Process the message
    }
}

