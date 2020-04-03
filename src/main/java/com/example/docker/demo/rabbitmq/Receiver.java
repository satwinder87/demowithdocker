package com.example.docker.demo.rabbitmq;

import com.example.docker.demo.model.Customer;
import java.nio.charset.Charset;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {


    @RabbitListener(queues = "demo")
    public void receiveMessage(Message message){

        String jsonCustomer = new String(message.getBody(),Charset.defaultCharset());

        System.out.println("Json String : " + jsonCustomer);

        Customer customer = Customer.fromJson(jsonCustomer);

        System.out.println(customer.toString());

        System.out.println("****************" + message.getMessageProperties().getType());

      //  System.out.println("Received <" + customer + ">" + ", Type =" + messageProperties.getType());
    }

/*


    @RabbitListener(queues = "demo")
    public void receiveMessage(Customer customer){


        System.out.println("Received <" + customer.toString() + ">");
    }
*/

}
