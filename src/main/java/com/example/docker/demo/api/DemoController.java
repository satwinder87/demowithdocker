package com.example.docker.demo.api;

import com.example.docker.demo.model.Customer;
import com.example.docker.demo.rabbitmq.Sender;
import com.example.docker.demo.repository.CustomerRepository;
import java.nio.charset.Charset;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private Sender sender;

    @GetMapping(value = "sayHello")
    public String helloWorld(){
        return "Hello ! From Docker World";
    }


    @PostMapping(value = "customer/create")
    public Customer addCustomer(@RequestBody Customer customer){
        System.out.println("Customer Received :" + customer.getFirstName());
        sender.send(customer.toString());
        System.out.println("Customer Sent :" + customer.getFirstName());
        return customerRepository.save(customer);
    }

    @PostMapping(value = "sendMessage")
    public void sendMessage(){
        Customer customer = new Customer();
        customer.setFirstName("test");
        customer.setLastName("val");

        Message messageWithHeader = MessageBuilder.withBody(customer.toJson().getBytes(Charset.defaultCharset()))
            .setType("EVENT_ROAM")
            .build();
        sender.send(messageWithHeader);
       // sender.send(customer);

    }

    @GetMapping(value = "customer/all")
    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

    @GetMapping(value = "customer/byFirstName/{firstName}")
    public List<Customer> customerByFirstName(@PathVariable String firstName){
        return customerRepository.findByFirstName(firstName);
    }

}
