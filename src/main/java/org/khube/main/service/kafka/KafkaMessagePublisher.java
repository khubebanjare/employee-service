package org.khube.main.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public KafkaMessagePublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishToKafkaTopic(String message) {
        String topic = "employee-service-topic"; // Replace with your actual topic name
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, message);
        future.whenComplete((result, ex) -> {
            if (ex != null) {
                System.err.println("Error sending message to Kafka: " + ex.getMessage());
            } else {
                System.out.println("Message sent successfully to Kafka topic: " + topic + ", offset: " + result.getRecordMetadata().offset() + ", partition: " + result.getRecordMetadata().partition() + ", message: " + message);
            }
        });

    }
}
