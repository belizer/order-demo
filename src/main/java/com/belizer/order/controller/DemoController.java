package com.belizer.order.controller;

import com.belizer.order.service.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {
    private final KafkaProducer kafkaProducer;

    @RequestMapping("/send")
    public String send(@RequestParam(name = "message") String message) {
        kafkaProducer.sendMessage("test-topic", message);
        return "success";
    }
}
