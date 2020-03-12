package com.example.docker.demo.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {
    private final static String exchangeName="demo-exchange";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String message){
        rabbitTemplate.convertAndSend(exchangeName, "foo.bar.baz", message);
    }
}
