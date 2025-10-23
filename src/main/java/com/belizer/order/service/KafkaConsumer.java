package com.belizer.order.service;

import com.belizer.order.entity.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final OrderService orderService;

    @KafkaListener(topics = "test-topic", groupId = "my-group")
    public void listen(ConsumerRecord<String, String> record) {
        System.out.printf("Received message: topic - %s, partition - %d, offset - %d, key - %s, value - %s%n",
                record.topic(), record.partition(), record.offset(), record.key(), record.value());
    }


    @KafkaListener(topics = "order-topic", groupId = "my-group")
    public void listenCreateOrder(ConsumerRecord<String, String> record) throws JsonProcessingException {
        System.out.printf("Received message: topic - %s, partition - %d, offset - %d, key - %s, value - %s%n",
                record.topic(), record.partition(), record.offset(), record.key(), record.value());

        String value = record.value();
        ObjectMapper objectMapper = new ObjectMapper();
        Order order = objectMapper.readValue(value, Order.class);

        orderService.insert(order);
    }
}
