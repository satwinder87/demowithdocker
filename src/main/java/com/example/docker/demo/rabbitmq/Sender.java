package com.example.docker.demo.rabbitmq;

import com.example.docker.demo.model.Customer;
import org.springframework.amqp.core.Message;
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

    public void send(Message message){
        rabbitTemplate.convertAndSend(exchangeName, "foo.bar.baz", message);
    }

    public void send(Customer customer){
        rabbitTemplate.convertAndSend(exchangeName, "foo.bar.baz", customer);
    }
}
