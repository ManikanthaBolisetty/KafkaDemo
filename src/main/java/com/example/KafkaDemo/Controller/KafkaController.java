package com.example.KafkaDemo.Controller;

import com.example.KafkaDemo.Consumer.ConsumerApi;
import com.example.KafkaDemo.Producer.ProducerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class KafkaController {

    @Autowired
    ProducerApi producerApi;

    @Autowired
    ConsumerApi consumerApi;

    @GetMapping("/publish")
    public ResponseEntity<String> publishMessage(@RequestParam("message") String message){
        producerApi.sendMessage(message);
        return ResponseEntity.ok("Successfully send the message to topic");

    }

    @GetMapping("/poll")
    public ResponseEntity<String> pollMessage(){
       String message= consumerApi.pollMessage();
        return ResponseEntity.ok(message);

    }
}
