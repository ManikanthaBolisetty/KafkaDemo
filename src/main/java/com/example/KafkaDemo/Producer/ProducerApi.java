package com.example.KafkaDemo.Producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

        ProducerRecord<String,String> producerRecord= new ProducerRecord<String,String>("kafkaTesting", message);

        firstProducer.send(producerRecord, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {

                Logger logger= LoggerFactory.getLogger(ProducerApi.class);
                if(e==null){
                    logger.info("Successfully received details from "+ "Topic :" + recordMetadata.topic() + "partition:" + recordMetadata.partition()
                    + "Offset :" + recordMetadata.offset());
                }else{
                    logger.error("Can't deliver message to producer, error " + e.getMessage());
                }
            }
        });
        firstProducer.flush();
        firstProducer.close();
        
    }


}
