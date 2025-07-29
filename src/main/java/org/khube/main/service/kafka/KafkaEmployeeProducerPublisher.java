package org.khube.main.service.kafka;

import org.khube.main.dto.response.EmployeeResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

//@ConfigurationProperties(prefix = "kafka.topic")
@Service
public class KafkaEmployeeProducerPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaEmployeeProducerPublisher.class);

    @Value("${kafka.topic.employee}")
    private String employeeTopic;

    @Autowired
    public KafkaEmployeeProducerPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishToKafkaTopic(EmployeeResponseDto employeeDto) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(employeeTopic, employeeDto);
        future.whenComplete((result, ex) -> {
            if (ex != null) {
                LOGGER.error("Error sending Employee Data to Kafka: {}",  ex.getMessage());
            } else {
                LOGGER.info("Employee Data sent successfully to Kafka topic: {}, offset: {}, partition: {}, data: {}", employeeTopic, result.getRecordMetadata().offset(), result.getRecordMetadata().partition(), employeeDto);
            }
        });

    }
}
