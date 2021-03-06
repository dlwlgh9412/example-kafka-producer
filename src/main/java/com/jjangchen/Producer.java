package com.jjangchen;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class Producer {
    private final static Logger logger = LoggerFactory.getLogger(Producer.class);
    private final static String TOPIC_NAME = "test";
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";

    public static void main(String[] args) {
        Properties configs = new Properties();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        // 직렬화 설정
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(configs);

        String msgKey = "jjang";
        String msgVal = "testMsg";
        //ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, msgVal);
        // 메시지 키 포함 데이터
        //ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, msgKey, msgVal);
        int partitionNo = 0;
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, partitionNo, msgKey, msgVal);
        producer.send(record);
        logger.info("{}", record);
        producer.flush();
        producer.close();
    }
}
