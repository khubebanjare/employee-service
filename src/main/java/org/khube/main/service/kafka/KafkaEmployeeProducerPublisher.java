package org.khube.main.service.kafka;

import org.khube.main.dto.EmployeeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import static org.khube.main.constants.EmployeeConstants.SEND_TO_KAFKA_OUT_0;

@Service
public class KafkaEmployeeProducerPublisher {


    private final StreamBridge streamBridge;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaEmployeeProducerPublisher.class);

    @Autowired
    public KafkaEmployeeProducerPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    /**
     * Publishes the EmployeeDto to the Kafka topic using StreamBridge.
     *
     * @param employeeDto the EmployeeDto to be published
     */
    public void publishToKafkaTopic(EmployeeDto employeeDto) {
        boolean kafkaFlag = streamBridge.send(SEND_TO_KAFKA_OUT_0, employeeDto);
            if (kafkaFlag) {
                LOGGER.info("Employee Data sent successfully to Kafka topic: {}", employeeDto);
            } else {
                LOGGER.error("Error sending Employee Data to Kafka");
            }

    }
}
