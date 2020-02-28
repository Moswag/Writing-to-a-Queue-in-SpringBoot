package com.example.que;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableJms
public class QueApplication {
    @Autowired
    private JmsTemplate jmsTemplate;
    public static void main(String[] args) {
        SpringApplication.run(QueApplication.class, args);
    }

    @GetMapping("send")
    String send(){
        try{
            jmsTemplate.convertAndSend("EQUATION.TRANS.QUEUE", "Hello World!");
            return "OK";
        }catch(JmsException ex){
            ex.printStackTrace();
            return "FAIL";
        }
    }

}
