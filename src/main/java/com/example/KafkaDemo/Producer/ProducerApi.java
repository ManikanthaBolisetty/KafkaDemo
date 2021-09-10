package com.example.KafkaDemo.Producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;

import java.util.Properties;
@Service
public class ProducerApi {

    public void sendMessage(String message){

        Properties properties= new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        KafkaProducer<String,String> firstProducer = new KafkaProducer<String, String>(properties);

        ProducerRecord<String,String> producerRecord= new ProducerRecord<String,String>("kafkaTesting", "This is my first message");

        firstProducer.send(producerRecord);
        firstProducer.flush();
        firstProducer.close();
    }


}
